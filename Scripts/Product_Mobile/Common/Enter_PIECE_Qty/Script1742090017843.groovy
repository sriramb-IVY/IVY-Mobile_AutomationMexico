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

if (Mobile.verifyElementVisible(findTestObject('Mobile/OrderInvoice/PIECE_Bar'), 1, FailureHandling.OPTIONAL)) {
    Mobile.tap(findTestObject('Mobile/OrderInvoice/Enter_Qty'), 0)
} else {
    Mobile.tap(findTestObject('Object Repository/Mobile/OrderInvoice/CASE_Bar'), 0)

    Mobile.tap(findTestObject('Mobile/OrderInvoice/Enter_Qty'), 1, FailureHandling.OPTIONAL)
}

Mobile.tap(findTestObject('Mobile/Common/Vertical keypad-BackSpace'), 1, FailureHandling.OPTIONAL)

String Qty = Integer.parseInt(Quantity)

length = Qty.size()

if (4 == length) {
    GlobalVariable.keypadValue = Qty.charAt(0)

    Mobile.tap(findTestObject('Object Repository/Mobile/Common/Global_Number_keypad'), 5)

    GlobalVariable.keypadValue = Qty.charAt(1)

    Mobile.tap(findTestObject('Object Repository/Mobile/Common/Global_Number_keypad'), 5)

    GlobalVariable.keypadValue = Qty.charAt(2)

    Mobile.tap(findTestObject('Object Repository/Mobile/Common/Global_Number_keypad'), 5)

    GlobalVariable.keypadValue = Qty.charAt(3)

    Mobile.tap(findTestObject('Object Repository/Mobile/Common/Global_Number_keypad'), 5)
} else if (3 == length) {
    GlobalVariable.keypadValue = Qty.charAt(0)

    Mobile.tap(findTestObject('Object Repository/Mobile/Common/Global_Number_keypad'), 5)

    GlobalVariable.keypadValue = Qty.charAt(1)

    Mobile.tap(findTestObject('Object Repository/Mobile/Common/Global_Number_keypad'), 5)

    GlobalVariable.keypadValue = Qty.charAt(2)

    Mobile.tap(findTestObject('Object Repository/Mobile/Common/Global_Number_keypad'), 5)
} else if (2 == length) {
    GlobalVariable.keypadValue = Qty.charAt(0)

    Mobile.tap(findTestObject('Object Repository/Mobile/Common/Global_Number_keypad'), 5)

    GlobalVariable.keypadValue = Qty.charAt(1)

    Mobile.tap(findTestObject('Object Repository/Mobile/Common/Global_Number_keypad'), 5)
} else {
    GlobalVariable.keypadValue = Qty

    Mobile.tap(findTestObject('Object Repository/Mobile/Common/Global_Number_keypad'), 5)
}

