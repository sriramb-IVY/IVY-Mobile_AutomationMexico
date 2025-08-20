import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.chrome.ChromeDriver as ChromeDriver
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.keyword.excel.ExcelKeywords as ExcelKeywords
import internal.GlobalVariable as GlobalVariable
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import org.openqa.selenium.Keys as Keys
import java.sql.Connection as Connection
import java.sql.DriverManager as DriverManager
import java.sql.ResultSet as ResultSet
import java.sql.Statement as Statement
import org.openqa.selenium.By as By
import org.openqa.selenium.WebElement as WebElement
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import java.text.SimpleDateFormat as SimpleDateFormat
import java.math.BigDecimal as BigDecimal
import java.math.RoundingMode as RoundingMode
import java.text.DecimalFormat as DecimalFormat
import com.kms.katalon.core.mobile.keyword.internal.MobileDriverFactory as MobileDriverFactory
import io.appium.java_client.AppiumDriver as AppiumDriver
//import io.appium.java_client.MobileElement as MobileElement
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import java.text.DateFormat as DateFormat
import java.util.Date as Date

not_run: Mobile.startApplication(GlobalVariable.APK_File, false, FailureHandling.STOP_ON_FAILURE)

not_run: Mobile.delay(15)

'Completed the Sales return'
if (Mobile.verifyElementExist(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/Menu-SalesReturn'), 2, FailureHandling.OPTIONAL)) {
    'SalesReturn menu visible'
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

Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/Menu-SalesReturn'), 0)

Mobile.delay(2)

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Search_Product'), [:], FailureHandling.STOP_ON_FAILURE)

GlobalVariable.ProductName = findTestData('VBL_Web Input Data/Stock_Cash_Reconciliation').getValue('SKU', 1)

//GlobalVariable.S_Sku_Name = GlobalVariable.ProductName
Mobile.setText(findTestObject('XXXXXXXXXXXX/Mobile Part/Reusable Object/Product Enter Search'), GlobalVariable.ProductName, 
    0)

Mobile.delay(2)

Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/SalesReturn/Piece-1st'), 0)

Mobile.setText(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/SalesReturn/Return-InvoiceNo'), findTestData('VBL_Mobile Input Data/CallAnalysis').getValue(
        'InvoiceNo', 1), 0, FailureHandling.OPTIONAL)

Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/SalesReturn/Return-Select Reason'), 0)

Mobile.delay(2)

Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/SalesReturn/EXCESS-option'), 0)

Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/SalesReturn/Return-Piece'), 0, FailureHandling.STOP_ON_FAILURE)

GlobalVariable.keypadValue = findTestData('VBL_Web Input Data/Stock_Cash_Reconciliation').getValue('Return_Qty', 1)

GlobalVariable.PieceQty = GlobalVariable.keypadValue

Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Reusable Object/Vertical KeyPad-Numbers/Global-number_keypad'), 0)

'Replacement Tab'
Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/SalesReturn/Replace/Tab_REPLACE'), 0)

Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/SalesReturn/Replace/Search Product Name'), 0)

Mobile.setText(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/SalesReturn/Replace/Search Product Name'), GlobalVariable.ProductName, 
    0)

Boundary = Mobile.getAttribute(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/SalesReturn/Replace/Search Product Name'), 
    'bounds', 0)

String[] bound = Boundary.split(',')

String y = bound[2]

Y_Val = y.replace(']', '')

Y_Value = (Double.parseDouble(Y_Val) + 25)

Mobile.tapAtPosition(50, Y_Value, FailureHandling.STOP_ON_FAILURE)

Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/SalesReturn/Replace/Piece_Field'), 0)

GlobalVariable.keypadValue = findTestData('VBL_Web Input Data/Stock_Cash_Reconciliation').getValue('Replacement_Qty', 1)

Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Reusable Object/Vertical KeyPad-Numbers/Global-number_keypad'), 0)

Mobile.tap(findTestObject('Mobile/Common/Btn_OK'), 0)

Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/SalesReturn/Replace/Done_Btn'), 0)

Mobile.delay(2)

Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/SalesReturn/SR-Next btn'), 0)

Mobile.delay(2)

Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/SalesReturn/SR-Save btn'), 0)

Mobile.delay(2)

Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/SalesReturn/SR-Ok'), 10, FailureHandling.OPTIONAL)

Mobile.verifyElementVisible(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/SalesReturn/SR-Saved Successfully'), 0)

Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/SalesReturn/SR-Ok'), 10, FailureHandling.OPTIONAL)

Mobile.takeScreenshot()

println('Sales return created successfully')

Mobile.delay(3)

not_run: Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/SalesReturn/SR-Ok'), 0, FailureHandling.OPTIONAL)

Mobile.delay(3)

Mobile.swipe(0, 150, 0, 400, FailureHandling.OPTIONAL)

