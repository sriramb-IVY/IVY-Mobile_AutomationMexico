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
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.keyword.excel.ExcelKeywords as ExcelKeywords
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint

not_run: Mobile.startApplication(GlobalVariable.APK_File, false)

not_run: Mobile.tap(findTestObject('XXXXXXXXXXXX/Re-usable/Mobile Login/Menu'), 10)

//
Mobile.callTestCase(findTestCase('VBL_Mobile/Re-Usables/Select_TradeCoverage_Menu'), [:], FailureHandling.STOP_ON_FAILURE)

Scheme_Index = 2

SchemeAppyNo = findTestData('Mobile Input Data/RetailerTypeCreditNote_Scheme').getValue('SchemeAppyNo', Scheme_Index)

Expected_SchemeAppyNo = findTestData('Mobile Input Data/RetailerTypeCreditNote_Scheme').getValue('Expected_SchemeAppyNo', Scheme_Index)

if (Mobile.verifyMatch(SchemeAppyNo, Expected_SchemeAppyNo, false, FailureHandling.STOP_ON_FAILURE)) {
    KeywordUtil.logInfo('For this Retailer "SchemeAppyNo" and "Expected_SchemeAppyNo" was Equal as credit, hence we can validate scheme !')

    // Mobile.tap(findTestObject('XXXXXXXXXXXX/Re-usable/Mobile Login/Menu'), 10)
    //ebUI.callTestCase(findTestCase('VBL_Mobile/Re-Usables/Select_Retailer and Going_OrderInvoice_Screen'), [('RetailerName') : findTestData('Mobile Input Data/RetailerTypeCreditNote_Scheme').getValue('Retailer', Scheme_Index)], FailureHandling.STOP_ON_FAILURE)
    WebUI.callTestCase(findTestCase('Product_Mobile/Common/TradeCoverage(SelectRetailer)'), [('RetailerName') : findTestData('Mobile Input Data/RetailerTypeCreditNote_Scheme').getValue('Retailer', Scheme_Index)], FailureHandling.STOP_ON_FAILURE)

    WebUI.callTestCase(findTestCase('Product_Mobile/Common/StartVisit'), [:], FailureHandling.STOP_ON_FAILURE)

    WebUI.callTestCase(findTestCase('Product_Mobile/Common/Store_Menu_Selection'), [('MenuName') : findTestData('Mobile Input Data/Common').getValue('Menu', 4), ('SubMenuName') : findTestData('Mobile Input Data/Common').getValue('Menu', 1)], FailureHandling.STOP_ON_FAILURE)

    Mobile.callTestCase(findTestCase('Product_Mobile/Common/Clear_Star_Filter_Default_Selection'), [:], FailureHandling.STOP_ON_FAILURE)

    'Slab 1'

    'AND Logic'
    GlobalVariable.ProductName = findTestData('Mobile Input Data/RetailerTypeCreditNote_Scheme').getValue('BuyProduct1', Scheme_Index)

    Mobile.setText(findTestObject('XXXXXXXXXXXX/Mobile Part/Reusable Object/Product Enter Search'), GlobalVariable.ProductName, 5)

    Mobile.delay(2, FailureHandling.STOP_ON_FAILURE)

    Mobile.takeScreenshot()

    Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/ProductName-(Global)'), 5, FailureHandling.STOP_ON_FAILURE)

    Mobile.verifyElementVisible(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/Tab-Product Details'), 5)

    Mobile.takeScreenshot()

    Actual_BasePrice = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/ProductDetails/BasePrice_Value_Field'), 0)

    Actual_PiecePrice = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/ProductDetails/PiecePrice_Value_Field'), 0)

    Actual_CaseSize = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/ProductDetails/CaseSize_Value_Field'), 0)

    Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/Case_Field'), 0)

    Slab_1_Min_Qty = findTestData('Mobile Input Data/RetailerTypeCreditNote_Scheme').getValue('Slab_1_Min_Qty', Scheme_Index)

    String Slab_Qty = Integer.parseInt(Slab_1_Min_Qty)

    println(GlobalVariable.Qty = Slab_Qty)

    Mobile.callTestCase(findTestCase('Product_Mobile/Common/Enter_CASE_Qty'), [:], FailureHandling.STOP_ON_FAILURE)

    /*length = Slab_Qty.size()

    if (2 == length) {
        GlobalVariable.keypadValue = Slab_Qty.charAt(0)

        Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Reusable Object/Box-Keypad/Global-number_keypad'), 5)

        GlobalVariable.keypadValue = Slab_Qty.charAt(1)

        Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Reusable Object/Box-Keypad/Global-number_keypad'), 5)
    } else {
        GlobalVariable.keypadValue = Slab_Qty

        Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Reusable Object/Box-Keypad/Global-number_keypad'), 5)
    }
    
    Mobile.tap(findTestObject('Mobile/Common/Btn_OK'), 5)*/
    Mobile.tap(findTestObject('Mobile/Common/Icon_Back'), 0)

    Mobile.delay(2, FailureHandling.STOP_ON_FAILURE)

    Mobile.takeScreenshot()

    SKU_1_TOTAL = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/OrderInvoiceScrn-SkuTotal'), 0)

    Total1 = ((Double.parseDouble(Actual_CaseSize) * Double.parseDouble(Slab_Qty)) * Double.parseDouble(Actual_PiecePrice))

    Mobile.verifyEqual(Double.parseDouble(SKU_1_TOTAL), Total1, FailureHandling.STOP_ON_FAILURE)

    Mobile.takeScreenshot()

    KeywordUtil.logInfo('Sku Total Amount calculated and displayed correctly according the formula.')

    //2_SKU
    '2_SKU'
    Mobile.callTestCase(findTestCase('Product_Mobile/Common/Clear_Star_Filter_Default_Selection'), [:], FailureHandling.STOP_ON_FAILURE)

    Mobile.callTestCase(findTestCase('Product_Mobile/Common/Search_Product'), [:], FailureHandling.STOP_ON_FAILURE)

    GlobalVariable.ProductName = findTestData('Mobile Input Data/RetailerTypeCreditNote_Scheme').getValue('BuyProduct2', Scheme_Index)

    Mobile.setText(findTestObject('XXXXXXXXXXXX/Mobile Part/Reusable Object/Product Enter Search'), GlobalVariable.ProductName, 5)

    Mobile.delay(2, FailureHandling.STOP_ON_FAILURE)

    Mobile.takeScreenshot()

    Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/ProductName-(Global)'), 5, FailureHandling.STOP_ON_FAILURE)

    Mobile.verifyElementVisible(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/Tab-Product Details'), 5)

    Mobile.takeScreenshot()

    Actual_BasePrice = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/ProductDetails/BasePrice_Value_Field'), 0)

    Actual_PiecePrice = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/ProductDetails/PiecePrice_Value_Field'), 0)

    Actual_CaseSize = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/ProductDetails/CaseSize_Value_Field'), 0)

    Actual_CaseSize02 = Actual_CaseSize

    Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/Case_Field'), 0)

    Slab_1_Min_Qty = findTestData('Mobile Input Data/RetailerTypeCreditNote_Scheme').getValue('Slab_1_Min_Qty', Scheme_Index)

    String Slab_Qty2 = Integer.parseInt(Slab_1_Min_Qty)

    println(GlobalVariable.Qty = Slab_Qty2)

    Mobile.callTestCase(findTestCase('Product_Mobile/Common/Enter_CASE_Qty'), [:], FailureHandling.STOP_ON_FAILURE)

    /*length = Slab_Qty2.size()

    if (2 == length) {
        GlobalVariable.keypadValue = Slab_Qty2.charAt(0)

        Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Reusable Object/Box-Keypad/Global-number_keypad'), 5)

        GlobalVariable.keypadValue = Slab_Qty2.charAt(1)

        Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Reusable Object/Box-Keypad/Global-number_keypad'), 5)
    } else {
        GlobalVariable.keypadValue = Slab_Qty2

        Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Reusable Object/Box-Keypad/Global-number_keypad'), 5)
    }
    
    Mobile.tap(findTestObject('Mobile/Common/Btn_OK'), 5)*/
    Mobile.tap(findTestObject('Mobile/Common/Icon_Back'), 0)

    Mobile.delay(2, FailureHandling.STOP_ON_FAILURE)

    Mobile.takeScreenshot()

    SKU_2_TOTAL = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/OrderInvoiceScrn-SkuTotal'), 0)

    Total2 = ((Double.parseDouble(Actual_CaseSize) * Double.parseDouble(Slab_Qty2)) * Double.parseDouble(Actual_PiecePrice))

    Mobile.verifyEqual(Double.parseDouble(SKU_2_TOTAL), Total2, FailureHandling.STOP_ON_FAILURE)

    Mobile.takeScreenshot()

    KeywordUtil.logInfo('Sku Total Amount calculated and displayed correctly according the formula.')

    'Get free product price details'

    'Free sku 01'
    GlobalVariable.Scheme_Free_SKU = findTestData('Mobile Input Data/RetailerTypeCreditNote_Scheme').getValue('FreeProduct_Slab1', Scheme_Index)

    Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Reusable Object/Product Search Icon'), 1, FailureHandling.OPTIONAL)

    GlobalVariable.ProductName = GlobalVariable.Scheme_Free_SKU

    Mobile.setText(findTestObject('XXXXXXXXXXXX/Mobile Part/Reusable Object/Product Enter Search'), GlobalVariable.ProductName, 5)

    Mobile.delay(2, FailureHandling.STOP_ON_FAILURE)

    Mobile.takeScreenshot()

    Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/ProductName-(Global)'), 5, FailureHandling.STOP_ON_FAILURE)

    Mobile.verifyElementVisible(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/Tab-Product Details'), 5)

    Mobile.takeScreenshot()

    Actual_PiecePrice_Free01 = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/ProductDetails/PiecePrice_Value_Field'), 0)

    Actual_CaseSize_Free01 = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/ProductDetails/CaseSize_Value_Field'), 0)

    Mobile.tap(findTestObject('Mobile/Common/Icon_Back'), 0)

    Mobile.delay(2, FailureHandling.STOP_ON_FAILURE)

    'Free sku 02'
    GlobalVariable.Scheme_Free_SKU = findTestData('Mobile Input Data/RetailerTypeCreditNote_Scheme').getValue('FreeProduct02_Slab1', Scheme_Index)

    Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Reusable Object/Product Search Icon'), 1, FailureHandling.OPTIONAL)

    GlobalVariable.ProductName = GlobalVariable.Scheme_Free_SKU

    Mobile.setText(findTestObject('XXXXXXXXXXXX/Mobile Part/Reusable Object/Product Enter Search'), GlobalVariable.ProductName, 5)

    Mobile.delay(2, FailureHandling.STOP_ON_FAILURE)

    Mobile.takeScreenshot()

    Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/ProductName-(Global)'), 5, FailureHandling.STOP_ON_FAILURE)

    Mobile.verifyElementVisible(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/Tab-Product Details'), 5)

    Mobile.takeScreenshot()

    Actual_PiecePrice_Free02 = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/ProductDetails/PiecePrice_Value_Field'), 0)

    Actual_CaseSize_Free02 = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/ProductDetails/CaseSize_Value_Field'), 0)

    Mobile.tap(findTestObject('Mobile/Common/Icon_Back'), 0)

    Mobile.delay(2, FailureHandling.STOP_ON_FAILURE)

    Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/Next Btn-OrderInvoiceScreen'), 0)

    WebUI.callTestCase(findTestCase('VBL_Mobile/Re-Usables/MustSell_Alert'), [:], FailureHandling.STOP_ON_FAILURE)

    'Scheme should be applied'
    Mobile.takeScreenshot()

    Mobile.verifyElementVisible(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/Scheme/SchemeName'), 5, FailureHandling.STOP_ON_FAILURE)

    SchemeName = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/Scheme/SchemeName'), 0)

    SchemeDesc = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/Scheme/SchemeDesc'), 0)

    Discountvalue = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/Scheme/Additional Discount Field'), 5)

    Discount_Amt = Discountvalue.replaceAll('Additional Discount : ', '')

    Mobile.verifyMatch(SchemeName, findTestData('Mobile Input Data/RetailerTypeCreditNote_Scheme').getValue('SchemeName', Scheme_Index), false, FailureHandling.STOP_ON_FAILURE)

    Mobile.takeScreenshot()

    KeywordUtil.logInfo(SchemeName + ' : Slab_1 Scheme Name correctly applied !')

    Mobile.verifyMatch(SchemeDesc, findTestData('Mobile Input Data/RetailerTypeCreditNote_Scheme').getValue('SchemeDescSlab1', Scheme_Index), false, FailureHandling.STOP_ON_FAILURE)

    Mobile.takeScreenshot()

    KeywordUtil.logInfo(SchemeDesc + ' : Slab_1 Scheme Description correctly displayed !')

    Web_Addition_dis_Amt = findTestData('Mobile Input Data/RetailerTypeCreditNote_Scheme').getValue('Additional_Dis_Amt', Scheme_Index)

    'user need to add both product case qty because it is AND logic for buy product'

    'formula : x= [ (inv qty * add. disc amt) / (sum of line value) ]* particular sku line value '
    Two_SKU_Case_Qty = (Double.parseDouble(Slab_Qty) + Double.parseDouble(Slab_Qty2))

    KeywordUtil.logInfo(Two_SKU_Case_Qty.toString() + ' : Sum of two product invoice Quantity')

    SumOfTheLineValue = (Double.parseDouble(SKU_1_TOTAL) + Double.parseDouble(SKU_2_TOTAL))

    KeywordUtil.logInfo(SumOfTheLineValue.toString() + ' : Sum of two sku total line value')

    SKU_1_DiscountAmt = (((Two_SKU_Case_Qty * Double.parseDouble(Web_Addition_dis_Amt)) / SumOfTheLineValue) * Double.parseDouble(SKU_1_TOTAL))

    KeywordUtil.logInfo(SKU_1_DiscountAmt.toString() + ' : Sku 1 discount amount')

    SKU_2_DiscountAmt = (((Two_SKU_Case_Qty * Double.parseDouble(Web_Addition_dis_Amt)) / SumOfTheLineValue) * Double.parseDouble(SKU_2_TOTAL))

    KeywordUtil.logInfo(SKU_2_DiscountAmt.toString() + ' : Sku 2 discount amount')

    Total_Scheme_Discount_amt = (SKU_1_DiscountAmt + SKU_2_DiscountAmt)

    Total_Scheme_Discount_amt = Math.ceil(Total_Scheme_Discount_amt)

    '(GlobalVariable.DiscountAmt) This is used to verify the credit note discount amount in the database'
    GlobalVariable.DiscountAmt = Total_Scheme_Discount_amt

    KeywordUtil.logInfo(Total_Scheme_Discount_amt.toString() + ' : Sum of the sku discount amt (total discount amt)')

    Mobile.takeScreenshot()

    Mobile.verifyEqual(Double.parseDouble(Discount_Amt), Total_Scheme_Discount_amt, FailureHandling.STOP_ON_FAILURE)

    KeywordUtil.logInfo(Discount_Amt + ' : Additional discount Amount Displayed correctly in Screen Screen !')

    Mobile.takeScreenshot()

    Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/Scheme/Scheme_View_Btn'), 0)

    Mobile.takeScreenshot()

    GlobalVariable.Scheme_Free_SKU = findTestData('Mobile Input Data/RetailerTypeCreditNote_Scheme').getValue('FreeProduct_Slab1', Scheme_Index)

    Mobile.waitForElementPresent(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/Scheme/SchemeViewScreen_FreeSKU'), 5)

    FreeProduct_Slab1 = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/Scheme/SchemeViewScreen_FreeSKU'), 5)

    FreeSKU_Qty_Slab1 = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/Scheme/SchemeViewScreen_FreeSKU_Qty(Global)'), 5)

    FreeSKU_MinQty_Slab1 = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/Scheme/SchemeViewScreen_FreeSKU_MinQty(Global)'), 5)

    FreeSKU_MaxQty_Slab1 = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/Scheme/SchemeViewScreen_FreeSKU_MaxQty(Global)'), 5)

    Mobile.verifyMatch(FreeProduct_Slab1, GlobalVariable.Scheme_Free_SKU, false, FailureHandling.STOP_ON_FAILURE)

    Mobile.takeScreenshot()

    KeywordUtil.logInfo(FreeProduct_Slab1 + ' :Slab 1 Free product correctly displayed !')

    Mobile.verifyMatch(FreeSKU_Qty_Slab1, findTestData('Mobile Input Data/RetailerTypeCreditNote_Scheme').getValue('FreeProduct_Case_Slab1', Scheme_Index), false, FailureHandling.STOP_ON_FAILURE)

    Mobile.takeScreenshot()

    KeywordUtil.logInfo(FreeSKU_Qty_Slab1 + ' :Slab 1 Free Product Case Qty correctly displayed !')

    Expecetd_FreeSKU_MinQty_Slab1 = ('Min:' + findTestData('Mobile Input Data/RetailerTypeCreditNote_Scheme').getValue('FreeProduct_Case_Slab1', Scheme_Index))

    Mobile.verifyMatch(FreeSKU_MinQty_Slab1.replaceAll('\\s', ''), Expecetd_FreeSKU_MinQty_Slab1, false, FailureHandling.STOP_ON_FAILURE)

    Mobile.takeScreenshot()

    KeywordUtil.logInfo(FreeSKU_MinQty_Slab1 + ' :Slab 1 Free Product Min Case Qty correctly displayed !')

    Expecetd_FreeSKU_MaxQty_Slab1 = ('Max:' + findTestData('Mobile Input Data/RetailerTypeCreditNote_Scheme').getValue('FreeProduct_Case_Slab1_MAX', Scheme_Index))

    Mobile.verifyMatch(FreeSKU_MaxQty_Slab1.replaceAll('\\s', ''), Expecetd_FreeSKU_MaxQty_Slab1, false, FailureHandling.STOP_ON_FAILURE)

    Mobile.takeScreenshot()

    KeywordUtil.logInfo(FreeSKU_MaxQty_Slab1 + ' : Slab 1 Free Product Max Case Qty correctly displayed !')

    '2nd free product'
    GlobalVariable.Scheme_Free_SKU = findTestData('Mobile Input Data/RetailerTypeCreditNote_Scheme').getValue('FreeProduct02_Slab1', Scheme_Index)

    Mobile.waitForElementPresent(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/Scheme/SchemeViewScreen_FreeSKU'), 5)

    FreeProduct_Slab1 = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/Scheme/SchemeViewScreen_FreeSKU'), 5)

    FreeSKU_Qty_Slab1 = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/Scheme/SchemeViewScreen_FreeSKU_Qty(Global)'), 5)

    FreeSKU_MinQty_Slab1 = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/Scheme/SchemeViewScreen_FreeSKU_MinQty(Global)'), 5)

    FreeSKU_MaxQty_Slab1 = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/Scheme/SchemeViewScreen_FreeSKU_MaxQty(Global)'), 5)

    Mobile.verifyMatch(FreeProduct_Slab1, GlobalVariable.Scheme_Free_SKU, false, FailureHandling.STOP_ON_FAILURE)

    Mobile.takeScreenshot()

    KeywordUtil.logInfo(FreeProduct_Slab1 + ' :Slab 1 Free product correctly displayed !')

    Mobile.verifyMatch(FreeSKU_Qty_Slab1, findTestData('Mobile Input Data/RetailerTypeCreditNote_Scheme').getValue('FreeProduct02_Case_Slab1', Scheme_Index), false, FailureHandling.STOP_ON_FAILURE)

    Mobile.takeScreenshot()

    KeywordUtil.logInfo(FreeSKU_Qty_Slab1 + ' :Slab 1 Free Product Case Qty correctly displayed !')

    Expecetd_FreeSKU_MinQty_Slab1 = ('Min:' + findTestData('Mobile Input Data/RetailerTypeCreditNote_Scheme').getValue('FreeProduct02_Case_Slab1', Scheme_Index))

    Mobile.verifyMatch(FreeSKU_MinQty_Slab1.replaceAll('\\s', ''), Expecetd_FreeSKU_MinQty_Slab1, false, FailureHandling.STOP_ON_FAILURE)

    Mobile.takeScreenshot()

    KeywordUtil.logInfo(FreeSKU_MinQty_Slab1 + ' :Slab 1 Free Product Min Case Qty correctly displayed !')

    Expecetd_FreeSKU_MaxQty_Slab1 = ('Max:' + findTestData('Mobile Input Data/RetailerTypeCreditNote_Scheme').getValue('FreeProduct02_Case_Slab1_MAX', Scheme_Index))

    Mobile.verifyMatch(FreeSKU_MaxQty_Slab1.replaceAll('\\s', ''), Expecetd_FreeSKU_MaxQty_Slab1, false, FailureHandling.STOP_ON_FAILURE)

    Mobile.takeScreenshot()

    KeywordUtil.logInfo(FreeSKU_MaxQty_Slab1 + ' : Slab 1 Free Product Max Case Qty correctly displayed !')

    Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/Scheme/Scheme_Done_Btn'), 0)

    Mobile.callTestCase(findTestCase('VBL_Mobile/Re-Usables/Tap_Next_Btn_ApplyschemeScrn_CT'), [:], FailureHandling.STOP_ON_FAILURE)

    ////////////////
    'Summary Screen'

    //1_free Sku
    'And_Logic Free Product'
    Mobile.takeScreenshot()

    GlobalVariable.Scheme_Free_SKU = findTestData('Mobile Input Data/RetailerTypeCreditNote_Scheme').getValue('FreeProduct_Slab1', Scheme_Index)

    Mobile.verifyElementVisible(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/SummaryScreen/Summary_FreeSkuTitle(Global)'), 5)

    Slab1_FreeSku = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/SummaryScreen/Summary_FreeSkuTitle(Global)'), 5)

    Slab1_FreeSku_CaseQty = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/SummaryScreen/Summary_ProductBasedCaseQty(Global)'), 5)

    Slab1_FreeSku_Amt1 = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/SummaryScreen/Summary_ProductBasedTotalAmt(Global)'), 5)

    Mobile.verifyMatch(Slab1_FreeSku, GlobalVariable.Scheme_Free_SKU, false, FailureHandling.STOP_ON_FAILURE)

    Mobile.takeScreenshot()

    KeywordUtil.logInfo(Slab1_FreeSku + ' :Slab 1 Free Product  correctly displayed in Summary Screen !')

    Mobile.verifyMatch(Slab1_FreeSku_CaseQty, findTestData('Mobile Input Data/RetailerTypeCreditNote_Scheme').getValue('FreeProduct_Case_Slab1', Scheme_Index), false, FailureHandling.STOP_ON_FAILURE)

    Mobile.takeScreenshot()

    KeywordUtil.logInfo(Slab1_FreeSku_CaseQty + ' :Slab 1 Free Product Case Qty correctly displayed in Summary Screen !')

    //2_freeSku
    Mobile.takeScreenshot()

    GlobalVariable.Scheme_Free_SKU = findTestData('Mobile Input Data/RetailerTypeCreditNote_Scheme').getValue('FreeProduct02_Slab1', Scheme_Index)

    Mobile.verifyElementVisible(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/SummaryScreen/Summary_FreeSkuTitle(Global)'), 5)

    Slab1_FreeSku = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/SummaryScreen/Summary_FreeSkuTitle(Global)'), 5)

    Slab1_FreeSku_CaseQty = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/SummaryScreen/Summary_ProductBasedCaseQty(Global)'), 5)

    Slab1_FreeSku_Amt2 = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/SummaryScreen/Summary_ProductBasedTotalAmt(Global)'), 5)

    Mobile.verifyMatch(Slab1_FreeSku, GlobalVariable.Scheme_Free_SKU, false, FailureHandling.STOP_ON_FAILURE)

    Mobile.takeScreenshot()

    KeywordUtil.logInfo(Slab1_FreeSku + ' :Slab 1 Free Product  correctly displayed in Summary Screen !')

    Mobile.verifyMatch(Slab1_FreeSku_CaseQty, findTestData('Mobile Input Data/RetailerTypeCreditNote_Scheme').getValue('FreeProduct02_Case_Slab1', Scheme_Index), false, FailureHandling.STOP_ON_FAILURE)

    Mobile.takeScreenshot()

    KeywordUtil.logInfo(Slab1_FreeSku_CaseQty + ' :Slab 1 Free Product Case Qty correctly displayed in Summary Screen !')

    'Amount Split Up Screen'
    Mobile.verifyElementVisible(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/SummaryScreen/SummaryScreen-InfoIcon'), 15)

    Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/SummaryScreen/SummaryScreen-InfoIcon'), 0)

    Mobile.takeScreenshot()

    OrderAmt = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/SummaryScreen/SplitScreen-OrderAmt'), 0)

    'Apportionate Scheme so Buy and get product line value should be added in the Order value'
    Slab_Qty_Free01 = findTestData('Mobile Input Data/RetailerTypeCreditNote_Scheme').getValue('FreeProduct_Case_Slab1', Scheme_Index)

    FreeSku01_total = ((Double.parseDouble(Actual_CaseSize_Free01) * Double.parseDouble(Slab_Qty_Free01)) * Double.parseDouble(Actual_PiecePrice_Free01))

    KeywordUtil.logInfo(FreeSku01_total.toString() + ' : Free sku 01 total line value')

    Slab_Qty_Free02 = findTestData('Mobile Input Data/RetailerTypeCreditNote_Scheme').getValue('FreeProduct02_Case_Slab1', Scheme_Index)

    FreeSku02_total = ((Double.parseDouble(Actual_CaseSize_Free02) * Double.parseDouble(Slab_Qty_Free02)) * Double.parseDouble(Actual_PiecePrice_Free02))

    KeywordUtil.logInfo(FreeSku02_total.toString() + ' : Free sku 02 total line value')

    '(GlobalVariable.FreeSkuTotal) : this global variable used to verify free sku credit note amount in database'
    GlobalVariable.FreeSkuTotal = (FreeSku01_total + FreeSku02_total)

    KeywordUtil.logInfo(GlobalVariable.FreeSkuTotal + ' : this global variable used to verify credit note amount in database')

    Calculated_Order_Value = (((Total1 + Total2) + FreeSku01_total) + FreeSku02_total)

    KeywordUtil.logInfo(Calculated_Order_Value.toString() + ' : total line value')

    Mobile.verifyEqual(Double.parseDouble(OrderAmt), Calculated_Order_Value, FailureHandling.STOP_ON_FAILURE)

    KeywordUtil.logInfo(OrderAmt + ' : Order value displayed correctly !')

    Mobile.takeScreenshot()

    'This is credit note retailer thats why scheme diplayed as zero'
    Mobile.takeScreenshot()

    Scheme_Amt = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/SummaryScreen/SplitScreen_SCHEME Amt'), 0)

    SchemeAmt = Scheme_Amt.replaceAll('- ', '')

    KeywordUtil.logInfo(SchemeAmt)

    Mobile.verifyEqual(Double.parseDouble(SchemeAmt), 0, FailureHandling.STOP_ON_FAILURE)

    KeywordUtil.logInfo(SchemeAmt + ' : Scheme amount displayed correctly !')

    Mobile.takeScreenshot()

    CGST = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/SummaryScreen/SplitScreen-CGST Value'), 0, FailureHandling.OPTIONAL)

    CESS = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/SummaryScreen/SplitScreen-CESS Value'), 0, FailureHandling.OPTIONAL)

    IGST = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/SummaryScreen/SplitScreen-IGST Value'), 0, FailureHandling.OPTIONAL)

    SGST = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/SummaryScreen/SplitScreen-SGSTValue'), 0, FailureHandling.OPTIONAL)

    TotalAmt = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/SummaryScreen/SliptScreen_TotalAmt'), 0)

    //tax = (((Double.parseDouble(CGST) + Double.parseDouble(CESS)) + Double.parseDouble(IGST)) + Double.parseDouble(SGST))
    'This is credit retailer + apportionate so buy product total + Tax equal to total'
    tax = Double.parseDouble(IGST)

    orderAmount = (Total1 + Total2)

    //Calculated_TotalAmt = (orderAmount + tax)
    Mobile.verifyEqual(Double.parseDouble(TotalAmt), Calculated_Order_Value, FailureHandling.STOP_ON_FAILURE)

    //Mobile.verifyMatch(TotalAmt, Calculated_TotalAmt.toString(), false, FailureHandling.OPTIONAL)
    Mobile.takeScreenshot()

    KeywordUtil.logInfo(Calculated_Order_Value.toString() + ' : Slab_1 Total amount in split screen displayed correctly !')

    Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/SummaryScreen/SummaryScreen-info_closeBtn'), 0)

    Mobile.callTestCase(findTestCase('Product_Mobile/Common/Generate_OrderInvoice and CloseCall'), [:], FailureHandling.STOP_ON_FAILURE)

    exlpath = 'DDF\\Web Input Data\\Web Input Data.xlsx'

    Sheetname = 'Sales_Order_Invoice_View'

    workbook01 = ExcelKeywords.getWorkbook(exlpath)

    sheet1 = ExcelKeywords.getExcelSheet(workbook01, Sheetname)

    ExcelKeywords.setValueToCellByIndex(sheet1, 7, 6, GlobalVariable.InvoiceNo)

    ExcelKeywords.setValueToCellByIndex(sheet1, 7, 12, GlobalVariable.DiscountAmt)

    ExcelKeywords.saveWorkbook(exlpath, workbook01 //    'Stock reduce validation for scheme'
        ) //    Slab_1_Min_Qty = findTestData('Mobile Input Data/RetailerTypeCreditNote_Scheme').getValue('Slab_1_Min_Qty', Scheme_Index)
    //
    //    SIH_BUY_SKU01 = (Integer.parseInt(Slab_1_Min_Qty) * Integer.parseInt(Actual_CaseSize))
    //
    //    SIH_BUY_SKU02 = (Integer.parseInt(Slab_1_Min_Qty) * Integer.parseInt(Actual_CaseSize02))
    //
    //    FreeProduct_Case_Slab1 = findTestData('Mobile Input Data/RetailerTypeCreditNote_Scheme').getValue('FreeProduct_Case_Slab1', Scheme_Index)
    //
    //    SIH_GET_SKU01 = (Integer.parseInt(FreeProduct_Case_Slab1) * Integer.parseInt(Actual_CaseSize_Free01))
    //
    //    FreeProduct02_Case_Slab1 = findTestData('Mobile Input Data/RetailerTypeCreditNote_Scheme').getValue('FreeProduct02_Case_Slab1', Scheme_Index)
    //
    //    SIH_GET_SKU02 = (Integer.parseInt(FreeProduct02_Case_Slab1) * Integer.parseInt(Actual_CaseSize_Free02))
    //
    //	exlpath = 'DDF\\Mobile Input data\\Mobile Input data.xlsx'
    //	
    //	Sheetname = 'Scheme_Stock_Validation'
    //
    //    workbook01 = ExcelKeywords.getWorkbook(exlpath)
    //
    //    sheet1 = ExcelKeywords.getExcelSheet(workbook01, Sheetname)
    //
    //    //Buy_Qty_01 = findTestData('VBL_Mobile Input Data/Scheme_Stock_Validation').getValue('SKU_Invoice_Generated_Qty', 15)
    //    //
    //    //SIH_BUY_SKU01 = SIH_BUY_SKU01 + Integer.parseInt(Buy_Qty_01)
    //    ExcelKeywords.setValueToCellByIndex(sheet1, 11, 5, SIH_BUY_SKU01)
    //
    //    //Buy_Qty_02 = findTestData('VBL_Mobile Input Data/Scheme_Stock_Validation').getValue('SKU_Invoice_Generated_Qty', 16)
    //    //
    //    //SIH_BUY_SKU02 = SIH_BUY_SKU02 + Integer.parseInt(Buy_Qty_02)
    //    ExcelKeywords.setValueToCellByIndex(sheet1, 12, 5, SIH_BUY_SKU02)
    //
    //    //Get_Qty_01 = findTestData('VBL_Mobile Input Data/Scheme_Stock_Validation').getValue('SKU_Invoice_Generated_Qty', 8)
    //    //
    //    //SIH_GET_SKU01 = SIH_GET_SKU01 + Integer.parseInt(Get_Qty_01)
    //    //
    //    //Get_Qty_02 = findTestData('VBL_Mobile Input Data/Scheme_Stock_Validation').getValue('SKU_Invoice_Generated_Qty', 9)
    //    //
    //    //SIH_GET_SKU01 = SIH_GET_SKU01 + Integer.parseInt(Get_Qty_02)
    //    ExcelKeywords.setValueToCellByIndex(sheet1, 13, 5, SIH_GET_SKU01)
    //
    //    ExcelKeywords.setValueToCellByIndex(sheet1, 14, 5, SIH_GET_SKU02)
    //
    //    ExcelKeywords.saveWorkbook(exlpath, workbook01)
}

