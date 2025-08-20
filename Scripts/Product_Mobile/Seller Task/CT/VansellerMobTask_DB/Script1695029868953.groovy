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


Connection connection = null

url = findTestData('DB Credentials/Config1').getValue('URL', 1)

dbname = findTestData('DB Credentials/Config1').getValue('dbname', 1)

username = findTestData('DB Credentials/Config1').getValue('username', 1)

password = findTestData('DB Credentials/Config1').getValue('password', 1)

String conn = (((((('jdbc:sqlserver://' + url) + ';databaseName=') + dbname) + ';user=') + username) + ';password=') + password

connection = DriverManager.getConnection(conn)

Statement stm = connection.createStatement()

'Task created by vanseller mobile'
GlobalVariable.input = findTestData('Mobile Input Data/Task_Management').getValue('Title_Name', 1)

queryString = (('select top 1 *  from ADM_Task  where AT_Code= \'' + GlobalVariable.input) + '\' order by 1 desc ')

KeywordUtil.logInfo(queryString)

rs = stm.executeQuery(queryString)

recordSet = rs

if (recordSet.next()) {
	data1 = recordSet.getObject('AT_Id')

	GlobalVariable.label = data1

	KeywordUtil.logInfo(data1 + 'ADM_Task table Id used to get AppData_Task_Completion_History table data')

	'Verify CPG task properly inserted into the ADM_Task'
	AT_Code = recordSet.getObject('AT_Code')

	WebUI.verifyEqual(AT_Code, findTestData('Mobile Input Data/Task_Management').getValue('Title_Name', 1), FailureHandling.STOP_ON_FAILURE)

	AT_Description = recordSet.getObject('AT_Description')

	WebUI.verifyEqual(AT_Description, findTestData('Mobile Input Data/Task_Management').getValue('Title_Description', 1),
		FailureHandling.STOP_ON_FAILURE)
} else {
	'If data is empty in the DB, then the flow must fail'
	Assert.fail()
}

queryString = (('select * from AppData_Task_Completion_History  where TCH_AT_Id = \'' + GlobalVariable.label) + '\' order by 1 desc ')

KeywordUtil.logInfo(queryString)

rs1 = stm.executeQuery(queryString)

recordSet1 = rs1

if (recordSet1.next()) {
	data1 = recordSet1.getObject('TCH_ARTR_Id')

	GlobalVariable.label = data1

	KeywordUtil.logInfo(data1 + 'AppData_Task_Completion_History table retailer Id used to get adm_retailer table data')
} else {
	'If data is empty in the DB, then the flow must fail'
	Assert.fail()
}

queryString = (('select * from ADM_retailer where  ARTR_Id = \'' + GlobalVariable.label) + '\' order by 1 desc ')

KeywordUtil.logInfo(queryString)

rs2 = stm.executeQuery(queryString)

recordSet2 = rs2

if (recordSet2.next()) {
	data1 = recordSet2.getObject('ARTR_Name')

	GlobalVariable.label = data1

	KeywordUtil.logInfo(data1 + ' : Retailer Name')

	Mobile.verifyMatch(data1, findTestData('Mobile Input Data/Task_Management').getValue('Retailer', 1), false, FailureHandling.STOP_ON_FAILURE)
} else {
	'If data is empty in the DB, then the flow must fail'
	Assert.fail()
}

