/*
 * @author  : Dezhou Wang
 * @BU-id   : U46937632
 * @BU-class: CS591 P1
 * @Date    : Oct 6, 2019
 *
 * TriantaEna class only use for Trianta Ena
 * Store the game rules and logic
 */

import java.lang.reflect.Array;
import java.util.ArrayList;

public class TriantaEna extends CardGame{
    // Instances
    private GroupTriantaEna group;

    // Constructors
    public TriantaEna() {
        group = new GroupTriantaEna();
    }

    // Other functions
    public void playGames() {
        System.out.println(InterfaceTriantaEna.welcome());
        System.out.println(InterfaceTriantaEna.menuOptions());
        while (true) {
            System.out.println(InterfaceTriantaEna.listPeople(group));
            Object[] inputs = InterfaceTriantaEna.promptOption();
            try {
                if (((String) inputs[0]).equals("add")) {
                    group.addOnePlayer(new PlayerTriantaEnaHuman((String) inputs[1]));
                } else if (((String) inputs[0]).equals("remove")) {
                    group.kickOnePlayer((String) inputs[1]);
                } else if (((String) inputs[0]).equals("set")) {
                    group.setMoney((int) inputs[1]);
                } else if (((String) inputs[0]).equals("start")) {
                    playOneGame();
                    System.out.println(InterfaceTriantaEna.menuOptions());
                } else if (((String) inputs[0]).equals("exit")) {
                    System.out.println("Goodbye~");
                    break;
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    public void playOneGame() {
        if (!group.isAbleToStart()) {
            throw new IllegalStateException("Unable to start the game!");
        } else {
            System.out.println("*************** One game start ***************");
            group.reset();
            group.sortByMoney();
            stageAskToBeBanker();
            group.distributeOneCardToEveryone();
            stagePlayersBetOrFold();
            group.distributeOneCardToEveryone();
            group.distributeOneCardToEveryone();
            stagePlayersPlayTillEnd();
            stageBankerPlayTillEnd();
            stageCheckResult();
            stageSummary();
            System.out.println("*************** One game ends  ***************");
        }
    }

    // Helper functions
    private void stageAskToBeBanker() {
        int bankerIndex = group.getBankerIndex();
        PlayerTriantaEna banker = group.getBanker();
        ArrayList<Integer> playersIndexes = group.getPlayersIndexes();
        int lastPlayableIndex = -1;
        boolean bankerNoChange = false;
        boolean noOneWantToBeBanker = true;
        for (int i: playersIndexes) {
            if (i == bankerIndex) {
                bankerNoChange = true;
                break;
            } else {
                PlayerTriantaEna player = group.getPlayerByIndex(i);
                if (player.isAbleToPlay()) {
                    lastPlayableIndex = i;
                    System.out.println(InterfaceTriantaEna.playerIfWantBeBankerRequirement());
                    boolean ifWilling = ((PlayerTriantaEnaHuman) player).promptWillingToBeBanker();
                    if (ifWilling) {
                        player.setRole(PlayerTriantaEnaRoles.BANKER);
                        banker.setRole(PlayerTriantaEnaRoles.PLAYER);
                        group.setBankerIndex(i);
                        noOneWantToBeBanker = false;
                        break;
                    }
                } else {
                    break;
                }
            }
        }
        if (!bankerNoChange && noOneWantToBeBanker) {
            PlayerTriantaEna player = group.getPlayerByIndex(lastPlayableIndex);
            player.setRole(PlayerTriantaEnaRoles.BANKER);
            banker.setRole(PlayerTriantaEnaRoles.PLAYER);
            group.setBankerIndex(lastPlayableIndex);
        }
    }

    private void stagePlayersBetOrFold() {
        System.out.println(InterfaceTriantaEna.playerBetOrFoldRequirement());
        int bankerIndex = group.getBankerIndex();
        PlayerTriantaEna banker = group.getBanker();
        ArrayList<Integer> playersIndexes = group.getPlayersIndexes();
        for (int i: playersIndexes) {
            if (i == bankerIndex) {
                continue;
            } else {
                PlayerTriantaEna player = group.getPlayerByIndex(i);
                if (!player.isAbleToPlay()) {
                    break;
                } else if (!player.getIfStand()) {
                    // If player is a human
                    if (player instanceof PlayerTriantaEnaHuman) {
                        while (true) {
                            System.out.println(InterfaceTriantaEna.showBankerVSOnePlayer(banker, player));
                            String userInput = ((PlayerTriantaEnaHuman) player).promptOptionAfter1stCard();
                            if (userInput.equals("fold")) {
                                player.fold();
                                System.out.println(player);
                                break;
                            } else if (userInput.equals("bet")) {
                                break;
                            } else {
                                System.out.println("Option not configured!");
                            }
                        }
                    }
                    // If player is an AI
                }
            }
        }
    }

    private void stagePlayersPlayTillEnd() {
        System.out.println(InterfaceTriantaEna.playerOptions());
        while (!group.areAllPlayersStand()) {
            stagePlayersPlayOne();
        }
    }

    private void stagePlayersPlayOne() {
        int bankerIndex = group.getBankerIndex();
        PlayerTriantaEna banker = group.getBanker();
        ArrayList<Integer> playersIndexes = group.getPlayersIndexes();
        for (int i: playersIndexes) {
            if (i == bankerIndex) {
                continue;
            } else {
                PlayerTriantaEna player = group.getPlayerByIndex(i);
                if (!player.getIfStand()) {
                    // If player is a human
                    if (player instanceof PlayerTriantaEnaHuman) {
                        while (true) {
                            System.out.println(InterfaceTriantaEna.showBankerVSOnePlayer(banker, player));
                            String userInput = ((PlayerTriantaEnaHuman) player).promptOptionAfterBet();
                            if (userInput.equals("hit")) {
                                player.hit(group.distributeOneCard());
                                if (player.getIfStand()) {
                                    System.out.println(InterfaceTriantaEna.showBankerVSOnePlayer(banker, player));
                                    if (player.getResult() == GameResult.BUST) {
                                        banker.addMoney(player.getBet());
                                    }
                                }
                                break;
                            } else if (userInput.equals("stand")) {
                                player.stand();
                                break;
                            } else {
                                System.out.println("Option not configured!");
                            }
                        }
                    }
                    // If player is an AI
                }
            }
        }
    }

    private void stageBankerPlayTillEnd() {
        PlayerTriantaEna banker = group.getBanker();
        while (!banker.getIfStand()) {
            stagetBankerPlayOne();
        }
    }

    private void stagetBankerPlayOne() {
        PlayerTriantaEna banker = group.getBanker();
        if (banker instanceof PlayerTriantaEnaHuman) {
            while (true) {
                String userInput = ((PlayerTriantaEnaHuman) banker).promptOptionAfterBet();
                if (userInput.equals("hit")) {
                    banker.hit(group.distributeOneCard());
                    if (banker.getIfStand()) {
                        System.out.println(InterfaceTriantaEna.showBankerHand(banker));
                    }
                    break;
                } else if (userInput.equals("stand")) {
                    banker.stand();
                    break;
                } else {
                    System.out.println("Option not configured!");
                }
            }
        }
    }

    private void stageCheckResult() {
        int bankerIndex = group.getBankerIndex();
        PlayerTriantaEna banker = group.getBanker();
        banker.addOneNumberOfBeingBanker();
        HandTriantaEna bankerHand = banker.getHand();
        ArrayList<Integer> playersIndexes = group.getPlayersIndexes();
        for (int i: playersIndexes) {
            if (i == bankerIndex) {
                continue;
            } else {
                PlayerTriantaEna player = group.getPlayerByIndex(i);
                if (player.getIfJoin()) {
                    player.checkHand(bankerHand);
                    if (player.getResult() == GameResult.WIN) {
                        banker.deductMoney(player.getBet());
                    } else if (player.getResult() == GameResult.LOSE) {
                        banker.addMoney(player.getBet());
                    }
                } else {
                    break;
                }
            }
        }
    }

    private void stageSummary() {
        System.out.println(InterfaceTriantaEna.showSummary(group));
    }
}
