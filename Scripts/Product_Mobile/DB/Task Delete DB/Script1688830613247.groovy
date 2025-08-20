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
import org.junit.Assert as Assert

'Delete Web created CPG_OneTimeTask'


	
Connection connection = null

url = findTestData('DB Credentials/Config1').getValue('URL', 1)

dbname = findTestData('DB Credentials/Config1').getValue('dbname', 1)

username = findTestData('DB Credentials/Config1').getValue('username', 1)

password = findTestData('DB Credentials/Config1').getValue('password', 1)

String conn = (((((('jdbc:sqlserver://' + url) + ';databaseName=') + dbname) + ';user=') + username) + ';password=') + password

connection = DriverManager.getConnection(conn)

Statement stm = connection.createStatement()

GlobalVariable.Name = findTestData('Web Input Data/Task_Management').getValue('Title_Name', 1)

queryString = (('select * from ADM_Task  where AT_Code= \'' + GlobalVariable.Name) + '\' and AT_Created_By = \'' + GlobalVariable.DivisionUserId + '\'')

KeywordUtil.logInfo(queryString)

ResultSet rs = stm.executeQuery(queryString)

def recordSet = rs

while (recordSet.next()) {
	String data1 = recordSet.getObject('AT_Id')

	KeywordUtil.logInfo(data1)

	GlobalVariable.label = data1
	
	String conn7 = (((((('jdbc:sqlserver://' + url) + ';databaseName=') + dbname) + ';user=') + username) + ';password=') + password
	
	connection7 = DriverManager.getConnection(conn7)
	
	Statement stm7 = connection7.createStatement()
	
	queryString7 = (('delete from ADM_Task where AT_Id=\'' + GlobalVariable.label) + '\' ')
	
	KeywordUtil.logInfo(queryString7)
	
	boolean rs7 = stm7.execute(queryString7)
	
	println(rs7)
	
	String conn06 = (((((('jdbc:sqlserver://' + url) + ';databaseName=') + dbname) + ';user=') + username) + ';password=') + password
	
	connection06 = DriverManager.getConnection(conn06)
	
	Statement stm06 = connection06.createStatement()
	
	queryString06 = (('delete from AppData_Task_Completion_History where TCH_AT_Id= \'' + GlobalVariable.label) + '\'')
	
	KeywordUtil.logInfo(queryString06)
	
	boolean rs06 = stm06.execute(queryString06)
	
	println(rs06)
	

	
	}
//}

	
'Delete Web created CPG_Routine Task'

Statement stm2 = connection.createStatement()

GlobalVariable.Name = findTestData('Web Input Data/Task_Management').getValue('Title_Name', 2)

queryString2 = ('select * from ADM_Task  where AT_Code= \'' + GlobalVariable.Name + '\' and AT_Created_By = \'' + GlobalVariable.DivisionUserId + '\'')

KeywordUtil.logInfo(queryString2)

ResultSet rs2 = stm2.executeQuery(queryString2)

def recordSet2 = rs2

while (recordSet2.next()) {
	String data2 = recordSet2.getObject('AT_Id')

	KeywordUtil.logInfo(data2)

	GlobalVariable.label = data2

	String conn0 = (((((('jdbc:sqlserver://' + url) + ';databaseName=') + dbname) + ';user=') + username) + ';password=') + password
	
	connection0 = DriverManager.getConnection(conn0)
	
	Statement stm0 = connection0.createStatement()
	
	queryString0 = (('delete from AppData_Task_Completion_History where TCH_AT_Id= \'' + GlobalVariable.label) + '\'')
	
	KeywordUtil.logInfo(queryString0)
	
	boolean rs0 = stm0.execute(queryString0)
	
	println(rs0)
	
	}

	
'Delete Mobile created task'

Connection connection3 = null

url = findTestData('DB Credentials/Config1').getValue('URL', 1)

dbname = findTestData('DB Credentials/Config1').getValue('dbname', 1)

username = findTestData('DB Credentials/Config1').getValue('username', 1)

password = findTestData('DB Credentials/Config1').getValue('password', 1)

String conn3 = (((((('jdbc:sqlserver://' + url) + ';databaseName=') + dbname) + ';user=') + username) + ';password=') + password

connection3 = DriverManager.getConnection(conn3)

Statement stm3 = connection3.createStatement()

GlobalVariable.Name = findTestData('Mobile Input Data/Task_Management').getValue('Title_Name', 1)

queryString3 = (('select * from ADM_Task  where AT_Code= \'' + GlobalVariable.Name) + '\' and AT_Created_By = \'' + GlobalVariable.vanseller_user_id + '\'')

KeywordUtil.logInfo(queryString3)

ResultSet rs3 = stm3.executeQuery(queryString3)

def recordSet3 = rs3

while (recordSet3.next()) {
	String data3 = recordSet3.getObject('AT_Id')

	KeywordUtil.logInfo(data3)

	GlobalVariable.label = data3

	String conn4 = (((((('jdbc:sqlserver://' + url) + ';databaseName=') + dbname) + ';user=') + username) + ';password=') + password
	
	connection4 = DriverManager.getConnection(conn4)
	
	Statement stm4 = connection4.createStatement()
	
	queryString4 = (('delete from ADM_Task where AT_Id=\'' + GlobalVariable.label) + '\' ')
	
	KeywordUtil.logInfo(queryString4)
	
	boolean rs4 = stm4.execute(queryString4)
	
	println(rs4)
	
	
	String conn5 = (((((('jdbc:sqlserver://' + url) + ';databaseName=') + dbname) + ';user=') + username) + ';password=') + password
	
	connection5 = DriverManager.getConnection(conn5)
	
	Statement stm5 = connection5.createStatement()
	
	queryString5 = (('delete from AppData_Task_Completion_History where TCH_AT_Id= \'' + GlobalVariable.label) + '\'')
	
	KeywordUtil.logInfo(queryString5)
	
	boolean rs5 = stm5.execute(queryString5)
	
	println(rs5)

}