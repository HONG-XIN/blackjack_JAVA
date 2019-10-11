/*
 * @author  : Dezhou Wang
 * @BU-id   : U46937632
 * @BU-class: CS591 P1
 * @Date    : Oct 6, 2019
 *
 * DealerBlackjackHuman class implements scanner to prompt inputs
 */

import java.util.Scanner;

public class DealerBlackjackHuman extends DealerBlackjack {
    // Instances
    private Scanner scan;

    // Constructors
    public DealerBlackjackHuman(String name) {
        super(name);
        scan = new Scanner(System.in);
    }

    // Other functions
    public String promptOption() {
        // Only accept "hit" or "stand"
        while (true) {
            System.out.print("Dealer \"" + getName() + "\", enter your option: ");
            String userInput = scan.next().toLowerCase();
            if (userInput.equals("hit")) {
                return "hit";
            } else if (userInput.equals("stand")) {
                return "stand";
            } else {
                System.out.println("~ Fail! Unrecognized option!");
            }
        }
    }

}
