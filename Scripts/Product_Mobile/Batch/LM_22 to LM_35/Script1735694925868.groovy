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
import java.sql.Connection as Connection
import java.sql.DriverManager as DriverManager
import java.sql.ResultSet as ResultSet
import java.sql.Statement as Statement
import java.text.SimpleDateFormat as SimpleDateFormat
import com.kms.katalon.core.mobile.keyword.internal.MobileDriverFactory as MobileDriverFactory
import io.appium.java_client.AppiumDriver as AppiumDriver
import org.openqa.selenium.WebElement as WebElement
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import org.junit.Assert as Assert
import java.util.ArrayList as ArrayList
import java.util.Collections as Collections
import java.util.Comparator as Comparator
import org.openqa.selenium.By as By
import org.openqa.selenium.WebDriver as WebDriver
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import org.openqa.selenium.chrome.ChromeDriver as ChromeDriver
import com.kms.katalon.keyword.excel.ExcelKeywords as ExcelKeywords
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import java.time.LocalDate as LocalDate
import java.time.format.DateTimeFormatter as DateTimeFormatter
import java.time.format.DateTimeParseException as DateTimeParseException

//Mobile.callTestCase(findTestCase('Product_Mobile/Common/Mobile_Login/VanSeller_Login-With Reset'), [:], FailureHandling.STOP_ON_FAILURE)
Mobile.startApplication(GlobalVariable.APK_File, false)

Mobile.delay(5)

Mobile.tap(findTestObject('Mobile/Common/Btn_Menu'), 2, FailureHandling.OPTIONAL)

Mobile.tap(findTestObject('Mobile/MainMenu/Menu_End of the Day'), 0)

Mobile.tap(findTestObject('Mobile/End of the Day/SubMenu_Manual Van Load'), 0)

Mobile.delay(2, FailureHandling.STOP_ON_FAILURE)

'To verify whether that Manual Vanload screen is displayed while click on Manual Vanload menu'
Mobile.takeScreenshot()

Mobile.verifyElementVisible(findTestObject('Mobile/End of the Day/Manual Van Load/Label_UI/ScreenHeader_Manual Van Load'), 0)

Mobile.verifyElementVisible(findTestObject('Mobile/LoadManagement/CurrentStockView/Filter_Icon'), 0, FailureHandling.OPTIONAL)

Mobile.verifyElementVisible(findTestObject('Mobile/End of the Day/Manual Van Load/Label_UI/Product Name'), 0)

Mobile.verifyElementVisible(findTestObject('Mobile/End of the Day/Manual Van Load/Label_UI/SIH'), 0)

Mobile.verifyElementVisible(findTestObject('Mobile/End of the Day/Manual Van Load/Label_UI/Tot Qty'), 0)

Mobile.verifyElementVisible(findTestObject('Mobile/Common/Icon_Back'), 0)

Mobile.verifyElementVisible(findTestObject('Mobile/Common/Icon_Search'), 0)

Mobile.verifyElementVisible(findTestObject('Mobile/End of the Day/Manual Van Load/Save-btn'), 0)

'To verify whether the functionality of the product hierarchy filter icon and search icon in the Manual Vanload screen'
Mobile.tap(findTestObject('Mobile/Common/Icon_FunnelFilter'), 5)

GlobalVariable.label = findTestData('Mobile Input Data/VanLoad').getValue('Menu', 1)

Mobile.tap(findTestObject('Mobile/Common/FilterScreen-MenuList(Global)'), 5)

GlobalVariable.label = findTestData('Mobile Input Data/VanLoad').getValue('Category', 1)

Mobile.tap(findTestObject('Mobile/Common/FilterScreen-SubMenuList(Global)'), 5)

Mobile.tap(findTestObject('Mobile/Common/Btn_Apply'), 0)

'To verify search icon functionality on the Manual Van Load screen.'
Mobile.tap(findTestObject('Mobile/Common/Icon_Search'), 0)

GlobalVariable.ProductName = findTestData('Mobile Input Data/VanLoad').getValue('Sku_Name', 1)

Mobile.setText(findTestObject('Mobile/Common/Field_Search_EnterText'), GlobalVariable.ProductName, 0)

Mobile.verifyElementText(findTestObject('Mobile/End of the Day/Manual Van Load/ProductName'), GlobalVariable.ProductName)

Mobile.takeScreenshot()

Existing_Tot_Qty = Mobile.getText(findTestObject('Mobile/End of the Day/Manual Van Load/Fields/TotalQty_Value(Global)'), 0, FailureHandling.STOP_ON_FAILURE)

Mobile.verifyEqual(Integer.parseInt(Existing_Tot_Qty), 0, FailureHandling.STOP_ON_FAILURE)

'To verify that the SIH value is correctly displayed for a SKU that already has an existing SIH value in the Current StockView screen.'
Mobile.tap(findTestObject('Mobile/Common/Icon_Back'), 5)

Mobile.tap(findTestObject('Mobile/Common/Btn_Menu'), 2, FailureHandling.OPTIONAL)

Mobile.tap(findTestObject('Mobile/MainMenu/Menu_Load Management'), 0)

Mobile.tap(findTestObject('Mobile/LoadManagement/SubMenu_Current Stock view'), 0)

int Expected_SKU_SIH

if (Mobile.verifyElementVisible(findTestObject('Mobile/LoadManagement/CurrentStockView/No data available'), 1, FailureHandling.OPTIONAL)) {
    'If existing SIH is zero in the current stock view screen, it should also be accurately displayed in the Manual vanload screen.'
    Expected_SKU_SIH = 0
} else {
    'Existing SIH in the current stock view screen should be accurately displayed in the Manual vanload screen.'
    Mobile.tap(findTestObject('Mobile/Common/Icon_Search'), 0)

    GlobalVariable.ProductName = findTestData('Mobile Input Data/VanLoad').getValue('Sku_Name', 1)

    Mobile.setText(findTestObject('Mobile/Common/Field_Search_EnterText'), GlobalVariable.ProductName, 0)

    Mobile.hideKeyboard()

    Case_Qty = Mobile.getText(findTestObject('Mobile/LoadManagement/CurrentStockView/CaseQty(Global)'), 0, FailureHandling.STOP_ON_FAILURE)

    Piece_Qty = Mobile.getText(findTestObject('Mobile/LoadManagement/CurrentStockView/PieceQty(Global)'), 0, FailureHandling.STOP_ON_FAILURE)

    CaseSize = findTestData('Mobile Input Data/VanLoad').getValue('Case_Size', 1)

    Expected_SKU_SIH = ((Integer.parseInt(Case_Qty) * Integer.parseInt(CaseSize)) + Integer.parseInt(Piece_Qty))
}

Mobile.tap(findTestObject('Mobile/Common/Icon_Back'), 5)

Mobile.tap(findTestObject('Mobile/Common/Btn_Menu'), 2, FailureHandling.OPTIONAL)

Mobile.tap(findTestObject('Mobile/MainMenu/Menu_End of the Day'), 0)

Mobile.tap(findTestObject('Mobile/End of the Day/SubMenu_Manual Van Load'), 0)

Mobile.tap(findTestObject('Mobile/Common/Icon_Search'), 0)

Mobile.setText(findTestObject('Mobile/Common/Field_Search_EnterText'), GlobalVariable.ProductName, 0)

Mobile.delay(1)

'To verify that the SIH value is correctly displayed for a SKU that already has an existing SIH value in the Current StockView screen.'
Mobile.takeScreenshot()

Existing_SIH = Mobile.getText(findTestObject('Mobile/End of the Day/Manual Van Load/Fields/SIH_Value(Global)'), 0, FailureHandling.STOP_ON_FAILURE)

Mobile.verifyEqual(Integer.parseInt(Existing_SIH), Expected_SKU_SIH, FailureHandling.STOP_ON_FAILURE)

Mobile.tap(findTestObject('Mobile/End of the Day/Manual Van Load/Select Distributor'), 0)

switch (GlobalVariable.Activation_Key) {
    case GlobalVariable.V155_ActivationKey:
        Mobile.delay(2, FailureHandling.STOP_ON_FAILURE)

        GlobalVariable.label = GlobalVariable.DistributorName

        Mobile.tap(findTestObject('Mobile/End of the Day/Manual Van Load/Ditributor_Dropdown_option'), 0)

        break
    case GlobalVariable.V158_ActivationKey:
        y = Mobile.getAttribute(findTestObject('Mobile/End of the Day/Manual Van Load/Select Distributor'), 'y', 0)

        height = Mobile.getAttribute(findTestObject('Mobile/End of the Day/Manual Van Load/Select Distributor'), 'height', 0)

        Mobile.setText(findTestObject('Mobile/End of the Day/Manual Van Load/Select Distributor'), GlobalVariable.DistributorName, 0, FailureHandling.STOP_ON_FAILURE)

        Mobile.delay(2, FailureHandling.STOP_ON_FAILURE)

        Y_Value = ((Integer.parseInt(y) + Integer.parseInt(height)) + 25)

        Mobile.tapAtPosition(100, Y_Value, FailureHandling.STOP_ON_FAILURE)

        break
}

Mobile.tap(findTestObject('Mobile/Common/Icon_X'), 0)

'To verify whether click save button without enter any data'
Mobile.tap(findTestObject('Mobile/End of the Day/Manual Van Load/Save-btn'), 0)

AppiumDriver driver = MobileDriverFactory.getDriver()

Actualtoastmessage = driver.findElementByXPath('//android.widget.Toast[1]').getText()

KeywordUtil.logInfo(Actualtoastmessage)

Mobile.verifyMatch(Actualtoastmessage, findTestData('Mobile Input Data/VanLoad').getValue('Alerts', 4), false)

Mobile.takeScreenshot()

'To verify whether Batch Allocation screen is displayed when tap the Inner view icon for SKU.'
GlobalVariable.ProductName = findTestData('Mobile Input Data/VanLoad').getValue('Sku_Name', 2)

Mobile.tap(findTestObject('Mobile/Common/Icon_Search'), 0)

Mobile.setText(findTestObject('Mobile/Common/Field_Search_EnterText'), GlobalVariable.ProductName, 0)

Mobile.delay(1)

Mobile.takeScreenshot()

Mobile.tap(findTestObject('Mobile/End of the Day/Manual Van Load/Fields/SKUDetails_Inner_View_Icon'), 0)

Mobile.takeScreenshot()

Mobile.verifyElementVisible(findTestObject('Mobile/End of the Day/Manual Van Load/Batch_Allocation/ScreenHeader_Batch Allocation'), 0)

Mobile.verifyElementVisible(findTestObject('Mobile/End of the Day/Manual Van Load/Batch_Allocation/SKU_Name'), 0)

Mobile.verifyElementVisible(findTestObject('Mobile/End of the Day/Manual Van Load/Batch_Allocation/SKU_OC'), 0)

Mobile.verifyElementVisible(findTestObject('Mobile/End of the Day/Manual Van Load/Batch_Allocation/SKU_OP'), 0)

Mobile.verifyElementVisible(findTestObject('Mobile/End of the Day/Manual Van Load/Batch_Allocation/SKU_Total Qty'), 0)

Mobile.verifyElementVisible(findTestObject('Mobile/End of the Day/Manual Van Load/Batch_Allocation/Batch_Name'), 0)

Mobile.verifyElementVisible(findTestObject('Mobile/End of the Day/Manual Van Load/Batch_Allocation/Batch_OC'), 0)

Mobile.verifyElementVisible(findTestObject('Mobile/End of the Day/Manual Van Load/Batch_Allocation/Batch_OP'), 0)

Mobile.verifyElementVisible(findTestObject('Mobile/End of the Day/Manual Van Load/Batch_Allocation/Batch_Total'), 0)

Mobile.verifyElementVisible(findTestObject('Mobile/End of the Day/Manual Van Load/Batch_Allocation/Batch_Mfg Date'), 0)

Mobile.verifyElementVisible(findTestObject('Mobile/End of the Day/Manual Van Load/Batch_Allocation/Batch_Exp Date'), 0)

Mobile.verifyElementVisible(findTestObject('Mobile/End of the Day/Manual Van Load/Batch_Allocation/Add New Batch_Btn'), 0)

Mobile.verifyElementVisible(findTestObject('Mobile/Common/Btn_Done'), 0)

Mobile.verifyElementVisible(findTestObject('Mobile/End of the Day/Van Unload/Label_UI/Buttom_Number_Keys'), 0)

'To verify whether SKU name and SKU batch-wise list are displayed on the Batch Allocation screen'
Mobile.takeScreenshot()

GlobalVariable.ProductName = findTestData('Mobile Input Data/VanLoad').getValue('Sku_Name', 2)

Mobile.verifyElementVisible(findTestObject('Mobile/End of the Day/Manual Van Load/Batch_Allocation/SKU_Name'), 0)

GlobalVariable.BatchName = findTestData('Mobile Input Data/VanLoad').getValue('Batch_Name', 2)

Mobile.verifyElementVisible(findTestObject('Mobile/End of the Day/Manual Van Load/Batch_Allocation/Batch_Name'), 0)

GlobalVariable.BatchName = findTestData('Mobile Input Data/VanLoad').getValue('Batch_Name', 3)

Mobile.verifyElementVisible(findTestObject('Mobile/End of the Day/Manual Van Load/Batch_Allocation/Batch_Name'), 0)

'To verify whether SKU batch wise ExpDate and MfdDate is displayed  in the batch allocation screen from the adm_product_batch table'
queryString = (('select * from adm_product_batch where APB_Code =\'' + GlobalVariable.BatchName) + '\' and APB_IsActive = 1 ')

KeywordUtil.logInfo(queryString)

String conn = (((((('jdbc:sqlserver://' + GlobalVariable.DB_URL) + ';databaseName=') + GlobalVariable.DB_Name) + ';user=') + GlobalVariable.DB_Username) + ';password=') + GlobalVariable.DB_Password

connection = DriverManager.getConnection(conn)

Statement stm = connection.createStatement()

ResultSet rs = stm.executeQuery(queryString)

def recordSet = rs

while (recordSet.next()) {
    String Production_Date = recordSet.getObject('APB_Production_Date')

    KeywordUtil.logInfo(Production_Date)

    String DB_Mfg_Date = Production_Date.split(' ')[0]

    Actual_mfgDate = Mobile.getText(findTestObject('Mobile/End of the Day/Manual Van Load/Batch_Allocation/Field_Batch_MfgDate'), 0, FailureHandling.STOP_ON_FAILURE)

    DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern('yyyy-MM-dd')

    DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern('yyyy/MM/dd')

    LocalDate date1 = LocalDate.parse(DB_Mfg_Date, formatter1)

    KeywordUtil.logInfo(date1.toString())

    LocalDate date2 = LocalDate.parse(Actual_mfgDate, formatter2)

    KeywordUtil.logInfo(date2.toString())

    SameDate = date1.equals(date2)

    KeywordUtil.logInfo(SameDate.toString())

    Assert.assertTrue(SameDate)

    'Validated for Exp.Date'
    String Expiry_Date = recordSet.getObject('APB_Expiry_Date')

    KeywordUtil.logInfo(Expiry_Date)

    String DB_Expiry_Date = Expiry_Date.split(' ')[0]

    Actual_expDate = Mobile.getText(findTestObject('Mobile/End of the Day/Manual Van Load/Batch_Allocation/Field_Batch_ExpDate'), 0, FailureHandling.STOP_ON_FAILURE)

    LocalDate date3 = LocalDate.parse(DB_Expiry_Date, formatter1)

    KeywordUtil.logInfo(date3.toString())

    LocalDate date4 = LocalDate.parse(Actual_expDate, formatter2)

    KeywordUtil.logInfo(date4.toString())

    SameDate = date3.equals(date4)

    KeywordUtil.logInfo(SameDate.toString())

    Assert.assertTrue(SameDate)
}

'To verify whether click bottom row number keys without select any case or piece field'
GlobalVariable.keypadValue = 1

Mobile.tap(findTestObject('Object Repository/Mobile/Common/Global_Number_keypad'), 0)

Actualtoastmessage = driver.findElementByXPath('//android.widget.Toast[1]').getText()

KeywordUtil.logInfo(Actualtoastmessage)

Mobile.verifyMatch(Actualtoastmessage, findTestData('Mobile Input Data/VanLoad').getValue('Alerts', 5), false)

Mobile.takeScreenshot()

'To verify user can enter the case and piece qty in the batch allocation screen on the manual vanload screen'

'Enter Qty for batch-1'
GlobalVariable.BatchName = findTestData('Mobile Input Data/VanLoad').getValue('Batch_Name', 2)

batch_Case_Size = findTestData('Mobile Input Data/VanLoad').getValue('Case_Size', 2)

Mobile.tap(findTestObject('Mobile/End of the Day/Manual Van Load/Batch_Allocation/Field_Batch_Piece'), 0)

GlobalVariable.keypadValue = 1

Mobile.tap(findTestObject('Object Repository/Mobile/Common/Global_Number_keypad'), 0)

Mobile.tap(findTestObject('Mobile/End of the Day/Manual Van Load/Batch_Allocation/Field_Batch_Case'), 0)

GlobalVariable.keypadValue = 2

Mobile.tap(findTestObject('Object Repository/Mobile/Common/Global_Number_keypad'), 0)

Entered_Piece_Qty_B1 = Mobile.getText(findTestObject('Mobile/End of the Day/Manual Van Load/Batch_Allocation/Field_Batch_Piece'), 0, FailureHandling.STOP_ON_FAILURE)

Entered_Case_Qty_B1 = Mobile.getText(findTestObject('Mobile/End of the Day/Manual Van Load/Batch_Allocation/Field_Batch_Case'), 0, FailureHandling.STOP_ON_FAILURE)

Entered_Total_Qty_B1 = Mobile.getText(findTestObject('Mobile/End of the Day/Manual Van Load/Batch_Allocation/Field_Batch_Total'), 0, FailureHandling.STOP_ON_FAILURE)

Expected_BatchWise_Total_Qty_B1 = ((Integer.parseInt(Entered_Case_Qty_B1) * Integer.parseInt(batch_Case_Size)) + Integer.parseInt(Entered_Piece_Qty_B1))

Mobile.verifyEqual(Integer.parseInt(Entered_Total_Qty_B1), Expected_BatchWise_Total_Qty_B1, FailureHandling.STOP_ON_FAILURE)

'Enter Qty for batch-2'
GlobalVariable.BatchName = findTestData('Mobile Input Data/VanLoad').getValue('Batch_Name', 3)

batch_Case_Size = findTestData('Mobile Input Data/VanLoad').getValue('Case_Size', 3)

Mobile.tap(findTestObject('Mobile/End of the Day/Manual Van Load/Batch_Allocation/Field_Batch_Piece'), 0)

Mobile.tap(findTestObject('Mobile/Mobile Part/Reusable Object/Box-Keypad/3-keypad'), 0)

Mobile.tap(findTestObject('Mobile/End of the Day/Manual Van Load/Batch_Allocation/Field_Batch_Case'), 0)

Mobile.tap(findTestObject('Mobile/Mobile Part/Reusable Object/Box-Keypad/4-keypad'), 0)

Entered_Piece_Qty_B2 = Mobile.getText(findTestObject('Mobile/End of the Day/Manual Van Load/Batch_Allocation/Field_Batch_Piece'), 0, FailureHandling.STOP_ON_FAILURE)

Entered_Case_Qty_B2 = Mobile.getText(findTestObject('Mobile/End of the Day/Manual Van Load/Batch_Allocation/Field_Batch_Case'), 0, FailureHandling.STOP_ON_FAILURE)

Entered_Total_Qty_B2 = Mobile.getText(findTestObject('Mobile/End of the Day/Manual Van Load/Batch_Allocation/Field_Batch_Total'), 0, FailureHandling.STOP_ON_FAILURE)

Expected_BatchWise_Total_Qty_B2 = ((Integer.parseInt(Entered_Case_Qty_B2) * Integer.parseInt(batch_Case_Size)) + Integer.parseInt(Entered_Piece_Qty_B2))

Mobile.takeScreenshot()

Mobile.verifyEqual(Integer.parseInt(Entered_Total_Qty_B2), Expected_BatchWise_Total_Qty_B2, FailureHandling.STOP_ON_FAILURE)

'To verify whether SKU case, piece and total quantity field values are correctly updated after entering batch wise case and piece quanity.'
SKU_Case_Qty = Mobile.getText(findTestObject('Mobile/End of the Day/Manual Van Load/Batch_Allocation/Field_SKU_Case'), 0, FailureHandling.STOP_ON_FAILURE)

Expected_SKU_Case_Qty = (Integer.parseInt(Entered_Case_Qty_B1) + Integer.parseInt(Entered_Case_Qty_B2))

Mobile.takeScreenshot()

Mobile.verifyEqual(Integer.parseInt(SKU_Case_Qty), Expected_SKU_Case_Qty, FailureHandling.STOP_ON_FAILURE)

SKU_Piece_Qty = Mobile.getText(findTestObject('Mobile/End of the Day/Manual Van Load/Batch_Allocation/Field_SKU_Piece'), 0, FailureHandling.STOP_ON_FAILURE)

Expected_SKU_Piece_Qty = (Integer.parseInt(Entered_Piece_Qty_B1) + Integer.parseInt(Entered_Piece_Qty_B2))

Mobile.takeScreenshot()

Mobile.verifyEqual(Integer.parseInt(SKU_Piece_Qty), Expected_SKU_Piece_Qty, FailureHandling.STOP_ON_FAILURE)

SKU_Total_Qty = Mobile.getText(findTestObject('Mobile/End of the Day/Manual Van Load/Batch_Allocation/Field_SKU_Total'), 0, FailureHandling.STOP_ON_FAILURE)

Expected_SKU_Total_Qty = (Integer.parseInt(Entered_Total_Qty_B1) + Integer.parseInt(Entered_Total_Qty_B2))

Mobile.takeScreenshot()

Mobile.verifyEqual(Integer.parseInt(SKU_Total_Qty), Expected_SKU_Total_Qty, FailureHandling.STOP_ON_FAILURE)

'To verify whether done button functionality in the batch allocation screen.'
Mobile.tap(findTestObject('Mobile/Common/Btn_Done'), 0)

Mobile.takeScreenshot()

Mobile.verifyElementNotExist(findTestObject('Mobile/End of the Day/Manual Van Load/Batch_Allocation/ScreenHeader_Batch Allocation'), 2, FailureHandling.STOP_ON_FAILURE)

SKU_Total_Quantity = Mobile.getText(findTestObject('Mobile/End of the Day/Manual Van Load/Fields/TotalQty_Value(Global)'), 0, FailureHandling.STOP_ON_FAILURE)

Mobile.verifyEqual(Integer.parseInt(SKU_Total_Quantity), Expected_SKU_Total_Qty, FailureHandling.STOP_ON_FAILURE)

'To verify that the popup screen is displayed when save button is clicked.'
Mobile.tap(findTestObject('Mobile/End of the Day/Manual Van Load/Save-btn'), 0)

Mobile.takeScreenshot()

Mobile.verifyElementVisible(findTestObject('Mobile/End of the Day/Manual Van Load/Do you want to save stock'), 0)

Mobile.verifyElementVisible(findTestObject('Mobile/LoadManagement/Vanload/CANCEL-Btn'), 0)

Mobile.verifyElementVisible(findTestObject('Mobile/LoadManagement/Vanload/YES-Btn'), 0)

'To verify that the CANCEL and YES button functionality while tap save button in the manual van load screen.'
Mobile.tap(findTestObject('Mobile/LoadManagement/Vanload/CANCEL-Btn'), 0)

Mobile.takeScreenshot()

Mobile.verifyElementNotExist(findTestObject('Mobile/End of the Day/Manual Van Load/Do you want to save stock'), 2)

Mobile.tap(findTestObject('Mobile/End of the Day/Manual Van Load/Save-btn'), 0)

Mobile.tap(findTestObject('Mobile/LoadManagement/Vanload/YES-Btn'), 0)

Mobile.takeScreenshot()

Mobile.verifyElementVisible(findTestObject('Web Part/Collection Acceptance/Save Successful Alert'), 0)

Mobile.verifyElementVisible(findTestObject('Mobile/Common/Btn_OK'), 0)

Mobile.tap(findTestObject('Mobile/Common/Btn_OK'), 0)

Mobile.tap(findTestObject('Mobile/Common/Btn_Menu'), 2, FailureHandling.OPTIONAL)

'To verify that the new manual vanload number is correctly displayed on the vanload acceptance screen after saving the data in the manual vanload screen.'
Mobile.tap(findTestObject('Mobile/MainMenu/Menu_Load Management'), 0)

Mobile.tap(findTestObject('Mobile/LoadManagement/SubMenu-Van Load'), 0)

String pattern = 'MM-dd-yyyy'

SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern)

String currentdate = simpleDateFormat.format(new Date())

println(currentdate)

current_date = currentdate.replaceAll('\\D+', '')

KeywordUtil.logInfo(current_date)

UserID = findTestData('Mobile Input Data/ManualVanLoad').getValue('User_ID', 1)

String Vanload_no = UserID + current_date

KeywordUtil.logInfo(Vanload_no)

GlobalVariable.VanLoad_No = Vanload_no

Mobile.scrollToText(GlobalVariable.VanLoad_No, FailureHandling.OPTIONAL)

GlobalVariable.VanLoad_No = Mobile.getText(findTestObject('Mobile/LoadManagement/Vanload/Vanload_No(contains)'), 0, FailureHandling.STOP_ON_FAILURE)

Mobile.verifyElementVisible(findTestObject('Mobile/LoadManagement/Vanload/VanLoading No-(Global)'), 5)

String pattern_02 = 'yyyy/MM/dd'

SimpleDateFormat simpleDateFormat_02 = new SimpleDateFormat(pattern_02)

String currentdate_02 = simpleDateFormat_02.format(new Date())

KeywordUtil.logInfo(currentdate_02)

Mobile.verifyElementText(findTestObject('Mobile/LoadManagement/Vanload/Date(Global))'), currentdate_02)

Mobile.verifyElementText(findTestObject('Mobile/LoadManagement/Vanload/TotalLines(Global)'), '2')

Mobile.tap(findTestObject('Mobile/LoadManagement/Vanload/Vanload_No(contains)'), 0)

Mobile.tap(findTestObject('Mobile/Common/Icon_Search'), 0)

Mobile.setText(findTestObject('Mobile/Common/Field_Search_EnterText'), GlobalVariable.ProductName, 0)

Mobile.verifyElementVisible(findTestObject('Mobile/LoadManagement/Vanload/ProductName(Global)'), 0)

"Batch_01 -Validation"

GlobalVariable.BatchName = findTestData('Mobile Input Data/VanLoad').getValue('Batch_Name', 2)

KeywordUtil.logInfo(GlobalVariable.BatchName)

String BatchName = Mobile.getText(findTestObject('Mobile/LoadManagement/Vanload/BatchName(Global)'), 0)

Boolean present = BatchName.contains(GlobalVariable.BatchName)

Assert.assertTrue(present)

KeywordUtil.logInfo(Entered_Piece_Qty_B1)

Mobile.verifyElementText(findTestObject('Mobile/LoadManagement/Vanload/PieceQty(Global)'), Entered_Piece_Qty_B1, FailureHandling.STOP_ON_FAILURE)

KeywordUtil.logInfo(Entered_Case_Qty_B1)

Mobile.verifyElementText(findTestObject('Mobile/LoadManagement/Vanload/CaseQty(Global)'), Entered_Case_Qty_B1, FailureHandling.STOP_ON_FAILURE)

TotalValueForEachBat = Mobile.getText(findTestObject('Mobile/LoadManagement/Vanload/TotalValueForEachBatch(Global)'), 0)

batch_Case_Size_B1 = findTestData('Mobile Input Data/VanLoad').getValue('Case_Size', 2)

batch_DP_Piece_B1 = findTestData('Mobile Input Data/VanLoad').getValue('DP_Piece', 2)

AppliedQuantity = ((Double.parseDouble(Entered_Case_Qty_B1) * Double.parseDouble(batch_Case_Size_B1)) + Double.parseDouble(Entered_Piece_Qty_B1))

totalvalueforeachbatch_01 = (AppliedQuantity * Double.parseDouble(batch_DP_Piece_B1))

Mobile.verifyEqual(Double.parseDouble(TotalValueForEachBat), totalvalueforeachbatch_01, FailureHandling.STOP_ON_FAILURE)

String AppliedQty_B1 = Mobile.getText(findTestObject('Mobile/LoadManagement/Vanload/BatchName(Global)'), 0)

String[] parts_B1 = AppliedQty_B1.split(':')

String quantity_B1 = (parts_B1[2]).trim()

Mobile.verifyEqual(AppliedQuantity, Double.parseDouble(quantity_B1), FailureHandling.OPTIONAL)

"Batch_02 -Validation"

GlobalVariable.BatchName = findTestData('Mobile Input Data/VanLoad').getValue('Batch_Name', 3)

KeywordUtil.logInfo(GlobalVariable.BatchName)

String BatchName_02 = Mobile.getText(findTestObject('Mobile/LoadManagement/Vanload/BatchName(Global)'), 0)

Boolean present_02 = BatchName_02.contains(GlobalVariable.BatchName)

Assert.assertTrue(present_02)

KeywordUtil.logInfo(Entered_Piece_Qty_B2)

Mobile.verifyElementText(findTestObject('Mobile/LoadManagement/Vanload/PieceQty(Global)'), Entered_Piece_Qty_B2, FailureHandling.STOP_ON_FAILURE)

KeywordUtil.logInfo(Entered_Case_Qty_B2)

Mobile.verifyElementText(findTestObject('Mobile/LoadManagement/Vanload/CaseQty(Global)'), Entered_Case_Qty_B2, FailureHandling.STOP_ON_FAILURE)

TotalValueForEachBat = Mobile.getText(findTestObject('Mobile/LoadManagement/Vanload/TotalValueForEachBatch(Global)'), 0)

batch_Case_Size_B2 = findTestData('Mobile Input Data/VanLoad').getValue('Case_Size', 3)

batch_DP_Piece_B2 = findTestData('Mobile Input Data/VanLoad').getValue('DP_Piece', 3)

AppliedQuantity = ((Double.parseDouble(Entered_Case_Qty_B2) * Double.parseDouble(batch_Case_Size_B2)) + Double.parseDouble(Entered_Piece_Qty_B2))

totalvalueforeachbatch_02 = (AppliedQuantity * Double.parseDouble(batch_DP_Piece_B2))

Mobile.verifyEqual(Double.parseDouble(TotalValueForEachBat), totalvalueforeachbatch_02, FailureHandling.STOP_ON_FAILURE)

String AppliedQty_B2 = Mobile.getText(findTestObject('Mobile/LoadManagement/Vanload/BatchName(Global)'), 0)

String[] parts_B2 = AppliedQty_B2.split(':')

String quantity_B2 = (parts_B2[2]).trim()

Mobile.verifyEqual(AppliedQuantity, Double.parseDouble(quantity_B2), FailureHandling.OPTIONAL)

Mobile.tap(findTestObject('Mobile/Common/Icon_Back'), 0)

Mobile.scrollToText(GlobalVariable.VanLoad_No, FailureHandling.OPTIONAL)

Actual_Total_Value = Mobile.getText(findTestObject('Mobile/LoadManagement/Vanload/TotalValue(Global)'), 0)

totalvalue = totalvalueforeachbatch_01 + totalvalueforeachbatch_02

Mobile.verifyEqual(Double.parseDouble(Actual_Total_Value), totalvalue)

Mobile.tap(findTestObject('Mobile/LoadManagement/Vanload/Global_RadioIcon'), 0)

Mobile.delay(2)

Mobile.tap(findTestObject('Mobile/LoadManagement/Vanload/VanLoadScreen-Accept Btn'), 0)

Mobile.tap(findTestObject('Mobile/LoadManagement/Vanload/YES-Btn'), 0)

AppiumDriver driver1 = MobileDriverFactory.getDriver()

Actualtoastmessage = driver1.findElementByXPath('//android.widget.Toast[1]').getText()

KeywordUtil.logInfo(Actualtoastmessage)

Mobile.verifyMatch(Actualtoastmessage, findTestData('Mobile Input Data/VanLoad').getValue('Alerts', 1), false)

Mobile.takeScreenshot()

