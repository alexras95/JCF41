/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HuffmanTree;

abstract class HuffmanTree implements Comparable<HuffmanTree> {

    public final int frequency;

    public HuffmanTree(int freq) {
        frequency = freq;
    }

    // zet in de juiste plek in de PriorityQueue met laagste frequentie bovenaan
    public int compareTo(HuffmanTree tree) {
        return frequency - tree.frequency;
    }
}
