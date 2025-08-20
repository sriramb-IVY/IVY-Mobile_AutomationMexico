import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import internal.GlobalVariable as GlobalVariable

not_run: WebUI.callTestCase(findTestCase('Product_Mobile/Survey/CT/SV_CT_01'), [:], FailureHandling.STOP_ON_FAILURE)

not_run: WebUI.callTestCase(findTestCase('Product_Mobile/Common/Web Login and Navigation/ClearCashe Login'), [:], FailureHandling.STOP_ON_FAILURE)

//WebUI.callTestCase(findTestCase('Product_Mobile/Common/Mobile_Login/PreSeller_Login'), [:], FailureHandling.STOP_ON_FAILURE)
not_run: Mobile.callTestCase(findTestCase('Product_Mobile/Common/Mobile_Login/VanSeller_Login-With Reset'), [:], FailureHandling.STOP_ON_FAILURE)

Mobile.startApplication(GlobalVariable.APK_File, false)

Mobile.tap(findTestObject('Mobile/Common/Btn_Menu'), 0)

//Mobile.swipe(0, 400, 0, 300, FailureHandling.OPTIONAL)
Mobile.tap(findTestObject('Mobile/Seller_2/Survey/Seller survey'), 0)

//Object Repository/Product_Mobile/Mobile Part/Seller_2/Survey/Seller survey
Mobile.takeScreenshot()

Mobile.delay(2)

Question_displayed = Mobile.getText(findTestObject('Mobile/Seller_2/Survey/Question Description'), 0)

KeywordUtil.logInfo(Question_displayed)

WebUI.verifyMatch(Question_displayed, findTestData('Web Input Data/Survey').getValue('Question', 1), false, FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

Mobile.tap(findTestObject('Mobile/Seller_2/Survey/Save_button'), 0)

Mobile.takeScreenshot()

Mobile.delay(2)

Error_popup_text = Mobile.getText(findTestObject('Mobile/Seller_2/Survey/Nodata_toSave_Popup'), 0)

KeywordUtil.logInfo(Error_popup_text)

WebUI.verifyMatch(Error_popup_text, findTestData('Mobile Input data/Survey').getValue('Popup', 1), false, FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

Mobile.tap(findTestObject('Mobile/Common/Btn_OK'), 0)

Mobile.delay(2)

GlobalVariable.input = findTestData('Mobile Input Data/Survey').getValue('Answer_type', 1)

Mobile.tap(findTestObject('Mobile/Seller_2/Survey/Answer_Yes_checkbox'), 0)

Mobile.tap(findTestObject('Mobile/Seller_2/Survey/Save_button'), 2)

Mobile.takeScreenshot()

popup_text = Mobile.getText(findTestObject('Mobile/Seller_2/Survey/Take_Photos_popup'), 0)

KeywordUtil.logInfo(popup_text)

Mobile.verifyMatch(popup_text, findTestData('Mobile Input data/Survey').getValue('Popup', 2), false, FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

Mobile.tap(findTestObject('Mobile/Common/Btn_OK'), 0)

Mobile.delay(2)

Mobile.tap(findTestObject('Mobile/Seller_2/Survey/Photo_Capture_Icon'), 0)

if (Mobile.verifyElementVisible(findTestObject('Mobile/Seller_2/Survey/Takepictures_permission_popup'), 3, FailureHandling.OPTIONAL)) {
    Mobile.tap(findTestObject('Mobile/Seller_2/Survey/Takepictures_permission_popup'), 0)

    Mobile.tap(findTestObject('Mobile/Seller_2/Survey/Photo_Capture_Icon'), 0)
}

Mobile.delay(2)

Mobile.tap(findTestObject('Mobile/Seller_2/Survey/Click_Photo_Icon'), 0)

Mobile.delay(2)

Mobile.tap(findTestObject('Mobile/Seller_2/Survey/RightMark_Image'), 0)

Mobile.tap(findTestObject('Object Repository/Mobile/Mobile Part/Seller_2/Survey/SAVE_button'), 0)

Mobile.tap(findTestObject('Mobile/Seller_2/Survey/Signature_field'), 0)

Mobile.swipe(210, 720, 520, 850, FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

Mobile.tap(findTestObject('Object Repository/Mobile/Seller_2/Survey/Signature_Save_button'), 10)

Saved_popup_text = Mobile.getText(findTestObject('Mobile/Seller_2/Survey/Saved_popup'), 0)

KeywordUtil.logInfo(Saved_popup_text)

WebUI.verifyMatch(Saved_popup_text, findTestData('Mobile Input data/Survey').getValue('Popup', 3), false, FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

Mobile.tap(findTestObject('Mobile/Common/Btn_OK'), 0)

Mobile.tap(findTestObject('Mobile/Common/Btn_Menu'), 0)

