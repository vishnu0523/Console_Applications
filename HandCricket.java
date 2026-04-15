import java.util.Random;
import java.util.Scanner;

public class HandCricketGame {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random rand = new Random();

        int userScore = 0, compScore = 0, target;

        System.out.println("===== HAND CRICKET GAME =====");

        // Toss
        System.out.print("Choose Odd or Even: ");
        String choice = sc.next().toLowerCase();

        System.out.print("Enter your toss number (1 to 6): ");
        int userToss = sc.nextInt();
        int compToss = rand.nextInt(6) + 1;

        System.out.println("Computer chose: " + compToss);

        boolean userWonToss = false;
        if ((userToss + compToss) % 2 == 0 && choice.equals("even")) {
            userWonToss = true;
        } else if ((userToss + compToss) % 2 != 0 && choice.equals("odd")) {
            userWonToss = true;
        }

        boolean userBatFirst;

        if (userWonToss) {
            System.out.println("You won the toss!");
            System.out.print("Choose batting or bowling: ");
            String opt = sc.next().toLowerCase();
            userBatFirst = opt.equals("batting");
        } else {
            System.out.println("Computer won the toss!");
            userBatFirst = rand.nextBoolean();
            if (userBatFirst) {
                System.out.println("Computer chose bowling. You bat first.");
            } else {
                System.out.println("Computer chose batting. You bowl first.");
            }
        }

        // First innings
        if (userBatFirst) {
            System.out.println("\n--- You are Batting ---");
            while (true) {
                System.out.print("Enter your run (1 to 6): ");
                int userRun = sc.nextInt();
                int compRun = rand.nextInt(6) + 1;

                System.out.println("Computer bowls: " + compRun);

                if (userRun == compRun) {
                    System.out.println("OUT!");
                    break;
                } else {
                    userScore += userRun;
                    System.out.println("Your score: " + userScore);
                }
            }

            target = userScore + 1;
            System.out.println("\nComputer needs " + target + " runs to win.");

            System.out.println("\n--- You are Bowling ---");
            while (true) {
                System.out.print("Enter your bowl number (1 to 6): ");
                int userRun = sc.nextInt();
                int compRun = rand.nextInt(6) + 1;

                System.out.println("Computer bats: " + compRun);

                if (userRun == compRun) {
                    System.out.println("Computer is OUT!");
                    break;
                } else {
                    compScore += compRun;
                    System.out.println("Computer score: " + compScore);
                }

                if (compScore >= target) {
                    break;
                }
            }

        } else {
            System.out.println("\n--- You are Bowling ---");
            while (true) {
                System.out.print("Enter your bowl number (1 to 6): ");
                int userRun = sc.nextInt();
                int compRun = rand.nextInt(6) + 1;

                System.out.println("Computer bats: " + compRun);

                if (userRun == compRun) {
                    System.out.println("Computer is OUT!");
                    break;
                } else {
                    compScore += compRun;
                    System.out.println("Computer score: " + compScore);
                }
            }

            target = compScore + 1;
            System.out.println("\nYou need " + target + " runs to win.");

            System.out.println("\n--- You are Batting ---");
            while (true) {
                System.out.print("Enter your run (1 to 6): ");
                int userRun = sc.nextInt();
                int compRun = rand.nextInt(6) + 1;

                System.out.println("Computer bowls: " + compRun);

                if (userRun == compRun) {
                    System.out.println("OUT!");
                    break;
                } else {
                    userScore += userRun;
                    System.out.println("Your score: " + userScore);
                }

                if (userScore >= target) {
                    break;
                }
            }
        }

        // Result
        System.out.println("\n===== FINAL RESULT =====");
        System.out.println("Your Score: " + userScore);
        System.out.println("Computer Score: " + compScore);

        if (userScore > compScore) {
            System.out.println("You win!");
        } else if (compScore > userScore) {
            System.out.println("Computer wins!");
        } else {
            System.out.println("Match Draw!");
        }

        sc.close();
    }
}
