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
import org.openqa.selenium.WebElement as WebElement
import com.kms.katalon.core.mobile.keyword.internal.MobileDriverFactory as MobileDriverFactory
import io.appium.java_client.AppiumDriver as AppiumDriver
//import io.appium.java_client.MobileElement as MobileElement
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.keyword.excel.ExcelKeywords as ExcelKeywords

Mobile.startApplication(GlobalVariable.APKFile, false)

Mobile.tap(findTestObject('Mobile/Common/Btn_Menu'), 10)

WebUI.callTestCase(findTestCase('Product_Mobile/Common/TradeCoverage(SelectRetailer)'), [('RetailerName') : findTestData('Mobile Input Data/Empty_Return').getValue('Retailer', 1)], FailureHandling.STOP_ON_FAILURE)

Mobile.tap(findTestObject('Mobile/Common/Icon_FunnelFilter'), 5)

Mobile.tap(findTestObject('Mobile/Common/Btn_Apply'), 5)

Mobile.tap(findTestObject('Mobile/Common/Icon_Search'), 4, FailureHandling.OPTIONAL)

GlobalVariable.ProductName = findTestData('Mobile Input Data/Empty_Return').getValue('Salable_SKU_Name', 1)

Mobile.setText(findTestObject('Mobile/Common/Field_Search_EnterText'), GlobalVariable.ProductName, 5)

Mobile.delay(2)

Mobile.tap(findTestObject('Mobile/OrderInvoice/ProductName-(Global)'), 5, FailureHandling.STOP_ON_FAILURE)

Mobile.verifyElementVisible(findTestObject('Mobile/OrderInvoice/Tab-Product Details'), 5)

Mobile.takeScreenshot()

GlobalVariable.Qty = findTestData('Mobile Input Data/Empty_Return').getValue('Buy_Qty', 5)

KeywordUtil.logInfo(GlobalVariable.Qty + 'Buy qty')

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Enter_PIECE_Qty'), [:], FailureHandling.STOP_ON_FAILURE)

Mobile.delay(2)

SKU1_TOTAL = Mobile.getText(findTestObject('Mobile/OrderInvoice/OrderInvoiceScrn-SkuTotal'), 0)

Mobile.takeScreenshot()

KeywordUtil.logInfo(SKU1_TOTAL + 'SKU 1 Order value ')

Mobile.tap(findTestObject('Mobile/Common/Icon_Back'), 0)

Total_SKU_value=Double.parseDouble(SKU1_TOTAL) 

KeywordUtil.logInfo(Total_SKU_value + 'Total Order value ')

//Mobile.tap(findTestObject('Mobile/Common/Icon_Back'), 0)

Mobile.tap(findTestObject('Mobile/Common/Btn_Next'), 0)

WebUI.callTestCase(findTestCase('Product_Mobile/Common/MustSell_Alert'), [:], FailureHandling.STOP_ON_FAILURE)

Mobile.delay(2)
Mobile.tap(findTestObject('Mobile/Common/Btn_Next'), 5)

Mobile.comment('Validation for Empty Return Screen')

Mobile.verifyElementExist(findTestObject('Object Repository/Mobile/Seller_2/Stock and Order/Empty_Product_Details_Title'), 2, FailureHandling.STOP_ON_FAILURE)

GlobalVariable.label = findTestData('Mobile Input Data/Empty_Return').getValue('SKU_Name', 1)

Mobile.verifyElementExist(findTestObject('Object Repository/Mobile/Seller_2/Stock and Order/Global_Empty_Product_Name'), 2, FailureHandling.STOP_ON_FAILURE)

Liable_Qty1=Mobile.getText(findTestObject('Object Repository/Mobile/Seller_2/Stock and Order/Empty_Liable_qty_get'), 0)

SKU1_price=Mobile.getText(findTestObject('Object Repository/Mobile/Seller_2/Stock and Order/Empty_price_get'), 0, FailureHandling.STOP_ON_FAILURE)

Mobile.tap(findTestObject('Object Repository/Mobile/Seller_2/Stock and Order/Empty_return_qty_enter_field'), 1, FailureHandling.STOP_ON_FAILURE)

Mobile.setText(findTestObject('Object Repository/Mobile/Seller_2/Stock and Order/Empty_return_qty_enter_field'), Liable_Qty1, 0, FailureHandling.STOP_ON_FAILURE)

Total_Empty_value=Mobile.getText(findTestObject('Object Repository/Mobile/Seller_2/Stock and Order/Total_Empty_Return_Value'), 0, FailureHandling.STOP_ON_FAILURE)

Mobile.verifyEqual( Double.parseDouble(Total_Empty_value), '0', FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

//Next screen

Mobile.tap(findTestObject('Mobile/Common/Btn_Done'), 2, FailureHandling.STOP_ON_FAILURE)

if(Mobile.verifyElementNotExist(findTestObject('Mobile/SummaryScreen/SummaryScreen-InfoIcon'), 0, FailureHandling.OPTIONAL)) {
	
	Mobile.tap(findTestObject('Mobile/Common/Btn_Next'), 5)
	Mobile.delay(2)
}

Mobile.delay(2)

Mobile.verifyElementVisible(findTestObject('Mobile/SummaryScreen/SummaryScreen-InfoIcon'),
	5)

Mobile.comment('Validation for when user return the qty(Full Return qty)')

GlobalVariable.label = findTestData('Mobile Input Data/Empty_Return').getValue('SKU_Name', 1)

Returned_qty1 = Mobile.getText(findTestObject('Object Repository/Mobile/Seller_2/Stock and Order/Empty_return_qty_get_summary_screen'),
	0)

Mobile.verifyMatch(Returned_qty1, Liable_Qty1, false, FailureHandling.STOP_ON_FAILURE)

ReturnSKU1_Summary_scrn_amt = Mobile.getText(findTestObject('Object Repository/Mobile/Seller_2/Stock and Order/Empty_return_amt_summary_screen'),
	0)

Mobile.verifyEqual(Double.parseDouble(ReturnSKU1_Summary_scrn_amt), '0', FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

Mobile.comment('Validation for Empty return amount in info screen')

Mobile.tap(findTestObject('Mobile/SummaryScreen/SummaryScreen-InfoIcon'), 0)

OrderAmt = Mobile.getText(findTestObject('Mobile/SummaryScreen/SplitScreen-OrderAmt'), 0)

Mobile.verifyEqual(Double.parseDouble(OrderAmt), Total_SKU_value, FailureHandling.STOP_ON_FAILURE)

Empty_Amount = Mobile.getText(findTestObject('Object Repository/Mobile/Seller_2/Stock and Order/Empty_Return_value_get_info_screen'),
	0)

Mobile.verifyEqual(Double.parseDouble(Empty_Amount), '0', FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

Mobile.tap(findTestObject('Mobile/SummaryScreen/SummaryScreen-info_closeBtn'), 0)

Mobile.waitForElementPresent(findTestObject('Mobile/SummaryScreen/SummaryScreen-Order btn'), 0)

Mobile.tap(findTestObject('Mobile/SummaryScreen/SummaryScreen-Order btn'), 0)

Mobile.callTestCase(findTestCase('Product_Mobile/Common/DatePicker_and _Navigation/Mobile Date Picker - Current Date'), [:], FailureHandling.STOP_ON_FAILURE)

Mobile.tap(findTestObject('Mobile/Common/Btn_Confirm'), 0)

Mobile.delay(2, FailureHandling.STOP_ON_FAILURE)

id = Mobile.getText(findTestObject('Mobile/SummaryScreen/Click Order-OrderSavedID-Title'), 0)

Ord_ID = id.replaceAll('[Order Saved. Order ID is:\']', '')

invoice_ID = Ord_ID.replaceAll('[\']', '')

KeywordUtil.logInfo(invoice_ID)

exlpath = 'DDF\\Mobile Input data\\Mobile Input data.xlsx'

Sheetname = 'EmptyReturn_Validation'

workbook01 = ExcelKeywords.getWorkbook(exlpath)

sheet1 = ExcelKeywords.getExcelSheet(workbook01, Sheetname)

ExcelKeywords.setValueToCellByIndex(sheet1, 3, 6, Total_Empty_value)

ExcelKeywords.setValueToCellByIndex(sheet1, 3, 8, invoice_ID)

ExcelKeywords.setValueToCellByIndex(sheet1, 3, 5, Liable_Qty1)

ExcelKeywords.setValueToCellByIndex(sheet1, 3, 13, SKU1_price)

ExcelKeywords.saveWorkbook(exlpath, workbook01)


Mobile.tap(findTestObject('Mobile/Common/Btn_OK'), 0)

Mobile.delay(2)

Mobile.swipe(0, 150, 0, 400, FailureHandling.OPTIONAL)

Mobile.delay(2)

Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/Menu-OrderInvoice'), 5, FailureHandling.STOP_ON_FAILURE)

if (Mobile.verifyElementVisible(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/OrderPopUp/OrderPopUp_Title'), 15, FailureHandling.OPTIONAL)) {
	AppiumDriver driver = MobileDriverFactory.getDriver()

	List<Integer> elements = driver.findElementsByClassName('android.widget.ImageView')

	def size = elements.size()

	println('The size of elements is ::' + elements.size())

	for (int i = 0; i < (size - 1); i++) {
		Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/OrderPopUp/Edit_Order 1'), 0)

		Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/SummaryScreen/SummaryScreen-Invoice btn'), 0)

		Mobile.delay(1)

		if (Mobile.verifyElementExist(findTestObject('Object Repository/Mobile/SummaryScreen/Field_EnterCollectionAmount'), 2, FailureHandling.OPTIONAL)) {
			Mobile.tap(findTestObject('Object Repository/Mobile/SummaryScreen/Field_EnterCollectionAmount'), 0)

			Mobile.delay(1)

			Mobile.tap(findTestObject('Mobile/Common/Btn_Done'), 0)
		}
		
		Mobile.callTestCase(findTestCase('Product_Mobile/Common/DatePicker_and _Navigation/Mobile Date Picker - Current Date'), [:], FailureHandling.STOP_ON_FAILURE)

		Mobile.tap(findTestObject('Mobile/Common/Btn_Confirm'), 0)

		Mobile.delay(2)

		if (Mobile.verifyElementExist(findTestObject('Mobile/Common/Icon_Back'), 2, FailureHandling.OPTIONAL)) {
			Mobile.tap(findTestObject('Mobile/Common/Icon_Back'), 3, FailureHandling.OPTIONAL)

			Mobile.delay(1)

			Mobile.tap(findTestObject('Mobile/Common/Icon_Back'), 3, FailureHandling.OPTIONAL)

			Mobile.delay(1)
		} else if (Mobile.verifyElementExist(findTestObject('Mobile/Common/Btn_OK'), 2, FailureHandling.OPTIONAL)) {
			Mobile.tap(findTestObject('Mobile/Common/Btn_OK'), 0, FailureHandling.OPTIONAL)
		}
		
		Mobile.delay(2)
	}
}

Mobile.callTestCase(findTestCase('Test Cases/Product_Mobile/Common/Call_Analysis'), [:], FailureHandling.STOP_ON_FAILURE)



