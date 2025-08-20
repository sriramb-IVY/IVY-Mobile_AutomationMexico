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

//Mobile.startApplication(GlobalVariable.APKFile, false)
//
//Mobile.tap(findTestObject('Mobile/Common/Btn_Menu'), 0)
//
//not_run: Mobile.callTestCase(findTestCase('Product_Mobile/Common/Mobile_Login/VanSeller_Login-With Reset'), [:], FailureHandling.STOP_ON_FAILURE)
//
//GlobalVariable.RetailerName = 'Dretailer5'
//
//Mobile.tap(findTestObject('Mobile/MainMenu/Menu_Trade Coverage'), 0)
//
//Mobile.tap(findTestObject('Mobile/Common/Icon_Search'), 5)
//
//Mobile.setText(findTestObject('Mobile/Common/Field_Search_EnterText'), GlobalVariable.RetailerName,
//    5)
//
//Mobile.tap(findTestObject('Mobile/TradeCoverage/First_Retailer_Selection_From_List'), 5)
//
//Mobile.tap(findTestObject('Mobile/Common/Btn_StartVisit'), 5)
//
//if (Mobile.verifyElementVisible(findTestObject('Object Repository/Mobile/Common/Popup_Title_LocationValidation'),
//    5, FailureHandling.OPTIONAL)) {
//    Mobile.tap(findTestObject('Mobile/Common/Btn_YES'), 5)
//}
//
//Mobile.delay(6)
//
//Mobile.swipe(0, 150, 0, 400, FailureHandling.OPTIONAL)
//Mobile.startApplication(GlobalVariable.APKFile, false)
//
//Mobile.tap(findTestObject('Mobile/Common/Btn_Menu'), 0)
//
//GlobalVariable.RetailerName = findTestData('Mobile Input Data/CallAnalysis').getValue('Retailer_Name', 1)
//
//Mobile.tap(findTestObject('Mobile/MainMenu/Menu_Trade Coverage'), 0)
//
//Mobile.tap(findTestObject('Mobile/Common/Icon_Search'), 5)
//
//Mobile.setText(findTestObject('Mobile/Common/Field_Search_EnterText'), GlobalVariable.RetailerName, 
//    5)
//
//Mobile.tap(findTestObject('Mobile/TradeCoverage/First_Retailer_Selection_From_List'), 5)
//
//Mobile.tap(findTestObject('Mobile/Common/Btn_StartVisit'), 5)
//
//if (Mobile.verifyElementVisible(findTestObject('Object Repository/Mobile/Common/Popup_Title_LocationValidation'), 
//    5, FailureHandling.OPTIONAL)) {
//    Mobile.tap(findTestObject('Mobile/Common/Btn_YES'), 5)
//}
//
//Mobile.delay(6)
//
//Mobile.swipe(0, 150, 0, 400, FailureHandling.OPTIONAL)
//
//not_run: Mobile.tap(findTestObject('Mobile/Common/Icon_Back'), 0)
//
//not_run: Mobile.delay(3)
//
//not_run: Mobile.swipe(0, 150, 0, 400, FailureHandling.OPTIONAL)
if (Mobile.verifyElementExist(findTestObject('Mobile/Store_Actvy/Menu-EmptiesDeposit'), 2, FailureHandling.OPTIONAL)) {
    'Collection menu visible'
} else {
    Mobile.callTestCase(findTestCase('Product_Mobile/Common/Mobile_Login/Relaunch the app'), [:], FailureHandling.CONTINUE_ON_FAILURE)

    Mobile.tap(findTestObject('Mobile/Common/Btn_Menu'), 0)

    GlobalVariable.RetailerName = findTestData('Mobile Input Data/CallAnalysis').getValue('Retailer_Name', 1)

    Mobile.tap(findTestObject('Mobile/MainMenu/Menu_Trade Coverage'), 0)

    Mobile.tap(findTestObject('Mobile/Common/Icon_Search'), 5)

    Mobile.setText(findTestObject('Mobile/Common/Field_Search_EnterText'), GlobalVariable.RetailerName, 5)

    Mobile.tap(findTestObject('Mobile/TradeCoverage/First_Retailer_Selection_From_List'), 5)

    Mobile.tap(findTestObject('Mobile/Common/Btn_StartVisit'), 5)

    if (Mobile.verifyElementVisible(findTestObject('Object Repository/Mobile/Common/Popup_Title_LocationValidation'), 5, FailureHandling.OPTIONAL)) {
        Mobile.tap(findTestObject('Mobile/Common/Btn_YES'), 5)
    }
    
    Mobile.delay(6)

    Mobile.swipe(0, 150, 0, 400, FailureHandling.OPTIONAL)
}

Mobile.tap(findTestObject('Mobile/Store_Actvy/Menu-EmptiesDeposit'), 0)

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



Mobile.callTestCase(findTestCase('Product_Mobile/Common/Call_Analysis'), [:], FailureHandling.STOP_ON_FAILURE)

not_run: Mobile.delay(3)

not_run: Mobile.swipe(0, 150, 0, 400, FailureHandling.OPTIONAL)

not_run: Mobile.tap(findTestObject('Mobile/CallAnalysis/StoreActy_Close Call Btn'), 0)

not_run: Mobile.delay(2)

not_run: Mobile.tap(findTestObject('Mobile/CallAnalysis/Close Call'), 0)

not_run: Mobile.tap(findTestObject('Mobile/Common/Btn_OK'), 0)

Mobile.waitForElementPresent(findTestObject('Mobile/Common/Btn_Menu'), 0)

Mobile.tap(findTestObject('Mobile/Common/Btn_Menu'), 0)

Mobile.tap(findTestObject('Mobile/Common/Global_SellerMenuName_Selection'), 5)

if (Mobile.verifyElementVisible(findTestObject('Mobile/Attendance/Btn_END'), 5, FailureHandling.OPTIONAL)) {
    Mobile.tap(findTestObject('Mobile/Attendance/Btn_END'), 0)
}

not_run: Mobile.tap(findTestObject('Mobile/Common/Btn_Menu'), 0)

not_run: WebUI.callTestCase(findTestCase('Product_Mobile/Sync/SY_01_02'), [:], FailureHandling.STOP_ON_FAILURE)

