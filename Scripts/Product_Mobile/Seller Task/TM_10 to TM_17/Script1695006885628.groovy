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

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Mobile_Login/VanSeller_Login-With Reset'), [:], FailureHandling.STOP_ON_FAILURE)

not_run: Mobile.startApplication(GlobalVariable.APKFile, false)

not_run: Mobile.tap(findTestObject('Mobile/Common/Btn_Menu'), 70)

//WebUI.callTestCase(findTestCase('Product_Mobile/Common/TradeCoverage(SelectRetailer)'), [('RetailerName') : findTestData('Web Input Data/Task_Management').getValue(
//            'Retailer', 1)], FailureHandling.STOP_ON_FAILURE)
Mobile.tap(findTestObject('Object Repository/Mobile/Deviation/Trade Coverage Menu'), 3)

Mobile.tap(findTestObject('Mobile/Common/Icon_Search'), 5)

Mobile.sendKeys(findTestObject('Mobile/Common/Field_Search_EnterText'), findTestData('Mobile Input Data/Task_Management').getValue(
        'Retailer', 1))

Mobile.takeScreenshot()

Retailer_name = Mobile.getText(findTestObject('Mobile/Seller Task/Retailer'), 0)

KeywordUtil.logInfo('Retailer_name = ' + Retailer_name)

Mobile.tap(findTestObject('Mobile/Seller Task/Retailer'), 0)

Mobile.tap(findTestObject('Mobile/Seller Task/Start Visit button'), 0)

Mobile.tap(findTestObject('Mobile/Deviation/Location validation yes button'), 5)

Mobile.delay(6, FailureHandling.STOP_ON_FAILURE)

Mobile.swipe(0, 150, 0, 400)

Mobile.tap(findTestObject('Mobile/Seller Task/Task Execution Menu'), 3)

'verify user can create task by entering data in the mandatory fields'
Mobile.tap(findTestObject('Mobile/Seller Task/Add ImageButton'), 3)

'verify user can enter task title'
Mobile.sendKeys(findTestObject('Mobile/Seller Task/Task_Title_Field'), findTestData('Mobile Input Data/Task_Management').getValue(
        'Title_Name', 1))

Mobile.tap(findTestObject('Mobile/Seller Task/Description'), 3)

Mobile.takeScreenshot()

Actual_Task_Title = Mobile.getText(findTestObject('Mobile/Seller Task/Task_Title_Field'), 0)

Mobile.verifyMatch(Actual_Task_Title, findTestData('Mobile Input Data/Task_Management').getValue('Title_Name', 1), false)

Mobile.takeScreenshot()

'verify user can enter description'
Mobile.tap(findTestObject('Mobile/Seller Task/Description'), 3)

Mobile.sendKeys(findTestObject('Mobile/Seller Task/Description'), findTestData('Mobile Input Data/Task_Management').getValue(
        'Title_Description', 1))

Mobile.tap(findTestObject('Mobile/Seller Task/Self radio button'), 0)

Actual_Description = Mobile.getText(findTestObject('Mobile/Seller Task/Description'), 0)

Mobile.verifyMatch(Actual_Description, findTestData('Mobile Input Data/Task_Management').getValue('Title_Description', 1), 
    false)

Mobile.takeScreenshot()

'Verify User can\'t pass the past date in the due date field'
Mobile.tap(findTestObject('Mobile/Seller Task/Due Date'), 0)

SimpleDateFormat sdf = new SimpleDateFormat('dd-MM-yyyy')

Calendar c = Calendar.getInstance()

c.setTime(new Date())

c.add(Calendar.DATE, -1)

c.add(Calendar.MONTH, 0)

c.add(Calendar.YEAR, 0)

String Past_date = sdf.format(c.getTime())

System.out.println(Past_date)

DateValue = Past_date

String[] sDate1 = DateValue.split('-')

println(((((sDate1[0]) + ';') + (sDate1[1])) + ';') + (sDate1[2]))

String day1 = sDate1[0]

if (day1.startsWith('0') == true) {
    GlobalVariable.label = day1.charAt(1)
} else {
    GlobalVariable.label = (sDate1[0])
}

//GlobalVariable.label = (sDate1[0])
PastDate_Enable_Property = Mobile.getAttribute(findTestObject('Mobile/Seller Task/Past_Date(Global)'), 
    'enabled', 0)

Mobile.verifyMatch(PastDate_Enable_Property, 'false', false)

Mobile.takeScreenshot()

'Verify User can enter due date'
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

if (day.startsWith('0') == true) {
    GlobalVariable.label = day.charAt(1)
} else {
    GlobalVariable.label = (sDate[0])
}

//GlobalVariable.label = (sDate[0])
Mobile.verifyElementVisible(findTestObject('Mobile/Seller Task/DateField(Global)'), 0)

Mobile.tap(findTestObject('Mobile/Seller Task/DateField(Global)'), 0)

Mobile.tap(findTestObject('Mobile/Common/Btn_OK'), 3)

Mobile.takeScreenshot()

Actual_Date = Mobile.getText(findTestObject('Mobile/Seller Task/Due Date'), 0)

Mobile.verifyMatch(Actual_Date, Future_date, false)

Mobile.takeScreenshot()

'verify (Applicable for) field should be auto populated as Retailer and will be Non-editable'
Mobile.verifyElementVisible(findTestObject('Mobile/Seller Task/Self radio button'), 0)

Mobile.verifyElementChecked(findTestObject('Mobile/Seller Task/Self radio button'), 0)

Mobile.takeScreenshot()

'verify user can take photo'
Mobile.tap(findTestObject('Mobile/Seller Task/TAKE PHOTO icon'), 3)

Mobile.delay(3, FailureHandling.STOP_ON_FAILURE)

Mobile.waitForElementPresent(findTestObject('Mobile/Common/Camera_ShutterButton'), 15, FailureHandling.OPTIONAL)

Mobile.tap(findTestObject('Mobile/Common/Camera_ShutterButton'), 3)

Mobile.delay(3, FailureHandling.STOP_ON_FAILURE)

Mobile.waitForElementPresent(findTestObject('Mobile/Common/Camera_ConfirmButton'), 15, FailureHandling.OPTIONAL)

Mobile.tap(findTestObject('Mobile/Common/Camera_ConfirmButton'), 10)

Mobile.tap(findTestObject('Mobile/Common/Camera_ConfirmButton'), 2, FailureHandling.OPTIONAL)

Mobile.delay(2, FailureHandling.STOP_ON_FAILURE)

Mobile.verifyElementVisible(findTestObject('Mobile/Seller Task/CapturedImageView_Field'), 0)

Mobile.takeScreenshot()

'Verify (Saved Successfully) alert should display while saving the new task'
Mobile.tap(findTestObject('Mobile/Seller Task/Save button'), 0)

Mobile.verifyElementVisible(findTestObject('Mobile/Seller Task/Saved Successfully-Popup'), 0)

Mobile.takeScreenshot()

Mobile.tap(findTestObject('Mobile/Common/Btn_OK'), 0)

'Verify user can view the task in ALL Tab '
Mobile.tap(findTestObject('Mobile/Seller Task/Tab-ALL'), 0)

GlobalVariable.Name = findTestData('Mobile Input Data/Task_Management').getValue('Title_Name', 1)

Mobile.scrollToText(GlobalVariable.Name, FailureHandling.OPTIONAL)

Mobile.verifyElementVisible(findTestObject('Mobile/Seller Task/Active task'), 0)

Mobile.verifyElementText(findTestObject('Mobile/Seller Task/Active task'), findTestData('Mobile Input Data/Task_Management').getValue(
        'Title_Name', 1))

Mobile.takeScreenshot()

'Verify user can view the task in MY TASK Tab '
Mobile.tap(findTestObject('Mobile/Seller Task/Tab-MY TASK'), 0)

//Mobile.scrollToText(GlobalVariable.label, FailureHandling.OPTIONAL)

Mobile.verifyElementVisible(findTestObject('Mobile/Seller Task/Active task'), 0)

Mobile.verifyElementText(findTestObject('Mobile/Seller Task/Active task'), findTestData('Mobile Input Data/Task_Management').getValue(
        'Title_Name', 1))

Mobile.takeScreenshot()

'Verify user can open the task'
Mobile.tap(findTestObject('Mobile/Seller Task/Active task'), 0)

Mobile.verifyElementVisible(findTestObject('Mobile/Seller Task/Task Detail'), 0)

Mobile.verifyElementText(findTestObject('Mobile/Seller Task/TaskTitle-DetailScreen'), findTestData('Mobile Input Data/Task_Management').getValue(
        'Title_Name', 1))

Mobile.takeScreenshot()

'Verify user can execute the task'
Mobile.tap(findTestObject('Mobile/Seller Task/MARK AS EXECUTE'), 0)

AppiumDriver driver = MobileDriverFactory.getDriver()

Actualtoastmessage = driver.findElementByXPath('//android.widget.Toast[1]').getText()

KeywordUtil.logInfo(Actualtoastmessage)

Mobile.verifyMatch(Actualtoastmessage, findTestData('Mobile Input Data/Task_Management').getValue('Alert', 1), false)

Mobile.takeScreenshot()

'verify user can take evidence photo while execute the task'
Mobile.scrollToText(GlobalVariable.Name, FailureHandling.OPTIONAL)

Mobile.tap(findTestObject('Mobile/Seller Task/Active task'), 0)

Mobile.tap(findTestObject('Mobile/Seller Task/TAKE PHOTO(evidence)'), 0)

Mobile.delay(3, FailureHandling.STOP_ON_FAILURE)

Mobile.waitForElementPresent(findTestObject('Mobile/Common/Camera_ShutterButton'), 15, FailureHandling.OPTIONAL)

Mobile.tap(findTestObject('Mobile/Common/Camera_ShutterButton'), 3)

Mobile.delay(3, FailureHandling.STOP_ON_FAILURE)

Mobile.waitForElementPresent(findTestObject('Mobile/Common/Camera_ConfirmButton'), 15, FailureHandling.OPTIONAL)

Mobile.tap(findTestObject('Mobile/Common/Camera_ConfirmButton'), 10)

Mobile.tap(findTestObject('Mobile/Common/Camera_ConfirmButton'), 2, FailureHandling.OPTIONAL)

Mobile.delay(2, FailureHandling.STOP_ON_FAILURE)

Mobile.verifyElementVisible(findTestObject('Mobile/Seller Task/CapturedEvidenceImage_field'), 0)

Mobile.takeScreenshot()

'Verify task created from CPG is reflected in the mobile'
Mobile.tap(findTestObject('Mobile/Common/Icon_Back'), 0)

Mobile.tap(findTestObject('Mobile/Seller Task/Tab-ASSIGNED'), 0)

'verify one time task displaying in the Assign tab before execute'
GlobalVariable.Name = findTestData('Web Input Data/Task_Management').getValue('Title_Name', 1)

Mobile.scrollToText(GlobalVariable.Name, FailureHandling.OPTIONAL)

Mobile.verifyElementVisible(findTestObject('Mobile/Seller Task/Active task'), 0)

Mobile.verifyElementText(findTestObject('Mobile/Seller Task/Active task'), GlobalVariable.Name)

Mobile.tap(findTestObject('Mobile/Seller Task/Active task'), 0)

Mobile.takeScreenshot()

Mobile.tap(findTestObject('Mobile/Seller Task/MARK AS EXECUTE'), 0)

Actualtoastmessage1 = driver.findElementByXPath('//android.widget.Toast[1]').getText()

KeywordUtil.logInfo(Actualtoastmessage1)

Mobile.verifyMatch(Actualtoastmessage1, findTestData('Mobile Input Data/Task_Management').getValue('Alert', 1), false)

Mobile.takeScreenshot()

Mobile.scrollToText(GlobalVariable.Name, FailureHandling.OPTIONAL)

Mobile.tap(findTestObject('Mobile/Seller Task/Active task'), 0)

'verify user can take evidence photo while execute the CPG task'
Mobile.tap(findTestObject('Mobile/Seller Task/TAKE PHOTO(evidence)'), 0)

Mobile.delay(3, FailureHandling.STOP_ON_FAILURE)

Mobile.waitForElementPresent(findTestObject('Mobile/Common/Camera_ShutterButton'), 15, FailureHandling.OPTIONAL)

Mobile.tap(findTestObject('Mobile/Common/Camera_ShutterButton'), 3)

Mobile.delay(3, FailureHandling.STOP_ON_FAILURE)

Mobile.waitForElementPresent(findTestObject('Mobile/Common/Camera_ConfirmButton'), 15, FailureHandling.OPTIONAL)

Mobile.tap(findTestObject('Mobile/Common/Camera_ConfirmButton'), 10)

Mobile.tap(findTestObject('Mobile/Common/Camera_ConfirmButton'), 2, FailureHandling.OPTIONAL)

Mobile.delay(2, FailureHandling.STOP_ON_FAILURE)

Mobile.verifyElementVisible(findTestObject('Mobile/Seller Task/CapturedEvidenceImage_field'), 0)

Mobile.takeScreenshot()

Mobile.tap(findTestObject('Mobile/Common/Icon_Back'), 0)

'verify routine task displaying in the Assign tab before execute'
GlobalVariable.Name = findTestData('Web Input Data/Task_Management').getValue('Title_Name', 2)

Mobile.scrollToText(GlobalVariable.Name, FailureHandling.OPTIONAL)

Mobile.verifyElementVisible(findTestObject('Mobile/Seller Task/Active task'), 0)

Mobile.verifyElementText(findTestObject('Mobile/Seller Task/Active task'), GlobalVariable.Name)

Mobile.tap(findTestObject('Mobile/Seller Task/Active task'), 0)

Mobile.takeScreenshot()

Mobile.tap(findTestObject('Mobile/Seller Task/MARK AS EXECUTE'), 0)

Actualtoastmessage2 = driver.findElementByXPath('//android.widget.Toast[1]').getText()

KeywordUtil.logInfo(Actualtoastmessage2)

Mobile.verifyMatch(Actualtoastmessage2, findTestData('Mobile Input Data/Task_Management').getValue('Alert', 1), false)

Mobile.takeScreenshot()

Mobile.scrollToText(GlobalVariable.Name, FailureHandling.STOP_ON_FAILURE)

Mobile.tap(findTestObject('Mobile/Seller Task/Active task'), 0)

'verify user can take evidence photo while execute the CPG task'
Mobile.tap(findTestObject('Mobile/Seller Task/TAKE PHOTO(evidence)'), 0)

Mobile.delay(3, FailureHandling.STOP_ON_FAILURE)

Mobile.waitForElementPresent(findTestObject('Mobile/Common/Camera_ShutterButton'), 15, FailureHandling.OPTIONAL)

Mobile.tap(findTestObject('Mobile/Common/Camera_ShutterButton'), 3)

Mobile.delay(3, FailureHandling.STOP_ON_FAILURE)

Mobile.waitForElementPresent(findTestObject('Mobile/Common/Camera_ConfirmButton'), 15, FailureHandling.OPTIONAL)

Mobile.tap(findTestObject('Mobile/Common/Camera_ConfirmButton'), 10)

Mobile.tap(findTestObject('Mobile/Common/Camera_ConfirmButton'), 2, FailureHandling.OPTIONAL)

Mobile.delay(2, FailureHandling.STOP_ON_FAILURE)

Mobile.verifyElementVisible(findTestObject('Mobile/Seller Task/CapturedEvidenceImage_field'), 0)

Mobile.takeScreenshot()

Mobile.tap(findTestObject('Mobile/Common/Icon_Back'), 0)

Mobile.delay(2, FailureHandling.STOP_ON_FAILURE)

Mobile.tap(findTestObject('Mobile/Common/Icon_Back'), 0)

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Call_Analysis(without Order)'), [:], FailureHandling.STOP_ON_FAILURE)

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Sync'), [:], FailureHandling.STOP_ON_FAILURE)

Mobile.callTestCase(findTestCase('Product_Mobile/Common/HHT_Transaction_DB'), [:], FailureHandling.STOP_ON_FAILURE)

