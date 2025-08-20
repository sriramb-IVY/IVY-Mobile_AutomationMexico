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
import com.kms.katalon.core.mobile.keyword.internal.MobileDriverFactory as MobileDriverFactory
import io.appium.java_client.AppiumDriver as AppiumDriver
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil


WebUI.callTestCase(findTestCase('Product_Mobile/Common/Generate_Order'), [:], FailureHandling.STOP_ON_FAILURE)

//WebUI.callTestCase(findTestCase('Product_Mobile/Common/Store_Menu_Selection'), [('MenuName') : findTestData('Mobile Input Data/Common').getValue(
//	'Menu', 4), ('SubMenuName') : findTestData('Mobile Input Data/Common').getValue('Menu', 1)], FailureHandling.STOP_ON_FAILURE)

MenuName = findTestData('Mobile Input Data/Common').getValue('Menu', 4)

if (Mobile.verifyElementExist(findTestObject('Mobile/Common/Global_StoreMenuName_Selection', [('Name') : MenuName]),
	1, FailureHandling.OPTIONAL)) {

	Mobile.tap(findTestObject('Mobile/Common/Global_StoreMenuName_Selection', [('Name') : MenuName]), 0)
	
	Mobile.delay(3)
	
	Mobile.callTestCase(findTestCase('Product_Mobile/Common/Clear_Star_Filter_Default_Selection'), [:], FailureHandling.STOP_ON_FAILURE)
}

if (Mobile.verifyElementVisible(findTestObject('Mobile/Common/YOU_WANT_TO_PROCEED.,msg'), 10, FailureHandling.OPTIONAL)) {
	
	Mobile.tap(findTestObject('Mobile/Common/YOU_WANT_TO_PROCEED.,msg'), 10)
	
	Mobile.delay(3)
	
}

if (Mobile.verifyElementVisible(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/OrderPopUp/OrderPopUp_Title'), 10, FailureHandling.OPTIONAL)) {
	
	AppiumDriver driver = MobileDriverFactory.getDriver()

	List<String> elements = driver.findElementsByClassName('android.widget.ImageView')

	def size = elements.size()

	 KeywordUtil.logInfo('The size of elements is ::' + elements.size())

	for (int i = 0; i < (size - 1); i++) {
		
		Mobile.tap(findTestObject('Mobile/SummaryScreen/OrderPopUp/Edit_Order 1'), 0)
		
		'Order to Invoice Generation'

		WebUI.callTestCase(findTestCase('Product_Mobile/Common/Generate_Invoice'), [('MenuName') : findTestData('Mobile Input Data/Common').getValue('Menu', 4)], FailureHandling.STOP_ON_FAILURE)

	}
}
