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
import com.kms.katalon.entity.global.GlobalVariableEntity
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil

WebUI.callTestCase(findTestCase('Product_Mobile/Todays Plan/CT/TP_CT_01'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('Product_Mobile/Todays Plan/CT/TP_CT_02'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('Product_Mobile/Common/Web Login and Navigation/ClearCashe Login'), [:], FailureHandling.STOP_ON_FAILURE)

String sheet_name = 'Task_Management'

String file_name = 'Web Input Data'

ArrayList<String> Retailer = CustomKeywords.'poi.Automation.GetAllWebDataRow_Flag.getAllDataRow'(file_name, sheet_name,
		'Retailer')

for (int i = 0; i < Retailer.size(); i++) {

	//Mobile.callTestCase(findTestCase('Product_Mobile/Common/Mobile_Login/PreSeller_Login'), [:], FailureHandling.STOP_ON_FAILURE)

	Mobile.callTestCase(findTestCase('Product_Mobile/Common/Mobile_Login/VanSeller_Login-With Reset'), [:], FailureHandling.STOP_ON_FAILURE)
	
	Mobile.tap(findTestObject('Mobile/Deviation/Trade Coverage Menu'), 0)

	Number_of_store = Mobile.getText(findTestObject('Mobile/Seller_2/Todays Plan/Number_of_store'),
			0)

	KeywordUtil.logInfo(Number_of_store)
	
	Mobile.tap(findTestObject('Product/Mobile Part/Seller_2/Deviation/Deviation Button'), 3)
	
	if (Mobile.verifyElementVisible(findTestObject('Product/Mobile Part/Seller_2/Deviation/While using the app For Deviation'),
		3, FailureHandling.OPTIONAL)) {
		Mobile.tap(findTestObject('Product/Mobile Part/Seller_2/Deviation/While using the app For Deviation'), 5)
	}
	
	Mobile.tap(findTestObject('Product/Mobile Part/Seller_2/Deviation/Search Icon'), 3)
	
	GlobalVariable.RetailerName = findTestData('Mobile Input Data/Deviation').getValue('Retailer', 1)
	
	Mobile.setText(findTestObject('Product/Mobile Part/Seller_2/Deviation/Search_Input'), GlobalVariable.RetailerName, 5)
	
	Mobile.tap(findTestObject('Product/Mobile Part/Seller_2/Deviation/Retailer Name'), 3)
	
	Mobile.tap(findTestObject('Product/Mobile Part/Seller_2/Deviation/Add To Plan Button'), 5)
	
	Mobile.tap(findTestObject('Product/Mobile Part/Seller_2/Deviation/today task over RBTN'), 5)
	
	Mobile.tap(findTestObject('Product/Mobile Part/Seller_2/Deviation/Todays PLan ADD Button'), 5)
	
	Mobile.tap(findTestObject('Product/Mobile Part/Seller_2/Deviation/Task save OK button'), 5)
	
	
	Mobile.tap(findTestObject('Mobile/Common/Icon_Search'), 3)
	
	Mobile.tap(findTestObject('Mobile/Common/Field_Search_EnterText'), 5)
	
	GlobalVariable.ProductName = findTestData('Mobile Input Data/Todays Plan').getValue('Retailer', 1)

	Mobile.sendKeys(findTestObject('Mobile/Common/Field_Search_EnterText'), GlobalVariable.ProductName)

	Mobile.takeScreenshot()
	
	Mobile.tap(findTestObject('Mobile/Common/Btn_Menu'), 0)
	
	Mobile.tap(findTestObject('Mobile/Deviation/Trade Coverage Menu'), 0)
	
	Mobile.tap(findTestObject('Mobile/Common/Field_Search_EnterText'), 0)

	GlobalVariable.ProductName = findTestData('Mobile Input Data/Todays Plan').getValue('RetailerName', 1)

	Mobile.sendKeys(findTestObject('Mobile/Common/Field_Search_EnterText'), GlobalVariable.ProductName)

	Mobile.takeScreenshot()
	
	KeywordUtil.logInfo('User is able to search the store name')

	Retailer_name = Mobile.getText(findTestObject('Mobile/Seller Task/Retailer'), 0)

	KeywordUtil.logInfo('Retailer_name = ' + Retailer_name)

	Mobile.tap(findTestObject('Mobile/Seller Task/Retailer'), 0)

	Mobile.takeScreenshot()
	
	Store_Name = Mobile.getText(findTestObject('Mobile/Seller_2/Todays Plan/Store Name Selected'), 0)

	KeywordUtil.logInfo(Store_Name)

	Mobile.verifyMatch(	findTestData('Mobile Input Data/Todays Plan').getValue('RetailerName', 2), Store_Name, false)
	
	Mobile.takeScreenshot()
	
	GlobalVariable.label = findTestData('Mobile Input Data/Todays Plan').getValue('Location', 1)

	Location_Name = Mobile.getText(findTestObject('Mobile/Seller_2/Todays Plan/Location'), 0)

	KeywordUtil.logInfo(Location_Name)

Mobile.verifyMatch(	findTestData('Mobile Input Data/Todays Plan').getValue('Location', 2), Location_Name, false)
	
	Mobile.takeScreenshot()
	}

Mobile.tap(findTestObject('Mobile/Seller Task/Start Visit button'), 0)

Mobile.tap(findTestObject('Mobile/Deviation/Location validation yes button'), 5)

GlobalVariable.label = 'Stock and Order'

Mobile.scrollToText(GlobalVariable.label)
	
Mobile.tap(findTestObject('Mobile/Deviation/Stock and Order Menu'), 5)

//Mobile.tap(findTestObject('Mobile/Common/Icon_Search'), 5)
//
//Mobile.tap(findTestObject('Mobile/Common/Field_Search_EnterText'), 5)
//
//GlobalVariable.ProductName = findTestData('Mobile Input Data/Deviation').getValue('planned_retailer', 1)
//
//Mobile.setText(findTestObject('Mobile/Common/Field_Search_EnterText'), GlobalVariable.ProductName,
//		5)

Mobile.tap(findTestObject('Mobile/Deviation/Enter Qty'), 5)

Mobile.tap(findTestObject('Mobile/Deviation/1 qty'), 5)

Mobile.tap(findTestObject('Mobile/Deviation/Avail_CheckBox'), 5)

Product_Amt = Mobile.getText(findTestObject('Mobile/Deviation/Product Total AMT'), 5)

GlobalVariable.Price = Product_Amt

KeywordUtil.logInfo('Product_Amt is  = ' + GlobalVariable.Price)

Mobile.tap(findTestObject('Mobile/Deviation/Next button'), 5)

Mobile.delay(6)

Mobile.tap(findTestObject('Mobile/Common/Btn_Done'), 5)

Mobile.delay(5)

Mobile.tap(findTestObject('Mobile/Deviation/Scheme Next Button'), 5)

Summary_Amt = Mobile.getText(findTestObject('Mobile/Deviation/Summary Total AMT'), 5)

KeywordUtil.logInfo('Summary_Amt is  = ' + Summary_Amt)

Mobile.tap(findTestObject('Mobile/Seller_2/Todays Plan/Order_button'), 5)

Mobile.tap(findTestObject('Mobile/Common/Btn_Confirm'), 5)

Mobile.delay(2)

Order_popup_text = Mobile.getText(findTestObject('Mobile/Seller_2/Todays Plan/Order Saved Popup'), 0)

KeywordUtil.logInfo('Order text is  = ' + Order_popup_text)

Submitted_1 = Order_popup_text.split(':')

Submitted_2 = (Submitted_1[1])

String order_num = (Submitted_2.replaceAll("'",""))

GlobalVariable.ordernumber = order_num

Mobile.tap(findTestObject('Mobile/Common/Btn_OK'), 5)

Mobile.delay(5)

Mobile.tap(findTestObject('Mobile/Seller Task/Close Call1 Button'), 3)

//Mobile.tap(findTestObject('Mobile/Seller Task/Select Reason for no order'), 3)
//
//Mobile.tap(findTestObject('null'), 3)
//
//Mobile.tap(findTestObject('Mobile/Seller Task/Remarks'), 3)
//
//Remark = 'Testing'
//
//Mobile.setText(findTestObject('Mobile/Seller Task/Remarks'), Remark, 5)
Mobile.waitForElementPresent(findTestObject('Mobile/Seller Task/Close Call2 Button'), 4)

Mobile.tap(findTestObject('Mobile/Seller Task/Close Call2 Button'), 4)

Mobile.tap(findTestObject('Mobile/Common/Btn_OK'), 4)

Mobile.delay(3)

//sync
Mobile.tap(findTestObject('Mobile/Common/Btn_Menu'), 0)

Mobile.swipe(0, 400, 0, 200, FailureHandling.OPTIONAL)

Mobile.tap(findTestObject('Mobile/MainMenu/Menu_Sync'), 0)

Mobile.tap(findTestObject('Mobile/Sync/Btn-SYNCHRONIZATION'), 0)

Mobile.verifyElementVisible(findTestObject('Mobile/Common/Btn_OK'), 100)

Mobile.tap(findTestObject('Mobile/Common/Btn_OK'), 0)

Mobile.verifyElementVisible(findTestObject('Mobile/Seller_2/Survey/Download_btn'), 100)

Mobile.tap(findTestObject('Mobile/Seller_2/Survey/Download_btn'), 0)

Mobile.verifyElementVisible(findTestObject('Mobile/Common/Btn_OK'), 100)

Mobile.tap(findTestObject('Mobile/Common/Btn_OK'), 0)

Mobile.delay(6)

WebUI.callTestCase(findTestCase('Product_Mobile/Todays Plan/DB'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('Product_Mobile/Todays Plan/CT/TP_CT_03'), [:], FailureHandling.STOP_ON_FAILURE)

//Mobile.callTestCase(findTestCase('Product_Mobile/Common/Mobile_Login/PreSeller_Login'), [:], FailureHandling.STOP_ON_FAILURE)

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Mobile_Login/VanSeller_Login-With Reset'), [:], FailureHandling.STOP_ON_FAILURE)

Mobile.tap(findTestObject('Mobile/Common/Btn_Menu'), 0)

Mobile.tap(findTestObject('Mobile/Deviation/Trade Coverage Menu'), 0)

Mobile.tap(findTestObject('Mobile/Common/Icon_Search'), 0)

GlobalVariable.ProductName = findTestData('Mobile Input Data/Todays Plan').getValue('RetailerName', 1)

Mobile.sendKeys(findTestObject('Mobile/Common/Field_Search_EnterText'), GlobalVariable.ProductName)

Mobile.verifyElementNotVisible(findTestObject('Mobile/Seller Task/Retailer'), 0)

Mobile.takeScreenshot()

WebUI.callTestCase(findTestCase('null'), [:], FailureHandling.STOP_ON_FAILURE)


