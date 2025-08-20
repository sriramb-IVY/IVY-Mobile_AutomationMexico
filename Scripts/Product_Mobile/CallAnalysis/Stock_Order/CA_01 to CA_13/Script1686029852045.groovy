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

//Mobile.startApplication(GlobalVariable.APK_File, false)
//Mobile.startExistingApplication("com.ivy.cpg.view.splash.DecryptionActivity")
//Mobile.tap(findTestObject('Mobile/Common/Btn_Menu'), 0)
//
//if (Mobile.verifyElementExist(findTestObject('Mobile/MainMenu/Menu_Trade Coverage'), 2, FailureHandling.OPTIONAL)) {
//    'Load Management menu visible'
//} else {
//    Mobile.callTestCase(findTestCase('Product_Mobile/Common/Mobile_Login/Relaunch the app'), [:], FailureHandling.CONTINUE_ON_FAILURE)
//
//    Mobile.tap(findTestObject('Mobile/Common/Btn_Menu'), 0)
//}
//
//GlobalVariable.RetailerName = findTestData('Mobile Input Data/CallAnalysis').getValue('Retailer_Name', 1)
//
//Mobile.tap(findTestObject('Mobile/MainMenu/Menu_Trade Coverage'), 0)
//
//Mobile.tap(findTestObject('Mobile/Common/Icon_Search'), 5)
//
//Mobile.setText(findTestObject('Mobile/Common/Field_Search_EnterText'), GlobalVariable.RetailerName, 
//    5)
//
//Mobile.tap(findTestObject('Mobile/TradeCoverage/First_Retailer_Selection_From_List'), 5)
//
//Mobile.tap(findTestObject('Mobile/Common/Btn_StartVisit'), 5)
//
//if (Mobile.verifyElementVisible(findTestObject('Object Repository/Mobile/Common/Popup_Title_LocationValidation'), 
//    5, FailureHandling.OPTIONAL)) {
//    Mobile.tap(findTestObject('Mobile/Common/Btn_YES'), 5)
//}
//
//Mobile.delay(9)
WebUI.callTestCase(findTestCase('Product_Mobile/Common/Seller_Menu_Selection'), [('MenuName') : findTestData('Mobile Input Data/Common').getValue('Menu', 3), ('SubMenuName') : findTestData('Mobile Input Data/Common').getValue('Menu', 1)], FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('Product_Mobile/Common/TradeCoverage(SelectRetailer)'), [('RetailerName') : findTestData('Mobile Input Data/CallAnalysis').getValue('Retailer_Name', 1)], FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('Product_Mobile/Common/StartVisit'), [:], FailureHandling.STOP_ON_FAILURE)

Mobile.swipe(0, 150, 0, 400, FailureHandling.OPTIONAL)

Mobile.delay(2)

Mobile.tap(findTestObject('Mobile/CallAnalysis/StoreActy_Close Call Btn'), 0)

Mobile.verifyElementVisible(findTestObject('Mobile/CallAnalysis/Call Duration'), 0)

Mobile.verifyElementExist(findTestObject('Mobile/CallAnalysis/Call Duration'), 0)

Mobile.takeScreenshot()

println('Should be able to view the Visit duration  ')

Mobile.verifyElementVisible(findTestObject('Mobile/CallAnalysis/Select Reason for no order'), 0)

Mobile.verifyElementExist(findTestObject('Mobile/CallAnalysis/Select Reason for no order'), 0)

Mobile.takeScreenshot()

println('Without any invoice "select reason for no order" field is displayed properly! ')

Sales = Mobile.getText(findTestObject('Mobile/CallAnalysis/Value-Sales'), 0, FailureHandling.STOP_ON_FAILURE)

String[] CallAna_Sale = Sales.split('/')

String CallAnalysisOrder = CallAna_Sale[0]

Mobile.verifyEqual(Double.parseDouble(CallAnalysisOrder), 0, FailureHandling.OPTIONAL)

Line = Mobile.getText(findTestObject('Mobile/CallAnalysis/Value-Total Lines'), 0, FailureHandling.STOP_ON_FAILURE)

Mobile.verifyEqual(Double.parseDouble(Line), 0, FailureHandling.OPTIONAL)

cases = Mobile.getText(findTestObject('Mobile/CallAnalysis/Volume Cases'), 0, FailureHandling.STOP_ON_FAILURE)

Mobile.verifyEqual(Double.parseDouble(cases), 0, FailureHandling.OPTIONAL)

piece = Mobile.getText(findTestObject('Mobile/CallAnalysis/Volume Pieces'), 0, FailureHandling.STOP_ON_FAILURE)

Mobile.verifyEqual(Double.parseDouble(piece), 0, FailureHandling.OPTIONAL)

TotalV = Mobile.getText(findTestObject('Mobile/CallAnalysis/Value-Total Value'), 0, FailureHandling.STOP_ON_FAILURE)

Mobile.verifyEqual(Double.parseDouble(TotalV), 0, FailureHandling.OPTIONAL)

AmountC = Mobile.getText(findTestObject('Mobile/CallAnalysis/Value-Collection Amount'), 0, FailureHandling.STOP_ON_FAILURE)

Mobile.verifyEqual(Double.parseDouble(AmountC), 0, FailureHandling.OPTIONAL)

F_Value = Mobile.getText(findTestObject('Mobile/CallAnalysis/Value-Focus Brand'), 0, FailureHandling.STOP_ON_FAILURE)

Mobile.verifyEqual(Double.parseDouble(F_Value), 0, FailureHandling.OPTIONAL)

M_Value = Mobile.getText(findTestObject('Mobile/CallAnalysis/Value-MSL Lines'), 0, FailureHandling.STOP_ON_FAILURE)

Mobile.verifyEqual(Double.parseDouble(M_Value), 0, FailureHandling.OPTIONAL)

Mobile.scrollToText('Sales Volume', FailureHandling.STOP_ON_FAILURE)

Sales_Vol = Mobile.getText(findTestObject('Mobile/CallAnalysis/Sales Volume'), 0, FailureHandling.STOP_ON_FAILURE)

Mobile.verifyEqual(Double.parseDouble(Sales_Vol), 0, FailureHandling.OPTIONAL)

SR_Vol = Mobile.getText(findTestObject('Mobile/CallAnalysis/Sales Return Volume'), 0, FailureHandling.STOP_ON_FAILURE)

Mobile.verifyEqual(Double.parseDouble(SR_Vol), 0, FailureHandling.OPTIONAL)

Mobile.scrollToText('Sales Return', FailureHandling.STOP_ON_FAILURE)

SR_Val = Mobile.getText(findTestObject('Mobile/CallAnalysis/Value-Sales Return'), 0, FailureHandling.STOP_ON_FAILURE)

Mobile.verifyEqual(Double.parseDouble(SR_Val), 0, FailureHandling.OPTIONAL)

Mobile.tap(findTestObject('Mobile/CallAnalysis/Close Call'), 0)

AppiumDriver driver = MobileDriverFactory.getDriver()

Actualtoastmessage = driver.findElementByXPath('//android.widget.Toast[1]').getText()

KeywordUtil.logInfo(Actualtoastmessage)

Mobile.verifyMatch(Actualtoastmessage, findTestData('Mobile Input Data/CallAnalysis').getValue('Reason_Alert', 1), false, FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

KeywordUtil.logInfo('Reason mandatory if no order taken')

Mobile.delay(2)

Mobile.tap(findTestObject('Mobile/CallAnalysis/Select Reason for no order'), 0)

Mobile.tap(findTestObject('Object Repository/Mobile/CallAnalysis/Others'), 0)

Mobile.verifyElementVisible(findTestObject('Mobile/CallAnalysis/Remarks'), 0)

Mobile.setText(findTestObject('Object Repository/Mobile/CallAnalysis/Remarks'), findTestData('Mobile Input Data/CallAnalysis').getValue('Reason', 1), 0, FailureHandling.STOP_ON_FAILURE)

Mobile.hideKeyboard()

Mobile.takeScreenshot()

KeywordUtil.logInfo('Should be able to enter reason if No Order is taken in the visit')

Mobile.tap(findTestObject('Mobile/CallAnalysis/Close Call'), 0)

Mobile.tap(findTestObject('Mobile/Common/Btn_OK'), 0)

//Mobile.delay(3)
//
//Mobile.tap(findTestObject('Mobile/Common/Btn_Menu'), 0)
//
//Mobile.tap(findTestObject('Mobile/MainMenu/Menu_Trade Coverage'), 0)
//
//Mobile.tap(findTestObject('Mobile/Common/Icon_Search'), 5, FailureHandling.OPTIONAL)
//
//Mobile.hideKeyboard(FailureHandling.OPTIONAL)
//
//Mobile.delay(3)
//
//Mobile.setText(findTestObject('Mobile/Common/Field_Search_EnterText'), GlobalVariable.RetailerName, 
//    5)
//
//Mobile.delay(1)
//
//Mobile.tap(findTestObject('Mobile/TradeCoverage/First_Retailer_Selection_From_List'), 5)
//
//Mobile.tap(findTestObject('Mobile/Common/Btn_StartVisit'), 5)
//
//if (Mobile.verifyElementVisible(findTestObject('Object Repository/Mobile/Common/Popup_Title_LocationValidation'), 
//    5, FailureHandling.OPTIONAL)) {
//    Mobile.tap(findTestObject('Mobile/Common/Btn_YES'), 5)
//}
//
//Mobile.delay(9)
//
//Mobile.swipe(0, 150, 0, 400, FailureHandling.OPTIONAL)
//
//Mobile.delay(1)
//
//Mobile.tap(findTestObject('Mobile/Store_Actvy/Menu-OrderInvoice'), 0, FailureHandling.STOP_ON_FAILURE)
//
//Mobile.delay(1)
WebUI.callTestCase(findTestCase('Product_Mobile/Common/Seller_Menu_Selection'), [('MenuName') : findTestData('Mobile Input Data/Common').getValue('Menu', 3), ('SubMenuName') : findTestData('Mobile Input Data/Common').getValue('Menu', 1)], FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('Product_Mobile/Common/TradeCoverage(SelectRetailer)'), [('RetailerName') : findTestData('Mobile Input Data/Invoice').getValue('Retailer_Name', 1)], FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('Product_Mobile/Common/StartVisit'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('Product_Mobile/Common/Store_Menu_Selection'), [('MenuName') : findTestData('Mobile Input Data/Common').getValue('Menu', 4), ('SubMenuName') : findTestData('Mobile Input Data/Common').getValue('Menu', 1)], FailureHandling.STOP_ON_FAILURE)

//mustsell order start
Mobile.tap(findTestObject('Mobile/Common/Icon_Star'), 0)

Mobile.tap(findTestObject('Mobile/Common/Filter_MustSell'), 2, FailureHandling.OPTIONAL)

Mobile.tap(findTestObject('Mobile/Common/Btn_Apply'), 0)

Mobile.delay(2, FailureHandling.STOP_ON_FAILURE)

Product_Count = Mobile.getText(findTestObject('Mobile/OrderInvoice/OrderInvoice_Title'), 0)

Total_MustSell_Product_Count = Product_Count.substring(11, 12)

println(Total_MustSell_Product_Count)

KeywordUtil.logInfo(Total_MustSell_Product_Count)

Mobile.tap(findTestObject('Mobile/Common/Icon_Search'), 4, FailureHandling.OPTIONAL)

MSL_Product = findTestData('Mobile Input Data/CallAnalysis').getValue('MSL_Product_Name', 1)

GlobalVariable.ProductName = MSL_Product

//Mobile.setText(findTestObject('Mobile/Common/Field_Search_EnterText'), GlobalVariable.ProductName, 
//    5)
//
//Mobile.delay(3, FailureHandling.STOP_ON_FAILURE)
Mobile.callTestCase(findTestCase('Product_Mobile/Common/Search_Product'), [('ProductName') : MSL_Product], FailureHandling.STOP_ON_FAILURE)

//GlobalVariable.Qty = findTestData('Mobile Input Data/Invoice').getValue('Keypad_Number', 1)
//
//println(GlobalVariable.Qty)
//
//Case_Qty = GlobalVariable.Qty
//
//Mobile.callTestCase(findTestCase('Product_Mobile/Common/Enter_CASE_Qty'), [:], FailureHandling.STOP_ON_FAILURE)
//
//Mobile.hideKeyboard(FailureHandling.OPTIONAL)
Mobile.callTestCase(findTestCase('Product_Mobile/Common/Enter_CASE_Qty'), [('Quantity') : findTestData('Mobile Input Data/Invoice').getValue('Keypad_Number', 1)], FailureHandling.STOP_ON_FAILURE)

MSL_Product_Total = Mobile.getText(findTestObject('Mobile/OrderInvoice/OrderInvoiceScrn-SkuTotal'), 10)

Product_Counts = Mobile.getText(findTestObject('Mobile/OrderInvoice/OrderInvoice_Title'), 0)

Entered_MustSell_Product_Count = Product_Counts.substring(11, 12)

KeywordUtil.logInfo(Entered_MustSell_Product_Count)

//Mobile.delay(3)
//
//Mobile.tap(findTestObject('Mobile/Common/Icon_Search'), 2, FailureHandling.OPTIONAL)
//
//Mobile.tap(findTestObject('Mobile/Common/Icon_X'), 2, FailureHandling.OPTIONAL)
//
//Mobile.tap(findTestObject('Mobile/Common/Icon_Star'), 0)
//
//Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/FilterScreen/FilterScreen-clear btn'), 2, FailureHandling.OPTIONAL)
//
//Mobile.delay(2)
WebUI.callTestCase(findTestCase('Product_Mobile/Common/Clear_Star_Filter_Default_Selection'), [:], FailureHandling.STOP_ON_FAILURE)

Mobile.tap(findTestObject('Mobile/Common/Icon_Star'), 1, FailureHandling.OPTIONAL)

Mobile.tap(findTestObject('Mobile/Common/Filter_FocusBrand'), 0)

Mobile.tap(findTestObject('Mobile/Common/Btn_Apply'), 0)

Mobile.delay(2, FailureHandling.STOP_ON_FAILURE)

Product_Count = Mobile.getText(findTestObject('Mobile/OrderInvoice/OrderInvoice_Title'), 0)

Total_FocusBrand_Product_Count = Product_Count.substring(13, 14)

KeywordUtil.logInfo(Total_FocusBrand_Product_Count)

Mobile.tap(findTestObject('Mobile/Common/Icon_Search'), 4, FailureHandling.OPTIONAL)

Focusbrand_product = findTestData('Mobile Input Data/CallAnalysis').getValue('FB_Product_Name', 1)

GlobalVariable.ProductName = Focusbrand_product

Mobile.setText(findTestObject('Mobile/Common/Field_Search_EnterText'), GlobalVariable.ProductName, 5)

Mobile.delay(2, FailureHandling.STOP_ON_FAILURE)

GlobalVariable.Qty = findTestData('Mobile Input Data/Invoice').getValue('Keypad_Number', 1)

println(GlobalVariable.Qty)

Piece_Qty = GlobalVariable.Qty

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Enter_PIECE_Qty'), [:], FailureHandling.STOP_ON_FAILURE)

Mobile.hideKeyboard(FailureHandling.OPTIONAL)

Focusbrand_product_Total = Mobile.getText(findTestObject('Mobile/OrderInvoice/OrderInvoiceScrn-SkuTotal'), 10)

Product_Counts = Mobile.getText(findTestObject('Mobile/OrderInvoice/OrderInvoice_Title'), 0)

Entered_focusbrand_Product_Count = Product_Counts.substring(13, 14)

KeywordUtil.logInfo(Entered_focusbrand_Product_Count)

Mobile.tap(findTestObject('Mobile/Common/Btn_Next'), 0)

Mobile.callTestCase(findTestCase('Product_Mobile/Common/MustSell_Alert'), [:], FailureHandling.STOP_ON_FAILURE)

Mobile.verifyElementVisible(findTestObject('Mobile/Common/Btn_Next'), 10, FailureHandling.OPTIONAL)

Mobile.tap(findTestObject('Mobile/Common/Btn_Next'), 0, FailureHandling.OPTIONAL)

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Handle_Empties_Screen'), [:], FailureHandling.OPTIONAL)

'***********************************************************************************************************************'
total_Value = Mobile.getText(findTestObject('Mobile/SummaryScreen/SummaryScreen-ValueAmt'), 0)

Mobile.tap(findTestObject('Mobile/SummaryScreen/SummaryScreen-Order btn'), 0)

WebUI.callTestCase(findTestCase('Product_Mobile/Common/DatePicker_and _Navigation/Mobile Date Picker - Current Date'), [:], FailureHandling.STOP_ON_FAILURE)

Mobile.tap(findTestObject('Mobile/Common/Btn_Confirm'), 0)

Mobile.delay(2, FailureHandling.STOP_ON_FAILURE)

id = Mobile.getText(findTestObject('Mobile/SummaryScreen/Click Order-OrderSavedID-Title'), 0)

Ord_ID = id.replaceAll('[Order Saved. Order ID is:\']', '')

invoice_ID = Ord_ID.replaceAll('[\']', '')

KeywordUtil.logInfo(invoice_ID)

Mobile.tap(findTestObject('Mobile/SummaryScreen/Click Order-PRINT ORDER btn'), 0)

Mobile.delay(2)

Mobile.tap(findTestObject('Mobile/Common/Icon_Back'), 0)

Mobile.tap(findTestObject('Mobile/Common/Btn_OK'), 2, FailureHandling.OPTIONAL)

Mobile.tap(findTestObject('Mobile/Common/Icon_Back'), 2, FailureHandling.OPTIONAL)

Mobile.delay(3)

Mobile.swipe(0, 150, 0, 400, FailureHandling.OPTIONAL)

Mobile.tap(findTestObject('Mobile/Store_Actvy/Menu-OrderInvoice'), 0, FailureHandling.STOP_ON_FAILURE)

Mobile.delay(2)

WebUI.callTestCase(findTestCase('Product_Mobile/Common/OrderPopUp(EditOrder)'), [:], FailureHandling.STOP_ON_FAILURE)

Mobile.tap(findTestObject('Mobile/Common/Icon_Back'), 0, FailureHandling.OPTIONAL)

Mobile.delay(3)

Mobile.swipe(0, 150, 0, 400, FailureHandling.OPTIONAL)

Mobile.tap(findTestObject('Mobile/CallAnalysis/StoreActy_Close Call Btn'), 0)

Mobile.delay(2)

CallAnalysis_Cases = Mobile.getText(findTestObject('Mobile/CallAnalysis/Volume Cases'), 0)

Mobile.verifyEqual(Integer.parseInt(CallAnalysis_Cases), Case_Qty, FailureHandling.STOP_ON_FAILURE)

CallAnalysis_Pieces = Mobile.getText(findTestObject('Mobile/CallAnalysis/Volume Pieces'), 0)

Mobile.verifyEqual(Integer.parseInt(CallAnalysis_Pieces), Piece_Qty, FailureHandling.STOP_ON_FAILURE)

CallAnalysis_SIH = Mobile.getText(findTestObject('Mobile/CallAnalysis/Sales Volume'), 0)

Expected_SIH = ((Integer.parseInt(Case_Qty) * 10) + Integer.parseInt(Piece_Qty))

Mobile.verifyEqual(Integer.parseInt(CallAnalysis_Pieces), Expected_SIH, FailureHandling.STOP_ON_FAILURE)

//totalLine
Total_No_Product_SummaryScrn = (Integer.parseInt(Entered_MustSell_Product_Count) + Integer.parseInt(Entered_focusbrand_Product_Count))

KeywordUtil.logInfo(Total_No_Product_SummaryScrn.toString())

Mobile.takeScreenshot()

CallAnalysis_TotalLines = Mobile.getText(findTestObject('Mobile/CallAnalysis/Value-Total Lines'), 0)

Mobile.verifyEqual(Integer.parseInt(CallAnalysis_TotalLines), Total_No_Product_SummaryScrn, FailureHandling.STOP_ON_FAILURE)

KeywordUtil.logInfo((CallAnalysis_TotalLines.toString() + '  : is EQUAL to :   ') + Total_No_Product_SummaryScrn.toString())

Mobile.takeScreenshot()

println('Total Lines verified properly')

//SalesOrder
CallAnalysis_SaleOrder = Mobile.getText(findTestObject('Mobile/CallAnalysis/Value-Sales'), 0)

String[] CallAnalysisSale = CallAnalysis_SaleOrder.split('/')

String CallAnalysisSaleOrder = CallAnalysisSale[0]

Mobile.verifyEqual(Double.parseDouble(CallAnalysisSaleOrder), Double.parseDouble(total_Value), FailureHandling.STOP_ON_FAILURE)

KeywordUtil.logInfo((CallAnalysis_SaleOrder + '  : is EQUAL to :  ') + total_Value)

Mobile.takeScreenshot()

println('Sales Value verified properly')

//TotalValue
CallAnalysis_TotalValue = Mobile.getText(findTestObject('Mobile/CallAnalysis/Value-Total Value'), 0)

GlobalVariable.TotalValue = CallAnalysis_TotalValue

Mobile.verifyEqual(Double.parseDouble(CallAnalysis_TotalValue), Double.parseDouble(total_Value), FailureHandling.STOP_ON_FAILURE)

KeywordUtil.logInfo((CallAnalysis_TotalValue + '  : is EQUAL to :  ') + total_Value)

Mobile.takeScreenshot()

println('Total Value verified properly')

//MustSell Value
MSL_Value = Mobile.getText(findTestObject('Mobile/CallAnalysis/Value-MSL Lines'), 0)

Mobile.verifyEqual(Double.parseDouble(MSL_Value), Double.parseDouble(Entered_MustSell_Product_Count), FailureHandling.STOP_ON_FAILURE)

KeywordUtil.logInfo((MSL_Value + '  : is EQUAL to :  ') + Entered_MustSell_Product_Count)

Mobile.takeScreenshot()

println('Must Sell value verified proprly')

// must sell Total Value
MSL_TotalValue = Mobile.getText(findTestObject('Mobile/CallAnalysis/TotalValue-MSL Lines'), 0)

MSL_Total_Value = MSL_TotalValue.replaceAll('[/]', '')

KeywordUtil.logInfo(MSL_Total_Value)

Mobile.verifyEqual(Double.parseDouble(MSL_Total_Value), Double.parseDouble(Total_MustSell_Product_Count), FailureHandling.STOP_ON_FAILURE)

KeywordUtil.logInfo((MSL_Total_Value + '  : is EQUAL to :  ') + Total_MustSell_Product_Count)

Mobile.takeScreenshot()

println('MSL Total Value verified properly')

//% of Must Sell
Perc_of_MSL = ((Double.parseDouble(Entered_MustSell_Product_Count) / Double.parseDouble(Total_MustSell_Product_Count)) * 100)

KeywordUtil.logInfo(Perc_of_MSL.toString())

PercValue_MSL = Mobile.getText(findTestObject('Mobile/CallAnalysis/PercVale-MSL Lines'), 0)

Perc_Value_MSL = PercValue_MSL.replaceAll('[% Achieved]', '')

KeywordUtil.logInfo(Perc_Value_MSL)

Mobile.verifyEqual(Double.parseDouble(Perc_Value_MSL), Perc_of_MSL, FailureHandling.STOP_ON_FAILURE)

KeywordUtil.logInfo((Perc_Value_MSL.toString() + '  : is EQUAL to :  ') + Perc_of_MSL.toString())

Mobile.takeScreenshot()

println('Percentage of MSL verified properly')

//////////
//FocusBrand Value
FocusBrand_Value = Mobile.getText(findTestObject('Mobile/CallAnalysis/Value-MSL Lines'), 0)

Mobile.verifyEqual(Double.parseDouble(FocusBrand_Value), Double.parseDouble(Entered_focusbrand_Product_Count), FailureHandling.STOP_ON_FAILURE)

KeywordUtil.logInfo((FocusBrand_Value + '  : is EQUAL to :  ') + Entered_focusbrand_Product_Count)

Mobile.takeScreenshot()

println('FocusBrand value verified proprly')

// FocusBrand Total Value
FocusBrand_TotalValue = Mobile.getText(findTestObject('Mobile/CallAnalysis/TotalValue-MSL Lines'), 0)

FocusBrand_Total_Value = FocusBrand_TotalValue.replaceAll('[/]', '')

KeywordUtil.logInfo(FocusBrand_Total_Value)

Mobile.verifyEqual(Double.parseDouble(FocusBrand_Total_Value), Double.parseDouble(Total_FocusBrand_Product_Count), FailureHandling.STOP_ON_FAILURE)

KeywordUtil.logInfo((FocusBrand_Total_Value + '  : is EQUAL to :  ') + Total_FocusBrand_Product_Count)

Mobile.takeScreenshot()

println('FocusBrand Total Value verified properly')

//% of Focus brand
Perc_of_FocusBrand = ((Double.parseDouble(Entered_focusbrand_Product_Count) / Double.parseDouble(Total_FocusBrand_Product_Count)) * 100)

KeywordUtil.logInfo(Perc_of_FocusBrand.toString())

PercValue_FocusBrand = Mobile.getText(findTestObject('Mobile/CallAnalysis/PercVale-MSL Lines'), 0)

Perc_Value_FocusBrand = PercValue_FocusBrand.replaceAll('[% Achieved]', '')

KeywordUtil.logInfo(Perc_Value_FocusBrand)

Mobile.verifyEqual(Double.parseDouble(Perc_Value_FocusBrand), Perc_of_FocusBrand, FailureHandling.STOP_ON_FAILURE)

KeywordUtil.logInfo((Perc_Value_FocusBrand + '  : is EQUAL to :  ') + Perc_of_FocusBrand.toString())

Mobile.takeScreenshot()

println('Percentage of FocusBrand verified properly')

Mobile.tap(findTestObject('Mobile/Common/Icon_Back'), 0, FailureHandling.OPTIONAL)

Mobile.delay(3)

Mobile.swipe(0, 150, 0, 400, FailureHandling.OPTIONAL)

