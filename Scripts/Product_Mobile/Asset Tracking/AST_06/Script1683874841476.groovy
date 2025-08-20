import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.annotation.Keyword as Keyword
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

SR_NO = GlobalVariable.Asset_SR_No

KeywordUtil.logInfo('serial no is =' + SR_NO)

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Web Login and Navigation/DivisionLogin'), [:], FailureHandling.STOP_ON_FAILURE)

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Web Login and Navigation/Navigate to Asset Transfer Approval'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.waitForElementNotVisible(findTestObject('Web Part/Re-usable/Page Load'), 100)

WebUI.click(findTestObject('Web Part/Asset Approval/Asset_Name_input'))

WebUI.setText(findTestObject('Web Part/Asset Approval/Asset_Name_input'), findTestData('Mobile Input Data/NewRetailer').getValue('Asset_Name', 2))

Mobile.delay(2, FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Web Part/Asset Approval/Serial_No_input'))

WebUI.setText(findTestObject('Web Part/Asset Approval/Serial_No_input'), SR_NO)

Mobile.delay(2, FailureHandling.STOP_ON_FAILURE)

CB_Val = WebUI.getAttribute(findTestObject('Web Part/Asset Approval/Checkbox_src'), 'src')

KeywordUtil.logInfo(CB_Val)

if (CB_Val.contains('item_chk0.gif')) {
    WebUI.click(findTestObject('Web Part/Asset Approval/Select_val_Checkbox'))
} else if (CB_Val.contains('item_chk1.gif')) {
    WebUI.click(findTestObject('Web Part/Asset Approval/Select_val_Checkbox'))

    Mobile.delay(2, FailureHandling.STOP_ON_FAILURE)

    WebUI.click(findTestObject('Web Part/Asset Approval/Select_val_Checkbox'))
}

Mobile.delay(4, FailureHandling.STOP_ON_FAILURE)

GlobalVariable.label = findTestData('Mobile Input Data/NewRetailer').getValue('Asset_Name', 2)

Seri_No_WEB = WebUI.getText(findTestObject('Web Part/Asset Approval/Serial_no_val'))

KeywordUtil.logInfo('serial No = ' + Seri_No_WEB)

WebUI.delay(2)

Mobile.verifyMatch(Seri_No_WEB, GlobalVariable.Asset_SR_No, false)

WebUI.click(findTestObject('Web Part/Asset Approval/Reject_btn'))

WebUI.delay(5)

WebUI.acceptAlert()

