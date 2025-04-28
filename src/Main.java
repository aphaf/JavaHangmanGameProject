import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //Hangman game
        String filePath = "src\\Word List.txt";
        ArrayList<String> words = new ArrayList<>();

        try(BufferedReader reader = new BufferedReader(new FileReader(filePath))){

            String line;
            //reading file line by line
            //during each cycle, the line is reader.readLine (current line)
            //reader will point to next line until no line is found (null)
            while((line = reader.readLine()) != null){
                line = line.trim();
                boolean validWord = true;
                for(int i = 0; i < line.length(); i++){
                    if (!Character.isLetter(line.charAt(i))){
                        validWord = false;
                        break;
                    }
                }
                if (validWord){
                    words.add(line);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Could not locate file location.");
        }
        catch(IOException e){
            System.out.println("Something went wrong.");
        }

        if (words.isEmpty()){
            System.out.println("No valid words found in the file.");
            return;
        }

        Random random = new Random();

        Scanner scanner = new Scanner(System.in);

        System.out.println("--- Welcome to Java Hangman! ---\n");

        boolean continuePlaying = true;

        String word = words.get(random.nextInt(words.size()));
        Hangman hangman = new Hangman(word);
        System.out.println(hangman.displayHiddenWord());

        while(continuePlaying){

            char guessChar;

            try{
                System.out.print("\nEnter a guess: ");

                String guess = scanner.nextLine().trim().toLowerCase();
                if (!hangman.isValidGuess(guess)) {
                    System.out.println("Please enter a valid single letter.");
                    continue;
                }

                guessChar = guess.charAt(0);

                if (hangman.checkIfGuessWasAlreadyMade(guessChar)){
                    System.out.println("You've already guessed that letter! Please try again.");
                    continue;
                }
            }
            catch(Exception e){
                System.out.println("Something went wrong.");
                continue;
            }

            boolean isCorrect = hangman.checkIfWordContainsGuess(guessChar);

            if (isCorrect){
                System.out.println("--- Correct!! ---\nThe word does contain " + guessChar);
            }
            else{
                System.out.println("--- Incorrect ---\nSorry, the word does not contain " + guessChar);
            }

            System.out.println("\n--- HANGMAN ---\n-------------\n" + hangman.getHangmanDisplay() + "\n-------------\n");

            if (hangman.checkIfWordWasCompleted()) {
                System.out.println("--- You Win ---\nCongratulations! You've guessed the word: " + word.toLowerCase());
                continuePlaying = false;
            }
            else if (hangman.checkIfHangmanIsCompleted()){
                System.out.println("--- You Lost ---\nSorry, you ran out of guesses! The word was: " + word);
                continuePlaying = false;
            }

            if (!continuePlaying){

                System.out.print("\n--- Play again? ---\nWould you like to play again? (yes / no): ");

                try{
                    String input = scanner.nextLine().toLowerCase();
                    while (!input.equalsIgnoreCase("no") && !input.equalsIgnoreCase("yes")){
                        System.out.print("Please enter a valid option.\nWould you like to play again? (yes / no): ");
                        input = scanner.nextLine().toLowerCase();
                    }
                    if (input.equalsIgnoreCase("yes")) {
                        continuePlaying =  true;
                        word = words.get(random.nextInt(words.size()));
                        hangman = new Hangman(word);
                    }
                    else{
                        break;
                    }
                }
                catch(Exception e){
                    System.out.println("Something went wrong.");
                }
            }

            System.out.println(hangman.displayHiddenWord());
            System.out.println("Correct Letters Guessed: " + hangman.displayCorrectLettersGuessed());
            System.out.println("Incorrect Letters Guessed: " + hangman.displayIncorrectLettersGuessed());
        }

        scanner.close();

        System.out.println("\nThanks for playing!");
    }
}