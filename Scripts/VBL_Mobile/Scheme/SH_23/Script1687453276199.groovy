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
import com.sun.net.httpserver.Authenticator.Failure as Failure
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.chrome.ChromeDriver as ChromeDriver
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import com.kms.katalon.keyword.excel.ExcelKeywords as ExcelKeywords
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import org.junit.Assert as Assert

//Mobile.startApplication(GlobalVariable.APK_File, false)
//
//Mobile.callTestCase(findTestCase('VBL_Mobile/Re-Usables/Select_TradeCoverage_Menu'), [:], FailureHandling.STOP_ON_FAILURE)

String sheet_name = 'Fixed_Variable_Scheme'

String file_name = 'Mobile Input data'

ArrayList<String> Retailer = CustomKeywords.'poi.VBL.Automation.GetAllDataRow_Flag.getAllDataRow'(file_name, sheet_name, 'Retailer')

List<Double> BUY_SKU01_qty = new ArrayList<String>()

for (int i = 0; i < Retailer.size(); i++) {
    if (Retailer.get(i) != 'null') {
        Scheme_Index = 1

        Retailer_Index = (i + 1)

        RetailerApplyType = findTestData('VBL_Mobile Input Data/Fixed_Variable_Scheme').getValue('RetailerApplyType', Retailer_Index)

        KeywordUtil.logInfo(RetailerApplyType)

        SchemeAppyType = findTestData('VBL_Mobile Input Data/Fixed_Variable_Scheme').getValue('SchemeAppyType', Scheme_Index)

        KeywordUtil.logInfo(SchemeAppyType)

        if (SchemeAppyType == GlobalVariable.FixedScheme) {
            KeywordUtil.logInfo('This scripts can validate fixed Scheme')
        } else {
            KeywordUtil.logInfo('This scripts only validation for fixed Scheme')

            Assert.fail()
        }
        
       // Mobile.tap(findTestObject('XXXXXXXXXXXX/Re-usable/Mobile Login/Menu'), 10)
		
		//Mobile.callTestCase(findTestCase('VBL_Mobile/Re-Usables/Select_TradeCoverage_Menu'), [:], FailureHandling.STOP_ON_FAILURE)

        WebUI.callTestCase(findTestCase('VBL_Mobile/Re-Usables/Select_Retailer and Going_OrderInvoice_Screen'), [('RetailerName') : findTestData('VBL_Mobile Input Data/Fixed_Variable_Scheme').getValue('Retailer', Retailer_Index)], FailureHandling.STOP_ON_FAILURE)


        //slab_1
        'Slab 1'
        GlobalVariable.ProductName = findTestData('VBL_Mobile Input Data/Fixed_Variable_Scheme').getValue('BuyProduct1', Scheme_Index)

        Mobile.setText(findTestObject('XXXXXXXXXXXX/Mobile Part/Reusable Object/Product Enter Search'), GlobalVariable.ProductName, 5)

        Mobile.delay(2, FailureHandling.STOP_ON_FAILURE)

        Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/ProductName-(Global)'), 5, FailureHandling.STOP_ON_FAILURE)

        Mobile.verifyElementVisible(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/Tab-Product Details'), 5)

        Mobile.takeScreenshot()

        Actual_BasePrice = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/ProductDetails/BasePrice_Value_Field'), 0)

        Actual_PiecePrice = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/ProductDetails/PiecePrice_Value_Field'), 0)

        Actual_CaseSize = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/ProductDetails/CaseSize_Value_Field'), 0)

		GlobalVariable.CaseSize = Actual_CaseSize
		
        Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/Case_Field'), 0)

        GlobalVariable.keypadValue = findTestData('VBL_Mobile Input Data/Fixed_Variable_Scheme').getValue('Slab_1_Min_Qty', Scheme_Index)

        Slab_1_Min_Qty = findTestData('VBL_Mobile Input Data/Fixed_Variable_Scheme').getValue('Slab_1_Min_Qty', Scheme_Index)

		BUY_SKU01_qty.add(Integer.parseInt(Slab_1_Min_Qty))
		
        String Slab_Qty = Integer.parseInt(Slab_1_Min_Qty)

        length = Slab_Qty.size()

        if (2 == length) {
            GlobalVariable.keypadValue = Slab_Qty.charAt(0)

            Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Reusable Object/Box-Keypad/Global-number_keypad'), 5)

            GlobalVariable.keypadValue = Slab_Qty.charAt(1)

            Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Reusable Object/Box-Keypad/Global-number_keypad'), 5)
        } else {
            GlobalVariable.keypadValue = Slab_Qty

            Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Reusable Object/Box-Keypad/Global-number_keypad'), 5)
        }
        
        Mobile.tap(findTestObject('Mobile/Common/Btn_OK'), 5)

        Mobile.tap(findTestObject('Mobile/Common/Icon_Back'), 0)

        Mobile.delay(2, FailureHandling.STOP_ON_FAILURE)

        Mobile.takeScreenshot()

        SKU_TOTAL = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/OrderInvoiceScrn-SkuTotal'), 0)

        Total = ((Double.parseDouble(Actual_CaseSize) * Double.parseDouble(Slab_1_Min_Qty)) * Double.parseDouble(Actual_PiecePrice))

        Mobile.verifyEqual(Double.parseDouble(SKU_TOTAL), Total, FailureHandling.STOP_ON_FAILURE)

        Mobile.takeScreenshot()

        KeywordUtil.logInfo(Total.toString() + ': Slab_1 Sku Total calculated and displayed correctly according the formula.')

        Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/Next Btn-OrderInvoiceScreen'), 0)

        WebUI.callTestCase(findTestCase('VBL_Mobile/Re-Usables/MustSell_Alert'), [:], FailureHandling.STOP_ON_FAILURE)

        'Entering scheme validation'
        if (((RetailerApplyType == GlobalVariable.Fixed_DD) || (RetailerApplyType == GlobalVariable.Fixed_HT)) || (RetailerApplyType == GlobalVariable.Fixed)) {
            'Scheme Should be applied'
            Mobile.takeScreenshot()

            Mobile.verifyElementVisible(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/Scheme/SchemeName'), 15)

            SchemeName = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/Scheme/SchemeName'), 0)

            SchemeDesc = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/Scheme/SchemeDesc'), 0)

            DiscountPerc = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/Scheme/DiscountPerc'), 0)

            Mobile.verifyMatch(SchemeName, findTestData('VBL_Mobile Input Data/Fixed_Variable_Scheme').getValue('SchemeName', Scheme_Index), false, FailureHandling.STOP_ON_FAILURE)

            Mobile.takeScreenshot()

            KeywordUtil.logInfo(SchemeName + ' : Slab_1 Scheme Name correctly applied !')

            Mobile.verifyMatch(SchemeDesc, findTestData('VBL_Mobile Input Data/Fixed_Variable_Scheme').getValue('SchemeDescSlab1', Scheme_Index), false, FailureHandling.STOP_ON_FAILURE)

            Mobile.takeScreenshot()

            KeywordUtil.logInfo(SchemeDesc + ' : Slab_1 Scheme Description correctly displayed !')

            Discount_Perc_Sheet = findTestData('VBL_Mobile Input Data/Fixed_Variable_Scheme').getValue('DiscountPercSlab1', Scheme_Index)

            Mobile.verifyEqual(Double.parseDouble(DiscountPerc), Double.parseDouble(Discount_Perc_Sheet), FailureHandling.STOP_ON_FAILURE)

            Mobile.takeScreenshot()

            KeywordUtil.logInfo(DiscountPerc + ' : Slab_1 Discount percentage correctly applied !')

           Mobile.callTestCase(findTestCase('VBL_Mobile/Re-Usables/Tap_Next_Btn_ApplyschemeScrn_CT'), [:], FailureHandling.STOP_ON_FAILURE)

            Mobile.takeScreenshot()

            Mobile.verifyElementVisible(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/SummaryScreen/SummaryScreen-InfoIcon'), 15)

            Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/SummaryScreen/SummaryScreen-InfoIcon'), 0)

            Mobile.takeScreenshot()

            OrderAmt = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/SummaryScreen/SplitScreen-OrderAmt'), 0)

            Mobile.verifyEqual(Double.parseDouble(OrderAmt), Total, FailureHandling.STOP_ON_FAILURE)

            Mobile.takeScreenshot()

            KeywordUtil.logInfo(Total.toString() + ' : Slab_1 Calculated order amt correctly displayed !')

            Scheme_Amt = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/SummaryScreen/SplitScreen_SCHEME Amt'), 0)

            SchemeAmt = Scheme_Amt.replaceAll('- ', '')

            KeywordUtil.logInfo(SchemeAmt)

            CGST = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/SummaryScreen/SplitScreen-CGST Value'), 0)

            TotalAmt1 = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/SummaryScreen/SliptScreen_TotalAmt'), 0)

            TotalAmt = TotalAmt1.replaceAll(',', '')

            Actual_MRP = findTestData('VBL_Mobile Input Data/Fixed_Variable_Scheme').getValue('BuyProduct1_MRP', Scheme_Index)

            Total_MRP = ((Double.parseDouble(Actual_CaseSize) * Double.parseDouble(Slab_1_Min_Qty)) * Double.parseDouble(Actual_MRP))

            Calculated_Sch_Amt = ((Total_MRP * Double.parseDouble(Discount_Perc_Sheet)) / 100)

            KeywordUtil.logInfo(Calculated_Sch_Amt.toString() + ' : Scheme ammount calculated by MRP')

            Mobile.verifyEqual(Double.parseDouble(SchemeAmt), Calculated_Sch_Amt, FailureHandling.STOP_ON_FAILURE)

            Mobile.takeScreenshot()

            KeywordUtil.logInfo(Calculated_Sch_Amt.toString() + ' : Slab_1 Calculated Scheme Amount correctly displayed !')

            Calculated_TotalAmt = ((Double.parseDouble(OrderAmt) + Double.parseDouble(CGST)) - Calculated_Sch_Amt)

            Mobile.verifyEqual(Double.parseDouble(TotalAmt), Calculated_TotalAmt, FailureHandling.STOP_ON_FAILURE)

            Mobile.takeScreenshot()

            KeywordUtil.logInfo(Calculated_TotalAmt.toString() + ' : Slab_1 Total amount in split screen displayed correctly !')
        } else if (RetailerApplyType == GlobalVariable.Variable) {
            'Scheme should not be applied'
            Mobile.takeScreenshot()

            Mobile.verifyElementNotVisible(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/Scheme/SchemeName'), 5)

            KeywordUtil.logInfo('Scheme not apllied for this particular product')

            Mobile.callTestCase(findTestCase('VBL_Mobile/Re-Usables/Tap_Next_Btn_ApplyschemeScrn_CT'), [:], FailureHandling.STOP_ON_FAILURE)

            Mobile.takeScreenshot()

            Mobile.verifyElementVisible(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/SummaryScreen/SummaryScreen-InfoIcon'), 15)

            Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/SummaryScreen/SummaryScreen-InfoIcon'), 0)

            Mobile.takeScreenshot()

            Scheme_Amt = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/SummaryScreen/SplitScreen_SCHEME Amt'), 0)

            SchemeAmt = Scheme_Amt.replaceAll('- ', '')

            KeywordUtil.logInfo(SchemeAmt)

            Mobile.verifyMatch(SchemeAmt, '0', false, FailureHandling.STOP_ON_FAILURE)

            Mobile.takeScreenshot()

            KeywordUtil.logInfo('Scheme value is :' + SchemeAmt)
        } else {
            'Scheme details mismatched or scripts need to update'
            Mobile.takeScreenshot()

            Assert.fail()
        }
        
        Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/SummaryScreen/SummaryScreen-info_closeBtn'), 0)

        

		Mobile.callTestCase(findTestCase('Product_Mobile/Common/Generate_OrderInvoice and CloseCall'), [:], FailureHandling.STOP_ON_FAILURE)
    }
}

'Stock reduce validation for scheme'

int sum_1 = 0

for (int j = 0; j < BUY_SKU01_qty.size(); j++) {
	sum_1 += BUY_SKU01_qty.get(j)

	println('sum1:' + sum_1)

}

KeywordUtil.logInfo(sum_1.toString())

//SKU_Conversion_Qty = findTestData('VBL_Mobile Input Data/Scheme_Stock_Validation').getValue('SKU_Conversion_Qty', 1)

SIH_BUYandGET_SKU01 = (sum_1 * Integer.parseInt(GlobalVariable.CaseSize))

exlpath = 'DDF\\VBL\\Mobile Input data\\Mobile Input data.xlsx'

Sheetname = 'Scheme_Stock_Validation'

workbook01 = ExcelKeywords.getWorkbook(exlpath)

sheet1 = ExcelKeywords.getExcelSheet(workbook01, Sheetname)

ExcelKeywords.setValueToCellByIndex(sheet1, 1, 5, SIH_BUYandGET_SKU01)

//ExcelKeywords.setValueToCellByIndex(sheet1, 1, 3, GlobalVariable.CaseSize)

ExcelKeywords.saveWorkbook(exlpath, workbook01)