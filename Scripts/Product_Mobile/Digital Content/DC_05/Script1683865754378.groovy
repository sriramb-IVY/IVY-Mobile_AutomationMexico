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


if (Mobile.verifyElementExist(findTestObject('Mobile/Common/Global_SellerMenuName_Selection'), 2, FailureHandling.OPTIONAL)) {
	'order invoice menu visible'
} else {
	Mobile.callTestCase(findTestCase('Product_Mobile/Common/Mobile_Login/Relaunch the app'), [:], FailureHandling.CONTINUE_ON_FAILURE)
}

WebUI.callTestCase(findTestCase('Product_Mobile/Common/Seller_Menu_Selection'), [('MenuName') : findTestData('Mobile Input Data/Common').getValue('Menu', 18)
	, ('SubMenuName') : findTestData('Mobile Input Data/Common').getValue('Menu', 19)], FailureHandling.STOP_ON_FAILURE)

Mobile.tap(findTestObject('Mobile/Digital_Content/IMAGES_Tab'), 0)

Image_Name = Mobile.getText(findTestObject('Mobile/Digital_Content/Image_Name'), 0)

Mobile.verifyMatch(Image_Name, findTestData('Web Input Data/Digital Content').getValue('Name', 1), false)

Mobile.tap(findTestObject('Mobile/Digital_Content/FileView'), 0)

Mobile.verifyElementNotExist(findTestObject('Mobile/Digital_Content/File_Not_Found'), 3)

Mobile.takeScreenshot()

Mobile.pressBack()

Mobile.tap(findTestObject('Mobile/Digital_Content/VIDEO_Tab'), 0)

Video_Name = Mobile.getText(findTestObject('Mobile/Digital_Content/Video_Name'), 0)

Mobile.verifyMatch(Video_Name, findTestData('Web Input Data/Digital Content').getValue('Name', 3), false)

Mobile.tap(findTestObject('Mobile/Digital_Content/FileView'), 0)

Mobile.verifyElementNotExist(findTestObject('Mobile/Digital_Content/File_Not_Found'), 3)

Mobile.delay(10, FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

Mobile.pressBack(FailureHandling.OPTIONAL)

Mobile.tap(findTestObject('Mobile/Digital_Content/PDF_Tab'), 0)

Video_Name = Mobile.getText(findTestObject('Mobile/Digital_Content/PDF_Name'), 0)

Mobile.verifyMatch(Video_Name, findTestData('Web Input Data/Digital Content').getValue('Name', 2), false)

Mobile.tap(findTestObject('Mobile/Digital_Content/FileView'), 0)

Mobile.verifyElementNotExist(findTestObject('Mobile/Digital_Content/File_Not_Found'), 3)

Mobile.takeScreenshot()

Mobile.pressBack(FailureHandling.OPTIONAL)

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Sync'), [:], FailureHandling.STOP_ON_FAILURE)


