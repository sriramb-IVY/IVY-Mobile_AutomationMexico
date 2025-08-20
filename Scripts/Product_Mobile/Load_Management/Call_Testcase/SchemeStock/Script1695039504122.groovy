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

WebUI.callTestCase(findTestCase('Product_Mobile/Common/Web Login and Navigation/DistributorLogin'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('Product_Mobile/Common/Web Login and Navigation/Navigate to StockAllocation'), [:], 
    FailureHandling.STOP_ON_FAILURE)

String file_name = 'Web Input Data'

String sheet_name = 'Scheme_Stock_Allocation'

ArrayList<String> Warehouse = CustomKeywords.'poi.Automation.GetAllWebDataRow_Flag.getAllDataRow'(file_name, sheet_name, 
    'Warehouse')

ArrayList<String> Store = CustomKeywords.'poi.Automation.GetAllWebDataRow_Flag.getAllDataRow'(file_name, sheet_name, 'Store')

ArrayList<String> User_Details = CustomKeywords.'poi.Automation.GetAllWebDataRow_Flag.getAllDataRow'(file_name, sheet_name, 
    'User_Details')

ArrayList<String> case_Quantity = CustomKeywords.'poi.Automation.GetAllWebDataRow_Flag.getAllDataRow'(file_name, sheet_name, 
    'case_Quantity')

//ArrayList<String> Piece_Quantity = CustomKeywords.'poi.Automation.GetAllWebDataRow_Flag.getAllDataRow'(file_name, sheet_name, 
//    'Piece_Quantity')
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

            WebUI.click(findTestObject('Web Part/Stock Allocation/Filter/Appy_Btn'))

            WebUI.verifyElementPresent(findTestObject('Web Part/Stock Allocation/Global Variables/EnterCaseValue_Td Tag'), 
                2, FailureHandling.CONTINUE_ON_FAILURE)

            GlobalVariable.CaseQty = case_Quantity.get(j)

            WebUI.sendKeys(findTestObject('Web Part/Stock Allocation/Global Variables/EnterCaseValue_Td Tag'), 
                GlobalVariable.CaseQty, FailureHandling.CONTINUE_ON_FAILURE) //            WebUI.waitForElementNotVisible(findTestObject('Web Part/Re-usable/Page Load'), 50, FailureHandling.STOP_ON_FAILURE)
            //
            //            GlobalVariable.PieceQty = Piece_Quantity.get(j)
            //
            //            WebUI.sendKeys(findTestObject('Web Part/Stock Allocation/Global Variables/EnterPieceValue_Td Tag'), 
            //                GlobalVariable.PieceQty)
            //
            //            WebUI.waitForElementNotVisible(findTestObject('Web Part/Re-usable/Page Load'), 50, FailureHandling.STOP_ON_FAILURE)
        }
        
        WebUI.click(findTestObject('Web Part/Stock Allocation/Button/Allocate_btn'))

        WebUI.verifyElementPresent(findTestObject('Web Part/Stock Allocation/Button/Manual_btn'), 10)

        //VL_01 To verify user can able to put order in Stock allocation
        println('Manual data added in Stock allocation Successfully!!')
    }
}

WebUI.closeBrowser()

WebUI.delay(3)

//1st DB
Connection connection = null

url = findTestData('DB Credentials/Config1').getValue('URL', 1)

dbname = findTestData('DB Credentials/Config1').getValue('dbname', 1)

username = findTestData('DB Credentials/Config1').getValue('username', 1)

password = findTestData('DB Credentials/Config1').getValue('password', 1)

queryString = 'select top 1 * from AppData_Van_Load_Header where VLH_AUH_Id = 1589 order by 1 desc'

KeywordUtil.logInfo(queryString)

String conn = (((((('jdbc:sqlserver://' + url) + ';databaseName=') + dbname) + ';user=') + username) + ';password=') + password

connection = DriverManager.getConnection(conn)

Statement stm = connection.createStatement()

ResultSet rs = stm.executeQuery(queryString)

def recordSet = rs

while (recordSet.next()) {
    Object data1 = recordSet.getObject('VLH_Reference_No')

    GlobalVariable.VanLoad_No = data1

    KeywordUtil.logInfo('Van Load Reference no  :  ' + GlobalVariable.VanLoad_No)
}

//2nd DB
queryString1 = (('Select * from appdata_van_load_header where vlh_reference_no=\'' + GlobalVariable.VanLoad_No) + '\'')

KeywordUtil.logInfo(queryString1)

String conn1 = (((((('jdbc:sqlserver://' + url) + ';databaseName=') + dbname) + ';user=') + username) + ';password=') + 
password

connection1 = DriverManager.getConnection(conn1)

Statement stm1 = connection1.createStatement()

ResultSet rs1 = stm1.executeQuery(queryString1)

def recordSet1 = rs1

while (recordSet1.next()) {
    Object data2 = recordSet1.getObject('VLH_Id')

    GlobalVariable.label1 = data2

    KeywordUtil.logInfo('Vlh id  :  ' + GlobalVariable.label1)
}

//3nd DB
queryString2 = (('Select top 1 * from appdata_van_load_detail where VLD_VLH_Id =\'' + GlobalVariable.label1) + '\'')

KeywordUtil.logInfo(queryString2)

String conn2 = (((((('jdbc:sqlserver://' + url) + ';databaseName=') + dbname) + ';user=') + username) + ';password=') + 
password

connection2 = DriverManager.getConnection(conn2)

Statement stm2 = connection2.createStatement()

ResultSet rs2 = stm2.executeQuery(queryString2)

def recordSet2 = rs2

while (recordSet2.next()) {
    Object data3 = recordSet2.getObject('VLD_Qty')

    GlobalVariable.PieceQty = data3

    KeywordUtil.logInfo('Total piece Qty  :  ' + GlobalVariable.PieceQty)

    Object data4 = recordSet2.getObject('VLD_APH_Id')

    GlobalVariable.label2 = data4
}

//3nd DB
queryString3 = (('select * from adm_product_hierarchy where aph_id=\'' + GlobalVariable.label2) + '\' order by 1 desc')

KeywordUtil.logInfo(queryString3)

String conn3 = (((((('jdbc:sqlserver://' + url) + ';databaseName=') + dbname) + ';user=') + username) + ';password=') + 
password

connection3 = DriverManager.getConnection(conn3)

Statement stm3 = connection3.createStatement()

ResultSet rs3 = stm3.executeQuery(queryString3)

def recordSet3 = rs3

while (recordSet3.next()) {
    Object data5 = recordSet3.getObject('APH_Code')

    GlobalVariable.ProductName = data5

    KeywordUtil.logInfo('Allocated product name  :  ' + GlobalVariable.ProductName)
}

String exlpath = 'DDF\\Mobile Input data\\Mobile Input data.xlsx'

String Sheetname = 'VanLoad'

workbook01 = ExcelKeywords.getWorkbook(exlpath)

sheet1 = ExcelKeywords.getExcelSheet(workbook01, Sheetname)

ExcelKeywords.setValueToCellByIndex(sheet1, 1, 0, GlobalVariable.ProductName)

ExcelKeywords.setValueToCellByIndex(sheet1, 1, 1, GlobalVariable.PieceQty)

ExcelKeywords.setValueToCellByIndex(sheet1, 1, 2, GlobalVariable.VanLoad_No)

ExcelKeywords.saveWorkbook(exlpath, workbook01)

