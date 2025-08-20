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
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import org.openqa.selenium.WebElement as WebElement
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import org.junit.Assert as Assert

//WebUI.callTestCase(findTestCase('Product_Mobile/Common/Web Login and Navigation/DistributorLogin'), [:], FailureHandling.STOP_ON_FAILURE)
//
//WebUI.callTestCase(findTestCase('Product_Mobile/Common/Web Login and Navigation/Navigate to Collection View'), [:], FailureHandling.STOP_ON_FAILURE)
//
//WebUI.click(findTestObject('Web Part/Re-usable/Navigation Hide Icon'))
//
//WebUI.click(findTestObject('Web Part/Collection Acceptance/Search_Btn'))
//
//WebUI.waitForElementNotVisible(findTestObject('Web Part/Re-usable/Page Load'), 100)
Connection connection = null

url = findTestData('DB Credentials/Config1').getValue('URL', 1)

dbname = findTestData('DB Credentials/Config1').getValue('dbname', 1)

username = findTestData('DB Credentials/Config1').getValue('username', 1)

password = findTestData('DB Credentials/Config1').getValue('password', 1)

String conn = (((((('jdbc:sqlserver://' + url) + ';databaseName=') + dbname) + ';user=') + username) + ';password=') + password

connection = DriverManager.getConnection(conn)

Statement stm = connection.createStatement()

GlobalVariable.Transaction_No = findTestData('Mobile Input Data/Collection').getValue('TransactionNo', 1)

queryString4 = (('select top 1 * from appdata_collection where c_sih_no=\'' + GlobalVariable.Transaction_No) + '\' and C_Cheque_No = 12345 order by 1 desc')

//queryString4 = ('select top 1 * from appdata_collection where  C_Cheque_No = 12345 order by 1 desc')
KeywordUtil.logInfo(queryString4)

connection4 = DriverManager.getConnection(conn)

Statement stm4 = connection4.createStatement()

ResultSet rs4 = stm4.executeQuery(queryString4)

def recordSet4 = rs4

if (recordSet4.next()) {
    Object C_Receipt_No = recordSet4.getObject('C_Receipt_No')

    println('C_Receipt_No : ' + C_Receipt_No)

    GlobalVariable.Receipt_No = C_Receipt_No

    GlobalVariable.label = C_Receipt_No

    WebUI.setText(findTestObject('Web Part/Collection View/Enter_ReceiptNo_SearchField'), C_Receipt_No)

    WebUI.delay(1)

    WebUI.verifyElementPresent(findTestObject('Web Part/Re-usable/Global/div_Tag(GridSkuRow)'), 5)

    WebUI.verifyElementVisible(findTestObject('Web Part/Re-usable/Global/div_Tag(GridSkuRow)'), FailureHandling.STOP_ON_FAILURE)

    WebUI.takeScreenshot()

    KeywordUtil.logInfo(GlobalVariable.label + ' : is the receipt number in the DataBase is reflected in the collection view screen')

    WebUI.scrollToElement(findTestObject('Web Part/Collection View/View_btn'), 0, FailureHandling.OPTIONAL)

    WebUI.click(findTestObject('Web Part/Collection View/View_btn'))

    WebUI.waitForElementNotVisible(findTestObject('Web Part/Re-usable/Page Load'), 100)

    ReceiptNo = WebUI.getText(findTestObject('Web Part/Collection View/ReceiptNo_Field'))

    WebUI.verifyMatch(ReceiptNo, C_Receipt_No, false, FailureHandling.STOP_ON_FAILURE)

    WebUI.takeScreenshot()

    KeywordUtil.logInfo(ReceiptNo + ' : is the ReceiptNo.')

    SalesPerson = WebUI.getText(findTestObject('Web Part/Collection View/Seller_field'))

    WebUI.verifyMatch(SalesPerson, findTestData('Mobile Input Data/Collection').getValue('SalesPerson', 1), false, FailureHandling.STOP_ON_FAILURE)

    WebUI.takeScreenshot()

    KeywordUtil.logInfo(SalesPerson + ' : is the SalesPerson.')

    Retailer_Name = WebUI.getText(findTestObject('Web Part/Collection View/RetailerName_Field'))

    WebUI.verifyMatch(Retailer_Name, findTestData('Mobile Input Data/Collection').getValue('Retailer_Name', 1), false, FailureHandling.STOP_ON_FAILURE)

    WebUI.takeScreenshot()

    KeywordUtil.logInfo(Retailer_Name + ' : is the Retailer_Name.')

    Retailer_Code = WebUI.getText(findTestObject('Web Part/Collection View/RetailerCode_Field'))

    WebUI.verifyMatch(Retailer_Code, findTestData('Mobile Input Data/Collection').getValue('Retailer_Code', 1), false, FailureHandling.STOP_ON_FAILURE)

    WebUI.takeScreenshot()

    KeywordUtil.logInfo(Retailer_Code + ' : is the Retailer_Code.')

    GlobalVariable.Receipt_No = WebUI.getText(findTestObject('Web Part/Collection View/Get_ReceiptNo'))

    'Reference no'

    //    GlobalVariable.label = findTestData('Mobile Input Data/Collection').getValue('Mode_of_Collection_2', 1)
    //
    //    WebUI.scrollToElement(findTestObject('Web Part/Re-usable/Global/div_Tag(GridSkuRow)'), 0, FailureHandling.STOP_ON_FAILURE)
    //
    //    c_ReferenceNo = WebUI.getText(findTestObject('Web Part/Re-usable/Global/div_Tag(GridSkuRow)'))
    //
    //    c_ref_no = c_ReferenceNo.replaceAll('\\s', '')
    //
    //    GlobalVariable.label1 = c_ref_no
    //
    //    KeywordUtil.logInfo(c_ref_no)
    //
    //    Object C_SIH_No = recordSet4.getObject('C_SIH_No')
    //
    //    println('Reference no : ' + C_SIH_No)
    //
    //    WebUI.verifyEqual(GlobalVariable.label1, C_SIH_No, FailureHandling.STOP_ON_FAILURE)
    //
    //    KeywordUtil.logInfo('Reference no displayed properly in database and collection view Screen After accepting the collection')
    'Collection_Amt'
    Collection_Amt = WebUI.getText(findTestObject('Web Part/Collection View/Get_PaidAmt'))

    GlobalVariable.Collection_Amt = Collection_Amt.replace('$ ', '')

    Object c_paid_amount = recordSet4.getObject('c_paid_amount')

    KeywordUtil.logInfo('Paid Amount is : ' + c_paid_amount)

    WebUI.verifyEqual(GlobalVariable.Collection_Amt, c_paid_amount, FailureHandling.STOP_ON_FAILURE)

    KeywordUtil.logInfo(GlobalVariable.Collection_Amt + '  : Paid Amount is Displayed properly in database and collection view Screen After accepting the collection')

    Object c_pay_type = recordSet4.getObject('c_pay_type_lov_id')

    GlobalVariable.label = c_pay_type

    WebUI.takeScreenshot()
}
else {
	'If data is empty in the DB, then the flow must fail'
	Assert.fail()
}
WebUI.takeScreenshot()

WebUI.scrollToElement(findTestObject('Web Part/Collection View/Get_PaymentType'), 3, FailureHandling.OPTIONAL)

paymentype = WebUI.getText(findTestObject('Web Part/Collection View/Get_PaymentType'))

println(paymentype)

GlobalVariable.label1 = paymentype

WebUI.verifyMatch(paymentype, findTestData('Mobile Input Data/Collection').getValue('Mode_of_Collection_2', 1), false, FailureHandling.STOP_ON_FAILURE)

KeywordUtil.logInfo(paymentype + ' : The Payment Type displayed properly in collection view Screen ')

WebUI.takeScreenshot()

//5th db
queryString5 = (('select top 1 * from adm_lovs where alov_id=\'' + GlobalVariable.label) + '\' order by 1 desc')

connection5 = DriverManager.getConnection(conn)

Statement stm5 = connection5.createStatement()

ResultSet rs5 = stm5.executeQuery(queryString5)

def recordSet5 = rs5

if (recordSet5.next()) {
    Object payment_Type = recordSet5.getObject('ALOV_Name')

    KeywordUtil.logInfo('payment_Type : ' + payment_Type)

    WebUI.verifyMatch(payment_Type, GlobalVariable.label1, false, FailureHandling.STOP_ON_FAILURE)

    KeywordUtil.logInfo(GlobalVariable.label1 + ' : The Payment Type displayed properly in database and collection view Screen After accepting the collection')

    WebUI.takeScreenshot()
}
else {
	'If data is empty in the DB, then the flow must fail'
	Assert.fail()
}
WebUI.takeScreenshot()

WebUI.click(findTestObject('Web Part/Collection View/X_Btn(Based on receipt number)'))

WebUI.delay(2)

WebUI.scrollToElement(findTestObject('Web Part/Collection View/Enter_ReceiptNo_SearchField'), 0)

WebUI.waitForElementVisible(findTestObject('Web Part/Collection View/Enter_ReceiptNo_SearchField'), 0)

WebUI.click(findTestObject('Web Part/Collection View/Enter_ReceiptNo_SearchField'))

WebUI.clearText(findTestObject('Web Part/Collection View/Enter_ReceiptNo_SearchField'))

