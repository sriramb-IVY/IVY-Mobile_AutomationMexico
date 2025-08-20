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

Mobile.callTestCase(findTestCase('Product_Mobile/CM/Reusables/DB/Delete_Pendingbill_UpdateOverdue'), [('Row_Index') : [4]], FailureHandling.STOP_ON_FAILURE)

not_run: Mobile.callTestCase(findTestCase('Product_Mobile/CM/Reusables/DB/Web and DB Validation'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('Product_Mobile/Common/Mobile_Login/VanSeller_Login-With Reset'), [:], FailureHandling.STOP_ON_FAILURE)

Mobile.callTestCase(findTestCase('Product_Mobile/CM/Reusables/Stock_Update_Mobile'), [('ProductName') : findTestData('Mobile Input Data/CreditManagement').getValue('CM_SKU_Name', 1), ('Quantity') : '1000'], FailureHandling.STOP_ON_FAILURE)

//
//Mobile.startApplication(GlobalVariable.APK_File, false)
//
//Mobile.tap(findTestObject('Mobile/Common/Btn_Menu'), 10)
Credit_Index = 4

'Warn_Bill_Count_Secanrio_01: If user generated the invoice count less than the Warn_Bill_Count:\r\n1. \'Warning Credit Bill Count Reached..\' toast message should not be displayed in the summary screen.\r\n2. \'Invoice created successfully.\' alert should be displayed in the summary screen.\r\n3. Verify pending invoice count less than the Warn_Bill_Count.'
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

Warn_Bill_Count = findTestData('Mobile Input Data/CreditManagement').getValue('Warn_Credit_Bill', Credit_Index)

KeywordUtil.logInfo('User set the Warn_Bill_Count in DMS : ' + Warn_Bill_Count)

'3. Verify pending invoice count less than the Warn_Bill_Count.'
Mobile.verifyLessThan(Scenario_01_Pending_Invoice_Count, Integer.parseInt(Warn_Bill_Count), FailureHandling.STOP_ON_FAILURE)

Mobile.callTestCase(findTestCase('Product_Mobile/CM/Reusables/Again_Goto_StockOrderScreen'), [:], FailureHandling.STOP_ON_FAILURE)

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
'Warn_Bill_Count_Secanrio_02: If user generated the invoice count equal to the Warn_Bill_Count\r\n1. \'Warning Credit Bill Count Reached..\' toast message should not be displayed in the summary screen.\r\n2. \'Invoice created successfully.\' alert should be displayed in the summary screen.\r\n3. Verify pending invoice count equal to the Warn_Bill_Count.'
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

Warn_Bill_Count = findTestData('Mobile Input Data/CreditManagement').getValue('Warn_Credit_Bill', Credit_Index)

KeywordUtil.logInfo('User set the Warn_Bill_Count in DMS : ' + Warn_Bill_Count)

'3. Verify pending invoice count equal to the warn bill count.'
Mobile.verifyEqual(Scenario_02_Pending_Invoice_Count, Integer.parseInt(Warn_Bill_Count), FailureHandling.STOP_ON_FAILURE)

Mobile.callTestCase(findTestCase('Product_Mobile/CM/Reusables/Again_Goto_StockOrderScreen'), [:], FailureHandling.STOP_ON_FAILURE)

'Warn_Bill_Count_Secanrio_03: If user generated the invoice count greater than the Warn_Bill_Count\r\n1. \'Warning Credit Bill Count Reached..\' toast message should be displayed in the summary screen.\r\n3. Verify pending invoice count greater than the Warn_Bill_Count.'
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

'1. \'Warning Credit Bill Count Reached.\' toast message should be displayed in the summary screen.'
Mobile.callTestCase(findTestCase('Product_Mobile/CM/Reusables/Toast_Validation'), [('Expecting_Toast_Text') : findTestData('Mobile Input Data/CreditManagement').getValue('Toast_Message01', Credit_Index)], FailureHandling.STOP_ON_FAILURE)

Scenario_03_Pending_Invoice_Count = (Scenario_02_Pending_Invoice_Count + Current_Invoice_Count)

KeywordUtil.logInfo('Scenario_03_Pending_Invoice_Count is : ' + Scenario_03_Pending_Invoice_Count.toString())

Warn_Bill_Count = findTestData('Mobile Input Data/CreditManagement').getValue('Warn_Credit_Bill', Credit_Index)

KeywordUtil.logInfo('User set the Warn_Bill_Count in DMS : ' + Warn_Bill_Count)

'2. Verify pending invoice count greater than the bill count.'
Mobile.verifyGreaterThan(Scenario_03_Pending_Invoice_Count, Integer.parseInt(Warn_Bill_Count), FailureHandling.STOP_ON_FAILURE)

'In this scenario invoice generation allowed but user not generated So, we need to reduce the Scenario_03_Pending_Invoice_Count '
Scenario_03_Pending_Invoice_Count = (Scenario_03_Pending_Invoice_Count - Current_Invoice_Count)

KeywordUtil.logInfo('Scenario_03_Pending_Invoice_Count is : ' + Scenario_03_Pending_Invoice_Count.toString())

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

'Collect the pending invoice amount for one invoice'
Mobile.callTestCase(findTestCase('Product_Mobile/CM/Reusables/Invoice_Amt_Collected'), [('Credit_Index') : Credit_Index], FailureHandling.STOP_ON_FAILURE)

'User reduce the one invoice count because of collected cash for the one invoice'
Pending_Invoice_Count_After_Count = (Scenario_03_Pending_Invoice_Count - 1)

Mobile.callTestCase(findTestCase('Product_Mobile/CM/Reusables/Again_Goto_StockOrderScreen'), [:], FailureHandling.STOP_ON_FAILURE)

'Warn_Bill_Count_Secanrio_04: If user generated the invoice count equal to the Warn_Bill_Count after collection.\r\n1. \'Warning Credit Bill Count Reached..\' toast message should not be displayed in the summary screen.\r\n2. \'Invoice created successfully.\' alert should be displayed in the summary screen.\r\n3. Verify pending invoice count equal to the Warn_Bill_Count after collection.'
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

Warn_Bill_Count = findTestData('Mobile Input Data/CreditManagement').getValue('Warn_Credit_Bill', Credit_Index)

KeywordUtil.logInfo('User set the Warn_Bill_Count in DMS : ' + Warn_Bill_Count)

'3. Verify pending invoice count equal to the warn bill count.'
Mobile.verifyEqual(Scenario_04_Pending_Invoice_Count, Integer.parseInt(Warn_Bill_Count), FailureHandling.STOP_ON_FAILURE)

Mobile.callTestCase(findTestCase('Product_Mobile/CM/Reusables/Again_Goto_StockOrderScreen'), [:], FailureHandling.STOP_ON_FAILURE)

'Warn_Bill_Count_Secanrio_05: If user generated the invoice count greater than the Warn_Bill_Count after collection.\r\n1. \'Warning Credit Bill Count Reached..\' toast message should be displayed in the summary screen.\r\n2. Verify pending invoice count greater than the Warn_Bill_Count after collection.'
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

'1. \'Warning Credit Bill Count Reached.\' toast message should be displayed in the summary screen.'
Mobile.callTestCase(findTestCase('Product_Mobile/CM/Reusables/Toast_Validation'), [('Expecting_Toast_Text') : findTestData('Mobile Input Data/CreditManagement').getValue('Toast_Message01', Credit_Index)], FailureHandling.STOP_ON_FAILURE)

Scenario_05_Pending_Invoice_Count = (Scenario_04_Pending_Invoice_Count + Current_Invoice_Count)

KeywordUtil.logInfo('Scenario_05_Pending_Invoice_Count is : ' + Scenario_05_Pending_Invoice_Count.toString())

Warn_Bill_Count = findTestData('Mobile Input Data/CreditManagement').getValue('Warn_Credit_Bill', Credit_Index)

KeywordUtil.logInfo('User set the Warn_Bill_Count in DMS : ' + Warn_Bill_Count)

'2. Verify pending invoice count greater than the warn bill count.'
Mobile.verifyGreaterThan(Scenario_05_Pending_Invoice_Count, Integer.parseInt(Warn_Bill_Count), FailureHandling.STOP_ON_FAILURE)

'In this scenario invoice allow to generate but user not generated so, we need to reduce the Scenario_05_Pending_Invoice_Count '
Scenario_05_Pending_Invoice_Count = (Scenario_05_Pending_Invoice_Count - Current_Invoice_Count)

KeywordUtil.logInfo('Scenario_05_Pending_Invoice_Count is : ' + Scenario_05_Pending_Invoice_Count.toString())

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
Credit_Index = 4

Mobile.callTestCase(findTestCase('Product_Mobile/CM/Reusables/Collection_Acceptance'), [('Credit_Index') : Credit_Index], FailureHandling.STOP_ON_FAILURE)

'Update pending bill date'
Mobile.callTestCase(findTestCase('Product_Mobile/CM/Reusables/DB/Update_Pendingbill_Date'), [('Credit_Index') : Credit_Index, ('Datee') : -1], FailureHandling.STOP_ON_FAILURE)

'Increased the Bill_Count value in web '
Mobile.callTestCase(findTestCase('Product_Mobile/CM/Reusables/DB/Increase_Value_In_Web'), [('Credit_Index') : Credit_Index], FailureHandling.STOP_ON_FAILURE)

'Verify the Warn_Bill_Count functionality in vanseller mobile after increased the Warn_Bill_Count in web'
not_run: Mobile.callTestCase(findTestCase('Product_Mobile/CM/Reusables/Download_Attendance_Vanload'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('Product_Mobile/Common/Mobile_Login/VanSeller_Login-With Reset'), [:], FailureHandling.STOP_ON_FAILURE)

Mobile.callTestCase(findTestCase('Product_Mobile/CM/Reusables/Stock_Update_Mobile'), [('ProductName') : findTestData('Mobile Input Data/CreditManagement').getValue('CM_SKU_Name', 1), ('Quantity') : '1000'], FailureHandling.STOP_ON_FAILURE)

'Warn_Bill_Count_Secanrio_06: If user generated the invoice count equal to the increased Warn_Warn_Bill_Count\r\n1. \'Warning Credit Bill Count Reached..\' toast message should not be displayed in the summary screen.\r\n2. \'Invoice created successfully.\' alert should be displayed in the summary screen.\r\n3. Verify pending invoice count equal to the increased Warn_Bill_Count.'
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

Increased_Warn_Bill_Count = findTestData('Mobile Input Data/CreditManagement').getValue('Increase_Value', Credit_Index)

KeywordUtil.logInfo('User set the Increased_Warn_Bill_Count in DMS : ' + Increased_Warn_Bill_Count)

'3. Verify pending invoice count equal to the Increased_Warn_Bill_Count.'
Mobile.verifyEqual(Scenario_06_Pending_Invoice_Count, Integer.parseInt(Increased_Warn_Bill_Count), FailureHandling.STOP_ON_FAILURE)

Mobile.callTestCase(findTestCase('Product_Mobile/CM/Reusables/Again_Goto_StockOrderScreen'), [:], FailureHandling.STOP_ON_FAILURE)

////////////////////////////
'Warn_Bill_Count_Secanrio_07: If user generated the invoice count greater than the increased Warn_Bill_Count.\r\n1. \'Warning Credit Bill Count Reached..\' toast message should be displayed in the summary screen.\r\n2. Verify pending invoice count greater than the increased Warn_Bill_Count.\r\n3. \'Invoice created successfully.\' alert should be displayed in the summary screen'
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

'1. \'Warning Credit Bill Count Reached.\' toast message should be displayed in the summary screen.'
Mobile.callTestCase(findTestCase('Product_Mobile/CM/Reusables/Toast_Validation'), [('Expecting_Toast_Text') : findTestData('Mobile Input Data/CreditManagement').getValue('Toast_Message01', Credit_Index)], FailureHandling.STOP_ON_FAILURE)

Scenario_07_Pending_Invoice_Count = (Scenario_06_Pending_Invoice_Count + 1)

KeywordUtil.logInfo('Scenario_07_Pending_Invoice_Count is : ' + Scenario_07_Pending_Invoice_Count.toString())

Increased_Warn_Bill_Count = findTestData('Mobile Input Data/CreditManagement').getValue('Increase_Value', Credit_Index)

KeywordUtil.logInfo('User set the Increased_Warn_Bill_Count in DMS : ' + Increased_Warn_Bill_Count)

'2. Verify pending invoice count greater than the increased Warn bill count.'
Mobile.verifyGreaterThan(Scenario_07_Pending_Invoice_Count, Integer.parseInt(Increased_Warn_Bill_Count), FailureHandling.STOP_ON_FAILURE)

Mobile.callTestCase(findTestCase('Product_Mobile/Common/DatePicker_and _Navigation/Mobile Date Picker - Current Date'), [:], FailureHandling.OPTIONAL)

Mobile.tap(findTestObject('Mobile/Common/Btn_Confirm'), 0)

Mobile.takeScreenshot()

'3. \'Invoice created successfully.\' alert should be displayed in the summary screen'
GlobalVariable.Alert = findTestData('Mobile Input Data/CreditManagement').getValue('Alert_Popup01', Credit_Index)

Mobile.waitForElementPresent(findTestObject('Mobile/Credit Management/Alert_Text'), 5, FailureHandling.CONTINUE_ON_FAILURE)

Mobile.takeScreenshot()

Mobile.verifyElementText(findTestObject('Mobile/Credit Management/Alert_Text'), GlobalVariable.Alert, FailureHandling.CONTINUE_ON_FAILURE)

Mobile.tap(findTestObject('Mobile/Common/Btn_OK'), 0, FailureHandling.OPTIONAL)

'Collecting the invoice amount partically'
Mobile.callTestCase(findTestCase('Product_Mobile/CM/Reusables/Invoice_Amt_Partical_Collection'), [('Credit_Index') : ''], FailureHandling.STOP_ON_FAILURE)

'Warn_Bill_Count_Secanrio_08: If user generated the invoice count greater than the increased Warn_Bill_Count.\r\n1. \'Warning Credit Bill Count Reached..\' toast message should be displayed in the summary screen.\r\n2. Verify pending invoice count greater than the increased Warn_Bill_Count.\r\n3. \'Invoice created successfully.\' alert should be displayed in the summary screen'
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

'1. \'Warning Credit Bill Count Reached.\' toast message should be displayed in the summary screen.'
Mobile.callTestCase(findTestCase('Product_Mobile/CM/Reusables/Toast_Validation'), [('Expecting_Toast_Text') : findTestData('Mobile Input Data/CreditManagement').getValue('Toast_Message01', Credit_Index)], FailureHandling.STOP_ON_FAILURE)

Scenario_07_Pending_Invoice_Count = (Scenario_06_Pending_Invoice_Count + 1)

KeywordUtil.logInfo('Scenario_07_Pending_Invoice_Count is : ' + Scenario_07_Pending_Invoice_Count.toString())

Increased_Warn_Bill_Count = findTestData('Mobile Input Data/CreditManagement').getValue('Increase_Value', Credit_Index)

KeywordUtil.logInfo('User set the Increased_Warn_Bill_Count in DMS : ' + Increased_Warn_Bill_Count)

'2. Verify pending invoice count greater than the increased Warn bill count.'
Mobile.verifyGreaterThan(Scenario_07_Pending_Invoice_Count, Integer.parseInt(Increased_Warn_Bill_Count), FailureHandling.STOP_ON_FAILURE)

Mobile.callTestCase(findTestCase('Product_Mobile/Common/DatePicker_and _Navigation/Mobile Date Picker - Current Date'), [:], FailureHandling.OPTIONAL)

Mobile.tap(findTestObject('Mobile/Common/Btn_Confirm'), 0)

Mobile.takeScreenshot()

'3. \'Invoice created successfully.\' alert should be displayed in the summary screen'
GlobalVariable.Alert = findTestData('Mobile Input Data/CreditManagement').getValue('Alert_Popup01', Credit_Index)

Mobile.waitForElementPresent(findTestObject('Mobile/Credit Management/Alert_Text'), 5, FailureHandling.CONTINUE_ON_FAILURE)

Mobile.takeScreenshot()

Mobile.verifyElementText(findTestObject('Mobile/Credit Management/Alert_Text'), GlobalVariable.Alert, FailureHandling.CONTINUE_ON_FAILURE)

Mobile.tap(findTestObject('Mobile/Common/Btn_OK'), 0, FailureHandling.OPTIONAL)

WebUI.callTestCase(findTestCase('Product_Mobile/Common/Call_Analysis'), [:], FailureHandling.CONTINUE_ON_FAILURE)

