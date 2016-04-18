package scrabblebot;

import java.util.Scanner;

/**
 * @author Gary
 */
public class ScrabbleBot {
    String alphabet = "abcdefghijklmnopqrstuvwxyz";
    
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        ScrabbleBot bot = new ScrabbleBot();
        
        System.out.println("Welcome to SrabbleBot 0.1");
        
        System.out.print("Enter your letters seperated by commas:");
        String inputText = input.next();
        
        String[] letters = bot.cleanArray(inputText);
        
        for (String letter : letters) {
            System.out.println(letter);
        }
    }
    
    public String[] cleanArray(String array){
        String[] letterArray = array.toLowerCase().split("");
        String[] cleanLetters = new String[array.length()];
        
        int numberOfLetters = 0;
        
        // Put only letters into the new array
        for (int i = 0; i <array.length(); i++){
            if(alphabet.contains(letterArray[i])){
                cleanLetters[numberOfLetters] = letterArray[i];
                numberOfLetters++;
            }
        }
        
        // Removed null entries
        String[] reducedArray = new String[numberOfLetters];
        for(int i = 0; i < numberOfLetters; i++){
            reducedArray[i] = cleanLetters[i];
        }
        
        return reducedArray;
    }
    
}
