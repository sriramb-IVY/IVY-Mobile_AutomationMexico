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

Mobile.startApplication(GlobalVariable.APKFile, false)

not_run: CustomKeywords.'android.Keywords.android_custom_keywords.Resetapk'()

Mobile.delay(5)

'Camera, Mic, File and, Location permission are allowed'
for (int j = 1; j = 4; j++) {
    if (Mobile.verifyElementNotExist(findTestObject('Bimbo_Mexico/Mobile/Activation/Permission/PermissionAlert_Title_AllowIvyCpg'), 1, FailureHandling.OPTIONAL)) {
        break
    } else {
        Mobile.tap(findTestObject('Bimbo_Mexico/Mobile/Activation/Permission/PermissionAlert_Option_WhileUsingTheApp'), 1, FailureHandling.OPTIONAL)

        Mobile.tap(findTestObject('Bimbo_Mexico/Mobile/Activation/Permission/PermissionAlert_Option_Allow'), 1, FailureHandling.OPTIONAL)
    }
}

if (Mobile.verifyElementVisible(findTestObject('Bimbo_Mexico/Mobile/Activation/Field_ActivationKey_Text'), 1, FailureHandling.OPTIONAL)) {
    Mobile.sendKeys(findTestObject('Bimbo_Mexico/Mobile/Activation/Field_ActivationKey_Text'), GlobalVariable.Activation_Key)

    Mobile.tap(findTestObject('Bimbo_Mexico/Mobile//Activation/Btn_Activation'), 3)

    Mobile.waitForElementPresent(findTestObject('Bimbo_Mexico/Mobile/Activation/pop-up_Activation OK_button'), 100)

    Mobile.tap(findTestObject('Bimbo_Mexico/Mobile/Activation/pop-up_Activation OK_button'), 5)
}

