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
import com.kms.katalon.keyword.excel.ExcelKeywords

//retailer master

//WebUI.refresh()

WebUI.callTestCase(findTestCase('Product_Mobile/Common/Web Login and Navigation/DivisionLogin'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('Product_Mobile/Common/Web Login and Navigation/Navigate to Route Master'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.waitForElementNotVisible(findTestObject('Web Part/Re-usable/Page Load'), 100)

WebUI.mouseOver(findTestObject('Web Part/Retailer Master/Filter_icon'))

WebUI.click(findTestObject('Web Part/Retailer Master/Search_icon'))

WebUI.click(findTestObject('Web Part/Retailer Master/Retailer_Code_field'))

WebUI.setText(findTestObject('Web Part/Retailer Master/Retailer_Code_field'), findTestData('Web Input Data/Retailer Visit Plan').getValue(
        'Retailer', 1))

WebUI.click(findTestObject('Web Part/Retailer Master/Search_button'))

WebUI.click(findTestObject('Web Part/Retailer Master/Name_field'))

WebUI.delay(2)

WebUI.setText(findTestObject('Web Part/Retailer Master/Name_field'), findTestData('Web Input Data/Retailer Visit Plan').getValue(
        'Retailer_Name', 1))

WebUI.waitForElementNotVisible(findTestObject('Web Part/Re-usable/Page Load'), 100)

GlobalVariable.RetailerName = findTestData('Web Input Data/Retailer Visit Plan').getValue('Retailer', 1)

WebUI.click(findTestObject('Web Part/Retailer Master/1st_row'))

WebUI.click(findTestObject('Web Part/Retailer Master/Edit_icon'))

GlobalVariable.Distributor = findTestData('Web Input Data/Retailer Visit Plan').getValue('Location', 1)

Location_Expected = WebUI.getAttribute(findTestObject('Web Part/Retailer Master/Location_field'), 'value')

KeywordUtil.logInfo(Location_Expected)

GlobalVariable.label1 = Location_Expected

Store_Expected = WebUI.getAttribute(findTestObject('Web Part/Retailer Master/Store_name'), 'value')

KeywordUtil.logInfo(Store_Expected)

exlpath = 'DDF\\Mobile Input data\\Mobile Input data.xlsx'

Sheetname = 'Todays Plan'

workbook01 = ExcelKeywords.getWorkbook(exlpath)

sheet1 = ExcelKeywords.getExcelSheet(workbook01, Sheetname)

ExcelKeywords.setValueToCellByIndex(sheet1, 2, 1, Location_Expected)

ExcelKeywords.setValueToCellByIndex(sheet1, 2, 0, Store_Expected)

ExcelKeywords.saveWorkbook(exlpath, workbook01)

WebUI.closeBrowser()

