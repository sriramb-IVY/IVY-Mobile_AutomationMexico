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
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys

WebUI.callTestCase(findTestCase('PNG Product/SalesOrder/SalesOrder'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.delay(5)

WebUI.callTestCase(findTestCase('PNG Product/Sales Invoice/SalesInvoice'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.verifyElementPresent(findTestObject('PNG Product/Sales Invoice/salesOrder_no'), 0)

WebUI.takeScreenshot()

WebUI.click(findTestObject('PNG Product/Sales Invoice/button_GenerateInvoice'))

WebUI.delay(10)

WebUI.verifyElementPresent(findTestObject('PNG Product/Sales Invoice/button_Submit'), 0)

WebUI.click(findTestObject('PNG Product/Sales Invoice/button_Submit'))

WebUI.takeScreenshot()

WebUI.verifyElementPresent(findTestObject('PNG Product/Sales Invoice/button_Save Invoice'), 0)

WebUI.click(findTestObject('PNG Product/Sales Invoice/button_Save Invoice'))

WebUI.takeScreenshot()

WebUI.closeBrowser()

