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

Mobile.callTestCase(findTestCase('Product_Mobile/Common/DatePicker_and _Navigation/DB_Currentdate'), [:], FailureHandling.STOP_ON_FAILURE)

String conn = (((((('jdbc:sqlserver://' + GlobalVariable.DB_URL) + ';databaseName=') + GlobalVariable.DB_Name) + ';user=') + GlobalVariable.DB_Username) + ';password=') + GlobalVariable.DB_Password

connection = DriverManager.getConnection(conn)

Statement stm = connection.createStatement()

'Execute Query 01'

queryString = (('delete from ADM_Digital_Content where CONVERT(varchar, ADC_Created_Date, 23) like \'' + GlobalVariable.First_Date) + '\'')

KeywordUtil.logInfo(queryString)

result = stm.executeUpdate(queryString)

KeywordUtil.logInfo(result.toString())

'Execute Query 02' 
queryString = (('delete from ADM_Digital_Content_Criteria_Mapping where CONVERT(varchar, ADCCM_Created_Date, 23) like \'' + GlobalVariable.First_Date) + '\'')

KeywordUtil.logInfo(queryString)

result = stm.executeUpdate(queryString)

KeywordUtil.logInfo(result.toString())

'Execute Query 03'
queryString = (('delete from ADM_Digital_Content_Mapping_Header where CONVERT(varchar, ADCMH_Effective_From, 23) like \'' + GlobalVariable.First_Date) + '\' ')

KeywordUtil.logInfo(queryString)

result = stm.executeUpdate(queryString)

KeywordUtil.logInfo(result.toString())

'Execute Query 04'

queryString = (((('Select * from AppData_Digital_Content_Tracking_Header where DCTH_User_Id = \'' + GlobalVariable.vanseller_user_id) + '\' and DCTH_Date =\'') + GlobalVariable.sDate) + '\' ')

KeywordUtil.logInfo(queryString)

result = stm.executeQuery(queryString)

KeywordUtil.logInfo(result.toString())

def recordSet = result

while (recordSet.next()) {

	String DCTH_Id = recordSet.getObject('DCTH_Id')

	queryString = (('delete from AppData_Digital_Content_Tracking_Detail where DCTD_DCTH_Id = \'' + DCTH_Id) + '\'')
	
	KeywordUtil.logInfo(queryString)
	
	result = stm.executeUpdate(queryString)
	
	KeywordUtil.logInfo(result.toString())

	
}

'Execute Query 05'

queryString = (((('delete from AppData_Digital_Content_Tracking_Header where DCTH_User_Id = \'' + GlobalVariable.vanseller_user_id) + '\' and DCTH_Date =\'') + GlobalVariable.sDate) + '\' ')

KeywordUtil.logInfo(queryString)

result = stm.executeUpdate(queryString)

KeywordUtil.logInfo(result.toString())
