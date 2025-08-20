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

////Mobile.startApplication(GlobalVariable.APK_File, false)
////Mobile.tap(findTestObject('XXXXXXXXXXXX/Re-usable/Mobile Login/Menu'), 0)
////8888888888888888
//Mobile.tap(findTestObject('Mobile/Common/Icon_Back'), 0)
//
//Mobile.delay(2, FailureHandling.STOP_ON_FAILURE)
//
//Mobile.tap(findTestObject('Mobile/Common/Icon_Back'), 1, FailureHandling.OPTIONAL)
//
////
not_run: Mobile.startApplication(GlobalVariable.APK_File, false)

not_run: Mobile.tap(findTestObject('XXXXXXXXXXXX/Re-usable/Mobile Login/Menu'), 2, FailureHandling.OPTIONAL)

Mobile.callTestCase(findTestCase('VBL_Mobile/Re-Usables/Select_TradeCoverage_Menu'), [:], FailureHandling.STOP_ON_FAILURE)

Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/MainMenu/Menu_End of the Day'), 0)

Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/End of the Day/Manual Van Load_SubMenu'), 0)

Mobile.delay(2, FailureHandling.STOP_ON_FAILURE)

//
//Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/FilterScreen/Filter-Icon'), 5)
//
//GlobalVariable.label = findTestData('VBL_Mobile Input Data/StockProposal').getValue('Menu', 1)
//
//Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/FilterScreen/FilterScreen-SubMenu(Global)'), 5)
//
//GlobalVariable.label = findTestData('VBL_Mobile Input Data/StockProposal').getValue('Category', 1)
//
//Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/FilterScreen/FilterScreen-SubMenuValue(Global)'), 5)
//
//Mobile.takeScreenshot()
//
//Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/FilterScreen/FilterScreen-Apply btn'), 0)
//
//Mobile.delay(2)
//
//Mobile.takeScreenshot()
Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Reusable Object/Product Search Icon'), 0)

GlobalVariable.ProductName = findTestData('VBL_Mobile Input Data/VanUnload').getValue('ProductName', 1)
WebUI.callTestCase(findTestCase('Product_Mobile/Common/Search_Product'), [:], FailureHandling.STOP_ON_FAILURE)
Mobile.setText(findTestObject('XXXXXXXXXXXX/Mobile Part/Reusable Object/Product Enter Search'), GlobalVariable.ProductName, 0)

Mobile.verifyElementVisible(findTestObject('XXXXXXXXXXXX/Mobile Part/End of the Day/ManualVanload/ProductNameField'), 0)

Mobile.verifyElementVisible(findTestObject('XXXXXXXXXXXX/Mobile Part/End of the Day/ManualVanload/CaseField'), 0)

Mobile.verifyElementVisible(findTestObject('XXXXXXXXXXXX/Mobile Part/End of the Day/ManualVanload/PieceField'), 0)

Mobile.verifyElementVisible(findTestObject('XXXXXXXXXXXX/Mobile Part/End of the Day/ManualVanload/SIH_Field'), 0)

Mobile.takeScreenshot()

println('List of the product displayed with product name and SIH.')

Mobile.verifyElementText(findTestObject('XXXXXXXXXXXX/Mobile Part/End of the Day/ManualVanload/ProductNameField'), GlobalVariable.ProductName)

Mobile.takeScreenshot()

println('User verified the functionality of the product hierarchy filter icon in the manual van load screen')

Mobile.delay(1)

Mobile.hideKeyboard(FailureHandling.OPTIONAL)

Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/End of the Day/ManualVanload/CaseField'), 0, FailureHandling.STOP_ON_FAILURE)

Mobile.setText(findTestObject('XXXXXXXXXXXX/Mobile Part/End of the Day/ManualVanload/CaseField'), findTestData('VBL_Mobile Input Data/VanUnload').getValue('Negative_Qty', 1), 0, FailureHandling.STOP_ON_FAILURE)

Mobile.delay(3)

Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/End of the Day/StockProposal/Button-Save'), 0)

Mobile.verifyElementExist(findTestObject('XXXXXXXXXXXX/Mobile Part/End of the Day/ManualVanload/No Data to Save'), 0)

Mobile.verifyElementVisible(findTestObject('XXXXXXXXXXXX/Mobile Part/End of the Day/ManualVanload/No Data to Save'), 0)

Mobile.takeScreenshot()

println('Negative quantity cannot be added')

Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/End of the Day/StockProposal/Alert-OK Btn'), 0)

Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/End of the Day/ManualVanload/CaseField'), 2, FailureHandling.OPTIONAL)

Mobile.setText(findTestObject('XXXXXXXXXXXX/Mobile Part/End of the Day/ManualVanload/CaseField'), findTestData('VBL_Mobile Input Data/VanUnload').getValue('CaseQty', 1), 0, FailureHandling.STOP_ON_FAILURE)

Mobile.setText(findTestObject('XXXXXXXXXXXX/Mobile Part/End of the Day/ManualVanload/PieceField'), findTestData('VBL_Mobile Input Data/VanUnload').getValue('PieceQty', 1), 0, FailureHandling.STOP_ON_FAILURE)

Entered_Case_Qty = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/End of the Day/ManualVanload/CaseField'), 2, FailureHandling.STOP_ON_FAILURE)

Entered_Piece_Qty = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/End of the Day/ManualVanload/PieceField'), 2, FailureHandling.STOP_ON_FAILURE)

Mobile.verifyMatch(Entered_Case_Qty, findTestData('VBL_Mobile Input Data/VanUnload').getValue('CaseQty', 1), false, FailureHandling.STOP_ON_FAILURE)

Mobile.verifyMatch(Entered_Piece_Qty, findTestData('VBL_Mobile Input Data/VanUnload').getValue('PieceQty', 1), false, FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

println('user can able to enter quantity to the product in the Manual van load screen')

Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/End of the Day/StockProposal/Button-Save'), 0)

Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/End of the Day/StockProposal/Alert-OK Btn'), 0)

AppiumDriver driver = MobileDriverFactory.getDriver()

Actualtoastmessage = driver.findElementByXPath('//android.widget.Toast[1]').getText()

Mobile.verifyMatch(Actualtoastmessage, findTestData('VBL_Mobile Input Data/VanUnload').getValue('Verify', 1), false, FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

println('Van Unload should be saved successfully')

Mobile.tap(findTestObject('XXXXXXXXXXXX/Re-usable/Mobile Login/Menu'), 2, FailureHandling.OPTIONAL)

Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/MainMenu/Menu_Load Management'), 0)

Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/LoadManagement/SubMenu-Van Load'), 0)

String pattern = 'MM-dd-yyyy'

SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern)

String currentdate = simpleDateFormat.format(new Date())

println(currentdate)

current_date = currentdate.replaceAll('\\D+', '')

KeywordUtil.logInfo(current_date)

UserID = GlobalVariable.vanseller_user_id

String Vanload_no = UserID + current_date

KeywordUtil.logInfo(Vanload_no)

GlobalVariable.VanLoad_No = Vanload_no

Mobile.scrollToText(GlobalVariable.VanLoad_No, FailureHandling.OPTIONAL)

GlobalVariable.VanLoad_No = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/LoadManagement/Vanload/Vanload_No(contains)'), 0, FailureHandling.STOP_ON_FAILURE)

Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/LoadManagement/Vanload/Vanload_No(contains)'), 0)

Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Reusable Object/Product Search Icon'), 0)
WebUI.callTestCase(findTestCase('Product_Mobile/Common/Search_Product'), [:], FailureHandling.STOP_ON_FAILURE)
Mobile.setText(findTestObject('XXXXXXXXXXXX/Mobile Part/Reusable Object/Product Enter Search'), GlobalVariable.ProductName, 0)

Mobile.verifyElementVisible(findTestObject('XXXXXXXXXXXX/Mobile Part/LoadManagement/Vanload/VanLoadScreen_SkuTitle'), 5, FailureHandling.STOP_ON_FAILURE)

Mobile.verifyElementText(findTestObject('XXXXXXXXXXXX/Mobile Part/LoadManagement/Vanload/VanLoadScreen_SkuTitle'), GlobalVariable.ProductName, FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

Mobile.tap(findTestObject('Mobile/Common/Icon_Back'), 0)

Mobile.delay(2, FailureHandling.STOP_ON_FAILURE)

Mobile.tap(findTestObject('Mobile/Common/Icon_Back'), 0, FailureHandling.OPTIONAL)

Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/LoadManagement/SubMenu_Current Stock view'), 0)

Mobile.verifyElementExist(findTestObject('XXXXXXXXXXXX/Mobile Part/LoadManagement/CurrentStockView/Title_Current Stock view'), 0)

Mobile.verifyElementVisible(findTestObject('XXXXXXXXXXXX/Mobile Part/LoadManagement/CurrentStockView/Title_Current Stock view'), 0)

Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Reusable Object/Product Search Icon'), 0)
WebUI.callTestCase(findTestCase('Product_Mobile/Common/Search_Product'), [:], FailureHandling.STOP_ON_FAILURE)
Mobile.setText(findTestObject('XXXXXXXXXXXX/Mobile Part/Reusable Object/Product Enter Search'), GlobalVariable.ProductName, 0)

if (Mobile.verifyElementVisible(findTestObject('XXXXXXXXXXXX/Mobile Part/LoadManagement/CurrentStockView/SIH_Value'), 5, FailureHandling.OPTIONAL)) {
    Mobile.takeScreenshot()

    println('that Currwent stock view is displaying')

    Before_SIH_value = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/LoadManagement/CurrentStockView/SIH_Value'), 0)

    println('Before vanload accept_SIH_value  : ' + Before_SIH_value)

    Mobile.tap(findTestObject('Mobile/Common/Icon_Back'), 0)

    Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/LoadManagement/SubMenu-Van Load'), 0)

    Mobile.scrollToText(GlobalVariable.VanLoad_No, FailureHandling.OPTIONAL)

    Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/LoadManagement/Vanload/Global_RadioIcon'), 3, FailureHandling.OPTIONAL)

    Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/LoadManagement/Vanload/VanLoadScreen-Accept Btn'), 3, FailureHandling.OPTIONAL)

    Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/LoadManagement/Vanload/OK Btn'), 3, FailureHandling.OPTIONAL)

    //AppiumDriver driver = MobileDriverFactory.getDriver()
    Actualtoastmessage = driver.findElementByXPath('//android.widget.Toast[1]').getText()

    println(Actualtoastmessage)

    Mobile.verifyMatch(Actualtoastmessage, findTestData('VBL_Mobile Input Data/VanLoad').getValue('Alerts', 1), false)

    Mobile.takeScreenshot()

    println('Verified : that User is able to accept the van load')

    //VL_06 To verify that User is able to accept the van load
    Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/LoadManagement/SubMenu_Current Stock view'), 0)

    Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Reusable Object/Product Search Icon'), 0)
	WebUI.callTestCase(findTestCase('Product_Mobile/Common/Search_Product'), [:], FailureHandling.STOP_ON_FAILURE)
    Mobile.setText(findTestObject('XXXXXXXXXXXX/Mobile Part/Reusable Object/Product Enter Search'), GlobalVariable.ProductName, 0)

    after_SIH_value = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/LoadManagement/CurrentStockView/SIH_Value'), 0)

    KeywordUtil.logInfo('Before vanload accept_SIH_value  : ' + Before_SIH_value)

    KeywordUtil.logInfo('after_SIH_value : ' + after_SIH_value)

    Mobile.verifyGreaterThan(after_SIH_value, Before_SIH_value, FailureHandling.STOP_ON_FAILURE)

    Mobile.takeScreenshot()

    KeywordUtil.logInfo('Manual Vanload functionality Verified :  Current stock view screen SIH qty should be correctly reflected after accepting vanload no' //Mobile.scrollToText(GlobalVariable.VanLoad_No, FailureHandling.OPTIONAL)
        //AppiumDriver driver = MobileDriverFactory.getDriver()
        // Mobile.verifyMatch(Integer.parseInt(SIH_value), case_piece_Qty, false)
        )
} else {
    Mobile.tap(findTestObject('Mobile/Common/Icon_Back'), 0)

    Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/LoadManagement/SubMenu-Van Load'), 0)

    Mobile.scrollToText(GlobalVariable.VanLoad_No, FailureHandling.OPTIONAL)

    Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/LoadManagement/Vanload/Vanload_No(contains)'), 0)

    Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Reusable Object/Product Search Icon'), 0)
	WebUI.callTestCase(findTestCase('Product_Mobile/Common/Search_Product'), [:], FailureHandling.STOP_ON_FAILURE)
    Mobile.setText(findTestObject('XXXXXXXXXXXX/Mobile Part/Reusable Object/Product Enter Search'), GlobalVariable.ProductName, 0)

    case_piece_Qty = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/LoadManagement/Vanload/VL_Piece_Value'), 0)

    println(case_piece_Qty)

    Mobile.tap(findTestObject('Mobile/Common/Icon_Back'), 0)

    Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/LoadManagement/Vanload/Global_RadioIcon'), 3, FailureHandling.OPTIONAL)

    Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/LoadManagement/Vanload/VanLoadScreen-Accept Btn'), 3, FailureHandling.OPTIONAL)

    Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/LoadManagement/Vanload/OK Btn'), 3, FailureHandling.OPTIONAL)

    Actualtoastmessage = driver.findElementByXPath('//android.widget.Toast[1]').getText()

    println(Actualtoastmessage)

    Mobile.verifyMatch(Actualtoastmessage, findTestData('VBL_Mobile Input Data/VanLoad').getValue('Alerts', 1), false)

    Mobile.takeScreenshot()

    println('Verified : that User is able to accept the van load')

    Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/LoadManagement/SubMenu_Current Stock view'), 0)

    Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Reusable Object/Product Search Icon'), 0)

    not_run: Mobile.setText(findTestObject('XXXXXXXXXXXX/Mobile Part/Reusable Object/Product Enter Search'), findTestData('VBL_Mobile Input Data/ManualVanLoad').getValue('Product_Name', 1), 0)

    SIH_value = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/LoadManagement/CurrentStockView/SIH_Value'), 0)

    Case_size = findTestData('VBL_Mobile Input Data/VanUnload').getValue('Conversion_Qty', 1)

    case_piece_Qty = ((Integer.parseInt(Case_size) * Integer.parseInt(Entered_Case_Qty)) + Integer.parseInt(Entered_Piece_Qty))

    KeywordUtil.logInfo('vanload _SIH_value  : ' + case_piece_Qty.toString())

    KeywordUtil.logInfo('after accept_vanload_SIH_value : ' + SIH_value)

    Mobile.verifyEqual(Integer.parseInt(SIH_value), case_piece_Qty, FailureHandling.STOP_ON_FAILURE)

    Mobile.takeScreenshot()

    println('Manual Vanload functionality Verified :  Current stock view screen SIH qty should be correctly reflected after accepting vanload no')
}

Mobile.tap(findTestObject('Mobile/Common/Icon_Back'), 0)

WebUI.callTestCase(findTestCase('VBL_Mobile/Re-Usables/Sync'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('VBL_Mobile/Re-Usables/HHT_Transaction_DB'), [:], FailureHandling.STOP_ON_FAILURE)

