/*
 * @author  : Dezhou Wang
 * @BU-id   : U46937632
 * @BU-class: CS591 P1
 * @Date    : Oct 6, 2019
 *
 * PlayerTriantaEnaHuman class implements scanner to prompt for user's input
 * If role is banker, it will hit until 27. Automatically, some kind of like an AI.
 */

import java.util.Scanner;

public class PlayerTriantaEnaHuman extends PlayerTriantaEna{
    // Instances
    Scanner scan;

    // Constructors
    public PlayerTriantaEnaHuman(String name) {
        super(name);
        scan = new Scanner(System.in);
    }

    // Other functions
    public boolean promptWillingToBeBanker() {
        while (true) {
            System.out.print("Player \"" + getName() + "\", are you willing to become a banker? ");
            String userInput = scan.next().toLowerCase();
            if (userInput.equals("yes") | userInput.equals("y")) {
                return true;
            } else if (userInput.equals("no") | userInput.equals("n")) {
                return false;
            } else {
                System.out.println("~ Fail! Unrecognized option!");
            }
        }
    }

    public String promptOptionAfter1stCard() {
        while (true) {
            System.out.print("Player \"" + getName() + "\", enter a value for bet or 'fold': ");
            String userInput = scan.next().toLowerCase();
            if (userInput.equals("fold") || userInput.equals("f")) {
                return "fold";
            } else {
                try {
                    bet(Integer.parseInt(userInput));
                    return "bet";
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        }
    }

    public String promptOptionAfterBet() {
        if (getRole() == PLAYER) { // Player
            return promptOptionPlayer();
        } else { // Banker
            return promptOptionBanker();
        }
    }

    // Helper functions
    private String promptOptionPlayer() {
        // Only accept "hit" or "stand"
        while (true) {
            System.out.print("Player \"" + getName() + "\", enter your option: ");
            String userInput = scan.next().toLowerCase();
            if (userInput.equals("hit") || userInput.equals("h")) {
                return "hit";
            } else if (userInput.equals("stand") || userInput.equals("s")) {
                return "stand";
            } else {
                System.out.println("~ Fail! Unrecognized option!");
            }
        }
    }

    private String promptOptionBanker() {
        int score = getOptimalScore();
        if (score >= 27) {
            return "stand";
        } else {
            return "hit";
        }
    }
}
