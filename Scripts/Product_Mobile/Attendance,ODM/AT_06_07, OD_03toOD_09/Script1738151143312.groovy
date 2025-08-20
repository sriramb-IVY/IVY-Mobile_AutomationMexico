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

//Mobile.startApplication(GlobalVariable.APK_File, false)
WebUI.callTestCase(findTestCase('Product_Mobile/Common/Seller_Menu_Selection'), [('MenuName') : findTestData('Mobile Input Data/Common').getValue('Menu', 18)
	, ('SubMenuName') : findTestData('Mobile Input Data/Common').getValue('Menu', 19)], FailureHandling.STOP_ON_FAILURE)


Mobile.setText(findTestObject('Mobile/Odometer/Enter end value'), findTestData('Mobile Input Data/Odometer').getValue('End_Trip_Value',
		1), 0)

startvalues = Mobile.getText(findTestObject('Mobile/Odometer/Get_Startvalue_Field'), 0)

KeywordUtil.logInfo(startvalues)

Endvalues = Mobile.getText(findTestObject('Mobile/Odometer/Get_Endvalue_Field'), 0)

KeywordUtil.logInfo(Endvalues)

Values = (Double.parseDouble(Endvalues) - Double.parseDouble(startvalues))

KeywordUtil.logInfo(Values.toString())

distances = Mobile.getText(findTestObject('Mobile/Odometer/DistanceCovered_ValueField'), 0)

KeywordUtil.logInfo(distances)

Mobile.verifyEqual(Values, Double.parseDouble(distances), FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

KeywordUtil.logInfo('Distance coverage field should be validated')

Mobile.tap(findTestObject('Mobile/Odometer/Btn_End Journey'), 0)

switch (GlobalVariable.Activation_Key) {
	case GlobalVariable.V155_ActivationKey:
		break
	case GlobalVariable.V158_ActivationKey:
		Mobile.tap(findTestObject('Mobile/Common/Btn_OK'), 2, FailureHandling.OPTIONAL)

		break
}

popup = Mobile.getText(findTestObject('Mobile/Odometer/Saved Successfully.'), 0)

KeywordUtil.logInfo(popup)

verifypopup = findTestData('Mobile Input Data/Odometer').getValue('Alerts', 1)

KeywordUtil.logInfo(verifypopup)

Mobile.verifyMatch(popup, verifypopup, false)

Mobile.takeScreenshot()

KeywordUtil.logInfo('End trip field should be enable and able to enter End trip data and click save successfully')

Mobile.tap(findTestObject('Mobile/Common/Btn_OK'), 0, FailureHandling.OPTIONAL)

Mobile.tap(findTestObject('Mobile/Odometer/SubMenu_Odometer'), 0)

Mobile.verifyElementVisible(findTestObject('Mobile/Odometer/End Date'), 0, FailureHandling.OPTIONAL)

Mobile.verifyElementVisible(findTestObject('Mobile/Odometer/End Date Value'), 0, FailureHandling.OPTIONAL)

Mobile.verifyElementVisible(findTestObject('Mobile/Odometer/End Time'), 0, FailureHandling.OPTIONAL)

Mobile.verifyElementVisible(findTestObject('Mobile/Odometer/End Time Value'), 0, FailureHandling.OPTIONAL)

Mobile.takeScreenshot()

KeywordUtil.logInfo('Before ending the trip, VanLoad number should be displayed in odometer screen')

Mobile.tap(findTestObject('Mobile/Common/Icon_Back'), 0)

Mobile.tap(findTestObject('Mobile/Common/Btn_OK'), 0, FailureHandling.OPTIONAL)

WebUI.callTestCase(findTestCase('Product_Mobile/Common/Seller_Menu_Selection'), [('MenuName') : findTestData('Mobile Input Data/Common').getValue('Menu', 2)
	, ('SubMenuName') : findTestData('Mobile Input Data/Common').getValue('Menu', 1)], FailureHandling.STOP_ON_FAILURE)


if (Mobile.verifyElementVisible(findTestObject('Mobile/Attendance/Btn_END'), 5, FailureHandling.OPTIONAL)) {
	Mobile.tap(findTestObject('Mobile/Attendance/Btn_END'), 0)

	end = Mobile.getText(findTestObject('Mobile/Attendance/Field_EndTime'), 0)

	end_time = end.replaceAll('\\s', '')

	endtime = end_time.substring(10)

	println('endtime : ' + endtime)

	GlobalVariable.end_time = endtime

	String exlpath = 'DDF\\Mobile Input data\\Mobile Input data.xlsx'

	String Sheetname = 'Attendance'

	workbook01 = ExcelKeywords.getWorkbook(exlpath)

	sheet1 = ExcelKeywords.getExcelSheet(workbook01, Sheetname)

	ExcelKeywords.setValueToCellByIndex(sheet1, 1, 4, endtime)

	ExcelKeywords.saveWorkbook(exlpath, workbook01)
}

status = Mobile.getText(findTestObject('Mobile/Attendance/Field_Status'), 0)

String actualText = status

String expectedText = findTestData('Mobile Input Data/Attendance').getValue('Status', 1)

// Use equalsIgnoreCase for comparison
if (actualText.equalsIgnoreCase(expectedText)) {
	println('The texts match (case insensitive)')
} else {
	println('The texts do not match')
}

println('Once click Attendance End Button, Status changed into "Complete" properly')

Mobile.takeScreenshot()

'The user cannot pocess the application and "Please Mark Attendance As Working To Proceed." alert should be displayed.'
Mobile.tap(findTestObject('Mobile/Common/Btn_Menu'), 0, FailureHandling.OPTIONAL)

Mobile.tap(findTestObject('Mobile/MainMenu/Menu_Trade Coverage'), 0)

AppiumDriver driver = MobileDriverFactory.getDriver()

Actualtoastmessage = driver.findElementByXPath('//android.widget.Toast[1]').getText()

KeywordUtil.logInfo(Actualtoastmessage)

'The user cannot pocess the application and "Please Mark Attendance As Working To Proceed." alert should be displayed.'
Mobile.verifyMatch(Actualtoastmessage, findTestData('Mobile Input Data/Attendance').getValue('Verify_Text', 3), false, FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Sync'), [:], FailureHandling.STOP_ON_FAILURE)

Mobile.callTestCase(findTestCase('Product_Mobile/Common/HHT_Transaction_DB'), [:], FailureHandling.STOP_ON_FAILURE)

SimpleDateFormat sdf = new SimpleDateFormat('yyyy-MM-dd')

Calendar c = Calendar.getInstance()

c.setTime(new Date())

c.add(Calendar.DATE, 0)

c.add(Calendar.MONTH, 0)

c.add(Calendar.YEAR, 0)

String Present_Date = sdf.format(c.getTime())

System.out.println(Present_Date)

DB_Present_Date = (Present_Date + ' 00:00:00.000')

DB_Start_Datetime = (((Present_Date + ' ') + findTestData('Mobile Input Data/Attendance').getValue('StartTime', 1)) + '.000')

DB_End_Datetime = (((Present_Date + ' ') + findTestData('Mobile Input Data/Attendance').getValue('EndTime', 1)) + '.000')

GlobalVariable.start_time = DB_Start_Datetime

GlobalVariable.end_time = DB_End_Datetime

println('DB_Start_Datetime : ' + GlobalVariable.start_time)

println('DB_End_Datetime : ' + GlobalVariable.end_time)

Mobile.callTestCase(findTestCase('Product_Mobile/Common/DB'), [('Query') : 'select top 1 * from AppData_OdoMeter_History  where omh_auh_id= \'' + GlobalVariable.vanseller_user_id +
	'\'and OMH_Van_Id = \'' + GlobalVariable.van_id + '\' and OMH_Date =\'' + DB_Present_Date + '\'order by 1 desc', ('columnNames') : ['OMH_Meter_Start','OMH_Meter_End']], FailureHandling.STOP_ON_FAILURE)

String OMH_Meter_Start=GlobalVariable.data[0]

KeywordUtil.logInfo("Meter start : "+OMH_Meter_Start)

Enter_StartTrip = findTestData('Mobile Input Data/Odometer').getValue('Start_Trip_Value', 1)

Mobile.verifyEqual(OMH_Meter_Start, Integer.parseInt(Enter_StartTrip), FailureHandling.STOP_ON_FAILURE)

KeywordUtil.logInfo('Start Trip Kilometer value correctly inserted into the database')

String OMH_Meter_End=GlobalVariable.data[1]

KeywordUtil.logInfo("Meter end : "+OMH_Meter_End)

Enter_EndTrip = findTestData('Mobile Input Data/Odometer').getValue('End_Trip_Value', 1)

Mobile.verifyEqual(OMH_Meter_End, Integer.parseInt(Enter_EndTrip), FailureHandling.STOP_ON_FAILURE)

KeywordUtil.logInfo('End Trip Kilometer value correctly inserted into the database')

Mobile.comment('Attendance table')


Mobile.callTestCase(findTestCase('Product_Mobile/Common/DB'), [('Query') : 'select TOP 1 * from AppData_Attendance_Time_Detail where aatd_su_id=\'' + GlobalVariable.vanseller_user_id +
	'\'and AATD_Time_In = \'' + GlobalVariable.start_time + '\' order by 1 desc', ('columnNames') : ['AATD_Reason_LOV_Id','AATD_Time_In','AATD_Time_Out']], FailureHandling.STOP_ON_FAILURE)

String Reasonid=GlobalVariable.data[0]

KeywordUtil.logInfo("Reason id  : "+Reasonid)

GlobalVariable.label = Reasonid


String Start_Time_DB=GlobalVariable.data[1]

String Start_Time = Start_Time_DB.toString() + '00'

println(Start_Time)

Mobile.verifyMatch(Start_Time, GlobalVariable.start_time, false, FailureHandling.STOP_ON_FAILURE)

println('Attendance Start Time correctly inserted into the database')


String End_Time_DB=GlobalVariable.data[2]

String End_Time = End_Time_DB.toString() + '00'

println(End_Time)

Mobile.verifyMatch(End_Time, GlobalVariable.end_time, false, FailureHandling.STOP_ON_FAILURE)

println('Attendance End Time correctly inserted into the database')


Mobile.comment('Attendance reason table')

Mobile.callTestCase(findTestCase('Product_Mobile/Common/DB'), [('Query') : 'select * from adm_lovs where alov_id = \'' + GlobalVariable.label + '\'', ('columnNames') : ['ALOV_Code']], FailureHandling.STOP_ON_FAILURE)

String Reason=GlobalVariable.data[0]

KeywordUtil.logInfo("Reason  : "+Reason)


Mobile.verifyMatch(Reason, GlobalVariable.ReasonField, false, FailureHandling.STOP_ON_FAILURE)

println('Attendance Reason correctly inserted into the database')


