import java.sql.Connection as Connection
import java.sql.DriverManager as DriverManager
import java.sql.ResultSet as ResultSet
import java.sql.Statement as Statement
import java.text.SimpleDateFormat as SimpleDateFormat
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.mobile.keyword.internal.MobileDriverFactory as MobileDriverFactory
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import internal.GlobalVariable as GlobalVariable
import io.appium.java_client.AppiumDriver as AppiumDriver
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.chrome.ChromeDriver as ChromeDriver
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import com.kms.katalon.keyword.excel.ExcelKeywords as ExcelKeywords
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil


SimpleDateFormat sdf = new SimpleDateFormat('yyyy-MM-dd')

Calendar c = Calendar.getInstance()

c.setTime(new Date())

c.add(Calendar.DATE, 0)

c.add(Calendar.MONTH, 0)

c.add(Calendar.YEAR, 0)

String Present_Date = sdf.format(c.getTime())

System.out.println(Present_Date)

DB_Present_Date = (Present_Date + ' 00:00:00.000')

DB_Start_Datetime = (((Present_Date + ' ') + findTestData('VBL_Mobile Input Data/Attendance').getValue('StartTime', 1)) + '.000')

DB_End_Datetime = (((Present_Date + ' ') + findTestData('VBL_Mobile Input Data/Attendance').getValue('EndTime', 1)) + '.000')

GlobalVariable.start_time = DB_Start_Datetime

GlobalVariable.end_time = DB_End_Datetime

println('DB_Start_Datetime : ' + GlobalVariable.start_time)

println('DB_End_Datetime : ' + GlobalVariable.end_time)

Connection connection4 = null

url4 = findTestData('VBL_DB Credentials/Config1').getValue('URL', 1)

dbname4 = findTestData('VBL_DB Credentials/Config1').getValue('dbname', 1)

username4 = findTestData('VBL_DB Credentials/Config1').getValue('username', 1)

password4 = findTestData('VBL_DB Credentials/Config1').getValue('password', 1)

queryString4 = (('select top 1 * from AppData_OdoMeter_History  where omh_auh_id= 297 and OMH_Van_Id = 15 and OMH_Date =\'' +
DB_Present_Date) + '\'order by 1 desc')

KeywordUtil.logInfo(queryString4)

String conn4 = (((((('jdbc:sqlserver://' + url4) + ';databaseName=') + dbname4) + ';user=') + username4) + ';password=') +
password4

connection4 = DriverManager.getConnection(conn4)

Statement stm4 = connection4.createStatement()

ResultSet rs4 = stm4.executeQuery(queryString4)

def recordSet4 = rs4

while (recordSet4.next()) {
	Object OMH_Meter_Start = recordSet4.getObject('OMH_Meter_Start')

	println(OMH_Meter_Start)

	Enter_StartTrip = findTestData('VBL_Mobile Input Data/Odometer').getValue('Start_Trip_Value', 1)

	Mobile.verifyEqual(OMH_Meter_Start, Integer.parseInt(Enter_StartTrip), FailureHandling.STOP_ON_FAILURE)

	println('Start Trip Kilometer value correctly inserted into the database')

	Object OMH_Meter_End = recordSet4.getObject('OMH_Meter_End')

	println(OMH_Meter_End)

	Enter_EndTrip = findTestData('VBL_Mobile Input Data/Odometer').getValue('End_Trip_Value', 1)

	Mobile.verifyEqual(OMH_Meter_End, Integer.parseInt(Enter_EndTrip), FailureHandling.STOP_ON_FAILURE)

	println('End Trip Kilometer value correctly inserted into the database')
}

Connection connection1 = null

url1 = findTestData('VBL_DB Credentials/Config1').getValue('URL', 1)

dbname1 = findTestData('VBL_DB Credentials/Config1').getValue('dbname', 1)

username1 = findTestData('VBL_DB Credentials/Config1').getValue('username', 1)

password1 = findTestData('VBL_DB Credentials/Config1').getValue('password', 1)

queryString1 = (('select TOP 1 * from AppData_Attendance_Time_Detail where aatd_su_id=297 and AATD_Time_In = \'' + GlobalVariable.start_time) +
'\' order by 1 desc')

KeywordUtil.logInfo(queryString1)

String conn1 = (((((('jdbc:sqlserver://' + url1) + ';databaseName=') + dbname1) + ';user=') + username1) + ';password=') +
password1

connection1 = DriverManager.getConnection(conn1)

Statement stm1 = connection1.createStatement()

ResultSet rs1 = stm1.executeQuery(queryString1)

def recordSet1 = rs1

while (recordSet1.next()) {
	Object data2 = recordSet1.getObject('AATD_Reason_LOV_Id')

	println(data2)

	GlobalVariable.label = data2

	Object Start_Time_DB = recordSet1.getObject('AATD_Time_In')

	String Start_Time = Start_Time_DB.toString() + '00'

	println(Start_Time)

	//Mobile.verifyEqual(Start_Time_DB, GlobalVariable.start_time, FailureHandling.STOP_ON_FAILURE)
	Mobile.verifyMatch(Start_Time, GlobalVariable.start_time, false, FailureHandling.STOP_ON_FAILURE)

	println('Attendance Start Time correctly inserted into the database')

	Object End_Time_DB = recordSet1.getObject('AATD_Time_Out')

	String End_Time = End_Time_DB.toString() + '00'

	println(End_Time)

	Mobile.verifyMatch(End_Time, GlobalVariable.end_time, false, FailureHandling.STOP_ON_FAILURE)

	println('Attendance End Time correctly inserted into the database')
}

Connection connection2 = null

url2 = findTestData('VBL_DB Credentials/Config1').getValue('URL', 1)

dbname2 = findTestData('VBL_DB Credentials/Config1').getValue('dbname', 1)

username2 = findTestData('VBL_DB Credentials/Config1').getValue('username', 1)

password2 = findTestData('VBL_DB Credentials/Config1').getValue('password', 1)

queryString2 = (('select * from adm_lovs where alov_id = \'' + GlobalVariable.label) + '\'')

KeywordUtil.logInfo(queryString2)

String conn2 = (((((('jdbc:sqlserver://' + url2) + ';databaseName=') + dbname2) + ';user=') + username2) + ';password=') +
password2

connection2 = DriverManager.getConnection(conn2)

Statement stm2 = connection2.createStatement()

ResultSet rs2 = stm2.executeQuery(queryString2)

def recordSet2 = rs2

while (recordSet2.next()) {
	Object data3 = recordSet2.getObject('ALOV_Code')

	println(data3)

	Mobile.verifyMatch(data3, GlobalVariable.ReasonField, false, FailureHandling.STOP_ON_FAILURE)

	println('Attendance Reason correctly inserted into the database')
}

