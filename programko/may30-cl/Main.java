import java.util.ArrayList;

public class Main {
    public static class Node {
        Object value;
        Node left;
        Node right;

        public Node(Object value) {
            this.value = value;
        }
        public Node(Object value, Object left, Object right) {
            this.value = value;
            this.left = new Node(left);
            this.right = new Node(right);
        }
        public Node(Object value, Node left, Node right) {
            this.value = value;
            this.left =  left;
            this.right = right;
        }

        public Node appendNodeL(Object value) {
            this.left = new Node(value);
            return this.left;
        }   
        public Node appendNodeR(Object value) {
            this.right = new Node(value);
            return this.right;
        }
        public Node appendBothNodes(Object left, Object right) {
            this.left = new Node(left);
            this.right = new Node(right);
            return this;
        }

        public Node appendSubTreeL(Tree left) {
            this.left = left.root;
            return this;
        }
        public Node appendSubTreeR(Tree right) {
            this.right = right.root;
            return this;
        }
        public Node appendSubTrees(Tree left, Tree right) {
            this.left = left.root;
            this.right = right.root;
            return this;
        }

        public Node getLeft() {
            return this.left;
        }
        public Node getRight() {
            return this.right;
        }

        @Override
        public String toString() {
            return this.value.toString();
        }
    }

    public static class Tree {
        Node root;

        public Tree(Object rootValue) {
            this.root = new Node(rootValue);
        }
        public Tree(Object rootValue, Object left, Object right) {
            this.root = new Node(rootValue, left, right);
        }
        public Tree(Object rootValue, Tree left, Tree right) {
            this.root = new Node(rootValue);
            if(left != null) this.root.left = left.root;
            if(right != null) this.root.right = right.root;
        }

        public void appendSubTrees(Tree left, Tree right) {
            this.root.left = left.root;
            this.root.right = right.root;
        }

        public Node getRoot() {
            return root;
        }

        public void addChildNode(Node child, int location) {
            String s = Integer.toBinaryString(location);
            for(int i = 0; i < s.length(); i++) {
                int l = s.charAt(i) - '0';

            }
        }

        //DFS

        private void printSubTree(Node root, int offset, char word) {
            for(int i = 0; i < offset-1; i++) {
                System.out.print("  ");      
            }
            if(offset != 0) System.out.print("\\-");  
            System.out.print(word);              
            System.out.print(") ");
            System.out.println(root);

            if(root.left != null) this.printSubTree(root.left, offset+1, 'L');
            if(root.right != null) this.printSubTree(root.right, offset+1, 'R');
        }
        public void printTree() {
            this.printSubTree(root, 0, 'M');
        }

        private void addSubTreeString(StringBuilder sb, Node root, int offset, char word) {
            for(int i = 0; i < offset-1; i++) {
               sb.append("  ");
            }
            if(offset != 0) sb.append("\\-");  
            sb.append(word).append(") ").append(root).append('\n');

            if(root.left != null) this.addSubTreeString(sb, root.left, offset+1, 'L');
            if(root.right != null) this.addSubTreeString(sb, root.right, offset+1, 'R');
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            this.addSubTreeString(sb, root, 0, 'M');
            return sb.toString();
        }

        private void printSubBFS(ArrayList<Node> queue) {
            ArrayList<Node> nextQueue = new ArrayList<Node>();

            for(int i = 0; i < queue.size(); i++) {
                System.out.print(queue.get(i));
                System.out.print(' ');
                if(queue.get(i).left != null) nextQueue.add(queue.get(i).left);
                if(queue.get(i).right != null) nextQueue.add(queue.get(i).right);
            }

            if(nextQueue.size() == 0) return;

            System.out.println();

            this.printSubBFS(nextQueue);
        }

        public void printLevel() {
            ArrayList<Node> queue = new ArrayList<Node>();
            queue.add(root);
            this.printSubBFS(queue);
        }
    }
    
    public static void main(String[] args) {
        /*
        Tree t = new Tree(2, 7, 5);

        t.getRoot().getLeft().appendBothNodes(2, 6).getRight().appendBothNodes(5, 11);
        t.getRoot().getRight().appendNodeR(9).appendNodeR(4);
        */

        Tree t = new Tree(
            2,
            new Tree(7,
                new Tree(2),
                new Tree(6,
                    new Tree(5),
                    new Tree(11)
                    )),
            new Tree(5,
                new Tree(9,
                    new Tree(4),
                    null
                ),
                null
                )
        );

        t.printTree();
        System.out.println(t.toString());
        t.printLevel();
    }
}