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

String sheet_name = 'Scheme_CriteriaMapping'

String file_name = 'Mobile Input data'

String s = GlobalVariable.Scheme_name

ArrayList<String> Scheme_Code = CustomKeywords.'poi.Automation.GetAllDataRow_Flag.getAllDataRow'(file_name, sheet_name, 
    'Scheme_Code')

ArrayList<String> Scheme_Name = CustomKeywords.'poi.Automation.GetAllDataRow_Flag.getAllDataRow'(file_name, sheet_name, 
    'SchemeName')

//WebUI.callTestCase(findTestCase('Reusables/Login/Division Login'), [:], FailureHandling.STOP_ON_FAILURE)
//
//WebUI.callTestCase(findTestCase('Reusables/Navigation/Navigate to Scheme Request'), [:], FailureHandling.STOP_ON_FAILURE)
WebUI.callTestCase(findTestCase('Product_Mobile/Common/Web Login and Navigation/Navigate to Scheme Master'), [:], FailureHandling.STOP_ON_FAILURE)

not_run: WebUI.waitForElementNotVisible(findTestObject('Web Part/Re-usable/Page Load'), 100)

not_run: WebUI.click(findTestObject('Product/Web Part/Reusables/menu_hide_lever'), FailureHandling.STOP_ON_FAILURE)

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

//Clicking the Next Button in the Promotion Group page:-
WebUI.waitForElementVisible(findTestObject('Web Part/Scheme Request/Click/click_next_button_index_2'), 10)

WebUI.click(findTestObject('Web Part/Scheme Request/Click/click_next_button_index_2'))

WebUI.waitForElementNotVisible(findTestObject('Web Part/Re-usable/Page Load'), 100)

//Clicking the Next button in the Promotion Slab page:-
WebUI.waitForElementVisible(findTestObject('Web Part/Scheme Request/Click/click_next_button_index_4'), 10)

WebUI.click(findTestObject('Web Part/Scheme Request/Click/click_next_button_index_4'))

if (Scheme_Code.get(GlobalVariable.index).equals(Scheme_Code.get(28))) {
    //Clicking the View icon in the Mapping set:-
    GlobalVariable.label = findTestData('Web Input Data/Scheme_Retailer_Master').getValue('Mapping_criteria', 1)

    GlobalVariable.icons = findTestData('Web Input Data/Scheme_Retailer_Master').getValue('Icons', 1)

    WebUI.click(findTestObject('Web Part/Scheme Request/Global Variable/criteria_maping_view'))

    WebUI.waitForElementNotVisible(findTestObject('Web Part/Re-usable/Page Load'), 100)

    //Capturing the Criteria Mapping data to a global variable:-
    //location,channel,retailer attribute,retailer,Distr
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

    WebUI.click(findTestObject('Web Part/Scheme Request/Click/criteria_mapping_close'))

    GlobalVariable.label = findTestData('Web Input Data/Scheme_Retailer_Master').getValue('Mapping_criteria', 2)

    GlobalVariable.icons = findTestData('Web Input Data/Scheme_Retailer_Master').getValue('Icons', 1)

    WebUI.waitForElementVisible(findTestObject('Web Part/Scheme Request/Global Variable/criteria_maping_view'), 
        50)

    WebUI.click(findTestObject('Web Part/Scheme Request/Global Variable/criteria_maping_view'))

    WebUI.waitForElementNotVisible(findTestObject('Web Part/Re-usable/Page Load'), 70)

    GlobalVariable.mapping = findTestData('Web Input Data/Scheme_Retailer_Master').getValue('Retailer', 1)

    WebUI.scrollToElement(findTestObject('Web Part/Scheme Request/Global Variable/mapping_td_tag'), 5)

    GlobalVariable.retailer_name = WebUI.getText(findTestObject('Web Part/Scheme Request/Global Variable/mapping_td_tag'))

    KeywordUtil.logInfo(GlobalVariable.retailer_name)

    WebUI.takeScreenshot()
}

