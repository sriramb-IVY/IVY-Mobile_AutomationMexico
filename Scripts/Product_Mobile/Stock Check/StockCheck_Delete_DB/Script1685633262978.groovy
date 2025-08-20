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

url = findTestData('DB Credentials/Config1').getValue('URL', 1)

dbname = findTestData('DB Credentials/Config1').getValue('dbname', 1)

username = findTestData('DB Credentials/Config1').getValue('username', 1)

password = findTestData('DB Credentials/Config1').getValue('password', 1)

RSH_Availability_Share = GlobalVariable.Share_Percent

RSH_Availability_Share = 100.0000

queryString01 = ('Select * from appdata_retailer_stock_header where RSH_Availability_Share = \'' + RSH_Availability_Share +'\' and RSH_User_Id= \''+ GlobalVariable.vanseller_user_id +'\'')

KeywordUtil.logInfo(queryString01)

String conn01 = (((((('jdbc:sqlserver://' + url) + ';databaseName=') + dbname) + ';user=') + username) + ';password=') + password

if ((connection != null) && !(connection.isClosed())) {
	connection.close()
}

connection01 = DriverManager.getConnection(conn01)

Statement stm01 = connection01.createStatement()

boolean rs0 = stm01.execute(queryString01)

queryString02 = ('delete from appdata_retailer_stock_header where RSH_Availability_Share = \'' + RSH_Availability_Share +'\' and RSH_User_Id= \''+ GlobalVariable.vanseller_user_id  +'\'')

KeywordUtil.logInfo(queryString02)

String conn02 = (((((('jdbc:sqlserver://' + url) + ';databaseName=') + dbname) + ';user=') + username) + ';password=') + password

if ((connection != null) && !(connection.isClosed())) {
	connection.close()
}

connection02 = DriverManager.getConnection(conn02)

Statement stm02 = connection02.createStatement()

boolean rs02 = stm02.execute(queryString02)

queryString03 = ('delete from appdata_retailer_stock_detail where RSD_Reference_No = \'' + GlobalVariable.label + '\'')

KeywordUtil.logInfo(queryString03)

String conn03 = (((((('jdbc:sqlserver://' + url) + ';databaseName=') + dbname) + ';user=') + username) + ';password=') + password

if ((connection != null) && !(connection.isClosed())) {
	connection.close()
}

connection03 = DriverManager.getConnection(conn03)

Statement stm03 = connection03.createStatement()

boolean rs03 = stm03.execute(queryString03)



