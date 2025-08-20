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

Credit_Index = 15

//Mobile.startApplication(GlobalVariable.APK_File, false)
//
//Mobile.tap(findTestObject('Mobile/Common/Btn_Menu'), 10)

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Module_Config_Enable_Update_DB'), [('Seller_Type_Id') : findTestData('Mobile Input Data/Module Configuration').getValue('Sales_Rep_Id', 1), ('Config_Code') : findTestData('Mobile Input Data/Module Configuration').getValue('Config_Code', 1), ('Rfield') : 1], 
    FailureHandling.STOP_ON_FAILURE)

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Module_Config_Enable_Update_DB'), [('Seller_Type_Id') : findTestData('Mobile Input Data/Module Configuration').getValue('Sales_Rep_Id', 1), ('Config_Code') : findTestData('Mobile Input Data/Module Configuration').getValue('Config_Code', 2), ('Rfield') : 2], 
    FailureHandling.STOP_ON_FAILURE)

Mobile.callTestCase(findTestCase('VBL_Mobile/Re-Usables/Web Login and Navigation/ClearCashe Login'), [:], FailureHandling.STOP_ON_FAILURE)

Mobile.callTestCase(findTestCase('Product_Mobile/CM/Reusables/DB/Delete_Pendingbill_UpdateOverdue'), [('Row_Index') : [15]], FailureHandling.STOP_ON_FAILURE)

Mobile.callTestCase(findTestCase('Product_Mobile/CM/Reusables/DB/Web and DB Validation'), [('Row_Index') : [15]], FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('Product_Mobile/Common/Mobile_Login/VanSeller_Login-With Reset'), [:], FailureHandling.STOP_ON_FAILURE)

Mobile.callTestCase(findTestCase('Product_Mobile/CM/Reusables/Stock_Update_Mobile'), [('ProductName') : findTestData('Mobile Input Data/CreditManagement').getValue('CM_SKU_Name', 1), ('Quantity') : '1000'], FailureHandling.STOP_ON_FAILURE)

'Secanrio_01:: Pre-requesites of generate first invoice'
KeywordUtil.logInfo('Scenario_01::::--------------->>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>')

Mobile.callTestCase(findTestCase('Product_Mobile/Common/TradeCoverage(SelectRetailer)'), [('RetailerName') : findTestData('Mobile Input Data/CreditManagement').getValue('Retailer_Name', Credit_Index)], FailureHandling.STOP_ON_FAILURE)

Mobile.setText(findTestObject('Mobile/Common/Field_Search_EnterText'), findTestData('Mobile Input Data/CreditManagement').getValue('CM_SKU_Name', 1), 5)

GlobalVariable.Qty = '1'

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Enter_PIECE_Qty'), [:], FailureHandling.STOP_ON_FAILURE)

Invoice_amt_01 = Mobile.getText(findTestObject('Mobile/OrderInvoice/OrderInvoiceScrn-SkuTotal'), 0)

Mobile.tap(findTestObject('Mobile/Common/Btn_Next'), 0)

WebUI.callTestCase(findTestCase('Product_Mobile/Common/MustSell_Alert'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('Test Cases/VBL_Mobile/Re-Usables/Tap_Next_Btn_ApplyschemeScrn_CT'), [:], FailureHandling.STOP_ON_FAILURE)

Mobile.tap(findTestObject('Mobile/SummaryScreen/SummaryScreen-Invoice btn'), 0)

'\'Warning Credit Bill Count Reached.\' toast message should not be displayed in the summary screen.'
Mobile.callTestCase(findTestCase('Product_Mobile/CM/Reusables/Toast_Negative_Validation'), [:], FailureHandling.STOP_ON_FAILURE)

Mobile.callTestCase(findTestCase('Product_Mobile/Common/DatePicker_and _Navigation/Mobile Date Picker - Current Date'), [:], FailureHandling.OPTIONAL)

Mobile.tap(findTestObject('Mobile/Common/Btn_Confirm'), 0)

Mobile.takeScreenshot()

' \'Invoice created successfully.\' alert should be displayed in the summary screen'
GlobalVariable.Alert = findTestData('Mobile Input Data/CreditManagement').getValue('Alert_Popup01', Credit_Index)

Mobile.waitForElementPresent(findTestObject('Mobile/Credit Management/Alert_Text'), 5, FailureHandling.CONTINUE_ON_FAILURE)

Mobile.takeScreenshot()

Mobile.verifyElementText(findTestObject('Mobile/Credit Management/Alert_Text'), GlobalVariable.Alert, FailureHandling.CONTINUE_ON_FAILURE)

Mobile.tap(findTestObject('Mobile/Common/Btn_OK'), 0)

'When user login the application and visit the retailer that time Pending_Invoice_Count should be consider as zero after that first invoice generated successfully!'
int Current_Invoice_Count = 1

Scenario_01_Pending_Invoice_Count = Current_Invoice_Count

KeywordUtil.logInfo('Scenario_01_Pending_Invoice_Count is : ' + Scenario_01_Pending_Invoice_Count.toString())

WebUI.callTestCase(findTestCase('Product_Mobile/Common/Call_Analysis'), [:], FailureHandling.CONTINUE_ON_FAILURE)

WebUI.callTestCase(findTestCase('Test Cases/Product_Mobile/Common/Sync with End Attendence'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('Test Cases/Product_Mobile/Common/HHT_Transaction_DB'), [:], FailureHandling.STOP_ON_FAILURE)

'Scenario_01: Pre-requesite:'

'Past date updated for pending invoice of this retailers for warn credit period validation.'

Credit_Period = findTestData('Mobile Input Data/CreditManagement').getValue('Credit_Period', Credit_Index)

Credit_Period = (Integer.parseInt(Credit_Period) + 1)

Mobile.callTestCase(findTestCase('Product_Mobile/CM/Reusables/DB/Update_Pendingbill_Date'), [('Credit_Index') : Credit_Index, ('Datee') : -(Credit_Period)], FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('Product_Mobile/Common/Mobile_Login/VanSeller_Login-With Reset'), [:], FailureHandling.STOP_ON_FAILURE)

Mobile.callTestCase(findTestCase('Product_Mobile/Common/StartVisit'), [('RetailerName') : findTestData('Mobile Input Data/CreditManagement').getValue('Retailer_Name', Credit_Index)], FailureHandling.STOP_ON_FAILURE)

'Scenerio_01 : To verify whether the invoice is generated when the credit period and warn credit bill is exceeded.'

KeywordUtil.logInfo('Scenario_01 ::::--------------->>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>')

Mobile.callTestCase(findTestCase('Product_Mobile/CM/Reusables/Date_Difference'), [('Credit_Index') : Credit_Index], FailureHandling.STOP_ON_FAILURE)

Invoice_Period = GlobalVariable.sDate

Credit_Period = findTestData('Mobile Input Data/CreditManagement').getValue('Credit_Period', Credit_Index)

'1. The invoice date should be greater than the credit period value..'

Mobile.verifyGreaterThan(Invoice_Period, Integer.parseInt(Credit_Period), FailureHandling.STOP_ON_FAILURE)

Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/Menu-OrderInvoice'), 0, FailureHandling.STOP_ON_FAILURE)

Mobile.callTestCase(findTestCase('Product_Mobile/CM/Reusables/Toast_Negative_Validation'), [:], FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

'2. \'Credit days for invoice got expired\' soft alert should be displayed in the store activity screen.'

GlobalVariable.Alert = findTestData('Mobile Input Data/CreditManagement').getValue('Alert_Popup01', Credit_Index)

Mobile.waitForElementPresent(findTestObject('Mobile/Credit Management/Alert_Text'), 5, FailureHandling.CONTINUE_ON_FAILURE)

Mobile.takeScreenshot()

Mobile.verifyElementText(findTestObject('Mobile/Credit Management/Alert_Text'), GlobalVariable.Alert, FailureHandling.CONTINUE_ON_FAILURE)

Mobile.tap(findTestObject('Mobile/Credit Management/Credit Period_PROCEED_Btn'), 0)

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Clear_Star_Filter_Default_Selection'), [:], FailureHandling.CONTINUE_ON_FAILURE)

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Search_Product'), [:], FailureHandling.CONTINUE_ON_FAILURE)

Mobile.setText(findTestObject('Mobile/Common/Field_Search_EnterText'), findTestData('Mobile Input Data/CreditManagement').getValue('CM_SKU_Name', 1), 5)

String PieceQty2 = "1"

GlobalVariable.Qty = PieceQty2

KeywordUtil.logInfo('SKU Qty in Piece :' + PieceQty2)

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Enter_PIECE_Qty'), [:], FailureHandling.STOP_ON_FAILURE)

Scenario_02_Pending_Invoice_Count = Current_Invoice_Count

KeywordUtil.logInfo('Scenario_02_Pending_Invoice_Count is : ' + Scenario_02_Pending_Invoice_Count.toString())

Total_Invoice_count = Scenario_01_Pending_Invoice_Count + Scenario_02_Pending_Invoice_Count

Warn_Credit_Bill = findTestData('Mobile Input Data/CreditManagement').getValue('Warn_Credit_Bill', Credit_Index)

KeywordUtil.logInfo('Warn_Credit_Bill :' + Warn_Credit_Bill)

'3. The pending invoice count should be greater than the warn credit bills value.'

Mobile.verifyGreaterThan(Total_Invoice_count, Integer.parseInt(Warn_Credit_Bill), FailureHandling.STOP_ON_FAILURE)

Mobile.tap(findTestObject('Mobile/Common/Btn_Next'), 0)

WebUI.callTestCase(findTestCase('Product_Mobile/Common/MustSell_Alert'), [:], FailureHandling.STOP_ON_FAILURE)

' Toast message should not be displayed in the stock and order screen.'
Mobile.callTestCase(findTestCase('Product_Mobile/CM/Reusables/Toast_Negative_Validation'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('Test Cases/VBL_Mobile/Re-Usables/Tap_Next_Btn_ApplyschemeScrn_CT'), [:], FailureHandling.STOP_ON_FAILURE)

Mobile.tap(findTestObject('Mobile/SummaryScreen/SummaryScreen-Invoice btn'), 0)

'4. \'Warning Credit Bill Count Reached.\' toast message should be displayed in the store and order screen.'

Mobile.callTestCase(findTestCase('Product_Mobile/CM/Reusables/Toast_Validation'), [('Expecting_Toast_Text') : findTestData('Mobile Input Data/CreditManagement').getValue('Toast_Message01', Credit_Index)], FailureHandling.STOP_ON_FAILURE)

Mobile.callTestCase(findTestCase('Product_Mobile/Common/DatePicker_and _Navigation/Mobile Date Picker - Current Date'), [:], FailureHandling.OPTIONAL)

Mobile.tap(findTestObject('Mobile/Common/Btn_Confirm'), 0)

Mobile.takeScreenshot()

' \'Invoice created successfully.\' alert should be displayed in the summary screen'
GlobalVariable.Alert = findTestData('Mobile Input Data/CreditManagement').getValue('Alert_Popup01', Credit_Index)

Mobile.waitForElementPresent(findTestObject('Mobile/Credit Management/Alert_Text'), 5, FailureHandling.CONTINUE_ON_FAILURE)

Mobile.takeScreenshot()

Mobile.verifyElementText(findTestObject('Mobile/Credit Management/Alert_Text'), GlobalVariable.Alert, FailureHandling.CONTINUE_ON_FAILURE)

Mobile.tap(findTestObject('Mobile/Common/Btn_OK'), 0)


WebUI.callTestCase(findTestCase('Product_Mobile/Common/Call_Analysis'), [:], FailureHandling.CONTINUE_ON_FAILURE)

WebUI.callTestCase(findTestCase('Test Cases/Product_Mobile/Common/Sync with End Attendence'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('Test Cases/Product_Mobile/Common/HHT_Transaction_DB'), [:], FailureHandling.STOP_ON_FAILURE)

'Scenario_02: Pre-requesite:'

'Past date updated for pending invoice of this retailers for warn credit period validation.'

Warn_Credit_Period = findTestData('Mobile Input Data/CreditManagement').getValue('Warn_Credit_Period', Credit_Index)

Warn_Credit_Period = (Integer.parseInt(Warn_Credit_Period) + 1)

Mobile.callTestCase(findTestCase('Product_Mobile/CM/Reusables/DB/Update_Pendingbill_Date'), [('Credit_Index') : Credit_Index, ('Datee') : -(Warn_Credit_Period)], FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('Product_Mobile/Common/Mobile_Login/VanSeller_Login-With Reset'), [:], FailureHandling.STOP_ON_FAILURE)

Mobile.callTestCase(findTestCase('Product_Mobile/Common/StartVisit'), [('RetailerName') : findTestData('Mobile Input Data/CreditManagement').getValue('Retailer_Name', Credit_Index)], FailureHandling.STOP_ON_FAILURE)

'Scenerio_02 : To verify whether the invoice is generated when the warn credit period and pending invoice count bill is exceeded.'

KeywordUtil.logInfo('Scenario_02 ::::--------------->>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>')

Mobile.callTestCase(findTestCase('Product_Mobile/CM/Reusables/Date_Difference'), [('Credit_Index') : Credit_Index], FailureHandling.STOP_ON_FAILURE)

Invoice_Period = GlobalVariable.sDate

'1. The invoice date should be greater than the warn credit period value.'

Mobile.verifyGreaterThan(Invoice_Period, Integer.parseInt(Warn_Credit_Period), FailureHandling.STOP_ON_FAILURE)

Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/Menu-OrderInvoice'), 0, FailureHandling.STOP_ON_FAILURE)

'2. \'Warning Credit days reached for invoices.\' toast message should be displayed in the store activity screen.'
Mobile.callTestCase(findTestCase('Product_Mobile/CM/Reusables/Toast_Validation'), [('Expecting_Toast_Text') : findTestData('Mobile Input Data/CreditManagement').getValue('Toast_Message02', Credit_Index)], FailureHandling.STOP_ON_FAILURE)

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Clear_Star_Filter_Default_Selection'), [:], FailureHandling.CONTINUE_ON_FAILURE)

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Search_Product'), [:], FailureHandling.CONTINUE_ON_FAILURE)

Mobile.setText(findTestObject('Mobile/Common/Field_Search_EnterText'), findTestData('Mobile Input Data/CreditManagement').getValue('CM_SKU_Name', 1), 5)

String PieceQty3 = "1"

GlobalVariable.Qty = PieceQty3

KeywordUtil.logInfo('SKU Qty in Piece :' + PieceQty3)

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Enter_PIECE_Qty'), [:], FailureHandling.STOP_ON_FAILURE)

Scenario_03_Pending_Invoice_Count = Current_Invoice_Count

KeywordUtil.logInfo('Scenario_03_Pending_Invoice_Count is : ' + Scenario_03_Pending_Invoice_Count.toString())

Total_Invoice_count = Scenario_01_Pending_Invoice_Count + Scenario_02_Pending_Invoice_Count+ Scenario_03_Pending_Invoice_Count

Credit_Bill = findTestData('Mobile Input Data/CreditManagement').getValue('Credit_Bill', Credit_Index)

KeywordUtil.logInfo('Credit_Bill :' + Credit_Bill)

'3. The pending invoice count should be greater than the warn credit bills value.'

Mobile.verifyGreaterThan(Total_Invoice_count, Integer.parseInt(Credit_Bill), FailureHandling.STOP_ON_FAILURE)

Mobile.tap(findTestObject('Mobile/Common/Btn_Next'), 0)

WebUI.callTestCase(findTestCase('Product_Mobile/Common/MustSell_Alert'), [:], FailureHandling.STOP_ON_FAILURE)

' Toast message should not be displayed in the stock and order screen.'
Mobile.callTestCase(findTestCase('Product_Mobile/CM/Reusables/Toast_Negative_Validation'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('Test Cases/VBL_Mobile/Re-Usables/Tap_Next_Btn_ApplyschemeScrn_CT'), [:], FailureHandling.STOP_ON_FAILURE)

Mobile.tap(findTestObject('Mobile/SummaryScreen/SummaryScreen-Invoice btn'), 0)

'4. \'Max Credit Bill Count Reached.\' alert should be displayed in the summary screen.'

GlobalVariable.Alert = findTestData('Mobile Input Data/CreditManagement').getValue('Alert_Popup02', Credit_Index)

Mobile.waitForElementPresent(findTestObject('Mobile/Credit Management/Alert_Text'), 5, FailureHandling.CONTINUE_ON_FAILURE)

Mobile.takeScreenshot()

Mobile.verifyElementText(findTestObject('Mobile/Credit Management/Alert_Text'), GlobalVariable.Alert, FailureHandling.CONTINUE_ON_FAILURE)

Mobile.tap(findTestObject('Mobile/Common/Btn_OK'), 0)



