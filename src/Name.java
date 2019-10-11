/*
 * @author  : Dezhou Wang
 * @BU-id   : U46937632
 * @BU-class: CS591 P1
 * @Date    : Oct 6, 2019
 *
 * Name class stores name attributes of a people.
 */

public class Name {
    // Instances
    private String first_name;
    private String middle_name;
    private String last_name;
    private String nick_name;

    // Constructors
    public Name(String first_name, String middle_name, String last_name, String nick_name) {
        this.first_name  = first_name;
        this.middle_name = middle_name;
        this.last_name   = last_name;
        this.nick_name   = nick_name;
    }

    public Name(String nick_name) {
        this(null, null,null, nick_name);
    }

    // Accessor methods
    public String getNick_name() {
        return nick_name;
    }

    // Mutator methods
    public void setNick_name(String n) {
        nick_name = n;
    }

    // Other functions
    public String getFullName() {
        String result = "";
        if (first_name != null) {
            result += first_name;
        }
        if (middle_name != null) {
            if (!result.equals("")) {
                result += " ";
            }
            result += middle_name;
        }
        if (last_name != null) {
            if (!result.equals("")) {
                result += " ";
            }
            result += last_name;
        }
        if (nick_name != null) {
            if (!result.equals("")) {
                result += " ";
            }
            result += nick_name;
        }
        return result;
    }

    // Override functions
    @Override
    public String toString() {
        return getFullName();
    }
}
