package credit_management

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows

import internal.GlobalVariable

public class Admin_Configs_1 {
	@Keyword
	public static void Set_Configs(def Config,def alert ){

		WebUI.callTestCase(findTestCase('Product_Mobile/Common/Web Login and Navigation/AdminLogin'), [:], FailureHandling.STOP_ON_FAILURE)

		WebUI.callTestCase(findTestCase('Product_Mobile/Common/Web Login and Navigation/Navigate to Module Configuration'),
				[:], FailureHandling.STOP_ON_FAILURE)

		WebUI.waitForElementNotVisible(findTestObject('Web Part/Re-usable/Page Load'), 100)

		WebUI.delay(2)

		GlobalVariable.label = findTestData('Data Files/Web Input Data/Module Configuration').getValue('Labels', 1)

		WebUI.click(findTestObject('Web Part/Module Configuration/Click_dropdown'))

		WebUI.waitForElementVisible(findTestObject('Web Part/Module Configuration/Select_option'), 10)

		GlobalVariable.label1 = findTestData('Data Files/Web Input Data/Module Configuration').getValue('Dropdown_values', 1)

		WebUI.click(findTestObject('Web Part/Module Configuration/Select_option'))

		WebUI.delay(2)

		GlobalVariable.label = findTestData('Data Files/Web Input Data/Module Configuration').getValue('Labels', 2)

		WebUI.click(findTestObject('Web Part/Module Configuration/Click_dropdown'))

		GlobalVariable.label1 = findTestData('Data Files/Web Input Data/Module Configuration').getValue('Dropdown_values', 2)

		WebUI.waitForElementVisible(findTestObject('Web Part/Module Configuration/Select_option'), 10)

		WebUI.click(findTestObject('Web Part/Module Configuration/Select_option'))

		WebUI.delay(2)

		GlobalVariable.label = findTestData('Data Files/Web Input Data/Module Configuration').getValue('Labels', 4)

		WebUI.click(findTestObject('Web Part/Module Configuration/Click_dropdown'))

		GlobalVariable.label1 = findTestData('Data Files/Web Input Data/Module Configuration').getValue('Dropdown_values', 4)

		WebUI.waitForElementVisible(findTestObject('Web Part/Module Configuration/Select_option'), 10)

		WebUI.click(findTestObject('Web Part/Module Configuration/Select_option'))

		WebUI.delay(2)

		GlobalVariable.label = findTestData('Data Files/Web Input Data/Module Configuration').getValue('Labels', 5)

		WebUI.click(findTestObject('Web Part/Module Configuration/Click_dropdown'))

		GlobalVariable.label1 = findTestData('Data Files/Web Input Data/Module Configuration').getValue('Dropdown_values', 5)

		WebUI.waitForElementVisible(findTestObject('Web Part/Module Configuration/Select_option'), 10)

		WebUI.scrollToElement(findTestObject('Web Part/Module Configuration/Select_option'),10)

		WebUI.click(findTestObject('Web Part/Module Configuration/Select_option'))

		WebUI.delay(2)

		GlobalVariable.label = findTestData('Data Files/Web Input Data/Module Configuration').getValue('Labels', 6)

		WebUI.click(findTestObject('Web Part/Module Configuration/Click_dropdown'))

		GlobalVariable.label1 = findTestData('Data Files/Web Input Data/Module Configuration').getValue('Dropdown_values', 6)

		WebUI.scrollToElement(findTestObject('Web Part/Module Configuration/Select_option'),10)

		WebUI.waitForElementVisible(findTestObject('Web Part/Module Configuration/Select_option'), 10)

		WebUI.click(findTestObject('Web Part/Module Configuration/Select_option'))

		WebUI.delay(2)

		WebUI.scrollToElement(findTestObject('Web Part/Module Configuration/Code_search_field'),10)

		WebUI.click(findTestObject('Web Part/Module Configuration/Code_search_field'))

		WebUI.setText(findTestObject('Web Part/Module Configuration/Code_search_field'), Config)

		WebUI.click(findTestObject('Web Part/Module Configuration/Rfield_enter'))

		WebUI.clearText(findTestObject('Web Part/Module Configuration/Editable_Rfield'))

		WebUI.setText(findTestObject('Web Part/Module Configuration/Editable_Rfield'), alert)

		WebUI.delay(2)

		WebUI.click(findTestObject('Web Part/Module Configuration/Save_btn'))

		WebUI.callTestCase(findTestCase('Product_Mobile/Common/Web Login and Navigation/ClearCashe Login'), [:], FailureHandling.OPTIONAL)
	}
}

