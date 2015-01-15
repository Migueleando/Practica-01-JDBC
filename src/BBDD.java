import java.sql.*;
import java.util.*;

import com.mysql.jdbc.DatabaseMetaData;

public class BBDD{

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
     * 
     * @param Categoria
     * @throws SQLException
     */
    public void ConsultarPreguntasYRespuestas() throws SQLException {
	int i = 0;
	try {
	    // Consultamos las la tabla Answer
	    Statement stmt1 = con.createStatement();
	    ResultSet rs1 = stmt1.executeQuery("select * from QUESTION");
	    // Para cada resultado devuelto me guardo el id_question y la
	    // pregunta
	    while (rs1.next()) {
		int NumeroDePregunta = rs1.getInt("id_question");
		String TextoPregunta = rs1.getString("text");
		// Y saco todas las respuestas pertenecientes a dicha pregunta y
		// que sean correctas (sera solo 1)
		Statement stmt2 = con.createStatement();
		ResultSet rs2 = stmt2
			.executeQuery("select * from ANSWER where id_question = "
				+ NumeroDePregunta + " and is_correct = true");
		String TextoRespuesta = rs2.getString("text");
		// Y muestro los resultados: La pregunta y la respuesta correcta
		System.out.println("Pregunta nº(" + i + ") -> " + TextoPregunta
			+ "?");
		i++;
		System.out.println("- " + TextoRespuesta + " <-- Correcta"
			+ "\n");
	    }
	} finally {
	    con.close();
	}
    }

    /**
     * Crear un método que reciba un usuario y un test, ambos deben de existir
     * en la base de datos, empezará a preguntar al usuario preguntas del test,
     * al finalizar le mostrará la puntuación y guardará el resultado
     * 
     * @param user
     * @param test
     * @throws SQLException
     */
    public void test(User user, Test test) throws SQLException {

	String queryUser = "Select * from USER where ID=?";
	PreparedStatement preparedStmtUser = con.prepareStatement(queryUser);
	preparedStmtUser.setInt(1, user.getId());
	if (preparedStmtUser.execute()) {
	    String queryUserTest = "Select * from TEST where ID=?";
	    PreparedStatement preparedStmtUserTest = con
		    .prepareStatement(queryUserTest);
	    preparedStmtUserTest.setInt(1, test.getId());
	    if (preparedStmtUserTest.execute()) {
		int x = 0;
		int[] id_question = null;

		String queryTest = "Select ID_QUESTION from TEST_QUESTION where ID_TEST = ?";
		PreparedStatement preparedStmtTest = con
			.prepareStatement(queryTest);
		preparedStmtTest.setInt(1, test.getId());

		ResultSet rs = preparedStmtTest.executeQuery();
		while (rs.next()) {
		    id_question[x] = rs.getInt("ID_QUESTION");
		    x++;
		}
		for (int i = 0; i < id_question.length; i++) {

		    String queryQuestion = "Select TEXT from QUESTION where ID=?";
		    PreparedStatement preparedStmtQuestion = con
			    .prepareStatement(queryQuestion);
		    preparedStmtQuestion.setInt(1, id_question[i]);

		    ResultSet rs1 = preparedStmtQuestion.executeQuery();
		    while (rs1.next()) {
			System.out
				.println("Pregunta: " + rs1.getString("TEXT"));
		    }

		    String queryAnswer = "Select TEXT from ANSWER where ID_QUESTION=?";
		    PreparedStatement preparedStmtAnswer = con
			    .prepareStatement(queryAnswer);
		    preparedStmtAnswer.setInt(1, id_question[i]);
		    ResultSet rs2 = preparedStmtAnswer.executeQuery();
		    String[] resp = null;
		    int num = 0;
		    while (rs2.next()) {
			resp[num] = rs2.getString("TEXT");
			num++;
		    }
		    for (int j = 0; j < resp.length; j++) {
			System.out.println((j + 1) + ") " + resp[j]);
		    }
		    System.out
			    .println("Escribe el número de la casilla que crees correcta.");
		    Scanner respuestaUser = new Scanner(System.in);
		    int respUser = respuestaUser.nextInt();

		    String queryRespUser = "Select IS_CORRECT from ANSWER where TEXT=? and ID_QUESTION=?";
		    PreparedStatement preparedStmtRespUser = con
			    .prepareStatement(queryRespUser);
		    preparedStmtRespUser.setString(1, resp[respUser - 1]);
		    preparedStmtAnswer.setInt(2, id_question[i]);
		    ResultSet rsRespUser = preparedStmtRespUser.executeQuery();
		    while (rsRespUser.next()) {
			boolean comprobacion = rs.getBoolean("IS_CORRECT");
			if (comprobacion) {
			    System.out.println("¡Correcto!");
			    test.setScore(test.getScore() + 20);
			} else
			    System.out.println("¡Fallaste!");
		    }
		}

		System.out.println("Has realizado una puntuacion de: "
			+ test.getScore());
	    } else
		System.out.println("El test no existe");

	} else
	    System.out.println("El usuario no existe.");

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
