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

not_run: Mobile.callTestCase(findTestCase('Product_Mobile/Common/Web Login and Navigation/DivisionLogin'), [:], FailureHandling.STOP_ON_FAILURE)

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Web Login and Navigation/Navigate to Scheme Master'), [:], FailureHandling.STOP_ON_FAILURE)

String sheet_name = 'Fixed_Variable_Scheme'

String file_name = 'Mobile Input data'

ArrayList<String> SchemeName = CustomKeywords.'poi.Automation.GetAllDataRow_Flag.getAllDataRow'(file_name, sheet_name, 
    'SchemeName')

for (int i = 0; i < SchemeName.size(); i++) {
    WebUI.waitForElementVisible(findTestObject('Web Part/Scheme/Code_Filter'), 100)

    WebUI.click(findTestObject('Web Part/Scheme/Code_Filter'))

    GlobalVariable.label = SchemeName.get(i)

    WebUI.setText(findTestObject('Web Part/Scheme/Code_Filter'), GlobalVariable.label)
	
	WebUI.delay(2)

    WebUI.waitForElementVisible(findTestObject('Web Part/Re-usable/Global/td_Tag(GridSkuRow)'), 50)

    WebUI.click(findTestObject('Web Part/Re-usable/Global/td_Tag(GridSkuRow)'))
	WebUI.delay(2)
    WebUI.click(findTestObject('Web Part/Scheme/Edit_Btn'))

    WebUI.waitForElementNotVisible(findTestObject('Web Part/Re-usable/Page Load'), 100)

    WebUI.scrollToElement(findTestObject('Web Part/Scheme/SchemeApplyType'), 100)

    WebUI.waitForElementVisible(findTestObject('Web Part/Scheme/SchemeApplyType'), 100)

    SchemeApplyType = WebUI.getAttribute(findTestObject('Web Part/Scheme/SchemeApplyType'), 'value')

    WebUI.click(findTestObject('Web Part/Scheme/Return_Btn1'))

    exlpath = 'DDF\\Mobile Input data\\Mobile Input data.xlsx'

    Sheetname = 'Fixed_Variable_Scheme'

    workbook01 = ExcelKeywords.getWorkbook(exlpath)

    sheet1 = ExcelKeywords.getExcelSheet(workbook01, Sheetname)

    ExcelKeywords.setValueToCellByIndex(sheet1, i + 1, 4, SchemeApplyType)

    ExcelKeywords.saveWorkbook(exlpath, workbook01)
}

