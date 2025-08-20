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

WebUI.callTestCase(findTestCase('VBL_Mobile/Re-Usables/Web Login and Navigation/AdminLogin'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('VBL_Mobile/Re-Usables/Web Login and Navigation/Navigate to Retailer Attribute'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.waitForElementVisible(findTestObject('XXXXXXXXXXXX/Web Part/RetailerAttribute/EnterDistributorFilterField'), 0)

WebUI.click(findTestObject('XXXXXXXXXXXX/Web Part/RetailerAttribute/EnterDistributorFilterField'), FailureHandling.OPTIONAL)

GlobalVariable.label = findTestData('VBL_Mobile Input Data/RetailerTypeCreditNote_Scheme').getValue('Distributor', 1)

WebUI.setText(findTestObject('XXXXXXXXXXXX/Web Part/RetailerAttribute/EnterDistributorFilterField'), GlobalVariable.label)

//WebUI.scrollToElement(findTestObject('XXXXXXXXXXXX/Web Part/RetailerAttribute/Span and b_tag (distributor)'), 5, FailureHandling.OPTIONAL)
WebUI.delay(3)

WebUI.waitForElementVisible(findTestObject('XXXXXXXXXXXX/Web Part/RetailerAttribute/Span and b_tag (distributor)'), 50)

WebUI.click(findTestObject('XXXXXXXXXXXX/Web Part/RetailerAttribute/Span and b_tag (distributor)'))

WebUI.delay(3)

WebUI.waitForElementVisible(findTestObject('XXXXXXXXXXXX/Web Part/RetailerAttribute/Channel_Name_Filter_Field'), 100)

WebUI.click(findTestObject('XXXXXXXXXXXX/Web Part/RetailerAttribute/Channel_Name_Filter_Field'), FailureHandling.OPTIONAL)

GlobalVariable.label = findTestData('VBL_Mobile Input Data/RetailerTypeCreditNote_Scheme').getValue('Channel_Name', 1)

WebUI.setText(findTestObject('XXXXXXXXXXXX/Web Part/RetailerAttribute/Channel_Name_Filter_Field'), GlobalVariable.label)

WebUI.delay(3)

WebUI.waitForElementVisible(findTestObject('XXXXXXXXXXXX/Web Part/Re-usable/Global/td_Tag(GridSkuRow)'), 0)

WebUI.click(findTestObject('XXXXXXXXXXXX/Web Part/Re-usable/Global/td_Tag(GridSkuRow)'))

String sheet_name = 'RetailerTypeCreditNote_Scheme'

String file_name = 'Mobile Input data'

ArrayList<String> Retailer = CustomKeywords.'poi.VBL.Automation.GetAllDataRow_Flag.getAllDataRow'(file_name, sheet_name, 
    'Retailer')

for (int i = 0; i < Retailer.size(); i++) {
    if (Retailer.get(i) != 'null') {
        println(Retailer.get(i))

        WebUI.waitForElementVisible(findTestObject('XXXXXXXXXXXX/Web Part/RetailerAttribute/Retailer_Filter_Field'), 100)

        WebUI.click(findTestObject('XXXXXXXXXXXX/Web Part/RetailerAttribute/Retailer_Filter_Field'), FailureHandling.OPTIONAL)

        GlobalVariable.label = Retailer.get(i)

        WebUI.setText(findTestObject('XXXXXXXXXXXX/Web Part/RetailerAttribute/Retailer_Filter_Field'), GlobalVariable.label)

        WebUI.waitForElementVisible(findTestObject('XXXXXXXXXXXX/Web Part/RetailerAttribute/Retailer_Edit_Icon'), 100)

        WebUI.click(findTestObject('XXXXXXXXXXXX/Web Part/RetailerAttribute/Retailer_Edit_Icon'))

        WebUI.waitForElementNotVisible(findTestObject('XXXXXXXXXXXX/Web Part/Re-usable/Page Load'), 100)

        WebUI.scrollToElement(findTestObject('XXXXXXXXXXXX/Web Part/RetailerAttribute/SchemeApplyOn'), 100)

        SchemeApplyNo = WebUI.getAttribute(findTestObject('XXXXXXXXXXXX/Web Part/RetailerAttribute/SchemeApplyOn'), 'value')

        //		if (SchemeApplyType1 == 'FIXED_HT') {
        //			GlobalVariable.label1 = SchemeApplyType1.replaceAll('FIXED_HT', 'Fixed' //println()
        //				)
        //		} else if (SchemeApplyType1 == 'Fixed_DD') {
        //			GlobalVariable.label1 = SchemeApplyType1.replaceAll('Fixed_DD', 'Fixed')
        //		} else {
        //			GlobalVariable.label1 = SchemeApplyType1.replaceAll('\\s', '')
        //		}
        WebUI.click(findTestObject('XXXXXXXXXXXX/Web Part/RetailerAttribute/Cloase_Btn'))

        exlpath = 'DDF\\VBL\\Mobile Input data\\Mobile Input data.xlsx'

        Sheetname = 'RetailerTypeCreditNote_Scheme'

        workbook01 = ExcelKeywords.getWorkbook(exlpath)

        sheet1 = ExcelKeywords.getExcelSheet(workbook01, Sheetname)

        ExcelKeywords.setValueToCellByIndex(sheet1, i + 1, 2, SchemeApplyNo)

        ExcelKeywords.saveWorkbook(exlpath, workbook01)
    }
}

