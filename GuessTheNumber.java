import java.util.Random;
import java.util.Scanner;

public class GuessTheNumber {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        int rounds = 3; // Number of rounds
        int totalScore = 0;

        System.out.println("Welcome to the Guess the Number Game!");

        for (int round = 1; round <= rounds; round++) {
            int numberToGuess = random.nextInt(100) + 1;
            int attempts = 0;
            int maxAttempts = 10; // Limiting the number of attempts
            boolean hasGuessedCorrectly = false;

            System.out.println("\nRound " + round + " of " + rounds);
            System.out.println("I'm thinking of a number between 1 and 100.");

            while (attempts < maxAttempts) {
                System.out.print("Enter your guess: ");
                int userGuess = scanner.nextInt();
                attempts++;

                if (userGuess == numberToGuess) {
                    System.out.println("Congratulations! You've guessed the correct number.");
                    hasGuessedCorrectly = true;
                    break;
                } else if (userGuess < numberToGuess) {
                    System.out.println("The number is higher than your guess.");
                } else {
                    System.out.println("The number is lower than your guess.");
                }
            }

            if (!hasGuessedCorrectly) {
                System.out.println("Sorry, you've used all your attempts. The number was " + numberToGuess);
            }

            int points = maxAttempts - attempts + 1; // Points based on the number of attempts
            totalScore += points;
            System.out.println("You scored " + points + " points this round.");
        }

        System.out.println("\nGame Over! Your total score is " + totalScore + " points.");
        scanner.close();
    }
}
