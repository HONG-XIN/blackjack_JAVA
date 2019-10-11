/*
 * @author  : Dezhou Wang
 * @BU-id   : U46937632
 * @BU-class: CS591 P1
 * @Date    : Oct 6, 2019
 *
 * Group class for general card games
 * Contains multiple people
 */

import java.util.ArrayList;

public class Group<T> {
    // Instances
    private ArrayList<T> groupOfPeople;
    private int decksNum;

    // Constructors
    public Group() {
        groupOfPeople = new ArrayList<T>();
        decksNum = 1; // Default: decks of cards are set to be 1
    }

    // Accessor methods
    public ArrayList<T> getGroupOfPeople() {
        return groupOfPeople;
    }

    public int getGroupSize() {
        return groupOfPeople.size();
    }

    // Mutator methods
    public void setDecksNum(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Number of decks cannot be negative!");
        }
        decksNum = n;
    }

    // Other functions
    public void addOnePerson(T person) {
        for (T p: groupOfPeople) {
            if (p instanceof People) {
                if (((People)p).getName().getFullName().equals(((People)person).getName().getFullName())) {
                    throw new IllegalArgumentException("Players' names are not allowed to be identical!");
                }
            }
        }
        groupOfPeople.add(person);
    }

    public int kickOnePerson(String nick_name) {
        int index = -1;
        for (int i=0; i<groupOfPeople.size(); i++) {
            if (groupOfPeople.get(i) instanceof People) {
                if (((People)groupOfPeople.get(i)).getName().getNick_name().equals(nick_name)) {
                    index = i;
                    break;
                }
            }
        }
        if (index == -1) {
            throw new IllegalArgumentException("Unable to find a player with the given nick name!");
        } else {
            groupOfPeople.remove(index);
        }
        return index;
    }
}
