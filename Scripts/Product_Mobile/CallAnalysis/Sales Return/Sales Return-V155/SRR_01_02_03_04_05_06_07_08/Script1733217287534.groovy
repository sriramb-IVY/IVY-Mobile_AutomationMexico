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
import org.testng.Assert as Assert
import io.appium.java_client.android.nativekey.KeyEvent as KeyEvent
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import com.kms.katalon.keyword.excel.ExcelKeywords as ExcelKeywords

//Mobile.startApplication(GlobalVariable.APKFile, false)
////
//Mobile.tap(findTestObject('Mobile/Common/Btn_Menu'), 0)

if (Mobile.verifyElementExist(findTestObject('Mobile/Store_Actvy/Menu-SalesReturn'), 2, FailureHandling.OPTIONAL)) {
	'SalesReturn menu visible'
	
} else {
	Mobile.callTestCase(findTestCase('Product_Mobile/Common/Mobile_Login/Relaunch the app'), [:], FailureHandling.CONTINUE_ON_FAILURE)

	Mobile.tap(findTestObject('Mobile/Common/Btn_Menu'), 0)

	
GlobalVariable.RetailerName = findTestData('Mobile Input Data/SalesReturn_Replacement').getValue('Retailer', 1)

	Mobile.tap(findTestObject('Mobile/MainMenu/Menu_Trade Coverage'), 0)

	Mobile.tap(findTestObject('Mobile/Common/Icon_Search'), 5)

	Mobile.setText(findTestObject('Mobile/Common/Field_Search_EnterText'), GlobalVariable.RetailerName, 5)

	Mobile.tap(findTestObject('Mobile/TradeCoverage/First_Retailer_Selection_From_List'), 5)

	Mobile.tap(findTestObject('Mobile/Common/Btn_StartVisit'), 5)

	if (Mobile.verifyElementVisible(findTestObject('Object Repository/Mobile/Common/Popup_Title_LocationValidation'), 5, FailureHandling.OPTIONAL)) {
		Mobile.tap(findTestObject('Mobile/Common/Btn_YES'), 5)
	}
	
	Mobile.delay(6)

	Mobile.swipe(0, 150, 0, 400, FailureHandling.OPTIONAL)
}


Mobile.tap(findTestObject('Mobile/Store_Actvy/Menu-SalesReturn'), 0)

Mobile.delay(2)

Mobile.verifyElementVisible(findTestObject('Mobile/SalesReturn/Label-Product Name'), 0)

Mobile.verifyElementVisible(findTestObject('Mobile/SalesReturn/Label-Cases'), 0)

Mobile.tap(findTestObject('Mobile/Common/Icon_Search'), 0)

GlobalVariable.S_Sku_Name = findTestData('Mobile Input Data/SalesReturn_Replacement').getValue('Return_SKU_Name', 1)

Mobile.setText(findTestObject('Mobile/Common/Field_Search_EnterText'), GlobalVariable.S_Sku_Name, 0)

Mobile.takeScreenshot()

Mobile.verifyElementVisible(findTestObject('Mobile/SalesReturn/ProductName-1st'), 3, FailureHandling.STOP_ON_FAILURE)

Mobile.verifyElementText(findTestObject('Mobile/SalesReturn/ProductName-1st'), GlobalVariable.S_Sku_Name, FailureHandling.STOP_ON_FAILURE)

KeywordUtil.logInfo('The list of the products  displayed in the Sales return screen')

Mobile.takeScreenshot()

Mobile.tap(findTestObject('Mobile/SalesReturn/Piece-1st'), 0)

Mobile.delay(2)

Mobile.verifyElementExist(findTestObject('Mobile/SalesReturn/RETURN_Screen_Title'), 0)

Mobile.setText(findTestObject('Mobile/SalesReturn/Return-InvoiceNo'), findTestData('Mobile Input Data/CallAnalysis').getValue('InvoiceNo', 1), 0, FailureHandling.OPTIONAL)

Mobile.takeScreenshot()

MRP = Mobile.getText(findTestObject('Mobile/SalesReturn/Return-MRP'), 0)

Mobile.tap(findTestObject('Mobile/SalesReturn/Return-Select Reason'), 0)

Mobile.takeScreenshot()

Reason = Mobile.getText(findTestObject('Mobile/SalesReturn/Damage-option'), 0)

GlobalVariable.S_ReasonType = Reason

Mobile.tap(findTestObject('Mobile/SalesReturn/Damage-option'), 0)

Mobile.tap(findTestObject('Mobile/SalesReturn/Return-Piece'), 0, FailureHandling.STOP_ON_FAILURE)

GlobalVariable.keypadValue = findTestData('Mobile Input Data/SalesReturn_Replacement').getValue('Qty', 2)

Mobile.takeScreenshot()

GlobalVariable.PieceQty = GlobalVariable.keypadValue

Mobile.tap(findTestObject('Mobile/Common/Global_Number_keypad'), 0)

Mobile.tap(findTestObject('Mobile/SalesReturn/Return-Case'), 0, FailureHandling.STOP_ON_FAILURE)

GlobalVariable.keypadValue = findTestData('Mobile Input Data/SalesReturn_Replacement').getValue('Qty', 1)

GlobalVariable.CaseQty = GlobalVariable.keypadValue

Mobile.tap(findTestObject('Mobile/Common/Global_Number_keypad'), 0)

Mobile.takeScreenshot()

Conversion = findTestData('Mobile Input Data/SalesReturn_Replacement').getValue('Conversion_qty', 1)

Totalreturn_qty = ((Double.parseDouble(GlobalVariable.CaseQty) * Double.parseDouble(Conversion)) + Double.parseDouble(GlobalVariable.PieceQty))

KeywordUtil.logInfo(Totalreturn_qty + ': Total return quantity')

Mobile.tap(findTestObject('Mobile/Common/Btn_Done'), 0)

Mobile.delay(2)

Total_Return_amount_Returnscrn = Mobile.getText(findTestObject('Mobile/Seller_2/SalesReturn_Replacement/Return_product_amount'), 0)

KeywordUtil.logInfo(Total_Return_amount_Returnscrn + ': Total return amount')

Mobile.tap(findTestObject('Mobile/SalesReturn/Piece-1st'), 0)

Mobile.delay(2)

Mobile.comment('Validation for Replace screen fields and functionality SRR01')

Mobile.verifyElementExist(findTestObject('Object Repository/Mobile/Seller_2/SalesReturn_Replacement/Replace_title_tab'), 0)

Mobile.takeScreenshot()

Mobile.tap(findTestObject('Object Repository/Mobile/Seller_2/SalesReturn_Replacement/Replace_title_tab'), 0)

Mobile.verifyElementExist(findTestObject('Mobile/Common/Field_Search_EnterText'), 0)

Mobile.takeScreenshot()

X_val = Mobile.getAttribute(findTestObject('Mobile/Common/Field_Search_EnterText'), 'x', 0)

Y_val = Mobile.getAttribute(findTestObject('Mobile/Common/Field_Search_EnterText'), 'y', 0)

x = (Integer.parseInt(X_val) + 50)

y = (Integer.parseInt(Y_val) + 145)

Mobile.tap(findTestObject('Mobile/Common/Field_Search_EnterText'), 0)

Replacement_sku1 = findTestData('Mobile Input Data/SalesReturn_Replacement').getValue('Replacement_SKU_Name', 1)

Mobile.setText(findTestObject('Mobile/Common/Field_Search_EnterText'), Replacement_sku1, 0)

Mobile.verifyElementExist(findTestObject('Mobile/Seller_2/SalesReturn_Replacement/Search_clear_btn'), 0)

Mobile.tap(findTestObject('Mobile/Seller_2/SalesReturn_Replacement/Search_clear_btn'), 0)

KeywordUtil.logInfo('search clear button working properly')

Mobile.takeScreenshot()

After_clear_the_text = Mobile.getText(findTestObject('Mobile/Common/Field_Search_EnterText'), 0)

Mobile.verifyNotMatch(Replacement_sku1, After_clear_the_text, false, FailureHandling.STOP_ON_FAILURE)

Mobile.tap(findTestObject('Mobile/Common/Field_Search_EnterText'), 0)

Mobile.setText(findTestObject('Mobile/Common/Field_Search_EnterText'), Replacement_sku1, 0)

Mobile.tapAtPosition(x, y)


Mobile.tapAtPosition(x, y)

Mobile.takeScreenshot()

Replace_product_name = Mobile.getText(findTestObject('Object Repository/Mobile/Seller_2/SalesReturn_Replacement/Replacement_Product_Name_get'), 0)

if (Replace_product_name.contains(Replacement_sku1)) {
    KeywordUtil.logInfo('searched and selected product loading in replacement screen ')

    Mobile.takeScreenshot()
} else {
    KeywordUtil.logInfo('search product selected wrongly')

    Assert.fail()
}

Mobile.comment('Validation for product name,price,value,case,piece present for sku in replacement screen')

GlobalVariable.label = Replacement_sku1

Mobile.verifyElementExist(findTestObject('Mobile/Seller_2/SalesReturn_Replacement/Replacement_Price_Get'), 0)

Mobile.verifyElementExist(findTestObject('Mobile/Seller_2/SalesReturn_Replacement/Replacement_Value_Get'), 0)

Mobile.verifyElementExist(findTestObject('Mobile/Seller_2/SalesReturn_Replacement/Replacement_Case_field'), 0)

Mobile.verifyElementExist(findTestObject('Mobile/Seller_2/SalesReturn_Replacement/Replacement_Piece_field'), 0)

Mobile.verifyElementExist(findTestObject('Mobile/Seller_2/SalesReturn_Replacement/Replacement_product_remove_btn'), 0)

Mobile.takeScreenshot()

Mobile.comment('Validation for able to enter qty in case and piece field')

Mobile.tap(findTestObject('Mobile/Seller_2/SalesReturn_Replacement/Replacement_Case_field'), 0)

Mobile.setText(findTestObject('Mobile/Seller_2/SalesReturn_Replacement/Replacement_Case_field'), findTestData('Mobile Input Data/SalesReturn_Replacement').getValue('Qty', 1), 0)

Case_qty = Mobile.getText(findTestObject('Mobile/Seller_2/SalesReturn_Replacement/Replacement_Case_field'), 0)

Mobile.verifyMatch(Case_qty, findTestData('Mobile Input Data/SalesReturn_Replacement').getValue('Qty', 1), false)

Mobile.tap(findTestObject('Mobile/Seller_2/SalesReturn_Replacement/Replacement_Piece_field'), 0)

Mobile.setText(findTestObject('Mobile/Seller_2/SalesReturn_Replacement/Replacement_Piece_field'), findTestData('Mobile Input Data/SalesReturn_Replacement').getValue('Qty', 2), 0)

Piece_qty = Mobile.getText(findTestObject('Mobile/Seller_2/SalesReturn_Replacement/Replacement_Piece_field'), 0)

Mobile.verifyMatch(Piece_qty, findTestData('Mobile Input Data/SalesReturn_Replacement').getValue('Qty', 2), false)

Mobile.takeScreenshot()

Mobile.comment('Validation for value calculation')

Conversion = findTestData('Mobile Input Data/SalesReturn_Replacement').getValue('Conversion_qty', 1)

Totalreturn_qty = ((Double.parseDouble(Case_qty) * Double.parseDouble(Conversion)) + Double.parseDouble(Piece_qty))

KeywordUtil.logInfo(Totalreturn_qty + ': replacement qty ')

Price = Mobile.getText(findTestObject('Object Repository/Mobile/Seller_2/SalesReturn_Replacement/Replacement_Price_Get'), 0)

Sku_price = (Price.split(': ')[1])

cal_value = (Totalreturn_qty * Double.parseDouble(Sku_price))

KeywordUtil.logInfo(cal_value + ': calculated value ')

Replace_value = Mobile.getText(findTestObject('Object Repository/Mobile/Seller_2/SalesReturn_Replacement/Replacement_Value_Get'), 0)

Replacement_value = (Replace_value.split(': ')[1])

Mobile.verifyEqual(cal_value, Double.parseDouble(Replacement_value), FailureHandling.STOP_ON_FAILURE)

Mobile.comment('Validation for clear the product')

Mobile.tap(findTestObject('Mobile/Seller_2/SalesReturn_Replacement/Replacement_product_remove_btn'), 0)

Mobile.verifyElementNotExist(findTestObject('Mobile/Seller_2/SalesReturn_Replacement/Replacement_Product_Name_get'), 0)

Mobile.takeScreenshot()

Mobile.comment('Validation for two replacement products')

ret_amt = (Double.parseDouble(Total_Return_amount_Returnscrn) / 2)

KeywordUtil.logInfo(ret_amt + ' : value ')

One_rep_amt = (ret_amt - 100)

KeywordUtil.logInfo('rep amt :'+One_rep_amt)

'Fisrt replacement product'
GlobalVariable.label = findTestData('Mobile Input Data/SalesReturn_Replacement').getValue('Replacement_SKU_Name', 1)

Mobile.tap(findTestObject('Mobile/Common/Field_Search_EnterText'), 0)

Mobile.setText(findTestObject('Mobile/Common/Field_Search_EnterText'), GlobalVariable.label, 0)

Mobile.delay(2)

Mobile.tapAtPosition(x, y)

Mobile.tapAtPosition(x, y)

Mobile.delay(2)

SKU1_Pri = Mobile.getText(findTestObject('Object Repository/Mobile/Seller_2/SalesReturn_Replacement/Replacement_Price_Get'), 0)

Sku1_price = (SKU1_Pri.split(': ')[1])

SKU1_rep_amt = (One_rep_amt / Double.parseDouble(Sku1_price))

SKU1_Replace_piece = SKU1_rep_amt.round()

String SKU1_Replace_piece_qty = SKU1_Replace_piece

Mobile.setText(findTestObject('Mobile/Seller_2/SalesReturn_Replacement/Replacement_Piece_field'), SKU1_Replace_piece_qty, 0)

SKU1_Replace_value = Mobile.getText(findTestObject('Object Repository/Mobile/Seller_2/SalesReturn_Replacement/Replacement_Value_Get'), 0)

SKU1Replacement_value = (SKU1_Replace_value.split(': ')[1])

Mobile.delay(2)

'Second replacement product'
GlobalVariable.label = findTestData('Mobile Input Data/SalesReturn_Replacement').getValue('Replacement_SKU_Name', 2)

Mobile.tap(findTestObject('Mobile/Common/Field_Search_EnterText'), 0)

Mobile.setText(findTestObject('Mobile/Common/Field_Search_EnterText'), GlobalVariable.label, 0)

Mobile.delay(2)

Mobile.tapAtPosition(x, y)

Mobile.tapAtPosition(x, y)

Mobile.delay(2)

if (Mobile.verifyElementNotExist(findTestObject('Object Repository/Mobile/Seller_2/SalesReturn_Replacement/Replacement_Price_Get'), 5, FailureHandling.OPTIONAL)) {
    GlobalVariable.label = findTestData('Mobile Input Data/SalesReturn_Replacement').getValue('Replacement_SKU_Name', 2)

    Mobile.tap(findTestObject('Object Repository/Mobile/Seller_2/SalesReturn_Replacement/Search_clear_btn'), 0, FailureHandling.OPTIONAL)

    Mobile.tap(findTestObject('Mobile/Common/Field_Search_EnterText'), 0)

    Mobile.setText(findTestObject('Mobile/Common/Field_Search_EnterText'), GlobalVariable.label, 0)

    Mobile.delay(2)

    Mobile.tapAtPosition(x, y)

    Mobile.tapAtPosition(x, y)
}

SKU2_Pri = Mobile.getText(findTestObject('Object Repository/Mobile/Seller_2/SalesReturn_Replacement/Replacement_Price_Get'), 0)

Sku2_price = (SKU2_Pri.split(': ')[1])

SKU2_rep_amt = (One_rep_amt / Double.parseDouble(Sku2_price))

SKU2_Replace_piece = SKU2_rep_amt.round()

String SKU2_Replace_piece_qty = SKU2_Replace_piece

Mobile.setText(findTestObject('Mobile/Seller_2/SalesReturn_Replacement/Replacement_Piece_field'), SKU2_Replace_piece_qty, 0)

SKU2_Replace_value = Mobile.getText(findTestObject('Object Repository/Mobile/Seller_2/SalesReturn_Replacement/Replacement_Value_Get'), 0)

SKU2Replacement_value = (SKU2_Replace_value.split(': ')[1])

Overall_replacement_value = (Double.parseDouble(SKU1Replacement_value) + Double.parseDouble(SKU2Replacement_value))

KeywordUtil.logInfo(Overall_replacement_value + ' :Total replacement value ')

Total_replacement_qty = (Double.parseDouble(SKU1_Replace_piece_qty) + Double.parseDouble(SKU2_Replace_piece_qty))

KeywordUtil.logInfo(Total_replacement_qty + ' : Total replaced quantity ')

Total_rep_quantity = Mobile.getText(findTestObject('Object Repository/Mobile/Seller_2/SalesReturn_Replacement/Replacement_qty_get'), 0)

Total_rep_valuee = Mobile.getText(findTestObject('Object Repository/Mobile/Seller_2/SalesReturn_Replacement/Replacement_bottom_value_get'), 0)

Mobile.verifyEqual(Total_replacement_qty, Double.parseDouble(Total_rep_quantity), FailureHandling.STOP_ON_FAILURE)

Mobile.verifyEqual(Overall_replacement_value, Double.parseDouble(Total_rep_valuee), FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

exlpath = 'DDF\\Mobile Input data\\Mobile Input data.xlsx'

Sheetname = 'SalesReturn_Replacement'

workbook01 = ExcelKeywords.getWorkbook(exlpath)

sheet1 = ExcelKeywords.getExcelSheet(workbook01, Sheetname)

ExcelKeywords.setValueToCellByIndex(sheet1, 1, 9, SKU1_Replace_piece_qty)

ExcelKeywords.setValueToCellByIndex(sheet1, 1, 10, Sku1_price)

ExcelKeywords.setValueToCellByIndex(sheet1, 1, 11, SKU1Replacement_value)

ExcelKeywords.setValueToCellByIndex(sheet1, 2, 9, SKU2_Replace_piece_qty)

ExcelKeywords.setValueToCellByIndex(sheet1, 2, 10, Sku2_price)

ExcelKeywords.setValueToCellByIndex(sheet1, 2, 11, SKU2Replacement_value)

ExcelKeywords.saveWorkbook(exlpath, workbook01)

Mobile.tap(findTestObject('Mobile/Common/Btn_Done'), 0)

Mobile.delay(2)

Mobile.tap(findTestObject('Mobile/SalesReturn/SR-Next btn'), 0)

Mobile.delay(2)

Mobile.verifyElementExist(findTestObject('Mobile/SalesReturn/Sales Return Summary-Title'), 5)

Mobile.delay(2)

Mobile.comment('Validation for replacement values in summary screen')

Mobile.verifyElementExist(findTestObject('Object Repository/Mobile/Seller_2/SalesReturn_Replacement/Summary_Screen/Replacement_title_summary_screen'), 0)

Mobile.verifyElementExist(findTestObject('Mobile/Seller_2/SalesReturn_Replacement/Summary_Screen/Replacement_piece_get_sum_scrn'), 0)

Mobile.verifyElementExist(findTestObject('Mobile/Seller_2/SalesReturn_Replacement/Summary_Screen/Replacement_case_get_sum_scrn'), 0)

Mobile.verifyElementExist(findTestObject('Mobile/Seller_2/SalesReturn_Replacement/Summary_Screen/Replacement_Product_Price_sum_scrn'), 0)

Mobile.verifyElementExist(findTestObject('Mobile/Seller_2/SalesReturn_Replacement/Summary_Screen/Replacement_value_sum_scrn'), 0)

Mobile.takeScreenshot()

Summary_Total_replace_value = Mobile.getText(findTestObject('Mobile/Seller_2/SalesReturn_Replacement/Summary_Screen/Total_Replacement_value_get_sum_scrn'), 0)

Summary_Total_return_value = Mobile.getText(findTestObject('Mobile/SalesReturn/SR-TotalValue - 2'), 0)

Mobile.comment('Validation for Summary screen total return value is greater than total replacement value ')

Mobile.takeScreenshot()

Mobile.verifyEqual(Overall_replacement_value, Double.parseDouble(Summary_Total_replace_value), FailureHandling.STOP_ON_FAILURE)

if (Summary_Total_return_value > Summary_Total_replace_value) {
    KeywordUtil.logInfo('Return Amount greater than replacement amount so credit should be generated')
} else {
    KeywordUtil.logInfo('Return Amount leaser than replacement amount')

    Assert.fail()
}

Credit_NoteAmount = (Double.parseDouble(Summary_Total_return_value) - Double.parseDouble(Summary_Total_replace_value))

KeywordUtil.logInfo(Credit_NoteAmount + ' : Credit note amount')


exlpath = 'DDF\\Mobile Input data\\Mobile Input data.xlsx'

Sheetname = 'SalesReturn_Replacement'

workbook01 = ExcelKeywords.getWorkbook(exlpath)

sheet1 = ExcelKeywords.getExcelSheet(workbook01, Sheetname)

ExcelKeywords.setValueToCellByIndex(sheet1, 1, 5, Total_Return_amount_Returnscrn)

ExcelKeywords.setValueToCellByIndex(sheet1, 1, 6, Overall_replacement_value)

ExcelKeywords.setValueToCellByIndex(sheet1, 1, 7, Credit_NoteAmount)

ExcelKeywords.saveWorkbook(exlpath, workbook01)

Mobile.tap(findTestObject('Mobile/SalesReturn/SR-Save btn'), 0)

Mobile.comment('Credit note generation alert')

Mobile.verifyElementExist(findTestObject('Mobile/Seller_2/SalesReturn_Replacement/Summary_Screen/Confirm_Credit_Note_Generation_Alert'), 3, FailureHandling.STOP_ON_FAILURE)

Mobile.verifyElementExist(findTestObject('Mobile/Seller_2/SalesReturn_Replacement/Summary_Screen/Replacement_credit_note_generation_msg'), 3, FailureHandling.STOP_ON_FAILURE)

Mobile.comment('Validation for credit note generation alert text')

Credit_Note_Alert = Mobile.getText(findTestObject('Mobile/Seller_2/SalesReturn_Replacement/Summary_Screen/Alert_Get'), 0)

Mobile.verifyMatch(Credit_Note_Alert, findTestData('Mobile Input Data/SalesReturn_Replacement').getValue('Alerts', 2), false, FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

Mobile.tap(findTestObject('Mobile/Common/Btn_OK'), 3, FailureHandling.OPTIONAL)

Mobile.verifyElementExist(findTestObject('Mobile/SalesReturn/SR-Saved Successfully'), 3, FailureHandling.OPTIONAL)

Mobile.takeScreenshot()

KeywordUtil.logInfo('Sales return created successfully')

Mobile.tap(findTestObject('Mobile/Common/Btn_OK'), 3, FailureHandling.OPTIONAL)

Mobile.delay(3)

Mobile.comment('Validation for generated credit note loading in collection credit note screen')

Mobile.swipe(0, 150, 0, 400, FailureHandling.OPTIONAL)

Mobile.tap(findTestObject('Mobile/Store_Actvy/Menu-Collection'), 0)

Mobile.verifyElementVisible(findTestObject('Mobile/Seller_2/Collection_CreditNote/Invoice_inx1'), 0)

Mobile.tap(findTestObject('Mobile/Seller_2/Collection_CreditNote/Invoice_inx1'), 0)

Mobile.tap(findTestObject('Mobile/Collection/Pay_Btn'), 0)

Mobile.tap(findTestObject('Mobile/Collection/CreditNote/Credit Note_Submenu'), 0)

Mobile.verifyElementVisible(findTestObject('Mobile/Collection/CreditNote/Credit Note Screen_Title'), 0)

Mobile.takeScreenshot()

GlobalVariable.label = Credit_NoteAmount

Mobile.verifyElementVisible(findTestObject('Mobile/Seller_2/Collection_CreditNote/Credit_Note_Amt_get_collection_scren'), 0)

KeywordUtil.logInfo('Genertayed credit note loading in Collection credit note screen')

Mobile.takeScreenshot()

CreditNote_id = Mobile.getText(findTestObject('Mobile/Seller_2/Collection_CreditNote/Credit_Note_Id_get_collection_scrn'), 0)

SalesReturnId = Mobile.getText(findTestObject('Mobile/Seller_2/Collection_CreditNote/SalesReturn_Id_get_collection_scrn'), 0)

Mobile.tap(findTestObject('Mobile/Common/Icon_Back'), 2, FailureHandling.STOP_ON_FAILURE)

Mobile.tap(findTestObject('Mobile/Common/Icon_Back'), 2, FailureHandling.STOP_ON_FAILURE)

Mobile.tap(findTestObject('Mobile/Common/Icon_Back'), 2, FailureHandling.STOP_ON_FAILURE)

Mobile.delay(2)

Mobile.swipe(0, 150, 0, 400, FailureHandling.OPTIONAL)

Mobile.takeScreenshot()

