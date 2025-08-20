package product.Config.Automation

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

public class manjulogin {
	@Keyword
	public void manju() {

		WebUI.openBrowser('')

		WebUI.maximizeWindow()

		WebUI.navigateToUrl(GlobalVariable.ApplicationUrl)

		WebUI.delay(2)

		WebUI.setText(findTestObject('Web Part/Re-usable/Login/Username'), GlobalVariable.username)

		WebUI.setText(findTestObject('Web Part/Re-usable/Login/Password'), GlobalVariable.Password)

		WebUI.click(findTestObject('Web Part/Re-usable/Login/Login Button'))

		WebUI.delay(2)

		GlobalVariable.label1 = findTestData('Web Input Data/Stock allocation').getValue('Navigate', 1)

		WebUI.click(findTestObject('Web Part/TaxScreen/Global Object/A_tag_Global Variable'))

		WebUI.delay(2)

		GlobalVariable.label1 = findTestData('Web Input Data/Stock allocation').getValue('Navigate', 2)

		WebUI.click(findTestObject('Web Part/TaxScreen/Global Object/A_tag_Global Variable'))
	}
}
