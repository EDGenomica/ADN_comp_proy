public class BinarySearchTree {
    Node root;

    static class Node {
        String key;
        int value;
        Node left;
        Node right;
        Node p;
        public Node (String key, int value, Node p){
            this.key = key;
            this.value = value;
            this.p = p;
        }
    }
    Node search (Node n, String key){
        if (n == null || n.key.compareTo(key) == 0)
            return n;
        if (key.compareTo(n.key) < 0)
            return search(n.left, key);
        else
            return search(n.right, key);
    }

    public Boolean search(String key) {
        Node n = search(root, key);
        if (n != null) {
            remove(n.key);
            return true;
        }
        return false;
    }

    Node insert (Node n, Node p, String key, int value) {
        if (n == null)
            n = new Node(key, value, p);
        else {
            if (key.compareTo(n.key) < 0)
                n.left = insert(n.left, n, key, value);
            else
                n.right = insert(n.right, n, key, value);
        }
        return n;
    }

    public void insert (String key, int value) {
        root = insert(root, null, key, value);
    }

    void replace (Node a, Node b){
        if (a.p == null)
            root = b;
        else if (a == a.p.left){
            a.p.left = b;
        }
        else
            a.p.right = b;
        if (b != null)
            b.p = a.p;
    }

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

    void print(Node n){
        if (n != null){
            print(n.left);
            System.out.println(n.key + " : " + n.value + " ");
            print(n.right);
        }
    }

    public void print(){
        print(root);
        System.out.println();
    }
}
