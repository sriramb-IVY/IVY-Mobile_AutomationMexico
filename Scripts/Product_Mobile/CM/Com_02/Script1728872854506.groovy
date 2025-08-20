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
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import io.appium.java_client.AppiumDriver as AppiumDriver
import io.appium.java_client.MobileDriver as MobileDriver
import io.appium.java_client.MobileBy as MobileBy
//import io.appium.java_client.MobileElement as MobileElement
import io.appium.java_client.android.AndroidDriver as AndroidDriver
import org.openqa.selenium.support.ui.ExpectedConditions as ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait as WebDriverWait
import com.kms.katalon.core.mobile.keyword.internal.MobileDriverFactory as MobileDriverFactory
import org.junit.Assert as Assert
import org.openqa.selenium.NoSuchElementException as NoSuchElementException

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Module_Config_Enable_Update_DB'), [('Seller_Type_Id') : findTestData('Mobile Input Data/Module Configuration').getValue('Sales_Rep_Id', 1), ('Config_Code') : findTestData('Mobile Input Data/Module Configuration').getValue('Config_Code', 1), ('Rfield') : 1], 
    FailureHandling.STOP_ON_FAILURE)

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Module_Config_Enable_Update_DB'), [('Seller_Type_Id') : findTestData('Mobile Input Data/Module Configuration').getValue('Sales_Rep_Id', 1), ('Config_Code') : findTestData('Mobile Input Data/Module Configuration').getValue('Config_Code', 2), ('Rfield') : 2], 
    FailureHandling.STOP_ON_FAILURE)

Mobile.callTestCase(findTestCase('VBL_Mobile/Re-Usables/Web Login and Navigation/ClearCashe Login'), [:], FailureHandling.STOP_ON_FAILURE)

Mobile.callTestCase(findTestCase('Product_Mobile/CM/Reusables/DB/Delete_Pendingbill_UpdateOverdue'), [('Row_Index') : [12]], FailureHandling.STOP_ON_FAILURE)

//Mobile.callTestCase(findTestCase('Product_Mobile/CM/Reusables/DB/Web and DB Validation'), [('Row_Index') : [12]], FailureHandling.STOP_ON_FAILURE)
WebUI.callTestCase(findTestCase('Product_Mobile/Common/Mobile_Login/VanSeller_Login-With Reset'), [:], FailureHandling.STOP_ON_FAILURE)

Mobile.callTestCase(findTestCase('Product_Mobile/CM/Reusables/Stock_Update_Mobile'), [('ProductName') : findTestData('Mobile Input Data/CreditManagement').getValue('CM_SKU_Name', 1), ('Quantity') : '1000'], FailureHandling.STOP_ON_FAILURE)

'Scenario_01 pre-requesite of generated first invoice'
KeywordUtil.logInfo('Scenario_01 pre-requesite of generated first invoice::::--------------->>>>>>>>>>>')

//Credit_Index = 12
//
//Mobile.startApplication(GlobalVariable.APK_File, false)
//
//Mobile.tap(findTestObject('Mobile/Common/Btn_Menu'), 10)
//
//Mobile.delay(40)
Mobile.callTestCase(findTestCase('Product_Mobile/Common/TradeCoverage(SelectRetailer)'), [('RetailerName') : findTestData('Mobile Input Data/CreditManagement').getValue('Retailer_Name', Credit_Index)], FailureHandling.STOP_ON_FAILURE)

Mobile.setText(findTestObject('Mobile/Common/Field_Search_EnterText'), findTestData('Mobile Input Data/CreditManagement').getValue('CM_SKU_Name', 1), 5)

Warn_Credit_Limit = findTestData('Mobile Input Data/CreditManagement').getValue('Warn_Credit_Limit', Credit_Index)

KeywordUtil.logInfo('Warn_Credit_Limit :' + Warn_Credit_Limit)

Last_Invoice_Amt = 0.0

KeywordUtil.logInfo('This is first invoice so we assign here zero' + Last_Invoice_Amt.toString())

Warn_Credit_Bill = findTestData('Mobile Input Data/CreditManagement').getValue('Warn_Credit_Bill', Credit_Index)

KeywordUtil.logInfo('Warn_Credit_Bill :' + Warn_Credit_Bill)

int Invoice_Count = 0

KeywordUtil.logInfo('When user download and start the visit that time invoice count should be zero so we take invoice count is ' + Invoice_Count.toString())

SKU_Piece_Price = findTestData('Mobile Input Data/CreditManagement').getValue('SKU_Piece_Price', 1)

KeywordUtil.logInfo('SKU Piece Price:' + SKU_Piece_Price)

Count = (Double.parseDouble(Warn_Credit_Limit) / Double.parseDouble(SKU_Piece_Price))

KeywordUtil.logInfo(Count.toString())

Qty = (Count.round() - 2)

String PieceQty = Qty

GlobalVariable.Qty = PieceQty

KeywordUtil.logInfo('SKU Qty inn Piece :' + PieceQty)

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Enter_PIECE_Qty'), [:], FailureHandling.STOP_ON_FAILURE)

Current_Invoice_SKU_TOTAL = Mobile.getText(findTestObject('Mobile/OrderInvoice/OrderInvoiceScrn-SkuTotal'), 0)

KeywordUtil.logInfo(((((('The retailer name is "' + GlobalVariable.RetailerName) + '" and its warn credit limit "') + Warn_Credit_Limit) + '" But, I have creating invoice amount as "') + Current_Invoice_SKU_TOTAL) + '".')

Mobile.tap(findTestObject('Mobile/Common/Btn_Next'), 0)

WebUI.callTestCase(findTestCase('Product_Mobile/Common/MustSell_Alert'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('Test Cases/VBL_Mobile/Re-Usables/Tap_Next_Btn_ApplyschemeScrn_CT'), [:], FailureHandling.STOP_ON_FAILURE)

Mobile.tap(findTestObject('Mobile/SummaryScreen/SummaryScreen-Invoice btn'), 0)

KeywordUtil.logInfo('Invoice count is ' + Invoice_Count.toString())

Mobile.callTestCase(findTestCase('Product_Mobile/Common/DatePicker_and _Navigation/Mobile Date Picker - Current Date'), [:], FailureHandling.OPTIONAL)

Mobile.tap(findTestObject('Mobile/Common/Btn_Confirm'), 0)

Mobile.takeScreenshot()

' \'Invoice created successfully.\' alert should be displayed.'
GlobalVariable.Alert = findTestData('Mobile Input Data/CreditManagement').getValue('Alert_Popup01', Credit_Index)

Mobile.waitForElementPresent(findTestObject('Mobile/Credit Management/Alert_Text'), 5, FailureHandling.CONTINUE_ON_FAILURE)

Mobile.takeScreenshot()

Mobile.verifyElementText(findTestObject('Mobile/Credit Management/Alert_Text'), GlobalVariable.Alert, FailureHandling.CONTINUE_ON_FAILURE)

Mobile.tap(findTestObject('Mobile/Common/Btn_OK'), 0)

Invoice_01_Amt = (Double.parseDouble(Current_Invoice_SKU_TOTAL) + Last_Invoice_Amt)

KeywordUtil.logInfo('Invoice amount is ' + Invoice_01_Amt.toString())

Invoice_Count++

KeywordUtil.logInfo('Invoice count is ' + Invoice_Count.toString())

Mobile.callTestCase(findTestCase('Product_Mobile/CM/Reusables/Again_Goto_StockOrderScreen'), [:], FailureHandling.STOP_ON_FAILURE)

'Scenario_01 pre-requesite of generated second invoice'
KeywordUtil.logInfo('Scenario_01 pre-requesite of generated second invoice::::--------------->>>>>>>>>>>')

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Clear_Star_Filter_Default_Selection'), [:], FailureHandling.CONTINUE_ON_FAILURE)

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Search_Product'), [:], FailureHandling.CONTINUE_ON_FAILURE)

Mobile.setText(findTestObject('Mobile/Common/Field_Search_EnterText'), findTestData('Mobile Input Data/CreditManagement').getValue('CM_SKU_Name', 1), 5)

Last_Invoice_Amt = Invoice_01_Amt

KeywordUtil.logInfo(' Invoice Amount : ' + Last_Invoice_Amt.toString())

Count = ((Double.parseDouble(Warn_Credit_Limit) - Last_Invoice_Amt) / Double.parseDouble(SKU_Piece_Price))

KeywordUtil.logInfo(Count.toString())

Qty = Count.round()

PieceQty = Qty

GlobalVariable.Qty = PieceQty

KeywordUtil.logInfo('SKU Qty inn Piece :' + PieceQty)

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Enter_PIECE_Qty'), [:], FailureHandling.STOP_ON_FAILURE)

Current_Invoice_SKU_TOTAL = Mobile.getText(findTestObject('Mobile/OrderInvoice/OrderInvoiceScrn-SkuTotal'), 0)

KeywordUtil.logInfo(((((('The retailer name is "' + GlobalVariable.RetailerName) + '" and its credit limit "') + Warn_Credit_Limit) + '" But, I have creating invoice amount as "') + Current_Invoice_SKU_TOTAL) + '".')

Mobile.tap(findTestObject('Mobile/Common/Btn_Next'), 0)

WebUI.callTestCase(findTestCase('Product_Mobile/Common/MustSell_Alert'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('Test Cases/VBL_Mobile/Re-Usables/Tap_Next_Btn_ApplyschemeScrn_CT'), [:], FailureHandling.STOP_ON_FAILURE)

Mobile.tap(findTestObject('Mobile/SummaryScreen/SummaryScreen-Invoice btn'), 0)

Mobile.callTestCase(findTestCase('Product_Mobile/Common/DatePicker_and _Navigation/Mobile Date Picker - Current Date'), [:], FailureHandling.OPTIONAL)

Mobile.tap(findTestObject('Mobile/Common/Btn_Confirm'), 0)

Mobile.takeScreenshot()

'\'Invoice created successfully.\' alert should be displayed.'
GlobalVariable.Alert = findTestData('Mobile Input Data/CreditManagement').getValue('Alert_Popup01', Credit_Index)

Mobile.waitForElementPresent(findTestObject('Mobile/Credit Management/Alert_Text'), 5, FailureHandling.CONTINUE_ON_FAILURE)

Mobile.takeScreenshot()

Mobile.verifyElementText(findTestObject('Mobile/Credit Management/Alert_Text'), GlobalVariable.Alert, FailureHandling.CONTINUE_ON_FAILURE)

Mobile.tap(findTestObject('Mobile/Common/Btn_OK'), 0)

Invoice_02_Amt = (Double.parseDouble(Current_Invoice_SKU_TOTAL) + Last_Invoice_Amt)

KeywordUtil.logInfo('Invoice Amount : ' + Invoice_02_Amt.toString())

Invoice_Count++

KeywordUtil.logInfo('Invoice count is : ' + Invoice_Count.toString())

'Scenario_01: To verify whether the invoice is generated when the both warn credit limit and warn credit bill is exceeded.'
KeywordUtil.logInfo('Scenario_01::::--------------->>>>>>>>>>>')

Mobile.callTestCase(findTestCase('Product_Mobile/CM/Reusables/Again_Goto_StockOrderScreen'), [:], FailureHandling.STOP_ON_FAILURE)

'1. Any credit related toast message should not be displayed in the store activity screen.'
Mobile.callTestCase(findTestCase('Product_Mobile/CM/Reusables/Toast_Negative_Validation'), [:], FailureHandling.CONTINUE_ON_FAILURE)

Last_Invoice_Amt = Invoice_02_Amt

KeywordUtil.logInfo(' Invoice Amount : ' + Last_Invoice_Amt.toString())

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Clear_Star_Filter_Default_Selection'), [:], FailureHandling.CONTINUE_ON_FAILURE)

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Search_Product'), [:], FailureHandling.CONTINUE_ON_FAILURE)

Mobile.setText(findTestObject('Mobile/Common/Field_Search_EnterText'), findTestData('Mobile Input Data/CreditManagement').getValue('CM_SKU_Name', 1), 5)

Count = ((Double.parseDouble(Warn_Credit_Limit) - Last_Invoice_Amt) / Double.parseDouble(SKU_Piece_Price))

KeywordUtil.logInfo(Count.toString())

Qty = (Count.round() + 1)

PieceQty = Qty

GlobalVariable.Qty = PieceQty

KeywordUtil.logInfo('SKU Qty in Piece :' + PieceQty)

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Enter_PIECE_Qty'), [:], FailureHandling.STOP_ON_FAILURE)

Current_Invoice_SKU_TOTAL = Mobile.getText(findTestObject('Mobile/OrderInvoice/OrderInvoiceScrn-SkuTotal'), 0)

KeywordUtil.logInfo(((((('The retailer name is "' + GlobalVariable.RetailerName) + '" and its credit limit "') + Warn_Credit_Limit) + '" But, I have creating invoice amount as "') + Current_Invoice_SKU_TOTAL) + '".')

'2. Total invoice amount should be greater than the credit limit.'
Invoice_Amt = (Double.parseDouble(Current_Invoice_SKU_TOTAL) + Last_Invoice_Amt)

Mobile.verifyGreaterThan(Invoice_Amt, Double.parseDouble(Warn_Credit_Limit), FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

'3. The total invoice count should be greater than the warn credit bill count.'
Invoice_Count++

Mobile.verifyGreaterThan(Invoice_Count, Double.parseDouble(Warn_Credit_Bill), FailureHandling.STOP_ON_FAILURE)

Mobile.tap(findTestObject('Mobile/Common/Btn_Next'), 0)

WebUI.callTestCase(findTestCase('Product_Mobile/Common/MustSell_Alert'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('Test Cases/VBL_Mobile/Re-Usables/Tap_Next_Btn_ApplyschemeScrn_CT'), [:], FailureHandling.STOP_ON_FAILURE)

Mobile.tap(findTestObject('Mobile/SummaryScreen/SummaryScreen-Invoice btn'), 0)

'4. \'Warning Credit Bill Count Reached.\' and \'Amount exceeds warning Credit Balance.\' ar the both toast message should be displayed simultaneously in the summary screen.'
Mobile.callTestCase(findTestCase('Product_Mobile/CM/Reusables/Two_Toast_Validation'), [:], FailureHandling.STOP_ON_FAILURE)

Actualtoastmessage01 = GlobalVariable.label1

Actualtoastmessage02 = GlobalVariable.label2

Expecting_Toast_Text_01 = findTestData('Mobile Input Data/CreditManagement').getValue('Toast_Message02', Credit_Index)

Mobile.verifyMatch(Actualtoastmessage01, Expecting_Toast_Text_01, false, FailureHandling.CONTINUE_ON_FAILURE)

Expecting_Toast_Text_02 = findTestData('Mobile Input Data/CreditManagement').getValue('Toast_Message03', Credit_Index)

Mobile.verifyMatch(Actualtoastmessage02, Expecting_Toast_Text_02, false, FailureHandling.CONTINUE_ON_FAILURE)

Mobile.tap(findTestObject('Mobile/Common/Btn_Cancel'), 0)

Invoice_Count--

KeywordUtil.logInfo('Invoice not generated so currently invoice count is ' + Invoice_Count.toString())

Mobile.tap(findTestObject('Mobile/Common/Icon_Back'), 1, FailureHandling.OPTIONAL)

Mobile.delay(2)

Mobile.tap(findTestObject('Mobile/Common/Btn_OK'), 2, FailureHandling.OPTIONAL)

Mobile.delay(2)

Mobile.tap(findTestObject('Mobile/Common/Icon_Back'), 1, FailureHandling.OPTIONAL)

Mobile.delay(2)

Mobile.tap(findTestObject('Mobile/Common/Icon_Back'), 1, FailureHandling.OPTIONAL)

Mobile.delay(2)

Mobile.tap(findTestObject('Mobile/Common/Btn_OK'), 0, FailureHandling.OPTIONAL)

'Scenario_02 pre-requesite of collected, accepted invoice in web and updated past date in DB'
KeywordUtil.logInfo('Scenario_02: pre-requesite::::--------------->>>>>>>>>>>')

'Collect the pending invoice amount'
Mobile.callTestCase(findTestCase('Product_Mobile/CM/Reusables/Invoice_Amt_Collected'), [('Credit_Index') : Credit_Index], FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('Product_Mobile/Common/Call_Analysis'), [:], FailureHandling.CONTINUE_ON_FAILURE)

WebUI.callTestCase(findTestCase('Test Cases/Product_Mobile/Common/Sync with End Attendence'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('Test Cases/Product_Mobile/Common/HHT_Transaction_DB'), [:], FailureHandling.STOP_ON_FAILURE)

Mobile.callTestCase(findTestCase('Product_Mobile/CM/Reusables/Collection_Acceptance'), [('Credit_Index') : Credit_Index], FailureHandling.STOP_ON_FAILURE)

Invoice_Count--

KeywordUtil.logInfo('One of the invoice amount collected so Invoice count is reduced so currently invoice count is : ' + Invoice_Count.toString())

Credit_Period = findTestData('Mobile Input Data/CreditManagement').getValue('Warn_Credit_Period', Credit_Index)

GreaterThanCreditPeriod = (Integer.parseInt(Credit_Period) + 1)

Mobile.callTestCase(findTestCase('Product_Mobile/CM/Reusables/DB/Update_Pendingbill_Date'), [('Credit_Index') : Credit_Index, ('Datee') : -(GreaterThanCreditPeriod)], FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('Product_Mobile/Common/Mobile_Login/VanSeller_Login-With Reset'), [:], FailureHandling.STOP_ON_FAILURE)

Mobile.callTestCase(findTestCase('Product_Mobile/CM/Reusables/Stock_Update_Mobile'), [('ProductName') : findTestData('Mobile Input Data/CreditManagement').getValue('CM_SKU_Name', 1), ('Quantity') : '1000'], FailureHandling.STOP_ON_FAILURE)

Mobile.callTestCase(findTestCase('Product_Mobile/Common/StartVisit'), [('RetailerName') : findTestData('Mobile Input Data/CreditManagement').getValue('Retailer_Name', Credit_Index)], FailureHandling.STOP_ON_FAILURE)

'Scenario_02: To verify whether the invoice is generated when the both warn credit limit amount and warn credit period is exceeded.'
KeywordUtil.logInfo('Scenario_02::::--------------->>>>>>>>>>>')

'1. The pending invoice date should be greater than the credit period value.'
Mobile.callTestCase(findTestCase('Product_Mobile/CM/Reusables/Date_Difference'), [('Credit_Index') : Credit_Index], FailureHandling.STOP_ON_FAILURE)

Invoice_Period = GlobalVariable.sDate

Credit_Period = findTestData('Mobile Input Data/CreditManagement').getValue('Warn_Credit_Period', Credit_Index)

Mobile.verifyGreaterThan(Invoice_Period, Integer.parseInt(Credit_Period), FailureHandling.STOP_ON_FAILURE)

Mobile.delay(3)

Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/Menu-OrderInvoice'), 0, FailureHandling.STOP_ON_FAILURE)

'2. \'Warning Credit days reached for invoices.\' toast message should be displayed in the summary screen.'
Mobile.callTestCase(findTestCase('Product_Mobile/CM/Reusables/Toast_Validation'), [('Expecting_Toast_Text') : findTestData('Mobile Input Data/CreditManagement').getValue('Toast_Message01', Credit_Index)], FailureHandling.STOP_ON_FAILURE)

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Clear_Star_Filter_Default_Selection'), [:], FailureHandling.CONTINUE_ON_FAILURE)

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Search_Product'), [:], FailureHandling.CONTINUE_ON_FAILURE)

Mobile.setText(findTestObject('Mobile/Common/Field_Search_EnterText'), findTestData('Mobile Input Data/CreditManagement').getValue('CM_SKU_Name', 1), 5)

Mobile.callTestCase(findTestCase('Product_Mobile/CM/Reusables/DB/Get_Over_Due_As_Last_Invoice_Amt'), [('RetailerCode') : findTestData('Mobile Input Data/CreditManagement').getValue('Retailer_Name', Credit_Index)], FailureHandling.STOP_ON_FAILURE)

Last_Invoice_Amt = GlobalVariable.label

Warn_Credit_Limit = findTestData('Mobile Input Data/CreditManagement').getValue('Warn_Credit_Limit', Credit_Index)

KeywordUtil.logInfo('Warn_Credit_Limit :' + Warn_Credit_Limit)

SKU_Piece_Price = findTestData('Mobile Input Data/CreditManagement').getValue('SKU_Piece_Price', 1)

KeywordUtil.logInfo('SKU Piece Price:' + SKU_Piece_Price)

Count = ((Double.parseDouble(Warn_Credit_Limit) - Double.parseDouble(Last_Invoice_Amt)) / Double.parseDouble(SKU_Piece_Price))

KeywordUtil.logInfo(Count.toString())

Qty = (Count.round() + 1)

String PieceQty1 = Qty

GlobalVariable.Qty = PieceQty1

KeywordUtil.logInfo('SKU Qty in Piece :' + PieceQty1)

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Enter_PIECE_Qty'), [:], FailureHandling.STOP_ON_FAILURE)

Current_Invoice_SKU_TOTAL = Mobile.getText(findTestObject('Mobile/OrderInvoice/OrderInvoiceScrn-SkuTotal'), 0)

KeywordUtil.logInfo((Last_Invoice_Amt.toString() + ' : This is the last invoice amount of this particular retailer name is : ') + GlobalVariable.RetailerName)

'3. Total invoice amount should be greater than the credit limit.'
Invoice_Amt = (Double.parseDouble(Current_Invoice_SKU_TOTAL) + Double.parseDouble(Last_Invoice_Amt))

Mobile.verifyGreaterThan(Invoice_Amt, Double.parseDouble(Warn_Credit_Limit), FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

Mobile.tap(findTestObject('Mobile/Common/Btn_Next'), 0)

WebUI.callTestCase(findTestCase('Product_Mobile/Common/MustSell_Alert'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('Test Cases/VBL_Mobile/Re-Usables/Tap_Next_Btn_ApplyschemeScrn_CT'), [:], FailureHandling.STOP_ON_FAILURE)

Mobile.tap(findTestObject('Mobile/SummaryScreen/SummaryScreen-Invoice btn'), 0)

'4. \'Amount exceeds warning Credit Balance.\' toast message should be displayed in the summary screen.'
Mobile.callTestCase(findTestCase('Product_Mobile/CM/Reusables/Toast_Validation'), [('Expecting_Toast_Text') : findTestData('Mobile Input Data/CreditManagement').getValue('Toast_Message03', Credit_Index)], FailureHandling.STOP_ON_FAILURE)

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

Mobile.swipe(50, 10, 50, 600)

'Scenario_03 : Pre-requesites of generating invoice.'
KeywordUtil.logInfo('Scenario_03 - Pre-requesites::::--------------->>>>>>>>>>>')

Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/Menu-OrderInvoice'), 0, FailureHandling.STOP_ON_FAILURE)

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Clear_Star_Filter_Default_Selection'), [:], FailureHandling.CONTINUE_ON_FAILURE)

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Search_Product'), [:], FailureHandling.CONTINUE_ON_FAILURE)

Mobile.setText(findTestObject('Mobile/Common/Field_Search_EnterText'), findTestData('Mobile Input Data/CreditManagement').getValue('CM_SKU_Name', 1), 5)

Qty = (Count.round() - 1)

PieceQty1 = Qty

GlobalVariable.Qty = PieceQty1

KeywordUtil.logInfo('SKU Qty in Piece :' + PieceQty1)

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Enter_PIECE_Qty'), [:], FailureHandling.STOP_ON_FAILURE)

Current_Invoice_SKU_TOTAL = Mobile.getText(findTestObject('Mobile/OrderInvoice/OrderInvoiceScrn-SkuTotal'), 0)

Mobile.tap(findTestObject('Mobile/Common/Btn_Next'), 0)

WebUI.callTestCase(findTestCase('Product_Mobile/Common/MustSell_Alert'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('Test Cases/VBL_Mobile/Re-Usables/Tap_Next_Btn_ApplyschemeScrn_CT'), [:], FailureHandling.STOP_ON_FAILURE)

Mobile.tap(findTestObject('Mobile/SummaryScreen/SummaryScreen-Invoice btn'), 0)

Mobile.callTestCase(findTestCase('Product_Mobile/Common/DatePicker_and _Navigation/Mobile Date Picker - Current Date'), [:], FailureHandling.OPTIONAL)

Mobile.tap(findTestObject('Mobile/Common/Btn_Confirm'), 0)

Mobile.takeScreenshot()

' \'Invoice created successfully.\' alert should be displayed.'
GlobalVariable.Alert = findTestData('Mobile Input Data/CreditManagement').getValue('Alert_Popup01', Credit_Index)

Mobile.waitForElementPresent(findTestObject('Mobile/Credit Management/Alert_Text'), 5, FailureHandling.CONTINUE_ON_FAILURE)

Mobile.takeScreenshot()

Mobile.verifyElementText(findTestObject('Mobile/Credit Management/Alert_Text'), GlobalVariable.Alert, FailureHandling.CONTINUE_ON_FAILURE)

Mobile.tap(findTestObject('Mobile/Common/Btn_OK'), 0)

KeywordUtil.logInfo((Last_Invoice_Amt.toString() + ' : This is the last invoice amount of this particular retailer name is : ') + GlobalVariable.RetailerName)

Invoice_Amt_03 = (Double.parseDouble(Current_Invoice_SKU_TOTAL) + Double.parseDouble(Last_Invoice_Amt))

KeywordUtil.logInfo('Invoice amount is ' + Invoice_Amt_03.toString())

Invoice_Count++

KeywordUtil.logInfo('Invoice count is ' + Invoice_Count.toString())

Mobile.swipe(50, 10, 50, 600)

'Scenario_03 : To verify whether the invoice is generated when the both warn credit bill count and warn credit period is exceeded..'
KeywordUtil.logInfo('Scenario_03::::--------------->>>>>>>>>>>')

Mobile.callTestCase(findTestCase('Product_Mobile/CM/Reusables/Date_Difference'), [('Credit_Index') : Credit_Index], FailureHandling.STOP_ON_FAILURE)

Invoice_Period = GlobalVariable.sDate

Warn_Credit_Period = findTestData('Mobile Input Data/CreditManagement').getValue('Warn_Credit_Period', Credit_Index)

'1. The pending invoice date should be greater than the warn credit period value.'
Mobile.verifyGreaterThan(Invoice_Period, Integer.parseInt(Warn_Credit_Period), FailureHandling.STOP_ON_FAILURE)

Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/Menu-OrderInvoice'), 0, FailureHandling.STOP_ON_FAILURE)

'2. \'Warning Credit days reached for invoices.\' toast message should be displayed in the summary screen.'
Mobile.callTestCase(findTestCase('Product_Mobile/CM/Reusables/Toast_Validation'), [('Expecting_Toast_Text') : findTestData('Mobile Input Data/CreditManagement').getValue('Toast_Message01', Credit_Index)], FailureHandling.STOP_ON_FAILURE)

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Clear_Star_Filter_Default_Selection'), [:], FailureHandling.CONTINUE_ON_FAILURE)

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Search_Product'), [:], FailureHandling.CONTINUE_ON_FAILURE)

Mobile.setText(findTestObject('Mobile/Common/Field_Search_EnterText'), findTestData('Mobile Input Data/CreditManagement').getValue('CM_SKU_Name', 1), 5)

Last_Invoice_Amt = Invoice_Amt_03

KeywordUtil.logInfo('Scenario_01 Invoice Amount : ' + Last_Invoice_Amt.toString())

Count = ((Double.parseDouble(Warn_Credit_Limit) - Last_Invoice_Amt) / Double.parseDouble(SKU_Piece_Price))

KeywordUtil.logInfo(Count.toString())

Qty = Count.round()

PieceQty1 = Qty

GlobalVariable.Qty = PieceQty1

KeywordUtil.logInfo('SKU Qty in Piece :' + PieceQty1)

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Enter_PIECE_Qty'), [:], FailureHandling.STOP_ON_FAILURE)

Current_Invoice_SKU_TOTAL = Mobile.getText(findTestObject('Mobile/OrderInvoice/OrderInvoiceScrn-SkuTotal'), 0)

Mobile.tap(findTestObject('Mobile/Common/Btn_Next'), 0)

WebUI.callTestCase(findTestCase('Product_Mobile/Common/MustSell_Alert'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('Test Cases/VBL_Mobile/Re-Usables/Tap_Next_Btn_ApplyschemeScrn_CT'), [:], FailureHandling.STOP_ON_FAILURE)

'3. The total invoice count should be greater than the warn credit bill count.'
Invoice_Count++

Mobile.verifyGreaterThan(Invoice_Count, Double.parseDouble(Warn_Credit_Bill), FailureHandling.STOP_ON_FAILURE)

Mobile.tap(findTestObject('Mobile/SummaryScreen/SummaryScreen-Invoice btn'), 0)

'4. \'Warning Credit Bill Count Reached.\' toast message should be displayed in the summary screen.'
Mobile.callTestCase(findTestCase('Product_Mobile/CM/Reusables/Toast_Validation'), [('Expecting_Toast_Text') : findTestData('Mobile Input Data/CreditManagement').getValue('Toast_Message02', Credit_Index)], FailureHandling.STOP_ON_FAILURE)

Mobile.callTestCase(findTestCase('Product_Mobile/Common/DatePicker_and _Navigation/Mobile Date Picker - Current Date'), [:], FailureHandling.OPTIONAL)

Mobile.tap(findTestObject('Mobile/Common/Btn_Confirm'), 0)

Mobile.takeScreenshot()

'5. \'Invoice created successfully.\' alert should be displayed.'
GlobalVariable.Alert = findTestData('Mobile Input Data/CreditManagement').getValue('Alert_Popup01', Credit_Index)

Mobile.waitForElementPresent(findTestObject('Mobile/Credit Management/Alert_Text'), 5, FailureHandling.CONTINUE_ON_FAILURE)

Mobile.takeScreenshot()

Mobile.verifyElementText(findTestObject('Mobile/Credit Management/Alert_Text'), GlobalVariable.Alert, FailureHandling.CONTINUE_ON_FAILURE)

Mobile.tap(findTestObject('Mobile/Common/Btn_OK'), 0)

Invoice_Amt_04 = (Double.parseDouble(Current_Invoice_SKU_TOTAL) + Last_Invoice_Amt)

KeywordUtil.logInfo('Invoice amount is ' + Invoice_Amt_04.toString())

'Scenario_04: To verify whether the invoice is generated when the warn credit limit amount, warn credit bill count and, warn credit period is exceeded.'
KeywordUtil.logInfo('Scenario_04::::--------------->>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>')

Mobile.callTestCase(findTestCase('Product_Mobile/CM/Reusables/Date_Difference'), [('Credit_Index') : Credit_Index], FailureHandling.STOP_ON_FAILURE)

Invoice_Period = GlobalVariable.sDate

Warn_Credit_Period = findTestData('Mobile Input Data/CreditManagement').getValue('Warn_Credit_Period', Credit_Index)

'1. The pending invoice date should be greater than the warn credit period value.'
Mobile.verifyGreaterThan(Invoice_Period, Integer.parseInt(Warn_Credit_Period), FailureHandling.STOP_ON_FAILURE)

Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/Menu-OrderInvoice'), 0, FailureHandling.STOP_ON_FAILURE)

'2. \'Warning Credit days reached for invoices.\' toast message should be displayed in the store actvity screen.'
Mobile.callTestCase(findTestCase('Product_Mobile/CM/Reusables/Toast_Validation'), [('Expecting_Toast_Text') : findTestData('Mobile Input Data/CreditManagement').getValue('Toast_Message01', Credit_Index)], FailureHandling.STOP_ON_FAILURE)

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Clear_Star_Filter_Default_Selection'), [:], FailureHandling.CONTINUE_ON_FAILURE)

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Search_Product'), [:], FailureHandling.CONTINUE_ON_FAILURE)

Mobile.setText(findTestObject('Mobile/Common/Field_Search_EnterText'), findTestData('Mobile Input Data/CreditManagement').getValue('CM_SKU_Name', 1), 5)

Last_Invoice_Amt = Invoice_Amt_04

KeywordUtil.logInfo('Scenario_01 Invoice Amount : ' + Last_Invoice_Amt.toString())

Count = ((Double.parseDouble(Warn_Credit_Limit) - Last_Invoice_Amt) / Double.parseDouble(SKU_Piece_Price))

KeywordUtil.logInfo(Count.toString())

Qty = (Count.round() + 1)

PieceQty1 = Qty

GlobalVariable.Qty = PieceQty1

KeywordUtil.logInfo('SKU Qty in Piece :' + PieceQty1)

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Enter_PIECE_Qty'), [:], FailureHandling.STOP_ON_FAILURE)

Current_Invoice_SKU_TOTAL = Mobile.getText(findTestObject('Mobile/OrderInvoice/OrderInvoiceScrn-SkuTotal'), 0)

Mobile.tap(findTestObject('Mobile/Common/Btn_Next'), 0)

WebUI.callTestCase(findTestCase('Product_Mobile/Common/MustSell_Alert'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('Test Cases/VBL_Mobile/Re-Usables/Tap_Next_Btn_ApplyschemeScrn_CT'), [:], FailureHandling.STOP_ON_FAILURE)

'3. The total invoice count should be greater than the warn credit bill count.'
Invoice_Count++

Mobile.verifyGreaterThan(Invoice_Count, Double.parseDouble(Warn_Credit_Bill), FailureHandling.STOP_ON_FAILURE)

KeywordUtil.logInfo((Last_Invoice_Amt.toString() + ' : This is the last invoice amount of this particular retailer name is : ') + GlobalVariable.RetailerName)

'4. Total invoice amount should be greater than the credit limit.'
Invoice_Amt = (Double.parseDouble(Current_Invoice_SKU_TOTAL) + Last_Invoice_Amt)

Mobile.verifyGreaterThan(Invoice_Amt, Double.parseDouble(Warn_Credit_Limit), FailureHandling.STOP_ON_FAILURE)

Mobile.tap(findTestObject('Mobile/SummaryScreen/SummaryScreen-Invoice btn'), 0)

'5. \'Warning Credit Bill Count Reached.\' and \'Amount exceeds warning Credit Balance.\' ar the both toast message should be displayed simultaneously in the summary screen.'
Mobile.callTestCase(findTestCase('Product_Mobile/CM/Reusables/Two_Toast_Validation'), [:], FailureHandling.STOP_ON_FAILURE)

Actualtoastmessage01 = GlobalVariable.label1

Actualtoastmessage02 = GlobalVariable.label2

Expecting_Toast_Text_01 = findTestData('Mobile Input Data/CreditManagement').getValue('Toast_Message02', Credit_Index)

Mobile.verifyMatch(Actualtoastmessage01, Expecting_Toast_Text_01, false, FailureHandling.CONTINUE_ON_FAILURE)

Expecting_Toast_Text_02 = findTestData('Mobile Input Data/CreditManagement').getValue('Toast_Message03', Credit_Index)

Mobile.verifyMatch(Actualtoastmessage02, Expecting_Toast_Text_02, false, FailureHandling.CONTINUE_ON_FAILURE)

Mobile.callTestCase(findTestCase('Product_Mobile/Common/DatePicker_and _Navigation/Mobile Date Picker - Current Date'), [:], FailureHandling.OPTIONAL)

Mobile.tap(findTestObject('Mobile/Common/Btn_Confirm'), 0)

Mobile.takeScreenshot()

'6. \'Invoice created successfully.\' alert should be displayed.'
GlobalVariable.Alert = findTestData('Mobile Input Data/CreditManagement').getValue('Alert_Popup01', Credit_Index)

Mobile.waitForElementPresent(findTestObject('Mobile/Credit Management/Alert_Text'), 5, FailureHandling.CONTINUE_ON_FAILURE)

Mobile.takeScreenshot()

Mobile.verifyElementText(findTestObject('Mobile/Credit Management/Alert_Text'), GlobalVariable.Alert, FailureHandling.CONTINUE_ON_FAILURE)

Mobile.tap(findTestObject('Mobile/Common/Btn_OK'), 0)

