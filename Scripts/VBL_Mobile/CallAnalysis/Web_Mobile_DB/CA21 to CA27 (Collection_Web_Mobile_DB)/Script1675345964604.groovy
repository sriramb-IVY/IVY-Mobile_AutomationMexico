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

Connection connection = null

url = findTestData('VBL_DB Credentials/Config1').getValue('URL', 1)

dbname = findTestData('VBL_DB Credentials/Config1').getValue('dbname', 1)

username = findTestData('VBL_DB Credentials/Config1').getValue('username', 1)

password = findTestData('VBL_DB Credentials/Config1').getValue('password', 1)

//GlobalVariable.Transaction_No = '29709272023121929'
queryString = (('select top 1 * from appdata_collection_request where cr_sih_no=\'' + GlobalVariable.Transaction_No) + '\' order by 1 desc')

String conn = (((((('jdbc:sqlserver://' + url) + ';databaseName=') + dbname) + ';user=') + username) + ';password=') + password

connection = DriverManager.getConnection(conn)

Statement stm = connection.createStatement()

ResultSet rs = stm.executeQuery(queryString)

def recordSet = rs

while (recordSet.next()) {
    Object data = recordSet.getObject('CR_Receipt_No')

    GlobalVariable.Receipt_No = data

    println('Receipt Number  :  ' + GlobalVariable.Receipt_No)

    ///////////////// mobile validation
    Object transaction_no = recordSet.getObject('CR_SIH_No')

    WebUI.verifyMatch(GlobalVariable.Transaction_No, transaction_no, false, FailureHandling.STOP_ON_FAILURE)

    println('Mobile collection Transaction ID correctly inserted in database')

    Object RecievedAmt = recordSet.getObject('CR_Paid_Amount')

    WebUI.verifyEqual(GlobalVariable.C_ReceivedAmt, RecievedAmt, FailureHandling.STOP_ON_FAILURE)

    println('Mobile collection Recieved amount correctly inserted in database' ////////////////////////////////
        )
}

Mobile.callTestCase(findTestCase('VBL_Mobile/Re-Usables/Web Login and Navigation/ClearCashe Login'), [:], FailureHandling.OPTIONAL)

Mobile.callTestCase(findTestCase('VBL_Mobile/Re-Usables/Web Login and Navigation/ManjuLogin'), [:], FailureHandling.STOP_ON_FAILURE)

Mobile.callTestCase(findTestCase('VBL_Mobile/Re-Usables/Web Login and Navigation/Navigate to Collection Acceptance'), [:], 
    FailureHandling.STOP_ON_FAILURE)

WebUI.delay(2)

WebUI.click(findTestObject('XXXXXXXXXXXX/Web Part/Collection Acceptance/SalesPerson'))

GlobalVariable.label = findTestData('VBL_Web Input Data/CollectionManagement').getValue('SalesPerson', 1)

WebUI.click(findTestObject('XXXXXXXXXXXX/Web Part/Collection Acceptance/Global_Dropdown_Option'))

WebUI.waitForElementNotVisible(findTestObject('XXXXXXXXXXXX/Web Part/Re-usable/Page Load'), 100)

WebUI.click(findTestObject('XXXXXXXXXXXX/Web Part/Collection Acceptance/Retailer'))

GlobalVariable.label = findTestData('VBL_Web Input Data/CollectionManagement').getValue('Retailer', 1)

WebUI.scrollToElement(findTestObject('XXXXXXXXXXXX/Web Part/Collection Acceptance/Global_Dropdown_Option'), 0)

WebUI.click(findTestObject('XXXXXXXXXXXX/Web Part/Collection Acceptance/Global_Dropdown_Option'))

WebUI.doubleClick(findTestObject('XXXXXXXXXXXX/Web Part/Collection Acceptance/Route'), FailureHandling.STOP_ON_FAILURE)

WebUI.waitForElementNotVisible(findTestObject('XXXXXXXXXXXX/Web Part/Re-usable/Page Load'), 100)

WebUI.click(findTestObject('XXXXXXXXXXXX/Web Part/Collection Acceptance/Search_Btn'))

WebUI.waitForElementNotVisible(findTestObject('XXXXXXXXXXXX/Web Part/Re-usable/Page Load'), 100)

WebUI.click(findTestObject('XXXXXXXXXXXX/Web Part/Collection Acceptance/ReceiptNo_Search_Field'))

WebUI.setText(findTestObject('XXXXXXXXXXXX/Web Part/Collection Acceptance/ReceiptNo_Search_Field'), GlobalVariable.Receipt_No)

WebUI.verifyElementPresent(findTestObject('XXXXXXXXXXXX/Web Part/Collection Acceptance/Global_Td_A_Tag(grid Value)'), 5, FailureHandling.STOP_ON_FAILURE)

WebUI.verifyElementVisible(findTestObject('XXXXXXXXXXXX/Web Part/Collection Acceptance/Global_Td_A_Tag(grid Value)'), FailureHandling.STOP_ON_FAILURE)

WebUI.takeScreenshot()

println('The receipt number in the DataBase is reflected in the Appliction')

WebUI.click(findTestObject('XXXXXXXXXXXX/Web Part/Collection Acceptance/Global_Td_A_Tag(grid Value)'))

WebUI.waitForElementVisible(findTestObject('XXXXXXXXXXXX/Web Part/Re-usable/Page Load'), 100)

WebUI.scrollToElement(findTestObject('XXXXXXXXXXXX/Web Part/Collection Acceptance/Get_RefNo'), 10)

ReferenceNo = WebUI.getText(findTestObject('XXXXXXXXXXXX/Web Part/Collection Acceptance/Get_RefNo'))

ref_no = ReferenceNo.replaceAll('\\s', '')

GlobalVariable.label1 = ref_no

println(ref_no)

WebUI.takeScreenshot()

GlobalVariable.Net_Amt = WebUI.getText(findTestObject('XXXXXXXXXXXX/Web Part/Collection Acceptance/Get_NetAmount'))

println(GlobalVariable.Net_Amt)

GlobalVariable.Balance_Amt = WebUI.getText(findTestObject('XXXXXXXXXXXX/Web Part/Collection Acceptance/Get_Balance_Amt'))

println(GlobalVariable.Balance_Amt)

GlobalVariable.Collection_Amt = WebUI.getText(findTestObject('XXXXXXXXXXXX/Web Part/Collection Acceptance/Get_CollectionAmt'))

println(GlobalVariable.Collection_Amt)

WebUI.takeScreenshot()

WebUI.delay(3)

//2nd DB
queryString1 = (('select top 1 * from appdata_pending_bills where pb_sih_no=\'' + GlobalVariable.Transaction_No) + '\' order by 1 desc')

connection1 = DriverManager.getConnection(conn)

Statement stm1 = connection1.createStatement()

ResultSet rs1 = stm1.executeQuery(queryString1)

def recordSet1 = rs1

while (recordSet1.next()) {
    Object Paid_Amount = recordSet1.getObject('PB_Paid_Amount')

    println('Paid_Amount : ' + Paid_Amount)

    WebUI.verifyEqual(Paid_Amount, 0, FailureHandling.STOP_ON_FAILURE)

    println('Paid Amount is Zero in data base before accepting the collection')

    WebUI.takeScreenshot()
}

WebUI.click(findTestObject('XXXXXXXXXXXX/Web Part/Collection Acceptance/Accept_btn'))

WebUI.click(findTestObject('XXXXXXXXXXXX/Web Part/Collection Acceptance/OK_btn'))

WebUI.delay(3)

WebUI.takeScreenshot()

//3nd DB
//queryString2 = (('select top 1 * from appdata_pending_bills where pb_sih_no=\'' + GlobalVariable.Transaction_No) + '\' order by 1 desc')
//
//connection2 = DriverManager.getConnection(conn)
//
//Statement stm2 = connection2.createStatement()
//
//ResultSet rs2 = stm2.executeQuery(queryString2)
//
//def recordSet2 = rs2
//
//while (recordSet2.next()) {
//    Object Net_Amount = recordSet2.getObject('PB_Net_Amount')
//
//    println('Net_Amount : ' + Net_Amount)
//
//    WebUI.verifyEqual(GlobalVariable.Net_Amt, Net_Amount, FailureHandling.OPTIONAL)
//
//    println('The Net Amount Displayed properly!')
//
//    ///////////////// mobile validation
//    WebUI.verifyEqual(GlobalVariable.C_TransactionAmt, Net_Amount, FailureHandling.OPTIONAL)
//
//    println('The mobile transaction Net Amount properly inserted into database!')
//
//    //////////////////////
//    Object Balance_Amount = recordSet2.getObject('PB_Balance_Amount')
//
//    println('Balance_Amount : ' + Balance_Amount)
//
//    WebUI.verifyEqual(GlobalVariable.Balance_Amt, Balance_Amount, FailureHandling.OPTIONAL)
//
//    println('The Balance Amount Displayed properly!')
//
//    ///////////////// mobile validation
//    WebUI.verifyEqual(GlobalVariable.C_OS_Amt, Balance_Amount, FailureHandling.OPTIONAL)
//
//    println('The mobile OS Amount (BalanceAmt) properly inserted into database!')
//
//    //////////////////
//    Object ref_no = recordSet2.getObject('PB_SIH_No')
//
//    println('ref_no : ' + ref_no)
//
//    WebUI.verifyEqual(GlobalVariable.label1, ref_no, FailureHandling.OPTIONAL)
//
//    println('The Reference Number Displayed properly!')
//
//    Object Paid_Amount = recordSet2.getObject('PB_Paid_Amount')
//
//    println('Paid_Amount : ' + Paid_Amount)
//
//    WebUI.verifyEqual(GlobalVariable.Collection_Amt, Paid_Amount, FailureHandling.OPTIONAL)
//
//    println('Paid Amount is Displayed properly in data base After accepting the collection')
//
//    WebUI.takeScreenshot()
//}
WebUI.callTestCase(findTestCase('VBL_Mobile/Re-Usables/Web Login and Navigation/Navigate to Collection View'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('XXXXXXXXXXXX/Web Part/Collection Acceptance/SalesPerson'))

GlobalVariable.label = findTestData('VBL_Web Input Data/CollectionManagement').getValue('SalesPerson', 1)

WebUI.click(findTestObject('XXXXXXXXXXXX/Web Part/Collection Acceptance/Global_Dropdown_Option'))

WebUI.waitForElementNotVisible(findTestObject('XXXXXXXXXXXX/Web Part/Re-usable/Page Load'), 100)

WebUI.click(findTestObject('XXXXXXXXXXXX/Web Part/Collection Acceptance/Search_Btn'))

WebUI.waitForElementNotVisible(findTestObject('XXXXXXXXXXXX/Web Part/Re-usable/Page Load'), 100)

//4nd DB
queryString4 = (('select top 1 * from appdata_collection where c_sih_no=\'' + GlobalVariable.Transaction_No) + '\' order by 1 desc')

connection4 = DriverManager.getConnection(conn)

Statement stm4 = connection4.createStatement()

ResultSet rs4 = stm4.executeQuery(queryString4)

def recordSet4 = rs4

while (recordSet4.next()) {
    Object C_Receipt_No = recordSet4.getObject('C_Receipt_No')

    println('C_Receipt_No : ' + C_Receipt_No)

    GlobalVariable.Receipt_No = C_Receipt_No

    WebUI.setText(findTestObject('XXXXXXXXXXXX/Web Part/Collection View/Enter_ReceiptNo_SearchField'), C_Receipt_No)

    WebUI.delay(1)

    WebUI.verifyElementPresent(findTestObject('XXXXXXXXXXXX/Web Part/Collection View/Global_Td_Tag(grid Value)'), 5)

    WebUI.verifyElementVisible(findTestObject('XXXXXXXXXXXX/Web Part/Collection View/Global_Td_Tag(grid Value)'), FailureHandling.STOP_ON_FAILURE)

    WebUI.takeScreenshot()

    println('The receipt number in the DataBase is reflected in the Appliction')

    WebUI.click(findTestObject('XXXXXXXXXXXX/Web Part/Collection View/View_btn'))

    WebUI.waitForElementNotVisible(findTestObject('XXXXXXXXXXXX/Web Part/Re-usable/Page Load'), 100)

    GlobalVariable.Receipt_No = WebUI.getText(findTestObject('XXXXXXXXXXXX/Web Part/Collection View/Get_ReceiptNo'))

    GlobalVariable.Collection_Amt = WebUI.getText(findTestObject('XXXXXXXXXXXX/Web Part/Collection View/Get_PaidAmt'))

    c_ReferenceNo = WebUI.getText(findTestObject('XXXXXXXXXXXX/Web Part/Collection Acceptance/Get_RefNo'))

    c_ref_no = c_ReferenceNo.replaceAll('\\s', '')

    GlobalVariable.label1 = c_ref_no

    println(c_ref_no)

    Object c_paid_amount = recordSet4.getObject('c_paid_amount')

    println('Paid Amount is : ' + c_paid_amount)

    WebUI.verifyEqual(GlobalVariable.Collection_Amt, c_paid_amount, FailureHandling.STOP_ON_FAILURE)

    println('Paid Amount is Displayed properly in database and collection view Screen After accepting the collection')

    Object C_SIH_No = recordSet4.getObject('C_SIH_No')

    println('Reference no : ' + C_SIH_No)

    WebUI.verifyEqual(GlobalVariable.label1, C_SIH_No, FailureHandling.STOP_ON_FAILURE)

    println('Reference no displayed properly in database and collection view Screen After accepting the collection')

    Object c_pay_type = recordSet4.getObject('c_pay_type_lov_id')

    GlobalVariable.label = c_pay_type

    WebUI.takeScreenshot()
}

WebUI.takeScreenshot()

paymentype = WebUI.getText(findTestObject('XXXXXXXXXXXX/Web Part/Collection View/Get_PaymentType'))

println(paymentype)

GlobalVariable.label1 = paymentype

//5th db
queryString5 = (('select top 1 * from adm_lovs where alov_id=\'' + GlobalVariable.label) + '\' order by 1 desc')

connection5 = DriverManager.getConnection(conn)

Statement stm5 = connection5.createStatement()

ResultSet rs5 = stm5.executeQuery(queryString5)

def recordSet5 = rs5

while (recordSet5.next()) {
    Object payment_Type = recordSet5.getObject('ALOV_Name')

    println('payment_Type : ' + payment_Type)

    WebUI.verifyMatch(payment_Type, GlobalVariable.label1, false, FailureHandling.STOP_ON_FAILURE)

    println('The Payment Type displayed properly in database and collection view Screen After accepting the collection')

    WebUI.takeScreenshot()
}

WebUI.takeScreenshot()

WebUI.closeBrowser()

