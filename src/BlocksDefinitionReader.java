//package ass5;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author Yaron's Laptop
 * this class is used to read and parse the definitions.
 * of the blocks.
 */
public class BlocksDefinitionReader {
    /**
     * return a factory object that creates blocks from the reader of the block.
     * definitions.
     * @param reader the reader that reads the block definition file.
     * @return the factory that creates the blocks as specified.
     */
    public static BlocksFromSymbolsFactory fromReader(java.io.Reader reader) {
        Scanner scanner = new Scanner(new BufferedReader(reader));
        // set the delimitier to be the rows.
        scanner.useDelimiter("\\n");
        List<BlockCreator> definerList = new ArrayList<BlockCreator>();
        List<SpaceInfo> spaceInfos = new ArrayList<>();
        List<String> inputs = new ArrayList<>();
        // used for the default block values.
        BlockCreator defaultI = null;
        while (scanner.hasNext()) {
            inputs.add(scanner.next());
        }
        // read each string of the file.
        for (String i : inputs) {
            // if it is a comment we do nothing.
            if (i.contains("#")) {
                continue;
            } else if (i.contains("bdef")) { // the line is a block definition
                definerList.add(new BlockCreator(i));
            } else if (i.contains("default")) { // the line is a default definition
                defaultI = new BlockCreator(i);
            } else if (i.contains("sdef")) { // the line is a space definition
                spaceInfos.add(new SpaceInfo(i));
            }
        }
        try { // close the reader as we don't need it anymore.
            reader.close();
        } catch (IOException e) {
            System.out.println("problem closing block definition reader");
        }
        if (defaultI != null) { // if we have dafault values save them into the block.
            return new BlocksFromSymbolsFactory(spaceInfos, defaultI.copyDefaultsToBlocks(defaultI, definerList));
        } else { // we don't have them so we just use the blocks.
            return new BlocksFromSymbolsFactory(spaceInfos, definerList);
        }

    }
}