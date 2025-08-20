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

Mobile.startApplication(GlobalVariable.APKFile, false)

Mobile.tap(findTestObject('Mobile/Common/Btn_Menu'), 10)

GlobalVariable.RetailerName = findTestData('Mobile Input Data/Empty_Return').getValue('Retailer', 1)

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

Mobile.tap(findTestObject('Mobile/Store_Actvy/Menu-SalesReturn'), 0)

Mobile.delay(2)

//SEARCH Product
Mobile.tap(findTestObject('Mobile/Common/Icon_Search'), 0)

GlobalVariable.S_Sku_Name = findTestData('Mobile Input Data/Empty_Return').getValue('Salable_SKU_Name', 1)

Mobile.setText(findTestObject('Mobile/Common/Field_Search_EnterText'), GlobalVariable.S_Sku_Name, 0)

Mobile.takeScreenshot()

Mobile.verifyElementVisible(findTestObject('Mobile/SalesReturn/ProductName-1st'), 3, FailureHandling.STOP_ON_FAILURE)

Mobile.tap(findTestObject('Mobile/SalesReturn/Piece-1st'), 0)

Mobile.delay(2)

Mobile.verifyElementExist(findTestObject('Mobile/SalesReturn/RETURN_Screen_Title'), 5)

Mobile.setText(findTestObject('Mobile/SalesReturn/Return-InvoiceNo'), findTestData('Mobile Input Data/CallAnalysis').getValue('InvoiceNo', 1), 0, FailureHandling.OPTIONAL)

Mobile.takeScreenshot()

SKU1_MRP = Mobile.getText(findTestObject('Mobile/SalesReturn/Return-MRP'), 0)

Mobile.tap(findTestObject('Mobile/SalesReturn/Return-Select Reason'), 0)

Mobile.takeScreenshot()

Mobile.tap(findTestObject('Object Repository/Mobile/SalesReturn/Damage-option'), 0)

Mobile.tap(findTestObject('Mobile/SalesReturn/Return-Piece'), 0, FailureHandling.STOP_ON_FAILURE)

GlobalVariable.keypadValue = findTestData('Mobile Input Data/Empty_Return').getValue('Sales_return_qty', 1)

Mobile.takeScreenshot()

GlobalVariable.PieceQty = GlobalVariable.keypadValue

Mobile.tap(findTestObject('Mobile/Common/Global_Number_keypad'), 0)

Mobile.tap(findTestObject('Mobile/Common/Btn_Done'), 0)

Mobile.delay(2)

Mobile.tap(findTestObject('Object Repository/Mobile/SalesReturn/SR-Next btn'), 0)

Mobile.comment('Validation for Sales Return Damage Option in Empty Return')

Mobile.verifyElementNotExist(findTestObject('Mobile/Seller_2/Sales Return/Empty_Return_title'), 5, FailureHandling.STOP_ON_FAILURE)

KeywordUtil.logInfo( 'For Sales Return Damage option empty return not applied')

Mobile.takeScreenshot()

Mobile.delay(2)

//--empty return not applied

Mobile.tap(findTestObject('Object Repository/Mobile/Common/Icon_Back'), 0)

Mobile.delay(2)

Mobile.tap(findTestObject('Mobile/Common/Icon_X'), 0,FailureHandling.OPTIONAL)

Mobile.tap(findTestObject('Mobile/Common/Icon_Search'), 0,FailureHandling.OPTIONAL)

Mobile.clearText(findTestObject('Mobile/Common/Field_Search_EnterText'), 0,FailureHandling.OPTIONAL)

GlobalVariable.S_Sku_Name = findTestData('Mobile Input Data/Empty_Return').getValue('Salable_SKU_Name', 1)

Mobile.setText(findTestObject('Mobile/Common/Field_Search_EnterText'), GlobalVariable.S_Sku_Name, 0)

Mobile.takeScreenshot()

Mobile.verifyElementVisible(findTestObject('Mobile/SalesReturn/ProductName-1st'), 3, FailureHandling.STOP_ON_FAILURE)

Mobile.tap(findTestObject('Mobile/SalesReturn/Piece-1st'), 0)

Mobile.delay(2)

Mobile.verifyElementExist(findTestObject('Mobile/SalesReturn/RETURN_Screen_Title'), 5)


Mobile.tap(findTestObject('Object Repository/Mobile/SalesReturn/Damage-option'), 0)

Mobile.takeScreenshot()

Mobile.tap(findTestObject('Object Repository/Mobile/SalesReturn/Salable-option'), 0)

Mobile.tap(findTestObject('Mobile/Common/Btn_Done'), 0)

Mobile.delay(2)

Mobile.tap(findTestObject('Object Repository/Mobile/SalesReturn/SR-Next btn'), 0)

Mobile.delay(2)
Mobile.comment('Validation for Sales Return Salable Option in Empty Return')

Mobile.verifyElementExist(findTestObject('Mobile/Seller_2/Sales Return/Empty_Return_title'), 5, FailureHandling.STOP_ON_FAILURE)

KeywordUtil.logInfo( 'For Sales Return Salable option empty return is applied')

Mobile.takeScreenshot()

SR_Empty_price = Mobile.getText(findTestObject('Mobile/Seller_2/Sales Return/Empty_price_get'), 0)

KeywordUtil.logInfo(SR_Empty_price + 'Empty return price ')

SR_Empty_qty = Mobile.getText(findTestObject('Mobile/Seller_2/Sales Return/Empty_qty_get'), 0)

KeywordUtil.logInfo(SR_Empty_qty + 'Empty return qty ')

SR_Empty_Value = Mobile.getText(findTestObject('Mobile/Seller_2/Sales Return/Empty_Value_get'), 0)

KeywordUtil.logInfo(SR_Empty_Value + 'Empty return value ')

SR_Empty_Return_Value = Mobile.getText(findTestObject('Mobile/Seller_2/Sales Return/Total_Return_value'), 0)

KeywordUtil.logInfo(SR_Empty_Return_Value + 'Total Empty return value')

Mobile.tap(findTestObject('Mobile/SalesReturn/SR-Save btn'), 0)

Mobile.takeScreenshot()

Mobile.verifyElementExist(findTestObject('Mobile/SalesReturn/SR-Saved Successfully'), 3, FailureHandling.OPTIONAL)

Mobile.tap(findTestObject('Mobile/Common/Btn_OK'), 3, FailureHandling.OPTIONAL)

Mobile.takeScreenshot()

KeywordUtil.logInfo('Sales return created successfully with empty return')

Mobile.callTestCase(findTestCase('Test Cases/Product_Mobile/Common/Call_Analysis'), [:], FailureHandling.STOP_ON_FAILURE)

