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
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil

//GlobalVariable.index = 3
String sheet_name = 'Scheme_CriteriaMapping'

String file_name = 'Mobile Input data'

String s = GlobalVariable.Scheme_name

//String s = 'Location&Channel'
ArrayList<String> Scheme_Code = CustomKeywords.'poi.Automation.GetAllDataRow_Flag.getAllDataRow'(file_name, sheet_name, 
    'Scheme_Code')

ArrayList<String> Scheme_Name = CustomKeywords.'poi.Automation.GetAllDataRow_Flag.getAllDataRow'(file_name, sheet_name, 
    'SchemeName')

//Mobile.callTestCase(findTestCase('Product_Mobile/Common/Web Login and Navigation/DivisionLogin'), [:], FailureHandling.STOP_ON_FAILURE)
WebUI.callTestCase(findTestCase('Product_Mobile/Common/Web Login and Navigation/Navigate to Scheme Master'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.waitForElementNotVisible(findTestObject('Web Part/Re-usable/Page Load'), 100)

not_run: WebUI.click(findTestObject('Web Part/Re-usable/menu_hide_lever'), FailureHandling.STOP_ON_FAILURE)

WebUI.waitForElementNotVisible(findTestObject('Web Part/Re-usable/Page Load'), 100)

//for(i = 0; i < Scheme_Code.size(); i++) {
//Using Split concept to remove the required word from the Scheme_Name:-
String[] scheme_code = s.split(' ', 0)

for (String w : scheme_code) {
    System.out.println(w)

    GlobalVariable.label = w

    break
}

//Entering the Code in the Search field:-
WebUI.waitForElementVisible(findTestObject('Web Part/Scheme Request/Click/click_code_search_field'), 50)

WebUI.click(findTestObject('Web Part/Scheme Request/Click/click_code_search_field'))

WebUI.sendKeys(findTestObject('Web Part/Scheme Request/Click/click_code_search_field'), GlobalVariable.label)

//Selecting the Scheme from the grid:-
GlobalVariable.Scheme_name = Scheme_Name.get(GlobalVariable.index)

KeywordUtil.logInfo(GlobalVariable.Scheme_name)

WebUI.waitForElementVisible(findTestObject('Web Part/Scheme Request/Global Variable/scheme_td_tag'), 50)

WebUI.click(findTestObject('Web Part/Scheme Request/Global Variable/scheme_td_tag'), FailureHandling.STOP_ON_FAILURE)

//Clicking the Edit grid label:-
GlobalVariable.grid = findTestData('Web Input Data/Scheme_Retailer_Master').getValue('Grid_Labels', 1)

WebUI.waitForElementVisible(findTestObject('Web Part/Scheme Request/Global Variable/grid_labels_a_tag'), 
    50)

WebUI.click(findTestObject('Web Part/Scheme Request/Global Variable/grid_labels_a_tag'), FailureHandling.STOP_ON_FAILURE)

WebUI.waitForElementNotVisible(findTestObject('Web Part/Re-usable/Page Load'), 100)

//Clicking the Next Button in the Scheme page:-
WebUI.waitForElementVisible(findTestObject('Web Part/Scheme Request/Click/click_next_button_index_1'), 10)

WebUI.click(findTestObject('Web Part/Scheme Request/Click/click_next_button_index_1'))

WebUI.waitForElementNotVisible(findTestObject('Web Part/Re-usable/Page Load'), 100)

///brand name mapping verification
GlobalVariable.mapping = findTestData('Web Input Data/Scheme_Retailer_Master').getValue('brand_name', 1)

GlobalVariable.label = GlobalVariable.mapping

Scheme_mapped_brand_name = WebUI.getText(findTestObject('Object Repository/Web Part/Re-usable/Global/td_Tag(GridSkuRow)'))

KeywordUtil.logInfo(Scheme_mapped_brand_name)

WebUI.verifyMatch(Scheme_mapped_brand_name, GlobalVariable.Brand_name, false, FailureHandling.STOP_ON_FAILURE)

//Clicking the Next Button in the Promotion Group page:-
WebUI.waitForElementVisible(findTestObject('Web Part/Scheme Request/Click/click_next_button_index_2'), 10)

WebUI.click(findTestObject('Web Part/Scheme Request/Click/click_next_button_index_2'))

WebUI.waitForElementNotVisible(findTestObject('Web Part/Re-usable/Page Load'), 100)

//Clicking the Next button in the Promotion Slab page:-
WebUI.waitForElementVisible(findTestObject('Web Part/Scheme Request/Click/click_next_button_index_4'), 10)

WebUI.click(findTestObject('Web Part/Scheme Request/Click/click_next_button_index_4'))

//Clicking the View icon in the Mapping set:-
GlobalVariable.icons = findTestData('Web Input Data/Scheme_Retailer_Master').getValue('Icons', 1)

WebUI.waitForElementVisible(findTestObject('Web Part/Scheme Request/Global Variable/icons_i_tag'), 0)

WebUI.click(findTestObject('Web Part/Scheme Request/Global Variable/icons_i_tag'))

WebUI.waitForElementNotVisible(findTestObject('Web Part/Re-usable/Page Load'), 100)

//Capturing the Criteria Mapping data to a global variable:-
if (Scheme_Code.get(GlobalVariable.index).equals(Scheme_Code.get(0))) {
    //Location and Channel:-
    GlobalVariable.mapping = findTestData('Web Input Data/Scheme_Retailer_Master').getValue('Location', 2)

    GlobalVariable.location_name = WebUI.getText(findTestObject('Web Part/Scheme Request/Global Variable/mapping_td_tag'))

    KeywordUtil.logInfo(GlobalVariable.location_name)

    WebUI.takeScreenshot()

    GlobalVariable.mapping = findTestData('Web Input Data/Scheme_Retailer_Master').getValue('Channel', 4)

    println(GlobalVariable.mapping)

    GlobalVariable.Channel_name = WebUI.getText(findTestObject('Web Part/Scheme Request/Global Variable/mapping_td_tag'))

    KeywordUtil.logInfo(GlobalVariable.Channel_name)

    WebUI.takeScreenshot()
}

//Location and Distributor:-
if (Scheme_Code.get(GlobalVariable.index).equals(Scheme_Code.get(2))) {
    GlobalVariable.mapping = findTestData('Web Input Data/Scheme_Retailer_Master').getValue('Location', 2)

    GlobalVariable.location_name = WebUI.getText(findTestObject('Web Part/Scheme Request/Global Variable/mapping_td_tag'))

    KeywordUtil.logInfo(GlobalVariable.location_name)

    WebUI.takeScreenshot()

    GlobalVariable.mapping = findTestData('Web Input Data/Scheme_Retailer_Master').getValue('Distributor', 1)

    println(GlobalVariable.mapping)

    GlobalVariable.Distributor_Name = WebUI.getText(findTestObject('Web Part/Scheme Request/Global Variable/mapping_td_tag'))

    KeywordUtil.logInfo(GlobalVariable.Distributor_Name)

    WebUI.takeScreenshot()
}

//Location and Retailer:-
if (Scheme_Code.get(GlobalVariable.index).equals(Scheme_Code.get(4))) {
    GlobalVariable.mapping = findTestData('Web Input Data/Scheme_Retailer_Master').getValue('Location', 2)

    GlobalVariable.location_name = WebUI.getText(findTestObject('Web Part/Scheme Request/Global Variable/mapping_td_tag'))

    KeywordUtil.logInfo(GlobalVariable.location_name)

    WebUI.takeScreenshot()

    GlobalVariable.mapping = findTestData('Web Input Data/Scheme_Retailer_Master').getValue('Retailer', 1)

    println(GlobalVariable.mapping)

    GlobalVariable.retailer_name = WebUI.getText(findTestObject('Web Part/Scheme Request/Global Variable/mapping_td_tag'))

    KeywordUtil.logInfo(GlobalVariable.retailer_name)

    WebUI.takeScreenshot()
}

//Channel & Distributor
if (Scheme_Code.get(GlobalVariable.index).equals(Scheme_Code.get(6))) {
    GlobalVariable.mapping = findTestData('Web Input Data/Scheme_Retailer_Master').getValue('Channel', 4)

    println(GlobalVariable.mapping)

    GlobalVariable.Channel_name = WebUI.getText(findTestObject('Web Part/Scheme Request/Global Variable/mapping_td_tag'))

    KeywordUtil.logInfo(GlobalVariable.Channel_name)

    WebUI.takeScreenshot()

    GlobalVariable.mapping = findTestData('Web Input Data/Scheme_Retailer_Master').getValue('Distributor', 1)

    GlobalVariable.Distributor_Name = WebUI.getText(findTestObject('Web Part/Scheme Request/Global Variable/mapping_td_tag'))

    KeywordUtil.logInfo(GlobalVariable.Distributor_Name)

    WebUI.takeScreenshot()
}

//Channel & Retailer
if (Scheme_Code.get(GlobalVariable.index).equals(Scheme_Code.get(8))) {
    GlobalVariable.mapping = findTestData('Web Input Data/Scheme_Retailer_Master').getValue('Channel', 4)

    println(GlobalVariable.mapping)

    GlobalVariable.Channel_name = WebUI.getText(findTestObject('Web Part/Scheme Request/Global Variable/mapping_td_tag'))

    KeywordUtil.logInfo(GlobalVariable.Channel_name)

    WebUI.takeScreenshot()

    GlobalVariable.mapping = findTestData('Web Input Data/Scheme_Retailer_Master').getValue('Retailer', 1)

    WebUI.scrollToElement(findTestObject('Web Part/Scheme Request/Global Variable/mapping_td_tag'), 5)

    GlobalVariable.retailer_name = WebUI.getText(findTestObject('Web Part/Scheme Request/Global Variable/mapping_td_tag'))

    KeywordUtil.logInfo(GlobalVariable.retailer_name)

    WebUI.takeScreenshot()
}

//Distributor & Retailer
if (Scheme_Code.get(GlobalVariable.index).equals(Scheme_Code.get(10))) {
    GlobalVariable.mapping = findTestData('Web Input Data/Scheme_Retailer_Master').getValue('Retailer', 1)

    WebUI.scrollToElement(findTestObject('Web Part/Scheme Request/Global Variable/mapping_td_tag'), 5)

    GlobalVariable.retailer_name = WebUI.getText(findTestObject('Web Part/Scheme Request/Global Variable/mapping_td_tag'))

    KeywordUtil.logInfo(GlobalVariable.retailer_name)

    WebUI.takeScreenshot()

    GlobalVariable.mapping = findTestData('Web Input Data/Scheme_Retailer_Master').getValue('Distributor', 1)

    GlobalVariable.Distributor_Name = WebUI.getText(findTestObject('Web Part/Scheme Request/Global Variable/mapping_td_tag'))

    KeywordUtil.logInfo(GlobalVariable.Distributor_Name)

    WebUI.takeScreenshot()
}

//Channel
if (Scheme_Code.get(GlobalVariable.index).equals(Scheme_Code.get(12))) {
    GlobalVariable.mapping = findTestData('Web Input Data/Scheme_Retailer_Master').getValue('Channel', 4)

    println(GlobalVariable.mapping)

    GlobalVariable.Channel_name = WebUI.getText(findTestObject('Web Part/Scheme Request/Global Variable/mapping_td_tag'))

    KeywordUtil.logInfo(GlobalVariable.Channel_name)

    WebUI.takeScreenshot()
}

//Distributor
if (Scheme_Code.get(GlobalVariable.index).equals(Scheme_Code.get(14))) {
    GlobalVariable.mapping = findTestData('Web Input Data/Scheme_Retailer_Master').getValue('Distributor', 1)

    GlobalVariable.Distributor_Name = WebUI.getText(findTestObject('Web Part/Scheme Request/Global Variable/mapping_td_tag'))

    KeywordUtil.logInfo(GlobalVariable.Distributor_Name)

    WebUI.takeScreenshot()
}

//Retailer Attribute
if (Scheme_Code.get(GlobalVariable.index).equals(Scheme_Code.get(16))) {
    GlobalVariable.mapping = findTestData('Web Input Data/Scheme_Retailer_Master').getValue('Retailer_Attribute', 1)

    GlobalVariable.Retailer_Attribute = WebUI.getText(findTestObject('Web Part/Scheme Request/Global Variable/mapping_td_tag'))

    KeywordUtil.logInfo(GlobalVariable.Retailer_Attribute)

    WebUI.takeScreenshot()
}

//Location and retailer attribute
if (Scheme_Code.get(GlobalVariable.index).equals(Scheme_Code.get(18))) {
    GlobalVariable.mapping = findTestData('Web Input Data/Scheme_Retailer_Master').getValue('Location', 1)

    GlobalVariable.location_name = WebUI.getText(findTestObject('Web Part/Scheme Request/Global Variable/mapping_td_tag'))

    KeywordUtil.logInfo(GlobalVariable.location_name)

    WebUI.takeScreenshot()

    GlobalVariable.mapping = findTestData('Web Input Data/Scheme_Retailer_Master').getValue('Retailer_Attribute', 1)

    GlobalVariable.Retailer_Attribute = WebUI.getText(findTestObject('Web Part/Scheme Request/Global Variable/mapping_td_tag'))

    KeywordUtil.logInfo(GlobalVariable.Retailer_Attribute)

    WebUI.takeScreenshot()
}

//Channel and retailer attribute
if (Scheme_Code.get(GlobalVariable.index).equals(Scheme_Code.get(20))) {
    GlobalVariable.mapping = findTestData('Web Input Data/Scheme_Retailer_Master').getValue('Retailer_Attribute', 1)

    GlobalVariable.Retailer_Attribute = WebUI.getText(findTestObject('Web Part/Scheme Request/Global Variable/mapping_td_tag'))

    KeywordUtil.logInfo(GlobalVariable.Retailer_Attribute)

    WebUI.takeScreenshot()

    GlobalVariable.mapping = findTestData('Web Input Data/Scheme_Retailer_Master').getValue('Channel', 1)

    println(GlobalVariable.mapping)

    GlobalVariable.Channel_name = WebUI.getText(findTestObject('Web Part/Scheme Request/Global Variable/mapping_td_tag'))

    KeywordUtil.logInfo(GlobalVariable.Channel_name)

    WebUI.takeScreenshot()
}

//Distributor & retailer Attribute
if (Scheme_Code.get(GlobalVariable.index).equals(Scheme_Code.get(22))) {
    GlobalVariable.mapping = findTestData('Web Input Data/Scheme_Retailer_Master').getValue('Retailer_Attribute', 1)

    GlobalVariable.Retailer_Attribute = WebUI.getText(findTestObject('Web Part/Scheme Request/Global Variable/mapping_td_tag'))

    KeywordUtil.logInfo(GlobalVariable.Retailer_Attribute)

    WebUI.takeScreenshot()

    GlobalVariable.mapping = findTestData('Web Input Data/Scheme_Retailer_Master').getValue('Distributor', 1)

    GlobalVariable.Distributor_Name = WebUI.getText(findTestObject('Web Part/Scheme Request/Global Variable/mapping_td_tag'))

    KeywordUtil.logInfo(GlobalVariable.Distributor_Name)

    WebUI.takeScreenshot()
}

//Retailer & Retailer Attibute
if (Scheme_Code.get(GlobalVariable.index).equals(Scheme_Code.get(24))) {
    GlobalVariable.mapping = findTestData('Web Input Data/Scheme_Retailer_Master').getValue('Retailer_Attribute', 1)

    GlobalVariable.Retailer_Attribute = WebUI.getText(findTestObject('Web Part/Scheme Request/Global Variable/mapping_td_tag'))

    KeywordUtil.logInfo(GlobalVariable.Retailer_Attribute)

    WebUI.takeScreenshot()

    GlobalVariable.mapping = findTestData('Web Input Data/Scheme_Retailer_Master').getValue('Retailer', 2)

    WebUI.scrollToElement(findTestObject('Web Part/Scheme Request/Global Variable/mapping_td_tag'), 5)

    GlobalVariable.retailer_name = WebUI.getText(findTestObject('Web Part/Scheme Request/Global Variable/mapping_td_tag'))

    KeywordUtil.logInfo(GlobalVariable.retailer_name)

    WebUI.takeScreenshot()
}

//location,channel,retailer attribute
if (Scheme_Code.get(GlobalVariable.index).equals(Scheme_Code.get(26))) {
    GlobalVariable.mapping = findTestData('Web Input Data/Scheme_Retailer_Master').getValue('Location', 1)

    GlobalVariable.location_name = WebUI.getText(findTestObject('Web Part/Scheme Request/Global Variable/mapping_td_tag'))

    KeywordUtil.logInfo(GlobalVariable.location_name)

    WebUI.takeScreenshot()

    GlobalVariable.mapping = findTestData('Web Input Data/Scheme_Retailer_Master').getValue('Channel', 1)

    println(GlobalVariable.mapping)

    GlobalVariable.Channel_name = WebUI.getText(findTestObject('Web Part/Scheme Request/Global Variable/mapping_td_tag'))

    KeywordUtil.logInfo(GlobalVariable.Channel_name)

    WebUI.takeScreenshot()

    GlobalVariable.mapping = findTestData('Web Input Data/Scheme_Retailer_Master').getValue('Retailer_Attribute', 1)

    GlobalVariable.Retailer_Attribute = WebUI.getText(findTestObject('Web Part/Scheme Request/Global Variable/mapping_td_tag'))

    KeywordUtil.logInfo(GlobalVariable.Retailer_Attribute)

    WebUI.takeScreenshot()
}

//location,channel,retailer attribute,retailer
if (Scheme_Code.get(GlobalVariable.index).equals(Scheme_Code.get(28))) {
    GlobalVariable.mapping = findTestData('Web Input Data/Scheme_Retailer_Master').getValue('Location', 1)

    GlobalVariable.location_name = WebUI.getText(findTestObject('Web Part/Scheme Request/Global Variable/mapping_td_tag'))

    KeywordUtil.logInfo(GlobalVariable.location_name)

    WebUI.takeScreenshot()

    GlobalVariable.mapping = findTestData('Web Input Data/Scheme_Retailer_Master').getValue('Retailer_Attribute', 1)

    GlobalVariable.Retailer_Attribute = WebUI.getText(findTestObject('Web Part/Scheme Request/Global Variable/mapping_td_tag'))

    KeywordUtil.logInfo(GlobalVariable.Retailer_Attribute)

    WebUI.takeScreenshot()

    GlobalVariable.mapping = findTestData('Web Input Data/Scheme_Retailer_Master').getValue('Channel', 1)

    println(GlobalVariable.mapping)

    GlobalVariable.Channel_name = WebUI.getText(findTestObject('Web Part/Scheme Request/Global Variable/mapping_td_tag'))

    KeywordUtil.logInfo(GlobalVariable.Channel_name)

    WebUI.takeScreenshot()

    GlobalVariable.mapping = findTestData('Web Input Data/Scheme_Retailer_Master').getValue('Retailer', 1)

    WebUI.scrollToElement(findTestObject('Web Part/Scheme Request/Global Variable/mapping_td_tag'), 5)

    GlobalVariable.retailer_name = WebUI.getText(findTestObject('Web Part/Scheme Request/Global Variable/mapping_td_tag'))

    KeywordUtil.logInfo(GlobalVariable.retailer_name)

    WebUI.takeScreenshot()
}

//location,channel,retailer attribute,retailer,Distr
if (Scheme_Code.get(GlobalVariable.index).equals(Scheme_Code.get(30))) {
    GlobalVariable.mapping = findTestData('Web Input Data/Scheme_Retailer_Master').getValue('Location', 1)

    GlobalVariable.location_name = WebUI.getText(findTestObject('Web Part/Scheme Request/Global Variable/mapping_td_tag'))

    KeywordUtil.logInfo(GlobalVariable.location_name)

    WebUI.takeScreenshot()

    GlobalVariable.mapping = findTestData('Web Input Data/Scheme_Retailer_Master').getValue('Retailer_Attribute', 1)

    GlobalVariable.Retailer_Attribute = WebUI.getText(findTestObject('Web Part/Scheme Request/Global Variable/mapping_td_tag'))

    KeywordUtil.logInfo(GlobalVariable.Retailer_Attribute)

    WebUI.takeScreenshot()

    GlobalVariable.mapping = findTestData('Web Input Data/Scheme_Retailer_Master').getValue('Channel', 1)

    println(GlobalVariable.mapping)

    GlobalVariable.Channel_name = WebUI.getText(findTestObject('Web Part/Scheme Request/Global Variable/mapping_td_tag'))

    KeywordUtil.logInfo(GlobalVariable.Channel_name)

    WebUI.takeScreenshot()

    GlobalVariable.mapping = findTestData('Web Input Data/Scheme_Retailer_Master').getValue('Distributor', 1)

    GlobalVariable.Distributor_Name = WebUI.getText(findTestObject('Web Part/Scheme Request/Global Variable/mapping_td_tag'))

    KeywordUtil.logInfo(GlobalVariable.Distributor_Name)

    WebUI.takeScreenshot()

    GlobalVariable.mapping = findTestData('Web Input Data/Scheme_Retailer_Master').getValue('Retailer', 1)

    WebUI.scrollToElement(findTestObject('Web Part/Scheme Request/Global Variable/mapping_td_tag'), 5)

    GlobalVariable.retailer_name = WebUI.getText(findTestObject('Web Part/Scheme Request/Global Variable/mapping_td_tag'))

    KeywordUtil.logInfo(GlobalVariable.retailer_name)

    WebUI.takeScreenshot()
}

if (Scheme_Code.get(GlobalVariable.index).equals(Scheme_Code.get(32))) {
    //Location
    GlobalVariable.mapping = findTestData('Web Input Data/Scheme_Retailer_Master').getValue('Location', 2)

    GlobalVariable.location_name = WebUI.getText(findTestObject('Web Part/Scheme Request/Global Variable/mapping_td_tag'))

    KeywordUtil.logInfo(GlobalVariable.location_name)

    WebUI.takeScreenshot()
}

if (Scheme_Code.get(GlobalVariable.index).equals(Scheme_Code.get(34))) {
    //retailer
    GlobalVariable.mapping = findTestData('Web Input Data/Scheme_Retailer_Master').getValue('Retailer', 1)

    println(GlobalVariable.mapping)

    GlobalVariable.retailer_name = WebUI.getText(findTestObject('Web Part/Scheme Request/Global Variable/mapping_td_tag'))

    KeywordUtil.logInfo(GlobalVariable.retailer_name)

    WebUI.takeScreenshot()
}

