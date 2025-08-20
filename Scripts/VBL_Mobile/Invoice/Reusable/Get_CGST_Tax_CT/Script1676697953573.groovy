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

println(ProductName)

GlobalVariable.ProductName = ProductName

Mobile.callTestCase(findTestCase('VBL_Mobile/Re-Usables/Web Login and Navigation/AdminLogin'), [:], FailureHandling.STOP_ON_FAILURE)

Mobile.callTestCase(findTestCase('VBL_Mobile/Re-Usables/Web Login and Navigation/Navigate to Tax Group Mapping'), [:], FailureHandling.STOP_ON_FAILURE)

GlobalVariable.label = findTestData('VBL_Mobile Input Data/Invoice').getValue('Tax_Location', 1)

WebUI.click(findTestObject('XXXXXXXXXXXX/Web Part/Re-usable/Global/td_Tag(GridSkuRow)'))

WebUI.delay(2)

WebUI.click(findTestObject('XXXXXXXXXXXX/Web Part/Collection View/View_btn'))

not_run: WebUI.click(findTestObject('XXXXXXXXXXXX/Web Part/Re-usable/Global/td_Tag(GridSkuRow)'), FailureHandling.OPTIONAL)

WebUI.waitForElementNotVisible(findTestObject('XXXXXXXXXXXX/Web Part/Re-usable/Page Load'), 50)

WebUI.click(findTestObject('XXXXXXXXXXXX/Web Part/TaxScreen/Proceed_Btn'))

WebUI.click(findTestObject('XXXXXXXXXXXX/Web Part/TaxScreen/DirectProduct_Tab'))

WebUI.waitForElementNotVisible(findTestObject('XXXXXXXXXXXX/Web Part/Re-usable/Page Load'), 50)

WebUI.delay(1)

WebUI.click(findTestObject('XXXXXXXXXXXX/Web Part/TaxScreen/Product_filter_field'))

WebUI.setText(findTestObject('XXXXXXXXXXXX/Web Part/TaxScreen/Product_filter_field'), GlobalVariable.ProductName)

WebUI.waitForElementNotVisible(findTestObject('XXXXXXXXXXXX/Web Part/Re-usable/Page Load'), 50)

GlobalVariable.label = GlobalVariable.ProductName

WebUI.delay(1)

Tax_Group_Name = WebUI.getText(findTestObject('XXXXXXXXXXXX/Web Part/TaxScreen/Get_TaxGroupName'))

println('Tax_Group_Name : ' + Tax_Group_Name)

Mobile.callTestCase(findTestCase('VBL_Mobile/Re-Usables/Web Login and Navigation/Navigate to Tax Group Creation'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.waitForElementNotVisible(findTestObject('XXXXXXXXXXXX/Web Part/Re-usable/Page Load'), 50)

WebUI.click(findTestObject('XXXXXXXXXXXX/Web Part/TaxScreen/TaxDescription_Filter_Field'))

WebUI.setText(findTestObject('XXXXXXXXXXXX/Web Part/TaxScreen/TaxDescription_Filter_Field'), Tax_Group_Name)

WebUI.delay(2)

GlobalVariable.label = Tax_Group_Name

WebUI.click(findTestObject('XXXXXXXXXXXX/Web Part/TaxScreen/Global Object/td_Tag(GridSkuRow)'))

WebUI.click(findTestObject('XXXXXXXXXXXX/Web Part/TaxScreen/Edit_Btn'))

WebUI.waitForElementNotVisible(findTestObject('XXXXXXXXXXXX/Web Part/Re-usable/Page Load'), 50)

GlobalVariable.CGST_Tax = WebUI.getText(findTestObject('XXXXXXXXXXXX/Web Part/TaxScreen/Get_CGST_TaxValue'))

println('CGST_TaxAmt : ' + GlobalVariable.CGST_Tax)

WebUI.closeBrowser()

String exlpath = 'DDF\\VBL\\Mobile Input data\\Mobile Input data.xlsx'

String Sheetname = 'Invoice'

workbook01 = ExcelKeywords.getWorkbook(exlpath)

sheet1 = ExcelKeywords.getExcelSheet(workbook01, Sheetname)

ExcelKeywords.setValueToCellByIndex(sheet1, Index, 6, GlobalVariable.CGST_Tax)

ExcelKeywords.saveWorkbook(exlpath, workbook01)

WebUI.closeBrowser()

