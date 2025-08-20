
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
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import org.testng.Assert as Assert
import io.appium.java_client.android.nativekey.KeyEvent as KeyEvent
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import com.kms.katalon.keyword.excel.ExcelKeywords as ExcelKeywords

String pattern = 'yyyy-MM-dd'

SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern)

String currentdate = simpleDateFormat.format(new Date())

println(currentdate)

DB_Currentdate = (currentdate + ' 00:00:00.000')


GlobalVariable.SR_Amt = findTestData('Mobile Input Data/SalesReturn').getValue('SalesReturn_Amt', 1)

Mobile.callTestCase(findTestCase('Product_Mobile/Common/DB'), [('Query') : 'select  * from appdata_sales_return_header where srh_Date = \'' + DB_Currentdate + '\'  and SRH_Amount = \'' +
	GlobalVariable.SR_Amt + '\' and SRH_UserId=1589 and SRH_ARTR_Id=411 order by 1 desc', ('columnNames') : ['SRH_No']], FailureHandling.STOP_ON_FAILURE)

String SRH_No=GlobalVariable.data[0]

KeywordUtil.logInfo('Header id : '+SRH_No)


GlobalVariable.SR_No = SRH_No

KeywordUtil.logInfo('Sales return Number  :  ' + GlobalVariable.SR_No)


Mobile.callTestCase(findTestCase('Product_Mobile/Common/DB'), [('Query') : 'select  * from ADM_Credit_Note where ACN_Effective_From = \'' + DB_Currentdate + '\'  and ACN_Reference_No = \'' +
	GlobalVariable.SR_No + '\'  order by 1 desc', ('columnNames') : ['ACN_Number','ACN_Amount']], FailureHandling.STOP_ON_FAILURE)

String Credit_Note_Num=GlobalVariable.data[0]

KeywordUtil.logInfo('Credit note No : '+Credit_Note_Num)


GlobalVariable.label2 = Credit_Note_Num

KeywordUtil.logInfo('Credit note number : '+GlobalVariable.label2)

String Credit_Amount=GlobalVariable.data[1]



KeywordUtil.logInfo('Credit_Amount : '+Credit_Amount)

Cred_amt= findTestData('Mobile Input Data/SalesReturn').getValue('SalesReturn_Amt', 1)

WebUI.verifyEqual(Credit_Amount, Double.parseDouble(Cred_amt), FailureHandling.STOP_ON_FAILURE)


WebUI.callTestCase(findTestCase('Product_Mobile/Common/Web Login and Navigation/DistributorLogin'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('Test Cases/Product_Mobile/Common/Web Login and Navigation/Navigate to CN View'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.waitForElementVisible(findTestObject('Web Part/CN Create/SalesPerson'), 50, FailureHandling.OPTIONAL)

WebUI.click(findTestObject('Web Part/CN Create/SalesPerson'))

GlobalVariable.label = findTestData('Web Input Data/Credit_Note').getValue('SalesPerson', 1)

WebUI.setText(findTestObject('Web Part/CN Create/SalesPerson'), GlobalVariable.label)

WebUI.delay(2)

WebUI.click(findTestObject('Web Part/Re-usable/Global/Span_Tag(Dropdown)'))

WebUI.waitForElementNotVisible(findTestObject('Web Part/Re-usable/Page Load'), 100)

WebUI.click(findTestObject('Web Part/SalesReturnView/Search_Btn'))

WebUI.delay(2)

WebUI.click(findTestObject('Object Repository/Web Part/CreditNote_view/Filter_icon'))

WebUI.delay(2)

WebUI.click(findTestObject('Object Repository/Web Part/CreditNote_view/Credit_Note_num_filter_search'))

WebUI.setText(findTestObject('Object Repository/Web Part/CreditNote_view/Credit_Note_num_filter_search'), GlobalVariable.label2)

WebUI.delay(2)

WebUI.scrollToElement(findTestObject('Object Repository/Web Part/SalesOrderView/Apply_Btn_In_Filter'), 5, FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Object Repository/Web Part/SalesOrderView/Apply_Btn_In_Filter'))

WebUI.delay(2)

WebUI.comment('Validation for credit note number and amount in grid column')

SR_Credit_Note_Number = WebUI.getText(findTestObject('Web Part/CreditNote_view/Grid_Credit_Note_Num_get'))

SR_Credit_note_amount = WebUI.getText(findTestObject('Web Part/CreditNote_view/Grid_credit_amt_get'))

Credit_note_amt=SR_Credit_note_amount.replace('$ ','')

WebUI.verifyMatch(SR_Credit_Note_Number,GlobalVariable.label2 , false, FailureHandling.STOP_ON_FAILURE)

Cred_amt=findTestData('Mobile Input Data/SalesReturn').getValue('SalesReturn_Amt', 1)

WebUI.verifyEqual(Double.parseDouble(Credit_note_amt), Double.parseDouble(Cred_amt), FailureHandling.STOP_ON_FAILURE)

WebUI.takeScreenshot()

WebUI.comment('Validation for credit note referenece no')

WebUI.click(findTestObject('Web Part/CreditNote_view/Grid_Credit_Note_Num_get'))

Details_scrn_reference_no = WebUI.getAttribute(findTestObject('Web Part/CreditNote_view/Details_reference_no_get'), 'value')

WebUI.verifyMatch(Details_scrn_reference_no, GlobalVariable.SR_No, false, FailureHandling.STOP_ON_FAILURE)

WebUI.takeScreenshot()

WebUI.closeBrowser()

