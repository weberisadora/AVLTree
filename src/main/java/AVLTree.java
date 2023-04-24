public class AVLTree {
    private Node root;

    public Node find(int key) {
        return find(key, root);
    }

    private Node find(int key, Node node) {
        if (node == null)
            return null;

        if (key == node.getKey())
            return node;

        else if (key < node.getKey())
            return find(key, node.getLeftChild());

        else
            return find(key, node.getRightChild());
    }

    public void insert(int key) {
        root = insert(key, root);
    }

    private Node insert(int key, Node node) {
        if (node == null)
            return new Node(key);

        if (key < node.getKey())
            node.setLeftChild(insert(key, node.getLeftChild()));
        else if (key > node.getKey())
            node.setRightChild(insert(key, node.getRightChild()));
        else
            return node;

        updateHeight(node);
        return applyRotation(node);
    }

    public void delete(int key) {
        root = delete(key, root);
    }

    private Node delete(int key, Node node) {
        if (node == null) {
            System.out.println("O valor informado não foi encontrado.");
            return null;
        }

        if (key < node.getKey())
            node.setLeftChild(delete(key, node.getLeftChild()));

        else if (key > node.getKey())
            node.setRightChild(delete(key, node.getRightChild()));


        else {
            if (node.getLeftChild() == null)
                return node.getRightChild();

            else if (node.getRightChild() == null)
                return node.getLeftChild();

            node.setKey(getMax(node.getLeftChild()));

            node.setLeftChild(delete(node.getKey(), node.getLeftChild()));
        }

        updateHeight(node);
        return applyRotation(node);
    }

    public void preOrderTraversal() {
        preOrderTraversal(root);
    }

    private void preOrderTraversal(Node node) {
        if (node != null) {
            System.out.println(node.getKey());
            preOrderTraversal(node.getLeftChild());
            preOrderTraversal(node.getRightChild());
        }
    }

    public void inOrderTraversal() {
        inOrderTraversal(root);
    }

    private void inOrderTraversal(Node node) {
        if (node != null) {
            inOrderTraversal(node.getLeftChild());
            System.out.println(node.getKey());
            inOrderTraversal(node.getRightChild());
        }
    }

    public void postOrderTraversal() {
        postOrderTraversal(root);
    }

    private void postOrderTraversal(Node node) {
        if (node != null) {
            postOrderTraversal(node.getLeftChild());
            postOrderTraversal(node.getRightChild());
            System.out.println(node.getKey());
        }
    }

    public Integer getMax() {
        if (isEmpty())
            return null;

        return getMax(root);
    }

    private int getMax(Node node) {
        if (node.getRightChild() != null)
            return getMax(node.getRightChild());

        return node.getKey();
    }

    public Integer getMin() {
        if (isEmpty())
            return null;

        return getMin(root);
    }

    private int getMin(Node node) {
        if (node.getLeftChild() != null)
            return getMin(node.getLeftChild());

        return node.getKey();
    }

    public boolean isEmpty() {
        return root == null;
    }

    private Node applyRotation(Node node) {
        int balance = balanceFactor(node);

        if (balance > 1) {
            System.out.println("Rotação sendo aplicada no nodo de chave " + node.getKey());
            System.out.println("Fator de balanceamento do nodo: " + balance);

            int lefChildBalance = balanceFactor(node.getLeftChild());
            System.out.println("Fator de balanceamento do filho à esquerda: " + lefChildBalance);

            if (lefChildBalance < 0) {
                node.setLeftChild(rotateLeft(node.getLeftChild()));
                System.out.println("Aplicada rotação simples à esquerda.");
            }

            System.out.println("Aplicada rotação simples à direita.");
            return rotateRight(node);
        }

        if (balance < -1) {
            System.out.println("Rotação sendo aplicada no nodo de chave " + node.getKey());
            System.out.println("Fator de balanceamento do nodo: " + balance);

            int rightChildBalance = balanceFactor(node.getRightChild());
            System.out.println("Fator de balanceamento do filho à direita: " + rightChildBalance);

            if (rightChildBalance > 0) {
                node.setRightChild(rotateRight(node.getRightChild()));
                System.out.println("Aplicada rotação simples à direita.");
            }

            System.out.println("Aplicada rotação simples à esquerda.");
            return rotateLeft(node);
        }

        return node;
    }

    private Node rotateRight(Node rootNode) {
        Node leftNode = rootNode.getLeftChild();
        Node centerNode = leftNode.getRightChild();
        leftNode.setRightChild(rootNode);
        rootNode.setLeftChild(centerNode);
        updateHeight(rootNode);
        updateHeight(leftNode);
        return leftNode;
    }

    private Node rotateLeft(Node rootNode) {
        Node rightNode = rootNode.getRightChild();
        Node centerNode = rightNode.getLeftChild();
        rightNode.setLeftChild(rootNode);
        rootNode.setRightChild(centerNode);
        updateHeight(rootNode);
        updateHeight(rightNode);
        return rightNode;
    }

    private int balanceFactor(Node node) {
        if (node == null)
            return 0;

        return height(node.getLeftChild()) - height(node.getRightChild());
    }

    private void updateHeight(Node node) {
        int maxHeight = Math.max(
                height(node.getLeftChild()),
                height(node.getRightChild())
        );
        node.setHeight(maxHeight + 1);
    }

    private int height(Node node) {
        if (node == null)
            return 0;

        return node.getHeight();
    }

    public void displayTree() {
        displayTree(root, "", true);
    }

    private void displayTree(Node node, String prefix, boolean isLeft) {
        if (node == null) {
            System.out.println("Árvore vazia");
            return;
        }

        if (node.getRightChild() != null)
            displayTree(node.getRightChild(), prefix + (isLeft ? "│   " : "    "), false);

        System.out.println(prefix + (isLeft ? "└── " : "┌── ") + node.getKey());

        if (node.getLeftChild() != null)
            displayTree(node.getLeftChild(), prefix + (isLeft ? "    " : "│   "), true);

    }
}
