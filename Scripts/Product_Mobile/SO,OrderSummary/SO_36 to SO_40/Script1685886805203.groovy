import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import internal.GlobalVariable as GlobalVariable
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil

// Mobile.startApplication(GlobalVariable.APKFile, false)

//Mobile.delay(30)

if (Mobile.verifyElementExist(findTestObject('Object Repository/Mobile/Store_Actvy/Menu-OrderInvoice'), 10, FailureHandling.OPTIONAL)) {
    'order invoice menu visible'
} else {
    Mobile.callTestCase(findTestCase('Product_Mobile/Common/Mobile_Login/Relaunch the app'), [:], FailureHandling.CONTINUE_ON_FAILURE)

    WebUI.callTestCase(findTestCase('Product_Mobile/Common/Seller_Menu_Selection'), [('MenuName') : findTestData('Mobile Input Data/Common').getValue('Menu', 3), ('SubMenuName') : findTestData('Mobile Input Data/Common').getValue('Menu', 1)], FailureHandling.STOP_ON_FAILURE)

    WebUI.callTestCase(findTestCase('Product_Mobile/Common/TradeCoverage(SelectRetailer)'), [('RetailerName') : findTestData('Mobile Input Data/Invoice').getValue('Retailer_Name', 1)], FailureHandling.STOP_ON_FAILURE)

    WebUI.callTestCase(findTestCase('Product_Mobile/Common/StartVisit'), [:], FailureHandling.STOP_ON_FAILURE)
}

WebUI.callTestCase(findTestCase('Product_Mobile/Common/Store_Menu_Selection'), [('MenuName') : findTestData('Mobile Input Data/Common').getValue('Menu', 4), ('SubMenuName') : findTestData('Mobile Input Data/Common').getValue('Menu', 1)], FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('Product_Mobile/Common/Clear_Star_Filter_Default_Selection'), [:], FailureHandling.STOP_ON_FAILURE)

'*************Enter Piece Qty****************'
int Piece_sku_index = 1

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Search_Product'), [('ProductName') : findTestData('Mobile Input Data/Invoice').getValue('Sku_Name', Piece_sku_index)], FailureHandling.STOP_ON_FAILURE)

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Enter_PIECE_Qty'), [('Quantity') : findTestData('Mobile Input Data/Invoice').getValue('Keypad_Number', 3)], FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

PieceQtySku1 = Mobile.getText(findTestObject('Mobile/OrderInvoice/Enter_Qty'), 0)

Actual_SkuTotal_1 = Mobile.getText(findTestObject('Mobile/OrderInvoice/OrderInvoiceScrn-SkuTotal'), 0)

PiecePrice = findTestData('Mobile Input Data/Invoice').getValue('PiecePrice', Piece_sku_index)

CaseSize = findTestData('Mobile Input Data/Invoice').getValue('CaseSize', Piece_sku_index)

Calculated_SkuTotal_1 = (Double.parseDouble(PieceQtySku1) * Double.parseDouble(PiecePrice))


KeywordUtil.logInfo('Total 1 : '+Calculated_SkuTotal_1)

Mobile.verifyEqual(Double.parseDouble(Actual_SkuTotal_1), Calculated_SkuTotal_1, FailureHandling.CONTINUE_ON_FAILURE)

Mobile.takeScreenshot('SO_36 :To verify Total Value field if we enter qty in pieces field', FailureHandling.STOP_ON_FAILURE)

'*************Enter Case Qty****************'
int Case_sku_index = 2

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Search_Product'), [('ProductName') : findTestData('Mobile Input Data/Invoice').getValue('Sku_Name', Case_sku_index)], FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('Product_Mobile/Common/Enter_CASE_Qty'), [('Quantity') : findTestData('Mobile Input Data/Invoice').getValue('Keypad_Number', 1)], FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

//Mobile.callTestCase(findTestCase('Product_Mobile/Common/Get_CASE_PIECE_Qty'), [:], FailureHandling.STOP_ON_FAILURE)

CaseQtySku2 = Mobile.getText(findTestObject('Mobile/OrderInvoice/Enter_Qty'), 0)

Actual_SkuTotal_2 = Mobile.getText(findTestObject('Mobile/OrderInvoice/OrderInvoiceScrn-SkuTotal'), 0)

PiecePrice = findTestData('Mobile Input Data/Invoice').getValue('PiecePrice', Case_sku_index)

CaseSize = findTestData('Mobile Input Data/Invoice').getValue('CaseSize', Case_sku_index)

Calculated_SkuTotal_2 = ((Double.parseDouble(CaseQtySku2) * Double.parseDouble(CaseSize)) * Double.parseDouble(PiecePrice))

KeywordUtil.logInfo('Total 2 : '+Calculated_SkuTotal_2)

Mobile.verifyEqual(Double.parseDouble(Actual_SkuTotal_2), Calculated_SkuTotal_2, FailureHandling.CONTINUE_ON_FAILURE)

Mobile.takeScreenshot()

KeywordUtil.logInfo('Verified: Total Value field if we enter qty in Case field')

'*************Enter Case_Piece Qty****************'
Piece_Qty_value = Integer.parseInt(PieceQtySku1)

KeywordUtil.logInfo('Piece qty : '+Piece_Qty_value)

Case_Qty_value = Integer.parseInt(CaseQtySku2)

KeywordUtil.logInfo('Case Qty : '+Case_Qty_value)

Mobile.hideKeyboard(FailureHandling.OPTIONAL)

Actual_Total_Value = Mobile.getText(findTestObject('Mobile/OrderInvoice/OrderInvoiceScreen_bottomValueField'), 0)

KeywordUtil.logInfo('Total Value : '+Actual_Total_Value)

Calculated_Total_Value = (Calculated_SkuTotal_1 + Calculated_SkuTotal_2)

KeywordUtil.logInfo('Cal Total value : '+Calculated_Total_Value)

Mobile.verifyEqual(Double.parseDouble(Actual_Total_Value), Calculated_Total_Value, FailureHandling.CONTINUE_ON_FAILURE)

Mobile.takeScreenshot()

KeywordUtil.logInfo('Verified : the total piece and case quantity in Order and Invoice Screen')

Total_PieceCase_Oty = Mobile.getText(findTestObject('Mobile/OrderInvoice/OrderInvoiceScreen-TotalQty(CasePiece)'), 0)

KeywordUtil.logInfo('Case piece qty : '+Total_PieceCase_Oty)

String[] arr = Total_PieceCase_Oty.split('/')

Actual_Case_Oty = (arr[0]).trim()

Actual_Piece_Oty = (arr[1]).trim()

Mobile.verifyMatch(Actual_Piece_Oty, Piece_Qty_value.toString() + ' P', false, FailureHandling.CONTINUE_ON_FAILURE)

Mobile.verifyMatch(Actual_Case_Oty, Case_Qty_value.toString() + ' C', false, FailureHandling.CONTINUE_ON_FAILURE)

Mobile.takeScreenshot()

KeywordUtil.logInfo('verified: the value in Order and Invoice Screen')

Mobile.tap(findTestObject('Mobile/Common/Icon_Back'), 2, FailureHandling.OPTIONAL)

Mobile.tap(findTestObject('Mobile/Common/Btn_OK'), 1, FailureHandling.OPTIONAL)

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Call_Analysis'), [:], FailureHandling.STOP_ON_FAILURE)

