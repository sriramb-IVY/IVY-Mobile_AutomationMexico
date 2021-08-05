import internal.GlobalVariable
import java.sql.ResultSet
import org.postgresql.Driver
import java.sql.DriverManager
import java.sql.SQLException
import java.sql.Statement
import org.postgresql.jdbc.PgConnection
import org.postgresql.jdbc.PgResultSet
import org.postgresql.jdbc.PgStatement


PgConnection connection = null
String url="172.21.4.242"
String dbname="ivyretailassist_bnlindia_qa"
String port="5432"
String username="ivyretailassist_bnlindia_qa_readuser"
String password="Ivyretasstbnlnpfreaduser0820*"

	//Load driver class for your specific database type

	String conn = "jdbc:postgresql://" + url + ":" + port + "/" + dbname

	if (connection != null && !connection.isClosed()) {

		connection.close()

	}

	connection = DriverManager.getConnection(conn, username, password)
	return connection

	


