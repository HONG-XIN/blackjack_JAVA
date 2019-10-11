/*
 * @author  : Dezhou Wang
 * @BU-id   : U46937632
 * @BU-class: CS591 P1
 * @Date    : Oct 6, 2019
 *
 * People class stores people's name and money information
 */

public class People {
    // Instances
    private Name name;
    private Money money;
    private int games;

    // Constructors
    public People(String name, int money) {
        this.name = new Name(name);
        this.money = new Money(money);
        games = 0;
    }

    // Accessor methods
    public Name getName() {
        return name;
    }

    public Money getMoney() {
        return money;
    }

    public int getGames() {
        return games;
    }

    // Mutator methods
    public void setName(String name) {
        this.name.setNick_name(name);
    }

    public void setMoney(int m) {
        this.money.setValue(m);
    }

    // Other functions
    public void addMoney(int m) {
        money.add(m);
    }

    public void deductMoney(int m) {
        money.deduct(m);
    }

    public void addOneGame() {
        games += 1;
    }

    // Override functions
    @Override
    public String toString() {
        return name + "; " + money + "; games: " + games;
    }
}
