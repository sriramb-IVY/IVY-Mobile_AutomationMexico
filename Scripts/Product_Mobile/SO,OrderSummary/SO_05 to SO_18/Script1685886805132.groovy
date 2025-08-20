import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.chrome.ChromeDriver as ChromeDriver
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.keyword.excel.ExcelKeywords as ExcelKeywords
import internal.GlobalVariable as GlobalVariable
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import org.openqa.selenium.Keys as Keys

//Mobile.startApplication(GlobalVariable.APK_File, false)


WebUI.callTestCase(findTestCase('Product_Mobile/Common/Seller_Menu_Selection'), [('MenuName') : findTestData('Mobile Input Data/Common').getValue(
            'Menu', 18), ('SubMenuName') : findTestData('Mobile Input Data/Common').getValue('Menu', 20)], FailureHandling.STOP_ON_FAILURE)

Product_Index = 3

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Search_Product'), [('ProductName') : findTestData('Mobile Input Data/Invoice').getValue(
            'Sku_Name', Product_Index)], FailureHandling.STOP_ON_FAILURE)

KeywordUtil.logInfo(GlobalVariable.ProductName + ' : This product mapped with tax and item discount')

Mobile.takeScreenshot()

Exepected_SIH_value = Mobile.getText(findTestObject('Mobile/LoadManagement/CurrentStockView/SIH_Value'), 0)

Mobile.tap(findTestObject('Mobile/Common/Icon_Back'), 0)

println('vanload_SIH_value  : ' + Exepected_SIH_value)

exlpath = 'DDF/Mobile Input data/Mobile Input data.xlsx'

Sheetname = 'Invoice'

workbook01 = ExcelKeywords.getWorkbook(exlpath)

sheet1 = ExcelKeywords.getExcelSheet(workbook01, Sheetname)

ExcelKeywords.setValueToCellByIndex(sheet1, 1, 5, Exepected_SIH_value)

ExcelKeywords.saveWorkbook(exlpath, workbook01)

WebUI.callTestCase(findTestCase('Product_Mobile/Common/Seller_Menu_Selection'), [('MenuName') : findTestData('Mobile Input Data/Common').getValue(
            'Menu', 3), ('SubMenuName') : findTestData('Mobile Input Data/Common').getValue('Menu', 1)], FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('Product_Mobile/Common/TradeCoverage(SelectRetailer)'), [('RetailerName') : findTestData(
            'Mobile Input Data/Invoice').getValue('Retailer_Name', 1)], FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('Product_Mobile/Common/StartVisit'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('Product_Mobile/Common/Store_Menu_Selection'), [('MenuName') : findTestData('Mobile Input Data/Common').getValue(
            'Menu', 4), ('SubMenuName') : findTestData('Mobile Input Data/Common').getValue('Menu', 1)], FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('Product_Mobile/Common/Clear_Star_Filter_Default_Selection'), [:], FailureHandling.STOP_ON_FAILURE)

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Search_Product'), [('ProductName') : GlobalVariable.ProductName], 
    FailureHandling.STOP_ON_FAILURE)

Mobile.setText(findTestObject('Mobile/Common/Field_Search_EnterText'), GlobalVariable.ProductName, 5)

Mobile.tap(findTestObject('Mobile/OrderInvoice/Enter_Qty'), 0)

Mobile.clearText(findTestObject('Mobile/OrderInvoice/Enter_Qty'), 2, FailureHandling.OPTIONAL)

Mobile.sendKeys(findTestObject('Mobile/OrderInvoice/Enter_Qty'), Keys.chord(Keys.BACK_SPACE), FailureHandling.OPTIONAL)

Mobile.sendKeys(findTestObject('Mobile/OrderInvoice/Enter_Qty'), '-2', FailureHandling.OPTIONAL)

Mobile.tap(findTestObject('Mobile/Common/Btn_Next'), 0, FailureHandling.STOP_ON_FAILURE)

Mobile.verifyElementExist(findTestObject('Mobile/OrderInvoice/No Items Added'), 5, FailureHandling.STOP_ON_FAILURE)

Mobile.verifyElementVisible(findTestObject('Mobile/OrderInvoice/No Items Added'), 5, FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

KeywordUtil.logInfo('Negative quantity should not be allowed while taking orders in the stock and order screen')

Mobile.tap(findTestObject('Object Repository/Mobile/Common/Btn_OK'), 0)

Mobile.clearText(findTestObject('Mobile/OrderInvoice/Enter_Qty'), 0, FailureHandling.OPTIONAL)

Mobile.delay(3, FailureHandling.STOP_ON_FAILURE)

Mobile.tap(findTestObject('Mobile/OrderInvoice/ProductName-(Global)'), 5, FailureHandling.STOP_ON_FAILURE)

Mobile.verifyElementExist(findTestObject('Mobile/OrderInvoice/Tab-Product Details'), 5)

Mobile.verifyElementVisible(findTestObject('Mobile/OrderInvoice/Tab-Product Details'), 5)

Mobile.takeScreenshot()

println('Verified : product details tab presented in Product Scheme details Screen.')

Mobile.verifyElementExist(findTestObject('Mobile/OrderInvoice/ProductDetails/BasePrice_Value_Field'), 0)

Mobile.verifyElementVisible(findTestObject('Mobile/OrderInvoice/ProductDetails/BasePrice_Value_Field'), 0)

Mobile.verifyElementExist(findTestObject('Mobile/OrderInvoice/ProductDetails/CaseSize_Value_Field'), 0)

Mobile.verifyElementVisible(findTestObject('Mobile/OrderInvoice/ProductDetails/CaseSize_Value_Field'), 0)

Mobile.verifyElementExist(findTestObject('Mobile/OrderInvoice/ProductDetails/PiecePrice_Value_Field'), 0)

Mobile.verifyElementVisible(findTestObject('Mobile/OrderInvoice/ProductDetails/PiecePrice_Value_Field'), 0)

Mobile.verifyElementExist(findTestObject('Mobile/OrderInvoice/ProductDetails/SIH_Value_Field'), 0)

Mobile.verifyElementVisible(findTestObject('Mobile/OrderInvoice/ProductDetails/SIH_Value_Field'), 0)

Mobile.takeScreenshot()

println('Verified : these are fields are present under in the Product details tab.')

Actual_BasePrice = Mobile.getText(findTestObject('Mobile/OrderInvoice/ProductDetails/BasePrice_Value_Field'), 0)

Actual_PiecePrice = Mobile.getText(findTestObject('Mobile/OrderInvoice/ProductDetails/PiecePrice_Value_Field'), 0)

Actual_CaseSize = Mobile.getText(findTestObject('Mobile/OrderInvoice/ProductDetails/CaseSize_Value_Field'), 0)

Actual_SIH = Mobile.getText(findTestObject('Mobile/OrderInvoice/ProductDetails/SIH_Value_Field'), 0)

GlobalVariable.BasePrice = findTestData('Mobile Input Data/Invoice').getValue('BasePrice', Product_Index)

GlobalVariable.PiecePrice = findTestData('Mobile Input Data/Invoice').getValue('PiecePrice', Product_Index)

GlobalVariable.CaseSize = findTestData('Mobile Input Data/Invoice').getValue('CaseSize', Product_Index)

Mobile.verifyEqual(Actual_BasePrice, GlobalVariable.BasePrice, FailureHandling.OPTIONAL)

Mobile.verifyEqual(Actual_PiecePrice, GlobalVariable.PiecePrice, FailureHandling.CONTINUE_ON_FAILURE)

Mobile.verifyEqual(Actual_CaseSize, GlobalVariable.CaseSize, FailureHandling.STOP_ON_FAILURE)

Mobile.verifyEqual(Actual_SIH, Exepected_SIH_value, FailureHandling.OPTIONAL)

Mobile.takeScreenshot()

println('Verified : the field values from the product master are presented under in the Product details tab ')

GlobalVariable.Qty = findTestData('Mobile Input Data/Invoice').getValue('Keypad_Number', 1)

println(GlobalVariable.Qty)

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Enter_PIECE_Qty'), [('Quantity') : GlobalVariable.Qty], FailureHandling.STOP_ON_FAILURE)

Mobile.tap(findTestObject('Mobile/Common/Icon_Back'), 0)

Mobile.delay(2, FailureHandling.STOP_ON_FAILURE)

Actual_piece_value = Mobile.getText(findTestObject('Mobile/OrderInvoice/Enter_Qty'), 0)

SKU_TOTAL = Mobile.getText(findTestObject('Mobile/OrderInvoice/OrderInvoiceScrn-SkuTotal'), 0)

Mobile.verifyMatch(Actual_piece_value, GlobalVariable.Qty, false, FailureHandling.CONTINUE_ON_FAILURE)

println('Verified:  User entered piece quantity in the Product scheme details Screen, it displayed both Stock&Order screen and Product scheme details Screen')

Mobile.takeScreenshot()

println('Verified :User should be able to take order through the Product details page')

exlpath = 'DDF/Web Input Data/Web Input Data.xlsx'

Sheetname = 'Sales_Order_Invoice_View'

workbook01 = ExcelKeywords.getWorkbook(exlpath)

sheet1 = ExcelKeywords.getExcelSheet(workbook01, Sheetname)

ExcelKeywords.setValueToCellByIndex(sheet1, 1, 0, GlobalVariable.ProductName)

ExcelKeywords.setValueToCellByIndex(sheet1, 1, 1, Actual_PiecePrice)

ExcelKeywords.setValueToCellByIndex(sheet1, 1, 2, GlobalVariable.Qty)

ExcelKeywords.setValueToCellByIndex(sheet1, 1, 3, SKU_TOTAL)

ExcelKeywords.saveWorkbook(exlpath, workbook01)

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Get_CASE_PIECE_Qty'), [:], FailureHandling.STOP_ON_FAILURE)

Mobile.tap(findTestObject('Mobile/Common/Btn_Next'), 0)

Mobile.callTestCase(findTestCase('Product_Mobile/Common/MustSell_Alert'), [:], FailureHandling.STOP_ON_FAILURE)

//WebUI.callTestCase(findTestCase('Product_Mobile/Common/Handle_Batch_Screen'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('Product_Mobile/Common/Handle_ApplyingScheme_Screen'), [:], FailureHandling.STOP_ON_FAILURE)

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Handle_Empties_Screen'), [:], FailureHandling.OPTIONAL)

'SUMMARY SCREEN VALIDATIONS'

//TC_15
' Entered Case and Piece Qty and product name  in the Order & Invoice Screen should be correctly reflected on Summary Screen'
Actual_Sku = Mobile.getText(findTestObject('Mobile/SummaryScreen/SummaryScreen-ProductTitle'), 0)

Mobile.verifyMatch(Actual_Sku, GlobalVariable.ProductName, false, FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

KeywordUtil.logInfo(('Verified : ' + Actual_Sku) + ' This Product code viewed in the summary screen.')

Actual_case = Mobile.getText(findTestObject('Mobile/SummaryScreen/Summary-Screen-CaseValue'), 0)

Mobile.verifyEqual(Actual_case, GlobalVariable.CaseQty, FailureHandling.STOP_ON_FAILURE)

KeywordUtil.logInfo(('Verified :  User not entered the case quanity so ' + Actual_case) + ' viewed in the summary screen.')

Mobile.takeScreenshot()

Actual_Piece = Mobile.getText(findTestObject('Mobile/SummaryScreen/Summary-Screen-PieceValue'), 0)

Mobile.verifyEqual(Actual_Piece, GlobalVariable.PieceQty, FailureHandling.STOP_ON_FAILURE)

KeywordUtil.logInfo(('Verified :  User entered the piece quanity so ' + Actual_case) + ' viewed in the summary screen.')

Mobile.takeScreenshot()

//TC_15
Mobile.tap(findTestObject('Mobile/SummaryScreen/SummaryScreen-InfoIcon'), 0)

//TC_17
Mobile.takeScreenshot()

Actual_SplitScreen_OrderAmt = Mobile.getText(findTestObject('Mobile/SummaryScreen/SplitScreen-OrderAmt'), 0, FailureHandling.STOP_ON_FAILURE)

Calculated_Order_Value = (((Double.parseDouble(Actual_case) * Double.parseDouble(Actual_CaseSize)) + Double.parseDouble(
    Actual_Piece)) * Double.parseDouble(Actual_PiecePrice))

Mobile.verifyEqual(Double.parseDouble(Actual_SplitScreen_OrderAmt), Calculated_Order_Value, FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

KeywordUtil.logInfo('Verified : the Amount Splitup Screen Order Values is ' + Actual_SplitScreen_OrderAmt)

//TC_17
//Discount new
Actual_SplitScreen_ITEM_Disc = Mobile.getText(findTestObject('Mobile/SummaryScreen/SplitScreen-ITEM_Discount'), 0, FailureHandling.STOP_ON_FAILURE)

Actual_SplitScreen_ITEM_Disc = Actual_SplitScreen_ITEM_Disc.replaceAll('-', '')

Expected_Item_Discount = findTestData('Web Input Data/Discount').getValue('Discount_Value', 1)

Mobile.verifyEqual(Double.parseDouble(Actual_SplitScreen_ITEM_Disc), Double.parseDouble(Expected_Item_Discount), FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

KeywordUtil.logInfo('Verified : the Amount Splitup Screen ITEM Discount Values is ' + Actual_SplitScreen_ITEM_Disc)

//tax new
//TC_18
Actual_SplitScreen_CGST_Value = Mobile.getText(findTestObject('Mobile/SummaryScreen/SplitScreen-CGST Value'), 0)

CGST_Percentage = findTestData('Web Input Data/Tax').getValue('Tax_Rate', 1)

Expected_CGST_Value = (((((Double.parseDouble(Actual_case) * Double.parseDouble(Actual_CaseSize)) + Double.parseDouble(Actual_Piece)) * 
Double.parseDouble(Actual_PiecePrice)) - Double.parseDouble(Actual_SplitScreen_ITEM_Disc)) * (Double.parseDouble(CGST_Percentage) / 
100))

Mobile.verifyEqual(Double.parseDouble(Actual_SplitScreen_CGST_Value), Expected_CGST_Value, FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

KeywordUtil.logInfo('Verified : the Amount Splitup Screen IGST Values is ' + Actual_SplitScreen_CGST_Value)

//TC_18
//TC_19
Actual_SplitScreen_Total_Amt = Mobile.getText(findTestObject('Mobile/SummaryScreen/SliptScreen_TotalAmt'), 0)

Calculated_Total_Amt = ((Calculated_Order_Value + Expected_CGST_Value) - Double.parseDouble(Actual_SplitScreen_ITEM_Disc))

Mobile.verifyEqual(Double.parseDouble(Actual_SplitScreen_Total_Amt), Calculated_Total_Amt, FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

KeywordUtil.logInfo('Verified : the Amount Splitup Screen Total Values is ' + Actual_SplitScreen_Total_Amt)

//TC_19
Mobile.tap(findTestObject('Mobile/SummaryScreen/SummaryScreen-info_closeBtn'), 0)

//SKU_Total_new
Actual_SKU_totalAmt = Mobile.getText(findTestObject('Mobile/SummaryScreen/Summary-Screen-TotalAmt'), 0, FailureHandling.OPTIONAL)

Mobile.verifyEqual(Double.parseDouble(Actual_SKU_totalAmt), Calculated_Total_Amt, FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

KeywordUtil.logInfo('Verified : the Summary Screen SKU Total Values is ' + Actual_SKU_totalAmt)

//SKU_Total_new
//TC_16
Actual_ValueAmt = Mobile.getText(findTestObject('Mobile/SummaryScreen/SummaryScreen-ValueAmt'), 0, FailureHandling.OPTIONAL)

Mobile.verifyEqual(Double.parseDouble(Actual_ValueAmt), Calculated_Total_Amt, FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

KeywordUtil.logInfo('Verified : the Summary Screen Left Buttom Values is ' + Actual_ValueAmt)

WebUI.callTestCase(findTestCase('Product_Mobile/Common/Generate_Order'), [:], FailureHandling.STOP_ON_FAILURE)

//new_order ID
ExcelKeywords.setValueToCellByIndex(sheet1, 1, 4, Actual_SplitScreen_CGST_Value)

ExcelKeywords.setValueToCellByIndex(sheet1, 1, 19, Actual_SplitScreen_ITEM_Disc)

ExcelKeywords.setValueToCellByIndex(sheet1, 1, 5, Actual_SplitScreen_Total_Amt)

ExcelKeywords.setValueToCellByIndex(sheet1, 1, 6, GlobalVariable.InvoiceNo)

ExcelKeywords.saveWorkbook(exlpath, workbook01)

WebUI.callTestCase(findTestCase('Product_Mobile/Common/Store_Menu_Selection'), [('MenuName') : findTestData('Mobile Input Data/Common').getValue(
            'Menu', 4), ('SubMenuName') : findTestData('Mobile Input Data/Common').getValue('Menu', 1)], FailureHandling.STOP_ON_FAILURE)

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Call_Analysis'), [:], FailureHandling.STOP_ON_FAILURE)

