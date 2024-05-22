import java.util.Scanner;
import java.util.Random;

public class NumberGuessingGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        
        int minRange = 1; // Minimum value of the range
        int maxRange = 100; // Maximum value of the range
        int attempts = 5; // Number of attempts allowed
        int rounds = 3; // Number of rounds
        
        int userScore = 0;
        int computerScore = 0;
        
        System.out.println("Welcome to the Number Guessing Game!");
        System.out.println("You and the computer will play " + rounds + " rounds.");
        System.out.println("Each round, I will select a number between " + minRange + " and " + maxRange + ".");
        System.out.println("You have " + attempts + " attempts to guess it.");
        
        for (int round = 1; round <= rounds; round++) {
            System.out.println("\nRound " + round + ":");
            int randomNumber = random.nextInt(maxRange - minRange + 1) + minRange;
            System.out.println("I have selected a number. Your turn to guess!");
            
            int attemptCount = 0;
            boolean guessedCorrectly = false;
            
            while (attemptCount < attempts && !guessedCorrectly) {
                System.out.print("Enter your guess: ");
                int userGuess = scanner.nextInt();
                attemptCount++;
                
                if (userGuess == randomNumber) {
                    System.out.println("Congratulations! You guessed the number in " + attemptCount + " attempts.");
                    guessedCorrectly = true;
                    userScore++;
                } else if (userGuess < randomNumber) {
                    System.out.println("Too low! Try again.");
                } else {
                    System.out.println("Too high! Try again.");
                }
            }
            
            if (!guessedCorrectly) {
                System.out.println("Sorry, you've run out of attempts. The correct number was: " + randomNumber);
                computerScore++;
            }
            
            System.out.println("Your score: " + userScore + ", Computer's score: " + computerScore);
        }
        
        if (userScore > computerScore) {
            System.out.println("\nCongratulations! You won the game!");
        } else if (userScore < computerScore) {
            System.out.println("\nSorry, you lost the game. Better luck next time!");
        } else {
            System.out.println("\nThe game ended in a tie!");
        }
        
        scanner.close();
    }
}