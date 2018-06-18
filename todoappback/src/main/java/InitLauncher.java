import DBClient.DBClient;

import java.sql.SQLException;

public class InitLauncher {
    public static void main (String args[]) {
        try {
            DBClient.connect();
            DBClient.initDb();
            DBClient.disconnect();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
