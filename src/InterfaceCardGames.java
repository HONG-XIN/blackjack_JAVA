/*
 * @author  : Dezhou Wang
 * @BU-id   : U46937632
 * @BU-class: CS591 P1
 * @Date    : Oct 6, 2019
 *
 * InterfaceCardGames is the main menu.
 * User can choose which card game to play here.
 */


import java.util.Scanner;

public class InterfaceCardGames {
    // Static functions
    public static String welcome() {
        return  "+------------------------+\n" +
                "| Welcome to Card Games  |\n" +
                "+------------------------+";
    }

    public static String menuOptions() {
        return  "+-----------------------------+\n" +
                "| Menu Options:               |\n" +
                "| * 1. Blackjack              | ex. 1\n" +
                "| * 2. Trianta Ena            | ex. 2\n" +
                "| * Exit                      |\n" +
                "+-----------------------------+";
    }

    public static String promptOption() {
        Scanner scan = new Scanner(System.in);
        while (true) {
            System.out.print("Enter your option: ");
            String userInput = scan.next().toLowerCase();
            try {
                if (userInput.equals("1")) {
                    return "1";
                } else if (userInput.equals("2")) {
                    return "2";
                } else if (userInput.equals("exit")) {
                    return "exit";
                } else {
                    System.out.println("Option unrecognized!");
                }
            } catch (Exception e) {
                System.out.println("Something failed!");
            }
        }
    }
}
