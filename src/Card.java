/*
 * @author  : Dezhou Wang
 * @BU-id   : U46937632
 * @BU-class: CS591 P1
 * @Date    : Oct 6, 2019
 *
 * Card class represents each single playing card
 * Include 2 instances:
 * 1. suit (CLUB, DIAMOND, HEART, SPADE, or JOKERs)
 * 2. number (11=J, 12=Q, 13=K)
 */

public class Card implements CardStaticVariables, ConsoleColors{
    // Instances
    private int suit;
    private int number;

    // Constructors
    public Card(int suit, int number) {
        setSuit(suit);
        setNumber(number);
    }

    public Card(int suit) {
        this(suit, 1);
    }

    public Card() {
        this(CLUB, 1);
    }

    // Accessor methods
    public int getNumber() {
        return number;
    }

    // Mutator methods
    private void setSuit(int s) {
        if (s<0 || s>5) {
            throw new IllegalArgumentException("The color for a card is invalid!");
        }
        suit = s;
    }

    private void setNumber(int number) {
        if (number<1 || number>13) {
            throw new IllegalArgumentException("The number for a card is invalid!");
        }
        this.number = number;
    }

    // Override functions
    @Override
    public String toString() {
        String result = "[";
        if (suit == LITTLE_JOKER) {
            return "[LITTLE_JOKER]";
        } else if (suit == BIG_JOKER) {
            return "[BIG_JOKER]";
        } else if (suit == CLUB) {
            result += CLUB_SYMBOL;
        } else if (suit == DIAMOND) {
            // Check if the terminal supports printing color
            if (System.console() != null && System.getenv().get("TERM") != null) {
                result += RED + DIAMOND_SYMBOL + RESET;
            } else {
                result += DIAMOND_SYMBOL;
            }
        } else if (suit == HEART) {
            // Check if the terminal supports printing color
            if (System.console() != null && System.getenv().get("TERM") != null) {
                result += RED + HEART_SYMBOL + RESET;
            } else {
                result += HEART_SYMBOL;
            }
        } else{
            result += SPADE_SYMBOL;
        }
        if (number < 11) {
            result += " " + number + "]";
        } else if (number == 11) {
            result += " J]";
        } else if (number == 12) {
            result += " Q]";
        } else {
            result += " K]";
        }
        return result;
    }
}