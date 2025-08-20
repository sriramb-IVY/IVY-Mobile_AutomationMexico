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
import org.openqa.selenium.Keys as Keys

Mobile.startApplication(GlobalVariable.APKFile, false)

Mobile.tap(findTestObject('Mobile/Common/Btn_Menu'), 0)


	Mobile.tap(findTestObject('Mobile/Common/Btn_Menu'), 0)

	GlobalVariable.RetailerName = findTestData('Mobile Input Data/Empty_Return').getValue('Retailer', 1)

	GlobalVariable.RetailerName = 'Dret7'

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


Mobile.tap(findTestObject('Mobile/Mobile Part/Store_Actvy/Menu-EmptyDeposit'), 0)

Mobile.delay(2)

Mobile.comment('Validation for Deposit screen and fields')

Mobile.verifyElementExist(findTestObject('Mobile/Empty_Deposit/Deposit_Title'), 5, FailureHandling.STOP_ON_FAILURE)

Mobile.verifyElementExist(findTestObject('Mobile/Common/Btn_Plus'), 5, FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

//Add icon
Mobile.tap(findTestObject('Mobile/Common/Btn_Plus'), 5)

//Deposit create page


//Select deposit radio btn
Mobile.tap(findTestObject('Mobile/Empty_Deposit/DEPOSIT_radio_btn'), 5)

Mobile.tap(findTestObject('Mobile/Empty_Deposit/Reason_Enter_Dropdown'), 5)

Mobile.setText(findTestObject('Mobile/Empty_Deposit/Reason_Enter_Dropdown'), findTestData('Mobile Input Data/Empty_Deposit').getValue('Reason', 1), 0)

Mobile.sendKeys(findTestObject('Mobile/Empty_Deposit/Reason_Enter_Dropdown'), Keys.chord(Keys.DOWN, Keys.ENTER))

Mobile.delay(3)



