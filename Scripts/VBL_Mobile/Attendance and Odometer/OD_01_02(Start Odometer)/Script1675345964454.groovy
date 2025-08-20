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

not_run: Mobile.callTestCase(findTestCase('VBL_Mobile/Attendance and Odometer/AT_01_02_03(Start Attendance)'), [:], FailureHandling.STOP_ON_FAILURE)

not_run: Mobile.startApplication(GlobalVariable.APK_File, false)

not_run: Mobile.tap(findTestObject('XXXXXXXXXXXX/Re-usable/Mobile Login/Menu'), 0)

if (Mobile.verifyElementExist(findTestObject('XXXXXXXXXXXX/Mobile Part/MainMenu/Menu_Load Management'), 2, FailureHandling.OPTIONAL)) {
    'Load Management menu visible'
} else {
    Mobile.callTestCase(findTestCase('Test Cases/VBL_Mobile/Re-Usables/Mobile_Login/Relaunch APK'), [:], FailureHandling.CONTINUE_ON_FAILURE)

    Mobile.tap(findTestObject('XXXXXXXXXXXX/Re-usable/Mobile Login/Menu'), 0)
}

Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/MainMenu/Menu_Load Management'), 0)

Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Odometer/SubMenu_Odometer'), 0)

odometer = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/Odometer/OdometerScreenTitle'), 0)

println(odometer)

odometerscreen = findTestData('VBL_Mobile Input Data/Odometer').getValue('ScreenHeader', 1)

println(odometerscreen)

Mobile.verifyMatch(odometer, odometerscreen, false)

Mobile.takeScreenshot()

println('Odometer screen should be display!')

Mobile.setText(findTestObject('XXXXXXXXXXXX/Mobile Part/Odometer/Enter start value'), findTestData('VBL_Mobile Input Data/Odometer').getValue(
        'Start_Trip_Value', 1), 5)

Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Odometer/Btn_Start Journey'), 0)

popup = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/Odometer/Saved Successfully.'), 0)

println(popup)

verifypopup = findTestData('VBL_Mobile Input Data/Odometer').getValue('Alerts', 1)

println(verifypopup)

Mobile.verifyMatch(popup, verifypopup, false)

Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Odometer/Saved Successfully- OK'), 0)

Mobile.takeScreenshot()

println('User can enter start trip kilometer value ')

Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Reusable Object/Menu'), 0)

Mobile.delay(3, FailureHandling.STOP_ON_FAILURE)

