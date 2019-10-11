/*
 * @author  : Dezhou Wang
 * @BU-id   : U46937632
 * @BU-class: CS591 P1
 * @Date    : Oct 6, 2019
 *
 * PlayerBlackjack class only use for Blackjack
 * Stands for general player in Blackjack
 * Could be extended for human or AI
 */

public class PlayerBlackjack extends Player implements PlayerBlackjackBehavior, GameResult {
    // Instances
    private HandBlackjack hand1;
    private HandBlackjack hand2;
    private int bet1;
    private int bet2;
    private boolean ifStand1;
    private boolean ifStand2;
    private int result1;
    private int result2;

    // Constructors
    public PlayerBlackjack(String name, int money) {
        super(name, money);
        if (money < 0) {
            throw new IllegalArgumentException("Player's money cannot be negative!");
        }
        reset();
    }

    // Accessor methods
    public HandBlackjack getHand1() {
        return hand1;
    }

    public HandBlackjack getHand2() {
        return hand2;
    }

    public int getBet1() {
        return bet1;
    }

    public int getBet2() {
        return bet2;
    }

    public boolean getIfStand1() {
        return ifStand1;
    }

    public boolean getIfStand2() {
        return ifStand2;
    }

    public int getResult1() {
        return result1;
    }

    public int getResult2() {
        return result2;
    }

    // Mutator methods
    public void setBet1(int m) {
        if (m < 0) {
            throw new IllegalArgumentException("Bet cannot be less than 0!");
        } else if (m + bet2 > getMoney().getValue()) {
            throw new IllegalArgumentException("Player does not have enough money to set this bet!");
        }
        bet1 = m;
    }

    public void setBet2(int m) {
        if (m < 0) {
            throw new IllegalArgumentException("Bet cannot be less than 0!");
        } else if (bet1 + m > getMoney().getValue()) {
            throw new IllegalArgumentException("Player does not have enough money to set this bet!");
        }
        bet2 = m;
    }

    public void setResult1(int r) {
        switch (r) {
            case NULL:
                result1 = NULL;
                break;
            case WIN:
                addOneWin();
                result1 = WIN;
                addMoney(bet1 * 2);
                break;
            case LOSE:
                addOneLose();
                result1 = LOSE;
                deductMoney(bet1);
                break;
            case TIE:
                addOneTie();
                result1 = TIE;
                break;
            case BUST:
                addOneLose();
                result1 = BUST;
                deductMoney(bet1);
                break;
            default:
                throw new IllegalArgumentException("Result option not configured!");
        }
        // Check if to add one game to records
        if (r != NULL) {
            if (!ifHasSplit()) {
                addOneGame();
            } else if (ifHasSplit() && result2 != NULL) {
                addOneGame();
            }
        }
    }

    public void setResult2(int r) {
        switch (r) {
            case NULL:
                result2 = NULL;
                break;
            case WIN:
                addOneWin();
                result2 = WIN;
                addMoney(bet2 * 2);
                break;
            case LOSE:
                addOneLose();
                result2 = LOSE;
                deductMoney(bet2);
                break;
            case TIE:
                addOneTie();
                result2 = TIE;
                break;
            case BUST:
                addOneLose();
                result2 = BUST;
                deductMoney(bet2);
                break;
            default:
                throw new IllegalArgumentException("Result option not configured!");
        }
        // Check if to add one game to records
        if (r != NULL) {
            if (result1 != NULL) {
                addOneGame();
            }
        }
    }

    // Primary functions
    public void hit1(Card c) {
        // If player has chose to stand, he cannot hit any more.
        if (ifStand1) {
            throw new IllegalStateException("Player's hand1 already stands!");
        }
        hand1.add(c);
        // If score reaches 21, automatically stop
        int optimalScore = hand1.getOptimalScore();
        if (optimalScore >= 21) {
            stand1();
            if (optimalScore > 21) { // bust
                setResult1(BUST);
            }
        }
    }

    public void hit2(Card c) {
        if (ifStand2) {
            throw new IllegalStateException("Player's hand2 already stands or not split!");
        }
        hand2.add(c);
        // If score reaches 21, automatically stop
        int optimalScore = hand2.getOptimalScore();
        if (optimalScore >= 21) {
            stand2();
            if (optimalScore > 21) { // bust
                setResult2(BUST);
            }
        }
    }

    public void stand1() {
        if (ifStand1) {
            throw new IllegalStateException("Player's hand1 cannot stand twice!");
        }
        ifStand1 = true;
    }

    public void stand2() {
        if (ifStand2) {
            throw new IllegalStateException("Player's hand2 cannot stand twice!");
        }
        ifStand2 = true;
    }

    public void split(Card c1, Card c2) {
        if (ifStand1) {
            throw new IllegalStateException("Player's hand1 already stands!");
        } else if (!ifAbleToSplit()) {
            throw new IllegalStateException("Player's hand1 is not able to split!");
        } else {
            hand2.add(hand1.split());
            ifStand2 = false;
            setBet2(bet1);
            hit1(c1);
            hit2(c2);
        }
    }

    public void doubleUp1(Card c) {
        if (ifStand1) {
            throw new IllegalStateException("Player's hand1 already stands!");
        } else if (!ifAbleToDoubleUp1()) {
            throw new IllegalStateException("Player does not have enough money to doubleUp hand1!");
        } else {
            setBet1(bet1 * 2);
            hit1(c);
            if (!ifStand1) { // If player hit another card, it may automatically stand.
                stand1();
            }
        }
    }

    public void doubleUp2(Card c) {
        if (ifStand2) {
            throw new IllegalStateException("Player's hand2 already stands!");
        }
        if (!ifAbleToDoubleUp2()) {
            throw new IllegalStateException("Player does not have enough money to doubleUp hand2!");
        } else {
            setBet2(bet2 * 2);
            hit2(c);
            if (!ifStand2) { // If player hit another card, it may automatically stand.
                stand2();
            }
        }
    }

    // Other functions
    public boolean getIfStand() {
        // Both hand1 and hand2 should stand
        return ifStand1 && ifStand2;
    }

    public String getResult1String() {
        if (result1 == NULL) {
            return "NULL";
        } else if (result1 == WIN) {
            return "WIN";
        } else if (result1 == LOSE) {
            return "LOSE";
        } else if (result1 == TIE) {
            return "TIE";
        } else {
            return "BUST";
        }
    }

    public String getResult2String() {
        if (result2 == NULL) {
            return "NULL";
        } else if (result2 == WIN) {
            return "WIN";
        } else if (result2 == LOSE) {
            return "LOSE";
        } else if (result2 == TIE) {
            return "TIE";
        } else {
            return "BUST";
        }
    }

    public void reset() {
        hand1 = new HandBlackjack();
        hand2 = new HandBlackjack();
        setBet1(0);
        setBet2(0);
        ifStand1 = false;
        ifStand2 = true; // Start with only one hand
        result1 = NULL;
        result2 = NULL;
    }

    public boolean ifHasSplit() {
        return hand2.getCardsNum() > 0;
    }

    public boolean ifAbleToSplit() {
        // It should satisfy:
        // * hand1 does not stand
        // * has not split before
        // * the 2 cards in hand1 is able to split
        // * money in hand is greater than double of bet1
        return !ifStand1 && !ifHasSplit() && hand1.isSplittable() && bet1*2 <= getMoney().getValue();
    }

    public boolean ifAbleToDoubleUp1() {
        // It should satisfy:
        // * hand1 does not stand
        // * If double bet1, it should still smaller the money in pocket
        return !ifStand1 && bet1*2+bet2 <= getMoney().getValue();
    }

    public boolean ifAbleToDoubleUp2() {
        // It should satisfy:
        // * hand2 does not stand
        // * If double bet2, it should still smaller the money in pocket
        return !ifStand2 && bet1+bet2*2 <= getMoney().getValue();
    }

    public void checkHands(HandBlackjack h) {
        checkHand1Result(h);
        if (ifHasSplit()) {
            checkHand2Result(h);
        }
    }

    public String getDetailsForList() {
        return getName() + "; " + getMoney();
    }

    // Helper functions
    private void checkHand1Result(HandBlackjack h) {
        if (result1 != NULL) { // Already get a result
            return;
        }
        if (hand1.compareTo(h) > 0) {
            setResult1(WIN);
        } else if (hand1.compareTo(h) == 0) {
            setResult1(TIE);
        } else {
            setResult1(LOSE);
        }
    }

    private void checkHand2Result(HandBlackjack h) {
        if (result2 != NULL) { // Already get a result
            return;
        }
        if (hand2.compareTo(h) > 0) {
            setResult2(WIN);
        } else if (hand2.compareTo(h) == 0) {
            setResult2(TIE);
        } else {
            setResult2(LOSE);
        }
    }

    // Overrides functions
    @Override
    public String toString() {
        String result = super.toString();
        if (ifHasSplit()) {
            result += "\nHand1; bet: $" + bet1;
            result += "\n" + hand1;
            if (result1 != NULL) {
                result += "\n" + getResult1String() + "!!!";
            }
            result += "\nHand2; bet: $" + bet2;
            result += "\n" + hand2;
            if (result2 != NULL) {
                result += "\n" + getResult2String() + "!!!";
            }
        } else {
            result += "; bet: $" + bet1;
            result += "\n" + hand1;
            if (result1 != NULL) {
                result += "\n" + getResult1String() + "!!!";
            }
        }
        return result;
    }
}
