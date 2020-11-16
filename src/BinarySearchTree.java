import java.util.ArrayList;

public class BinarySearchTree {
    Node root;
    public int max_count = 0;
    StackRefGeneric<String> freqSubs = new StackRefGeneric<>();

    static class Node {
        String key;
        QueueRefGeneric<Integer> index = new QueueRefGeneric<>();
        Node left;
        Node right;
        public Node (String key, int i){
            this.key = key;
            this.index.enqueue(i);
        }
    }

    public BinarySearchTree() {
        root = null;
    }

    Node search (Node n, String key){
        if (n == null || n.key.equals(key))
            return n;
        if (key.compareTo(n.key) < 0)
            return search(n.left, key);
        else
            return search(n.right, key);
    }

    public Boolean search(String key) {
        Node n = search(root, key);
        if (n != null) {
            deleteNode(n, key);
            return true;
        }
        return false;
    }

    public QueueRefGeneric<Integer> searchIndex(String key) {
        Node n = search(root, key);
        if (n != null)
            return n.index;
        return null;
    }

    Node insert (Node n, String key, int value) {
        if (n == null) {
            return new Node(key, value);
        }
        else
            if (key.compareTo(n.key) < 0)
                n.left = insert(n.left, key, value);
            else
                if (key.compareTo(n.key) > 0)
                    n.right = insert(n.right, key, value);
                else
                    n.index.enqueue(value);
        return n;
    }

    public void insert (String key, int value) {
        root = insert(root, key, value);
    }

    static Node minValueNode(Node Node)
    {
        Node current = Node;

        /* loop down to find the leftmost leaf */
        while (current.left != null)
            current = current.left;

        return current;
    }

    /* Given a binary search tree and a key,  
    this function deletes a given key and  
    returns root of modified tree */
    static Node deleteNode(Node root, String key)
    {
        // base case 
        if (root == null) return root;

        // If the key to be deleted is smaller than the 
        // root's key, then it lies in left subtree 
        if (key.compareTo(root.key) < 0)
            root.left = deleteNode(root.left, key);

            // If the key to be deleted is greater than  
            // the root's key, then it lies in right subtree 
        else if (key.compareTo(root.key) > 0)
            root.right = deleteNode(root.right, key);

            // if key is same as root's key 
        else
        {
            // If key is present more than once,  
            // simply decrement count and return 
            if (!root.index.isEmpty())
            {
                root.index.dequeue();
                return root;
            }

            // ElSE, delete the Node
            // Node with only one child or no child 
            if (root.left == null)
            {
                Node temp = root.right;
                root = null;
                return temp;
            }
            else if (root.right == null)
            {
                Node temp = root.left;
                root = null;
                return temp;
            }

            // Node with two children: Get the inorder  
            // successor (smallest in the right subtree) 
            Node temp = minValueNode(root.right);

            // Copy the inorder successor's  
            // content to this Node 
            root.key = temp.key;

            // Delete the inorder successor 
            root.right = deleteNode(root.right,
                    temp.key);
        }
        return root;
    }

    public void DFSmax(Node root) {
        if (root != null) {
            DFSmax(root.left);
            if(root.index.size > max_count)
                max_count = root.index.size;
            DFSmax(root.right);
        }
    }

    public void inOrderTraversalmax() {
        DFSmax(root);
    }

    public void DFSidx(Node root) {
        if (root != null) {
            DFSidx(root.left);
            if(root.index.size == max_count) {
                freqSubs.push(root.key);
                System.out.println(root.key);
            }
            DFSidx(root.right);
        }
    }

    public void inOrderTraversalidx() {
        DFSidx(root);
    }

        /*
    void remove (Node n, String key){
        if (n == null)
            return;
        if (key.compareTo(n.key) < 0)
            remove(n.left, key);
        else if (key.compareTo(n.key) > 0)
            remove(n.right, key);
        else if (n.left != null && n.right != null){
            Node m  = n.right;
            while (m.left != null)
                m = m.left;
            n.key = m.key;
            n.value = m.value;
            replace(m, m.right);
        }
        else if (n.left != null)
            replace(n, n.left);
        else if (n.right != null)
            replace(n, n.right);
        else
            replace(n, null);
    }

    public void remove (String key){
        remove(root, key);
    }
    
         */

    void print(Node n){
        if (n != null){
            print(n.left);
            System.out.println(n.key + " : ");
            while(!n.index.isEmpty())
                System.out.println(n.index.dequeue());
            print(n.right);
        }
    }

    public void print(){
        print(root);
        System.out.println("ROOT : " + root.key);
    }
}
