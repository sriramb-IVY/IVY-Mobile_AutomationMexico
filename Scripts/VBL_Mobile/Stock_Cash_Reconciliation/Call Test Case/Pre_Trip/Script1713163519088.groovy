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
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil

WebUI.callTestCase(findTestCase('Product_Mobile/Common/Web Login and Navigation/DistributorLogin'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('Product_Mobile/Common/Web Login and Navigation/Navigate to Stock Reconcile Create'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.verifyElementPresent(findTestObject('Mobile/Web Part/Stock_Cash_Reconciliation/UserSelect'), 0)

WebUI.click(findTestObject('Mobile/Web Part/Stock_Cash_Reconciliation/UserSelect'))

WebUI.takeScreenshot()

WebUI.scrollToElement(findTestObject('Mobile/Web Part/Stock_Cash_Reconciliation/Button/Proceed_Btn'), 0)

WebUI.verifyElementPresent(findTestObject('Mobile/Web Part/Stock_Cash_Reconciliation/Button/Proceed_Btn'), 0)

WebUI.click(findTestObject('Mobile/Web Part/Stock_Cash_Reconciliation/Button/Proceed_Btn'))

WebUI.verifyElementPresent(findTestObject('Object Repository/Mobile/Web Part/Stock_Cash_Reconciliation/Warehouse_Warn_Message'), 0)

WebUI.takeScreenshot()

Warehouse_WN_Text = WebUI.getText(findTestObject('Object Repository/Mobile/Web Part/Stock_Cash_Reconciliation/Warehouse_Warn_Message'))

KeywordUtil.logInfo(Warehouse_WN_Text)

WebUI.verifyMatch(findTestData('Web Input Data/Stock_Cash_Reconciliation').getValue('Warn_Message', 1), Warehouse_WN_Text, false)

WebUI.verifyElementPresent(findTestObject('Object Repository/Mobile/Web Part/Stock_Cash_Reconciliation/Tirp_Warn_Message'), 0)

WebUI.takeScreenshot()

Trip_WN_Text = WebUI.getText(findTestObject('Object Repository/Mobile/Web Part/Stock_Cash_Reconciliation/Tirp_Warn_Message'))

KeywordUtil.logInfo(Trip_WN_Text)

WebUI.verifyMatch(findTestData('Web Input Data/Stock_Cash_Reconciliation').getValue('Warn_Message', 2), Trip_WN_Text, false)

WebUI.takeScreenshot()

WebUI.closeBrowser()

