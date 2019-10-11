/*
 * @author  : Dezhou Wang
 * @BU-id   : U46937632
 * @BU-class: CS591 P1
 * @Date    : Oct 6, 2019
 *
 * GameResultRecord class records:
 * number of wins
 * number of loses
 * number of ties
 * number of being a banker
 */

public class GameResultRecord {
    // Instances
    private int wins;
    private int loses;
    private int ties;
    private int numberOfBeingBanker;

    // Constructors
    public GameResultRecord() {
        wins  = 0;
        loses = 0;
        ties  = 0;
        numberOfBeingBanker = 0;
    }

    // Accessor methods
    public int getWins() {
        return wins;
    }

    public int getLoses() {
        return loses;
    }

    public int getTies() {
        return ties;
    }

    public int getNumberOfBeingBanker() {
        return numberOfBeingBanker;
    }

    // Mutator methods
    public void setWins(int w) {
        if (w < 0) {
            throw new IllegalArgumentException("Number of wins must be larger or equal to 0!");
        }
        wins = w;
    }

    public void setLoses(int l) {
        if (l < 0) {
            throw new IllegalArgumentException("Number of loses must be larger or equal to 0!");
        }
        loses = l;
    }

    public void setTies(int t) {
        if (t < 0) {
            throw new IllegalArgumentException("Number of ties must be larger or equal to 0!");
        }
        ties = t;
    }

    public void setNumberOfBeingBanker(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Number of being a banker cannot be negative!");
        }
        numberOfBeingBanker = n;
    }

    // Other functions
    public void addOneWin() {
        wins += 1;
    }

    public void addOneLose() {
        loses += 1;
    }

    public void addOneTie() {
        ties += 1;
    }

    public void addOneNumberOfBeingBanker() {
        numberOfBeingBanker += 1;
    }
}
