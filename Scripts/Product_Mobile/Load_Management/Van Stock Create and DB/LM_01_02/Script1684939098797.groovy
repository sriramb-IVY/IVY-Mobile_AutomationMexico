import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import java.sql.Connection as Connection
import java.sql.DriverManager as DriverManager
import java.sql.ResultSet as ResultSet
import java.sql.Statement as Statement
import org.openqa.selenium.By as By
import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.WebElement as WebElement
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable
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
import org.junit.Assert as Assert


WebUI.callTestCase(findTestCase('Product_Mobile/Common/Web Login and Navigation/DistributorLogin'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('Product_Mobile/Common/Web Login and Navigation/Navigate to StockAllocation'), [:], FailureHandling.STOP_ON_FAILURE)

String sheet_name = 'Stock allocation'

String file_name = 'Web Input Data'

ArrayList<String> Warehouse = CustomKeywords.'poi.Automation.GetAllWebDataRow_Flag.getAllDataRow'(file_name, sheet_name,
		'Warehouse')

ArrayList<String> Store = CustomKeywords.'poi.Automation.GetAllWebDataRow_Flag.getAllDataRow'(file_name, sheet_name,
		'Store')

ArrayList<String> User_Details = CustomKeywords.'poi.Automation.GetAllWebDataRow_Flag.getAllDataRow'(file_name, sheet_name,
		'User_Details')

ArrayList<String> case_Quantity = CustomKeywords.'poi.Automation.GetAllWebDataRow_Flag.getAllDataRow'(file_name, sheet_name,
		'case_Quantity')

ArrayList<String> Piece_Quantity = CustomKeywords.'poi.Automation.GetAllWebDataRow_Flag.getAllDataRow'(file_name, sheet_name,
		'Piece_Quantity')

ArrayList<String> SKU_Name = CustomKeywords.'poi.Automation.GetAllWebDataRow_Flag.getAllDataRow'(file_name, sheet_name,
		'SKU_Name')

for (int i = 0; i < Warehouse.size(); i++) {
	if (Warehouse.get(i) != 'NULL') {
		WebUI.click(findTestObject('Web Part/Stock Allocation/Drop down values/Ware house'))

		GlobalVariable.label = Warehouse.get(i)

		WebUI.click(findTestObject('Web Part/Stock Allocation/Global Variables/DropdownSelect_Li Tag'))

		WebUI.waitForElementNotVisible(findTestObject('Web Part/Re-usable/Page Load'), 50, FailureHandling.STOP_ON_FAILURE)

		WebUI.click(findTestObject('Web Part/Stock Allocation/Drop down values/Store'))

		GlobalVariable.label = Store.get(i)

		WebUI.click(findTestObject('Web Part/Stock Allocation/Global Variables/DropdownSelect_Li Tag'))

		WebUI.waitForElementNotVisible(findTestObject('Web Part/Re-usable/Page Load'), 50, FailureHandling.STOP_ON_FAILURE)

		WebUI.click(findTestObject('Web Part/Stock Allocation/Sku(hyperlink)Field values/User Details_Title'))

		GlobalVariable.label = User_Details.get(i)

		println(GlobalVariable.label)

		//CustomKeywords.'poi.Automation.BrowserZoomControl.zoomOut'()

		WebUI.scrollToElement(findTestObject('Web Part/Stock Allocation/Global Variables/UserSelect_Span b Tag'), 1, FailureHandling.OPTIONAL)

		WebUI.click(findTestObject('Web Part/Stock Allocation/Global Variables/UserSelect_Span b Tag'))

		WebUI.waitForElementNotVisible(findTestObject('Web Part/Re-usable/Page Load'), 50, FailureHandling.STOP_ON_FAILURE)

		WebUI.click(findTestObject('Web Part/Stock Allocation/Button/Manual_btn'))

		WebUI.waitForElementNotVisible(findTestObject('Web Part/Re-usable/Page Load'), 50, FailureHandling.STOP_ON_FAILURE)

		for (j = 0; j < SKU_Name.size(); j++) {
			GlobalVariable.ProductName = SKU_Name.get(j)

			GlobalVariable.label = SKU_Name.get(j)

			WebUI.verifyElementVisible(findTestObject('Web Part/Stock Allocation/Filter/Filter_Icon'), FailureHandling.STOP_ON_FAILURE)

			WebUI.click(findTestObject('Web Part/Stock Allocation/Filter/Filter_Icon'))

			WebUI.click(findTestObject('Web Part/Stock Allocation/Filter/Sku_Name'))

			WebUI.clearText(findTestObject('Web Part/Stock Allocation/Filter/Sku_Name'), FailureHandling.OPTIONAL)

			WebUI.setText(findTestObject('Web Part/Stock Allocation/Filter/Sku_Name'), GlobalVariable.ProductName)

			WebUI.scrollToElement(findTestObject('Web Part/Stock Allocation/Filter/Appy_Btn'), 1, FailureHandling.OPTIONAL)

			WebUI.click(findTestObject('Web Part/Stock Allocation/Filter/Appy_Btn'))

			WebUI.verifyElementPresent(findTestObject('Web Part/Stock Allocation/Global Variables/EnterCaseValue_Td Tag'),
					5, FailureHandling.STOP_ON_FAILURE)

			GlobalVariable.CaseQty = case_Quantity.get(j)

			WebUI.sendKeys(findTestObject('Web Part/Stock Allocation/Global Variables/EnterCaseValue_Td Tag'),
					GlobalVariable.CaseQty)

			WebUI.waitForElementNotVisible(findTestObject('Web Part/Re-usable/Page Load'), 50, FailureHandling.STOP_ON_FAILURE)

			GlobalVariable.PieceQty = Piece_Quantity.get(j)

			WebUI.sendKeys(findTestObject('Web Part/Stock Allocation/Global Variables/EnterPieceValue_Td Tag'),
					GlobalVariable.PieceQty)

			WebUI.waitForElementNotVisible(findTestObject('Web Part/Re-usable/Page Load'), 50, FailureHandling.STOP_ON_FAILURE)
		}

		WebUI.click(findTestObject('Web Part/Stock Allocation/Button/Allocate_btn'))

		WebUI.verifyElementPresent(findTestObject('Web Part/Stock Allocation/Button/Manual_btn'), 10)

		println('Manual data added in Stock allocation Successfully!!')
	}
}

WebUI.closeBrowser()

WebUI.delay(3)

//1st DB



Mobile.callTestCase(findTestCase('Product_Mobile/Common/DB'), [('Query') : 'select top 1 * from AppData_Van_Load_Header where VLH_AUH_Id = 1589 order by 1 desc', ('columnNames') : ['VLH_Reference_No']], FailureHandling.STOP_ON_FAILURE)

String VLH_Reference_No=GlobalVariable.data[0]

KeywordUtil.logInfo('Reference No : '+VLH_Reference_No)


GlobalVariable.VanLoad_No = VLH_Reference_No

KeywordUtil.logInfo('Van Load Reference no  :  ' + GlobalVariable.VanLoad_No )


Mobile.callTestCase(findTestCase('Product_Mobile/Common/DB'), [('Query') : 'Select * from appdata_van_load_header where vlh_reference_no=\'' + GlobalVariable.VanLoad_No + '\'', ('columnNames') : ['VLH_Id']], FailureHandling.STOP_ON_FAILURE)

String VLH_Id=GlobalVariable.data[0]

KeywordUtil.logInfo('Header No : '+VLH_Id)

GlobalVariable.label1 = VLH_Id

KeywordUtil.logInfo('VLH id  :  ' + GlobalVariable.label1 )

//3nd DB


Mobile.callTestCase(findTestCase('Product_Mobile/Common/DB'), [('Query') : 'Select top 1 * from appdata_van_load_detail where VLD_VLH_Id =\'' + GlobalVariable.label1 + '\'', ('columnNames') : ['VLD_Qty','VLD_APH_Id']], FailureHandling.STOP_ON_FAILURE)

String Quantity=GlobalVariable.data[0]

KeywordUtil.logInfo('Quantity  : '+Quantity)

GlobalVariable.PieceQty = Quantity

KeywordUtil.logInfo('Total piece Qty  :  ' + GlobalVariable.PieceQty )

String SKUid=GlobalVariable.data[1]

GlobalVariable.label2 = SKUid

//3nd DB

Mobile.callTestCase(findTestCase('Product_Mobile/Common/DB'), [('Query') : 'select * from adm_product_hierarchy where aph_id=\'' + GlobalVariable.label2 + '\' order by 1 desc', ('columnNames') : ['APH_Code']], FailureHandling.STOP_ON_FAILURE)

String SKUName=GlobalVariable.data[0]

KeywordUtil.logInfo('SKU Name  : '+ SKUName)

GlobalVariable.ProductName = SKUName

KeywordUtil.logInfo('Allocated product name  :  ' + GlobalVariable.ProductName)


String exlpath = 'DDF\\Mobile Input data\\Mobile Input data.xlsx'

String Sheetname = 'VanLoad'

workbook01 = ExcelKeywords.getWorkbook(exlpath)

sheet1 = ExcelKeywords.getExcelSheet(workbook01, Sheetname)

ExcelKeywords.setValueToCellByIndex(sheet1, 1, 0, GlobalVariable. ProductName)

ExcelKeywords.setValueToCellByIndex(sheet1, 1, 1, GlobalVariable.PieceQty)

ExcelKeywords.setValueToCellByIndex(sheet1, 1, 2, GlobalVariable.VanLoad_No)

ExcelKeywords.saveWorkbook(exlpath, workbook01)
