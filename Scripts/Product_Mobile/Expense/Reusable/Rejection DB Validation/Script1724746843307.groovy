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

Mobile.callTestCase(findTestCase('Product_Mobile/Common/DatePicker_and _Navigation/DB_OtherDate(past,future)'), [('Other_Date') : -2], FailureHandling.CONTINUE_ON_FAILURE)



Mobile.callTestCase(findTestCase('Product_Mobile/Common/DB'), [('Query') : 'SELECT * FROM AppData_Expense_Header WHERE CONVERT(date, AEH_Date) = \'' + GlobalVariable.sDate + '\' AND AEH_Total_Amount = \'' + GlobalVariable.Total_Amt + '\' order by 1 desc', ('columnNames') : ['AEH_Reference_No','AEH_Total_Amount','AEH_Approval_Status']], FailureHandling.STOP_ON_FAILURE)

String AEH_Reference_No=GlobalVariable.data[0]

KeywordUtil.logInfo('Reference No : '+AEH_Reference_No)

GlobalVariable.label = AEH_Reference_No

String AEH_Total_Amount=GlobalVariable.data[1]

KeywordUtil.logInfo(AEH_Total_Amount)

Mobile.verifyEqual(Double.parseDouble(AEH_Total_Amount), Double.parseDouble(GlobalVariable.Total_Amt), FailureHandling.CONTINUE_ON_FAILURE)

String AEH_Approval_Status = GlobalVariable.data[2]

KeywordUtil.logInfo(AEH_Approval_Status)

GlobalVariable.expenseStatus = findTestData('Web Input Data/Expense Approval').getValue('DB_Status', 1)

Mobile.verifyMatch(AEH_Approval_Status, GlobalVariable.expenseStatus, false, FailureHandling.CONTINUE_ON_FAILURE)



'Validation in Expense detail table'

Mobile.callTestCase(findTestCase('Product_Mobile/Common/DB'), [('Query') : 'select * from AppData_Expense_Detail where AED_Reference_No = \'' + GlobalVariable.label + '\'', ('columnNames') : ['AED_Amount','AED_Reimbursed_Amount','AED_Approval_Status']], FailureHandling.STOP_ON_FAILURE)

String AED_Amount=GlobalVariable.data[0]

KeywordUtil.logInfo('Amount : '+AED_Amount)


Mobile.verifyEqual(Double.parseDouble(AED_Amount), Double.parseDouble(GlobalVariable.Total_Amt), FailureHandling.CONTINUE_ON_FAILURE)

String AED_Reimbursed_Amount = GlobalVariable.data[1]

KeywordUtil.logInfo(AED_Reimbursed_Amount)

Mobile.verifyEqual(Double.parseDouble(AED_Reimbursed_Amount), Double.parseDouble(GlobalVariable.reimbursedAmt), FailureHandling.CONTINUE_ON_FAILURE)

String AED_Approval_Status = GlobalVariable.data[2]

KeywordUtil.logInfo(AED_Approval_Status)

Mobile.verifyMatch(AED_Approval_Status, GlobalVariable.expenseStatus, false, FailureHandling.CONTINUE_ON_FAILURE)


