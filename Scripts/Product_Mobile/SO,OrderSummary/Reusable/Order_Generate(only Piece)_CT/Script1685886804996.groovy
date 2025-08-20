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

GlobalVariable.ProductName = ProductName

println(GlobalVariable.ProductName)

GlobalVariable.keypadValue = KeypadNumber

println(GlobalVariable.keypadValue)

Mobile.tap(findTestObject('Mobile/Common/Icon_FunnelFilter'), 5)

Mobile.hideKeyboard(FailureHandling.OPTIONAL)

Mobile.tap(findTestObject('Mobile/Common/Btn_Apply'), 5)

Mobile.tap(findTestObject('Mobile/Common/Icon_Search'), 4, FailureHandling.OPTIONAL)

Mobile.setText(findTestObject('Mobile/Common/Field_Search_EnterText'), GlobalVariable.ProductName, 
    5)

Mobile.delay(3, FailureHandling.STOP_ON_FAILURE)

Mobile.tap(findTestObject('Mobile/OrderInvoice/Qty_Field'), 5)

GlobalVariable.keypadValue = KeypadNumber

Mobile.tap(findTestObject('Mobile/Common/Global_Number_keypad'), 5)

Mobile.tap(findTestObject('Mobile/Common/Btn_OK'), 5)

Mobile.tap(findTestObject('Mobile/Common/Icon_Search'), 5, FailureHandling.OPTIONAL)

Mobile.clearText(findTestObject('Mobile/Common/Field_Search_EnterText'), 3, FailureHandling.OPTIONAL)

