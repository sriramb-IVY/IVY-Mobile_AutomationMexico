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
import org.testng.Assert;

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Web Login and Navigation/DivisionLogin'), [:], FailureHandling.STOP_ON_FAILURE)

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Web Login and Navigation/Navigate to Product Hierarchy'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.waitForElementNotVisible(findTestObject('Web Part/Re-usable/Page Load'), 100)

WebUI.click(findTestObject('Web Part/Product_Hierarchy/SKU_SubMenu'))

WebUI.waitForElementNotVisible(findTestObject('Web Part/Re-usable/Page Load'), 100)

sheet_name = 'Empty_Return'

file_name = 'Mobile Input data'

ArrayList<String> SKU_Name = CustomKeywords.'poi.Automation.GetAllDataRow_Flag.getAllDataRow'(file_name, sheet_name, 'Salable_SKU_Name')

for (int a = 0; a < SKU_Name.size(); a++) {
    if (SKU_Name.get(a) != 'null') {
        GlobalVariable.ProductName = SKU_Name.get(a)

        WebUI.waitForElementVisible(findTestObject('Web Part/Product_Hierarchy/Sku_Filter_Field'), 50, FailureHandling.STOP_ON_FAILURE)

        WebUI.setText(findTestObject('Web Part/Product_Hierarchy/Sku_Filter_Field'), GlobalVariable.ProductName)

        WebUI.waitForElementNotVisible(findTestObject('Web Part/Re-usable/Page Load'), 100)

        GlobalVariable.label = GlobalVariable.ProductName

        WebUI.delay(1)

        WebUI.click(findTestObject('Web Part/TaxScreen/Global Object/td_Tag(GridSkuRow)'))

        WebUI.delay(1)

        WebUI.click(findTestObject('Web Part/Product_Hierarchy/Edit_Btn'))

        WebUI.waitForElementNotVisible(findTestObject('Web Part/Re-usable/Page Load'), 100)

        WebUI.scrollToElement(findTestObject('Web Part/Product_Hierarchy/Case_ConversionQty'), 0)

        GlobalVariable.CaseSize = WebUI.getText(findTestObject('Web Part/Product_Hierarchy/Case_ConversionQty'))

        KeywordUtil.logInfo('CoversionQty_Value : ' + GlobalVariable.CaseSize)

        BasePrice = WebUI.getText(findTestObject('Web Part/Product_Hierarchy/Piece_DP_Value'))

        GlobalVariable.BasePrice = BasePrice.replace('$ ', '')

        KeywordUtil.logInfo('BasePrice_Value (DP) : ' + GlobalVariable.BasePrice)

        PiecePrice = WebUI.getText(findTestObject('Web Part/Product_Hierarchy/Piece_RP_Value'))

        GlobalVariable.PiecePrice = PiecePrice.replace('$ ', '')

        KeywordUtil.logInfo('PiecePrice_Value (RP) : ' + GlobalVariable.PiecePrice)

        WebUI.click(findTestObject('Web Part/Product_Hierarchy/X_btn'))

        String exlpath = 'DDF\\Mobile Input data\\Mobile Input data.xlsx'

        String Sheetname = 'Empty_Return'

        workbook01 = ExcelKeywords.getWorkbook(exlpath)

        sheet1 = ExcelKeywords.getExcelSheet(workbook01, Sheetname)

        //ExcelKeywords.setValueToCellByIndex(sheet1, 1, 1, GlobalVariable.ProductName)
        ExcelKeywords.setValueToCellByIndex(sheet1, a + 1, 10, GlobalVariable.BasePrice)

        //ExcelKeywords.setValueToCellByIndex(sheet1, a+1, 3, GlobalVariable.PiecePrice)
        ExcelKeywords.setValueToCellByIndex(sheet1, a + 1, 9, GlobalVariable.CaseSize)

        //ExcelKeywords.setValueToCellByIndex(sheet1, 1, 6, GlobalVariable.CGST_Tax)
        ExcelKeywords.saveWorkbook(exlpath, workbook01)
    }
}

WebUI.refresh()

//--------------------------
Mobile.callTestCase(findTestCase('Product_Mobile/Common/Web Login and Navigation/Navigate to Product Hierarchy'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.waitForElementNotVisible(findTestObject('Web Part/Re-usable/Page Load'), 100)

WebUI.click(findTestObject('Web Part/Product_Hierarchy/SKU_SubMenu'))

WebUI.waitForElementNotVisible(findTestObject('Web Part/Re-usable/Page Load'), 100)

sheet_name = 'Empty_Return'

file_name = 'Mobile Input data'


ArrayList<String> Product_Name = CustomKeywords.'poi.Automation.GetAllDataRow_Flag.getAllDataRow'(file_name, sheet_name, 'SKU_Name')

for (int a = 0; a < Product_Name.size(); a++) {
    if (Product_Name.get(a) != 'null') {
        GlobalVariable.ProductName = Product_Name.get(a)

        WebUI.waitForElementVisible(findTestObject('Web Part/Product_Hierarchy/Sku_Filter_Field'), 50, FailureHandling.STOP_ON_FAILURE)

        WebUI.setText(findTestObject('Web Part/Product_Hierarchy/Sku_Filter_Field'), GlobalVariable.ProductName)

        WebUI.waitForElementNotVisible(findTestObject('Web Part/Re-usable/Page Load'), 100)

        GlobalVariable.label = GlobalVariable.ProductName

        WebUI.delay(1)

        WebUI.click(findTestObject('Web Part/TaxScreen/Global Object/td_Tag(GridSkuRow)'))

        WebUI.delay(1)

        WebUI.click(findTestObject('Web Part/Product_Hierarchy/Edit_Btn'))

        WebUI.waitForElementNotVisible(findTestObject('Web Part/Re-usable/Page Load'), 100)

        Returnable_cb=WebUI.getAttribute(findTestObject('Mobile/Web Part/Product_Hierarchy/Returnable_CB'), 'checked')
if(Returnable_cb=='true') {
	KeywordUtil.logInfo('SKU is returnable')
}
else {
	
	Assert.fail()
	KeywordUtil.logInfo('SKU is Non returnable')

}

        WebUI.click(findTestObject('Web Part/Product_Hierarchy/X_btn'))
    }
}

WebUI.closeBrowser()

