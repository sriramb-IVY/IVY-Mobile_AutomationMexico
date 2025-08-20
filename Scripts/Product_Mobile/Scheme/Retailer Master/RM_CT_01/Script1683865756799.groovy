import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.keyword.excel.ExcelKeywords as ExcelKeywords
import internal.GlobalVariable as GlobalVariable
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint

//0 = 0
String sheet_name = 'CriteriaMapping_Validation'

String file_name = 'Mobile Input data'

ArrayList<String> Retailer = CustomKeywords.'poi.Automation.GetAllDataRow_Flag.getAllDataRow'(file_name, sheet_name, 'Retailer')

//ArrayList<String> Sales_person = CustomKeywords.'poi.Automation.GetAllDataRow_Flag.getAllDataRow'(file_name, sheet_name, 
//    'Sales_person')

ArrayList<String> Location = CustomKeywords.'poi.Automation.GetAllDataRow_Flag.getAllDataRow'(file_name, sheet_name, 
    'Location')

WebUI.callTestCase(findTestCase('Product_Mobile/Common/Web Login and Navigation/DivisionLogin'), [:], FailureHandling.STOP_ON_FAILURE)
WebUI.waitForElementNotVisible(findTestObject('Web Part/Re-usable/Page Load'), 100)

WebUI.callTestCase(findTestCase('Product_Mobile/Common/Web Login and Navigation/Navigate to Retailer Master'), [:], 
    FailureHandling.STOP_ON_FAILURE)

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

GlobalVariable.dropdownValue = findTestData('Web Input Data/Scheme_Retailer_Master').getValue('State', 1)

WebUI.click(findTestObject('Web Part/Scheme Request/Retailer_mapping/input_data'))

WebUI.delay(2)

String Location_selected = WebUI.getAttribute(findTestObject('Web Part/Scheme Request/Retailer_mapping/label_select'), 
    'value')

KeywordUtil.logInfo(Location_selected)

Location_name_mapped = Location.get(0)

// location verification
WebUI.verifyMatch(Location_selected, Location_name_mapped, false)

GlobalVariable.label = findTestData('Web Input Data/Scheme_Retailer_Master').getValue('Label', 4)

WebUI.waitForElementClickable(findTestObject('Web Part/Scheme Request/Retailer_mapping/label_select'), 15)

WebUI.click(findTestObject('Web Part/Scheme Request/Retailer_mapping/label_select'))

GlobalVariable.dropdownValue = findTestData('Web Input Data/Scheme_Retailer_Master').getValue('Area', 1)

WebUI.click(findTestObject('Web Part/Scheme Request/Retailer_mapping/input_data'))

WebUI.delay(2)

GlobalVariable.label = findTestData('Web Input Data/Scheme_Retailer_Master').getValue('Label', 5)

WebUI.waitForElementClickable(findTestObject('Web Part/Scheme Request/Retailer_mapping/label_select'), 15)

WebUI.click(findTestObject('Web Part/Scheme Request/Retailer_mapping/label_select'))

GlobalVariable.dropdownValue = findTestData('Web Input Data/Scheme_Retailer_Master').getValue('City', 1)

WebUI.click(findTestObject('Web Part/Scheme Request/Retailer_mapping/input_data'))

WebUI.delay(2)

GlobalVariable.label = findTestData('Web Input Data/Scheme_Retailer_Master').getValue('Label', 6)

WebUI.waitForElementClickable(findTestObject('Web Part/Scheme Request/Retailer_mapping/label_select'), 15)

WebUI.click(findTestObject('Web Part/Scheme Request/Retailer_mapping/label_select'))

GlobalVariable.dropdownValue = findTestData('Web Input Data/Scheme_Retailer_Master').getValue('Town', 1)

WebUI.click(findTestObject('Web Part/Scheme Request/Retailer_mapping/input_data'))

WebUI.delay(2)

WebUI.sendKeys(findTestObject('Web Part/Scheme Request/Retailer_mapping/name_input'), Retailer.get(0))

GlobalVariable.label = Retailer.get(0)

String retailername = WebUI.getText(findTestObject('Object Repository/Web Part/Scheme Request/Retailer_mapping/Data_Select'))

KeywordUtil.logInfo(retailername)

GlobalVariable.label = findTestData('Web Input Data/Scheme_Retailer_Master').getValue('Channel', 3)

String channel = WebUI.getText(findTestObject('Object Repository/Web Part/Scheme Request/Retailer_mapping/Data_Select'))

KeywordUtil.logInfo(channel)

WebUI.click(findTestObject('Object Repository/Web Part/Scheme Request/Retailer_mapping/Data_Select'))

GlobalVariable.Button = findTestData('Web Input Data/Scheme_Retailer_Master').getValue('Button', 1)

WebUI.click(findTestObject('Object Repository/Web Part/Scheme Request/Retailer_mapping/branch_mapping_label_btn'))

WebUI.waitForElementNotVisible(findTestObject('Web Part/Re-usable/Page Load'), 100)

WebUI.delay(2)

WebUI.takeScreenshot()

GlobalVariable.label = findTestData('Web Input Data/Scheme_Retailer_Master').getValue('Distributor', 3)

String txtSrcall1 = WebUI.getAttribute(findTestObject('Web Part/Retailer Master/retailer_checkbox'), 'src')

KeywordUtil.logInfo(txtSrcall1)

if (txtSrcall1.contains('item_chk1.gif')) {
    KeywordUtil.logInfo('CheckBox Checked')

    GlobalVariable.label = findTestData('Web Input Data/Scheme_Retailer_Master').getValue('Distributor', 3)

    GlobalVariable.branch_name = WebUI.getText(findTestObject('Object Repository/Web Part/Scheme Request/Retailer_mapping/Data_Select'))

    GlobalVariable.label = findTestData('Web Input Data/Scheme_Retailer_Master').getValue('Label', 8)

    WebUI.click(findTestObject('Web Part/Scheme Request/Retailer_mapping/x_close'))
}

GlobalVariable.label = findTestData('Web Input Data/Scheme_Retailer_Master').getValue('Channel', 3)

WebUI.doubleClick(findTestObject('Web Part/Scheme Request/Retailer_mapping/Data_Select'))

WebUI.waitForElementNotVisible(findTestObject('Web Part/Re-usable/Page Load'), 150)

WebUI.takeScreenshot()

 exlpath = 'DDF\\Mobile Input data\\Mobile Input data.xlsx'

 Sheetname = 'CriteriaMapping_Validation'

workbook01 = ExcelKeywords.getWorkbook(exlpath)

sheet1 = ExcelKeywords.getExcelSheet(workbook01, Sheetname)

ExcelKeywords.setValueToCellByIndex(sheet1, 1 , 3, channel)

ExcelKeywords.setValueToCellByIndex(sheet1, 1 , 4, GlobalVariable.branch_name)

//ExcelKeywords.setValueToCellByIndex(sheet1, 1, 5, retailername)

//ExcelKeywords.setValueToCellByIndex(sheet1, 1+0, 6, GlobalVariable.Distributor)
ExcelKeywords.saveWorkbook(exlpath, workbook01)

