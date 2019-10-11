/*
 * @author  : Dezhou Wang
 * @BU-id   : U46937632
 * @BU-class: CS591 P1
 * @Date    : Oct 6, 2019
 *
 * Deck class to represent ONE deck of cards
 * Could be with or without Jokers
 */

import java.util.ArrayList;
import java.util.Collections;

public class Deck implements DeckStaticVariables {
    // Instances
    private int ifWithJokers;
    private ArrayList<Card> undrawnCards; // Cards are not drawn yet, and still can be drawn
    private ArrayList<Card> drawnCards; // Cards already drawn and distribute to one player or dealer

    // Constructors
    public Deck(int ifWithJokers) {
        if(ifWithJokers != WITH_JOKERS && ifWithJokers != WITHOUT_JOKERS) {
            throw new IllegalArgumentException("Must specify with or without Jokers!");
        }
        this.ifWithJokers = ifWithJokers;
        undrawnCards = new ArrayList<Card>();
        drawnCards = new ArrayList<Card>();
        addCardsWithoutJokers();
        if(ifWithJokers == WITH_JOKERS) {
            addJokers();
        }
    }

    public Deck() {
        this(WITHOUT_JOKERS);
    }

    // Accessor methods
    public int getUndrawnCardsNum() {
        return undrawnCards.size();
    }

    // Other functions
    public void shuffle() {
        Collections.shuffle(undrawnCards);
    }

    public Card draw() {
        if (undrawnCards.size() == 0) {
            throw new IllegalStateException("The deck of cards has been drawn out!");
        }
        Card c = undrawnCards.remove(0);
        drawnCards.add(c);
        return c;
    }

    // Helper functions
    private void addCardsWithoutJokers() {
        int[] suits = CardStaticVariables.get4Suits();
        for (int suit: suits) {
            for (int i=1; i<=13; i++) {
                undrawnCards.add(new Card(suit, i));
            }
        }
    }

    private void addJokers() {
        undrawnCards.add(new Card(Card.LITTLE_JOKER));
        undrawnCards.add(new Card(Card.BIG_JOKER));
    }

    // Override functions
    @Override
    public String toString() {
        String result = "Deck " + undrawnCards.size() + "/";
        if (ifWithJokers == WITHOUT_JOKERS) {
            result += "52 without Jokers";
        } else {
            result += "54 with Jokers";
        }
        return result;
    }
}
