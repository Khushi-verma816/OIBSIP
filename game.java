import javax.swing.JOptionPane;
import java.util.Random;

public class game {

    private static final int MAX_ATTEMPTS = 10; // Maximum number of attempts
    private static int score = 0; // Player score

    public static void main(String[] args) {
        boolean playAgain;
        do {
            playAgain = playGame();
            if (playAgain) {
                JOptionPane.showMessageDialog(null, "Your current score is: " + score);
            }
        } while (playAgain);
        JOptionPane.showMessageDialog(null, "Final score: " + score + ". Thanks for playing!");
    }

    private static boolean playGame() {
        Random random = new Random();
        int targetNumber = random.nextInt(100) + 1; // Random number between 1 and 100
        int attemptsLeft = MAX_ATTEMPTS;
        boolean hasGuessedCorrectly = false;

        while (attemptsLeft > 0) {
            String input = JOptionPane.showInputDialog(null, "Guess a number between 1 and 100. Attempts left: " + attemptsLeft);
            
            if (input == null) {
                // User pressed cancel
                JOptionPane.showMessageDialog(null, "Game cancelled.");
                return false;
            }

            try {
                int guessedNumber = Integer.parseInt(input);

                if (guessedNumber < 1 || guessedNumber > 100) {
                    JOptionPane.showMessageDialog(null, "Please enter a number between 1 and 100.");
                    continue;
                }

                if (guessedNumber < targetNumber) {
                    JOptionPane.showMessageDialog(null, "Too low! Try again.");
                } else if (guessedNumber > targetNumber) {
                    JOptionPane.showMessageDialog(null, "Too high! Try again.");
                } else {
                    JOptionPane.showMessageDialog(null, "Congratulations! You guessed the number!");
                    hasGuessedCorrectly = true;
                    break;
                }

                attemptsLeft--;
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Invalid input. Please enter a valid number.");
            }
        }

        if (!hasGuessedCorrectly) {
            JOptionPane.showMessageDialog(null, "Sorry! You've run out of attempts. The number was " + targetNumber + ".");
        } else {
            // Give points based on the number of attempts used
            int points = MAX_ATTEMPTS - attemptsLeft;
            score += points;
        }

        int response = JOptionPane.showConfirmDialog(null, "Do you want to play again?");
        return response == JOptionPane.YES_OPTION;
    }
}
