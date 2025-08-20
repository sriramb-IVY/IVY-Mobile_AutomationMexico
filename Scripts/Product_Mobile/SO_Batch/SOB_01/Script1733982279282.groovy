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
import java.time.LocalDate as LocalDate
import org.testng.Assert as Assert
import com.kms.katalon.core.mobile.keyword.internal.MobileDriverFactory as MobileDriverFactory
import io.appium.java_client.AppiumDriver as AppiumDriver

Mobile.startApplication(GlobalVariable.APKFile, false)

Mobile.tap(findTestObject('Mobile/Common/Btn_Menu'), 20)

if (Mobile.verifyElementExist(findTestObject('Mobile/Common/Btn_Menu'), 2, FailureHandling.OPTIONAL)) {
    'Menu Icon visible'
} else {
    Mobile.callTestCase(findTestCase('Product_Mobile/Common/Mobile_Login/Relaunch the app'), [:], FailureHandling.CONTINUE_ON_FAILURE)
}

WebUI.callTestCase(findTestCase('Product_Mobile/Common/TradeCoverage(SelectRetailer)'), [('RetailerName') : findTestData('Mobile Input Data/SO_Batch').getValue('Retailer', 1)], FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('Product_Mobile/Common/StartVisit'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('Product_Mobile/Common/Store_Menu_Selection'), [('MenuName') : findTestData('Mobile Input Data/Common').getValue('Menu', 4), ('SubMenuName') : findTestData('Mobile Input Data/Common').getValue('Menu', 1)], FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('Product_Mobile/Common/Clear_Star_Filter_Default_Selection'), [:], FailureHandling.STOP_ON_FAILURE)

//Mobile.tap(findTestObject('Mobile/Common/Icon_Search'), 4, FailureHandling.OPTIONAL)

GlobalVariable.ProductName = findTestData('Mobile Input Data/SO_Batch').getValue('SKU_Name', 1)

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Search_Product'), [('ProductName') :  GlobalVariable.ProductName], FailureHandling.STOP_ON_FAILURE)

Mobile.delay(2)

SIH_SKU1 = Mobile.getText(findTestObject('Mobile/Seller_2/Stock_order_Batch/Product_SIH_get'), 0)

GlobalVariable.Qty = findTestData('Mobile Input Data/SO_Batch').getValue('Qty', 1)

println(GlobalVariable.Qty)

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Enter_PIECE_Qty'), [:], FailureHandling.STOP_ON_FAILURE)

Mobile.tap(findTestObject('Mobile/Common/Btn_Next'), 0)

Mobile.callTestCase(findTestCase('Product_Mobile/Common/MustSell_Alert_without_batch'), [:], FailureHandling.STOP_ON_FAILURE)

Mobile.delay(3)

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

qty1 = (Double.parseDouble(Order_piece) / 2).round()

KeywordUtil.logInfo('Quantity1 : ' + qty1)

String Quantity1 = qty1

GlobalVariable.keypadValue = Quantity1

GlobalVariable.batch = GlobalVariable.batch1

Mobile.tap(findTestObject('Mobile/Seller_2/Stock_order_Batch/Piece_field'), 0)

Mobile.tap(findTestObject('Object Repository/Mobile/Common/Global_Number_keypad'), 5)

qty2 = (Double.parseDouble(Order_piece) - qty1).round()

KeywordUtil.logInfo('Quantity2 : ' + qty2)

String Quantity2 = qty2

GlobalVariable.keypadValue = Quantity2

GlobalVariable.batch = GlobalVariable.batch2

'before enter quantity for second batch'
bef_Sec_batch_piece = Mobile.getText(findTestObject('Mobile/Seller_2/Stock_order_Batch/Piece_field'), 0)

Mobile.delay(2)

Mobile.tap(findTestObject('Mobile/Seller_2/Stock_order_Batch/Piece_field'), 0)

Mobile.tap(findTestObject('Mobile/Seller_2/Stock_order_Batch/Piece_field'), 0)

Mobile.setText(findTestObject('Mobile/Seller_2/Stock_order_Batch/Piece_field'), Quantity2, 0)

//Mobile.tap(findTestObject('Object Repository/Mobile/Common/Global_Number_keypad'), 5)
Mobile.delay(2)

Mobile.tap(findTestObject('Object Repository/Mobile/Common/Btn_Done'), 0)

Mobile.delay(2)

GlobalVariable.label = findTestData('Mobile Input Data/SO_Batch').getValue('SKU_Name', 1)

Mobile.verifyElementExist(findTestObject('Mobile/Seller_2/Stock_order_Batch/Product_Name_batch_screen'), 0, FailureHandling.STOP_ON_FAILURE)

Mobile.tap(findTestObject('Mobile/Seller_2/Stock_order_Batch/Product_Name_batch_screen'), 0)

Mobile.waitForElementPresent(findTestObject('Mobile/Seller_2/Stock_order_Batch/Product_Name'), 0)

GlobalVariable.batch = GlobalVariable.batch2

After_Sec_batch_piece = Mobile.getText(findTestObject('Mobile/Seller_2/Stock_order_Batch/Piece_field'), 0)

Mobile.verifyNotEqual(Double.parseDouble(bef_Sec_batch_piece), Double.parseDouble(After_Sec_batch_piece), FailureHandling.STOP_ON_FAILURE)

KeywordUtil.logInfo('Able to edit and add quantity for both batches')

Mobile.tap(findTestObject('Object Repository/Mobile/Common/Btn_Done'), 0)

'back to order screen'
Mobile.tap(findTestObject('Mobile/Common/Icon_Back'), 0)

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Clear_Star_Filter_Default_Selection'), [:], FailureHandling.CONTINUE_ON_FAILURE)

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Search_Product'), [:], FailureHandling.CONTINUE_ON_FAILURE)

GlobalVariable.ProductName = findTestData('Mobile Input Data/SO_Batch').getValue('SKU_Name', 1)

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Search_Product'), [('ProductName') :  GlobalVariable.ProductName], FailureHandling.STOP_ON_FAILURE)

Mobile.delay(2)

Mobile.verifyElementVisible(findTestObject('Mobile/OrderInvoice/PIECE_Bar'), 1, FailureHandling.OPTIONAL)

Mobile.tap(findTestObject('Mobile/OrderInvoice/Enter_Qty'), 0)

Mobile.tap(findTestObject('Mobile/Common/Vertical keypad-BackSpace'), 0)

Mobile.tap(findTestObject('Mobile/Common/Vertical keypad-BackSpace'), 0)

Mobile.delay(3)

