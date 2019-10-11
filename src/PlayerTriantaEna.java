/*
 * @author  : Dezhou Wang
 * @BU-id   : U46937632
 * @BU-class: CS591 P1
 * @Date    : Oct 6, 2019
 *
 * PlayerTriantaEna class is used only for Trianta Ena
 * Could be extended for human or AI
 */

public class PlayerTriantaEna extends Player implements PlayerTriantaEnaRoles, GameResult {
    // Instances
    private HandTriantaEna hand;
    private int role;
    private boolean ifStand;
    private int bet;
    private int result;
    private int debt; // Allow for use debt to pay LOSE
    private boolean ifFold;
    private boolean ifJoin;

    // Constructors
    public PlayerTriantaEna(String name) {
        super(name, 0);
        hand = new HandTriantaEna();
        role = PLAYER;
        ifStand = false;
        bet = 0;
        result = NULL;
        debt = 0;
        ifFold = false;
        ifJoin = true;
    }

    // Accessor methods
    public HandTriantaEna getHand() {
        return hand;
    }

    public int getRole() {
        return role;
    }

    public boolean getIfStand() {
        return ifStand;
    }

    public int getBet() {
        return bet;
    }

    public int getResult() {
        return result;
    }

    public boolean getIfFold() {
        return ifFold;
    }

    public boolean getIfJoin() {
        return ifJoin;
    }

    // Mutator methods
    public void setMoney(int m) {
        if (m <= 0) {
            throw new IllegalArgumentException("Money must be positive!");
        }
        getMoney().setValue(m);
    }

    public void setRole(int r) {
        if (r!=PLAYER && r!=BANKER) {
            throw new IllegalArgumentException("Role not configured!");
        }
        role = r;
    }

    public void setBet(int b) {
        if (b > getMoney().getValue()) {
            throw new IllegalArgumentException("Bet cannot be larger than money!");
        }
        bet = b;
    }

    public void setResult(int r) {
        // Don't have tie, because tie is count as lose
        if (r == NULL) {
            result = NULL;
        } else if (r == WIN) {
            addOneWin();
            result = WIN;
            addMoney(bet);
        } else if (r == LOSE) {
            addOneLose();
            result = LOSE;
            deductMoney(bet);
        } else if (r == BUST) {
            result = BUST;
            if (role == PLAYER) {
                addOneLose();
                deductMoney(bet);
            }
        } else {
            throw new IllegalArgumentException("Result option not configured!");
        }
    }

    // Primary functions
    public void bet(int b) {
        if (b <= 0) {
            throw new IllegalArgumentException("Bet must be a positive integer!");
        }
        setBet(b);
    }

    public void fold() {
        ifStand = true;
        ifFold = true;
    }

    public void hit(Card c) {
        if (ifStand) {
            throw new IllegalStateException("Player has already chosen to stand!");
        }
        hand.add(c);
        // If optimal score reaches 31, automatically stop
        int optimalScore = hand.getOptimalScore();
        if (optimalScore >= 31) {
            stand();
            if (optimalScore > 31) {
                setResult(BUST);
            }
        }
    }

    public void stand() {
        ifStand = true;
    }

    // Other functions
    public void addMoney(int m) {
        if (m <= debt) {
            debt -= m;
        } else {
            super.addMoney(m-debt);
            debt = 0;
        }
    }

    public void deductMoney(int m) {
        if (getMoney().getValue() >= m) {
            super.deductMoney(m);
        } else {
            debt = m - getMoney().getValue();
            getMoney().setValue(0);
        }
    }

    public void checkHand(HandTriantaEna h) {
        if (result != NULL) {
            return;
        } else if (ifFold == true) {
            return;
        }
        if (hand.compareTo(h) > 0) {
            setResult(WIN);
        } else {
            setResult(LOSE);
        }
    }

    public int getOptimalScore() {
        return hand.getOptimalScore();
    }

    public boolean isAbleToPlay() {
        return getMoney().getValue() > 0;
    }

    public String getDetailsForList() {
        String s = "";
        if (role == PLAYER) {
            s += "Player-";
        } else {
            s += "Banker-";
        }
        return s + getName() + "; " + getMoney();
    }

    public String getResultString() {
        if (result == NULL) {
            return "NULL";
        } else if (result == WIN) {
            return "WIN";
        } else if (result == LOSE) {
            return "LOSE";
        } else if (result == BUST) {
            return "BUST";
        } else {
            return null;
        }
    }

    public void reset() {
        hand = new HandTriantaEna();
        ifStand = false;
        bet = 0;
        result = NULL;
        ifFold = false;
        ifJoin = true;
    }

    public void quit() {
        hand = new HandTriantaEna();
        ifStand = true;
        bet = 0;
        result = NULL;
        ifFold = false;
        ifJoin = false;
    }

    // Override functions
    @Override
    public String toString() {
        String s = "";
        if (role == PLAYER) {
            s += "PLAYER-";
        } else {
            s += "BANKER-";
        }
        s += getName() + "; " + getMoney() + "; games: " + getGames();
        if (role == PLAYER) {
            s += "; bet: $" + bet;
        }
        for (Card c: hand.getCards()) {
            s += "\n" + c;
        }
        s += "\nOptimal Score: " + hand.getOptimalScore();
        if (result != NULL) {
            s += "\n" + getResultString() + "!!!";
        }
        if (hand.isNaturalTriantaEna()) {
            s += "\nNatural Trianta Ena!!!";
        }
        if (ifFold) {
            s += "\nFOLD!!!";
        }
        return s;
    }
}
