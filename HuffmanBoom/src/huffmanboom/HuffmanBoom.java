package huffmanboom;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 *
 * @author Frank Haver
 */
public class HuffmanBoom {

     static final String INPUT = "Bananen";

    public static void main(String[] args) {
        Map count = telLetters(INPUT);
        System.out.println(count);

        Queue sorted = sorteerLijst(count);
        System.out.println(sorted);

        Node tree = bouwHuffmanboom(sorted);
        System.out.println(tree);

        Map<Character, String> codes = new HashMap<Character, String>();
        getCodes(tree, codes, "");
        System.out.println(codes);

        String encoded = codeer(INPUT, codes);
        System.out.println(encoded);

        String decoded = decodeer(encoded, tree);
        System.out.println(decoded);
    }

    // Stap 1
    //For-loop is O(n), en de put ook dus worst case is deze methode O(n^2)
    public static Map<Character, Integer> telLetters(String input) {
        Map<Character, Integer> count = new HashMap<Character, Integer>();
        for (Character c : input.toCharArray()) {
            count.put(c, count.containsKey(c) ? count.get(c) + 1 : 1);
        }
        return count;
    }

    // Stap 2
    //for loop is O(n) en het toevoegen aan de lijst is log n dus deze methode is worst case O(n * log (n))
    public static Queue<Node> sorteerLijst(Map<Character, Integer> count) {
        Queue<Node> list = new PriorityQueue<Node>();
        for (Map.Entry<Character, Integer> pair : count.entrySet()) {
            list.add(new Node(pair.getKey(), pair.getValue()));
        }
        return list;
    }
    
    // Stap 3
    //while loop is O(n) en het toevoegen aan de lijst is log n dus deze methode is worst case O(n * log (n))
    public static Node bouwHuffmanboom(Queue<Node> nodes) {
        while (nodes.size() > 1) {
            nodes.add(new Node(nodes.remove(), nodes.remove()));
        }
        return nodes.remove();
    }
    
    // Stap 4
    //Deze methode loopt door iedere node heen, dus is deze methode O(n)
    public static void getCodes(Node node, Map<Character, String> codes, String root) {
        if (node.character != null) {
            codes.put(node.character, root);
        }

        if (node.left != null) {
            getCodes(node.left, codes, root + "0");
        }
        if (node.right != null) {
            getCodes(node.right, codes, root + "1");
        }
    }
    
    // Stap 5
    //Deze for-loop is O(n) en de get methode is O(1), dus deze methode is in totaal O(n)
    public static String codeer(String input, Map<Character, String> codes) {
        String output = "";
        for (Character c : input.toCharArray()) {
            output += codes.get(c);
        }
        return output;
    }
    
    // Stap 6
    //Door de for-loop is deze methode O(n)
    public static String decodeer(String input, Node tree) {
        Node current = tree;
        String output = "";
        for (Character c : input.toCharArray()) {
            if (c == '0') {
                current = current.left;
            }
            else if (c == '1') {
                current = current.right;
            }

            if (current.character != null) {
                output += current.character;
                current = tree;
            }
        }
        return output;
    }
    
}
