/*
 * @author  : Dezhou Wang
 * @BU-id   : U46937632
 * @BU-class: CS591 P1
 * @Date    : Oct 6, 2019
 *
 * Money class stores the money.
 * Default: concurrency is set to be US-dollar.
 */

public class Money {
    // Instances
    private int value;
    private String currency;

    // Constructors
    public Money(int m) {
        value = m;
        currency = "US-dollar";
    }

    public Money() {
        this(0);
    }

    // Accessor methods
    public int getValue() {
        return value;
    }

    // Mutator methods
    public void setValue(int v) {
        if (v < 0) {
            throw new IllegalArgumentException("Money value cannot be negative!");
        }
        value = v;
    }

    public void setCurrency(String cur) {
        currency = cur;
    }

    // Other functions
    public void add(int i) {
        value += i;
    }

    public void deduct(int i) {
        if (i > value) {
            throw new IllegalArgumentException("Don't have enough money to deduct!");
        }
        value -= i;
    }

    // Override functions
    @Override
    public String toString() {
        String result = "";
        if (currency.equals("US-dollar")) {
            result += "$";
        }
        result += value;
        return result;
    }
}
