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

String pattern = 'yyyy-MM-dd'

SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern)

String currentdate = simpleDateFormat.format(new Date())

println(currentdate)

DB_Currentdate = (currentdate + ' 00:00:00.000')

Connection connection = null

url = findTestData('DB Credentials/Config1').getValue('URL', 1)

dbname = findTestData('DB Credentials/Config1').getValue('dbname', 1)

username = findTestData('DB Credentials/Config1').getValue('username', 1)

password = findTestData('DB Credentials/Config1').getValue('password', 1)

queryString = ('Select top 1 * from appdata_retailer_stock_header where RSH_Stock_Date = \'' + DB_Currentdate +'\' and RSH_User_Id= \''+ GlobalVariable.vanseller_user_id +'\' order by 1 desc')

KeywordUtil.logInfo(queryString)

String conn = (((((('jdbc:sqlserver://' + url) + ';databaseName=') + dbname) + ';user=') + username) + ';password=') + password

if ((connection != null) && !(connection.isClosed())) {
	connection.close()
}

connection = DriverManager.getConnection(conn)

Statement stm = connection.createStatement()

ResultSet rs = stm.executeQuery(queryString)

def recordSet = rs

while (recordSet.next()) {
	String data1 = recordSet.getObject('RSH_User_Id')

	KeywordUtil.logInfo(data1)

	String Reference_No = recordSet.getObject('RSH_Reference_No')

	KeywordUtil.logInfo(GlobalVariable.label = Reference_No)

	String data2 = recordSet.getObject('RSH_Availability_Share')

	KeywordUtil.logInfo(data2)

}

queryString1 = ('Select top 1 * from appdata_retailer_stock_detail  where RSD_Reference_No = \'' + GlobalVariable.label + '\'order by 1 desc')

KeywordUtil.logInfo(queryString1)

String conn1 = (((((('jdbc:sqlserver://' + url) + ';databaseName=') + dbname) + ';user=') + username) + ';password=') + password

if ((connection != null) && !(connection.isClosed())) {
	connection.close()
}

connection = DriverManager.getConnection(conn1)

Statement stm1 = connection.createStatement()

ResultSet rs1 = stm1.executeQuery(queryString1)

def recordSet1 = rs1

while (recordSet1.next()) {
	String data01 = recordSet1.getObject('RSD_OnshelfQty_Pcs')

	KeywordUtil.logInfo(data01)

	Mobile.verifyMatch(data01, findTestData('Mobile Input Data/Stock Check').getValue('Quantity', 1), false)

	String Facing = recordSet1.getObject('RSD_Facing_Qty')

	KeywordUtil.logInfo(Facing)

	Mobile.verifyMatch(Facing, findTestData('Mobile Input Data/Stock Check').getValue('No_Facing', 1), false)

	String input = recordSet1.getObject('RSD_APH_Id')

	GlobalVariable.input= input

	KeywordUtil.logInfo(GlobalVariable.input)

}

queryString001 = ('select * from ADM_Product_Hierarchy where APH_Id = \'' + GlobalVariable.input + '\'')

KeywordUtil.logInfo(queryString001)

String conn001 = (((((('jdbc:sqlserver://' + url) + ';databaseName=') + dbname) + ';user=') + username) + ';password=') + password

if ((connection != null) && !(connection.isClosed())) {
	connection.close()
}

connection = DriverManager.getConnection(conn001)

Statement stm001 = connection.createStatement()

ResultSet rs001 = stm001.executeQuery(queryString001)

def recordSet001 = rs001

while (recordSet001.next()) {
	String data001 = recordSet001.getObject('APH_Name')

	KeywordUtil.logInfo(data001)

	Mobile.verifyMatch(data001, findTestData('Mobile Input Data/Stock Check').getValue('ProductName2', 1), false)
}

///product2_validation


queryString12 = ('Select top 1 * from appdata_retailer_stock_detail  where RSD_Reference_No = \'' + GlobalVariable.label + '\'')

KeywordUtil.logInfo(queryString12)

String conn12 = (((((('jdbc:sqlserver://' + url) + ';databaseName=') + dbname) + ';user=') + username) + ';password=') + password

if ((connection != null) && !(connection.isClosed())) {
	connection.close()
}

connection = DriverManager.getConnection(conn12)

Statement stm12 = connection.createStatement()

ResultSet rs12 = stm12.executeQuery(queryString12)

def recordSet12 = rs12

while (recordSet12.next()) {
	String data012 = recordSet12.getObject('RSD_OnshelfQty_Pcs')

	KeywordUtil.logInfo(data012)

	Mobile.verifyMatch(data012, findTestData('Mobile Input Data/Stock Check').getValue('Quantity', 1), false)

	String Facing = recordSet12.getObject('RSD_Facing_Qty')

	KeywordUtil.logInfo(Facing)

	Mobile.verifyMatch(Facing, findTestData('Mobile Input Data/Stock Check').getValue('No_Facing', 1), false)

	String input2 = recordSet12.getObject('RSD_APH_Id')

	GlobalVariable.label1= input2

	KeywordUtil.logInfo(GlobalVariable.label1)

}

queryString0012 = ('select * from ADM_Product_Hierarchy where APH_Id = \'' + GlobalVariable.label1 + '\'')

KeywordUtil.logInfo(queryString0012)

String conn0012 = (((((('jdbc:sqlserver://' + url) + ';databaseName=') + dbname) + ';user=') + username) + ';password=') + password

if ((connection != null) && !(connection.isClosed())) {
	connection.close()
}

connection = DriverManager.getConnection(conn0012)

Statement stm0012 = connection.createStatement()

ResultSet rs0012 = stm0012.executeQuery(queryString0012)

def recordSet0012 = rs0012

while (recordSet0012.next()) {
	String data0012 = recordSet0012.getObject('APH_Name')

	KeywordUtil.logInfo(data0012)

	Mobile.verifyMatch(data0012, findTestData('Mobile Input Data/Stock Check').getValue('ProductName1', 1), false)
}











