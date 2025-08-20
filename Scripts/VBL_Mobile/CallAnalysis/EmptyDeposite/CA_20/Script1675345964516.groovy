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
import org.openqa.selenium.WebElement as WebElement
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.chrome.ChromeDriver as ChromeDriver
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import com.kms.katalon.keyword.excel.ExcelKeywords as ExcelKeywords
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint

if (Mobile.verifyElementExist(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/Menu-EmptiesDeposit'), 2, FailureHandling.OPTIONAL)) {
    'EmptiesDeposit menu visible'
} else {
    Mobile.callTestCase(findTestCase('Test Cases/VBL_Mobile/Re-Usables/Mobile_Login/Relaunch APK'), [:], FailureHandling.CONTINUE_ON_FAILURE)

    Mobile.tap(findTestObject('XXXXXXXXXXXX/Re-usable/Mobile Login/Menu'), 0)

    Mobile.callTestCase(findTestCase('VBL_Mobile/Re-Usables/Select_TradeCoverage_Menu'), [:], FailureHandling.STOP_ON_FAILURE)

    GlobalVariable.RetailerName = findTestData('VBL_Mobile Input Data/CallAnalysis').getValue('Retailer_Name', 1)

    Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/MainMenu/Menu_Trade Coverage'), 0)

    Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Reusable Object/Retailer Search Icon'), 5)

    Mobile.setText(findTestObject('XXXXXXXXXXXX/Mobile Part/Reusable Object/Retailer Enter Search Field'), GlobalVariable.RetailerName, 
        5)

    Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/stores click'), 5)

    Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/Start Visit Button'), 5)

    if (Mobile.verifyElementVisible(findTestObject('Object Repository/XXXXXXXXXXXX/Mobile Part/Store_Actvy/Location Validation'), 
        5, FailureHandling.OPTIONAL)) {
        Mobile.tap(findTestObject('Object Repository/XXXXXXXXXXXX/Mobile Part/Store_Actvy/location validtion- YES'), 5)
    }
    
    Mobile.delay(7)

    Mobile.swipe(0, 150, 0, 400, FailureHandling.OPTIONAL)
}

Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/Menu-EmptiesDeposit'), 0)

Mobile.delay(2)

Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/EmptyDeposite/ED-Qty-1st_ProductName'), 0)

Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Reusable Object/Vertical KeyPad-Numbers/One'), 0)

Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/End of the Day/StockProposal/Button-Save'), 0)

//AppiumDriver driver = MobileDriverFactory.getDriver()
//
//Actualtoastmessage = driver.findElementByXPath('//android.widget.Toast[1]').getText()
//
//Mobile.verifyMatch(Actualtoastmessage, findTestData('VBL_Mobile Input Data/CallAnalysis').getValue('Verify', 1), false)
//
//Mobile.takeScreenshot()
//
//println('Empty deposite should be created successfully')
//
//Mobile.tap(findTestObject('Mobile/Common/Icon_Back'), 0)
//
//Mobile.delay(2)

Mobile.tap(findTestObject('Mobile/Common/Btn_OK'), 0)

Mobile.delay(3)

Mobile.swipe(0, 150, 0, 400, FailureHandling.OPTIONAL)

Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/CallAnalysis/StoreActy_Close Call Btn'), 0)

Mobile.delay(2)

if (Mobile.verifyElementVisible(findTestObject('Object Repository/XXXXXXXXXXXX/Mobile Part/Store_Actvy/CallAnalysis/Select Reason for no order'), 
    5, FailureHandling.OPTIONAL)) {
    Mobile.tap(findTestObject('Object Repository/XXXXXXXXXXXX/Mobile Part/Store_Actvy/CallAnalysis/Select Reason for no order'), 
        0)

    Mobile.tap(findTestObject('Object Repository/XXXXXXXXXXXX/Mobile Part/Store_Actvy/CallAnalysis/Non Productive'), 0)
}

Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/CallAnalysis/Close Call'), 0)

Mobile.delay(2)

Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/CallAnalysis/CloseCall_Alert_OK btn'), 0)

Mobile.delay(2)

Mobile.waitForElementPresent(findTestObject('XXXXXXXXXXXX/Mobile Part/Reusable Object/Menu'), 0)

Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Reusable Object/Menu'), 0)

Mobile.delay(2)

Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Attendance/Attendance_Menu'), 5)

if (Mobile.verifyElementVisible(findTestObject('XXXXXXXXXXXX/Mobile Part/Attendance/AttendanceScreen-End Button'), 5, FailureHandling.OPTIONAL)) {
    Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Attendance/AttendanceScreen-End Button'), 0)
}

Mobile.tap(findTestObject('XXXXXXXXXXXX/Re-usable/Mobile Login/Menu'), 0)

not_run: WebUI.callTestCase(findTestCase('VBL_Mobile/Sync/SY_01_02'), [:], FailureHandling.STOP_ON_FAILURE)

