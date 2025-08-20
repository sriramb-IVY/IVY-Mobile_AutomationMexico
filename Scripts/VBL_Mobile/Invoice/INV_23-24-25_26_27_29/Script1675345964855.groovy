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

//Mobile.startApplication(GlobalVariable.APK_File, false)
//
//Mobile.tap(findTestObject('XXXXXXXXXXXX/Re-usable/Mobile Login/Menu'), 0)
//
//Mobile.callTestCase(findTestCase('VBL_Mobile/Re-Usables/Select_Retailer and Going_OrderInvoice_Screen'), [(GlobalVariable.RetailerName) : findTestData('VBL_Mobile Input Data/Invoice').getValue(
//            'Retailer_Name', 1)], FailureHandling.STOP_ON_FAILURE)
if (Mobile.verifyElementExist(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/FilterScreen/Filter-Icon'), 
    2, FailureHandling.OPTIONAL)) {
    'Load Management menu visible'
} else {
    Mobile.callTestCase(findTestCase('Test Cases/VBL_Mobile/Re-Usables/Mobile_Login/Relaunch APK'), [:], FailureHandling.CONTINUE_ON_FAILURE)

    Mobile.tap(findTestObject('XXXXXXXXXXXX/Re-usable/Mobile Login/Menu'), 0)

    Mobile.callTestCase(findTestCase('VBL_Mobile/Re-Usables/Select_Retailer and Going_OrderInvoice_Screen'), [(RetailerName) : findTestData(
                'VBL_Mobile Input Data/Invoice').getValue('Retailer_Name', 1)], FailureHandling.STOP_ON_FAILURE)
}

Mobile.verifyElementExist(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/Star image'), 0)

Mobile.verifyElementVisible(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/Star image'), 0)

Mobile.takeScreenshot()

println('Verified : Star icon filter Field')

Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/Star image'), 0)

Mobile.verifyElementExist(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/StarIconFilter/Special Filter'), 
    0)

Mobile.verifyElementVisible(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/StarIconFilter/Special Filter'), 
    0)

Mobile.verifyElementExist(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/StarIconFilter/Must Sell'), 0)

Mobile.verifyElementVisible(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/StarIconFilter/Must Sell'), 
    0)

Mobile.verifyElementExist(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/StarIconFilter/Focus Brand'), 
    0)

Mobile.verifyElementVisible(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/StarIconFilter/Focus Brand'), 
    0)

Mobile.takeScreenshot()

println('Verified : Star icon filter Screen')

Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/StarIconFilter/Must Sell'), 0)

Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/StarIconFilter/FilterScreen-Apply btn'), 0)

Product_Category = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/OrderInvoice_Title'), 
    0)

Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Reusable Object/Product Search Icon'), 4, FailureHandling.OPTIONAL)

GlobalVariable.ProductName = findTestData('VBL_Web Input Data/MustSell').getValue('SkuName', 1)

Mobile.setText(findTestObject('XXXXXXXXXXXX/Mobile Part/Reusable Object/Product Enter Search'), GlobalVariable.ProductName, 
    5)

Mobile.delay(3, FailureHandling.STOP_ON_FAILURE)

MustSell = findTestData('VBL_Web Input Data/MustSell').getValue('Verify', 2)

println(MustSell)

WebUI.verifyMatch(Product_Category.substring(0, 9), MustSell, false, FailureHandling.STOP_ON_FAILURE)

Mobile.verifyElementText(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/ProductName-(Global)'), GlobalVariable.ProductName)

Mobile.takeScreenshot()

println('Verified : Star icon filter mustsell menu ')

Mobile.callTestCase(findTestCase('VBL_Mobile/Invoice/Reusable/Order_Generate(only Piece)_CT'), [('ProductName') : findTestData(
            'VBL_Mobile Input Data/Invoice').getValue('Product_Name', 4), ('KeypadNumber') : findTestData('VBL_Mobile Input Data/Invoice').getValue(
            'Keypad_Number', 1)], FailureHandling.STOP_ON_FAILURE)

Mobile.callTestCase(findTestCase('VBL_Mobile/Invoice/Reusable/Order_Generate(only Piece)_CT'), [('ProductName') : findTestData(
            'VBL_Mobile Input Data/Invoice').getValue('Product_Name', 5), ('KeypadNumber') : findTestData('VBL_Mobile Input Data/Invoice').getValue(
            'Keypad_Number', 1)], FailureHandling.STOP_ON_FAILURE)

Mobile.hideKeyboard(FailureHandling.OPTIONAL)

Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/Next Btn-OrderInvoiceScreen'), 0)

Mobile.verifyElementNotVisible(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/MustSell_Alert/Must Sell-Title'), 
    2)

Mobile.verifyElementNotExist(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/MustSell_Alert/Must Sell-Title'), 
    2)

Mobile.takeScreenshot()

println('Verified : Star icon filter Mustsell PopUp screen not shown')

Mobile.tap(findTestObject('Mobile/Common/Icon_Back'), 0)

Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/Piece_Field'), 0)

Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Reusable Object/Box-Keypad/Back Arrow-Keypad'), 0)

Mobile.tap(findTestObject('Mobile/Common/Btn_OK'), 0)

Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/Star image'), 0)

Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/FilterScreen/FilterScreen-clear btn'), 0)

Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Reusable Object/Product Search Icon'), 4, FailureHandling.OPTIONAL)

Non_MustSell_Product = findTestData('VBL_Mobile Input Data/Invoice').getValue('Product_Name', 1)

Mobile.setText(findTestObject('XXXXXXXXXXXX/Mobile Part/Reusable Object/Product Enter Search'), Non_MustSell_Product, 5)

Mobile.delay(3, FailureHandling.STOP_ON_FAILURE)

Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/Piece_Field'), 0)

Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Reusable Object/Box-Keypad/1-keypad'), 0)

Mobile.tap(findTestObject('Mobile/Common/Btn_OK'), 0)

Mobile.hideKeyboard(FailureHandling.OPTIONAL)

Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/Next Btn-OrderInvoiceScreen'), 0)

Mobile.verifyElementVisible(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/MustSell_Alert/Must Sell-Title'), 
    0)

Mobile.verifyElementExist(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/MustSell_Alert/Must Sell-Title'), 
    0)

Mobile.takeScreenshot()

println('Verified : Star icon filter Mustsell PopUp screen displayed')

Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/MustSell_Alert/MSL_Alert_Cancel btn'), 0)

Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/Star image'), 0)

Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/StarIconFilter/Focus Brand'), 0)

Mobile.hideKeyboard(FailureHandling.OPTIONAL)

Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/StarIconFilter/FilterScreen-Apply btn'), 0)

String Product_Category = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/OrderInvoice_Title'), 
    0)

Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Reusable Object/Product Search Icon'), 4, FailureHandling.OPTIONAL)

GlobalVariable.ProductName = findTestData('VBL_Web Input Data/FocusBrand').getValue('SkuName', 1)

Mobile.setText(findTestObject('XXXXXXXXXXXX/Mobile Part/Reusable Object/Product Enter Search'), GlobalVariable.ProductName, 
    5)

Mobile.delay(2, FailureHandling.STOP_ON_FAILURE)

FocusBrand = findTestData('VBL_Web Input Data/FocusBrand').getValue('Verify', 2)

println(FocusBrand)

WebUI.verifyMatch(Product_Category.substring(0, 11), FocusBrand, false, FailureHandling.STOP_ON_FAILURE)

Mobile.verifyElementText(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/ProductName-(Global)'), GlobalVariable.ProductName)

Mobile.takeScreenshot()

println('Verified : Star icon filter FocusBrand menu ')

//completed
Mobile.tap(findTestObject('Mobile/Common/Icon_Back'), 2, FailureHandling.OPTIONAL)

Mobile.tap(findTestObject('Mobile/Common/Btn_OK'), 1, FailureHandling.OPTIONAL)

Mobile.delay(2, FailureHandling.STOP_ON_FAILURE)

Mobile.swipe(0, 150, 0, 300, FailureHandling.OPTIONAL)

Mobile.delay(2)

Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/Menu-OrderInvoice'), 2, FailureHandling.OPTIONAL)

WebUI.callTestCase(findTestCase('VBL_Mobile/Re-Usables/OrderPopUp(EditOrder)'), [:], FailureHandling.OPTIONAL)

