/*
 * @author  : Dezhou Wang
 * @BU-id   : U46937632
 * @BU-class: CS591 P1
 * @Date    : Oct 6, 2019
 *
 * General dealer class
 * A person who holds a pile of cards in a game.
 * A dealer does not need to count the money.
 */

public class Dealer extends People {
    // Instances
    private Pile pile;

    // Constructors
    public Dealer(String name) {
        super(name, 0);
    }

    // Other functions
    public void setPile(int numDecks, int ifWithJokers) {
        pile = new Pile(numDecks, ifWithJokers);
    }

    public void shufflePile() {
        pile.shuffle();
    }

    public Card distribute() {
        return pile.draw();
    }

    // Override functions
    @Override
    public String toString() {
        return "Dealer-\"" + getName() + "\"; games: " + getGames();
    }

}
