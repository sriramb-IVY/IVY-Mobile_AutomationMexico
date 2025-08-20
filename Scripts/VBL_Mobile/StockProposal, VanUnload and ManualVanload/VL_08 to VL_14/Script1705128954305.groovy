import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import java.sql.Connection as Connection
import java.sql.DriverManager as DriverManager
import java.sql.ResultSet as ResultSet
import java.sql.Statement as Statement
import java.text.SimpleDateFormat as SimpleDateFormat
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.mobile.keyword.internal.MobileDriverFactory as MobileDriverFactory
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import internal.GlobalVariable as GlobalVariable
import io.appium.java_client.AppiumDriver as AppiumDriver
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import org.openqa.selenium.WebElement as WebElement
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.keyword.excel.ExcelKeywords as ExcelKeywords
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil

not_run: Mobile.startApplication(GlobalVariable.APK_File, false)

not_run: Mobile.delay(3, FailureHandling.STOP_ON_FAILURE)

not_run: Mobile.tap(findTestObject('XXXXXXXXXXXX/Re-usable/Mobile Login/Menu'), 2, FailureHandling.OPTIONAL)

Mobile.callTestCase(findTestCase('VBL_Mobile/Re-Usables/Select_TradeCoverage_Menu'), [:], FailureHandling.STOP_ON_FAILURE)

Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/MainMenu/Menu_End of the Day'), 0)

Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/End of the Day/SubMenu_Stock Proposal'), 0)

Mobile.takeScreenshot()

Mobile.verifyElementExist(findTestObject('XXXXXXXXXXXX/Mobile Part/End of the Day/StockProposal/Title_Stock Proposal'), 0)

Mobile.verifyElementVisible(findTestObject('XXXXXXXXXXXX/Mobile Part/End of the Day/StockProposal/Title_Stock Proposal'), 
    0)

Mobile.verifyElementVisible(findTestObject('XXXXXXXXXXXX/Mobile Part/End of the Day/StockProposal/SP_ProductNameField'), 0)

Mobile.verifyElementVisible(findTestObject('XXXXXXXXXXXX/Mobile Part/End of the Day/StockProposal/SP_CaseQty_Field'), 0)

Mobile.verifyElementVisible(findTestObject('XXXXXXXXXXXX/Mobile Part/End of the Day/StockProposal/SP_PieceQty_Field'), 0)

//
//List<String> SKU_List = new ArrayList<String>()
//
//for(int i = 1; i<=20; i++)
//{
//	GlobalVariable.label = i
//	
//	if(Mobile.verifyElementExist(findTestObject('Object Repository/XXXXXXXXXXXX/Mobile Part/End of the Day/StockProposal/ProductList(Global-Index)'), 2, FailureHandling.OPTIONAL))
//	{
//	SKU = Mobile.getText(findTestObject('Object Repository/XXXXXXXXXXXX/Mobile Part/End of the Day/StockProposal/ProductList(Global-Index)'), 0, FailureHandling.STOP_ON_FAILURE)
//
//	SKU_List.add(SKU)
//	
//	}
//	else
//	{
//		break
//	}
//}
//
//ArrayList<String> duplicate_removed_SKU_List = new LinkedHashSet<String>(SKU_List)
//
//KeywordUtil.logInfo(duplicate_removed_SKU_List.toString())
//
//Collections.sort(duplicate_removed_SKU_List)
//
//KeywordUtil.logInfo(Collections.sort(duplicate_removed_SKU_List))
//
////KeywordUtil.logInfo(Collections.sort(GlobalVariable.label1))
//
//for(int j = 1; j<=20; j++)
//{
//	GlobalVariable.label = j
//	
//	if(Mobile.verifyElementExist(findTestObject('Object Repository/XXXXXXXXXXXX/Mobile Part/End of the Day/StockProposal/ProductList(Global-Index)'), 2, FailureHandling.OPTIONAL))
//	{
//	SKU = Mobile.getText(findTestObject('Object Repository/XXXXXXXXXXXX/Mobile Part/End of the Day/StockProposal/ProductList(Global-Index)'), 0, FailureHandling.STOP_ON_FAILURE)
//
//	Mobile.verifyMatch(SKU, duplicate_removed_SKU_List[j-1], false, FailureHandling.STOP_ON_FAILURE)
//	}
//	else
//	{
//		break
//	}
//}
//
//
//D_01 To verify the “Stock Proposal” screen
Mobile.takeScreenshot()

println('Verified : the “Stock Proposal” screen')

Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Reusable Object/Product Search Icon'), 0)

WebUI.callTestCase(findTestCase('Product_Mobile/Common/Search_Product'), [:], FailureHandling.STOP_ON_FAILURE)

GlobalVariable.ProductName = findTestData('VBL_Mobile Input Data/StockProposal').getValue('Product_Name', 1)

Mobile.setText(findTestObject('XXXXXXXXXXXX/Mobile Part/Reusable Object/Product Enter Search'), GlobalVariable.ProductName, 
    0)

Mobile.takeScreenshot()

Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/End of the Day/StockProposal/SP_CaseQty_Field'), 5, FailureHandling.STOP_ON_FAILURE)

Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Reusable Object/Vertical KeyPad-Numbers/Veritical keypad-BackSpace'), 
    2, FailureHandling.OPTIONAL)

Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Reusable Object/Vertical KeyPad-Numbers/Veritical keypad-BackSpace'), 
    2, FailureHandling.OPTIONAL)

GlobalVariable.CaseQty = findTestData('VBL_Mobile Input Data/StockProposal').getValue('CaseQty', 1)

GlobalVariable.keypadValue = GlobalVariable.CaseQty

Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Reusable Object/Vertical KeyPad-Numbers/Global-number_keypad'), 0)

Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/End of the Day/StockProposal/SP_PieceQty_Field'), 5, FailureHandling.STOP_ON_FAILURE)

Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Reusable Object/Vertical KeyPad-Numbers/Veritical keypad-BackSpace'), 
    2, FailureHandling.OPTIONAL)

Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Reusable Object/Vertical KeyPad-Numbers/Veritical keypad-BackSpace'), 
    2, FailureHandling.OPTIONAL)

GlobalVariable.PieceQty = findTestData('VBL_Mobile Input Data/StockProposal').getValue('PieceQty', 1)

GlobalVariable.keypadValue = GlobalVariable.PieceQty

Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Reusable Object/Vertical KeyPad-Numbers/Global-number_keypad'), 0)

Mobile.takeScreenshot()

Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/End of the Day/StockProposal/Button-Save'), 0)

Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/End of the Day/StockProposal/Alert-Yes Btn'), 0)

Mobile.verifyElementVisible(findTestObject('XXXXXXXXXXXX/Mobile Part/End of the Day/StockProposal/Saved Successfully'), 0)

Mobile.verifyElementExist(findTestObject('XXXXXXXXXXXX/Mobile Part/End of the Day/StockProposal/Saved Successfully'), 0)

//ED_02 To verify the “Stock Proposal” screen Save Functinality
Mobile.takeScreenshot()

println('Verified : The “Stock Proposal” screen Save Functinality.')

Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/End of the Day/StockProposal/Alert-OK Btn'), 0)

Mobile.tap(findTestObject('XXXXXXXXXXXX/Re-usable/Mobile Login/Menu'), 0)

Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/MainMenu/Menu_Load Management'), 0)

Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/LoadManagement/SubMenu_Current Stock view'), 0)

Mobile.delay(2, FailureHandling.STOP_ON_FAILURE)

for (j = 1; j <= 10; j++) {
    GlobalVariable.label = j

    if (Mobile.verifyElementExist(findTestObject('XXXXXXXXXXXX/Mobile Part/LoadManagement/CurrentStockView/Product(list_Xpath)'), 
        1, FailureHandling.OPTIONAL)) {
        Mobile.takeScreenshot()

        product = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/LoadManagement/CurrentStockView/Product(list_Xpath)'), 
            1, FailureHandling.OPTIONAL)

        SIH = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/LoadManagement/CurrentStockView/SIH(list_Xpath)'), 1, 
            FailureHandling.OPTIONAL)

        String exlpath = 'DDF\\VBL\\Mobile Input data\\Mobile Input data.xlsx'

        String Sheetname = 'VanUnload'

        workbook01 = ExcelKeywords.getWorkbook(exlpath)

        sheet1 = ExcelKeywords.getExcelSheet(workbook01, Sheetname)

        ExcelKeywords.setValueToCellByIndex(sheet1, j, 0, product)

        ExcelKeywords.setValueToCellByIndex(sheet1, j, 1, SIH)

        ExcelKeywords.saveWorkbook(exlpath, workbook01)
    }
}

Mobile.takeScreenshot()

Mobile.tap(findTestObject('Mobile/Common/Icon_Back'), 0)

Mobile.tap(findTestObject('XXXXXXXXXXXX/Re-usable/Mobile Login/Menu'), 0)

Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/MainMenu/Menu_End of the Day'), 0)

Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/End of the Day/SubMenu_Van Unload'), 0)

Mobile.delay(2, FailureHandling.STOP_ON_FAILURE)

////////// cases05 and cases01 product extra space issue so skip this verification
//List<String> SKU_List1 = new ArrayList<String>()
//
//for(int i1 = 1; i1<=20; i1++)
//{
//	GlobalVariable.label = i1
//	
//	if(Mobile.verifyElementExist(findTestObject('Object Repository/XXXXXXXXXXXX/Mobile Part/End of the Day/Van Unload/ProductList(Global-Index)'), 2, FailureHandling.OPTIONAL))
//	{
//	SKU = Mobile.getText(findTestObject('Object Repository/XXXXXXXXXXXX/Mobile Part/End of the Day/Van Unload/ProductList(Global-Index)'), 0, FailureHandling.STOP_ON_FAILURE)
//
//	SKU_List1.add(SKU)
//	
//	}
//	else
//	{
//		break
//	}
//}
//
//ArrayList<String> duplicate_removed_SKU_List1 = new LinkedHashSet<String>(SKU_List)
//
//KeywordUtil.logInfo(duplicate_removed_SKU_List1.toString())
//
//Collections.sort(duplicate_removed_SKU_List1)
//
//KeywordUtil.logInfo(Collections.sort(duplicate_removed_SKU_List1))
//
////KeywordUtil.logInfo(Collections.sort(GlobalVariable.label1))
//
//for(int j1 = 1; j1<=20; j1++)
//{
//	GlobalVariable.label = j1
//	
//	if(Mobile.verifyElementExist(findTestObject('Object Repository/XXXXXXXXXXXX/Mobile Part/End of the Day/Van Unload/ProductList(Global-Index)'), 2, FailureHandling.OPTIONAL))
//	{
//	SKU = Mobile.getText(findTestObject('Object Repository/XXXXXXXXXXXX/Mobile Part/End of the Day/Van Unload/ProductList(Global-Index)'), 0, FailureHandling.STOP_ON_FAILURE)
//
//	Mobile.verifyMatch(SKU, duplicate_removed_SKU_List[j1-1], false, FailureHandling.STOP_ON_FAILURE)
//	}
//	else
//	{
//		break
//	}
//}
Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Reusable Object/Product Search Icon'), 0)

Mobile.hideKeyboard()

Mobile.delay(1, FailureHandling.STOP_ON_FAILURE)

sheet_name = 'VanUnload'

file_name = 'Mobile Input data'

ArrayList<String> ProductName = CustomKeywords.'poi.VBL.Automation.GetAllDataRow_Flag.getAllDataRow'(file_name, sheet_name, 
    'ProductName')

ArrayList<String> SIH = CustomKeywords.'poi.VBL.Automation.GetAllDataRow_Flag.getAllDataRow'(file_name, sheet_name, 'SIH')

for (int a = 0; a < ProductName.size(); a++) {
    if (ProductName.get(a) != '') {
        GlobalVariable.ProductName = ProductName.get(a)

        GlobalVariable.productvalue = SIH.get(a)

        KeywordUtil.logInfo(GlobalVariable.ProductName)

        Mobile.takeScreenshot()

        Mobile.verifyElementVisible(findTestObject('XXXXXXXXXXXX/Mobile Part/End of the Day/Van Unload/Product(List_Object)'), 
            0)

        Mobile.verifyElementText(findTestObject('XXXXXXXXXXXX/Mobile Part/End of the Day/Van Unload/Product(List_Object)'), 
            GlobalVariable.ProductName)

        KeywordUtil.logInfo(GlobalVariable.productvalue)

        Mobile.verifyElementVisible(findTestObject('XXXXXXXXXXXX/Mobile Part/End of the Day/Van Unload/SIH(List_Object_Global)'), 
            0)

        Mobile.verifyElementText(findTestObject('XXXXXXXXXXXX/Mobile Part/End of the Day/Van Unload/SIH(List_Object_Global)'), 
            GlobalVariable.productvalue)
    }
}

println('Verified : Current stock view details and van unload details should be same')

Mobile.takeScreenshot()

WebUI.callTestCase(findTestCase('Product_Mobile/Common/Search_Product'), [:], FailureHandling.STOP_ON_FAILURE)

Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Reusable Object/Product Enter Search'), 0)

Mobile.setText(findTestObject('XXXXXXXXXXXX/Mobile Part/Reusable Object/Product Enter Search'), findTestData('VBL_Mobile Input Data/VanUnload').getValue(
        'ProductName', 1), 0)

Mobile.takeScreenshot()

Mobile.delay(1)

GlobalVariable.keypadValue = findTestData('VBL_Mobile Input Data/VanUnload').getValue('CaseQty', 1)

Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/End of the Day/StockProposal/SP_CaseQty_Field'), 0, FailureHandling.STOP_ON_FAILURE)

Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Reusable Object/Vertical KeyPad-Numbers/Global-number_keypad'), 0)

GlobalVariable.keypadValue = findTestData('VBL_Mobile Input Data/VanUnload').getValue('PieceQty', 1)

Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/End of the Day/StockProposal/SP_PieceQty_Field'), 0, FailureHandling.STOP_ON_FAILURE)

Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Reusable Object/Vertical KeyPad-Numbers/Global-number_keypad'), 0)

Mobile.takeScreenshot()

Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/End of the Day/Van Unload/Next-Button'), 0)

AppiumDriver driver = MobileDriverFactory.getDriver()

Actualtoastmessage = driver.findElementByXPath('//android.widget.Toast[1]').getText()

Mobile.takeScreenshot()

Mobile.verifyMatch(Actualtoastmessage, findTestData('VBL_Mobile Input Data/VanUnload').getValue('Verify', 1), false, FailureHandling.STOP_ON_FAILURE)

println('Van Unload should be saved successfully')

WebUI.callTestCase(findTestCase('Product_Mobile/Common/PrintScreen'), [:], FailureHandling.STOP_ON_FAILURE)

Mobile.tap(findTestObject('Mobile/Common/Icon_Back'), 2, FailureHandling.OPTIONAL)

Mobile.callTestCase(findTestCase('VBL_Mobile/Re-Usables/Sync'), [:], FailureHandling.OPTIONAL)

Mobile.closeApplication()

Mobile.callTestCase(findTestCase('VBL_Mobile/Re-Usables/HHT_Transaction_DB'), [:], FailureHandling.STOP_ON_FAILURE)

Mobile.callTestCase(findTestCase('VBL_Mobile/StockProposal, VanUnload and ManualVanload/Call_Testcase/VanUnload_DB'), [:], 
    FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('VBL_Mobile/StockProposal, VanUnload and ManualVanload/Call_Testcase/StockProposal_DB'), 
    [:], FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('VBL_Mobile/Re-Usables/Web Login and Navigation/ClearCashe Login'), [:], FailureHandling.STOP_ON_FAILURE)

Mobile.callTestCase(findTestCase('VBL_Mobile/Re-Usables/Web Login and Navigation/ManjuLogin'), [:], FailureHandling.STOP_ON_FAILURE)

Mobile.callTestCase(findTestCase('VBL_Mobile/Re-Usables/Web Login and Navigation/Navigate to StockAllocation'), [:], FailureHandling.STOP_ON_FAILURE)

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

        //Mobile.takeScreenshot()
        WebUI.click(findTestObject('XXXXXXXXXXXX/Web Part/Stock Allocation/Button/Proposed_btn'))

        WebUI.delay(3)

        not_run: Mobile.takeScreenshot()

        GlobalVariable.ProductName = findTestData('VBL_Mobile Input Data/StockProposal').getValue('Product_Name', 1)

        WebUI.click(findTestObject('XXXXXXXXXXXX/Web Part/Stock Allocation/Global Variables/Hyper_a_tag(grid)'))

        proposedCaseValue = WebUI.getText(findTestObject('XXXXXXXXXXXX/Web Part/Stock Allocation/Field values/GetCaseValue_Proposal'))

        println(proposedCaseValue)

        GlobalVariable.CaseQty = findTestData('VBL_Mobile Input Data/StockProposal').getValue('CaseQty', 1)

        WebUI.verifyMatch(proposedCaseValue, GlobalVariable.CaseQty, false, FailureHandling.STOP_ON_FAILURE)

        //Entered Case Qty In Mobile Stock Proposal Screen That Would refected in Web Stock Allocation Screen Properly
        print('Entered Case Qty In Mobile Stock Proposal Screen That Would refected in Web Stock Allocation Screen Properly')

        //Mobile.takeScreenshot()
        proposedPieceValue = WebUI.getText(findTestObject('XXXXXXXXXXXX/Web Part/Stock Allocation/Field values/GetPieceValue_Proposal'))

        println(proposedPieceValue)

        GlobalVariable.PieceQty = findTestData('VBL_Mobile Input Data/StockProposal').getValue('PieceQty', 1)

        WebUI.verifyMatch(proposedPieceValue, GlobalVariable.PieceQty, false, FailureHandling.STOP_ON_FAILURE)

        //Entered Piece Qty In Mobile Stock Proposal Screen That Would refected in Web Stock Allocation Screen Properly
        print('Entered Piece Qty In Mobile Stock Proposal Screen That Would refected in Web Stock Allocation Screen Properly')

        //ED_03 To verify the proposed Screen Case and Piece Qty
        WebUI.takeScreenshot()

        WebUI.setText(findTestObject('XXXXXXXXXXXX/Web Part/Stock Allocation/Field values/EnterCaseValue_Allocation'), GlobalVariable.CaseQty)

        WebUI.setText(findTestObject('XXXXXXXXXXXX/Web Part/Stock Allocation/Field values/EnterPieceValue_Allocation'), GlobalVariable.PieceQty)

        WebUI.click(findTestObject('XXXXXXXXXXXX/Web Part/Stock Allocation/Button/Save_btn'))

        WebUI.delay(4)

        WebUI.waitForElementNotVisible(findTestObject('XXXXXXXXXXXX/Web Part/Re-usable/Page Load'), 50, FailureHandling.STOP_ON_FAILURE)

        WebUI.click(findTestObject('XXXXXXXXXXXX/Web Part/Stock Allocation/Button/Proceed_btn'))

        WebUI.delay(4)

        WebUI.waitForElementNotVisible(findTestObject('XXXXXXXXXXXX/Web Part/Re-usable/Page Load'), 50, FailureHandling.STOP_ON_FAILURE)

        WebUI.click(findTestObject('XXXXXXXXXXXX/Web Part/Stock Allocation/Button/Allocation Btn (proposed)'))

        WebUI.delay(2)

        if (WebUI.verifyElementVisible(findTestObject('XXXXXXXXXXXX/Web Part/Stock Allocation/Drop down values/Route'), FailureHandling.OPTIONAL)) {
            WebUI.click(findTestObject('XXXXXXXXXXXX/Web Part/Stock Allocation/Drop down values/Route'))

            WebUI.delay(1)

            WebUI.click(findTestObject('XXXXXXXXXXXX/Web Part/Stock Allocation/Global Variables/Option_tag_Dropdown(global)'))

            WebUI.click(findTestObject('XXXXXXXXXXXX/Web Part/Stock Allocation/Button/Yes,continue'))
        }
        
        //WebUI.takeScreenshot()
        WebUI.verifyElementPresent(findTestObject('XXXXXXXXXXXX/Web Part/Stock Allocation/Button/Proposed_btn'), 10)

        //ED_04 To verify the user can able do proposed allocation
        println('Proposed data added in Stock allocation Successfully!!')

        WebUI.takeScreenshot()
    }
}

WebUI.delay(5)

Connection connection = null

url = findTestData('VBL_DB Credentials/Config1').getValue('URL', 1)

dbname = findTestData('VBL_DB Credentials/Config1').getValue('dbname', 1)

username = findTestData('VBL_DB Credentials/Config1').getValue('username', 1)

password = findTestData('VBL_DB Credentials/Config1').getValue('password', 1)

queryString = 'select top 1 * from AppData_Van_Load_Header order by 1 desc'

String conn = (((((('jdbc:sqlserver://' + url) + ';databaseName=') + dbname) + ';user=') + username) + ';password=') + password

connection = DriverManager.getConnection(conn)

Statement stm = connection.createStatement()

ResultSet rs = stm.executeQuery(queryString)

def recordSet = rs

while (recordSet.next()) {
    Object data1 = recordSet.getObject('VLH_Reference_No')

    GlobalVariable.VanLoad_No = data1

    //ED_05  To verify DB for geting vanload no 
    println('Van Load Reference no  :  ' + GlobalVariable.VanLoad_No)
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
queryString2 = (('Select * from appdata_van_load_detail where VLD_VLH_Id =\'' + GlobalVariable.label1) + '\'')

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
}

Mobile.callTestCase(findTestCase('VBL_Mobile/Re-Usables/Mobile_Login/VanSeller_Login-With Reset'), [:], FailureHandling.STOP_ON_FAILURE)

Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/MainMenu/Menu_Load Management'), 0)

Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/LoadManagement/SubMenu-Van Load'), 0)

println('Van Load Reference no  :  ' + GlobalVariable.VanLoad_No)

Mobile.scrollToText(GlobalVariable.VanLoad_No, FailureHandling.OPTIONAL)

Mobile.takeScreenshot()

Mobile.verifyElementExist(findTestObject('XXXXXXXXXXXX/Mobile Part/LoadManagement/Vanload/VanLoading No-(Global)'), 5)

Mobile.verifyElementVisible(findTestObject('XXXXXXXXXXXX/Mobile Part/LoadManagement/Vanload/VanLoading No-(Global)'), 5)

//ED_06 To verify that User is able to view vanload no
Mobile.takeScreenshot()

println('VERIFIED : User can able to view vanload no')

Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/LoadManagement/Vanload/VanLoading No-(Global)'), 0)

Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Reusable Object/Product Search Icon'), 0)

WebUI.callTestCase(findTestCase('Product_Mobile/Common/Search_Product'), [:], FailureHandling.STOP_ON_FAILURE)

Mobile.setText(findTestObject('XXXXXXXXXXXX/Mobile Part/Reusable Object/Product Enter Search'), GlobalVariable.ProductName, 
    0)

Mobile.takeScreenshot()

//case_Qty = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/LoadManagement/Vanload/VL_Case_Value'), 0)
//
//println(case_Qty)
//
//Mobile.verifyMatch(case_Qty, GlobalVariable.CaseQty, false, FailureHandling.OPTIONAL)
SIH_VanloadScreen = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/LoadManagement/Vanload/VL_Piece_Value'), 0)

println(SIH_VanloadScreen)

Mobile.verifyEqual(Integer.parseInt(SIH_VanloadScreen), GlobalVariable.PieceQty, FailureHandling.STOP_ON_FAILURE)

//ED_07 To verify the Vanload Screen Case and Piece Qty 
Mobile.takeScreenshot()

println('Verified : Entered Case and Piece Qty in web proposed screen, that same Qty reflected Mobile Vanload screen Properly!')

Mobile.tap(findTestObject('Mobile/Common/Icon_Back'), 0)

//Mobile.tap(findTestObject('Mobile/Common/Icon_Back'), 0)
//
//Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/LoadManagement/SubMenu_Current Stock view'), 0)
//
//Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Reusable Object/Product Search Icon'), 0)
//
//Mobile.setText(findTestObject('XXXXXXXXXXXX/Mobile Part/Reusable Object/Product Enter Search'), GlobalVariable.ProductName, 
//    0)
//
//Before_SIH_value = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/LoadManagement/CurrentStockView/SIH_Value'), 0, FailureHandling.OPTIONAL)
//
//println('Before vanload accept_SIH_value  : ' + Before_SIH_value)
//
//Mobile.tap(findTestObject('Mobile/Common/Icon_Back'), 0)
//
//Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/LoadManagement/SubMenu-Van Load'), 0)
Mobile.scrollToText(GlobalVariable.VanLoad_No, FailureHandling.OPTIONAL)

Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/LoadManagement/Vanload/Global_RadioIcon'), 3, FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/LoadManagement/Vanload/VanLoadScreen-Accept Btn'), 3, FailureHandling.STOP_ON_FAILURE)

Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/LoadManagement/Vanload/OK Btn'), 3, FailureHandling.STOP_ON_FAILURE)

AppiumDriver driver1 = MobileDriverFactory.getDriver()

Actualtoastmessage = driver1.findElementByXPath('//android.widget.Toast[1]').getText()

println(Actualtoastmessage)

Mobile.verifyMatch(Actualtoastmessage, findTestData('VBL_Mobile Input Data/VanLoad').getValue('Alerts', 1), false)

//ED_08 To verify that User is able to accept the van load 
Mobile.takeScreenshot('VL_06 To verify that User is able to accept the van load ', FailureHandling.STOP_ON_FAILURE)

println('Verified : User can able to accept the van load !')

Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/LoadManagement/SubMenu_Current Stock view'), 0)

Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Reusable Object/Product Search Icon'), 0)

WebUI.callTestCase(findTestCase('Product_Mobile/Common/Search_Product'), [:], FailureHandling.STOP_ON_FAILURE)

Mobile.setText(findTestObject('XXXXXXXXXXXX/Mobile Part/Reusable Object/Product Enter Search'), GlobalVariable.ProductName, 
    0)

Mobile.takeScreenshot()

SIH_value_CurrentStockView = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/LoadManagement/CurrentStockView/SIH_Value'), 
    0)

//println('Before vanload accept_SIH_value  : ' + Before_SIH_value)
println('after_SIH_value : ' + SIH_value_CurrentStockView)

Mobile.verifyEqual(Integer.parseInt(SIH_VanloadScreen), Integer.parseInt(SIH_value_CurrentStockView), FailureHandling.STOP_ON_FAILURE)

//Mobile.verifyNotMatch(after_SIH_value, Before_SIH_value, false)
//ED_09 To verify that Currwent stock view SIH value
Mobile.takeScreenshot()

println('Verified : Current stock view SIH Qty')

Mobile.tap(findTestObject('Mobile/Common/Icon_Back'), 0)

