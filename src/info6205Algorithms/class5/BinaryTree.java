package info6205Algorithms.class5;

import java.util.*;

public class BinaryTree {
    public static void main(String[] args){
        Tree tree = new Tree();
        System.out.println("---preOrder---");
        tree.preOrder();
        System.out.println("---inOrder---");
        tree.inOrder();
        System.out.println("---postOrder---");
        tree.postOrder();
        System.out.println("size->"+tree.getSize());
        System.out.println("height->"+tree.getHeight());
        System.out.println("---print level order---");
        tree.printLevelOrder();
        System.out.println("---print zigzag---");
        tree.printZigZag();
        System.out.println("---print all leaves---");
        tree.printAllLeaves();
        System.out.println("---print perimeter---");
        tree.printPerimeter();
        tree.root.left.left.left.right = new Node(12);
        System.out.println("---print left view---");
        tree.printLeftView();
        System.out.println("---print right view---");
        tree.printRightView();

        System.out.println("---print top view---");
        tree.printTopView();
    }


}

class Node{
    public int data;

    public Node left;

    public Node right;

    private Node(){

    }

    public Node(int data){
        this.data = data;
    }
}

class Tree{
    public Node root;

    public Tree() {
        createTree();
    }

    private void createTree(){
        Node node = new Node(1);
        root = node;

        root.left = new Node(2);
        root.right = new Node(3);

        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.left.left.left = new Node(8);
        root.left.left.right = new Node(9);

        root.left.right.right = new Node(10);

        root.right.left = new Node(6);
        root.right.right = new Node(7);

        root.right.right.right = new Node(11);
    }

    class QItem{
        public Node node;
        public int distance;
        QItem(Node node,int distance){
            this.node = node;
            this.distance = distance;
        }
    }

    public void printTopView(){
        Set<Integer> set = new HashSet<>();
        Queue<QItem> queue = new LinkedList<>();
        QItem qItem = new QItem(root,0);
        queue.add(qItem);
        while (queue.size()!=0){
            qItem = queue.remove();
            int key = qItem.distance;
            Node node = qItem.node;
            if(!set.contains(key)){
                set.add(key);
                System.out.print(node.data+"  ");
            }
            if (node.left!=null){
                queue.add(new QItem(node.left,key-1));
            }
            if(node.right!=null){
                queue.add(new QItem(node.right,key+1));
            }
        }
    }
    /**public void printTopView(){
     HashMap<Integer,Integer> map = new HashMap<>();
     printTopView(root,0,map);

     System.out.println();
     }

     private void printTopView(Node node,int key, HashMap<Integer,Integer> map){
     if(node != null){
     if(!map.containsKey(key)) {
     map.put(key, node.data);
     System.out.print(node.data + ",");
     printTopView(node.left,key-1,map);
     printTopView(node.right,key+1,map);
     }
     }
     }**/

    //data -> left -> right
    public void preOrder(){
        preOrder(root);
        System.out.println();
    }

    private void preOrder(Node node){
        if(node != null){
            System.out.print(node.data+",");
            preOrder(node.left);
            preOrder(node.right);
        }
    }

    //left -> data -> right
    public void inOrder(){
        inOrder(root);
        System.out.println();
    }

    private void inOrder(Node node){
        if(node != null){
            inOrder(node.left);
            System.out.print(node.data+",");
            inOrder(node.right);
        }
    }

    //left -> right -> data
    public void postOrder(){
        postOrder(root);
        System.out.println();
    }

    private void postOrder(Node node){
        if(node != null){
            postOrder(node.left);
            postOrder(node.right);
            System.out.print(node.data+",");
        }
    }

    public int getSize(){
        return getSize(root);
    }

    private int getSize(Node node){
        if(node == null)
            return 0;
        return getSize(node.left) + 1 + getSize(node.right);
    }

    public int getHeight(){
        return getHeight(root);
    }

    private int getHeight(Node node){
        if(node == null)
            return 0;
        int lHeight = getHeight(node.left);
        int rHeight = getHeight(node.right);
        return 1+Math.max(lHeight,rHeight);
    }

    public void printRightView(){
        if(root == null)
            return;

        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        queue.add(null);
        int lastVal = root.data;

        while (queue.size()!=0){
            Node node = queue.remove();
            if(node != null){
                lastVal = node.data;
                if(node.left != null)
                    queue.add(node.left);
                if(node.right != null)
                    queue.add(node.right);
            }else{
                System.out.print(lastVal + ",");
                if(queue.size() == 0)
                    break;
                queue.add(null);
            }
        }
        System.out.println();
    }

    public void printLeftView(){
        if(root == null)
            return;

        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        queue.add(null);
        boolean printed = false;

        while (queue.size()!=0){
            Node node = queue.remove();
            if(node != null){
                if(!printed){
                    System.out.print(node.data+",");
                    printed = !printed;
                }
                if(node.left != null)
                    queue.add(node.left);
                if(node.right != null)
                    queue.add(node.right);
            }else{
                if(queue.size() == 0)
                    break;
                printed = !printed;
                queue.add(null);
            }
        }
        System.out.println();
    }

    //
    public void printLevelOrder(){
        if(root == null)
            return;

        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        queue.add(null);

        while (queue.size()!=0){
            Node node = queue.remove();
            if(node != null){
                System.out.print(node.data+",");
                if(node.left != null)
                    queue.add(node.left);
                if(node.right != null)
                    queue.add(node.right);
            }else{
                System.out.println();
                if(queue.size() == 0)
                    break;
                queue.add(null);
            }
        }
    }

    public void printPerimeter(){
        if(root == null)
            return;
        System.out.print(root.data + ",");
        printPerimeter(root, 0, 0);
        System.out.println();
    }

    //set each point with a coordinate->root:(0.0), left:(1,0) (2,0)..., right:(0,1) (0,2)...
    //for the left: preOrder, for the right: postOrder
    private void printPerimeter(Node node,int left, int right){
        if(node != null){
            if(right == 0 && left != 0){
                System.out.print(node.data+",");
                printPerimeter(node.left, left + 1, right);
                printPerimeter(node.right, left, right + 1);
            } else if(left == 0 && right != 0){
                printPerimeter(node.left, left + 1, right);
                printPerimeter(node.right, left, right + 1);
                System.out.print(node.data+",");
            } else if(node.left == null && node.right == null){
                System.out.print(node.data+",");
            } else {
                printPerimeter(node.left, left + 1, right);
                printPerimeter(node.right, left, right + 1);
            }
        }
    }

    public void printAllLeaves(){
        printAllLeaves(root);
        System.out.println();
    }

    private void printAllLeaves(Node node){
        if(node != null){
            printAllLeaves(node.left);
            printAllLeaves(node.right);

            if(node.left == null && node.right == null)
                System.out.print(node.data+",");
        }
    }

    //same with print level order, just reverse the even line
    //use stack to store even line(easy to reverse)
    public void printZigZag(){
        if(root == null)
            return;
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        queue.add(null);

        boolean print = true;

        Stack<Node> stack = new Stack<>();

        while (queue.size() != 0){
            Node node = queue.remove();
            if(node!=null){
                if(print)
                    System.out.print(node.data+",");
                else
                    stack.push(node);
                if(node.left!= null)
                    queue.add(node.left);
                if(node.right!=null)
                    queue.add(node.right);
            }else {
                if(queue.size()==0)
                    break;
                queue.add(null);
                print=!print;
                while (stack.size()!=0)
                    System.out.print(stack.pop().data+",");
                System.out.println();
            }
        }
        while (stack.size()!=0)
            System.out.print(stack.pop().data+",");
        System.out.println();
    }


}