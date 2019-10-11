/*
 * @author  : Dezhou Wang
 * @BU-id   : U46937632
 * @BU-class: CS591 P1
 * @Date    : Oct 6, 2019
 *
 * Pile class can contain multiple decks of cards
 */

import java.util.ArrayList;
import java.util.Collections;

public class Pile{
    // Instances
    private Deck[] decks;
    private ArrayList<Integer> drawSequence;

    // Constructors
    public Pile(int numDecks, int ifWithJokers) {
        if (numDecks < 0) {
            throw new IllegalArgumentException("Decks number cannot be negative!");
        }
        decks = new Deck[numDecks];
        drawSequence = new ArrayList<Integer>();
        for (int i=0; i<numDecks; i++) {
            decks[i] = new Deck(ifWithJokers);
            drawSequence.addAll(Collections.nCopies(decks[i].getUndrawnCardsNum(), i));
        }
    }

    public Pile() {
        this(1, Deck.WITHOUT_JOKERS);
    }

    // Accessor methods
    public int getDecksNum() {
        return decks.length;
    }

    public int getUndrawnCardsNum() {
        int num = 0;
        for (Deck d: decks) {
            num += d.getUndrawnCardsNum();
        }
        return num;
    }

    // Other functions
    public void shuffle() {
        Collections.shuffle(drawSequence);
        for (Deck d: decks) {
            d.shuffle();
        }
    }

    public Card draw() {
        return decks[drawSequence.remove(0)].draw();
    }

    public int getLeftNumber() {
        return drawSequence.size();
    }

    // Override
    @Override
    public String toString() {
        String result = "Decks number: " + getDecksNum();
        for (Deck d: decks) {
            result += "\n" + d;
        }
        return result;
    }
}
