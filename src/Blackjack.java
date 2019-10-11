/*
 * @author  : Dezhou Wang
 * @BU-id   : U46937632
 * @BU-class: CS591 P1
 * @Date    : Oct 6, 2019
 *
 * Blackjack class only use for blackjack
 * Store the game rules and logic
 */

public class Blackjack extends CardGame{
    // Instances
    private GroupBlackjack group;

    // Constructors
    public Blackjack() {
        group = new GroupBlackjack();
    }

    // Other functions
    public void playGames() {
        System.out.println(InterfaceBlackjack.welcome());
        System.out.println(InterfaceBlackjack.menuOptions());
        group.setDealer(new DealerBlackjackHuman("Dealer"));
        while (true) {
            System.out.println(InterfaceBlackjack.listPeople(group));
            Object[] inputs = InterfaceBlackjack.promptOption();
            try {
                if (((String) inputs[0]).compareTo("add") == 0) {
                    group.addOnePlayer(new PlayerBlackjackHuman((String) inputs[1], (Integer) inputs[2]));
                } else if (((String) inputs[0]).compareTo("remove") == 0) {
                    group.kickOnePlayer((String) inputs[1]);
                } else if (((String) inputs[0]).compareTo("start") == 0) {
                    playOneGame();
                    System.out.println(InterfaceBlackjack.menuOptions());
                } else if (((String) inputs[0]).compareTo("exit") == 0) {
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
            throw new IllegalStateException("The group cannot start a game!");
        } else {
            System.out.println("*************** One game start ***************");
            group.reset();
            // Distribute 2 cards to everyone
            stageFirst2Cards();
            // Player stage
            stagePlayers();
            // Dealer stage
            stageDealer();
            // Summary
            stageSummary();
            System.out.println("*************** One game ends  ***************");
        }
    }

    // Helper functions
    private void stageFirst2Cards() {
        for (int i=0; i<2; i++) {
            for (PlayerBlackjack p: group.getPlayers()) {
                p.hit1(group.getDealerDistributeOneCard());
            }
            group.getDealer().hit(group.getDealerDistributeOneCard());
        }
    }

    private void stagePlayers() {
        // Players set bet
        stagePlayersSetBet();
        // Players choose options in turns
        stagePlayersPlayTillEnd();
    }

    private void stagePlayersSetBet() {
        System.out.println(InterfaceBlackjack.playerBetRequirement());
        for (PlayerBlackjack p: group.getPlayers()) {
            // If p is a human
            if (p instanceof PlayerBlackjackHuman) {
                System.out.println(InterfaceBlackjack.showDealerVSOnePlayer(group.getDealer(), p));
                ((PlayerBlackjackHuman) p).promptBet();
            }
            // If p is an AI
            // ...
        }
    }

    private void stagePlayersPlayTillEnd() {
        System.out.println(InterfaceBlackjack.playerOptions());
        while (!group.arePlayersAllStand()) {
            stagePlayersPlayOne();
        }
    }

    private void stagePlayersPlayOne() {
        for (PlayerBlackjack p: group.getPlayers()) {
            // If p is a human
            if (p instanceof PlayerBlackjackHuman) {
                boolean ifSplitThisRound = false;
                if (!p.getIfStand1()) {
                    while (true) {
                        try {
                            System.out.println(InterfaceBlackjack.showDealerVSOnePlayer(group.getDealer(), p));
                            String userInput = ((PlayerBlackjackHuman) p).promptOption1();
                            if (userInput.compareTo("hit") == 0) {
                                p.hit1(group.getDealerDistributeOneCard());
                                if (p.getIfStand()) {
                                    System.out.println(InterfaceBlackjack.showDealerVSOnePlayer(group.getDealer(), p));
                                }
                                break;
                            } else if (userInput.compareTo("stand") == 0) {
                                p.stand1();
                                break;
                            } else if (userInput.compareTo("split") == 0) {
                                if (!p.ifAbleToSplit()) {
                                    throw new IllegalStateException("Player are not able to split!");
                                } else {
                                    ifSplitThisRound = true;
                                    p.split(group.getDealerDistributeOneCard(), group.getDealerDistributeOneCard());
                                    break;
                                }
                            } else if (userInput.compareTo("doubleup") == 0) {
                                if (!p.ifAbleToDoubleUp1()) {
                                    throw new IllegalStateException("Player does not have enough money to double up!");
                                } else {
                                    p.doubleUp1(group.getDealerDistributeOneCard());
                                    break;
                                }
                            } else { // Code will never reach here
                                System.out.println("Option not configured!");
                            }
                        } catch (Exception e) {
                            System.out.println(e);
                        }
                    }
                }
                if (!ifSplitThisRound && !p.getIfStand2()) {
                    while (true) {
                        try {
                            System.out.println(InterfaceBlackjack.showDealerVSOnePlayer(group.getDealer(), p));
                            String userInput = ((PlayerBlackjackHuman) p).promptOption2();
                            if (userInput.compareTo("hit") == 0) {
                                p.hit2(group.getDealerDistributeOneCard());
                                if (p.getIfStand2()) {
                                    System.out.println(InterfaceBlackjack.showDealerVSOnePlayer(group.getDealer(), p));
                                }
                                break;
                            } else if (userInput.compareTo("stand") == 0) {
                                p.stand2();
                                break;
                            } else if (userInput.compareTo("doubleup") == 0) {
                                if (!p.ifAbleToDoubleUp2()) {
                                    throw new IllegalStateException("Player does not have enough money to double up!");
                                } else {
                                    p.doubleUp2(group.getDealerDistributeOneCard());
                                    break;
                                }
                            } else { // Code will never reach here
                                System.out.println("Option not configured!");
                            }
                        } catch (Exception e) {
                            System.out.println(e);
                        }
                    }
                }
            }
            // If p an AI
            // ...
        }
    }

    private void stageDealer() {
        // Dealer play till stand
        stageDealerPlayTillEnd();
        // Check result of all players
        stageDealerCheckResult();
    }

    private void stageDealerPlayTillEnd() {
        System.out.println(InterfaceBlackjack.dealerOptions());
        while (!group.getDealer().getIfStand()) {
            stageDealerPlayOne();
        }
    }

    private void stageDealerPlayOne() {
        DealerBlackjack dealer = group.getDealer();
        // If dealer is a human
        if (dealer instanceof DealerBlackjackHuman) {
            while (true) {
                try {
                    System.out.println(InterfaceBlackjack.showDealerHand(dealer));
                    String userInput = ((DealerBlackjackHuman) dealer).promptOption();
                    if (userInput.compareTo("hit") == 0) {
                        dealer.hit(group.getDealerDistributeOneCard());
                        if (dealer.getIfStand()) {
                            System.out.println(InterfaceBlackjack.showDealerHand(dealer));
                        }
                        break;
                    } else if (userInput.compareTo("stand") == 0) {
                        dealer.stand();
                        break;
                    } else { // Code will never reach here
                        System.out.println("Option not configured!");
                    }
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        }
        // If dealer is an AI
        // ...
    }

    private void stageDealerCheckResult() {
        DealerBlackjack dealer = group.getDealer();
        dealer.addOneGame();
        for (PlayerBlackjack p: group.getPlayers()) {
            p.checkHands(group.getDealer().getHand());
        }
    }

    private void stageSummary() {
        System.out.println(InterfaceBlackjack.showSummary(group));
    }
}
