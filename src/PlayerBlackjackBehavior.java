/*
 * @author  : Dezhou Wang
 * @BU-id   : U46937632
 * @BU-class: CS591 P1
 * @Date    : Oct 6, 2019
 *
 * PlayerBlackjackBehavior Interface
 * List all possible options that a player can choose
 */

public interface PlayerBlackjackBehavior {
    void hit1(Card c);
    void hit2(Card c);
    void stand1();
    void stand2();
    void split(Card c1, Card c2);
    void doubleUp1(Card c);
    void doubleUp2(Card c);
}
