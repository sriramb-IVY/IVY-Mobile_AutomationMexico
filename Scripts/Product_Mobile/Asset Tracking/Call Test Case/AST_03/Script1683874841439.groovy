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
import java.text.SimpleDateFormat as SimpleDateFormat

not_run: Mobile.callTestCase(findTestCase('Product_Mobile/Common/Web Login and Navigation/DivisionLogin'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.refresh()

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Web Login and Navigation/Navigate to Asset Mapping'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.waitForElementNotVisible(findTestObject('Web Part/Re-usable/Page Load'), 100)

WebUI.click(findTestObject('Web Part/Asset Mapping/Search_btn'))

WebUI.delay(2)

WebUI.setText(findTestObject('Web Part/Asset Mapping/Retailer_Name_input'), findTestData('Web Input Data/Asset Management').getValue(
        'Retailers', 1))

WebUI.delay(2)

WebUI.click(findTestObject('Web Part/Asset Mapping/Search_btn_find_screen'))

WebUI.waitForElementNotVisible(findTestObject('Web Part/Re-usable/Page Load'), 100)

GlobalVariable.label = findTestData('Web Input Data/Asset Management').getValue('Retailers', 1)

WebUI.click(findTestObject('Web Part/Asset Mapping/label tag_checkbox'))

WebUI.click(findTestObject('Web Part/Asset Mapping/Select_btn'))

WebUI.waitForElementNotVisible(findTestObject('Web Part/Re-usable/Page Load'), 100)

GlobalVariable.label1 = findTestData('Web Input Data/Asset Management').getValue('Retailers', 2)

WebUI.click(findTestObject('Web Part/Asset Mapping/div tag_slected_retailer name'))

GlobalVariable.label = findTestData('Web Input Data/Asset Management').getValue('Description', 1)

if (WebUI.verifyElementVisible(findTestObject('Web Part/Asset Mapping/Asset_name'), FailureHandling.OPTIONAL)) {
    GlobalVariable.label = findTestData('Web Input Data/Asset Management').getValue('Description', 1)

    WebUI.verifyElementPresent(findTestObject('Web Part/Asset Mapping/Asset_name'), 0)

    WebUI.verifyElementVisible(findTestObject('Web Part/Asset Mapping/Asset_name'))
} else {
    WebUI.refresh()

    Mobile.callTestCase(findTestCase('Product_Mobile/Common/Web Login and Navigation/Navigate to Asset Mapping'), [:], 
        FailureHandling.STOP_ON_FAILURE)

    WebUI.waitForElementNotVisible(findTestObject('Web Part/Re-usable/Page Load'), 100)

    WebUI.click(findTestObject('Web Part/Asset Mapping/Search_btn'))

    WebUI.delay(2)

    WebUI.setText(findTestObject('Web Part/Asset Mapping/Retailer_Name_input'), findTestData('Web Input Data/Asset Management').getValue(
            'Retailers', 1))

    WebUI.delay(2)

    WebUI.click(findTestObject('Web Part/Asset Mapping/Search_btn_find_screen'))

    WebUI.waitForElementNotVisible(findTestObject('Web Part/Re-usable/Page Load'), 100)

    GlobalVariable.label = findTestData('Web Input Data/Asset Management').getValue('Retailers', 1)

    WebUI.click(findTestObject('Web Part/Asset Mapping/label tag_checkbox'))

    WebUI.click(findTestObject('Web Part/Asset Mapping/Select_btn'))

    WebUI.waitForElementNotVisible(findTestObject('Web Part/Re-usable/Page Load'), 100)

    GlobalVariable.label = findTestData('Web Input Data/Asset Management').getValue('Description', 1)

    GlobalVariable.label1 = findTestData('Web Input Data/Asset Management').getValue('Retailers', 2)

    WebUI.dragAndDropToObject(findTestObject('Web Part/Asset Mapping/i tag_to drag the asset'), findTestObject(
            'Product_Mobile/Web Part/Asset Mapping/div tag_slected_retailer name'))

    WebUI.waitForElementNotVisible(findTestObject('Web Part/Re-usable/Page Load'), 100)

    WebUI.delay(4)

    GlobalVariable.label = findTestData('Web Input Data/Asset Management').getValue('Input_Fields', 3)

    WebUI.setText(findTestObject('Web Part/Asset Mapping/input tag'), findTestData('Web Input Data/Asset Management').getValue(
            'Details', 1))

    GlobalVariable.label = findTestData('Web Input Data/Asset Management').getValue('Input_Fields', 6)

    WebUI.setText(findTestObject('Web Part/Asset Mapping/input tag'), findTestData('Web Input Data/Asset Management').getValue(
            'Details', 2))

    GlobalVariable.label = findTestData('Web Input Data/Asset Management').getValue('Input_Fields', 4)

    WebUI.click(findTestObject('Web Part/Asset Mapping/Date selection field_button tag'))

    WebUI.callTestCase(findTestCase('Product_Mobile/Common/DatePicker_and _Navigation/Date Picker'), [('DateValue') : 'Today'], 
        FailureHandling.STOP_ON_FAILURE)

    WebUI.takeScreenshot()

    GlobalVariable.button = findTestData('Web Input Data/Asset Management').getValue('Input_Fields', 5)

    WebUI.click(findTestObject('Web Part/Asset Mapping/button tag'))
}

WebUI.delay(3)

WebUI.closeBrowser()

