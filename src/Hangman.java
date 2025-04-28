import java.util.ArrayList;

public class Hangman {

    private final String word;

    private ArrayList<Character> wordState = new ArrayList<>();

    private ArrayList<Character> correctLettersGuessed = new ArrayList<>();

    private ArrayList<Character> incorrectLettersGuessed = new ArrayList<>();

    private String hangmanDisplay;

    public Hangman(String word){
        this.word = word.toLowerCase();
        updateWordState();
        hangmanDisplay = "";
    }

    public String getHangmanDisplay(){
        return hangmanDisplay;
    }

    public void updateHangmanDisplay(){
        String[] stages = {
                """
                |
                o
                """,
                """
                |
                o
                |
                """,
                """
                 |
                 o
                /|
                """,
                """
                 |
                 o
                /|\\
                """,
                """
                 |
                 o
                /|\\
                /
                """,
                """
                 |
                 o
                /|\\
                / \\
                """
        };

        int incorrectGuessesMade = incorrectLettersGuessed.size();

        if (incorrectGuessesMade >= 1 && incorrectGuessesMade <= 6) {
            hangmanDisplay = stages[incorrectGuessesMade - 1];
        }
    }

    public boolean checkIfHangmanIsCompleted(){
        return incorrectLettersGuessed.size() == 6;
    }

    public void updateWordState(){
        if (correctLettersGuessed.isEmpty() && wordState.isEmpty()){
            for(int i = 0; i < word.length(); i++){
                wordState.add('_');
            }
        }
        else{
            for(int i = 0; i < word.length(); i++){
                if (correctLettersGuessed.contains(word.charAt(i))){
                    wordState.set(i, word.charAt(i));
                }
            }
        }
    }

    public boolean checkIfWordWasCompleted(){
        boolean wordCompleted = true;

        for(int i = 0; i < word.length(); i++) {
            if (!correctLettersGuessed.contains(word.charAt(i))) {
                wordCompleted = false;
                break;
            }
        }

        return wordCompleted;
    }

    public boolean isValidGuess(String guess){
        return( guess.length() == 1 && Character.isLetter(guess.charAt(0))) ;
    }

    public boolean checkIfGuessWasAlreadyMade(char guess){
        boolean guessWasMade = false;
        if (correctLettersGuessed.contains(guess) || incorrectLettersGuessed.contains(guess)){
            guessWasMade = true;
        }
        return guessWasMade;
    }

    public boolean checkIfWordContainsGuess(char guess){
        boolean wordContainsGuess = false;

        if (Character.isLetter(guess) && !checkIfGuessWasAlreadyMade(guess)){
            if (word.contains(String.valueOf(guess))){
                correctLettersGuessed.add(guess);
                updateWordState();
                wordContainsGuess = true;
            }
            else {
                incorrectLettersGuessed.add(guess);
                updateHangmanDisplay();
            }
        }

        return wordContainsGuess;
    }

    public String displayHiddenWord(){
        StringBuilder sb = new StringBuilder();

        sb.append("The word is ").append(word.length()).append(" letters long.\n");

        for(char c : wordState){
            sb.append(c).append(" ");
        }

        return sb.toString();
    }

    public String displayCorrectLettersGuessed(){
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < correctLettersGuessed.size(); i++){
            sb.append(correctLettersGuessed.get(i));
            if (i != correctLettersGuessed.size() - 1){
                sb.append(", ");
            }
        }
        return sb.toString();
    }

    public String displayIncorrectLettersGuessed(){
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < incorrectLettersGuessed.size(); i++){
            sb.append(incorrectLettersGuessed.get(i));
            if (i != incorrectLettersGuessed.size() - 1){
                sb.append(", ");
            }
        }
        return sb.toString();
    }

}
