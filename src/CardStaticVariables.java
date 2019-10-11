/*
 * @author  : Dezhou Wang
 * @BU-id   : U46937632
 * @BU-class: CS591 P1
 * @Date    : Oct 6, 2019
 *
 * Card static variables
 * Each suit is represented by a unique int
 * Maintain suit symbol character
 */

public interface CardStaticVariables {
    // Static variables
    int CLUB              = 0;
    int DIAMOND           = 1;
    int HEART             = 2;
    int SPADE             = 3;
    int LITTLE_JOKER      = 4;
    int BIG_JOKER         = 5;
    String CLUB_SYMBOL    = "\u2663";
    String DIAMOND_SYMBOL = "\u2666";
    String HEART_SYMBOL   = "\u2665";
    String SPADE_SYMBOL   = "\u2660";

    // Static functions
    public static int[] get4Suits() {
        return new int[]{CLUB, DIAMOND, HEART, SPADE};
    }
}
