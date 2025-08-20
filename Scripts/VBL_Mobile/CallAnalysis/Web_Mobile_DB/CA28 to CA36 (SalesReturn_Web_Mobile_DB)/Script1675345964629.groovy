import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import java.sql.Connection as Connection
import java.sql.DriverManager as DriverManager
import java.sql.ResultSet as ResultSet
import java.sql.Statement as Statement
import java.text.SimpleDateFormat as SimpleDateFormat
import com.google.common.collect.FilteredEntryMultimap.Keys as Keys
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint

WebUI.callTestCase(findTestCase('VBL_Mobile/Re-Usables/Web Login and Navigation/ManjuLogin'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('VBL_Mobile/Re-Usables/Web Login and Navigation/Navigate to Sales Return View'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.sendKeys(findTestObject('XXXXXXXXXXXX/Web Part/SalesReturnView/SalesPerson'), findTestData('VBL_Web Input Data/SalesReturn').getValue(
        'SalesPerson', 1))

WebUI.sendKeys(findTestObject('XXXXXXXXXXXX/Web Part/SalesReturnView/SalesPerson'), Keys.chord(Keys.DOWN, Keys.ENTER))

WebUI.waitForElementNotVisible(findTestObject('XXXXXXXXXXXX/Web Part/Re-usable/Page Load'), 100)

String pattern = 'yyyy-MM-dd'

SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern)

String currentdate = simpleDateFormat.format(new Date())

println(currentdate)

DB_Currentdate = (currentdate + ' 00:00:00.000')

Connection connection = null

url = findTestData('VBL_DB Credentials/Config1').getValue('URL', 1)

dbname = findTestData('VBL_DB Credentials/Config1').getValue('dbname', 1)

username = findTestData('VBL_DB Credentials/Config1').getValue('username', 1)

password = findTestData('VBL_DB Credentials/Config1').getValue('password', 1)

//GlobalVariable.SR_Amt = '550.000000'
queryString = (((('select top 1 * from appdata_sales_return_header where srh_Date = \'' + DB_Currentdate) + '\'  and SRH_Amount = \'') + 
GlobalVariable.SR_Amt) + '\' and SRH_UserId=297 and SRH_ARTR_Id=195 order by 1 desc ')

String conn = (((((('jdbc:sqlserver://' + url) + ';databaseName=') + dbname) + ';user=') + username) + ';password=') + password

connection = DriverManager.getConnection(conn)

Statement stm = connection.createStatement()

ResultSet rs = stm.executeQuery(queryString)

def recordSet = rs

while (recordSet.next()) {
    Object SRH_ID = recordSet.getObject('SRH_ID')

    GlobalVariable.label2 = SRH_ID

    println(GlobalVariable.label2)

    Object SRH_Amount = recordSet.getObject('SRH_Amount')

    Object SRH_No = recordSet.getObject('SRH_No')

    GlobalVariable.SR_No = SRH_No

    println('Sales return Number  :  ' + GlobalVariable.SR_No)

    WebUI.setText(findTestObject('XXXXXXXXXXXX/Web Part/SalesReturnView/ReturnNo'), GlobalVariable.SR_No)

    WebUI.click(findTestObject('XXXXXXXXXXXX/Web Part/SalesReturnView/Search_Btn'))

    WebUI.waitForElementNotVisible(findTestObject('XXXXXXXXXXXX/Web Part/Re-usable/Page Load'), 100)

    WebUI.verifyElementPresent(findTestObject('XXXXXXXXXXXX/Web Part/SalesReturnView/Global_Td_Tag(grid Value)'), 0)

    WebUI.verifyElementVisible(findTestObject('XXXXXXXXXXXX/Web Part/SalesReturnView/Global_Td_Tag(grid Value)'))

    WebUI.verifyElementText(findTestObject('XXXXXXXXXXXX/Web Part/SalesReturnView/Global_Td_Tag(grid Value)'), GlobalVariable.SR_No)

    WebUI.takeScreenshot()

    println('Sales Return Number Displayed Properly in both Database and SalesReturnView Screen details grid!')

    DetailsGrid_ReturnAmt = WebUI.getText(findTestObject('XXXXXXXXXXXX/Web Part/SalesReturnView/DetailsGrid_ReturnAmt'))

    println(DetailsGrid_ReturnAmt)

    WebUI.verifyEqual(Double.parseDouble(DetailsGrid_ReturnAmt), SRH_Amount, FailureHandling.STOP_ON_FAILURE)

    println('Return Amount Displayed Properly in both Database and SalesReturnView details grid Screen!')

    WebUI.takeScreenshot()
}

WebUI.click(findTestObject('XXXXXXXXXXXX/Web Part/SalesReturnView/View_Icon'))

WebUI.waitForElementNotVisible(findTestObject('XXXXXXXXXXXX/Web Part/Re-usable/Page Load'), 100)

WebUI.delay(2)

//2nd DB
queryString1 = (('select * from appdata_sales_return_detail where srd_srh_id=\'' + GlobalVariable.label2) + '\'')

ResultSet rs1 = stm.executeQuery(queryString1)

def recordSet1 = rs1

while (recordSet1.next()) {
    Object SRD_SRH_No = recordSet1.getObject('SRD_SRH_No')

    println('Sales Return no : ' + SRD_SRH_No)

    PopUpView_SR_No = WebUI.getAttribute(findTestObject('XXXXXXXXXXXX/Web Part/SalesReturnView/PopUp-SR_No'), 'value', FailureHandling.STOP_ON_FAILURE)

    println(PopUpView_SR_No)

    WebUI.verifyMatch(PopUpView_SR_No, SRD_SRH_No, false, FailureHandling.STOP_ON_FAILURE)

    println('Sales Return number displayed properly in both database and salesreturn view popup screen')

    WebUI.takeScreenshot()

    Object SRD_Piece_Qty = recordSet1.getObject('SRD_Piece_Qty')

    GlobalVariable.DB_PieceQty = SRD_Piece_Qty

    println(GlobalVariable.DB_PieceQty)

    /////////////////////mobile validation
    WebUI.verifyEqual(GlobalVariable.DB_PieceQty, GlobalVariable.S_CasePieceQty, FailureHandling.STOP_ON_FAILURE)

    println('Entere Case and Piece Qty in the mobile apllication properly inserted into database!')

    //////
    GlobalVariable.CaseQty = WebUI.getText(findTestObject('XXXXXXXXXXXX/Web Part/SalesReturnView/PopUp-Grid_CaseQty'))

    GlobalVariable.PieceQty = WebUI.getText(findTestObject('XXXXXXXXXXXX/Web Part/SalesReturnView/PopUp-Grid_PieceQty'))

    GlobalVariable.CaseSize = findTestData('VBL_Mobile Input Data/Invoice').getValue('CaseSize', 2)

    QtyCase = (Integer.parseInt(GlobalVariable.CaseSize) * Integer.parseInt(GlobalVariable.CaseQty))

    Case_Piece_Qty = (QtyCase + Integer.parseInt(GlobalVariable.PieceQty))

    WebUI.verifyEqual(GlobalVariable.DB_PieceQty, Case_Piece_Qty, FailureHandling.STOP_ON_FAILURE)

    println('Case and Piece Qty displayed properly in both database and SalesReturn popup view Screen')

    WebUI.takeScreenshot()

    Object SRD_Line_Value = recordSet1.getObject('SRD_Line_Value')

    GlobalVariable.Line_Value = SRD_Line_Value

    Grid_TotalAmt = WebUI.getText(findTestObject('XXXXXXXXXXXX/Web Part/SalesReturnView/PopUp-Grid_TotalAmt'))

    WebUI.verifyEqual(Double.parseDouble(Grid_TotalAmt), GlobalVariable.Line_Value, FailureHandling.STOP_ON_FAILURE)

    println('Total Amount displayed properly in both database and salesreturn view popup grid screen')

    WebUI.takeScreenshot()

    PopUpView_Total_Amt = WebUI.getAttribute(findTestObject('XXXXXXXXXXXX/Web Part/SalesReturnView/PopUp-Total_Amt'), 'value', 
        FailureHandling.STOP_ON_FAILURE)

    WebUI.verifyEqual(Double.parseDouble(PopUpView_Total_Amt), GlobalVariable.Line_Value, FailureHandling.STOP_ON_FAILURE)

    println('Total Amount displayed properly in both database and salesreturn view popup screen')

    WebUI.takeScreenshot()

    PopUpView_ReturnAmt = WebUI.getAttribute(findTestObject('XXXXXXXXXXXX/Web Part/SalesReturnView/PopUp-SR_Amt'), 'value', 
        FailureHandling.STOP_ON_FAILURE)

    WebUI.verifyEqual(Double.parseDouble(PopUpView_ReturnAmt), GlobalVariable.Line_Value, FailureHandling.STOP_ON_FAILURE)

    println('Return Amount displayed properly in both database and salesreturn view popup screen')

    WebUI.takeScreenshot()

    PopUpView_Credit_Amt = WebUI.getAttribute(findTestObject('XXXXXXXXXXXX/Web Part/SalesReturnView/PopUp-Credit_Amt'), 'value', 
        FailureHandling.STOP_ON_FAILURE)

    WebUI.verifyEqual(Double.parseDouble(PopUpView_Credit_Amt), Double.parseDouble(PopUpView_ReturnAmt), FailureHandling.STOP_ON_FAILURE)

    println('Credit Amount Generated properly based on return amount in salesreturn view popup screen')

    WebUI.takeScreenshot()

    Object SRD_APH_Id = recordSet1.getObject('SRD_APH_Id')

    GlobalVariable.label1 = SRD_APH_Id

    println('Sku ID : ' + GlobalVariable.label1)
}

queryString2 = (('select * from adm_product_hierarchy where aph_id=\'' + GlobalVariable.label1) + '\'')

ResultSet rs2 = stm.executeQuery(queryString2)

def recordSet2 = rs2

while (recordSet2.next()) {
    Object Sku_Name = recordSet2.getObject('APH_Name')

    PopUpView_Grid_SKU_Name = WebUI.getText(findTestObject('XXXXXXXXXXXX/Web Part/SalesReturnView/PopUp-Grid_SKU_Name'))

    WebUI.verifyMatch(Sku_Name, PopUpView_Grid_SKU_Name, false, FailureHandling.STOP_ON_FAILURE)

    println('SKU name displayed properly in both database and salesreturn view popup screen')

    WebUI.takeScreenshot()

    ////////////////////////Mobile Validation
    WebUI.verifyMatch(GlobalVariable.S_Sku_Name, Sku_Name, false, FailureHandling.STOP_ON_FAILURE)

    println('SKU name in the mobile application inserted into the database')
}

queryString3 = (('select * from appdata_sales_return_reasons where srr_srh_id=\'' + GlobalVariable.label2) + '\'')

ResultSet rs3 = stm.executeQuery(queryString3)

def recordSet3 = rs3

while (recordSet3.next()) {
    Object SRR_Reason_Lov_Id = recordSet3.getObject('SRR_Reason_Lov_Id')

    GlobalVariable.label1 = SRR_Reason_Lov_Id

    println('Reason ID :' + GlobalVariable.label1)
}

queryString4 = (('select * from adm_lovs where alov_id=\'' + GlobalVariable.label1) + '\'')

ResultSet rs4 = stm.executeQuery(queryString4)

def recordSet4 = rs4

while (recordSet4.next()) {
    Object SRR_Reason = recordSet4.getObject('ALOV_Code')

    WebUI.click(findTestObject('XXXXXXXXXXXX/Web Part/SalesReturnView/PopUp-Grid_Reason'))

    WebUI.waitForElementNotVisible(findTestObject('XXXXXXXXXXXX/Web Part/Re-usable/Page Load'), 100)

    GlobalVariable.CaseQty = WebUI.getText(findTestObject('XXXXXXXXXXXX/Web Part/SalesReturnView/ReasonGrid-CaseQty'))

    GlobalVariable.PieceQty = WebUI.getText(findTestObject('XXXXXXXXXXXX/Web Part/SalesReturnView/ReasonGrid-PieceQty'))

    GlobalVariable.CaseSize = findTestData('VBL_Mobile Input Data/Invoice').getValue('CaseSize', 2)

    QtyCase = (Integer.parseInt(GlobalVariable.CaseSize) * Integer.parseInt(GlobalVariable.CaseQty))

    Case_Piece_Qty = (QtyCase + Integer.parseInt(GlobalVariable.PieceQty))

    WebUI.verifyEqual(GlobalVariable.DB_PieceQty, Case_Piece_Qty, FailureHandling.STOP_ON_FAILURE)

    println('Case and Piece Qty displayed properly in both database and SalesReturn popup reason view Screen')

    WebUI.takeScreenshot()

    reason_Name = WebUI.getText(findTestObject('XXXXXXXXXXXX/Web Part/SalesReturnView/ReasonGrid-ReasonType'))

    WebUI.verifyMatch(SRR_Reason, reason_Name, false, FailureHandling.STOP_ON_FAILURE)

    println('Reason Name displayed properly in both database and SalesReturn popup reason view Screen')

    ///////////////// mobile validation
    WebUI.verifyMatch(GlobalVariable.S_ReasonType, SRR_Reason, false, FailureHandling.STOP_ON_FAILURE)

    println('Mentioned Reason in the mobile application properly inserted into the database') ///////////
}

WebUI.closeBrowser()

