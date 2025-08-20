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

'SH_13 Credit Scheme DB validation'

CreditNote = 6

Connection connection = null

url = findTestData('VBL_DB Credentials/Config1').getValue('URL', 1)

dbname = findTestData('VBL_DB Credentials/Config1').getValue('dbname', 1)

username = findTestData('VBL_DB Credentials/Config1').getValue('username', 1)

password = findTestData('VBL_DB Credentials/Config1').getValue('password', 1)

GlobalVariable.SO_Number = findTestData('VBL_Web Input Data/Sales_Order_Invoice_View').getValue('OrderNumber', CreditNote)

queryString = (('select * from appdata_sales_invoice_header where SIH_Sales_Order_Id =\'' + GlobalVariable.SO_Number) + 
'\'')

KeywordUtil.logInfo(('select * from appdata_sales_invoice_header where SIH_Sales_Order_Id =\'' + GlobalVariable.SO_Number) + 
    '\'')

String conn = (((((('jdbc:sqlserver://' + url) + ';databaseName=') + dbname) + ';user=') + username) + ';password=') + password

connection = DriverManager.getConnection(conn)

Statement stm = connection.createStatement()

ResultSet rs = stm.executeQuery(queryString)

def recordSet = rs

if (recordSet.next()) {
    Object sih_id = recordSet.getObject('sih_id')

    GlobalVariable.label = sih_id

    KeywordUtil.logInfo('sih_id : ' + GlobalVariable.label)
}

queryString6 = (('Select top 2 * from AppData_Sales_Invoice_Scheme_Detail_Credit where SISDC_SIH_Id =\'' + GlobalVariable.label) + 
'\' order by 1 desc ')

ResultSet rs6 = stm.executeQuery(queryString6)

def recordSet6 = rs6

List<Double> Values2 = new ArrayList<String>()

while (recordSet6.next()) {
    Object SISDC_Value1 = recordSet6.getObject('SISDC_Value')

    KeywordUtil.logInfo(SISDC_Value1.toString())

    Values2.add(SISDC_Value1)
}

println(Values2)

double sum2 = 0

for (int j = 0; j < Values2.size(); j++) {
    sum2 += Values2.get(j)

    println('sum2:' + sum2)
}

GlobalVariable.DiscountAmt = findTestData('VBL_Web Input Data/Sales_Order_Invoice_View').getValue('DiscountAmt', CreditNote)

Mobile.verifyEqual(sum2 ,  GlobalVariable.DiscountAmt , FailureHandling.STOP_ON_FAILURE)

