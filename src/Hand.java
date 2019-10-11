/*
 * @author  : Dezhou Wang
 * @BU-id   : U46937632
 * @BU-class: CS591 P1
 * @Date    : Oct 6, 2019
 *
 * Hand class to represent a hand of cards held by a people
 */

import java.util.ArrayList;

public class Hand{
    // Instances
    private ArrayList<Card> cards;

    // Constructors
    public Hand() {
        cards = new ArrayList<Card>();
    }

    // Accessor methods
    public int getCardsNum() {
        return cards.size();
    }

    public ArrayList<Card> getCards() {
        return cards;
    }

    // Mutator methods
    public void add(Card c) {
        cards.add(c);
    }

    // Override functions
    @Override
    public String toString() {
        String result = "Cards number: " + getCardsNum();
        for (Card c: cards) {
            result += "\n" + c;
        }
        return result;
    }
}
