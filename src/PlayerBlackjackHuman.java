/*
 * @author  : Dezhou Wang
 * @BU-id   : U46937632
 * @BU-class: CS591 P1
 * @Date    : Oct 6, 2019
 *
 * PlayerBlackjackHuman class implements scanner to prompt for user input
 */

import java.util.Scanner;

public class PlayerBlackjackHuman extends PlayerBlackjack {
    // Instances
    private Scanner scan;

    // Constructors
    public PlayerBlackjackHuman(String name, int money) {
        super(name, money);
        scan = new Scanner(System.in);
    }

    // Other functions
    public void promptBet() {
        while(true) {
            System.out.print("Player \""+getName()+"\", enter your bet: ");
            try {
                setBet1(Integer.parseInt(scan.next()));
                break;
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    public String promptOption1() {
        // Only accept "hit", "stand", "split", "doubleUp"
        while (true) {
            if (!ifHasSplit()) {
                System.out.print("Player \""+getName()+"\", enter your option: ");
            } else {
                System.out.print("Player \""+getName()+"\", enter your option for Hand1: ");
            }
            String result = checkOption();
            if (result == null) {
                System.out.println("~ Fail! Unrecognized option!");
            } else {
                return result;
            }
        }
    }

    public String promptOption2() {
        // Only accept "hit", "stand", "doubleUp"
        while (true) {
            System.out.print("Player \""+getName()+"\", enter your option for Hand2: ");
            String result = checkOption();
            if (result == null) {
                System.out.println("~ Fail! Unrecognized option!");
            } else {
                return result;
            }
        }
    }

    // Helper functions
    private String checkOption() {
        String s = scan.next().toLowerCase();
        if (s.equals("hit") || s.equals("h")) {
            return "hit";
        } else if (s.equals("stand") || s.equals("st")) {
            return "stand";
        } else if (s.equals("split") || s.equals("sp")) {
            return "split";
        } else if (s.equals("doubleup") || s.equals("d")) {
            return "doubleup";
        } else {
            return null;
        }
    }
}
