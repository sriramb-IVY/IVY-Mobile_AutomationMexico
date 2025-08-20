import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.keyword.excel.ExcelKeywords as ExcelKeywords
import internal.GlobalVariable as GlobalVariable
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil

//1 = 1

String sheet_name = 'CriteriaMapping_Validation'

String file_name = 'Mobile Input data'

ArrayList<String> Retailer = CustomKeywords.'poi.Automation.GetAllDataRow_Flag.getAllDataRow'(file_name, sheet_name, 'Retailer')

ArrayList<String> Location = CustomKeywords.'poi.Automation.GetAllDataRow_Flag.getAllDataRow'(file_name, sheet_name, 'Location')

WebUI.callTestCase(findTestCase('Product_Mobile/Common/Web Login and Navigation/DivisionLogin'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.waitForElementNotVisible(findTestObject('Web Part/Re-usable/Page Load'), 100)

WebUI.callTestCase(findTestCase('Product_Mobile/Common/Web Login and Navigation/Navigate to Retailer Master'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.delay(3)

GlobalVariable.label = findTestData('Web Input Data/Scheme_Retailer_Master').getValue('Label', 1)

WebUI.waitForElementClickable(findTestObject('Web Part/Scheme Request/Retailer_mapping/label_select'), 15)

WebUI.click(findTestObject('Web Part/Scheme Request/Retailer_mapping/label_select'))

GlobalVariable.dropdownValue = findTestData('Web Input Data/Scheme_Retailer_Master').getValue('National', 1)

WebUI.click(findTestObject('Web Part/Scheme Request/Retailer_mapping/input_data'))

WebUI.delay(2)

GlobalVariable.label = findTestData('Web Input Data/Scheme_Retailer_Master').getValue('Label', 2)

WebUI.waitForElementClickable(findTestObject('Web Part/Scheme Request/Retailer_mapping/label_select'), 15)

WebUI.click(findTestObject('Web Part/Scheme Request/Retailer_mapping/label_select'))

GlobalVariable.dropdownValue = findTestData('Web Input Data/Scheme_Retailer_Master').getValue('Region', 1)

WebUI.click(findTestObject('Web Part/Scheme Request/Retailer_mapping/input_data'))

WebUI.delay(2)

GlobalVariable.label = findTestData('Web Input Data/Scheme_Retailer_Master').getValue('Label', 3)

WebUI.waitForElementClickable(findTestObject('Web Part/Scheme Request/Retailer_mapping/label_select'), 15)

WebUI.click(findTestObject('Web Part/Scheme Request/Retailer_mapping/label_select'))

GlobalVariable.dropdownValue = findTestData('Web Input Data/Scheme_Retailer_Master').getValue('State', 2)

WebUI.click(findTestObject('Web Part/Scheme Request/Retailer_mapping/input_data'))

WebUI.delay(2)

String Location_selected = WebUI.getAttribute(findTestObject('Web Part/Scheme Request/Retailer_mapping/label_select'), 
    'value')

KeywordUtil.logInfo(Location_selected)

Location_name_mapped = Location.get(1)

// location verification
WebUI.verifyMatch(Location_selected, Location_name_mapped, false)

GlobalVariable.label = findTestData('Web Input Data/Scheme_Retailer_Master').getValue('Label', 4)

WebUI.waitForElementClickable(findTestObject('Web Part/Scheme Request/Retailer_mapping/label_select'), 15)

WebUI.click(findTestObject('Web Part/Scheme Request/Retailer_mapping/label_select'))

GlobalVariable.dropdownValue = findTestData('Web Input Data/Scheme_Retailer_Master').getValue('Area', 2)

WebUI.click(findTestObject('Web Part/Scheme Request/Retailer_mapping/input_data'))

WebUI.delay(2)

GlobalVariable.label = findTestData('Web Input Data/Scheme_Retailer_Master').getValue('Label', 5)

WebUI.waitForElementClickable(findTestObject('Web Part/Scheme Request/Retailer_mapping/label_select'), 15)

WebUI.click(findTestObject('Web Part/Scheme Request/Retailer_mapping/label_select'))

GlobalVariable.dropdownValue = findTestData('Web Input Data/Scheme_Retailer_Master').getValue('City', 2)

WebUI.click(findTestObject('Web Part/Scheme Request/Retailer_mapping/input_data'))

WebUI.delay(2)

GlobalVariable.label = findTestData('Web Input Data/Scheme_Retailer_Master').getValue('Label', 6)

WebUI.waitForElementClickable(findTestObject('Web Part/Scheme Request/Retailer_mapping/label_select'), 15)

WebUI.click(findTestObject('Web Part/Scheme Request/Retailer_mapping/label_select'))

GlobalVariable.dropdownValue = findTestData('Web Input Data/Scheme_Retailer_Master').getValue('Town', 2)

WebUI.click(findTestObject('Web Part/Scheme Request/Retailer_mapping/input_data'))

WebUI.delay(2)

WebUI.sendKeys(findTestObject('Web Part/Scheme Request/Retailer_mapping/name_input'), Retailer.get(1))

GlobalVariable.label = Retailer.get(1)

retailername = WebUI.getText(findTestObject('Object Repository/Web Part/Scheme Request/Retailer_mapping/Data_Select'))

KeywordUtil.logInfo(retailername)

GlobalVariable.label = findTestData('Web Input Data/Scheme_Retailer_Master').getValue('Channel', 2)

channel = WebUI.getText(findTestObject('Object Repository/Web Part/Scheme Request/Retailer_mapping/Data_Select'))

KeywordUtil.logInfo(channel)

WebUI.click(findTestObject('Object Repository/Web Part/Scheme Request/Retailer_mapping/Data_Select'))

GlobalVariable.Button = findTestData('Web Input Data/Scheme_Retailer_Master').getValue('Button', 1)

WebUI.click(findTestObject('Object Repository/Web Part/Scheme Request/Retailer_mapping/branch_mapping_label_btn'))

WebUI.waitForElementNotVisible(findTestObject('Web Part/Re-usable/Page Load'), 100)

WebUI.delay(2)

GlobalVariable.label = findTestData('Web Input Data/Scheme_Retailer_Master').getValue('Distributor', 2)

txtSrcall1 = WebUI.getAttribute(findTestObject('Web Part/Retailer Master/retailer_checkbox'), 'src')

KeywordUtil.logInfo(txtSrcall1)

if (txtSrcall1.contains('item_chk1.gif')) {
    println('CheckBox Checked')

    GlobalVariable.label = findTestData('Web Input Data/Scheme_Retailer_Master').getValue('Distributor', 3)

    GlobalVariable.branch_name = WebUI.getText(findTestObject('Object Repository/Web Part/Scheme Request/Retailer_mapping/Data_Select'))

    GlobalVariable.label = findTestData('Web Input Data/Scheme_Retailer_Master').getValue('Label', 8)

    WebUI.click(findTestObject('Web Part/Scheme Request/Retailer_mapping/x_close'))
}

GlobalVariable.label = findTestData('Web Input Data/Scheme_Retailer_Master').getValue('Channel', 2)

WebUI.doubleClick(findTestObject('Object Repository/Web Part/Scheme Request/Retailer_mapping/Data_Select'))

WebUI.waitForElementNotVisible(findTestObject('Web Part/Re-usable/Page Load'), 150)

WebUI.delay(5)

GlobalVariable.label = findTestData('Web Input Data/Scheme_Retailer_Master').getValue('Label', 7)

WebUI.click(findTestObject('Object Repository/Web Part/Scheme Request/Retailer_mapping/select_tab'))

GlobalVariable.label = findTestData('Web Input Data/Scheme_Retailer_Master').getValue('Attribute', 1)

attribute = WebUI.getText(findTestObject('Object Repository/Web Part/Scheme Request/Retailer_mapping/Data_Select'))

KeywordUtil.logInfo(attribute)

exlpath = 'DDF\\Mobile Input data\\Mobile Input data.xlsx'

Sheetname = 'Scheme_CriteriaMapping'

workbook01 = ExcelKeywords.getWorkbook(exlpath)

sheet1 = ExcelKeywords.getExcelSheet(workbook01, Sheetname)

ExcelKeywords.setValueToCellByIndex(sheet1, 2, 3, channel)

ExcelKeywords.setValueToCellByIndex(sheet1, 2, 4, GlobalVariable.branch_name)

//ExcelKeywords.setValueToCellByIndex(sheet1, 2, 5, retailername)

ExcelKeywords.setValueToCellByIndex(sheet1, 2, 6, attribute)

ExcelKeywords.saveWorkbook(exlpath, workbook01)

