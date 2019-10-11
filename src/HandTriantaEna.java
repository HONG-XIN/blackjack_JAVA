/*
 * @author  : Dezhou Wang
 * @BU-id   : U46937632
 * @BU-class: CS591 P1
 * @Date    : Oct 6, 2019
 *
 * HandTriantaEna class represents a hand during playing a game called Trianta Ena
 */

import java.util.ArrayList;

public class HandTriantaEna extends Hand implements Comparable<HandTriantaEna>{
    // Constructors
    public HandTriantaEna() {
        super();
    }

    // Other functions
    public boolean isNaturalTriantaEna() {
        // It should satisfy the following requirements:
        // * number of cards is exactly 3
        // * total score is equal to 31
        return (getCardsNum() == 3 && getOptimalScore() == 31);
    }

    public int getOptimalScore() {
        ArrayList<Integer> scores = getScores();
        int optimal = scores.get(0);
        for (int i=1; i<scores.size(); i++) {
            if (scores.get(i) < 31 && scores.get(i) > optimal) {
                optimal = scores.get(i);
            } else if (scores.get(i) == 31) {
                optimal = 31;
                break;
            }
        }
        return optimal;
    }

    // Helper functions
    private ArrayList<Integer> getScores() {
        // J, Q, K are count as 10
        // A is count as 1 or 11
        // At most 1 A can be count as 1
        ArrayList<Integer> scores = new ArrayList<Integer>();
        scores.add(0);
        boolean ifACountAsOne = false;
        for (Card c: getCards()) {
            int num = scores.size();
            if (c.getNumber() == 1 && !ifACountAsOne) {
                for (int i=0; i<num; i++) {
                    scores.add(scores.get(i) + 1);
                    scores.set(i, scores.get(i) + 11);
                }
                ifACountAsOne = true;
            } else if (c.getNumber() == 1) {
                for (int i=0; i<num; i++) {
                    scores.set(i, scores.get(i) + 11);
                }
            } else if (c.getNumber() <= 10) {
                for (int i=0; i<num; i++) {
                    scores.set(i, scores.get(i) + c.getNumber());
                }
            } else {
                for (int i=0; i<num; i++) {
                    scores.set(i, scores.get(i) + 10);
                }
            }
        }
        return scores;
    }

    // Override functions
    @Override
    /*
     * If current hand is bigger than m, return 1.
     * If current is same with m, return 0.
     * If current hand is smaller than m, return -1.
     */
    public int compareTo(HandTriantaEna m) {
        if (m == null) {
            throw new IllegalArgumentException("Unable to compare to null object!");
        }
        int score1 = getOptimalScore();
        int score2 = m.getOptimalScore();
        if (isNaturalTriantaEna()) {
            if (m.isNaturalTriantaEna()) {
                return 0;
            } else {
                return 1;
            }
        } else if (m.isNaturalTriantaEna()) {
            return -1;
        } else if (score1 <= 31) {
            if (score2 <= 31) {
                if (score1 > score2) {
                    return 1;
                } else if (score1 == score2) {
                    return 0;
                } else{
                    return -1;
                }
            } else {
                return 1;
            }
        } else if (score2 <= 31) {
            return -1;
        }
        return 0;
    }

    @Override
    public String toString() {
        String result = super.toString();
        result += "\nOptimal Score: " + getOptimalScore();
        if (isNaturalTriantaEna()) {
            result += "\nNatural Trianta Ena!!!";
        }
        return result;
    }
}
