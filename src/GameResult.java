/*
 * @author  : Dezhou Wang
 * @BU-id   : U46937632
 * @BU-class: CS591 P1
 * @Date    : Oct 6, 2019
 *
 * GameResult interface
 * Assign each result with a unique integer
 * Could be used for any game with the following result
 */

public interface GameResult {
    int NULL = 0;
    int WIN  = 1;
    int LOSE = 2;
    int TIE  = 3;
    int BUST = 4;
}
