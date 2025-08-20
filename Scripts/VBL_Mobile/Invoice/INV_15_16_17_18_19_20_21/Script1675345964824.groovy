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

not_run: Mobile.callTestCase(findTestCase('VBL_Mobile/Re-Usables/Mobile_Login/VanSeller_Login'), [:], FailureHandling.STOP_ON_FAILURE)

//Mobile.startApplication(GlobalVariable.APK_File, false)
if (Mobile.verifyElementExist(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/FilterScreen/Filter-Icon'), 2, FailureHandling.OPTIONAL)) {
    Mobile.callTestCase(findTestCase('Product_Mobile/Common/Search_Product'), [:], FailureHandling.STOP_ON_FAILURE)

    Mobile.callTestCase(findTestCase('Product_Mobile/Common/Clear_Star_Filter_Default_Selection'), [:], FailureHandling.STOP_ON_FAILURE //Mobile.tap(findTestObject('XXXXXXXXXXXX/Re-usable/Mobile Login/Menu'), 0)
        )
} else {
    Mobile.callTestCase(findTestCase('Test Cases/VBL_Mobile/Re-Usables/Mobile_Login/Relaunch APK'), [:], FailureHandling.CONTINUE_ON_FAILURE)

    Mobile.callTestCase(findTestCase('VBL_Mobile/Re-Usables/Select_Retailer and Going_OrderInvoice_Screen'), [(RetailerName) : findTestData('VBL_Mobile Input Data/Invoice').getValue('Retailer_Name', 1)], FailureHandling.STOP_ON_FAILURE)
}

GlobalVariable.ProductName = findTestData('VBL_Mobile Input Data/Invoice').getValue('Product_Name', 1)

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Search_Product'), [:], FailureHandling.STOP_ON_FAILURE)

Mobile.setText(findTestObject('XXXXXXXXXXXX/Mobile Part/Reusable Object/Product Enter Search'), GlobalVariable.ProductName, 5)

Mobile.delay(3, FailureHandling.STOP_ON_FAILURE)

Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/Piece_Field'), 5)

GlobalVariable.keypadValue = findTestData('VBL_Mobile Input Data/Invoice').getValue('Keypad_Number', 2)

Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Reusable Object/Box-Keypad/Global-number_keypad'), 5)

Mobile.tap(findTestObject('Mobile/Common/Btn_OK'), 5)

GivenCaseQty = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/Case_Field'), 0)

GivenPieceQty = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/Piece_Field'), 0)

Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/Next Btn-OrderInvoiceScreen'), 0)

Mobile.callTestCase(findTestCase('VBL_Mobile/Re-Usables/MustSell_Alert'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('VBL_Mobile/Re-Usables/Tap_Next_Btn_ApplyschemeScrn_CT'), [:], FailureHandling.STOP_ON_FAILURE)

Actual_Sku = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/SummaryScreen/SummaryScreen-ProductTitle'), 0)

Actual_case = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/SummaryScreen/Summary-Screen-CaseValue'), 0)

Actual_Piece = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/SummaryScreen/Summary-Screen-PieceValue'), 0)

Actual_totalAmt = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/SummaryScreen/Summary-Screen-TotalAmt'), 0, FailureHandling.OPTIONAL)

Actual_ValueAmt = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/SummaryScreen/SummaryScreen-ValueAmt'), 0, FailureHandling.OPTIONAL)

PiecePrice = findTestData('VBL_Mobile Input Data/Invoice').getValue('PiecePrice', 1)

CaseSize = findTestData('VBL_Mobile Input Data/Invoice').getValue('CaseSize', 1)

CGST_Value = findTestData('VBL_Mobile Input Data/Invoice').getValue('CGST_Tax', 1)

calculated_CGST_TAX = ((((Double.parseDouble(PiecePrice) * Double.parseDouble(GivenPieceQty)) + ((Double.parseDouble(GivenCaseQty) * Double.parseDouble(CaseSize)) * Double.parseDouble(PiecePrice))) * Double.parseDouble(CGST_Value)) / 100)

KeywordUtil.logInfo(calculated_CGST_TAX.toString())

Calculated_Order_Value = ((Double.parseDouble(PiecePrice) * Double.parseDouble(GivenPieceQty)) + ((Double.parseDouble(GivenCaseQty) * Double.parseDouble(CaseSize)) * Double.parseDouble(PiecePrice)))

println(Calculated_Order_Value.toString())

Calculated_FinalTotal = (calculated_CGST_TAX + Calculated_Order_Value)

println(Calculated_FinalTotal.toString())

Mobile.verifyMatch(Actual_Sku, GlobalVariable.ProductName, false, FailureHandling.STOP_ON_FAILURE)

Mobile.verifyEqual(Actual_case, GivenCaseQty, FailureHandling.STOP_ON_FAILURE)

Mobile.verifyEqual(Actual_Piece, GivenPieceQty, FailureHandling.STOP_ON_FAILURE)

println('INV_15 To verify the Summary Screen Field case & Piece Values')

Mobile.verifyEqual(Double.parseDouble(Actual_totalAmt), Calculated_FinalTotal, FailureHandling.STOP_ON_FAILURE)

println('INV_16 To verify the Summary Screen Field Total Values')

Mobile.takeScreenshot()

println('Verified : Summary Screen Verification')

Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/SummaryScreen/SummaryScreen-InfoIcon'), 0)

Actual_SplitScreen_CGST_Value = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/SummaryScreen/SplitScreen-CGST Value'), 0)

Actual_SplitScreen_OrderAmt = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/SummaryScreen/SplitScreen-OrderAmt'), 0, FailureHandling.STOP_ON_FAILURE)

Actual_SplitScreen_Total_Amt = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/SummaryScreen/SliptScreen_TotalAmt'), 0)

Mobile.verifyEqual(Double.parseDouble(Actual_SplitScreen_OrderAmt), Calculated_Order_Value, FailureHandling.STOP_ON_FAILURE)

println('INV_17 To verify the Amount Splitup Screen Order Values')

Mobile.verifyEqual(Double.parseDouble(Actual_SplitScreen_CGST_Value), calculated_CGST_TAX, FailureHandling.STOP_ON_FAILURE)

println('INV_18 To verify the Amount Splitup Screen CGST Tax')

Mobile.verifyEqual(Double.parseDouble(Actual_SplitScreen_Total_Amt), Calculated_FinalTotal, FailureHandling.STOP_ON_FAILURE)

println('INV_19 To verify the Amount Splitup Screen Total Amount')

Mobile.takeScreenshot()

println('Verified : Split Screen verification')

Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/SummaryScreen/SummaryScreen-info_closeBtn'), 0)

Mobile.verifyElementExist(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/SummaryScreen/SummaryScreen-EditIcon'), 0)

Mobile.verifyElementVisible(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/SummaryScreen/SummaryScreen-EditIcon'), 0)

Mobile.verifyElementExist(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/SummaryScreen/SummaryScreen-CameraIcon'), 0)

Mobile.verifyElementVisible(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/SummaryScreen/SummaryScreen-CameraIcon'), 0)

Mobile.verifyElementExist(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/SummaryScreen/SummaryScreen-SignIcon'), 0)

Mobile.verifyElementVisible(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/SummaryScreen/SummaryScreen-SignIcon'), 0)

Mobile.verifyElementExist(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/SummaryScreen/SummaryScreen-PreviewIcon'), 0)

Mobile.verifyElementVisible(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/SummaryScreen/SummaryScreen-PreviewIcon'), 0)

Mobile.takeScreenshot()

println('Verified : summaryscreen icons')

beforeEdit_PieceQty = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/SummaryScreen/Summary-Screen-PieceValue'), 0)

Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/SummaryScreen/SummaryScreen-EditIcon'), 0)

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Clear_Star_Filter_Default_Selection'), [:], FailureHandling.STOP_ON_FAILURE)

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Search_Product'), [:], FailureHandling.STOP_ON_FAILURE)

Mobile.setText(findTestObject('XXXXXXXXXXXX/Mobile Part/Reusable Object/Product Enter Search'), GlobalVariable.ProductName, 5)

Mobile.delay(2, FailureHandling.STOP_ON_FAILURE)

Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/Piece_Field'), 5)

Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Reusable Object/Box-Keypad/Back Arrow-Keypad'), 1, FailureHandling.OPTIONAL)

Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Reusable Object/Box-Keypad/Back Arrow-Keypad'), 1, FailureHandling.OPTIONAL)

GlobalVariable.keypadValue = findTestData('VBL_Mobile Input Data/Invoice').getValue('Keypad_Number', 3)

Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Reusable Object/Box-Keypad/Global-number_keypad'), 5)

Mobile.tap(findTestObject('Mobile/Common/Btn_OK'), 5)

AterEdit_PieceQty_orderScreen = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/Piece_Field'), 0)

Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/Next Btn-OrderInvoiceScreen'), 0)

Mobile.callTestCase(findTestCase('VBL_Mobile/Re-Usables/MustSell_Alert'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('VBL_Mobile/Re-Usables/Tap_Next_Btn_ApplyschemeScrn_CT'), [:], FailureHandling.STOP_ON_FAILURE)

afterEdit_PieceQty = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/SummaryScreen/Summary-Screen-PieceValue'), 0)

Mobile.verifyMatch(GlobalVariable.keypadValue, AterEdit_PieceQty_orderScreen, false, FailureHandling.STOP_ON_FAILURE)

Mobile.verifyNotMatch(beforeEdit_PieceQty, afterEdit_PieceQty, false, FailureHandling.STOP_ON_FAILURE)

Mobile.verifyNotMatch(beforeEdit_PieceQty, AterEdit_PieceQty_orderScreen, false, FailureHandling.STOP_ON_FAILURE)

println('Order can be editable, verified successfully')

Mobile.takeScreenshot()

println('Verified : user can able to edit order')

Mobile.tap(findTestObject('Mobile/Common/Icon_Back'), 3, FailureHandling.OPTIONAL)

Mobile.delay(2)

Mobile.tap(findTestObject('Mobile/Common/Icon_Back'), 3, FailureHandling.OPTIONAL)

Mobile.tap(findTestObject('Mobile/Common/Btn_OK'), 1, FailureHandling.OPTIONAL)

Mobile.delay(2, FailureHandling.STOP_ON_FAILURE)

Mobile.swipe(0, 150, 0, 400, FailureHandling.OPTIONAL)

Mobile.delay(2)

Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/Menu-OrderInvoice'), 2, FailureHandling.OPTIONAL)

WebUI.callTestCase(findTestCase('VBL_Mobile/Re-Usables/OrderPopUp(EditOrder)'), [:], FailureHandling.OPTIONAL)

