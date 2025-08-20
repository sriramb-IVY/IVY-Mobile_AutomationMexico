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
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import java.sql.Connection as Connection
import java.sql.DriverManager as DriverManager
import java.sql.ResultSet as ResultSet
import java.sql.Statement as Statement
import java.text.SimpleDateFormat as SimpleDateFormat
import com.kms.katalon.core.mobile.keyword.internal.MobileDriverFactory as MobileDriverFactory
import io.appium.java_client.AppiumDriver as AppiumDriver
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import org.openqa.selenium.WebElement as WebElement
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import org.junit.Assert as Assert
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil

not_run: WebUI.callTestCase(findTestCase('Product_Mobile/Common/Web Login and Navigation/DistributorLogin'), [:], FailureHandling.STOP_ON_FAILURE)

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Web Login and Navigation/Navigate to Collection Acceptance'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Web Part/Collection Acceptance/SalesPerson'))

GlobalVariable.label = findTestData('Mobile Input Data/Collection').getValue('SalesPerson', 1)

WebUI.click(findTestObject('Web Part/Collection Acceptance/Global_Dropdown_Option(2)'))

WebUI.delay(5)

WebUI.click(findTestObject('Web Part/Collection Acceptance/Search_Btn'))

WebUI.click(findTestObject('Web Part/Collection Acceptance/Search_Btn'), FailureHandling.OPTIONAL)

WebUI.waitForElementNotVisible(findTestObject('Web Part/Re-usable/Page Load'), 100)

WebUI.click(findTestObject('Web Part/Collection Acceptance/Show Denomination'))

WebUI.waitForElementNotVisible(findTestObject('Web Part/Re-usable/Page Load'), 100)

WebUI.takeScreenshot()

GlobalVariable.Collected_Cash_Amt = findTestData('Mobile Input Data/Collection').getValue('AMT_1', 1)

Expected_Amt = WebUI.getAttribute(findTestObject('Web Part/Collection Acceptance/Amount'), 'value')

Expected_Amt = Expected_Amt.replace('$ ', '')

WebUI.verifyEqual(Double.parseDouble(GlobalVariable.Collected_Cash_Amt), Double.parseDouble(Expected_Amt))

WebUI.scrollToElement(findTestObject('Web Part/Collection Acceptance/Grid_TotalValue'), 0)

WebUI.takeScreenshot()

Expected_Amt = WebUI.getText(findTestObject('Web Part/Collection Acceptance/Grid_TotalValue'))

Expected_TotalValue = Expected_Amt.replace('$ ', '')

WebUI.verifyEqual(Double.parseDouble(GlobalVariable.Collected_Cash_Amt), Double.parseDouble(Expected_TotalValue))

String sheet_name = 'Collection'

String file_name = 'Mobile Input Data'

ArrayList<String> Field_name = CustomKeywords.'poi.Automation.GetAllDataRow_Flag.getAllDataRow'(file_name, sheet_name, 'Field_name')

ArrayList<String> Entered_Count = CustomKeywords.'poi.Automation.GetAllDataRow_Flag.getAllDataRow'(file_name, sheet_name, 'Entered_Count')

ArrayList<String> Entered_Amt = CustomKeywords.'poi.Automation.GetAllDataRow_Flag.getAllDataRow'(file_name, sheet_name, 'Entered_Amt')

for (a = 0; a < Entered_Count.size(); a++) {

	if (Entered_Count.get(a) != '') {


		WebUI.takeScreenshot()

		GlobalVariable.label = Field_name.get(a)

		KeywordUtil.logInfo(GlobalVariable.label)

		count = WebUI.getText(findTestObject('Web Part/Collection Acceptance/Count_Field(global)'), FailureHandling.STOP_ON_FAILURE)

		WebUI.verifyEqual(Integer.parseInt(count), Integer.parseInt(Entered_Count.get(a)))

		value = WebUI.getText(findTestObject('Web Part/Collection Acceptance/Value_Field(global)'), FailureHandling.STOP_ON_FAILURE)

		value = value.replace('$ ', '')

		WebUI.verifyEqual(Double.parseDouble(value), Integer.parseInt(Entered_Amt.get(a)))
	}
}

WebUI.click(findTestObject('Web Part/Collection Acceptance/Close Btn'))


'Validate denomination_Header table '
String pattern = 'yyyy-MM-dd'

SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern)

String currentdate = simpleDateFormat.format(new Date())

println(currentdate)

DB_Currentdate = (currentdate + ' 00:00:00.000')

GlobalVariable.label = DB_Currentdate

Mobile.callTestCase(findTestCase('Product_Mobile/Common/DB'), [('Query') : 'select Top 1 * from AppData_Denomination_Header where DH_User_Id=\'' + GlobalVariable.vanseller_user_id + '\' and DH_Date = \'' + GlobalVariable.label + '\'', ('columnNames') : ['DH_Total_Amount']], FailureHandling.STOP_ON_FAILURE)

String DH_Total_Amount=GlobalVariable.data[0]

KeywordUtil.logInfo('Total amount : '+DH_Total_Amount)

Mobile.verifyEqual(Double.parseDouble(GlobalVariable.Collected_Cash_Amt), DH_Total_Amount, FailureHandling.STOP_ON_FAILURE)


GlobalVariable.Transaction_No = findTestData('Mobile Input Data/Collection').getValue('TransactionNo', 1)

Mobile.callTestCase(findTestCase('Product_Mobile/Common/DB'), [('Query') : 'select top 1 * from appdata_collection_request  where CR_UserId =\'' + GlobalVariable.vanseller_user_id + '\' and  cr_sih_no=  \'' + GlobalVariable.Transaction_No + '\'', ('columnNames') : ['CR_Paid_Amount','CR_Receipt_No']], FailureHandling.STOP_ON_FAILURE)

String CR_Paid_Amount=GlobalVariable.data[0]

KeywordUtil.logInfo('CR PAID amount : '+CR_Paid_Amount)

Mobile.verifyEqual(Double.parseDouble(GlobalVariable.Collected_Cash_Amt), CR_Paid_Amount, FailureHandling.STOP_ON_FAILURE)

Object CR_Receipt_No = GlobalVariable.data[1]

GlobalVariable.Receipt_No = CR_Receipt_No


WebUI.click(findTestObject('Web Part/Collection Acceptance/ReceiptNo_Search_Field'))

WebUI.setText(findTestObject('Web Part/Collection Acceptance/ReceiptNo_Search_Field'), GlobalVariable.Receipt_No)

WebUI.verifyElementVisible(findTestObject('Web Part/Collection Acceptance/Global_Td_A_Tag(grid Value)'))

WebUI.click(findTestObject('Web Part/Collection Acceptance/Checkbox_Enable'))

//////////
CR = '%C%'


Mobile.callTestCase(findTestCase('Product_Mobile/Common/DB'), [('Query') : 'select top 1 * from appdata_collection_request where cr_sih_no=\'' + GlobalVariable.Transaction_No + '\' and CR_ReferenceNo like \'' + CR + '\' and  CR_UserId =\'' + GlobalVariable.vanseller_user_id + '\'  order by 1 desc', ('columnNames') : ['CR_Receipt_No']], FailureHandling.STOP_ON_FAILURE)

String CR_Receipt_No=GlobalVariable.data[0]

KeywordUtil.logInfo('CR receipt no : '+CR_Receipt_No)

GlobalVariable.Receipt_No = CR_Receipt_No

WebUI.click(findTestObject('Web Part/Collection Acceptance/ReceiptNo_Search_Field'))

WebUI.setText(findTestObject('Web Part/Collection Acceptance/ReceiptNo_Search_Field'), GlobalVariable.Receipt_No)

WebUI.verifyElementVisible(findTestObject('Web Part/Collection Acceptance/Global_Td_A_Tag(grid Value)'))

WebUI.click(findTestObject('Web Part/Collection Acceptance/Checkbox_Enable'))

Mobile.callTestCase(findTestCase('Product_Mobile/Common/DB'), [('Query') : 'select top 1 * from appdata_collection_request where cr_sih_no=\'' + GlobalVariable.Transaction_No + '\' and CR_Cheque_No = 12345 and  CR_UserId =\'' + GlobalVariable.vanseller_user_id + '\'  order by 1 desc', ('columnNames') : ['CR_Receipt_No']], FailureHandling.STOP_ON_FAILURE)

String CR_Receipt_No=GlobalVariable.data[0]

KeywordUtil.logInfo('CR receipt no : '+CR_Receipt_No)

GlobalVariable.Receipt_No = CR_Receipt_No


WebUI.click(findTestObject('Web Part/Collection Acceptance/ReceiptNo_Search_Field'))

WebUI.setText(findTestObject('Web Part/Collection Acceptance/ReceiptNo_Search_Field'), GlobalVariable.Receipt_No)

WebUI.verifyElementVisible(findTestObject('Web Part/Collection Acceptance/Global_Td_A_Tag(grid Value)'))

WebUI.click(findTestObject('Web Part/Collection Acceptance/Checkbox_Enable'))

WebUI.click(findTestObject('Web Part/Collection Acceptance/Accept_btn'))

WebUI.click(findTestObject('Web Part/Collection Acceptance/OK_btn'))

WebUI.verifyElementPresent(findTestObject('Web Part/Collection Acceptance/Save Successful Alert'), 0)

