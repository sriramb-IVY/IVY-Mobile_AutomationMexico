import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.chrome.ChromeDriver as ChromeDriver
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.keyword.excel.ExcelKeywords as ExcelKeywords
import internal.GlobalVariable as GlobalVariable
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil

Scheme_Index = 1

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Web Login and Navigation/DivisionLogin'), [:], FailureHandling.STOP_ON_FAILURE)

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Web Login and Navigation/Navigate to Retailer Master'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.waitForElementVisible(findTestObject('Web Part/Retailer Master/Retailer_Filter_Icon'), 0)

WebUI.click(findTestObject('Web Part/Retailer Master/Retailer_Filter_Icon'))

WebUI.click(findTestObject('Mobile/Web Part/Retailer Master/Search_Icon'))

WebUI.delay(2)

WebUI.click(findTestObject('Web Part/Retailer Master/TreeFilter_Icon'))

WebUI.mouseOver(findTestObject('Web Part/Retailer Master/Search_Btn'))

WebUI.waitForElementVisible(findTestObject('Web Part/Retailer Master/RetailerName_Field'), 0)

not_run: WebUI.click(findTestObject('Web Part/Retailer Master/RetailerName_Field'))

GlobalVariable.label = findTestData('Mobile Input Data/Attribute_Scheme').getValue('Retailer', Scheme_Index)

WebUI.setText(findTestObject('Web Part/Retailer Master/RetailerName_Field'), GlobalVariable.label)

WebUI.click(findTestObject('Web Part/Retailer Master/Search_Btn'))

WebUI.waitForElementNotVisible(findTestObject('Web Part/Re-usable/Page Load'), 100)

WebUI.delay(2)

WebUI.waitForElementVisible(findTestObject('Web Part/Re-usable/Global/td_Tag(GridSkuRow)'), 0)

WebUI.click(findTestObject('Web Part/Re-usable/Global/td_Tag(GridSkuRow)'))

WebUI.click(findTestObject('Web Part/Retailer Master/Edit_Btn'))

WebUI.waitForElementNotVisible(findTestObject('Web Part/Re-usable/Page Load'), 100)

WebUI.waitForElementVisible(findTestObject('Web Part/Retailer Master/Attribute_Tab'), 0)

WebUI.click(findTestObject('Web Part/Retailer Master/Attribute_Tab'))

WebUI.waitForElementNotVisible(findTestObject('Web Part/Re-usable/Page Load'), 100)

WebUI.click(findTestObject('Web Part/Retailer Master/MappedSlab_Code_Filter'))

String MappedRetailerAttribute_Name = findTestData('Mobile Input Data/Attribute_Scheme').getValue('MappedRetailerAttribute_Name', 
    Scheme_Index)

RetailerAttribute_Name = MappedRetailerAttribute_Name.substring(0, 5)

WebUI.setText(findTestObject('Web Part/Retailer Master/MappedSlab_Code_Filter'), RetailerAttribute_Name)

WebUI.delay(2)

ExpectedAttributeName = WebUI.getText(findTestObject('Web Part/Retailer Master/MappedSlab_Grid_1row_code'))

WebUI.verifyMatch(RetailerAttribute_Name, ExpectedAttributeName, false)

