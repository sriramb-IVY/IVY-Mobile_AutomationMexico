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

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Web Login and Navigation/DivisionLogin'), [:], FailureHandling.STOP_ON_FAILURE)

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Web Login and Navigation/Navigate to Discount Create'), [:], 
    FailureHandling.STOP_ON_FAILURE)

String sheet_name = 'Discount'

String file_name = 'Web Input Data'

ArrayList<String> Code = CustomKeywords.'poi.Automation.GetAllWebDataRow_Flag.getAllDataRow'(file_name, sheet_name, 'Code')

ArrayList<String> Description = CustomKeywords.'poi.Automation.GetAllWebDataRow_Flag.getAllDataRow'(file_name, sheet_name, 
    'Description')

ArrayList<String> Product_Filter = CustomKeywords.'poi.Automation.GetAllWebDataRow_Flag.getAllDataRow'(file_name, sheet_name, 
    'Product_Filter')

ArrayList<String> ProductName = CustomKeywords.'poi.Automation.GetAllWebDataRow_Flag.getAllDataRow'(file_name, sheet_name, 
    'ProductName')

ArrayList<String> User = CustomKeywords.'poi.Automation.GetAllWebDataRow_Flag.getAllDataRow'(file_name, sheet_name, 'User')

ArrayList<String> Retailer = CustomKeywords.'poi.Automation.GetAllWebDataRow_Flag.getAllDataRow'(file_name, sheet_name, 
    'Retailer')

ArrayList<String> Discount_Value = CustomKeywords.'poi.Automation.GetAllWebDataRow_Flag.getAllDataRow'(file_name, sheet_name, 
    'Discount_Value')

for (int i = 0; i < Code.size(); i++) {
    if (Code.get(i) != 'NULL') {
        WebUI.click(findTestObject('Web Part/FocusBrand(MSL)/CodeFilterField'))

        GlobalVariable.label = Code.get(i)

        println(GlobalVariable.label)

        WebUI.setText(findTestObject('Web Part/FocusBrand(MSL)/CodeFilterField'), GlobalVariable.label)

        WebUI.delay(1)

        if (WebUI.verifyElementPresent(findTestObject('Web Part/FocusBrand(MSL)/td_Tag(GridCode)'), 2, FailureHandling.OPTIONAL)) {
            WebUI.click(findTestObject('Web Part/FocusBrand(MSL)/td_Tag(GridCode)'))

            WebUI.click(findTestObject('Web Part/FocusBrand(MSL)/Edit_Btn'))

            WebUI.waitForElementNotVisible(findTestObject('Web Part/Re-usable/Page Load'), 100)

            WebUI.click(findTestObject('Web Part/Discount Item/Next-btn'))

            WebUI.waitForElementNotVisible(findTestObject('Web Part/Re-usable/Page Load'), 100)

            WebUI.click(findTestObject('Object Repository/Web Part/Discount Item/Filter_Icon'))

            WebUI.click(findTestObject('Object Repository/Web Part/Discount Item/Tree filter Product Brand search field'))

            GlobalVariable.label = Product_Filter.get(i)

            WebUI.setText(findTestObject('Object Repository/Web Part/Discount Item/Tree filter Product Brand search field'), 
                GlobalVariable.label)

            WebUI.delay(3)

            WebUI.click(findTestObject('Object Repository/Web Part/Discount Item/UserSelect_Span b Tag'))

            WebUI.delay(2)

            WebUI.click(findTestObject('Object Repository/Web Part/Discount Item/Apply-btn'))

            WebUI.click(findTestObject('Object Repository/Web Part/Discount Item/SKU_Name_filter_Field'))

            for (b = 0; b < ProductName.size(); b++) {
                if (ProductName.get(b) != 'NULL') {
                    GlobalVariable.label = ProductName.get(b)

                    WebUI.setText(findTestObject('Object Repository/Web Part/Discount Item/SKU_Name_filter_Field'), 
                        GlobalVariable.label)

                    WebUI.delay(1)

                    WebUI.waitForElementVisible(findTestObject('Object Repository/Web Part/Discount Item/Discount_min(global)'), 
                        0, FailureHandling.STOP_ON_FAILURE)

                    WebUI.click(findTestObject('Object Repository/Web Part/Discount Item/Discount_min(global)'))
					
					WebUI.clearText(findTestObject('Object Repository/Web Part/Discount Item/Discount_min(global-input tag)'), FailureHandling.STOP_ON_FAILURE)

                    WebUI.setText(findTestObject('Object Repository/Web Part/Discount Item/Discount_min(global-input tag)'), 
                        Discount_Value.get(b))

                    WebUI.click(findTestObject('Object Repository/Web Part/Discount Item/Discount_max(global)'))

					WebUI.clearText(findTestObject('Object Repository/Web Part/Discount Item/Discount_max(global-input tag)'), FailureHandling.STOP_ON_FAILURE)
					
                    WebUI.setText(findTestObject('Object Repository/Web Part/Discount Item/Discount_max(global-input tag)'), 
                        Discount_Value.get(b))

                    WebUI.click(findTestObject('Object Repository/Web Part/Discount Item/SaveAndProceed-btn'))
                }
            }
            
            Alert = WebUI.getText(findTestObject('Object Repository/Web Part/Discount Item/Green_Card_Alert'), 
                FailureHandling.STOP_ON_FAILURE)

            //WebUI.verifyMatch(Alert, findTestData('Web Input Data/Discount').getValue('Alerts', 1), false, FailureHandling.STOP_ON_FAILURE)
            WebUI.verifyElementPresent(findTestObject('Object Repository/Web Part/Discount Item/Green_Card_Alert'), 
                2, FailureHandling.OPTIONAL)

            WebUI.takeScreenshot()
        } else {
            WebUI.click(findTestObject('Web Part/FocusBrand(MSL)/Add_Btn'))

            WebUI.click(findTestObject('Object Repository/Web Part/Discount Item/Code'))

            WebUI.setText(findTestObject('Object Repository/Web Part/Discount Item/Code'), Code.get(i))

            WebUI.click(findTestObject('Object Repository/Web Part/Discount Item/Description'))

            WebUI.setText(findTestObject('Object Repository/Web Part/Discount Item/Description'), Description.get(
                    i))

            WebUI.click(findTestObject('Object Repository/Web Part/Discount Item/Description'))

            WebUI.click(findTestObject('Object Repository/Web Part/Discount Item/Effective_To'))

            WebUI.callTestCase(findTestCase('Product_Mobile/Common/DatePicker_and _Navigation/Date Picker - Future(1year)'), 
                [:], FailureHandling.STOP_ON_FAILURE)

            WebUI.click(findTestObject('Web Part/Discount Item/Next-btn'))

            WebUI.waitForElementNotVisible(findTestObject('Web Part/Re-usable/Page Load'), 100)

            WebUI.click(findTestObject('Object Repository/Web Part/Discount Item/Filter_Icon'))

            WebUI.click(findTestObject('Object Repository/Web Part/Discount Item/Tree filter Product Brand search field'))

            GlobalVariable.label = Product_Filter.get(i)

            WebUI.setText(findTestObject('Object Repository/Web Part/Discount Item/Tree filter Product Brand search field'), 
                GlobalVariable.label)

            WebUI.delay(5)

            WebUI.click(findTestObject('Object Repository/Web Part/Discount Item/UserSelect_Span b Tag'))

            WebUI.delay(2)

            WebUI.click(findTestObject('Object Repository/Web Part/Discount Item/Apply-btn'))

            WebUI.click(findTestObject('Object Repository/Web Part/Discount Item/SKU_Name_filter_Field'))

            for (b = 0; b < ProductName.size(); b++) {
                if (ProductName.get(b) != 'NULL') {
                    GlobalVariable.label = ProductName.get(b)

                    WebUI.setText(findTestObject('Object Repository/Web Part/Discount Item/SKU_Name_filter_Field'), 
                        GlobalVariable.label)

                    WebUI.delay(1)

                    WebUI.waitForElementVisible(findTestObject('Object Repository/Web Part/Discount Item/Discount_min(global)'), 
                        0, FailureHandling.STOP_ON_FAILURE)

                    WebUI.click(findTestObject('Object Repository/Web Part/Discount Item/Discount_min(global)'))

                    WebUI.setText(findTestObject('Object Repository/Web Part/Discount Item/Discount_min(global-input tag)'), 
                        Discount_Value.get(b))

                    WebUI.click(findTestObject('Object Repository/Web Part/Discount Item/Discount_max(global)'))

                    WebUI.setText(findTestObject('Object Repository/Web Part/Discount Item/Discount_max(global-input tag)'), 
                        Discount_Value.get(b))

                    WebUI.click(findTestObject('Object Repository/Web Part/Discount Item/SaveAndProceed-btn'))
                }
            }
            
            Alert = WebUI.getText(findTestObject('Object Repository/Web Part/Discount Item/Green_Card_Alert'), 
                FailureHandling.STOP_ON_FAILURE)

            WebUI.verifyMatch(Alert, findTestData('Data Files/Web Input Data/Discount').getValue('Alerts', 1), false, FailureHandling.STOP_ON_FAILURE)

            WebUI.takeScreenshot()
			
			Mobile.callTestCase(findTestCase('Product_Mobile/Common/JOB RUN/Discount_Job Run-TC'), [:], FailureHandling.STOP_ON_FAILURE)
        }
        
        Mobile.callTestCase(findTestCase('Test Cases/Product_Mobile/Common/Web Login and Navigation/Navigate to Discount Config'), 
            [:], FailureHandling.STOP_ON_FAILURE)

        WebUI.click(findTestObject('Web Part/FocusBrand(MSL)/CodeFilterField'))

        GlobalVariable.label = Description.get(i)

        println(GlobalVariable.label)

        WebUI.setText(findTestObject('Web Part/FocusBrand(MSL)/CodeFilterField'), GlobalVariable.label)

        WebUI.delay(1)

        if (WebUI.verifyElementPresent(findTestObject('Web Part/FocusBrand(MSL)/td_Tag(GridCode)'), 2, FailureHandling.OPTIONAL)) {
            WebUI.click(findTestObject('Web Part/FocusBrand(MSL)/td_Tag(GridCode)'))

            WebUI.click(findTestObject('Object Repository/Web Part/Discount Item/Configuration-btn'))

            WebUI.waitForElementNotVisible(findTestObject('Web Part/Re-usable/Page Load'), 100)

            WebUI.click(findTestObject('Object Repository/Web Part/Discount Item/Retailer_TabHeader'))

            WebUI.click(findTestObject('Object Repository/Web Part/Discount Item/Tree filter search field'))

            GlobalVariable.label = User.get(i)

            println(GlobalVariable.label)

            WebUI.setText(findTestObject('Object Repository/Web Part/Discount Item/Tree filter search field'), 
                GlobalVariable.label)

            WebUI.delay(4)

            WebUI.click(findTestObject('Object Repository/Web Part/Stock Allocation/Global Variables/b Tag(Button)'))

            WebUI.delay(2)

            WebUI.waitForElementNotVisible(findTestObject('Web Part/Re-usable/Page Load'), 100)

            WebUI.click(findTestObject('Object Repository/Web Part/Discount Item/StoreName_Filter_Field'))

            for (int b = 0; b < Retailer.size(); b++) {
                if (Retailer.get(i) != 'NULL') {
                    GlobalVariable.label = Retailer.get(b)

                    WebUI.setText(findTestObject('Object Repository/Web Part/Discount Item/StoreName_Filter_Field'), 
                        GlobalVariable.label)

                    WebUI.delay(1)

                    WebUI.takeScreenshot()

                    checkbox_type = WebUI.getAttribute(findTestObject('Object Repository/Web Part/Discount Item/Retailer-checkbox(global)'), 
                        'src', FailureHandling.STOP_ON_FAILURE)

                    unchecked_checkbox = findTestData('Web Input Data/Discount').getValue('Verify', 1)

                    if (checkbox_type == unchecked_checkbox) {
                        WebUI.click(findTestObject('Object Repository/Web Part/Discount Item/Retailer-checkbox(global)'))
                    }
                    
                    WebUI.takeScreenshot()
                }
            }
            
            WebUI.click(findTestObject('Object Repository/Web Part/Discount Item/Save-btn'))

            WebUI.takeScreenshot()

            Alert = WebUI.getText(findTestObject('Object Repository/Web Part/Discount Item/Green_Card_Alert'), 
                FailureHandling.STOP_ON_FAILURE)

         
            WebUI.verifyElementPresent(findTestObject('Object Repository/Web Part/Discount Item/Green_Card_Alert'), 
                2, FailureHandling.OPTIONAL)

            WebUI.takeScreenshot()
        }
    }
}

