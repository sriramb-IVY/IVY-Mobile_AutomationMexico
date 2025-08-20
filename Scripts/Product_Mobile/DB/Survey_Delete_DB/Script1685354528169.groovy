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

import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import java.sql.Connection
import java.sql.DriverManager
import java.sql.ResultSet
import java.sql.Statement
import org.openqa.selenium.WebElement
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.keyword.excel.ExcelKeywords
import internal.GlobalVariable
import java.text.SimpleDateFormat;
import java.util.Date

Date date = new Date()
SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd")
String today_Date= formatter.format(date)
KeywordUtil.logInfo('Today date is ' + today_Date)
String time = '00:00:00.000'

Connection connection = null

url = findTestData('DB Credentials/Config1').getValue('URL', 1)

dbname = findTestData('DB Credentials/Config1').getValue('dbname', 1)

username = findTestData('DB Credentials/Config1').getValue('username', 1)

password = findTestData('DB Credentials/Config1').getValue('password', 1)

Product = '1589'

KeywordUtil.logInfo('Today date is ' + today_Date)

queryString = ('Select * from AppData_Survey_Result_Header where SRH_AUH_Id =  \'' + Product +'\' and SRH_Date= \''+ today_Date +'\' and SRH_Created_By = \'' + GlobalVariable.vanseller_user_id + '\'')

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
	String data1 = recordSet.getObject('SRH_ID')

	KeywordUtil.logInfo(GlobalVariable.label = data1)

}

queryString1 = ('Select * from AppData_Survey_Result_Detail  where srd_srh_id= \'' + GlobalVariable.label + '\'')

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
	String data01 = recordSet1.getObject('SRD_SRH_ID')

	KeywordUtil.logInfo(data01)

	GlobalVariable.label1 = data01

}

queryString2 = ('select * from AppData_Survey_Result_Images  where SRI_SRH_Id =\'' + GlobalVariable.label1 + '\'')

KeywordUtil.logInfo(queryString2)

String conn2 = (((((('jdbc:sqlserver://' + url) + ';databaseName=') + dbname) + ';user=') + username) + ';password=') + password

if ((connection != null) && !(connection.isClosed())) {
	connection.close()
}

connection = DriverManager.getConnection(conn2)

Statement stm2 = connection.createStatement()

ResultSet rs2 = stm2.executeQuery(queryString2)

def recordSet2 = rs2

while (recordSet2.next()) {
	String Data002 = recordSet2.getObject('SRI_Image_Name')

	KeywordUtil.logInfo(Data002)

}

//delete_querry

queryString01 = ('delete from AppData_Survey_Result_Header where SRH_AUH_Id =  \'' + Product +'\' and SRH_Date= \''+ today_Date +'\' and SRH_Created_By = \'' + GlobalVariable.vanseller_user_id + '\'')

KeywordUtil.logInfo(queryString01)

String conn01 = (((((('jdbc:sqlserver://' + url) + ';databaseName=') + dbname) + ';user=') + username) + ';password=') + password

if ((connection != null) && !(connection.isClosed())) {
	connection.close()
}

connection01 = DriverManager.getConnection(conn01)

Statement stm01 = connection01.createStatement()

boolean rs0 = stm01.execute(queryString01)

queryString02 = ('delete from AppData_Survey_Result_Detail  where srd_srh_id= \'' + GlobalVariable.label + '\'')

KeywordUtil.logInfo(queryString02)

String conn02 = (((((('jdbc:sqlserver://' + url) + ';databaseName=') + dbname) + ';user=') + username) + ';password=') + password

if ((connection != null) && !(connection.isClosed())) {
	connection.close()
}

connection02 = DriverManager.getConnection(conn02)

Statement stm02 = connection02.createStatement()

boolean rs02 = stm02.execute(queryString02)

queryString03 = ('delete from AppData_Survey_Result_Images  where SRI_SRH_Id =\'' + GlobalVariable.label1 + '\'')

KeywordUtil.logInfo(queryString03)

String conn03 = (((((('jdbc:sqlserver://' + url) + ';databaseName=') + dbname) + ';user=') + username) + ';password=') + password

if ((connection != null) && !(connection.isClosed())) {
	connection.close()
}

connection03 = DriverManager.getConnection(conn03)

Statement stm03 = connection03.createStatement()

boolean rs03 = stm03.execute(queryString03)


