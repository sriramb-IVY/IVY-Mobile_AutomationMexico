import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
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
import com.kms.katalon.core.mobile.keyword.internal.MobileDriverFactory as MobileDriverFactory
import io.appium.java_client.AppiumDriver as AppiumDriver
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil

not_run: Mobile.callTestCase(findTestCase('VBL_Mobile/Re-Usables/Mobile_Login/VanSeller_Login-With Reset'), [:], FailureHandling.STOP_ON_FAILURE)

Mobile.comment('Validation for start trip alert')

Mobile.tap(findTestObject('Mobile/Common/Global_SellerMenuName_Selection'), 0)

AppiumDriver driver = MobileDriverFactory.getDriver()

String Start_trip_popup = driver.findElementByXPath('//android.widget.Toast[1]').getText()

Mobile.takeScreenshot()

KeywordUtil.logInfo(Start_trip_popup)

Alert_Text = findTestData('VBL_Web Input Data/Stock_Cash_Reconciliation').getValue('Alert_Popup', 1)

Mobile.verifyMatch(Start_trip_popup, Alert_Text, false)

Mobile.tap(findTestObject('Mobile/Common/Btn_Menu'), 0, FailureHandling.OPTIONAL)

Mobile.tap(findTestObject('Mobile/Mobile Part/Seller_3/Cash_Stock_Reconcilation/Menu_Start_Trip'), 0)

Mobile.comment('Validation for user able go to start trip screen')

Mobile.verifyElementExist(findTestObject('Mobile/Mobile Part/Seller_3/Cash_Stock_Reconcilation/Start_Trip_confirm_msg'), 0)

Mobile.verifyElementVisible(findTestObject('Mobile/Mobile Part/Seller_3/Cash_Stock_Reconcilation/Start_Trip_confirm_msg'), 0)

Mobile.takeScreenshot()

Mobile.tap(findTestObject('Mobile/Mobile Part/Seller_3/Cash_Stock_Reconcilation/Start_Trip_yes_btn'), 0)

Mobile.comment('Validation for user able to start the trip')

Mobile.verifyElementExist(findTestObject('Mobile/Mobile Part/Seller_3/Cash_Stock_Reconcilation/Start_Trip_completed_msg'), 0)

Mobile.verifyElementVisible(findTestObject('Mobile/Mobile Part/Seller_3/Cash_Stock_Reconcilation/Start_Trip_completed_msg'), 0)

Mobile.takeScreenshot()

Mobile.tap(findTestObject('Mobile/Mobile Part/Seller_3/Cash_Stock_Reconcilation/Ok_btn'), 0)

Mobile.tap(findTestObject('XXXXXXXXXXXX/Re-usable/Mobile Login/Menu'), 0)

