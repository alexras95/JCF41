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
import java.util.List;
import java.util.ResourceBundle;
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
    
   private static final String DEFAULT_TEXT =   "Een, twee, drie, vier\n" +
                                                "Hoedje van, hoedje van\n" +
                                                "Een, twee, drie, vier\n" +
                                                "Hoedje van papier\n" +
                                                "\n" +
                                                "Heb je dan geen hoedje meer\n" +
                                                "Maak er één van bordpapier\n" +
                                                "Eén, twee, drie, vier\n" +
                                                "Hoedje van papier\n" +
                                                "\n" +
                                                "Een, twee, drie, vier\n" +
                                                "Hoedje van, hoedje van\n" +
                                                "Een, twee, drie, vier\n" +
                                                "Hoedje van papier\n" +
                                                "\n" +
                                                "En als het hoedje dan niet past\n" +
                                                "Zetten we 't in de glazenkas\n" +
                                                "Een, twee, drie, vier\n" +
                                                "Hoedje van papier";
    
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
         throw new UnsupportedOperationException("Not supported yet."); 
    }

    @FXML
    private void sorteerAction(ActionEvent event) {
        ArrayList<String> allWords = getTextWords();
        for(String s : allWords){
            System.out.println(s);
        }
    }

    @FXML
    private void frequentieAction(ActionEvent event) {
         throw new UnsupportedOperationException("Not supported yet."); 
    }

    @FXML
    private void concordatieAction(ActionEvent event) {
         throw new UnsupportedOperationException("Not supported yet."); 
    }
   
    /**
     * @Author Frank Haver
     * Pakt alle losse woorden uit de inputbox en zet deze in een arraylist
     * de lege woorden en de woorden kleiner dan 3 karakters worden er uit gefilterd
     * ook worden alle accenten van de letters afgehaald
     * @return retourneert de arraylist met alle losse woorden
     */
    private ArrayList<String> getTextWords(){
        String input = taInput.getText();
        input = input.replace(",", "");
        input = input.replace("\n", " ");
        String[] words = input.split(" ");
        List<String> listWords = Arrays.asList(words);
        ArrayList<String> allWords = new ArrayList<>();
        for(String s : listWords){
            if(!s.isEmpty() && s.length() >= 3){
                s = s.toLowerCase();
                s = Normalizer.normalize(s, Normalizer.Form.NFD);
                s = s.replaceAll("[^\\p{ASCII}]", "");
                allWords.add(s);
            }
        }
        return allWords;
    }
}
