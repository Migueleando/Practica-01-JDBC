import java.sql.*;
import java.util.*;

import com.mysql.jdbc.DatabaseMetaData;

public class BBDD {

    private Connection con;
    private int IdPregunta = 0;
    private int IdRespuesta = 0;

    public void EstablecerConexion() throws SQLException {
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

    /**
     * Metodo que recibe un objeto pregunta y 4 respuestas (en nuestro juego
     * siempre hay 4 respuestas) y los a�ada a la base de datos
     * 
     * @param pregunta
     * @param respuesta1
     * @param respuesta2
     * @param respuesta3
     * @param respuesta4
     * @throws SQLException
     */
    public void AñadirPregunta(Question pregunta, Answer respuesta1,
	    Answer respuesta2, Answer respuesta3, Answer respuesta4)
	    throws SQLException {

	// Insertamos en las tablas
	// Inserci�n de Pregunta
	String queryQUESTION = " insert into QUESTION (ID, TEXT) values (?, ?)";
	PreparedStatement preparedStmtPregunta = con
		.prepareStatement(queryQUESTION);
	IdPregunta++;
	preparedStmtPregunta.setInt(1, IdPregunta);
	preparedStmtPregunta.setString(2, pregunta.getText());

	// Inserci�n de Respuesta 1
	String queryANSWER1 = " insert into ANSWER (ID, TEXT, IS_CORRECT) values (?, ?, ?)";
	PreparedStatement preparedStmtRespuesta1 = con
		.prepareStatement(queryANSWER1);
	IdRespuesta++;
	preparedStmtRespuesta1.setInt(1, IdRespuesta);
	preparedStmtRespuesta1.setString(2, respuesta1.getText());
	preparedStmtRespuesta1.setBoolean(3, respuesta1.isCorrect());

	// Inserci�n de Respuesta 2
	String queryANSWER2 = " insert into ANSWER (TEXT, IS_CORRECT) values (?, ?, ?)";
	PreparedStatement preparedStmtRespuesta2 = con
		.prepareStatement(queryANSWER2);
	IdRespuesta++;
	preparedStmtRespuesta2.setInt(1, IdRespuesta);
	preparedStmtRespuesta2.setString(2, respuesta1.getText());
	preparedStmtRespuesta2.setBoolean(3, respuesta1.isCorrect());

	// Inserci�n de Respuesta 3
	String queryANSWER3 = " insert into ANSWER (TEXT, IS_CORRECT) values (?, ?, ?)";
	PreparedStatement preparedStmtRespuesta3 = con
		.prepareStatement(queryANSWER3);
	IdRespuesta++;
	preparedStmtRespuesta3.setInt(1, IdRespuesta);
	preparedStmtRespuesta3.setString(2, respuesta1.getText());
	preparedStmtRespuesta3.setBoolean(3, respuesta1.isCorrect());

	// Inserci�n de Respuesta 4
	String queryANSWER4 = " insert into ANSWER (TEXT, IS_CORRECT) values (?, ?, ?)";
	PreparedStatement preparedStmtRespuesta4 = con
		.prepareStatement(queryANSWER4);
	IdRespuesta++;
	preparedStmtRespuesta4.setInt(1, IdRespuesta);
	preparedStmtRespuesta4.setString(2, respuesta1.getText());
	preparedStmtRespuesta4.setBoolean(3, respuesta1.isCorrect());

	// Ejecutamos los statements
	preparedStmtPregunta.execute();
	preparedStmtRespuesta1.execute();
	preparedStmtRespuesta2.execute();
	preparedStmtRespuesta3.execute();
	preparedStmtRespuesta4.execute();

	// Cerramos la conexion
	con.close();
    }

    // Consulta 2

    // Consulta 3

    // Consulta 4
    // 5 mejores resultados
    
    public void mejores5(){
	String query = "SELECT score, date FROM test ORDER BY score DESC";
	try {
	    Statement stm = con.createStatement();
	    ResultSet rs = stm.executeQuery(query);
	    while(rs.next()){
		System.out.println("Las cinco mejores puntuaciones son: "+rs);
	    }
	} catch (SQLException e) {
	    e.printStackTrace();
	}
    }
    

}
