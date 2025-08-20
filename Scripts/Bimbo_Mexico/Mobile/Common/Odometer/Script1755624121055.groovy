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

if (Mobile.verifyElementVisible(findTestObject('Bimbo_Mexico/Mobile/Side_Bar/Btn_Burger_Menu'), 1, FailureHandling.OPTIONAL)) {
    Mobile.tap(findTestObject('Bimbo_Mexico/Mobile/Side_Bar/Btn_Burger_Menu'), 0)

    if (Mobile.verifyElementVisible(findTestObject('Bimbo_Mexico/Mobile/Side_Bar/Menu_Start of the day'), 1, FailureHandling.OPTIONAL)) {
        Mobile.tap(findTestObject('Bimbo_Mexico/Mobile/Side_Bar/Menu_Start of the day'), 0)

        if (Mobile.verifyElementVisible(findTestObject('Bimbo_Mexico/Mobile/Start of the day/Sub_Menu_Odometer'), 1, FailureHandling.OPTIONAL)) {
            Mobile.tap(findTestObject('Bimbo_Mexico/Mobile/Start of the day/Sub_Menu_Odometer'), 0)

            if (Mobile.verifyElementVisible(findTestObject('Bimbo_Mexico/Mobile/Odometer/Text_Trip_Start'), 1, FailureHandling.OPTIONAL)) {
                Mobile.setText(findTestObject('Bimbo_Mexico/Mobile/Odometer/Text_Trip_Start'), GlobalVariable.Odometer_Trip_Start, 
                    0)

                if (Mobile.verifyElementVisible(findTestObject('Bimbo_Mexico/Mobile/Odometer/Btn_Start Journey'), 1, FailureHandling.OPTIONAL)) {
                    Mobile.tap(findTestObject('Bimbo_Mexico/Mobile/Odometer/Btn_Start Journey'), 0)

                    if (Mobile.verifyElementVisible(findTestObject('Bimbo_Mexico/Mobile/Odometer/Pop-up_Do you want to go back'), 
                        1, FailureHandling.OPTIONAL)) {
                        Mobile.tap(findTestObject('Object Repository/Bimbo_Mexico/Mobile/Odometer/Btn_Saved_successfully_OK'), 
                            0)

                        Mobile.verifyElementVisible(findTestObject('Bimbo_Mexico/Mobile/Start of the day/Label_Start of the day'), 
                            1, FailureHandling.OPTIONAL)
                    }
                }
            }
        }
    }
}

