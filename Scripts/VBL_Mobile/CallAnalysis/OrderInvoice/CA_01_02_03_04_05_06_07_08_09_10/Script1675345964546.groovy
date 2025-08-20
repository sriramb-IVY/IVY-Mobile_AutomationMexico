import java.sql.Connection as Connection
import java.sql.DriverManager as DriverManager
import java.sql.ResultSet as ResultSet
import java.sql.Statement as Statement
import java.text.SimpleDateFormat as SimpleDateFormat
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.mobile.keyword.internal.MobileDriverFactory as MobileDriverFactory
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import internal.GlobalVariable as GlobalVariable
import io.appium.java_client.AppiumDriver as AppiumDriver
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import org.openqa.selenium.WebElement as WebElement
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.chrome.ChromeDriver as ChromeDriver
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import com.kms.katalon.keyword.excel.ExcelKeywords as ExcelKeywords
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint

//Mobile.callTestCase(findTestCase('VBL_Mobile/Re-Usables/Mobile_Login/VanSeller_Login-With Reset'), [:], FailureHandling.STOP_ON_FAILURE)
Mobile.callTestCase(findTestCase('VBL_Mobile/Re-Usables/Select_TradeCoverage_Menu'), [:], FailureHandling.STOP_ON_FAILURE)

GlobalVariable.RetailerName = findTestData('VBL_Mobile Input Data/CallAnalysis').getValue('Retailer_Name', 1)

Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/MainMenu/Menu_Trade Coverage'), 0)

Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Reusable Object/Retailer Search Icon'), 5)

Mobile.hideKeyboard()

Mobile.delay(1)

not_run: Mobile.sendKeys(findTestObject('XXXXXXXXXXXX/Mobile Part/Reusable Object/Retailer Enter Search Field'), GlobalVariable.RetailerName, 
    FailureHandling.OPTIONAL)

Mobile.setText(findTestObject('XXXXXXXXXXXX/Mobile Part/Reusable Object/Retailer Enter Search Field'), GlobalVariable.RetailerName, 
    5)

Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/stores click'), 5)

Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/Start Visit Button'), 5)

if (Mobile.verifyElementVisible(findTestObject('Object Repository/XXXXXXXXXXXX/Mobile Part/Store_Actvy/Location Validation'), 
    5, FailureHandling.OPTIONAL)) {
    Mobile.tap(findTestObject('Object Repository/XXXXXXXXXXXX/Mobile Part/Store_Actvy/location validtion- YES'), 5)
}

Mobile.delay(9)

Mobile.swipe(0, 150, 0, 400, FailureHandling.OPTIONAL)

Mobile.delay(2)

Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/CallAnalysis/StoreActy_Close Call Btn'), 0)

if (Mobile.verifyElementVisible(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/CallAnalysis/Select Reason for no order'), 
    0, FailureHandling.OPTIONAL)) {
    Mobile.verifyElementVisible(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/CallAnalysis/Select Reason for no order'), 
        0)

    Mobile.verifyElementExist(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/CallAnalysis/Select Reason for no order'), 
        0)

    Mobile.takeScreenshot()

    println('Without any Order "select reason for no order" field is displayed properly! ')

    Mobile.delay(2)

    Mobile.tap(findTestObject('Mobile/Common/Icon_Back'), 0)

    Mobile.delay(3)

    Mobile.swipe(0, 150, 0, 400, FailureHandling.OPTIONAL)
} else {
    Mobile.verifyElementVisible(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/CallAnalysis/Close Call'), 3)

    Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/CallAnalysis/Close Call'), 0)

    Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/CallAnalysis/CloseCall_Alert_OK btn'), 0)

    Mobile.delay(3)

    Mobile.swipe(0, 150, 0, 400, FailureHandling.OPTIONAL)

    Mobile.delay(3)

    Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/CallAnalysis/StoreActy_Close Call Btn'), 0)

    Mobile.verifyElementVisible(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/CallAnalysis/Select Reason for no order'), 
        0)

    Mobile.verifyElementExist(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/CallAnalysis/Select Reason for no order'), 
        0)

    Mobile.takeScreenshot()

    println('Without any Order "select reason for no order" field is displayed properly!')

    Mobile.delay(2)

    Mobile.tap(findTestObject('Mobile/Common/Icon_Back'), 0)

    Mobile.delay(3)

    Mobile.swipe(0, 150, 0, 400, FailureHandling.OPTIONAL)
}

//ProductName = findTestData('VBL_Mobile Input Data/CallAnalysis').getValue('FB_Product_Name', 1)
Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/Menu-OrderInvoice'), 0, FailureHandling.STOP_ON_FAILURE)

//mustsell order start
Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/Star image'), 0)

Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/Star image'), 1, FailureHandling.OPTIONAL)

Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/StarIconFilter/Must Sell'), 0)

Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/StarIconFilter/FilterScreen-Apply btn'), 0)

Mobile.delay(2, FailureHandling.STOP_ON_FAILURE)

Product_Count = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/OrderInvoice_Title'), 0)

Total_MustSell_Product_Count = Product_Count.substring(11, 12)

println(Total_MustSell_Product_Count)

KeywordUtil.logInfo(Total_MustSell_Product_Count)

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Search_Product'), [:], FailureHandling.STOP_ON_FAILURE)

MSL_Product = findTestData('VBL_Mobile Input Data/CallAnalysis').getValue('MSL_Product_Name', 1)

GlobalVariable.ProductName = MSL_Product

Mobile.setText(findTestObject('XXXXXXXXXXXX/Mobile Part/Reusable Object/Product Enter Search'), GlobalVariable.ProductName, 
    5)

Mobile.delay(3, FailureHandling.STOP_ON_FAILURE)

Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/Piece_Field'), 5)

Mobile.hideKeyboard(FailureHandling.OPTIONAL)

GlobalVariable.keypadValue = findTestData('VBL_Mobile Input Data/Invoice').getValue('Keypad_Number', 1)

Mobile.delay(1, FailureHandling.STOP_ON_FAILURE)

Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Reusable Object/Box-Keypad/Global-number_keypad'), 5)

Mobile.tap(findTestObject('Mobile/Common/Btn_OK'), 5)

MSL_Product_Total = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/OrderInvoiceScrn-SkuTotal'), 
    10)

Product_Counts = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/OrderInvoice_Title'), 0)

Entered_MustSell_Product_Count = Product_Counts.substring(11, 12)

KeywordUtil.logInfo(Entered_MustSell_Product_Count)

Mobile.delay(3)

Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Reusable Object/Product Search Icon'), 2, FailureHandling.OPTIONAL)

Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Reusable Object/Product Clear(X) Icon'), 2, FailureHandling.OPTIONAL)

//Mobile.callTestCase(findTestCase('Product_Mobile/Common/Clear_Star_Filter_Default_Selection'), [:], FailureHandling.STOP_ON_FAILURE)
not_run: Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/Star image'), 0)

Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/Star image'), 1, FailureHandling.OPTIONAL)

Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/FilterScreen/FilterScreen-clear btn'), 2, FailureHandling.OPTIONAL)

Mobile.delay(2)

Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/Star image'), 1, FailureHandling.OPTIONAL)

Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/StarIconFilter/Focus Brand'), 0)

Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/StarIconFilter/FilterScreen-Apply btn'), 0)

Mobile.delay(2, FailureHandling.STOP_ON_FAILURE)

Product_Count = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/OrderInvoice_Title'), 0)

Total_FocusBrand_Product_Count = Product_Count.substring(13, 14)

KeywordUtil.logInfo(Total_FocusBrand_Product_Count)

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Search_Product'), [:], FailureHandling.STOP_ON_FAILURE)

Focusbrand_product = findTestData('VBL_Mobile Input Data/CallAnalysis').getValue('FB_Product_Name', 1)

GlobalVariable.ProductName = Focusbrand_product

Mobile.setText(findTestObject('XXXXXXXXXXXX/Mobile Part/Reusable Object/Product Enter Search'), GlobalVariable.ProductName, 
    5)

Mobile.delay(2, FailureHandling.STOP_ON_FAILURE)

Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/Piece_Field'), 5)

Mobile.hideKeyboard(FailureHandling.OPTIONAL)

GlobalVariable.keypadValue = findTestData('VBL_Mobile Input Data/Invoice').getValue('Keypad_Number', 1)

Mobile.delay(1, FailureHandling.STOP_ON_FAILURE)

Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Reusable Object/Box-Keypad/Global-number_keypad'), 5)

Mobile.tap(findTestObject('Mobile/Common/Btn_OK'), 5)

Focusbrand_product_Total = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/OrderInvoiceScrn-SkuTotal'), 
    10)

Product_Counts = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/OrderInvoice_Title'), 0)

Entered_focusbrand_Product_Count = Product_Counts.substring(13, 14)

KeywordUtil.logInfo(Entered_focusbrand_Product_Count)

Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/Next Btn-OrderInvoiceScreen'), 0)

Mobile.callTestCase(findTestCase('VBL_Mobile/Re-Usables/MustSell_Alert'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('VBL_Mobile/Re-Usables/Tap_Next_Btn_ApplyschemeScrn_CT'), [:], FailureHandling.STOP_ON_FAILURE)

'***********************************************************************************************************************'
total_Value = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/SummaryScreen/SummaryScreen-ValueAmt'), 
    0)

Mobile.tap(findTestObject('Mobile/SummaryScreen/SummaryScreen-Order btn'), 0)

Mobile.tap(findTestObject('Mobile/Common/Btn_Confirm'), 0)

Mobile.delay(2, FailureHandling.STOP_ON_FAILURE)

id = Mobile.getText(findTestObject('Mobile/SummaryScreen/Click Order-OrderSavedID-Title'), 0)

Ord_ID = id.replaceAll('[Order Saved. Order ID is:\']', '')

invoice_ID = Ord_ID.replaceAll('[\']', '')

KeywordUtil.logInfo(invoice_ID)

Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/SummaryScreen/Click Order-PRINT ORDER btn'), 0)

Mobile.delay(2)

Mobile.tap(findTestObject('Mobile/Common/Icon_Back'), 0)

Mobile.tap(findTestObject('Mobile/Common/Icon_Back'), 2, FailureHandling.OPTIONAL)

Mobile.delay(3)

Mobile.swipe(0, 150, 0, 400, FailureHandling.OPTIONAL)

Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/Menu-OrderInvoice'), 0, FailureHandling.STOP_ON_FAILURE)

Mobile.delay(2)

WebUI.callTestCase(findTestCase('VBL_Mobile/Re-Usables/OrderPopUp(EditOrder)'), [:], FailureHandling.STOP_ON_FAILURE)

Mobile.tap(findTestObject('Mobile/Common/Icon_Back'), 0, FailureHandling.OPTIONAL)

Mobile.delay(3)

Mobile.swipe(0, 150, 0, 400, FailureHandling.OPTIONAL)

Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/CallAnalysis/StoreActy_Close Call Btn'), 0)

Mobile.delay(2)

//totalLine
Total_No_Product_SummaryScrn = (Integer.parseInt(Entered_MustSell_Product_Count) + Integer.parseInt(Entered_focusbrand_Product_Count))

KeywordUtil.logInfo(Total_No_Product_SummaryScrn.toString())

CallAnalysis_TotalLines = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/CallAnalysis/Value-Total Lines'), 
    0)

Mobile.verifyEqual(Integer.parseInt(CallAnalysis_TotalLines), Total_No_Product_SummaryScrn, FailureHandling.STOP_ON_FAILURE)

KeywordUtil.logInfo((CallAnalysis_TotalLines.toString() + '  : is EQUAL to :   ') + Total_No_Product_SummaryScrn.toString())

Mobile.takeScreenshot()

println('Total Lines verified properly')

//SalesOrder
CallAnalysis_SaleOrder = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/CallAnalysis/Value-Sales'), 0)

String[] CallAnalysisSale = CallAnalysis_SaleOrder.split('/')

String CallAnalysisSaleOrder = CallAnalysisSale[0]

//Mobile.verifyMatch(CallAnalysisSaleOrder, total_Value, false, FailureHandling.OPTIONAL)
Mobile.verifyEqual(Double.parseDouble(CallAnalysisSaleOrder), Double.parseDouble(total_Value), FailureHandling.STOP_ON_FAILURE)

KeywordUtil.logInfo((CallAnalysis_SaleOrder + '  : is EQUAL to :  ') + total_Value)

Mobile.takeScreenshot()

println('Sales Value verified properly')

//TotalValue
CallAnalysis_TotalValue = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/CallAnalysis/Value-Total Value'), 
    0)

GlobalVariable.TotalValue = CallAnalysis_TotalValue

Mobile.verifyMatch(CallAnalysis_TotalValue, total_Value, false, FailureHandling.STOP_ON_FAILURE)

KeywordUtil.logInfo((CallAnalysis_TotalValue + '  : is EQUAL to :  ') + total_Value)

Mobile.takeScreenshot()

println('Total Value verified properly')

//MustSell Value
MSL_Value = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/CallAnalysis/Value-MSL Lines'), 0)

Mobile.verifyEqual(Double.parseDouble(MSL_Value), Double.parseDouble(Entered_MustSell_Product_Count), FailureHandling.STOP_ON_FAILURE)

KeywordUtil.logInfo((MSL_Value + '  : is EQUAL to :  ') + Entered_MustSell_Product_Count)

Mobile.takeScreenshot()

println('Must Sell value verified proprly')

// must sell Total Value
MSL_TotalValue = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/CallAnalysis/TotalValue-MSL Lines'), 
    0)

MSL_Total_Value = MSL_TotalValue.replaceAll('[/]', '')

KeywordUtil.logInfo(MSL_Total_Value)

Mobile.verifyEqual(Double.parseDouble(MSL_Total_Value), Double.parseDouble(Total_MustSell_Product_Count), FailureHandling.STOP_ON_FAILURE)

KeywordUtil.logInfo((MSL_Total_Value + '  : is EQUAL to :  ') + Total_MustSell_Product_Count)

Mobile.takeScreenshot()

println('MSL Total Value verified properly')

//% of Must Sell
Perc_of_MSL = ((Double.parseDouble(Entered_MustSell_Product_Count) / Double.parseDouble(Total_MustSell_Product_Count)) * 
100)

KeywordUtil.logInfo(Perc_of_MSL.toString())

PercValue_MSL = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/CallAnalysis/PercVale-MSL Lines'), 0)

Perc_Value_MSL = PercValue_MSL.replaceAll('[% Achieved]', '')

KeywordUtil.logInfo(Perc_Value_MSL)

Mobile.verifyEqual(Double.parseDouble(Perc_Value_MSL), Perc_of_MSL, FailureHandling.STOP_ON_FAILURE)

KeywordUtil.logInfo((Perc_Value_MSL.toString() + '  : is EQUAL to :  ') + Perc_of_MSL.toString())

Mobile.takeScreenshot()

println('Percentage of MSL verified properly')

//////////
//FocusBrand Value
FocusBrand_Value = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/CallAnalysis/Value-MSL Lines'), 0)

Mobile.verifyEqual(Double.parseDouble(FocusBrand_Value), Double.parseDouble(Entered_focusbrand_Product_Count), FailureHandling.STOP_ON_FAILURE)

KeywordUtil.logInfo((FocusBrand_Value + '  : is EQUAL to :  ') + Entered_focusbrand_Product_Count)

Mobile.takeScreenshot()

println('FocusBrand value verified proprly')

// FocusBrand Total Value
FocusBrand_TotalValue = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/CallAnalysis/TotalValue-MSL Lines'), 
    0)

FocusBrand_Total_Value = FocusBrand_TotalValue.replaceAll('[/]', '')

KeywordUtil.logInfo(FocusBrand_Total_Value)

Mobile.verifyEqual(Double.parseDouble(FocusBrand_Total_Value), Double.parseDouble(Total_FocusBrand_Product_Count), FailureHandling.STOP_ON_FAILURE)

KeywordUtil.logInfo((FocusBrand_Total_Value + '  : is EQUAL to :  ') + Total_FocusBrand_Product_Count)

Mobile.takeScreenshot()

println('FocusBrand Total Value verified properly')

//% of Focus brand
Perc_of_FocusBrand = ((Double.parseDouble(Entered_focusbrand_Product_Count) / Double.parseDouble(Total_FocusBrand_Product_Count)) * 
100)

KeywordUtil.logInfo(Perc_of_FocusBrand.toString())

PercValue_FocusBrand = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/CallAnalysis/PercVale-MSL Lines'), 
    0)

Perc_Value_FocusBrand = PercValue_FocusBrand.replaceAll('[% Achieved]', '')

KeywordUtil.logInfo(Perc_Value_FocusBrand)

Mobile.verifyEqual(Double.parseDouble(Perc_Value_FocusBrand), Perc_of_FocusBrand, FailureHandling.STOP_ON_FAILURE)

KeywordUtil.logInfo((Perc_Value_FocusBrand + '  : is EQUAL to :  ') + Perc_of_FocusBrand.toString())

Mobile.takeScreenshot()

println('Percentage of FocusBrand verified properly')

Mobile.tap(findTestObject('Mobile/Common/Icon_Back'), 0)

Mobile.delay(3)

Mobile.swipe(0, 150, 0, 400, FailureHandling.OPTIONAL)

