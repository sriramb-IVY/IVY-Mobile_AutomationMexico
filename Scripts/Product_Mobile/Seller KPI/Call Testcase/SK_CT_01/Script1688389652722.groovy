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
import java.text.DateFormatSymbols as DateFormatSymbols
import java.text.SimpleDateFormat as SimpleDateFormat

String pattern = 'MM/yyyy'

SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern)

String currentMonth = simpleDateFormat.format(new Date())

println(currentMonth)

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Web Login and Navigation/DivisionLogin'), [:], FailureHandling.STOP_ON_FAILURE)

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Web Login and Navigation/Navigate to KPI Seller Target'), [:], 
    FailureHandling.STOP_ON_FAILURE)

WebUI.waitForElementNotVisible(findTestObject('Web Part/Re-usable/Page Load'), 100)

WebUI.click(findTestObject('Web Part/Seller KPI/Parameter_select'))

WebUI.click(findTestObject('Web Part/Seller KPI/Sales_value'))

WebUI.delay(2)

WebUI.setText(findTestObject('Web Part/Seller KPI/Target_from_inp'), currentMonth)

WebUI.delay(2)

GlobalVariable.label = findTestData('Web Input Data/Seller KPI').getValue('Options', 2)

WebUI.takeScreenshot()

if (WebUI.verifyElementVisible(findTestObject('Web Part/Seller KPI/DD_Value'), FailureHandling.OPTIONAL)) {
	
	WebUI.takeScreenshot()
	
    WebUI.click(findTestObject('Web Part/Seller KPI/DD_Value'), FailureHandling.STOP_ON_FAILURE)

    WebUI.click(findTestObject('Web Part/SecondaryGroupCreation/Edit_Btn'))

    WebUI.waitForElementNotVisible(findTestObject('Web Part/Re-usable/Page Load'), 100)
	
	WebUI.takeScreenshot()

    Month = WebUI.getAttribute(findTestObject('Web Part/Seller KPI/Target_Month_inp'), 'value')

    println(Month)

    GlobalVariable.label = findTestData('Web Input Data/Seller KPI').getValue('Options', 5)

    WebUI.setText(findTestObject('Web Part/Seller KPI/SkuCode_search'), GlobalVariable.label)

    WebUI.delay(4)
	
	WebUI.takeScreenshot()

    amt = WebUI.getText(findTestObject('Web Part/Seller KPI/Entered_value_Amt'))

    println(amt)

    WebUI.takeScreenshot()

    GlobalVariable.button = findTestData('Web Input Data/Seller KPI').getValue('Button', 1)

    WebUI.waitForElementVisible(findTestObject('Web Part/Seller KPI/text_button_tag'), 10, FailureHandling.STOP_ON_FAILURE)

    WebUI.click(findTestObject('Web Part/Seller KPI/text_button_tag'))
	
	WebUI.takeScreenshot()
} else {
	
	WebUI.takeScreenshot()
	
    WebUI.click(findTestObject('Web Part/Seller KPI/Add_btn'))

    WebUI.waitForElementNotVisible(findTestObject('Web Part/Re-usable/Page Load'), 100)
	
	WebUI.takeScreenshot()

    GlobalVariable.input = findTestData('Web Input Data/Seller KPI').getValue('Inputs', 2)

    WebUI.click(findTestObject('Web Part/Seller KPI/Type_select'))

    GlobalVariable.label = findTestData('Web Input Data/Seller KPI').getValue('Options', 2)

    WebUI.click(findTestObject('Web Part/Seller KPI/Option_select'))

    GlobalVariable.input = findTestData('Web Input Data/Seller KPI').getValue('Inputs', 4)

    WebUI.click(findTestObject('Web Part/Seller KPI/Type_select'))

    WebUI.click(findTestObject('Web Part/Seller KPI/Month_select_inx2'))

    WebUI.delay(2)
	
	WebUI.takeScreenshot()

    GlobalVariable.label = findTestData('Web Input Data/Seller KPI').getValue('Options', 5)

    WebUI.setText(findTestObject('Web Part/Seller KPI/SkuCode_search'), GlobalVariable.label)

    WebUI.click(findTestObject('Web Part/Seller KPI/Seller_value_row_grid'))

    WebUI.click(findTestObject('Web Part/Seller KPI/Checkbox'))

    WebUI.delay(2)

    WebUI.click(findTestObject('Web Part/Seller KPI/Value_input'))

    WebUI.clearText(findTestObject('Web Part/Seller KPI/Value_input'))

    WebUI.setText(findTestObject('Web Part/Seller KPI/Value_input'), findTestData('Web Input Data/Seller KPI').getValue(
            'Value', 1))

    WebUI.click(findTestObject('Web Part/Seller KPI/Value_tick_icon'))

    WebUI.takeScreenshot()

    GlobalVariable.button = findTestData('Web Input Data/Seller KPI').getValue('Button', 2)

    WebUI.click(findTestObject('Web Part/Seller KPI/text_button_tag'))

    GlobalVariable.button = findTestData('Web Input Data/Seller KPI').getValue('Button', 1)

    WebUI.waitForElementVisible(findTestObject('Web Part/Seller KPI/text_button_tag'), 10, FailureHandling.STOP_ON_FAILURE)

    WebUI.click(findTestObject('Web Part/Seller KPI/text_button_tag'))
	
	WebUI.takeScreenshot()
}

