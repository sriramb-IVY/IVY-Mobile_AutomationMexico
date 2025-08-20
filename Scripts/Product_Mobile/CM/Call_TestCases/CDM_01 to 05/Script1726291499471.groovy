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

'Credit_Limit_Scenario_01:-\r\n\r\n1. Total invoice amount should be less than the credit limit\r\n2. \'Order exceeds Credit Balance\' toast message should not be displayed in the Stock&Order screen.\r\n3. \'Amount exceeds warning Credit Balance.\' toast message should not be displayed in the summary screen.\r\n4. \'Invoice created successfully.\' alert should be displayed in the Summary screen.\r\n'
KeywordUtil.logInfo('Scenario_01::::--------------->>>>>>>>>>>')

Credit_Index = 1

Mobile.callTestCase(findTestCase('Product_Mobile/Common/TradeCoverage(SelectRetailer)'), [('RetailerName') : findTestData('Mobile Input Data/CreditManagement').getValue('Retailer_Name', Credit_Index)], FailureHandling.STOP_ON_FAILURE)

Mobile.setText(findTestObject('Mobile/Common/Field_Search_EnterText'), findTestData('Mobile Input Data/CreditManagement').getValue('CM_SKU_Name', 1), 5)

Credit_Limit = findTestData('Mobile Input Data/CreditManagement').getValue('Credit_Limit', Credit_Index)

KeywordUtil.logInfo('Credit_Limit :' + Credit_Limit)

SKU_Piece_Price = findTestData('Mobile Input Data/CreditManagement').getValue('SKU_Piece_Price', 1)

KeywordUtil.logInfo('SKU Piece Price:' + SKU_Piece_Price)

Count = (Double.parseDouble(Credit_Limit) / Double.parseDouble(SKU_Piece_Price))

KeywordUtil.logInfo(Count.toString())

Qty = (Count.round() - 1)

String PieceQty = Qty

GlobalVariable.Qty = PieceQty

KeywordUtil.logInfo('SKU Qty inn Piece :' + PieceQty)

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Enter_PIECE_Qty'), [:], FailureHandling.STOP_ON_FAILURE)

Current_Invoice_SKU_TOTAL = Mobile.getText(findTestObject('Mobile/OrderInvoice/OrderInvoiceScrn-SkuTotal'), 0)

KeywordUtil.logInfo(((((('The retailer name is "' + GlobalVariable.RetailerName) + '" and its credit limit "') + Credit_Limit) + '" But, I have creating invoice amount as "') + Current_Invoice_SKU_TOTAL) + '".')

'User enter the value less than the credit limit:'
Last_Invoice_Amt = 0.0

KeywordUtil.logInfo('This is first invoice so we assign here zero' + Last_Invoice_Amt.toString())

Scenario_01_Invoice_Amt = (Double.parseDouble(Current_Invoice_SKU_TOTAL) + Last_Invoice_Amt)

'1. Total invoice amount should be less than the credit limit'
Mobile.verifyLessThan(Scenario_01_Invoice_Amt, Double.parseDouble(Credit_Limit), FailureHandling.CONTINUE_ON_FAILURE)

Mobile.takeScreenshot()

Mobile.tap(findTestObject('Mobile/Common/Btn_Next'), 0)

WebUI.callTestCase(findTestCase('Product_Mobile/Common/MustSell_Alert'), [:], FailureHandling.STOP_ON_FAILURE)

'2. \'Order exceeds Credit Balance\' toast message should not be displayed in the Stock&Order screen.'
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

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
'Credit_Limit_Scenario_02:-\r\n\r\n1. Total invoice amount should be equal to the credit limit\r\n2. \'Order exceeds Credit Balance\' toast message should not be displayed in the Stock&Order screen.\r\n3. \'Amount exceeds warning Credit Balance.\' toast message should not be displayed in the summary screen.\r\n4. \'Invoice created successfully.\' alert should be displayed in the Summary screen.\r\n'
KeywordUtil.logInfo('Scenario_02::::--------------->>>>>>>>>>>')

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Clear_Star_Filter_Default_Selection'), [:], FailureHandling.CONTINUE_ON_FAILURE)

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Search_Product'), [:], FailureHandling.CONTINUE_ON_FAILURE)

Mobile.setText(findTestObject('Mobile/Common/Field_Search_EnterText'), findTestData('Mobile Input Data/CreditManagement').getValue('CM_SKU_Name', 1), 5)

Last_Invoice_Amt = Scenario_01_Invoice_Amt

KeywordUtil.logInfo('Scenario_01 Invoice Amount : ' + Last_Invoice_Amt.toString())

Count = ((Double.parseDouble(Credit_Limit) - Last_Invoice_Amt) / Double.parseDouble(SKU_Piece_Price))

KeywordUtil.logInfo(Count.toString())

Qty = Count.round()

PieceQty = Qty

GlobalVariable.Qty = PieceQty

KeywordUtil.logInfo('SKU Qty inn Piece :' + PieceQty)

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Enter_PIECE_Qty'), [:], FailureHandling.STOP_ON_FAILURE)

Current_Invoice_SKU_TOTAL = Mobile.getText(findTestObject('Mobile/OrderInvoice/OrderInvoiceScrn-SkuTotal'), 0)

KeywordUtil.logInfo(((((('The retailer name is "' + GlobalVariable.RetailerName) + '" and its credit limit "') + Credit_Limit) + '" But, I have creating invoice amount as "') + Current_Invoice_SKU_TOTAL) + '".')

'User enter the value Equal to the credit limit:'
Scenario_02_Invoice_Amt = (Double.parseDouble(Current_Invoice_SKU_TOTAL) + Last_Invoice_Amt)

'1. Total invoice amount should be equal to the credit limit'
Mobile.verifyEqual(Scenario_02_Invoice_Amt, Double.parseDouble(Credit_Limit), FailureHandling.CONTINUE_ON_FAILURE)

Mobile.takeScreenshot()

Mobile.tap(findTestObject('Mobile/Common/Btn_Next'), 0)

WebUI.callTestCase(findTestCase('Product_Mobile/Common/MustSell_Alert'), [:], FailureHandling.STOP_ON_FAILURE)

'2. \'Order exceeds Credit Balance\' toast message should not be displayed in the Stock&Order screen.'
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
'Credit_Limit_Scenario_03:-\r\n\r\n1. \'Not allowed to take order,credit balance 0\' soft alert should be displayed in the StoreActivities Screen.\r\n2. Total invoice amount should be greater than the credit limit.\r\n3. \'Order exceeds the credit limit\' toast msg should not be displayed in the Stock&Order screen.\r\n4. \'Credit Balance not available.\' alert should be displayed in the summary screen. '
KeywordUtil.logInfo('Scenario_03::::--------------->>>>>>>>>>>')

'1. \'Not allowed to take order,credit balance 0\' Alert should not be displayed in the Stock&Order Screen.'
Last_Invoice_Amt = Scenario_02_Invoice_Amt

KeywordUtil.logInfo('Scenario_02 Invoice Amount : ' + Last_Invoice_Amt.toString())

Count = (Double.parseDouble(Credit_Limit) - Last_Invoice_Amt)

Mobile.verifyEqual(Count, 0)

GlobalVariable.Alert = findTestData('Mobile Input Data/CreditManagement').getValue('Alert_Popup03', Credit_Index)

Mobile.waitForElementPresent(findTestObject('Mobile/Credit Management/Alert_Text'), 5, FailureHandling.CONTINUE_ON_FAILURE)

Mobile.takeScreenshot()

Mobile.verifyElementText(findTestObject('Mobile/Credit Management/Alert_Text'), GlobalVariable.Alert, FailureHandling.CONTINUE_ON_FAILURE)

Mobile.tap(findTestObject('Object Repository/Mobile/Credit Management/Btn-CONTINUE'), 0)

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Clear_Star_Filter_Default_Selection'), [:], FailureHandling.CONTINUE_ON_FAILURE)

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Search_Product'), [:], FailureHandling.CONTINUE_ON_FAILURE)

Mobile.setText(findTestObject('Mobile/Common/Field_Search_EnterText'), findTestData('Mobile Input Data/CreditManagement').getValue('CM_SKU_Name', 1), 5)

Count = ((Double.parseDouble(Credit_Limit) - Last_Invoice_Amt) / Double.parseDouble(SKU_Piece_Price))

KeywordUtil.logInfo(Count.toString())

Qty = (Count.round() + 1)

PieceQty = Qty

GlobalVariable.Qty = PieceQty

KeywordUtil.logInfo('SKU Qty in Piece :' + PieceQty)

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Enter_PIECE_Qty'), [:], FailureHandling.STOP_ON_FAILURE)

Current_Invoice_SKU_TOTAL = Mobile.getText(findTestObject('Mobile/OrderInvoice/OrderInvoiceScrn-SkuTotal'), 0)

KeywordUtil.logInfo(((((('The retailer name is "' + GlobalVariable.RetailerName) + '" and its credit limit "') + Credit_Limit) + '" But, I have creating invoice amount as "') + Current_Invoice_SKU_TOTAL) + '".')

'2. Total invoice amount should be greater than the credit limit.'
Invoice_Amt = (Double.parseDouble(Current_Invoice_SKU_TOTAL) + Last_Invoice_Amt)

Mobile.verifyGreaterThan(Invoice_Amt, Double.parseDouble(Credit_Limit), FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

Mobile.tap(findTestObject('Mobile/Common/Btn_Next'), 0)

'3. \'Order exceeds the credit limit\' toast msg should not be displayed in the Stock&Order screen.'
WebUI.callTestCase(findTestCase('Product_Mobile/Common/MustSell_Alert'), [:], FailureHandling.STOP_ON_FAILURE)

Mobile.callTestCase(findTestCase('Product_Mobile/CM/Reusables/Toast_Negative_Validation'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('Test Cases/VBL_Mobile/Re-Usables/Tap_Next_Btn_ApplyschemeScrn_CT'), [:], FailureHandling.STOP_ON_FAILURE)

Mobile.tap(findTestObject('Mobile/SummaryScreen/SummaryScreen-Invoice btn'), 0)

Mobile.takeScreenshot()

' 4. \'Credit Balance not available.\' alert should be displayed in the summary screen. '
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

'Collecting the pending invoice amount for credit limit validation'
Mobile.callTestCase(findTestCase('Product_Mobile/CM/Reusables/Invoice_Amt_Collected'), [('Credit_Index') : Credit_Index], FailureHandling.STOP_ON_FAILURE)

Mobile.callTestCase(findTestCase('Product_Mobile/CM/Reusables/Again_Goto_StockOrderScreen'), [:], FailureHandling.STOP_ON_FAILURE)

///////////////////////////////////////////////////////////////////////////////////////////////////////////
'Credit_Limit_Scenario_04:-\r\n\r\n1. Total invoice amount should be equal to the credit limit after one of the pending invoice amount has been collected.\r\n2. \'Order exceeds Credit Balance\' toast message should not be displayed in the Stock&Order screen.\r\n3. \'Amount exceeds warning Credit Balance.\' toast message should not be displayed in the summary screen.\r\n4. \'Invoice created successfully.\' alert should be displayed in the Summary screen.\r\n'
KeywordUtil.logInfo('Scenario_04::::--------------->>>>>>>>>>>')

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Clear_Star_Filter_Default_Selection'), [:], FailureHandling.CONTINUE_ON_FAILURE)

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Search_Product'), [:], FailureHandling.CONTINUE_ON_FAILURE)

Mobile.setText(findTestObject('Mobile/Common/Field_Search_EnterText'), findTestData('Mobile Input Data/CreditManagement').getValue('CM_SKU_Name', 1), 5)

Last_Invoice_Amt = Scenario_02_Invoice_Amt

KeywordUtil.logInfo('Scenario_03 invoice not allow to generate so we consider Scenario_02 Invoice Amount : ' + Last_Invoice_Amt.toString())

Pending_Invoice_Amt = (Last_Invoice_Amt - Double.parseDouble(GlobalVariable.C_TransactionAmt))

KeywordUtil.logInfo('Collect the last invoice amount so this is the pending invoice amount : ' + Pending_Invoice_Amt.toString())

Count = ((Double.parseDouble(Credit_Limit) - Pending_Invoice_Amt) / Double.parseDouble(SKU_Piece_Price))

KeywordUtil.logInfo(Count.toString())

Qty = Count.round()

PieceQty = Qty

GlobalVariable.Qty = PieceQty

KeywordUtil.logInfo('SKU Qty inn Piece :' + PieceQty)

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Enter_PIECE_Qty'), [:], FailureHandling.STOP_ON_FAILURE)

Current_Invoice_SKU_TOTAL = Mobile.getText(findTestObject('Mobile/OrderInvoice/OrderInvoiceScrn-SkuTotal'), 0)

KeywordUtil.logInfo(((((('The retailer name is "' + GlobalVariable.RetailerName) + '" and its credit limit "') + Credit_Limit) + '" But, I have creating invoice amount as "') + Current_Invoice_SKU_TOTAL) + '".')

Scenario_04_Invoice_Amt = (Double.parseDouble(Current_Invoice_SKU_TOTAL) + Pending_Invoice_Amt)

'1. Total invoice amount should be equal to the credit limit after one of the pending invoice amount has been collected.'
Mobile.verifyEqual(Scenario_04_Invoice_Amt, Double.parseDouble(Credit_Limit), FailureHandling.CONTINUE_ON_FAILURE)

Mobile.takeScreenshot()

Mobile.tap(findTestObject('Mobile/Common/Btn_Next'), 0)

WebUI.callTestCase(findTestCase('Product_Mobile/Common/MustSell_Alert'), [:], FailureHandling.STOP_ON_FAILURE)

'2. \'Order exceeds Credit Balance\' toast message should not be displayed in the Stock&Order screen.'
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

Mobile.verifyElementText(findTestObject('Mobile/Credit Management/Alert_Text'), GlobalVariable.Alert, FailureHandling.CONTINUE_ON_FAILURE)

Mobile.tap(findTestObject('Mobile/Common/Btn_OK'), 0)

Mobile.callTestCase(findTestCase('Product_Mobile/CM/Reusables/Again_Goto_StockOrderScreen'), [:], FailureHandling.STOP_ON_FAILURE)

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
'Credit_Limit_Scenario_05:-\r\n\r\n1. \'Not allowed to take order,credit balance 0\' soft alert should be displayed in the StoreActivities Screen.\r\n2. Total invoice amount should be greater than the credit limit after one of the pending invoice amount has been collected.\r\n3. \'Order exceeds the credit limit\' toast msg should not be displayed in the Stock&Order screen.\r\n4. \'Credit Balance not available.\' alert should be displayed in the summary screen. '
KeywordUtil.logInfo('Scenario_05::::--------------->>>>>>>>>>>')

'1. \'Not allowed to take order,credit balance 0\' soft alert should be displayed in the Store activities Screen.'
Last_Invoice_Amt = Scenario_04_Invoice_Amt

KeywordUtil.logInfo('Scenario_04 Invoice Amount : ' + Last_Invoice_Amt.toString())

Count = (Double.parseDouble(Credit_Limit) - Last_Invoice_Amt)

Mobile.verifyEqual(Count, 0)

GlobalVariable.Alert = findTestData('Mobile Input Data/CreditManagement').getValue('Alert_Popup03', Credit_Index)

Mobile.waitForElementPresent(findTestObject('Mobile/Credit Management/Alert_Text'), 5, FailureHandling.CONTINUE_ON_FAILURE)

Mobile.takeScreenshot()

Mobile.verifyElementText(findTestObject('Mobile/Credit Management/Alert_Text'), GlobalVariable.Alert, FailureHandling.CONTINUE_ON_FAILURE)

Mobile.tap(findTestObject('Object Repository/Mobile/Credit Management/Btn-CONTINUE'), 0)

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Clear_Star_Filter_Default_Selection'), [:], FailureHandling.CONTINUE_ON_FAILURE)

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Search_Product'), [:], FailureHandling.CONTINUE_ON_FAILURE)

Mobile.setText(findTestObject('Mobile/Common/Field_Search_EnterText'), findTestData('Mobile Input Data/CreditManagement').getValue('CM_SKU_Name', 1), 5)

Count = ((Double.parseDouble(Credit_Limit) - Last_Invoice_Amt) / Double.parseDouble(SKU_Piece_Price))

KeywordUtil.logInfo(Count.toString())

Qty = (Count.round() + 1)

PieceQty = Qty

GlobalVariable.Qty = PieceQty

KeywordUtil.logInfo('SKU Qty inn Piece :' + PieceQty)

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Enter_PIECE_Qty'), [:], FailureHandling.STOP_ON_FAILURE)

Current_Invoice_SKU_TOTAL = Mobile.getText(findTestObject('Mobile/OrderInvoice/OrderInvoiceScrn-SkuTotal'), 0)

KeywordUtil.logInfo(((((('The retailer name is "' + GlobalVariable.RetailerName) + '" and its credit limit "') + Credit_Limit) + '" But, I have creating invoice amount as "') + Current_Invoice_SKU_TOTAL) + '".')

'2. Total invoice amount should be greater than the credit limit after one of the pending invoice amount has been collected.'
Invoice_Amt = (Double.parseDouble(Current_Invoice_SKU_TOTAL) + Last_Invoice_Amt)

Mobile.verifyGreaterThan(Invoice_Amt, Double.parseDouble(Credit_Limit), FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

Mobile.tap(findTestObject('Mobile/Common/Btn_Next'), 0)

WebUI.callTestCase(findTestCase('Product_Mobile/Common/MustSell_Alert'), [:], FailureHandling.STOP_ON_FAILURE)

'3. \'Order exceeds the credit limit\' toast msg should not be displayed in the Stock&Order screen.'
Mobile.callTestCase(findTestCase('Product_Mobile/CM/Reusables/Toast_Negative_Validation'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('Test Cases/VBL_Mobile/Re-Usables/Tap_Next_Btn_ApplyschemeScrn_CT'), [:], FailureHandling.STOP_ON_FAILURE)

Mobile.tap(findTestObject('Mobile/SummaryScreen/SummaryScreen-Invoice btn'), 0)

Mobile.takeScreenshot()

'4. \'Credit Balance not available.\' alert should be displayed in the summary screen. '
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

