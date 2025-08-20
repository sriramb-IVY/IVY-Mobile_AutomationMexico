import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import java.sql.Connection as Connection
import java.sql.DriverManager as DriverManager
import java.sql.ResultSet as ResultSet
import java.sql.Statement as Statement
import java.text.SimpleDateFormat as SimpleDateFormat
import com.google.common.collect.FilteredEntryMultimap.Keys as Keys
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil

String pattern = 'yyyy-MM-dd'

SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern)

String currentdate = simpleDateFormat.format(new Date())

println(currentdate)

DB_Currentdate = (currentdate + ' 00:00:00.000')
 
Connection connection = null

url = findTestData('VBL_DB Credentials/Config1').getValue('URL', 1)

dbname = findTestData('VBL_DB Credentials/Config1').getValue('dbname', 1)

username = findTestData('VBL_DB Credentials/Config1').getValue('username', 1)

password = findTestData('VBL_DB Credentials/Config1').getValue('password', 1)

queryString = (('Select * from appdata_sales_invoice_header where SIH_UserId =  \'' + GlobalVariable.vanseller_user_id + '\' and SIH_Date = \'' + DB_Currentdate) + '\' order by 1 desc ')

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
	String data1 = recordSet.getObject('sih_id')

	KeywordUtil.logInfo(data1)

	GlobalVariable.label = data1


queryString01 = (('delete from appdata_sales_invoice_detail where sid_sih_id=\'' + GlobalVariable.label) + '\'')

KeywordUtil.logInfo(queryString01)

String conn01 = (((((('jdbc:sqlserver://' + url) + ';databaseName=') + dbname) + ';user=') + username) + ';password=') + password

connection01 = DriverManager.getConnection(conn01)

Statement stm01 = connection01.createStatement()

boolean rs01 = stm01.execute(queryString01)

//println(rs01)

queryString0 = (('delete from appdata_sales_invoice_scheme_detail  where sisd_sih_id=\'' + GlobalVariable.label) + '\' ')

KeywordUtil.logInfo(queryString0)

String conn0 = (((((('jdbc:sqlserver://' + url) + ';databaseName=') + dbname) + ';user=') + username) + ';password=') + password



connection0 = DriverManager.getConnection(conn0)

Statement stm0 = connection0.createStatement()

boolean rs0 = stm0.execute(queryString0)

//println(rs0)

queryString1 = (('delete from appdata_sales_invoice_tax_detail where sitd_sih_id=\'' + GlobalVariable.label) + '\' ')
KeywordUtil.logInfo(queryString1)

String conn1 = (((((('jdbc:sqlserver://' + url) + ';databaseName=') + dbname) + ';user=') + username) + ';password=') +
		password


connection1 = DriverManager.getConnection(conn1)

Statement stm1 = connection1.createStatement()

boolean rs1 = stm1.execute(queryString1)

//println(rs1)

queryString2 = (('delete from appdata_sales_invoice_discount_detail  where sidd_sih_id=\'' + GlobalVariable.label) + '\' ')

String conn2 = (((((('jdbc:sqlserver://' + url) + ';databaseName=') + dbname) + ';user=') + username) + ';password=') +
		password


connection2 = DriverManager.getConnection(conn2)

Statement stm2 = connection2.createStatement()

boolean rs2 = stm2.execute(queryString2)

//println(rs2)

queryString3 = (('delete from AppData_Sales_Invoice_Scheme_Detail_Credit  where sisdc_sih_id=\'' + GlobalVariable.label) + '\' ')
KeywordUtil.logInfo(queryString3)

String conn3 = (((((('jdbc:sqlserver://' + url) + ';databaseName=') + dbname) + ';user=') + username) + ';password=') +
		password


connection3 = DriverManager.getConnection(conn3)

Statement stm3 = connection3.createStatement()

boolean rs3 = stm3.execute(queryString3)

//println(rs3)

queryString4 = (('delete from appdata_sales_invoice_header where SIH_UserId =  \'' + GlobalVariable.vanseller_user_id + '\' and sih_id = \'' + GlobalVariable.label) + '\'')

KeywordUtil.logInfo(queryString4)

String conn4 = (((((('jdbc:sqlserver://' + url) + ';databaseName=') + dbname) + ';user=') + username) + ';password=') +
		password



connection4 = DriverManager.getConnection(conn4)

Statement stm4 = connection4.createStatement()

boolean rs4 = stm4.execute(queryString4)

//println(rs4)
}


