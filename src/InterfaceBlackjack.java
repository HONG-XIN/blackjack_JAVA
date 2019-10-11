/*
 * @author  : Dezhou Wang
 * @BU-id   : U46937632
 * @BU-class: CS591 P1
 * @Date    : Oct 6, 2019
 *
 * InterfaceBlackjack class print basic welcome and other messages in the progress of playing.
 */


import java.util.Scanner;

public class InterfaceBlackjack {
    // Static methods
    public static String welcome() {
        return  "+----------------------+\n" +
                "| Welcome to Blackjack |\n" +
                "+----------------------+";
    }

    public static String menuOptions() {
        return  "+-----------------------------+\n" +
                "| Menu Options:               |\n" +
                "| * Add,[player name],[money] | ex. Add,Jack,100\n" +
                "| * Remove,[player name]      | ex. Remove,Jack\n" +
                "| * Start                     |\n" +
                "| * Exit                      |\n" +
                "+-----------------------------+";
    }

    public static Object[] promptOption() {
        Object[] data = new Object[3];
        Scanner scan = new Scanner(System.in);
        while (true) {
            System.out.print("Enter your option: ");
            String userInput = scan.next();
            try {
                String[] inputs = userInput.split(",");
                if (inputs[0].toLowerCase().equals("add")) {
                    data[0] = "add";
                    data[1] = inputs[1];
                    data[2] = Integer.parseInt(inputs[2]);
                    break;
                } else if (inputs[0].toLowerCase().equals("remove")) {
                    data[0] = "remove";
                    data[1] = inputs[1];
                    break;
                } else if (inputs[0].toLowerCase().equals("start")) {
                    data[0] = "start";
                    break;
                } else if (inputs[0].toLowerCase().equals("exit")) {
                    data[0] = "exit";
                    break;
                } else {
                    System.out.println("Option unrecognized!");
                }
            } catch (Exception e) {
                System.out.println("Something failed!");
            }
        }
        return data;
    }

    public static String listPeople(GroupBlackjack group) {
        String result = "";
        if (group.getDealer() != null) {
            result += String.format("|| *.%1$s", group.getDealer().getDetailsForList());
        }
        for (int i=0; i<group.getPlayers().size(); i++) {
            result += String.format("\n|| %1$d. %2$s", i+1, group.getPlayers().get(i).getDetailsForList());
            result += "; Games: " +  group.getPlayers().get(i).getGames();
            result += "; Wins: " + group.getPlayers().get(i).getWins();
            result += "; Loses: " + group.getPlayers().get(i).getLoses();
            result += "; Ties: " + group.getPlayers().get(i).getTies();
        }
        return result;
    }

    public static String playerBetRequirement() {
        return  "+------------------------+\n" +
                "| Bet requirement:       |\n" +
                "| * any non-negative int |\n" +
                "+------------------------+";
    }

    public static String playerOptions() {
        return  "+-------------------+\n" +
                "| Player's Options: |\n" +
                "| * Hit             |\n" +
                "| * Stand           |\n" +
                "| * Split           |\n" +
                "| * DoubleUp        |\n" +
                "+-------------------+";
    }

    public static String dealerOptions() {
        return  "+-------------------+\n" +
                "| Dealer's Options: |\n" +
                "| * Hit             |\n" +
                "| * Stand           |\n" +
                "+-------------------+";
    }

    public static String showDealerVSOnePlayer(DealerBlackjack dealer, PlayerBlackjack player) {
        return dealer.showOthers() + "\n\n" + player;
    }

    public static String showDealerHand(DealerBlackjack dealer) {
        return dealer.toString();
    }

    public static String showSummary(GroupBlackjack group) {
        String result = "+--------------------------------------------+\n" +
                        "|                   Summary                  |\n" +
                        "+--------------------------------------------+";
        result += "\n" + group.getDealer();
        for (PlayerBlackjack p: group.getPlayers()) {
            result += "\n\n" + p;
        }
        result += "\n+--------------------------------------------+";
        return result;
    }
}
