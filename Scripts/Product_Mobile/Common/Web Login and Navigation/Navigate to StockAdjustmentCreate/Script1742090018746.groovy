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

WebUI.refresh()

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Navigation'), [('Navigation') : findTestData('Web Input Data/Navigation').getValue('Navigate_Menu', 2)], 
    FailureHandling.STOP_ON_FAILURE)
//
//WebUI.waitForElementNotVisible(findTestObject('Web Part/Re-usable/Page Load'), 50)
//
//GlobalVariable.label = findTestData('Web Input Data/Stock allocation').getValue('Navigation', 3)
//
//WebUI.waitForElementVisible(findTestObject('Web Part/TaxScreen/Global Object/A_tag_Global Variable'), 15)
//
//WebUI.scrollToElement(findTestObject('Web Part/TaxScreen/Global Object/A_tag_Global Variable'), 5)
//
//WebUI.click(findTestObject('Web Part/TaxScreen/Global Object/A_tag_Global Variable'))
//
//GlobalVariable.label = findTestData('Web Input Data/Stock allocation').getValue('Navigation', 4)
//
//WebUI.waitForElementVisible(findTestObject('Web Part/TaxScreen/Global Object/A_tag_Global Variable'), 5)
//
//WebUI.scrollToElement(findTestObject('Web Part/TaxScreen/Global Object/A_tag_Global Variable'), 5)
//
//WebUI.click(findTestObject('Web Part/TaxScreen/Global Object/A_tag_Global Variable'))
//
//WebUI.waitForElementNotVisible(findTestObject('Web Part/Re-usable/Page Load'), 50, FailureHandling.STOP_ON_FAILURE)
//
//GlobalVariable.label = findTestData('Web Input Data/Stock allocation').getValue('Navigation', 5)
//
//WebUI.waitForElementVisible(findTestObject('Web Part/TaxScreen/Global Object/A_tag_Global Variable'), 5)
//
//WebUI.scrollToElement(findTestObject('Web Part/TaxScreen/Global Object/A_tag_Global Variable'), 5)
//
//WebUI.click(findTestObject('Web Part/TaxScreen/Global Object/A_tag_Global Variable'))
//
//WebUI.waitForElementNotVisible(findTestObject('Web Part/Re-usable/Page Load'), 50, FailureHandling.STOP_ON_FAILURE)
//
