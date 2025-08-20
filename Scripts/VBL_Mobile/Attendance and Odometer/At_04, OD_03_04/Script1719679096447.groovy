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


Mobile.waitForElementPresent(findTestObject('XXXXXXXXXXXX/Mobile Part/Reusable Object/Menu'), 0)

Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Reusable Object/Menu'), 0)

Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/MainMenu/Menu_Load Management'), 0)

Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Odometer/SubMenu_Odometer'), 0)

Mobile.setText(findTestObject('XXXXXXXXXXXX/Mobile Part/Odometer/Enter end value'), findTestData('VBL_Mobile Input Data/Odometer').getValue(
        'End_Trip_Value', 1), 0)

startvalues = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/Odometer/Get_Startvalue_Field'), 0)

println(Double.parseDouble(startvalues))

Endvalues = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/Odometer/Get_Endvalue_Field'), 0)

println(Double.parseDouble(Endvalues))

Values = (Double.parseDouble(Endvalues) - Double.parseDouble(startvalues))

println(Values)

distances = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/Odometer/DistanceCovered_ValueField'), 0)

println(Double.parseDouble(distances))

Mobile.verifyEqual(Values, Double.parseDouble(distances), FailureHandling.STOP_ON_FAILURE)

Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Odometer/Btn_End Journey'), 0)

popup = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/Odometer/Saved Successfully.'), 0)

println(popup)

verifypopup = findTestData('VBL_Mobile Input Data/Odometer').getValue('Alerts', 1)

println(verifypopup)

Mobile.verifyMatch(popup, verifypopup, false)

Mobile.takeScreenshot()

Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Odometer/Saved Successfully- OK'), 0)

Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Reusable Object/Menu'), 0)

Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Attendance/Attendance_Menu'), 5)

if (Mobile.verifyElementVisible(findTestObject('XXXXXXXXXXXX/Mobile Part/Attendance/AttendanceScreen-End Button'), 5, FailureHandling.OPTIONAL)) {
    Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Attendance/AttendanceScreen-End Button'), 0)

    end = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/Attendance/EndTime_Field'), 0)

    end_time = end.replaceAll('\\s', '')

    endtime = end_time.substring(10)

    println('endtime : ' + endtime)

    GlobalVariable.end_time = endtime

    String exlpath = 'DDF\\VBL\\Mobile Input data\\Mobile Input data.xlsx'

    String Sheetname = 'Attendance'

    workbook01 = ExcelKeywords.getWorkbook(exlpath)

    sheet1 = ExcelKeywords.getExcelSheet(workbook01, Sheetname)

    ExcelKeywords.setValueToCellByIndex(sheet1, 1, 4, endtime)

    ExcelKeywords.saveWorkbook(exlpath, workbook01)
}

status = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/Attendance/Status_Field'), 0)

Mobile.verifyMatch(status, findTestData('VBL_Mobile Input Data/Attendance').getValue('Status', 1), false, FailureHandling.STOP_ON_FAILURE)

println('Once click Attendance End Button, Status changed into "Complete" properly')

Mobile.takeScreenshot()

//Mobile.callTestCase(findTestCase('VBL_Mobile/Re-Usables/Sync'), [:], FailureHandling.CONTINUE_ON_FAILURE)
//
//Mobile.callTestCase(findTestCase('VBL_Mobile/Re-Usables/HHT_Transaction_DB'), [:], FailureHandling.STOP_ON_FAILURE)

