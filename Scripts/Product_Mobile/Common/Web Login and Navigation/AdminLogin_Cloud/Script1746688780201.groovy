import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.chrome.ChromeDriver as ChromeDriver
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory

CustomKeywords.'com.katalon.helpers.TestCloudHelper.createTestCloudWindowsChromeDriver'('d3f7fa0e-c552-47e4-85b5-5474faefb733',
	'795142', '135')

WebUI.navigateToUrl(GlobalVariable.ApplicationUrl)


WebUI.waitForElementVisible(findTestObject('Web Part/Re-usable/Login/Username'), 5)

WebUI.setText(findTestObject('Web Part/Re-usable/Login/Username'), GlobalVariable.AdminUsername)

WebUI.setText(findTestObject('Web Part/Re-usable/Login/Password'), GlobalVariable.AdminPassword)

WebUI.click(findTestObject('Web Part/Re-usable/Login/Login Button'))
