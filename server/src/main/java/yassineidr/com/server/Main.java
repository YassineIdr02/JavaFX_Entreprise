package yassineidr.com.server;

import yassineidr.com.server.JDBC.DAOFactory;
import yassineidr.com.server.JDBC.DAOs.DAOEmployer;

public class Main {
    public static void main(String[] args) {
        DAOFactory.getConnection();
        DAOEmployer em = new DAOEmployer();
        em.All();
    }
}
