import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class Node {
    private int key;
    private int height = 1;
    private Node leftChild;
    private Node rightChild;

    public Node(int key) {
        this.key = key;
    }
}
