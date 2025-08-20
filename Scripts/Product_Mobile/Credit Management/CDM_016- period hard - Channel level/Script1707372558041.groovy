import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import internal.GlobalVariable as GlobalVariable
import java.awt.Robot as Robot
import java.awt.event.KeyEvent as KeyEvent
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import org.apache.commons.lang.RandomStringUtils as RandomStringUtils
import com.kms.katalon.core.mobile.keyword.internal.MobileDriverFactory as MobileDriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import io.appium.java_client.AppiumDriver as AppiumDriver
import java.sql.Connection as Connection
import java.sql.DriverManager as DriverManager
import java.sql.ResultSet as ResultSet
import java.sql.Statement as Statement
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.keyword.excel.ExcelKeywords as ExcelKeywords



'Period Rfield - 0 Hard Alert'

CustomKeywords.'credit_management.Admin_Configs_1.Set_Configs'('CREDITDAY01', '0')

//Mobile.startApplication(GlobalVariable.APKFile, false)

//Mobile.tap(findTestObject('Mobile/Common/Btn_Menu'), 30)

String sheet_name = 'Channel_Retailer'

String file_name = 'Mobile Input data'

ArrayList<String> Retailer = CustomKeywords.'poi.Automation.GetAllDataRow_Flag.getAllDataRow'(file_name, sheet_name, 'Retailer')

ArrayList<String> Product = CustomKeywords.'poi.Automation.GetAllDataRow_Flag.getAllDataRow'(file_name, sheet_name, 'Product')

ArrayList<String> Quantity1 = CustomKeywords.'poi.Automation.GetAllDataRow_Flag.getAllDataRow'(file_name, sheet_name, 'Quantity1')

ArrayList<String> Period_Exceed = CustomKeywords.'poi.Automation.GetAllDataRow_Flag.getAllDataRow'(file_name, sheet_name, 'Period_Exceed')

WebUI.callTestCase(findTestCase('Product_Mobile/Credit Management/Call Test Case/Van_Load'), [:], FailureHandling.STOP_ON_FAILURE)

Mobile.tap(findTestObject('Mobile/Common/Btn_Menu'), 30)


for (int i = 3; i <=3; i++) {
	

Mobile.tap(findTestObject('Mobile/MainMenu/Menu_Trade Coverage'), 0)

	Mobile.tap(findTestObject('Mobile/Common/Icon_Search'), 5)

	Mobile.setText(findTestObject('Mobile/Common/Field_Search_EnterText'), Retailer.get(
			i), 5)

	Mobile.tap(findTestObject('Mobile/TradeCoverage/First_Retailer_Selection_From_List'), 5)

	Mobile.tap(findTestObject('Mobile/Common/Btn_StartVisit'), 5)

	if (Mobile.verifyElementVisible(findTestObject('Mobile/Common/Popup_Title_LocationValidation'), 5, FailureHandling.OPTIONAL)) {
		Mobile.tap(findTestObject('Mobile/Common/Btn_YES'), 5)
	}
	
	if (Mobile.verifyElementVisible(findTestObject('Mobile/Common/Global_Radio_Btn'), 1, FailureHandling.OPTIONAL)) {
		Mobile.tap(findTestObject('Mobile/Common/Global_Radio_Btn'), 5)
	}
	
	Mobile.delay(6)

	Mobile.swipe(0, 150, 0, 400, FailureHandling.OPTIONAL)

	Mobile.delay(1,FailureHandling.STOP_ON_FAILURE)
	
	Mobile.tap(findTestObject('Mobile/Store_Actvy/Menu-OrderInvoice'), 0, FailureHandling.STOP_ON_FAILURE)
	
	GlobalVariable.Alert = Period_Exceed.get(i)



	Mobile.verifyElementVisible(findTestObject('Object Repository/Mobile/Credit Management/ALERT_Message'),5)

	Mobile.takeScreenshot()
	
	Period_Hard_Alert_Text = Mobile.getText(findTestObject('Object Repository/Mobile/Credit Management/ALERT_Message'),
		0)
	
	KeywordUtil.logInfo(Period_Hard_Alert_Text)
	
	Mobile.verifyMatch(Period_Hard_Alert_Text, findTestData('Data Files/Mobile Input Data/Period_Bill_Combination').getValue('Period_Exceed', 4 ), false)

	Mobile.tap(findTestObject('Mobile/Common/Btn_OK'), 0, FailureHandling.STOP_ON_FAILURE)
	

  WebUI.callTestCase(findTestCase('Product_Mobile/Common/Call_Analysis'), [:], FailureHandling.STOP_ON_FAILURE)

	
	
	}

