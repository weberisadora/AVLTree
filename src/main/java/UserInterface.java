import java.util.Scanner;

public class UserInterface {
    AVLTree avlTree;
    Scanner scanner;
    private int option;

    public UserInterface() {
        avlTree = new AVLTree();
        scanner = new Scanner(System.in);
    }

    public void menu() {
        do {
            System.out.println("\nMenu");
            showOptions();
            requestOption();
            validateOption();
            scanner.nextLine();
            checkOption();
        } while (option < 9);
    }

    private void showOptions() {
        System.out.println("[1] Inserir nodo\n" +
                "[2] Pesquisar nodo\n" +
                "[3] Deletar nodo\n" +
                "[4] Exibir árvore\n" +
                "[5] Exibir percurso em pré-ordem\n" +
                "[6] Exibir percurso em pós-ordem\n" +
                "[7] Exibir percurso em-ordem\n" +
                "[8] Sair");
    }

    public void requestOption() {
        System.out.println("Digite o número correspondente à operação desejada: ");
        option = scanner.nextInt();
    }

    public void validateOption() {
        while (option < 1 || option > 8) {
            System.out.println("Opção inválida");
            requestOption();
        }
    }

    private void checkOption() {
        switch (option) {
            case 1:
                insertNode();
                break;

            case 2:
                searchNode();
                break;

            case 3:
                deleteNode();
                break;

            case 4:
                avlTree.displayTree();
                break;

            case 5:
                avlTree.preOrderTraversal();
                break;

            case 6:
                avlTree.postOrderTraversal();
                break;

            case 7:
                avlTree.inOrderTraversal();
                break;

            case 8:
                break;
        }
    }

    private void insertNode() {
        System.out.println("Digite o valor que deseja inserir na árvore:");
        int nodeKey = scanner.nextInt();

        if (avlTree.find(nodeKey) != null)
            System.out.println("O valor informado já está presente na árvore.");

        else {
            System.out.println("Inserindo nodo de chave " + nodeKey);
            avlTree.insert(nodeKey);
            System.out.println("Valor inserido com sucesso.");
        }
    }

    private void deleteNode() {
        System.out.println("Digite o valor que deseja deletar da árvore:");
        int nodeKey = scanner.nextInt();

        if (avlTree.find(nodeKey) == null)
            System.out.println("O valor informado não foi encontrado.");
        else {
            avlTree.delete(nodeKey);
            System.out.println("Valor deletado com sucesso.");
        }
    }

    private void searchNode() {
        System.out.println("Digite o valor que deseja pesquisar na árvore:");
        int nodeKey = scanner.nextInt();
        Node result = avlTree.find(nodeKey);

        if (result == null)
            System.out.println("O valor informado não foi encontrado.");
        else {
            System.out.println("Nodo encontrado!");
            System.out.println(result.toString());
        }
    }
}
