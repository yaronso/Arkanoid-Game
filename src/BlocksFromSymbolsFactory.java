//package ass5;

import java.util.List;

/**
 * @author Yaron's Laptop
 * this is a factory class that is used to create blocks
 * by using the definitions.
 */
public class BlocksFromSymbolsFactory {
    private List<BlockCreator> definers;
    private List<SpaceInfo> spaceSymbols;

    /**
     * use the space and block information to create new blocks and return data.
     * about spacing.
     * @param spaces the list of space symbols.
     * @param def the list of block information and symbols.
     */
    public BlocksFromSymbolsFactory(List<SpaceInfo> spaces, List<BlockCreator> def) {
        this.definers = def;
        this.spaceSymbols = spaces;
    }

    /**
     * check if a string is a space symbol.
     * @param s the string we are checking.
     * @return true - it is a symbol. false - it is not.
     */
    public boolean isSpaceSymbol(String s) {
        for (SpaceInfo space : this.spaceSymbols) {
            if (space.getSymbol().equals(s)) {
                return true;
            }
        }
        return false;
    }

    /**
     * check if the string is a block symbol.
     * @param s the string we are checking.
     * @return true - it is a symbol. false - it is not.
     */
    public boolean isBlockSymbol(String s) {
        for (BlockCreator b : this.definers) {
            if (b.getSymbol().equals(s)) {
                return true;
            }
        }
        return false;
    }

    /**
     * create a new block based on the symbol and position.
     * @param s the symbol of the block.
     * @param xpos the x position.
     * @param ypos the y position.
     * @return the block we had created.
     */
    public Block getBlock(String s, int xpos, int ypos) {
        for (BlockCreator b : this.definers) {
            if (b.getSymbol().equals(s)) {
                return new Block(b, xpos, ypos);
            }
        }
        return null;
    }

    /**
     * get tbe width of the symbol.
     * @param s the symbol we want the width of.
     * @return the width.
     */
    public int getSpaceWidth(String s) {
        for (SpaceInfo space : this.spaceSymbols) {
            if (space.getSymbol().equals(s)) {
                return space.getWidth();
            }
        }
        return 0;
    }

    /**
     * get the width of the block with this symbol.
     * @param s the simbol we want the width of.
     * @return the width of the block.
     */
    public int getBlockWidte(String s) {
        for (BlockCreator b : this.definers) {
            if (b.getSymbol().equals(s)) {
                return b.getWidth();
            }
        }
        return 0;
    }

}
