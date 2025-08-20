import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.keyword.excel.ExcelKeywords as ExcelKeywords
import internal.GlobalVariable as GlobalVariable
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import org.openqa.selenium.Keys as Keys

WebUI.callTestCase(findTestCase('Product_Mobile/Common/Web Login and Navigation/DivisionLogin'), [:], FailureHandling.STOP_ON_FAILURE)

Mobile.callTestCase(findTestCase('Product_Mobile/Scheme/Retailer Master/RM_CT_01'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.refresh()

Mobile.callTestCase(findTestCase('Product_Mobile/Scheme/Retailer Master/RM_CT_02'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.refresh()



String sheet_name = 'CriteriaMapping_Validation'

String file_name = 'Mobile Input data'

ArrayList<String> Retailer = CustomKeywords.'poi.Automation.GetAllDataRow_Flag.getAllDataRow'(file_name, sheet_name, 'Retailer')

ArrayList<String> Scheme_Names = CustomKeywords.'poi.Automation.GetAllDataRow_Flag.getAllDataRow'(file_name, sheet_name, 'Scheme_Names')

ArrayList<String> Location = CustomKeywords.'poi.Automation.GetAllDataRow_Flag.getAllDataRow'(file_name, sheet_name, 'Location')

for (a = 0; a < Scheme_Names.size(); a++) {
    WebUI.callTestCase(findTestCase('Product_Mobile/Common/Web Login and Navigation/Navigate to Scheme Master'), [:], FailureHandling.STOP_ON_FAILURE)

    WebUI.waitForElementNotVisible(findTestObject('Web Part/Re-usable/Page Load'), 100)

    //WebUI.click(findTestObject('Reusables/menu_hide_lever'), FailureHandling.STOP_ON_FAILURE)
    WebUI.waitForElementNotVisible(findTestObject('Web Part/Re-usable/Page Load'), 100)

    GlobalVariable.index = a

    s = Scheme_Names.get(a)

    //Using Split concept to remove the required word from the Scheme_Name:-
    String[] Scheme_code = s.split('[ _&]', 0)

    for (String w : Scheme_code) {
        System.out.println(w)

        GlobalVariable.label = w

        break
    }
    
    //Entering the Code in the Search field:-
    WebUI.waitForElementVisible(findTestObject('Web Part/Scheme Request/Click/click_code_search_field'), 50)

    WebUI.click(findTestObject('Web Part/Scheme Request/Click/click_code_search_field'))

    WebUI.sendKeys(findTestObject('Web Part/Scheme Request/Click/click_code_search_field'), GlobalVariable.label)

    //Selecting the Scheme from the grid:-
    GlobalVariable.Scheme_name = Scheme_Names.get(a)

    WebUI.waitForElementVisible(findTestObject('Web Part/Scheme Request/Global Variable/scheme_td_tag'), 50)

    WebUI.click(findTestObject('Web Part/Scheme Request/Global Variable/scheme_td_tag'), FailureHandling.STOP_ON_FAILURE)

    //Clicking the Edit grid label:-
    GlobalVariable.grid = findTestData('Web Input Data/Scheme_Retailer_Master').getValue('Grid_Labels', 1)

    WebUI.waitForElementVisible(findTestObject('Web Part/Scheme Request/Global Variable/grid_labels_a_tag'), 50)

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

    //Clicking the View icon in the Mapping set:-
    GlobalVariable.icons = findTestData('Web Input Data/Scheme_Retailer_Master').getValue('Icons', 1)

    WebUI.click(findTestObject('Web Part/Scheme Request/Global Variable/icons_i_tag'))

    WebUI.waitForElementNotVisible(findTestObject('Web Part/Re-usable/Page Load'), 100)

    //Capturing the Criteria Mapping data to a global variable:-
    if (Scheme_Names.get(a).equals(Scheme_Names.get(0))) {
        verificationData(1)

        //Location and Channel:-
        GlobalVariable.mapping = findTestData('Web Input Data/Scheme_Retailer_Master').getValue('Location', 2)

        scheme_Mapped_Location = WebUI.getText(findTestObject('Web Part/Scheme Request/Global Variable/mapping_td_tag'))

        KeywordUtil.logInfo(GlobalVariable.location_name)

        WebUI.takeScreenshot()

        GlobalVariable.mapping = findTestData('Web Input Data/Scheme_Retailer_Master').getValue('Channel', 4)

        println(GlobalVariable.mapping)

        scheme_mapped_channel = WebUI.getText(findTestObject('Web Part/Scheme Request/Global Variable/mapping_td_tag'))

        KeywordUtil.logInfo(GlobalVariable.Channel_name)

        WebUI.takeScreenshot()

        WebUI.verifyMatch(scheme_Mapped_Location.substring(0, 9), GlobalVariable.location_name, false)

        WebUI.verifyMatch(scheme_mapped_channel.substring(0, 13), GlobalVariable.Channel_name, false)
    }
    
    //Location and Distributor
    if (Scheme_Names.get(a).equals(Scheme_Names.get(1))) {
        verificationData(1)

        GlobalVariable.mapping = findTestData('Web Input Data/Scheme_Retailer_Master').getValue('Location', 2)

        scheme_Mapped_Location = WebUI.getText(findTestObject('Web Part/Scheme Request/Global Variable/mapping_td_tag'))

        KeywordUtil.logInfo(GlobalVariable.location_name)

        WebUI.takeScreenshot()

        GlobalVariable.mapping = findTestData('Web Input Data/Scheme_Retailer_Master').getValue('Distributor', 1)

        println(GlobalVariable.mapping)

        scheme_mapped_distributor = WebUI.getText(findTestObject('Web Part/Scheme Request/Global Variable/mapping_td_tag'))

        KeywordUtil.logInfo(GlobalVariable.Distributor_Name)

        WebUI.takeScreenshot()

        WebUI.verifyMatch(scheme_Mapped_Location.substring(0, 9), GlobalVariable.location_name, false)

        WebUI.verifyMatch(scheme_mapped_distributor.substring(0, 13), GlobalVariable.Distributor_Name, false)
    }
    
    //Location and Retailer:-
    if (Scheme_Names.get(a).equals(Scheme_Names.get(2))) {
        verificationData(1)

        GlobalVariable.mapping = findTestData('Web Input Data/Scheme_Retailer_Master').getValue('Location', 2)

        scheme_Mapped_Location = WebUI.getText(findTestObject('Web Part/Scheme Request/Global Variable/mapping_td_tag'))

        KeywordUtil.logInfo(GlobalVariable.location_name)

        WebUI.takeScreenshot()

        GlobalVariable.mapping = findTestData('Web Input Data/Scheme_Retailer_Master').getValue('Retailer', 1)

        println(GlobalVariable.mapping)

        scheme_mapped_retailer = WebUI.getText(findTestObject('Web Part/Scheme Request/Global Variable/mapping_td_tag'))

        KeywordUtil.logInfo(GlobalVariable.retailer_name)

        WebUI.takeScreenshot()

        WebUI.verifyMatch(scheme_Mapped_Location.substring(0, 9), GlobalVariable.location_name, false)

        WebUI.verifyMatch(scheme_mapped_retailer.substring(0, 9), GlobalVariable.retailer_name, false)
    }
    
    //Channel & Distributor
    if (Scheme_Names.get(a).equals(Scheme_Names.get(3))) {
        verificationData(1)

        GlobalVariable.mapping = findTestData('Web Input Data/Scheme_Retailer_Master').getValue('Channel', 4)

        println(GlobalVariable.mapping)

        scheme_mapped_channel = WebUI.getText(findTestObject('Web Part/Scheme Request/Global Variable/mapping_td_tag'))

        KeywordUtil.logInfo(GlobalVariable.Channel_name)

        WebUI.takeScreenshot()

        GlobalVariable.mapping = findTestData('Web Input Data/Scheme_Retailer_Master').getValue('Distributor', 1)

        scheme_mapped_distributor = WebUI.getText(findTestObject('Web Part/Scheme Request/Global Variable/mapping_td_tag'))

        KeywordUtil.logInfo(GlobalVariable.Distributor_Name)

        WebUI.takeScreenshot()

        WebUI.verifyMatch(scheme_mapped_channel.substring(0, 13), GlobalVariable.Channel_name, false)

        WebUI.verifyMatch(scheme_mapped_distributor.substring(0, 13), GlobalVariable.Distributor_Name, false)
    }
    
    //Channel & Retailer
    if (Scheme_Names.get(a).equals(Scheme_Names.get(4))) {
        verificationData(1)

        GlobalVariable.mapping = findTestData('Web Input Data/Scheme_Retailer_Master').getValue('Channel', 4)

        println(GlobalVariable.mapping)

        scheme_mapped_channel = WebUI.getText(findTestObject('Web Part/Scheme Request/Global Variable/mapping_td_tag'))

        KeywordUtil.logInfo(GlobalVariable.Channel_name)

        WebUI.takeScreenshot()

        GlobalVariable.mapping = findTestData('Web Input Data/Scheme_Retailer_Master').getValue('Retailer', 1)

        WebUI.scrollToElement(findTestObject('Web Part/Scheme Request/Global Variable/mapping_td_tag'), 5)

        scheme_mapped_retailer = WebUI.getText(findTestObject('Web Part/Scheme Request/Global Variable/mapping_td_tag'))

        KeywordUtil.logInfo(GlobalVariable.retailer_name)

        WebUI.takeScreenshot()

        WebUI.verifyMatch(scheme_mapped_channel.substring(0, 13), GlobalVariable.Channel_name, false)

        WebUI.verifyMatch(scheme_mapped_retailer.substring(0, 9), GlobalVariable.retailer_name, false)
    }
    
    //Distributor & Retailer
    if (Scheme_Names.get(a).equals(Scheme_Names.get(5))) {
        verificationData(1)

        GlobalVariable.mapping = findTestData('Web Input Data/Scheme_Retailer_Master').getValue('Retailer', 1)

        WebUI.scrollToElement(findTestObject('Web Part/Scheme Request/Global Variable/mapping_td_tag'), 5)

        scheme_mapped_retailer = WebUI.getText(findTestObject('Web Part/Scheme Request/Global Variable/mapping_td_tag'))

        KeywordUtil.logInfo(GlobalVariable.retailer_name)

        WebUI.takeScreenshot()

        GlobalVariable.mapping = findTestData('Web Input Data/Scheme_Retailer_Master').getValue('Distributor', 1)

        scheme_mapped_distributor = WebUI.getText(findTestObject('Web Part/Scheme Request/Global Variable/mapping_td_tag'))

        KeywordUtil.logInfo(GlobalVariable.Distributor_Name)

        WebUI.takeScreenshot()

        WebUI.verifyMatch(scheme_mapped_distributor.substring(0, 13), GlobalVariable.Distributor_Name, false)

        WebUI.verifyMatch(scheme_mapped_retailer.substring(0, 9), GlobalVariable.retailer_name, false)

        verificationData(2)

        WebUI.verifyMatch(scheme_mapped_distributor.substring(0, 13), GlobalVariable.Distributor_Name, false)
    }
    
    //Channel
    if (Scheme_Names.get(a).equals(Scheme_Names.get(6))) {
        verificationData(1)

        GlobalVariable.mapping = findTestData('Web Input Data/Scheme_Retailer_Master').getValue('Channel', 4)

        println(GlobalVariable.mapping)

        scheme_mapped_channel = WebUI.getText(findTestObject('Web Part/Scheme Request/Global Variable/mapping_td_tag'))

        KeywordUtil.logInfo(GlobalVariable.Channel_name)

        WebUI.takeScreenshot()

        WebUI.verifyMatch(scheme_mapped_channel.substring(0, 13), GlobalVariable.Channel_name, false)
    }
    
    //Distributor
    if (Scheme_Names.get(a).equals(Scheme_Names.get(7))) {
        verificationData(1)

        GlobalVariable.mapping = findTestData('Web Input Data/Scheme_Retailer_Master').getValue('Distributor', 1)

        scheme_mapped_distributor = WebUI.getText(findTestObject('Web Part/Scheme Request/Global Variable/mapping_td_tag'))

        KeywordUtil.logInfo(GlobalVariable.Distributor_Name)

        WebUI.takeScreenshot()

        WebUI.verifyMatch(scheme_mapped_distributor.substring(0, 13), GlobalVariable.Distributor_Name, false)

        verificationData(2)

        WebUI.verifyMatch(scheme_mapped_distributor.substring(0, 13), GlobalVariable.Distributor_Name, false)
    }
    
    //Retailer Attribute
    if (Scheme_Names.get(a).equals(Scheme_Names.get(8))) {
        verificationData(2)

        GlobalVariable.mapping = findTestData('Web Input Data/Scheme_Retailer_Master').getValue('Retailer_Attribute', 1)

        scheme_Mapped_Retailer_Attribute = WebUI.getText(findTestObject('Web Part/Scheme Request/Global Variable/mapping_td_tag'))

        KeywordUtil.logInfo(GlobalVariable.Retailer_Attribute)

        WebUI.takeScreenshot()

        WebUI.verifyMatch(scheme_Mapped_Retailer_Attribute.substring(0, 9), GlobalVariable.Retailer_Attribute, false)
    }
    
    //Location and retailer attribute
    if (Scheme_Names.get(a).equals(Scheme_Names.get(9))) {
        verificationData(2)

        GlobalVariable.mapping = findTestData('Web Input Data/Scheme_Retailer_Master').getValue('Location', 1)

        scheme_Mapped_Location = WebUI.getText(findTestObject('Web Part/Scheme Request/Global Variable/mapping_td_tag'))

        KeywordUtil.logInfo(GlobalVariable.location_name)

        WebUI.takeScreenshot()

        GlobalVariable.mapping = findTestData('Web Input Data/Scheme_Retailer_Master').getValue('Retailer_Attribute', 1)

        scheme_mapped_retailer_attribute = WebUI.getText(findTestObject('Web Part/Scheme Request/Global Variable/mapping_td_tag'))

        KeywordUtil.logInfo(GlobalVariable.Retailer_Attribute)

        WebUI.takeScreenshot()

        WebUI.verifyMatch(scheme_Mapped_Location.substring(0, 6), GlobalVariable.location_name, false)

        WebUI.verifyMatch(scheme_mapped_retailer_attribute.substring(0, 9), GlobalVariable.Retailer_Attribute, false)
    }
    
    //Channel and retailer attribute
    if (Scheme_Names.get(a).equals(Scheme_Names.get(10))) {
        verificationData(2)

        GlobalVariable.mapping = findTestData('Web Input Data/Scheme_Retailer_Master').getValue('Retailer_Attribute', 1)

        scheme_mapped_retailer_attribute = WebUI.getText(findTestObject('Web Part/Scheme Request/Global Variable/mapping_td_tag'))

        KeywordUtil.logInfo(GlobalVariable.Retailer_Attribute)

        WebUI.takeScreenshot()

        GlobalVariable.mapping = findTestData('Web Input Data/Scheme_Retailer_Master').getValue('Channel', 1)

        println(GlobalVariable.mapping)

        scheme_mapped_channel = WebUI.getText(findTestObject('Web Part/Scheme Request/Global Variable/mapping_td_tag'))

        KeywordUtil.logInfo(GlobalVariable.Channel_name)

        WebUI.takeScreenshot()

        WebUI.verifyMatch(scheme_mapped_retailer_attribute.substring(0, 9), GlobalVariable.Retailer_Attribute, false)

        WebUI.verifyMatch(scheme_mapped_channel.substring(0, 12), GlobalVariable.Channel_name, false)
    }
    
    //Distributor & retailer Attribute
    if (Scheme_Names.get(a).equals(Scheme_Names.get(11))) {
        verificationData(2)

        GlobalVariable.mapping = findTestData('Web Input Data/Scheme_Retailer_Master').getValue('Retailer_Attribute', 1)

        scheme_Mapped_Retailer_Attribute = WebUI.getText(findTestObject('Web Part/Scheme Request/Global Variable/mapping_td_tag'))

        KeywordUtil.logInfo(GlobalVariable.Retailer_Attribute)

        WebUI.takeScreenshot()

        GlobalVariable.mapping = findTestData('Web Input Data/Scheme_Retailer_Master').getValue('Distributor', 1)

        scheme_mapped_distributor = WebUI.getText(findTestObject('Web Part/Scheme Request/Global Variable/mapping_td_tag'))

        KeywordUtil.logInfo(GlobalVariable.Distributor_Name)

        WebUI.takeScreenshot()

        WebUI.verifyMatch(scheme_Mapped_Retailer_Attribute.substring(0, 9), GlobalVariable.Retailer_Attribute, false)

        WebUI.verifyMatch(scheme_mapped_distributor.substring(0, 13), GlobalVariable.Distributor_Name, false)
    }
    
    //Retailer & Retailer Attibute
    if (Scheme_Names.get(a).equals(Scheme_Names.get(12))) {
        verificationData(2)

        GlobalVariable.mapping = findTestData('Web Input Data/Scheme_Retailer_Master').getValue('Retailer_Attribute', 1)

        scheme_Mapped_Retailer_Attribute = WebUI.getText(findTestObject('Web Part/Scheme Request/Global Variable/mapping_td_tag'))

        KeywordUtil.logInfo(GlobalVariable.Retailer_Attribute)

        WebUI.takeScreenshot()

        GlobalVariable.mapping = findTestData('Web Input Data/Scheme_Retailer_Master').getValue('Retailer', 2)

        WebUI.scrollToElement(findTestObject('Web Part/Scheme Request/Global Variable/mapping_td_tag'), 5)

        scheme_Retailer_mapping = WebUI.getText(findTestObject('Web Part/Scheme Request/Global Variable/mapping_td_tag'))

        KeywordUtil.logInfo(GlobalVariable.retailer_name)

        WebUI.takeScreenshot()

        WebUI.verifyMatch(scheme_Retailer_mapping.substring(0, 17), GlobalVariable.retailer_name, false)

        WebUI.verifyMatch(scheme_Mapped_Retailer_Attribute.substring(0, 9), GlobalVariable.Retailer_Attribute, false)
    }
    
    //location,channel,retailer attribute
    if (Scheme_Names.get(a).equals(Scheme_Names.get(13))) {
        verificationData(2)

        GlobalVariable.mapping = findTestData('Web Input Data/Scheme_Retailer_Master').getValue('Location', 1)

        scheme_Mapped_Location = WebUI.getText(findTestObject('Web Part/Scheme Request/Global Variable/mapping_td_tag'))

        KeywordUtil.logInfo(GlobalVariable.location_name)

        WebUI.takeScreenshot()

        GlobalVariable.mapping = findTestData('Web Input Data/Scheme_Retailer_Master').getValue('Channel', 1)

        println(GlobalVariable.mapping)

        scheme_mapped_channel = WebUI.getText(findTestObject('Web Part/Scheme Request/Global Variable/mapping_td_tag'))

        KeywordUtil.logInfo(GlobalVariable.Channel_name)

        WebUI.takeScreenshot()

        GlobalVariable.mapping = findTestData('Web Input Data/Scheme_Retailer_Master').getValue('Retailer_Attribute', 1)

        scheme_mapped_retailer_attribute = WebUI.getText(findTestObject('Web Part/Scheme Request/Global Variable/mapping_td_tag'))

        KeywordUtil.logInfo(GlobalVariable.Retailer_Attribute)

        WebUI.takeScreenshot()

        WebUI.verifyMatch(scheme_Mapped_Location.substring(0, 6), GlobalVariable.location_name, false)

        WebUI.verifyMatch(scheme_mapped_retailer_attribute.substring(0, 9), GlobalVariable.Retailer_Attribute, false)

        WebUI.verifyMatch(scheme_mapped_channel.substring(0, 12), GlobalVariable.Channel_name, false)
    }
    
    //location,channel,retailer attribute,retailer
    if (Scheme_Names.get(a).equals(Scheme_Names.get(14))) {
        verificationData(2)

        GlobalVariable.label = findTestData('Web Input Data/Scheme_Retailer_Master').getValue('Mapping_criteria', 1)

        GlobalVariable.icons = findTestData('Web Input Data/Scheme_Retailer_Master').getValue('Icons', 1)

        WebUI.delay(2)

        //WebUI.click(findTestObject('Object Repository/Web Part/Scheme Request/Global Variable/criteria_maping_view'))
        WebUI.waitForElementNotVisible(findTestObject('Web Part/Re-usable/Page Load'), 100)

        //Capturing the Criteria Mapping data to a global variable:-
        //location,channel,retailer attribute,retailer,Distr
        GlobalVariable.mapping = findTestData('Web Input Data/Scheme_Retailer_Master').getValue('Location', 1)

        scheme_Mapped_Location = WebUI.getText(findTestObject('Web Part/Scheme Request/Global Variable/mapping_td_tag'))

        KeywordUtil.logInfo(scheme_Mapped_Location)

        WebUI.takeScreenshot()

        GlobalVariable.mapping = findTestData('Web Input Data/Scheme_Retailer_Master').getValue('Retailer_Attribute', 1)

        scheme_mapped_retailer_attribute = WebUI.getText(findTestObject('Web Part/Scheme Request/Global Variable/mapping_td_tag'))

        KeywordUtil.logInfo(scheme_mapped_retailer_attribute)

        WebUI.takeScreenshot()

        GlobalVariable.mapping = findTestData('Web Input Data/Scheme_Retailer_Master').getValue('Channel', 1)

        println(GlobalVariable.mapping)

        scheme_mapped_channel = WebUI.getText(findTestObject('Web Part/Scheme Request/Global Variable/mapping_td_tag'))

        KeywordUtil.logInfo(scheme_mapped_channel)

        WebUI.takeScreenshot()

        WebUI.click(findTestObject('Object Repository/Web Part/Scheme Request/Click/criteria_mapping_close'))

        GlobalVariable.label = findTestData('Web Input Data/Scheme_Retailer_Master').getValue('Mapping_criteria', 2)

        GlobalVariable.icons = findTestData('Web Input Data/Scheme_Retailer_Master').getValue('Icons', 1)

        WebUI.waitForElementVisible(findTestObject('Object Repository/Web Part/Scheme Request/Global Variable/criteria_maping_view'), 50)

        WebUI.click(findTestObject('Web Part/Scheme Request/Global Variable/criteria_maping_view'))

        WebUI.waitForElementNotVisible(findTestObject('Web Part/Re-usable/Page Load'), 70)

        GlobalVariable.mapping = findTestData('Web Input Data/Scheme_Retailer_Master').getValue('Retailer', 1)

        WebUI.scrollToElement(findTestObject('Web Part/Scheme Request/Global Variable/mapping_td_tag'), 5)

        scheme_Retailer_mapping = WebUI.getText(findTestObject('Web Part/Scheme Request/Global Variable/mapping_td_tag'))

        KeywordUtil.logInfo(GlobalVariable.retailer_name)

        WebUI.takeScreenshot()

        WebUI.verifyMatch(scheme_Mapped_Location.substring(0, 6), GlobalVariable.location_name, false)

        WebUI.verifyMatch(scheme_mapped_channel.substring(0, 12), GlobalVariable.Channel_name, false)

        WebUI.verifyMatch(scheme_mapped_retailer_attribute.substring(0, 9), GlobalVariable.Retailer_Attribute, false)

        verificationData(1)

        WebUI.verifyMatch(scheme_Retailer_mapping.substring(0, 9), GlobalVariable.retailer_name, false)
    }
    
    //location,channel,retailer attribute,retailer,Distr
    if (Scheme_Names.get(a).equals(Scheme_Names.get(15))) {
        verificationData(2)

        //Clicking the View icon in the Mapping set:-
        GlobalVariable.label = findTestData('Web Input Data/Scheme_Retailer_Master').getValue('Mapping_criteria', 2)

        GlobalVariable.icons = findTestData('Web Input Data/Scheme_Retailer_Master').getValue('Icons', 1)

        //WebUI.click(findTestObject('Object Repository/Web Part/Scheme Request/Global Variable/criteria_maping_view'))
        WebUI.waitForElementNotVisible(findTestObject('Web Part/Re-usable/Page Load'), 100)

        //Capturing the Criteria Mapping data to a global variable:-
        //location,channel,retailer attribute,retailer,Distr
        GlobalVariable.mapping = findTestData('Web Input Data/Scheme_Retailer_Master').getValue('Location', 1)

        scheme_Mapped_Location = WebUI.getText(findTestObject('Web Part/Scheme Request/Global Variable/mapping_td_tag'))

        KeywordUtil.logInfo(scheme_Mapped_Location)

        WebUI.takeScreenshot()

        GlobalVariable.mapping = findTestData('Web Input Data/Scheme_Retailer_Master').getValue('Retailer_Attribute', 1)

        scheme_mapped_attribute = WebUI.getText(findTestObject('Web Part/Scheme Request/Global Variable/mapping_td_tag'))

        KeywordUtil.logInfo(scheme_mapped_attribute)

        WebUI.takeScreenshot()

        GlobalVariable.mapping = findTestData('Web Input Data/Scheme_Retailer_Master').getValue('Channel', 1)

        println(GlobalVariable.mapping)

        scheme_mapped_channel = WebUI.getText(findTestObject('Web Part/Scheme Request/Global Variable/mapping_td_tag'))

        KeywordUtil.logInfo(scheme_mapped_channel)

        WebUI.takeScreenshot()

        GlobalVariable.mapping = findTestData('Web Input Data/Scheme_Retailer_Master').getValue('Distributor', 1)

        scheme_mapped_distributor = WebUI.getText(findTestObject('Web Part/Scheme Request/Global Variable/mapping_td_tag'))

        KeywordUtil.logInfo(scheme_mapped_distributor)

        WebUI.takeScreenshot()

        WebUI.click(findTestObject('Object Repository/Web Part/Scheme Request/Click/criteria_mapping_close'))

        GlobalVariable.label = findTestData('Web Input Data/Scheme_Retailer_Master').getValue('Mapping_criteria', 3)

        GlobalVariable.icons = findTestData('Web Input Data/Scheme_Retailer_Master').getValue('Icons', 1)

        WebUI.waitForElementVisible(findTestObject('Object Repository/Web Part/Scheme Request/Global Variable/criteria_maping_view'), 50)

        WebUI.click(findTestObject('Object Repository/Web Part/Scheme Request/Global Variable/criteria_maping_view'))

        WebUI.waitForElementNotVisible(findTestObject('Web Part/Re-usable/Page Load'), 70)

        GlobalVariable.mapping = findTestData('Web Input Data/Scheme_Retailer_Master').getValue('Retailer', 1)

        WebUI.scrollToElement(findTestObject('Web Part/Scheme Request/Global Variable/mapping_td_tag'), 5)

        scheme_mapped_retailer = WebUI.getText(findTestObject('Web Part/Scheme Request/Global Variable/mapping_td_tag'))

        KeywordUtil.logInfo(scheme_mapped_retailer)

        WebUI.takeScreenshot()

        WebUI.verifyMatch(scheme_Mapped_Location.substring(0, 6), GlobalVariable.location_name, false)

        WebUI.verifyMatch(scheme_mapped_channel.substring(0, 12), GlobalVariable.Channel_name, false)

        WebUI.verifyMatch(scheme_mapped_distributor.substring(0, 13), GlobalVariable.Distributor_Name, false)

        WebUI.verifyMatch(scheme_mapped_attribute.substring(0, 9), GlobalVariable.Retailer_Attribute, false)

        verificationData(1)

        WebUI.verifyMatch(scheme_mapped_retailer.substring(0, 9), GlobalVariable.retailer_name, false)
    }
    
    if (Scheme_Names.get(a).equals(Scheme_Names.get(16))) {
        verificationData(1)

        //Location
        GlobalVariable.mapping = findTestData('Web Input Data/Scheme_Retailer_Master').getValue('Location', 2)

        scheme_Mapped_Location = WebUI.getText(findTestObject('Web Part/Scheme Request/Global Variable/mapping_td_tag'))

        KeywordUtil.logInfo(GlobalVariable.location_name)

        WebUI.takeScreenshot()

        WebUI.verifyMatch(scheme_Mapped_Location.substring(0, 9), GlobalVariable.location_name, false)
    }
    
    if (Scheme_Names.get(a).equals(Scheme_Names.get(17))) {
        verificationData(1)

        //retailer
        GlobalVariable.mapping = findTestData('Web Input Data/Scheme_Retailer_Master').getValue('Retailer', 1)

        println(GlobalVariable.mapping)

        scheme_mapped_retailer = WebUI.getText(findTestObject('Web Part/Scheme Request/Global Variable/mapping_td_tag'))

        KeywordUtil.logInfo(GlobalVariable.retailer_name)

        WebUI.takeScreenshot()

        WebUI.verifyMatch(scheme_mapped_retailer.substring(0, 9), GlobalVariable.retailer_name, false)
    }
    
    WebUI.refresh()
}

WebUI.closeBrowser()

def verificationData(int retailerDataRowNumber) {
    GlobalVariable.location_name = findTestData('Data Files/Mobile Input Data/Scheme_CriteriaMapping').getValue('Location_name_mapping', retailerDataRowNumber)

    GlobalVariable.Channel_name = findTestData('Data Files/Mobile Input Data/Scheme_CriteriaMapping').getValue('Channel_name_mapping', retailerDataRowNumber)

    GlobalVariable.Distributor_Name = findTestData('Data Files/Mobile Input Data/Scheme_CriteriaMapping').getValue('Distributor_name_mapping', retailerDataRowNumber)

    GlobalVariable.retailer_name = findTestData('Data Files/Mobile Input Data/Scheme_CriteriaMapping').getValue('Retailer_mapping', retailerDataRowNumber)

    GlobalVariable.Retailer_Attribute = findTestData('Data Files/Mobile Input Data/Scheme_CriteriaMapping').getValue('Retailer_attribute_mapping', retailerDataRowNumber)
}

