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

not_run: WebUI.callTestCase(findTestCase('Product_Mobile/Common/Web Login and Navigation/DistributorLogin'), [:], FailureHandling.STOP_ON_FAILURE)

//not_run: WebUI.callTestCase(findTestCase('Product_Mobile/Common/Web Login and Navigation/Navigate to StockAdjustmentCreate'), [:], FailureHandling.STOP_ON_FAILURE)
//
//not_run: if (WebUI.waitForElementNotVisible(findTestObject('Web Part/Stock Allocation/Button/Freeze_btn'), 3, FailureHandling.OPTIONAL)) {
//    WebUI.waitForElementNotVisible(findTestObject('Web Part/Re-usable/Page Load'), 50, FailureHandling.STOP_ON_FAILURE)
//
//    WebUI.click(findTestObject('Web Part/Stock Allocation/Button/Defreeze_btn'))
//
//    WebUI.waitForElementVisible(findTestObject('Web Part/Stock Allocation/Button/Freeze_btn'), 30)
//} else {
//    WebUI.waitForElementVisible(findTestObject('Web Part/Stock Allocation/Button/Freeze_btn'), 30)
//}
//
//not_run: println('Stock Allocation is in Defreeze')
//
//not_run: WebUI.takeScreenshot()
not_run: WebUI.callTestCase(findTestCase('Product_Mobile/Common/Web Login and Navigation/Navigate to StockAllocation'), 
    [:], FailureHandling.STOP_ON_FAILURE)

not_run: String sheet_name = 'Stock allocation'

not_run: String file_name = 'Web Input Data'

not_run: ArrayList<String> Warehouse = CustomKeywords.'poi.Automation.GetAllWebDataRow_Flag.getAllDataRow'(file_name, sheet_name, 
    'Warehouse')

not_run: ArrayList<String> Store = CustomKeywords.'poi.Automation.GetAllWebDataRow_Flag.getAllDataRow'(file_name, sheet_name, 
    'Store')

not_run: ArrayList<String> User_Details = CustomKeywords.'poi.Automation.GetAllWebDataRow_Flag.getAllDataRow'(file_name, 
    sheet_name, 'User_Details')

not_run: ArrayList<String> case_Quantity = CustomKeywords.'poi.Automation.GetAllWebDataRow_Flag.getAllDataRow'(file_name, 
    sheet_name, 'case_Quantity')

not_run: ArrayList<String> Piece_Quantity = CustomKeywords.'poi.Automation.GetAllWebDataRow_Flag.getAllDataRow'(file_name, 
    sheet_name, 'Piece_Quantity')

not_run: ArrayList<String> SKU_Name = CustomKeywords.'poi.Automation.GetAllWebDataRow_Flag.getAllDataRow'(file_name, sheet_name, 
    'SKU_Name')

not_run: for (int i = 0; i < Warehouse.size(); i++) {
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

        for (j = Starting_Row_No; j <= Ending_Row_No; j++) {
            GlobalVariable.ProductName = SKU_Name.get(j)

            GlobalVariable.label = SKU_Name.get(j)

            WebUI.verifyElementVisible(findTestObject('Web Part/Stock Allocation/Filter/Filter_Icon'), FailureHandling.STOP_ON_FAILURE)

            WebUI.click(findTestObject('Web Part/Stock Allocation/Filter/Filter_Icon'))

            WebUI.click(findTestObject('Web Part/Stock Allocation/Filter/Sku_Name'))

            WebUI.clearText(findTestObject('Web Part/Stock Allocation/Filter/Sku_Name'), FailureHandling.OPTIONAL)

            WebUI.setText(findTestObject('Web Part/Stock Allocation/Filter/Sku_Name'), GlobalVariable.ProductName)

            WebUI.click(findTestObject('Web Part/Stock Allocation/Filter/Appy_Btn'))

            WebUI.verifyElementPresent(findTestObject('Web Part/Stock Allocation/Global Variables/EnterCaseValue_Td Tag'), 
                5, FailureHandling.STOP_ON_FAILURE)

            GlobalVariable.CaseQty = case_Quantity.get(j)

            WebUI.sendKeys(findTestObject('Web Part/Stock Allocation/Global Variables/EnterCaseValue_Td Tag'), 
                GlobalVariable.CaseQty //           WebUI.waitForElementNotVisible(findTestObject('Web Part/Re-usable/Page Load'), 50, FailureHandling.STOP_ON_FAILURE)
                //
                ) //            GlobalVariable.PieceQty = Piece_Quantity.get(j)
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

not_run: WebUI.closeBrowser()

not_run: WebUI.delay(3)

//1st DB
not_run: Connection connection = null

not_run: url = findTestData('DB Credentials/Config1').getValue('URL', 1)

not_run: dbname = findTestData('DB Credentials/Config1').getValue('dbname', 1)

not_run: username = findTestData('DB Credentials/Config1').getValue('username', 1)

not_run: password = findTestData('DB Credentials/Config1').getValue('password', 1)

not_run: queryString = 'select top 1 * from AppData_Van_Load_Header where VLH_AUH_Id = 1589 order by 1 desc'

not_run: String conn = (((((('jdbc:sqlserver://' + url) + ';databaseName=') + dbname) + ';user=') + username) + ';password=') + 
password

not_run: connection = DriverManager.getConnection(conn)

not_run: Statement stm = connection.createStatement()

not_run: ResultSet rs = stm.executeQuery(queryString)

not_run: def recordSet = rs

not_run: while (recordSet.next()) {
    Object data1 = recordSet.getObject('VLH_Reference_No')

    GlobalVariable.VanLoad_No = data1

    println('Van Load Reference no  :  ' + GlobalVariable.VanLoad_No ///VL_02 To verify DB for geting vanload no 
        )
}

not_run: Mobile.callTestCase(findTestCase('Product_Mobile/Common/Mobile_Login/StartApplication_attendence_VanloadAccept'), 
    [:], FailureHandling.STOP_ON_FAILURE)

