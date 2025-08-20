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
import org.openqa.selenium.chrome.ChromeDriver as ChromeDriver
import com.kms.katalon.keyword.excel.ExcelKeywords as ExcelKeywords
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import org.junit.Assert as Assert
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException
 


'In the stock allocation screen, SKU batch wise case and piece qty should be same as the Stock summary screen.'
Mobile.callTestCase(findTestCase('Product_Mobile/Common/Web Login and Navigation/DistributorLogin'), [:], FailureHandling.STOP_ON_FAILURE)

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Web Login and Navigation/Navigate to Stock Summary'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Web Part/StockSummary/Dropdown_Warehouse'))

GlobalVariable.label = findTestData('Mobile Input Data/VanLoad').getValue('Warehouse', 1)

WebUI.click(findTestObject('Web Part/Stock Allocation/Global Variables/DropdownSelect_Li Tag'))

WebUI.waitForElementNotVisible(findTestObject('Web Part/Re-usable/Page Load'), 50, FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Web Part/StockSummary/Dropdown_Store'))

GlobalVariable.label = findTestData('Mobile Input Data/VanLoad').getValue('Store', 1)

WebUI.click(findTestObject('Web Part/Stock Allocation/Global Variables/DropdownSelect_Li Tag'))

WebUI.waitForElementNotVisible(findTestObject('Web Part/Re-usable/Page Load'), 50, FailureHandling.STOP_ON_FAILURE)

sku = findTestData('Mobile Input Data/VanLoad')

SKU_Count = sku.getRowNumbers()

ArrayList<String> CaseValues = new ArrayList<String>()

ArrayList<String> PieceValues = new ArrayList<String>()

'Get SKU batch wise case piece qty from stock summary'
for (i = 1; i < 7; i++) {
    GlobalVariable.ProductName = findTestData('Mobile Input Data/VanLoad').getValue('Sku_Name', i)

    GlobalVariable.BatchName = findTestData('Mobile Input Data/VanLoad').getValue('Batch_Name', i)

    WebUI.scrollToElement(findTestObject('Web Part/Stock Allocation/Filter/Filter_Icon'), 0)

    WebUI.click(findTestObject('Web Part/Stock Allocation/Filter/Filter_Icon'))

    WebUI.click(findTestObject('Web Part/Stock Allocation/Filter/Batch_Name'))

    WebUI.clearText(findTestObject('Web Part/Stock Allocation/Filter/Batch_Name'), FailureHandling.OPTIONAL)

    WebUI.setText(findTestObject('Web Part/Stock Allocation/Filter/Batch_Name'), GlobalVariable.BatchName)

    WebUI.click(findTestObject('Web Part/Stock Allocation/Filter/Appy_Btn'))

    WebUI.takeScreenshot()

    case_Value = WebUI.getText(findTestObject('Web Part/StockSummary/Grid_Case_Value'))

    piece_Value = WebUI.getText(findTestObject('Web Part/StockSummary/Grid_Piece_Value'))

    CaseValues.add(case_Value)

    PieceValues.add(piece_Value)
}

WebUI.callTestCase(findTestCase('Product_Mobile/Common/Web Login and Navigation/Navigate to StockAllocation'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Web Part/Stock Allocation/Drop down values/Ware house'))

GlobalVariable.label = findTestData('Mobile Input Data/VanLoad').getValue('Warehouse', 1)

WebUI.click(findTestObject('Web Part/Stock Allocation/Global Variables/DropdownSelect_Li Tag'))

WebUI.waitForElementNotVisible(findTestObject('Web Part/Re-usable/Page Load'), 50, FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Web Part/Stock Allocation/Drop down values/Store'))

GlobalVariable.label = findTestData('Mobile Input Data/VanLoad').getValue('Store', 1)

WebUI.click(findTestObject('Web Part/Stock Allocation/Global Variables/DropdownSelect_Li Tag'))

WebUI.waitForElementNotVisible(findTestObject('Web Part/Re-usable/Page Load'), 50, FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Web Part/Stock Allocation/Sku(hyperlink)Field values/User Details_Title'))

GlobalVariable.label = findTestData('Mobile Input Data/VanLoad').getValue('User_Details', 1)

WebUI.click(findTestObject('Web Part/Stock Allocation/Global Variables/UserSelect_Span b Tag'))

WebUI.waitForElementNotVisible(findTestObject('Web Part/Re-usable/Page Load'), 50, FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Web Part/Stock Allocation/Button/Manual_btn'))

WebUI.waitForElementNotVisible(findTestObject('Web Part/Re-usable/Page Load'), 50, FailureHandling.STOP_ON_FAILURE)

for (i = 1; i <7; i++) {
    GlobalVariable.ProductName = findTestData('Mobile Input Data/VanLoad').getValue('Sku_Name', i)

    GlobalVariable.BatchName = findTestData('Mobile Input Data/VanLoad').getValue('Batch_Name', i)

    WebUI.scrollToElement(findTestObject('Web Part/Stock Allocation/Filter/Filter_Icon'), 0)

    WebUI.click(findTestObject('Web Part/Stock Allocation/Filter/Filter_Icon'))

    WebUI.click(findTestObject('Web Part/Stock Allocation/Filter/Batch_Name'))

    WebUI.clearText(findTestObject('Web Part/Stock Allocation/Filter/Batch_Name'), FailureHandling.OPTIONAL)

    WebUI.setText(findTestObject('Web Part/Stock Allocation/Filter/Batch_Name'), GlobalVariable.BatchName)

    WebUI.click(findTestObject('Web Part/Stock Allocation/Filter/Appy_Btn'))

    WebUI.takeScreenshot()

    'Verified -In the stock allocation create screen, "SKU name" is same as the Stock summary screen'
    GlobalVariable.label = GlobalVariable.ProductName

    WebUI.verifyElementVisible(findTestObject('Web Part/Asset Master/td tag_grid data'), FailureHandling.STOP_ON_FAILURE)

    'Verified - In the stock allocation create screen, "SKU Batch Name" is same as the Stock summary screen'
    GlobalVariable.label = GlobalVariable.BatchName

    WebUI.verifyElementVisible(findTestObject('Web Part/Asset Master/td tag_grid data'), FailureHandling.STOP_ON_FAILURE)
	
	'Verified - In the stock allocation create screen, "SKU Batch EXP.Date" is same as the Batch table'
	
	conn = (((((('jdbc:sqlserver://' + GlobalVariable.DB_URL) + ';databaseName=') + GlobalVariable.DB_Name) + ';user=') + GlobalVariable.DB_Username) + ';password=') + GlobalVariable.DB_Password
	
	connection = DriverManager.getConnection(conn)
	
	Statement stm = connection.createStatement()
	
	queryString = 'select * from adm_product_batch where apb_Code =\'' + GlobalVariable.BatchName + '\'  and APB_Isactive = 1'
	
	KeywordUtil.logInfo(queryString)
	
	ResultSet recordSet = stm.executeQuery(queryString)
	
	if (recordSet.next()) {
		String APB_Expiry_Date = recordSet.getObject('APB_Expiry_Date')
		
		String dateStr = APB_Expiry_Date.split(" ")[0]
		
		String Actual_Expiry_Date = WebUI.getText(findTestObject('Web Part/StockSummary/Grid_Expiry_Date'))

        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy-MM-dd");  
 
        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("dd-MMM-yyyy");  

		LocalDate date1 = LocalDate.parse(dateStr, formatter1);
		
		KeywordUtil.logInfo(date1.toString())
		
		LocalDate date2 = LocalDate.parse(Actual_Expiry_Date, formatter2);
		
		KeywordUtil.logInfo(date2.toString())
		
		SameDate = date1.equals(date2)
		
		KeywordUtil.logInfo(SameDate.toString())
		
		Assert.assertTrue(SameDate)
      
	}
	else
	{
	   'If data is empty in the DB, then the flow must fail'
		Assert.fail()
	}

    'Verified- In the stock allocation create screen, "SKU batch wise case quantity" is same as the Stock summary screen'
    Actual_case_Value = WebUI.getText(findTestObject('Web Part/StockSummary/Grid_Case_Value'))

    Expected_case_Value = CaseValues.get(i - 1)

    WebUI.verifyMatch(Actual_case_Value, Expected_case_Value, false, FailureHandling.STOP_ON_FAILURE)

    'Verified- In the stock allocation create screen, "SKU batch wise piece quantity" is same as the Stock summary screen'
    Actual_piece_Value = WebUI.getText(findTestObject('Web Part/StockSummary/Grid_Piece_Value'))

    Expected_piece_Value = PieceValues.get(i - 1)

    WebUI.verifyMatch(Actual_piece_Value, Expected_piece_Value, false, FailureHandling.STOP_ON_FAILURE)

    CaseQty = findTestData('Mobile Input Data/VanLoad').getValue('Case_Qty', i)

    WebUI.sendKeys(findTestObject('Web Part/Stock Allocation/Global Variables/EnterCaseValue_Td Tag'), CaseQty)

    PieceQty = findTestData('Mobile Input Data/VanLoad').getValue('Piece_Qty', i)

    WebUI.sendKeys(findTestObject('Web Part/Stock Allocation/Global Variables/EnterPieceValue_Td Tag'), PieceQty)
}

'Manual data added in Stock allocation Successfully!!'
WebUI.click(findTestObject('Web Part/Stock Allocation/Button/Allocate_btn'))

WebUI.takeScreenshot()

WebUI.verifyElementPresent(findTestObject('Web Part/Stock Allocation/Button/Manual_btn'), 10)

WebUI.closeBrowser()

WebUI.delay(3)

'To verify whether allocated stock details inserted into the Vanload_Header  and Vanload_Details table'

//1st DB
queryString = 'select top 1 * from AppData_Van_Load_Header where VLH_AUH_Id =\'' + GlobalVariable.vanseller_user_id + '\' order by 1 desc'

KeywordUtil.logInfo(queryString)

String conn = (((((('jdbc:sqlserver://' + GlobalVariable.DB_URL) + ';databaseName=') + GlobalVariable.DB_Name) + ';user=') + GlobalVariable.DB_Username) + ';password=') + GlobalVariable.DB_Password

connection = DriverManager.getConnection(conn)

Statement stm = connection.createStatement()

ResultSet rs = stm.executeQuery(queryString)

def recordSet = rs

VLH_Id = 0

if (recordSet.next()) {
	Object data1 = recordSet.getObject('VLH_Reference_No')

	GlobalVariable.VanLoad_No = data1

	KeywordUtil.logInfo('Van Load Reference no  :  ' + GlobalVariable.VanLoad_No )
	
	VLH_Id = recordSet.getObject('VLH_Id')

}
else
	{
	   'If data is empty in the DB, then the flow must fail'
		Assert.fail()
	}

queryString = (('Select * from appdata_van_load_detail where VLD_VLH_Id =\'' + VLH_Id) + '\'')

KeywordUtil.logInfo(queryString)

ResultSet recordSet2 = stm.executeQuery(queryString)

j = 1

if (recordSet2.next()) {
    VLD_APH_Id = recordSet2.getObject('VLD_APH_Id')
	
	KeywordUtil.logInfo(VLD_APH_Id.toString())
	
	VLD_APB_Id = recordSet2.getObject('VLD_APB_Id')
	
	KeywordUtil.logInfo(VLD_APB_Id.toString())
	
	Statement stm2 = connection.createStatement()
	
	
	queryString = (('select * from adm_product_hierarchy where aph_id=\'' + VLD_APH_Id) + '\'')
	
	KeywordUtil.logInfo(queryString)
	
	ResultSet recordSet5 = stm2.executeQuery(queryString)
	
	if (recordSet5.next()) {
		APH_Name = recordSet5.getObject('APH_Name')
	
		KeywordUtil.logInfo(APH_Name)
		
		Sku_Name = findTestData('Mobile Input Data/VanLoad').getValue('Sku_Name', j)
		
		'Verified - "SKU Name" is properly reflected in the Vanload_Details table. '
		
		WebUI.verifyMatch(Sku_Name, APH_Name, false, FailureHandling.STOP_ON_FAILURE)
	}
	else
		{
		   'If data is empty in the DB, then the flow must fail'
			Assert.fail()
		}
	//4nd DB
	queryString = (('select * from adm_product_batch where apb_id=\'' + VLD_APB_Id) + '\'')
	
	KeywordUtil.logInfo(queryString)
	
	ResultSet recordSet4 = stm2.executeQuery(queryString)
	
	if (recordSet4.next()) {
		APB_Code = recordSet4.getObject('APB_Code')
	
		KeywordUtil.logInfo(APB_Code)
		
		Batch_Name = findTestData('Mobile Input Data/VanLoad').getValue('Batch_Name', j)
		
		'Verified - "SKU Bstch Name" is properly reflected in the Vanload_Details table. '
		
		WebUI.verifyMatch(Batch_Name, APB_Code, false, FailureHandling.STOP_ON_FAILURE)
	}
	else
		{
		   'If data is empty in the DB, then the flow must fail'
			Assert.fail()
		}
	
	
	VLD_Qty = recordSet2.getObject('VLD_Qty')
	
	KeywordUtil.logInfo(VLD_Qty.toString())
	
	CaseQty = findTestData('Mobile Input Data/VanLoad').getValue('Case_Qty', j)
	
	Case_Size = findTestData('Mobile Input Data/VanLoad').getValue('Case_Size', j)
	
	PieceQty = findTestData('Mobile Input Data/VanLoad').getValue('Piece_Qty', j)
	
	SIH = (Integer.parseInt(CaseQty) * Integer.parseInt(Case_Size)) + Integer.parseInt(PieceQty)
	
	'Verified - "SKU Batch-wise Qty" is properly reflected in the Vanload_Details table. '
	
	WebUI.verifyEqual(VLD_Qty, SIH, FailureHandling.STOP_ON_FAILURE)
	
	j++
		
}
else
	{
	   'If data is empty in the DB, then the flow must fail'
		Assert.fail()
	}

'Update Van Reference Number in the sheet for further validation'

String exlpath = 'DDF/Mobile Input data/Mobile Input data.xlsx'

String Sheetname = 'VanLoad'

workbook01 = ExcelKeywords.getWorkbook(exlpath)

sheet1 = ExcelKeywords.getExcelSheet(workbook01, Sheetname)

ExcelKeywords.setValueToCellByIndex(sheet1, 1, 10, GlobalVariable.VanLoad_No)

ExcelKeywords.saveWorkbook(exlpath, workbook01)


