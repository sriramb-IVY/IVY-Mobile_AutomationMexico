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

if (Mobile.waitForElementPresent(findTestObject('Bimbo_Mexico/Mobile/Master_Download/Mapped_Employee_Select'), 10, FailureHandling.OPTIONAL)) {
    Mobile.tap(findTestObject('Bimbo_Mexico/Mobile/Master_Download/Mapped_Employee_Select'), 10)

    if (Mobile.waitForElementPresent(findTestObject('Bimbo_Mexico/Mobile/Master_Download/Pop-up-Master_Downloading'), 5, 
        FailureHandling.OPTIONAL)) 
	
    if (Mobile.waitForElementPresent(findTestObject('Bimbo_Mexico/Mobile/Master_Download/Permission_Popup/Location_Permission/Pop-up_Location_Permission'), 
        5, FailureHandling.OPTIONAL)) {
        Mobile.tap(findTestObject('Bimbo_Mexico/Mobile/Master_Download/Permission_Popup/Location_Permission/Btn - While using the app'), 
            10)
    }
    
    if (Mobile.waitForElementPresent(findTestObject('Bimbo_Mexico/Mobile/Master_Download/Permission_Popup/NearByDevice/Pop-up - NearByDevice_Permission'), 
        5, FailureHandling.OPTIONAL)) {
        Mobile.tap(findTestObject('Bimbo_Mexico/Mobile/Master_Download/Permission_Popup/NearByDevice/Btn - Allow'), 10)
    }
}

