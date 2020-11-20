/**
 * Represents a position in Path Myopic Col.
 *
 * @author Kyle Burke <paithanq@gmail.com>
 * 
 */
 
import java.lang.*;
import java.io.*;
import java.util.*;

public class PathMyopicCol extends CombinatorialGame {
    
    /* constants */

    /**
     * Represents uncolored.
     */
    public static int UNCOLORED = 2;
    
    //represents the color names
    private String[] colorNames = new String[] {"Blue", "Red ", "    "};

    /* instance variables */
    
    //Array of references to the head of each path.
    private PureLinkedList<Integer> path;

    /**
     * Class constructor for a board with randomly-colored spaces.
     *
     * @param length  The length of the path to create.
     * @param density  The probability that each vertex will be colored.
     * @throws IllegalArgumentException  If any of the lengths is non-positive.
     */
    public PathMyopicCol(int length, double density) throws IllegalArgumentException {
        if (length <1) {
            throw new IllegalArgumentException("Tried to create a path with non-positive length of " + length + "!");
        }
        //first, copy the lengths
        Random random = new Random();
        int color=UNCOLORED;
        if (random.nextDouble() <= density) {
            if (random.nextDouble() < .5) {
                color = CombinatorialGame.LEFT;
            } else {
                color = CombinatorialGame.RIGHT;
            }
        }
        this.path = new PureLinkedList<Integer>(color);
        for (int i = 1; i < length; i++) {
            color = UNCOLORED;
            if (random.nextDouble() <= density) {
                if (random.nextDouble() < .5) {
                    color = CombinatorialGame.LEFT;
                } else {
                    color = CombinatorialGame.RIGHT;
                }
            }
            this.path.add(new Integer(color));
        }
    }
    
    /**
     * Constructor for a board with spaces that may be colored.
     * 
     * @param path  LinkedList that represents the colors of this board.
     */
    public PathMyopicCol(PureLinkedList<Integer> path) {
        //this.paths = new ArrayList<PureLinkedList<Integer>>();
        this.path = this.copyPath(path);
        ArrayList<Integer> legalColors = new ArrayList<Integer>();
        legalColors.add(UNCOLORED);
        legalColors.add(CombinatorialGame.LEFT);
        legalColors.add(CombinatorialGame.RIGHT);
        for (int i = 0; i < this.path.length(); i++) {
            if (!legalColors.contains(this.path.get(i))) {
                throw new IllegalArgumentException("Tried to create a path with an illegal color: " + this.path.get(i));
            }
        }
    }
    
    //copies a path
    private static PureLinkedList<Integer> copyPath(PureLinkedList<Integer> path) {
        PureLinkedList<Integer> pathCopy = new PureLinkedList<Integer>(path.getFirst());
        PureLinkedList<Integer> pathCopyHead = pathCopy; //alias the first element of pathCopy
        
        while (!path.isLast()) {
            //add the next thing in path to pathCopy
            path = path.getTail();
            pathCopy.setTail(new PureLinkedList<Integer>(path.getFirst()));
            pathCopy = pathCopy.getTail();
        }
        return pathCopyHead;
    }
    
    /* public methods */
    
    /**
     * Returns a String version of this.
     * 
     * @return  A String description of this.
     */
    public String toString() {
        StringBuffer buffer = new StringBuffer();
        PureLinkedList<Integer> path = this.path;
        while (path != null) {
            Integer color = path.getFirst();
            buffer.append("[" + this.getColorName(color) + "]");
            if (!path.isLast()) {
                buffer.append("-->");
            }
            path = path.getTail();
        }
        return buffer.toString();
    }
    
    /**
     * Clones this.
     *
     * @return  A deep clone of this.
     */
    public CombinatorialGame clone() {
        return new PathMyopicCol(this.path);
    }
    
    /**
     * Determines whether two positions are identical.
     * 
     * @param other  The other position to compare to this.
     */
    public boolean equals(CombinatorialGame other) {
        PathMyopicCol otherCol;
        try {
            otherCol = (PathMyopicCol) other;
            return this.path.equals(otherCol.path);
        } catch (ClassCastException cce) {
            //other isn't a PathMyopicCol instance
            return false;
        }
    }
    
    /**
     * Gets the paths.
     * 
     * @return  A copy of the paths of this.
     */
    public PureLinkedList<Integer> getPath() {
        return copyPath(this.path);
    }
    
    @Override
    public Collection<CombinatorialGame> getOptions(int player) {
        Collection<CombinatorialGame> options = new ArrayList<CombinatorialGame>();
        for (int nodeIndex = 0; nodeIndex < this.path.length(); nodeIndex ++) {
            int nodeColor = this.path.get(nodeIndex).intValue();
            if (nodeColor == UNCOLORED) {
                if ((nodeIndex == this.path.length() - 1) ||
                    (this.path.get(nodeIndex + 1).intValue() != player)) {
                    //coloring the current node is a legal move.  Let's generate it!
                    //first, copy the position
                    PathMyopicCol option = (PathMyopicCol) this.clone();
                    //then, set the new color
                    //System.out.println("nodeIndex: " + nodeIndex);
                    //System.out.println("nodeColor: " + nodeColor);
                    //System.out.println("this.path: " + this.path);
                    option.path.set(nodeIndex, new Integer(player));
                    options.add(option);
                }
            }
        }
        return options;
    }
    
    /* public classes */
    
    public static class PositionBuilder implements PositionFactory<PathMyopicCol> {
    
        //minimum length of a path
        private int minPathLength;
        
        //maximum length of a path
        private int maxPathLength;
        
        //likelihood of coloring
        private double colorDensity;
        
        /**
         *  Class constructor.
         * 
         * @param minPathLength  The shortest possible length of the path.
         * @param maxPathLength  The longest possible length of the path.
         * @param colorDensity  The probability that a given vertex is colored.
         */
        public PositionBuilder(int minPathLength, int maxPathLength, double colorDensity) {
            this.minPathLength = minPathLength;
            this.maxPathLength = maxPathLength;
            this.colorDensity = colorDensity;
        }
        
        @Override
        public PathMyopicCol getPosition() {
            Random random = new Random();
            int length = minPathLength + random.nextInt(maxPathLength - minPathLength);
            return new PathMyopicCol(length, colorDensity);
        }
    } //end of PositionFactory
    
    /* private methods */
    
    //gets the name of the color
    private String getColorName(int colorCode) {
        return this.colorNames[colorCode];
    }
    
    //gets the name of the color
    private String getColorName(Integer colorCode) {
        return this.getColorName(colorCode.intValue());
    }
    
    /* main method for testing */
    
    /**
     * Tests the functionality of this class.
     * 
     * @param args  Strings used in command line tests of this.
     */
    public static void main(String[] args) {
    
        PositionFactory<PathMyopicCol> factory = new PathMyopicCol.PositionBuilder(6, 10, .2);
        System.out.println(factory.getPosition());
        //this is all for the old version, which had multiple lists!
        /*
        //test the empty-game constructor
        ArrayList<Integer> lengths = new ArrayList<Integer>();
        lengths.add(3);
        lengths.add(5);
        PathMyopicCol empty = new PathMyopicCol(lengths);
        System.out.println("An empty 3-5 position:");
        System.out.println(empty);
        
        //test the partially-played constructor with one path
        ArrayList<PureLinkedList<Integer>> paths = new ArrayList<PureLinkedList<Integer>>();
        PureLinkedList<Integer> path = new PureLinkedList<Integer>(CombinatorialGame.RIGHT);
        path.add(CombinatorialGame.LEFT);
        path.add(UNCOLORED);
        path.add(CombinatorialGame.LEFT);
        path.add(UNCOLORED);
        path.add(CombinatorialGame.RIGHT);
        paths.add(path);
        PathMyopicCol onePath = new PathMyopicCol(paths);
        System.out.println("A position with one path: \n" + onePath);
        //now add another path
        path = new PureLinkedList<Integer>(UNCOLORED);
        path.add(CombinatorialGame.LEFT);
        path.add(CombinatorialGame.LEFT);
        path.add(CombinatorialGame.RIGHT);
        paths.add(path);
        PureLinkedList<Integer> blankPath = new PureLinkedList<Integer>(UNCOLORED);
        blankPath.add(UNCOLORED);
        paths.add(blankPath);
        paths.add(path);
        System.out.println("Should still just have one path: \n" + onePath);
        
        //test the constructor on a game with many paths
        PathMyopicCol multiPaths = new PathMyopicCol(paths);
        System.out.println("Should have more paths: \n" + multiPaths);
        
        //test number of paths
        System.out.println("Should say 1: " + onePath.numberOfPaths());
        System.out.println("Should say 4: " + multiPaths.numberOfPaths());
        
        //test clone
        PathMyopicCol threePathsClone = (PathMyopicCol) multiPaths.clone();
        System.out.println("Copy of multiPaths: \n" + threePathsClone);
        
        //test equals
        //easy true tests
        System.out.println("Should say true: " + multiPaths.equals(threePathsClone));
        System.out.println("Should say true: " + threePathsClone.equals(multiPaths));
        //false tests
        System.out.println("Should say false: " + onePath.equals(multiPaths));
        System.out.println("Should say false: " + multiPaths.equals(new PathMyopicCol(new ArrayList<PureLinkedList<Integer>>())));
        //more complex true tests
        paths = new ArrayList<PureLinkedList<Integer>>();
        paths.add(blankPath);
        paths.add(path);
        paths.add(path);
        path = new PureLinkedList<Integer>(CombinatorialGame.RIGHT);
        path.add(CombinatorialGame.LEFT);
        path.add(UNCOLORED);
        path.add(CombinatorialGame.LEFT);
        path.add(UNCOLORED);
        path.add(CombinatorialGame.RIGHT);
        paths.add(path);
        PathMyopicCol reorderedPaths = new PathMyopicCol(paths);
        System.out.println("Should say true: " + multiPaths.equals(reorderedPaths));
        System.out.println("Should say true: " + reorderedPaths.equals(multiPaths));
        
        //test getPaths
        System.out.println("Positions as lists of integers:");
        paths = onePath.getPaths();
        System.out.println("Single-path position:");
        for (PureLinkedList<Integer> pathList : paths) {
            System.out.println(pathList);
        }
        paths = multiPaths.getPaths();
        System.out.println("Multi-path position:");
        for (PureLinkedList<Integer> pathList : paths) {
            System.out.println(pathList);
        }
        
        //test getOptions
        System.out.println("Left options of single-path game: ");
        System.out.println("Reminder: Single-Path Game = " + onePath);
        for (CombinatorialGame option : onePath.getOptions(CombinatorialGame.LEFT)) {
            System.out.println(option);
        }
        System.out.println("Right options of single-path game: ");
        for (CombinatorialGame option : onePath.getOptions(CombinatorialGame.RIGHT)) {
            System.out.println(option);
        }
        System.out.println("Left options of multi-path game: ");
        for (CombinatorialGame option: multiPaths.getOptions(CombinatorialGame.LEFT)) {
            System.out.println(option);
        }
        System.out.println("Right options of multi-path game: ");
        for (CombinatorialGame option: multiPaths.getOptions(CombinatorialGame.RIGHT)) {
            System.out.println(option);
        }
        */
    }

} //end of PathMyopicCol.java