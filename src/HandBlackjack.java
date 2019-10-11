/*
 * @author  : Dezhou Wang
 * @BU-id   : U46937632
 * @BU-class: CS591 P1
 * @Date    : Oct 6, 2019
 *
 * HandBlackjack class to be use for Blackjack only
 * Can calculate the optimal score based on Blackjack's rule
 * Able to compare with other HandBlackjack object
 */

import java.util.ArrayList;

public class HandBlackjack extends Hand implements Comparable<HandBlackjack> {
    // Constructors
    public HandBlackjack() {
        super();
    }

    // Other functions
    public boolean isBlackjack() {
        return (getCardsNum()==2 && getOptimalScore()==21);
    }

    public int getOptimalScore() {
        ArrayList<Integer> scores = getScores();
        int result = scores.get(0);
        for (int i=1; i<scores.size(); i++) {
            if(scores.get(i)<21 && scores.get(i)>result) {
                result = scores.get(i);
            } else if(scores.get(i) == 21) {
                result = 21;
                break;
            }
        }
        return result;
    }

    public Card split() {
        if (!isSplittable()) {
            throw new IllegalStateException("Not able to split cards in hand!");
        } else {
            return getCards().remove(1);
        }
    }

    public boolean isSplittable() {
        return (getCardsNum()==2 && getCards().get(0).getNumber()==getCards().get(1).getNumber());
    }

    // Helper functions
    private ArrayList<Integer> getScores() {
        ArrayList<Integer> scores = new ArrayList<Integer>();
        scores.add(0);
        for (Card c: getCards()) {
            int num = scores.size();
            if (c.getNumber() == 1) {
                for (int i=0; i<num; i++) {
                    scores.add(scores.get(i)+11);
                }
                for (int i=0; i<num; i++) {
                    scores.set(i, scores.get(i)+1);
                }
            } else if (c.getNumber() < 10) {
                for (int i=0; i<num; i++) {
                    scores.set(i, scores.get(i)+c.getNumber());
                }
            } else {
                for (int i=0; i<num; i++) {
                    scores.set(i, scores.get(i)+10);
                }
            }
        }
        return scores;
    }

    // Override functions
    @Override
    /*
     * If current hand is bigger than m, return 1.
     * If current hand is tie to m, return 0.
     * If current hand is smaller than m, return -1.
     */
    public int compareTo(HandBlackjack m) {
        if(m == null) {
            throw new NullPointerException("Cannot compare to null Hand!");
        }
        int score1 = getOptimalScore();
        int score2 = m.getOptimalScore();
        if (isBlackjack()) {
            if (!m.isBlackjack()) {
                return 1;
            } else {
                return 0;
            }
        } else if (m.isBlackjack()) {
            return -1;
        } else if (score1 <= 21) {
            if (score2 <= 21) {
                if (score1 > score2) {
                    return 1;
                } else if (score1 == score2) {
                    return 0;
                } else {
                    return -1;
                }
            } else {
                return 1;
            }
        } else if (score2 <= 21) {
            return -1;
        }
        return 0;
    }

    @Override
    public String toString() {
        String result = super.toString();
        result += "\nOptimal score: " + getOptimalScore();
        if (isBlackjack()) {
            result += "\nBLACKJACK!!!";
        }
        return result;
    }
}
