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

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Mobile_Login/VanSeller_Login-With Reset'), [:], FailureHandling.STOP_ON_FAILURE)

Mobile.tap(findTestObject('Mobile/MainMenu/Menu_Load Management'), 0)

Mobile.tap(findTestObject('Mobile/LoadManagement/SubMenu-Van Load'), 0)

KeywordUtil.logInfo('Van Load Reference no  :  ' + GlobalVariable.VanLoad_No)

Mobile.scrollToText(GlobalVariable.VanLoad_No, FailureHandling.OPTIONAL)

Mobile.verifyElementExist(findTestObject('Mobile/LoadManagement/Vanload/VanLoading No-(Global)'), 5)

Mobile.verifyElementVisible(findTestObject('Mobile/LoadManagement/Vanload/VanLoading No-(Global)'), 5)

Mobile.takeScreenshot()

KeywordUtil.logInfo('VERIFIED : User can able to view vanload no')

Mobile.tap(findTestObject('Mobile/LoadManagement/Vanload/VanLoading No-(Global)'), 0)

Mobile.tap(findTestObject('Mobile/Common/Icon_Search'), 0)

Mobile.setText(findTestObject('Mobile/Common/Field_Search_EnterText'), GlobalVariable.ProductName, 0)

//case_Qty = Mobile.getText(findTestObject('Mobile/LoadManagement/Vanload/VL_Case_Value'), 0)
//
//println(case_Qty)
//
//Mobile.verifyMatch(case_Qty, GlobalVariable.CaseQty, false, FailureHandling.OPTIONAL)

SIH_VanloadScreen = Mobile.getText(findTestObject('Mobile/LoadManagement/Vanload/VL_Piece_Value'), 0)

KeywordUtil.logInfo('Piece Value : '+SIH_VanloadScreen)

Mobile.verifyEqual(Integer.parseInt(SIH_VanloadScreen), GlobalVariable.PieceQty, FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

KeywordUtil.logInfo('Verified : Entered Case and Piece Qty in web proposed screen, that same Qty reflected Mobile Vanload screen Properly!')

Mobile.tap(findTestObject('Mobile/Common/Icon_Back'), 0)

Mobile.delay(2, FailureHandling.STOP_ON_FAILURE)

Mobile.tap(findTestObject('Mobile/Common/Icon_Back'), 0)

//Mobile.startApplication(GlobalVariable.APK_File, false)

Mobile.tap(findTestObject('Mobile/Common/Btn_Menu'), 2, FailureHandling.OPTIONAL)

Mobile.tap(findTestObject('Mobile/MainMenu/Menu_End of the Day'), 0)

Mobile.tap(findTestObject('Mobile/End of the Day/SubMenu_Manual Van Load'), 0)

Mobile.delay(2, FailureHandling.STOP_ON_FAILURE)

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

        Y_Value = (Integer.parseInt(y) + Integer.parseInt(height) + 25)

        Mobile.tapAtPosition(100, Y_Value, FailureHandling.STOP_ON_FAILURE)

        break
}

Mobile.verifyElementExist(findTestObject('Mobile/End of the Day/Manual Van Load/ProductName'), 0)

Mobile.verifyElementVisible(findTestObject('Mobile/End of the Day/Manual Van Load/ProductName'), 0)

Mobile.verifyElementExist(findTestObject('Mobile/End of the Day/Manual Van Load/SIH'), 0)

Mobile.verifyElementVisible(findTestObject('Mobile/End of the Day/Manual Van Load/SIH'), 0)

Mobile.takeScreenshot()

KeywordUtil.logInfo('List of the product displayed with product name and SIH.')

Mobile.tap(findTestObject('Mobile/Common/Icon_FunnelFilter'), 5)

GlobalVariable.label = findTestData('Mobile Input Data/StockProposal').getValue('Menu', 1)

Mobile.tap(findTestObject('Mobile/Common/FilterScreen-MenuList(Global)'), 5)

GlobalVariable.label = findTestData('Mobile Input Data/StockProposal').getValue('Category', 1)

Mobile.tap(findTestObject('Mobile/Common/FilterScreen-SubMenuList(Global)'), 5)

Mobile.takeScreenshot()

Mobile.tap(findTestObject('Mobile/Common/Btn_Apply'), 0)

Mobile.delay(2)

Mobile.takeScreenshot()

Mobile.tap(findTestObject('Mobile/Common/Icon_Search'), 0)

Mobile.setText(findTestObject('Mobile/Common/Field_Search_EnterText'), findTestData('Mobile Input Data/ManualVanLoad').getValue('Product_Name', 1), 0)

Mobile.verifyElementVisible(findTestObject('Mobile/End of the Day/Manual Van Load/ProductName'), 0)

Mobile.verifyElementText(findTestObject('Mobile/End of the Day/Manual Van Load/ProductName'), findTestData('Mobile Input Data/ManualVanLoad').getValue('Product_Name', 1))

Mobile.takeScreenshot()

KeywordUtil.logInfo('User verified the functionality of the product hierarchy filter icon in the manual van load screen')

Mobile.delay(1)

Mobile.hideKeyboard(FailureHandling.OPTIONAL)

Mobile.tap(findTestObject('Mobile/End of the Day/Manual Van Load/Case'), 0, FailureHandling.STOP_ON_FAILURE)

Mobile.setText(findTestObject('Mobile/End of the Day/Manual Van Load/Case'), findTestData('Mobile Input Data/ManualVanLoad').getValue('Negative_Qty', 1), 0, FailureHandling.STOP_ON_FAILURE)

Mobile.delay(3)

Mobile.tap(findTestObject('Mobile/End of the Day/Manual Van Load/Save-btn'), 0)

switch (GlobalVariable.Activation_Key) {
    case GlobalVariable.V155_ActivationKey:
        Mobile.verifyElementExist(findTestObject('Mobile/End of the Day/Manual Van Load/No Data to Save'), 0)

        Mobile.verifyElementVisible(findTestObject('Mobile/End of the Day/Manual Van Load/No Data to Save'), 0)

        Mobile.takeScreenshot()

        println('Negative quantity cannot be added')

        Mobile.tap(findTestObject('Mobile/Common/Btn_OK'), 0)
    case GlobalVariable.V158_ActivationKey:
        AppiumDriver driver = MobileDriverFactory.getDriver()

        Actualtoastmessage = driver.findElementByXPath('//android.widget.Toast[1]').getText()

        Mobile.verifyMatch(Actualtoastmessage, findTestData('Mobile Input Data/ManualVanLoad').getValue('Verify', 2), false, FailureHandling.STOP_ON_FAILURE)

        Mobile.takeScreenshot()
}

Mobile.tap(findTestObject('Mobile/End of the Day/Manual Van Load/Case'), 2, FailureHandling.OPTIONAL)

Mobile.setText(findTestObject('Mobile/End of the Day/Manual Van Load/Case'), findTestData('Mobile Input Data/ManualVanLoad').getValue('CaseQty', 1), 0, FailureHandling.STOP_ON_FAILURE)

Mobile.setText(findTestObject('Mobile/End of the Day/Manual Van Load/Piece'), findTestData('Mobile Input Data/ManualVanLoad').getValue('PieceQty', 1), 0, FailureHandling.STOP_ON_FAILURE)

Entered_Case_Qty = Mobile.getText(findTestObject('Mobile/End of the Day/Manual Van Load/Case'), 2, FailureHandling.STOP_ON_FAILURE)

Entered_Piece_Qty = Mobile.getText(findTestObject('Mobile/End of the Day/Manual Van Load/Piece'), 2, FailureHandling.STOP_ON_FAILURE)

Mobile.verifyMatch(Entered_Case_Qty, findTestData('Mobile Input Data/ManualVanLoad').getValue('CaseQty', 1), false, FailureHandling.STOP_ON_FAILURE)

Mobile.verifyMatch(Entered_Piece_Qty, findTestData('Mobile Input Data/ManualVanLoad').getValue('PieceQty', 1), false, FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

println('user can able to enter quantity to the product in the Manual van load screen')

Mobile.tap(findTestObject('Mobile/End of the Day/Manual Van Load/Save-btn'), 0)

switch (GlobalVariable.Activation_Key) {
    case GlobalVariable.V155_ActivationKey:
        'manual should be saved successfully'
        Mobile.tap(findTestObject('Mobile/Common/Btn_OK'), 0)

        AppiumDriver driver = MobileDriverFactory.getDriver()

        Actualtoastmessage = driver.findElementByXPath('//android.widget.Toast[1]').getText()

        Mobile.verifyMatch(Actualtoastmessage, findTestData('Mobile Input Data/ManualVanLoad').getValue('Verify', 1), false, FailureHandling.STOP_ON_FAILURE)

        Mobile.takeScreenshot()
    case GlobalVariable.V158_ActivationKey:
        Mobile.tap(findTestObject('Object Repository/Mobile/End of the Day/StockProposal/Alert-Yes Btn'), 0)

        Mobile.verifyElementVisible(findTestObject('Mobile/End of the Day/StockProposal/Saved Successfully'), 0)

        Mobile.tap(findTestObject('Mobile/Common/Btn_OK'), 0)
}

Mobile.tap(findTestObject('Mobile/Common/Btn_Menu'), 2, FailureHandling.OPTIONAL)

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

Mobile.tap(findTestObject('Mobile/LoadManagement/Vanload/Vanload_No(contains)'), 0)

Mobile.tap(findTestObject('Mobile/Common/Icon_Search'), 0)

GlobalVariable.ProductName = findTestData('Mobile Input Data/ManualVanLoad').getValue('Product_Name', 1)

Mobile.setText(findTestObject('Mobile/Common/Field_Search_EnterText'), GlobalVariable.ProductName, 0)

Mobile.verifyElementVisible(findTestObject('Mobile/LoadManagement/Vanload/ProductName'), 5, FailureHandling.STOP_ON_FAILURE)

Mobile.verifyElementText(findTestObject('Mobile/LoadManagement/Vanload/ProductName'), GlobalVariable.ProductName, FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

Mobile.tap(findTestObject('Mobile/Common/Icon_Back'), 0)

Mobile.delay(2, FailureHandling.STOP_ON_FAILURE)

Mobile.tap(findTestObject('Mobile/Common/Icon_Back'), 0, FailureHandling.OPTIONAL)

Mobile.tap(findTestObject('Mobile/LoadManagement/SubMenu_Current Stock view'), 0)

Mobile.verifyElementExist(findTestObject('Mobile/LoadManagement/CurrentStockView/Title_Current Stock view'), 0)

Mobile.verifyElementVisible(findTestObject('Mobile/LoadManagement/CurrentStockView/Title_Current Stock view'), 0)

Mobile.tap(findTestObject('Mobile/Common/Icon_Search'), 0)

Mobile.setText(findTestObject('Mobile/Common/Field_Search_EnterText'), GlobalVariable.ProductName, 0)

if (Mobile.verifyElementVisible(findTestObject('Mobile/LoadManagement/CurrentStockView/SIH_Value'), 5, FailureHandling.OPTIONAL)) {
    Mobile.takeScreenshot()

    KeywordUtil.logInfo('that Currwent stock view is displaying')

    Before_SIH_value = Mobile.getText(findTestObject('Mobile/LoadManagement/CurrentStockView/SIH_Value'), 0)

    KeywordUtil.logInfo('Before vanload accept_SIH_value  : ' + Before_SIH_value)

    Mobile.tap(findTestObject('Mobile/Common/Icon_Back'), 0)

    Mobile.tap(findTestObject('Mobile/LoadManagement/SubMenu-Van Load'), 0)

    Mobile.scrollToText(GlobalVariable.VanLoad_No, FailureHandling.OPTIONAL)

    Mobile.tap(findTestObject('Mobile/LoadManagement/Vanload/Global_RadioIcon'), 3, FailureHandling.OPTIONAL)

    Mobile.tap(findTestObject('Mobile/LoadManagement/Vanload/VanLoadScreen-Accept Btn'), 3, FailureHandling.OPTIONAL)

    Mobile.tap(findTestObject('Mobile/LoadManagement/Vanload/OK Btn'), 3, FailureHandling.OPTIONAL)

    AppiumDriver driver = MobileDriverFactory.getDriver()

    Actualtoastmessage = driver.findElementByXPath('//android.widget.Toast[1]').getText()

    KeywordUtil.logInfo(Actualtoastmessage)

    Mobile.verifyMatch(Actualtoastmessage, findTestData('Mobile Input Data/VanLoad').getValue('Alerts', 1), false)

    Mobile.takeScreenshot()

    KeywordUtil.logInfo('Verified : that User is able to accept the van load')

    //VL_06 To verify that User is able to accept the van load
    Mobile.tap(findTestObject('Mobile/LoadManagement/SubMenu_Current Stock view'), 0)

    Mobile.tap(findTestObject('Mobile/Common/Icon_Search'), 0)

    Mobile.setText(findTestObject('Mobile/Common/Field_Search_EnterText'), findTestData('Mobile Input Data/ManualVanLoad').getValue('Product_Name', 1), 0)

    after_SIH_value = Mobile.getText(findTestObject('Mobile/LoadManagement/CurrentStockView/SIH_Value'), 0)

    KeywordUtil.logInfo('Before vanload accept_SIH_value  : ' + Before_SIH_value)

    KeywordUtil.logInfo('after_SIH_value : ' + after_SIH_value)

    Mobile.verifyGreaterThan(after_SIH_value, Before_SIH_value, FailureHandling.STOP_ON_FAILURE)

    Mobile.takeScreenshot()

    KeywordUtil.logInfo('Manual Vanload functionality Verified :  Current stock view screen SIH qty should be correctly reflected after accepting vanload no' //Mobile.scrollToText(GlobalVariable.VanLoad_No, FailureHandling.OPTIONAL)
        )
} else {
    Mobile.tap(findTestObject('Mobile/Common/Icon_Back'), 0)

    Mobile.tap(findTestObject('Mobile/LoadManagement/SubMenu-Van Load'), 0)

    Mobile.scrollToText(GlobalVariable.VanLoad_No, FailureHandling.OPTIONAL)

    Mobile.tap(findTestObject('Mobile/LoadManagement/Vanload/Vanload_No(contains)'), 0)

    Mobile.tap(findTestObject('Mobile/Common/Icon_Search'), 0)

    Mobile.setText(findTestObject('Mobile/Common/Field_Search_EnterText'), GlobalVariable.ProductName, 0)

    case_piece_Qty = Mobile.getText(findTestObject('Mobile/LoadManagement/Vanload/VL_Piece_Value'), 0)

    KeywordUtil.logInfo('Case Piece Qty : '+case_piece_Qty)

    Mobile.tap(findTestObject('Mobile/Common/Icon_Back'), 0)

    Mobile.tap(findTestObject('Mobile/LoadManagement/Vanload/Global_RadioIcon'), 3, FailureHandling.OPTIONAL)

    Mobile.tap(findTestObject('Mobile/LoadManagement/Vanload/VanLoadScreen-Accept Btn'), 3, FailureHandling.OPTIONAL)

    Mobile.tap(findTestObject('Mobile/LoadManagement/Vanload/OK Btn'), 3, FailureHandling.OPTIONAL)

    AppiumDriver driver = MobileDriverFactory.getDriver()

    Actualtoastmessage = driver.findElementByXPath('//android.widget.Toast[1]').getText()

    KeywordUtil.logInfo(Actualtoastmessage)

    Mobile.verifyMatch(Actualtoastmessage, findTestData('Mobile Input Data/VanLoad').getValue('Alerts', 1), false)

    Mobile.takeScreenshot()

    KeywordUtil.logInfo('Verified : that User is able to accept the van load')

    Mobile.tap(findTestObject('Mobile/LoadManagement/SubMenu_Current Stock view'), 0)

    not_run: Mobile.tap(findTestObject('Mobile/Common/Icon_Search'), 0)

    WebUI.callTestCase(findTestCase('Product_Mobile/Common/Search_Product'), [:], FailureHandling.STOP_ON_FAILURE)

    Mobile.setText(findTestObject('Mobile/Common/Field_Search_EnterText'), findTestData('Mobile Input Data/ManualVanLoad').getValue('Product_Name', 1), 0)

    SIH_value = Mobile.getText(findTestObject('Mobile/LoadManagement/CurrentStockView/SIH_Value'), 0)

    Case_size = findTestData('Mobile Input Data/ManualVanLoad').getValue('Conversion_Qty', 1)

    case_piece_Qty = ((Integer.parseInt(Case_size) * Integer.parseInt(Entered_Case_Qty)) + Integer.parseInt(Entered_Piece_Qty))

    KeywordUtil.logInfo('vanload _SIH_value  : ' + case_piece_Qty.toString())

    KeywordUtil.logInfo('after accept_vanload_SIH_value : ' + SIH_value)

    Mobile.verifyEqual(Integer.parseInt(SIH_value), case_piece_Qty, FailureHandling.STOP_ON_FAILURE)

    Mobile.takeScreenshot()

    KeywordUtil.logInfo('Manual Vanload functionality Verified :  Current stock view screen SIH qty should be correctly reflected after accepting vanload no')
}

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Mobile_Login/CloseApplication'), [:], FailureHandling.STOP_ON_FAILURE)

