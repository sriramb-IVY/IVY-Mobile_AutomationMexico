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

not_run: Mobile.callTestCase(findTestCase('Product_Mobile/Common/Web Login and Navigation/DivisionLogin'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.refresh()

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Web Login and Navigation/Navigate to Asset Product Mapping'), 
    [:], FailureHandling.STOP_ON_FAILURE)

WebUI.waitForElementNotVisible(findTestObject('Web Part/Re-usable/Page Load'), 100)

WebUI.click(findTestObject('Web Part/Asset Product Mapping/Name Search filter'))

WebUI.setText(findTestObject('Web Part/Asset Product Mapping/Name Search filter'), findTestData('Web Input Data/Asset Management').getValue(
        'Products', 1))

WebUI.delay(2)

GlobalVariable.label = findTestData('Web Input Data/Asset Management').getValue('Products', 1)

WebUI.click(findTestObject('Web Part/Asset Product Mapping/td tag_grid data'))

WebUI.click(findTestObject('Web Part/Asset Product Mapping/td tag_grid data'))

WebUI.click(findTestObject('Web Part/Asset Product Mapping/Map_btn'))

WebUI.waitForElementNotVisible(findTestObject('Web Part/Re-usable/Page Load'), 100)

WebUI.click(findTestObject('Web Part/Asset Product Mapping/Posm Mapping popup screen/short description'))

WebUI.setText(findTestObject('Web Part/Asset Product Mapping/Posm Mapping popup screen/short description'), 
    findTestData('Web Input Data/Asset Management').getValue('Short_Description', 1))

WebUI.delay(2)

GlobalVariable.label = findTestData('Web Input Data/Asset Management').getValue('Description', 1)

CB_val = WebUI.getAttribute(findTestObject('Web Part/Asset Product Mapping/Posm Mapping popup screen/checkbox of searched data'), 
    'src')

println(CB_val)

WebUI.takeScreenshot()

if (CB_val.contains('item_chk0.gif')) {
    WebUI.click(findTestObject('Web Part/Asset Product Mapping/Posm Mapping popup screen/checkbox of searched data'))

    WebUI.delay(2)

    WebUI.click(findTestObject('Web Part/Asset Product Mapping/posm save button'))
} else if (CB_val.contains('item_chk1.gif')) {
    WebUI.click(findTestObject('Web Part/Asset Product Mapping/posm save button'))
}

