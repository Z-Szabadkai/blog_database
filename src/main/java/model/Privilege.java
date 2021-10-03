package model;

public enum Privilege {

    LURKER,
    USER,
    MODERATOR,
    ADMIN;

    public static Privilege find (String permission) {
        for (Privilege enduser : Privilege.values()) {
            if (enduser.toString().equalsIgnoreCase(permission)) {
                return enduser;
            }
        }
        return Privilege.LURKER;
    }

    public int getDBIndex() {
        return ordinal() + 1;
    }


}
