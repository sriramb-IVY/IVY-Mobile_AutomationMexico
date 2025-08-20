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
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import java.sql.Connection as Connection
import java.sql.DriverManager as DriverManager
import java.sql.ResultSet as ResultSet
import java.sql.Statement as Statement
import java.text.SimpleDateFormat as SimpleDateFormat
import com.kms.katalon.core.mobile.keyword.internal.MobileDriverFactory as MobileDriverFactory
import io.appium.java_client.AppiumDriver as AppiumDriver
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import org.openqa.selenium.WebElement as WebElement
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import org.junit.Assert as Assert

WebUI.callTestCase(findTestCase('Product_Mobile/Common/Web Login and Navigation/DistributorLogin'), [:], FailureHandling.STOP_ON_FAILURE)

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Web Login and Navigation/Navigate to Collection Acceptance'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Web Part/Collection Acceptance/SalesPerson'))

GlobalVariable.label = findTestData('Mobile Input Data/Collection').getValue('SalesPerson', 1)

WebUI.click(findTestObject('Web Part/Collection Acceptance/Global_Dropdown_Option(2)'))

WebUI.delay(5)

WebUI.click(findTestObject('Web Part/Collection Acceptance/Search_Btn'))

WebUI.click(findTestObject('Web Part/Collection Acceptance/Search_Btn'), FailureHandling.OPTIONAL)

WebUI.waitForElementNotVisible(findTestObject('Web Part/Re-usable/Page Load'), 100)

GlobalVariable.Transaction_No = findTestData('Mobile Input Data/CreditManagement').getValue('Cash_Collected_Invoice_No', Credit_Index)

queryString02 = (((('select top 1 * from appdata_collection_request  where CR_UserId =\'' + GlobalVariable.vanseller_user_id) + '\' and  cr_sih_no=  \'') + GlobalVariable.Transaction_No) + '\'')

KeywordUtil.logInfo(queryString02)

String conn = (((((('jdbc:sqlserver://' + GlobalVariable.DB_URL) + ';databaseName=') + GlobalVariable.DB_Name) + ';user=') + GlobalVariable.DB_Username) + ';password=') + GlobalVariable.DB_Password

connection02 = DriverManager.getConnection(conn)

Statement stm02 = connection02.createStatement()

ResultSet rs02 = stm02.executeQuery(queryString02)

def recordSet02 = rs02

if (recordSet02.next()) {
    
    Object CR_Receipt_No = recordSet02.getObject('CR_Receipt_No')

    GlobalVariable.Receipt_No = CR_Receipt_No
	
	Object CR_Paid_Amount = recordSet02.getObject('CR_Paid_Amount')
	
	GlobalVariable.Paid_Amt = CR_Paid_Amount
} else {
    'If data is empty in the DB, then the flow must fail'
    Assert.fail()
}


WebUI.click(findTestObject('Web Part/Collection Acceptance/ReceiptNo_Search_Field'))

WebUI.setText(findTestObject('Web Part/Collection Acceptance/ReceiptNo_Search_Field'), GlobalVariable.Receipt_No)

WebUI.verifyElementVisible(findTestObject('Web Part/Collection Acceptance/Global_Td_A_Tag(grid Value)'))

WebUI.click(findTestObject('Web Part/Collection Acceptance/Checkbox_Enable'))

WebUI.click(findTestObject('Web Part/Collection Acceptance/Accept_btn'))

WebUI.click(findTestObject('Web Part/Collection Acceptance/OK_btn'))

WebUI.verifyElementPresent(findTestObject('Web Part/Collection Acceptance/Save Successful Alert'), 0)

WebUI.closeBrowser()

