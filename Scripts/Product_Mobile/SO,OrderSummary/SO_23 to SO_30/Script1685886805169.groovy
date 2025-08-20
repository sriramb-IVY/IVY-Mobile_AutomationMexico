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

'Verified : Star icon filter Field'

if (Mobile.verifyElementExist(findTestObject('Object Repository/Mobile/Store_Actvy/Menu-OrderInvoice'), 2, FailureHandling.OPTIONAL)) {
	'order invoice menu visible'
} else {
	Mobile.callTestCase(findTestCase('Product_Mobile/Common/Mobile_Login/Relaunch the app'), [:], FailureHandling.CONTINUE_ON_FAILURE)

	WebUI.callTestCase(findTestCase('Product_Mobile/Common/Seller_Menu_Selection'), [('MenuName') : findTestData('Mobile Input Data/Common').getValue('Menu', 3), ('SubMenuName') : findTestData('Mobile Input Data/Common').getValue('Menu', 1)], FailureHandling.STOP_ON_FAILURE)

	WebUI.callTestCase(findTestCase('Product_Mobile/Common/TradeCoverage(SelectRetailer)'), [('RetailerName') : findTestData('Mobile Input Data/Invoice').getValue('Retailer_Name', 1)], FailureHandling.STOP_ON_FAILURE)

	WebUI.callTestCase(findTestCase('Product_Mobile/Common/StartVisit'), [:], FailureHandling.STOP_ON_FAILURE)
}

WebUI.callTestCase(findTestCase('Product_Mobile/Common/Store_Menu_Selection'), [('MenuName') : findTestData('Mobile Input Data/Common').getValue('Menu', 4), ('SubMenuName') : findTestData('Mobile Input Data/Common').getValue('Menu', 1)], FailureHandling.STOP_ON_FAILURE)

Mobile.verifyElementVisible(findTestObject('Mobile/Common/Icon_Star'), 0)

Mobile.takeScreenshot()

Mobile.tap(findTestObject('Mobile/Common/Icon_Star'), 0)

Mobile.takeScreenshot()

Mobile.verifyElementExist(findTestObject('Mobile/Common/Filter_Special'), 0)

Mobile.verifyElementVisible(findTestObject('Mobile/Common/Filter_Special'), 0)

Mobile.verifyElementExist(findTestObject('Mobile/Common/Filter_MustSell'), 0)

Mobile.verifyElementVisible(findTestObject('Mobile/Common/Filter_MustSell'), 0)

Mobile.verifyElementExist(findTestObject('Mobile/Common/Filter_FocusBrand'), 0)

Mobile.verifyElementVisible(findTestObject('Mobile/Common/Filter_FocusBrand'), 0)

Mobile.takeScreenshot()

println('Verified : Star icon filter Screen')

Mobile.tap(findTestObject('Mobile/Common/Filter_MustSell'), 0)

Mobile.tap(findTestObject('Mobile/Common/Btn_Apply'), 0)

Product_Category = Mobile.getText(findTestObject('Mobile/OrderInvoice/OrderInvoice_Title'), 0)

Mobile.takeScreenshot()

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Search_Product'), [('ProductName') : findTestData('Web Input Data/MustSell').getValue('SkuName', 1)], FailureHandling.STOP_ON_FAILURE)

MustSell = findTestData('Web Input Data/MustSell').getValue('Verify', 2)

println(MustSell)

Mobile.takeScreenshot()

WebUI.verifyMatch(Product_Category.substring(0, 9), MustSell, false, FailureHandling.STOP_ON_FAILURE)

Mobile.verifyElementText(findTestObject('Mobile/OrderInvoice/ProductName-(Global)'), GlobalVariable.ProductName)

Mobile.takeScreenshot()

println('Verified : Star icon filter mustsell menu ')

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Enter_PIECE_Qty'), [('Quantity') : findTestData('Mobile Input Data/Invoice').getValue('Keypad_Number', 2)], FailureHandling.STOP_ON_FAILURE)

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Search_Product'), [('ProductName') : findTestData('Web Input Data/MustSell').getValue('SkuName', 2)], FailureHandling.STOP_ON_FAILURE)

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Enter_PIECE_Qty'), [('Quantity') : findTestData('Mobile Input Data/Invoice').getValue('Keypad_Number', 2)], FailureHandling.STOP_ON_FAILURE)

Mobile.tap(findTestObject('Mobile/Common/Btn_Next'), 0)

Mobile.takeScreenshot()

Mobile.verifyElementNotVisible(findTestObject('Mobile/OrderInvoice/MustSell_Alert/Must Sell-Title'), 2)

Mobile.verifyElementNotExist(findTestObject('Mobile/OrderInvoice/MustSell_Alert/Must Sell-Title'), 2)

Mobile.takeScreenshot()

println('Verified : Star icon filter Mustsell PopUp screen not shown')

Mobile.tap(findTestObject('Mobile/Common/Icon_Back'), 0)

Mobile.tap(findTestObject('Mobile/Common/Icon_Star'), 0)

Mobile.tap(findTestObject('Mobile/Common/Filter_MustSell'), 0)

Mobile.tap(findTestObject('Mobile/Common/Btn_Apply'), 0)

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Search_Product'), [('ProductName') : findTestData('Web Input Data/MustSell').getValue('SkuName', 2)], FailureHandling.STOP_ON_FAILURE)

Mobile.tap(findTestObject('Mobile/OrderInvoice/Enter_Qty'), 0)

Mobile.tap(findTestObject('Mobile/Common/Vertical keypad-BackSpace'), 1, FailureHandling.OPTIONAL)

WebUI.callTestCase(findTestCase('Product_Mobile/Common/Clear_Star_Filter_Default_Selection'), [:], FailureHandling.STOP_ON_FAILURE)

Non_MustSell_Product = findTestData('Mobile Input Data/Invoice').getValue('Product_Name', 1)

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Search_Product'), [('ProductName') : Non_MustSell_Product], FailureHandling.STOP_ON_FAILURE)

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Enter_PIECE_Qty'), [('Quantity') : findTestData('Mobile Input Data/Invoice').getValue('Keypad_Number', 2)], FailureHandling.STOP_ON_FAILURE)

Mobile.tap(findTestObject('Mobile/Common/Btn_Next'), 0)

Mobile.takeScreenshot()

Mobile.verifyElementVisible(findTestObject('Mobile/OrderInvoice/MustSell_Alert/Must Sell-Title'), 0)

Mobile.verifyElementExist(findTestObject('Mobile/OrderInvoice/MustSell_Alert/Must Sell-Title'), 0)

Mobile.takeScreenshot()

println('Verified : Star icon filter Mustsell PopUp screen displayed')

Mobile.tap(findTestObject('Mobile/OrderInvoice/MustSell_Alert/MSL_Alert_Cancel btn'), 0)

Mobile.tap(findTestObject('Mobile/Common/Icon_Back'), 2, FailureHandling.OPTIONAL)

Mobile.tap(findTestObject('Mobile/Common/Btn_OK'), 1, FailureHandling.OPTIONAL)

WebUI.callTestCase(findTestCase('Product_Mobile/Common/Store_Menu_Selection'), [('MenuName') : findTestData('Mobile Input Data/Common').getValue('Menu', 4), ('SubMenuName') : findTestData('Mobile Input Data/Common').getValue('Menu', 1)], FailureHandling.STOP_ON_FAILURE)

Mobile.tap(findTestObject('Mobile/Common/Icon_Star'), 0)

Mobile.tap(findTestObject('Mobile/Common/Filter_FocusBrand'), 0)

Mobile.hideKeyboard(FailureHandling.OPTIONAL)

Mobile.tap(findTestObject('Mobile/Common/Btn_Apply'), 0)

Mobile.takeScreenshot()

String Product_Category = Mobile.getText(findTestObject('Mobile/OrderInvoice/OrderInvoice_Title'), 0)

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Search_Product'), [('ProductName') : findTestData('Web Input Data/FocusBrand').getValue('SkuName', 1)], FailureHandling.STOP_ON_FAILURE)

FocusBrand = findTestData('Web Input Data/FocusBrand').getValue('Verify', 2)

println(FocusBrand)

WebUI.verifyMatch(Product_Category.substring(0, 11), FocusBrand, false, FailureHandling.STOP_ON_FAILURE)

Mobile.verifyElementText(findTestObject('Mobile/OrderInvoice/ProductName-(Global)'), GlobalVariable.ProductName)

Mobile.takeScreenshot()

println('Verified : Star icon filter FocusBrand menu ')

Mobile.tap(findTestObject('Mobile/Common/Icon_Back'), 2, FailureHandling.OPTIONAL)

not_run: Mobile.tap(findTestObject('Mobile/Common/Btn_OK'), 1, FailureHandling.OPTIONAL)

WebUI.callTestCase(findTestCase('Product_Mobile/Common/Store_Menu_Selection'), [('MenuName') : findTestData('Mobile Input Data/Common').getValue('Menu', 4), ('SubMenuName') : findTestData('Mobile Input Data/Common').getValue('Menu', 1)], FailureHandling.STOP_ON_FAILURE)

Mobile.tap(findTestObject('Mobile/Common/Icon_Star'), 0)

Mobile.delay(2, FailureHandling.STOP_ON_FAILURE)

Mobile.verifyElementExist(findTestObject('Mobile/Common/Filter_Promotion'), 0)

Mobile.tap(findTestObject('Mobile/Common/Filter_Promotion'), 0)

Mobile.tap(findTestObject('Mobile/Common/Btn_Apply'), 0)

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Search_Product'), [('ProductName') : findTestData('Mobile Input Data/Invoice').getValue('Sku_Name', 6)], FailureHandling.STOP_ON_FAILURE)

Mobile.verifyElementVisible(findTestObject('Object Repository/Mobile/OrderInvoice/ProductName-(Global)_SchemeIcon'), 0, FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

println('Verified :  The user viewed Indicator for Scheme SKUs')

Mobile.tap(findTestObject('Object Repository/Mobile/OrderInvoice/ProductName-(Global)'), 0)

Mobile.tap(findTestObject('Object Repository/Mobile/OrderInvoice/Tab-Scheme Details'), 0)

Mobile.takeScreenshot()

Mobile.verifyElementVisible(findTestObject('Object Repository/Mobile/OrderInvoice/ProductName-(Global)_SchemeIcon'), 0, FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

println('Verified : The userviewed the scheme details under the scheme details tab')

Mobile.tap(findTestObject('Mobile/Common/Icon_Back'), 2, FailureHandling.OPTIONAL)

