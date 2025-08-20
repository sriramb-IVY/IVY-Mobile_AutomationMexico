import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import java.awt.Robot as Robot
import java.awt.event.KeyEvent as KeyEvent
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import org.apache.commons.lang.RandomStringUtils as RandomStringUtils
import com.kms.katalon.core.mobile.keyword.internal.MobileDriverFactory as MobileDriverFactory
import io.appium.java_client.AppiumDriver as AppiumDriver
import java.sql.Connection as Connection
import java.sql.DriverManager as DriverManager
import java.sql.ResultSet as ResultSet
import java.sql.Statement as Statement
import javax.management.Descriptor as Descriptor
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import org.junit.Assert as Assert


Connection connection = null

url = findTestData('DB Credentials/Config1').getValue('URL', 1)

dbname = findTestData('DB Credentials/Config1').getValue('dbname', 1)

username = findTestData('DB Credentials/Config1').getValue('username', 1)

password = findTestData('DB Credentials/Config1').getValue('password', 1)

GlobalVariable.button='135.00'

//GlobalVariable.button=findTestData('Mobile Input Data/Empty_Return').getValue('SKU_Name', 1)

queryString = (((((('Select * from AppData_Indicative_Empties_collection_Header where IECH_Date=\'' + GlobalVariable.label1) +
'\'  and IECH_ARTR_Id =\'') + GlobalVariable.label) + '\'  and IECH_Value =\'') + GlobalVariable.button) +'\'')

KeywordUtil.logInfo('Query 1 = ' + queryString)

String conn = (((((('jdbc:sqlserver://' + url) + ';databaseName=') + dbname) + ';user=') + username) + ';password=') + password

connection = DriverManager.getConnection(conn)

Statement stm = connection.createStatement()

ResultSet rs = stm.executeQuery(queryString)

def recordSet = rs

if (recordSet.next()) {
	
	Object Empties_header_id = recordSet.getObject('IECH_Id')

	KeywordUtil.logInfo('Empties header id = ' + Empties_header_id)

	GlobalVariable.button = Empties_header_id
	
	
	Object Settlement_Mode = recordSet.getObject('IECH_Settlement_Mode')
	
		KeywordUtil.logInfo(' Return Settlement Mode = ' + Settlement_Mode)
	
Mobile.verifyMatch(Settlement_Mode, null, false, FailureHandling.STOP_ON_FAILURE)
	
}
else {
	'If data is empty in the DB, then the flow must fail'
	Assert.fail()
}

GlobalVariable.label=findTestData('Mobile Input Data/Empty_Return').getValue('SKU_Name', 1)

queryString2 = (((('Select * from AppData_Indicative_Empties_Collection_Detail where IECD_IECH_Id=\'' + GlobalVariable.button) +
'\'  and IECD_APH_Id =\'') +GlobalVariable.label)  +'\'')

KeywordUtil.logInfo('Query 2 = ' + queryString2)

ResultSet rs2 = stm.executeQuery(queryString2)

def recordSet2 = rs2

if (recordSet2.next()) {
	
	Object Line_Value = recordSet2.getObject('IECD_Line_Value')
	
	KeywordUtil.logInfo('LineValue =' + Line_Value)
	
	Object Price = recordSet2.getObject('IECD_Price')
	
	KeywordUtil.logInfo('Prodcut Price = ' + Price)
	
	Object Return_qty = recordSet2.getObject('IECD_Qty')
	
	KeywordUtil.logInfo('Empty Return qty = ' + Return_qty)
	
}

else {
	'If data is empty in the DB, then the flow must fail'
	Assert.fail()
}

//Empties partial return for Second Product

GlobalVariable.label=findTestData('Mobile Input Data/Empty_Return').getValue('SKU_Name', 1)

queryString3 = (((('Select * from AppData_Indicative_Empties_Collection_Detail where IECD_IECH_Id=\'' + GlobalVariable.button) +
'\'  and IECD_APH_Id =\'') +GlobalVariable.label)  +'\'')

KeywordUtil.logInfo('Query 3 = ' + queryString3)

ResultSet rs3 = stm.executeQuery(queryString3)

def recordSet3 = rs3

if (recordSet3.next()) {

	
	Object Line_Value = recordSet3.getObject('IECD_Line_Value')
	
	KeywordUtil.logInfo('LineValue = ' + Line_Value)
	
	Object Price = recordSet3.getObject('IECD_Price')
	
	KeywordUtil.logInfo('Prodcut Price = ' + Price)
	
	Object Return_qty = recordSet3.getObject('IECD_Qty')
	
	KeywordUtil.logInfo('Empty Return qty = ' + Return_qty)
}

else {
	'If data is empty in the DB, then the flow must fail'
	Assert.fail()
}
