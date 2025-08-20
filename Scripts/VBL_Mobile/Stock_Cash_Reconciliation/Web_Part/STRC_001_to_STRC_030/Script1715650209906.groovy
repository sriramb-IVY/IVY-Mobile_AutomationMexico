import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import org.junit.Assert as Assert
import java.text.SimpleDateFormat as SimpleDateFormat
import java.util.Date as Date
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import java.sql.Connection as Connection
import java.sql.DriverManager as DriverManager
import java.sql.ResultSet as ResultSet
import java.sql.Statement as Statement
import com.kms.katalon.core.mobile.keyword.internal.MobileDriverFactory as MobileDriverFactory
import io.appium.java_client.AppiumDriver as AppiumDriver
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import org.openqa.selenium.WebElement as WebElement

WebUI.callTestCase(findTestCase('VBL_Mobile/Stock_Cash_Reconciliation/Call Test Case/Trip_Id_Query'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('Test Cases/VBL_Mobile/Re-Usables/Web Login and Navigation/ManjuLogin'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('VBL_Mobile/Re-Usables/Web Login and Navigation/Navigate to Stock Reconcile Create'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.waitForElementNotVisible(findTestObject('XXXXXXXXXXXX/Web Part/Re-usable/Page Load'), 50, FailureHandling.STOP_ON_FAILURE)

WebUI.verifyElementPresent(findTestObject('XXXXXXXXXXXX/Web Part/Stock_Cash_Reconciliation/UserSelect'), 5)

WebUI.click(findTestObject('XXXXXXXXXXXX/Web Part/Stock_Cash_Reconciliation/UserSelect'))

WebUI.takeScreenshot()

WebUI.scrollToElement(findTestObject('XXXXXXXXXXXX/Web Part/Stock_Cash_Reconciliation/Button/Proceed_Btn'), 0)

WebUI.verifyElementPresent(findTestObject('XXXXXXXXXXXX/Web Part/Stock_Cash_Reconciliation/Button/Proceed_Btn'), 0)

WebUI.click(findTestObject('XXXXXXXXXXXX/Web Part/Stock_Cash_Reconciliation/Button/Proceed_Btn'))

WebUI.scrollToElement(findTestObject('Object Repository/XXXXXXXXXXXX/Web Part/Stock_Cash_Reconciliation/Warehouse_Warn_Message'), 0)

WebUI.verifyElementPresent(findTestObject('Object Repository/XXXXXXXXXXXX/Web Part/Stock_Cash_Reconciliation/Warehouse_Warn_Message'), 0)

WebUI.takeScreenshot()

Warehouse_WN_Text = WebUI.getText(findTestObject('Object Repository/XXXXXXXXXXXX/Web Part/Stock_Cash_Reconciliation/Warehouse_Warn_Message'))

KeywordUtil.logInfo(Warehouse_WN_Text)

WebUI.verifyMatch(findTestData('VBL_Web Input Data/Stock_Cash_Reconciliation').getValue('Warn_Message', 1), Warehouse_WN_Text, false)

WebUI.click(findTestObject('XXXXXXXXXXXX/Web Part/Stock_Cash_Reconciliation/Ware house'))

GlobalVariable.label = findTestData('VBL_Web Input Data/Stock_Cash_Reconciliation').getValue('WareHouse', 1)

WebUI.click(findTestObject('XXXXXXXXXXXX/Web Part/Stock Allocation/Global Variables/DropdownSelect_Li Tag'))

WebUI.verifyElementPresent(findTestObject('XXXXXXXXXXXX/Web Part/Stock_Cash_Reconciliation/Van'), 0)

Van = WebUI.getText(findTestObject('XXXXXXXXXXXX/Web Part/Stock_Cash_Reconciliation/Van'))

KeywordUtil.logInfo(Van)

WebUI.verifyElementPresent(findTestObject('XXXXXXXXXXXX/Web Part/Stock_Cash_Reconciliation/TripId'), 0)

WebUI.takeScreenshot()

WebUI.click(findTestObject('XXXXXXXXXXXX/Web Part/Stock_Cash_Reconciliation/TripId'))

GlobalVariable.label = GlobalVariable.referenceno

WebUI.click(findTestObject('XXXXXXXXXXXX/Web Part/Stock Allocation/Global Variables/DropdownSelect_Li Tag'))

Trip_Td = WebUI.getText(findTestObject('XXXXXXXXXXXX/Web Part/Stock_Cash_Reconciliation/TripId'))

KeywordUtil.logInfo(Trip_Td)

//GlobalVariable.referenceno = '29705192024193115'
GlobalVariable.referenceno

WebUI.verifyMatch(Trip_Td, GlobalVariable.referenceno, false)

WebUI.scrollToElement(findTestObject('XXXXXXXXXXXX/Web Part/Stock_Cash_Reconciliation/Button/Proceed_Btn'), 0)

WebUI.verifyElementPresent(findTestObject('XXXXXXXXXXXX/Web Part/Stock_Cash_Reconciliation/Button/Proceed_Btn'), 0)

WebUI.click(findTestObject('XXXXXXXXXXXX/Web Part/Stock_Cash_Reconciliation/Button/Proceed_Btn'))

WebUI.waitForElementNotVisible(findTestObject('XXXXXXXXXXXX/Web Part/Re-usable/Page Load'), 50, FailureHandling.STOP_ON_FAILURE)

WebUI.verifyElementPresent(findTestObject('XXXXXXXXXXXX/Web Part/Stock_Cash_Reconciliation/Grids/SKU_search_field'), 0)

WebUI.setText(findTestObject('XXXXXXXXXXXX/Web Part/Stock_Cash_Reconciliation/Grids/SKU_search_field'), findTestData('VBL_Web Input Data/Stock_Cash_Reconciliation').getValue('SKU', 1))

WebUI.takeScreenshot()

GlobalVariable.S_Sku_Name = findTestData('VBL_Web Input Data/Stock_Cash_Reconciliation').getValue('SKU', 1)

GlobalVariable.label = findTestData('VBL_Web Input Data/Stock_Cash_Reconciliation').getValue('Index', 1)

WebUI.verifyElementPresent(findTestObject('XXXXXXXXXXXX/Web Part/Stock_Cash_Reconciliation/Grids/Get_Quantity'), 0)

vanLoadQtyInCase = WebUI.getText(findTestObject('XXXXXXXXXXXX/Web Part/Stock_Cash_Reconciliation/Grids/Get_Case_Quantity'))

KeywordUtil.logInfo(vanLoadQtyInCase)

conversionQty = findTestData('VBL_Web Input Data/Stock_Cash_Reconciliation').getValue('Conversion_Qty', 1)

CaseIntoPieces = (Double.parseDouble(vanLoadQtyInCase) * Double.parseDouble(conversionQty))

vanloadQtyInPiece = WebUI.getText(findTestObject('XXXXXXXXXXXX/Web Part/Stock_Cash_Reconciliation/Grids/Get_Quantity'))

KeywordUtil.logInfo(vanloadQtyInPiece)

vanloadStock = (CaseIntoPieces + Double.parseDouble(vanloadQtyInPiece))

GlobalVariable.Vanload_Qty = vanloadStock

loadedStock = findTestData('VBL_Web Input Data/Stock_Cash_Reconciliation').getValue('Vanload_Qty', 1)

WebUI.verifyEqual(vanloadStock, Double.parseDouble(loadedStock))

///---
GlobalVariable.label = findTestData('VBL_Web Input Data/Stock_Cash_Reconciliation').getValue('Index', 3)

WebUI.verifyElementPresent(findTestObject('Object Repository/XXXXXXXXXXXX/Web Part/Stock_Cash_Reconciliation/Grids/Get_Quantity'), 0)

ReturnQtyInCase = WebUI.getText(findTestObject('XXXXXXXXXXXX/Web Part/Stock_Cash_Reconciliation/Grids/Get_Case_Quantity'))

KeywordUtil.logInfo(ReturnQtyInCase)

retrunCasesIntoPieces = (Double.parseDouble(ReturnQtyInCase) * Double.parseDouble(conversionQty))

returnQtyInPiece = WebUI.getText(findTestObject('Object Repository/XXXXXXXXXXXX/Web Part/Stock_Cash_Reconciliation/Grids/Get_Quantity'))

KeywordUtil.logInfo(returnQtyInPiece)

returnedQty = (retrunCasesIntoPieces + Double.parseDouble(returnQtyInPiece))

GlobalVariable.Sales_Return_Qty = returnedQty

QtyReturn = findTestData('VBL_Web Input Data/Stock_Cash_Reconciliation').getValue('Return_Qty', 1)

WebUI.verifyEqual(returnedQty, Double.parseDouble(QtyReturn))

///---Replacement
GlobalVariable.label = findTestData('VBL_Web Input Data/Stock_Cash_Reconciliation').getValue('Index', 8)

WebUI.verifyElementPresent(findTestObject('Object Repository/XXXXXXXXXXXX/Web Part/Stock_Cash_Reconciliation/Grids/Get_Quantity'), 0)

ReplaceQtyInCase = WebUI.getText(findTestObject('XXXXXXXXXXXX/Web Part/Stock_Cash_Reconciliation/Grids/Get_Case_Quantity'))

KeywordUtil.logInfo(ReplaceQtyInCase)

conversionQty = findTestData('VBL_Web Input Data/Stock_Cash_Reconciliation').getValue('Conversion_Qty', 1)

ReplaceCasesIntoPieces = (Double.parseDouble(ReturnQtyInCase) * Double.parseDouble(conversionQty))

ReplaceQtyInPiece = WebUI.getText(findTestObject('Object Repository/XXXXXXXXXXXX/Web Part/Stock_Cash_Reconciliation/Grids/Get_Quantity'))

KeywordUtil.logInfo(ReplaceQtyInPiece)

ReplacedQty = (ReplaceCasesIntoPieces + Double.parseDouble(ReplaceQtyInPiece))

Replacement_Qty = findTestData('VBL_Web Input Data/Stock_Cash_Reconciliation').getValue('Replacement_Qty', 1)

WebUI.verifyEqual(ReplacedQty, Double.parseDouble(Replacement_Qty))

///unload
GlobalVariable.label = findTestData('VBL_Web Input Data/Stock_Cash_Reconciliation').getValue('Index', 4)

WebUI.verifyElementPresent(findTestObject('Object Repository/XXXXXXXXXXXX/Web Part/Stock_Cash_Reconciliation/Grids/Get_Quantity'), 0)

vanClose_StockInCase = WebUI.getText(findTestObject('Object Repository/XXXXXXXXXXXX/Web Part/Stock_Cash_Reconciliation/Grids/Get_Case_Quantity'))

KeywordUtil.logInfo(vanClose_StockInCase)

vanClose_StockCaseIntoPieces = (Double.parseDouble(vanClose_StockInCase) * Double.parseDouble(conversionQty))

vanClose_StockInPiece = WebUI.getText(findTestObject('Object Repository/XXXXXXXXXXXX/Web Part/Stock_Cash_Reconciliation/Grids/Get_Quantity'))

KeywordUtil.logInfo(vanClose_StockInPiece)

vanClose_Stock = (vanClose_StockCaseIntoPieces + Double.parseDouble(vanClose_StockInPiece))

GlobalVariable.Van_Unload_Qty = vanClose_Stock

stockWhileClosing = findTestData('VBL_Web Input Data/Stock_Cash_Reconciliation').getValue('Unload_Qty', 1)

WebUI.verifyEqual(vanClose_Stock, stockWhileClosing)

WebUI.verifyElementPresent(findTestObject('Object Repository/XXXXXXXXXXXX/Web Part/Stock_Cash_Reconciliation/Grids/Actual_Stock_case'), 10)

actualStockinCase = WebUI.getAttribute(findTestObject('Object Repository/XXXXXXXXXXXX/Web Part/Stock_Cash_Reconciliation/Grids/Actual_Stock_case'), 'title')

KeywordUtil.logInfo(actualStockinCase)

actualStockCaseIntoPiece = (Double.parseDouble(actualStockinCase) * Double.parseDouble(conversionQty))

KeywordUtil.logInfo('qty after converting to piece : ' + actualStockCaseIntoPiece)

actualStockinPiece = WebUI.getAttribute(findTestObject('Object Repository/XXXXXXXXXXXX/Web Part/Stock_Cash_Reconciliation/Grids/Actual_Stock_piece'), 'title')

KeywordUtil.logInfo(actualStockinPiece)

actualStock = (actualStockCaseIntoPiece + Double.parseDouble(actualStockinPiece))

KeywordUtil.logInfo('total actual stock : ' + actualStock)

QtywhileUnload = findTestData('VBL_Web Input Data/Stock_Cash_Reconciliation').getValue('Unload_Qty', 1)

WebUI.verifyEqual(actualStock, Double.parseDouble(QtywhileUnload))

additionalstock = (returnedQty + actualStock)

actualStocktoEnter = Math.round(additionalstock).toString()

WebUI.doubleClick(findTestObject('Object Repository/XXXXXXXXXXXX/Web Part/Stock_Cash_Reconciliation/Grids/Actual_Stock_case_input'))

WebUI.sendKeys(findTestObject('Object Repository/XXXXXXXXXXXX/Web Part/Stock_Cash_Reconciliation/Grids/Actual_Stock_case_input'), Keys.chord(Keys.BACK_SPACE))

WebUI.click(findTestObject('Object Repository/XXXXXXXXXXXX/Web Part/Stock_Cash_Reconciliation/Grids/Actual_Stock_piece_input'))

WebUI.doubleClick(findTestObject('XXXXXXXXXXXX/Web Part/Stock_Cash_Reconciliation/Grids/Actual_Stock_piece_input'))

WebUI.sendKeys(findTestObject('Object Repository/XXXXXXXXXXXX/Web Part/Stock_Cash_Reconciliation/Grids/Actual_Stock_piece_input'), Keys.chord(Keys.BACK_SPACE))

WebUI.click(findTestObject('Object Repository/XXXXXXXXXXXX/Web Part/Stock_Cash_Reconciliation/Grids/Actual_Stock_case'))

WebUI.delay(3)

Variance_QtyNegative = WebUI.getText(findTestObject('XXXXXXXXXXXX/Web Part/Stock_Cash_Reconciliation/Grids/Variance'))

QtyNegative = Double.parseDouble(Variance_QtyNegative)

WebUI.verifyGreaterThan(0, QtyNegative, FailureHandling.STOP_ON_FAILURE)

AmountNegative = WebUI.getText(findTestObject('XXXXXXXXXXXX/Web Part/Stock_Cash_Reconciliation/Grids/Amount'))

AmtNegative = Double.parseDouble(AmountNegative)

WebUI.verifyGreaterThan(0, AmtNegative, FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('XXXXXXXXXXXX/Web Part/Stock_Cash_Reconciliation/Grids/Actual_Stock_piece_input'))

GlobalVariable.Actual_Stock_Qty = actualStocktoEnter

WebUI.setText(findTestObject('XXXXXXXXXXXX/Web Part/Stock_Cash_Reconciliation/Grids/Actual_Stock_piece_input'), actualStocktoEnter)

WebUI.click(findTestObject('Object Repository/XXXXXXXXXXXX/Web Part/Stock_Cash_Reconciliation/Grids/Actual_Stock_case'))

WebUI.verifyElementPresent(findTestObject('XXXXXXXXXXXX/Web Part/Stock_Cash_Reconciliation/Grids/Variance'), 0)

Variance_Qty = WebUI.getText(findTestObject('XXXXXXXXXXXX/Web Part/Stock_Cash_Reconciliation/Grids/Variance'))

GlobalVariable.Variance_Qty = Variance_Qty

KeywordUtil.logInfo(Variance_Qty)

WebUI.verifyElementPresent(findTestObject('XXXXXXXXXXXX/Web Part/Stock_Cash_Reconciliation/Grids/Amount'), 0)

Amount_Qty = WebUI.getText(findTestObject('XXXXXXXXXXXX/Web Part/Stock_Cash_Reconciliation/Grids/Amount'))

GlobalVariable.Amount_Qty = Amount_Qty

KeywordUtil.logInfo(Amount_Qty)

WebUI.scrollToElement(findTestObject('XXXXXXXXXXXX/Web Part/Stock_Cash_Reconciliation/Button/Reconcile_Btn'), 0)

WebUI.click(findTestObject('XXXXXXXXXXXX/Web Part/Stock_Cash_Reconciliation/Button/Reconcile_Btn'))

WebUI.takeScreenshot()

WebUI.verifyElementPresent(findTestObject('Object Repository/XXXXXXXXXXXX/Web Part/Stock_Cash_Reconciliation/Button/OK_Btn'), 5)

WebUI.click(findTestObject('Object Repository/XXXXXXXXXXXX/Web Part/Stock_Cash_Reconciliation/Button/OK_Btn'))

WebUI.takeScreenshot()

WebUI.waitForElementNotVisible(findTestObject('XXXXXXXXXXXX/Web Part/Re-usable/Page Load'), 50, FailureHandling.STOP_ON_FAILURE)

WebUI.refresh()

'Stock Reconcilation View screen data validation'
WebUI.callTestCase(findTestCase('VBL_Mobile/Re-Usables/Web Login and Navigation/Navigate to Stock Reconciliation View'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Object Repository/XXXXXXXXXXXX/Web Part/Stock_Cash_Reconciliation/van_Drop_Down'))

WebUI.waitForElementNotVisible(findTestObject('XXXXXXXXXXXX/Web Part/Re-usable/Page Load'), 100)

WebUI.setText(findTestObject('Object Repository/XXXXXXXXXXXX/Web Part/Stock_Cash_Reconciliation/Select_van'), findTestData('VBL_Web Input Data/Stock_Cash_Reconciliation').getValue('Van', 1))

GlobalVariable.label = findTestData('VBL_Web Input Data/Stock_Cash_Reconciliation').getValue('Van', 1)

WebUI.click(findTestObject('Object Repository/XXXXXXXXXXXX/Web Part/Stock_Cash_Reconciliation/li_tag'))

WebUI.waitForElementNotVisible(findTestObject('XXXXXXXXXXXX/Web Part/Re-usable/Page Load'), 100)

GlobalVariable.label = findTestData('VBL_Web Input Data/Stock_Cash_Reconciliation').getValue('Label', 1)

WebUI.click(findTestObject('Object Repository/XXXXXXXXXXXX/Web Part/Stock_Cash_Reconciliation/label'))

//route
WebUI.click(findTestObject('Object Repository/XXXXXXXXXXXX/Web Part/Stock_Cash_Reconciliation/route_Drop_Down'))

WebUI.waitForElementNotVisible(findTestObject('XXXXXXXXXXXX/Web Part/Re-usable/Page Load'), 100)

WebUI.setText(findTestObject('Object Repository/XXXXXXXXXXXX/Web Part/Stock_Cash_Reconciliation/Select_route'), findTestData('VBL_Web Input Data/Stock_Cash_Reconciliation').getValue('Route', 1))

GlobalVariable.label = findTestData('VBL_Web Input Data/Stock_Cash_Reconciliation').getValue('Route', 1)

WebUI.click(findTestObject('Object Repository/XXXXXXXXXXXX/Web Part/Stock_Cash_Reconciliation/li_tag'))

WebUI.waitForElementNotVisible(findTestObject('XXXXXXXXXXXX/Web Part/Re-usable/Page Load'), 100)

GlobalVariable.label = findTestData('VBL_Web Input Data/Stock_Cash_Reconciliation').getValue('Label', 1)

WebUI.click(findTestObject('Object Repository/XXXXXXXXXXXX/Web Part/Stock_Cash_Reconciliation/label'))

//salesperson
WebUI.click(findTestObject('Object Repository/XXXXXXXXXXXX/Web Part/Stock_Cash_Reconciliation/Sales_Person_Drop_Down'))

WebUI.waitForElementNotVisible(findTestObject('XXXXXXXXXXXX/Web Part/Re-usable/Page Load'), 100)

WebUI.setText(findTestObject('Object Repository/XXXXXXXXXXXX/Web Part/Stock_Cash_Reconciliation/Select_sales_person'), findTestData('VBL_Web Input Data/Stock_Cash_Reconciliation').getValue('Seller', 1))

GlobalVariable.label = findTestData('VBL_Web Input Data/Stock_Cash_Reconciliation').getValue('Seller', 1)

WebUI.click(findTestObject('Object Repository/XXXXXXXXXXXX/Web Part/Stock_Cash_Reconciliation/li_tag'))

WebUI.waitForElementNotVisible(findTestObject('XXXXXXXXXXXX/Web Part/Re-usable/Page Load'), 100)

WebUI.takeScreenshot()

GlobalVariable.label = findTestData('VBL_Web Input Data/Stock_Cash_Reconciliation').getValue('Label', 1)

WebUI.scrollToElement(findTestObject('Object Repository/XXXXXXXXXXXX/Web Part/Stock_Cash_Reconciliation/label'), 5)

WebUI.click(findTestObject('Object Repository/XXXXXXXXXXXX/Web Part/Stock_Cash_Reconciliation/label'))

WebUI.waitForElementNotVisible(findTestObject('XXXXXXXXXXXX/Web Part/Re-usable/Page Load'), 100)

//status
WebUI.click(findTestObject('Object Repository/XXXXXXXXXXXX/Web Part/Stock_Cash_Reconciliation/select_status'))

WebUI.click(findTestObject('Object Repository/XXXXXXXXXXXX/Web Part/Stock_Cash_Reconciliation/Status_dropdown'))

WebUI.delay(2)

//search
GlobalVariable.label = findTestData('VBL_Web Input Data/Stock_Cash_Reconciliation').getValue('onclick', 1)

WebUI.click(findTestObject('Object Repository/XXXXXXXXXXXX/Web Part/Stock_Cash_Reconciliation/Button/global_button'))

WebUI.waitForElementNotVisible(findTestObject('XXXXXXXXXXXX/Web Part/Re-usable/Page Load'), 100)

WebUI.takeScreenshot()

//filter_icon
WebUI.scrollToElement(findTestObject('Object Repository/XXXXXXXXXXXX/Web Part/Stock_Cash_Reconciliation/Filter_Icon'), 10)

WebUI.click(findTestObject('Object Repository/XXXXXXXXXXXX/Web Part/Stock_Cash_Reconciliation/Filter_Icon'))

WebUI.setText(findTestObject('Object Repository/XXXXXXXXXXXX/Web Part/Stock_Cash_Reconciliation/Filter_trip_no'), GlobalVariable.referenceno)

//apply_filter
WebUI.scrollToElement(findTestObject('Object Repository/XXXXXXXXXXXX/Web Part/Stock_Cash_Reconciliation/apply_button'), 10)

WebUI.click(findTestObject('Object Repository/XXXXXXXXXXXX/Web Part/Stock_Cash_Reconciliation/apply_button'))

WebUI.delay(2)

WebUI.takeScreenshot()

trip_id = WebUI.getText(findTestObject('Object Repository/XXXXXXXXXXXX/Web Part/Stock_Cash_Reconciliation/trip_id_grid'))

KeywordUtil.logInfo(trip_id)

WebUI.verifyElementVisible(findTestObject('Object Repository/XXXXXXXXXXXX/Web Part/Stock_Cash_Reconciliation/trip_id_grid'), FailureHandling.STOP_ON_FAILURE)

String conn = (((((('jdbc:sqlserver://' + GlobalVariable.DB_URL) + ';databaseName=') + GlobalVariable.DB_Name) + ';user=') + GlobalVariable.DB_Username) + ';password=') + GlobalVariable.DB_Password

queryString = (((('Select top 1 * from AppData_Trip where T_Start_Date = \'' + GlobalVariable.sDate) + '\' and T_SU_Id= \'') + GlobalVariable.vanseller_user_id) + '\' order by 1 desc')

KeywordUtil.logInfo(queryString)

connection = DriverManager.getConnection(conn)

Statement stm1 = connection.createStatement()

ResultSet rs1 = stm1.executeQuery(queryString)

def recordSet1 = rs1

if (recordSet1.next()) {
    String ASRH_Id = recordSet1.getObject('T_Id')

    KeywordUtil.logInfo(GlobalVariable.label = ASRH_Id)
} else {
    'If data is empty in the DB, then the flow must fail'
    Assert.fail()
}

WebUI.takeScreenshot()

queryString = (((((('Select top 1 * from AppData_Stock_Reconcilation_Header where ASRH_Trip_Id = \'' + GlobalVariable.label) + '\' and  ASRH_Date = \'') + GlobalVariable.sDate) + '\' and ASRH_AUH_Id = \'') + GlobalVariable.vanseller_user_id) + '\' order by 1 desc')

connection = DriverManager.getConnection(conn)

Statement stm = connection.createStatement()

ResultSet rs = stm.executeQuery(queryString)

def recordSet = rs

if (recordSet.next()) {
    'SRV screen out grid data validation for Reference number, Trip Id, variance amount'
    Object ASRH_RefNo = recordSet.getObject('ASRH_RefNo')

    WebUI.verifyElementText(findTestObject('Object Repository/XXXXXXXXXXXX/Web Part/Stock_Cash_Reconciliation/RefNo_In_Grid'), ASRH_RefNo, FailureHandling.STOP_ON_FAILURE)

    Object ASRH_Net_Variance_Amount = recordSet.getObject('ASRH_Net_Variance_Amount')

    KeywordUtil.logInfo(ASRH_Net_Variance_Amount.toString())

    variance_amount = WebUI.getText(findTestObject('Object Repository/XXXXXXXXXXXX/Web Part/Stock_Cash_Reconciliation/variance_amount_grid'))

    WebUI.verifyEqual(Double.parseDouble(variance_amount), ASRH_Net_Variance_Amount, FailureHandling.STOP_ON_FAILURE)

    //View_icon
    WebUI.click(findTestObject('Object Repository/XXXXXXXXXXXX/Web Part/Stock_Cash_Reconciliation/view_icon'))

    WebUI.waitForElementNotVisible(findTestObject('XXXXXXXXXXXX/Web Part/Re-usable/Page Load'), 100)

    'SRV screen popup screen field validation for Reference number, Trip Id, variance amount'
    WebUI.takeScreenshot()

    Actual_TridID = WebUI.getAttribute(findTestObject('Object Repository/XXXXXXXXXXXX/Web Part/Stock_Cash_Reconciliation/Stock_view_PopupScrn/TripId_Field'), 'value', FailureHandling.STOP_ON_FAILURE)

    WebUI.verifyMatch(Actual_TridID, GlobalVariable.referenceno, false, FailureHandling.STOP_ON_FAILURE)

    Actual_ReferenceNo = WebUI.getAttribute(findTestObject('Object Repository/XXXXXXXXXXXX/Web Part/Stock_Cash_Reconciliation/Stock_view_PopupScrn/ReferenceNo'), 'value', FailureHandling.STOP_ON_FAILURE)

    WebUI.verifyMatch(Actual_ReferenceNo, ASRH_RefNo, false, FailureHandling.STOP_ON_FAILURE)

    Actual_Variance_Amt = WebUI.getAttribute(findTestObject('Object Repository/XXXXXXXXXXXX/Web Part/Stock_Cash_Reconciliation/Stock_view_PopupScrn/Variance_Amt'), 'value', FailureHandling.STOP_ON_FAILURE)

    WebUI.verifyEqual(Double.parseDouble(Actual_Variance_Amt), ASRH_Net_Variance_Amount)

    Object ASRH_Id = recordSet.getObject('ASRH_Id')

    GlobalVariable.label = ASRH_Id
} else {
    'If data is empty in the DB, then the flow must fail'
    Assert.fail()
}

WebUI.scrollToPosition(0, 500)

WebUI.takeScreenshot()

queryString = (('Select top 1 * from AppData_Stock_Reconcilation_Detail where ASRD_ASRH_Id = \'' + GlobalVariable.label) + '\' order by 1 desc')

connection = DriverManager.getConnection(conn)

Statement stm2 = connection.createStatement()

ResultSet rs2 = stm2.executeQuery(queryString)

def recordSet2 = rs2

if (recordSet2.next()) {
    //filter_icon
    WebUI.scrollToElement(findTestObject('Object Repository/XXXXXXXXXXXX/Web Part/Stock_Cash_Reconciliation/Filter_Icon1'), 10)

    WebUI.click(findTestObject('Object Repository/XXXXXXXXXXXX/Web Part/Stock_Cash_Reconciliation/Filter_Icon1'))

    WebUI.setText(findTestObject('Object Repository/XXXXXXXXXXXX/Web Part/Stock_Cash_Reconciliation/Filter_skuname'), findTestData('VBL_Web Input Data/Stock_Cash_Reconciliation').getValue('SKU', 1))

    //apply_filter
    WebUI.scrollToElement(findTestObject('Object Repository/XXXXXXXXXXXX/Web Part/Stock_Cash_Reconciliation/apply_button1'), 10)

    WebUI.click(findTestObject('Object Repository/XXXXXXXXXXXX/Web Part/Stock_Cash_Reconciliation/apply_button1'))

    'SRV screen popup screen grid data validation for Vanload Opening Qty, Sales return Qty, Sales Invoice Qty, Free SKU Qty, Van Unload, Variance Qty, Actual Qty, Variance Amount'

    'validation for Vanload Opening Qty'
    Object ASRD_Opening_Qty = recordSet2.getObject('ASRD_Opening_Qty')

    GlobalVariable.S_Sku_Name = findTestData('VBL_Web Input Data/Stock_Cash_Reconciliation').getValue('SKU', 1)

    GlobalVariable.label = findTestData('VBL_Web Input Data/Stock_Cash_Reconciliation').getValue('Index', 1)

    conversionQty = findTestData('VBL_Web Input Data/Stock_Cash_Reconciliation').getValue('Conversion_Qty', 1)

    VanloadCaseQtyInView = WebUI.getText(findTestObject('XXXXXXXXXXXX/Web Part/Stock_Cash_Reconciliation/Grids/Get_Case_Quantity'))

    vanloadPieceQtyinView = WebUI.getText(findTestObject('XXXXXXXXXXXX/Web Part/Stock_Cash_Reconciliation/Grids/Get_Quantity'))

    KeywordUtil.logInfo(VanloadCaseQtyInView)

    vanloadStockinView = ((Double.parseDouble(VanloadCaseQtyInView) * Double.parseDouble(conversionQty)) + Double.parseDouble(vanloadPieceQtyinView))

    KeywordUtil.logInfo(vanloadStockinView.toString())

    WebUI.verifyEqual(vanloadStockinView, ASRD_Opening_Qty)

    'validation for Sales return Qty'
    Object ASRD_Sales_Return_Qty = recordSet2.getObject('ASRD_Sales_Return_Qty')

    GlobalVariable.label = findTestData('VBL_Web Input Data/Stock_Cash_Reconciliation').getValue('Index', 8)

    WebUI.verifyElementPresent(findTestObject('Object Repository/XXXXXXXXXXXX/Web Part/Stock_Cash_Reconciliation/Grids/Get_Quantity'), 0)

    returnCaseQtyinView = WebUI.getText(findTestObject('XXXXXXXXXXXX/Web Part/Stock_Cash_Reconciliation/Grids/Get_Case_Quantity'))

    KeywordUtil.logInfo(returnCaseQtyinView)

    returnCasesIntoPieces = (Double.parseDouble(returnCaseQtyinView) * Double.parseDouble(conversionQty))

    returnPieceQtyInView = WebUI.getText(findTestObject('Object Repository/XXXXXXXXXXXX/Web Part/Stock_Cash_Reconciliation/Grids/Get_Quantity'))

    KeywordUtil.logInfo(returnPieceQtyInView)

    returnStockInView = (returnCasesIntoPieces + Double.parseDouble(returnPieceQtyInView))

    WebUI.verifyEqual(returnStockInView, ASRD_Sales_Return_Qty)

    'validation for Sales Replaced Qty'
    Object ASRD_Replaced_Qty = recordSet2.getObject('ASRD_Replaced_Qty')

    GlobalVariable.label = findTestData('VBL_Web Input Data/Stock_Cash_Reconciliation').getValue('Index', 4)

    WebUI.verifyElementPresent(findTestObject('Object Repository/XXXXXXXXXXXX/Web Part/Stock_Cash_Reconciliation/Grids/Get_Quantity'), 0)

    ReplaceCaseQtyinView = WebUI.getText(findTestObject('XXXXXXXXXXXX/Web Part/Stock_Cash_Reconciliation/Grids/Get_Case_Quantity'))

    KeywordUtil.logInfo(ReplaceCaseQtyinView)

    ReplaceCasesIntoPieces = (Double.parseDouble(ReplaceCaseQtyinView) * Double.parseDouble(conversionQty))

    ReplacePieceQtyInView = WebUI.getText(findTestObject('Object Repository/XXXXXXXXXXXX/Web Part/Stock_Cash_Reconciliation/Grids/Get_Quantity'))

    KeywordUtil.logInfo(ReplacePieceQtyInView)

    ReplaceStockInView = (ReplaceCasesIntoPieces + Double.parseDouble(ReplacePieceQtyInView))

    WebUI.verifyEqual(ReplaceStockInView, ASRD_Replaced_Qty)

    'validation for Free SKU Qty'
    Object ASRD_Free_Qty = recordSet2.getObject('ASRD_Free_Qty')

    GlobalVariable.label = findTestData('VBL_Web Input Data/Stock_Cash_Reconciliation').getValue('Index', 3)

    WebUI.verifyElementPresent(findTestObject('Object Repository/XXXXXXXXXXXX/Web Part/Stock_Cash_Reconciliation/Grids/Get_Quantity'), 0)

    freeSKUCaseQtyinView = WebUI.getText(findTestObject('XXXXXXXXXXXX/Web Part/Stock_Cash_Reconciliation/Grids/Get_Case_Quantity'))

    KeywordUtil.logInfo(freeSKUCaseQtyinView)

    conversionQty = findTestData('VBL_Web Input Data/Stock_Cash_Reconciliation').getValue('Conversion_Qty', 1)

    freeSKUCasesIntoPieces = (Double.parseDouble(freeSKUCaseQtyinView) * Double.parseDouble(conversionQty))

    KeywordUtil.logInfo(freeSKUCasesIntoPieces.toString())

    freeSKUPieceQtyInView = WebUI.getText(findTestObject('Object Repository/XXXXXXXXXXXX/Web Part/Stock_Cash_Reconciliation/Grids/Get_Quantity'))

    KeywordUtil.logInfo(freeSKUPieceQtyInView)

    freeSKUStockInView = (freeSKUCasesIntoPieces + Double.parseDouble(freeSKUPieceQtyInView))

    KeywordUtil.logInfo(freeSKUStockInView.toString())

    WebUI.verifyEqual(freeSKUStockInView, ASRD_Free_Qty)

    'validation for VanUnload Qty'
    Object ASRD_Unload_Qty = recordSet2.getObject('ASRD_Unload_Qty')

    GlobalVariable.label = findTestData('VBL_Web Input Data/Stock_Cash_Reconciliation').getValue('Index', 7)

    UnloadCaseQtyinView = WebUI.getText(findTestObject('XXXXXXXXXXXX/Web Part/Stock_Cash_Reconciliation/Grids/Get_Case_Quantity'))

    KeywordUtil.logInfo(UnloadCaseQtyinView)

    conversionQty = findTestData('VBL_Web Input Data/Stock_Cash_Reconciliation').getValue('Conversion_Qty', 1)

    UnloadCasesIntoPieces = (Double.parseDouble(UnloadCaseQtyinView) * Double.parseDouble(conversionQty))

    WebUI.verifyElementPresent(findTestObject('Object Repository/XXXXXXXXXXXX/Web Part/Stock_Cash_Reconciliation/Grids/Get_Quantity'), 0)

    Van_Unload_Qty_view = WebUI.getText(findTestObject('Object Repository/XXXXXXXXXXXX/Web Part/Stock_Cash_Reconciliation/Grids/Get_Quantity'))

    KeywordUtil.logInfo(Van_Unload_Qty_view)

    double Van_Unload_Qty = UnloadCasesIntoPieces + Double.parseDouble(Van_Unload_Qty_view)

    WebUI.verifyEqual(Van_Unload_Qty, ASRD_Unload_Qty, FailureHandling.CONTINUE_ON_FAILURE)

    'validation for Actual Qty'
    Object ASRD_Received_Qty = recordSet2.getObject('ASRD_Received_Qty')

    GlobalVariable.label = findTestData('VBL_Web Input Data/Stock_Cash_Reconciliation').getValue('Index', 6)

    actualCaseQtyInView = WebUI.getText(findTestObject('XXXXXXXXXXXX/Web Part/Stock_Cash_Reconciliation/Grids/Get_Case_Quantity'))

    conversionQty = findTestData('VBL_Web Input Data/Stock_Cash_Reconciliation').getValue('Conversion_Qty', 1)

    actualCaseQtyIntoPiecesInView = (Double.parseDouble(actualCaseQtyInView) * Double.parseDouble(conversionQty))

    actualPieceQtyInView = WebUI.getText(findTestObject('Object Repository/XXXXXXXXXXXX/Web Part/Stock_Cash_Reconciliation/Grids/Get_Quantity'))

    KeywordUtil.logInfo(actualPieceQtyInView)

    actualQtyInView = (actualCaseQtyIntoPiecesInView + Double.parseDouble(actualPieceQtyInView))

    WebUI.verifyEqual(actualQtyInView, ASRD_Received_Qty, FailureHandling.CONTINUE_ON_FAILURE)

    'validation for Variance Qty'
    Object ASRD_Variance_Qty = recordSet2.getObject('ASRD_Variance_Qty')

    GlobalVariable.label = findTestData('VBL_Web Input Data/Stock_Cash_Reconciliation').getValue('Index', 9)

    varianceCaseQtyInView = WebUI.getText(findTestObject('XXXXXXXXXXXX/Web Part/Stock_Cash_Reconciliation/Grids/Get_Case_Quantity'))

    conversionQty = findTestData('VBL_Web Input Data/Stock_Cash_Reconciliation').getValue('Conversion_Qty', 1)

    varianceCaseQtyIntoPiecesInView = (Double.parseDouble(varianceCaseQtyInView) * Double.parseDouble(conversionQty))

    Variance_PieceQty_view = WebUI.getText(findTestObject('Object Repository/XXXXXXXXXXXX/Web Part/Stock_Cash_Reconciliation/Grids/Get_Quantity'))

    VarianceQtyInview = (varianceCaseQtyIntoPiecesInView + Double.parseDouble(Variance_PieceQty_view))

    WebUI.verifyEqual(VarianceQtyInview, ASRD_Variance_Qty, FailureHandling.CONTINUE_ON_FAILURE)

    'validation for Variance_Amount'
    Object ASRD_Variance_Amount = recordSet2.getObject('ASRD_Variance_Amount')

    Amount_Qty_view = WebUI.getText(findTestObject('Object Repository/XXXXXXXXXXXX/Web Part/Stock_Cash_Reconciliation/total_variance_amount_view'))

    KeywordUtil.logInfo(Amount_Qty_view)

    WebUI.verifyEqual(Double.parseDouble(Amount_Qty_view), ASRD_Variance_Amount, FailureHandling.CONTINUE_ON_FAILURE)

    GlobalVariable.Variance_Amt = Amount_Qty_view //WebUI.closeBrowser()
    //	//van_current_stock:
    //	GlobalVariable.label = findTestData('VBL_Web Input Data/Stock_Cash_Reconciliation').getValue('Index', 7)
    //	
    //	vanUnloadCaseQtyInView = WebUI.getText(findTestObject('XXXXXXXXXXXX/Web Part/Stock_Cash_Reconciliation/Grids/Get_Case_Quantity'))
    //	
    //	vanUnloadCaseQtyIntoPiecesInView = (Double.parseDouble(vanUnloadCaseQtyInView) * Double.parseDouble(conversionQty))
    //	
    //	WebUI.verifyElementPresent(findTestObject('Object Repository/XXXXXXXXXXXX/Web Part/Stock_Cash_Reconciliation/Grids/Get_Quantity'), 10)
    //	
    //	vanUnloadPieceQtyInView = WebUI.getText(findTestObject('Object Repository/XXXXXXXXXXXX/Web Part/Stock_Cash_Reconciliation/Grids/Get_Quantity'))
    //	
    //	KeywordUtil.logInfo(vanUnloadPieceQtyInView)
    //	
    //	vanUnloadQtyInView = (vanUnloadCaseQtyIntoPiecesInView + Double.parseDouble(vanUnloadPieceQtyInView))
    //	
    //	WebUI.verifyEqual(vanUnloadQtyInView, GlobalVariable.Van_Unload_Qty, FailureHandling.CONTINUE_ON_FAILURE)
} else {
    'If data is empty in the DB, then the flow must fail'
    Assert.fail()
}

