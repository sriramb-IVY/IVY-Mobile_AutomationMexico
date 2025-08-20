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

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Web Login and Navigation/Navigate to Asset Master'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.waitForElementNotVisible(findTestObject('Web Part/Re-usable/Page Load'), 100)

WebUI.click(findTestObject('Web Part/Asset Master/Short description_search filter'))

WebUI.setText(findTestObject('Web Part/Asset Master/Short description_search filter'), findTestData('Web Input Data/Asset Management').getValue('Short_Description', 1))

GlobalVariable.label = findTestData('Web Input Data/Asset Management').getValue('Description', 1)

WebUI.delay(2)

if (WebUI.verifyElementPresent(findTestObject('Web Part/Asset Master/searched row data in grid'), 10, FailureHandling.OPTIONAL)) {
    WebUI.click(findTestObject('Web Part/Asset Master/searched row data in grid'))

    WebUI.click(findTestObject('Web Part/Asset Master/Edit_btn'))

    WebUI.waitForElementNotVisible(findTestObject('Web Part/Re-usable/Page Load'), 100)

    WebUI.delay(2)

    WebUI.scrollToElement(findTestObject('Web Part/Asset Master/Save_btn'), 0)

    WebUI.click(findTestObject('Web Part/Asset Master/Save_btn'), FailureHandling.STOP_ON_FAILURE)
} else {
    WebUI.click(findTestObject('Web Part/Asset Master/Add_btn'))

    WebUI.waitForElementNotVisible(findTestObject('Web Part/Re-usable/Page Load'), 100)

    GlobalVariable.label = findTestData('Web Input Data/Asset Management').getValue('Input_Fields', 1)

    WebUI.click(findTestObject('Web Part/Asset Master/Short description_input tag'))

    WebUI.setText(findTestObject('Web Part/Asset Master/Short description_input tag'), findTestData('Web Input Data/Asset Management').getValue('Short_Description', 1))

    GlobalVariable.label = findTestData('Web Input Data/Asset Management').getValue('Input_Fields', 2)

    WebUI.click(findTestObject('Web Part/Asset Master/Description_text area tag'))

    WebUI.setText(findTestObject('Web Part/Asset Master/Description_text area tag'), findTestData('Web Input Data/Asset Management').getValue('Description', 1))

    WebUI.delay(2)

    WebUI.click(findTestObject('Web Part/Asset Master/is Asset_checkbox'))

    WebUI.scrollToElement(findTestObject('Web Part/Asset Master/Save_btn'), 0)

    WebUI.click(findTestObject('Web Part/Asset Master/Save_btn'))

    WebUI.delay(2)

    WebUI.click(findTestObject('Web Part/Asset Master/Cancel_btn'), FailureHandling.OPTIONAL)

    WebUI.delay(2)

    WebUI.click(findTestObject('Web Part/Asset Master/Short description_search filter'))

    WebUI.clearText(findTestObject('Web Part/Asset Master/Short description_search filter'))

    WebUI.setText(findTestObject('Web Part/Asset Master/Short description_search filter'), findTestData('Web Input Data/Asset Management').getValue('Short_Description', 1))

    WebUI.delay(2)

    GlobalVariable.label = findTestData('Web Input Data/Asset Management').getValue('Description', 1)

    WebUI.verifyElementPresent(findTestObject('Web Part/Asset Master/searched row data in grid'), 0)

    WebUI.verifyElementVisible(findTestObject('Web Part/Asset Master/searched row data in grid'), FailureHandling.STOP_ON_FAILURE)

    WebUI.takeScreenshot()
}

