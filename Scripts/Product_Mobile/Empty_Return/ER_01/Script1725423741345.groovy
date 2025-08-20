import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.chrome.ChromeDriver as ChromeDriver
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.keyword.excel.ExcelKeywords as ExcelKeywords
import internal.GlobalVariable as GlobalVariable
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import com.kms.katalon.core.mobile.keyword.internal.MobileDriverFactory as MobileDriverFactory
import io.appium.java_client.android.AndroidDriver as AndroidDriver
//import io.appium.java_client.MobileElement as MobileElement
import io.appium.java_client.android.nativekey.AndroidKey as AndroidKey
import io.appium.java_client.android.nativekey.KeyEvent as KeyEvent
import io.appium.java_client.AppiumDriver as AppiumDriver

Mobile.startApplication(GlobalVariable.APKFile, false)
Mobile.tap(findTestObject('Mobile/Common/Btn_Menu'), 0)
//Mobile.tap(findTestObject('Mobile/Common/Btn_Menu'), 0)

if (Mobile.verifyElementExist(findTestObject('Mobile/Store_Actvy/Menu-EmptyReturn'), 2, FailureHandling.OPTIONAL)) {
    'Collection menu visible'
    Mobile.takeScreenshot()
} else {
    Mobile.callTestCase(findTestCase('Product_Mobile/Common/Mobile_Login/Relaunch the app'), [:], FailureHandling.CONTINUE_ON_FAILURE)

    Mobile.tap(findTestObject('Mobile/Common/Btn_Menu'), 0)

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
}

Mobile.tap(findTestObject('Mobile/Store_Actvy/Menu-EmptyReturn'), 0)

Mobile.delay(2)

Mobile.comment('Validation for Empty return Title')

Mobile.verifyElementExist(findTestObject('Object Repository/Mobile/Empty_Return/Empty_Return_Title'), 5, FailureHandling.STOP_ON_FAILURE)

Mobile.comment('Validation for Empty return Products')

GlobalVariable.label = findTestData('Mobile Input Data/Empty_Return').getValue('SKU_Name', 1)

Mobile.verifyElementExist(findTestObject('Object Repository/Mobile/Empty_Return/Global_Product_Name'), 35, FailureHandling.STOP_ON_FAILURE)

GlobalVariable.label = findTestData('Mobile Input Data/Empty_Return').getValue('SKU_Name', 2)

Mobile.verifyElementExist(findTestObject('Object Repository/Mobile/Empty_Return/Global_Product_Name'), 35, FailureHandling.STOP_ON_FAILURE)

GlobalVariable.label = findTestData('Mobile Input Data/Empty_Return').getValue('SKU_Name', 3)

Mobile.verifyElementExist(findTestObject('Object Repository/Mobile/Empty_Return/Global_Product_Name'), 35, FailureHandling.STOP_ON_FAILURE)

GlobalVariable.label = findTestData('Mobile Input Data/Empty_Return').getValue('SKU_Name', 4)

Mobile.verifyElementExist(findTestObject('Object Repository/Mobile/Empty_Return/Global_Product_Name'), 35, FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

//click on qty field
GlobalVariable.label = findTestData('Mobile Input Data/Empty_Return').getValue('SKU_Name', 1)

Mobile.tap(findTestObject('Object Repository/Mobile/Empty_Return/Global_Product_qty'), 100)

Mobile.delay(2)

Mobile.comment('Verify Piece Category and Reason dropdown')

Mobile.verifyElementExist(findTestObject('Object Repository/Mobile/Empty_Return/Piece_enter_field'), 5, FailureHandling.STOP_ON_FAILURE)

Mobile.setText(findTestObject('Mobile/Empty_Return/Piece_enter_field'), findTestData('Mobile Input Data/Empty_Return').getValue('Quantity', 1), 0)

Mobile.delay(2)

GlobalVariable.label = findTestData('Mobile Input Data/Empty_Return').getValue('Catagory', 1)

Mobile.verifyElementExist(findTestObject('Object Repository/Mobile/Empty_Return/Global_Reason_options'), 5, FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

Mobile.tap(findTestObject('Object Repository/Mobile/Empty_Return/Global_Reason_options'), 5)

//Select category
GlobalVariable.reasons = findTestData('Mobile Input Data/Empty_Return').getValue('Catagory', 3)

Mobile.waitForElementPresent(findTestObject('Object Repository/Mobile/Empty_Return/Global_dropdown_value_select'), 10, FailureHandling.OPTIONAL)

Mobile.tap(findTestObject('Mobile/Empty_Return/Global_dropdown_value_select'), 5)

Mobile.delay(2)

GlobalVariable.label = findTestData('Mobile Input Data/Empty_Return').getValue('Catagory', 3)

Mobile.verifyElementExist(findTestObject('Object Repository/Mobile/Empty_Return/Global_Reason_options'), 5, FailureHandling.OPTIONAL)

Mobile.verifyElementExist(findTestObject('Object Repository/Mobile/Empty_Return/Global_dropdown_value_select'), 5, FailureHandling.OPTIONAL)

category = Mobile.getText(findTestObject('Mobile/Empty_Return/Global_Reason_options'), 0)

Mobile.verifyMatch(category, findTestData('Mobile Input Data/Empty_Return').getValue('Catagory', 3), false)

Mobile.takeScreenshot()

//Reason Dropdown
GlobalVariable.label = findTestData('Mobile Input Data/Empty_Return').getValue('Reasons', 4)

Mobile.verifyElementExist(findTestObject('Object Repository/Mobile/Empty_Return/Global_Reason_options'), 5, FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

Mobile.tap(findTestObject('Object Repository/Mobile/Empty_Return/Global_Reason_options'), 5)

//Select reason
GlobalVariable.reasons = findTestData('Mobile Input Data/Empty_Return').getValue('Reasons', 1)

Mobile.tap(findTestObject('Mobile/Empty_Return/Global_dropdown_value_select'), 5)

Mobile.delay(2)

GlobalVariable.label = findTestData('Mobile Input Data/Empty_Return').getValue('Reasons', 1)

Mobile.verifyElementExist(findTestObject('Object Repository/Mobile/Empty_Return/Global_Reason_options'), 5, FailureHandling.STOP_ON_FAILURE)

Reasons = Mobile.getText(findTestObject('Mobile/Empty_Return/Global_Reason_options'), 0)

Mobile.verifyMatch(Reasons, findTestData('Mobile Input Data/Empty_Return').getValue('Reasons', 1), false)

Mobile.takeScreenshot()

//Add icon present
Mobile.verifyElementExist(findTestObject('Object Repository/Mobile/Empty_Return/Add_another_Reason_Quantity_icon'), 5, FailureHandling.STOP_ON_FAILURE)

Mobile.tap(findTestObject('Object Repository/Mobile/Empty_Return/Add_another_Reason_Quantity_icon'), 5)

Mobile.delay(2)

Mobile.setText(findTestObject('Mobile/Empty_Return/Piece_enter_field_inx_2'), findTestData('Mobile Input Data/Empty_Return').getValue('Quantity', 2), 0)

Mobile.delay(2)

//2nd reason and qty add
GlobalVariable.label = findTestData('Mobile Input Data/Empty_Return').getValue('Catagory', 1)

Mobile.verifyElementExist(findTestObject('Object Repository/Mobile/Empty_Return/Global_Reason_options'), 5, FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

Mobile.tap(findTestObject('Object Repository/Mobile/Empty_Return/Global_Reason_options'), 5)

//Select category
GlobalVariable.label = findTestData('Mobile Input Data/Empty_Return').getValue('Catagory', 2)

Mobile.tap(findTestObject('Object Repository/Mobile/Empty_Return/Global_Reason_options'), 5)

Mobile.delay(2)

Mobile.verifyElementExist(findTestObject('Object Repository/Mobile/Empty_Return/Global_Reason_options'), 5, FailureHandling.STOP_ON_FAILURE)

secondcategory = Mobile.getText(findTestObject('Mobile/Empty_Return/Global_Reason_options'), 0)

Mobile.verifyMatch(secondcategory, findTestData('Mobile Input Data/Empty_Return').getValue('Catagory', 2), false)

Mobile.takeScreenshot()

//Reason Dropdown
GlobalVariable.label = findTestData('Mobile Input Data/Empty_Return').getValue('Reasons', 4)

Mobile.verifyElementExist(findTestObject('Object Repository/Mobile/Empty_Return/Global_Reason_options'), 5, FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

Mobile.tap(findTestObject('Object Repository/Mobile/Empty_Return/Global_Reason_options'), 5)

//Select category
GlobalVariable.label = findTestData('Mobile Input Data/Empty_Return').getValue('Reasons', 2)

Mobile.tap(findTestObject('Object Repository/Mobile/Empty_Return/Global_Reason_options'), 5)

Mobile.delay(2)

Mobile.verifyElementExist(findTestObject('Object Repository/Mobile/Empty_Return/Global_Reason_options'), 5, FailureHandling.STOP_ON_FAILURE)

secondReasons = Mobile.getText(findTestObject('Mobile/Empty_Return/Global_Reason_options'), 0)

Mobile.verifyMatch(secondReasons, findTestData('Mobile Input Data/Empty_Return').getValue('Reasons', 2), false)

Mobile.comment('Validation for add two resons for single product')

KeywordUtil.logInfo(('First category and reason for product 1 : ' + category) + Reasons)

KeywordUtil.logInfo(('Second category and reason for product 1 : ' + secondcategory) + secondReasons)

KeywordUtil.logInfo('Added two reasons for a single product')

Mobile.takeScreenshot()

//Click done btn
Mobile.tap(findTestObject('Mobile/Common/Btn_Done'), 5)

Mobile.delay(2)

GlobalVariable.label = findTestData('Mobile Input Data/Empty_Return').getValue('SKU_Name', 1)

Product1_Total_Piece_qty = Mobile.getText(findTestObject('Mobile/Empty_Return/Global_Product_qty'), 2)

KeywordUtil.logInfo('Piece_qty : ' + Product1_Total_Piece_qty)

Total_Piece = Integer.parseInt(Product1_Total_Piece_qty)

Piece1 = findTestData('Mobile Input Data/Empty_Return').getValue('Quantity', 1)

Piece2 = findTestData('Mobile Input Data/Empty_Return').getValue('Quantity', 2)

Piece_1_2 = (Integer.parseInt(Piece1) + Integer.parseInt(Piece2))

Mobile.comment('Validate added piece qty for single product')

Mobile.verifyEqual(Total_Piece, Piece_1_2, FailureHandling.STOP_ON_FAILURE)

GlobalVariable.CaseQty = Total_Piece

Mobile.takeScreenshot()

//2nd product empty add
'return empty for second product'
GlobalVariable.label = findTestData('Mobile Input Data/Empty_Return').getValue('SKU_Name', 2)

Mobile.tap(findTestObject('Object Repository/Mobile/Empty_Return/Global_Product_qty'), 100)

Mobile.delay(2)

Mobile.tap(findTestObject('Mobile/Empty_Return/Piece_enter_field'), 10)

Mobile.setText(findTestObject('Mobile/Empty_Return/Piece_enter_field'), findTestData('Mobile Input Data/Empty_Return').getValue('Quantity', 3), 0)

Mobile.delay(2)

GlobalVariable.label = findTestData('Mobile Input Data/Empty_Return').getValue('Catagory', 1)

Mobile.tap(findTestObject('Object Repository/Mobile/Empty_Return/Global_Reason_options'), 5)

//Select category
GlobalVariable.reasons = findTestData('Mobile Input Data/Empty_Return').getValue('Catagory', 3)

Mobile.waitForElementPresent(findTestObject('Object Repository/Mobile/Empty_Return/Global_dropdown_value_select'), 10, FailureHandling.OPTIONAL)

Mobile.tap(findTestObject('Mobile/Empty_Return/Global_dropdown_value_select'), 5)

Mobile.delay(2)

//Reason Dropdown
GlobalVariable.label = findTestData('Mobile Input Data/Empty_Return').getValue('Reasons', 4)

Mobile.tap(findTestObject('Object Repository/Mobile/Empty_Return/Global_Reason_options'), 5)

//Select reason
GlobalVariable.reasons = findTestData('Mobile Input Data/Empty_Return').getValue('Reasons', 1)

Mobile.tap(findTestObject('Mobile/Empty_Return/Global_dropdown_value_select'), 5)

Mobile.delay(2)

Mobile.takeScreenshot()

//Click done btn
Mobile.tap(findTestObject('Mobile/Common/Btn_Done'), 5)

Mobile.delay(2)

GlobalVariable.label = findTestData('Mobile Input Data/Empty_Return').getValue('SKU_Name', 2)

Product2_Total_Piece_qty = Mobile.getText(findTestObject('Mobile/Empty_Return/Global_Product_qty'), 2)

KeywordUtil.logInfo('Piece_qty for product 2 : ' + Product2_Total_Piece_qty)

GlobalVariable.CaseSize = Product2_Total_Piece_qty

exlpath = 'DDF\\Mobile Input data\\Mobile Input data.xlsx'

Sheetname = 'EmptyReturn_Validation'

workbook01 = ExcelKeywords.getWorkbook(exlpath)

sheet1 = ExcelKeywords.getExcelSheet(workbook01, Sheetname)

ExcelKeywords.setValueToCellByIndex(sheet1, 1, 7, Total_Piece)
ExcelKeywords.setValueToCellByIndex(sheet1, 2, 7, Product2_Total_Piece_qty)

ExcelKeywords.saveWorkbook(exlpath, workbook01)

//Click save btn
Mobile.tap(findTestObject('Object Repository/Mobile/Empty_Return/Save_btn'), 5)

Mobile.comment('Validation for save button functionality')

Mobile.verifyElementExist(findTestObject('Object Repository/Mobile/Empty_Return/Saved_Successfully_msg'), 5, FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

Mobile.tap(findTestObject('Object Repository/Mobile/Common/Btn_OK'), 5)

Mobile.tap(findTestObject('Mobile/Common/Icon_Back'), 0, FailureHandling.OPTIONAL)

Mobile.callTestCase(findTestCase('Test Cases/Product_Mobile/Common/Call_Analysis'), [:], FailureHandling.STOP_ON_FAILURE)

Mobile.tap(findTestObject('Mobile/Common/Btn_Menu'), 0)

Mobile.callTestCase(findTestCase('Product_Mobile/Empty_Return/ER_04'), [:], FailureHandling.STOP_ON_FAILURE)

Mobile.delay(10)

