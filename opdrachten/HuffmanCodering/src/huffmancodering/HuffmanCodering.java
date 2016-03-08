/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huffmancodering;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Frank Haver
 */
public class HuffmanCodering {

    private static final String DEFAULT_TEXT = "Een, twee, drie, vier\n"
            + "Hoedje van, hoedje van\n"
            + "Een, twee, drie, vier\n"
            + "Hoedje van papier\n";
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        HashMap charFrequency = getCharFrequency();
        String output = "";
        for(Object o : charFrequency.keySet()){
            output += String.valueOf(o) + ": " + String.valueOf(charFrequency.get(String.valueOf(o))) + "\n";
        }
        System.out.println(output);
    }
    
    public static HashMap<String, Integer> getCharFrequency(){
        HashMap<String, Integer> charFrequency = new HashMap<>();
        for(char ch : DEFAULT_TEXT.toCharArray()){
            if(String.valueOf(ch).length() != 0 && !String.valueOf(ch).equals("\n")){
                if(charFrequency.containsKey(String.valueOf(ch))){
                charFrequency.put(String.valueOf(ch), charFrequency.get(String.valueOf(ch)) + 1);
                }
                else{
                    charFrequency.put(String.valueOf(ch), 1);
                }
            }      
        }
        return charFrequency;
    }
    
    public class IntegerComparator implements Comparator {
        
        public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2 )
        {
                return (o1.getValue()).compareTo( o2.getValue() );
        }

        @Override
        public int compare(Object t, Object t1) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    }
    
    
    
}
