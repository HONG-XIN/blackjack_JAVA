/*
 * @author  : Dezhou Wang
 * @BU-id   : U46937632
 * @BU-class: CS591 P1
 * @Date    : Oct 6, 2019
 *
 * InterfaceTriantaEna class only uses for Trianta Ena.
 * It prints welcome and other information.
 */

import java.util.ArrayList;
import java.util.Scanner;

public class InterfaceTriantaEna {
    // Static functions
    public static String welcome() {
        return  "+------------------------+\n" +
                "| Welcome to Trianta Ena |\n" +
                "+------------------------+";
    }

    public static String menuOptions() {
        return  "+-----------------------------+\n" +
                "| Menu Options:               |\n" +
                "| * Add,[player name]         | ex. Add,Jack\n" +
                "| * Remove,[player name]      | ex. Remove,Jack\n" +
                "| * Set,[money]               | Set money for all players, and bankers = 3*money. ex. Set,100\n" +
                "| * Start                     |\n" +
                "| * Exit                      |\n" +
                "+-----------------------------+";
    }

    public static Object[] promptOption() {
        Object[] data = new Object[2];
        Scanner scan = new Scanner(System.in);
        while (true) {
            System.out.print("Enter your option: ");
            String userInput = scan.next();
            try {
                String[] inputs = userInput.split(",");
                if (inputs[0].toLowerCase().equals("add")) {
                    data[0] = "add";
                    data[1] = inputs[1];
                    break;
                } else if (inputs[0].toLowerCase().equals("remove")) {
                    data[0] = "remove";
                    data[1] = inputs[1];
                    break;
                } else if (inputs[0].toLowerCase().equals("set")) {
                    data[0] = "set";
                    data[1] = Integer.parseInt(inputs[1]);
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

    public static String listPeople(GroupTriantaEna group) {
        String result = "";
        PlayerTriantaEna banker = group.getBanker();
        if (banker != null) {
            result += String.format("\n|| *.%1$s", banker.getDetailsForList());
            result += "; Games: " + banker.getGames();
            result += "; Wins: " + banker.getWins();
            result += "; Loses: " + banker.getLoses();
            result += "; Number of being banker: " + banker.getNumberOfBeingBanker();
        }
        ArrayList<Integer> playersIndexes = group.getPlayersIndexes();
        int bankerIndex = group.getBankerIndex();
        int num = 0;
        for (int i: playersIndexes) {
            if (i == bankerIndex) {
                continue;
            } else {
                num += 1;
                PlayerTriantaEna player = group.getPlayerByIndex(i);
                result += String.format("\n|| %1$d. %2$s", num, player.getDetailsForList());
                result += "; Games: " + player.getGames();
                result += "; Wins: " + player.getWins();
                result += "; Loses: " + player.getLoses();
                result += "; Number of being banker: " + player.getNumberOfBeingBanker();
                if (!player.isAbleToPlay()) {
                    result += "; Unable to play";
                }
            }
        }
        return result;
    }

    public static String playerIfWantBeBankerRequirement() {
        return  "+-------------------------+\n" +
                "| Willing become a Banker |\n" +
                "| * Yes or Y              |\n" +
                "| * No or N               |\n" +
                "+-------------------------+";
    }

    public static String playerBetOrFoldRequirement() {
        return  "+------------------------+\n" +
                "| Bet or fold:           |\n" +
                "| * positive int         | ex. 10\n" +
                "| * fold                 | ex. fold\n" +
                "+------------------------+";
    }

    public static String playerOptions() {
        return  "+-------------------+\n" +
                "| Player's Options: |\n" +
                "| * Hit             |\n" +
                "| * Stand           |\n" +
                "+-------------------+";
    }

    public static String showBankerVSOnePlayer(PlayerTriantaEna banker, PlayerTriantaEna player) {
        return banker + "\n\n" + player;
    }

    public static String showBankerHand(PlayerTriantaEna banker) {
        return banker.toString();
    }

    public static String showSummary(GroupTriantaEna group) {
        String result = "+--------------------------------------------+\n" +
                        "|                   Summary                  |\n" +
                        "+--------------------------------------------+";
        result += "\n" + group.getBanker();
        ArrayList<Integer> playersIndexes = group.getPlayersIndexes();
        int bankerIndex = group.getBankerIndex();
        for (int i: playersIndexes) {
            if (i == bankerIndex) {
                continue;
            } else {
                PlayerTriantaEna player = group.getPlayerByIndex(i);
                result += "\n\n" + player;
            }
        }
        result += "\n+--------------------------------------------+";
        return result;
    }
}
