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
import java.text.SimpleDateFormat as SimpleDateFormat

if (Mobile.verifyElementExist(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/Menu-Collection'), 2, FailureHandling.OPTIONAL)) {
    'Collection menu visible'
} else {
    Mobile.callTestCase(findTestCase('Test Cases/VBL_Mobile/Re-Usables/Mobile_Login/Relaunch APK'), [:], FailureHandling.CONTINUE_ON_FAILURE)

    Mobile.tap(findTestObject('XXXXXXXXXXXX/Re-usable/Mobile Login/Menu'), 0)

    Mobile.callTestCase(findTestCase('VBL_Mobile/Re-Usables/Select_TradeCoverage_Menu'), [:], FailureHandling.STOP_ON_FAILURE)

    GlobalVariable.RetailerName = findTestData('VBL_Mobile Input Data/CallAnalysis').getValue('Retailer_Name', 1)

    Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/MainMenu/Menu_Trade Coverage'), 0)

    Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Reusable Object/Retailer Search Icon'), 5)

    Mobile.setText(findTestObject('XXXXXXXXXXXX/Mobile Part/Reusable Object/Retailer Enter Search Field'), GlobalVariable.RetailerName, 
        5)

    Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/stores click'), 5)

    Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/Start Visit Button'), 5)

    if (Mobile.verifyElementVisible(findTestObject('Object Repository/XXXXXXXXXXXX/Mobile Part/Store_Actvy/Location Validation'), 
        5, FailureHandling.OPTIONAL)) {
        Mobile.tap(findTestObject('Object Repository/XXXXXXXXXXXX/Mobile Part/Store_Actvy/location validtion- YES'), 5)
    }
    
    Mobile.delay(7)

    Mobile.swipe(0, 150, 0, 400, FailureHandling.OPTIONAL)
}

Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/Menu-Collection'), 0)

Mobile.delay(2)

String pattern = 'yyyy/MM/dd'

SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern)

String currentdate = simpleDateFormat.format(new Date())

ScrollDate = (currentdate + '(')

Mobile.scrollToText(ScrollDate, FailureHandling.OPTIONAL)

GlobalVariable.label = currentdate

int b = Mobile.getDeviceHeight()

int y1 = b / 2

int y2 = y1 - 30

Mobile.swipe(10, y1, 10, y2)

TransactionNo = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/Collection/Get_TransactionNo'), 0)

GlobalVariable.Transaction_No = TransactionNo

Transaction_Amt = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/Collection/Transaction_Amt'), 0)

GlobalVariable.C_TransactionAmt = Transaction_Amt

before_OS_Amt = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/Collection/OS_Amt'), 0)

Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/Collection/Get_TransactionNo'), 0)

Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/Collection/Pay_Btn'), 0)

Mobile.delay(2)

Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/Collection/Menu-Cash'), 0)

//Mobile.clearText(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/Collection/Amount'), 0)
Paid_Amount = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/Collection/Amount'), 0)

//GlobalVariable.keypadValue = findTestData('VBL_Mobile Input Data/CallAnalysis').getValue('Keypad_Number', 2)
//
//Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Reusable Object/Vertical KeyPad-Numbers/Global-number_keypad'), 0)
Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/Collection/APPLY_Btn'), 0)

Paid_Amt = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/Collection/Paid_Amt'), 0)

Paid_Amt2 = Paid_Amt.replaceAll('\\s', '')

Exepected_Paid_Amount = Paid_Amt2.replaceAll('Paid', '')

//Exepected_Paid_Amount = ((' ' + Paid_Amount) + '.0 Paid')
//Mobile.verifyMatch(Paid_Amt, Exepected_Paid_Amount, false, FailureHandling.STOP_ON_FAILURE)
Mobile.verifyEqual(Double.parseDouble(Paid_Amount), Double.parseDouble(Exepected_Paid_Amount), FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

println('Edited amount value should be displated correctly')

Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/Collection/SAVE_Btn'), 0)

Mobile.delay(2)

Mobile.setText(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/Collection/Enter Receipt Number'), findTestData('VBL_Mobile Input Data/CallAnalysis').getValue(
        'InvoiceNo', 1), 0)

Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/Collection/OK_Btn'), 0)

Mobile.tap(findTestObject('Mobile/Common/Icon_Back'), 0)

Mobile.tap(findTestObject('Mobile/Common/Icon_Back'), 2, FailureHandling.OPTIONAL)

Mobile.scrollToText(ScrollDate, FailureHandling.OPTIONAL)

Mobile.swipe(10, y1, 10, y2)

After_OS_Amt = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/Collection/OS_Amt'), 2, FailureHandling.OPTIONAL)

GlobalVariable.C_OS_Amt = After_OS_Amt

String pastdate = simpleDateFormat.format(new Date() - 1)

Received_Amt = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/Collection/Received_Amt'), 2, FailureHandling.OPTIONAL)

GlobalVariable.label = pastdate

//Received_Amt = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/Collection/Received_Amt'), 2, FailureHandling.OPTIONAL)

GlobalVariable.C_ReceivedAmt = Received_Amt

Mobile.verifyEqual(Double.parseDouble(Received_Amt), Double.parseDouble(Exepected_Paid_Amount), FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

println('Entered amount value should be display in recieved amt field')

Calculated_OS_Amt = (Double.parseDouble(Transaction_Amt) - Double.parseDouble(Exepected_Paid_Amount))

Mobile.verifyEqual(Double.parseDouble(After_OS_Amt), Calculated_OS_Amt, FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

println('OS amount should be displayed properly')

Mobile.tap(findTestObject('Mobile/Common/Icon_Back'), 0)

Mobile.delay(3)

Mobile.swipe(0, 150, 0, 400, FailureHandling.OPTIONAL)

Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/CallAnalysis/StoreActy_Close Call Btn'), 0)

Mobile.delay(2)

Collection_Amt = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/CallAnalysis/Value-Collection Amount'), 
    0)

Mobile.verifyEqual(Double.parseDouble(Collection_Amt), Double.parseDouble(Received_Amt), FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

println('Collection value should displayed properly')

Mobile.tap(findTestObject('Mobile/Common/Icon_Back'), 0)

Mobile.delay(3)

Mobile.swipe(0, 150, 0, 400, FailureHandling.OPTIONAL)

