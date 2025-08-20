import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
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
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Web Login and Navigation/DivisionLogin'), [:], FailureHandling.STOP_ON_FAILURE)

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Web Login and Navigation/Navigate to Product Distributor'), 
    [:], FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Web Part/Product distributor/Name_Filter'))

GlobalVariable.label = findTestData('Mobile Input Data/StockProposal').getValue('Distributor_Name', 1)

WebUI.setText(findTestObject('Web Part/Product distributor/Name_Filter'), GlobalVariable.label)

WebUI.delay(3)

WebUI.doubleClick(findTestObject('Web Part/Re-usable/Global/td_Tag(GridSkuRow)'))

WebUI.waitForElementNotVisible(findTestObject('Web Part/Re-usable/Page Load'), 100, FailureHandling.OPTIONAL)

WebUI.waitForElementVisible(findTestObject('Web Part/Product distributor/Category_Field'), 0)

WebUI.click(findTestObject('Web Part/Product distributor/Category_Field'))

GlobalVariable.label = findTestData('Mobile Input Data/StockProposal').getValue('Category', 1)

WebUI.click(findTestObject('Web Part/Re-usable/Global/Span_Tag(Dropdown)'))

WebUI.waitForElementNotVisible(findTestObject('Web Part/Re-usable/Page Load'), 100, FailureHandling.OPTIONAL)

WebUI.click(findTestObject('Web Part/Product distributor/BRAND_Field'))

GlobalVariable.label = findTestData('Mobile Input Data/StockProposal').getValue('BRAND', 1)

WebUI.click(findTestObject('Web Part/Re-usable/Global/Span_Tag(Dropdown)'))

WebUI.waitForElementNotVisible(findTestObject('Web Part/Re-usable/Page Load'), 50, FailureHandling.OPTIONAL)

GlobalVariable.label = findTestData('Mobile Input Data/StockProposal').getValue('Sub_Brand', 1)

WebUI.click(findTestObject('Web Part/Product distributor/Sub Brand'))

WebUI.click(findTestObject('Web Part/Re-usable/Global/Span_Tag(Dropdown)'))

WebUI.waitForElementNotVisible(findTestObject('Web Part/Re-usable/Page Load'), 50, FailureHandling.OPTIONAL)

WebUI.setText(findTestObject('Web Part/Product distributor/Product_Desc_Filter_Field'), findTestData('Mobile Input Data/StockProposal').getValue(
        'Product_Name', 1))

WebUI.delay(2)

product_desc = WebUI.getText(findTestObject('Web Part/Product distributor/Get_SKU_Desc'))

KeywordUtil.logInfo(product_desc + '  :  This product we need to verify in the stock proposal vanseller screen')

String exlpath = 'DDF\\Mobile Input data\\Mobile Input data.xlsx'

String Sheetname = 'StockProposal'

String Sheetname2 = 'ManualVanLoad'

String Sheetname3 = 'VanUnload'

workbook01 = ExcelKeywords.getWorkbook(exlpath)

sheet1 = ExcelKeywords.getExcelSheet(workbook01, Sheetname)

sheet2 = ExcelKeywords.getExcelSheet(workbook01, Sheetname2)

sheet3 = ExcelKeywords.getExcelSheet(workbook01, Sheetname3)

ExcelKeywords.setValueToCellByIndex(sheet1, 1, 1, product_desc)

ExcelKeywords.setValueToCellByIndex(sheet2, 1, 0, product_desc)

ExcelKeywords.setValueToCellByIndex(sheet3, 1, 2, product_desc)

ExcelKeywords.saveWorkbook(exlpath, workbook01)

//Get price
Mobile.callTestCase(findTestCase('Product_Mobile/Common/Web Login and Navigation/Navigate to Product Hierarchy'), [:], 
    FailureHandling.STOP_ON_FAILURE)

WebUI.waitForElementNotVisible(findTestObject('Web Part/Re-usable/Page Load'), 50)

WebUI.click(findTestObject('Web Part/Product_Hierarchy/SKU_SubMenu'))

WebUI.waitForElementNotVisible(findTestObject('Web Part/Re-usable/Page Load'), 50)

WebUI.waitForElementVisible(findTestObject('Web Part/Product_Hierarchy/Sku_Filter_Field'), 50, FailureHandling.STOP_ON_FAILURE)

GlobalVariable.ProductName = product_desc

WebUI.setText(findTestObject('Web Part/Product_Hierarchy/Sku_Filter_Field'), GlobalVariable.ProductName)

WebUI.waitForElementNotVisible(findTestObject('Web Part/Re-usable/Page Load'), 50)

GlobalVariable.label = GlobalVariable.ProductName

WebUI.delay(1)

WebUI.click(findTestObject('Web Part/TaxScreen/Global Object/td_Tag(GridSkuRow)'))

WebUI.delay(1)

WebUI.click(findTestObject('Web Part/Product_Hierarchy/Edit_Btn'))

WebUI.waitForElementNotVisible(findTestObject('Web Part/Re-usable/Page Load'), 50)

WebUI.scrollToElement(findTestObject('Web Part/Product_Hierarchy/Case_ConversionQty'), 0)

GlobalVariable.CaseSize = WebUI.getText(findTestObject('Web Part/Product_Hierarchy/Case_ConversionQty'))

println('CoversionQty_Value : ' + GlobalVariable.CaseSize)

BasePrice = WebUI.getText(findTestObject('Web Part/Product_Hierarchy/Piece_DP_Value'))

GlobalVariable.BasePrice = BasePrice.replace('$ ', '')

println('BasePrice_Value (DP) : ' + GlobalVariable.BasePrice)

//		PiecePrice = WebUI.getText(findTestObject('Web Part/Product_Hierarchy/Piece_RP_Value'))
//
//		GlobalVariable.PiecePrice = PiecePrice.replace('$ ', '')
//
//		println('PiecePrice_Value (RP) : ' + GlobalVariable.PiecePrice)
WebUI.click(findTestObject('Web Part/Product_Hierarchy/X_btn'))

String Sheetname1 = 'StockProposal'

workbook01 = ExcelKeywords.getWorkbook(exlpath)

sheet1 = ExcelKeywords.getExcelSheet(workbook01, Sheetname1)

sheet2 = ExcelKeywords.getExcelSheet(workbook01, Sheetname2)

ExcelKeywords.setValueToCellByIndex(sheet1, 1, 2, GlobalVariable.BasePrice)

ExcelKeywords.setValueToCellByIndex(sheet1, 1, 3, GlobalVariable.CaseSize)

ExcelKeywords.setValueToCellByIndex(sheet2, 1, 4, GlobalVariable.CaseSize)

ExcelKeywords.saveWorkbook(exlpath, workbook01)

WebUI.closeBrowser()

