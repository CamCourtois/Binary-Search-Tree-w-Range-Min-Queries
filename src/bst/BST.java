
package bst;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;


/**
 *
 * @author cameroncourtois
 */
public class BST {

    Node root;
    public static void main(String[] args) throws FileNotFoundException {

        BST theTree1 = new BST();
        BST theTree2 = new BST();
        BST theTree3 = new BST();
//        String[] bstInstructions;
//        String instructionLine;
//
//
//
//        File inputFile = new File("inputFile.txt");
//        Scanner in = new Scanner(inputFile);
//        String numLines;
//        numLines = in.nextLine();
//        int numInstructions = Integer.parseInt(numLines);

        Integer[] minData = new Integer[1];
        minData[0] = null;



//        for (int i = 0; i < numInstructions; i++) {
//            instructionLine = in.nextLine();
//            bstInstructions = instructionLine.split(" ");
//            if (bstInstructions[0].equalsIgnoreCase("IN")) {
//                int newKey = Integer.parseInt(bstInstructions[1]);
//                int newData = Integer.parseInt(bstInstructions[2]);
//
//                theTree.root = theTree.insert(theTree.root, newKey, newData, minData);
//
//            } else if(bstInstructions[0].equalsIgnoreCase("RMQ")){
//
//                int key1 = Integer.parseInt(bstInstructions[1]);
//                int key2 = Integer.parseInt(bstInstructions[2]);
//
//                theTree.rangeReportOne(theTree.root, key1, key2, minData);
//                theTree.rangeReportTwo(key1, key2, theTree.root);
//
//            }
//            else
//                return;
//        }
        Random rand = new Random();

        double start = System.currentTimeMillis();
        for(int i = 0; i <= 3000; i++){
            int newKey = rand.nextInt(32767);
            int newData = rand.nextInt(32767);
            theTree1.root = theTree1.insert(theTree1.root, newKey, newData, minData);
        }
        double elapsed = System.currentTimeMillis() - start;
        System.out.printf(" time to build tree 1: %.4f ms\n", elapsed);

        start = System.currentTimeMillis();
        for(int i = 0; i <= 10000; i++){
            int newKey = rand.nextInt(32767);
            int newData = rand.nextInt(32767);
            theTree2.root = theTree2.insert(theTree2.root, newKey, newData, minData);
        }
        elapsed = System.currentTimeMillis() - start;
        System.out.printf(" time to build tree 2: %.4f ms\n", elapsed);

        start = System.currentTimeMillis();
        for(int i = 0; i <= 30000; i++){
            int newKey = rand.nextInt(32767);
            int newData = rand.nextInt(32767);
            theTree3.root = theTree3.insert(theTree3.root, newKey, newData, minData);
        }
        elapsed = System.currentTimeMillis() - start;
        System.out.printf(" time to build tree 3: %.4f ms\n", elapsed);

        //RMQs on Tree 1
        System.out.println("RMQ's on Tree 1");
        start = System.currentTimeMillis();
        for(int i = 0; i <= 1000; i++){
            int key1 = rand.nextInt(32767);
            int key2 = rand.nextInt(32767);
            theTree1.rangeReportOne(key1, key2, theTree1.root, minData);
        }
        elapsed = System.currentTimeMillis() - start;
        System.out.printf("Random RMQs using Method 1: %.4f ms", elapsed);
//
//        start = System.currentTimeMillis();
//        for(int i = 0; i <= 1000; i++){
//            int key1 = rand.nextInt(32767);
//            int key2 = rand.nextInt(32767);
//            theTree1.rangeReportTwo(key1, key2, theTree1.root);
//        }
//        elapsed = System.currentTimeMillis() - start;
//        System.out.printf("Random RMQs using Method 2 %.4f", elapsed);



    }
    public void inOrderTraversal(Node root)
    {
        if(root == null)
            return;
        inOrderTraversal(root.left);
        System.out.println("Key "+root.key+" LocalMinData "+root.localMinData);
        inOrderTraversal(root.right);
    }


    public Node insert(Node root, int key, int data, Integer[] minData) {

        if (root == null) {
            root = new Node(key, data, minData);

            return root;
        }
        if (key < root.key) {
            root.left = insert(root.left, key, data, minData);
            root.localMinData = Math.min(root.left.localMinData, root.localMinData);
        }
        else
        {
            root.right = insert(root.right, key, data, minData);
            root.localMinData = Math.min(root.right.localMinData, root.localMinData);
        }
        return root;
    }

    public void rangeReportOne(int key1, int key2, Node currentNode, Integer[] minData)
    {
        if(currentNode == null)
            return;
        if (!(currentNode.key< key1))
            rangeReportOne(key1,key2, currentNode.left, minData);
        if ((currentNode.key >= key1) && (currentNode.key <=key2)){
            if(minData[0] == null){
                minData[0] = currentNode.data;
            }
            else if(currentNode.data < minData[0])
            {
                minData[0] = currentNode.data;

            }
        }
        if (!(currentNode.key > key2)){
            rangeReportOne(key1, key2,currentNode.right, minData);
        }

    }

    public static int min;
    public void rangeReportTwo( int key1, int key2, Node currentNode)
    {
    if (currentNode != null) {
        while (!((key1 <= currentNode.key) && (currentNode.key <= key2))) {
            if ((key1 < currentNode.key) && (key2 < currentNode.key)){
                currentNode = currentNode.left;
            } else {
                currentNode = currentNode.right;
            }
        }

        min = currentNode.data;

        rangeMinRight(key1, currentNode.left);
        rangeMinLeft(key2, currentNode.right);
    }

    }
    public void rangeMinRight(int key1, Node currentNode)
    {
        if(currentNode == null){
            return;
        }
        else if(key1 < currentNode.key){
            if(currentNode.right != null) {
                min = Math.min(min, currentNode.right.localMinData);
                rangeMinRight(key1, currentNode.left);
            }
            else{
                min = Math.min(min, currentNode.data);
                rangeMinRight(key1, currentNode.left);
            }
        }
        else if(key1 > currentNode.key){
                rangeMinRight(key1, currentNode.right);
        }
        else {
            if(currentNode.right != null){
                min = Math.min(min, currentNode.right.localMinData);
            }
            else
                min = Math.min(min, currentNode.data);
        }
    }
    public void rangeMinLeft(int key2, Node currentNode){
        if(currentNode == null) {
            return;
        }
        else if (key2 > currentNode.key) {
            if(currentNode.left != null) {
                min = Math.min(min, currentNode.left.localMinData);
                rangeMinLeft(key2, currentNode.right);
            }
            else{
                min = Math.min(min, currentNode.data);
                rangeMinLeft(key2, currentNode.right);
            }
        }
        else if(key2 < currentNode.key){
                rangeMinLeft(key2, currentNode.left);
        }
        else{
            if(currentNode.left != null)
                min = Math.min(min, currentNode.left.localMinData);
            else
                min = Math.min(min, currentNode.data);
        }

    }
}

class Node
{
    public int key;
    public int data;
    Integer[] minData;
    public int localMinData;
    public Node left;
    public Node right;
    

    public Node(int key, int data, Integer[] minData){
        this.key = key;
        this.data = data;
        this.minData = minData;
        this.localMinData = data;
    }

}
