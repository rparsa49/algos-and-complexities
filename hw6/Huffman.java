import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;


class Tree {
    public final int data;
    public final Tree leftChild;
    public final Tree rightChild;
    public final Character ch;

    //constructor
    private Tree(int d, Character c,  Tree left, Tree right) {
        data = d;
        leftChild = left;
        rightChild = right;
        ch = c;
    }

    static Tree leaf(char c, int f) {
        return new Tree(f, c, null, null);
    }

    static Tree combine(Tree a, Tree b) {
        return new Tree(a.data + b.data, null, a, b);
    }

    public boolean isLeaf() {return leftChild == null && rightChild == null;}

}

public class Huffman {

    public Map<Character, String> compute_coding(Map<Character, Integer> character_counts) {
        PriorityQueue<Tree> pq = new PriorityQueue<>(Comparator.comparingInt(t -> t.data));

        for (Map.Entry<Character, Integer> entry : character_counts.entrySet()) {
            pq.offer(Tree.leaf(entry.getKey(), entry.getValue()));
        }

        while (pq.size() > 1) {
            Tree left = pq.poll();
            Tree right = pq.poll();
            pq.offer(Tree.combine(left, right));
        }

        return assignCode(pq.poll());
    }

    public Map<Character, String> assignCode(Tree tree) {
        Map<Character, String> codes = new HashMap<>();
        assignCodeHelper(tree, "", codes);
        return codes;
    }

    public void assignCodeHelper(Tree tree, String code, Map<Character, String> codes) {
        if (!tree.isLeaf()) {
            assignCodeHelper(tree.leftChild, code + "0", codes);
            assignCodeHelper(tree.rightChild, code + "1", codes);
        } else {
            codes.put(tree.ch, code);
        }
    }
    
    public static void main(String[] args) {
        Map<Character, Integer> freqs = new HashMap<Character, Integer>(){{
            put('a', 15);
            put('e', 7);
            put('i', 30);
            put('o', 120);
            put('u', 11);
        }};
        Map<Character, String> codes = new Huffman().compute_coding(freqs);
        for (Character ch : freqs.keySet()) {
            String code_word = codes.get(ch);
            System.out.println(ch + ": " + code_word);
        }
    }
}