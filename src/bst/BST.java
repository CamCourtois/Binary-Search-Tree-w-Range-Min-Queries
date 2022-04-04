
package bst;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;


/**
 *
 * @author cameroncourtois
 */
public class BST {


    public static void main(String[] args) throws FileNotFoundException {
        BST theTree = new BST();
        String[] bstInstructions;
        String instructionLine;



        File inputFile = new File("inputFile.txt");
        Scanner in = new Scanner(inputFile);
        String numLines;
        numLines = in.nextLine();
        int numInstructions = Integer.parseInt(numLines);

        Integer[] minData = new Integer[1];
        minData[0] = null;


        for (int i = 0; i < numInstructions; i++) {
            instructionLine = in.nextLine();
            bstInstructions = instructionLine.split(" ");
            if (bstInstructions[0].equalsIgnoreCase("IN")) {
                int newKey = Integer.parseInt(bstInstructions[1]);
                int newData = Integer.parseInt(bstInstructions[2]);

                theTree.root = theTree.insert(theTree.root, newKey, newData, minData);

            } else if(bstInstructions[0].equalsIgnoreCase("RMQ")){

                int key1 = Integer.parseInt(bstInstructions[1]);
                int key2 = Integer.parseInt(bstInstructions[2]);

//                minData[0] = null;
//              theTree.rangeReportOne(theTree.root,key1,key2,minData);
//              System.out.println(minData[0]);
//                theTree.inOrderTraversal(theTree.root);
                theTree.rangeReportTwo(key1, key2, theTree.root);

            }
        }
        System.out.println(min);
    }
    public void inOrderTraversal(Node root)
    {
        if(root == null)
            return;
        inOrderTraversal(root.left);
        System.out.println("Key "+root.key+" LocalMinData "+root.localMinData);
        inOrderTraversal(root.right);
    }

    Node root;

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

    public void rangeReportOne(Node currentNode, int key1, int key2, Integer[] minData)
    {

        if(currentNode == null)
            return;
        if (!(currentNode.key< key1))
            rangeReportOne(currentNode.left,key1,key2, minData);
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
            rangeReportOne(currentNode.right, key1, key2, minData);
        }


    }

    public static int min;
    public void rangeReportTwo( int key1, int key2, Node currentNode)
    {
    if (currentNode != null) {
        while (!(key1 <= currentNode.key && currentNode.key <= key2)) {
            if (key1 < currentNode.key && key2 < currentNode.key)
                currentNode = currentNode.left;
            else
                currentNode = currentNode.right;
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
                min = minOfThree(min, currentNode.data, currentNode.right.localMinData);
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
                min = minOfThree(min, currentNode.data, currentNode.right.localMinData);
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
                min = minOfThree(min, currentNode.data, currentNode.left.localMinData);
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
                min = minOfThree(min, currentNode.data, currentNode.left.localMinData);
            else
                min = Math.min(min, currentNode.data);
        }

    }

    public int minOfThree(int num1, int num2, int num3) {
        return Math.min(Math.min(num1, num2), num3);
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
