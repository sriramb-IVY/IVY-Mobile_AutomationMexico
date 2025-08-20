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
import io.appium.java_client.android.AndroidDriver as AndroidDriver
//import io.appium.java_client.MobileElement as MobileElement
import io.appium.java_client.android.nativekey.AndroidKey as AndroidKey
import io.appium.java_client.android.nativekey.KeyEvent as KeyEvent
import com.kms.katalon.core.mobile.keyword.internal.MobileDriverFactory as MobileDriverFactory

//Mobile.startApplication(GlobalVariable.APK_File, false)
//

AndroidDriver<MobileElement> driver = MobileDriverFactory.getDriver()

driver.pressKey(new KeyEvent(AndroidKey.APP_SWITCH))

if (Mobile.verifyElementExist(findTestObject('XXXXXXXXXXXX/Google/RecentApp_CloseAll_Btn'), 2, FailureHandling.OPTIONAL)) {
    Mobile.delay(2)

    Mobile.tap(findTestObject('XXXXXXXXXXXX/Google/RecentApp_CloseAll_Btn'), 0)
} else {
    int y = Mobile.getDeviceHeight()

    int x = Mobile.getDeviceWidth()

    int X1 = (x / 2) + 100

    int Y1 = (y / 2) + 450

    int Y2 = (y / 2) - 450

    Mobile.swipe(X1, Y1, X1, Y2, FailureHandling.OPTIONAL)

    int yy = y / 2

    for (int i = 0; i <= 5; i++) {
        
		Mobile.swipe(10, yy, x, yy, FailureHandling.OPTIONAL)

		//Mobile.switchToNative(FailureHandling.OPTIONAL)
		
        if (Mobile.verifyElementExist(findTestObject('XXXXXXXXXXXX/Google/RecentApp_CloseAll_Btn'), 2, FailureHandling.OPTIONAL)) {
            Mobile.delay(2)

            Mobile.tap(findTestObject('XXXXXXXXXXXX/Google/RecentApp_CloseAll_Btn'), 0)

            break
        }
    }
}

String GetDevice = driver.getCapabilities()

String[] D1 = GetDevice.split('deviceName: ')

println(D1[1])

String GetDevice2 = D1[1]

String[] DeviceName = GetDevice2.split(',')

println(DeviceName[0])

String CloudDeviceName = DeviceName[0]

int b = Mobile.getDeviceHeight()

int a = Mobile.getDeviceWidth()

int A1 = (a / 2) + 100

int B1 = (b / 2) + 350

int B2 = (b / 2) - 350

if (CloudDeviceName.contains('Pixel')) {
    Mobile.pressHome(FailureHandling.OPTIONAL)

    Mobile.swipe(A1, B1, A1, B2, FailureHandling.OPTIONAL)

    int c1 = (b / 2) + 200

    int c2 = (b / 2) - 200

    int c3 = a / 2

    for (i = 0; i <= 5; i++) {
        if (Mobile.verifyElementExist(findTestObject('XXXXXXXXXXXX/Google/IvyCpg Logo in Google'), 2, FailureHandling.OPTIONAL)) {
            Mobile.tap(findTestObject('XXXXXXXXXXXX/Google/IvyCpg Logo in Google'), 0)
			Mobile.delay(2)
            break
        } else {
            Mobile.swipe(c3, c1, c3, c2, FailureHandling.OPTIONAL)

            Mobile.scrollToText('IvyCpg', FailureHandling.OPTIONAL)
        }
    }
} else if (CloudDeviceName.contains('Galaxy')) {
    Mobile.pressHome(FailureHandling.OPTIONAL)

    Mobile.swipe(A1, B1, A1, B2, FailureHandling.OPTIONAL)

    int T1 = (a / 2) + 200

    int T2 = (a / 2) - 200

    int T3 = b / 2

    for (i = 0; i <= 5; i++) {
        if (Mobile.verifyElementExist(findTestObject('XXXXXXXXXXXX/Google/IvyCpg Logo in Google'), 2, FailureHandling.OPTIONAL)) {
            Mobile.tap(findTestObject('XXXXXXXXXXXX/Google/IvyCpg Logo in Google'), 0)
			Mobile.delay(2)
            break
        } else {
            Mobile.swipe(T1, T3, T2, T3, FailureHandling.OPTIONAL)

            Mobile.scrollToText('IvyCpg', FailureHandling.OPTIONAL)
        }
    }
} else {
    Mobile.pressHome(FailureHandling.OPTIONAL)

    Mobile.swipe(A1, B1, A1, B2, FailureHandling.OPTIONAL)

    Mobile.tap(findTestObject('XXXXXXXXXXXX/Google/Search_Field_2 in Google'), 0)

    Mobile.setText(findTestObject('XXXXXXXXXXXX/Google/Search_Field_2 in Google'), 'Ivy', 0, FailureHandling.STOP_ON_FAILURE)

    if (Mobile.verifyElementExist(findTestObject('XXXXXXXXXXXX/Google/IvyCpg Logo in Google'), 2, FailureHandling.OPTIONAL)) {
        Mobile.tap(findTestObject('XXXXXXXXXXXX/Google/IvyCpg Logo in Google'), 0)
    
		Mobile.delay(2)
		
		}
}

