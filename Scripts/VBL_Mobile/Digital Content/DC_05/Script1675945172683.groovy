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

not_run: Mobile.startApplication(GlobalVariable.APK_File, false)

not_run: Mobile.tap(findTestObject('XXXXXXXXXXXX/Re-usable/Mobile Login/Menu'), 200)

Mobile.callTestCase(findTestCase('VBL_Mobile/Re-Usables/Mobile_Login/VanSeller_Login-With Reset'), [:], FailureHandling.STOP_ON_FAILURE)

Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/MainMenu/Menu_Digital Content'), 200)

Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Digital_Content/IMAGES_Tab'), 0)

Image_Name = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/Digital_Content/Image_Name'), 0)

Mobile.verifyMatch(Image_Name, findTestData('VBL_Web Input Data/Digital Content').getValue('Name', 1), false)

Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Digital_Content/FileView'), 0)

Mobile.verifyElementNotExist(findTestObject('XXXXXXXXXXXX/Mobile Part/Reusable Object/File_Not_Found'), 3)

Mobile.takeScreenshot()

Mobile.pressBack()

Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Digital_Content/VIDEO_Tab'), 0)

Video_Name = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/Digital_Content/Video_Name'), 0)

Mobile.verifyMatch(Video_Name, findTestData('VBL_Web Input Data/Digital Content').getValue('Name', 3), false)

Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Digital_Content/FileView'), 0)

Mobile.verifyElementNotExist(findTestObject('XXXXXXXXXXXX/Mobile Part/Reusable Object/File_Not_Found'), 3)

Mobile.delay(10, FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

Mobile.pressBack(FailureHandling.OPTIONAL)

Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Digital_Content/PDF_Tab'), 0)

Video_Name = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/Digital_Content/PDF_Name'), 0)

Mobile.verifyMatch(Video_Name, findTestData('VBL_Web Input Data/Digital Content').getValue('Name', 2), false)

Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Digital_Content/FileView'), 0)

Mobile.verifyElementNotExist(findTestObject('XXXXXXXXXXXX/Mobile Part/Reusable Object/File_Not_Found'), 3)

Mobile.takeScreenshot()

Mobile.pressBack(FailureHandling.OPTIONAL)

WebUI.callTestCase(findTestCase('VBL_Mobile/Re-Usables/Sync'), [:], FailureHandling.STOP_ON_FAILURE)

