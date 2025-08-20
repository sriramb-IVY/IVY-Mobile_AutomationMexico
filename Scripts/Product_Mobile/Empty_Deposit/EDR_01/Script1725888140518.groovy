import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.chrome.ChromeDriver as ChromeDriver
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.keyword.excel.ExcelKeywords as ExcelKeywords
import internal.GlobalVariable as GlobalVariable
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import com.kms.katalon.core.mobile.keyword.internal.MobileDriverFactory as MobileDriverFactory
import io.appium.java_client.android.AndroidDriver as AndroidDriver
//import io.appium.java_client.MobileElement as MobileElement
import io.appium.java_client.android.nativekey.AndroidKey as AndroidKey
import io.appium.java_client.android.nativekey.KeyEvent as KeyEvent
import io.appium.java_client.AppiumDriver as AppiumDriver
import org.openqa.selenium.Keys as Keys

Mobile.startApplication(GlobalVariable.APKFile, false)

Mobile.tap(findTestObject('Mobile/Common/Btn_Menu'), 0)

if (Mobile.verifyElementExist(findTestObject('Mobile/Store_Actvy/Menu-EmptyReturn'), 2, FailureHandling.OPTIONAL)) {
    'Collection menu visible'
   Mobile.takeScreenshot()
} else {
    Mobile.callTestCase(findTestCase('Product_Mobile/Common/Mobile_Login/Relaunch the app'), [:], FailureHandling.CONTINUE_ON_FAILURE)

    Mobile.tap(findTestObject('Mobile/Common/Btn_Menu'), 0)

    GlobalVariable.RetailerName = findTestData('Mobile Input Data/Empty_Return').getValue('Retailer', 1)

    GlobalVariable.RetailerName = 'Dret7'

    Mobile.tap(findTestObject('Mobile/MainMenu/Menu_Trade Coverage'), 0)

    Mobile.tap(findTestObject('Mobile/Common/Icon_Search'), 5)

    Mobile.setText(findTestObject('Mobile/Common/Field_Search_EnterText'), GlobalVariable.RetailerName, 5)

    Mobile.tap(findTestObject('Mobile/TradeCoverage/First_Retailer_Selection_From_List'), 5)

    Mobile.tap(findTestObject('Mobile/Common/Btn_StartVisit'), 5)

    if (Mobile.verifyElementVisible(findTestObject('Object Repository/Mobile/Common/Popup_Title_LocationValidation'), 5, FailureHandling.OPTIONAL)) {
        Mobile.tap(findTestObject('Mobile/Common/Btn_YES'), 5)
    }
    
    Mobile.delay(6)

    Mobile.swipe(0, 150, 0, 400, FailureHandling.OPTIONAL)
}

Mobile.tap(findTestObject('Mobile/Mobile Part/Store_Actvy/Menu-EmptyDeposit'), 0)

Mobile.delay(2)

Mobile.comment('Validation for Deposit screen and fields')

Mobile.verifyElementExist(findTestObject('Mobile/Empty_Deposit/Deposit_Title'), 5, FailureHandling.STOP_ON_FAILURE)

Mobile.verifyElementExist(findTestObject('Mobile/Common/Btn_Plus'), 5, FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

//Add icon
Mobile.tap(findTestObject('Mobile/Common/Btn_Plus'), 5)

//Deposit create page
Mobile.verifyElementExist(findTestObject('Mobile/Empty_Deposit/DEPOSIT_radio_btn'), 5, FailureHandling.STOP_ON_FAILURE)

Mobile.verifyElementExist(findTestObject('Mobile/Empty_Deposit/Reason_Enter_Dropdown'), 5, FailureHandling.STOP_ON_FAILURE)

Mobile.verifyElementExist(findTestObject('Mobile/Empty_Deposit/Amount_Enter_field'), 5, FailureHandling.STOP_ON_FAILURE)

Mobile.verifyElementExist(findTestObject('Mobile/Empty_Deposit/Discount_Enter_field'), 5, FailureHandling.STOP_ON_FAILURE)

Mobile.verifyElementExist(findTestObject('Mobile/Empty_Deposit/Net_Amount_Enter_field'), 5, FailureHandling.STOP_ON_FAILURE)

Mobile.verifyElementExist(findTestObject('Mobile/Empty_Deposit/Save_Btn'), 5, FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

Mobile.comment('Validation for without reason able to save the deposit')

Mobile.tap(findTestObject('Mobile/Empty_Deposit/Save_Btn'), 5)

AppiumDriver driver = MobileDriverFactory.getDriver()

reason_select_alert = driver.findElementByXPath('//android.widget.Toast[1]').getText()

KeywordUtil.logInfo('Reason not selected alert msg :' + reason_select_alert)

Mobile.comment('Validation for Return Not Available when deposit not created ')

Mobile.verifyElementNotExist(findTestObject('Object Repository/Mobile/Empty_Deposit/Return_Radio_btn'), 5, FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

//Select deposit radio btn
Mobile.tap(findTestObject('Mobile/Empty_Deposit/DEPOSIT_radio_btn'), 5)

Mobile.tap(findTestObject('Mobile/Empty_Deposit/Reason_Enter_Dropdown'), 5)

Mobile.setText(findTestObject('Mobile/Empty_Deposit/Reason_Enter_Dropdown'), findTestData('Mobile Input Data/Empty_Deposit').getValue('Reason', 1), 0)

//Mobile.sendKeys(findTestObject('Mobile/Empty_Deposit/Reason_Enter_Dropdown'), Keys.chord(Keys.DOWN, Keys.ENTER))

Mobile.delay(3)

Mobile.tap(findTestObject('Mobile/Empty_Deposit/Reason_Enter_Dropdown'), 2)

Mobile.delay(3)

x_val = Mobile.getAttribute(findTestObject('Mobile/Empty_Deposit/Reason_Enter_Dropdown'), 'x',
	0, FailureHandling.STOP_ON_FAILURE)

y_val = Mobile.getAttribute(findTestObject('Mobile/Empty_Deposit/Reason_Enter_Dropdown'), 'y',
	0, FailureHandling.STOP_ON_FAILURE)

int x = Integer.parseInt(x_val) + 0

int y = Integer.parseInt(y_val) + 100

println(x)

println(y)

Mobile.tapAtPosition(x, y)

Mobile.tapAtPosition(x, y)

Mobile.delay(10)

Mobile.tap(findTestObject('Mobile/Empty_Deposit/Amount_Enter_field'), 0)

Mobile.setText(findTestObject('Mobile/Empty_Deposit/Amount_Enter_field'), findTestData('Mobile Input Data/Empty_Deposit').getValue('Amount', 1), 0)

Mobile.hideKeyboard(FailureHandling.OPTIONAL)

Mobile.delay(2)

Amount_enter = Mobile.getText(findTestObject('Mobile/Empty_Deposit/Amount_Enter_field'), 0)

Mobile.verifyMatch(Amount_enter, findTestData('Mobile Input Data/Empty_Deposit').getValue('Amount', 1), false, FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

Amount_Editable = Mobile.getAttribute(findTestObject('Mobile/Empty_Deposit/Amount_Enter_field'), 'enabled', 0)

if (Amount_Editable == 'true') {
    KeywordUtil.logInfo('Amount field is editable')
}

Net_Amount_Editable = Mobile.getAttribute(findTestObject('Mobile/Empty_Deposit/Net_Amount_Enter_field'), 'enabled', 0)

if (Net_Amount_Editable == 'false') {
    KeywordUtil.logInfo('Amount field is Non editable')
}

Mobile.takeScreenshot()

//Disocount enter
Mobile.tap(findTestObject('Object Repository/Mobile/Empty_Deposit/Discount_Enter_field'), 0)

Mobile.setText(findTestObject('Object Repository/Mobile/Empty_Deposit/Discount_Enter_field'), findTestData('Mobile Input Data/Empty_Deposit').getValue('Discount', 1), 0)

Mobile.hideKeyboard(FailureHandling.OPTIONAL)

Mobile.delay(2)

Discount_enter = Mobile.getText(findTestObject('Object Repository/Mobile/Empty_Deposit/Discount_Enter_field'), 0)

Net_Amt = (Integer.parseInt(Amount_enter) - Integer.parseInt(Discount_enter))

Double Net_Amount = Double.valueOf(Net_Amt)

Net = Mobile.getText(findTestObject('Object Repository/Mobile/Empty_Deposit/Net_Amount_Enter_field'), 0)

Mobile.verifyEqual(Net_Amount, Double.parseDouble(Net), FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

/*Mobile.tap(findTestObject('Object Repository/Mobile/Empty_Deposit/Discount_Enter_field'), 0)

Mobile.clearText(findTestObject('Object Repository/Mobile/Empty_Deposit/Discount_Enter_field'), 0)

Exceed_disount=(Integer.parseInt(Discount_enter))+20

KeywordUtil.logInfo('Exceed Amount : '+ Exceed_disount)

String Exceed=Exceed_disount

Mobile.setText(findTestObject('Object Repository/Mobile/Empty_Deposit/Discount_Enter_field'),Exceed , 0)

Mobile.hideKeyboard(FailureHandling.OPTIONAL)

Mobile.delay(2)

Mobile.tap(findTestObject('Object Repository/Mobile/Empty_Deposit/Save_Btn'), 0)

Discount_exceed_alert = driver.findElementByXPath('//android.widget.Toast[1]').getText()

KeywordUtil.logInfo('Discount amount cant alert msg :' + Discount_exceed_alert)

Mobile.takeScreenshot()

Alert=findTestData('Mobile Input Data/Empty_Deposit').getValue('Alerts', 1)
Add_alert=Alert+Amount_enter+'.'

KeywordUtil.logInfo('alert msg :' + Add_alert)

Mobile.verifyMatch(Discount_exceed_alert,Add_alert , false, FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()*/
'Discount amt not exceed deposit amount'
Mobile.tap(findTestObject('Object Repository/Mobile/Empty_Deposit/Save_Btn'), 0)

Deposit_Saved_msg = driver.findElementByXPath('//android.widget.Toast[1]').getText()

Mobile.takeScreenshot()

KeywordUtil.logInfo('Deposit saved msg :' + Deposit_Saved_msg)

Mobile.verifyMatch(Deposit_Saved_msg, findTestData('Mobile Input Data/Empty_Deposit').getValue('Alerts', 3), false, FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

GlobalVariable.label = findTestData('Mobile Input Data/Empty_Deposit').getValue('Type', 1)

Type = Mobile.getText(findTestObject('Mobile/Empty_Deposit/Type_get'), 0)

Reason = Mobile.getText(findTestObject('Mobile/Empty_Deposit/Reason_get'), 0)

Amountt = Mobile.getText(findTestObject('Mobile/Empty_Deposit/Amount_get'), 0)

Discount = Mobile.getText(findTestObject('Mobile/Empty_Deposit/Discount_get'), 0)

Net_Amountt = Mobile.getText(findTestObject('Mobile/Empty_Deposit/Net_Amount_get'), 0)

Mobile.verifyMatch(Type, findTestData('Mobile Input Data/Empty_Deposit').getValue('Type', 1), false, FailureHandling.STOP_ON_FAILURE)

Mobile.verifyMatch(Reason, findTestData('Mobile Input Data/Empty_Deposit').getValue('Reason', 1), false, FailureHandling.STOP_ON_FAILURE)

Mobile.verifyEqual(Integer.parseInt(Amountt), Integer.parseInt(Amount_enter), FailureHandling.STOP_ON_FAILURE)

Mobile.verifyEqual(Integer.parseInt(Discount), Integer.parseInt(Discount_enter), FailureHandling.STOP_ON_FAILURE)

Mobile.verifyMatch(Net_Amountt, Net, false, FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

Mobile.tap(findTestObject('Mobile/Common/Btn_Plus'), 0)

Mobile.delay(2)

Mobile.verifyElementExist(findTestObject('Mobile/Empty_Deposit/Return_Radio_btn'), 0)

Mobile.verifyElementExist(findTestObject('Mobile/Empty_Deposit/Reason_Enter_Dropdown'), 5, FailureHandling.STOP_ON_FAILURE)

Mobile.verifyElementExist(findTestObject('Mobile/Empty_Deposit/Amount_Enter_field'), 5, FailureHandling.STOP_ON_FAILURE)

Mobile.verifyElementExist(findTestObject('Mobile/Empty_Deposit/Discount_Enter_field'), 5, FailureHandling.STOP_ON_FAILURE)

Mobile.verifyElementExist(findTestObject('Mobile/Empty_Deposit/Net_Amount_Enter_field'), 5, FailureHandling.STOP_ON_FAILURE)

Mobile.verifyElementExist(findTestObject('Mobile/Empty_Deposit/Save_Btn'), 5, FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

Depositt_Amount = Mobile.getText(findTestObject('Mobile/Empty_Deposit/Deposit_Amt_Header'), 0)

return_amt = (Double.parseDouble(Depositt_Amount) +20)
String ret_amt=return_amt

Mobile.tap(findTestObject('Mobile/Empty_Deposit/Return_Radio_btn'), 0)

Mobile.tap(findTestObject('Mobile/Empty_Deposit/Reason_Enter_Dropdown'), 0)

Mobile.setText(findTestObject('Mobile/Empty_Deposit/Reason_Enter_Dropdown'), findTestData('Mobile Input Data/Empty_Deposit').getValue('Reason', 2), 0)


Mobile.delay(10)

Mobile.tap(findTestObject('Mobile/Empty_Deposit/Amount_Enter_field'), 0)

Mobile.setText(findTestObject('Mobile/Empty_Deposit/Amount_Enter_field'), ret_amt, 0)

Mobile.tap(findTestObject('Mobile/Empty_Deposit/Save_Btn'), 0)

Return_exceed_of_deposit_alert = driver.findElementByXPath('//android.widget.Toast[1]').getText()

Mobile.takeScreenshot()

KeywordUtil.logInfo('Discount amount cant alert msg :' + Return_exceed_of_deposit_alert)

Alert1=findTestData('Mobile Input Data/Empty_Deposit').getValue('Alerts', 4)

Deposit_alert=Alert1+Depositt_Amount+'.'

KeywordUtil.logInfo('alert msg :' + Deposit_alert)

Mobile.verifyMatch(Return_exceed_of_deposit_alert,Deposit_alert , false, FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

below_ret_amt = (Double.parseDouble(Depositt_Amount) -20)

String below_ret_amt=below_ret_amt

Mobile.tap(findTestObject('Mobile/Empty_Deposit/Amount_Enter_field'), 0)

Mobile.clearText(findTestObject('Mobile/Empty_Deposit/Amount_Enter_field'), 0)

Mobile.setText(findTestObject('Mobile/Empty_Deposit/Amount_Enter_field'), below_ret_amt, 0)

Return_Net = Mobile.getText(findTestObject('Object Repository/Mobile/Empty_Deposit/Net_Amount_Enter_field'), 0)

Mobile.tap(findTestObject('Mobile/Empty_Deposit/Save_Btn'), 0)

Return_Saved_msg = driver.findElementByXPath('//android.widget.Toast[1]').getText()

Mobile.takeScreenshot()

KeywordUtil.logInfo('Return saved msg :' + Return_Saved_msg)

Mobile.verifyMatch(Return_Saved_msg, findTestData('Mobile Input Data/Empty_Deposit').getValue('Alerts', 3), false, FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

GlobalVariable.label = findTestData('Mobile Input Data/Empty_Deposit').getValue('Type', 2)

Return_Type = Mobile.getText(findTestObject('Mobile/Empty_Deposit/Type_get'), 0)

Return_Reason = Mobile.getText(findTestObject('Mobile/Empty_Deposit/Reason_get'), 0)

Return_Amountt = Mobile.getText(findTestObject('Mobile/Empty_Deposit/Amount_get'), 0)

Return_Discount = Mobile.getText(findTestObject('Mobile/Empty_Deposit/Discount_get'), 0)

Return_Net_Amountt = Mobile.getText(findTestObject('Mobile/Empty_Deposit/Net_Amount_get'), 0)

Mobile.verifyMatch(Return_Type, findTestData('Mobile Input Data/Empty_Deposit').getValue('Type', 2), false, FailureHandling.STOP_ON_FAILURE)

Mobile.verifyMatch(Return_Reason, findTestData('Mobile Input Data/Empty_Deposit').getValue('Reason', 2), false, FailureHandling.STOP_ON_FAILURE)

Mobile.verifyEqual(Integer.parseInt(Return_Amountt), Integer.parseInt(below_ret_amt), FailureHandling.STOP_ON_FAILURE)

Mobile.verifyEqual(Integer.parseInt(Discount), '0', FailureHandling.STOP_ON_FAILURE)

Mobile.verifyMatch(below_ret_amt,Return_Net_Amountt , false, FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()




