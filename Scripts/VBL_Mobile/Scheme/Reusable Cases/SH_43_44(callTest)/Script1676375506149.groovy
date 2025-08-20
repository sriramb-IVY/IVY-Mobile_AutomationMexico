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

Mobile.callTestCase(findTestCase('VBL_Mobile/Re-Usables/Web Login and Navigation/AdminLogin'), [:], FailureHandling.STOP_ON_FAILURE)

Mobile.callTestCase(findTestCase('VBL_Mobile/Re-Usables/Web Login and Navigation/Navigate to Scheme Master'), [:], FailureHandling.STOP_ON_FAILURE)

String sheet_name = 'Attribute_Scheme'

String file_name = 'Mobile Input data'

ArrayList<String> SchemeName = CustomKeywords.'poi.VBL.Automation.GetAllDataRow_Flag.getAllDataRow'(file_name, sheet_name, 
    'SchemeName')

for (int i = 0; i < SchemeName.size(); i++) {
    WebUI.waitForElementVisible(findTestObject('XXXXXXXXXXXX/Web Part/Scheme/Code_Filter'), 100)

    WebUI.click(findTestObject('XXXXXXXXXXXX/Web Part/Scheme/Code_Filter'))

    GlobalVariable.label = SchemeName.get(i)

    WebUI.setText(findTestObject('XXXXXXXXXXXX/Web Part/Scheme/Code_Filter'), GlobalVariable.label)

    WebUI.delay(2)

    WebUI.waitForElementVisible(findTestObject('XXXXXXXXXXXX/Web Part/Re-usable/Global/td_Tag(GridSkuRow)'), 50)

    WebUI.click(findTestObject('XXXXXXXXXXXX/Web Part/Re-usable/Global/td_Tag(GridSkuRow)'))

    WebUI.delay(2)

    WebUI.click(findTestObject('XXXXXXXXXXXX/Web Part/Scheme/Edit_Btn'))

    WebUI.waitForElementNotVisible(findTestObject('XXXXXXXXXXXX/Web Part/Re-usable/Page Load'), 100) //	exlpath = 'DDF\\Mobile Input data\\Mobile Input data.xlsx'
    

    WebUI.waitForElementVisible(findTestObject('XXXXXXXXXXXX/Web Part/Scheme/Next_Btn_SchemeTab'), 0)

    WebUI.click(findTestObject('XXXXXXXXXXXX/Web Part/Scheme/Next_Btn_SchemeTab'))

    WebUI.waitForElementNotVisible(findTestObject('XXXXXXXXXXXX/Web Part/Re-usable/Page Load'), 100 //	exlpath = 'DDF\\Mobile Input data\\Mobile Input data.xlsx'
        )

    WebUI.waitForElementVisible(findTestObject('XXXXXXXXXXXX/Web Part/Scheme/Next_Btn_PromotionGroupTab'), 0)

    WebUI.click(findTestObject('XXXXXXXXXXXX/Web Part/Scheme/Next_Btn_PromotionGroupTab'))

    WebUI.waitForElementNotVisible(findTestObject('XXXXXXXXXXXX/Web Part/Re-usable/Page Load'), 100 //	exlpath = 'DDF\\Mobile Input data\\Mobile Input data.xlsx'
        )

    WebUI.waitForElementVisible(findTestObject('XXXXXXXXXXXX/Web Part/Scheme/Next_Btn_PromotionSlabTab'), 0)

    WebUI.click(findTestObject('XXXXXXXXXXXX/Web Part/Scheme/Next_Btn_PromotionSlabTab'))

    WebUI.waitForElementNotVisible(findTestObject('XXXXXXXXXXXX/Web Part/Re-usable/Page Load'), 100 //	exlpath = 'DDF\\Mobile Input data\\Mobile Input data.xlsx'
        )

    WebUI.waitForElementVisible(findTestObject('XXXXXXXXXXXX/Web Part/Scheme/View_Icon_CriteriaMappingTab'), 0)

    WebUI.click(findTestObject('XXXXXXXXXXXX/Web Part/Scheme/View_Icon_CriteriaMappingTab'))

    WebUI.waitForElementVisible(findTestObject('XXXXXXXXXXXX/Web Part/Scheme/MappedRetailerAttribute_1row'), 0)

    MappedRetailerAttribute_Name = WebUI.getText(findTestObject('XXXXXXXXXXXX/Web Part/Scheme/MappedRetailerAttribute_1row'))

	
    WebUI.click(findTestObject('XXXXXXXXXXXX/Web Part/Scheme/X_btn_CriteriaMappingScreen'))

    WebUI.delay(1)

    WebUI.click(findTestObject('XXXXXXXXXXXX/Web Part/Scheme/Return_Btn_CriteriaMappingTab'))
	
	exlpath = 'DDF\\Mobile Input data\\Mobile Input data.xlsx'
	
	Sheetname = 'Attribute_Scheme'

	workbook01 = ExcelKeywords.getWorkbook(exlpath)

	sheet1 = ExcelKeywords.getExcelSheet(workbook01, Sheetname)

	ExcelKeywords.setValueToCellByIndex(sheet1, i + 1, 19, MappedRetailerAttribute_Name)

	ExcelKeywords.saveWorkbook(exlpath, workbook01)
}

