package model;

public enum templateColor {

    BLACK,
    BROWN,
    GREEN,
    PURPLE,
    RED,
    YELLOW;

    public static templateColor find (String name) {
        for (templateColor color : templateColor.values()) {
            if (color.toString().equalsIgnoreCase(name)) {
                return color;
            }
        }
        return templateColor.BLACK;
    }

    public int getDBIndex() {
        return ordinal() + 1;
    }
}
