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
import internal.GlobalVariable as GlobalVariable
import java.awt.Robot as Robot
import java.awt.event.KeyEvent as KeyEvent
import org.apache.commons.lang.RandomStringUtils as RandomStringUtils
import com.kms.katalon.core.mobile.keyword.internal.MobileDriverFactory as MobileDriverFactory
import io.appium.java_client.AppiumDriver as AppiumDriver
import java.sql.Connection as Connection
import java.sql.DriverManager as DriverManager
import java.sql.ResultSet as ResultSet
import java.sql.Statement as Statement
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import com.kms.katalon.keyword.excel.ExcelKeywords as ExcelKeywords



public class Alert_Popup_1 {

	@Keyword
	public  String Warn_Alert(def Warn_Alert){

		AppiumDriver driver = MobileDriverFactory.getDriver()

		String Warning_message_popup = driver.findElementByXPath('//android.widget.Toast[1]').getText()

		Mobile.takeScreenshot()

		KeywordUtil.logInfo(Warning_message_popup)

		Mobile.verifyMatch(Warning_message_popup,Warn_Alert, false)
	}


	@Keyword
	public  String Hard_Alert(def Hard_Alert){

		Mobile.verifyElementVisible(findTestObject('Object Repository/Mobile/Credit Management/Alert_Text'), 10)

		String Hard_Alert_Text = Mobile.getText(findTestObject('Object Repository/Mobile/Credit Management/Alert_Text'),
				0)

		Mobile.takeScreenshot()

		KeywordUtil.logInfo(Hard_Alert_Text)

		Mobile.verifyMatch(Hard_Alert_Text, Hard_Alert, false)


		Mobile.tap(findTestObject('Mobile/Common/Btn_OK'), 0)

		Mobile.delay(3)

		GlobalVariable.Name = 'Summary'

		Mobile.verifyElementVisible(findTestObject('Object Repository/Mobile/Credit Management/Screen_Name'), 10)

		Mobile.tap(findTestObject('Mobile/Common/Icon_Back'), 0)

		Mobile.tap(findTestObject('Mobile/Common/Btn_OK'), 0,FailureHandling.OPTIONAL)

		Mobile.delay(3)

		GlobalVariable.Name = 'Applying Scheme'

		Mobile.verifyElementVisible(findTestObject('Object Repository/Mobile/Credit Management/Screen_Name'), 10)

		Mobile.tap(findTestObject('Mobile/Common/Icon_Back'), 0)

		Mobile.delay(3)

		Mobile.tap(findTestObject('Mobile/Common/Icon_Back'), 0)

		Mobile.delay(3)

		Mobile.tap(findTestObject('Mobile/Common/Btn_OK'), 0)
	}
	@Keyword
	public  String Prd_Hard_Alert(def Pd_Hard_Alert){

		Mobile.verifyElementVisible(findTestObject('Object Repository/Mobile/Credit Management/Alert_Text'), 10)

		String Hard_Alert_Text = Mobile.getText(findTestObject('Object Repository/Mobile/Credit Management/Alert_Text'),
				0)

		Mobile.takeScreenshot()

		KeywordUtil.logInfo(Hard_Alert_Text)

		Mobile.verifyMatch(Hard_Alert_Text, Pd_Hard_Alert, false)

		Mobile.tap(findTestObject('Mobile/Common/Btn_OK'), 0)
	}
}
