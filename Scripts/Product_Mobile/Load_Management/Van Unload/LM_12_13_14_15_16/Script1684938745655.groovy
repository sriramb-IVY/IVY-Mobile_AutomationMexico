import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
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
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import org.openqa.selenium.WebElement as WebElement
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.keyword.excel.ExcelKeywords as ExcelKeywords
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import org.openqa.selenium.Keys as Keys

not_run: Mobile.startApplication(GlobalVariable.APK_File, false)

Mobile.tap(findTestObject('Mobile/Common/Btn_Menu'), 0, FailureHandling.OPTIONAL)

if (Mobile.verifyElementExist(findTestObject('Mobile/MainMenu/Menu_Load Management'), 2, FailureHandling.OPTIONAL)) {
    'Load Management menu visible'
} else {
    Mobile.callTestCase(findTestCase('Product_Mobile/Common/Mobile_Login/Relaunch the app'), [:], FailureHandling.CONTINUE_ON_FAILURE)

    Mobile.tap(findTestObject('Mobile/Common/Btn_Menu'), 0)
}

Mobile.tap(findTestObject('Mobile/MainMenu/Menu_Load Management'), 0)

Mobile.tap(findTestObject('Mobile/LoadManagement/SubMenu_Current Stock view'), 0)

Mobile.delay(2, FailureHandling.STOP_ON_FAILURE)

exlpath = 'DDF/Mobile Input data/Mobile Input data.xlsx'

Sheetname = 'VanUnload'

workbook01 = ExcelKeywords.getWorkbook(exlpath)

sheet1 = ExcelKeywords.getExcelSheet(workbook01, Sheetname)

for (int i = 1; i <= 15; i++) {
    ExcelKeywords.setValueToCellByIndex(sheet1, 1, 0, '')

    ExcelKeywords.setValueToCellByIndex(sheet1, 1, 1, '')
}

ExcelKeywords.saveWorkbook(exlpath, workbook01)

for (j = 1; j <= 10; j++) {
    GlobalVariable.label = j

    if (Mobile.verifyElementExist(findTestObject('Mobile/LoadManagement/CurrentStockView/Product(list_Xpath)'), 1, FailureHandling.OPTIONAL)) {
        product = Mobile.getText(findTestObject('Mobile/LoadManagement/CurrentStockView/Product(list_Xpath)'), 1, FailureHandling.OPTIONAL)

        SIH = Mobile.getText(findTestObject('Mobile/LoadManagement/CurrentStockView/SIH(list_Xpath)'), 1, FailureHandling.OPTIONAL)

        String exlpath = 'DDF/Mobile Input data/Mobile Input data.xlsx'

        String Sheetname = 'VanUnload'

        workbook01 = ExcelKeywords.getWorkbook(exlpath)

        sheet1 = ExcelKeywords.getExcelSheet(workbook01, Sheetname)

        ExcelKeywords.setValueToCellByIndex(sheet1, j, 0, product)

        ExcelKeywords.setValueToCellByIndex(sheet1, j, 1, SIH)

        ExcelKeywords.saveWorkbook(exlpath, workbook01)
    }
}

Mobile.tap(findTestObject('Mobile/Common/Icon_Back'), 0)

Mobile.tap(findTestObject('Mobile/Common/Btn_Menu'), 0)

Mobile.tap(findTestObject('Mobile/MainMenu/Menu_End of the Day'), 0)

Mobile.tap(findTestObject('Mobile/End of the Day/SubMenu_Van Unload'), 0)

Mobile.delay(2, FailureHandling.STOP_ON_FAILURE)

Mobile.tap(findTestObject('Mobile/Common/Icon_Search'), 0)

Mobile.hideKeyboard()

Mobile.delay(1, FailureHandling.STOP_ON_FAILURE)

sheet_name = 'VanUnload'

file_name = 'Mobile Input data'

ArrayList<String> ProductName = CustomKeywords.'poi.Automation.GetAllDataRow_Flag.getAllDataRow'(file_name, sheet_name, 'CurrentStockProductName')

ArrayList<String> SIH = CustomKeywords.'poi.Automation.GetAllDataRow_Flag.getAllDataRow'(file_name, sheet_name, 'SIH')

for (int a = 0; a < ProductName.size(); a++) {
    if (ProductName.get(a) != '') {
        GlobalVariable.ProductName = ProductName.get(a)

        GlobalVariable.productvalue = SIH.get(a)

        KeywordUtil.logInfo(GlobalVariable.ProductName)

        Mobile.verifyElementVisible(findTestObject('Mobile/End of the Day/Van Unload/Product(List_Object)'), 0)

        Mobile.verifyElementText(findTestObject('Mobile/End of the Day/Van Unload/Product(List_Object)'), GlobalVariable.ProductName)

        KeywordUtil.logInfo(GlobalVariable.productvalue)

        Mobile.verifyElementVisible(findTestObject('Mobile/End of the Day/Van Unload/SIH(List_Object)'), 0)

        Mobile.verifyElementText(findTestObject('Mobile/End of the Day/Van Unload/SIH(List_Object)'), GlobalVariable.productvalue)
    }
}

KeywordUtil.logInfo('Verified : Current stock view details and van unload details should be same')

Mobile.takeScreenshot()

Mobile.clearText(findTestObject('Mobile/Common/Field_Search_EnterText'), 2, FailureHandling.OPTIONAL)

Mobile.tap(findTestObject('Mobile/Common/Icon_FunnelFilter'), 5)

GlobalVariable.label = findTestData('Mobile Input Data/StockProposal').getValue('Menu', 1)

Mobile.tap(findTestObject('Mobile/Common/FilterScreen-MenuList(Global)'), 5)

GlobalVariable.label = findTestData('Mobile Input Data/StockProposal').getValue('Category', 1)

Mobile.tap(findTestObject('Mobile/Common/FilterScreen-SubMenuList(Global)'), 5)

Mobile.tap(findTestObject('Mobile/Common/Btn_Apply'), 0)

Mobile.delay(2)

Mobile.setText(findTestObject('Mobile/Common/Field_Search_EnterText'), findTestData('Mobile Input Data/VanUnload').getValue('Product_Name', 1), 0)

Mobile.verifyElementVisible(findTestObject('Mobile/End of the Day/Van Unload/Product_name'), 0)

Mobile.verifyElementText(findTestObject('Mobile/End of the Day/Van Unload/Product_name'), findTestData('Mobile Input Data/VanUnload').getValue('Product_Name', 1))

Mobile.takeScreenshot()

KeywordUtil.logInfo('User verified the functionality of the product hierarchy filter icon in the Stock Proposal screen')

Mobile.delay(1)

GlobalVariable.keypadValue = findTestData('Mobile Input Data/VanUnload').getValue('CaseQty', 1)

Mobile.tap(findTestObject('Mobile/End of the Day/Van Unload/Case'), 0, FailureHandling.STOP_ON_FAILURE)

Mobile.tap(findTestObject('Mobile/Common/Global_Number_keypad'), 0)

GlobalVariable.keypadValue = findTestData('Mobile Input Data/VanUnload').getValue('PieceQty', 1)

Mobile.tap(findTestObject('Mobile/End of the Day/Van Unload/Piece'), 0, FailureHandling.STOP_ON_FAILURE)

Mobile.tap(findTestObject('Mobile/Common/Global_Number_keypad'), 0)

Entered_Case_Qty = Mobile.getText(findTestObject('Mobile/End of the Day/Van Unload/Case'), 2, FailureHandling.STOP_ON_FAILURE)

Entered_Piece_Qty = Mobile.getText(findTestObject('Mobile/End of the Day/Van Unload/Piece'), 2, FailureHandling.STOP_ON_FAILURE)

Mobile.verifyMatch(Entered_Case_Qty, findTestData('Mobile Input Data/VanUnload').getValue('CaseQty', 1), false, FailureHandling.STOP_ON_FAILURE)

Mobile.verifyMatch(Entered_Piece_Qty, findTestData('Mobile Input Data/VanUnload').getValue('PieceQty', 1), false, FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

KeywordUtil.logInfo('user can able to enter quantity to the product in the van unload screen')

Mobile.verifyElementVisible(findTestObject('Mobile/End of the Day/Van Unload/Total_SIH'), 0)

Mobile.verifyElementExist(findTestObject('Mobile/End of the Day/Van Unload/Total_SIH'), 0)

Mobile.verifyElementVisible(findTestObject('Mobile/End of the Day/Van Unload/Total_Case'), 0)

Mobile.verifyElementText(findTestObject('Mobile/End of the Day/Van Unload/Total_Case'), findTestData('Mobile Input Data/VanUnload').getValue('CaseQty', 1))

Mobile.verifyElementVisible(findTestObject('Mobile/End of the Day/Van Unload/Total_Piece'), 0)

'the accumulation of the total case and total piece field in the Van Unload screen'
Mobile.verifyElementText(findTestObject('Mobile/End of the Day/Van Unload/Total_Piece'), findTestData('Mobile Input Data/VanUnload').getValue('PieceQty', 1))

Mobile.takeScreenshot()

'Van Unload should be saved successfully'
switch (GlobalVariable.Activation_Key) {
    case GlobalVariable.V155_ActivationKey:
        Mobile.tap(findTestObject('Mobile/End of the Day/Van Unload/Next-Button'), 0)

        AppiumDriver driver = MobileDriverFactory.getDriver()

        Actualtoastmessage = driver.findElementByXPath('//android.widget.Toast[1]').getText()

        Mobile.verifyMatch(Actualtoastmessage, findTestData('Mobile Input Data/VanUnload').getValue('Verify', 1), false, FailureHandling.STOP_ON_FAILURE)

        Mobile.takeScreenshot()

        break
    case GlobalVariable.V158_ActivationKey:
        Mobile.tap(findTestObject('Mobile/End of the Day/Van Unload/Next-Button'), 0)

        Mobile.verifyElementVisible(findTestObject('Mobile/End of the Day/StockProposal/Saved Successfully'), 0)

        Mobile.tap(findTestObject('Mobile/Common/Btn_OK'), 0)

        break
}

Mobile.tap(findTestObject('Mobile/Common/Btn_Menu'), 2, FailureHandling.OPTIONAL)

