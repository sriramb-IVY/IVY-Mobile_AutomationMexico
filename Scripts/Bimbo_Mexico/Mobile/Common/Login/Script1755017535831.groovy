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

if (Mobile.waitForElementPresent(findTestObject('Bimbo_Mexico/Mobile/Login/Field_Username_Text'), 10, FailureHandling.OPTIONAL)) {
    Mobile.sendKeys(findTestObject('Bimbo_Mexico/Mobile/Login/Field_Username_Text'), GlobalVariable.VanSeller_UserName)

    Mobile.sendKeys(findTestObject('Bimbo_Mexico/Mobile/Login/Field_Password_Text'), GlobalVariable.VanSeller_Password)

    Mobile.tap(findTestObject('Bimbo_Mexico/Mobile/Login/btn_Login'), 0)

    if (Mobile.waitForElementPresent(findTestObject('Mobile/Common/Popup_Title_ContinueInThisDevice'), 5, FailureHandling.OPTIONAL)) {
        Mobile.tap(findTestObject('Mobile/Common/Btn_YES'), 10)
    }
    


}

