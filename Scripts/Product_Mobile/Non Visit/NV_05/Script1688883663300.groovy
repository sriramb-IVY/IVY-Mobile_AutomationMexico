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

String pattern = 'yyyy-MM-dd'

SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern)

String currentdate = simpleDateFormat.format(new Date())

println(currentdate)

DB_Currentdate = (currentdate + ' 00:00:00.000')


Mobile.callTestCase(findTestCase('Product_Mobile/Common/DB'), [('Query') : 'select  * from AppData_Non_Productive_Calls where NPC_User_Id =  \'' + GlobalVariable.vanseller_user_id + '\' and NPC_Submitted_Date = \'' + DB_Currentdate + '\' order by 1 desc', ('columnNames') : ['NPC_Remarks']], FailureHandling.STOP_ON_FAILURE)

String Remark=GlobalVariable.data[0]

KeywordUtil.logInfo('Remarks : '+Remark)

GlobalVariable.label = findTestData('Web Input Data/Task_Management').getValue('Remark', 1)

Mobile.verifyMatch(Remark, GlobalVariable.label, false)

