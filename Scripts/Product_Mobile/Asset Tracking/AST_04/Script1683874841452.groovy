import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import org.openqa.selenium.Keys as Keys
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.mobile.keyword.internal.MobileDriverFactory as MobileDriverFactory
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import internal.GlobalVariable as GlobalVariable
import io.appium.java_client.AppiumDriver as KeyEvent
import io.appium.java_client.TouchAction as TouchAction
import io.appium.java_client.android.AndroidDriver as AndroidDriver
import io.appium.java_client.android.AndroidKeyCode as AndroidKeyCode
import org.openqa.selenium.remote.RemoteWebDriver as RemoteWebDriver
import io.appium.java_client.AppiumDriver as AppiumDriver
import java.awt.Robot as Robot
import java.awt.event.KeyEvent as KeyEvent
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
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

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Mobile_Login/VanSeller_Login-With Reset'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('Product_Mobile/Common/Seller_Menu_Selection'), [('MenuName') : findTestData('Mobile Input Data/Common').getValue('Menu', 3), ('SubMenuName') : findTestData('Mobile Input Data/Common').getValue('Menu', 1)], FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('Product_Mobile/Common/TradeCoverage(SelectRetailer)'), [('RetailerName') : findTestData('Web Input Data/Task_Management').getValue('Retailer', 1)], FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('Product_Mobile/Common/StartVisit'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('Product_Mobile/Common/Store_Menu_Selection'), [('MenuName') : findTestData('Mobile Input Data/Common').getValue('Menu', 13), ('SubMenuName') : findTestData('Mobile Input Data/Common').getValue('Menu', 14)], FailureHandling.STOP_ON_FAILURE)

not_run: Mobile.tap(findTestObject('Object Repository/Mobile/Deviation/Trade Coverage Menu'), 3)

not_run: Mobile.tap(findTestObject('Mobile/Common/Icon_Search'), 5)

not_run: GlobalVariable.RetailerName = findTestData('Web Input Data/Task_Management').getValue('Retailer', 1)

not_run: Mobile.setText(findTestObject('Mobile/Common/Field_Search_EnterText'), GlobalVariable.RetailerName, 5)

not_run: Retailer_name = Mobile.getText(findTestObject('Mobile/Seller Task/Retailer'), 0)

not_run: KeywordUtil.logInfo('Retailer_name = ' + Retailer_name)

not_run: Mobile.tap(findTestObject('Mobile/Seller Task/Retailer'), 0)

not_run: Mobile.tap(findTestObject('Mobile/Seller Task/Start Visit button'), 0)

not_run: Mobile.tap(findTestObject('Mobile/Deviation/Location validation yes button'), 5)

not_run: Mobile.delay(2)

not_run: Mobile.swipe(50, 50, 50, 600)

not_run: Mobile.tap(findTestObject('Object Repository/Mobile/Store_Actvy/Menu-StoreCheck'), 10)

not_run: Mobile.delay(2)

not_run: Mobile.tap(findTestObject('Object Repository/Mobile/Asset Tracking/Asset Tracking'), 10)

Mobile.delay(4)

Mobile.waitForElementPresent(findTestObject('Mobile/Asset Tracking/Add_plus_btn_Asset_Tracking'), 10, FailureHandling.OPTIONAL)

Mobile.tap(findTestObject('Mobile/Asset Tracking/Add_plus_btn_Asset_Tracking'), 5, FailureHandling.OPTIONAL)

Mobile.delay(3)

String bound = Mobile.getAttribute(findTestObject('Mobile/Asset Tracking/Choose Asset_field'), 'bounds', 2, FailureHandling.OPTIONAL)

x = Mobile.getAttribute(findTestObject('Mobile/Asset Tracking/Choose Asset_field'), 'x', 2, FailureHandling.OPTIONAL)

Mobile.tap(findTestObject('Mobile/Asset Tracking/Choose Asset_field'), 0)

Mobile.setText(findTestObject('Mobile/Asset Tracking/Choose Asset_field'), findTestData('Mobile Input Data/NewRetailer').getValue('Asset_Name', 2), 0)

String[] arr = bound.split(']')

Start_Y_value = (arr[0])

End_Y_value = (arr[1])

String[] ar = Start_Y_value.split(',')

Start_Y_value = (ar[1])

KeywordUtil.logInfo(Start_Y_value)

String[] ar1 = End_Y_value.split(',')

End_Y_value = (ar1[1])

KeywordUtil.logInfo(End_Y_value)

y_point = (Integer.parseInt(End_Y_value) + 20)

x_point = (Integer.parseInt(x) + 30)

Mobile.delay(4, FailureHandling.STOP_ON_FAILURE)

//480
Mobile.tapAtPosition(x_point, y_point)

Mobile.delay(3, FailureHandling.STOP_ON_FAILURE)

long Code1 = Math.random() * 100000

Codeno = Long.toString(Code1)

println(Codeno)

GlobalVariable.Asset_SR_No = Codeno

Mobile.tap(findTestObject('Mobile/Asset Tracking/Serial_No_field'), 0)

Mobile.setText(findTestObject('Mobile/Asset Tracking/Serial_No_field'), Codeno, 0)

Mobile.delay(2)

Mobile.tap(findTestObject('Mobile/Asset Tracking/Description_field'), 0)

Mobile.setText(findTestObject('Mobile/Asset Tracking/Description_field'), findTestData('Mobile Input Data/NewRetailer').getValue('Description', 1), 0)

Mobile.hideKeyboard(FailureHandling.OPTIONAL)

Mobile.tap(findTestObject('Mobile/Asset Tracking/Rental_Price_field'), 0)

Mobile.setText(findTestObject('Mobile/Asset Tracking/Rental_Price_field'), findTestData('Mobile Input Data/NewRetailer').getValue('Rental', 1), 0)

Mobile.hideKeyboard(FailureHandling.OPTIONAL)

Mobile.tap(findTestObject('Mobile/Asset Tracking/Add_button'), 0)

Mobile.tap(findTestObject('Mobile/Asset Tracking/Add_button'), 0, FailureHandling.OPTIONAL)

Mobile.delay(2)

not_run: Mobile.tap(findTestObject('Mobile/Asset Tracking/Asset_image_view_btn'), 0)

not_run: Mobile.delay(2, FailureHandling.STOP_ON_FAILURE)

Mobile.tap(findTestObject('Mobile/Asset Tracking/Select_Reason_field'), 0)

Mobile.setText(findTestObject('Mobile/Asset Tracking/Select_Reason_field'), findTestData('Mobile Input Data/NewRetailer').getValue('Reason', 3), 0)

Mobile.delay(2, FailureHandling.STOP_ON_FAILURE)

AST_Id = Mobile.getText(findTestObject('Mobile/Asset Tracking/Asset_Tracking_id_get'), 0)

println(AST_Id)

Asset_name = findTestData('Mobile Input Data/NewRetailer').getValue('Asset_Name', 2)

Mobile.verifyMatch(AST_Id, Asset_name, false)

Serial_no = Mobile.getText(findTestObject('Mobile/Asset Tracking/Serial_No_get'), 0)

println(Serial_no)

Mobile.verifyMatch(Serial_no, Codeno, false)

Mobile.tap(findTestObject('Mobile/Asset Tracking/Save_btn_assettracking'), 0)

Mobile.delay(2)

Mobile.tap(findTestObject('Mobile/Common/Icon_Back'), 0, FailureHandling.OPTIONAL)

Mobile.delay(2)

Mobile.tap(findTestObject('Mobile/Common/Btn_OK'), 0)

Mobile.delay(2)

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Call_Analysis'), [:], FailureHandling.STOP_ON_FAILURE)

not_run: Mobile.swipe(50, 50, 50, 600)

not_run: Mobile.tap(findTestObject('Mobile/Seller Task/Close Call1 Button'), 3)

not_run: Mobile.tap(findTestObject('Mobile/CallAnalysis/Select Reason for no order'), 0)

not_run: Mobile.tap(findTestObject('Object Repository/Mobile/CallAnalysis/Others'), 0)

not_run: Mobile.verifyElementVisible(findTestObject('Mobile/CallAnalysis/Remarks'), 0)

not_run: Mobile.setText(findTestObject('Object Repository/Mobile/CallAnalysis/Remarks'), findTestData('Mobile Input Data/CallAnalysis').getValue('Reason', 1), 0, FailureHandling.STOP_ON_FAILURE)

not_run: Mobile.hideKeyboard()

not_run: Mobile.takeScreenshot()

not_run: KeywordUtil.logInfo('Should be able to enter reason if No Order is taken in the visit')

not_run: Mobile.tap(findTestObject('Mobile/CallAnalysis/Close Call'), 0)

not_run: Mobile.tap(findTestObject('Mobile/Common/Btn_OK'), 0)

not_run: Mobile.tap(findTestObject('Mobile/Common/Btn_Menu'), 3)

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Sync'), [:], FailureHandling.STOP_ON_FAILURE)

not_run: Mobile.callTestCase(findTestCase('Product_Mobile/Common/HHT_Transaction_DB'), [:], FailureHandling.STOP_ON_FAILURE)

Mobile.delay(140)

