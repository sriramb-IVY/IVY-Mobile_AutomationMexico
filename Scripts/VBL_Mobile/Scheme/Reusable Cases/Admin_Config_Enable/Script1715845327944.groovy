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



Mobile.callTestCase(findTestCase('VBL_Mobile/Re-Usables/Web Login and Navigation/IvyAdminLogin'), [:], FailureHandling.STOP_ON_FAILURE)

Mobile.callTestCase(findTestCase('VBL_Mobile/Re-Usables/Web Login and Navigation/Navigate to Module Configuration'), [:], 
    FailureHandling.STOP_ON_FAILURE)

WebUI.waitForElementNotVisible(findTestObject('XXXXXXXXXXXX/Web Part/Re-usable/Page Load'), 100)

WebUI.delay(2)

GlobalVariable.label = findTestData('VBL_Web Input Data/Module Configuration').getValue('Labels', 1)

WebUI.click(findTestObject('XXXXXXXXXXXX/Web Part/Module Configuration/Click_dropdown'))

WebUI.waitForElementVisible(findTestObject('XXXXXXXXXXXX/Web Part/Module Configuration/Select_option'), 10)

GlobalVariable.label1 = findTestData('VBL_Web Input Data/Module Configuration').getValue('Dropdown_values', 1)

WebUI.click(findTestObject('XXXXXXXXXXXX/Web Part/Module Configuration/Select_option'))

WebUI.delay(2)

GlobalVariable.label = findTestData('VBL_Web Input Data/Module Configuration').getValue('Labels', 2)

WebUI.click(findTestObject('XXXXXXXXXXXX/Web Part/Module Configuration/Click_dropdown'))

GlobalVariable.label1 = findTestData('VBL_Web Input Data/Module Configuration').getValue('Dropdown_values', 2)

WebUI.waitForElementVisible(findTestObject('Object Repository/XXXXXXXXXXXX/Web Part/Module Configuration/Division_select_option'), 
    10)

WebUI.click(findTestObject('Object Repository/XXXXXXXXXXXX/Web Part/Module Configuration/Division_select_option'))

WebUI.delay(6)

GlobalVariable.label = findTestData('VBL_Web Input Data/Module Configuration').getValue('Labels', 4)

WebUI.click(findTestObject('Object Repository/XXXXXXXXXXXX/Web Part/Module Configuration/SubChannel_dropdown'))

GlobalVariable.label1 = findTestData('VBL_Web Input Data/Module Configuration').getValue('Dropdown_values', 4)

WebUI.waitForElementVisible(findTestObject('XXXXXXXXXXXX/Web Part/Module Configuration/Select_option'), 10)

WebUI.click(findTestObject('XXXXXXXXXXXX/Web Part/Module Configuration/Select_option'))

WebUI.delay(2)

GlobalVariable.label = findTestData('VBL_Web Input Data/Module Configuration').getValue('Labels', 5)

WebUI.click(findTestObject('XXXXXXXXXXXX/Web Part/Module Configuration/Click_dropdown'))

GlobalVariable.label1 = findTestData('VBL_Web Input Data/Module Configuration').getValue('Dropdown_values', 5)

WebUI.waitForElementVisible(findTestObject('XXXXXXXXXXXX/Web Part/Module Configuration/Select_option'), 10)

WebUI.scrollToElement(findTestObject('XXXXXXXXXXXX/Web Part/Module Configuration/Select_option'), 10)

WebUI.click(findTestObject('XXXXXXXXXXXX/Web Part/Module Configuration/Select_option'))

WebUI.delay(2)

//Version
GlobalVariable.label = findTestData('VBL_Web Input Data/Module Configuration').getValue('Labels', 6)

WebUI.click(findTestObject('XXXXXXXXXXXX/Web Part/Module Configuration/Click_dropdown'))

GlobalVariable.label1 = GlobalVariable.VersionCode

WebUI.scrollToElement(findTestObject('XXXXXXXXXXXX/Web Part/Module Configuration/Select_option'), 10)

WebUI.waitForElementVisible(findTestObject('XXXXXXXXXXXX/Web Part/Module Configuration/Select_option'), 10)

WebUI.click(findTestObject('XXXXXXXXXXXX/Web Part/Module Configuration/Select_option'))

WebUI.delay(2)

WebUI.scrollToElement(findTestObject('XXXXXXXXXXXX/Web Part/Module Configuration/Code_search_field'), 10)

WebUI.click(findTestObject('XXXXXXXXXXXX/Web Part/Module Configuration/Code_search_field'))

WebUI.setText(findTestObject('XXXXXXXXXXXX/Web Part/Module Configuration/Code_search_field'), findTestData('VBL_Web Input Data/Module Configuration').getValue(
        'Configs', 2))

GlobalVariable.label = findTestData('VBL_Web Input Data/Module Configuration').getValue('Configs', 2)

WebUI.waitForElementVisible(findTestObject('XXXXXXXXXXXX/Web Part/Module Configuration/Checkbox'), 10, FailureHandling.OPTIONAL)

checkbox_type = WebUI.getAttribute(findTestObject('XXXXXXXXXXXX/Web Part/Module Configuration/Checkbox'), 'src', FailureHandling.STOP_ON_FAILURE)

unchecked_checkbox = findTestData('VBL_Web Input Data/Module Configuration').getValue('Check_Box', 1)

if (checkbox_type == unchecked_checkbox) {
    KeywordUtil.logInfo('Checkbox is disabled')

    WebUI.click(findTestObject('XXXXXXXXXXXX/Web Part/Module Configuration/Checkbox'))
} else {
    KeywordUtil.logInfo('Checkbox is already enabled')
}

GlobalVariable.label = findTestData('VBL_Web Input Data/Module Configuration').getValue('Configs', 3)

WebUI.setText(findTestObject('XXXXXXXXXXXX/Web Part/Module Configuration/Code_search_field'), findTestData('VBL_Web Input Data/Module Configuration').getValue(
        'Configs', 3))

WebUI.waitForElementVisible(findTestObject('XXXXXXXXXXXX/Web Part/Module Configuration/Checkbox'), 10, FailureHandling.OPTIONAL)

checkbox_type = WebUI.getAttribute(findTestObject('XXXXXXXXXXXX/Web Part/Module Configuration/Checkbox'), 'src', FailureHandling.STOP_ON_FAILURE)

unchecked_checkbox = findTestData('VBL_Web Input Data/Module Configuration').getValue('Check_Box', 1)

if (checkbox_type == unchecked_checkbox) {
    KeywordUtil.logInfo('Checkbox is disabled')

    WebUI.click(findTestObject('XXXXXXXXXXXX/Web Part/Module Configuration/Checkbox'))
} else {
    KeywordUtil.logInfo('Checkbox is already enabled')
}

WebUI.click(findTestObject('XXXXXXXXXXXX/Web Part/Module Configuration/Save_btn'))

Save_popup = WebUI.getText(findTestObject('XXXXXXXXXXXX/Web Part/Module Configuration/Save_popup'), FailureHandling.OPTIONAL)

WebUI.verifyMatch(Save_popup, findTestData('VBL_Web Input Data/Module Configuration').getValue('Popup', 1), false, FailureHandling.OPTIONAL)

WebUI.takeScreenshot()

WebUI.closeBrowser()

Mobile.callTestCase(findTestCase('VBL_Mobile/Re-Usables/Web Login and Navigation/ClearCashe Login'), [:], FailureHandling.STOP_ON_FAILURE)

