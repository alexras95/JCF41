/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huffmancodering;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 *
 * @author Frank Haver
 */
public class HuffmanCodering {

    private static final String DEFAULT_TEXT = "zandbak";

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        TreeMap charFrequency = sortMapByValue(getCharFrequency());
        for (Object o : charFrequency.entrySet()) {
            System.out.println(o.toString());
        }
    }

    /**
     * @Author Frank Haver
     * @Description Het hoedje van papier liedje wordt als char array
     * doorgelopen en de characters met 0 lengte en enters worden er uit
     * gehaald. In een HashMap<String, Integer> wordt het character met de
     * frequentie opgeslagen.
     * @return
     */
    public static HashMap<String, Integer> getCharFrequency() {
        HashMap<String, Integer> charFrequency = new HashMap<>();
        for (char ch : DEFAULT_TEXT.toCharArray()) {
            if (String.valueOf(ch).length() != 0 && !String.valueOf(ch).equals("\n")) {
                if (charFrequency.containsKey(String.valueOf(ch))) {
                    charFrequency.put(String.valueOf(ch), charFrequency.get(String.valueOf(ch)) + 1);
                } else {
                    charFrequency.put(String.valueOf(ch), 1);
                }
            }
        }        
        return charFrequency;
    }

    /**
     * @Author Frank Haver
     * @Description Er wordt een nieuwe TreeMap<String, Integer> aangemaakt uit
     * een binnenkomende HashMap<String, Integer>. Deze wordt gesorteerd door
     * een custom gemaakte comparator met de klasse genaamd ValueComparator.
     * @param map
     * @return
     */
    public static TreeMap<String, Integer> sortMapByValue(HashMap<String, Integer> map) {
        TreeMap<String, Integer> result = new TreeMap<>(new ValueComparator(map));
        result.putAll(map);
        return result;
    }

    /**
     * @Author Frank Haver
     * @Description Klasse voor het sorteren van een HashMap
     */
    public static class ValueComparator implements Comparator<String> {

        HashMap<String, Integer> map = new HashMap<>();

        public ValueComparator(HashMap<String, Integer> map) {
            this.map.putAll(map);
        }

        @Override
        public int compare(String s1, String s2) {
            if (map.get(s1) >= map.get(s2)) {
                return -1;
            } else {
                return 1;
            }
        }
    }

}
