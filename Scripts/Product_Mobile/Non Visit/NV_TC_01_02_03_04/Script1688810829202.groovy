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

// Mobile.callTestCase(findTestCase('Product_Mobile/Common/Mobile_Login/VanSeller_Login-With Reset'), [:], FailureHandling.STOP_ON_FAILURE)
Mobile.startApplication(GlobalVariable.APKFile, false)

Mobile.tap(findTestObject('Mobile/Common/Btn_Menu'), 3)

Mobile.tap(findTestObject('Object Repository/Mobile/Deviation/Trade Coverage Menu'), 3)

Mobile.tap(findTestObject('Mobile/Common/Icon_Search'), 0)

Mobile.tap(findTestObject('Mobile/Common/Field_Search_EnterText'), 0)

GlobalVariable.ProductName = findTestData('Mobile Input Data/Non visit').getValue('Retailer', 1)

Mobile.sendKeys(findTestObject('Mobile/Common/Field_Search_EnterText'), GlobalVariable.ProductName)

Retailer_name = Mobile.getText(findTestObject('Mobile/Seller Task/Retailer'), 0)

KeywordUtil.logInfo('Retailer_name = ' + Retailer_name)

Mobile.verifyMatch(Retailer_name, GlobalVariable.ProductName, false)

Mobile.tap(findTestObject('Mobile/Seller Task/Retailer'), 0)

KeywordUtil.logInfo('verifing non visit button')

Mobile.verifyElementVisible(findTestObject('Object Repository/Mobile/Non Visit/No Visit button'), 0)

Mobile.tap(findTestObject('Object Repository/Mobile/Non Visit/No Visit button'), 0)

KeywordUtil.logInfo('Selecting reason for non visit')

Mobile.verifyElementVisible(findTestObject('Object Repository/Mobile/Non Visit/Others RDbutton'), 0)

Mobile.tap(findTestObject('Object Repository/Mobile/Non Visit/Others RDbutton'), 5)

Mobile.verifyElementVisible(findTestObject('Object Repository/Mobile/Non Visit/Reason Remarks non visit'), 
    0)

Mobile.tap(findTestObject('Object Repository/Mobile/Non Visit/Reason Remarks non visit'), 5)

GlobalVariable.label = findTestData('Mobile Input Data/Non visit').getValue('Reason_Remark', 1)

Mobile.sendKeys(findTestObject('Object Repository/Mobile/Non Visit/Reason Remarks non visit'), GlobalVariable.label)

Mobile.verifyElementVisible(findTestObject('Object Repository/Mobile/Non Visit/Non visit  reason ADD button'), 
    0)

Mobile.takeScreenshot()

Mobile.tap(findTestObject('Object Repository/Mobile/Non Visit/Non visit  reason ADD button'), 5)

Nonvisit_save_text = Mobile.getText(findTestObject('Object Repository/Mobile/Non Visit/Non visit Saved Successfully popup text'), 
    0)

KeywordUtil.logInfo('save_popup = ' + Nonvisit_save_text)

Mobile.takeScreenshot()

Mobile.tap(findTestObject('Mobile/Common/Btn_OK'), 5)

Mobile.tap(findTestObject('Mobile/Common/Btn_Menu'), 3)

Mobile.delay(2, FailureHandling.STOP_ON_FAILURE)

not_run: Mobile.swipe(0, 150, 0, 400, FailureHandling.OPTIONAL)

not_run: Mobile.tap(findTestObject('Mobile/MainMenu/Menu_Sync'), 0)

not_run: Mobile.tap(findTestObject('Mobile/Sync/Btn-SYNCHRONIZATION'), 0)

not_run: Mobile.waitForElementPresent(findTestObject('Mobile/Sync/SyncAlert_Data upload completed Successfully'), 
    20, FailureHandling.OPTIONAL)

not_run: Mobile.tap(findTestObject('Mobile/Common/Btn_OK'), 20, FailureHandling.OPTIONAL)

not_run: Mobile.callTestCase(findTestCase('Product_Mobile/Non Visit/NV_05'), [:], FailureHandling.STOP_ON_FAILURE)

