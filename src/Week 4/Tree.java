import java.util.Collections;
import java.util.List;

public class Tree {
    // global variable declaration
    private int mValue; // current Tree's value
    private List<Tree> mChildren; // current Tree's List of children Tree
    private int currLongestPath = 1; // longest path of Tree with consecutive values

    /**
     * default Tree constructor
     *
     * @param value value of Tree node
     * @param children list of Children Trees
     */
    public Tree(int value, List<Tree> children) {
        mValue = value; // assigns value to global var
        mChildren = Collections.unmodifiableList(children); // assigns list to unmodifiable global var
    }

    /**
     * longestPath() method defined by default
     *
     * @return longest path in the tree structure
     */
    public int longestPath() {
        return longestPath(mValue, mChildren); // calls the overloaded longestPath() function
    }

    /**
     * longestPath() method overloaded
     *
     * @param mValue current node's value
     * @param mChildren current node's Children Trees
     * @return overall longest parent path
     */
    private int longestPath(int mValue, List<Tree> mChildren) {
        findLongestPath(mValue, mChildren); // calls void method that searches through children nodes
        return currLongestPath;
    }

    /**
     * overloaded void function that sets current node as Parent
     *
     * @param mValue current node's value
     * @param mChildren current node's Children
     */
    private void findLongestPath(int mValue, List<Tree> mChildren) {
        if (!mChildren.isEmpty()) {
            for (Tree head : mChildren) {
                if (head.mValue == mValue - 1) { // if is a smaller consecutive
                    findLongestPath(head.mValue, head.mChildren, -1, 2); // set dir to down and searches
                } else if ((head.mValue == mValue + 1)) { // if is a larger consecutive
                    findLongestPath(head.mValue, head.mChildren, 1, 2); // set dir to up and searches
                } else { // if this Children is not a consecutive
                    findLongestPath(head.mValue, head.mChildren); // sets current Child as Parent and search
                }
            }
        }
    }

    /**
     * overloaded void function that continues to find the list in the correct direction
     *
     * @param mValue current node's value
     * @param mChildren current node's Children
     * @param dir 1 for upwards, -1 for downwards
     * @param currLength current transversed consecutive length
     */
    private void findLongestPath(int mValue, List<Tree> mChildren, int dir, int currLength) {
        if (!mChildren.isEmpty()) {
            int counter = 0; // counts how many times the Children are not consecutive
            for (int i = 0; i < mChildren.size(); i++) {
                Tree head = mChildren.get(i); // assigning current Tree to head to reduce access
                if (head.mValue == mValue + dir) { // if this Child is a correct consecutive in direction
                    findLongestPath(head.mValue, head.mChildren, dir, ++currLength); // continues search
                } else { // if this Child is not a consecutive
                    if (++counter == mChildren.size()) { // checks if this is the last Child
                        currLongestPath = Math.max(currLongestPath, currLength); // updates currLongestPath
                    }
                    longestPath(head.mValue, head.mChildren); // sets current Child as Parent and search
                }
            }
        } else { // if the current Children is empty
            currLongestPath = Math.max(currLongestPath, currLength); // updates currLongestPath
        }
    }
}
