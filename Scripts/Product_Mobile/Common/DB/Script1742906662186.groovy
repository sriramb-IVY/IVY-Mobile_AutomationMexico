import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys
import java.sql.Connection
import java.sql.DriverManager
import java.sql.ResultSet
import java.sql.SQLException
import java.sql.Statement
//import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.util.KeywordUtil
import org.junit.Assert as Assert
import internal.GlobalVariable

List<String> valuesList = new ArrayList<>()

Connection connection = null
// Declare the map inside the method
def columnValuesMap = [:]

String conn = GlobalVariable.dbdomain + GlobalVariable.DB_URL + ";databaseName=" + GlobalVariable.DB_Name+ ";user=" + GlobalVariable.DB_Username + ";password=" + GlobalVariable.DB_Password

try {
	connection = DriverManager.getConnection(conn)

	Statement stm = connection.createStatement()
	ResultSet rs = stm.executeQuery(Query)
	
	KeywordUtil.logInfo('execute query : '+Query)

	// Process the result set
	if (rs.next()) {
		// Dynamically process the columns based on the names in the list
		columnNames.each { columnName ->
			def columnValue = rs.getObject(columnName) // Extract the value of the current column dynamically
			KeywordUtil.logInfo("${columnName}: " + columnValue)

			// Store the result in the columnValuesMap
			columnValuesMap[columnName] = columnValue

			valuesList.add(columnValue);

			GlobalVariable.data=valuesList
			
		}
	}

	else {
		'Data - empty in the DB, script should fail'
		Assert.fail()
	}

} catch (SQLException e) {
	KeywordUtil.logInfo("Error executing SQL query: " + e.message)

} finally {
	// Close the connection
	try {
		if (connection != null && !connection.isClosed()) {
			connection.close()
		}
	} catch (SQLException e) {
		KeywordUtil.logInfo("Error closing database connection: " + e.message)
	}
}
// Log the values in the columnValuesMap
columnValuesMap.each { key, value ->
	KeywordUtil.logInfo("Column: ${key}, Value: ${value}")
}
// Return the map for further use
return columnValuesMap

