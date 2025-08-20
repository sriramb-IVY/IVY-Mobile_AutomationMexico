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

'Credit_Limit_Scenario_06:-\r\n\r\n1. Total invoice amount should be less than the increased credit limit\r\n2. \'Order exceeds Credit Balance\' toast message should not be displayed in the Stock&Order screen.\r\n3. \'Amount exceeds warning Credit Balance.\' toast message should not be displayed in the summary screen.\r\n4. \'Invoice created successfully.\' alert should be displayed in the Summary screen.\r\n'
KeywordUtil.logInfo('Scenario_06::::--------------->>>>>>>>>>>')

Credit_Index = 1

Mobile.callTestCase(findTestCase('Product_Mobile/Common/TradeCoverage(SelectRetailer)'), [('RetailerName') : findTestData('Mobile Input Data/CreditManagement').getValue('Retailer_Name', Credit_Index)], FailureHandling.STOP_ON_FAILURE)

Mobile.setText(findTestObject('Mobile/Common/Field_Search_EnterText'), findTestData('Mobile Input Data/CreditManagement').getValue('CM_SKU_Name', 1), 5)

Mobile.callTestCase(findTestCase('Product_Mobile/CM/Reusables/DB/Get_Credit_Bal_DB'), [('RetailerCode') : findTestData('Mobile Input Data/CreditManagement').getValue('Retailer_Name', Credit_Index)], FailureHandling.STOP_ON_FAILURE)

Credit_Balance = GlobalVariable.Credit_Balance

KeywordUtil.logInfo('After increased credit limit, credit balance in the ADM_Retailer_Over_Due which is consider as our credit limit :' + Credit_Balance)

SKU_Piece_Price = findTestData('Mobile Input Data/CreditManagement').getValue('SKU_Piece_Price', 1)

KeywordUtil.logInfo('SKU Piece Price:' + SKU_Piece_Price)

Count = (Double.parseDouble(Credit_Balance) / Double.parseDouble(SKU_Piece_Price))

KeywordUtil.logInfo(Count.toString())

Qty = (Count.round() - 1)

String PieceQty1 = Qty

GlobalVariable.Qty = PieceQty1

KeywordUtil.logInfo('SKU Qty in Piece :' + PieceQty1)

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Enter_PIECE_Qty'), [:], FailureHandling.STOP_ON_FAILURE)

Current_Invoice_SKU_TOTAL = Mobile.getText(findTestObject('Mobile/OrderInvoice/OrderInvoiceScrn-SkuTotal'), 0)

Increase_Credit_Limit_Value = findTestData('Mobile Input Data/CreditManagement').getValue('Increase_Value', Credit_Index)

KeywordUtil.logInfo(((((('The retailer name is "' + GlobalVariable.RetailerName) + '" and its increased credit limit "') + Increase_Credit_Limit_Value) + '" But, I have creating invoice amount as "') + Current_Invoice_SKU_TOTAL) + '".')

'1. Total invoice amount should be less than the increased credit limit'
Mobile.callTestCase(findTestCase('Product_Mobile/CM/Reusables/DB/Get_Over_Due_As_Last_Invoice_Amt'), [('RetailerCode') : findTestData('Mobile Input Data/CreditManagement').getValue('Retailer_Name', Credit_Index)], FailureHandling.STOP_ON_FAILURE)

Last_Invoice_Amt = GlobalVariable.label

KeywordUtil.logInfo((Last_Invoice_Amt.toString() + ' : This is the last invoice amount of this particular retailer name is : ') + GlobalVariable.RetailerName)

Scenario_06_Invoice_Amt = (Double.parseDouble(Current_Invoice_SKU_TOTAL) + Double.parseDouble(Last_Invoice_Amt))

Mobile.verifyLessThan(Scenario_06_Invoice_Amt, Double.parseDouble(Increase_Credit_Limit_Value), FailureHandling.CONTINUE_ON_FAILURE)

Mobile.takeScreenshot()

Mobile.tap(findTestObject('Mobile/Common/Btn_Next'), 0)

WebUI.callTestCase(findTestCase('Product_Mobile/Common/MustSell_Alert'), [:], FailureHandling.STOP_ON_FAILURE)

'2. \'Order exceeds Credit Balance\' toast message should be displayed in the Stock&Order screen.'

Mobile.callTestCase(findTestCase('Product_Mobile/CM/Reusables/Toast_Negative_Validation'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('Test Cases/VBL_Mobile/Re-Usables/Tap_Next_Btn_ApplyschemeScrn_CT'), [:], FailureHandling.STOP_ON_FAILURE)

Mobile.tap(findTestObject('Mobile/SummaryScreen/SummaryScreen-Invoice btn'), 0)

'3. \'Amount exceeds warning Credit Balance.\' toast message should not be displayed in the summary screen.'
Mobile.callTestCase(findTestCase('Product_Mobile/CM/Reusables/Toast_Negative_Validation'), [:], FailureHandling.STOP_ON_FAILURE)

Mobile.callTestCase(findTestCase('Product_Mobile/Common/DatePicker_and _Navigation/Mobile Date Picker - Current Date'), [:], FailureHandling.OPTIONAL)

Mobile.tap(findTestObject('Mobile/Common/Btn_Confirm'), 0)

Mobile.takeScreenshot()

'4. \'Invoice created successfully.\' alert should be displayed in the Summary screen.'
GlobalVariable.Alert = findTestData('Mobile Input Data/CreditManagement').getValue('Alert_Popup01', Credit_Index)

Mobile.waitForElementPresent(findTestObject('Mobile/Credit Management/Alert_Text'), 5, FailureHandling.CONTINUE_ON_FAILURE)

Mobile.takeScreenshot()

Mobile.verifyElementText(findTestObject('Mobile/Credit Management/Alert_Text'), GlobalVariable.Alert, FailureHandling.CONTINUE_ON_FAILURE)

Mobile.tap(findTestObject('Mobile/Common/Btn_OK'), 0)

Mobile.callTestCase(findTestCase('Product_Mobile/CM/Reusables/Again_Goto_StockOrderScreen'), [:], FailureHandling.STOP_ON_FAILURE)

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
'Credit_Limit_Scenario_07 :-\r\n\r\n1. Total invoice amount should be greater than the increased credit limit.\r\n2. \'Order exceeds the credit limit\' toast msg should be displayed in the Stock&Order screen.\r\n3. \'Credit Balance not available.\' alert should be displayed in the summary screen. '
KeywordUtil.logInfo('Scenario_07::::--------------->>>>>>>>>>>')

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Clear_Star_Filter_Default_Selection'), [:], FailureHandling.CONTINUE_ON_FAILURE)

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Search_Product'), [:], FailureHandling.CONTINUE_ON_FAILURE)

Mobile.setText(findTestObject('Mobile/Common/Field_Search_EnterText'), findTestData('Mobile Input Data/CreditManagement').getValue('CM_SKU_Name', 1), 5)

Last_Invoice_Amt = Scenario_06_Invoice_Amt

KeywordUtil.logInfo('Scenario_06 Invoice Amount : ' + Last_Invoice_Amt.toString())

Increase_Credit_Limit_Value = findTestData('Mobile Input Data/CreditManagement').getValue('Increase_Value', Credit_Index)

Count = ((Double.parseDouble(Increase_Credit_Limit_Value) - Last_Invoice_Amt) / Double.parseDouble(SKU_Piece_Price))

KeywordUtil.logInfo(Count.toString())

Qty = (Count.round() + 1)

PieceQty1 = Qty

GlobalVariable.Qty = PieceQty1

KeywordUtil.logInfo('SKU Qty in Piece :' + PieceQty1)

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Enter_PIECE_Qty'), [:], FailureHandling.STOP_ON_FAILURE)

Current_Invoice_SKU_TOTAL = Mobile.getText(findTestObject('Mobile/OrderInvoice/OrderInvoiceScrn-SkuTotal'), 0)

KeywordUtil.logInfo(((((('The retailer name is "' + GlobalVariable.RetailerName) + '" and its increased credit limit "') + Increase_Credit_Limit_Value) + '" But, I have creating invoice amount as "') + Current_Invoice_SKU_TOTAL) + '".')

'1. Total invoice amount should be greater than the increased credit limit.'
Invoice_Amt = (Double.parseDouble(Current_Invoice_SKU_TOTAL) + Last_Invoice_Amt)

Mobile.verifyGreaterThan(Invoice_Amt, Double.parseDouble(Increase_Credit_Limit_Value), FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

Mobile.tap(findTestObject('Mobile/Common/Btn_Next'), 0)

WebUI.callTestCase(findTestCase('Product_Mobile/Common/MustSell_Alert'), [:], FailureHandling.STOP_ON_FAILURE)

'2. \'Order exceeds the credit limit\' toast msg should be displayed in the Stock&Order screen.'
Mobile.callTestCase(findTestCase('Product_Mobile/CM/Reusables/Toast_Validation'), [('Expecting_Toast_Text') : findTestData('Mobile Input Data/CreditManagement').getValue('Toast_Message01', Credit_Index)], FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('Test Cases/VBL_Mobile/Re-Usables/Tap_Next_Btn_ApplyschemeScrn_CT'), [:], FailureHandling.STOP_ON_FAILURE)

Mobile.tap(findTestObject('Mobile/SummaryScreen/SummaryScreen-Invoice btn'), 0)

Mobile.takeScreenshot()

'3. \'Credit Balance not available.\' alert should be displayed in the summary screen. '
GlobalVariable.Alert = findTestData('Mobile Input Data/CreditManagement').getValue('Alert_Popup02', Credit_Index)

Mobile.waitForElementPresent(findTestObject('Mobile/Credit Management/Alert_Text'), 5, FailureHandling.CONTINUE_ON_FAILURE)

Mobile.verifyElementText(findTestObject('Mobile/Credit Management/Alert_Text'), GlobalVariable.Alert, FailureHandling.CONTINUE_ON_FAILURE)

Mobile.tap(findTestObject('Mobile/Common/Btn_OK'), 0)

Mobile.tap(findTestObject('Mobile/Common/Icon_Back'), 1, FailureHandling.OPTIONAL)

Mobile.delay(2)

Mobile.tap(findTestObject('Mobile/Common/Btn_OK'), 2, FailureHandling.OPTIONAL)

Mobile.delay(2)

Mobile.tap(findTestObject('Mobile/Common/Icon_Back'), 1, FailureHandling.OPTIONAL)

Mobile.delay(2)

Mobile.tap(findTestObject('Mobile/Common/Icon_Back'), 1, FailureHandling.OPTIONAL)

Mobile.delay(2)

Mobile.tap(findTestObject('Mobile/Common/Btn_OK'), 0, FailureHandling.OPTIONAL)

WebUI.callTestCase(findTestCase('Product_Mobile/Common/Call_Analysis'), [:], FailureHandling.CONTINUE_ON_FAILURE)

