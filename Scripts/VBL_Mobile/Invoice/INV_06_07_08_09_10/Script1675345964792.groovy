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

//not_run: Mobile.callTestCase(findTestCase('VBL_Mobile/Re-Usables/Mobile_Login/VanSeller_Login'), [:], FailureHandling.STOP_ON_FAILURE)
//
//Mobile.startApplication(GlobalVariable.APK_File, false)
//
//Mobile.tap(findTestObject('XXXXXXXXXXXX/Re-usable/Mobile Login/Menu'), 0)
//
//Mobile.callTestCase(findTestCase('VBL_Mobile/Re-Usables/Select_Retailer and Going_OrderInvoice_Screen'), [('RetailerName') : findTestData('VBL_Mobile Input Data/Invoice').getValue(
//            'Retailer_Name', 1)], FailureHandling.STOP_ON_FAILURE)
if (Mobile.verifyElementExist(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/FilterScreen/Filter-Icon'), 2, FailureHandling.OPTIONAL)) {
    'Load Management menu visible'
} else {
    Mobile.callTestCase(findTestCase('Test Cases/VBL_Mobile/Re-Usables/Mobile_Login/Relaunch APK'), [:], FailureHandling.CONTINUE_ON_FAILURE)

    Mobile.tap(findTestObject('XXXXXXXXXXXX/Re-usable/Mobile Login/Menu'), 0)

    Mobile.callTestCase(findTestCase('VBL_Mobile/Re-Usables/Select_Retailer and Going_OrderInvoice_Screen'), [(RetailerName) : findTestData('VBL_Mobile Input Data/Invoice').getValue('Retailer_Name', 1)], FailureHandling.STOP_ON_FAILURE)
}

int Piece_sku_index = 1

Mobile.callTestCase(findTestCase('VBL_Mobile/Invoice/Reusable/Order_Generate(only Piece)_CT'), [('ProductName') : findTestData('VBL_Mobile Input Data/Invoice').getValue('Sku_Name', Piece_sku_index), ('Keypad_Number') : findTestData('VBL_Mobile Input Data/Invoice').getValue('Keypad_Number', 3)], FailureHandling.STOP_ON_FAILURE)

PieceQtySku1 = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/Piece_Field'), 0)

CaseQtySku1 = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/Case_Field'), 0)

Actual_SkuTotal_1 = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/OrderInvoiceScrn-SkuTotal'), 0)

PiecePrice = findTestData('VBL_Mobile Input Data/Invoice').getValue('PiecePrice', Piece_sku_index)

CaseSize = findTestData('VBL_Mobile Input Data/Invoice').getValue('CaseSize', Piece_sku_index)

Calculated_SkuTotal_1 = ((Double.parseDouble(PieceQtySku1) * Double.parseDouble(PiecePrice)) + ((Double.parseDouble(CaseQtySku1) * Double.parseDouble(CaseSize)) * Double.parseDouble(PiecePrice)))

println(Calculated_SkuTotal_1)

Mobile.verifyEqual(Double.parseDouble(Actual_SkuTotal_1), Calculated_SkuTotal_1, FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot('INV_06 :To verify Total Value field if we enter qty in pieces field', FailureHandling.STOP_ON_FAILURE)

///piece
int Case_sku_index = 2

Mobile.callTestCase(findTestCase('VBL_Mobile/Invoice/Reusable/Order_Generate(only Case)_CT'), [('ProductName') : findTestData('VBL_Mobile Input Data/Invoice').getValue('Sku_Name', Case_sku_index), ('KeypadNumber') : findTestData('VBL_Mobile Input Data/Invoice').getValue('Keypad_Number', 1)], FailureHandling.STOP_ON_FAILURE)

PieceQtySku2 = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/Piece_Field'), 0)

CaseQtySku2 = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/Case_Field'), 0)

Actual_SkuTotal_2 = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/OrderInvoiceScrn-SkuTotal'), 0)

PiecePrice = findTestData('VBL_Mobile Input Data/Invoice').getValue('PiecePrice', Case_sku_index)

CaseSize = findTestData('VBL_Mobile Input Data/Invoice').getValue('CaseSize', Case_sku_index)

Calculated_SkuTotal_2 = ((Double.parseDouble(PieceQtySku2) * Double.parseDouble(PiecePrice)) + ((Double.parseDouble(CaseQtySku2) * Double.parseDouble(CaseSize)) * Double.parseDouble(PiecePrice)))

println(Calculated_SkuTotal_2)

Mobile.verifyEqual(Double.parseDouble(Actual_SkuTotal_2), Calculated_SkuTotal_2, FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

println('Verified: Total Value field if we enter qty in Case field')

//case
int CasePiece_sku_index = 3

Mobile.callTestCase(findTestCase('VBL_Mobile/Invoice/Reusable/Order_Generate(Case Piece)_CT'), [('ProductName') : findTestData('VBL_Mobile Input Data/Invoice').getValue('Sku_Name', CasePiece_sku_index), ('KeypadNumber') : findTestData('VBL_Mobile Input Data/Invoice').getValue('Keypad_Number', 1)], FailureHandling.STOP_ON_FAILURE)

PieceQtySku3 = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/Piece_Field'), 0)

CaseQtySku3 = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/Case_Field'), 0)

Actual_SkuTotal_3 = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/OrderInvoiceScrn-SkuTotal'), 0)

PiecePrice = findTestData('VBL_Mobile Input Data/Invoice').getValue('PiecePrice', CasePiece_sku_index)

CaseSize = findTestData('VBL_Mobile Input Data/Invoice').getValue('CaseSize', CasePiece_sku_index)

Calculated_SkuTotal_3 = ((Double.parseDouble(PieceQtySku3) * Double.parseDouble(PiecePrice)) + ((Double.parseDouble(CaseQtySku3) * Double.parseDouble(CaseSize)) * Double.parseDouble(PiecePrice)))

println(Calculated_SkuTotal_3)

Mobile.verifyEqual(Double.parseDouble(Actual_SkuTotal_3), Calculated_SkuTotal_3, FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

println('Verified: Total Value field if we enter qty in piece and case field')

Calculated_Piece_Qty_value = ((Integer.parseInt(PieceQtySku1) + Integer.parseInt(PieceQtySku2)) + Integer.parseInt(PieceQtySku3))

println(Calculated_Piece_Qty_value)

Calculated_Case_Qty_value = ((Integer.parseInt(CaseQtySku1) + Integer.parseInt(CaseQtySku2)) + Integer.parseInt(CaseQtySku3))

println(Calculated_Case_Qty_value)

Mobile.hideKeyboard(FailureHandling.OPTIONAL)

Actual_Total_Value = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/OrderInvoiceScreen_bottomValueField'), 0)

println(Actual_Total_Value)

Calculated_Total_Value = ((Calculated_SkuTotal_1 + Calculated_SkuTotal_2) + Calculated_SkuTotal_3)

println(Calculated_Total_Value)

Mobile.verifyEqual(Double.parseDouble(Actual_Total_Value), Calculated_Total_Value, FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

println('Verified : the total piece and case quantity in Order and Invoice Screen')

Total_PieceCase_Oty = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/OrderInvoiceScreen-TotalQty(CasePiece)'), 0)

println(Total_PieceCase_Oty)

String[] arr = Total_PieceCase_Oty.split('/')

Actual_Case_Oty = (arr[0]).trim()

Actual_Piece_Oty = (arr[1]).trim()

Mobile.verifyMatch(Actual_Piece_Oty, Calculated_Piece_Qty_value.toString() + ' P', false)

Mobile.verifyMatch(Actual_Case_Oty, Calculated_Case_Qty_value.toString() + ' C', false)

Mobile.takeScreenshot()

println('verified: the value in Order and Invoice Screen')

if (Mobile.verifyElementExist(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/FilterScreen/Filter-Icon'),
	2, FailureHandling.OPTIONAL)) {
	'Load Management menu visible'
} else {
	Mobile.callTestCase(findTestCase('Test Cases/VBL_Mobile/Re-Usables/Mobile_Login/Relaunch APK'), [:], FailureHandling.CONTINUE_ON_FAILURE)

	Mobile.tap(findTestObject('XXXXXXXXXXXX/Re-usable/Mobile Login/Menu'), 0)

	Mobile.callTestCase(findTestCase('VBL_Mobile/Re-Usables/Select_Retailer and Going_OrderInvoice_Screen'), [(RetailerName) : findTestData(
				'VBL_Mobile Input Data/Invoice').getValue('Retailer_Name', 1)], FailureHandling.STOP_ON_FAILURE)
}

Mobile.tap(findTestObject('Mobile/Common/Icon_Back'), 0)

Mobile.tap(findTestObject('Mobile/Common/Btn_OK'), 1, FailureHandling.OPTIONAL)

Mobile.delay(3)

Mobile.swipe(0, 150, 0, 400, FailureHandling.OPTIONAL)

Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/CallAnalysis/StoreActy_Close Call Btn'), 0)

Mobile.delay(2)

if (Mobile.verifyElementExist(findTestObject('Object Repository/XXXXXXXXXXXX/Mobile Part/Store_Actvy/CallAnalysis/Select Reason for no order'),
	2, FailureHandling.OPTIONAL)) {
	Mobile.tap(findTestObject('Object Repository/XXXXXXXXXXXX/Mobile Part/Store_Actvy/CallAnalysis/Select Reason for no order'),
		0)

	Mobile.tap(findTestObject('Object Repository/XXXXXXXXXXXX/Mobile Part/Store_Actvy/CallAnalysis/Non Productive'), 0)
}

Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/CallAnalysis/Close Call'), 0)

Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/CallAnalysis/CloseCall_Alert_OK btn'), 0)


