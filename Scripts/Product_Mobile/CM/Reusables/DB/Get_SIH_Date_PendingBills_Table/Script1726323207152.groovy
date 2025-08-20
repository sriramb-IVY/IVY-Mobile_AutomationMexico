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
import org.junit.Assert as Assert

Retailer_Name = findTestData('Mobile Input Data/CreditManagement').getValue('Retailer_Name', Credit_Index)

String conn = (((((('jdbc:sqlserver://' + GlobalVariable.DB_URL) + ';databaseName=') + GlobalVariable.DB_Name) + ';user=') + GlobalVariable.DB_Username) + ';password=') + GlobalVariable.DB_Password

connection = DriverManager.getConnection(conn)

stm = connection.createStatement()

queryString = (('select * from adm_retailer where ARTR_Name = \'' + Retailer_Name) + '\' and ARTR_Isactive = 1')

rs = stm.executeQuery(queryString)

KeywordUtil.logInfo(queryString)

def recordSet = rs

if (recordSet.next()) {
    String retailer_id = recordSet.getObject('ARTR_Id')

    KeywordUtil.logInfo('Retailer ID is : ' + retailer_id)

    GlobalVariable.label = retailer_id
}


connection = DriverManager.getConnection(conn)

stm = connection.createStatement()

queryString = (('select * from AppData_Pending_Bills where PB_ARTR_Id = \'' + GlobalVariable.label) + '\'')

rs = stm.executeQuery(queryString)

KeywordUtil.logInfo(queryString)

def recordSet1 = rs

if (recordSet1.next()) {
	String PB_SIH_Date = recordSet1.getObject('PB_SIH_Date')

	KeywordUtil.logInfo('PB_SIH_Date is : ' + PB_SIH_Date)
	
	String[] sDate = PB_SIH_Date.split(' ')
	
	String InvoiceDate = sDate[0]

	GlobalVariable.label = InvoiceDate
}


