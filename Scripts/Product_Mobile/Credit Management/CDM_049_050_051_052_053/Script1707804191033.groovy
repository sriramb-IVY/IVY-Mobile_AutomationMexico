import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import internal.GlobalVariable as GlobalVariable
import java.awt.Robot as Robot
import java.awt.event.KeyEvent as KeyEvent
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import org.apache.commons.lang.RandomStringUtils as RandomStringUtils
import com.kms.katalon.core.mobile.keyword.internal.MobileDriverFactory as MobileDriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import io.appium.java_client.AppiumDriver as AppiumDriver
import java.sql.Connection as Connection
import java.sql.DriverManager as DriverManager
import java.sql.ResultSet as ResultSet
import java.sql.Statement as Statement
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.keyword.excel.ExcelKeywords as ExcelKeywords

Mobile.startApplication(GlobalVariable.APKFile, false)

String sheet_name = 'Credit_Limit'

String file_name = 'Mobile Input data'

ArrayList<String> Channelwise_Retailer = CustomKeywords.'poi.Automation.GetAllDataRow_Flag.getAllDataRow'(file_name, sheet_name, 
    'Channelwise_Retailer')

ArrayList<String> Credit_Validation = CustomKeywords.'poi.Automation.GetAllDataRow_Flag.getAllDataRow'(file_name, sheet_name, 
    'Credit_Validation')

for (int i = 0; i < Channelwise_Retailer.size(); i++) {
    Scheme_Index = (i + 1)

    Mobile.tap(findTestObject('Mobile/Common/Btn_Menu'), 10)

    WebUI.callTestCase(findTestCase('Product_Mobile/Common/TradeCoverage(SelectRetailer)'), [('RetailerName') : findTestData('Mobile Input Data/Limit_Validations').getValue(
                'Channelwise_Retailer', Scheme_Index)], FailureHandling.STOP_ON_FAILURE)

    Mobile.tap(findTestObject('Mobile/Common/Icon_Search'), 4, FailureHandling.OPTIONAL)

    //slab_1
    'Slab_1'
    GlobalVariable.ProductName = findTestData('Mobile Input Data/Limit_Validations').getValue('Product_Name', Scheme_Index)

    Mobile.setText(findTestObject('Mobile/Common/Field_Search_EnterText'), GlobalVariable.ProductName, 
        5)

    Mobile.delay(5, FailureHandling.STOP_ON_FAILURE)

    Mobile.tap(findTestObject('Mobile/OrderInvoice/ProductName-(Global)'), 5, FailureHandling.OPTIONAL)

    Mobile.verifyElementVisible(findTestObject('Mobile/OrderInvoice/Tab-Product Details'), 
        5)

    Mobile.takeScreenshot()

    Actual_BasePrice = Mobile.getText(findTestObject('Mobile/OrderInvoice/ProductDetails/BasePrice_Value_Field'), 
        0)

    Actual_PiecePrice = Mobile.getText(findTestObject('Mobile/OrderInvoice/ProductDetails/PiecePrice_Value_Field'), 
        0)

    Piece_Price = Double.parseDouble(Actual_PiecePrice)

    KeywordUtil.logInfo('Price of the product' + Piece_Price)

    Actual_CaseSize = Mobile.getText(findTestObject('Mobile/OrderInvoice/ProductDetails/CaseSize_Value_Field'), 
        0)

    Mobile.tap(findTestObject('Mobile/OrderInvoice/Qty_Field'), 0)

    Tot_value = findTestData('Mobile Input Data/Limit_Validations').getValue('Sales_Value', Scheme_Index)

    Sales_Value = Double.parseDouble(Tot_value)

    'Entering Slab1 Piece qty'
    int Quantity = Sales_Value / Piece_Price

    qty = Quantity.toString()

    Buy_Qty = Double.parseDouble(qty)

    GlobalVariable.Qty = Quantity.toString()

    KeywordUtil.logInfo('Piece quantity to given' + GlobalVariable.Qty)

    Mobile.callTestCase(findTestCase('Product_Mobile/Common/Enter_PIECE_Qty'), [:], FailureHandling.STOP_ON_FAILURE)

    Mobile.takeScreenshot()

    Mobile.tap(findTestObject('Mobile/Common/Icon_Back'), 0)

    Mobile.delay(2, FailureHandling.STOP_ON_FAILURE)

    SKU_TOTAL = Mobile.getText(findTestObject('Mobile/OrderInvoice/OrderInvoiceScrn-SkuTotal'), 
        0)

    KeywordUtil.logInfo('SKU Total Value ' + SKU_TOTAL)

    Total = (Double.parseDouble(GlobalVariable.Qty) * Piece_Price)

    Mobile.verifyEqual(Double.parseDouble(SKU_TOTAL), Total, FailureHandling.STOP_ON_FAILURE)

    Mobile.takeScreenshot()

    KeywordUtil.logInfo(Total.toString() + ' : Slab Total Amount calculated and displayed correctly according the formula.')

    Mobile.tap(findTestObject('Mobile/Common/Btn_Next'), 0)

    WebUI.callTestCase(findTestCase('Product_Mobile/Common/MustSell_Alert'), [:], FailureHandling.STOP_ON_FAILURE)

    Mobile.takeScreenshot()

    Mobile.delay(2)

    Mobile.tap(findTestObject('Mobile/Common/Btn_Next'), 0)

    Mobile.tap(findTestObject('Mobile/SummaryScreen/SummaryScreen-Invoice btn'), 0)

    Mobile.takeScreenshot()

    if ((i == 0) || (i == 1) || (i == 3)) {
        //if ((Credit_Validation.get(i) == 'Infinite Limit & Warn') || (Credit_Validation.get(i) == 'Less than Credit & Warn limit')) {
        Mobile.tap(findTestObject('Mobile/Common/Btn_Confirm'), 0)

        Mobile.delay(2)

        Invoice_submitted_popup = Mobile.getText(findTestObject('Object Repository/Mobile/Credit Management/InvoiceSubmittedSuccessfully_Popup'), 
            2, FailureHandling.STOP_ON_FAILURE)

        KeywordUtil.logInfo(Invoice_submitted_popup)

        Mobile.verifyMatch(Invoice_submitted_popup, findTestData('Mobile Input Data/Limit_Validations').getValue('Alert_Popup', 
                Scheme_Index), false)

        KeywordUtil.logInfo('Invoice should be generated, until the Credit & Warn limit, Credit & Warn Bill achieved for the retailer ')

        KeywordUtil.logInfo('Invoice should be generated, if the retailer has infinite Credit & Warn limit, Credit & Warn Bill ')

        Mobile.takeScreenshot()

        Mobile.tap(findTestObject('Mobile/Common/Btn_OK'), 0)

        Mobile.takeScreenshot()

        KeywordUtil.logInfo('Invoice should be generated, when credit & warn limit is Infinite AND Credit&Warn limit is less than the Order Value')

        WebUI.callTestCase(findTestCase('Product_Mobile/Common/Call_Analysis'), [:], FailureHandling.STOP_ON_FAILURE)
    } else if (i == 2)  {
        AppiumDriver driver = MobileDriverFactory.getDriver()

        Warning_message_popup = driver.findElementByXPath('//android.widget.Toast[1]').getText()

        KeywordUtil.logInfo(Warning_message_popup)

        Mobile.verifyMatch(Warning_message_popup, findTestData('Mobile Input Data/Limit_Validations').getValue('Alert_Popup', 
                Scheme_Index), false)

        Mobile.takeScreenshot()

        KeywordUtil.logInfo('Amount exceeds warning Credit Balance soft alert should be displayed when credit bal exceeds warn credit limit ')

        Mobile.tap(findTestObject('Mobile/Common/Btn_Confirm'), 0)

        Mobile.delay(2)

        Mobile.tap(findTestObject('Mobile/Common/Btn_OK'), 0)

        Mobile.takeScreenshot()

		if (i == 2){
			WebUI.callTestCase(findTestCase('Product_Mobile/Credit Management/Call Test Cases/CDM_CT_01'), [:], FailureHandling.STOP_ON_FAILURE)
		
		}
        WebUI.callTestCase(findTestCase('Product_Mobile/Common/Call_Analysis'), [:], FailureHandling.STOP_ON_FAILURE)
    } else if (i == 4) {
        GlobalVariable.Alert_message = findTestData('Mobile Input Data/Limit_Validations').getValue('Alert_Popup', Scheme_Index)

        Mobile.verifyElementVisible(findTestObject('Object Repository/Mobile/Credit Management/Alert_Popup_global'), 
            5)

        limit_exceed_alert_popup = Mobile.getText(findTestObject('Object Repository/Mobile/Credit Management/Alert_Popup_global'), 
            2, FailureHandling.STOP_ON_FAILURE)

        KeywordUtil.logInfo(limit_exceed_alert_popup)

        Mobile.verifyMatch(limit_exceed_alert_popup, findTestData('Mobile Input Data/Limit_Validations').getValue('Alert_Popup', 
                Scheme_Index), false)

        Mobile.takeScreenshot()

        KeywordUtil.logInfo('Credit Balance not available hard alert should be displayed when credit bal exceeds credit limit ')

        Mobile.tap(findTestObject('Mobile/Common/Btn_OK'), 0)

        Mobile.tap(findTestObject('Mobile/Common/Icon_Back'), 1, FailureHandling.OPTIONAL)

        Mobile.delay(2)

        Mobile.tap(findTestObject('Mobile/Common/Icon_Back'), 1, FailureHandling.OPTIONAL)

        Mobile.delay(2)

        Mobile.tap(findTestObject('Mobile/Common/Icon_Back'), 1, FailureHandling.OPTIONAL)

        Mobile.delay(2)

        Mobile.tap(findTestObject('Mobile/Common/Btn_OK'), 0)

        WebUI.callTestCase(findTestCase('Product_Mobile/Common/Call_Analysis'), [:], FailureHandling.STOP_ON_FAILURE)
    }
}



