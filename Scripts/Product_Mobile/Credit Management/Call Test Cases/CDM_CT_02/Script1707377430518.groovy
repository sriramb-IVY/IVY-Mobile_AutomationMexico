import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
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
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import org.openqa.selenium.WebElement as WebElement
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.keyword.excel.ExcelKeywords as ExcelKeywords
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import org.openqa.selenium.Keys as Keys

Mobile.startApplication(GlobalVariable.APKFile, false)


Mobile.tap(findTestObject('Mobile/Common/Btn_Menu'), 0)

Mobile.tap(findTestObject('Mobile/MainMenu/Menu_End of the Day'), 0)

Mobile.tap(findTestObject('Mobile/End of the Day/SubMenu_Manual Van Load'), 0)

Mobile.delay(2, FailureHandling.STOP_ON_FAILURE)

Mobile.tap(findTestObject('Mobile/End of the Day/Manual Van Load/Select Distributor'), 0)

Mobile.delay(2, FailureHandling.STOP_ON_FAILURE)

GlobalVariable.label = GlobalVariable.DistributorName

Mobile.tap(findTestObject('Mobile/End of the Day/Manual Van Load/Ditributor_Dropdown_option'), 0)

Mobile.verifyElementExist(findTestObject('Mobile/End of the Day/Manual Van Load/ProductName'), 0)

Mobile.verifyElementVisible(findTestObject('Mobile/End of the Day/Manual Van Load/ProductName'), 0)

Mobile.verifyElementExist(findTestObject('Mobile/End of the Day/Manual Van Load/SIH'), 0)

Mobile.verifyElementVisible(findTestObject('Mobile/End of the Day/Manual Van Load/SIH'), 0)

Mobile.takeScreenshot()

Mobile.tap(findTestObject('Mobile/Common/Icon_Search'), 0)

Mobile.setText(findTestObject('Mobile/Common/Field_Search_EnterText'), findTestData('Mobile Input Data/Todays Plan').getValue(
		'Product', 1), 0)

Mobile.verifyElementVisible(findTestObject('Mobile/End of the Day/Manual Van Load/ProductName'), 0)

Mobile.verifyElementText(findTestObject('Mobile/End of the Day/Manual Van Load/ProductName'), findTestData(
		'Mobile Input Data/Todays Plan').getValue('Product', 1))

Mobile.takeScreenshot()

println('User verified the functionality of the product hierarchy filter icon in the manual van load screen')

Mobile.delay(1)

Mobile.hideKeyboard(FailureHandling.OPTIONAL)

Mobile.tap(findTestObject('Mobile/End of the Day/Manual Van Load/Case'), 2, FailureHandling.OPTIONAL)

Mobile.setText(findTestObject('Mobile/End of the Day/Manual Van Load/Case'), findTestData('Mobile Input Data/Limit_Validations').getValue(
		'CaseQty', 1), 0, FailureHandling.STOP_ON_FAILURE)

Entered_Case_Qty = Mobile.getText(findTestObject('Mobile/End of the Day/Manual Van Load/Case'), 2, FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

Mobile.tap(findTestObject('Mobile/End of the Day/Manual Van Load/Save-btn'), 0)

Mobile.tap(findTestObject('Mobile/Common/Btn_OK'), 0)

AppiumDriver driver = MobileDriverFactory.getDriver()

Actualtoastmessage = driver.findElementByXPath('//android.widget.Toast[1]').getText()

Mobile.verifyMatch(Actualtoastmessage, findTestData('Mobile Input Data/ManualVanLoad').getValue('Verify', 1), false, FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

println('Van Unload should be saved successfully')

Mobile.tap(findTestObject('Mobile/Common/Btn_Menu'), 2, FailureHandling.OPTIONAL)

Mobile.tap(findTestObject('Mobile/MainMenu/Menu_Load Management'), 0)

Mobile.tap(findTestObject('Mobile/LoadManagement/SubMenu-Van Load'), 0)

String pattern = 'MM-dd-yyyy'

SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern)

String currentdate = simpleDateFormat.format(new Date())

println(currentdate)

current_date = currentdate.replaceAll('\\D+', '')

KeywordUtil.logInfo(current_date)

UserID = findTestData('Mobile Input Data/ManualVanLoad').getValue('User_ID', 1)

String Vanload_no = UserID + current_date

KeywordUtil.logInfo(Vanload_no)

GlobalVariable.VanLoad_No = Vanload_no

Mobile.scrollToText(GlobalVariable.VanLoad_No, FailureHandling.OPTIONAL)

GlobalVariable.VanLoad_No = Mobile.getText(findTestObject('Mobile/LoadManagement/Vanload/Vanload_No(contains)'),
		0, FailureHandling.STOP_ON_FAILURE)

Mobile.tap(findTestObject('Mobile/LoadManagement/Vanload/Vanload_No(contains)'), 0)

Mobile.tap(findTestObject('Mobile/Common/Icon_Search'), 0)

GlobalVariable.ProductName = findTestData('Mobile Input Data/Todays Plan').getValue('Product', 1)

Mobile.setText(findTestObject('Mobile/Common/Field_Search_EnterText'), GlobalVariable.ProductName,
		0)

Mobile.verifyElementVisible(findTestObject('Mobile/LoadManagement/Vanload/ProductName'), 5, FailureHandling.STOP_ON_FAILURE)

Mobile.verifyElementText(findTestObject('Mobile/LoadManagement/Vanload/ProductName'), GlobalVariable.ProductName,
		FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

Mobile.tap(findTestObject('Mobile/Common/Icon_Back'), 0)

Mobile.scrollToText(GlobalVariable.VanLoad_No, FailureHandling.OPTIONAL)

Mobile.tap(findTestObject('Mobile/LoadManagement/Vanload/Global_RadioIcon'), 3, FailureHandling.OPTIONAL)

Mobile.tap(findTestObject('Mobile/LoadManagement/Vanload/VanLoadScreen-Accept Btn'), 3, FailureHandling.OPTIONAL)

Mobile.tap(findTestObject('Mobile/LoadManagement/Vanload/OK Btn'), 3, FailureHandling.OPTIONAL)

Actualtoastmessage = driver.findElementByXPath('//android.widget.Toast[1]').getText()

println(Actualtoastmessage)

Mobile.verifyMatch(Actualtoastmessage, findTestData('Mobile Input Data/VanLoad').getValue('Alerts', 1), false)

Mobile.takeScreenshot()

println('Verified : that User is able to accept the van load')


