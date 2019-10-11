/*
 * @author  : Dezhou Wang
 * @BU-id   : U46937632
 * @BU-class: CS591 P1
 * @Date    : Oct 6, 2019
 *
 * GroupTriantaEna class is used only for Trianta Ena.
 * It stores multiple players, among which there is a banker.
 */

import java.util.ArrayList;

public class GroupTriantaEna extends Group<PlayerTriantaEna>{
    // Instances
    private ArrayList<Integer> playersIndexes;
    private int bankerIndex;
    private Pile pile;

    // Constructors
    public GroupTriantaEna() {
        super();
        playersIndexes = new ArrayList<Integer>();
        bankerIndex = -1;
        pile = new Pile();
        pile.shuffle();
    }

    // Accessor methods
    public ArrayList<Integer> getPlayersIndexes() {
        return playersIndexes;
    }

    public int getBankerIndex() {
        return bankerIndex;
    }

    // Mutator methods
    public void setBankerIndex(int i) {
        bankerIndex = i;
    }

    // Other functions
    public void addOnePlayer(PlayerTriantaEna p) {
        if (getGroupSize() >= 10) {
            throw new IllegalStateException("Trianta Ena supports at most 10 players!");
        }
        addOnePerson(p);
        playersIndexes.add(getGroupSize() - 1);
    }

    public void kickOnePlayer(String nick_name) {
        int kickIndex= kickOnePerson(nick_name);
        playersIndexes.remove(new Integer(kickIndex));
        for (int i=0; i<playersIndexes.size(); i++) {
            if (kickIndex < playersIndexes.get(i)) {
                playersIndexes.set(i, playersIndexes.get(i)-1);
            }
        }
    }

    public void setMoney(int m) {
        if (m <= 0) {
            throw new IllegalStateException("Money must be positive!");
        }
        for (int i=0; i<playersIndexes.size(); i++) {
            PlayerTriantaEna player = getGroupOfPeople().get(playersIndexes.get(i));
            if (i == 0) {
                player.setRole(PlayerTriantaEnaRoles.BANKER);
                setBankerIndex(playersIndexes.get(i));
                player.setMoney(m * 3);
            } else {
                player.setMoney(m);
            }
        }
    }

    public Card distributeOneCard() {
        Card c = pile.draw();
        // The cards are reshuffled once all the cards have been used up.
        if (pile.getLeftNumber() == 0) {
            pile = new Pile();
            pile.shuffle();
        }
        return c;
    }

    public boolean isAbleToStart() {
        int num = 0;
        for (PlayerTriantaEna p: getGroupOfPeople()) {
            if (p.isAbleToPlay()) {
                num += 1;
                if (num >= 2) {
                    return true;
                }
            }
        }
        return false;
    }

    public void sortByMoney() {
        playersIndexes = new ArrayList<Integer>();
        for (int i=0; i<getGroupSize(); i++) {
            if (i == 0) {
                playersIndexes.add(0);
            } else {
                int insertIndex = -1;
                for (int j=0; j<playersIndexes.size(); j++) {
                    if (getMoneyValueByIndexOfGroup(i) > getMoneyValueByIndexOfGroup(playersIndexes.get(j))) {
                        insertIndex = j;
                        break;
                    }
                }
                if (insertIndex >= 0) {
                    playersIndexes.add(insertIndex, i);
                } else {
                    playersIndexes.add(i);
                }
            }
        }
    }

    public void distributeOneCardToEveryone() {
        for (Integer i: playersIndexes) {
            PlayerTriantaEna p = getGroupOfPeople().get(i);
            if (!p.getIfStand() && !p.getIfFold()) {
                p.hit(distributeOneCard());
            }
        }
    }

    public PlayerTriantaEna getBanker() {
        if (bankerIndex == -1) {
            return null;
        } else {
            return getGroupOfPeople().get(bankerIndex);
        }
    }

    public PlayerTriantaEna getPlayerByIndex(int i) {
        if (i < 0 || i >= getGroupSize()) {
            throw new IllegalArgumentException("Index is out of bound!");
        }
        return getGroupOfPeople().get(i);
    }

    public void reset() {
        for (PlayerTriantaEna player: getGroupOfPeople()) {
            if (player.isAbleToPlay()) {
                player.reset();
            } else {
                player.quit();
            }
        }
    }

    public boolean areAllPlayersStand() {
        for (int i: playersIndexes) {
            if (i == bankerIndex) {
                continue;
            } else {
                PlayerTriantaEna player = getPlayerByIndex(i);
                if (!player.getIfStand()) {
                    return false;
                }
            }
        }
        return true;
    }

    // Helper functions
    private int getMoneyValueByIndexOfGroup(int i) {
        if (i >= getGroupSize()) {
            throw new IllegalArgumentException("Index is out of bound!");
        }
        return getGroupOfPeople().get(i).getMoney().getValue();
    }
}
