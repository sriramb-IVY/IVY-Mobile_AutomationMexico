import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import java.sql.Connection
import java.sql.DriverManager
import java.sql.ResultSet
import java.sql.Statement
import org.openqa.selenium.WebElement
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.keyword.excel.ExcelKeywords
import internal.GlobalVariable
import java.text.SimpleDateFormat;
import java.util.Date

Connection connection = null

String url = findTestData('DB Credentials/Config1').getValue('URL', 1)

String dbname = findTestData('DB Credentials/Config1').getValue('dbname', 1)

String username = findTestData('DB Credentials/Config1').getValue('username', 1)

String password = findTestData('DB Credentials/Config1').getValue('password', 1)

String queryString = (('select * from adm_retailer where ARTR_Code = \'' + retailer_Code)+ '\'')

println(queryString)

String conn = (((((('jdbc:sqlserver://' + url) + ';databaseName=') + dbname) + ';user=') + username) + ';password=') + password

if ((connection != null) && !(connection.isClosed())) {
	connection.close()
}
connection = DriverManager.getConnection(conn)

Statement stm1 = connection.createStatement()

ResultSet rs1 = stm1.executeQuery(queryString)

def recordSet1 = rs1

while (recordSet1.next()) {
	String retailer_id = recordSet1.getObject('ARTR_Id')

	KeywordUtil.logInfo( GlobalVariable.Retailer_Id = retailer_id)
}

String queryString1 = (('select * from AppData_Pending_Bills where pb_artr_id=\'' +  GlobalVariable.Retailer_Id) +'\'  order by 1 desc ')

println(queryString1)

String conn1 = (((((('jdbc:sqlserver://' + url) + ';databaseName=') + dbname) + ';user=') + username) + ';password=') + password

if ((connection != null) && !(connection.isClosed())) {
	connection.close()
}
connection = DriverManager.getConnection(conn1)

Statement stm = connection.createStatement()

ResultSet rs = stm.executeQuery(queryString1)

def recordSet = rs

while (recordSet.next()) {
	String invoice = recordSet.getObject('PB_SIH_No')

	KeywordUtil.logInfo(invoice)

	KeywordUtil.logInfo(GlobalVariable.Pending_bills_invoice = invoice)

	String pending_id = recordSet.getObject('PB_Id')

	KeywordUtil.logInfo(GlobalVariable.Pending_bill_id = pending_id)

	return(invoice)
}


