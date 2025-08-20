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

not_run: Mobile.startApplication(GlobalVariable.APK_File, false)

not_run: Mobile.tap(findTestObject('XXXXXXXXXXXX/Re-usable/Mobile Login/Menu'), 0)

if (Mobile.verifyElementExist(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/Menu-SalesReturn'), 2, FailureHandling.OPTIONAL)) {
    'SalesReturn menu visible'
} else {
    Mobile.callTestCase(findTestCase('Test Cases/VBL_Mobile/Re-Usables/Mobile_Login/Relaunch APK'), [:], FailureHandling.CONTINUE_ON_FAILURE)

    Mobile.tap(findTestObject('XXXXXXXXXXXX/Re-usable/Mobile Login/Menu'), 0)

    Mobile.callTestCase(findTestCase('VBL_Mobile/Re-Usables/Select_TradeCoverage_Menu'), [:], FailureHandling.STOP_ON_FAILURE)

    GlobalVariable.RetailerName = findTestData('VBL_Mobile Input Data/CallAnalysis').getValue('Retailer_Name', 1)

    Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/MainMenu/Menu_Trade Coverage'), 0)

    Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Reusable Object/Retailer Search Icon'), 5)

    Mobile.setText(findTestObject('XXXXXXXXXXXX/Mobile Part/Reusable Object/Retailer Enter Search Field'), GlobalVariable.RetailerName, 5)

    Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/stores click'), 5)

    Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/Start Visit Button'), 5)

    if (Mobile.verifyElementVisible(findTestObject('Object Repository/XXXXXXXXXXXX/Mobile Part/Store_Actvy/Location Validation'), 5, FailureHandling.OPTIONAL)) {
        Mobile.tap(findTestObject('Object Repository/XXXXXXXXXXXX/Mobile Part/Store_Actvy/location validtion- YES'), 5)
    }
    
    Mobile.delay(7)

    Mobile.swipe(0, 150, 0, 400, FailureHandling.OPTIONAL)
}

Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/Menu-SalesReturn'), 0)

Mobile.delay(2)

not_run: Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Reusable Object/Product Search Icon'), 0)

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Search_Product'), [:], FailureHandling.STOP_ON_FAILURE)

GlobalVariable.S_Sku_Name = findTestData('VBL_Mobile Input Data/CallAnalysis').getValue('FB_Product_Name', 1)

Mobile.setText(findTestObject('XXXXXXXXXXXX/Mobile Part/Reusable Object/Product Enter Search'), GlobalVariable.S_Sku_Name, 0)

Mobile.delay(2)

ProductName = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/SalesReturn/ProductName-1st'), 0)

Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/SalesReturn/Piece-1st'), 0)

Mobile.setText(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/SalesReturn/Return-InvoiceNo'), findTestData('VBL_Mobile Input Data/CallAnalysis').getValue('InvoiceNo', 1), 0, FailureHandling.OPTIONAL)

MRP = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/SalesReturn/Return-MRP'), 0)

Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/SalesReturn/Return-Select Reason'), 0)

Mobile.delay(2)

Reason = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/SalesReturn/EXCESS-option'), 0)

GlobalVariable.S_ReasonType = Reason

Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/SalesReturn/EXCESS-option'), 0)

Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/SalesReturn/Return-Piece'), 0, FailureHandling.STOP_ON_FAILURE)

GlobalVariable.keypadValue = findTestData('VBL_Mobile Input Data/CallAnalysis').getValue('Keypad_Number', 1)

GlobalVariable.PieceQty = GlobalVariable.keypadValue

Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Reusable Object/Vertical KeyPad-Numbers/Global-number_keypad'), 0)

Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/SalesReturn/Return-Case'), 0, FailureHandling.STOP_ON_FAILURE)

GlobalVariable.keypadValue = findTestData('VBL_Mobile Input Data/CallAnalysis').getValue('Keypad_Number', 1)

GlobalVariable.CaseQty = GlobalVariable.keypadValue

Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Reusable Object/Vertical KeyPad-Numbers/Global-number_keypad'), 0)

Mobile.takeScreenshot()

println('All field should be entered')

Mobile.delay(2)

Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/SalesReturn/Return-Done'), 0)

Mobile.delay(2)

GlobalVariable.CaseSize = findTestData('VBL_Mobile Input Data/Invoice').getValue('CaseSize', 2)

QtyCase = (Integer.parseInt(GlobalVariable.CaseSize) * Integer.parseInt(GlobalVariable.CaseQty))

Case_Piece_Qty = (QtyCase + Integer.parseInt(GlobalVariable.PieceQty))

GlobalVariable.S_CasePieceQty = Case_Piece_Qty

piece_qty = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/SalesReturn/Piece-1st'), 0)

case_qty = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/SalesReturn/Case-1st'), 0)

Mobile.verifyMatch(piece_qty, GlobalVariable.PieceQty, false, FailureHandling.STOP_ON_FAILURE)

Mobile.verifyMatch(case_qty, GlobalVariable.CaseQty, false, FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

println('case and piece qty displayed correctly')

Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/SalesReturn/SR-Next btn'), 0)

Mobile.delay(2)

Mobile.verifyElementText(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/SalesReturn/SR-ProductName'), ProductName)

Mobile.verifyElementText(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/SalesReturn/SR-Reason'), Reason)

Mobile.verifyElementText(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/SalesReturn/SR_InvoiceNo'), findTestData('VBL_Mobile Input Data/CallAnalysis').getValue('InvoiceNo', 1), FailureHandling.OPTIONAL)

Mobile.verifyElementText(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/SalesReturn/SR_MRP'), MRP, FailureHandling.OPTIONAL)

Mobile.verifyElementText(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/SalesReturn/SR-Case'), GlobalVariable.CaseQty, FailureHandling.OPTIONAL)

Mobile.verifyElementText(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/SalesReturn/SR-Piece'), GlobalVariable.PieceQty, FailureHandling.OPTIONAL)

Mobile.takeScreenshot()

println('Entered data displayed correctly')

SR_TotalValue = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/SalesReturn/SR-TotalValue'), 0, FailureHandling.OPTIONAL)

SR_Total_Value = Double.parseDouble(SR_TotalValue).round()

Expected_SR_Total_Value = (SR_Total_Value.toString() + '.0')

Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/SalesReturn/SR-Save btn'), 0)

Mobile.delay(3)

Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/SalesReturn/SR-Ok'), 10, FailureHandling.OPTIONAL)

Mobile.verifyElementVisible(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/SalesReturn/SR-Saved Successfully'), 0)

Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/SalesReturn/SR-Ok'), 10, FailureHandling.OPTIONAL)

Mobile.takeScreenshot()

println('Sales return created successfully')

Mobile.delay(3)

not_run: Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/SalesReturn/SR-Ok'), 0, FailureHandling.OPTIONAL)

Mobile.delay(3)

Mobile.swipe(0, 150, 0, 400, FailureHandling.OPTIONAL)

Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/CallAnalysis/StoreActy_Close Call Btn'), 0)

Mobile.delay(3)

SalesReturn = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/CallAnalysis/Value-Sales Return'), 0)

GlobalVariable.SR_Amt = SalesReturn

Mobile.verifyMatch(SalesReturn, Expected_SR_Total_Value, false, FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

println('Sales return value should displayed correctly')

Mobile.tap(findTestObject('Mobile/Common/Icon_Back'), 0)

Mobile.delay(3)

Mobile.swipe(0, 150, 0, 400, FailureHandling.OPTIONAL)

