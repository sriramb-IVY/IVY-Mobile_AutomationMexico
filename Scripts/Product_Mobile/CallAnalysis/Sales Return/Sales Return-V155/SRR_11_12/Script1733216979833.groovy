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
import org.testng.Assert as Assert
import io.appium.java_client.android.nativekey.KeyEvent as KeyEvent
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import com.kms.katalon.keyword.excel.ExcelKeywords as ExcelKeywords

//Mobile.startApplication(GlobalVariable.APKFile, false)
//
//Mobile.tap(findTestObject('Mobile/Common/Btn_Menu'), 0)

if (Mobile.verifyElementExist(findTestObject('Mobile/Store_Actvy/Menu-SalesReturn'), 2, FailureHandling.OPTIONAL)) {
	'SalesReturn menu visible'
} else {
	Mobile.callTestCase(findTestCase('Product_Mobile/Common/Mobile_Login/Relaunch the app'), [:], FailureHandling.CONTINUE_ON_FAILURE)

	Mobile.tap(findTestObject('Mobile/Common/Btn_Menu'), 0)

	GlobalVariable.RetailerName = findTestData('Mobile Input Data/CallAnalysis').getValue('Retailer_Name', 1)

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

GlobalVariable.S_Sku_Name = findTestData('Mobile Input Data/SalesReturn_Replacement').getValue('Return_SKU_Name', 4)

Mobile.setText(findTestObject('Mobile/Common/Field_Search_EnterText'), GlobalVariable.S_Sku_Name, 0)

Mobile.takeScreenshot()

Mobile.verifyElementVisible(findTestObject('Mobile/SalesReturn/ProductName-1st'), 3, FailureHandling.STOP_ON_FAILURE)

Mobile.verifyElementText(findTestObject('Mobile/SalesReturn/ProductName-1st'), GlobalVariable.S_Sku_Name, FailureHandling.STOP_ON_FAILURE)

KeywordUtil.logInfo('The list of the products  displayed in the Sales return screen')

Mobile.takeScreenshot()

Mobile.tap(findTestObject('Mobile/SalesReturn/Piece-1st'), 0)

Mobile.delay(2)

Mobile.verifyElementExist(findTestObject('Mobile/SalesReturn/RETURN_Screen_Title'), 0)

Mobile.setText(findTestObject('Mobile/SalesReturn/Return-InvoiceNo'), findTestData('Mobile Input Data/CallAnalysis').getValue('InvoiceNo', 1), 0, FailureHandling.OPTIONAL)

Mobile.takeScreenshot()

MRP = Mobile.getText(findTestObject('Mobile/SalesReturn/Return-MRP'), 0)

Mobile.tap(findTestObject('Mobile/SalesReturn/Return-Select Reason'), 0)

Mobile.takeScreenshot()

Reason = Mobile.getText(findTestObject('Mobile/SalesReturn/Damage-option'), 0)

GlobalVariable.S_ReasonType = Reason

Mobile.tap(findTestObject('Mobile/SalesReturn/Damage-option'), 0)

Mobile.tap(findTestObject('Mobile/SalesReturn/Return-Piece'), 0, FailureHandling.STOP_ON_FAILURE)

GlobalVariable.keypadValue = findTestData('Mobile Input Data/SalesReturn_Replacement').getValue('Qty', 2)

Mobile.takeScreenshot()

GlobalVariable.PieceQty = GlobalVariable.keypadValue

Mobile.tap(findTestObject('Mobile/Common/Global_Number_keypad'), 0)

Mobile.tap(findTestObject('Mobile/Common/Btn_Done'), 0)

Mobile.delay(2)

Total_Return_amount_Returnscrn = Mobile.getText(findTestObject('Mobile/Seller_2/SalesReturn_Replacement/Return_product_amount'), 0)

KeywordUtil.logInfo(Total_Return_amount_Returnscrn + ': Total return amount')


Mobile.tap(findTestObject('Mobile/SalesReturn/Piece-1st'), 0)

Mobile.delay(2)

Mobile.verifyElementExist(findTestObject('Object Repository/Mobile/Seller_2/SalesReturn_Replacement/Replace_title_tab'), 0)

Mobile.takeScreenshot()

Mobile.tap(findTestObject('Object Repository/Mobile/Seller_2/SalesReturn_Replacement/Replace_title_tab'), 0)

Mobile.waitForElementPresent(findTestObject('Mobile/Common/Field_Search_EnterText'), 5)

Mobile.delay(2)

X_val = Mobile.getAttribute(findTestObject('Mobile/Common/Field_Search_EnterText'), 'x', 0)

Y_val = Mobile.getAttribute(findTestObject('Mobile/Common/Field_Search_EnterText'), 'y', 0)

x = (Integer.parseInt(X_val) + 100)

y = (Integer.parseInt(Y_val) + 135)

Mobile.tap(findTestObject('Mobile/Common/Field_Search_EnterText'), 0)

Replacement_sku1 = findTestData('Mobile Input Data/SalesReturn_Replacement').getValue('Replacement_SKU_Name', 3)

Mobile.setText(findTestObject('Mobile/Common/Field_Search_EnterText'), Replacement_sku1, 0)

Mobile.delay(2)

Mobile.tapAtPosition(x, y)

Mobile.tapAtPosition(x, y)

rep_qty = (Double.parseDouble(Total_Return_amount_Returnscrn) + 300)

GlobalVariable.label=findTestData('Mobile Input Data/SalesReturn_Replacement').getValue('Replacement_SKU_Name', 3)

Price = Mobile.getText(findTestObject('Object Repository/Mobile/Seller_2/SalesReturn_Replacement/Replacement_Price_Get'), 0)

Sku_price = (Price.split(': ')[1])

replace_qty = (rep_qty / Double.parseDouble(Sku_price)).round()

KeywordUtil.logInfo(replace_qty + ' : qty ')

String Qty=replace_qty



Mobile.tap(findTestObject('Mobile/Seller_2/SalesReturn_Replacement/Replacement_Piece_field'), 0)

Mobile.setText(findTestObject('Mobile/Seller_2/SalesReturn_Replacement/Replacement_Piece_field'), Qty, 0)

Replace_value = Mobile.getText(findTestObject('Object Repository/Mobile/Seller_2/SalesReturn_Replacement/Replacement_Value_Get'), 0)

Replacement_value = (Replace_value.split(': ')[1])

Mobile.tap(findTestObject('Mobile/Common/Btn_Done'), 0)

Mobile.delay(2)

Mobile.tap(findTestObject('Mobile/SalesReturn/SR-Next btn'), 0)

Mobile.delay(2)


if (Double.parseDouble(Replacement_value) > Double.parseDouble(Total_Return_amount_Returnscrn)) {
	
	KeywordUtil.logInfo('Replacement amount is greater than return amount so edit alert should be displayed')
} else {
	KeywordUtil.logInfo('Replacement amount is leaser than return amount')

	Assert.fail()
}

Mobile.tap(findTestObject('Mobile/SalesReturn/SR-Save btn'), 0)

Mobile.comment('Replacement amount edit alert should display')

Mobile.verifyElementExist(findTestObject('Object Repository/Mobile/Seller_2/SalesReturn_Replacement/Summary_Screen/Alert_Get'), 3, FailureHandling.STOP_ON_FAILURE)


Rep_edit_alert = Mobile.getText(findTestObject('Object Repository/Mobile/Seller_2/SalesReturn_Replacement/Summary_Screen/Alert_Get'), 0)

Mobile.verifyMatch(Rep_edit_alert, findTestData('Mobile Input Data/SalesReturn_Replacement').getValue('Alerts', 1), false, FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

Mobile.tap(findTestObject('Mobile/Common/Btn_OK'), 3, FailureHandling.OPTIONAL)

Mobile.verifyElementNotExist(findTestObject('Mobile/SalesReturn/SR-Saved Successfully'), 3, FailureHandling.OPTIONAL)

Mobile.takeScreenshot()


Mobile.tap(findTestObject('Object Repository/Mobile/Common/Icon_Back'), 0)


Mobile.waitForElementPresent(findTestObject('Mobile/SalesReturn/Piece-1st'), 5)


ret_qty=Mobile.getText(findTestObject('Mobile/SalesReturn/Piece-1st'), 4, FailureHandling.STOP_ON_FAILURE)

Mobile.tap(findTestObject('Mobile/SalesReturn/Piece-1st'), 0)

Mobile.delay(2)

Mobile.tap(findTestObject('Object Repository/Mobile/Seller_2/SalesReturn_Replacement/Replace_title_tab'), 0)

GlobalVariable.label=findTestData('Mobile Input Data/SalesReturn_Replacement').getValue('Replacement_SKU_Name', 3)
Mobile.waitForElementPresent(findTestObject('Mobile/Seller_2/SalesReturn_Replacement/Replacement_Piece_field'), 5)

Mobile.delay(2)


Mobile.tap(findTestObject('Mobile/Seller_2/SalesReturn_Replacement/Replacement_Piece_field'), 0)

Mobile.tap(findTestObject('Object Repository/Mobile/Common/Vertical keypad-BackSpace'), 0)

Mobile.tap(findTestObject('Object Repository/Mobile/Common/Vertical keypad-BackSpace'), 0)

Mobile.setText(findTestObject('Mobile/Seller_2/SalesReturn_Replacement/Replacement_Piece_field'), ret_qty, 0)

Mobile.tap(findTestObject('Mobile/Common/Btn_Done'), 0)

Mobile.delay(2)

Mobile.tap(findTestObject('Mobile/SalesReturn/SR-Next btn'), 0)

Mobile.delay(2)

Summary_Total_replace_value = Mobile.getText(findTestObject('Mobile/Seller_2/SalesReturn_Replacement/Summary_Screen/Total_Replacement_value_get_sum_scrn'), 0)

Summary_Total_return_value = Mobile.getText(findTestObject('Mobile/SalesReturn/SR-TotalValue - 2'), 0)

Mobile.comment('Validation for different product with same price ')

Mobile.verifyEqual(Double.parseDouble(Summary_Total_return_value), Double.parseDouble(Summary_Total_replace_value), FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

Mobile.tap(findTestObject('Mobile/SalesReturn/SR-Save btn'), 0)

Mobile.comment('No alert should display because both return and replacement amount is same')

Mobile.verifyElementExist(findTestObject('Mobile/SalesReturn/SR-Saved Successfully'), 3, FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()
Mobile.tap(findTestObject('Mobile/Common/Btn_OK'), 0)

Mobile.swipe(0, 150, 0, 400, FailureHandling.OPTIONAL)

