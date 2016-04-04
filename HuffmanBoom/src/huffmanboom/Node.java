/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huffmanboom;

/**
 *
 * @author Frank Haver
 */
public class Node implements Comparable<Node>{
    public Character character;
    public Integer weight;
    public Node left;
    public Node right;

    public Node(Character c, Integer w) {
        character = c;
        weight = w;
    }

    public Node(Node l, Node r) {
        left = l;
        right = r;
        weight = l.weight + r.weight;
    }

    @Override
    public int compareTo(Node o) {
        return this.weight.compareTo(o.weight);
    }

    @Override
    public String toString() {
        if (character != null) {
            return String.format("%s=%d", character, weight);
        }
        else {
            return String.format("(%s,%s)=%d", left, right, weight);
        }
    }
}
