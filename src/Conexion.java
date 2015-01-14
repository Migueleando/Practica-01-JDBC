import java.sql.*;
import java.util.*;

import com.mysql.jdbc.DatabaseMetaData;

public class Conexion {

    public static void main(String[] args) throws SQLException {
	String url = "jdbc:mysql://localhost:3306/QUIZIT";
	Properties prop = new Properties();
	prop.setProperty("user", "QUIZIT");
	prop.setProperty("password", "QUIZIT");
	Driver d = new com.mysql.jdbc.Driver();
	Connection con = d.connect(url, prop);
	if (con == null) {
	    System.out.println("connection failed");
	    return;
	}

	DatabaseMetaData dm = (DatabaseMetaData) con.getMetaData();
	String dbversion = dm.getDatabaseProductVersion();
	String dbname = dm.getDatabaseProductName();
	System.out.println("name: " + dbname);
	System.out.println("version: " + dbversion);

    }

}
