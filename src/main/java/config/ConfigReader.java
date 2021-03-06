package config;

public class ConfigReader {

    /* These are global variables for ease of access.
     */

    private static final String sqlSelect = "SELECT ";
    private static final String sqlFrom = " FROM ";
    private static final String sqlInsert = "INSERT INTO ";
    private static final String sqlWhere = " WHERE ";
    private static final String sqlValue = " VALUES ";
    private static final String sqlUpdate = "UPDATE ";
    private static final String sqlSet = " SET ";

    /*
    This method is for the "SELECT" type commands for the SQL database.
     */

    public static String selectSQLDB (String column, String db, String filter, String search) {
        StringBuilder result = new StringBuilder(sqlSelect);
        if (column != null) {
            result.append(column).append(sqlFrom).append(db);
        } else {
            result.append("*" + sqlFrom).append(db);
        }
        if (filter != null) {
            result.append(sqlWhere).append(search).append("=");
        }
        return result.toString();
    }

    /*
    This method is for the "INTO" type commands for the SQL database. Of course the values change depending on which table the user gives.
     */

    public static String intoSQLDB (String db) {
        String column = "";
        String value = "";
        StringBuilder result = new StringBuilder(sqlInsert);
        switch (db) {
            case "enduser":
                column = "(name, privilege, email, password, avatar, registration_date)";
                value = "(?, ?, ?, ?, ?, ?)";
                break;
            case "blog":
                column = "(title, background, templatestyle, templatecolor)";
                value = "(?, ?, ?, ?)";
                break;
            case "content":
                column = "(title, post, tags, published_date, can_comment_under)";
                value = "(?, ?, ?, ?, ?)";
                break;
            case "comment":
                column = "(comment_id, comment_post, comment_date)";
                value = "(?, ?, ?)";
                break;
        }
        result.append(column).append(sqlValue).append(value);
        return result.toString();
    }

    /*
    This method is only for giving back an "UPDATE" SQL query in a way that is not hard coded.
     */

    public static String updateSQLDB (String db, String column, String value, String filter) {
        return sqlUpdate +
                db +
                sqlSet + column + "=" +
                "'" + value + "'" +
                sqlWhere +
                filter + "=";
    }
}
