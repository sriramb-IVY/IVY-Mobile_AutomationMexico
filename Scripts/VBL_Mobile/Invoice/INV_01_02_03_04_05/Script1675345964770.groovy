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

//
//Mobile.startApplication(GlobalVariable.APK_File, false)
//
//Mobile.tap(findTestObject('XXXXXXXXXXXX/Re-usable/Mobile Login/Menu'), 0)
//
//Mobile.callTestCase(findTestCase('VBL_Mobile/Re-Usables/Select_Retailer and Going_OrderInvoice_Screen'), [('RetailerName') : findTestData('VBL_Mobile Input Data/Invoice').getValue(
//            'Retailer_Name', 1)], FailureHandling.STOP_ON_FAILURE)
if (Mobile.verifyElementExist(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/FilterScreen/Filter-Icon'), 2, FailureHandling.OPTIONAL)) {
    'Load Management menu visible'
} else {
    Mobile.callTestCase(findTestCase('Test Cases/VBL_Mobile/Re-Usables/Mobile_Login/Relaunch APK'), [:], FailureHandling.CONTINUE_ON_FAILURE)

    Mobile.tap(findTestObject('XXXXXXXXXXXX/Re-usable/Mobile Login/Menu'), 0)

    Mobile.callTestCase(findTestCase('VBL_Mobile/Re-Usables/Select_Retailer and Going_OrderInvoice_Screen'), [(RetailerName) : findTestData('VBL_Mobile Input Data/Invoice').getValue('Retailer_Name', 1)], FailureHandling.STOP_ON_FAILURE)
}

Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/Star image'), 0, FailureHandling.OPTIONAL)

Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/FilterScreen/FilterScreen-clear btn'), 0, FailureHandling.OPTIONAL)

Mobile.verifyElementExist(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/FilterScreen/Filter-Icon'), 0)

Mobile.verifyElementVisible(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/FilterScreen/Filter-Icon'), 0)

Mobile.takeScreenshot()

println('Verified : the Filters Icon in Order&Invoice screen')

Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/FilterScreen/Filter-Icon'), 5)

//To verify the Filters Icon in Order&Invoice screen
Mobile.delay(2)

String sheet_name = 'Invoice'

String file_name = 'Mobile Input data'

ArrayList<String> FilterSubMenu = CustomKeywords.'poi.VBL.Automation.GetAllDataRow_Flag.getAllDataRow'(file_name, sheet_name, 'FilterSubMenu')

for (int i = 0; i < FilterSubMenu.size(); i++) {
    println(FilterSubMenu.get(i))

    GlobalVariable.label = FilterSubMenu.get(i)

    Mobile.verifyElementExist(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/FilterScreen/FilterScreen-SubMenu(Global)'), 0)

    Mobile.verifyElementText(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/FilterScreen/FilterScreen-SubMenu(Global)'), FilterSubMenu.get(i))
}

Mobile.takeScreenshot()

println('Verified : product filters should be enabled (Category, Brand, flavour, container, sku, pack)')

//To verify the Filter Submenu details
GlobalVariable.label = findTestData('VBL_Mobile Input Data/Invoice').getValue('FilterSubMenu', 1)

Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/FilterScreen/FilterScreen-SubMenu(Global)'), 5)

GlobalVariable.label = findTestData('VBL_Mobile Input Data/Invoice').getValue('FilterSubMenu_Label', 1)

Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/FilterScreen/FilterScreen-SubMenuValue(Global)'), 5)

Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/FilterScreen/FilterScreen-Apply btn'), 0)

Mobile.delay(3)

GlobalVariable.ProductName = findTestData('VBL_Mobile Input Data/Invoice').getValue('FilterSubMenu_ProductName', 1)

Mobile.verifyElementExist(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/ProductName-(Global)'), 5, FailureHandling.STOP_ON_FAILURE)

Mobile.verifyElementText(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/ProductName-(Global)'), GlobalVariable.ProductName, FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

println('Verified : Products should be filtered out in the order screen based on the filter selection')

SkuListCount_AfterAppiledFilter = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/OrderInvoice_Title'), 0)

println('SkuListCount_AfterAppiledFilter' + SkuListCount_AfterAppiledFilter)

SkuListCount_After_AppiledFilter = SkuListCount_AfterAppiledFilter.replaceAll('[Order & Invoice()]', '')

Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/FilterScreen/Filter-Icon'), 5)

Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/FilterScreen/FilterScreen-clear btn'), 0)

Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/FilterScreen/FilterScreen-Apply btn'), 0)

Mobile.delay(2)

SkuListCount_RemoveFilter = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/OrderInvoice_Title'), 0)

SkuListCount_before_AppiledFilter = SkuListCount_RemoveFilter.replaceAll('[Order & Invoice()]', '')

println('SkuListCount_before_AppiledFilter' + SkuListCount_before_AppiledFilter)

Mobile.verifyNotMatch(SkuListCount_before_AppiledFilter, SkuListCount_After_AppiledFilter, false, FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

println('Verified : User should be able to clear the applied filters')

Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Reusable Object/Product Search Icon'), 3, FailureHandling.OPTIONAL)

GlobalVariable.ProductName = findTestData('VBL_Mobile Input Data/Invoice').getValue('Product_Name', 1)

Mobile.setText(findTestObject('XXXXXXXXXXXX/Mobile Part/Reusable Object/Product Enter Search'), GlobalVariable.ProductName, 5)

Mobile.delay(3, FailureHandling.STOP_ON_FAILURE)

Mobile.verifyElementText(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/ProductName-(Global)'), GlobalVariable.ProductName, FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

println('Verified : Products should be filtered out in the order screen based on the Search text')

//completed
Mobile.tap(findTestObject('Mobile/Common/Icon_Back'), 2, FailureHandling.OPTIONAL)

Mobile.tap(findTestObject('Mobile/Common/Btn_OK'), 1, FailureHandling.OPTIONAL)

Mobile.delay(3)

Mobile.swipe(0, 150, 0, 400, FailureHandling.OPTIONAL)

Mobile.delay(2)

Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/Menu-OrderInvoice'), 2, FailureHandling.OPTIONAL)

WebUI.callTestCase(findTestCase('VBL_Mobile/Re-Usables/OrderPopUp(EditOrder)'), [:], FailureHandling.OPTIONAL)

