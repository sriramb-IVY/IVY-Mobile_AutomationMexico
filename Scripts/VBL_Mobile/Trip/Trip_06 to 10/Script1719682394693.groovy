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
import java.sql.Connection as Connection
import java.sql.DriverManager as DriverManager
import java.sql.ResultSet as ResultSet
import java.sql.Statement as Statement
import java.text.SimpleDateFormat as SimpleDateFormat
import com.kms.katalon.core.mobile.keyword.internal.MobileDriverFactory as MobileDriverFactory
import io.appium.java_client.AppiumDriver as AppiumDriver
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import org.openqa.selenium.WebElement as WebElement
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.keyword.excel.ExcelKeywords as ExcelKeywords
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil

//'End attendence'
//not_run: for (a = 0; a <= 3; a++) {
//    if (Mobile.verifyElementExist(findTestObject('XXXXXXXXXXXX/Mobile Part/Attendance/Attendance_Menu'), 2, FailureHandling.OPTIONAL)) {
//        'Trade Coverage Menu Visible'
//        break
//    } else if (Mobile.verifyElementExist(findTestObject('XXXXXXXXXXXX/Re-usable/Mobile Login/Menu'), 2, FailureHandling.OPTIONAL)) {
//        Mobile.tap(findTestObject('XXXXXXXXXXXX/Re-usable/Mobile Login/Menu'), 0)
//    } else {
//        Mobile.callTestCase(findTestCase('Test Cases/VBL_Mobile/Re-Usables/Mobile_Login/Relaunch APK'), [:], FailureHandling.CONTINUE_ON_FAILURE)
//
//        Mobile.tap(findTestObject('XXXXXXXXXXXX/Re-usable/Mobile Login/Menu'), 0)
//    }
//}
//
//not_run: Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Attendance/Attendance_Menu'), 0)
//
//not_run: Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Attendance/AttendanceScreen-End Button'), 0)
//
//not_run: Mobile.tap(findTestObject('XXXXXXXXXXXX/Re-usable/Mobile Login/Menu'), 0)
Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/MainMenu/Menu_End of the Day'), 0)

Mobile.verifyElementVisible(findTestObject('Mobile/Trip/Trip_End_btn'), 0)

Mobile.tap(findTestObject('Mobile/Trip/Trip_End_btn'), 0)

AppiumDriver driver = MobileDriverFactory.getDriver()

Actualtoastmessage = driver.findElementByXPath('//android.widget.Toast[1]').getText()

Mobile.takeScreenshot()

Mobile.verifyMatch(Actualtoastmessage, findTestData('VBL_Mobile Input Data/Attendance').getValue('Verify_Text', 6), false, FailureHandling.STOP_ON_FAILURE)

'End Odometer and Attendence'
Mobile.callTestCase(findTestCase('VBL_Mobile/Attendance and Odometer/At_04, OD_03_04'), [:], FailureHandling.STOP_ON_FAILURE)

Mobile.tap(findTestObject('XXXXXXXXXXXX/Re-usable/Mobile Login/Menu'), 0)

Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/MainMenu/Menu_End of the Day'), 0)

Mobile.tap(findTestObject('Mobile/Trip/Trip_End_btn'), 0)

Mobile.verifyElementVisible(findTestObject('Mobile/Trip/You are closing'), 0)

Mobile.verifyElementVisible(findTestObject('Mobile/Trip/CANCEL_Btn'), 0)

Mobile.verifyElementVisible(findTestObject('Mobile/Common/Btn_OK'), 0)

Mobile.tap(findTestObject('Mobile/Trip/CANCEL_Btn'), 0)

Mobile.verifyElementNotExist(findTestObject('Mobile/Trip/You are closing'), 2)

Mobile.tap(findTestObject('Mobile/Trip/Trip_End_btn'), 0)

Mobile.tap(findTestObject('Mobile/Common/Btn_OK'), 0)

Mobile.verifyElementVisible(findTestObject('Mobile/Trip/Trip Ended Successfully. Day closed'), 0)

Mobile.tap(findTestObject('Mobile/Common/Btn_OK'), 0)

Mobile.tap(findTestObject('XXXXXXXXXXXX/Re-usable/Mobile Login/Menu'), 0)

Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/MainMenu/Menu_Trade Coverage'), 0)

Mobile.delay(2)

Mobile.tap(findTestObject('XXXXXXXXXXXX/Re-usable/Mobile Login/Menu'), 0)

Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/MainMenu/Menu_Trade Coverage'), 0)

//AppiumDriver driver = MobileDriverFactory.getDriver()
Actualtoastmessage = driver.findElementByXPath('//android.widget.Toast[1]').getText()

Mobile.takeScreenshot()

Mobile.verifyMatch(Actualtoastmessage, findTestData('VBL_Mobile Input Data/Attendance').getValue('Verify_Text', 4), false, FailureHandling.STOP_ON_FAILURE)

not_run: Mobile.tap(findTestObject('XXXXXXXXXXXX/Re-usable/Mobile Login/Menu'), 0, FailureHandling.OPTIONAL)

