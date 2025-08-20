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


//Mobile.startApplication(GlobalVariable.APKFile, false)



WebUI.callTestCase(findTestCase('Product_Mobile/Common/Store_Menu_Selection'), [('MenuName') : findTestData('Mobile Input Data/Common').getValue(
            'Menu', 4), ('SubMenuName') : findTestData('Mobile Input Data/Common').getValue('Menu', 1)], FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('Product_Mobile/Common/Clear_Star_Filter_Default_Selection'), [:], FailureHandling.STOP_ON_FAILURE)

Mobile.verifyElementExist(findTestObject('Mobile/Common/Icon_FunnelFilter'), 0)

Mobile.verifyElementVisible(findTestObject('Mobile/Common/Icon_FunnelFilter'), 0)

Mobile.takeScreenshot()

println('Verified : the Filters Icon in Order&Invoice screen')

Mobile.tap(findTestObject('Mobile/Common/Icon_FunnelFilter'), 5)

//To verify the Filters Icon in Order&Invoice screen
Mobile.delay(2)

String sheet_name = 'Invoice'

String file_name = 'Mobile Input data'

ArrayList<String> FilterSubMenu = CustomKeywords.'poi.Automation.GetAllDataRow_Flag.getAllDataRow'(file_name, sheet_name, 
    'FilterSubMenu')

for (int i = 0; i < FilterSubMenu.size(); i++) {
    if (FilterSubMenu.get(i) != 'null') {
        println(FilterSubMenu.get(i))

        GlobalVariable.label = FilterSubMenu.get(i)

        Mobile.verifyElementExist(findTestObject('Mobile/Common/FilterScreen-MenuList(Global)'), 0)

        Mobile.verifyElementText(findTestObject('Mobile/Common/FilterScreen-MenuList(Global)'), FilterSubMenu.get(i))
    }
}

Mobile.takeScreenshot()

println('Verified : product filters should be enabled (Category, Brand, Sub Brand,Parent sku)')

Mobile.tap(findTestObject('Mobile/Common/Btn_Apply'), 0)

WebUI.callTestCase(findTestCase('Product_Mobile/Common/Funnel_Filter'), [('Filter_Hierarchy') : findTestData('Mobile Input Data/Invoice').getValue('FilterSubMenu', 1), ('Hierarchy_Value') : findTestData('Mobile Input Data/Invoice').getValue('FilterSubMenu_Label', 1)], 
    FailureHandling.STOP_ON_FAILURE)

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Search_Product'), [('ProductName') : findTestData('Mobile Input Data/Invoice').getValue('FilterSubMenu_ProductName', 1)],
	FailureHandling.STOP_ON_FAILURE)

Mobile.verifyElementExist(findTestObject('Mobile/OrderInvoice/ProductName-(Global)'), 5, FailureHandling.STOP_ON_FAILURE)

Mobile.verifyElementText(findTestObject('Mobile/OrderInvoice/ProductName-(Global)'), GlobalVariable.ProductName, FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

println('Verified : Products should be filtered out in the order screen based on the filter selection')

SkuListCount_AfterAppiledFilter = Mobile.getText(findTestObject('Mobile/OrderInvoice/OrderInvoice_Title'), 0)

println('SkuListCount_AfterAppiledFilter' + SkuListCount_AfterAppiledFilter)

SkuListCount_After_AppiledFilter = SkuListCount_AfterAppiledFilter.replaceAll('[Stock and Order ()]', '')

Mobile.tap(findTestObject('Mobile/Common/Icon_X'), 5)

Mobile.tap(findTestObject('Mobile/Common/Icon_FunnelFilter'), 5)

Mobile.tap(findTestObject('Mobile/Common/Btn_Clear'), 0)

Mobile.tap(findTestObject('Mobile/Common/Btn_Apply'), 0)

Mobile.delay(2)

Mobile.takeScreenshot()

SkuListCount_RemoveFilter = Mobile.getText(findTestObject('Mobile/OrderInvoice/OrderInvoice_Title'), 0)

SkuListCount_before_AppiledFilter = SkuListCount_RemoveFilter.replaceAll('[Stock and Order ()]', '')

println('SkuListCount_before_AppiledFilter' + SkuListCount_before_AppiledFilter)

Mobile.verifyNotMatch(SkuListCount_before_AppiledFilter, SkuListCount_After_AppiledFilter, false, FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

println('Verified : User should be able to clear the applied filters')

Mobile.tap(findTestObject('Mobile/Common/Icon_Search'), 3, FailureHandling.OPTIONAL)

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Search_Product'), [('ProductName') : findTestData('Mobile Input Data/Invoice').getValue('Product_Name', 1)],
	FailureHandling.STOP_ON_FAILURE)

Mobile.verifyElementText(findTestObject('Mobile/OrderInvoice/ProductName-(Global)'), GlobalVariable.ProductName, FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

println('Verified : Products should be filtered out in the order screen based on the Search text')

//completed
Mobile.tap(findTestObject('Mobile/Common/Icon_Back'), 2, FailureHandling.OPTIONAL)

Mobile.tap(findTestObject('Mobile/Common/Btn_OK'), 1, FailureHandling.OPTIONAL)

