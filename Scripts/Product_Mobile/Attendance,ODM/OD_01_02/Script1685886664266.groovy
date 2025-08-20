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

WebUI.callTestCase(findTestCase('Product_Mobile/Common/Seller_Menu_Selection'), [('MenuName') : findTestData('Mobile Input Data/Common').getValue('Menu', 18)
	, ('SubMenuName') : findTestData('Mobile Input Data/Common').getValue('Menu', 19)], FailureHandling.STOP_ON_FAILURE)


odometer = Mobile.getText(findTestObject('Mobile/Odometer/OdometerScreenTitle'), 0)

KeywordUtil.logInfo(odometer)

odometerscreen = findTestData('Mobile Input Data/Odometer').getValue('ScreenHeader', 1)

println(odometerscreen)

Mobile.verifyMatch(odometer, odometerscreen, false)

Mobile.takeScreenshot()

println('Odometer screen should be display!')

Mobile.verifyElementVisible(findTestObject('Mobile/Odometer/LoadVan_No'), 0, FailureHandling.STOP_ON_FAILURE)

VanNum = Mobile.getText(findTestObject('Mobile/Odometer/LoadVan_No'), 0)

GlobalVariable.Vanload_No = findTestData('Mobile Input Data/Odometer').getValue('Van_No', 1)

Mobile.verifyMatch(VanNum, GlobalVariable.Vanload_No, false)

Mobile.takeScreenshot()

KeywordUtil.logInfo(VanNum + '  : Vanload number from web CPG correctly reflected in the odometer screen')

Mobile.setText(findTestObject('Mobile/Odometer/Enter start value'), findTestData('Mobile Input Data/Odometer').getValue(
        'Start_Trip_Value', 1), 5)

Mobile.tap(findTestObject('Mobile/Odometer/Btn_Start Journey'), 0)

popup = Mobile.getText(findTestObject('Mobile/Odometer/Saved Successfully.'), 0)

KeywordUtil.logInfo(popup)

verifypopup = findTestData('Mobile Input Data/Odometer').getValue('Alerts', 1)

KeywordUtil.logInfo(verifypopup)

Mobile.verifyMatch(popup, verifypopup, false)

Mobile.tap(findTestObject('Mobile/Common/Btn_OK'), 0)

Mobile.takeScreenshot()

println('User can enter start trip kilometer value ')

Mobile.tap(findTestObject('Mobile/Odometer/SubMenu_Odometer'), 0)

Mobile.verifyElementVisible(findTestObject('Mobile/Odometer/Start Date'), 0, FailureHandling.STOP_ON_FAILURE)

Mobile.verifyElementVisible(findTestObject('Mobile/Odometer/Start Date Value'), 0, FailureHandling.STOP_ON_FAILURE)

Mobile.verifyElementVisible(findTestObject('Mobile/Odometer/Start Time'), 0, FailureHandling.STOP_ON_FAILURE)

Mobile.verifyElementVisible(findTestObject('Mobile/Odometer/Start Time Value'), 0, FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

KeywordUtil.logInfo('Before starting the trip, VanLoad number should be displayed in odometer screen')

Mobile.tap(findTestObject('Mobile/Common/Icon_Back'), 0)

Mobile.tap(findTestObject('Mobile/Common/Btn_OK'), 0)


