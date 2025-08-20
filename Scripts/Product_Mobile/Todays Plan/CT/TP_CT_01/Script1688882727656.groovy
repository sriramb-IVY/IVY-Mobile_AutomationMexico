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

WebUI.callTestCase(findTestCase('Product_Mobile/Common/Web Login and Navigation/DivisionLogin'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.waitForElementNotVisible(findTestObject('Web Part/Re-usable/Page Load'), 100)

WebUI.callTestCase(findTestCase('Product_Mobile/Common/Web Login and Navigation/Navigate to Retailer Visit Plan'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.waitForElementNotVisible(findTestObject('Web Part/Re-usable/Page Load'), 100)

GlobalVariable.input = findTestData('Web Input Data/Retailer Visit Plan').getValue('Branch_Sup', 1)

WebUI.click(findTestObject('Web Part/Retailer Visit Plan/Seller_Search_TreeFilter'))

WebUI.sendKeys(findTestObject('Web Part/Retailer Visit Plan/Seller_Search_TreeFilter'), GlobalVariable.input)

WebUI.click(findTestObject('Web Part/Retailer Visit Plan/Branch_sup'))

WebUI.waitForElementNotVisible(findTestObject('Web Part/Re-usable/Page Load'), 100)

WebUI.click(findTestObject('Web Part/Retailer Visit Plan/Search_field'))

GlobalVariable.label = findTestData('Web Input Data/Retailer Visit Plan').getValue('Retailer', 1)

WebUI.setText(findTestObject('Web Part/Retailer Visit Plan/Search_field'), GlobalVariable.label)

WebUI.takeScreenshot()

if (WebUI.verifyElementPresent(findTestObject('Web Part/Retailer Visit Plan/Checked_cbox'), 3, FailureHandling.OPTIONAL)) {
    KeywordUtil.logInfo('Visit is planned for a day')

    WebUI.takeScreenshot()

    WebUI.click(findTestObject('Web Part/Retailer Visit Plan/Save_button'))
} else {
    for (int i = 0; i < 12; i++) {
        WebUI.click(findTestObject('Web Part/Retailer Visit Plan/Unchecked_cbox'), FailureHandling.OPTIONAL)
    }
    
    WebUI.takeScreenshot()

    WebUI.click(findTestObject('Web Part/Retailer Visit Plan/Save_button'))

    WebUI.closeBrowser()
}

WebUI.closeBrowser()

