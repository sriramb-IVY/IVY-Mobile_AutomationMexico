import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import internal.GlobalVariable as GlobalVariable
import java.awt.Robot as Robot
import java.awt.event.KeyEvent as KeyEvent
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import org.apache.commons.lang.RandomStringUtils as RandomStringUtils
import com.kms.katalon.core.mobile.keyword.internal.MobileDriverFactory as MobileDriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import io.appium.java_client.AppiumDriver as AppiumDriver
import java.sql.Connection as Connection
import java.sql.DriverManager as DriverManager
import java.sql.ResultSet as ResultSet
import java.sql.Statement as Statement
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.keyword.excel.ExcelKeywords as ExcelKeywords

//Mobile.callTestCase(findTestCase('Product_Mobile/Common/Mobile_Login/VanSeller_Login-With Reset'), [:], FailureHandling.STOP_ON_FAILURE)
not_run: Mobile.delay(8, FailureHandling.STOP_ON_FAILURE)

not_run: Mobile.tap(findTestObject('VBL/AssetMaster/Image_Button'), 5, FailureHandling.OPTIONAL)

//Mobile.startApplication(GlobalVariable.APK_File, false)

Mobile.delay(2, FailureHandling.STOP_ON_FAILURE)

Mobile.tap(findTestObject('Mobile/Common/Btn_Menu'), 0)

Mobile.delay(2, FailureHandling.STOP_ON_FAILURE)

Mobile.tap(findTestObject('Object Repository/Mobile/New Retailer/New_Retailer'), 0, FailureHandling.OPTIONAL)

Mobile.delay(3, FailureHandling.STOP_ON_FAILURE)

long Code1 = Math.random() * 100

Codeno = Long.toString(Code1)

println(Codeno)

String StoreName = 'VANSELLER' + Codeno

println(StoreName)

GlobalVariable.StoreName = StoreName

exlpath = 'DDF\\Mobile Input data\\Mobile Input data.xlsx'

Sheetname = 'NewRetailer'

workbook01 = ExcelKeywords.getWorkbook(exlpath)

sheet1 = ExcelKeywords.getExcelSheet(workbook01, Sheetname)

ExcelKeywords.setValueToCellByIndex(sheet1, 1, 0, StoreName)

ExcelKeywords.saveWorkbook(exlpath, workbook01)

Mobile.verifyElementVisible(findTestObject('Object Repository/Mobile/New Retailer/Store Name'), 0)

Mobile.tap(findTestObject('Object Repository/Mobile/New Retailer/Store Name'), 0)

Mobile.hideKeyboard(FailureHandling.OPTIONAL)

Mobile.setText(findTestObject('Object Repository/Mobile/New Retailer/Store Name'), StoreName, 0)

Mobile.delay(2, FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

Mobile.verifyElementVisible(findTestObject('Object Repository/Mobile/New Retailer/Address field'), 0)

//
//Mobile.tap(findTestObject('Object Repository/Mobile/New Retailer/Address field'), 0)
//
//Mobile.delay(2, FailureHandling.STOP_ON_FAILURE)
//
//Mobile.setText(findTestObject('Object Repository/Mobile/New Retailer/Address text field'), findTestData(
//        'Mobile Input Data/NewRetailer').getValue('Address', 1), 0, FailureHandling.OPTIONAL)
//
//
//Mobile.delay(2, FailureHandling.STOP_ON_FAILURE)


Mobile.verifyElementVisible(findTestObject('Object Repository/Mobile/New Retailer/Channel'), 0)

Mobile.tap(findTestObject('Object Repository/Mobile/New Retailer/Channel'), 0)

Mobile.delay(2, FailureHandling.STOP_ON_FAILURE)

Robot robot = new Robot()

robot.mouseMove(48, 671)

robot.keyPress(KeyEvent.VK_ENTER)

Mobile.tap(findTestObject('Object Repository/Mobile/New Retailer/channel select'), 0)

Mobile.delay(2, FailureHandling.STOP_ON_FAILURE)

Mobile.verifyElementVisible(findTestObject('Object Repository/Mobile/New Retailer/Sun Checkbox'), 0)

Mobile.tap(findTestObject('Object Repository/Mobile/New Retailer/Sun Checkbox'), 0)

//Robot robot = new Robot()
//
//robot.mouseMove(630, 420)
//
//robot.keyPress(KeyEvent.VK_DOWN)
Mobile.hideKeyboard(FailureHandling.OPTIONAL)

Mobile.verifyElementVisible(findTestObject('Object Repository/Mobile/New Retailer/wk1 Checkbox'), 0)

Mobile.tap(findTestObject('Object Repository/Mobile/New Retailer/wk1 Checkbox'), 0)

robot.mouseMove(630, 420)

robot.keyPress(KeyEvent.VK_DOWN)

Mobile.delay(5, FailureHandling.STOP_ON_FAILURE)

Mobile.verifyElementVisible(findTestObject('Object Repository/Mobile/New Retailer/Contact_person_name_field'), 0)

Mobile.tap(findTestObject('Object Repository/Mobile/New Retailer/Contact_person_name_field'), 0)

Mobile.hideKeyboard(FailureHandling.OPTIONAL)

Mobile.setText(findTestObject('Object Repository/Mobile/New Retailer/Contact_person_name_field'), findTestData('Mobile Input Data/NewRetailer').getValue('Contactperson_name', 1), 0)

//robot.mouseMove(630, 420)
//
//robot.keyPress(KeyEvent.VK_DOWN)
Mobile.delay(2, FailureHandling.STOP_ON_FAILURE)

GlobalVariable.label = 'Payment'

Mobile.scrollToText(GlobalVariable.label)

//Mobile.tap(findTestObject('Object Repository/Mobile/New Retailer/Phone No'), 0)
//
//Mobile.hideKeyboard(FailureHandling.OPTIONAL)
//Mobile.setText(findTestObject('Object Repository/Mobile/New Retailer/Address text field'), findTestData('Mobile Input Data/NewRetailer').getValue(
//	'PhoneNo', 1), 0)
//Mobile.sendKeys(findTestObject('Object Repository/Mobile/New Retailer/Address text field'), findTestData('Mobile Input Data/NewRetailer').getValue(
//	'PhoneNo', 1), 0)
//Robot robot = new Robot()
robot.mouseMove(630, 420)

robot.keyPress(KeyEvent.VK_DOWN)

Mobile.delay(5, FailureHandling.STOP_ON_FAILURE)

//Mobile.tap(findTestObject('Object Repository/Mobile/New Retailer/Route field'), 0)
//
////Mobile.delay(2, FailureHandling.STOP_ON_FAILURE)
//
//robot.mouseMove(630, 420)
//
//robot.keyPress(KeyEvent.VK_DOWN)
//
//Mobile.delay(5, FailureHandling.STOP_ON_FAILURE)
//
//Mobile.hideKeyboard(FailureHandling.OPTIONAL)
//
//Mobile.tap(findTestObject('Object Repository/Mobile/New Retailer/Dhivroute'), 0)
//
//robot.mouseMove(630, 420)
//
//robot.keyPress(KeyEvent.VK_DOWN)
Mobile.delay(5, FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

Mobile.verifyElementVisible(findTestObject('Object Repository/Mobile/New Retailer/SAVE button'), 0)

Mobile.tap(findTestObject('Object Repository/Mobile/New Retailer/SAVE button'), 0)

Mobile.takeScreenshot()

AppiumDriver driver = MobileDriverFactory.getDriver()

Save_Successfully_popup = driver.findElementByXPath('//android.widget.Toast[1]').getText()

KeywordUtil.logInfo(Save_Successfully_popup)

Mobile.verifyMatch(Save_Successfully_popup, findTestData('Mobile Input Data/NewRetailer').getValue('Popup', 1), false)

Mobile.takeScreenshot()

not_run: Mobile.delay(4, FailureHandling.STOP_ON_FAILURE)

not_run: Mobile.tap(findTestObject('Mobile/Common/Btn_Menu'), 3)

not_run: Mobile.delay(2, FailureHandling.STOP_ON_FAILURE)

not_run: Mobile.tap(findTestObject('Mobile/MainMenu/Menu_Sync'), 0)

not_run: Mobile.tap(findTestObject('Mobile/Sync/Btn-SYNCHRONIZATION'), 0)

not_run: Mobile.waitForElementPresent(findTestObject('Mobile/Sync/SyncAlert_Data upload completed Successfully'), 20, FailureHandling.OPTIONAL)

not_run: Mobile.tap(findTestObject('Mobile/Common/Btn_OK'), 20, FailureHandling.OPTIONAL)

not_run: Mobile.callTestCase(findTestCase('Product_Mobile/Common/HHT_Transaction_DB'), [:], FailureHandling.STOP_ON_FAILURE)

not_run: Mobile.callTestCase(findTestCase('Product_Mobile/New Retailer/NewRetailer_DB'), [:], FailureHandling.STOP_ON_FAILURE)

