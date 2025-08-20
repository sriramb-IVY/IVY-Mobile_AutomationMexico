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

//Mobile.startApplication(GlobalVariable.APK_File, false)

if (Mobile.verifyElementExist(findTestObject('Object Repository/Mobile/Store_Actvy/Menu-OrderInvoice'), 2, FailureHandling.OPTIONAL)) {
	'order invoice menu visible'
} else {
	Mobile.callTestCase(findTestCase('Product_Mobile/Common/Mobile_Login/Relaunch the app'), [:], FailureHandling.CONTINUE_ON_FAILURE)

	WebUI.callTestCase(findTestCase('Product_Mobile/Common/Seller_Menu_Selection'), [('MenuName') : findTestData('Mobile Input Data/Common').getValue('Menu', 3), ('SubMenuName') : findTestData('Mobile Input Data/Common').getValue('Menu', 1)], FailureHandling.STOP_ON_FAILURE)

	WebUI.callTestCase(findTestCase('Product_Mobile/Common/TradeCoverage(SelectRetailer)'), [('RetailerName') : findTestData('Mobile Input Data/Invoice').getValue('Retailer_Name', 1)], FailureHandling.STOP_ON_FAILURE)

	WebUI.callTestCase(findTestCase('Product_Mobile/Common/StartVisit'), [:], FailureHandling.STOP_ON_FAILURE)
}

WebUI.callTestCase(findTestCase('Product_Mobile/Common/Seller_Menu_Selection'), [('MenuName') : findTestData('Mobile Input Data/Common').getValue('Menu', 3), ('SubMenuName') : findTestData('Mobile Input Data/Common').getValue('Menu', 1)], FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('Product_Mobile/Common/TradeCoverage(SelectRetailer)'), [('RetailerName') : findTestData('Mobile Input Data/Invoice').getValue('Retailer_Name', 1)], FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('Product_Mobile/Common/StartVisit'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('Product_Mobile/Common/Store_Menu_Selection'), [('MenuName') : findTestData('Mobile Input Data/Common').getValue('Menu', 4), ('SubMenuName') : findTestData('Mobile Input Data/Common').getValue('Menu', 1)], FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('Product_Mobile/Common/Clear_Star_Filter_Default_Selection'), [:], FailureHandling.STOP_ON_FAILURE)

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Search_Product'), [('ProductName') : GlobalVariable.ProductName], FailureHandling.STOP_ON_FAILURE)

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Enter_PIECE_Qty'), [('Quantity') : findTestData('Mobile Input Data/Invoice').getValue('Keypad_Number', 2)], FailureHandling.STOP_ON_FAILURE)

Mobile.tap(findTestObject('Mobile/Common/Btn_Next'), 0)

Mobile.callTestCase(findTestCase('Product_Mobile/Common/MustSell_Alert'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('Product_Mobile/Common/Handle_Batch_Screen'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('Product_Mobile/Common/Handle_ApplyingScheme_Screen'), [:], FailureHandling.STOP_ON_FAILURE)

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Handle_Empties_Screen'), [:], FailureHandling.OPTIONAL)

Mobile.verifyElementVisible(findTestObject('Mobile/SummaryScreen/SummaryScreen-EditIcon'), 0)

Mobile.verifyElementVisible(findTestObject('Mobile/SummaryScreen/SummaryScreen-CameraIcon'), 0)

Mobile.verifyElementVisible(findTestObject('Mobile/SummaryScreen/SummaryScreen-SignIcon'), 0)

Mobile.takeScreenshot()

println('Verified : summaryscreen icons')

//TC_20
//new_Sign
Mobile.tap(findTestObject('Mobile/SummaryScreen/SummaryScreen-SignIcon'), 0)

Mobile.verifyElementVisible(findTestObject('Mobile/SummaryScreen/Sign/Signature'), 0)

Mobile.verifyElementVisible(findTestObject('Mobile/SummaryScreen/Sign/Please Sign below'), 0)

Mobile.takeScreenshot()

Mobile.swipe(100, 500, 400, 700, FailureHandling.STOP_ON_FAILURE)

Mobile.swipe(800, 500, 400, 1200, FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

Mobile.tap(findTestObject('Mobile/SummaryScreen/Sign/Next_Icon'), 0)

Mobile.delay(1)

Mobile.tap(findTestObject('Mobile/SummaryScreen/SummaryScreen-SignIcon'), 0)

Mobile.takeScreenshot()

Mobile.verifyElementVisible(findTestObject('Mobile/SummaryScreen/Sign/Signature Already taken'), 0)

KeywordUtil.logInfo(' The user capture signature  in the summary screen')

Mobile.tap(findTestObject('Mobile/Common/Btn_Cancel'), 0)

Mobile.delay(1)

WebUI.callTestCase(findTestCase('Product_Mobile/Common/Handle_Camera'), [:], FailureHandling.STOP_ON_FAILURE)

Mobile.tap(findTestObject('Mobile/SummaryScreen/SummaryScreen-CameraIcon'), 0)

Mobile.takeScreenshot()

Mobile.verifyElementVisible(findTestObject('Mobile/SummaryScreen/Sign/Photo_evidence'), 0)

Mobile.verifyElementVisible(findTestObject('Mobile/SummaryScreen/Sign/Already Photo captured'), 0)

Mobile.takeScreenshot()

KeywordUtil.logInfo(' The user capture Evidence image  in the summary screen')

Mobile.tap(findTestObject('Mobile/Common/Btn_Cancel'), 0, FailureHandling.OPTIONAL)

'Order can be editable or not'
beforeEdit_PieceQty = Mobile.getText(findTestObject('Mobile/SummaryScreen/Summary-Screen-PieceValue'), 0)

Mobile.tap(findTestObject('Mobile/SummaryScreen/SummaryScreen-EditIcon'), 0)

WebUI.callTestCase(findTestCase('Product_Mobile/Common/Clear_Star_Filter_Default_Selection'), [:], FailureHandling.STOP_ON_FAILURE)

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Search_Product'), [('ProductName') : GlobalVariable.ProductName], FailureHandling.STOP_ON_FAILURE)

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Enter_PIECE_Qty'), [('Quantity') : findTestData('Mobile Input Data/Invoice').getValue('Keypad_Number', 3)], FailureHandling.STOP_ON_FAILURE)

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Get_CASE_PIECE_Qty'), [:], FailureHandling.STOP_ON_FAILURE)

AterEdit_PieceQty_orderScreen = GlobalVariable.PieceQty

Mobile.tap(findTestObject('Mobile/Common/Btn_Next'), 0)

Mobile.callTestCase(findTestCase('Product_Mobile/Common/MustSell_Alert'), [:], FailureHandling.STOP_ON_FAILURE)

not_run: Mobile.tap(findTestObject('Mobile/Common/Btn_Next'), 0)

not_run: Mobile.callTestCase(findTestCase('Product_Mobile/Common/MustSell_Alert'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('Product_Mobile/Common/Handle_Batch_Screen'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('Product_Mobile/Common/Handle_ApplyingScheme_Screen'), [:], FailureHandling.STOP_ON_FAILURE)

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Handle_Empties_Screen'), [:], FailureHandling.OPTIONAL)

afterEdit_PieceQty = Mobile.getText(findTestObject('Mobile/SummaryScreen/Summary-Screen-PieceValue'), 0)

Mobile.verifyMatch(GlobalVariable.keypadValue, AterEdit_PieceQty_orderScreen, false, FailureHandling.STOP_ON_FAILURE)

Mobile.verifyNotMatch(beforeEdit_PieceQty, afterEdit_PieceQty, false, FailureHandling.STOP_ON_FAILURE)

Mobile.verifyNotMatch(beforeEdit_PieceQty, AterEdit_PieceQty_orderScreen, false, FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

Mobile.tap(findTestObject('Mobile/SummaryScreen/SummaryScreen-EditIcon'), 0)

Mobile.tap(findTestObject('Mobile/Common/Icon_Back'), 3, FailureHandling.OPTIONAL)

Mobile.tap(findTestObject('Mobile/Common/Btn_OK'), 1, FailureHandling.OPTIONAL)

