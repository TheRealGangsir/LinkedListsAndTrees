import java.util.Scanner;

/**
 * Created on 10/5/2016, 2:29 PM
 *
 * @author Noah Morton
 *         Tully 7th period
 *         Part of project BinaryTreeAndTester
 */

@SuppressWarnings({"WeakerAccess", "unchecked"})
public class BinaryTree<E extends Comparable> {

    private TreeNode<E> root;

    public BinaryTree() {
        this.root = null;
    }

    /**
     * Recursively goes through the tree, printing it in pre-order format.
     *
     * @param t initial value to start from.
     */
    public void preOrder(TreeNode<E> t) {
        if (t == null)
            return;
        System.out.println(t.toString());
        preOrder(t.getLeft());
        preOrder(t.getRight());
    }

    /**
     * Recursively goes through the tree, printing it in in-order format.
     *
     * @param t initial value to start from.
     */
    public void inOrder(TreeNode<E> t) {
        if (t == null)
            return;
        inOrder(t.getLeft());
        System.out.println(t.toString());
        inOrder(t.getRight());
    }

    /**
     * Recursively goes through the tree, printing it in post-order format.
     *
     * @param t initial value to start from.
     */
    public void postOrder(TreeNode<E> t) {
        if (t == null)
            return;
        postOrder(t.getLeft());
        postOrder(t.getRight());
        System.out.println(t.toString());
    }

    /**
     * Get the smallest value in the tree.
     *
     * @return Smallest value in tree
     */
    public E minValue(TreeNode<E> nav) {
        while (nav.getLeft() != null) {
            nav = nav.getLeft();
        }
        return nav.getData();
    }

    /**
     * Gets the largest value in the tree
     *
     * @return Largest value in tree
     */
    public E maxValue() {
        TreeNode<E> nav = root; //start at root
        while (nav.getRight() != null) {
            nav = nav.getRight();
        }
        return nav.getData();
    }


    /**
     * Gets the deepest element in the tree
     *
     * @return Deepest element in the tree (Node with most ancestors)
     */
    public int maxDepth() {
        int l, r;
        if (root == null)
            return 0;
        else {
            l = helperMD(root.getLeft(), 1);
            r = helperMD(root.getRight(), 1);
        }
        if (l > r) return l;
        else return r;
    }

    public int helperMD(TreeNode<E> t, int depth) {
        int l, r;
        if (t == null)
            return depth;

        l = helperMD(t.getLeft(), depth + 1);
        r = helperMD(t.getRight(), depth + 1);
        if (l > r) return l;
        else return r;
    }

    public void clear() {
        root = null;
    }

    public int size() {
        finalSize = 0;
        return sizeInternal(root);
    }

    private int finalSize = 0;

    public int sizeInternal(TreeNode<E> t) {
        if (t == null)
            return 0;
        finalSize++;
        sizeInternal(t.getLeft());
        sizeInternal(t.getRight());
        return finalSize;
    }

    public boolean empty() {
        return root == null;
    }

    /**
     * Find if a value is in the tree.
     *
     * @param value value to check for
     * @return true if value is in the tree.
     */
    public boolean contains(E value) {
        if (root == null) //auto return false if root itself doesn't exist
            return false;

        TreeNode<E> nav = root; //start at root
        while (true) {
            if (value.compareTo(nav.getData()) == 0) { //if value is same, aka a match
                return true;
            } else if (value.compareTo(nav.getData()) >= 1) { //if value is larger
                if (nav.getRight() != null) nav = nav.getRight(); //move right if possible
                else return false;
            } else { //if value is smaller
                if (nav.getLeft() != null) nav = nav.getLeft(); //move left if possible
                else return false;
            }
        }
    }

    /**
     * Inserts a value into the tree
     *
     * @param value The value to attempt adding
     * @return Success of add, fails if already in tree.
     */
    public boolean insert(E value) {
        if (contains(value)) { //if tree already contains value
            return false;
        }
        if (root == null) { //if root doesn't exist
            root = new TreeNode<>(value);
            return true;
        }
        TreeNode<E> nav = root; //start at root
        while (true) {
            if (value.compareTo(nav.getData()) >= 1) { //if value is larger
                if (nav.getRight() == null) {
                    nav.setRight(new TreeNode<>(value));
                    return true;
                } else
                    nav = nav.getRight();
            } else if (value.compareTo(nav.getData()) <= -1) { //if value is smaller
                if (nav.getLeft() == null) {
                    nav.setLeft(new TreeNode<>(value));
                    return true;
                } else
                    nav = nav.getLeft();
            }
        }
    }

    /**
     * Removes a value from the tree.
     *
     * @param value the value to remove
     * @return Success of removal.
     */
    public boolean remove(E value) {
        if (!contains(value))  //if value is not in tree, cannot remove
            return false;
        if (value.equals(root.getData())) {
            System.out.println("Trying to remove root.");
            if (root.getLeft() == null && root.getRight() == null) { //no children
                root = null; //nullify root
                return true;
            } else if (root.getLeft() != null && root.getRight() == null) { //left child
                root = root.getLeft(); //set root to it's left
                return true;
            } else if (root.getLeft() == null && root.getRight() != null) { //right child
                root = root.getRight(); //set root to it's right
                return true;
            } else if (root.getRight() != null && root.getLeft() != null) { //two children
                E v = minValue(root.getRight());
                removeHelper(root, v);
                root.setData(v);
            }
        } else {
            System.out.println("Removing a value that is not root.");
            removeHelper(root, value);
        }
        return true;
    }

    public TreeNode<E> getRoot() {
        return root;
    }

    /**
     * Assists remove with removing values from the tree.
     *
     * @param node  node to start at.
     * @param value value to remove.
     */
    @SuppressWarnings("UnusedAssignment")
    public void removeHelper(TreeNode<E> node, E value) {
        //System.out.println("Node is currently: " + node);
        //System.out.println("Value is currently: " + value);
        if (value.compareTo(node.getData()) < 0) {
            System.out.println("left");
            if (node.getLeft().getData().equals(value)) {
                //System.out.println("Travelling left, node.left is " + node.getLeft());
                if (node.getLeft().getLeft() == null && node.getLeft().getRight() == null) //no children
                    node.setLeft(null);
                else if (node.getLeft().getLeft() != null && node.getLeft().getRight() == null) { //left child
                    node.setLeft(node.getLeft().getLeft());
                } else if (node.getLeft().getLeft() == null && node.getLeft().getRight() != null) { //right child
                    node.setLeft(node.getLeft().getRight());
                } else if (node.getLeft().getLeft() != null && node.getLeft().getRight() != null) { //two children
                    E v = minValue(node.getLeft().getRight());
                    removeHelper(node.getLeft(), v);
                    node.getLeft().setData(v);
                }
            } else
                removeHelper(node.getLeft(), value);
        } else {
            System.out.println("right");

            if (node.getRight().getData().equals(value)) {
                //System.out.println("Travelling right, node.right is " + node.getRight());
                if (node.getRight().getLeft() == null && node.getRight().getRight() == null) //no children
                    node.setRight(null);
                else if (node.getRight().getLeft() != null && node.getRight().getRight() == null) { //left child
                    node.setRight(node.getRight().getLeft());
                } else if (node.getRight().getLeft() == null && node.getRight().getRight() != null) { //right child
                    node.setRight(node.getRight().getRight());
                } else if (node.getRight().getLeft() != null && node.getRight().getRight() != null) { //two children
                    E v = minValue(node.getRight().getRight());
                    //System.out.println("Need to remove " + v + " and set my right to it.");
                    removeHelper(node.getRight(), v);
                    node.getRight().setData(v);
                }
            } else
                removeHelper(node.getRight(), value);
        }
    }

    /**
     * Prints a menu of print choices.
     */
    public void printSubMenu() {
        Scanner printChoice = new Scanner(System.in);
        System.out.println("What kind of print? 1. PreOrder, 2. InOrder, 3. PostOrder.");
        switch (printChoice.nextInt()) {
            case 1:
                preOrder(root);
                break;
            case 2:
                inOrder(root);
                break;
            case 3:
                postOrder(root);
                break;
            default:
                System.out.println("Invalid choice, not printing.");
        }
    }
}
