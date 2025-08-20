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
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import java.text.SimpleDateFormat as SimpleDateFormat
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import java.sql.Connection as Connection
import java.sql.DriverManager as DriverManager
import java.sql.ResultSet as ResultSet
import java.sql.Statement as Statement
import com.kms.katalon.core.mobile.keyword.internal.MobileDriverFactory as MobileDriverFactory
import io.appium.java_client.AppiumDriver as AppiumDriver
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import org.openqa.selenium.WebElement as WebElement

not_run: Mobile.callTestCase(findTestCase('Product_Mobile/Common/Mobile_Login/VanSeller_Login-With Reset'), [:], FailureHandling.STOP_ON_FAILURE)

Mobile.startApplication(GlobalVariable.APKFile, false)

Mobile.tap(findTestObject('Mobile/Common/Btn_Menu'), 70)

Mobile.tap(findTestObject('Object Repository/Mobile/Deviation/Trade Coverage Menu'), 3)

Mobile.tap(findTestObject('Mobile/Common/Icon_Search'), 5)

Mobile.sendKeys(findTestObject('Mobile/Common/Field_Search_EnterText'), findTestData('Mobile Input Data/PhotoCapture').getValue(
        'Retailer', 1))

Mobile.takeScreenshot()

Retailer_name = Mobile.getText(findTestObject('Mobile/Seller Task/Retailer'), 0)

KeywordUtil.logInfo('Retailer_name = ' + Retailer_name)

Mobile.tap(findTestObject('Mobile/Seller Task/Retailer'), 0)

Mobile.tap(findTestObject('Mobile/Seller Task/Start Visit button'), 0)

Mobile.tap(findTestObject('Mobile/Deviation/Location validation yes button'), 5)

Mobile.delay(6, FailureHandling.STOP_ON_FAILURE)

Mobile.swipe(0, 150, 0, 400)

Mobile.tap(findTestObject('Mobile/Store_Actvy/Menu-StoreCheck'), 0)

Mobile.delay(1, FailureHandling.STOP_ON_FAILURE)

Mobile.tap(findTestObject('Mobile/Photo Capture/Photo_Capture_Submenu'), 0)

Mobile.delay(1, FailureHandling.STOP_ON_FAILURE)

Mobile.tap(findTestObject('Mobile/Photo Capture/Photo_Capture_Submenu'), 1, FailureHandling.OPTIONAL)

'User selecting Product type'
Mobile.waitForElementPresent(findTestObject('Mobile/Photo Capture/Select_Product_DD'), 0)

Mobile.verifyElementVisible(findTestObject('Mobile/Photo Capture/Select_Product_DD'), 0)

Mobile.tap(findTestObject('Mobile/Photo Capture/Select_Product_DD'), 0)

GlobalVariable.label = findTestData('Mobile Input Data/PhotoCapture').getValue('Product_Type', 1)

Mobile.tap(findTestObject('Mobile/Photo Capture/ProductType_DD_Value(Global)'), 0)

Mobile.takeScreenshot()

'User selecting Photo type'
Mobile.tap(findTestObject('Mobile/Photo Capture/Select_Photo_DD'), 0)

GlobalVariable.label = findTestData('Mobile Input Data/PhotoCapture').getValue('Photo_Type', 1)

Mobile.tap(findTestObject('Mobile/Photo Capture/PhotoType_DD_Value(Global)'), 0)

Mobile.takeScreenshot()

Mobile.tap(findTestObject('Mobile/Photo Capture/Comments_field'), 0)

Mobile.setText(findTestObject('Mobile/Photo Capture/Comments_field'), findTestData('Mobile Input Data/PhotoCapture').getValue(
        'Comments', 1), 0)

Mobile.hideKeyboard(FailureHandling.OPTIONAL)

Mobile.delay(2, FailureHandling.STOP_ON_FAILURE)

Mobile.tap(findTestObject('Mobile/Photo Capture/Center_Camera_icon'), 0)

Mobile.delay(2, FailureHandling.STOP_ON_FAILURE)

Mobile.tap(findTestObject('Mobile/Photo Capture/Camera_photo_click_btn'), 0)

Mobile.delay(5, FailureHandling.STOP_ON_FAILURE)

Mobile.tap(findTestObject('Mobile/Common/Btn_OK'), 0)

Mobile.delay(2, FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

Mobile.tap(findTestObject('Mobile/Photo Capture/FromDate'), 0)

SimpleDateFormat sdf = new SimpleDateFormat('dd-MM-yyyy')

Calendar c = Calendar.getInstance()

c.setTime(new Date())

c.setTime(new Date())

c.add(Calendar.DATE, 1)

c.add(Calendar.MONTH, 0)

c.add(Calendar.YEAR, 0)

String Future_date = sdf.format(c.getTime())

System.out.println(Future_date)

DateValue = Future_date

String[] sDate = DateValue.split('-')

println(((((sDate[0]) + ';') + (sDate[1])) + ';') + (sDate[2]))

String day = sDate[0]

println(day.substring(0))

println(day.charAt(1))

if (day.startsWith('0') == true) {
	GlobalVariable.label = day.charAt(1)
} else {
	GlobalVariable.label = (sDate[0])
}



Mobile.verifyElementVisible(findTestObject('Mobile/Seller Task/DateField(Global)'), 0)

Mobile.tap(findTestObject('Mobile/Seller Task/DateField(Global)'), 0)

Mobile.tap(findTestObject('Mobile/Common/Btn_OK'), 3)

Mobile.takeScreenshot()

Mobile.tap(findTestObject('Mobile/Photo Capture/ToDate'), 0)

Mobile.verifyElementVisible(findTestObject('Mobile/Seller Task/DateField(Global)'), 0)

Mobile.tap(findTestObject('Mobile/Seller Task/DateField(Global)'), 0)

Mobile.tap(findTestObject('Mobile/Common/Btn_OK'), 3)

Mobile.takeScreenshot()

Mobile.tap(findTestObject('Mobile/Photo Capture/Save_btn_photo_capture'), 0)

Mobile.verifyElementVisible(findTestObject('Mobile/Photo Capture/Saved Successfully'), 0)

Mobile.tap(findTestObject('Mobile/Common/Btn_OK'), 0)

Mobile.takeScreenshot()

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Call_Analysis'), [:], FailureHandling.STOP_ON_FAILURE)

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Sync'), [:], FailureHandling.STOP_ON_FAILURE)

