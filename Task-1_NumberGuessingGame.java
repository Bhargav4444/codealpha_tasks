import java.util.Random;
import javax.swing.JOptionPane;

public class NumberGuessingGame {
    public static void main(String[] args) {
        Random random = new Random();
        int randomNumber = random.nextInt(100) + 1; 
        int attempts = 0;
        boolean hasGuessedCorrectly = false;

        while (!hasGuessedCorrectly) {
            String input = JOptionPane.showInputDialog("Guess the number between 1 to 100:");
            int userGuess = Integer.parseInt(input);
            attempts++;

            if (userGuess == randomNumber) {
                hasGuessedCorrectly = true;
                JOptionPane.showMessageDialog(null, "Congratulations! You guessed the number in " + attempts + " attempts.");
            } else if (userGuess < randomNumber) {
                JOptionPane.showMessageDialog(null, "Too low! Try again.");
            } else {
                JOptionPane.showMessageDialog(null, "Too high! Try again.");
            }
        }
    }
}
