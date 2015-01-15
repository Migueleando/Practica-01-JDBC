import java.sql.*;
import java.util.*;

import com.mysql.jdbc.DatabaseMetaData;

public class BBDD {

    private Connection con;
    private int IdPregunta = 0;
    private int IdRespuesta = 0;
    private int idTest = 0;

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
     * siempre hay 4 respuestas) y los añada a la base de datos
     * 
     * @param pregunta
     * @param respuesta1
     * @param respuesta2
     * @param respuesta3
     * @param respuesta4
     * @throws SQLException
     */
    public void AddQuestion(Question pregunta, Answer respuesta1,
	    Answer respuesta2, Answer respuesta3, Answer respuesta4)
	    throws SQLException {

	// Insertamos en las tablas
	// Inserción de Pregunta
	String queryQUESTION = " insert into QUESTION (ID, TEXT) values (?, ?)";
	PreparedStatement preparedStmtPregunta = con
		.prepareStatement(queryQUESTION);
	IdPregunta++;
	preparedStmtPregunta.setInt(1, IdPregunta);
	preparedStmtPregunta.setString(2, pregunta.getText());

	// Inserción de Respuesta 1
	String queryANSWER1 = " insert into ANSWER (ID, TEXT, IS_CORRECT) values (?, ?, ?)";
	PreparedStatement preparedStmtRespuesta1 = con
		.prepareStatement(queryANSWER1);
	IdRespuesta++;
	preparedStmtRespuesta1.setInt(1, IdRespuesta);
	preparedStmtRespuesta1.setString(2, respuesta1.getText());
	preparedStmtRespuesta1.setBoolean(3, respuesta1.isCorrect());

	// Inserción de Respuesta 2
	String queryANSWER2 = " insert into ANSWER (TEXT, IS_CORRECT) values (?, ?, ?)";
	PreparedStatement preparedStmtRespuesta2 = con
		.prepareStatement(queryANSWER2);
	IdRespuesta++;
	preparedStmtRespuesta2.setInt(1, IdRespuesta);
	preparedStmtRespuesta2.setString(2, respuesta1.getText());
	preparedStmtRespuesta2.setBoolean(3, respuesta1.isCorrect());

	// Inserción de Respuesta 3
	String queryANSWER3 = " insert into ANSWER (TEXT, IS_CORRECT) values (?, ?, ?)";
	PreparedStatement preparedStmtRespuesta3 = con
		.prepareStatement(queryANSWER3);
	IdRespuesta++;
	preparedStmtRespuesta3.setInt(1, IdRespuesta);
	preparedStmtRespuesta3.setString(2, respuesta1.getText());
	preparedStmtRespuesta3.setBoolean(3, respuesta1.isCorrect());

	// Inserción de Respuesta 4
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
	
	/**
	 * Metodo que imprime todas las preguntas y la respuesta correcta
	 * @param Categoria
	 * @throws SQLException 
	 */
	public void ConsultarPreguntasYRespuestas () throws SQLException {
		int i = 0;
		try {
			// Consultamos las la tabla Answer		
			Statement stmt1 = con.createStatement();
			ResultSet rs1 = stmt1.executeQuery("select * from QUESTION");
			// Para cada resultado devuelto me guardo el id_question y la pregunta
			while (rs1.next()) {			
				int NumeroDePregunta = rs1.getInt("id_question");
				String TextoPregunta = rs1.getString("text");
				// Y saco todas las respuestas pertenecientes a dicha pregunta y que sean correctas (sera solo 1)
				Statement stmt2 = con.createStatement();
				ResultSet rs2 = stmt2.executeQuery("select * from ANSWER where id_question = " + NumeroDePregunta + " and is_correct = true");				
				String TextoRespuesta = rs2.getString("text");
				// Y muestro los resultados: La pregunta y la respuesta correcta
				System.out.println("Pregunta n�("+i+") -> " + TextoPregunta + "?");
				i++;
				System.out.println("- " + TextoRespuesta + " <-- Correcta" +"\n");				  
				}
		} finally {
			con.close();
		}
	}	
    
    public void mejores5() {
	String query = "SELECT score, date FROM test ORDER BY score DESC";
	try {
	    Statement stm = con.createStatement();
	    ResultSet rs = stm.executeQuery(query);
	    while (rs.next()) {
		System.out.println("Las cinco mejores puntuaciones son: "
			+ rs.getInt("SCORE"));
	    }
	} catch (SQLException e) {
	    e.printStackTrace();
	}
    }
}
