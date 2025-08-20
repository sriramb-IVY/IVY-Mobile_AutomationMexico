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
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import org.openqa.selenium.Keys as Keys

'APK reset and login:-'
WebUI.callTestCase(findTestCase('Product_Mobile/Common/Mobile_Login/VanSeller_Login-With Reset'), [:], FailureHandling.STOP_ON_FAILURE)

Mobile.scrollToText('Expense', FailureHandling.STOP_ON_FAILURE)

Mobile.tap(findTestObject('Mobile/Expense/Menu_Expense'), 0)

'Validating whether the Current Month Total Amount is 0:-'
currentMonthTotalAmt = Mobile.getText(findTestObject('Mobile/Expense/MTD_Total_Amount'), 0, FailureHandling.STOP_ON_FAILURE)

Mobile.verifyEqual(Double.parseDouble(currentMonthTotalAmt), 0.0, FailureHandling.STOP_ON_FAILURE)

Mobile.tap(findTestObject('Mobile/Expense/Tab_MTD'), 0) 

'Storing the amount and expense types from the sheet to an ArrayList for Validation:-'
String sheet_name = 'Expense'

String file_name = 'Mobile Input Data'

ArrayList<String> expenseType = CustomKeywords.'poi.Automation.GetAllDataRow_Flag.getAllDataRow'(file_name, sheet_name, 'Expense_Type')

ArrayList<String> amount = CustomKeywords.'poi.Automation.GetAllDataRow_Flag.getAllDataRow'(file_name, sheet_name, 'Amount')

int totalExpense = 0

for(i = 0; i < amount.size(); i++) {
	
	GlobalVariable.label = amount.get(i)
	
    AMT = amount.get(i)
	
	'Validating the Expense Type in the MTD screen:-'
	Mobile.verifyElementText(findTestObject('Mobile/Expense/MTD_Grid_Expense_Type'), expenseType.get(i), FailureHandling.STOP_ON_FAILURE)
	
	Mobile.takeScreenshot()
	
	totalExpense += Integer.parseInt(AMT)
	
	KeywordUtil.logInfo(totalExpense.toString())
	
	GlobalVariable.Total_Amt = totalExpense.toString()
}

'Validating the Total Amount in the MTD screen:-'

mtdTotalAmt = Mobile.getText(findTestObject('Mobile/Expense/MTD_Total_Amount'), 0, FailureHandling.STOP_ON_FAILURE)

Mobile.verifyEqual(Double.parseDouble(mtdTotalAmt), Double.parseDouble(GlobalVariable.Total_Amt), FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()





