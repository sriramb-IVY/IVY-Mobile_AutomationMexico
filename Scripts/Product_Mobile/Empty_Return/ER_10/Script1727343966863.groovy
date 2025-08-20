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
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import com.kms.katalon.core.mobile.keyword.internal.MobileDriverFactory as MobileDriverFactory
import io.appium.java_client.android.AndroidDriver as AndroidDriver
//import io.appium.java_client.MobileElement as MobileElement
import io.appium.java_client.android.nativekey.AndroidKey as AndroidKey
import io.appium.java_client.android.nativekey.KeyEvent as KeyEvent
import io.appium.java_client.AppiumDriver as AppiumDriver

Mobile.comment('Stock reconcilation toast alert')

Mobile.tap(findTestObject('Mobile/Mobile Part/End of the Day/Stock_Reconciliation/End_trip_btn'), 0)

AppiumDriver driver = MobileDriverFactory.getDriver()

Stock_Reconciliation_alert = driver.findElementByXPath('//android.widget.Toast[1]').getText()

KeywordUtil.logInfo('Stock Alert msg :' + Stock_Reconciliation_alert)

Mobile.verifyMatch(Stock_Reconciliation_alert, findTestData('Mobile Input Data/Empty_Return').getValue('Alert_Msg', 2), false)

Mobile.comment('Stock reconcilation Authentication screen')

Mobile.tap(findTestObject('Mobile/Mobile Part/End of the Day/Stock_Reconciliation/Stock_Reconciliation_menu'), 0)

Mobile.verifyElementExist(findTestObject('Mobile/Mobile Part/End of the Day/Stock_Reconciliation/Authentication_header'), 0)

Mobile.verifyElementExist(findTestObject('Mobile/Mobile Part/End of the Day/Stock_Reconciliation/Username_field'), 0)

Mobile.verifyElementExist(findTestObject('Mobile/Mobile Part/End of the Day/Stock_Reconciliation/Password_field'), 0)

Mobile.verifyElementExist(findTestObject('Mobile/Mobile Part/End of the Day/Stock_Reconciliation/Validate_btn'), 0)

Mobile.verifyElementExist(findTestObject('Mobile/Mobile Part/End of the Day/Stock_Reconciliation/Cancel_btn'), 0)

Mobile.takeScreenshot()

Mobile.comment('Save btn ')

Mobile.tap(findTestObject('Mobile/Mobile Part/End of the Day/Stock_Reconciliation/Validate_btn'), 0)

US_Validate_msg = driver.findElementByXPath('//android.widget.Toast[1]').getText()

Mobile.takeScreenshot()

KeywordUtil.logInfo('Username enter msg :' + US_Validate_msg)

Mobile.verifyMatch(US_Validate_msg, findTestData('Mobile Input Data/Empty_Return').getValue('Alert_Msg', 3), false)

Mobile.tap(findTestObject('Mobile/Mobile Part/End of the Day/Stock_Reconciliation/Username_field'), 0)

Mobile.setText(findTestObject('Mobile/Mobile Part/End of the Day/Stock_Reconciliation/Username_field'), findTestData('Mobile Input Data/Empty_Return').getValue('Supervisor', 1), 0)

Mobile.tap(findTestObject('Object Repository/Mobile/Mobile Part/End of the Day/Stock_Reconciliation/Password_field'), 0)

Mobile.setText(findTestObject('Object Repository/Mobile/Mobile Part/End of the Day/Stock_Reconciliation/Password_field'), findTestData('Mobile Input Data/Empty_Return').getValue('Supervisor', 3), 0)

Mobile.tap(findTestObject('Mobile/Mobile Part/End of the Day/Stock_Reconciliation/Validate_btn'), 0)

Invalid_Username_alert = driver.findElementByXPath('//android.widget.Toast[1]').getText()

Mobile.takeScreenshot()

KeywordUtil.logInfo('Invalid user msg :' + Invalid_Username_alert)

Mobile.verifyMatch(Invalid_Username_alert, findTestData('Mobile Input Data/Empty_Return').getValue('Alert_Msg', 4), false)

Mobile.comment('Stock reconcilation Authentication screen cancel and save btn functionality')

Mobile.tap(findTestObject('Mobile/Mobile Part/End of the Day/Stock_Reconciliation/Stock_Reconciliation_menu'), 0)

Mobile.tap(findTestObject('Object Repository/Mobile/Mobile Part/End of the Day/Stock_Reconciliation/Cancel_btn'), 0)

Mobile.verifyElementNotExist(findTestObject('Object Repository/Mobile/Mobile Part/End of the Day/Stock_Reconciliation/Authentication_header'), 0)

Mobile.takeScreenshot()

Mobile.tap(findTestObject('Mobile/Mobile Part/End of the Day/Stock_Reconciliation/Stock_Reconciliation_menu'), 0)

Mobile.delay(2)

Mobile.tap(findTestObject('Mobile/Mobile Part/End of the Day/Stock_Reconciliation/Username_field'), 0)

Mobile.setText(findTestObject('Mobile/Mobile Part/End of the Day/Stock_Reconciliation/Username_field'), findTestData('Mobile Input Data/Empty_Return').getValue('Supervisor', 2), 0)

Mobile.tap(findTestObject('Object Repository/Mobile/Mobile Part/End of the Day/Stock_Reconciliation/Password_field'), 0)

Mobile.setText(findTestObject('Object Repository/Mobile/Mobile Part/End of the Day/Stock_Reconciliation/Password_field'), findTestData('Mobile Input Data/Empty_Return').getValue('Supervisor', 3), 0)

Mobile.tap(findTestObject('Mobile/Mobile Part/End of the Day/Stock_Reconciliation/Validate_btn'), 0)

Authentication_msg = driver.findElementByXPath('//android.widget.Toast[1]').getText()

Mobile.takeScreenshot()

KeywordUtil.logInfo('Authentication  msg :' + Authentication_msg)

Mobile.verifyMatch(Authentication_msg, findTestData('Mobile Input Data/Empty_Return').getValue('Alert_Msg', 6), false)

Mobile.takeScreenshot()

'Search and get SIH'
Mobile.tap(findTestObject('Object Repository/Mobile/Mobile Part/End of the Day/Stock_Reconciliation/search_icon'), 0)

Mobile.setText(findTestObject('Mobile/Mobile Part/End of the Day/Van Unload/New/Product_search_field'), findTestData('Mobile Input Data/Empty_Return').getValue('SKU_Name', 1), 0)

Product1_Stock_case_qty = Mobile.getText(findTestObject('Object Repository/Mobile/Mobile Part/End of the Day/Stock_Reconciliation/Case_Enter_field'), 0)

KeywordUtil.logInfo('Case qty first prodcut :' + Product1_Stock_case_qty)

Product1_Stock_Piece_qty = Mobile.getText(findTestObject('Object Repository/Mobile/Mobile Part/End of the Day/Stock_Reconciliation/Piece_Enter_field'), 0)

KeywordUtil.logInfo('Piece qty first prodcut :' + Product1_Stock_Piece_qty)

Product1_Stock_SIH_qty = Mobile.getText(findTestObject('Object Repository/Mobile/Mobile Part/End of the Day/Stock_Reconciliation/SIH_get'), 0)

KeywordUtil.logInfo('SIH qty first prodcut :' + Product1_Stock_SIH_qty)

Mobile.tap(findTestObject('Mobile/Mobile Part/End of the Day/Van Unload/New/Product_search_field'), 0)

Mobile.clearText(findTestObject('Mobile/Mobile Part/End of the Day/Van Unload/New/Product_search_field'), 0)

Mobile.setText(findTestObject('Mobile/Mobile Part/End of the Day/Van Unload/New/Product_search_field'), findTestData('Mobile Input Data/Empty_Return').getValue('SKU_Name', 1), 0)

Product2_Stock_case_qty = Mobile.getText(findTestObject('Object Repository/Mobile/Mobile Part/End of the Day/Stock_Reconciliation/Case_Enter_field'), 0)

KeywordUtil.logInfo('Case qty Second prodcut :' + Product2_Stock_case_qty)

Product2_Stock_Piece_qty = Mobile.getText(findTestObject('Object Repository/Mobile/Mobile Part/End of the Day/Stock_Reconciliation/Piece_Enter_field'), 0)

KeywordUtil.logInfo('Piece qty Second prodcut :' + Product2_Stock_Piece_qty)

Product2_Stock_SIH_qty = Mobile.getText(findTestObject('Object Repository/Mobile/Mobile Part/End of the Day/Stock_Reconciliation/SIH_get'), 0)

KeywordUtil.logInfo('SIH qty Second prodcut :' + Product2_Stock_SIH_qty)

'Save btn fun for stock reconcilation'
Mobile.tap(findTestObject('Mobile/Mobile Part/End of the Day/Stock_Reconciliation/Piece_Enter_field'), 0)

Mobile.clearText(findTestObject('Mobile/Mobile Part/End of the Day/Stock_Reconciliation/Piece_Enter_field'), 0)

Mobile.setText(findTestObject('Mobile/Mobile Part/End of the Day/Stock_Reconciliation/Piece_Enter_field'), '9', 0)

Mobile.verifyElementExist(findTestObject('Mobile/Mobile Part/End of the Day/Stock_Reconciliation/Exceeds_actual_quantity_msg'), 0)

Exceed_alert = Mobile.getText(findTestObject('Mobile/Mobile Part/End of the Day/Stock_Reconciliation/Exceeds_actual_quantity_msg'), 0)

KeywordUtil.logInfo('Exceed alert msg :' + Exceed_alert)

Mobile.takeScreenshot()

Mobile.tap(findTestObject('Object Repository/Mobile/Common/Btn_OK'), 0)

Mobile.clearText(findTestObject('Mobile/Mobile Part/End of the Day/Stock_Reconciliation/Piece_Enter_field'), 0)

Mobile.setText(findTestObject('Mobile/Mobile Part/End of the Day/Stock_Reconciliation/Piece_Enter_field'), Product2_Stock_Piece_qty, 0)

Mobile.tap(findTestObject('Object Repository/Mobile/Empty_Return/Save_btn'), 0)

Mobile.verifyElementExist(findTestObject('Object Repository/Mobile/Mobile Part/End of the Day/Stock_Reconciliation/Do_you_want_to_save_msg'), 0)

Mobile.tap(findTestObject('Object Repository/Mobile/Common/Btn_OK'), 0)

Mobile.verifyElementExist(findTestObject('Object Repository/Mobile/Empty_Return/Saved_Successfully_msg'), 0)

Mobile.takeScreenshot()

Mobile.tap(findTestObject('Mobile/Common/Btn_Menu'), 5)

Mobile.tap(findTestObject('Mobile/MainMenu/Menu_Trade Coverage'), 5)

End_trip_alert = driver.findElementByXPath('//android.widget.Toast[1]').getText()

Mobile.takeScreenshot()

KeywordUtil.logInfo('End trip alert :' + End_trip_alert)

Mobile.verifyMatch(End_trip_alert, findTestData('Mobile Input Data/Empty_Return').getValue('Alert_Msg', 5), false)

