package credit_management

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import com.kms.katalon.core.mobile.keyword.internal.MobileDriverFactory as MobileDriverFactory
import internal.GlobalVariable as GlobalVariable
import java.sql.DriverManager as DriverManager
import java.sql.ResultSet as ResultSet
import java.text.SimpleDateFormat as SimpleDateFormat
import io.appium.java_client.AppiumDriver as AppiumDriver
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil


public class Stock_manual_load_1 {

	@Keyword
	public String Add_Stock(def Product, def Qty) {

		//		Mobile.startApplication(GlobalVariable.APKFile, false)
		//
		//		Mobile.tap(findTestObject('Mobile/Common/Btn_Menu'), 0)

		Mobile.tap(findTestObject('Mobile/MainMenu/Menu_End of the Day'), 0)

		Mobile.tap(findTestObject('Mobile/End of the Day/SubMenu_Manual Van Load'), 0)

		Mobile.delay(2, FailureHandling.STOP_ON_FAILURE)

		Mobile.tap(findTestObject('Mobile/End of the Day/Manual Van Load/Select Distributor'), 0)

		Mobile.delay(2, FailureHandling.STOP_ON_FAILURE)

		GlobalVariable.label = GlobalVariable.DistributorName

		Mobile.tap(findTestObject('Mobile/End of the Day/Manual Van Load/Ditributor_Dropdown_option'), 0)

		Mobile.verifyElementExist(findTestObject('Mobile/End of the Day/Manual Van Load/ProductName'), 0)

		Mobile.verifyElementVisible(findTestObject('Mobile/End of the Day/Manual Van Load/ProductName'), 0)

		Mobile.verifyElementExist(findTestObject('Mobile/End of the Day/Manual Van Load/SIH'), 0)

		Mobile.verifyElementVisible(findTestObject('Mobile/End of the Day/Manual Van Load/SIH'), 0)

		Mobile.takeScreenshot()

		Mobile.tap(findTestObject('Mobile/Common/Icon_Search'), 0)

		Mobile.setText(findTestObject('Mobile/Common/Field_Search_EnterText'), Product, 10)

		Mobile.verifyElementVisible(findTestObject('Mobile/End of the Day/Manual Van Load/ProductName'), 0)

		Mobile.verifyElementText(findTestObject('Mobile/End of the Day/Manual Van Load/ProductName'), Product)

		Mobile.takeScreenshot()

		println('User verified the functionality of the product hierarchy filter icon in the manual van load screen')

		Mobile.delay(1)

		Mobile.hideKeyboard(FailureHandling.OPTIONAL)

		Mobile.tap(findTestObject('Mobile/End of the Day/Manual Van Load/Case'), 2, FailureHandling.OPTIONAL)

		Mobile.setText(findTestObject('Mobile/End of the Day/Manual Van Load/Case'), Qty , 10)

		println('user can able to enter quantity to the product in the Manual van load screen')

		Mobile.tap(findTestObject('Mobile/End of the Day/Manual Van Load/Save-btn'), 0)

		Mobile.tap(findTestObject('Mobile/Common/Btn_OK'), 0)

		AppiumDriver driver = MobileDriverFactory.getDriver()

		String Actualtoastmessage = driver.findElementByXPath('//android.widget.Toast[1]').getText()

		Mobile.verifyMatch(Actualtoastmessage, findTestData('Mobile Input Data/ManualVanLoad').getValue('Verify', 1), false, FailureHandling.STOP_ON_FAILURE)

		Mobile.takeScreenshot()

		println('Van Unload should be saved successfully')

		Mobile.tap(findTestObject('Mobile/Common/Btn_Menu'), 2, FailureHandling.OPTIONAL)

		Mobile.tap(findTestObject('Mobile/MainMenu/Menu_Load Management'), 0)

		Mobile.tap(findTestObject('Mobile/LoadManagement/SubMenu-Van Load'), 0)

		Mobile.delay(10)
		
		String pattern = 'dd-MM-yyyy'

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern)

		String currentdate = simpleDateFormat.format(new Date())

		println(currentdate)

		String current_date = currentdate.replaceAll('\\D+', '')

		KeywordUtil.logInfo(current_date)

		String UserID = findTestData('Mobile Input Data/ManualVanLoad').getValue('User_ID', 1)

		String Vanload_no = UserID + current_date

		KeywordUtil.logInfo(Vanload_no)

		GlobalVariable.VanLoad_No = Vanload_no

		Mobile.scrollToText(GlobalVariable.VanLoad_No, FailureHandling.OPTIONAL)

		GlobalVariable.VanLoad_No = Mobile.getText(findTestObject('Mobile/LoadManagement/Vanload/Vanload_No(contains)'),
				0, FailureHandling.STOP_ON_FAILURE)

		
		Mobile.tap(findTestObject('Mobile/LoadManagement/Vanload/Vanload_No(contains)'), 0)

		
		Mobile.tap(findTestObject('Mobile/Common/Icon_Search'), 0)

		//GlobalVariable.ProductName = findTestData('Mobile Input Data/Credit_Limit').getValue('Product_Name', 1)
		GlobalVariable.ProductName = Product

		Mobile.setText(findTestObject('Mobile/Common/Field_Search_EnterText'), GlobalVariable.ProductName,
				0)

		Mobile.verifyElementVisible(findTestObject('Mobile/LoadManagement/Vanload/ProductName'), 5, FailureHandling.STOP_ON_FAILURE)

		Mobile.verifyElementText(findTestObject('Mobile/LoadManagement/Vanload/ProductName'), GlobalVariable.ProductName,
				FailureHandling.STOP_ON_FAILURE)

		Mobile.takeScreenshot()

		Mobile.tap(findTestObject('Mobile/Common/Icon_Back'), 0)

		Mobile.scrollToText(GlobalVariable.VanLoad_No, FailureHandling.OPTIONAL)

		Mobile.tap(findTestObject('Mobile/LoadManagement/Vanload/Global_RadioIcon'), 3, FailureHandling.OPTIONAL)

		Mobile.tap(findTestObject('Mobile/LoadManagement/Vanload/VanLoadScreen-Accept Btn'), 3, FailureHandling.OPTIONAL)

		Mobile.tap(findTestObject('Mobile/LoadManagement/Vanload/OK Btn'), 3, FailureHandling.OPTIONAL)

		//		Actualtoastmessage = driver.findElementByXPath('//android.widget.Toast[1]').getText()
		//
		//		println(Actualtoastmessage)
		//
		//		Mobile.verifyMatch(Actualtoastmessage, findTestData('Mobile Input Data/VanLoad').getValue('Alerts', 1), false)
		//
		Mobile.takeScreenshot()

		println('Verified : that User is able to accept the van load')
	}
}
