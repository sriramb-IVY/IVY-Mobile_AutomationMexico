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

//Mobile.startApplication(GlobalVariable.APK_File, false)
//
//Mobile.tap(findTestObject('Mobile/Common/Btn_Menu'), 10)
Mobile.callTestCase(findTestCase('Product_Mobile/Common/Module_Config_Enable_Update_DB'), [('Seller_Type_Id') : findTestData('Mobile Input Data/Module Configuration').getValue('Sales_Rep_Id', 1), ('Config_Code') : findTestData('Mobile Input Data/Module Configuration').getValue('Config_Code', 1), ('Rfield') : 1], 
    FailureHandling.STOP_ON_FAILURE)

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Module_Config_Enable_Update_DB'), [('Seller_Type_Id') : findTestData('Mobile Input Data/Module Configuration').getValue('Sales_Rep_Id', 1), ('Config_Code') : findTestData('Mobile Input Data/Module Configuration').getValue('Config_Code', 2), ('Rfield') : 2], 
    FailureHandling.STOP_ON_FAILURE)

Mobile.callTestCase(findTestCase('VBL_Mobile/Re-Usables/Web Login and Navigation/ClearCashe Login'), [:], FailureHandling.STOP_ON_FAILURE)

Mobile.callTestCase(findTestCase('Product_Mobile/CM/Reusables/DB/Delete_Pendingbill_UpdateOverdue,CD_Balance'), [('Row_Index') : [8, 9, 10]], FailureHandling.STOP_ON_FAILURE)

not_run: Mobile.callTestCase(findTestCase('Product_Mobile/CM/Reusables/DB/Web and DB Validation'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('Product_Mobile/Common/Mobile_Login/VanSeller_Login-With Reset'), [:], FailureHandling.STOP_ON_FAILURE)

Mobile.callTestCase(findTestCase('Product_Mobile/CM/Reusables/Stock_Update_Mobile'), [('ProductName') : findTestData('Mobile Input Data/CreditManagement').getValue('CM_SKU_Name', 1), ('Quantity') : '1000'], FailureHandling.STOP_ON_FAILURE)

///////////////////////////////
'Generated invoice for 3 retailer for warn credit period validation.'
Credit_Index = 8

Mobile.callTestCase(findTestCase('Product_Mobile/CM/Reusables/Generated_Invoice'), [('Credit_Index') : Credit_Index], FailureHandling.STOP_ON_FAILURE)

Credit_Index = 9

Mobile.callTestCase(findTestCase('Product_Mobile/CM/Reusables/Generated_Invoice'), [('Credit_Index') : Credit_Index], FailureHandling.STOP_ON_FAILURE)

Credit_Index = 10

Mobile.callTestCase(findTestCase('Product_Mobile/CM/Reusables/Generated_Invoice'), [('Credit_Index') : Credit_Index], FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('Test Cases/Product_Mobile/Common/Sync with End Attendence'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('Test Cases/Product_Mobile/Common/HHT_Transaction_DB'), [:], FailureHandling.STOP_ON_FAILURE)

'Past date updated for pending invoice of 3 retailers for warn credit period validation.'
Credit_Period = findTestData('Mobile Input Data/CreditManagement').getValue('Credit_Period', 5)

LessThanCreditPeriod = (Integer.parseInt(Credit_Period) - 1)

GreaterThanCreditPeriod = (Integer.parseInt(Credit_Period) + 1)

Mobile.callTestCase(findTestCase('Product_Mobile/CM/Reusables/DB/Update_Pendingbill_Date'), [('Credit_Index') : 8, ('Datee') : -(LessThanCreditPeriod)], FailureHandling.STOP_ON_FAILURE)

Mobile.callTestCase(findTestCase('Product_Mobile/CM/Reusables/DB/Update_Pendingbill_Date'), [('Credit_Index') : 9, ('Datee') : -(GreaterThanCreditPeriod)], FailureHandling.STOP_ON_FAILURE)

Mobile.callTestCase(findTestCase('Product_Mobile/CM/Reusables/DB/Update_Pendingbill_Date'), [('Credit_Index') : 10, ('Datee') : -(GreaterThanCreditPeriod)], FailureHandling.STOP_ON_FAILURE)

'Warn_Credit_Period_Scenario_01:-\r\nTo verify whether the invoice is generated if the pending invoice period is less than the warn credit period value.'
Credit_Index = 8

WebUI.callTestCase(findTestCase('Product_Mobile/Common/Mobile_Login/VanSeller_Login-With Reset'), [:], FailureHandling.STOP_ON_FAILURE)

Mobile.callTestCase(findTestCase('Product_Mobile/Common/StartVisit'), [('RetailerName') : findTestData('Mobile Input Data/CreditManagement').getValue('Retailer_Name', Credit_Index)], FailureHandling.STOP_ON_FAILURE)

'1. The pending invoice date should be less than the warn credit period value.'
Mobile.callTestCase(findTestCase('Product_Mobile/CM/Reusables/Date_Difference'), [('Credit_Index') : Credit_Index], FailureHandling.STOP_ON_FAILURE)

Invoice_Period = GlobalVariable.sDate

Credit_Period = findTestData('Mobile Input Data/CreditManagement').getValue('Credit_Period', 5)

Mobile.verifyLessThan(Invoice_Period, Integer.parseInt(Credit_Period), FailureHandling.STOP_ON_FAILURE)

Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/Menu-OrderInvoice'), 0, FailureHandling.STOP_ON_FAILURE)

'2. \'Warning Credit days reached for invoices.\' toast message should not be displayed in the store activity screen.'
Mobile.callTestCase(findTestCase('Product_Mobile/CM/Reusables/Toast_Negative_Validation'), [:], FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

'3. \'Credit days for invoice got expired\' soft alert should not be displayed in the store activity screen.\r\n4. \'Not allowed to take order, credit balance 0\' soft alert should not be displayed in the store activity screen.'
Mobile.verifyElementNotExist(findTestObject('Mobile/Credit Management/Alert_Screen'), 0)

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Clear_Star_Filter_Default_Selection'), [:], FailureHandling.CONTINUE_ON_FAILURE)

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Search_Product'), [:], FailureHandling.CONTINUE_ON_FAILURE)

Mobile.callTestCase(findTestCase('Product_Mobile/CM/Reusables/EnterPieceQtyForSingleProduct'), [('ProductName') : findTestData('Mobile Input Data/CreditManagement').getValue('CM_SKU_Name', 1), ('PieceQty') : '60'], FailureHandling.STOP_ON_FAILURE)

Mobile.tap(findTestObject('Mobile/Common/Btn_Next'), 0)

WebUI.callTestCase(findTestCase('Product_Mobile/Common/MustSell_Alert'), [:], FailureHandling.STOP_ON_FAILURE)

'5. \'Order exceeds Credit Balance\' toast message should not be displayed in the Stock&Order screen.'
Mobile.callTestCase(findTestCase('Product_Mobile/CM/Reusables/Toast_Negative_Validation'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('Test Cases/VBL_Mobile/Re-Usables/Tap_Next_Btn_ApplyschemeScrn_CT'), [:], FailureHandling.STOP_ON_FAILURE)

Mobile.tap(findTestObject('Mobile/SummaryScreen/SummaryScreen-Invoice btn'), 0)

'6. \'Amount exceeds warning Credit Balance.\' toast message should not be displayed in the summary screen.'
Mobile.callTestCase(findTestCase('Product_Mobile/CM/Reusables/Toast_Negative_Validation'), [:], FailureHandling.STOP_ON_FAILURE)

//Mobile.callTestCase(findTestCase('Product_Mobile/CM/Reusables/Toast_Negative_Validation'), [:], FailureHandling.CONTINUE_ON_FAILURE)
Mobile.callTestCase(findTestCase('Product_Mobile/Common/DatePicker_and _Navigation/Mobile Date Picker - Current Date'), [:], FailureHandling.OPTIONAL)

Mobile.tap(findTestObject('Mobile/Common/Btn_Confirm'), 0)

Mobile.takeScreenshot()

'7. \'Invoice created successfully.\' alert should be displayed in the summary screen.'
Mobile.waitForElementPresent(findTestObject('Mobile/Credit Management/InvoiceSubmittedSuccessfully_Popup'), 5, FailureHandling.CONTINUE_ON_FAILURE)

Expected_Alert_Text = findTestData('Mobile Input Data/CreditManagement').getValue('Alert_Popup01', Credit_Index)

Mobile.verifyElementText(findTestObject('Mobile/Credit Management/InvoiceSubmittedSuccessfully_Popup'), Expected_Alert_Text, FailureHandling.CONTINUE_ON_FAILURE)

Mobile.tap(findTestObject('Mobile/Common/Btn_OK'), 0)

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Call_Analysis'), [:], FailureHandling.STOP_ON_FAILURE)

'Warn_Credit_Period_Scenario_02:\r\nTo verify whether the invoice is generated if the pending invoice period is greater than the warn credit period value.'
Credit_Index = 9

Mobile.callTestCase(findTestCase('Product_Mobile/Common/StartVisit'), [('RetailerName') : findTestData('Mobile Input Data/CreditManagement').getValue('Retailer_Name', Credit_Index)], FailureHandling.STOP_ON_FAILURE)

Mobile.callTestCase(findTestCase('Product_Mobile/CM/Reusables/Date_Difference'), [('Credit_Index') : Credit_Index], FailureHandling.STOP_ON_FAILURE)

Invoice_Period = GlobalVariable.sDate

Credit_Period = findTestData('Mobile Input Data/CreditManagement').getValue('Credit_Period', 5)

'1. The pending invoice date should be greater than the warn credit period value.'
Mobile.verifyGreaterThan(Invoice_Period, Integer.parseInt(Credit_Period), FailureHandling.STOP_ON_FAILURE)

Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/Menu-OrderInvoice'), 0, FailureHandling.STOP_ON_FAILURE)

'2. \'Warning Credit days reached for invoices.\' toast message should be displayed in the store activity screen.'
Mobile.callTestCase(findTestCase('Product_Mobile/CM/Reusables/Toast_Validation'), [('Expecting_Toast_Text') : findTestData('Mobile Input Data/CreditManagement').getValue('Toast_Message01', Credit_Index)], FailureHandling.STOP_ON_FAILURE)

'3. Store activity screen navigate to next screen(Stock&Order)'
Mobile.verifyElementVisible(findTestObject('Object Repository/Mobile/OrderInvoice/OrderInvoice_Title'), 3, FailureHandling.STOP_ON_FAILURE)

Mobile.tap(findTestObject('Mobile/Common/Icon_Back'), 1, FailureHandling.OPTIONAL)

'Credit_Period_Scenario_03:\r\nTo verify whether the invoice is generated if partial collection is done for the pending invoices with the invoice period greater than the warn credit period.'
Mobile.delay(2)

'Collect the pending invoice amount partically.'
Mobile.callTestCase(findTestCase('Product_Mobile/CM/Reusables/Invoice_Amt_Partical_Collection'), [('Credit_Index') : ''], FailureHandling.STOP_ON_FAILURE)

Mobile.callTestCase(findTestCase('Product_Mobile/CM/Reusables/Again_Goto_StockOrderScreen'), [:], FailureHandling.STOP_ON_FAILURE)

'1. \'Warning Credit days reached for invoices.\' toast message should be displayed in the store activity screen.'
Mobile.callTestCase(findTestCase('Product_Mobile/CM/Reusables/Toast_Validation'), [('Expecting_Toast_Text') : findTestData('Mobile Input Data/CreditManagement').getValue('Toast_Message01', Credit_Index)], FailureHandling.STOP_ON_FAILURE)

'2. Store activity screen navigate to next screen(Stock&Order)'
Mobile.verifyElementVisible(findTestObject('Object Repository/Mobile/OrderInvoice/OrderInvoice_Title'), 3, FailureHandling.STOP_ON_FAILURE)

Mobile.tap(findTestObject('Mobile/Common/Icon_Back'), 1, FailureHandling.OPTIONAL)

'Credit_Period_Scenario_04:\r\nTo verify whether the invoice is generated if collection is done for the pending invoices with the invoice period greater than the warn credit period.'
Mobile.delay(2)

'Fully Collected the pending invoice amount'
Mobile.callTestCase(findTestCase('Product_Mobile/CM/Reusables/Invoice_Amt_Collection(no date)'), [('Credit_Index') : Credit_Index], FailureHandling.STOP_ON_FAILURE)

'1. \'Warning Credit days reached for invoices.\' toast message should not be displayed in the store activity screen.\r\n'
Mobile.callTestCase(findTestCase('Product_Mobile/CM/Reusables/Toast_Negative_Validation'), [:], FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Clear_Star_Filter_Default_Selection'), [:], FailureHandling.CONTINUE_ON_FAILURE)

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Search_Product'), [:], FailureHandling.CONTINUE_ON_FAILURE)

Mobile.callTestCase(findTestCase('Product_Mobile/CM/Reusables/EnterPieceQtyForSingleProduct'), [('ProductName') : findTestData('Mobile Input Data/CreditManagement').getValue('CM_SKU_Name', 1), ('PieceQty') : '60'], FailureHandling.STOP_ON_FAILURE)

Mobile.tap(findTestObject('Mobile/Common/Btn_Next'), 0)

WebUI.callTestCase(findTestCase('Product_Mobile/Common/MustSell_Alert'), [:], FailureHandling.STOP_ON_FAILURE)

'\'Order exceeds Credit Balance\' toast message should not be displayed in the Stock&Order screen.'
Mobile.callTestCase(findTestCase('Product_Mobile/CM/Reusables/Toast_Negative_Validation'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('Test Cases/VBL_Mobile/Re-Usables/Tap_Next_Btn_ApplyschemeScrn_CT'), [:], FailureHandling.STOP_ON_FAILURE)

Mobile.tap(findTestObject('Mobile/SummaryScreen/SummaryScreen-Invoice btn'), 0)

'\'Amount exceeds warning Credit Balance.\' toast message should not be displayed in the summary screen.'
Mobile.callTestCase(findTestCase('Product_Mobile/CM/Reusables/Toast_Negative_Validation'), [:], FailureHandling.STOP_ON_FAILURE)

//Mobile.callTestCase(findTestCase('Product_Mobile/CM/Reusables/Toast_Negative_Validation'), [:], FailureHandling.CONTINUE_ON_FAILURE)
Mobile.callTestCase(findTestCase('Product_Mobile/Common/DatePicker_and _Navigation/Mobile Date Picker - Current Date'), [:], FailureHandling.OPTIONAL)

Mobile.tap(findTestObject('Mobile/Common/Btn_Confirm'), 0)

Mobile.takeScreenshot()

'2. \'Invoice created successfully.\' alert should be displayed in the summary screen.'
Mobile.waitForElementPresent(findTestObject('Mobile/Credit Management/InvoiceSubmittedSuccessfully_Popup'), 5, FailureHandling.CONTINUE_ON_FAILURE)

Expected_Alert_Text = findTestData('Mobile Input Data/CreditManagement').getValue('Alert_Popup01', Credit_Index)

Mobile.verifyElementText(findTestObject('Mobile/Credit Management/InvoiceSubmittedSuccessfully_Popup'), Expected_Alert_Text, FailureHandling.CONTINUE_ON_FAILURE)

Mobile.tap(findTestObject('Mobile/Common/Btn_OK'), 0)

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Call_Analysis'), [:], FailureHandling.STOP_ON_FAILURE)

'Credit_Period_Scenario_05:\r\nTo verify whether the invoice is generated if the pending invoice period is less than the increased warn credit period value.'
Credit_Index = 9

Mobile.callTestCase(findTestCase('Product_Mobile/Common/StartVisit'), [('RetailerName') : findTestData('Mobile Input Data/CreditManagement').getValue('Retailer_Name', Credit_Index)], FailureHandling.STOP_ON_FAILURE)

Mobile.callTestCase(findTestCase('Product_Mobile/CM/Reusables/Date_Difference'), [('Credit_Index') : Credit_Index], FailureHandling.STOP_ON_FAILURE)

Invoice_Period = GlobalVariable.sDate

Credit_Period = findTestData('Mobile Input Data/CreditManagement').getValue('Credit_Period', 5)

'The pending invoice date should be greater than the credit period value.'
Mobile.verifyGreaterThan(Invoice_Period, Integer.parseInt(Credit_Period), FailureHandling.STOP_ON_FAILURE)

Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/Menu-OrderInvoice'), 0, FailureHandling.STOP_ON_FAILURE)

Mobile.callTestCase(findTestCase('Product_Mobile/CM/Reusables/Toast_Validation'), [('Expecting_Toast_Text') : findTestData('Mobile Input Data/CreditManagement').getValue('Toast_Message01', Credit_Index)], FailureHandling.STOP_ON_FAILURE)

Mobile.tap(findTestObject('Mobile/Common/Icon_Back'), 1, FailureHandling.OPTIONAL)

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Call_Analysis'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('Test Cases/Product_Mobile/Common/Sync with End Attendence'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('Test Cases/Product_Mobile/Common/HHT_Transaction_DB'), [:], FailureHandling.STOP_ON_FAILURE)

'Scenario_05: Pre-requesite for increased credit period value in web.'
Credit_Index = 10

Mobile.callTestCase(findTestCase('Product_Mobile/CM/Reusables/DB/Increase_Value_In_Web'), [('Credit_Index') : 7], FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('Product_Mobile/Common/Mobile_Login/VanSeller_Login-With Reset'), [:], FailureHandling.STOP_ON_FAILURE)

Mobile.callTestCase(findTestCase('Product_Mobile/Common/StartVisit'), [('RetailerName') : findTestData('Mobile Input Data/CreditManagement').getValue('Retailer_Name', Credit_Index)], FailureHandling.STOP_ON_FAILURE)

Mobile.callTestCase(findTestCase('Product_Mobile/CM/Reusables/Date_Difference'), [('Credit_Index') : Credit_Index], FailureHandling.STOP_ON_FAILURE)

Invoice_Period = GlobalVariable.sDate

Increase_Value = findTestData('Mobile Input Data/CreditManagement').getValue('Increase_Value', Credit_Index)

'1. The pending invoice date should be less than the increased credit period value.'
Mobile.verifyLessThan(Invoice_Period, Integer.parseInt(Increase_Value), FailureHandling.STOP_ON_FAILURE)

Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/Menu-OrderInvoice'), 0, FailureHandling.STOP_ON_FAILURE)

'2. \'Warning Credit days reached for invoices.\' toast message should not be displayed in the store activity screen.'
Mobile.callTestCase(findTestCase('Product_Mobile/CM/Reusables/Toast_Negative_Validation'), [:], FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

'3. \'Credit days for invoice got expired\' soft alert should not be displayed in the store activity screen.'
Mobile.verifyElementNotExist(findTestObject('Mobile/Credit Management/Alert_Screen'), 0)

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Clear_Star_Filter_Default_Selection'), [:], FailureHandling.CONTINUE_ON_FAILURE)

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Search_Product'), [:], FailureHandling.CONTINUE_ON_FAILURE)

Mobile.callTestCase(findTestCase('Product_Mobile/CM/Reusables/EnterPieceQtyForSingleProduct'), [('ProductName') : findTestData('Mobile Input Data/CreditManagement').getValue('CM_SKU_Name', 1), ('PieceQty') : '60'], FailureHandling.STOP_ON_FAILURE)

Mobile.tap(findTestObject('Mobile/Common/Btn_Next'), 0)

WebUI.callTestCase(findTestCase('Product_Mobile/Common/MustSell_Alert'), [:], FailureHandling.STOP_ON_FAILURE)

'4. \'Order exceeds Credit Balance\' toast message should not be displayed in the Stock&Order screen.'
Mobile.callTestCase(findTestCase('Product_Mobile/CM/Reusables/Toast_Negative_Validation'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('Test Cases/VBL_Mobile/Re-Usables/Tap_Next_Btn_ApplyschemeScrn_CT'), [:], FailureHandling.STOP_ON_FAILURE)

Mobile.tap(findTestObject('Mobile/SummaryScreen/SummaryScreen-Invoice btn'), 0)

'5. \'Amount exceeds warning Credit Balance.\' toast message should not be displayed in the summary screen.'
Mobile.callTestCase(findTestCase('Product_Mobile/CM/Reusables/Toast_Negative_Validation'), [:], FailureHandling.STOP_ON_FAILURE)

//Mobile.callTestCase(findTestCase('Product_Mobile/CM/Reusables/Toast_Negative_Validation'), [:], FailureHandling.CONTINUE_ON_FAILURE)
Mobile.callTestCase(findTestCase('Product_Mobile/Common/DatePicker_and _Navigation/Mobile Date Picker - Current Date'), [:], FailureHandling.OPTIONAL)

Mobile.tap(findTestObject('Mobile/Common/Btn_Confirm'), 0)

Mobile.takeScreenshot()

'6. \'Invoice created successfully.\' alert should be displayed in the summary screen.'
Mobile.waitForElementPresent(findTestObject('Mobile/Credit Management/InvoiceSubmittedSuccessfully_Popup'), 5, FailureHandling.CONTINUE_ON_FAILURE)

Expected_Alert_Text = findTestData('Mobile Input Data/CreditManagement').getValue('Alert_Popup01', Credit_Index)

Mobile.verifyElementText(findTestObject('Mobile/Credit Management/InvoiceSubmittedSuccessfully_Popup'), Expected_Alert_Text, FailureHandling.CONTINUE_ON_FAILURE)

Mobile.tap(findTestObject('Mobile/Common/Btn_OK'), 0)

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Call_Analysis'), [:], FailureHandling.STOP_ON_FAILURE)

