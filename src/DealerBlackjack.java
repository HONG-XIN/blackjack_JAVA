/*
 * @author  : Dezhou Wang
 * @BU-id   : U46937632
 * @BU-class: CS591 P1
 * @Date    : Oct 6, 2019
 *
 * DealerBlackjack class to be use only for Blackjack
 * A person who holds a pile of cards in a game.
 */

public class DealerBlackjack extends Dealer implements GameResult {
    // Instances
    private boolean ifHideSecondCard;
    private HandBlackjack hand;
    private boolean ifStand;
    private int result;
    private int decksNum;

    // Constructors
    public DealerBlackjack(String name) {
        super(name);
        ifHideSecondCard = true;
        hand = new HandBlackjack();
        ifStand = false;
        result = NULL;
        decksNum = 1;
    }

    // Accessor methods
    public HandBlackjack getHand() {
        return hand;
    }

    public boolean getIfStand() {
        return ifStand;
    }

    public int getResult() {
        return result;
    }

    // Mutator methods
    private void revealSecondCard() {
        ifHideSecondCard = false;
    }

    public void setDecksNum(int decksNum) {
        if (decksNum > 8) {
            throw new IllegalArgumentException("In Blackjack, num of decks cannot be larger than 8!");
        } else if (decksNum < 1) {
            throw new IllegalArgumentException("In Blackjack, it's impossible to play without cards!");
        }
        this.decksNum = decksNum;
    }

    // Primary functions
    public void hit(Card c) {
        // If dealer has chose to stand, he cannot hit any more.
        if (ifStand) {
            throw new IllegalStateException("Dealer already stands!");
        }
        if (hand.getCardsNum() >= 2) {
            revealSecondCard();
        }
        hand.add(c);
        // If score >= 21, automatically stop
        int optimalScore = hand.getOptimalScore();
        if (optimalScore >= 21) {
            stand();
            if (optimalScore > 21) {
                result = BUST;
            }
        }
    }

    public void stand() {
        // Dealer cannot stand twice.
        if (ifStand) {
            throw new IllegalStateException("Player already stands!");
        }
        ifStand = true;
    }

    // Other functions
    public void setBlackjackPile() {
        setPile(decksNum, Deck.WITHOUT_JOKERS);
    }

    public void reset() {
        ifHideSecondCard = true;
        hand = new HandBlackjack();
        ifStand = false;
        result = NULL;
        setBlackjackPile();
        shufflePile();
    }

    public String getDetailsForList() {
        return super.toString();
    }

    public String showOthers() {
        String result = super.toString();
        String[] handStrings = hand.toString().split("\n");
        for (int i=0; i<handStrings.length; i++) {
            // When i == 2, it refers to the second card
            if (ifHideSecondCard && i == 2) {
                result += "\n[Hidden card]";
            } else if (handStrings[i].startsWith("Optimal")) {
                break;
            } else {
                result += "\n" + handStrings[i];
            }
        }
        return result;
    }

    // Override functions
    @Override
    public String toString() {
        String result = super.toString();
        String[] handStrings = hand.toString().split("\n");
        for (int i=0; i<handStrings.length; i++) {
            result += "\n" + handStrings[i];
        }
        if (this.result == BUST) {
            result += "\nBUST!!!";
        }
        return result;
    }
}
