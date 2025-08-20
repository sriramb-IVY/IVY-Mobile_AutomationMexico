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

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Module_Config_Enable_Update_DB'), [('Seller_Type_Id') : findTestData('Mobile Input Data/Module Configuration').getValue('Sales_Rep_Id', 1), ('Config_Code') : findTestData('Mobile Input Data/Module Configuration').getValue('Config_Code', 1), ('Rfield') : 1], 
    FailureHandling.STOP_ON_FAILURE)

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Module_Config_Enable_Update_DB'), [('Seller_Type_Id') : findTestData('Mobile Input Data/Module Configuration').getValue('Sales_Rep_Id', 1), ('Config_Code') : findTestData('Mobile Input Data/Module Configuration').getValue('Config_Code', 2), ('Rfield') : 2], 
    FailureHandling.STOP_ON_FAILURE)

Mobile.callTestCase(findTestCase('VBL_Mobile/Re-Usables/Web Login and Navigation/ClearCashe Login'), [:], FailureHandling.STOP_ON_FAILURE)

Credit_Index = 2

Mobile.callTestCase(findTestCase('Product_Mobile/CM/Reusables/DB/Delete_Pendingbill_UpdateOverdue'), [('Row_Index') : [2]], FailureHandling.STOP_ON_FAILURE)

not_run: Mobile.callTestCase(findTestCase('Product_Mobile/CM/Reusables/DB/Web and DB Validation'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('Product_Mobile/Common/Mobile_Login/VanSeller_Login-With Reset'), [:], FailureHandling.STOP_ON_FAILURE)

Mobile.callTestCase(findTestCase('Product_Mobile/CM/Reusables/Stock_Update_Mobile'), [('ProductName') : findTestData('Mobile Input Data/CreditManagement').getValue('CM_SKU_Name', 1), ('Quantity') : '1000'], FailureHandling.STOP_ON_FAILURE)

//
//Mobile.startApplication(GlobalVariable.APK_File, false)
//
//Mobile.tap(findTestObject('Mobile/Common/Btn_Menu'), 10)
'Warn_Credit_Limit_Scenario_01:-\r\n\r\n1. Total invoice amount should be less than the warn credit limit\r\n2. \'Amount exceeds warning Credit Balance.\' toast message should not be displayed in the summary screen.\r\n2. \'Invoice created successfully.\' alert should be displayed in the summary screen.'
KeywordUtil.logInfo('Scenario_01::::--------------->>>>>>>>>>>')

Mobile.callTestCase(findTestCase('Product_Mobile/Common/TradeCoverage(SelectRetailer)'), [('RetailerName') : findTestData('Mobile Input Data/CreditManagement').getValue('Retailer_Name', Credit_Index)], FailureHandling.STOP_ON_FAILURE)

Mobile.setText(findTestObject('Mobile/Common/Field_Search_EnterText'), findTestData('Mobile Input Data/CreditManagement').getValue('CM_SKU_Name', 1), 5)

Warn_Credit_Limit = findTestData('Mobile Input Data/CreditManagement').getValue('Warn_Credit_Limit', Credit_Index)

KeywordUtil.logInfo('Warn_Credit_Limit :' + Warn_Credit_Limit)

SKU_Piece_Price = findTestData('Mobile Input Data/CreditManagement').getValue('SKU_Piece_Price', 1)

KeywordUtil.logInfo('SKU Piece Price:' + SKU_Piece_Price)

Count = (Double.parseDouble(Warn_Credit_Limit) / Double.parseDouble(SKU_Piece_Price))

KeywordUtil.logInfo(Count.toString())

Qty = (Count.round() - 1)

String PieceQty = Qty

GlobalVariable.Qty = PieceQty

KeywordUtil.logInfo('SKU Qty inn Piece :' + PieceQty)

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Enter_PIECE_Qty'), [:], FailureHandling.STOP_ON_FAILURE)

Current_Invoice_SKU_TOTAL = Mobile.getText(findTestObject('Mobile/OrderInvoice/OrderInvoiceScrn-SkuTotal'), 0)

KeywordUtil.logInfo(((((('The retailer name is "' + GlobalVariable.RetailerName) + '" and its warn credit limit "') + Warn_Credit_Limit) + '" But, I have creating invoice amount as "') + Current_Invoice_SKU_TOTAL) + '".')

'1. Total invoice amount should be less than the warn credit limit'
Last_Invoice_Amt = 0.0

KeywordUtil.logInfo('This is first invoice so we assign here zero' + Last_Invoice_Amt.toString())

Scenario_01_Invoice_Amt = (Double.parseDouble(Current_Invoice_SKU_TOTAL) + Last_Invoice_Amt)

Mobile.verifyLessThan(Scenario_01_Invoice_Amt, Double.parseDouble(Warn_Credit_Limit), FailureHandling.CONTINUE_ON_FAILURE)

Mobile.takeScreenshot()

Mobile.tap(findTestObject('Mobile/Common/Btn_Next'), 0)

WebUI.callTestCase(findTestCase('Product_Mobile/Common/MustSell_Alert'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('Test Cases/VBL_Mobile/Re-Usables/Tap_Next_Btn_ApplyschemeScrn_CT'), [:], FailureHandling.STOP_ON_FAILURE)

Mobile.tap(findTestObject('Mobile/SummaryScreen/SummaryScreen-Invoice btn'), 0)

'2. \'Amount exceeds warning Credit Balance.\' toast message should not be displayed in the summary screen.'
Mobile.callTestCase(findTestCase('Product_Mobile/CM/Reusables/Toast_Negative_Validation'), [:], FailureHandling.STOP_ON_FAILURE)

Mobile.callTestCase(findTestCase('Product_Mobile/Common/DatePicker_and _Navigation/Mobile Date Picker - Current Date'), [:], FailureHandling.OPTIONAL)

Mobile.tap(findTestObject('Mobile/Common/Btn_Confirm'), 0)

Mobile.takeScreenshot()

'3. \'Invoice created successfully.\' alert should be displayed in the summary screen.'
GlobalVariable.Alert = findTestData('Mobile Input Data/CreditManagement').getValue('Alert_Popup01', Credit_Index)

Mobile.waitForElementPresent(findTestObject('Mobile/Credit Management/Alert_Text'), 5, FailureHandling.CONTINUE_ON_FAILURE)

Mobile.takeScreenshot()

Mobile.verifyElementText(findTestObject('Mobile/Credit Management/Alert_Text'), GlobalVariable.Alert, FailureHandling.CONTINUE_ON_FAILURE)

Mobile.tap(findTestObject('Mobile/Common/Btn_OK'), 0)

Mobile.callTestCase(findTestCase('Product_Mobile/CM/Reusables/Again_Goto_StockOrderScreen'), [:], FailureHandling.STOP_ON_FAILURE)

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
'Warn_Credit_Limit_Scenario_02:-\r\n\r\n1. Total invoice amount should be equal to the warn credit limit\r\n2. \'Amount exceeds warning Credit Balance.\' toast message should not be displayed in the summary screen.\r\n3. \'Invoice created successfully.\' alert should be displayed in the summary screen.'
KeywordUtil.logInfo('Scenario_02::::--------------->>>>>>>>>>>')

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Clear_Star_Filter_Default_Selection'), [:], FailureHandling.CONTINUE_ON_FAILURE)

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Search_Product'), [:], FailureHandling.CONTINUE_ON_FAILURE)

Mobile.setText(findTestObject('Mobile/Common/Field_Search_EnterText'), findTestData('Mobile Input Data/CreditManagement').getValue('CM_SKU_Name', 1), 5)

Last_Invoice_Amt = Scenario_01_Invoice_Amt

KeywordUtil.logInfo('Scenario_01 Invoice Amount : ' + Last_Invoice_Amt.toString())

Count = ((Double.parseDouble(Warn_Credit_Limit) - Last_Invoice_Amt) / Double.parseDouble(SKU_Piece_Price))

KeywordUtil.logInfo(Count.toString())

Qty = Count.round()

PieceQty = Qty

GlobalVariable.Qty = PieceQty

KeywordUtil.logInfo('SKU Qty inn Piece :' + PieceQty)

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Enter_PIECE_Qty'), [:], FailureHandling.STOP_ON_FAILURE)

Current_Invoice_SKU_TOTAL = Mobile.getText(findTestObject('Mobile/OrderInvoice/OrderInvoiceScrn-SkuTotal'), 0)

KeywordUtil.logInfo(((((('The retailer name is "' + GlobalVariable.RetailerName) + '" and its warn credit limit "') + Warn_Credit_Limit) + '" But, I have creating invoice amount as "') + Current_Invoice_SKU_TOTAL) + '".')

'1. Total invoice amount should be equal to the warn credit limit.'
Scenario_02_Invoice_Amt = (Double.parseDouble(Current_Invoice_SKU_TOTAL) + Last_Invoice_Amt)

Mobile.verifyEqual(Scenario_02_Invoice_Amt, Double.parseDouble(Warn_Credit_Limit), FailureHandling.CONTINUE_ON_FAILURE)

Mobile.takeScreenshot()

Mobile.tap(findTestObject('Mobile/Common/Btn_Next'), 0)

WebUI.callTestCase(findTestCase('Product_Mobile/Common/MustSell_Alert'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('Test Cases/VBL_Mobile/Re-Usables/Tap_Next_Btn_ApplyschemeScrn_CT'), [:], FailureHandling.STOP_ON_FAILURE)

Mobile.tap(findTestObject('Mobile/SummaryScreen/SummaryScreen-Invoice btn'), 0)

'2. \'Amount exceeds warning Credit Balance.\' toast message should not be displayed in the summary screen.'
Mobile.callTestCase(findTestCase('Product_Mobile/CM/Reusables/Toast_Negative_Validation'), [:], FailureHandling.STOP_ON_FAILURE)

Mobile.callTestCase(findTestCase('Product_Mobile/Common/DatePicker_and _Navigation/Mobile Date Picker - Current Date'), [:], FailureHandling.OPTIONAL)

Mobile.tap(findTestObject('Mobile/Common/Btn_Confirm'), 0)

Mobile.takeScreenshot()

'3. \'Invoice created successfully.\' alert should be displayed in the summary screen.'
GlobalVariable.Alert = findTestData('Mobile Input Data/CreditManagement').getValue('Alert_Popup01', Credit_Index)

Mobile.waitForElementPresent(findTestObject('Mobile/Credit Management/Alert_Text'), 5, FailureHandling.CONTINUE_ON_FAILURE)

Mobile.takeScreenshot()

Mobile.verifyElementText(findTestObject('Mobile/Credit Management/Alert_Text'), GlobalVariable.Alert, FailureHandling.CONTINUE_ON_FAILURE)

Mobile.tap(findTestObject('Mobile/Common/Btn_OK'), 0)

Mobile.callTestCase(findTestCase('Product_Mobile/CM/Reusables/Again_Goto_StockOrderScreen'), [:], FailureHandling.STOP_ON_FAILURE)

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
'Warn_Credit_Limit_Scenario_03:-\r\n\r\n1. Total invoice amount should be greater than the warn credit limit\r\n2. \'Amount exceeds warning Credit Balance.\' toast message should be displayed in the summary screen.\r\n'
KeywordUtil.logInfo('Scenario_03::::--------------->>>>>>>>>>>')

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Clear_Star_Filter_Default_Selection'), [:], FailureHandling.CONTINUE_ON_FAILURE)

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Search_Product'), [:], FailureHandling.CONTINUE_ON_FAILURE)

Mobile.setText(findTestObject('Mobile/Common/Field_Search_EnterText'), findTestData('Mobile Input Data/CreditManagement').getValue('CM_SKU_Name', 1), 5)

Last_Invoice_Amt = Scenario_02_Invoice_Amt

KeywordUtil.logInfo('Scenario_02 Invoice Amount : ' + Last_Invoice_Amt.toString())

Count = ((Double.parseDouble(Warn_Credit_Limit) - Last_Invoice_Amt) / Double.parseDouble(SKU_Piece_Price))

KeywordUtil.logInfo(Count.toString())

Qty = (Count.round() + 1)

PieceQty = Qty

GlobalVariable.Qty = PieceQty

KeywordUtil.logInfo('SKU Qty in Piece :' + PieceQty)

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Enter_PIECE_Qty'), [:], FailureHandling.STOP_ON_FAILURE)

Current_Invoice_SKU_TOTAL = Mobile.getText(findTestObject('Mobile/OrderInvoice/OrderInvoiceScrn-SkuTotal'), 0)

KeywordUtil.logInfo(((((('The retailer name is "' + GlobalVariable.RetailerName) + '" and its warn credit limit "') + Warn_Credit_Limit) + '" But, I have creating invoice amount as "') + Current_Invoice_SKU_TOTAL) + '".')

'1. Total invoice amount should be greater than the warn credit limit'
Invoice_Amt = (Double.parseDouble(Current_Invoice_SKU_TOTAL) + Last_Invoice_Amt)

Mobile.verifyGreaterThan(Invoice_Amt, Double.parseDouble(Warn_Credit_Limit), FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

Mobile.tap(findTestObject('Mobile/Common/Btn_Next'), 0)

WebUI.callTestCase(findTestCase('Product_Mobile/Common/MustSell_Alert'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('Test Cases/VBL_Mobile/Re-Usables/Tap_Next_Btn_ApplyschemeScrn_CT'), [:], FailureHandling.STOP_ON_FAILURE)

Mobile.tap(findTestObject('Mobile/SummaryScreen/SummaryScreen-Invoice btn'), 0)

'2. \'Amount exceeds warning Credit Balance.\' toast message should be displayed in the summary screen.'
Mobile.callTestCase(findTestCase('Product_Mobile/CM/Reusables/Toast_Validation'), [('Expecting_Toast_Text') : findTestData('Mobile Input Data/CreditManagement').getValue('Toast_Message01', Credit_Index)], FailureHandling.STOP_ON_FAILURE)

Mobile.tap(findTestObject('Mobile/Common/Btn_Cancel'), 0)

Mobile.tap(findTestObject('Mobile/Common/Icon_Back'), 1, FailureHandling.OPTIONAL)

Mobile.delay(2)

Mobile.tap(findTestObject('Mobile/Common/Btn_OK'), 2, FailureHandling.OPTIONAL)

Mobile.delay(2)

Mobile.tap(findTestObject('Mobile/Common/Icon_Back'), 1, FailureHandling.OPTIONAL)

Mobile.delay(2)

Mobile.tap(findTestObject('Mobile/Common/Icon_Back'), 1, FailureHandling.OPTIONAL)

Mobile.delay(2)

Mobile.tap(findTestObject('Mobile/Common/Btn_OK'), 0, FailureHandling.OPTIONAL)

'Collecting the pending invoice amount'
Mobile.callTestCase(findTestCase('Product_Mobile/CM/Reusables/Invoice_Amt_Collected'), [('Credit_Index') : Credit_Index], FailureHandling.STOP_ON_FAILURE)

Mobile.callTestCase(findTestCase('Product_Mobile/CM/Reusables/Again_Goto_StockOrderScreen'), [:], FailureHandling.STOP_ON_FAILURE)

///////////////////////////////////////////////////////////////////////////////////////////////////////////
'Warn_Credit_Limit_Scenario_04:-\r\n\r\n1. Total invoice amount should be equal to the warn credit limit after one of the pending invoice amount has been collected.\r\n2. \'Amount exceeds warning Credit Balance.\' toast message should not be displayed in the summary screen.\r\n3. \'Invoice created successfully.\' alert should be displayed in the summary screen.'
KeywordUtil.logInfo('Scenario_04::::--------------->>>>>>>>>>>')

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Clear_Star_Filter_Default_Selection'), [:], FailureHandling.CONTINUE_ON_FAILURE)

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Search_Product'), [:], FailureHandling.CONTINUE_ON_FAILURE)

Mobile.setText(findTestObject('Mobile/Common/Field_Search_EnterText'), findTestData('Mobile Input Data/CreditManagement').getValue('CM_SKU_Name', 1), 5)

Last_Invoice_Amt = Scenario_02_Invoice_Amt

KeywordUtil.logInfo('Scenario_03 invoice not generated so, we consider Scenario_02 Invoice Amount : ' + Last_Invoice_Amt.toString())

Pending_Invoice_Amt = (Last_Invoice_Amt - Double.parseDouble(GlobalVariable.C_TransactionAmt))

KeywordUtil.logInfo('Collect the last invoice amount so this is the pending invoice amount : ' + Pending_Invoice_Amt.toString())

Count = ((Double.parseDouble(Warn_Credit_Limit) - Pending_Invoice_Amt) / Double.parseDouble(SKU_Piece_Price))

KeywordUtil.logInfo(Count.toString())

Qty = Count.round()

PieceQty = Qty

GlobalVariable.Qty = PieceQty

KeywordUtil.logInfo('SKU Qty inn Piece :' + PieceQty)

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Enter_PIECE_Qty'), [:], FailureHandling.STOP_ON_FAILURE)

Current_Invoice_SKU_TOTAL = Mobile.getText(findTestObject('Mobile/OrderInvoice/OrderInvoiceScrn-SkuTotal'), 0)

KeywordUtil.logInfo(((((('The retailer name is "' + GlobalVariable.RetailerName) + '" and its warn credit limit "') + Warn_Credit_Limit) + '" But, I have creating invoice amount as "') + Current_Invoice_SKU_TOTAL) + '".')

'1. Total invoice amount should be equal to the warn credit limit after one of the pending invoice amount has been collected.'
Scenario_04_Invoice_Amt = (Double.parseDouble(Current_Invoice_SKU_TOTAL) + Pending_Invoice_Amt)

Mobile.verifyEqual(Scenario_04_Invoice_Amt, Double.parseDouble(Warn_Credit_Limit), FailureHandling.CONTINUE_ON_FAILURE)

Mobile.takeScreenshot()

Mobile.tap(findTestObject('Mobile/Common/Btn_Next'), 0)

WebUI.callTestCase(findTestCase('Product_Mobile/Common/MustSell_Alert'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('Test Cases/VBL_Mobile/Re-Usables/Tap_Next_Btn_ApplyschemeScrn_CT'), [:], FailureHandling.STOP_ON_FAILURE)

Mobile.tap(findTestObject('Mobile/SummaryScreen/SummaryScreen-Invoice btn'), 0)

'2. \'Amount exceeds warning Credit Balance.\' toast message should not be displayed in the summary screen'
Mobile.callTestCase(findTestCase('Product_Mobile/CM/Reusables/Toast_Negative_Validation'), [:], FailureHandling.STOP_ON_FAILURE)

Mobile.callTestCase(findTestCase('Product_Mobile/Common/DatePicker_and _Navigation/Mobile Date Picker - Current Date'), [:], FailureHandling.OPTIONAL)

Mobile.tap(findTestObject('Mobile/Common/Btn_Confirm'), 0)

Mobile.takeScreenshot()

'3. \'Invoice created successfully.\' alert should be displayed in the summary screen.'
GlobalVariable.Alert = findTestData('Mobile Input Data/CreditManagement').getValue('Alert_Popup01', Credit_Index)

Mobile.waitForElementPresent(findTestObject('Mobile/Credit Management/Alert_Text'), 5, FailureHandling.CONTINUE_ON_FAILURE)

Mobile.verifyElementText(findTestObject('Mobile/Credit Management/Alert_Text'), GlobalVariable.Alert, FailureHandling.CONTINUE_ON_FAILURE)

Mobile.tap(findTestObject('Mobile/Common/Btn_OK'), 0)

Mobile.callTestCase(findTestCase('Product_Mobile/CM/Reusables/Again_Goto_StockOrderScreen'), [:], FailureHandling.STOP_ON_FAILURE)

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
'Warn_Credit_Limit_Scenario_05:-\r\n\r\n1. Total invoice amount should be greater than the warn credit limit after one of the pending invoice amount has been collected.\r\n2. \'Amount exceeds warning Credit Balance.\' toast message should be displayed in the summary screen.\r\n'
KeywordUtil.logInfo('Scenario_05::::--------------->>>>>>>>>>>')

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Clear_Star_Filter_Default_Selection'), [:], FailureHandling.CONTINUE_ON_FAILURE)

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Search_Product'), [:], FailureHandling.CONTINUE_ON_FAILURE)

Mobile.setText(findTestObject('Mobile/Common/Field_Search_EnterText'), findTestData('Mobile Input Data/CreditManagement').getValue('CM_SKU_Name', 1), 5)

Last_Invoice_Amt = Scenario_04_Invoice_Amt

KeywordUtil.logInfo('Scenario_04 Invoice Amount : ' + Last_Invoice_Amt.toString())

Count = ((Double.parseDouble(Warn_Credit_Limit) - Last_Invoice_Amt) / Double.parseDouble(SKU_Piece_Price))

KeywordUtil.logInfo(Count.toString())

Qty = (Count.round() + 1)

PieceQty = Qty

GlobalVariable.Qty = PieceQty

KeywordUtil.logInfo('SKU Qty inn Piece :' + PieceQty)

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Enter_PIECE_Qty'), [:], FailureHandling.STOP_ON_FAILURE)

Current_Invoice_SKU_TOTAL = Mobile.getText(findTestObject('Mobile/OrderInvoice/OrderInvoiceScrn-SkuTotal'), 0)

KeywordUtil.logInfo(((((('The retailer name is "' + GlobalVariable.RetailerName) + '" and its warn credit limit "') + Warn_Credit_Limit) + '" But, I have creating invoice amount as "') + Current_Invoice_SKU_TOTAL) + '".')

'1. Total invoice amount should be greater than the warn credit limit after one of the pending invoice amount has been collected.'
Invoice_Amt = (Double.parseDouble(Current_Invoice_SKU_TOTAL) + Last_Invoice_Amt)

Mobile.verifyGreaterThan(Invoice_Amt, Double.parseDouble(Warn_Credit_Limit), FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

Mobile.tap(findTestObject('Mobile/Common/Btn_Next'), 0)

WebUI.callTestCase(findTestCase('Product_Mobile/Common/MustSell_Alert'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('Test Cases/VBL_Mobile/Re-Usables/Tap_Next_Btn_ApplyschemeScrn_CT'), [:], FailureHandling.STOP_ON_FAILURE)

Mobile.tap(findTestObject('Mobile/SummaryScreen/SummaryScreen-Invoice btn'), 0)

'2. \'Amount exceeds warning Credit Balance.\' toast message should be displayed in the summary screen.'
Mobile.callTestCase(findTestCase('Product_Mobile/CM/Reusables/Toast_Validation'), [('Expecting_Toast_Text') : findTestData('Mobile Input Data/CreditManagement').getValue('Toast_Message01', Credit_Index)], FailureHandling.STOP_ON_FAILURE)

Mobile.tap(findTestObject('Mobile/Common/Btn_Cancel'), 0)

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

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
WebUI.callTestCase(findTestCase('Test Cases/Product_Mobile/Common/Sync with End Attendence'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('Test Cases/Product_Mobile/Common/HHT_Transaction_DB'), [:], FailureHandling.STOP_ON_FAILURE)

'Collected invoice cash on the mobile and need to do collection Acceptance.'
Credit_Index = 2

Mobile.callTestCase(findTestCase('Product_Mobile/CM/Reusables/Collection_Acceptance'), [('Credit_Index') : Credit_Index], FailureHandling.STOP_ON_FAILURE)

'Increased the Warn_Credit_Limit value in web '
Mobile.callTestCase(findTestCase('Product_Mobile/CM/Reusables/DB/Increase_Value_In_Web'), [('Credit_Index') : Credit_Index], FailureHandling.STOP_ON_FAILURE)

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
'Verify the Warn_Credit_Limit functionality in vanseller mobile after increased the Warn_Credit_Limt in web'
not_run: Mobile.callTestCase(findTestCase('Product_Mobile/CM/Reusables/Download_Attendance_Vanload'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('Product_Mobile/Common/Mobile_Login/VanSeller_Login-With Reset'), [:], FailureHandling.STOP_ON_FAILURE)

Mobile.callTestCase(findTestCase('Product_Mobile/CM/Reusables/Stock_Update_Mobile'), [('ProductName') : findTestData('Mobile Input Data/CreditManagement').getValue('CM_SKU_Name', 1), ('Quantity') : '1000'], FailureHandling.STOP_ON_FAILURE)

'Warn_Credit_Limit_Scenario_06:-\r\n\r\n1. Total invoice amount should be less than the increased warn credit limit\r\n2. \'Amount exceeds warning Credit Balance.\' toast message should not be displayed in the summary screen.\r\n3. \'Invoice created successfully.\' alert should be displayed in the summary screen.'
KeywordUtil.logInfo('Scenario_06::::--------------->>>>>>>>>>>')

Credit_Index = 2

Mobile.callTestCase(findTestCase('Product_Mobile/Common/TradeCoverage(SelectRetailer)'), [('RetailerName') : findTestData('Mobile Input Data/CreditManagement').getValue('Retailer_Name', Credit_Index)], FailureHandling.STOP_ON_FAILURE)

Mobile.setText(findTestObject('Mobile/Common/Field_Search_EnterText'), findTestData('Mobile Input Data/CreditManagement').getValue('CM_SKU_Name', 1), 5)

Increase_Warn_Credit_Limit_Value = findTestData('Mobile Input Data/CreditManagement').getValue('Increase_Value', Credit_Index)

SKU_Piece_Price = findTestData('Mobile Input Data/CreditManagement').getValue('SKU_Piece_Price', 1)

KeywordUtil.logInfo('SKU Piece Price:' + SKU_Piece_Price)

Mobile.callTestCase(findTestCase('Product_Mobile/CM/Reusables/DB/Get_Over_Due_As_Last_Invoice_Amt'), [('RetailerCode') : findTestData('Mobile Input Data/CreditManagement').getValue('Retailer_Name', Credit_Index)], FailureHandling.STOP_ON_FAILURE)

Last_Invoice_Amt = GlobalVariable.label

KeywordUtil.logInfo((Last_Invoice_Amt.toString() + ' : Pending invoice amount consider for this particular retailer name is : ') + GlobalVariable.RetailerName)

Count = ((Double.parseDouble(Increase_Warn_Credit_Limit_Value) - Double.parseDouble(Last_Invoice_Amt)) / Double.parseDouble(SKU_Piece_Price))

KeywordUtil.logInfo(Count.toString())

Qty = Count.round()

PieceQty = Qty

GlobalVariable.Qty = PieceQty

KeywordUtil.logInfo('SKU Qty in Piece :' + PieceQty)

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Enter_PIECE_Qty'), [:], FailureHandling.STOP_ON_FAILURE)

Current_Invoice_SKU_TOTAL = Mobile.getText(findTestObject('Mobile/OrderInvoice/OrderInvoiceScrn-SkuTotal'), 0)

KeywordUtil.logInfo(((((('The retailer name is "' + GlobalVariable.RetailerName) + '" and its increased warn credit limit "') + Increase_Warn_Credit_Limit_Value) + '" But, I have creating invoice amount as "') + Current_Invoice_SKU_TOTAL) + '".')

'1. Total invoice amount should be less than the increased warn credit limit'
Scenario_06_Invoice_Amt = (Double.parseDouble(Current_Invoice_SKU_TOTAL) + Double.parseDouble(Last_Invoice_Amt))

Mobile.verifyEqual(Scenario_06_Invoice_Amt, Double.parseDouble(Increase_Warn_Credit_Limit_Value), FailureHandling.CONTINUE_ON_FAILURE)

Mobile.takeScreenshot()

Mobile.tap(findTestObject('Mobile/Common/Btn_Next'), 0)

WebUI.callTestCase(findTestCase('Product_Mobile/Common/MustSell_Alert'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('Test Cases/VBL_Mobile/Re-Usables/Tap_Next_Btn_ApplyschemeScrn_CT'), [:], FailureHandling.STOP_ON_FAILURE)

Mobile.tap(findTestObject('Mobile/SummaryScreen/SummaryScreen-Invoice btn'), 0)

'2. \'Amount exceeds warning Credit Balance.\' toast message should not be displayed in the summary screen.'
Mobile.callTestCase(findTestCase('Product_Mobile/CM/Reusables/Toast_Negative_Validation'), [:], FailureHandling.STOP_ON_FAILURE)

Mobile.callTestCase(findTestCase('Product_Mobile/Common/DatePicker_and _Navigation/Mobile Date Picker - Current Date'), [:], FailureHandling.OPTIONAL)

Mobile.tap(findTestObject('Mobile/Common/Btn_Confirm'), 0)

Mobile.takeScreenshot()

'3. \'Invoice created successfully.\' alert should be displayed in the summary screen.'
GlobalVariable.Alert = findTestData('Mobile Input Data/CreditManagement').getValue('Alert_Popup01', Credit_Index)

Mobile.waitForElementPresent(findTestObject('Mobile/Credit Management/Alert_Text'), 5, FailureHandling.CONTINUE_ON_FAILURE)

Mobile.takeScreenshot()

Mobile.verifyElementText(findTestObject('Mobile/Credit Management/Alert_Text'), GlobalVariable.Alert, FailureHandling.CONTINUE_ON_FAILURE)

Mobile.tap(findTestObject('Mobile/Common/Btn_OK'), 0)

Mobile.callTestCase(findTestCase('Product_Mobile/CM/Reusables/Again_Goto_StockOrderScreen'), [:], FailureHandling.STOP_ON_FAILURE)

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
'Warn_Credit_Limit_Scenario_07:-\r\n\r\n1. Total invoice amount should be greater than the increased warn credit limit.\r\n2. \'Amount exceeds warning Credit Balance.\' toast message should be displayed in the summary screen.\r\n3. \'Invoice created successfully.\' alert should be displayed in the summary screen.'
KeywordUtil.logInfo('Scenario_07::::--------------->>>>>>>>>>>')

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Clear_Star_Filter_Default_Selection'), [:], FailureHandling.CONTINUE_ON_FAILURE)

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Search_Product'), [:], FailureHandling.CONTINUE_ON_FAILURE)

Mobile.setText(findTestObject('Mobile/Common/Field_Search_EnterText'), findTestData('Mobile Input Data/CreditManagement').getValue('CM_SKU_Name', 1), 5)

Last_Invoice_Amt = Scenario_06_Invoice_Amt

KeywordUtil.logInfo('Scenario_06 Invoice Amount : ' + Last_Invoice_Amt.toString())

Increase_Warn_Credit_Limit_Value = findTestData('Mobile Input Data/CreditManagement').getValue('Increase_Value', Credit_Index)

Count = ((Double.parseDouble(Increase_Warn_Credit_Limit_Value) - Last_Invoice_Amt) / Double.parseDouble(SKU_Piece_Price))

KeywordUtil.logInfo(Count.toString())

Qty = (Count.round() + 1)

PieceQty = Qty

GlobalVariable.Qty = PieceQty

KeywordUtil.logInfo('SKU Qty in Piece :' + PieceQty)

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Enter_PIECE_Qty'), [:], FailureHandling.STOP_ON_FAILURE)

Current_Invoice_SKU_TOTAL = Mobile.getText(findTestObject('Mobile/OrderInvoice/OrderInvoiceScrn-SkuTotal'), 0)

KeywordUtil.logInfo(((((('The retailer name is "' + GlobalVariable.RetailerName) + '" and its increased warn credit limit "') + Increase_Warn_Credit_Limit_Value) + '" But, I have creating invoice amount as "') + Current_Invoice_SKU_TOTAL) + '".')

'1. Total invoice amount should be greater than the increased warn credit limit.'
Invoice_Amt = (Double.parseDouble(Current_Invoice_SKU_TOTAL) + Last_Invoice_Amt)

Mobile.verifyGreaterThan(Invoice_Amt, Double.parseDouble(Increase_Warn_Credit_Limit_Value), FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

Mobile.tap(findTestObject('Mobile/Common/Btn_Next'), 0)

WebUI.callTestCase(findTestCase('Product_Mobile/Common/MustSell_Alert'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('Test Cases/VBL_Mobile/Re-Usables/Tap_Next_Btn_ApplyschemeScrn_CT'), [:], FailureHandling.STOP_ON_FAILURE)

Mobile.tap(findTestObject('Mobile/SummaryScreen/SummaryScreen-Invoice btn'), 0)

'2. \'Amount exceeds warning Credit Balance.\' toast message should be displayed in the summary screen.'
Mobile.callTestCase(findTestCase('Product_Mobile/CM/Reusables/Toast_Validation'), [('Expecting_Toast_Text') : findTestData('Mobile Input Data/CreditManagement').getValue('Toast_Message01', Credit_Index)], FailureHandling.STOP_ON_FAILURE)

Mobile.callTestCase(findTestCase('Product_Mobile/Common/DatePicker_and _Navigation/Mobile Date Picker - Current Date'), [:], FailureHandling.OPTIONAL)

Mobile.tap(findTestObject('Mobile/Common/Btn_Confirm'), 0)

Mobile.takeScreenshot()

'3. \'Invoice created successfully.\' alert should be displayed in the summary screen.'
GlobalVariable.Alert = findTestData('Mobile Input Data/CreditManagement').getValue('Alert_Popup01', Credit_Index)

Mobile.waitForElementPresent(findTestObject('Mobile/Credit Management/Alert_Text'), 5, FailureHandling.CONTINUE_ON_FAILURE)

Mobile.takeScreenshot()

Mobile.verifyElementText(findTestObject('Mobile/Credit Management/Alert_Text'), GlobalVariable.Alert, FailureHandling.CONTINUE_ON_FAILURE)

Mobile.tap(findTestObject('Mobile/Common/Btn_OK'), 0)

WebUI.callTestCase(findTestCase('Product_Mobile/Common/Call_Analysis'), [:], FailureHandling.CONTINUE_ON_FAILURE)

