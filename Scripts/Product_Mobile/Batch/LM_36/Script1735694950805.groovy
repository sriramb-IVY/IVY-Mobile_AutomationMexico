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

Mobile.startApplication(GlobalVariable.APK_File, false)

Mobile.delay(5)

Mobile.tap(findTestObject('Mobile/Common/Btn_Menu'), 2, FailureHandling.OPTIONAL)

WebUI.callTestCase(findTestCase('Product_Mobile/Common/Seller_Menu_Selection'), [('MenuName') : findTestData('Mobile Input Data/Common').getValue(
            'Menu', 18), ('SubMenuName') : findTestData('Mobile Input Data/Common').getValue('Menu', 20)], FailureHandling.STOP_ON_FAILURE)

if (Mobile.verifyElementVisible(findTestObject('Mobile/LoadManagement/CurrentStockView/No data available'), 1, FailureHandling.OPTIONAL)) {
    Mobile.tap(findTestObject('Mobile/Common/Icon_Back'), 5)

    Mobile.tap(findTestObject('Mobile/Common/Btn_Menu'), 2, FailureHandling.OPTIONAL)

    Mobile.tap(findTestObject('Mobile/MainMenu/Menu_End of the Day'), 0)

    Mobile.tap(findTestObject('Mobile/End of the Day/SubMenu_Van Unload'), 0)

    AppiumDriver driver = MobileDriverFactory.getDriver()

    Actualtoastmessage = driver.findElement(By.xpath('//android.widget.Toast[1]')).getText()

    KeywordUtil.logInfo(Actualtoastmessage)

    Mobile.verifyMatch(Actualtoastmessage, findTestData('Mobile Input Data/VanLoad').getValue('Alerts', 6), false)

    Mobile.takeScreenshot()

    Mobile.callTestCase(findTestCase('Product_Mobile/Batch/CT_Stock_Allocation'), [:], FailureHandling.STOP_ON_FAILURE)

    Mobile.startApplication(GlobalVariable.APK_File, false)

    Mobile.delay(5)

    Mobile.tap(findTestObject('Mobile/Common/Btn_Menu'), 0)

    Mobile.tap(findTestObject('Mobile/MainMenu/Menu_Load Management'), 0)

    Mobile.tap(findTestObject('Mobile/LoadManagement/Vanload/VanLoad_Refresh'), 0)

    Mobile.delay(5, FailureHandling.STOP_ON_FAILURE)

    Mobile.tap(findTestObject('Mobile/LoadManagement/SubMenu-Van Load'), 0)

    GlobalVariable.VanLoad_No = findTestData('Mobile Input Data/VanLoad').getValue('Vanload_No', 1)

    Mobile.scrollToText(GlobalVariable.VanLoad_No, FailureHandling.OPTIONAL)

    Mobile.tap(findTestObject('Mobile/LoadManagement/Vanload/Global_RadioIcon'), 0)

    Mobile.delay(2)

    Mobile.tap(findTestObject('Mobile/LoadManagement/Vanload/VanLoadScreen-Accept Btn'), 0)

    Mobile.tap(findTestObject('Mobile/LoadManagement/Vanload/YES-Btn'), 0)

    AppiumDriver driver1 = MobileDriverFactory.getDriver()

    Actualtoastmessage = driver1.findElement(By.xpath('//android.widget.Toast[1]')).getText()

    KeywordUtil.logInfo(Actualtoastmessage)

    Mobile.verifyMatch(Actualtoastmessage, findTestData('Mobile Input Data/VanLoad').getValue('Alerts', 1), false)

    Mobile.takeScreenshot()

    Mobile.tap(findTestObject('Mobile/LoadManagement/SubMenu_Current Stock view'), 0)
} else {
    'We have current stock so we able to validate van unload screen.'
}

'To verify whether Current stock view screen data is reflected to the Van Unload data screen.'
SKU = findTestData('Mobile Input Data/VanLoad')

SKU_Count = SKU.getRowNumbers()

KeywordUtil.logInfo(SKU_Count.toString())

sheet_name = 'VanLoad'

file_name = 'Mobile Input data'

ArrayList<String> SKU_Name = CustomKeywords.'poi.Automation.GetAllDataRow_Flag.getAllDataRow'(file_name, sheet_name, 'Sku_Name')

List<String> listWithoutDuplicates = new ArrayList<String>(new HashSet(SKU_Name))

System.out.println('List without duplicates: ' + listWithoutDuplicates)

ArrayList<String> SkuName = new ArrayList<String>()

ArrayList<String> BatchName = new ArrayList<String>()

ArrayList<String> PieceValues = new ArrayList<String>()

ArrayList<String> CaseValues = new ArrayList<String>()

Mobile.tap(findTestObject('Object Repository/Mobile/LoadManagement/CurrentStockView/Icon-Expand'), 0)

for (int a = 0; a < listWithoutDuplicates.size(); a++) {
    GlobalVariable.ProductName = listWithoutDuplicates.get(a)

    //    Mobile.tap(findTestObject('Mobile/Common/Icon_Search'), 1, FailureHandling.OPTIONAL)
    //
    //    Mobile.setText(findTestObject('Mobile/Common/Field_Search_EnterText'), GlobalVariable.ProductName, 0)
    //
    //    Mobile.hideKeyboard()
    //	  Mobile.tap(findTestObject('Object Repository/Mobile/LoadManagement/CurrentStockView/Icon-Expand'), 0)
    if (Mobile.verifyElementVisible(findTestObject('Mobile/LoadManagement/CurrentStockView/ProductName(Global)'), 0, FailureHandling.OPTIONAL)) {
        SkuName.add(GlobalVariable.ProductName)

        for (b = 1; b <= SKU_Count; b++) {
            if (listWithoutDuplicates.get(a) == findTestData('Mobile Input Data/VanLoad').getValue('Sku_Name', b)) {
                'Verified -BatchName'
                GlobalVariable.BatchName = findTestData('Mobile Input Data/VanLoad').getValue('Batch_Name', b)

                KeywordUtil.logInfo(GlobalVariable.BatchName)

                Mobile.scrollToText(GlobalVariable.BatchName, FailureHandling.OPTIONAL)

                BatchName.add(GlobalVariable.BatchName)

                String CaseQty = Mobile.getText(findTestObject('Mobile/LoadManagement/CurrentStockView/BatchCaseQty(Global)'), 
                    0)

                CaseValues.add(CaseQty)

                String PieceQty = Mobile.getText(findTestObject('Mobile/LoadManagement/CurrentStockView/BatchPieceQty(Global)'), 
                    0)

                PieceValues.add(PieceQty)
            }
        }
    } // Mobile.tap(findTestObject('Mobile/Common/Icon_X'), 1, FailureHandling.OPTIONAL)
}

Mobile.tap(findTestObject('Mobile/Common/Icon_Back'), 5)

Mobile.tap(findTestObject('Mobile/Common/Btn_Menu'), 2, FailureHandling.OPTIONAL)

WebUI.callTestCase(findTestCase('Product_Mobile/Common/Seller_Menu_Selection'), [('MenuName') : findTestData('Mobile Input Data/Common').getValue(
            'Menu', 25), ('SubMenuName') : findTestData('Mobile Input Data/Common').getValue('Menu', 26)], FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

'To verify whether Current stock view screen data is reflected to the Van Unload data screen.'
Mobile.verifyElementVisible(findTestObject('Mobile/End of the Day/Van Unload/Label_UI/ScreenHeader_Van Unload'), 0)

Mobile.verifyElementVisible(findTestObject('Mobile/LoadManagement/CurrentStockView/Filter_Icon'), 0)

Mobile.verifyElementVisible(findTestObject('Mobile/End of the Day/Van Unload/Label_UI/Icon_MoreOption'), 0)

Mobile.verifyElementVisible(findTestObject('Mobile/End of the Day/Van Unload/Label_UI/Product Name'), 0)

Mobile.verifyElementVisible(findTestObject('Mobile/End of the Day/Van Unload/Label_UI/SC'), 0)

Mobile.verifyElementVisible(findTestObject('Mobile/End of the Day/Van Unload/Label_UI/SP'), 0)

Mobile.verifyElementVisible(findTestObject('Mobile/End of the Day/Van Unload/Label_UI/UC'), 0)

Mobile.verifyElementVisible(findTestObject('Mobile/End of the Day/Van Unload/Label_UI/UP'), 0)

Mobile.verifyElementVisible(findTestObject('Mobile/End of the Day/Van Unload/Label_UI/Total SIH'), 0)

Mobile.verifyElementVisible(findTestObject('Mobile/End of the Day/Van Unload/Label_UI/Total Case'), 0)

Mobile.verifyElementVisible(findTestObject('Mobile/End of the Day/Van Unload/Label_UI/Total Piece'), 0)

Mobile.verifyElementVisible(findTestObject('Mobile/End of the Day/Van Unload/Label_UI/Buttom_Number_Keys'), 0)

Mobile.verifyElementVisible(findTestObject('Mobile/End of the Day/Van Unload/Label_UI/Btn_Save'), 0)

Mobile.takeScreenshot()

Total_SIH = 0

Total_CaseValues = 0

Total_PieceValues = 0

//for (int c = 0; c < SkuName.size(); c++) {
//    Mobile.tap(findTestObject('Mobile/Common/Icon_Search'), 0)
//
//    GlobalVariable.ProductName = listWithoutDuplicates.get(c)
//
//    Mobile.setText(findTestObject('Mobile/Common/Field_Search_EnterText'), GlobalVariable.ProductName, 0)
for (b = 0; b < BatchName.size(); b++) {
    'Verified -BatchName'
    GlobalVariable.BatchName = BatchName.get(b)

    KeywordUtil.logInfo('Batch Name is : ' + GlobalVariable.BatchName)

    KeywordUtil.logInfo('Case Quantity is : ' + CaseValues.get(b))

    KeywordUtil.logInfo('Piece Quantity is : ' + PieceValues.get(b))

    Total_CaseValues += Integer.parseInt(CaseValues.get(b))

    Total_PieceValues += Integer.parseInt(PieceValues.get(b))

    Total_SIH += ((Integer.parseInt(CaseValues.get(b)) * 10) + Integer.parseInt(PieceValues.get(b)))

    Mobile.verifyElementText(findTestObject('Object Repository/Mobile/End of the Day/Van Unload/Fields/SIH_CS_Value(Global)'), 
        CaseValues.get(b))

    Mobile.verifyElementText(findTestObject('Object Repository/Mobile/End of the Day/Van Unload/Fields/SIH_Value(Global)'), 
        PieceValues.get(b))
}

//}
Mobile.takeScreenshot()

Mobile.verifyElementText(findTestObject('Mobile/End of the Day/Van Unload/Fields/Total_SIH_Value'), Total_SIH.toString())

'1. "Please select any item" toast message should be displayed when tap row number keys without select case or piece field'
Mobile.tap(findTestObject('Mobile/End of the Day/Van Unload/Label_UI/Btn_Save'), 0)

AppiumDriver driver1 = MobileDriverFactory.getDriver()

Actualtoastmessage = driver1.findElement(By.xpath('//android.widget.Toast[1]')).getText()

KeywordUtil.logInfo(Actualtoastmessage)

Mobile.verifyMatch(Actualtoastmessage, findTestData('Mobile Input Data/VanLoad').getValue('Alerts', 4), false)

Mobile.takeScreenshot()

'2. "No Data to be Save" toast message should be displayed when tap the save button without entering data.'
GlobalVariable.keypadValue = 1

Mobile.tap(findTestObject('Object Repository/Mobile/Common/Global_Number_keypad'), 0)

Actualtoastmessage = driver1.findElement(By.xpath('//android.widget.Toast[1]')).getText()

KeywordUtil.logInfo(Actualtoastmessage)

Mobile.verifyMatch(Actualtoastmessage, findTestData('Mobile Input Data/VanLoad').getValue('Alerts', 5), false)

Mobile.takeScreenshot()

'1. "No data avalaible"  alert should be displayed when any of the data are not saving.'
Mobile.tap(findTestObject('Object Repository/Mobile/End of the Day/Van Unload/Label_UI/Icon_MoreOption'), 0)

Mobile.tap(findTestObject('Object Repository/Mobile/End of the Day/Van Unload/Label_UI/Icon_History_Option'), 0)

Mobile.takeScreenshot()

Mobile.verifyElementVisible(findTestObject('Object Repository/Mobile/End of the Day/Van Unload/No data available-HistoryScreen'), 
    0)

Mobile.tap(findTestObject('Mobile/Common/Btn_OK'), 0)

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

GlobalVariable.BatchName = findTestData('Mobile Input Data/VanLoad').getValue('Batch_Name', 1)

Mobile.setText(findTestObject('Mobile/Common/Field_Search_EnterText'), GlobalVariable.ProductName, 0)

Mobile.verifyElementText(findTestObject('Mobile/End of the Day/Manual Van Load/ProductName'), GlobalVariable.ProductName)

'To verify whether user able to enter the case and piece quantity by tapping buttom row number keys '
Mobile.takeScreenshot()

Mobile.tap(findTestObject('Mobile/End of the Day/Van Unload/Fields/Enter_CaseQty'), 0)

GlobalVariable.keypadValue = 1

Mobile.tap(findTestObject('Object Repository/Mobile/Common/Global_Number_keypad'), 0)

Mobile.takeScreenshot()

Mobile.tap(findTestObject('Mobile/End of the Day/Van Unload/Fields/Enter_PieceQty'), 0)

GlobalVariable.keypadValue = 1

Mobile.tap(findTestObject('Object Repository/Mobile/Common/Global_Number_keypad'), 0)

Mobile.takeScreenshot()

Mobile.verifyElementText(findTestObject('Mobile/End of the Day/Van Unload/Fields/Total_Case_Value'), '1')

Mobile.verifyElementText(findTestObject('Mobile/End of the Day/Van Unload/Fields/Total_Piece_Value'), '1')

Mobile.tap(findTestObject('Mobile/End of the Day/Van Unload/Label_UI/Btn_Save'), 0)

Mobile.takeScreenshot()

Mobile.verifyElementVisible(findTestObject('Web Part/Collection Acceptance/Save Successful Alert'), 0)

Mobile.verifyElementVisible(findTestObject('Mobile/Common/Btn_OK'), 0)

Mobile.tap(findTestObject('Mobile/Common/Btn_OK'), 0)

Mobile.tap(findTestObject('Mobile/End of the Day/SubMenu_Van Unload'), 0)

'To verify whether the Appy SIH option functionality'
Mobile.takeScreenshot()

Mobile.tap(findTestObject('Object Repository/Mobile/End of the Day/Van Unload/Label_UI/Icon_MoreOption'), 0)

Mobile.tap(findTestObject('Object Repository/Mobile/End of the Day/Van Unload/Apply SIH_Option'), 0)

Mobile.takeScreenshot()

Mobile.verifyElementText(findTestObject('Mobile/End of the Day/Van Unload/Fields/Total_Case_Value'), Total_CaseValues.toString())

Mobile.verifyElementText(findTestObject('Mobile/End of the Day/Van Unload/Fields/Total_Piece_Value'), Total_PieceValues.toString())

