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


GlobalVariable.ProductName = ProductName

for(a=0;a<=3;a++)
{

if(Mobile.verifyElementExist(findTestObject('Mobile/Common/Icon_Search'), 10, FailureHandling.OPTIONAL)) {
	
	Mobile.tap(findTestObject('Mobile/Common/Icon_Search'), 1, FailureHandling.STOP_ON_FAILURE)
	
	Mobile.waitForElementPresent(findTestObject('Mobile/Common/Field_Search_EnterText'), 0, FailureHandling.STOP_ON_FAILURE)
	
	Mobile.setText(findTestObject('Mobile/Common/Field_Search_EnterText'), ProductName, 0)
	
	Mobile.hideKeyboard(FailureHandling.OPTIONAL)
	
	break

	}
else
{
	Mobile.tap(findTestObject('Mobile/Common/Icon_X'), 10, FailureHandling.OPTIONAL)
	
	Mobile.hideKeyboard(FailureHandling.OPTIONAL)
}
}