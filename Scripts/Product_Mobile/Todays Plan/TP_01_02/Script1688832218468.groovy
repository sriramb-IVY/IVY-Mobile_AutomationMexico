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
import com.kms.katalon.entity.global.GlobalVariableEntity as GlobalVariableEntity
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.chrome.ChromeDriver as ChromeDriver
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import com.kms.katalon.keyword.excel.ExcelKeywords as ExcelKeywords
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint

//Mobile.callTestCase(findTestCase('Product_Mobile/Todays Plan/CT/TP_CT_01'), [:], FailureHandling.STOP_ON_FAILURE)
//
//Mobile.callTestCase(findTestCase('Product_Mobile/Todays Plan/CT/TP_CT_02'), [:], FailureHandling.STOP_ON_FAILURE)
//
//WebUI.callTestCase(findTestCase('Product_Mobile/Common/Mobile_Login/VanSeller_Login-With Reset'), [:], FailureHandling.STOP_ON_FAILURE)
Mobile.startApplication(GlobalVariable.APKFile, false)

Mobile.tap(findTestObject('Mobile/Common/Btn_Menu'), 0)

Mobile.callTestCase(findTestCase('Product_Mobile/Common/StockUpdation/CT/StartApplication_StockUpdate(Android,Specific_SKU)'), [('Starting_Row_No') : 0, ('Ending_Row_No') : 10], FailureHandling.STOP_ON_FAILURE)

Mobile.tap(findTestObject('Mobile/Common/Btn_Menu'), 0)

Mobile.tap(findTestObject('Mobile/Deviation/Trade Coverage Menu'), 0)

String sheet_name = 'Task_Management'

String file_name = 'Web Input Data'

ArrayList<String> Retailer = CustomKeywords.'poi.Automation.GetAllWebDataRow_Flag.getAllDataRow'(file_name, sheet_name, 'Retailer')

//for (int i = 0; i < Retailer.size(); i++) {
//    not_run: Number_of_store = Mobile.getText(findTestObject('Mobile/Seller_2/Todays Plan/Number_of_store'), 
//        0)
//
//    not_run: KeywordUtil.logInfo(Number_of_store)
Mobile.tap(findTestObject('Mobile/Deviation/Deviation Button'), 3)

if (Mobile.verifyElementVisible(findTestObject('Mobile/Deviation/While using the app For Deviation'), 3, FailureHandling.OPTIONAL)) {
    Mobile.tap(findTestObject('Mobile/Deviation/While using the app For Deviation'), 5)
}

Mobile.tap(findTestObject('Mobile/Common/Icon_Search'), 3)

Mobile.tap(findTestObject('Mobile/Common/Field_Search_EnterText'), 3)

KeywordUtil.logInfo('Plan For Current Date')

GlobalVariable.RetailerName = findTestData('Mobile Input Data/Todays Plan').getValue('Deviation_Retailer', 1)

Mobile.setText(findTestObject('Mobile/Common/Field_Search_EnterText'), GlobalVariable.RetailerName, 5)

Mobile.takeScreenshot()

Mobile.tap(findTestObject('Mobile/Deviation/Retailer Name'), 3)

Mobile.tap(findTestObject('Mobile/Deviation/Add To Plan Button'), 5)

Mobile.delay(2)

KeywordUtil.logInfo('Without Choosing Reason. Reason is mandatory')

Mobile.tap(findTestObject('Mobile/Deviation/Todays PLan ADD Button'), 5)

KeywordUtil.logInfo('Choosing Reason. Reason is mandatory')

Mobile.verifyElementVisible(findTestObject('Mobile/Deviation/today task over RBTN'), 5)

Mobile.tap(findTestObject('Mobile/Deviation/today task over RBTN'), 5)

Mobile.tap(findTestObject('Mobile/Deviation/Todays PLan ADD Button'), 5)

KeywordUtil.logInfo('Saved successfully Popup')

Mobile.takeScreenshot()

Mobile.tap(findTestObject('Mobile/Common/Btn_OK'), 5)

Mobile.delay(3)

Mobile.tap(findTestObject('Mobile/Common/Icon_Search'), 3)

Mobile.tap(findTestObject('Mobile/Common/Field_Search_EnterText'), 5)

GlobalVariable.ProductName = findTestData('Mobile Input Data/Deviation').getValue('Retailer', 2)

Mobile.sendKeys(findTestObject('Mobile/Common/Field_Search_EnterText'), GlobalVariable.ProductName)

Mobile.takeScreenshot()

Mobile.tap(findTestObject('Mobile/Common/Btn_Menu'), 0)

Mobile.tap(findTestObject('Mobile/Deviation/Trade Coverage Menu'), 0)

Mobile.tap(findTestObject('Mobile/Common/Icon_Search'), 3)

Mobile.tap(findTestObject('Mobile/Common/Field_Search_EnterText'), 5)

GlobalVariable.label = findTestData('Mobile Input Data/Todays Plan').getValue('RetailerName', 1)

Mobile.sendKeys(findTestObject('Mobile/Common/Field_Search_EnterText'), GlobalVariable.label)

Mobile.takeScreenshot()

KeywordUtil.logInfo('User is able to search the store name')

Retailer_name = Mobile.getText(findTestObject('Mobile/Seller Task/Retailer'), 0)

KeywordUtil.logInfo('Retailer_name = ' + Retailer_name)

Mobile.tap(findTestObject('Mobile/Seller Task/Retailer'), 0)

Mobile.takeScreenshot()

Store_Name = Mobile.getText(findTestObject('Mobile/Seller_2/Todays Plan/Store Name Selected'), 0)

KeywordUtil.logInfo(Store_Name)

Mobile.verifyMatch(findTestData('Mobile Input Data/Todays Plan').getValue('RetailerName', 2), Store_Name, false)

Mobile.takeScreenshot()

GlobalVariable.label = findTestData('Mobile Input Data/Todays Plan').getValue('Location', 1)

Location_Name = Mobile.getText(findTestObject('Mobile/Seller_2/Todays Plan/Location'), 0)

KeywordUtil.logInfo(Location_Name)

Mobile.verifyMatch(findTestData('Mobile Input Data/Todays Plan').getValue('Location', 2), Location_Name, false)

Mobile.takeScreenshot()

//}
Mobile.tap(findTestObject('Mobile/Seller Task/Start Visit button'), 0)

if (Mobile.verifyElementVisible(findTestObject('Mobile/Deviation/Location validation yes button'), 5, FailureHandling.OPTIONAL)) {
    Mobile.tap(findTestObject('Mobile/Deviation/Location validation yes button'), 0)
}

if (Mobile.verifyElementVisible(findTestObject('Object Repository/Mobile/Seller_2/TR/Select_Distributor_popup'), 5, FailureHandling.OPTIONAL)) {
    Mobile.tap(findTestObject('Object Repository/Mobile/Seller_2/TR/H10dhivbranch_radio_btn'), 0)

    Mobile.delay(2, FailureHandling.STOP_ON_FAILURE)
}

Mobile.delay(6, FailureHandling.STOP_ON_FAILURE)

Mobile.swipe(0, 150, 0, 400)

Mobile.tap(findTestObject('Mobile/Deviation/Stock and Order Menu'), 5)

Mobile.tap(findTestObject('Mobile/Common/Icon_Star'), 0, FailureHandling.OPTIONAL)

Mobile.tap(findTestObject('Mobile/Common/Btn_Clear'), 0, FailureHandling.OPTIONAL)

Mobile.tap(findTestObject('Mobile/Deviation/Enter Qty'), 5)

Mobile.tap(findTestObject('Mobile/Deviation/1 qty'), 5)

Mobile.tap(findTestObject('Mobile/Deviation/Avail_CheckBox'), 5)

Product_Amt = Mobile.getText(findTestObject('Mobile/Deviation/Product Total AMT'), 5)

//GlobalVariable.Price = Product_Amt
KeywordUtil.logInfo('Product_Amt is  = ' + GlobalVariable.Price)

exlpath = 'DDF\\Mobile Input data\\Mobile Input data.xlsx'

Sheetname = 'Todays Plan'

workbook01 = ExcelKeywords.getWorkbook(exlpath)

sheet1 = ExcelKeywords.getExcelSheet(workbook01, Sheetname)

ExcelKeywords.setValueToCellByIndex(sheet1, 1, 4, Product_Amt)

ExcelKeywords.saveWorkbook(exlpath, workbook01)

Mobile.tap(findTestObject('Mobile/Deviation/Next button'), 5)

if (Mobile.verifyElementVisible(findTestObject('Mobile/OrderInvoice/MustSell_Alert/Must Sell-Title'), 1, FailureHandling.OPTIONAL)) {
    Mobile.tap(findTestObject('Mobile/Common/Btn_Done'), 4, FailureHandling.OPTIONAL)
}

Mobile.delay(3, FailureHandling.STOP_ON_FAILURE)

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

String order_num = Submitted_2.replaceAll('\'', '')

ExcelKeywords.setValueToCellByIndex(sheet1, 1, 5, order_num)

ExcelKeywords.saveWorkbook(exlpath, workbook01)

Mobile.tap(findTestObject('Mobile/Common/Btn_OK'), 5)

Mobile.delay(2)

Mobile.tap(findTestObject('Mobile/Common/Icon_Back'), 2, FailureHandling.OPTIONAL)

Mobile.delay(2, FailureHandling.OPTIONAL)

Mobile.swipe(0, 150, 0, 400, FailureHandling.OPTIONAL)

Mobile.tap(findTestObject('Mobile/Store_Actvy/Menu-OrderInvoice'), 2, FailureHandling.OPTIONAL)

WebUI.callTestCase(findTestCase('Product_Mobile/Common/OrderPopUp(EditOrder)'), [:], FailureHandling.OPTIONAL)

not_run: Mobile.tap(findTestObject('Mobile/SummaryScreen/SummaryScreen-Invoice btn'), 2, FailureHandling.OPTIONAL)

not_run: Mobile.tap(findTestObject('Mobile/Common/Btn_Confirm'), 2, FailureHandling.OPTIONAL)

Mobile.delay(2, FailureHandling.OPTIONAL)

Mobile.tap(findTestObject('Mobile/Common/Icon_Back'), 2, FailureHandling.OPTIONAL)

Mobile.delay(2)

WebUI.callTestCase(findTestCase('Product_Mobile/Common/Call_Analysis'), [:], FailureHandling.STOP_ON_FAILURE)

