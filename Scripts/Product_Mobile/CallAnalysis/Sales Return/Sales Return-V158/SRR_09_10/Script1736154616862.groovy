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
import org.testng.Assert as Assert
import io.appium.java_client.android.nativekey.KeyEvent as KeyEvent
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import com.kms.katalon.keyword.excel.ExcelKeywords as ExcelKeywords

Mobile.startApplication(GlobalVariable.APKFile, false)


if (Mobile.verifyElementExist(findTestObject('Mobile/Store_Actvy/Menu-SalesReturn'), 2, FailureHandling.OPTIONAL)) {
    'SalesReturn menu visible'
} else {
    Mobile.callTestCase(findTestCase('Product_Mobile/Common/Mobile_Login/Relaunch the app'), [:], FailureHandling.CONTINUE_ON_FAILURE)

    Mobile.tap(findTestObject('Mobile/Common/Btn_Menu'), 0)

    GlobalVariable.RetailerName = findTestData('Mobile Input Data/SalesReturn_Replacement').getValue('Retailer', 1)

    Mobile.tap(findTestObject('Mobile/MainMenu/Menu_Trade Coverage'), 0)

    Mobile.tap(findTestObject('Mobile/Common/Icon_Search'), 5)

    Mobile.setText(findTestObject('Mobile/Common/Field_Search_EnterText'), GlobalVariable.RetailerName, 5)

    Mobile.tap(findTestObject('Mobile/TradeCoverage/First_Retailer_Selection_From_List'), 5)

    Mobile.tap(findTestObject('Mobile/Common/Btn_StartVisit'), 5)

    if (Mobile.verifyElementVisible(findTestObject('Object Repository/Mobile/Common/Popup_Title_LocationValidation'), 5, FailureHandling.OPTIONAL)) {
        Mobile.tap(findTestObject('Mobile/Common/Btn_YES'), 5)
    }
    
    Mobile.delay(6)

    Mobile.swipe(0, 150, 0, 400, FailureHandling.OPTIONAL)
}

Mobile.tap(findTestObject('Mobile/Store_Actvy/Menu-SalesReturn'), 0)

Mobile.delay(2)

Mobile.verifyElementVisible(findTestObject('Mobile/SalesReturn/Label-Product Name'), 0)

Mobile.verifyElementVisible(findTestObject('Mobile/SalesReturn/Label-Cases'), 0)

Mobile.tap(findTestObject('Mobile/Common/Icon_Search'), 0)

GlobalVariable.S_Sku_Name = findTestData('Mobile Input Data/SalesReturn_Replacement').getValue('Return_SKU_Name', 2)

Mobile.setText(findTestObject('Mobile/Common/Field_Search_EnterText'), GlobalVariable.S_Sku_Name, 0)

Mobile.takeScreenshot()

Mobile.verifyElementVisible(findTestObject('Mobile/SalesReturn/ProductName-1st'), 3, FailureHandling.STOP_ON_FAILURE)

Mobile.verifyElementText(findTestObject('Mobile/SalesReturn/ProductName-1st'), GlobalVariable.S_Sku_Name, FailureHandling.STOP_ON_FAILURE)

KeywordUtil.logInfo('The list of the products  displayed in the Sales return screen')

Mobile.takeScreenshot()

Mobile.tap(findTestObject('Mobile/SalesReturn/Piece-1st'), 0)

Mobile.delay(2)

Mobile.verifyElementExist(findTestObject('Mobile/SalesReturn/RETURN_Screen_Title'), 0)

Mobile.setText(findTestObject('Mobile/SalesReturn/Return-InvoiceNo'), findTestData('Mobile Input Data/CallAnalysis').getValue('InvoiceNo', 1), 0, FailureHandling.OPTIONAL)

Mobile.takeScreenshot()

MRP = Mobile.getText(findTestObject('Mobile/SalesReturn/Return-MRP'), 0)

a = Mobile.getAttribute(findTestObject('Mobile/SalesReturn/Return-Select Reason'), 'x', 2, FailureHandling.OPTIONAL)

b = Mobile.getAttribute(findTestObject('Mobile/SalesReturn/Return-Select Reason'), 'y', 2, FailureHandling.OPTIONAL)

height = Mobile.getAttribute(findTestObject('Mobile/SalesReturn/Return-Select Reason'), 'height', 2, FailureHandling.OPTIONAL)

x_point = (Integer.parseInt(a) + 50)

y_point = ((Integer.parseInt(b) + Integer.parseInt(height)) + 20)

Mobile.tap(findTestObject('Mobile/SalesReturn/Return-Select Reason'), 0)

Mobile.takeScreenshot()

Reason = 'Damage'

GlobalVariable.S_ReasonType = Reason

Mobile.setText(findTestObject('Mobile/SalesReturn/Return-Select Reason'), Reason, 0, FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

Mobile.tapAtPosition(x_point, y_point)

not_run: Mobile.tap(findTestObject('Mobile/SalesReturn/Return-Select Reason'), 0)

not_run: Mobile.takeScreenshot()

not_run: Reason = Mobile.getText(findTestObject('Mobile/SalesReturn/Damage-option'), 0)

not_run: GlobalVariable.S_ReasonType = Reason

not_run: Mobile.tap(findTestObject('Mobile/SalesReturn/Damage-option'), 0)

Mobile.tap(findTestObject('Mobile/SalesReturn/Return-Piece'), 0, FailureHandling.STOP_ON_FAILURE)

GlobalVariable.keypadValue = findTestData('Mobile Input Data/SalesReturn_Replacement').getValue('Qty', 2)

Mobile.takeScreenshot()

GlobalVariable.PieceQty = GlobalVariable.keypadValue

Mobile.tap(findTestObject('Mobile/Common/Global_Number_keypad'), 0)

Mobile.tap(findTestObject('Mobile/Common/Btn_Done'), 0)

Mobile.delay(2)

Before_edit_piece = Mobile.getText(findTestObject('Mobile/SalesReturn/Piece-1st'), 2, FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot('Before edit the quty')

'Edit the piece after done'
Mobile.tap(findTestObject('Mobile/SalesReturn/Piece-1st'), 0)

Mobile.delay(2)

Mobile.verifyElementExist(findTestObject('Mobile/SalesReturn/RETURN_Screen_Title'), 0)

Mobile.setText(findTestObject('Mobile/SalesReturn/Return-InvoiceNo'), findTestData('Mobile Input Data/CallAnalysis').getValue('InvoiceNo', 1), 0, FailureHandling.OPTIONAL)

Mobile.tap(findTestObject('Mobile/SalesReturn/Return-Piece'), 0, FailureHandling.STOP_ON_FAILURE)

Mobile.tap(findTestObject('Object Repository/Mobile/Common/Vertical keypad-BackSpace'), 0)

Mobile.tap(findTestObject('Object Repository/Mobile/Common/Vertical keypad-BackSpace'), 0)

GlobalVariable.keypadValue = findTestData('Mobile Input Data/SalesReturn_Replacement').getValue('Qty', 3)

GlobalVariable.PieceQty = GlobalVariable.keypadValue

Mobile.tap(findTestObject('Mobile/Common/Global_Number_keypad'), 0)

Mobile.tap(findTestObject('Mobile/Common/Btn_Done'), 0)

Mobile.delay(2)

After_edit_piece = Mobile.getText(findTestObject('Mobile/SalesReturn/Piece-1st'), 2, FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot('After edit the quty')

Mobile.verifyNotMatch(After_edit_piece, Before_edit_piece, false, FailureHandling.STOP_ON_FAILURE)

KeywordUtil.logInfo('Able to edit the quantity after click the done btn in Sales Return')

Mobile.comment('Able to Add 2 products for sales return')

'2nd product'
Mobile.tap(findTestObject('Mobile/Common/Icon_X'), 0)

Mobile.verifyElementVisible(findTestObject('Mobile/Common/Icon_Search'), 5, FailureHandling.STOP_ON_FAILURE)

Mobile.tap(findTestObject('Mobile/Common/Icon_Search'), 0)

GlobalVariable.S_Sku_Name = findTestData('Mobile Input Data/SalesReturn_Replacement').getValue('Return_SKU_Name', 3)

Mobile.setText(findTestObject('Mobile/Common/Field_Search_EnterText'), GlobalVariable.S_Sku_Name, 0)

Mobile.takeScreenshot()

Mobile.verifyElementVisible(findTestObject('Mobile/SalesReturn/ProductName-1st'), 3, FailureHandling.STOP_ON_FAILURE)

Mobile.verifyElementText(findTestObject('Mobile/SalesReturn/ProductName-1st'), GlobalVariable.S_Sku_Name, FailureHandling.STOP_ON_FAILURE)

KeywordUtil.logInfo('The list of the products  displayed in the Sales return screen')

Mobile.takeScreenshot()

Mobile.tap(findTestObject('Mobile/SalesReturn/Piece-1st'), 0)

Mobile.delay(2)

Mobile.verifyElementExist(findTestObject('Mobile/SalesReturn/RETURN_Screen_Title'), 0)

Mobile.setText(findTestObject('Mobile/SalesReturn/Return-InvoiceNo'), findTestData('Mobile Input Data/CallAnalysis').getValue('InvoiceNo', 1), 0, FailureHandling.OPTIONAL)

Mobile.takeScreenshot()

MRP2 = Mobile.getText(findTestObject('Mobile/SalesReturn/Return-MRP'), 0)

a = Mobile.getAttribute(findTestObject('Mobile/SalesReturn/Return-Select Reason'), 'x', 2, FailureHandling.OPTIONAL)

b = Mobile.getAttribute(findTestObject('Mobile/SalesReturn/Return-Select Reason'), 'y', 2, FailureHandling.OPTIONAL)

height = Mobile.getAttribute(findTestObject('Mobile/SalesReturn/Return-Select Reason'), 'height', 2, FailureHandling.OPTIONAL)

x_point = (Integer.parseInt(a) + 50)

y_point = ((Integer.parseInt(b) + Integer.parseInt(height)) + 20)

Mobile.tap(findTestObject('Mobile/SalesReturn/Return-Select Reason'), 0)

Mobile.takeScreenshot()

Reason2 = 'Expired'

GlobalVariable.S_ReasonType = Reason2

Mobile.setText(findTestObject('Mobile/SalesReturn/Return-Select Reason'), Reason2, 0, FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

Mobile.tapAtPosition(x_point, y_point)

not_run: Mobile.tap(findTestObject('Mobile/SalesReturn/Return-Select Reason'), 0)

not_run: Mobile.takeScreenshot()

not_run: Reason2 = Mobile.getText(findTestObject('Mobile/SalesReturn/Expired-option'), 0)

not_run: GlobalVariable.S_ReasonType = Reason2

not_run: Mobile.tap(findTestObject('Mobile/SalesReturn/Expired-option'), 0)

Mobile.tap(findTestObject('Mobile/SalesReturn/Return-Piece'), 0, FailureHandling.STOP_ON_FAILURE)

GlobalVariable.keypadValue = findTestData('Mobile Input Data/SalesReturn_Replacement').getValue('Qty', 1)

GlobalVariable.PieceQty = GlobalVariable.keypadValue

Mobile.tap(findTestObject('Mobile/Common/Global_Number_keypad'), 0)

Mobile.tap(findTestObject('Mobile/Common/Btn_Done'), 0)

Mobile.delay(2)

Total_Return_amount_Returnscrn = Mobile.getText(findTestObject('Mobile/Seller_2/SalesReturn_Replacement/Return_product_amount'), 0)

KeywordUtil.logInfo(Total_Return_amount_Returnscrn + ': Total return amount')

Mobile.tap(findTestObject('Mobile/SalesReturn/SR-Next btn'), 0)

Mobile.delay(2)

Mobile.verifyElementExist(findTestObject('Mobile/SalesReturn/Sales Return Summary-Title'), 5)

Mobile.delay(2)

Mobile.comment('Validation for Added products loading in summary screen')

Mobile.verifyElementText(findTestObject('Mobile/SalesReturn/SR-ProductName'), GlobalVariable.S_Sku_Name, FailureHandling.STOP_ON_FAILURE)

Mobile.verifyElementText(findTestObject('Mobile/SalesReturn/SR-Reason'), Reason2, FailureHandling.STOP_ON_FAILURE)

