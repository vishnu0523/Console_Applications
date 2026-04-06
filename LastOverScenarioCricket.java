import java.util.Random;
import java.util.Scanner;

public class LastOverScenarioCricket {
    static Scanner sc = new Scanner(System.in);
    static Random random = new Random();

    public static void main(String[] args) {
        System.out.println("========================================");
        System.out.println("   LAST OVER SCENARIO CRICKET");
        System.out.println("========================================");
        System.out.println("Chase pressure. One over. Big names.");
        System.out.println("Opener at the other end: Virat Kohli (VK)");
        System.out.println("Finisher on strike: MS Dhoni (MSD)");
        System.out.println("Bowler: Mitchell Starc");
        System.out.println("Venue: MCG under lights\n");

        System.out.print("Enter target runs needed in final over (6 to 24 recommended): ");
        int target = readInt(1, 36);

        playFinalOver(target);
    }

    static void playFinalOver(int target) {
        int teamScore = 0;
        int wickets = 0;
        int maxWickets = 2;
        int ballsLeft = 6;

        String striker = "MS Dhoni";
        String nonStriker = "Virat Kohli";

        int dhoniRuns = 0, dhoniBalls = 0;
        int kohliRuns = 0, kohliBalls = 0;

        System.out.println("========================================");
        System.out.println("FINAL OVER STARTS NOW");
        System.out.println("TARGET TO CHASE: " + target);
        System.out.println("India need " + target + " runs from 6 balls.");
        System.out.println("MS Dhoni on strike. Virat at the non-striker's end.");
        System.out.println("Mitchell Starc has the ball in hand.");
        System.out.println("========================================\n");

        for (int ball = 1; ball <= 6; ball++) {
            ballsLeft = 7 - ball;
            int required = target - teamScore;

            if (required <= 0) break;
            if (wickets == maxWickets) {
                System.out.println("India is all out. Match over.");
                break;
            }

            System.out.println("----------------------------------------");
            System.out.println("Ball " + ball + " | India need " + required + " from " + (7 - ball) + " ball(s)");
            System.out.println("Striker: " + striker + " | Non-Striker: " + nonStriker);
            System.out.println("Crowd noise rising... Starc runs in...\n");

            int shotChoice;
            if (striker.equals("MS Dhoni")) {
                System.out.println("Choose Dhoni's intent:");
                System.out.println("1. Calm single");
                System.out.println("2. Hard running double");
                System.out.println("3. Controlled boundary shot");
                System.out.println("4. Helicopter big shot");
                System.out.print("Your choice: ");
                shotChoice = readInt(1, 4);
            } else {
                System.out.println("Choose Kohli's intent:");
                System.out.println("1. Rotate strike");
                System.out.println("2. Push for two");
                System.out.println("3. Thread the gap");
                System.out.println("4. Go aerial");
                System.out.print("Your choice: ");
                shotChoice = readInt(1, 4);
            }

            int ballType = starcBall(required, ballsLeft);
            System.out.println("Starc bowls a " + ballTypeName(ballType) + "...");

            BallOutcome result = resolveBall(striker, shotChoice, ballType, required, ballsLeft);

            if (striker.equals("MS Dhoni")) dhoniBalls++;
            else kohliBalls++;

            if (result.isWicket) {
                wickets++;
                System.out.println(result.commentary);
                printBigMoment("WICKET");
                System.out.println(striker + " is OUT!");

                if (wickets < maxWickets) {
                    if (striker.equals("MS Dhoni")) striker = "Virat Kohli";
                    else striker = "MS Dhoni";
                    nonStriker = "Tailender";
                    System.out.println("New batter comes in: Tailender");
                }
            } else {
                teamScore += result.runs;
                System.out.println(result.commentary);
                System.out.println(striker + " scores " + result.runs + " run(s).");

                if (striker.equals("MS Dhoni")) dhoniRuns += result.runs;
                else kohliRuns += result.runs;

                if (result.runs % 2 == 1) {
                    String temp = striker;
                    striker = nonStriker;
                    nonStriker = temp;
                    System.out.println("Strike changes.");
                }
            }

            if (!result.isWicket && result.runs == 6) {
                printBigMoment("SIX");
            } else if (!result.isWicket && result.runs == 4) {
                printBigMoment("FOUR");
            }
            System.out.println("Score: " + teamScore + "/" + wickets + " after " + ball + " ball(s)");
            System.out.println("TARGET: " + target + " | NEED: " + Math.max(0, target - teamScore));
            System.out.println("====================================");
            System.out.println("   REQUIRED RUNS: " + Math.max(0, target - teamScore));
            System.out.println("====================================");
            System.out.println();
        }

        printBigMoment("SCORECARD");
        System.out.println("========================================");
        System.out.println("          FINAL SCORECARD");
        System.out.println("========================================");
        System.out.println("India scored: " + teamScore + "/" + wickets + " in 1 over");
        System.out.println("MS Dhoni : " + dhoniRuns + " (" + dhoniBalls + ")");
        System.out.println("Virat Kohli : " + kohliRuns + " (" + kohliBalls + ")");
        System.out.println("Target was : " + target);
        System.out.println();

        if (teamScore >= target) {
            System.out.println("INDIA WINS!");
            if (teamScore == target) {
                System.out.println("They got there exactly on the final over.");
            } else {
                System.out.println("MSD and VK pull off another iconic chase!");
            }
        } else {
            System.out.println("AUSTRALIA DEFENDS IT.");
            System.out.println("Mitchell Starc survives the pressure.");
        }
    }

    static BallOutcome resolveBall(String striker, int shotChoice, int ballType, int required, int ballsLeft) {
        if (striker.equals("MS Dhoni")) {
            return dhoniOutcome(shotChoice, ballType, required, ballsLeft);
        } else {
            return kohliOutcome(shotChoice, ballType, required, ballsLeft);
        }
    }

    static BallOutcome dhoniOutcome(int shot, int ballType, int required, int ballsLeft) {
        if (ballType == 3 && shot == 4) {
            return randomChance(45)
                    ? new BallOutcome(-1, true, "Starc nails the yorker! Dhoni misses the helicopter.")
                    : new BallOutcome(randomChance(50) ? 2 : 4, false, "Dhoni somehow digs it out under pressure!");
        }
        if (ballType == 4 && shot == 4) {
            return randomChance(35)
                    ? new BallOutcome(-1, true, "Starc deceives Dhoni with the slower ball.")
                    : new BallOutcome(randomChance(50) ? 1 : 6, false, "Dhoni waits... and launches!");
        }

        if (shot == 1) return pickOutcome(new int[]{0,1,1,1,2,-1}, strikerLine(1));
        if (shot == 2) return pickOutcome(new int[]{1,2,2,2,3,-1}, strikerLine(2));
        if (shot == 3) return pickOutcome(new int[]{0,2,4,4,1,-1}, strikerLine(3));
        return pickOutcome(new int[]{0,1,2,4,6,-1}, strikerLine(4));
    }

    static BallOutcome kohliOutcome(int shot, int ballType, int required, int ballsLeft) {
        if (ballType == 3 && shot == 4) {
            return randomChance(40)
                    ? new BallOutcome(-1, true, "Starc beats Kohli with a toe-crushing yorker!")
                    : new BallOutcome(randomChance(50) ? 1 : 2, false, "Kohli squeezes it somehow.");
        }

        if (shot == 1) return pickOutcome(new int[]{0,1,1,1,2,-1}, kohliLine(1));
        if (shot == 2) return pickOutcome(new int[]{1,2,2,3,0,-1}, kohliLine(2));
        if (shot == 3) return pickOutcome(new int[]{1,2,4,4,0,-1}, kohliLine(3));
        return pickOutcome(new int[]{0,1,2,4,6,-1}, kohliLine(4));
    }

    static BallOutcome pickOutcome(int[] arr, String line) {
        int pick = arr[random.nextInt(arr.length)];
        if (pick == -1) return new BallOutcome(-1, true, line);
        return new BallOutcome(pick, false, line);
    }

    static int starcBall(int required, int ballsLeft) {
        if (ballsLeft <= 2 && required >= 6) return randomChance(60) ? 3 : 4;
        if (required <= 3) return randomChance(50) ? 4 : 1;
        return random.nextInt(4) + 1;
    }

    static String strikerLine(int shot) {
        if (shot == 1) return "Dhoni stays calm and plays it late.";
        if (shot == 2) return "Dhoni calls loudly and charges back for more.";
        if (shot == 3) return "Dhoni opens the face and targets the gap.";
        return "Dhoni clears the front leg and swings hard!";
    }

    static String kohliLine(int shot) {
        if (shot == 1) return "Kohli works it into the leg side.";
        if (shot == 2) return "Kohli pushes hard and runs aggressively.";
        if (shot == 3) return "Kohli times it sweetly through the covers.";
        return "Kohli takes the aerial route!";
    }

    static String ballTypeName(int type) {
        if (type == 1) return "full ball";
        if (type == 2) return "hard length ball";
        if (type == 3) return "yorker";
        return "slower ball";
    }

    static boolean randomChance(int percent) {
        return random.nextInt(100) < percent;
    }

    static void printBigMoment(String text) {
        System.out.println();
        if (text.equals("SIX")) {
            System.out.println("███████╗██╗██╗  ██╗");
            System.out.println("██╔════╝██║╚██╗██╔╝");
            System.out.println("███████╗██║ ╚███╔╝ ");
            System.out.println("╚════██║██║ ██╔██╗ ");
            System.out.println("███████║██║██╔╝ ██╗");
            System.out.println("╚══════╝╚═╝╚═╝  ╚═╝");
        } else if (text.equals("FOUR")) {
            System.out.println("███████╗ ██████╗ ██╗   ██╗██████╗ ");
            System.out.println("██╔════╝██╔═══██╗██║   ██║██╔══██╗");
            System.out.println("█████╗  ██║   ██║██║   ██║██████╔╝");
            System.out.println("██╔══╝  ██║   ██║██║   ██║██╔══██╗");
            System.out.println("██║     ╚██████╔╝╚██████╔╝██║  ██║");
            System.out.println("╚═╝      ╚═════╝  ╚═════╝ ╚═╝  ╚═╝");
        } else if (text.equals("WICKET")) {
            System.out.println("██╗    ██╗██╗ ██████╗██╗  ██╗███████╗████████╗");
            System.out.println("██║    ██║██║██╔════╝██║ ██╔╝██╔════╝╚══██╔══╝");
            System.out.println("██║ █╗ ██║██║██║     █████╔╝ █████╗     ██║   ");
            System.out.println("██║███╗██║██║██║     ██╔═██╗ ██╔══╝     ██║   ");
            System.out.println("╚███╔███╔╝██║╚██████╗██║  ██╗███████╗   ██║   ");
            System.out.println(" ╚══╝╚══╝ ╚═╝ ╚═════╝╚═╝  ╚═╝╚══════╝   ╚═╝   ");
        } else if (text.equals("SCORECARD")) {
            System.out.println("███████╗ ██████╗ ██████╗ ██████╗ ███████╗ ██████╗ █████╗ ██████╗ ██████╗ ");
            System.out.println("██╔════╝██╔════╝██╔═══██╗██╔══██╗██╔════╝██╔════╝██╔══██╗██╔══██╗██╔══██╗");
            System.out.println("███████╗██║     ██║   ██║██████╔╝█████╗  ██║     ███████║██████╔╝██║  ██║");
            System.out.println("╚════██║██║     ██║   ██║██╔══██╗██╔══╝  ██║     ██╔══██║██╔══██╗██║  ██║");
            System.out.println("███████║╚██████╗╚██████╔╝██║  ██║███████╗╚██████╗██║  ██║██║  ██║██████╔╝");
            System.out.println("╚══════╝ ╚═════╝ ╚═════╝ ╚═╝  ╚═╝╚══════╝ ╚═════╝╚═╝  ╚═╝╚═╝  ╚═╝╚═════╝ ");
        }
        System.out.println();
    }

    static int readInt(int min, int max) {
        while (true) {
            if (sc.hasNextInt()) {
                int val = sc.nextInt();
                if (val >= min && val <= max) return val;
            } else {
                sc.next();
            }
            System.out.print("Enter a valid choice (" + min + "-" + max + "): ");
        }
    }

    static class BallOutcome {
        int runs;
        boolean isWicket;
        String commentary;

        BallOutcome(int runs, boolean isWicket, String commentary) {
            this.runs = runs;
            this.isWicket = isWicket;
            this.commentary = commentary;
        }
    }
}
