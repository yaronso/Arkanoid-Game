//package ass5;

/**
 * @author Yaron's Laptop
 *  this class is used to store the information about.
 *  spaces in the block list.
 */
public class SpaceInfo {
    private int width;
    private String symbol;

    /**
     * create a new space info based on the parsing of the "sdef" line in the block
     * definition file.
     * @param space the "sdef" line.
     */
    public SpaceInfo(String space) {
        // split the line into words.
        String[] spaceSplited = space.split(" ");
        for (int i = 1; i < 2; i++) { // go over the line
            String[] dotSplit = spaceSplited[i].split(":");
            switch (dotSplit[0]) { // parse by key value.
            case "symbol":
                this.symbol = dotSplit[1];
                break;
            case "width":
                this.width = Integer.parseInt(dotSplit[1]);
                break;
            default:
                break;
            }
        }
    }

    /**
     * return the width of the space.
     * @return the width.
     */
    public int getWidth() {
        return this.width;
    }

    /**
     * the symbol of the space.
     * @return the symbol.
     */
    public String getSymbol() {
        return this.symbol;
    }
}
