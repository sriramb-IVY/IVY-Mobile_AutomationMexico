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

'End TRip'
Mobile.scrollToText('End of the day', FailureHandling.OPTIONAL)

Mobile.tap(findTestObject('Mobile/MainMenu/Menu_End of the Day'), 0)

Mobile.comment('Validation for user able see the End btn in End of the day menu')

Mobile.verifyElementExist(findTestObject('Mobile/Mobile Part/Seller_3/Cash_Stock_Reconcilation/Trip_end_btn'), 0)

Mobile.verifyElementVisible(findTestObject('Mobile/Mobile Part/Seller_3/Cash_Stock_Reconcilation/Trip_end_btn'), 0)

Mobile.takeScreenshot()

Mobile.tap(findTestObject('Mobile/Mobile Part/Seller_3/Cash_Stock_Reconcilation/Trip_end_btn'), 0)

Mobile.comment('Validation for user able end the trip')

Mobile.verifyElementExist(findTestObject('Mobile/Mobile Part/Seller_3/Cash_Stock_Reconcilation/Close_Trip_msg'), 0)

Mobile.verifyElementVisible(findTestObject('Mobile/Mobile Part/Seller_3/Cash_Stock_Reconcilation/Close_Trip_msg'), 0)

Mobile.takeScreenshot()

Close_Trip_Day = Mobile.getText(findTestObject('Mobile/Mobile Part/Seller_3/Cash_Stock_Reconcilation/Close_Trip_msg'), 0)

Mobile.tap(findTestObject('Mobile/Mobile Part/Seller_3/Cash_Stock_Reconcilation/Ok_btn'), 0)

Mobile.comment('Validation for trip ended successfully Message')

Mobile.verifyElementExist(findTestObject('Mobile/Mobile Part/Seller_3/Cash_Stock_Reconcilation/End_Trip_successful_msg'), 0)

Mobile.verifyElementVisible(findTestObject('Mobile/Mobile Part/Seller_3/Cash_Stock_Reconcilation/End_Trip_successful_msg'), 0)

Mobile.takeScreenshot()

Mobile.comment('Validation for once trip is ended the Day closed popup should display')

Mobile.tap(findTestObject('Mobile/Mobile Part/Seller_3/Cash_Stock_Reconcilation/Ok_btn'), 0)

Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Reusable Object/Menu'), 0)

Mobile.tap(findTestObject('Mobile/MainMenu/Menu_End of the Day'), 0)

Mobile.pressBack()

Mobile.tap(findTestObject('Mobile/MainMenu/Menu_End of the Day'), 0)

AppiumDriver driver = MobileDriverFactory.getDriver()

String Day_closed_popup = driver.findElementByXPath('//android.widget.Toast[1]').getText()

Mobile.takeScreenshot()

KeywordUtil.logInfo(Day_closed_popup)

Day_End_Text = findTestData('VBL_Web Input Data/Stock_Cash_Reconciliation').getValue('Alert_Popup', 2)

Mobile.verifyMatch(Day_closed_popup, Day_End_Text, false)

