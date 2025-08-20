import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
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
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Web Login and Navigation/DivisionLogin_Cloud'), [:], FailureHandling.STOP_ON_FAILURE)

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Web Login and Navigation/Navigate to Tax Group Creation'), [:], FailureHandling.STOP_ON_FAILURE)

String sheet_name = 'Tax'

String file_name = 'Web Input Data'

ArrayList<String> Tax_Description = CustomKeywords.'poi.Automation.GetAllWebDataRow_Flag.getAllDataRow'(file_name, sheet_name, 'Tax_Description')

ArrayList<String> Tax_Type = CustomKeywords.'poi.Automation.GetAllWebDataRow_Flag.getAllDataRow'(file_name, sheet_name, 'Tax_Type')

ArrayList<String> Tax_Rate = CustomKeywords.'poi.Automation.GetAllWebDataRow_Flag.getAllDataRow'(file_name, sheet_name, 'Tax_Rate')

ArrayList<String> LocationLevel = CustomKeywords.'poi.Automation.GetAllWebDataRow_Flag.getAllDataRow'(file_name, sheet_name, 'LocationLevel')

ArrayList<String> Location = CustomKeywords.'poi.Automation.GetAllWebDataRow_Flag.getAllDataRow'(file_name, sheet_name, 'Location')

ArrayList<String> DestinationLocation = CustomKeywords.'poi.Automation.GetAllWebDataRow_Flag.getAllDataRow'(file_name, sheet_name, 'DestinationLocation')

ArrayList<String> TaxApplyType = CustomKeywords.'poi.Automation.GetAllWebDataRow_Flag.getAllDataRow'(file_name, sheet_name, 'TaxApplyType')

ArrayList<String> ProductName = CustomKeywords.'poi.Automation.GetAllWebDataRow_Flag.getAllDataRow'(file_name, sheet_name, 'ProductName')

ArrayList<String> HSN_Code = CustomKeywords.'poi.Automation.GetAllWebDataRow_Flag.getAllDataRow'(file_name, sheet_name, 'HSN_Code')

for (int i = 0; i < Tax_Description.size(); i++) {
    if (Tax_Description.get(i) != 'NULL') {
        WebUI.click(findTestObject('Object Repository/Web Part/TaxCreate/Tax_Desc_FilterField'))

        GlobalVariable.label = Tax_Description.get(i)

        println(GlobalVariable.label)

        WebUI.setText(findTestObject('Object Repository/Web Part/TaxCreate/Tax_Desc_FilterField'), GlobalVariable.label)

        WebUI.delay(1)

        if (WebUI.verifyElementPresent(findTestObject('Web Part/FocusBrand(MSL)/td_Tag(GridCode)'), 2, FailureHandling.OPTIONAL)) {
            WebUI.click(findTestObject('Web Part/FocusBrand(MSL)/td_Tag(GridCode)'))

            WebUI.click(findTestObject('Web Part/FocusBrand(MSL)/Edit_Btn'))

            WebUI.waitForElementNotVisible(findTestObject('Web Part/Re-usable/Page Load'), 100)

            WebUI.click(findTestObject('Web Part/TaxCreate/Tax_Type_Filter_Field'))

            for (b = 0; b < Tax_Type.size(); b++) {
                GlobalVariable.label = Tax_Type.get(b)

                WebUI.setText(findTestObject('Web Part/TaxCreate/Tax_Type_Filter_Field'), GlobalVariable.label)

                WebUI.delay(1)

                checkbox_type = WebUI.getAttribute(findTestObject('Object Repository/Web Part/TaxCreate/Tax_Type-checkbox(Global)'), 'src', FailureHandling.STOP_ON_FAILURE)

                unchecked_checkbox = findTestData('Web Input Data/Tax').getValue('Verify', 1)

                if (checkbox_type == unchecked_checkbox) {
                    WebUI.click(findTestObject('Object Repository/Web Part/TaxCreate/Tax_Type-checkbox(Global)'))
                }
                
                WebUI.click(findTestObject('Object Repository/Web Part/TaxCreate/Tax_Rate_Field'))

                WebUI.clearText(findTestObject('Object Repository/Web Part/TaxCreate/Tax_Rate(global-input tag)'), FailureHandling.OPTIONAL)

                WebUI.setText(findTestObject('Object Repository/Web Part/TaxCreate/Tax_Rate(global-input tag)'), Tax_Rate.get(i))
            }
            
            WebUI.click(findTestObject('Object Repository/Web Part/Discount Item/Save-btn'))

            WebUI.takeScreenshot()

            Alert = WebUI.getText(findTestObject('Object Repository/Web Part/Discount Item/Green_Card_Alert'), FailureHandling.STOP_ON_FAILURE)

            WebUI.verifyMatch(Alert, findTestData('Web Input Data/Tax').getValue('Alerts', 1), false, FailureHandling.STOP_ON_FAILURE)

            WebUI.takeScreenshot()
        } else {
            WebUI.click(findTestObject('Web Part/FocusBrand(MSL)/Add_Btn'))

            WebUI.waitForElementNotVisible(findTestObject('Web Part/Re-usable/Page Load'), 100)

            GlobalVariable.label = Tax_Description.get(i)

            WebUI.setText(findTestObject('Object Repository/Web Part/TaxCreate/Group_Desc'), GlobalVariable.label)

            WebUI.click(findTestObject('Object Repository/Web Part/TaxCreate/Eff From Date'))

            WebUI.callTestCase(findTestCase('Product_Mobile/Common/DatePicker_and _Navigation/Date Picker - Current'), [:], FailureHandling.STOP_ON_FAILURE)

            WebUI.waitForElementNotVisible(findTestObject('Web Part/Re-usable/Page Load'), 100)

            WebUI.click(findTestObject('Web Part/TaxCreate/Eff To Date'))

            WebUI.callTestCase(findTestCase('Product_Mobile/Common/DatePicker_and _Navigation/Date Picker - Future(1year)'), [:], FailureHandling.STOP_ON_FAILURE)

            WebUI.waitForElementNotVisible(findTestObject('Web Part/Re-usable/Page Load'), 100)

            WebUI.click(findTestObject('Web Part/TaxCreate/Tax_Type_Filter_Field'))

            for (b = 0; b < Tax_Type.size(); b++) {
                GlobalVariable.label = Tax_Type.get(b)

                WebUI.setText(findTestObject('Web Part/TaxCreate/Tax_Type_Filter_Field'), GlobalVariable.label)

                WebUI.delay(1)

                checkbox_type = WebUI.getAttribute(findTestObject('Object Repository/Web Part/TaxCreate/Tax_Type-checkbox(Global)'), 'src', FailureHandling.STOP_ON_FAILURE)

                unchecked_checkbox = findTestData('Web Input Data/Tax').getValue('Verify', 1)

                if (checkbox_type == unchecked_checkbox) {
                    WebUI.click(findTestObject('Object Repository/Web Part/TaxCreate/Tax_Type-checkbox(Global)'))
                }
                
                WebUI.click(findTestObject('Object Repository/Web Part/TaxCreate/Tax_Rate_Field'))

                WebUI.clearText(findTestObject('Object Repository/Web Part/TaxCreate/Tax_Rate(global-input tag)'), FailureHandling.OPTIONAL)

                WebUI.setText(findTestObject('Object Repository/Web Part/TaxCreate/Tax_Rate(global-input tag)'), Tax_Rate.get(i))
            }
            
            WebUI.click(findTestObject('Object Repository/Web Part/Discount Item/Save-btn'))

            WebUI.takeScreenshot()

            Alert = WebUI.getText(findTestObject('Object Repository/Web Part/Discount Item/Green_Card_Alert'), FailureHandling.STOP_ON_FAILURE)

            WebUI.verifyMatch(Alert, findTestData('Web Input Data/Tax').getValue('Alerts', 2), false, FailureHandling.STOP_ON_FAILURE)

            WebUI.takeScreenshot()

            Mobile.callTestCase(findTestCase('Product_Mobile/Common/JOB RUN/Tax_Job Run-TC'), [:], FailureHandling.STOP_ON_FAILURE)
        }
        
        Mobile.callTestCase(findTestCase('Product_Mobile/Common/Web Login and Navigation/Navigate to Tax Group Mapping'), [:], FailureHandling.STOP_ON_FAILURE)

        WebUI.click(findTestObject('Object Repository/Web Part/TaxMapping/Location_FilterField'))

        GlobalVariable.label = Location.get(i)

        println(GlobalVariable.label)

        WebUI.setText(findTestObject('Object Repository/Web Part/TaxMapping/Location_FilterField'), GlobalVariable.label)

        WebUI.delay(1)

        if (WebUI.verifyElementPresent(findTestObject('Web Part/FocusBrand(MSL)/td_Tag(GridCode)'), 2, FailureHandling.OPTIONAL)) {
            WebUI.click(findTestObject('Web Part/FocusBrand(MSL)/td_Tag(GridCode)'))

            WebUI.click(findTestObject('Object Repository/Web Part/TaxMapping/Edit-btn'))

            WebUI.waitForElementNotVisible(findTestObject('Web Part/Re-usable/Page Load'), 100)

            WebUI.click(findTestObject('Object Repository/Web Part/TaxMapping/Proceed-btn'))

            WebUI.waitForElementNotVisible(findTestObject('Web Part/Re-usable/Page Load'), 100)

            WebUI.click(findTestObject('Object Repository/Web Part/TaxMapping/Tax_Group_Dropdown'))

            GlobalVariable.label = Tax_Description.get(i)

            KeywordUtil.logInfo(GlobalVariable.label)

            WebUI.delay(1)

            WebUI.click(findTestObject('Object Repository/Web Part/TaxMapping/Span_Tag-2(Dropdown)'))

            WebUI.waitForElementNotVisible(findTestObject('Web Part/Re-usable/Page Load'), 100)

            WebUI.delay(1)

            for (int b = 0; b < ProductName.size(); b++) {
                WebUI.click(findTestObject('Object Repository/Web Part/TaxMapping/Product_FilterField'))

                GlobalVariable.label = ProductName.get(i)

                KeywordUtil.logInfo(GlobalVariable.label)

                WebUI.setText(findTestObject('Object Repository/Web Part/TaxMapping/Product_FilterField'), GlobalVariable.label)

                WebUI.delay(1)

                checkbox_type = WebUI.getAttribute(findTestObject('Object Repository/Web Part/Discount Item/Retailer-checkbox(global)'), 'src', FailureHandling.STOP_ON_FAILURE)

                unchecked_checkbox = findTestData('Web Input Data/Discount').getValue('Verify', 1)

                if (checkbox_type == unchecked_checkbox) {
                    WebUI.click(findTestObject('Object Repository/Web Part/TaxMapping/Product-checkbox'))
                }
                
                if (WebUI.verifyElementNotVisible(findTestObject('Object Repository/Web Part/TaxMapping/TaxGroupName(Grid-global)'), FailureHandling.OPTIONAL)) {
                    WebUI.click(findTestObject('Object Repository/Web Part/TaxMapping/Apply-btn'))
                }
            }
            
            WebUI.delay(1)

            WebUI.click(findTestObject('Object Repository/Web Part/TaxMapping/Finish-btn'))

            WebUI.takeScreenshot()

            Alert = WebUI.getText(findTestObject('Object Repository/Web Part/Discount Item/Green_Card_Alert'), FailureHandling.STOP_ON_FAILURE)

            WebUI.verifyMatch(Alert, findTestData('Web Input Data/Tax').getValue('Alerts', 3), false, FailureHandling.STOP_ON_FAILURE)

            WebUI.takeScreenshot()
        } else {
            WebUI.click(findTestObject('Web Part/FocusBrand(MSL)/Add_Btn'))

            WebUI.waitForElementNotVisible(findTestObject('Web Part/Re-usable/Page Load'), 100)

            WebUI.waitForElementVisible(findTestObject('Object Repository/Web Part/TaxMapping/LocationLevel_Dropdown'), 50, FailureHandling.STOP_ON_FAILURE)

            WebUI.click(findTestObject('Object Repository/Web Part/TaxMapping/LocationLevel_Dropdown'))

            GlobalVariable.label = LocationLevel.get(i)

            KeywordUtil.logInfo(GlobalVariable.label)

            WebUI.click(findTestObject('Object Repository/Web Part/Re-usable/Global/Span_Tag(Dropdown)'))

            WebUI.waitForElementNotVisible(findTestObject('Web Part/Re-usable/Page Load'), 100)

            WebUI.delay(1)

            WebUI.click(findTestObject('Object Repository/Web Part/TaxMapping/Location_Dropdown'))

            GlobalVariable.label = Location.get(i)

            KeywordUtil.logInfo(GlobalVariable.label)

            WebUI.click(findTestObject('Object Repository/Web Part/Re-usable/Global/Span_Tag(Dropdown)'))

            WebUI.waitForElementNotVisible(findTestObject('Web Part/Re-usable/Page Load'), 100)

            WebUI.delay(1)

            WebUI.click(findTestObject('Object Repository/Web Part/TaxMapping/Destination_Location_Dropdown'))

            GlobalVariable.label = DestinationLocation.get(i)

            KeywordUtil.logInfo(GlobalVariable.label)

            WebUI.click(findTestObject('Web Part/TaxMapping/Span_Tag-2(Dropdown)'))

            WebUI.waitForElementNotVisible(findTestObject('Web Part/Re-usable/Page Load'), 100)

            WebUI.delay(1)

            WebUI.click(findTestObject('Web Part/TaxMapping/TaxApplyType_Dropdown'))

            GlobalVariable.label = TaxApplyType.get(i)

            KeywordUtil.logInfo(GlobalVariable.label)

            WebUI.click(findTestObject('Web Part/Re-usable/Global/Span_Tag(Dropdown)'))

            WebUI.waitForElementNotVisible(findTestObject('Web Part/Re-usable/Page Load'), 100)

            WebUI.delay(1)

            WebUI.click(findTestObject('Object Repository/Web Part/TaxMapping/Eff To Field'))

            WebUI.callTestCase(findTestCase('Product_Mobile/Common/DatePicker_and _Navigation/Date Picker - Current'), [:], FailureHandling.STOP_ON_FAILURE)

            WebUI.waitForElementNotVisible(findTestObject('Web Part/Re-usable/Page Load'), 100)

            WebUI.click(findTestObject('Object Repository/Web Part/TaxMapping/Proceed-btn'))

            WebUI.waitForElementNotVisible(findTestObject('Web Part/Re-usable/Page Load'), 100)

            WebUI.click(findTestObject('Object Repository/Web Part/TaxMapping/Tax_Group_Dropdown'))

            GlobalVariable.label = Tax_Description.get(i)

            KeywordUtil.logInfo(GlobalVariable.label)

            WebUI.delay(1)

            WebUI.click(findTestObject('Object Repository/Web Part/TaxMapping/Span_Tag-2(Dropdown)'))

            WebUI.waitForElementNotVisible(findTestObject('Web Part/Re-usable/Page Load'), 100)

            WebUI.delay(1)

            for (int b = 0; b < ProductName.size(); b++) {
                WebUI.click(findTestObject('Object Repository/Web Part/TaxMapping/Product_FilterField'))

                GlobalVariable.label = ProductName.get(i)

                KeywordUtil.logInfo(GlobalVariable.label)

                WebUI.setText(findTestObject('Object Repository/Web Part/TaxMapping/Product_FilterField'), GlobalVariable.label)

                WebUI.delay(1)

                checkbox_type = WebUI.getAttribute(findTestObject('Object Repository/Web Part/Discount Item/Retailer-checkbox(global)'), 'src', FailureHandling.STOP_ON_FAILURE)

                unchecked_checkbox = findTestData('Web Input Data/Discount').getValue('Verify', 1)

                if (checkbox_type == unchecked_checkbox) {
                    WebUI.click(findTestObject('Object Repository/Web Part/TaxMapping/Product-checkbox'))
                }
                
                if (WebUI.verifyElementNotVisible(findTestObject('Object Repository/Web Part/TaxMapping/TaxGroupName(Grid-global)'), FailureHandling.OPTIONAL)) {
                    WebUI.delay(1)

                    WebUI.click(findTestObject('Object Repository/Web Part/TaxMapping/Apply-btn'))
                }
            }
            
            WebUI.click(findTestObject('Object Repository/Web Part/TaxMapping/Apply-btn'))

            WebUI.delay(1)

            WebUI.click(findTestObject('Object Repository/Web Part/TaxMapping/Finish-btn'))

            WebUI.takeScreenshot()

            Alert = WebUI.getText(findTestObject('Object Repository/Web Part/Discount Item/Green_Card_Alert'), FailureHandling.STOP_ON_FAILURE)

            WebUI.takeScreenshot()

            Mobile.callTestCase(findTestCase('Product_Mobile/Common/JOB RUN/Tax_Job Run-TC'), [:], FailureHandling.STOP_ON_FAILURE)
        }
    }
}

