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
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import org.openqa.selenium.WebElement as WebElement
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Mobile_Login/VanSeller_Login-With Reset'), [:], FailureHandling.STOP_ON_FAILURE)

not_run: Mobile.startApplication(GlobalVariable.APK_File, false)


Mobile.tap(findTestObject('Mobile/MainMenu/Menu_Load Management'), 0)

Mobile.tap(findTestObject('Mobile/LoadManagement/SubMenu-Van Load'), 0)

Mobile.verifyElementVisible(findTestObject('Mobile/LoadManagement/Vanload/VanLoad-Title'), 5)

Mobile.verifyElementText(findTestObject('Mobile/LoadManagement/Vanload/VanLoad-Title'), findTestData('Mobile Input Data/VanLoad').getValue('ScreenHeader', 1), FailureHandling.OPTIONAL)

Mobile.takeScreenshot()

KeywordUtil.logInfo(' To verify that Van load screen is displaying ')

Mobile.verifyElementVisible(findTestObject('Mobile/LoadManagement/Vanload/Date'), 0)

Mobile.verifyElementExist(findTestObject('Mobile/LoadManagement/Vanload/Date'), 0)

Mobile.verifyElementVisible(findTestObject('Mobile/LoadManagement/Vanload/Total_Lines'), 0)

Mobile.verifyElementExist(findTestObject('Mobile/LoadManagement/Vanload/Total_Lines'), 0)

Mobile.verifyElementVisible(findTestObject('Mobile/LoadManagement/Vanload/Total_Value'), 0)

Mobile.verifyElementExist(findTestObject('Mobile/LoadManagement/Vanload/Total_Value'), 0)

Mobile.takeScreenshot()

KeywordUtil.logInfo('Date, total lines and, total value fields should be displayed in the van load screen. ')

GlobalVariable.VanLoad_No = findTestData('Mobile Input Data/VanLoad').getValue('Vanload_No', 1)

//GlobalVariable.VanLoad_No = 'VNSTK//25092023/0032'
Mobile.scrollToText(GlobalVariable.VanLoad_No, FailureHandling.OPTIONAL)

Mobile.verifyElementExist(findTestObject('Mobile/LoadManagement/Vanload/VanLoading No-(Global)'), 5)

Mobile.verifyElementVisible(findTestObject('Mobile/LoadManagement/Vanload/VanLoading No-(Global)'), 5)

Mobile.takeScreenshot()

KeywordUtil.logInfo('Van Load Reference no  :  ' + GlobalVariable.VanLoad_No)

//////
Mobile.tap(findTestObject('Mobile/LoadManagement/Vanload/VanLoading No-(Global)'), 0)

Mobile.tap(findTestObject('Mobile/Common/Icon_Search'), 0)

GlobalVariable.ProductName = findTestData('Mobile Input Data/VanLoad').getValue('Product_Name', 1)

Mobile.setText(findTestObject('Mobile/Common/Field_Search_EnterText'), GlobalVariable.ProductName, 0)

Mobile.verifyElementVisible(findTestObject('Mobile/LoadManagement/Vanload/ProductName'), 5, FailureHandling.STOP_ON_FAILURE)

Mobile.verifyElementVisible(findTestObject('Mobile/LoadManagement/Vanload/VL_Case_Value'), 5, FailureHandling.STOP_ON_FAILURE)

Mobile.verifyElementVisible(findTestObject('Mobile/LoadManagement/Vanload/VL_Piece_Value'), 5, FailureHandling.STOP_ON_FAILURE)

Mobile.verifyElementVisible(findTestObject('Mobile/LoadManagement/Vanload/VL_TotalValueField'), 5)

Mobile.takeScreenshot()

KeywordUtil.logInfo('Verified : that User is able to view SKU qty and Value')

//VL_04 To verify that User is able to view SKU qty and Value
//case_Qty = Mobile.getText(findTestObject('Mobile/LoadManagement/Vanload/VL_Case_Value'), 0)
//
//println(case_Qty)
//
//Mobile.verifyMatch(case_Qty, GlobalVariable.CaseQty, false, FailureHandling.OPTIONAL)
piece_Qty = Mobile.getText(findTestObject('Mobile/LoadManagement/Vanload/VL_Piece_Value'), 0)

println(piece_Qty)

//GlobalVariable.PieceQty = findTestData('Mobile Input Data/VanLoad').getValue('Product_Qty', 1)--155
GlobalVariable.PieceQty = findTestData('Mobile Input Data/VanLoad').getValue('Piece_Qty', 1)

Mobile.verifyEqual(Integer.parseInt(piece_Qty), Integer.parseInt(GlobalVariable.PieceQty), FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

KeywordUtil.logInfo('Verified : Entered Case and Piece Qty in web proposed screen, that same Qty reflected Mobile Vanload screen Properly! ')

Mobile.verifyElementVisible(findTestObject('Object Repository/Mobile/LoadManagement/Vanload/Inner_TotalValue'), 0)

Mobile.verifyElementExist(findTestObject('Object Repository/Mobile/LoadManagement/Vanload/Inner_TotalValue'), 0)

Mobile.takeScreenshot()

KeywordUtil.logInfo('Total value field also should be displayed. ')

Mobile.tap(findTestObject('Mobile/Common/Icon_Back'), 0)

Mobile.delay(2, FailureHandling.STOP_ON_FAILURE)

Mobile.tap(findTestObject('Mobile/Common/Icon_Back'), 0, FailureHandling.OPTIONAL)

Mobile.tap(findTestObject('Mobile/LoadManagement/SubMenu_Current Stock view'), 0)

Mobile.verifyElementExist(findTestObject('Mobile/LoadManagement/CurrentStockView/Title_Current Stock view'), 0)

Mobile.verifyElementVisible(findTestObject('Mobile/LoadManagement/CurrentStockView/Title_Current Stock view'), 0)

WebUI.callTestCase(findTestCase('Product_Mobile/Common/Search_Product'), [('ProductName') : findTestData('Mobile Input Data/VanLoad').getValue('Product_Name', 1)], FailureHandling.STOP_ON_FAILURE)

not_run: Mobile.setText(findTestObject('Mobile/Common/Field_Search_EnterText'), GlobalVariable.ProductName, 0)

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

    Mobile.tap(findTestObject('Object Repository/Mobile/Common/Btn_VL_OK_Yes'), 3, FailureHandling.OPTIONAL)

    AppiumDriver driver = MobileDriverFactory.getDriver()

    Actualtoastmessage = driver.findElementByXPath('//android.widget.Toast[1]').getText()

    KeywordUtil.logInfo(Actualtoastmessage)

    Mobile.verifyMatch(Actualtoastmessage, findTestData('Mobile Input Data/VanLoad').getValue('Alerts', 1), false)

    Mobile.takeScreenshot()

    KeywordUtil.logInfo('Verified : that User is able to accept the van load')

    //VL_06 To verify that User is able to accept the van load 
    Mobile.tap(findTestObject('Mobile/LoadManagement/SubMenu_Current Stock view'), 0)

    WebUI.callTestCase(findTestCase('Product_Mobile/Common/Search_Product'), [('ProductName') : findTestData('Mobile Input Data/VanLoad').getValue('Product_Name', 1)], FailureHandling.STOP_ON_FAILURE)

    after_SIH_value = Mobile.getText(findTestObject('Mobile/LoadManagement/CurrentStockView/SIH_Value'), 0)

    KeywordUtil.logInfo('Before vanload accept_SIH_value  : ' + Before_SIH_value)

    KeywordUtil.logInfo('after_SIH_value : ' + after_SIH_value)

    Mobile.verifyNotMatch(after_SIH_value, Before_SIH_value, false)

    Mobile.takeScreenshot()

    KeywordUtil.logInfo('Verified :  Current stock view screen SIH qty should be correctly reflected after accepting vanload no')
} else {
    Mobile.tap(findTestObject('Mobile/Common/Icon_Back'), 0)

    Mobile.tap(findTestObject('Mobile/LoadManagement/SubMenu-Van Load'), 0)

    Mobile.scrollToText(GlobalVariable.VanLoad_No, FailureHandling.OPTIONAL)

    Mobile.tap(findTestObject('Mobile/LoadManagement/Vanload/VanLoading No-(Global)'), 0)

    Mobile.tap(findTestObject('Mobile/Common/Icon_Search'), 0)

    Mobile.setText(findTestObject('Mobile/Common/Field_Search_EnterText'), GlobalVariable.ProductName, 0)

    case_piece_Qty = Mobile.getText(findTestObject('Mobile/LoadManagement/Vanload/VL_Piece_Value'), 0)

    KeywordUtil.logInfo(case_piece_Qty)

    Mobile.tap(findTestObject('Mobile/Common/Icon_Back'), 0)

    Mobile.tap(findTestObject('Mobile/LoadManagement/Vanload/Global_RadioIcon'), 3, FailureHandling.OPTIONAL)

    Mobile.tap(findTestObject('Mobile/LoadManagement/Vanload/VanLoadScreen-Accept Btn'), 3, FailureHandling.OPTIONAL)

    Mobile.tap(findTestObject('Mobile/Common/Btn_VL_OK_Yes'), 3, FailureHandling.OPTIONAL)

    AppiumDriver driver = MobileDriverFactory.getDriver()

    Actualtoastmessage = driver.findElementByXPath('//android.widget.Toast[1]').getText()

    KeywordUtil.logInfo(Actualtoastmessage)

    Mobile.verifyMatch(Actualtoastmessage, findTestData('Mobile Input Data/VanLoad').getValue('Alerts', 1), false)

    Mobile.takeScreenshot()

    KeywordUtil.logInfo('Verified : that User is able to accept the van load')

    Mobile.tap(findTestObject('Mobile/LoadManagement/SubMenu_Current Stock view'), 0)

    WebUI.callTestCase(findTestCase('Product_Mobile/Common/Search_Product'), [('ProductName') : findTestData('Mobile Input Data/VanLoad').getValue('Product_Name', 1)], FailureHandling.STOP_ON_FAILURE)

    SIH_value = Mobile.getText(findTestObject('Mobile/LoadManagement/CurrentStockView/SIH_Value'), 0)

    KeywordUtil.logInfo('vanload _SIH_value  : ' + case_piece_Qty)

    KeywordUtil.logInfo('after accept_vanload_SIH_value : ' + SIH_value)

    Mobile.verifyMatch(SIH_value, case_piece_Qty, false)

    Mobile.takeScreenshot()

    KeywordUtil.logInfo('Verified :  Current stock view screen SIH qty should be correctly reflected after accepting vanload no')
}

Mobile.tap(findTestObject('Mobile/Common/Icon_FunnelFilter'), 5)

GlobalVariable.label = findTestData('Mobile Input Data/StockProposal').getValue('Menu', 1)

Mobile.tap(findTestObject('Mobile/Common/FilterScreen-MenuList(Global)'), 5)

GlobalVariable.label = findTestData('Mobile Input Data/StockProposal').getValue('Category', 1)

Mobile.tap(findTestObject('Mobile/Common/FilterScreen-SubMenuList(Global)'), 5)

Mobile.tap(findTestObject('Mobile/Common/Btn_Apply'), 0)

Mobile.delay(3)

Mobile.clearText(findTestObject('Mobile/Common/Field_Search_EnterText'), 0)

Mobile.setText(findTestObject('Mobile/Common/Field_Search_EnterText'), findTestData('Mobile Input Data/StockProposal').getValue('Product_Name', 1), 0)

Mobile.verifyElementVisible(findTestObject('Mobile/LoadManagement/CurrentStockView/Product_Name'), 0)

Mobile.verifyElementText(findTestObject('Mobile/LoadManagement/CurrentStockView/Product_Name'), findTestData('Mobile Input Data/StockProposal').getValue('Product_Name', 1))

Mobile.takeScreenshot()

KeywordUtil.logInfo('User verified the functionality of the product hierarchy filter icon in the Current Stock View screen')

Mobile.verifyElementVisible(findTestObject('Mobile/LoadManagement/CurrentStockView/Total'), 0)

Mobile.verifyElementExist(findTestObject('Mobile/LoadManagement/CurrentStockView/Total'), 0)

Mobile.verifyElementExist(findTestObject('Mobile/LoadManagement/CurrentStockView/TotalValue'), 0)

Mobile.verifyElementVisible(findTestObject('Mobile/LoadManagement/CurrentStockView/TotalValue'), 0)

Mobile.takeScreenshot()

KeywordUtil.logInfo('User viewed the Total and buttom Total value fields in the Current Stock View screen')

Mobile.tap(findTestObject('Mobile/Common/Icon_Back'), 2, FailureHandling.OPTIONAL)

Mobile.tap(findTestObject('Mobile/Common/Btn_Menu'), 2, FailureHandling.OPTIONAL)

not_run: Mobile.callTestCase(findTestCase('Product_Mobile/CallAnalysis/Reusable/CT_02'), [:], FailureHandling.STOP_ON_FAILURE)

'Stock refresh functionality'
not_run: Mobile.callTestCase(findTestCase('Product_Mobile/Load_Management/Call_Testcase/SchemeStock'), [:], FailureHandling.STOP_ON_FAILURE)

not_run: Mobile.tap(findTestObject('Mobile/MainMenu/Menu_Load Management'), 0)

not_run: Mobile.tap(findTestObject('Mobile/LoadManagement/SubMenu-Van Load'), 0)

not_run: KeywordUtil.logInfo('Van Load Reference no  :  ' + GlobalVariable.VanLoad_No)

not_run: Mobile.scrollToText(GlobalVariable.VanLoad_No, FailureHandling.OPTIONAL)

not_run: Mobile.takeScreenshot()

not_run: Mobile.verifyElementNotExist(findTestObject('Mobile/LoadManagement/Vanload/VanLoading No-(Global)'), 0, FailureHandling.STOP_ON_FAILURE)

not_run: Mobile.tap(findTestObject('Mobile/Common/Icon_Back'), 2, FailureHandling.OPTIONAL)

not_run: Mobile.tap(findTestObject('Mobile/LoadManagement/Vanload/VanLoad_Refresh'), 0)

not_run: Mobile.tap(findTestObject('Mobile/LoadManagement/Vanload/OK Btn'), 3, FailureHandling.OPTIONAL)

not_run: Mobile.tap(findTestObject('Mobile/LoadManagement/SubMenu-Van Load'), 0)

not_run: KeywordUtil.logInfo('Van Load Reference no  :  ' + GlobalVariable.VanLoad_No)

not_run: Mobile.scrollToText(GlobalVariable.VanLoad_No, FailureHandling.OPTIONAL)

not_run: Mobile.takeScreenshot()

not_run: Mobile.verifyElementExist(findTestObject('Mobile/LoadManagement/Vanload/VanLoading No-(Global)'), 5)

not_run: Mobile.verifyElementVisible(findTestObject('Mobile/LoadManagement/Vanload/VanLoading No-(Global)'), 5)

not_run: Mobile.tap(findTestObject('Mobile/Common/Icon_Back'), 2, FailureHandling.OPTIONAL)

not_run: Mobile.tap(findTestObject('Mobile/Common/Btn_Menu'), 2, FailureHandling.OPTIONAL)

