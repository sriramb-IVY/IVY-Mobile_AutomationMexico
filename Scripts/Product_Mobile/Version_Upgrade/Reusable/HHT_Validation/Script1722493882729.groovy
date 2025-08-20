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

import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import java.sql.Connection as Connection
import java.sql.DriverManager as DriverManager
import java.sql.ResultSet as ResultSet
import java.sql.Statement as Statement
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
//Connection connection = null
//
//url = findTestData('DB Credentials/Config1').getValue('URL', 1)
//
//dbname = findTestData('DB Credentials/Config1').getValue('dbname', 1)
//
//username = findTestData('DB Credentials/Config1').getValue('username', 1)
//
//password = findTestData('DB Credentials/Config1').getValue('password', 1)

queryString = 'select top 1 * from HHTTransactionDataQueueList  where UserId =\'' + GlobalVariable.vanseller_user_id + '\' order by 1 desc'

KeywordUtil.logInfo(queryString)

String conn = (((((('jdbc:sqlserver://' + GlobalVariable.DB_URL) + ';databaseName=') + GlobalVariable.DB_Name) + ';user=') + GlobalVariable.DB_Username) + ';password=') + GlobalVariable.DB_Password

connection = DriverManager.getConnection(conn)

Statement stm = connection.createStatement()

ResultSet rs = stm.executeQuery(queryString)

def recordSet = rs

while (recordSet.next()) {
	Object data1 = recordSet.getObject('PatchVersion')

	GlobalVariable.label = data1

  KeywordUtil.logInfo('Upgrade Version code  :  ' + data1)
}

//2nd DB
queryString1 = (('Select top 1 * from ADM_HHT_Version_Upgrade where AHVU_Isactive = 1 and AHVU_User_Id=\'' + GlobalVariable.vanseller_user_id) + '\' order by 1 desc')

KeywordUtil.logInfo(queryString1)

String conn1 = (((((('jdbc:sqlserver://' + GlobalVariable.DB_URL) + ';databaseName=') + GlobalVariable.DB_Name) + ';user=') + GlobalVariable.DB_Username) + ';password=') + GlobalVariable.DB_Password

connection1 = DriverManager.getConnection(conn1)

Statement stm1 = connection1.createStatement()

ResultSet rs1 = stm1.executeQuery(queryString1)

def recordSet1 = rs1

while (recordSet1.next()) {
	Object data2 = recordSet1.getObject('AHVU_AHV_Id')

	GlobalVariable.label1 = data2

   KeywordUtil.logInfo('Vlh id  :  ' + GlobalVariable.label1 )
}


//3nd DB
queryString2 = (('Select  * from ADM_HHT_Version where AHV_Id =\'' + GlobalVariable.label1) + '\' ')

KeywordUtil.logInfo(queryString2)

String conn2 = (((((('jdbc:sqlserver://' + GlobalVariable.DB_URL) + ';databaseName=') + GlobalVariable.DB_Name) + ';user=') + GlobalVariable.DB_Username) + ';password=') + GlobalVariable.DB_Password

connection2 = DriverManager.getConnection(conn2)

Statement stm2 = connection2.createStatement()

ResultSet rs2 = stm2.executeQuery(queryString2)

def recordSet2 = rs2

while (recordSet2.next()) {
	Object data3 = recordSet2.getObject('AHV_Version_Code')

   KeywordUtil.logInfo('Version code :  ' + data3)
   
   
   Mobile.verifyMatch(data3, GlobalVariable.label, false, FailureHandling.STOP_ON_FAILURE)
}

