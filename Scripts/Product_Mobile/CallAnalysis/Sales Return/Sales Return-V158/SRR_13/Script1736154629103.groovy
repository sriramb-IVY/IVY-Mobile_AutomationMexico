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
import org.testng.Assert;
import io.appium.java_client.android.nativekey.KeyEvent as KeyEvent
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.keyword.excel.ExcelKeywords as ExcelKeywords
import internal.GlobalVariable as GlobalVariable

//Mobile.startApplication(GlobalVariable.APKFile, false)
//
//Mobile.tap(findTestObject('Mobile/Common/Btn_Menu'), 0)

if (Mobile.verifyElementExist(findTestObject('Mobile/Store_Actvy/Menu-SalesReturn'), 2, FailureHandling.OPTIONAL)) {
	'SalesReturn menu visible'
} else {
	Mobile.callTestCase(findTestCase('Product_Mobile/Common/Mobile_Login/Relaunch the app'), [:], FailureHandling.CONTINUE_ON_FAILURE)

	Mobile.tap(findTestObject('Mobile/Common/Btn_Menu'), 0)

	GlobalVariable.RetailerName = findTestData('Mobile Input Data/SalesReturn_Replacement').getValue('Retailer', 1)
	


	Mobile.tap(findTestObject('Mobile/MainMenu/Menu_Trade Coverage'), 0)

	Mobile.tap(findTestObject('Mobile/Common/Icon_Search'), 5)

	Mobile.setText(findTestObject('Mobile/Common/Field_Search_EnterText'), GlobalVariable.RetailerName, 5)

	Mobile.tap(findTestObject('Mobile/TradeCoverage/First_Retailer_Selection_From_List'), 5)

	Mobile.tap(findTestObject('Mobile/Common/Btn_StartVisit'), 5)

	if (Mobile.verifyElementVisible(findTestObject('Object Repository/Mobile/Common/Popup_Title_LocationValidation'), 5, FailureHandling.OPTIONAL)) {
		Mobile.tap(findTestObject('Mobile/Common/Btn_YES'), 5)
	}
	
	Mobile.delay(6)

	Mobile.swipe(0, 150, 0, 400, FailureHandling.OPTIONAL)
}


Mobile.tap(findTestObject('Mobile/Store_Actvy/Menu-SalesReturn'), 0)

Mobile.delay(2)

Mobile.verifyElementVisible(findTestObject('Mobile/SalesReturn/Label-Product Name'), 0)

Mobile.verifyElementVisible(findTestObject('Mobile/SalesReturn/Label-Cases'), 0)

Mobile.tap(findTestObject('Mobile/Common/Icon_Search'), 0)

GlobalVariable.S_Sku_Name = findTestData('Mobile Input Data/SalesReturn_Replacement').getValue('Return_SKU_Name', 1)

Mobile.setText(findTestObject('Mobile/Common/Field_Search_EnterText'), GlobalVariable.S_Sku_Name, 0)


Mobile.verifyElementVisible(findTestObject('Mobile/SalesReturn/ProductName-1st'), 3, FailureHandling.STOP_ON_FAILURE)

Mobile.tap(findTestObject('Mobile/SalesReturn/Piece-1st'), 0)

Mobile.delay(2)

Mobile.verifyElementExist(findTestObject('Mobile/SalesReturn/RETURN_Screen_Title'), 0)

Mobile.setText(findTestObject('Mobile/SalesReturn/Return-InvoiceNo'), findTestData('Mobile Input Data/CallAnalysis').getValue('InvoiceNo', 1), 0, FailureHandling.OPTIONAL)


Mobile.tap(findTestObject('Mobile/SalesReturn/Return-Select Reason'), 0)

Mobile.takeScreenshot()

Reason = Mobile.getText(findTestObject('Mobile/SalesReturn/Damage-option'), 0)

GlobalVariable.S_ReasonType = Reason

Mobile.tap(findTestObject('Mobile/SalesReturn/Damage-option'), 0)

Mobile.tap(findTestObject('Mobile/SalesReturn/Return-Piece'), 0, FailureHandling.STOP_ON_FAILURE)

GlobalVariable.keypadValue = findTestData('Mobile Input Data/SalesReturn_Replacement').getValue('Qty', 1)

GlobalVariable.PieceQty = GlobalVariable.keypadValue

Mobile.tap(findTestObject('Mobile/Common/Global_Number_keypad'), 0)

Mobile.tap(findTestObject('Mobile/SalesReturn/Add another Reason Quantity'), 0)

		Mobile.verifyElementVisible(findTestObject('Mobile/SalesReturn/Return-MRP_2'), 0, FailureHandling.STOP_ON_FAILURE)

		Mobile.verifyElementVisible(findTestObject('Mobile/SalesReturn/Return-Select Reason'), 0, FailureHandling.STOP_ON_FAILURE)

		Mobile.verifyElementVisible(findTestObject('Mobile/SalesReturn/Return-Piece_2'), 0, FailureHandling.STOP_ON_FAILURE)

		Mobile.takeScreenshot()

Mobile.tap(findTestObject('Mobile/Seller_2/SalesReturn_Replacement/Replace_title_tab'), 0)

Mobile.verifyElementExist(findTestObject('Mobile/Common/Field_Search_EnterText'), 0)

Mobile.takeScreenshot()

X_val = Mobile.getAttribute(findTestObject('Mobile/Common/Field_Search_EnterText'), 'x', 0)

Y_val = Mobile.getAttribute(findTestObject('Mobile/Common/Field_Search_EnterText'), 'y', 0)

x = (Integer.parseInt(X_val) + 4)

y = (Integer.parseInt(Y_val) + 95)

Mobile.tap(findTestObject('Mobile/Common/Field_Search_EnterText'), 0)

Replacement_sku1 = findTestData('Mobile Input Data/SalesReturn_Replacement').getValue('Replacement_SKU_Name', 1)

Mobile.setText(findTestObject('Mobile/Common/Field_Search_EnterText'), Replacement_sku1, 0)

Mobile.delay(2)

Mobile.tapAtPosition(x, y)
Mobile.tapAtPosition(x, y)

Mobile.comment('Validation for first replacement product and qty')

Replace_product_name = Mobile.getText(findTestObject('Object Repository/Mobile/Seller_2/SalesReturn_Replacement/Replacement_Product_Name_get'), 0)

if (Replace_product_name.contains(Replacement_sku1)) {
	KeywordUtil.logInfo('searched and selected product loading in replacement screen ')

	Mobile.takeScreenshot()
} else {
	KeywordUtil.logInfo('search product selected wrongly or searched product not enabled')
	Assert.fail()
}
GlobalVariable.label = Replacement_sku1

Mobile.tap(findTestObject('Mobile/Seller_2/SalesReturn_Replacement/Replacement_Piece_field'), 0)

Mobile.setText(findTestObject('Mobile/Seller_2/SalesReturn_Replacement/Replacement_Piece_field'), findTestData('Mobile Input Data/SalesReturn_Replacement').getValue('Qty', 1), 0)

Mobile.takeScreenshot('for replacement first product qty added')

Mobile.tap(findTestObject('Object Repository/Mobile/SalesReturn/RETURN_Screen_Title'), 5)

Mobile.comment('User should  able to Add another reason and quantity for the same product in product return screen')

Mobile.delay(2)

		Mobile.tap(findTestObject('Mobile/SalesReturn/Return-Select Reason_2'), 0)

		Reason2 = Mobile.getText(findTestObject('Mobile/SalesReturn/Salable-option'), 0)

		GlobalVariable.S_ReasonType = Reason2

		Mobile.tap(findTestObject('Mobile/SalesReturn/Salable-option'), 0)

		Mobile.tap(findTestObject('Mobile/SalesReturn/Return-Piece_2'), 0, FailureHandling.STOP_ON_FAILURE)

		GlobalVariable.keypadValue = findTestData('Mobile Input Data/SalesReturn_Replacement').getValue('Qty', 2)

		GlobalVariable.PieceQty = GlobalVariable.keypadValue

		Mobile.tap(findTestObject('Mobile/Common/Global_Number_keypad'), 0)

		Mobile.takeScreenshot()
		
		Mobile.tap(findTestObject('Mobile/Seller_2/SalesReturn_Replacement/Replace_title_tab'), 0)
		
		Mobile.verifyElementExist(findTestObject('Mobile/Common/Field_Search_EnterText'), 0)
		

'SKU 2'
Replacement_sku2 = findTestData('Mobile Input Data/SalesReturn_Replacement').getValue('Replacement_SKU_Name', 2)

Mobile.setText(findTestObject('Mobile/Common/Field_Search_EnterText'), Replacement_sku2, 0)

Mobile.delay(2)

Mobile.tapAtPosition(x, y)

Mobile.tapAtPosition(x, y)

Mobile.comment('Validation for Second replacement product and qty')

Replace_product_name1 = Mobile.getText(findTestObject('Object Repository/Mobile/Seller_2/SalesReturn_Replacement/Replacement_Product_Name_get'), 0)

if (Replace_product_name1.contains(Replacement_sku2)) {
	KeywordUtil.logInfo('searched and selected product loading in replacement screen ')

	Mobile.takeScreenshot()
} else {
	KeywordUtil.logInfo('search product selected wrongly or searched product not enabled')
	Assert.fail()
}
GlobalVariable.label = Replacement_sku2

Mobile.tap(findTestObject('Mobile/Seller_2/SalesReturn_Replacement/Replacement_Piece_field'), 0)

Mobile.setText(findTestObject('Mobile/Seller_2/SalesReturn_Replacement/Replacement_Piece_field'), findTestData('Mobile Input Data/SalesReturn_Replacement').getValue('Qty', 2), 0)

Mobile.takeScreenshot('for replacement Second product qty added')

Mobile.tap(findTestObject('Object Repository/Mobile/SalesReturn/RETURN_Screen_Title'), 5)

Mobile.verifyElementExist(findTestObject('Mobile/SalesReturn/Return-Piece_2'), 0, FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot('Validation before clear second reason for return sku')

Mobile.tap(findTestObject('Mobile/Seller_2/SalesReturn_Replacement/Replace_title_tab'), 0)

Mobile.waitForElementPresent(findTestObject('Mobile/Common/Icon_X'), 0)

Mobile.tap(findTestObject('Mobile/Common/Icon_X'), 5)

Mobile.verifyElementNotExist(findTestObject('Mobile/SalesReturn/Return-Piece_2'), 0, FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot('Added replacement product  is removed')

Mobile.tap(findTestObject('Object Repository/Mobile/SalesReturn/RETURN_Screen_Title'), 5)

Mobile.verifyElementExist(findTestObject('Mobile/SalesReturn/Return-Piece_2'), 0, FailureHandling.STOP_ON_FAILURE)



