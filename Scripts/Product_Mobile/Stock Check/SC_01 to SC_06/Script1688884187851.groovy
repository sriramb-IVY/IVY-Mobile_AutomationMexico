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
import com.kms.katalon.keyword.excel.ExcelKeywords as ExcelKeywords

//CustomKeywords.'poi.VBL.Automation.app_crash_fixed.killNodejs'()
//Mobile.callTestCase(findTestCase('Product_Mobile/Common/Mobile_Login/PreSeller_Login'), [:], FailureHandling.STOP_ON_FAILURE)
not_run: Mobile.callTestCase(findTestCase('Product_Mobile/Common/Mobile_Login/VanSeller_Login-With Reset'), [:], FailureHandling.STOP_ON_FAILURE)

Mobile.startApplication(GlobalVariable.APKFile, false)

Mobile.tap(findTestObject('Mobile/Common/Btn_Menu'), 0)

Mobile.tap(findTestObject('Mobile/MainMenu/Menu_Trade Coverage'), 0)

Mobile.tap(findTestObject('Mobile/Common/Icon_Search'), 5)

GlobalVariable.RetailerName = findTestData('Mobile Input Data/Stock Check').getValue('RetailerName', 1)

Mobile.setText(findTestObject('Mobile/Common/Field_Search_EnterText'), GlobalVariable.RetailerName, 
    5)

Mobile.tap(findTestObject('Mobile/TradeCoverage/First_Retailer_Selection_From_List'), 5)

if (Mobile.verifyElementVisible(findTestObject('Mobile/Mobile Part/Preseller/New Store/Location_popup'), 0, FailureHandling.OPTIONAL)) {
    Mobile.tap(findTestObject('Mobile/Mobile Part/Preseller/New Store/Allow_Btn'), 0)

    Mobile.delay(2, FailureHandling.STOP_ON_FAILURE)
}

Mobile.tap(findTestObject('Mobile/Common/Btn_StartVisit'), 5)

if (Mobile.verifyElementVisible(findTestObject('Mobile/Deviation/Location validation yes button'), 5, 
    FailureHandling.OPTIONAL)) {
    Mobile.tap(findTestObject('Mobile/Deviation/Location validation yes button'), 0)
}

if (Mobile.verifyElementVisible(findTestObject('Object Repository/Mobile/Seller_2/TR/Select_Distributor_popup'), 
    5, FailureHandling.OPTIONAL)) {
    Mobile.tap(findTestObject('Object Repository/Mobile/Seller_2/TR/H10dhivbranch_radio_btn'), 0)

    Mobile.delay(2, FailureHandling.STOP_ON_FAILURE)
}

Mobile.delay(6)

Mobile.swipe(0, 150, 0, 400)

Mobile.delay(1, FailureHandling.STOP_ON_FAILURE)

Mobile.tap(findTestObject('Mobile/Seller_2/Stock Check/Stock Check Menu'), 0)

Mobile.delay(6)

Mobile.tap(findTestObject('Mobile/Common/Icon_Search'), 0)

GlobalVariable.ProductName = findTestData('Mobile Input Data/Stock Check').getValue('ProductName1', 1)

Mobile.setText(findTestObject('Mobile/Common/Field_Search_EnterText'), GlobalVariable.ProductName, 
    5)

Mobile.delay(2, FailureHandling.STOP_ON_FAILURE)

Mobile.verifyElementNotChecked(findTestObject('Mobile/Seller_2/Stock Check/Checkbox1'), 3, FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

//product1
Product_name = Mobile.getText(findTestObject('Mobile/Seller_2/Stock Check/Product Name'), 2)

KeywordUtil.logInfo(Product_name)

Mobile.verifyMatch(Product_name, findTestData('Mobile Input Data/Stock Check').getValue('ProductName1', 1), false, FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

Mobile.tap(findTestObject('Mobile/Seller_2/Stock Check/Checkbox1'), 3)

if (Mobile.verifyElementChecked(findTestObject('Mobile/Seller_2/Stock Check/Checked Checkbox'), 3, FailureHandling.STOP_ON_FAILURE)) {
    KeywordUtil.logInfo('Stock is available and checkbox is checked')

    Mobile.takeScreenshot()
}

Mobile.tap(findTestObject('Mobile/Seller_2/Stock Check/Piece Input Field'), 2)

Mobile.tap(findTestObject('Mobile/Seller_2/Stock Check/Erase Button'), 2)

Mobile.tap(findTestObject('Mobile/Seller_2/Stock Check/Input Qty Button'), 2)

Mobile.takeScreenshot()

KeywordUtil.logInfo('The user is able to enter UOM wise product quantity')

Total_qty = Mobile.getText(findTestObject('Mobile/Seller_2/Stock Check/Total Field'), 2)

if (Mobile.verifyNotEqual(Total_qty, '0', FailureHandling.STOP_ON_FAILURE)) {
    KeywordUtil.logInfo('Quantity cannot be zero if stock is available')

    Mobile.takeScreenshot()
}

Mobile.tap(findTestObject('Mobile/Seller_2/Stock Check/No of Facing Field'), 2)

Mobile.tap(findTestObject('Mobile/Seller_2/Stock Check/Input Qty Button'), 2)

Mobile.takeScreenshot()

KeywordUtil.logInfo('The user is able to enter number of facings for a product')

Total_lines_dispalyed = Mobile.getText(findTestObject('Mobile/Seller_2/Stock Check/Total Lines'), 2)

KeywordUtil.logInfo(Total_lines_dispalyed)

Mobile.takeScreenshot()

//Product2
//Mobile.tap(findTestObject('Mobile/Common/Icon_Search'), 0)
GlobalVariable.ProductName = findTestData('Mobile Input Data/Stock Check').getValue('ProductName2', 1)

Mobile.setText(findTestObject('Mobile/Common/Field_Search_EnterText'), GlobalVariable.ProductName, 
    5)

Product_name2 = Mobile.getText(findTestObject('Mobile/Seller_2/Stock Check/Product Name'), 2)

KeywordUtil.logInfo(Product_name2)

Mobile.verifyMatch(Product_name2, findTestData('Mobile Input Data/Stock Check').getValue('ProductName2', 1), false, FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

Mobile.tap(findTestObject('Mobile/Seller_2/Stock Check/Checkbox1'), 3)

if (Mobile.verifyElementChecked(findTestObject('Mobile/Seller_2/Stock Check/Checked Checkbox'), 3, FailureHandling.STOP_ON_FAILURE)) {
    KeywordUtil.logInfo('Stock is available and checkbox is checked')

    Mobile.takeScreenshot()
}

Mobile.tap(findTestObject('Mobile/Seller_2/Stock Check/Piece Input Field'), 2)

Mobile.tap(findTestObject('Mobile/Seller_2/Stock Check/Erase Button'), 2)

Mobile.tap(findTestObject('Mobile/Seller_2/Stock Check/Input Qty Button'), 2)

Mobile.takeScreenshot()

KeywordUtil.logInfo('The user is able to enter UOM wise product quantity')

Mobile.tap(findTestObject('Mobile/Seller_2/Stock Check/No of Facing Field'), 2)

Mobile.tap(findTestObject('Mobile/Seller_2/Stock Check/Input Qty Button'), 2)

Share_val = Mobile.getText(findTestObject('Mobile/Seller_2/Stock Check/Share Percent Value'), 2)

KeywordUtil.logInfo(Share_val)

GlobalVariable.Share_Percent = Share_val

Total_lines2 = Mobile.getText(findTestObject('Mobile/Seller_2/Stock Check/Total Lines'), 2)

KeywordUtil.logInfo(Total_lines2)

Mobile.takeScreenshot()

Mobile.tap(findTestObject('Mobile/Seller_2/Stock Check/Save Button'), 0)

Mobile.takeScreenshot()

Popup_text = Mobile.getText(findTestObject('Mobile/Seller_2/Stock Check/Saved Popup'), 2)

KeywordUtil.logInfo(Popup_text)

Mobile.verifyMatch(Popup_text, findTestData('Mobile Input Data/Stock Check').getValue('Popup', 1), false, FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

Mobile.tap(findTestObject('Mobile/Common/Btn_OK'), 0)

Mobile.delay(2)

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Call_Analysis(without Order)'), [:], FailureHandling.STOP_ON_FAILURE)

not_run: Mobile.swipe(0, 150, 0, 400)

not_run: Mobile.delay(1, FailureHandling.STOP_ON_FAILURE)

not_run: Mobile.tap(findTestObject('Mobile/Seller Task/Close Call1 Button'), 3)

not_run: Mobile.tap(findTestObject('Mobile/Seller Task/Select Reason for no order'), 3)

not_run: Mobile.tap(findTestObject('null'), 3)

not_run: Mobile.tap(findTestObject('Mobile/Seller Task/Remarks'), 3)

not_run: Remark = 'Testing'

not_run: Mobile.setText(findTestObject('Mobile/Seller Task/Remarks'), Remark, 5)

not_run: Mobile.waitForElementPresent(findTestObject('Mobile/Seller Task/Close Call2 Button'), 4)

not_run: Mobile.tap(findTestObject('Mobile/Seller Task/Close Call2 Button'), 4)

not_run: Mobile.tap(findTestObject('Mobile/Common/Btn_OK'), 4)

not_run: Mobile.delay(3)

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Sync'), [:], FailureHandling.STOP_ON_FAILURE)

Mobile.callTestCase(findTestCase('Product_Mobile/Common/HHT_Transaction_DB'), [:], FailureHandling.STOP_ON_FAILURE)

not_run: Mobile.callTestCase(findTestCase('Product_Mobile/Stock Check/SC_07'), [:], FailureHandling.STOP_ON_FAILURE)

not_run: WebUI.callTestCase(findTestCase('Product_Mobile/Stock Check/StockCheck_Delete_DB'), [:], FailureHandling.STOP_ON_FAILURE)

