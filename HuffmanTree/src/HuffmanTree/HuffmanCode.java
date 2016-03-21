package HuffmanTree;

import java.util.*;

public class HuffmanCode {

    public static HuffmanTree buildTree(int[] charFreqs) {
        PriorityQueue<HuffmanTree> trees = new PriorityQueue<>();
        for (int i = 0; i < charFreqs.length; i++) {
            if (charFreqs[i] > 0) {
                // zet alle chars met value in de priority queue op frequentie
                trees.offer(new HuffmanLeaf(charFreqs[i], (char) i));
            }
        }

        while (trees.size() > 1) {
            // haalt de bovenste van de Queue en zet deze weer terug als Node
            // met de 2 gepollde leaves als children
            HuffmanTree a = trees.poll();
            HuffmanTree b = trees.poll();

            trees.offer(new HuffmanNode(a, b));
        }
        return trees.poll();
    }

    public static void printCodes(HuffmanTree tree, StringBuffer prefix) {
        if (tree instanceof HuffmanLeaf) {
            HuffmanLeaf leaf = (HuffmanLeaf) tree;

            System.out.println(leaf.value + "\t" + leaf.frequency + "\t" + prefix);

        } else if (tree instanceof HuffmanNode) {
            HuffmanNode node = (HuffmanNode) tree;

            prefix.append('0');
            printCodes(node.left, prefix);
            prefix.deleteCharAt(prefix.length() - 1);

            prefix.append('1');
            printCodes(node.right, prefix);
            prefix.deleteCharAt(prefix.length() - 1);
        }
    }

    public static void main(String[] args) {
        String test = "Bananen";

        int[] charFreqs = new int[256];
        for (char c : test.toCharArray()) {
            charFreqs[c]++;
        }

        HuffmanTree tree = buildTree(charFreqs);

        System.out.println("SYMBOL\tWEIGHT\tHUFFMAN CODE");
        printCodes(tree, new StringBuffer());
    }
}
