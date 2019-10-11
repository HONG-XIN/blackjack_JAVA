/*
 * @author  : Dezhou Wang
 * @BU-id   : U46937632
 * @BU-class: CS591 P1
 * @Date    : Oct 6, 2019
 *
 * GroupBlackjack class only use for Blackjack
 * Contains at most 1 dealer and multiple players
 */

import java.util.ArrayList;

public class GroupBlackjack extends Group<PlayerBlackjack> {
    // Instances
    private DealerBlackjack dealer; // In addition to the group of players, Blackjack has a dealer

    // Constructors
    public GroupBlackjack() {
        super();
        dealer = null;
    }

    // Accessor methods
    public DealerBlackjack getDealer() {
        return dealer;
    }

    public ArrayList<PlayerBlackjack> getPlayers() {
        return getGroupOfPeople();
    }

    // Mutator methods
    public void setDealer(DealerBlackjack d) {
        if (dealer != null) {
            throw new IllegalStateException("This group already has a dealer!");
        }
        dealer = d;
    }

    // Other functions
    public void removeDealer() {
        if (dealer == null) {
            throw new IllegalStateException("This group does not have a dealer!");
        }
        dealer = null;
    }

    public void addOnePlayer(PlayerBlackjack p) {
        if (getGroupSize() >= 8) {
            throw new IllegalStateException("This group allows at most 8 players!");
        }
        addOnePerson(p);
    }

    public void kickOnePlayer(String nick_name) {
        kickOnePerson(nick_name);
    }

    public Card getDealerDistributeOneCard() {
        return dealer.distribute();
    }

    public void reset() {
        if (dealer != null) {
            dealer.reset();
        }
        for (PlayerBlackjack p: getGroupOfPeople()) {
            p.reset();
        }
    }

    public boolean isAbleToStart() {
        // It should satisfy:
        // * has one dealer
        // * has at least one player
        return dealer != null && getGroupSize() >= 1;
    }

    public boolean arePlayersAllStand() {
        for (PlayerBlackjack p: getGroupOfPeople()) {
            if (!p.getIfStand()) {
                return false;
            }
        }
        return true;
    }
}
