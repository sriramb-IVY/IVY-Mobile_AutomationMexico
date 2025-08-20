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

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Web Login and Navigation/DivisionLogin'), [:], FailureHandling.STOP_ON_FAILURE)

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Web Login and Navigation/Navigate to Asset Vendor'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.waitForElementNotVisible(findTestObject('Web Part/Re-usable/Page Load'), 100)

WebUI.click(findTestObject('Web Part/Asset Vendor/Code_Search_field'))

WebUI.setText(findTestObject('Web Part/Asset Vendor/Code_Search_field'), findTestData('Web Input Data/Asset Management').getValue(
        'Assetvendor', 1))

WebUI.delay(2)

GlobalVariable.label = findTestData('Web Input Data/Asset Management').getValue('Assetvendor', 1)

if (WebUI.verifyElementPresent(findTestObject('Web Part/Asset Product Mapping/td tag_grid data'), 0, FailureHandling.OPTIONAL)) {
    WebUI.click(findTestObject('Web Part/Asset Product Mapping/td tag_grid data'))

    WebUI.click(findTestObject('Web Part/Asset Master/Edit_btn'))

    WebUI.delay(2)

    GlobalVariable.button = findTestData('Web Input Data/Asset Management').getValue('Buttons', 1)

    WebUI.click(findTestObject('Web Part/Asset Vendor/Global_text_button_tag'))

    WebUI.waitForElementNotVisible(findTestObject('Web Part/Re-usable/Page Load'), 100, FailureHandling.OPTIONAL)
} else {
    WebUI.click(findTestObject('Web Part/Asset Master/Add_btn'))

    WebUI.delay(2)

    GlobalVariable.input = findTestData('Web Input Data/Asset Management').getValue('Input_id', 6)

    WebUI.setText(findTestObject('Web Part/Asset Vendor/Global_id_input_tag'), findTestData('Web Input Data/Asset Management').getValue(
            'Assetvendor', 1))

    GlobalVariable.input = findTestData('Web Input Data/Asset Management').getValue('Input_id', 7)

    WebUI.setText(findTestObject('Web Part/Asset Vendor/Global_id_input_tag'), findTestData('Web Input Data/Asset Management').getValue(
            'Assetvendor', 2))

    WebUI.delay(2)

	WebUI.takeScreenshot()
	
    GlobalVariable.button = findTestData('Web Input Data/Asset Management').getValue('Buttons', 1)

    WebUI.click(findTestObject('Web Part/Asset Vendor/Global_text_button_tag'))

    WebUI.waitForElementNotVisible(findTestObject('Web Part/Re-usable/Page Load'), 100, FailureHandling.OPTIONAL)

    WebUI.delay(2)
}

