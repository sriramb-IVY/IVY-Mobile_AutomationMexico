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

Mobile.callTestCase(findTestCase('VBL_Mobile/Re-Usables/Web Login and Navigation/ManjuLogin'), [:], FailureHandling.STOP_ON_FAILURE)

Mobile.callTestCase(findTestCase('VBL_Mobile/Re-Usables/Web Login and Navigation/Navigate to Stock Reconcile Create'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.waitForElementNotVisible(findTestObject('XXXXXXXXXXXX/Web Part/Re-usable/Page Load'), 100)

WebUI.delay(2)

//WebUI.click(findTestObject('XXXXXXXXXXXX/Web Part/Stock Reconciliation/Select_UserDetails'), FailureHandling.STOP_ON_FAILURE)
GlobalVariable.label = findTestData('VBL_Web Input Data/Stock allocation').getValue('User_Details', 1)

WebUI.click(findTestObject('XXXXXXXXXXXX/Web Part/Stock Reconciliation/Select_UserDetails'))

GlobalVariable.label = findTestData('VBL_Web Input Data/Stock allocation').getValue('Warehouse', 1)

WebUI.click(findTestObject('XXXXXXXXXXXX/Web Part/Stock Reconciliation/Warehouse_Field'))

WebUI.click(findTestObject('XXXXXXXXXXXX/Web Part/Stock Allocation/Global Variables/DropdownSelect_Li Tag'))

WebUI.click(findTestObject('XXXXXXXXXXXX/Web Part/Stock Allocation/Button/Proceed_btn'))

WebUI.waitForElementNotVisible(findTestObject('XXXXXXXXXXXX/Web Part/Re-usable/Page Load'), 100)

WebUI.click(findTestObject('XXXXXXXXXXXX/Web Part/Stock Reconciliation/Reconcile_Btn'))

WebUI.click(findTestObject('XXXXXXXXXXXX/Web Part/Collection Acceptance/OK_btn'))

