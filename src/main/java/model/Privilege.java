package model;

public enum Privilege {

    USER,
    MODERATOR,
    ADMIN;

    public static Privilege find (String permission) {
        for (Privilege enduser : Privilege.values()) {
            if (enduser.toString().equalsIgnoreCase(permission)) {
                return enduser;
            }
        }
        return Privilege.USER;
    }

    public int getDBIndex() {
        return ordinal() + 1;
    }


}
