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
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.mobile.keyword.internal.MobileDriverFactory as MobileDriverFactory
import io.appium.java_client.AppiumDriver as AppiumDriver

import org.openqa.selenium.WebElement as WebElement

if (Mobile.verifyElementVisible(findTestObject('Mobile/SummaryScreen/OrderPopUp/OrderPopUp_Title'), 1, FailureHandling.OPTIONAL)) {

	AppiumDriver driver = MobileDriverFactory.getDriver()

	List<String> elements = driver.findElementsByClassName('android.widget.ImageView')

	def size = elements.size()

	println('The size of elements is ::' + elements.size())

	for (int i = 0; i < (size - 1); i++) {

		if (Mobile.verifyElementVisible(findTestObject('Mobile/Common/YOU_WANT_TO_PROCEED.,msg'), 10, FailureHandling.OPTIONAL)) {

			Mobile.tap(findTestObject('Mobile/Common/YOU_WANT_TO_PROCEED.,msg'), 10)

			Mobile.delay(3)

		}

		Mobile.tap(findTestObject('Mobile/SummaryScreen/OrderPopUp/Edit_Order 1'), 0)

		'Order to Invoice Generation'

		Mobile.tap(findTestObject('Mobile/SummaryScreen/SummaryScreen-Invoice btn'), 0)

		WebUI.callTestCase(findTestCase('Product_Mobile/Common/Enter_Collection_Amt_In_SummaryScreen'), [:], FailureHandling.STOP_ON_FAILURE)

		WebUI.callTestCase(findTestCase('Product_Mobile/Common/DatePicker_and _Navigation/Mobile Date Picker - Current Date'),
				[('DateCount') : 0], FailureHandling.STOP_ON_FAILURE)

		Mobile.tap(findTestObject('Mobile/Common/Btn_Confirm'), 0)

		Mobile.waitForElementPresent(findTestObject('Mobile/Common/Btn_OK'), 0)

		Mobile.tap(findTestObject('Mobile/Common/Btn_OK'), 0)

		Mobile.swipe(50, 50, 50, 600, FailureHandling.OPTIONAL)

		Mobile.delay(2)

		if (Mobile.verifyElementNotExist(findTestObject('Mobile/Store_Actvy/Menu-OrderInvoice'), 1,
		FailureHandling.OPTIONAL)) {
	
			Mobile.tap(findTestObject('Object Repository/Mobile/Store_Actvy/StoreActivity-RightTopIcon'), 0)

			Mobile.delay(2)

			Mobile.tap(findTestObject('Mobile/Common/Icon_Back'), 0)

			Mobile.tap(findTestObject('Mobile/Common/Btn_OK'), 0, FailureHandling.OPTIONAL)

			Mobile.delay(2)

			Mobile.swipe(50, 50, 50, 600)

			Mobile.delay(2)
		}

		Mobile.tap(findTestObject('Mobile/Store_Actvy/Menu-OrderInvoice'), 0, FailureHandling.STOP_ON_FAILURE)

		if (Mobile.verifyElementVisible(findTestObject('Mobile/Common/YOU_WANT_TO_PROCEED.,msg'), 10, FailureHandling.OPTIONAL)) {

			Mobile.tap(findTestObject('Mobile/Common/YOU_WANT_TO_PROCEED.,msg'), 10)

			Mobile.delay(3)
		}

		Mobile.delay(2)

		Mobile.callTestCase(findTestCase('Product_Mobile/Common/Clear_Star_Filter_Default_Selection'), [:], FailureHandling.STOP_ON_FAILURE)
		
		Mobile.delay(2)


		//WebUI.callTestCase(findTestCase('Product_Mobile/Common/Generate_Invoice'), [('MenuName') : findTestData('Mobile Input Data/Common').getValue('Menu', 4)], FailureHandling.STOP_ON_FAILURE)

	}
}

