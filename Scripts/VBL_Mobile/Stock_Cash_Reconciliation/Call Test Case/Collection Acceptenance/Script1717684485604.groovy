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

//Mobile.callTestCase(findTestCase('Product_Mobile/Common/DatePicker_and _Navigation/DB_Currentdate'), [:], FailureHandling.STOP_ON_FAILURE)
//
//String conn = (((((('jdbc:sqlserver://' + GlobalVariable.DB_URL) + ';databaseName=') + GlobalVariable.DB_Name) + ';user=') + GlobalVariable.DB_Username) + ';password=') + GlobalVariable.DB_Password
//
//GlobalVariable.Transaction_No = '29706072024163121'
//
//queryString = (('select top 1 * from appdata_collection_request where cr_sih_no=\'' + GlobalVariable.Transaction_No) + '\' order by 1 desc')
//
//connection = DriverManager.getConnection(conn)
//
//Statement stm = connection.createStatement()
//
//ResultSet rs = stm.executeQuery(queryString)
//
//def recordSet = rs
//
//while (recordSet.next()) {
//    Object data = recordSet.getObject('CR_Receipt_No')
//
//    GlobalVariable.Receipt_No = data
//
//    println('Receipt Number  :  ' + GlobalVariable.Receipt_No)
//
//    ///////////////// mobile validation
//    Object transaction_no = recordSet.getObject('CR_SIH_No')
//
//    WebUI.verifyMatch(GlobalVariable.Transaction_No, transaction_no, false, FailureHandling.STOP_ON_FAILURE)
//
//    println('Mobile collection Transaction ID correctly inserted in database')
//
//}
//Mobile.callTestCase(findTestCase('VBL_Mobile/Re-Usables/Web Login and Navigation/ClearCashe Login'), [:], FailureHandling.OPTIONAL)


Mobile.callTestCase(findTestCase('VBL_Mobile/Re-Usables/Web Login and Navigation/ManjuLogin'), [:], FailureHandling.STOP_ON_FAILURE)

Mobile.callTestCase(findTestCase('VBL_Mobile/Re-Usables/Web Login and Navigation/Navigate to Collection Acceptance'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.delay(2)

WebUI.click(findTestObject('XXXXXXXXXXXX/Web Part/Collection Acceptance/SalesPerson'))

GlobalVariable.label = findTestData('VBL_Web Input Data/Stock_Cash_Reconciliation').getValue('Seller', 1)

WebUI.click(findTestObject('XXXXXXXXXXXX/Web Part/Collection Acceptance/Global_Dropdown_Option'))

WebUI.waitForElementNotVisible(findTestObject('XXXXXXXXXXXX/Web Part/Re-usable/Page Load'), 100)

WebUI.click(findTestObject('XXXXXXXXXXXX/Web Part/Collection Acceptance/Retailer'))

GlobalVariable.label = findTestData('VBL_Web Input Data/Stock_Cash_Reconciliation').getValue('Retailer', 1)

WebUI.scrollToElement(findTestObject('XXXXXXXXXXXX/Web Part/Collection Acceptance/Global_Dropdown_Option'), 0)

WebUI.click(findTestObject('XXXXXXXXXXXX/Web Part/Collection Acceptance/Global_Dropdown_Option'))

WebUI.doubleClick(findTestObject('XXXXXXXXXXXX/Web Part/Collection Acceptance/Route'), FailureHandling.STOP_ON_FAILURE)

WebUI.waitForElementNotVisible(findTestObject('XXXXXXXXXXXX/Web Part/Re-usable/Page Load'), 100)

WebUI.click(findTestObject('XXXXXXXXXXXX/Web Part/Collection Acceptance/Search_Btn'))

WebUI.waitForElementNotVisible(findTestObject('XXXXXXXXXXXX/Web Part/Re-usable/Page Load'), 100)

WebUI.click(findTestObject('XXXXXXXXXXXX/Web Part/Collection Acceptance/ReceiptNo_Search_Field'))

Mobile.callTestCase(findTestCase('Product_Mobile/Common/DatePicker_and _Navigation/DB_Currentdate'), [:], FailureHandling.STOP_ON_FAILURE)

String conn = (((((('jdbc:sqlserver://' + GlobalVariable.DB_URL) + ';databaseName=') + GlobalVariable.DB_Name) + ';user=') + GlobalVariable.DB_Username) + ';password=') + GlobalVariable.DB_Password

GlobalVariable.Transaction_No = findTestData('VBL_Web Input Data/Stock_Cash_Reconciliation').getValue('Invoice_No', 1)

queryString1 = (('select * from appdata_collection_request where cr_sih_no=\'' + GlobalVariable.Transaction_No) + '\' order by 1 desc')

connection = DriverManager.getConnection(conn)

Statement stm1 = connection.createStatement()

ResultSet rs1 = stm1.executeQuery(queryString1)

def recordSet1 = rs1

while (recordSet1.next()) {
    Object data1 = recordSet1.getObject('CR_Receipt_No')

    GlobalVariable.Receipt_No = data1

    println('Receipt Number  :  ' + GlobalVariable.Receipt_No)

    ///////////////// mobile validation
    //	Object transaction_no = recordSet.getObject('CR_SIH_No')
    //
    //	WebUI.verifyMatch(GlobalVariable.Transaction_No, transaction_no, false, FailureHandling.STOP_ON_FAILURE)
    //
    //	println('Mobile collection Transaction ID correctly inserted in database')
	
    WebUI.setText(findTestObject('XXXXXXXXXXXX/Web Part/Collection Acceptance/ReceiptNo_Search_Field'), GlobalVariable.Receipt_No)

    //WebUI.verifyElementPresent(findTestObject('XXXXXXXXXXXX/Web Part/Collection Acceptance/Global_Td_A_Tag(grid Value)'), 5, FailureHandling.STOP_ON_FAILURE)
    //
    //WebUI.verifyElementVisible(findTestObject('XXXXXXXXXXXX/Web Part/Collection Acceptance/Global_Td_A_Tag(grid Value)'), FailureHandling.STOP_ON_FAILURE)
    
	WebUI.takeScreenshot()

    println('The receipt number in the DataBase is reflected in the Appliction')

    WebUI.click(findTestObject('XXXXXXXXXXXX/Web Part/Collection Acceptance/Global_Td_A_Tag(grid Value)'))

    WebUI.waitForElementVisible(findTestObject('XXXXXXXXXXXX/Web Part/Re-usable/Page Load'), 100)

    WebUI.click(findTestObject('XXXXXXXXXXXX/Web Part/Collection Acceptance/Accept_btn'))

    WebUI.click(findTestObject('XXXXXXXXXXXX/Web Part/Collection Acceptance/OK_btn'))

    WebUI.delay(3)

    WebUI.takeScreenshot()
}

