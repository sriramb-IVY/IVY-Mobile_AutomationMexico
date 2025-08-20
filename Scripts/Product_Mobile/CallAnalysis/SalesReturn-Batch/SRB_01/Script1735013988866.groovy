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

import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import java.time.LocalDate as LocalDate
import org.testng.Assert as Assert
import com.kms.katalon.core.mobile.keyword.internal.MobileDriverFactory as MobileDriverFactory
import io.appium.java_client.AppiumDriver as AppiumDriver

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

Mobile.verifyElementText(findTestObject('Mobile/SalesReturn/ProductName-1st'), GlobalVariable.S_Sku_Name, FailureHandling.STOP_ON_FAILURE)

Mobile.tap(findTestObject('Mobile/SalesReturn/Piece-1st'), 0)

Mobile.delay(2)

Mobile.verifyElementExist(findTestObject('Mobile/SalesReturn/RETURN_Screen_Title'), 0)

Mobile.tap(findTestObject('Mobile/SalesReturn/Return-Select Reason'), 0)

Mobile.takeScreenshot()

Non_salable_Reason = Mobile.getText(findTestObject('Mobile/SalesReturn/Damage-option'), 0)

GlobalVariable.S_ReasonType = Non_salable_Reason

Mobile.tap(findTestObject('Mobile/SalesReturn/Damage-option'), 0)

Mobile.tap(findTestObject('Mobile/SalesReturn/Return-Piece'), 0, FailureHandling.STOP_ON_FAILURE)

GlobalVariable.keypadValue = findTestData('Mobile Input Data/SalesReturn_Replacement').getValue('Qty', 2)

Mobile.takeScreenshot()

GlobalVariable.PieceQty = GlobalVariable.keypadValue

Mobile.tap(findTestObject('Mobile/Common/Global_Number_keypad'), 0)

Mobile.tap(findTestObject('Mobile/Common/Btn_Done'), 0)

Mobile.delay(2)

Mobile.comment('Validation for batch screen not visible for other than salable reason')

Mobile.verifyElementNotExist(findTestObject('Mobile/Seller_2/Stock_order_Batch/Batch_Allocation_Screen_title'), 0)

Mobile.verifyElementNotVisible(findTestObject('Mobile/Seller_2/Stock_order_Batch/Batch_Allocation_Screen_title'), 0)

Mobile.takeScreenshot()

Mobile.tap(findTestObject('Mobile/SalesReturn/Piece-1st'), 0)

Mobile.delay(2)

GlobalVariable.label=Non_salable_Reason

Mobile.tap(findTestObject('Mobile/Seller_2/SalesReturn_Replacement/Return-Select Global Reason'), 0)

SalableReason = Mobile.getText(findTestObject('Mobile/SalesReturn/Salable-option'), 0)

GlobalVariable.S_ReasonType = SalableReason

Mobile.tap(findTestObject('Mobile/SalesReturn/Salable-option'), 0)

Mobile.tap(findTestObject('Mobile/Common/Btn_Done'), 0)

Mobile.delay(2)

Mobile.comment('Validation for batch screen not visible for other than salable reason')


Mobile.comment('Validation for batch screen visible or not')

Mobile.verifyElementExist(findTestObject('Mobile/Seller_2/Stock_order_Batch/Batch_Allocation_Screen_title'), 0, FailureHandling.STOP_ON_FAILURE)

Mobile.verifyElementVisible(findTestObject('Mobile/Seller_2/Stock_order_Batch/Batch_Allocation_Screen_title'), 0, FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

GlobalVariable.label = findTestData('Mobile Input Data/SO_Batch').getValue('SKU_Name', 1)

Mobile.verifyElementExist(findTestObject('Mobile/Seller_2/Stock_order_Batch/Product_Name_batch_screen'), 0, FailureHandling.STOP_ON_FAILURE)

Mobile.tap(findTestObject('Mobile/Seller_2/Stock_order_Batch/Product_Name_batch_screen'), 0)

Mobile.waitForElementPresent(findTestObject('Mobile/Seller_2/Stock_order_Batch/Product_Name'), 0)

Mobile.verifyElementExist(findTestObject('Mobile/Seller_2/Stock_order_Batch/Product_Name'), 0)

Mobile.comment('validation for batch names and fields')

'First Batch'
GlobalVariable.label = findTestData('Mobile Input Data/SO_Batch').getValue('Labels', 1)

Mobile.verifyElementExist(findTestObject('Mobile/Seller_2/Stock_order_Batch/Product_Batch_Name'), 0)

Batch_name_get = Mobile.getText(findTestObject('Mobile/Seller_2/Stock_order_Batch/Product_Batch_Name'), 0)

GlobalVariable.label1 = Batch_name_get

Mobile.callTestCase(findTestCase('Product_Mobile/SO_Batch/SOB_CT_01'), [:], FailureHandling.STOP_ON_FAILURE)

KeywordUtil.logInfo('first batch : ' + GlobalVariable.batch1)

GlobalVariable.batch1 = GlobalVariable.batch

Mobile.verifyElementExist(findTestObject('Mobile/Seller_2/Stock_order_Batch/Exp_date_get'), 0)

Mobile.verifyElementExist(findTestObject('Mobile/Seller_2/Stock_order_Batch/Mfg_date'), 0)

Mobile.verifyElementExist(findTestObject('Mobile/Seller_2/Stock_order_Batch/Case_field'), 0)

exp_date1 = Mobile.getText(findTestObject('Mobile/Seller_2/Stock_order_Batch/Exp_date_get'), 0)

String date = exp_date1

String[] part = date.split(':')

batch1_exp_date = (part[1]).trim()

KeywordUtil.logInfo('first batch exp date: ' + batch1_exp_date)

Mobile.takeScreenshot()

'Second Batch'
GlobalVariable.label = findTestData('Mobile Input Data/SO_Batch').getValue('Labels', 2)

Mobile.verifyElementExist(findTestObject('Mobile/Seller_2/Stock_order_Batch/Product_Batch_Name'), 0)

Batch_name2_get = Mobile.getText(findTestObject('Mobile/Seller_2/Stock_order_Batch/Product_Batch_Name'), 0)

GlobalVariable.label1 = Batch_name2_get

Mobile.callTestCase(findTestCase('Product_Mobile/SO_Batch/SOB_CT_01'), [:], FailureHandling.STOP_ON_FAILURE)

KeywordUtil.logInfo('Second batch : ' + GlobalVariable.batch2)

GlobalVariable.batch2 = GlobalVariable.batch

Mobile.verifyElementExist(findTestObject('Mobile/Seller_2/Stock_order_Batch/Exp_date_get'), 0)

Mobile.verifyElementExist(findTestObject('Mobile/Seller_2/Stock_order_Batch/Mfg_date'), 0)

Mobile.verifyElementExist(findTestObject('Mobile/Seller_2/Stock_order_Batch/Case_field'), 0)

exp_date2 = Mobile.getText(findTestObject('Mobile/Seller_2/Stock_order_Batch/Exp_date_get'), 0)

String date2 = exp_date2

String[] part1 = date2.split(':')

batch2_exp_date = (part1[1]).trim()

KeywordUtil.logInfo('Second batch exp date: ' + batch2_exp_date)

Mobile.takeScreenshot()

Mobile.comment('Validation for batches loading based on expiry date')

LocalDate Ex_date1 = LocalDate.parse(batch1_exp_date.replace('/', '-' // Convert to proper format
		))

LocalDate Ex_date2 = LocalDate.parse(batch2_exp_date.replace('/', '-'))

// Compare the dates
if (Ex_date1.isBefore(Ex_date2)) {
	KeywordUtil.logInfo('based on expired dates batches showing correctly')
} else {
	Assert.fail()
}

Mobile.takeScreenshot()

GlobalVariable.batch = GlobalVariable.batch1

println(GlobalVariable.batch)

Mobile.delay(2)

Mobile.comment('Validate able to done batch with less qty and more than order quantity ')

Order_piece = Mobile.getText(findTestObject('Mobile/Seller_2/Stock_order_Batch/Order_piece_qty'), 0)

Add_piece = (Integer.parseInt(Order_piece) + 2)

String pie = Add_piece

println(pie)

GlobalVariable.Qty = pie

String Qty = Integer.parseInt(GlobalVariable.Qty)

length = Qty.size()

Mobile.tap(findTestObject('Mobile/Seller_2/Stock_order_Batch/Piece_field'), 0)

Mobile.tap(findTestObject('Mobile/Seller_2/Stock_order_Batch/Piece_field'), 0)

if (1 == length) {
	GlobalVariable.keypadValue = Qty

	Mobile.tap(findTestObject('Object Repository/Mobile/Common/Global_Number_keypad'), 5)

	AppiumDriver driver = MobileDriverFactory.getDriver()

	Exceed_Alert = driver.findElementByXPath('//android.widget.Toast[1]').getText()

	KeywordUtil.logInfo(Exceed_Alert)
} else if (2 == length) {
	GlobalVariable.keypadValue = Qty.charAt(0)

	Mobile.tap(findTestObject('Object Repository/Mobile/Common/Global_Number_keypad'), 5)

	GlobalVariable.keypadValue = Qty.charAt(1)

	Mobile.tap(findTestObject('Object Repository/Mobile/Common/Global_Number_keypad'), 5)

	AppiumDriver driver = MobileDriverFactory.getDriver()

	Exceed_Alert = driver.findElementByXPath('//android.widget.Toast[1]').getText()

	KeywordUtil.logInfo(Exceed_Alert)
}

Mobile.takeScreenshot()

Mobile.tap(findTestObject('Mobile/Seller_2/Stock_order_Batch/Case_field'), 0)

Mobile.tap(findTestObject('Mobile/Common/Vertical keypad-BackSpace'), 0)

Mobile.tap(findTestObject('Mobile/Common/Vertical keypad-BackSpace'), 0)

Mobile.tap(findTestObject('Mobile/Seller_2/Stock_order_Batch/Piece_field'), 0)

Mobile.tap(findTestObject('Mobile/Common/Vertical keypad-BackSpace'), 0)

Mobile.tap(findTestObject('Mobile/Common/Vertical keypad-BackSpace'), 0)

Mobile.tap(findTestObject('Object Repository/Mobile/Common/Btn_Done'), 0)

AppiumDriver driver = MobileDriverFactory.getDriver()

Quantity_Alert = driver.findElementByXPath('//android.widget.Toast[1]').getText()

KeywordUtil.logInfo(Quantity_Alert)

Mobile.verifyMatch(Quantity_Alert, findTestData('Mobile Input Data/SO_Batch').getValue('Alerts', 1), false)

Mobile.takeScreenshot()




