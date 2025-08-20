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

WebUI.callTestCase(findTestCase('VBL_Mobile/Re-Usables/Web Login and Navigation/ManjuLogin'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('VBL_Mobile/Re-Usables/Web Login and Navigation/Navigate to StockAdjustmentCreate'), [:], 
    FailureHandling.STOP_ON_FAILURE)

if (WebUI.waitForElementNotVisible(findTestObject('XXXXXXXXXXXX/Web Part/Stock Allocation/Button/Freeze_btn'), 3, FailureHandling.OPTIONAL)) {
    WebUI.waitForElementNotVisible(findTestObject('XXXXXXXXXXXX/Web Part/Re-usable/Page Load'), 50, FailureHandling.STOP_ON_FAILURE)

    WebUI.click(findTestObject('XXXXXXXXXXXX/Web Part/Stock Allocation/Button/Defreeze_btn'))

    WebUI.waitForElementVisible(findTestObject('XXXXXXXXXXXX/Web Part/Stock Allocation/Button/Freeze_btn'), 30)
} else {
    WebUI.waitForElementVisible(findTestObject('XXXXXXXXXXXX/Web Part/Stock Allocation/Button/Freeze_btn'), 30)
}

println('Stock Allocation is in Defreeze')

WebUI.takeScreenshot()

WebUI.callTestCase(findTestCase('VBL_Mobile/Re-Usables/Web Login and Navigation/Navigate to StockAllocation'), [:], FailureHandling.STOP_ON_FAILURE)

String sheet_name = 'Stock allocation'

String file_name = 'Web Input Data'

ArrayList<String> Warehouse = CustomKeywords.'poi.VBL.Automation.GetAllWebDataRow_Flag.getAllDataRow'(file_name, sheet_name, 
    'Warehouse')

ArrayList<String> Store = CustomKeywords.'poi.VBL.Automation.GetAllWebDataRow_Flag.getAllDataRow'(file_name, sheet_name, 
    'Store')

ArrayList<String> User_Details = CustomKeywords.'poi.VBL.Automation.GetAllWebDataRow_Flag.getAllDataRow'(file_name, sheet_name, 
    'User_Details')

ArrayList<String> case_Quantity = CustomKeywords.'poi.VBL.Automation.GetAllWebDataRow_Flag.getAllDataRow'(file_name, sheet_name, 
    'case_Quantity')

ArrayList<String> Piece_Quantity = CustomKeywords.'poi.VBL.Automation.GetAllWebDataRow_Flag.getAllDataRow'(file_name, sheet_name, 
    'Piece_Quantity')

ArrayList<String> SKU_Name = CustomKeywords.'poi.VBL.Automation.GetAllWebDataRow_Flag.getAllDataRow'(file_name, sheet_name, 
    'SKU_Name')

for (int i = 0; i < Warehouse.size(); i++) {
    if (Warehouse.get(i) != 'NULL') {
        WebUI.click(findTestObject('XXXXXXXXXXXX/Web Part/Stock Allocation/Drop down values/Ware house'))

        GlobalVariable.label = Warehouse.get(i)

        WebUI.click(findTestObject('XXXXXXXXXXXX/Web Part/Stock Allocation/Global Variables/DropdownSelect_Li Tag'))

        WebUI.waitForElementNotVisible(findTestObject('XXXXXXXXXXXX/Web Part/Re-usable/Page Load'), 50, FailureHandling.STOP_ON_FAILURE)

        WebUI.click(findTestObject('XXXXXXXXXXXX/Web Part/Stock Allocation/Drop down values/Store'))

        GlobalVariable.label = Store.get(i)

        WebUI.click(findTestObject('XXXXXXXXXXXX/Web Part/Stock Allocation/Global Variables/DropdownSelect_Li Tag'))

        WebUI.waitForElementNotVisible(findTestObject('XXXXXXXXXXXX/Web Part/Re-usable/Page Load'), 50, FailureHandling.STOP_ON_FAILURE)

        WebUI.click(findTestObject('XXXXXXXXXXXX/Web Part/Stock Allocation/Field values/User Details_Title'))

        GlobalVariable.label = User_Details.get(i)

        WebUI.click(findTestObject('XXXXXXXXXXXX/Web Part/Stock Allocation/Global Variables/UserSelect_Span b Tag'))

        WebUI.waitForElementNotVisible(findTestObject('XXXXXXXXXXXX/Web Part/Re-usable/Page Load'), 50, FailureHandling.STOP_ON_FAILURE)

        WebUI.click(findTestObject('XXXXXXXXXXXX/Web Part/Stock Allocation/Button/Manual_btn'))

        WebUI.waitForElementNotVisible(findTestObject('XXXXXXXXXXXX/Web Part/Re-usable/Page Load'), 50, FailureHandling.STOP_ON_FAILURE)

        for (j = 0; j < SKU_Name.size(); j++) {
            GlobalVariable.ProductName = SKU_Name.get(j)

            GlobalVariable.label = SKU_Name.get(j)

            WebUI.verifyElementVisible(findTestObject('XXXXXXXXXXXX/Web Part/Stock Allocation/Filter/Filter_Icon'), FailureHandling.OPTIONAL)

            WebUI.click(findTestObject('XXXXXXXXXXXX/Web Part/Stock Allocation/Filter/Filter_Icon'), FailureHandling.OPTIONAL)

            WebUI.click(findTestObject('XXXXXXXXXXXX/Web Part/Stock Allocation/Filter/Sku_Name'), FailureHandling.OPTIONAL)

            WebUI.clearText(findTestObject('XXXXXXXXXXXX/Web Part/Stock Allocation/Filter/Sku_Name'), FailureHandling.OPTIONAL)

            WebUI.setText(findTestObject('XXXXXXXXXXXX/Web Part/Stock Allocation/Filter/Sku_Name'), GlobalVariable.ProductName, 
                FailureHandling.OPTIONAL)

            WebUI.click(findTestObject('XXXXXXXXXXXX/Web Part/Stock Allocation/Filter/Appy_Btn'), FailureHandling.OPTIONAL)

            WebUI.verifyElementPresent(findTestObject('XXXXXXXXXXXX/Web Part/Stock Allocation/Global Variables/EnterCaseValue_Td Tag'), 
                2, FailureHandling.OPTIONAL)

            GlobalVariable.CaseQty = case_Quantity.get(j)

            WebUI.sendKeys(findTestObject('XXXXXXXXXXXX/Web Part/Stock Allocation/Global Variables/EnterCaseValue_Td Tag'), 
                GlobalVariable.CaseQty, FailureHandling.OPTIONAL)

            WebUI.waitForElementNotVisible(findTestObject('XXXXXXXXXXXX/Web Part/Re-usable/Page Load'), 50, FailureHandling.OPTIONAL) //
            //            GlobalVariable.PieceQty = Piece_Quantity.get(j)
            //
            //            WebUI.sendKeys(findTestObject('XXXXXXXXXXXX/Web Part/Stock Allocation/Global Variables/EnterPieceValue_Td Tag'), 
            //                GlobalVariable.PieceQty, FailureHandling.OPTIONAL)
            //
            //            WebUI.waitForElementNotVisible(findTestObject('XXXXXXXXXXXX/Web Part/Re-usable/Page Load'), 50, FailureHandling.OPTIONAL)
        }
        
        WebUI.click(findTestObject('XXXXXXXXXXXX/Web Part/Stock Allocation/Button/Allocate_btn'))

        WebUI.delay(2)
		
		if(WebUI.verifyElementPresent(findTestObject('XXXXXXXXXXXX/Web Part/Stock Allocation/Drop down values/Route'), 1, FailureHandling.OPTIONAL))
		{

        WebUI.click(findTestObject('XXXXXXXXXXXX/Web Part/Stock Allocation/Drop down values/Route'))

        WebUI.delay(1)

        WebUI.click(findTestObject('XXXXXXXXXXXX/Web Part/Stock Allocation/Global Variables/Option_tag_Dropdown(global)'))

        WebUI.click(findTestObject('XXXXXXXXXXXX/Web Part/Stock Allocation/Button/Yes,continue'))

		}
		
        WebUI.verifyElementPresent(findTestObject('XXXXXXXXXXXX/Web Part/Stock Allocation/Button/Manual_btn'), 10)

        //VL_01 To verify user can able to put order in Stock allocation
        println('Manual data added in Stock allocation Successfully!!')
    }
}

WebUI.closeBrowser()

WebUI.delay(3)

//1st DB
Connection connection = null

url = findTestData('VBL_DB Credentials/Config1').getValue('URL', 1)

dbname = findTestData('VBL_DB Credentials/Config1').getValue('dbname', 1)

username = findTestData('VBL_DB Credentials/Config1').getValue('username', 1)

password = findTestData('VBL_DB Credentials/Config1').getValue('password', 1)

queryString = 'select top 1 * from AppData_Van_Load_Header where VLH_AUH_Id = 297 order by 1 desc'

String conn = (((((('jdbc:sqlserver://' + url) + ';databaseName=') + dbname) + ';user=') + username) + ';password=') + password

connection = DriverManager.getConnection(conn)

Statement stm = connection.createStatement()

ResultSet rs = stm.executeQuery(queryString)

def recordSet = rs

while (recordSet.next()) {
    Object data1 = recordSet.getObject('VLH_Reference_No')

    GlobalVariable.VanLoad_No = data1

    println('Van Load Reference no  :  ' + GlobalVariable.VanLoad_No ///VL_02 To verify DB for geting vanload no 
        )
}

//2nd DB
queryString1 = (('Select * from appdata_van_load_header where vlh_reference_no=\'' + GlobalVariable.VanLoad_No) + '\'')

String conn1 = (((((('jdbc:sqlserver://' + url) + ';databaseName=') + dbname) + ';user=') + username) + ';password=') + 
password

connection1 = DriverManager.getConnection(conn1)

Statement stm1 = connection1.createStatement()

ResultSet rs1 = stm1.executeQuery(queryString1)

def recordSet1 = rs1

while (recordSet1.next()) {
    Object data2 = recordSet1.getObject('VLH_Id')

    GlobalVariable.label1 = data2

    println('Vlh id  :  ' + GlobalVariable.label1 ///VL_02 To verify DB for geting vanload no
        )
}

//3nd DB
queryString2 = (('Select top 1 * from appdata_van_load_detail where VLD_VLH_Id =\'' + GlobalVariable.label1) + '\'')

String conn2 = (((((('jdbc:sqlserver://' + url) + ';databaseName=') + dbname) + ';user=') + username) + ';password=') + 
password

connection2 = DriverManager.getConnection(conn2)

Statement stm2 = connection2.createStatement()

ResultSet rs2 = stm2.executeQuery(queryString2)

def recordSet2 = rs2

while (recordSet2.next()) {
    Object data3 = recordSet2.getObject('VLD_Qty')

    GlobalVariable.PieceQty = data3

    println('Total piece Qty  :  ' + GlobalVariable.PieceQty ///VL_02 To verify DB for geting vanload no
        )

    Object data4 = recordSet2.getObject('VLD_APH_Id')

    GlobalVariable.label2 = data4
}

//3nd DB
queryString3 = (('select * from adm_product_hierarchy where aph_id=\'' + GlobalVariable.label2) + '\'')

String conn3 = (((((('jdbc:sqlserver://' + url) + ';databaseName=') + dbname) + ';user=') + username) + ';password=') + 
password

connection3 = DriverManager.getConnection(conn3)

Statement stm3 = connection3.createStatement()

ResultSet rs3 = stm3.executeQuery(queryString3)

def recordSet3 = rs3

while (recordSet3.next()) {
    Object data5 = recordSet3.getObject('APH_Name')

    GlobalVariable.ProductName = data5

    println('Total piece Qty  :  ' + GlobalVariable.ProductName ///VL_02 To verify DB for geting vanload no
        )
}

