import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import java.sql.Connection as Connection
import java.sql.DriverManager as DriverManager
import java.sql.ResultSet as ResultSet
import java.sql.Statement as Statement
import java.text.SimpleDateFormat as SimpleDateFormat
import com.google.common.collect.FilteredEntryMultimap.Keys as Keys
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Mobile_Login/VanSeller_Login-With Reset'), [:], FailureHandling.STOP_ON_FAILURE)

not_run: Mobile.startApplication(GlobalVariable.APKFile, false)

not_run: Mobile.tap(findTestObject('Mobile/Common/Btn_Menu'), 70)

Mobile.tap(findTestObject('Object Repository/Mobile/Deviation/Trade Coverage Menu'), 3)

Mobile.tap(findTestObject('Mobile/Common/Icon_Search'), 5)

Mobile.sendKeys(findTestObject('Mobile/Common/Field_Search_EnterText'), findTestData('Mobile Input Data/Task_Management').getValue('Retailer', 1))

Mobile.takeScreenshot()

Retailer_name = Mobile.getText(findTestObject('Mobile/Seller Task/Retailer'), 0)

KeywordUtil.logInfo('Retailer_name = ' + Retailer_name)

Mobile.tap(findTestObject('Mobile/Seller Task/Retailer'), 0)

Mobile.tap(findTestObject('Mobile/Seller Task/Start Visit button'), 0)

Mobile.tap(findTestObject('Mobile/Deviation/Location validation yes button'), 5)

Mobile.delay(6, FailureHandling.STOP_ON_FAILURE)

Mobile.swipe(0, 150, 0, 400)

'Verify completed task load under the completed tab'
Mobile.tap(findTestObject('Mobile/Seller Task/Task Execution Menu'), 3)

Mobile.tap(findTestObject('Mobile/Seller Task/Tab-COMPLETED'), 0)

Mobile.takeScreenshot()

'Mobile Task'
GlobalVariable.Name = findTestData('Mobile Input Data/Task_Management').getValue('Title_Name', 1)

Mobile.scrollToText(GlobalVariable.Name, FailureHandling.OPTIONAL)

Mobile.verifyElementVisible(findTestObject('Mobile/Seller Task/Active task'), 0, FailureHandling.STOP_ON_FAILURE)

Mobile.verifyElementText(findTestObject('Mobile/Seller Task/Active task'), findTestData('Mobile Input Data/Task_Management').getValue('Title_Name', 1), FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

'MobileOneTimeTask'
GlobalVariable.Name = findTestData('Web Input Data/Task_Management').getValue('Title_Name', 1)

Mobile.scrollToText(GlobalVariable.Name, FailureHandling.OPTIONAL)

Mobile.verifyElementVisible(findTestObject('Mobile/Seller Task/Active task'), 0, FailureHandling.STOP_ON_FAILURE)

Mobile.verifyElementText(findTestObject('Mobile/Seller Task/Active task'), findTestData('Web Input Data/Task_Management').getValue('Title_Name', 1), FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

'MobileRoutineTask'
GlobalVariable.Name = findTestData('Web Input Data/Task_Management').getValue('Title_Name', 2)

Mobile.scrollToText(GlobalVariable.Name, FailureHandling.OPTIONAL)

Mobile.verifyElementVisible(findTestObject('Mobile/Seller Task/Active task'), 0, FailureHandling.OPTIONAL)

Mobile.verifyElementText(findTestObject('Mobile/Seller Task/Active task'), findTestData('Web Input Data/Task_Management').getValue('Title_Name', 2), FailureHandling.OPTIONAL)

Mobile.takeScreenshot()

'Verify One time task will not be shown next time/day'
Mobile.tap(findTestObject('Mobile/Seller Task/Tab-ASSIGNED'), 0)

Mobile.takeScreenshot()

GlobalVariable.Name = findTestData('Web Input Data/Task_Management').getValue('Title_Name', 1)

Mobile.verifyElementNotExist(findTestObject('Mobile/Seller Task/Active task'), 0, FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

'Verify Routine task will be shown on daily basis'
GlobalVariable.Name = findTestData('Web Input Data/Task_Management').getValue('Title_Name', 2)

Mobile.scrollToText(GlobalVariable.Name, FailureHandling.OPTIONAL)

Mobile.verifyElementVisible(findTestObject('Mobile/Seller Task/Active task'), 0, FailureHandling.OPTIONAL)

Mobile.verifyElementText(findTestObject('Mobile/Seller Task/Active task'), findTestData('Web Input Data/Task_Management').getValue('Title_Name', 2), FailureHandling.OPTIONAL)

Mobile.takeScreenshot()

