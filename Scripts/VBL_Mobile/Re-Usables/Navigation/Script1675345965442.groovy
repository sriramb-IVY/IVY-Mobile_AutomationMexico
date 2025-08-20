import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import org.apache.poi.sl.draw.geom.MoveToCommand as MoveToCommand
import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.WebElement as WebElement
//import org.openqa.selenium.remote.server.DriverFactory as DriverFactory
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.common.WebUiCommonHelper as WebUiCommonHelper
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable

String sNavigation = Navigation //'Masters;Receivables;TeleCaller'

String[] Nav = sNavigation.split(';')

println(Nav.size())

for (i = 0; i < Nav.size(); i++) {
    WebUI.delay(1)

    GlobalVariable.label = (Nav[i])

    println(GlobalVariable.label)

    WebUI.waitForElementNotVisible(findTestObject('XXXXXXXXXXXX/Web Part/Re-usable/Page Load'), 100)

    WebUI.scrollToElement(findTestObject('XXXXXXXXXXXX/Web Part/Re-usable/A_tag_Global Variable'), 100)

    WebUI.click(findTestObject('XXXXXXXXXXXX/Web Part/Re-usable/A_tag_Global Variable'))
}

WebUI.waitForElementNotVisible(findTestObject('XXXXXXXXXXXX/Web Part/Re-usable/Page Load'), 100)

