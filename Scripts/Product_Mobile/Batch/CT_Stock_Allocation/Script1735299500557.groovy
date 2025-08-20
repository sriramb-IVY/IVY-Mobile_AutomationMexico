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
import java.time.LocalDate as LocalDate
import java.time.format.DateTimeFormatter as DateTimeFormatter
import java.time.format.DateTimeParseException as DateTimeParseException

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Web Login and Navigation/DistributorLogin'), [:], FailureHandling.STOP_ON_FAILURE)

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

sku = findTestData('Mobile Input Data/VanLoad')

SKU_Count = sku.getRowNumbers()

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

    GlobalVariable.label = GlobalVariable.BatchName

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

queryString = (('select top 1 * from AppData_Van_Load_Header where VLH_AUH_Id =\'' + GlobalVariable.vanseller_user_id) + 
'\' order by 1 desc')

KeywordUtil.logInfo(queryString)

String conn = (((((('jdbc:sqlserver://' + GlobalVariable.DB_URL) + ';databaseName=') + GlobalVariable.DB_Name) + ';user=') + 
GlobalVariable.DB_Username) + ';password=') + GlobalVariable.DB_Password

connection = DriverManager.getConnection(conn)

Statement stm = connection.createStatement()

ResultSet rs = stm.executeQuery(queryString)

def recordSet = rs

if (recordSet.next()) {
    Object data1 = recordSet.getObject('VLH_Reference_No')

    GlobalVariable.VanLoad_No = data1
} else {
    'If data is empty in the DB, then the flow must fail'
    Assert.fail()
}

'Update Van Reference Number in the sheet for further validation'
String exlpath = 'DDF\\Mobile Input data\\Mobile Input data.xlsx'

String Sheetname = 'VanLoad'

workbook01 = ExcelKeywords.getWorkbook(exlpath)

sheet1 = ExcelKeywords.getExcelSheet(workbook01, Sheetname)

ExcelKeywords.setValueToCellByIndex(sheet1, 1, 10, GlobalVariable.VanLoad_No)

ExcelKeywords.saveWorkbook(exlpath, workbook01)

