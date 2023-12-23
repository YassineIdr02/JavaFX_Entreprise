module yassineidr.com.server {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires static lombok;

    requires org.kordamp.bootstrapfx.core;
    requires mysql.connector.j;

    opens yassineidr.com.server to javafx.fxml;
    exports yassineidr.com.server;
    exports yassineidr.com.server.JDBC;
    opens yassineidr.com.server.JDBC to javafx.fxml;
    exports yassineidr.com.server.JDBC.Classes;
    opens yassineidr.com.server.JDBC.Classes to javafx.fxml;
}