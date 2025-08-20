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



Mobile.callTestCase(findTestCase('Product_Mobile/CM/Reusables/DB/Delete_Pendingbill_UpdateOverdue'), [('Row_Index') : [3]], FailureHandling.STOP_ON_FAILURE)

not_run: Mobile.callTestCase(findTestCase('Product_Mobile/CM/Reusables/DB/Web and DB Validation'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('Product_Mobile/Common/Mobile_Login/VanSeller_Login-With Reset'), [:], FailureHandling.STOP_ON_FAILURE)

Mobile.callTestCase(findTestCase('Product_Mobile/CM/Reusables/Stock_Update_Mobile'), [('ProductName') : findTestData('Mobile Input Data/CreditManagement').getValue('CM_SKU_Name', 1), ('Quantity') : '1000'], FailureHandling.STOP_ON_FAILURE)

//
//Mobile.startApplication(GlobalVariable.APK_File, false)
//
//Mobile.tap(findTestObject('Mobile/Common/Btn_Menu'), 10)
Credit_Index = 3

'Bill_Count_Secanrio_01: \r\n1. \'Warning Credit Bill Count Reached.\' toast message should not be displayed in the summary screen.\r\n2. \'Invoice created successfully.\' alert should be displayed in the summary screen.\r\n3. The total invoice count should be less than the credit bill count.'
KeywordUtil.logInfo('Scenario_01::::--------------->>>>>>>>>>>')

Mobile.callTestCase(findTestCase('Product_Mobile/Common/TradeCoverage(SelectRetailer)'), [('RetailerName') : findTestData('Mobile Input Data/CreditManagement').getValue('Retailer_Name', Credit_Index)], FailureHandling.STOP_ON_FAILURE)

Mobile.setText(findTestObject('Mobile/Common/Field_Search_EnterText'), findTestData('Mobile Input Data/CreditManagement').getValue('CM_SKU_Name', 1), 5)

GlobalVariable.Qty = '1'

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Enter_PIECE_Qty'), [:], FailureHandling.STOP_ON_FAILURE)

Mobile.tap(findTestObject('Mobile/Common/Btn_Next'), 0)

WebUI.callTestCase(findTestCase('Product_Mobile/Common/MustSell_Alert'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('Test Cases/VBL_Mobile/Re-Usables/Tap_Next_Btn_ApplyschemeScrn_CT'), [:], FailureHandling.STOP_ON_FAILURE)

Mobile.tap(findTestObject('Mobile/SummaryScreen/SummaryScreen-Invoice btn'), 0)

'1. \'Warning Credit Bill Count Reached.\' toast message should not be displayed in the summary screen.'
Mobile.callTestCase(findTestCase('Product_Mobile/CM/Reusables/Toast_Negative_Validation'), [:], FailureHandling.STOP_ON_FAILURE)

Mobile.callTestCase(findTestCase('Product_Mobile/Common/DatePicker_and _Navigation/Mobile Date Picker - Current Date'), [:], FailureHandling.OPTIONAL)

Mobile.tap(findTestObject('Mobile/Common/Btn_Confirm'), 0)

Mobile.takeScreenshot()

'2. \'Invoice created successfully.\' alert should be displayed in the summary screen'
GlobalVariable.Alert = findTestData('Mobile Input Data/CreditManagement').getValue('Alert_Popup01', Credit_Index)

Mobile.waitForElementPresent(findTestObject('Mobile/Credit Management/Alert_Text'), 5, FailureHandling.CONTINUE_ON_FAILURE)

Mobile.takeScreenshot()

Mobile.verifyElementText(findTestObject('Mobile/Credit Management/Alert_Text'), GlobalVariable.Alert, FailureHandling.CONTINUE_ON_FAILURE)

Mobile.tap(findTestObject('Mobile/Common/Btn_OK'), 0)

'When user login the application and visit the retailer that time Pending_Invoice_Count should be consider as zero after that first invoice generated successfully!'
int Current_Invoice_Count = 1

Scenario_01_Pending_Invoice_Count = Current_Invoice_Count

KeywordUtil.logInfo('Scenario_01_Pending_Invoice_Count is : ' + Scenario_01_Pending_Invoice_Count.toString())

Bill_Count = findTestData('Mobile Input Data/CreditManagement').getValue('Credit_Bill', Credit_Index)

KeywordUtil.logInfo('User set the Bill_Count in DMS : ' + Bill_Count)

'3. The total invoice count should be less than the credit bill count.'
Mobile.verifyLessThan(Scenario_01_Pending_Invoice_Count, Integer.parseInt(Bill_Count), FailureHandling.STOP_ON_FAILURE)

Mobile.callTestCase(findTestCase('Product_Mobile/CM/Reusables/Again_Goto_StockOrderScreen'), [:], FailureHandling.STOP_ON_FAILURE)

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
'Bill_Count_Secanrio_02: \r\n1. \'Warning Credit Bill Count Reached.\' toast message should not be displayed in the summary screen.\r\n2. \'Invoice created successfully.\' alert should be displayed in the summary screen.\r\n3. The total invoice count should be equal to the credit bill count.'
KeywordUtil.logInfo('Scenario_02::::--------------->>>>>>>>>>>')

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Clear_Star_Filter_Default_Selection'), [:], FailureHandling.CONTINUE_ON_FAILURE)

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Search_Product'), [:], FailureHandling.CONTINUE_ON_FAILURE)

Mobile.setText(findTestObject('Mobile/Common/Field_Search_EnterText'), findTestData('Mobile Input Data/CreditManagement').getValue('CM_SKU_Name', 1), 5)

GlobalVariable.Qty = '1'

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Enter_PIECE_Qty'), [:], FailureHandling.STOP_ON_FAILURE)

Mobile.tap(findTestObject('Mobile/Common/Btn_Next'), 0)

WebUI.callTestCase(findTestCase('Product_Mobile/Common/MustSell_Alert'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('Test Cases/VBL_Mobile/Re-Usables/Tap_Next_Btn_ApplyschemeScrn_CT'), [:], FailureHandling.STOP_ON_FAILURE)

Mobile.tap(findTestObject('Mobile/SummaryScreen/SummaryScreen-Invoice btn'), 0)

'1. \'Warning Credit Bill Count Reached.\' toast message should not be displayed in the summary screen.'
Mobile.callTestCase(findTestCase('Product_Mobile/CM/Reusables/Toast_Negative_Validation'), [:], FailureHandling.STOP_ON_FAILURE)

Mobile.callTestCase(findTestCase('Product_Mobile/Common/DatePicker_and _Navigation/Mobile Date Picker - Current Date'), [:], FailureHandling.OPTIONAL)

Mobile.tap(findTestObject('Mobile/Common/Btn_Confirm'), 0)

Mobile.takeScreenshot()

'2. \'Invoice created successfully.\' alert should be displayed in the summary screen'
GlobalVariable.Alert = findTestData('Mobile Input Data/CreditManagement').getValue('Alert_Popup01', Credit_Index)

Mobile.waitForElementPresent(findTestObject('Mobile/Credit Management/Alert_Text'), 5, FailureHandling.CONTINUE_ON_FAILURE)

Mobile.takeScreenshot()

Mobile.verifyElementText(findTestObject('Mobile/Credit Management/Alert_Text'), GlobalVariable.Alert, FailureHandling.CONTINUE_ON_FAILURE)

Mobile.tap(findTestObject('Mobile/Common/Btn_OK'), 0)

Scenario_02_Pending_Invoice_Count = (Scenario_01_Pending_Invoice_Count + Current_Invoice_Count)

KeywordUtil.logInfo('Scenario_02_Pending_Invoice_Count is : ' + Scenario_02_Pending_Invoice_Count.toString())

Bill_Count = findTestData('Mobile Input Data/CreditManagement').getValue('Credit_Bill', Credit_Index)

KeywordUtil.logInfo('User set the Bill_Count in DMS : ' + Bill_Count)

'3. Verify pending invoice count equal to the bill count.'
Mobile.verifyEqual(Scenario_02_Pending_Invoice_Count, Integer.parseInt(Bill_Count), FailureHandling.STOP_ON_FAILURE)

Mobile.callTestCase(findTestCase('Product_Mobile/CM/Reusables/Again_Goto_StockOrderScreen'), [:], FailureHandling.STOP_ON_FAILURE)

'Bill_Count_Secanrio_03: \r\n1. \'Warning Credit Bill Count Reached.\' toast message should not be displayed in the summary screen.\r\n2. \'Max Credit Bill Count Reached.\' alert should be displayed in the summary screen.\r\n3. The total invoice count should be greater than the credit bill count.'
KeywordUtil.logInfo('Scenario_03::::--------------->>>>>>>>>>>')

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Clear_Star_Filter_Default_Selection'), [:], FailureHandling.CONTINUE_ON_FAILURE)

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Search_Product'), [:], FailureHandling.CONTINUE_ON_FAILURE)

Mobile.setText(findTestObject('Mobile/Common/Field_Search_EnterText'), findTestData('Mobile Input Data/CreditManagement').getValue('CM_SKU_Name', 1), 5)

GlobalVariable.Qty = '1'

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Enter_PIECE_Qty'), [:], FailureHandling.STOP_ON_FAILURE)

Mobile.tap(findTestObject('Mobile/Common/Btn_Next'), 0)

WebUI.callTestCase(findTestCase('Product_Mobile/Common/MustSell_Alert'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('Test Cases/VBL_Mobile/Re-Usables/Tap_Next_Btn_ApplyschemeScrn_CT'), [:], FailureHandling.STOP_ON_FAILURE)

Mobile.tap(findTestObject('Mobile/SummaryScreen/SummaryScreen-Invoice btn'), 0)

Mobile.takeScreenshot()

'1. \'Warning Credit Bill Count Reached.\' toast message should not be displayed in the summary screen.'
Mobile.callTestCase(findTestCase('Product_Mobile/CM/Reusables/Toast_Negative_Validation'), [:], FailureHandling.STOP_ON_FAILURE)

'2. \'Max Credit Bill Count Reached.\' alert should be displayed in the summary screen.'
GlobalVariable.Alert = findTestData('Mobile Input Data/CreditManagement').getValue('Alert_Popup02', Credit_Index)

Mobile.waitForElementPresent(findTestObject('Mobile/Credit Management/Alert_Text'), 5, FailureHandling.CONTINUE_ON_FAILURE)

Mobile.takeScreenshot()

Mobile.verifyElementText(findTestObject('Mobile/Credit Management/Alert_Text'), GlobalVariable.Alert, FailureHandling.CONTINUE_ON_FAILURE)

Mobile.tap(findTestObject('Mobile/Common/Btn_OK'), 0)

Scenario_03_Pending_Invoice_Count = (Scenario_02_Pending_Invoice_Count + Current_Invoice_Count)

KeywordUtil.logInfo('Scenario_03_Pending_Invoice_Count is : ' + Scenario_03_Pending_Invoice_Count.toString())

Bill_Count = findTestData('Mobile Input Data/CreditManagement').getValue('Credit_Bill', Credit_Index)

KeywordUtil.logInfo('User set the Bill_Count in DMS : ' + Bill_Count)

'3. Verify pending invoice count greater than the bill count.'
Mobile.verifyGreaterThan(Scenario_03_Pending_Invoice_Count, Integer.parseInt(Bill_Count), FailureHandling.STOP_ON_FAILURE)

'In this scenario invoice not allow to generated So we need to reduce the Scenario_03_Pending_Invoice_Count '
Scenario_03_Pending_Invoice_Count = (Scenario_03_Pending_Invoice_Count - Current_Invoice_Count)

KeywordUtil.logInfo('Scenario_03_Pending_Invoice_Count is : ' + Scenario_03_Pending_Invoice_Count.toString())

Mobile.tap(findTestObject('Mobile/Common/Icon_Back'), 1, FailureHandling.OPTIONAL)

Mobile.delay(2)

Mobile.tap(findTestObject('Mobile/Common/Btn_OK'), 2, FailureHandling.OPTIONAL)

Mobile.delay(2)

Mobile.tap(findTestObject('Mobile/Common/Icon_Back'), 1, FailureHandling.OPTIONAL)

Mobile.delay(2)

Mobile.tap(findTestObject('Mobile/Common/Icon_Back'), 1, FailureHandling.OPTIONAL)

Mobile.delay(2)

Mobile.tap(findTestObject('Mobile/Common/Btn_OK'), 0, FailureHandling.OPTIONAL)

'Collect the pending invoice amount for one invoice'
Mobile.callTestCase(findTestCase('Product_Mobile/CM/Reusables/Invoice_Amt_Collected'), [('Credit_Index') : Credit_Index], FailureHandling.STOP_ON_FAILURE)

'User reduce the one invoice count because of collected cash for the one invoice'
Pending_Invoice_Count_After_Count = (Scenario_03_Pending_Invoice_Count - 1)

Mobile.callTestCase(findTestCase('Product_Mobile/CM/Reusables/Again_Goto_StockOrderScreen'), [:], FailureHandling.STOP_ON_FAILURE)

'Bill_Count_Secanrio_04: If user generated the invoice count equal to the bill count after collection.\r\n1. \'Warning Credit Bill Count Reached.\' toast message should not be displayed in the summary screen.\r\n2. \'Invoice created successfully.\' alert should be displayed in the summary screen.\r\n3. Verify pending invoice count equal to the bill count after collection.'
KeywordUtil.logInfo('Scenario_04::::--------------->>>>>>>>>>>')

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Clear_Star_Filter_Default_Selection'), [:], FailureHandling.CONTINUE_ON_FAILURE)

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Search_Product'), [:], FailureHandling.CONTINUE_ON_FAILURE)

Mobile.setText(findTestObject('Mobile/Common/Field_Search_EnterText'), findTestData('Mobile Input Data/CreditManagement').getValue('CM_SKU_Name', 1), 5)

GlobalVariable.Qty = '1'

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Enter_PIECE_Qty'), [:], FailureHandling.STOP_ON_FAILURE)

Mobile.tap(findTestObject('Mobile/Common/Btn_Next'), 0)

WebUI.callTestCase(findTestCase('Product_Mobile/Common/MustSell_Alert'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('Test Cases/VBL_Mobile/Re-Usables/Tap_Next_Btn_ApplyschemeScrn_CT'), [:], FailureHandling.STOP_ON_FAILURE)

Mobile.tap(findTestObject('Mobile/SummaryScreen/SummaryScreen-Invoice btn'), 0)

'1. \'Warning Credit Bill Count Reached.\' toast message should not be displayed in the summary screen.'
Mobile.callTestCase(findTestCase('Product_Mobile/CM/Reusables/Toast_Negative_Validation'), [:], FailureHandling.STOP_ON_FAILURE)

Mobile.callTestCase(findTestCase('Product_Mobile/Common/DatePicker_and _Navigation/Mobile Date Picker - Current Date'), [:], FailureHandling.OPTIONAL)

Mobile.tap(findTestObject('Mobile/Common/Btn_Confirm'), 0)

Mobile.takeScreenshot()

'2. \'Invoice created successfully.\' alert should be displayed in the summary screen'
GlobalVariable.Alert = findTestData('Mobile Input Data/CreditManagement').getValue('Alert_Popup01', Credit_Index)

Mobile.waitForElementPresent(findTestObject('Mobile/Credit Management/Alert_Text'), 5, FailureHandling.CONTINUE_ON_FAILURE)

Mobile.takeScreenshot()

Mobile.verifyElementText(findTestObject('Mobile/Credit Management/Alert_Text'), GlobalVariable.Alert, FailureHandling.CONTINUE_ON_FAILURE)

Mobile.tap(findTestObject('Mobile/Common/Btn_OK'), 0)

Scenario_04_Pending_Invoice_Count = (Pending_Invoice_Count_After_Count + Current_Invoice_Count)

KeywordUtil.logInfo('Scenario_04_Pending_Invoice_Count is : ' + Scenario_04_Pending_Invoice_Count.toString())

Bill_Count = findTestData('Mobile Input Data/CreditManagement').getValue('Credit_Bill', Credit_Index)

KeywordUtil.logInfo('User set the Bill_Count in DMS : ' + Bill_Count)

'3. Verify pending invoice count equal to the bill count.'
Mobile.verifyEqual(Scenario_04_Pending_Invoice_Count, Integer.parseInt(Bill_Count), FailureHandling.STOP_ON_FAILURE)

Mobile.callTestCase(findTestCase('Product_Mobile/CM/Reusables/Again_Goto_StockOrderScreen'), [:], FailureHandling.STOP_ON_FAILURE)

'Bill_Count_Secanrio_05: If user generated the invoice count greater than the bill count after collection.\r\n1. \'Warning Credit Bill Count Reached.\' toast message should not be displayed in the summary screen.\r\n2. \'Max Credit Bill Count Reached.\' alert should be displayed in the summary screen.\r\n3. Verify pending invoice count greater than the bill count after collection.'
KeywordUtil.logInfo('Scenario_05::::--------------->>>>>>>>>>>')

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Clear_Star_Filter_Default_Selection'), [:], FailureHandling.CONTINUE_ON_FAILURE)

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Search_Product'), [:], FailureHandling.CONTINUE_ON_FAILURE)

Mobile.setText(findTestObject('Mobile/Common/Field_Search_EnterText'), findTestData('Mobile Input Data/CreditManagement').getValue('CM_SKU_Name', 1), 5)

GlobalVariable.Qty = '1'

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Enter_PIECE_Qty'), [:], FailureHandling.STOP_ON_FAILURE)

Mobile.tap(findTestObject('Mobile/Common/Btn_Next'), 0)

WebUI.callTestCase(findTestCase('Product_Mobile/Common/MustSell_Alert'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('Test Cases/VBL_Mobile/Re-Usables/Tap_Next_Btn_ApplyschemeScrn_CT'), [:], FailureHandling.STOP_ON_FAILURE)

Mobile.tap(findTestObject('Mobile/SummaryScreen/SummaryScreen-Invoice btn'), 0)

Mobile.takeScreenshot()

'1. \'Warning Credit Bill Count Reached.\' toast message should not be displayed in the summary screen.'
Mobile.callTestCase(findTestCase('Product_Mobile/CM/Reusables/Toast_Negative_Validation'), [:], FailureHandling.STOP_ON_FAILURE)

'2. \'Max Credit Bill Count Reached.\' alert should be displayed in the summary screen.'
GlobalVariable.Alert = findTestData('Mobile Input Data/CreditManagement').getValue('Alert_Popup02', Credit_Index)

Mobile.waitForElementPresent(findTestObject('Mobile/Credit Management/Alert_Text'), 5, FailureHandling.CONTINUE_ON_FAILURE)

Mobile.takeScreenshot()

Mobile.verifyElementText(findTestObject('Mobile/Credit Management/Alert_Text'), GlobalVariable.Alert, FailureHandling.CONTINUE_ON_FAILURE)

Mobile.tap(findTestObject('Mobile/Common/Btn_OK'), 0)

Scenario_05_Pending_Invoice_Count = (Scenario_04_Pending_Invoice_Count + Current_Invoice_Count)

KeywordUtil.logInfo('Scenario_05_Pending_Invoice_Count is : ' + Scenario_05_Pending_Invoice_Count.toString())

Bill_Count = findTestData('Mobile Input Data/CreditManagement').getValue('Credit_Bill', Credit_Index)

KeywordUtil.logInfo('User set the Bill_Count in DMS : ' + Bill_Count)

'3. Verify pending invoice count greater than the bill count.'
Mobile.verifyGreaterThan(Scenario_05_Pending_Invoice_Count, Integer.parseInt(Bill_Count), FailureHandling.STOP_ON_FAILURE)

'In this scenario invoice not allow to generate so, we need to reduce the Scenario_05_Pending_Invoice_Count '
Scenario_05_Pending_Invoice_Count = (Scenario_05_Pending_Invoice_Count - Current_Invoice_Count)

KeywordUtil.logInfo('Scenario_05_Pending_Invoice_Count is : ' + Scenario_05_Pending_Invoice_Count.toString())

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
Credit_Index = 3

Mobile.callTestCase(findTestCase('Product_Mobile/CM/Reusables/Collection_Acceptance'), [('Credit_Index') : Credit_Index], FailureHandling.STOP_ON_FAILURE)

'Update pending bill date'
not_run: Mobile.callTestCase(findTestCase('Product_Mobile/CM/Reusables/DB/Update_Pendingbill_Date'), [('Credit_Index') : Credit_Index, ('Datee') : -1], FailureHandling.STOP_ON_FAILURE)

'Increased the Bill_Count value in web '
Mobile.callTestCase(findTestCase('Product_Mobile/CM/Reusables/DB/Increase_Value_In_Web'), [('Credit_Index') : Credit_Index], FailureHandling.STOP_ON_FAILURE)

'Verify the Bill_Count functionality in vanseller mobile after increased the Bill_Count in web'
not_run: Mobile.callTestCase(findTestCase('Product_Mobile/CM/Reusables/Download_Attendance_Vanload'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('Product_Mobile/Common/Mobile_Login/VanSeller_Login-With Reset'), [:], FailureHandling.STOP_ON_FAILURE)

Mobile.callTestCase(findTestCase('Product_Mobile/CM/Reusables/Stock_Update_Mobile'), [('ProductName') : findTestData('Mobile Input Data/CreditManagement').getValue('CM_SKU_Name', 1), ('Quantity') : '1000'], FailureHandling.STOP_ON_FAILURE)

'Bill_Count_Secanrio_06: If user generated the invoice count equal to the increased bill count\r\n1. \'Warning Credit Bill Count Reached.\' toast message should not be displayed in the summary screen.\r\n2. \'Invoice created successfully.\' alert should be displayed in the summary screen.\r\n3. Verify pending invoice count equal to the increased bill count.'
KeywordUtil.logInfo('Scenario_06::::--------------->>>>>>>>>>>')

Mobile.callTestCase(findTestCase('Product_Mobile/Common/TradeCoverage(SelectRetailer)'), [('RetailerName') : findTestData('Mobile Input Data/CreditManagement').getValue('Retailer_Name', Credit_Index)], FailureHandling.STOP_ON_FAILURE)

Mobile.setText(findTestObject('Mobile/Common/Field_Search_EnterText'), findTestData('Mobile Input Data/CreditManagement').getValue('CM_SKU_Name', 1), 5)

GlobalVariable.Qty = '1'

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Enter_PIECE_Qty'), [:], FailureHandling.STOP_ON_FAILURE)

Mobile.tap(findTestObject('Mobile/Common/Btn_Next'), 0)

WebUI.callTestCase(findTestCase('Product_Mobile/Common/MustSell_Alert'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('Test Cases/VBL_Mobile/Re-Usables/Tap_Next_Btn_ApplyschemeScrn_CT'), [:], FailureHandling.STOP_ON_FAILURE)

Mobile.tap(findTestObject('Mobile/SummaryScreen/SummaryScreen-Invoice btn'), 0)

'1. \'Warning Credit Bill Count Reached.\' toast message should not be displayed in the summary screen.'
Mobile.callTestCase(findTestCase('Product_Mobile/CM/Reusables/Toast_Negative_Validation'), [:], FailureHandling.STOP_ON_FAILURE)

Mobile.callTestCase(findTestCase('Product_Mobile/Common/DatePicker_and _Navigation/Mobile Date Picker - Current Date'), [:], FailureHandling.OPTIONAL)

Mobile.tap(findTestObject('Mobile/Common/Btn_Confirm'), 0)

Mobile.takeScreenshot()

'2. \'Invoice created successfully.\' alert should be displayed in the summary screen'
GlobalVariable.Alert = findTestData('Mobile Input Data/CreditManagement').getValue('Alert_Popup01', Credit_Index)

Mobile.waitForElementPresent(findTestObject('Mobile/Credit Management/Alert_Text'), 5, FailureHandling.CONTINUE_ON_FAILURE)

Mobile.takeScreenshot()

Mobile.verifyElementText(findTestObject('Mobile/Credit Management/Alert_Text'), GlobalVariable.Alert, FailureHandling.CONTINUE_ON_FAILURE)

Mobile.tap(findTestObject('Mobile/Common/Btn_OK'), 0)

'Get_pending_invoice_count for this retailer'
Mobile.callTestCase(findTestCase('Product_Mobile/CM/Reusables/DB/Get_Pending_Invoice_Count'), [('Credit_Index') : Credit_Index], FailureHandling.STOP_ON_FAILURE)

int Pending_Invoice_Count = GlobalVariable.label1

Scenario_06_Pending_Invoice_Count = (Pending_Invoice_Count + 1)

KeywordUtil.logInfo('Scenario_06_Pending_Invoice_Count is : ' + Scenario_06_Pending_Invoice_Count)

Increased_Bill_Count = findTestData('Mobile Input Data/CreditManagement').getValue('Increase_Value', Credit_Index)

KeywordUtil.logInfo('User set the Increased_Bill_Count in DMS : ' + Increased_Bill_Count)

'3. Verify pending invoice count equal to the bill count.'
Mobile.verifyEqual(Scenario_06_Pending_Invoice_Count, Integer.parseInt(Increased_Bill_Count), FailureHandling.STOP_ON_FAILURE)

Mobile.callTestCase(findTestCase('Product_Mobile/CM/Reusables/Again_Goto_StockOrderScreen'), [:], FailureHandling.STOP_ON_FAILURE)

////////////////////////////
'Bill_Count_Secanrio_07: If user generated the invoice count greater than the increased Bill_Count\r\n1. \'Warning Credit Bill Count Reached.\' toast message should not be displayed in the summary screen.\r\n2. \'Max Credit Bill Count Reached.\' alert should be displayed in the summary screen.\r\n3. Verify pending invoice count greater than the bill count.'
KeywordUtil.logInfo('Scenario_07::::--------------->>>>>>>>>>>')

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Clear_Star_Filter_Default_Selection'), [:], FailureHandling.CONTINUE_ON_FAILURE)

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Search_Product'), [:], FailureHandling.CONTINUE_ON_FAILURE)

Mobile.setText(findTestObject('Mobile/Common/Field_Search_EnterText'), findTestData('Mobile Input Data/CreditManagement').getValue('CM_SKU_Name', 1), 5)

GlobalVariable.Qty = '1'

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Enter_PIECE_Qty'), [:], FailureHandling.STOP_ON_FAILURE)

Mobile.tap(findTestObject('Mobile/Common/Btn_Next'), 0)

WebUI.callTestCase(findTestCase('Product_Mobile/Common/MustSell_Alert'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('Test Cases/VBL_Mobile/Re-Usables/Tap_Next_Btn_ApplyschemeScrn_CT'), [:], FailureHandling.STOP_ON_FAILURE)

Mobile.tap(findTestObject('Mobile/SummaryScreen/SummaryScreen-Invoice btn'), 0)

Mobile.takeScreenshot()

'1. \'Warning Credit Bill Count Reached.\' toast message should not be displayed in the summary screen.'
Mobile.callTestCase(findTestCase('Product_Mobile/CM/Reusables/Toast_Negative_Validation'), [:], FailureHandling.STOP_ON_FAILURE)

'2. \'Max Credit Bill Count Reached.\' alert should be displayed in the summary screen.'
GlobalVariable.Alert = findTestData('Mobile Input Data/CreditManagement').getValue('Alert_Popup02', Credit_Index)

Mobile.waitForElementPresent(findTestObject('Mobile/Credit Management/Alert_Text'), 5, FailureHandling.CONTINUE_ON_FAILURE)

Mobile.takeScreenshot()

Mobile.verifyElementText(findTestObject('Mobile/Credit Management/Alert_Text'), GlobalVariable.Alert, FailureHandling.CONTINUE_ON_FAILURE)

Mobile.tap(findTestObject('Mobile/Common/Btn_OK'), 0)

Scenario_07_Pending_Invoice_Count = (Scenario_06_Pending_Invoice_Count + 1)

KeywordUtil.logInfo('Scenario_07_Pending_Invoice_Count is : ' + Scenario_07_Pending_Invoice_Count.toString())

Bill_Count = findTestData('Mobile Input Data/CreditManagement').getValue('Increase_Value', Credit_Index)

KeywordUtil.logInfo('User set the Bill_Count in DMS : ' + Bill_Count)

'3. Verify pending invoice count greater than the bill count.'
Mobile.verifyGreaterThan(Scenario_07_Pending_Invoice_Count, Integer.parseInt(Bill_Count), FailureHandling.STOP_ON_FAILURE)

Mobile.tap(findTestObject('Mobile/Common/Icon_Back'), 1, FailureHandling.OPTIONAL)

Mobile.delay(2)

Mobile.tap(findTestObject('Mobile/Common/Btn_OK'), 2, FailureHandling.OPTIONAL)

Mobile.delay(2)

Mobile.tap(findTestObject('Mobile/Common/Icon_Back'), 1, FailureHandling.OPTIONAL)

Mobile.delay(2)

Mobile.tap(findTestObject('Mobile/Common/Icon_Back'), 1, FailureHandling.OPTIONAL)

Mobile.delay(2)

Mobile.tap(findTestObject('Mobile/Common/Btn_OK'), 0, FailureHandling.OPTIONAL)



'Collecting the invoice amount partically'
Mobile.callTestCase(findTestCase('Product_Mobile/CM/Reusables/Invoice_Amt_Partical_Collection'), [('Credit_Index') : ''], FailureHandling.STOP_ON_FAILURE)


if (Mobile.verifyElementExist(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/Menu-OrderInvoice'), 3, FailureHandling.OPTIONAL)) {
	'Order and invoice menu visible properly'
} else {
	Mobile.tap(findTestObject('Mobile/Store_Actvy/ScreenActivity_TopRightIcon(MenuReminder)'), 0, FailureHandling.OPTIONAL)

	Mobile.tap(findTestObject('Mobile/Common/Icon_Back'), 0, FailureHandling.OPTIONAL)

	Mobile.tap(findTestObject('Mobile/Common/Btn_OK'), 1, FailureHandling.OPTIONAL)

	Mobile.swipe(0, 150, 0, 400)

	Mobile.delay(1, FailureHandling.STOP_ON_FAILURE)

	Mobile.swipe(0, 150, 0, 400)
}

Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/Menu-OrderInvoice'), 0, FailureHandling.STOP_ON_FAILURE)

'Bill_Count_Secanrio_08: To verify whether the invoice is generated with partically collected invoice amount for any of the invoices. \r\n1. \'Warning Credit Bill Count Reached.\' toast message should not be displayed in the summary screen.\r\n2. \'Max Credit Bill Count Reached.\' alert should be displayed in the summary screen.\r\n3. Verify pending invoice count greater than the bill count.'
KeywordUtil.logInfo('Scenario_08::::--------------->>>>>>>>>>>')

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Clear_Star_Filter_Default_Selection'), [:], FailureHandling.CONTINUE_ON_FAILURE)

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Search_Product'), [:], FailureHandling.CONTINUE_ON_FAILURE)

Mobile.setText(findTestObject('Mobile/Common/Field_Search_EnterText'), findTestData('Mobile Input Data/CreditManagement').getValue('CM_SKU_Name', 1), 5)

GlobalVariable.Qty = '1'

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Enter_PIECE_Qty'), [:], FailureHandling.STOP_ON_FAILURE)

Mobile.tap(findTestObject('Mobile/Common/Btn_Next'), 0)

WebUI.callTestCase(findTestCase('Product_Mobile/Common/MustSell_Alert'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('Test Cases/VBL_Mobile/Re-Usables/Tap_Next_Btn_ApplyschemeScrn_CT'), [:], FailureHandling.STOP_ON_FAILURE)

Mobile.tap(findTestObject('Mobile/SummaryScreen/SummaryScreen-Invoice btn'), 0)

Mobile.takeScreenshot()

'1. \'Warning Credit Bill Count Reached.\' toast message should not be displayed in the summary screen.'
Mobile.callTestCase(findTestCase('Product_Mobile/CM/Reusables/Toast_Negative_Validation'), [:], FailureHandling.STOP_ON_FAILURE)

'2. \'Max Credit Bill Count Reached.\' alert should be displayed in the summary screen.'

'When we do partically collection for any invoice, pending invoice count will not be reduced'
GlobalVariable.Alert = findTestData('Mobile Input Data/CreditManagement').getValue('Alert_Popup02', Credit_Index)

Mobile.waitForElementPresent(findTestObject('Mobile/Credit Management/Alert_Text'), 5, FailureHandling.CONTINUE_ON_FAILURE)

Mobile.takeScreenshot()

Mobile.verifyElementText(findTestObject('Mobile/Credit Management/Alert_Text'), GlobalVariable.Alert, FailureHandling.CONTINUE_ON_FAILURE)

Mobile.tap(findTestObject('Mobile/Common/Btn_OK'), 0)

//Scenario_07_Pending_Invoice_Count = (Scenario_06_Pending_Invoice_Count + 1)
//KeywordUtil.logInfo('Scenario_08_Pending_Invoice_Count is : ' + Scenario_07_Pending_Invoice_Count.toString())

Bill_Count = findTestData('Mobile Input Data/CreditManagement').getValue('Increase_Value', Credit_Index)

KeywordUtil.logInfo('User set the Bill_Count in DMS : ' + Bill_Count)

'3. Verify pending invoice count greater than the bill count.'
//Mobile.verifyGreaterThan(Scenario_07_Pending_Invoice_Count, Integer.parseInt(Bill_Count), FailureHandling.STOP_ON_FAILURE)

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

