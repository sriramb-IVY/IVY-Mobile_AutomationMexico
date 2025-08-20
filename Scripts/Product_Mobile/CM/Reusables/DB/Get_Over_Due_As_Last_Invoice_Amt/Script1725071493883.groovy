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

conn = (((((('jdbc:sqlserver://' + GlobalVariable.DB_URL) + ';databaseName=') + GlobalVariable.DB_Name) + ';user=') + GlobalVariable.DB_Username) + ';password=') + GlobalVariable.DB_Password

connection = DriverManager.getConnection(conn)

stm = connection.createStatement()

queryString = (('select * from adm_retailer where ARTR_Code = \'' + RetailerCode)+ '\' and ARTR_Isactive = 1')

rs = stm.executeQuery(queryString)

KeywordUtil.logInfo(queryString)

def recordSet = rs

if (recordSet.next()) {
	String retailer_id = recordSet.getObject('ARTR_Id')

	KeywordUtil.logInfo('Retailer ID is : ' + retailer_id)

	GlobalVariable.label = retailer_id
	
	}
	
conn = (((((('jdbc:sqlserver://' + GlobalVariable.DB_URL) + ';databaseName=') + GlobalVariable.DB_Name) + ';user=') + GlobalVariable.DB_Username) + ';password=') + GlobalVariable.DB_Password

connection = DriverManager.getConnection(conn)

stm = connection.createStatement()

queryString = (('select * from ADM_Retailer_Over_Due where AROD_ARTR_Id = \'' + GlobalVariable.label)+ '\' and AROD_Isactive = 1')

rs = stm.executeQuery(queryString)

KeywordUtil.logInfo(queryString)

def recordSet1 = rs

if (recordSet1.next()) {
	String Over_Due = recordSet1.getObject('AROD_Over_Due')
	
	GlobalVariable.label = Over_Due
	
	KeywordUtil.logInfo(Over_Due + ' : Over_Due Value is consider as the last invoice amount')
}
		
		