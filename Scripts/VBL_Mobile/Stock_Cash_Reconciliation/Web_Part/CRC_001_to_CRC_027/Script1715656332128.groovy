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
import org.junit.Assert as Assert

WebUI.callTestCase(findTestCase('VBL_Mobile/Stock_Cash_Reconciliation/Call Test Case/Trip_Id_Query'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('Product_Mobile/Common/Web Login and Navigation/DistributorLogin'), [:], FailureHandling.STOP_ON_FAILURE)

Mobile.callTestCase(findTestCase('VBL_Mobile/Stock_Cash_Reconciliation/Call Test Case/Collection Acceptenance'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('Test Cases/VBL_Mobile/Re-Usables/Web Login and Navigation/Navigate to Cash Reconcilation'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.waitForElementNotVisible(findTestObject('XXXXXXXXXXXX/Web Part/Re-usable/Page Load'), 50, FailureHandling.STOP_ON_FAILURE)

WebUI.verifyElementPresent(findTestObject('XXXXXXXXXXXX/Web Part/Stock_Cash_Reconciliation/UserSelect'), 0)

WebUI.click(findTestObject('XXXXXXXXXXXX/Web Part/Stock_Cash_Reconciliation/UserSelect'))

WebUI.takeScreenshot()

WebUI.verifyElementPresent(findTestObject('XXXXXXXXXXXX/Web Part/Stock_Cash_Reconciliation/TripId'), 0)

WebUI.takeScreenshot()

WebUI.click(findTestObject('XXXXXXXXXXXX/Web Part/Stock_Cash_Reconciliation/TripId'))

//GlobalVariable.label = (GlobalVariable.referenceno = '29706032024123122')
GlobalVariable.label = GlobalVariable.referenceno

WebUI.click(findTestObject('Object Repository/XXXXXXXXXXXX/Web Part/Stock_Cash_Reconciliation/li_tag'))

WebUI.delay(3)

Trip_Td = WebUI.getText(findTestObject('XXXXXXXXXXXX/Web Part/Stock_Cash_Reconciliation/TripId'))

KeywordUtil.logInfo(Trip_Td)

WebUI.verifyMatch(Trip_Td, GlobalVariable.referenceno, false)

WebUI.scrollToElement(findTestObject('XXXXXXXXXXXX/Web Part/Stock_Cash_Reconciliation/Button/Proceed_Btn'), 0)

WebUI.verifyElementPresent(findTestObject('XXXXXXXXXXXX/Web Part/Stock_Cash_Reconciliation/Button/Proceed_Btn'), 0)

WebUI.click(findTestObject('XXXXXXXXXXXX/Web Part/Stock_Cash_Reconciliation/Button/Proceed_Btn'))

WebUI.waitForElementNotVisible(findTestObject('XXXXXXXXXXXX/Web Part/Re-usable/Page Load'), 50, FailureHandling.STOP_ON_FAILURE)

WebUI.takeScreenshot()

String conn = (((((('jdbc:sqlserver://' + GlobalVariable.DB_URL) + ';databaseName=') + GlobalVariable.DB_Name) + ';user=') + GlobalVariable.DB_Username) + ';password=') + GlobalVariable.DB_Password

GlobalVariable.Transaction_No = findTestData('VBL_Web Input Data/Stock_Cash_Reconciliation').getValue('Invoice_No', 1)

queryString = (('select * from appdata_pending_bills where pb_sih_no=\'' + GlobalVariable.Transaction_No) + '\' order by 1 desc')

connection = DriverManager.getConnection(conn)

Statement stm = connection.createStatement()

ResultSet rs = stm.executeQuery(queryString)

def recordSet = rs

if (recordSet.next()) {
    PB_Paid_Amount = recordSet.getObject('PB_Paid_Amount')

    GlobalVariable.C_ReceivedAmt = PB_Paid_Amount
} else {
    'If data is empty in the DB, then the flow must fail'
    Assert.fail()
}

//GlobalVariable.Variance_Amt = 40
String Actual_amt = GlobalVariable.C_ReceivedAmt

String VarianceAmt = GlobalVariable.Variance_Amt

'Settlement Type grid data validation'
WebUI.takeScreenshot()

Settlement_Cash_Sales = WebUI.getText(findTestObject('XXXXXXXXXXXX/Web Part/Stock_Cash_Reconciliation/cash reconcile create/SettlementType_Cash_Amt'))

WebUI.verifyEqual(Double.parseDouble(Settlement_Cash_Sales), Double.parseDouble(Actual_amt), FailureHandling.STOP_ON_FAILURE)

Settlement_Stock_Variance = WebUI.getText(findTestObject('XXXXXXXXXXXX/Web Part/Stock_Cash_Reconciliation/cash reconcile create/SettlementType_StockVariance_Amt'))

WebUI.verifyEqual(Double.parseDouble(Settlement_Stock_Variance), Double.parseDouble(VarianceAmt), FailureHandling.STOP_ON_FAILURE)

'Actual Amount Validation in the cash reconcilation screen'
WebUI.takeScreenshot()

summaryActualAmount = WebUI.getText(findTestObject('Object Repository/XXXXXXXXXXXX/Web Part/Stock_Cash_Reconciliation/cash reconcile create/summary_actual_amount'))

KeywordUtil.logInfo(summaryActualAmount)

WebUI.verifyEqual(Double.parseDouble(summaryActualAmount), Double.parseDouble(Actual_amt), FailureHandling.STOP_ON_FAILURE)

'Expected Amount Validation in the cash reconcilation screen'
WebUI.takeScreenshot()

summaryExpectedAmount = WebUI.getText(findTestObject('Object Repository/XXXXXXXXXXXX/Web Part/Stock_Cash_Reconciliation/cash reconcile create/summary_expected_amount'))

KeywordUtil.logInfo(summaryExpectedAmount)

ExpectedAmount = (Double.parseDouble(Actual_amt) - Double.parseDouble(VarianceAmt))

WebUI.verifyEqual(Double.parseDouble(summaryExpectedAmount), ExpectedAmount, FailureHandling.STOP_ON_FAILURE)

'Variance Amount Validation in the cash reconcilation screen'
WebUI.takeScreenshot()

summaryVarianceAmount = WebUI.getText(findTestObject('Object Repository/XXXXXXXXXXXX/Web Part/Stock_Cash_Reconciliation/cash reconcile create/summary_variance_amount'))

KeywordUtil.logInfo(summaryVarianceAmount)

WebUI.verifyEqual(Double.parseDouble(summaryVarianceAmount), Double.parseDouble(VarianceAmt), FailureHandling.STOP_ON_FAILURE)

'Cash row data vaidation'
conn1 = ((((((('jdbc:sqlserver://' + GlobalVariable.DB_URL) + ';databaseName=') + GlobalVariable.DB_Name) + ';user=') + GlobalVariable.DB_Username) + ';password=') + GlobalVariable.DB_Password)

GlobalVariable.Transaction_No = findTestData('VBL_Web Input Data/Stock_Cash_Reconciliation').getValue('Invoice_No', 1)

queryString = (('select * from appdata_collection_request where cr_sih_no=\'' + GlobalVariable.Transaction_No) + '\' order by 1 desc')

connection = DriverManager.getConnection(conn1)

Statement stm1 = connection.createStatement()

ResultSet rs1 = stm1.executeQuery(queryString)

def recordSet1 = rs1

while (recordSet1.next()) {
    Object CR_CHR_Id = recordSet1.getObject('CR_CHR_Id')

    GlobalVariable.label2 = CR_CHR_Id

    KeywordUtil.logInfo(CR_CHR_Id.toString())
}

queryString = (('select * from appdata_collection_Header where CH_CHR_Id=\'' + GlobalVariable.label2) + '\' order by 1 desc')

connection = DriverManager.getConnection(conn1)

Statement stm2 = connection.createStatement()

ResultSet rs2 = stm2.executeQuery(queryString)

def recordSet2 = rs2

if (recordSet2.next()) {
    Object CH_Cash_Amount = recordSet2.getObject('CH_Cash_Amount')

    //GlobalVariable.label2 =  CH_Total_Amount
    GlobalVariable.paymentType = 'Cash'

    paymentCash = WebUI.getText(findTestObject('Object Repository/XXXXXXXXXXXX/Web Part/Stock_Cash_Reconciliation/cash reconcile create/Payment_type'))

    KeywordUtil.logInfo(paymentCash)

    expectedAmountCash = WebUI.getText(findTestObject('Object Repository/XXXXXXXXXXXX/Web Part/Stock_Cash_Reconciliation/cash reconcile create/expected_amount'))

    KeywordUtil.logInfo(expectedAmountCash)

    WebUI.verifyEqual(Double.parseDouble(expectedAmountCash), CH_Cash_Amount, FailureHandling.STOP_ON_FAILURE)

    actualAmountCash = WebUI.getAttribute(findTestObject('Object Repository/XXXXXXXXXXXX/Web Part/Stock_Cash_Reconciliation/cash reconcile create/actual_amount_input'), 'data-amount-value', FailureHandling.STOP_ON_FAILURE)

    //WebUI.getText(findTestObject('Object Repository/XXXXXXXXXXXX/Web Part/Stock_Cash_Reconciliation/cash reconcile create/actual_amount_input'))
    KeywordUtil.logInfo(actualAmountCash)

    WebUI.verifyEqual(Double.parseDouble(actualAmountCash), CH_Cash_Amount, FailureHandling.STOP_ON_FAILURE)
} else {
    'If data is empty in the DB, then the flow must fail'
    Assert.fail()
}

'Cheque row data vaidation'
GlobalVariable.Transaction_No = findTestData('VBL_Web Input Data/Stock_Cash_Reconciliation').getValue('Invoice_No', 1)

queryString = (('select top 1 * from appdata_collection_request where cr_sih_no=\'' + GlobalVariable.Transaction_No) + '\' order by 1 desc')

connection = DriverManager.getConnection(conn1)

Statement stm3 = connection.createStatement()

ResultSet rs3 = stm3.executeQuery(queryString)

def recordSet3 = rs3

if (recordSet3.next()) {
    Object CR_CHR_Id = recordSet3.getObject('CR_CHR_Id')

    GlobalVariable.label2 = CR_CHR_Id

    KeywordUtil.logInfo(CR_CHR_Id.toString())
}

queryString = (('select * from appdata_collection_Header where CH_CHR_Id=\'' + GlobalVariable.label2) + '\' order by 1 desc')

connection = DriverManager.getConnection(conn1)

Statement stm4 = connection.createStatement()

ResultSet rs4 = stm4.executeQuery(queryString)

def recordSet4 = rs4

if (recordSet4.next()) {
    Object CH_Cheque_Amount = recordSet4.getObject('CH_Cheque_Amount')

    GlobalVariable.paymentType = 'Cheque'

    paymentCheque = WebUI.getText(findTestObject('Object Repository/XXXXXXXXXXXX/Web Part/Stock_Cash_Reconciliation/cash reconcile create/Payment_type'))

    KeywordUtil.logInfo(paymentCheque)

    expectedAmountCheque = WebUI.getText(findTestObject('Object Repository/XXXXXXXXXXXX/Web Part/Stock_Cash_Reconciliation/cash reconcile create/expected_amount'))

    KeywordUtil.logInfo(expectedAmountCheque)

    WebUI.verifyEqual(Double.parseDouble(expectedAmountCheque), CH_Cheque_Amount, FailureHandling.STOP_ON_FAILURE)

    actualAmountCheque = WebUI.getAttribute(findTestObject('Object Repository/XXXXXXXXXXXX/Web Part/Stock_Cash_Reconciliation/cash reconcile create/actual_amount_input'), 'data-amount-value', FailureHandling.STOP_ON_FAILURE)

    KeywordUtil.logInfo(actualAmountCheque)

    WebUI.verifyEqual(Double.parseDouble(actualAmountCheque), CH_Cheque_Amount, FailureHandling.STOP_ON_FAILURE)
} else {
    'If data is empty in the DB, then the flow must fail'
    Assert.fail()
}

'Credit Note row data vaidation'
GlobalVariable.Transaction_No = findTestData('VBL_Web Input Data/Stock_Cash_Reconciliation').getValue('Invoice_No', 1)

queryString = (('SELECT TOP 1 * FROM (SELECT TOP 2 *FROM appdata_collection_request WHERE cr_sih_no = \'' + GlobalVariable.Transaction_No) + '\' ORDER BY cr_sih_no ) AS subquery ORDER BY 1 DESC')

KeywordUtil.logInfo(queryString)

connection = DriverManager.getConnection(conn1)

Statement stm5 = connection.createStatement()

ResultSet rs5 = stm5.executeQuery(queryString)

def recordSet5 = rs5

if (recordSet5.next()) {
    Object CR_CHR_Id = recordSet5.getObject('CR_CHR_Id')

    GlobalVariable.label2 = CR_CHR_Id

    KeywordUtil.logInfo(CR_CHR_Id.toString())
} else {
    'If data is empty in the DB, then the flow must fail'
    Assert.fail()
}

queryString = (('select * from appdata_collection_Header where CH_CHR_Id=\'' + GlobalVariable.label2) + '\' order by 1 desc')

connection = DriverManager.getConnection(conn1)

Statement stm6 = connection.createStatement()

ResultSet rs6 = stm6.executeQuery(queryString)

def recordSet6 = rs6

if (recordSet6.next()) {
    Object CH_Credit_Amount = recordSet6.getObject('CH_Credit_Amount')

    GlobalVariable.paymentType = 'Credit Note'

    paymentCreditNote = WebUI.getText(findTestObject('Object Repository/XXXXXXXXXXXX/Web Part/Stock_Cash_Reconciliation/cash reconcile create/Payment_type'))

    KeywordUtil.logInfo(paymentCreditNote)

    expectedAmountCreditNote = WebUI.getText(findTestObject('Object Repository/XXXXXXXXXXXX/Web Part/Stock_Cash_Reconciliation/cash reconcile create/expected_amount'))

    KeywordUtil.logInfo(expectedAmountCreditNote)

    WebUI.verifyEqual(Double.parseDouble(expectedAmountCreditNote), CH_Credit_Amount, FailureHandling.STOP_ON_FAILURE)

    actualAmountCreditNote = WebUI.getAttribute(findTestObject('Object Repository/XXXXXXXXXXXX/Web Part/Stock_Cash_Reconciliation/cash reconcile create/actual_amount_input'), 'data-amount-value', FailureHandling.STOP_ON_FAILURE)

    KeywordUtil.logInfo(actualAmountCreditNote)

    WebUI.verifyEqual(Double.parseDouble(actualAmountCreditNote), CH_Credit_Amount, FailureHandling.STOP_ON_FAILURE)
} else {
    'If data is empty in the DB, then the flow must fail'
    Assert.fail()
}

'Validate Actual amount field by increase and decrease amount'
GlobalVariable.paymentType = 'Credit Note'

Before_summaryVarianceAmount = WebUI.getText(findTestObject('Object Repository/XXXXXXXXXXXX/Web Part/Stock_Cash_Reconciliation/cash reconcile create/summary_variance_amount'))

actualAmountCreditNote = WebUI.getAttribute(findTestObject('Object Repository/XXXXXXXXXXXX/Web Part/Stock_Cash_Reconciliation/cash reconcile create/actual_amount_input'), 'data-amount-value')

KeywordUtil.logInfo(actualAmountCreditNote)

actualAmountCreditRemoveSymbol = actualAmountCreditNote.replace('$', '')

double actualAmount = Double.parseDouble(actualAmountCreditRemoveSymbol)

double increasingValue = 5.00

double increaseAmount = actualAmount + increasingValue

KeywordUtil.logInfo('increased amount: ' + increaseAmount)

String increasedAmount = Math.round(increaseAmount).toString()

WebUI.doubleClick(findTestObject('Object Repository/XXXXXXXXXXXX/Web Part/Stock_Cash_Reconciliation/cash reconcile create/actual_amount_input'))

WebUI.sendKeys(findTestObject('XXXXXXXXXXXX/Web Part/Stock_Cash_Reconciliation/cash reconcile create/actual_amount_input'), Keys.chord(Keys.BACK_SPACE))

not_run: WebUI.clearText(findTestObject('Object Repository/XXXXXXXXXXXX/Web Part/Stock_Cash_Reconciliation/cash reconcile create/actual_amount_input'))

'check positive value in variance'
WebUI.setText(findTestObject('Object Repository/XXXXXXXXXXXX/Web Part/Stock_Cash_Reconciliation/cash reconcile create/actual_amount_input'), increasedAmount)

WebUI.click(findTestObject('Object Repository/XXXXXXXXXXXX/Web Part/Stock_Cash_Reconciliation/cash reconcile create/summary_actual_amount'))

WebUI.takeScreenshot()

varianceAmount = WebUI.getText(findTestObject('Object Repository/XXXXXXXXXXXX/Web Part/Stock_Cash_Reconciliation/cash reconcile create/variance_amount'))

varianceAmountRemoveSymbol = varianceAmount.replace('$', '')

WebUI.verifyGreaterThan(varianceAmountRemoveSymbol, 0, FailureHandling.STOP_ON_FAILURE)

'After increase the amount variance value should be increased'
WebUI.takeScreenshot()

After_summaryVarianceAmount = WebUI.getText(findTestObject('Object Repository/XXXXXXXXXXXX/Web Part/Stock_Cash_Reconciliation/cash reconcile create/summary_variance_amount'))

KeywordUtil.logInfo(After_summaryVarianceAmount)

WebUI.verifyNotEqual(Double.parseDouble(After_summaryVarianceAmount), Double.parseDouble(Before_summaryVarianceAmount))

VarianceAmt = (Double.parseDouble(Before_summaryVarianceAmount) + increasingValue)

WebUI.verifyEqual(Double.parseDouble(After_summaryVarianceAmount), VarianceAmt, FailureHandling.STOP_ON_FAILURE)

GlobalVariable.paymentType = 'Cash'

Before_summaryVarianceAmount = WebUI.getText(findTestObject('Object Repository/XXXXXXXXXXXX/Web Part/Stock_Cash_Reconciliation/cash reconcile create/summary_variance_amount'))

actualAmountCash = WebUI.getAttribute(findTestObject('Object Repository/XXXXXXXXXXXX/Web Part/Stock_Cash_Reconciliation/cash reconcile create/actual_amount_input'), 'data-amount-value')

KeywordUtil.logInfo(actualAmountCash)

actualAmountCashRemoveSymbol = actualAmountCash.replace('$', '')

double actualAmtCash = Double.parseDouble(actualAmountCashRemoveSymbol)

double decreasingValue = 2.00

double decreasedAmount = actualAmtCash - decreasingValue

KeywordUtil.logInfo('increased amount: ' + decreasedAmount)

String decreasedAmt = Math.round(decreasedAmount).toString()

WebUI.doubleClick(findTestObject('Object Repository/XXXXXXXXXXXX/Web Part/Stock_Cash_Reconciliation/cash reconcile create/actual_amount_input'))

WebUI.sendKeys(findTestObject('XXXXXXXXXXXX/Web Part/Stock_Cash_Reconciliation/cash reconcile create/actual_amount_input'), Keys.chord(Keys.BACK_SPACE))

not_run: WebUI.clearText(findTestObject('Object Repository/XXXXXXXXXXXX/Web Part/Stock_Cash_Reconciliation/cash reconcile create/actual_amount_input'))

'check negative value in variance'
WebUI.setText(findTestObject('Object Repository/XXXXXXXXXXXX/Web Part/Stock_Cash_Reconciliation/cash reconcile create/actual_amount_input'), decreasedAmt)

WebUI.click(findTestObject('Object Repository/XXXXXXXXXXXX/Web Part/Stock_Cash_Reconciliation/cash reconcile create/summary_actual_amount'))

WebUI.takeScreenshot()

varianceAmount = WebUI.getText(findTestObject('Object Repository/XXXXXXXXXXXX/Web Part/Stock_Cash_Reconciliation/cash reconcile create/variance_amount'))

varianceAmountRemoveSymbol = varianceAmount.replace('$', '')

WebUI.verifyLessThan(varianceAmountRemoveSymbol, 0, FailureHandling.STOP_ON_FAILURE)

'After decreasing the amount variance value should be decreased'
WebUI.takeScreenshot()

After_summaryVarianceAmount = WebUI.getText(findTestObject('Object Repository/XXXXXXXXXXXX/Web Part/Stock_Cash_Reconciliation/cash reconcile create/summary_variance_amount'))

KeywordUtil.logInfo(After_summaryVarianceAmount)

WebUI.verifyNotEqual(Double.parseDouble(After_summaryVarianceAmount), Double.parseDouble(Before_summaryVarianceAmount))

VarianceAmt = (Double.parseDouble(Before_summaryVarianceAmount) - decreasingValue)

WebUI.verifyEqual(Double.parseDouble(After_summaryVarianceAmount), VarianceAmt, FailureHandling.STOP_ON_FAILURE)

'// ---cash reconcile - cancel confirmation popup'
WebUI.click(findTestObject('Object Repository/XXXXXXXXXXXX/Web Part/Stock_Cash_Reconciliation/cash reconcile create/Back_button'))

WebUI.waitForElementVisible(findTestObject('Object Repository/XXXXXXXXXXXX/Web Part/Stock_Cash_Reconciliation/cash reconcile create/cancel_confirm_popup'), 20)

WebUI.verifyElementPresent(findTestObject('Object Repository/XXXXXXXXXXXX/Web Part/Stock_Cash_Reconciliation/cash reconcile create/cancel_confirm_popup'), 5)

WebUI.takeScreenshot()

WebUI.click(findTestObject('Object Repository/XXXXXXXXXXXX/Web Part/Stock_Cash_Reconciliation/cash reconcile create/cancel_button'))

//---cash reconcile submit
WebUI.scrollToElement(findTestObject('XXXXXXXXXXXX/Web Part/Stock_Cash_Reconciliation/Button/Reconcile_Btn'), 0)

WebUI.click(findTestObject('XXXXXXXXXXXX/Web Part/Stock_Cash_Reconciliation/Button/Reconcile_Btn'))

WebUI.takeScreenshot()

WebUI.verifyElementPresent(findTestObject('Object Repository/XXXXXXXXXXXX/Web Part/Stock_Cash_Reconciliation/Button/OK_Btn'), 5)

WebUI.click(findTestObject('Object Repository/XXXXXXXXXXXX/Web Part/Stock_Cash_Reconciliation/Button/OK_Btn'))

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
WebUI.refresh()


WebUI.callTestCase(findTestCase('Test Cases/VBL_Mobile/Re-Usables/Web Login and Navigation/Navigate to Cash Reconciliation View'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.takeScreenshot()

WebUI.delay(5)

WebUI.click(findTestObject('XXXXXXXXXXXX/Web Part/Stock_Cash_Reconciliation/Salesperson_Dropdown'))

GlobalVariable.label = findTestData('VBL_Web Input Data/Stock_Cash_Reconciliation').getValue('Seller', 1)

WebUI.click(findTestObject('XXXXXXXXXXXX/Web Part/Stock_Cash_Reconciliation/Salesperson_Dropdown_Value'))

WebUI.click(findTestObject('XXXXXXXXXXXX/Web Part/Stock_Cash_Reconciliation/Button/Search_Button'))

WebUI.click(findTestObject('XXXXXXXXXXXX/Web Part/Stock_Cash_Reconciliation/Filter_Icon'))

Date date = new Date()

SimpleDateFormat formatter = new SimpleDateFormat('dd-MMM-yyyy')

String strDate = formatter.format(date)

System.out.println(strDate)

WebUI.sendKeys(findTestObject('XXXXXXXXXXXX/Web Part/Stock_Cash_Reconciliation/Filter_TripStartDate_Field'), strDate)

WebUI.click(findTestObject('XXXXXXXXXXXX/Web Part/Stock_Cash_Reconciliation/Button/Filter_Apply_Button'))

WebUI.takeScreenshot()

WebUI.click(findTestObject('XXXXXXXXXXXX/Web Part/Stock_Cash_Reconciliation/Grids/Select_GridRow'))

WebUI.click(findTestObject('XXXXXXXXXXXX/Web Part/Stock_Cash_Reconciliation/Button/View_Button'))

WebUI.scrollToElement(findTestObject('XXXXXXXXXXXX/Web Part/Stock_Cash_Reconciliation/TripRef_Number'), 5)

WebUI.takeScreenshot()

TripRef_NumberView = WebUI.getAttribute(findTestObject('XXXXXXXXXXXX/Web Part/Stock_Cash_Reconciliation/TripRef_Number'), 'value')

KeywordUtil.logInfo(TripRef_NumberView)

WebUI.verifyMatch(GlobalVariable.referenceno, TripRef_NumberView, false, FailureHandling.STOP_ON_FAILURE)

Mobile.callTestCase(findTestCase('Product_Mobile/Common/DatePicker_and _Navigation/DB_Currentdate'), [:], FailureHandling.STOP_ON_FAILURE)

queryString = (((('select top 1 * from AppData_Cash_Reconciliation_Header where ACRH_Period_From  = \'' + GlobalVariable.sDate) + '\'  and ACRH_AUH_Id = \'') + GlobalVariable.vanseller_user_id) + '\' order by 1 desc')

KeywordUtil.logInfo(queryString)

conn1 = (((((('jdbc:sqlserver://' + GlobalVariable.DB_URL) + ';databaseName=') + GlobalVariable.DB_Name) + ';user=') + GlobalVariable.DB_Username) + ';password=') + GlobalVariable.DB_Password

connection = DriverManager.getConnection(conn1)

Statement stm7 = connection.createStatement()

ResultSet rs7 = stm7.executeQuery(queryString)

def recordSet7 = rs7

if (recordSet7.next()) {
    Object ACRH_Amount = recordSet7.getObject('ACRH_Amount')

    WebUI.takeScreenshot()

    TotalVariance_AmountView = WebUI.getAttribute(findTestObject('XXXXXXXXXXXX/Web Part/Stock_Cash_Reconciliation/TotalVariance_Amount'), 'value')

    KeywordUtil.logInfo(TotalVariance_AmountView)

    WebUI.verifyEqual(Double.parseDouble(TotalVariance_AmountView), ACRH_Amount, FailureHandling.STOP_ON_FAILURE)

    WebUI.takeScreenshot()

    Object ACRH_Id = recordSet7.getObject('ACRH_Id')

    GlobalVariable.label2 = ACRH_Id
} else {
    'If data is empty in the DB, then the flow must fail'
    Assert.fail()
}

'Grid row data validation'
queryString = (('select * from AppData_Cash_Reconciliation_Detail where ACRD_ACRH_ID=\'' + GlobalVariable.label2) + '\' order by 1 desc')

KeywordUtil.logInfo(queryString)

connection = DriverManager.getConnection(conn1)

Statement stm8 = connection.createStatement()

ResultSet rs8 = stm8.executeQuery(queryString)

def recordSet8 = rs8

if (recordSet8.next()) {
    Object ACRD_Amount = recordSet8.getObject('ACRD_Amount')

    GlobalVariable.paymentType = 'Cash'

    CollectedAmountView = WebUI.getAttribute(findTestObject('XXXXXXXXXXXX/Web Part/Stock_Cash_Reconciliation/Grids/Collected_Amount'), 'title')

    KeywordUtil.logInfo(CollectedAmountView)

    WebUI.verifyEqual(Double.parseDouble(CollectedAmountView), ACRD_Amount, FailureHandling.STOP_ON_FAILURE)

    Object ACRD_Acutal_Amount = recordSet8.getObject('ACRD_Acutal_Amount')

    ActualAmountView = WebUI.getAttribute(findTestObject('XXXXXXXXXXXX/Web Part/Stock_Cash_Reconciliation/Grids/Actual_Amount'), 'title')

    KeywordUtil.logInfo(ActualAmountView)

    WebUI.verifyEqual(Double.parseDouble(ActualAmountView), ACRD_Acutal_Amount, FailureHandling.STOP_ON_FAILURE)

    Object ACRD_Variance_Amount = recordSet8.getObject('ACRD_Variance_Amount')

    VarianceAmountView = WebUI.getAttribute(findTestObject('XXXXXXXXXXXX/Web Part/Stock_Cash_Reconciliation/Grids/Variance_Amount'), 'title')

    KeywordUtil.logInfo(VarianceAmountView)

    WebUI.verifyEqual(Double.parseDouble(VarianceAmountView), ACRD_Variance_Amount, FailureHandling.STOP_ON_FAILURE)

    Object ACRD_Payment_Type_Lov_Id = recordSet8.getObject('ACRD_Payment_Type_Lov_Id')

    queryString = (('select * from adm_lovs where alov_id=\'' + ACRD_Payment_Type_Lov_Id) + '\'')

    connection = DriverManager.getConnection(conn1)

    Statement stm9 = connection.createStatement()

    ResultSet rs9 = stm9.executeQuery(queryString)

    def recordSet9 = rs9

    if (recordSet9.next()) {
        Object ALOV_Name = recordSet9.getObject('ALOV_Name')

        PaymentModesView = WebUI.getAttribute(findTestObject('XXXXXXXXXXXX/Web Part/Stock_Cash_Reconciliation/Grids/Payment_Mode'), 'title')

        KeywordUtil.logInfo(PaymentModesView)

        WebUI.verifyMatch(PaymentModesView, ALOV_Name, false)
    }
} else {
    'If data is empty in the DB, then the flow must fail'
    Assert.fail()
}

queryString = (('select Top 1 * from AppData_Cash_Reconciliation_Detail where ACRD_ACRH_ID=\'' + GlobalVariable.label2) + '\'')

KeywordUtil.logInfo(queryString)

connection = DriverManager.getConnection(conn1)

Statement stm11 = connection.createStatement()

ResultSet rs11 = stm11.executeQuery(queryString)

def recordSet11 = rs11

if (recordSet11.next()) {
    Object ACRD_Amount = recordSet11.getObject('ACRD_Amount')

    GlobalVariable.paymentType = 'Cheque'

    CollectedAmountView = WebUI.getAttribute(findTestObject('XXXXXXXXXXXX/Web Part/Stock_Cash_Reconciliation/Grids/Collected_Amount'), 'title')

    KeywordUtil.logInfo(CollectedAmountView)

    WebUI.verifyEqual(Double.parseDouble(CollectedAmountView), ACRD_Amount, FailureHandling.STOP_ON_FAILURE)

    Object ACRD_Acutal_Amount = recordSet11.getObject('ACRD_Acutal_Amount')

    ActualAmountView = WebUI.getAttribute(findTestObject('XXXXXXXXXXXX/Web Part/Stock_Cash_Reconciliation/Grids/Actual_Amount'), 'title')

    KeywordUtil.logInfo(ActualAmountView)

    WebUI.verifyEqual(Double.parseDouble(ActualAmountView), ACRD_Acutal_Amount, FailureHandling.STOP_ON_FAILURE)

    Object ACRD_Variance_Amount = recordSet11.getObject('ACRD_Variance_Amount')

    VarianceAmountView = WebUI.getAttribute(findTestObject('XXXXXXXXXXXX/Web Part/Stock_Cash_Reconciliation/Grids/Variance_Amount'), 'title')

    KeywordUtil.logInfo(VarianceAmountView)

    WebUI.verifyEqual(Double.parseDouble(VarianceAmountView), ACRD_Variance_Amount, FailureHandling.STOP_ON_FAILURE)

    Object ACRD_Payment_Type_Lov_Id = recordSet11.getObject('ACRD_Payment_Type_Lov_Id')

    queryString = (('select * from adm_lovs where alov_id=\'' + ACRD_Payment_Type_Lov_Id) + '\'')

    connection = DriverManager.getConnection(conn1)

    Statement stm22 = connection.createStatement()

    ResultSet rs22 = stm22.executeQuery(queryString)

    def recordSet22 = rs22

    if (recordSet22.next()) {
        Object ALOV_Name = recordSet22.getObject('ALOV_Name')

        PaymentModesView = WebUI.getAttribute(findTestObject('XXXXXXXXXXXX/Web Part/Stock_Cash_Reconciliation/Grids/Payment_Mode'), 'title')

        KeywordUtil.logInfo(PaymentModesView)

        WebUI.verifyMatch(PaymentModesView, ALOV_Name, false)
    }
} else {
    'If data is empty in the DB, then the flow must fail'
    Assert.fail()
}

queryString = (('SELECT TOP 1 * FROM (select top 2 * from AppData_Cash_Reconciliation_Detail where ACRD_ACRH_ID=\'' + GlobalVariable.label2) + '\' order by ACRD_ID)  AS subquery ORDER BY 1 DESC')

KeywordUtil.logInfo(queryString)

connection = DriverManager.getConnection(conn1)

Statement stm33 = connection.createStatement()

ResultSet rs33 = stm33.executeQuery(queryString)

def recordSet33 = rs33

if (recordSet33.next()) {
    Object ACRD_Amount = recordSet33.getObject('ACRD_Amount')

    GlobalVariable.paymentType = 'Credit Note'

    CollectedAmountView = WebUI.getAttribute(findTestObject('XXXXXXXXXXXX/Web Part/Stock_Cash_Reconciliation/Grids/Collected_Amount'), 'title')

    KeywordUtil.logInfo(CollectedAmountView)

    WebUI.verifyEqual(Double.parseDouble(CollectedAmountView), ACRD_Amount, FailureHandling.STOP_ON_FAILURE)

    Object ACRD_Acutal_Amount = recordSet33.getObject('ACRD_Acutal_Amount')

    ActualAmountView = WebUI.getAttribute(findTestObject('XXXXXXXXXXXX/Web Part/Stock_Cash_Reconciliation/Grids/Actual_Amount'), 'title')

    KeywordUtil.logInfo(ActualAmountView)

    WebUI.verifyEqual(Double.parseDouble(ActualAmountView), ACRD_Acutal_Amount, FailureHandling.STOP_ON_FAILURE)

    Object ACRD_Variance_Amount = recordSet33.getObject('ACRD_Variance_Amount')

    VarianceAmountView = WebUI.getAttribute(findTestObject('XXXXXXXXXXXX/Web Part/Stock_Cash_Reconciliation/Grids/Variance_Amount'), 'title')

    KeywordUtil.logInfo(VarianceAmountView)

    WebUI.verifyEqual(Double.parseDouble(VarianceAmountView), ACRD_Variance_Amount, FailureHandling.STOP_ON_FAILURE)

    Object ACRD_Payment_Type_Lov_Id = recordSet33.getObject('ACRD_Payment_Type_Lov_Id')

    queryString = (('select * from adm_lovs where alov_id=\'' + ACRD_Payment_Type_Lov_Id) + '\'')

    connection = DriverManager.getConnection(conn1)

    Statement stm44 = connection.createStatement()

    ResultSet rs44 = stm44.executeQuery(queryString)

    def recordSet44 = rs44

    if (recordSet44.next()) {
        Object ALOV_Name = recordSet44.getObject('ALOV_Name')

        PaymentModesView = WebUI.getAttribute(findTestObject('XXXXXXXXXXXX/Web Part/Stock_Cash_Reconciliation/Grids/Payment_Mode'), 'title')

        KeywordUtil.logInfo(PaymentModesView)

        WebUI.verifyMatch(PaymentModesView, ALOV_Name, false)
    }
} else {
    'If data is empty in the DB, then the flow must fail'
    Assert.fail()
}

