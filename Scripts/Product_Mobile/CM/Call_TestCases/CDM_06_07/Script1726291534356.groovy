import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
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
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil

'Collected invoice cash on the mobile and need to do collection Acceptance.'
Credit_Index = 1

Mobile.callTestCase(findTestCase('Product_Mobile/CM/Reusables/DB/Get_Over_Due_As_Last_Invoice_Amt'), [('RetailerCode') : findTestData('Mobile Input Data/CreditManagement').getValue('Retailer_Name', Credit_Index)], FailureHandling.STOP_ON_FAILURE)

Before_Collection_Acceptance_Overdue_Amt =  GlobalVariable.label

KeywordUtil.logInfo(Before_Collection_Acceptance_Overdue_Amt)

Mobile.callTestCase(findTestCase('Product_Mobile/CM/Reusables/Collection_Acceptance'), [('Credit_Index') : Credit_Index], FailureHandling.STOP_ON_FAILURE)

Mobile_Collection_Accepted_Amt = GlobalVariable.Paid_Amt

Mobile.callTestCase(findTestCase('Product_Mobile/CM/Reusables/DB/Get_Over_Due_As_Last_Invoice_Amt'), [('RetailerCode') : findTestData('Mobile Input Data/CreditManagement').getValue('Retailer_Name', Credit_Index)], FailureHandling.STOP_ON_FAILURE)

After_Collection_Acceptance_Overdue_Amt =  GlobalVariable.label

KeywordUtil.logInfo(After_Collection_Acceptance_Overdue_Amt)

'Verified overdue column in the ADM_Retailer_Over_Due, amount should be reduced after collection acceptenance.'

Mobile.verifyLessThan(After_Collection_Acceptance_Overdue_Amt, Before_Collection_Acceptance_Overdue_Amt, FailureHandling.STOP_ON_FAILURE)

'Get Credit Balance before increase the credit limit'

Mobile.callTestCase(findTestCase('Product_Mobile/CM/Reusables/DB/Get_Credit_Bal_DB'), [('RetailerCode') : findTestData('Mobile Input Data/CreditManagement').getValue('Retailer_Name', Credit_Index)], FailureHandling.STOP_ON_FAILURE)

Credit_Balanace_Before_Increase_Credit_Limit = GlobalVariable.Credit_Balance

KeywordUtil.logInfo('Credit Balance BEFORE increase the credit limit :' + Credit_Balanace_Before_Increase_Credit_Limit)

'Increased the Credit_Limit value in web '
Mobile.callTestCase(findTestCase('Product_Mobile/CM/Reusables/DB/Increase_Value_In_Web'), [('Credit_Index') : Credit_Index], FailureHandling.STOP_ON_FAILURE)

'Get Credit Balance after increase the credit limit'
Mobile.callTestCase(findTestCase('Product_Mobile/CM/Reusables/DB/Get_Credit_Bal_DB'), [('RetailerCode') : findTestData('Mobile Input Data/CreditManagement').getValue('Retailer_Name', Credit_Index)], FailureHandling.STOP_ON_FAILURE)

Credit_Balanace_After_Increase_Credit_Limit = GlobalVariable.Credit_Balance

KeywordUtil.logInfo('Credit Balance AFTER increase the credit limit :' + Credit_Balanace_After_Increase_Credit_Limit)

'Verify the ADM_Retailer_Over_Due table credit balance value changed after increase the credit limit in web side'
Credit_Limit = findTestData('Mobile Input Data/CreditManagement').getValue('Credit_Limit', Credit_Index)

Increased_Credit_Limit = findTestData('Mobile Input Data/CreditManagement').getValue('Increase_Value', Credit_Index)

Expected_Credit_Balance_In_DB = (Double.parseDouble(Increased_Credit_Limit) - Double.parseDouble(Credit_Limit))

Actual_Credit_Balance_In_DB = (Double.parseDouble(Credit_Balanace_After_Increase_Credit_Limit) - Double.parseDouble(Credit_Balanace_Before_Increase_Credit_Limit))

WebUI.verifyEqual(Actual_Credit_Balance_In_DB, Expected_Credit_Balance_In_DB, FailureHandling.STOP_ON_FAILURE)