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

not_run: Mobile.callTestCase(findTestCase('VBL_Mobile/Re-Usables/Web Login and Navigation/AdminLogin'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('Product_Mobile/Common/Web Login and Navigation/DivisionLogin'), [:], FailureHandling.STOP_ON_FAILURE)

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Web Login and Navigation/Navigate to Product Hierarchy'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.waitForElementNotVisible(findTestObject('XXXXXXXXXXXX/Web Part/Re-usable/Page Load'), 100)

WebUI.click(findTestObject('XXXXXXXXXXXX/Web Part/Product_Hierarchy/SKU_SubMenu'))

WebUI.waitForElementNotVisible(findTestObject('XXXXXXXXXXXX/Web Part/Re-usable/Page Load'), 100)

sheet_name = 'OTP_Automation_Scheme_CMB'

file_name = 'Mobile Input data'

ArrayList<String> BuyProduct1 = CustomKeywords.'poi.Automation.GetAllDataRow_Flag.getAllDataRow'(file_name, sheet_name, 'BuyProduct1')

ArrayList<String> BuyProduct2 = CustomKeywords.'poi.Automation.GetAllDataRow_Flag.getAllDataRow'(file_name, sheet_name, 'BuyProduct2')

for (int a = 0; a < BuyProduct1.size(); a++) {
    if (BuyProduct1.get(a) != 'null') {
        GlobalVariable.ProductName = BuyProduct1.get(a)

        WebUI.waitForElementVisible(findTestObject('XXXXXXXXXXXX/Web Part/Product_Hierarchy/Sku_Filter_Field'), 50, FailureHandling.STOP_ON_FAILURE)

        WebUI.setText(findTestObject('XXXXXXXXXXXX/Web Part/Product_Hierarchy/Sku_Filter_Field'), GlobalVariable.ProductName)

        WebUI.waitForElementNotVisible(findTestObject('XXXXXXXXXXXX/Web Part/Re-usable/Page Load'), 100)

        GlobalVariable.label = GlobalVariable.ProductName

        WebUI.delay(2)

        WebUI.click(findTestObject('XXXXXXXXXXXX/Web Part/TaxScreen/Global Object/td_Tag(GridSkuRow)'))

        WebUI.click(findTestObject('XXXXXXXXXXXX/Web Part/Product_Hierarchy/Edit_Btn'))

        WebUI.waitForElementNotVisible(findTestObject('XXXXXXXXXXXX/Web Part/Re-usable/Page Load'), 100)

        WebUI.scrollToElement(findTestObject('Object Repository/Web Part/Product_Hierarchy/Piece_MRP_Value'), 0)

        Product_MRP = WebUI.getText(findTestObject('Object Repository/Web Part/Product_Hierarchy/Piece_MRP_Value'))

		GlobalVariable.MRP = Product_MRP.replace('$','')
		
        KeywordUtil.logInfo('Product_MRP : ' + GlobalVariable.MRP)

        WebUI.click(findTestObject('XXXXXXXXXXXX/Web Part/Product_Hierarchy/X_btn'))

        String exlpath = 'DDF\\Mobile Input data\\Mobile Input data.xlsx'

        String Sheetname = 'OTP_Automation_Scheme_CMB'

        workbook01 = ExcelKeywords.getWorkbook(exlpath)

        sheet1 = ExcelKeywords.getExcelSheet(workbook01, Sheetname)

        //ExcelKeywords.setValueToCellByIndex(sheet1, 1, 1, GlobalVariable.ProductName)
        ExcelKeywords.setValueToCellByIndex(sheet1, a + 1, 6, GlobalVariable.MRP)

        ExcelKeywords.saveWorkbook(exlpath, workbook01)
    }
}

for (int b = 0; b < BuyProduct2.size(); b++) {
    if (BuyProduct2.get(b) != 'null') {
        GlobalVariable.ProductName = BuyProduct2.get(b)

        WebUI.waitForElementVisible(findTestObject('XXXXXXXXXXXX/Web Part/Product_Hierarchy/Sku_Filter_Field'), 50, FailureHandling.STOP_ON_FAILURE)

        WebUI.setText(findTestObject('XXXXXXXXXXXX/Web Part/Product_Hierarchy/Sku_Filter_Field'), GlobalVariable.ProductName)

        WebUI.waitForElementNotVisible(findTestObject('XXXXXXXXXXXX/Web Part/Re-usable/Page Load'), 100)

        WebUI.delay(2)

        GlobalVariable.label = GlobalVariable.ProductName

        WebUI.click(findTestObject('XXXXXXXXXXXX/Web Part/TaxScreen/Global Object/td_Tag(GridSkuRow)'))

        WebUI.click(findTestObject('XXXXXXXXXXXX/Web Part/Product_Hierarchy/Edit_Btn'))

        WebUI.waitForElementNotVisible(findTestObject('XXXXXXXXXXXX/Web Part/Re-usable/Page Load'), 100)

        WebUI.scrollToElement(findTestObject('Object Repository/Web Part/Product_Hierarchy/Piece_MRP_Value'), 0)

        GlobalVariable.MRP = WebUI.getText(findTestObject('Object Repository/Web Part/Product_Hierarchy/Piece_MRP_Value'))

		String Product_MRP = GlobalVariable.MRP
		
		GlobalVariable.MRP = Product_MRP.replace('$',"")
		
        KeywordUtil.logInfo('Product_MRP : ' + GlobalVariable.MRP)

        WebUI.click(findTestObject('XXXXXXXXXXXX/Web Part/Product_Hierarchy/X_btn'))

        String exlpath = 'DDF\\Mobile Input data\\Mobile Input data.xlsx'

        String Sheetname = 'OTP_Automation_Scheme_CMB'

        workbook01 = ExcelKeywords.getWorkbook(exlpath)

        sheet1 = ExcelKeywords.getExcelSheet(workbook01, Sheetname)

        //ExcelKeywords.setValueToCellByIndex(sheet1, 1, 1, GlobalVariable.ProductName)
        ExcelKeywords.setValueToCellByIndex(sheet1, b + 1, 8, GlobalVariable.MRP)

        ExcelKeywords.saveWorkbook(exlpath, workbook01)
    }
}

WebUI.closeBrowser()

