package woordenapplicatie.gui;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.net.URL;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.TreeSet;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

/**
 * FXML Controller class
 *
 * @author frankcoenen
 */
public class WoordenController implements Initializable {

    private static final String DEFAULT_TEXT = "Een, twee, drie, vier\n"
            + "Hoedje van, hoedje van\n"
            + "Een, twee, drie, vier\n"
            + "Hoedje van papier\n"
            + "\n"
            + "Heb je dan geen hoedje meer\n"
            + "Maak er één van bordpapier\n"
            + "Eén, twee, drie, vier\n"
            + "Hoedje van papier\n"
            + "\n"
            + "Een, twee, drie, vier\n"
            + "Hoedje van, hoedje van\n"
            + "Een, twee, drie, vier\n"
            + "Hoedje van papier\n"
            + "\n"
            + "En als het hoedje dan niet past\n"
            + "Zetten we 't in de glazenkas\n"
            + "Een, twee, drie, vier\n"
            + "Hoedje van papier";

    @FXML
    private Button btAantal;
    @FXML
    private TextArea taInput;
    @FXML
    private Button btSorteer;
    @FXML
    private Button btFrequentie;
    @FXML
    private Button btConcordantie;
    @FXML
    private TextArea taOutput;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        taInput.setText(DEFAULT_TEXT);
    }

    @FXML
    private void aantalAction(ActionEvent event) {
        taOutput.textProperty().set("Totaal aantal woorden: " + getTextWords().size() + "\n Aantal verschillende woorden: " + getDistinctWords().size());
    }

    @FXML
    private void sorteerAction(ActionEvent event) {
        taOutput.setText("");
        TreeSet<String> allWords = getDescendingSortedWords();
        String textOutput = "";
        for (String s : allWords) {
            textOutput += s + "\n";
        }
        taOutput.textProperty().set(textOutput);
    }

    @FXML
    private void frequentieAction(ActionEvent event) {
        String textOutput = "";
        Map<String, Integer> frequenceWords = new HashMap<>();
        for (String textWord : getTextWordsEnter()) {
            if (getDistinctWordsHashMap().containsKey(textWord)) {
                if(!frequenceWords.containsKey(textWord)){
                    frequenceWords.put(textWord, 1);
                }
                else{
                    frequenceWords.put(textWord,frequenceWords.get(textWord) + 1);
                }
            }
        }
        for(Map.Entry<String, Integer> entry : frequenceWords.entrySet()){
            textOutput = textOutput + entry.getKey() + ": " + entry.getValue().toString() + "\n";
        }
        taOutput.textProperty().set(textOutput);
    }

    @FXML
    private void concordatieAction(ActionEvent event) {
        String concordanceString = "";
        HashMap concordanceWords = getWordsAtLines();
        // voor alle keys
        for (Object k : concordanceWords.keySet()) {
            concordanceString += String.valueOf(k) + ": [";
            TreeSet ts = (TreeSet<Integer>)concordanceWords.get(String.valueOf(k));
            for(Object i : ts){
                concordanceString += String.valueOf(i) + ", ";
            }
            concordanceString = concordanceString.substring(0, concordanceString.length() -2) + "]\n";
        }
        taOutput.textProperty().set(concordanceString);
    }

    /**
     * @Author Frank Haver
     * @Description Pakt alle losse woorden uit de inputbox en zet deze in een
     * arraylist de lege woorden worden er uit gefilterd ook worden alle
     * accenten van de letters afgehaald
     * @return retourneert de LinkedList met alle losse woorden
     */
    private LinkedList<String> getTextWords() {
        String input = taInput.getText();
        input = input.replace(",", "");
        input = input.replace("\n", " ");
        String[] words = input.split(" ");
        List<String> listWords = Arrays.asList(words);
        LinkedList<String> allWords = new LinkedList<>();
        for (String s : listWords) {
            if (!s.isEmpty()) {
                s = s.toLowerCase();
                s = Normalizer.normalize(s, Normalizer.Form.NFD);
                s = s.replaceAll("[^\\p{ASCII}]", "");
                allWords.add(s);
            }
        }
        return allWords;
    }

    /**
     * @Author Frank Haver
     * @Description Geeft alle woorden uit het versje terug met de enters erbij
     * als text
     * @return retourneert een LinkedList met 'enter' woorden
     */
    private LinkedList<String> getTextWordsEnter() {
        String input = taInput.getText();
        input = input.replace(",", "");
        input = input.replace("\n", " enter ");
        String[] words = input.split(" ");
        List<String> listWords = Arrays.asList(words);
        LinkedList<String> allWords = new LinkedList<>();
        for (String s : listWords) {
            if (!s.isEmpty()) {
                s = s.toLowerCase();
                s = Normalizer.normalize(s, Normalizer.Form.NFD);
                s = s.replaceAll("[^\\p{ASCII}]", "");
                allWords.add(s);
            }
        }
        return allWords;
    }

    /**
     * @Author Frank Haver Deze methode pakt alle verschillende woorden en zet
     * @Description deze in een HashSet dit kan in een HashSet omdat hier toch
     * geen dubbele entries in kunnen staan
     * @return retourneert een HashSet van alle verschillende woorden
     */
    private HashSet<String> getDistinctWords() {
        HashSet<String> distinctWords = new HashSet<>();
        for (String textWord : getTextWords()) {
            distinctWords.add(textWord);
        }
        return distinctWords;
    }

    private HashMap getDistinctWordsHashMap(){
        HashMap distinctWords = new HashMap();
        for (String textWord : getTextWords()) {
            distinctWords.put(textWord, "");
        }
        return distinctWords;
    }

    /**
     * Opdracht 2
     *
     * @Author Frank Haver
     * @Description Deze methode pakt alle verschillende woorden en zet deze in
     * een TreeSet gesorteerd achterstevoren omdat een TreeSet geen dubbele
     * entries kan heb je alle distinct woorden Het wordt gesorteerd door het
     * toevoegen van een eigen Comparer
     * @return retourneert een TreeSet van alle verschillende woorden
     * achterstevoren gesorteerd
     */
    private TreeSet<String> getDescendingSortedWords() {
        TreeSet<String> distinctWords = new TreeSet<>(new StringComparator());
        for (String textWord : getTextWords()) {
            distinctWords.add(textWord);
        }
        return distinctWords;
    }

    /**
     * Opdracht 4
     *
     * @Author Frank Haver
     * @Description Deze methode gaat voor elk verschillend woord kijken in
     * welke regel deze zit aan de hand van de getTextWordsEnter() methode het
     * woord en de regel(s) worden opgeslagen in een HashMap, met het distinct
     * woord als key en de regelnummers als value.
     * @return retourneert een HashMap met alle woorden en de regel(s) waar de
     * woorden zich bevinden deze regels staan in een TreeSet
     */
    private HashMap<String, TreeSet<Integer>> getWordsAtLines() {
        Integer regel = 1;
        boolean first = true;
        boolean found = false;
        Map<String, TreeSet<Integer>> concordanceWords = new HashMap<>();
        HashMap distinctWords = getDistinctWordsHashMap();
        regel = 1;
        for (String textWord : getTextWordsEnter()) {
            if (distinctWords.containsKey(textWord)) {
                if(!concordanceWords.containsKey(textWord)){
                    concordanceWords.put(textWord, new TreeSet<>());
                }
                TreeSet set = concordanceWords.get(textWord);
                set.add(regel);
                concordanceWords.put(textWord, set);
            }
            if (textWord.equals("enter")) {
                regel++;
            }
        }
        return (HashMap<String, TreeSet<Integer>>) concordanceWords;
    }

    /**
     * @Author Frank Haver
     * @Description Eigen comparator voor het vergelijken van strings als er een
     * sort wordt aangeroepen dan wordt er alfabetisch gesorteerd van z-a
     */
    public class StringComparator implements Comparator {

        public int compare(Object o1, Object o2) {
            String s1 = (String) o1,
                    s2 = (String) o2;
            return s2.compareTo(s1);
        }
    }

}
