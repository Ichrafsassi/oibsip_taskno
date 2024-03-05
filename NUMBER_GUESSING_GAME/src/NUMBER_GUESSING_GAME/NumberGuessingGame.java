package NUMBER_GUESSING_GAME;

import java.util.Random;
import java.util.Scanner;

public class NumberGuessingGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        
        int minNumber = 1;
        int maxNumber = 100;
        int attempts = 0;
        int score = 0;
        boolean playAgain = true;

        System.out.println("Welcome to the Number Guessing Game!");

        while (playAgain) {
            int randomNumber = random.nextInt(maxNumber - minNumber + 1) + minNumber;
            int guess;
            boolean guessedCorrectly = false;
            attempts = 0;

            System.out.println("I have picked a number between " + minNumber + " and " + maxNumber + ". Guess it!");

            while (!guessedCorrectly && attempts < 5) {
            	// Limiting the number of attempts to 5
                System.out.print("Enter your guess: ");
                guess = scanner.nextInt();
                attempts++;

                if (guess == randomNumber) {
                    System.out.println("Congratulations! You guessed the number in " + attempts + " attempts.");
                    // Giving points based on the number of attempts
                    score += 5 - attempts;
                    guessedCorrectly = true;
                } else if (guess < randomNumber) {
                    System.out.println("Try again. The number is higher than " + guess);
                } else {
                    System.out.println("Try again. The number is lower than " + guess);
                }
            }

            if (!guessedCorrectly) {
                System.out.println("Sorry, you have exceeded the maximum number of attempts. The number was " + randomNumber);
            }

            System.out.println("Your current score: " + score);
            System.out.print("Do you want to play again? (yes/no): ");
            String playAgainInput = scanner.next();
            playAgain = playAgainInput.equalsIgnoreCase("yes");
        }

        System.out.println("Thank you for playing the Number Guessing Game!");
        scanner.close();
    }
}
