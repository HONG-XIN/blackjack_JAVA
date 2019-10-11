/*
 * @author  : Dezhou Wang
 * @BU-id   : U46937632
 * @BU-class: CS591 P1
 * @Date    : Oct 6, 2019
 *
 * General class represents a player
 * Could be used for any game
 */

public class Player extends People{
    // Instances
    private GameResultRecord gameResultRecord;

    // Constructors
    public Player(String name, int money) {
        super(name, money);
        gameResultRecord = new GameResultRecord();
    }

    // Accessor methods
    public int getWins() {
        return gameResultRecord.getWins();
    }

    public int getLoses() {
        return gameResultRecord.getLoses();
    }

    public int getTies() {
        return gameResultRecord.getTies();
    }

    public int getNumberOfBeingBanker() {
        return gameResultRecord.getNumberOfBeingBanker();
    }

    // Other methods
    public void addOneWin() {
        gameResultRecord.addOneWin();
    }

    public void addOneLose() {
        gameResultRecord.addOneLose();
    }

    public void addOneTie() {
        gameResultRecord.addOneTie();
    }

    public void addOneNumberOfBeingBanker() {
        gameResultRecord.addOneNumberOfBeingBanker();
    }
}
