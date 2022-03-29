
package bst;

/**
 *
 * @author cameroncourtois
 */
public class BST {
    
    public static void main(String[] args) 
    {
        BST theTree = new BST();
        theTree.insertNode(2, 31);
        theTree.insertNode(3, 24);
        theTree.insertNode(1, 11);
        theTree.insertNode(4, 4);
      
        Integer[] minData = new Integer[1]; 
        minData[0] = null;
        theTree.rangeReport(theTree.root, 2, 3, minData);

    }
    
    Node root;

    public void insertNode(int key, int data){

        Node newNode = new Node(key, data);
        
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
                        return;
                    }
                }
                else
                {
                    currentNode = currentNode.right;
                    
                    if(currentNode == null){
                        parent.right = newNode;
                        return;
                    }
                }
                    
            }
            
        }

    }
    public void rangeReport(Node currentNode, int key1, int key2, Integer[] minData)
    {    
        if(currentNode == null)
            return;
        if (!(currentNode.key< key1))
            rangeReport(currentNode.left,key1,key2, minData); 
        if ((currentNode.key >= key1) && (currentNode.key <=key2)){
            if(minData[0] == null){
                minData[0] = currentNode.data;
            }
            else if(currentNode.data < minData[0])
            {
                minData[0] = currentNode.data;
                
            }
            
            System.out.println(minData[0]);
        }
        if (!(currentNode.key > key2)){
            rangeReport(currentNode.right, key1, key2, minData);
        }
        
        
    }
    
    public void setMinData(){
    
    }
            
}

class Node
{
    int key;
    int data;
    Node left;
    Node right;
    

    public Node(int key, int data){
        this.key = key;
        this.data = data;
    }

}
