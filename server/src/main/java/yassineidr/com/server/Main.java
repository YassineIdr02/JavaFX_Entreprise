package yassineidr.com.server;

import yassineidr.com.server.JDBC.DAOFactory;

public class Main {
    public static void main(String[] args) {
        DAOFactory.getConnection();
    }
}
