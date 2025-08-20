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

Mobile.callTestCase(findTestCase('VBL_Mobile/Re-Usables/Mobile_Login/VanSeller_withoutAttendance'), [:], FailureHandling.STOP_ON_FAILURE)

//Mobile.startApplication(GlobalVariable.APK_File, false)
//
//Mobile.tap(findTestObject('XXXXXXXXXXXX/Re-usable/Mobile Login/Menu'), 0)
Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/MainMenu/Menu_Trade Coverage'), 0)

AppiumDriver driver = MobileDriverFactory.getDriver()

Actualtoastmessage = driver.findElementByXPath('//android.widget.Toast[1]').getText()

println(Actualtoastmessage)

Mobile.verifyMatch(Actualtoastmessage, findTestData('VBL_Mobile Input Data/Attendance').getValue('Verify_Text', 2), false, FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

//AT_02The user must verify whether an order can be created without attendance.
println('Application not allow to take order')

Mobile.tap(findTestObject('XXXXXXXXXXXX/Re-usable/Mobile Login/Menu'), 0)

Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/MainMenu/Menu_Attendance'), 0)

if (Mobile.verifyElementVisible(findTestObject('Object Repository/XXXXXXXXXXXX/Re-usable/Mobile Login/While using the app'), 
    2, FailureHandling.OPTIONAL)) {
    Mobile.tap(findTestObject('Object Repository/XXXXXXXXXXXX/Re-usable/Mobile Login/While using the app'), 0)
}

Mobile.verifyElementExist(findTestObject('XXXXXXXXXXXX/Mobile Part/Attendance/Title-Attendance'), 3, FailureHandling.STOP_ON_FAILURE)

Mobile.verifyElementText(findTestObject('XXXXXXXXXXXX/Mobile Part/Attendance/Title-Attendance'), findTestData('VBL_Mobile Input Data/Attendance').getValue(
        'Verify_Text', 1))

Mobile.takeScreenshot()

//AT_01 To verify attendance  screen
println('Attendance Screen Title verified!')

Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Attendance/Attendance_Plus Icon'), 0)

selected_reason = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/Attendance/RadioButton-Working'), 3)

Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Attendance/RadioButton-Working'), 0)

Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Attendance/RadioButton-PopUp-OK'), 0)

GlobalVariable.ReasonField = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/Attendance/Reason_Field_Text'), 3)

Mobile.verifyMatch(GlobalVariable.ReasonField, selected_reason, false, FailureHandling.STOP_ON_FAILURE)

println('selected reason should be corrected to reason field in attendance screen')

Mobile.takeScreenshot()

str = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/Attendance/StartTime_Field'), 0)

start_time = str.replaceAll('\\s', '')

starttime = start_time.substring(10)

println('starttime : ' + starttime)

//endtime = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/Attendance/EndTime_Field'), 0)
status = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/Attendance/Status_Field'), 0)

String exlpath = 'DDF\\VBL\\Mobile Input data\\Mobile Input data.xlsx'

String Sheetname = 'Attendance'

workbook01 = ExcelKeywords.getWorkbook(exlpath)

sheet1 = ExcelKeywords.getExcelSheet(workbook01, Sheetname)

ExcelKeywords.setValueToCellByIndex(sheet1, 1, 3, starttime)

//ExcelKeywords.setValueToCellByIndex(sheet1, 1, 4, endtime)
//ExcelKeywords.setValueToCellByIndex(sheet1, 1, 5, status)
ExcelKeywords.setValueToCellByIndex(sheet1, 1, 6, GlobalVariable.ReasonField)

ExcelKeywords.saveWorkbook(exlpath, workbook01)

Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Reusable Object/Menu'), 0)

Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/MainMenu/Menu_Trade Coverage'), 0)

Mobile.verifyElementExist(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/Trade Coverage(Title)'), 3, FailureHandling.STOP_ON_FAILURE)

Mobile.verifyElementVisible(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/Trade Coverage(Title)'), 2, FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

//AT_03 The user must verify whether an order can be create with attendance.
println('User can able to put order only after given attendance!')

Mobile.tap(findTestObject('XXXXXXXXXXXX/Re-usable/Mobile Login/Menu'), 0)

Mobile.delay(3)

