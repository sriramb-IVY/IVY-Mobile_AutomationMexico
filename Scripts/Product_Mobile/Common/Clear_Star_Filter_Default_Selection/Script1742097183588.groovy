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

for (int q = 0; q <= 3; q++) {
    Mobile.tap(findTestObject('Mobile/Common/Icon_Star'), 3, FailureHandling.OPTIONAL)

    if (Mobile.verifyElementExist(findTestObject('Mobile/Common/Btn_Clear'), 
        2, FailureHandling.OPTIONAL)) {
        Mobile.tap(findTestObject('Mobile/Common/Btn_Clear'), 
            1, FailureHandling.OPTIONAL)

        //Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/FilterScreen/FilterScreen-Apply btn'), 0)
        if (Mobile.verifyElementNotExist(findTestObject('Mobile/Common/Btn_Clear'), 
            2, FailureHandling.OPTIONAL)) {
            break
        } else {
            Mobile.tap(findTestObject('Mobile/Common/Btn_Clear'), 
                0)
        }
    }
}

