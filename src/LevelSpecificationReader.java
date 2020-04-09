//package ass5;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Yaron's Laptop
 *  this class is used to read an parse an level.
 * specification file.
 */
public class LevelSpecificationReader {

    /**
     * read the file and return the list of levels it contains.
     * @param reader the reader we are using to read the file.
     * @return the list of level we had parsed from the file.
     */
    public List<LevelInformation> fromReader(java.io.Reader reader) {
        // create a list of level inforamtion.
        List<LevelInformation> information = new ArrayList<LevelInformation>();
        // create a list of strings to save the levels name.
        List<String> levels = new ArrayList<String>();
        // create a scanner for saving the strings.
        Scanner scanner = new Scanner(reader);
        // set the delimiter we want to use.
        scanner.useDelimiter("END_LEVEL");
        String input;
        // read the the string between the delimiter.
        while (scanner.hasNext()) {
            levels.add(scanner.next());
        }
        // read each level by itself.
        for (String s : levels) {
            if (s.length() > 1) {
                information.add(readSingleSpecification(s));
            }
        }
        try { // close the reader after we are finished reading.
            reader.close();
        } catch (IOException e) {
            System.out.println("unable to close level specification file.");
        }
        return information;
    }
    // seperate the file by lines

    /**
     * get a string containing only 1 level and parse it into a level we can run.
     * @param level the string that containes the whole level.
     * @return the level we have parsed from that string.
     */
    public LevelInformation readSingleSpecification(String level) {
        List<String> blocks = new ArrayList<>();
        // split the level into the levels thing and the block string.
        String[] levelNblocks = level.split("START_BLOCKS");
        // to contain the lines.
        List<String> strings = new ArrayList<>();
        // the level string without the blocks. we split them to lines.
        String[] splited = levelNblocks[0].split("\\n");
        // add the splited lines to the list.
        for (int i = 0; i < splited.length; i++) {
            strings.add(splited[i]);
        }
        if (strings.size() > 0) { // make sure every thing was ok.
            // create a new level from the string.
            PrasedLevel prased = getLevelFromString(strings);
            // put the string of the blocks into the level.
            prased.setBlocks(levelNblocks[1]);
            return prased;
        }
        return null;
    }

    /**
     * parse the string of the level and return a level containing all of the data.
     * @param list the lines of the file.
     * @return a new level information.
     */
    public PrasedLevel getLevelFromString(List<String> list) {
        PrasedLevel level = new PrasedLevel();
        String[] line;
        for (String l : list) { // for each line in the file.
            line = l.split(":"); // split it by the double dots.
            if (line.length > 1) { // make sure we have value and key.
                // replace unwanted characters.
                line[1] = line[1].replaceAll("\\)", "");
                line[1] = line[1].replaceAll("\\r", "");
                switch (line[0]) { // choose the right task based on the key
                case "level_name":
                    level.setLevelName(line[1]);
                    break;
                case "ball_velocities":
                    level.setBallVelo(velocitiesFromString(line[1]));
                    break;
                case "background":
                    level.setBackGround(new BackGround(line[1]));
                    break;
                case "paddle_speed":
                    level.setPaddleSpeed(Integer.parseInt(line[1]));
                    break;
                case "paddle_width":
                    level.setPaddleWidth(Integer.parseInt(line[1]));
                    break;
                case "block_definitions":
                    level.setBlockDef(line[1]);
                    break;
                case "blocks_start_x":
                    level.setBlockStartX(Integer.parseInt(line[1]));
                    break;
                case "blocks_start_y":
                    level.setBlockStartY(Integer.parseInt(line[1]));
                    break;
                case "row_height":
                    level.setRowHeight(Integer.parseInt(line[1]));
                    break;
                case "num_blocks":
                    level.setBlockNum(Integer.parseInt(line[1]));
                    break;
                default:
                    break;
                }
            }
        }
        return level;
    }

    /**
     * get the velocites of the ball from the string that containes their line.
     * @param s the string that containes the velocities.
     * @return a list of ball velocities.
     */
    public List<Velocity> velocitiesFromString(String s) {
        // find the pattern of the numbers.
        Matcher m = Pattern.compile("[-+]?[0-9]*\\.?[0-9]").matcher(s);
        List<Double> numbers = new ArrayList<Double>();
        List<Velocity> velocities = new ArrayList<Velocity>();
        while (m.find()) { // read each number into our list.
            numbers.add(Double.parseDouble(m.group()));
        }
        // if we have an odd number of balls it is an error.
        if (numbers.size() % 2 != 0) {
            return null;
        } else { // we have an even number of velocites.
            // read the numbers list and create the right velocity from them.
            for (int i = 0; i < numbers.size(); i = i + 2) {
                velocities.add(Velocity.fromAngleAndSpeed(numbers.get(i), numbers.get(i + 1)));
            }
        }
        return velocities;
    }

}
