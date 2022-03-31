
package bst;
import java.io.Console;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;


/**
 *
 * @author cameroncourtois
 */
public class BST {



    public static void main(String[] args) throws FileNotFoundException
    {
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


        for(int i = 0; i < numInstructions; i++)
        {
            instructionLine = in.nextLine();
            bstInstructions = instructionLine.split(" ");
            if(bstInstructions[0].equalsIgnoreCase("IN")){
                int newKey = Integer.parseInt(bstInstructions[1]);
                int newData = Integer.parseInt(bstInstructions[2]);

                theTree.insert(newKey, newData, minData);
//                theTree.insertNodeOne(newKey, newData, minData);

            }
            else
            {
                int key1 = Integer.parseInt(bstInstructions[1]);
                int key2 = Integer.parseInt(bstInstructions[2]);

//                minData[0] = null;
//              theTree.rangeReportOne(theTree.root,key1,key2,minData);
                theTree.rangeReportTwo(key1, key2, theTree.root);
                System.out.println(minData[0]);

            }


        }

    }


    Node root;
    public void insert(int key, int data, Integer[] minData){
        Node newNode = new Node(key, data, minData);

        if(root == null){
            root = newNode;
            root.localMinData = root.data;
        }
        else {
                Node currentNode = root;
                Node parent;

                while(true){
                    parent = currentNode;
                    if(key < currentNode.key){
                        currentNode = currentNode.left;

                        if(currentNode == null){
                            parent.left = newNode;
                            root.localMinData = Math.min(root.localMinData, newNode.data);
                        }
                        return;
                    }
                    else
                    {
                        currentNode = currentNode.right;

                        if(currentNode == null){
                            parent.right = newNode;
                            root.localMinData = Math.min(root.localMinData, newNode.data);
//                        if(parent.data < parent.right.data){
//                            parent.right.localMinData = parent.right.data;
//
//                        }
                            return;
                        }
                    }
                }
//            if (root.key < newNode.key) {
//                root.localMinData = Math.min(root.localMinData, newNode.data);
//                insert(root.right, newNode);
//            } else {
//                root.localMinData = Math.min(root.localMinData, newNode.data);
//                insert(root.left, newNode);
//            }
        }
    }

//    Node root;
    public void insertNodeOne(int key, int data, Integer[] minData){

        Node newNode = new Node(key, data, minData);

        if(root == null){
            root = newNode;

        }
        else
        {
            Node currentNode = root;
            Node parent;

            while(true){
                parent = currentNode;

                if(key < currentNode.key){
                    currentNode = currentNode.left;

                    if(currentNode == null){
                        parent.left = newNode;

//                        if(parent.data < parent.left.data){
//                            parent.left.localMinData = parent.data;
//
//                        }
                        return;
                    }
                }
                else
                {
                    currentNode = currentNode.right;

                    if(currentNode == null){
                        parent.right = newNode;

//                        if(parent.data < parent.right.data){
//                            parent.right.localMinData = parent.right.data;
//
//                        }
                        return;
                    }
                }

            }

        }

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
    int min;
    public void rangeReportTwo(int key1, int key2, Node currentNode)
    {

        while (!(key1 <= currentNode.key && currentNode.key <= key2)){
            if(key1 < currentNode.key && key2 < currentNode.key)
                currentNode = currentNode.left;
            else
                currentNode = currentNode.right;
        }
        min = currentNode.data;
        rangeMinRight(key1, currentNode.left);
        rangeMinLeft(key2, currentNode.right);

    }
    public void rangeMinRight(int key1, Node currentNode)
    {
        if(key1 < currentNode.key){
            min = minOfThree(min, currentNode.data, currentNode.right.localMinData);
            rangeMinRight(key1, currentNode.left);
        }
        else if(key1 > currentNode.key){
            rangeMinRight(key1, currentNode.right);
        }
        else
            min = minOfThree(min, currentNode.data, currentNode.right.localMinData);
    }
    public void rangeMinLeft(int key2, Node currentNode){
        if(key2 < currentNode.key){
            min = minOfThree(min, currentNode.data, currentNode.left.localMinData);
            rangeMinLeft(key2, currentNode.left);
        }
        else if(key2 > currentNode.key){
            rangeMinLeft(key2, currentNode.right);
        }
        else
            min = minOfThree(min, currentNode.data, currentNode.left.localMinData);

    }

    public int minOfThree(int num1, int num2, int num3) {
        return Math.min(Math.min(num1, num2), num3);
    }


}

class Node
{
    int key;
    int data;
    Integer[] minData;
    int localMinData;
    Node left;
    Node right;
    

    public Node(int key, int data, Integer[] minData){
        this.key = key;
        this.data = data;
        this.minData = minData;
        this.localMinData = data;
    }

}
