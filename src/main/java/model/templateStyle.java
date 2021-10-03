package model;

public enum templateStyle {

    BOOK,
    FOOD,
    MARKETING,
    MUSIC,
    NEWS,
    PERSONAL,
    TRAVEL;

    public static templateStyle find (String name) {
        for (templateStyle style : templateStyle.values()) {
            if (style.toString().equalsIgnoreCase(name)) {
                return style;
            }
        }
        return templateStyle.PERSONAL;
    }


}
