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
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import java.sql.Connection as Connection
import java.sql.DriverManager as DriverManager
import java.sql.ResultSet as ResultSet
import java.sql.Statement as Statement
import org.openqa.selenium.By as By
import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.WebElement as WebElement
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable


//import com.kms.katalon.core.testcase.TestCase as TestCase
//import com.kms.katalon.core.testdata.TestData as TestData
//import internal.GlobalVariable as GlobalVariable
//
//// Specify the path to your JMX file
////String jmxFilePath = GlobalVariable.Jmeter_File_Path
//String jmxFilePath = "D:/Application/apache-jmeter-5.6.3/bin/jmeter.bat"
//// Run JMeter test
//String[] cmd = ["jmeter", "-n", "-t", jmxFilePath]
//ProcessBuilder builder = new ProcessBuilder(cmd)
//builder.redirectErrorStream(true)
//Process process = builder.start()
//process.waitFor()

import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import internal.GlobalVariable as GlobalVariable
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil


// Specify the full path to your JMX file
String jmxFilePath = GlobalVariable.Jmeter_File_Path

// Specify the full path to JMeter executable

String jmeterPath = GlobalVariable.Jmeter_Executable_Path
// Run JMeter test
String[] cmd = [jmeterPath, "-n", "-t", jmxFilePath]

ProcessBuilder builder = new ProcessBuilder(cmd)

builder.redirectErrorStream(true)

Process process = builder.start()

process.waitFor()

WebUI.delay(3)

//1st DB
Connection connection = null

String conn = (((((('jdbc:sqlserver://' + GlobalVariable.DB_URL) + ';databaseName=') + GlobalVariable.DB_Name) + ';user=') + GlobalVariable.DB_Username) + ';password=') + GlobalVariable.DB_Password

queryString = 'select top 1 * from AppData_Van_Load_Header where VLH_AUH_Id = \'' + GlobalVariable.vanseller_user_id + '\' order by 1 desc'

KeywordUtil.logInfo(queryString)

connection = DriverManager.getConnection(conn)

Statement stm = connection.createStatement()

ResultSet rs = stm.executeQuery(queryString)

def recordSet = rs

while (recordSet.next()) {
	Object data1 = recordSet.getObject('VLH_Reference_No')

	GlobalVariable.VanLoad_No = data1

	KeywordUtil.logInfo('Van Load Reference no  :  ' + GlobalVariable.VanLoad_No 
		)
}
