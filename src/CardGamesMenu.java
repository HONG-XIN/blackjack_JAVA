/*
 * @author  : Dezhou Wang
 * @BU-id   : U46937632
 * @BU-class: CS591 P1
 * @Date    : Oct 6, 2019
 *
 * CardGamesMenu allows users to choose which game to play
 */

public class CardGamesMenu {
    // Static functions
    public static void play() {
        System.out.println(InterfaceCardGames.welcome());
        while (true) {
            System.out.println(InterfaceCardGames.menuOptions());
            String input = InterfaceCardGames.promptOption();
            if (input.equals("1")) {
                Blackjack b = new Blackjack();
                b.playGames();
            } else if (input.equals("2")) {
                TriantaEna t = new TriantaEna();
                t.playGames();
            } else if (input.equals("exit")) {
                System.out.println("Goodbye~");
                break;
            }
        }
    }
}
