import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import java.sql.Connection as Connection
import java.sql.DriverManager as DriverManager
import java.sql.ResultSet as ResultSet
import java.sql.Statement as Statement
import java.text.SimpleDateFormat as SimpleDateFormat
import com.google.common.collect.FilteredEntryMultimap.Keys as Keys
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil

WebUI.callTestCase(findTestCase('Product_Mobile/Common/Web Login and Navigation/DistributorLogin'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('Product_Mobile/Common/Web Login and Navigation/Navigate to CN Create'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.waitForElementVisible(findTestObject('Web Part/CN Create/SalesPerson'), 50, FailureHandling.OPTIONAL)

WebUI.click(findTestObject('Web Part/CN Create/SalesPerson'))

GlobalVariable.label = findTestData('Web Input Data/Credit_Note').getValue('SalesPerson', 1)

WebUI.setText(findTestObject('Web Part/CN Create/SalesPerson'), GlobalVariable.label)

WebUI.delay(1)

WebUI.click(findTestObject('Web Part/Re-usable/Global/Span_Tag(Dropdown)'))

WebUI.waitForElementNotVisible(findTestObject('Web Part/Re-usable/Page Load'), 100)

WebUI.waitForElementVisible(findTestObject('Web Part/CN Create/Route'), 50, FailureHandling.OPTIONAL)

WebUI.click(findTestObject('Web Part/CN Create/Route'))

route = findTestData('Web Input Data/Credit_Note').getValue('Route', 1)

String[] route1 = route.split(' – ', 0)

WebUI.setText(findTestObject('Web Part/CN Create/Route'), route1[0])

GlobalVariable.label = findTestData('Web Input Data/Credit_Note').getValue('Route', 1)

WebUI.click(findTestObject('Web Part/Re-usable/Global/Span_Tag(Dropdown)'))

WebUI.waitForElementNotVisible(findTestObject('Web Part/Re-usable/Page Load'), 100)

WebUI.waitForElementVisible(findTestObject('Web Part/CN Create/Retailer'), 50, FailureHandling.OPTIONAL)

WebUI.click(findTestObject('Web Part/CN Create/Retailer'))

GlobalVariable.label = findTestData('Web Input Data/Credit_Note').getValue('Retailer', 1)

WebUI.setText(findTestObject('Web Part/CN Create/Retailer'), GlobalVariable.label)

WebUI.click(findTestObject('Web Part/Re-usable/Global/Span_Tag(Dropdown)'))

WebUI.waitForElementNotVisible(findTestObject('Web Part/Re-usable/Page Load'), 100)

WebUI.click(findTestObject('Web Part/CN Create/Type'))

GlobalVariable.label = findTestData('Web Input Data/Credit_Note').getValue('Type', 1)

WebUI.click(findTestObject('Web Part/Re-usable/Global/li_Tag(Dropdown)'))

WebUI.waitForElementNotVisible(findTestObject('Web Part/Re-usable/Page Load'), 100)

WebUI.click(findTestObject('Web Part/CN Create/Amount'))

WebUI.setText(findTestObject('Web Part/CN Create/Amount'), findTestData('Web Input Data/Credit_Note').getValue('Amount', 1))

WebUI.click(findTestObject('Web Part/CN Create/Tax'), FailureHandling.OPTIONAL)

WebUI.click(findTestObject('Object Repository/Web Part/CN Create/Comments'))

WebUI.setText(findTestObject('Web Part/CN Create/Comments'), findTestData('Web Input Data/Credit_Note').getValue('Comment', 1))

WebUI.waitForElementNotVisible(findTestObject('Web Part/Re-usable/Page Load'), 100)

WebUI.click(findTestObject('Web Part/CN Create/Tax'), FailureHandling.OPTIONAL)

WebUI.click(findTestObject('Object Repository/Web Part/CN Create/Btn_Add'))

WebUI.waitForElementNotVisible(findTestObject('Web Part/Re-usable/Page Load'), 100)

WebUI.verifyElementVisible(findTestObject('Web Part/CN Create/Btn_Save'), FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Web Part/CN Create/Btn_Save'))

WebUI.verifyElementVisible(findTestObject('Web Part/CN Create/Btn_OK'), FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Web Part/CN Create/Btn_OK'))

Mobile.closeApplication()

