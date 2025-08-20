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
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

not_run: Mobile.callTestCase(findTestCase('Product_Mobile/Common/Web Login and Navigation/DivisionLogin'), [:], FailureHandling.STOP_ON_FAILURE)

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Web Login and Navigation/Navigate to Focus Brand'), [:], FailureHandling.STOP_ON_FAILURE)

String sheet_name = 'FocusBrand'

String file_name = 'Web Input Data'

ArrayList<String> Code = CustomKeywords.'poi.Automation.GetAllWebDataRow_Flag.getAllDataRow'(file_name, sheet_name, 'Code')

ArrayList<String> Name = CustomKeywords.'poi.Automation.GetAllWebDataRow_Flag.getAllDataRow'(file_name, sheet_name, 'Name')

ArrayList<String> GroupCode = CustomKeywords.'poi.Automation.GetAllWebDataRow_Flag.getAllDataRow'(file_name, sheet_name, 
    'GroupCode')

ArrayList<String> GroupDesc = CustomKeywords.'poi.Automation.GetAllWebDataRow_Flag.getAllDataRow'(file_name, sheet_name, 
    'GroupDesc')

ArrayList<String> SkuName = CustomKeywords.'poi.Automation.GetAllWebDataRow_Flag.getAllDataRow'(file_name, sheet_name, 'SkuName')

ArrayList<String> LocationName = CustomKeywords.'poi.Automation.GetAllWebDataRow_Flag.getAllDataRow'(file_name, sheet_name, 
    'LocationName')

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

            WebUI.click(findTestObject('Web Part/FocusBrand(MSL)/SaveAndProceed_Btn'))

            WebUI.waitForElementNotVisible(findTestObject('Web Part/Re-usable/Page Load'), 100)

            WebUI.click(findTestObject('Web Part/FocusBrand(MSL)/CodeFilterField'))

            GlobalVariable.label = GroupCode.get(i)

            WebUI.setText(findTestObject('Web Part/FocusBrand(MSL)/CodeFilterField'), GlobalVariable.label)

            if (WebUI.verifyElementPresent(findTestObject('Web Part/FocusBrand(MSL)/td_Tag(GridCode)'), 2, 
                FailureHandling.OPTIONAL)) {
                WebUI.click(findTestObject('Web Part/FocusBrand(MSL)/td_Tag(GridCode)'))

                WebUI.click(findTestObject('Web Part/FocusBrand(MSL)/Edit_Btn'))

                WebUI.click(findTestObject('Web Part/FocusBrand(MSL)/SkuNameFilter'))

                for (int b = 0; b < SkuName.size(); b++) {
                    GlobalVariable.label = SkuName.get(b)

                    WebUI.setText(findTestObject('Web Part/FocusBrand(MSL)/SkuNameFilter'), GlobalVariable.label)

                    WebUI.delay(1)

                    checkbox_type = WebUI.getAttribute(findTestObject('Web Part/FocusBrand(MSL)/SkuCheckBox_td_Tag(Global)'), 
                        'src', FailureHandling.STOP_ON_FAILURE)

                    unchecked_checkbox = findTestData('Web Input Data/MustSell').getValue('Verify', 1)

                    if (checkbox_type == unchecked_checkbox) {
                        WebUI.click(findTestObject('Web Part/FocusBrand(MSL)/SkuCheckBox_td_Tag(Global)'))
                    }
                }
                
                WebUI.click(findTestObject('Web Part/FocusBrand(MSL)/Save_Btn'))

                WebUI.takeScreenshot()

                WebUI.waitForElementNotVisible(findTestObject('Web Part/Re-usable/Page Load'), 100)
            } else {
                WebUI.click(findTestObject('Web Part/FocusBrand(MSL)/Add_Btn'))

                WebUI.click(findTestObject('Web Part/FocusBrand(MSL)/GroupCode'))

                WebUI.setText(findTestObject('Web Part/FocusBrand(MSL)/GroupCode'), GroupCode.get(i))

                WebUI.click(findTestObject('Web Part/FocusBrand(MSL)/GroupDesc'))

                WebUI.setText(findTestObject('Web Part/FocusBrand(MSL)/GroupDesc'), GroupDesc.get(i))

                WebUI.click(findTestObject('Web Part/FocusBrand(MSL)/SkuNameFilter'))

                for (int c = 0; c < SkuName.size(); c++) {
                    GlobalVariable.label = SkuName.get(c)

                    WebUI.setText(findTestObject('Web Part/FocusBrand(MSL)/SkuNameFilter'), SkuName.get(c))

                    WebUI.delay(1)

                    checkbox_type = WebUI.getAttribute(findTestObject('Web Part/FocusBrand(MSL)/SkuCheckBox_td_Tag(Global)'), 
                        'src', FailureHandling.STOP_ON_FAILURE)

                    unchecked_checkbox = findTestData('Web Input Data/MustSell').getValue('Verify', 1)

                    if (checkbox_type == unchecked_checkbox) {
                        WebUI.click(findTestObject('Web Part/FocusBrand(MSL)/SkuCheckBox_td_Tag(Global)'))
                    }
                }
                
                WebUI.click(findTestObject('Web Part/FocusBrand(MSL)/Save_Btn'))

                WebUI.takeScreenshot()
				
				Mobile.callTestCase(findTestCase('Product_Mobile/Common/JOB RUN/ProductTagging_Job Run-TC'), [:], FailureHandling.STOP_ON_FAILURE)
            }
            
            WebUI.click(findTestObject('Web Part/FocusBrand(MSL)/Next_Btn'))

            WebUI.waitForElementNotVisible(findTestObject('Web Part/Re-usable/Page Load'), 100)

            WebUI.click(findTestObject('Web Part/FocusBrand(MSL)/GroupCodeFilter-2'))

            GlobalVariable.label = GroupCode.get(i)

            WebUI.setText(findTestObject('Web Part/FocusBrand(MSL)/GroupCodeFilter-2'), GlobalVariable.label)

            WebUI.click(findTestObject('Web Part/FocusBrand(MSL)/GroupCode-2(Td_Tag_Global)'))

            WebUI.waitForElementNotVisible(findTestObject('Web Part/Re-usable/Page Load'), 100)

            WebUI.click(findTestObject('Web Part/FocusBrand(MSL)/LocationFilterField'))

            for (int d = 0; d < LocationName.size(); d++) {
                if (LocationName.get(d) != 'NULL') {
                    GlobalVariable.label = LocationName.get(d)

                    println(GlobalVariable.label)

                    WebUI.setText(findTestObject('Web Part/FocusBrand(MSL)/LocationFilterField'), GlobalVariable.label)

                    WebUI.delay(2)

                    checkbox_type = WebUI.getAttribute(findTestObject('Web Part/FocusBrand(MSL)/SkuCheckBox_td_Tag(Global)'), 
                        'src', FailureHandling.STOP_ON_FAILURE)

                    unchecked_checkbox = findTestData('Web Input Data/MustSell').getValue('Verify', 1)

                    if (checkbox_type == unchecked_checkbox) {
                        WebUI.click(findTestObject('Web Part/FocusBrand(MSL)/SkuCheckBox_td_Tag(Global)'))
                    }
                    
                    WebUI.click(findTestObject('Web Part/FocusBrand(MSL)/td_Tag(GridCode)'))
                }
            }
            
            WebUI.click(findTestObject('Web Part/FocusBrand(MSL)/Finish_Btn'))

            WebUI.verifyElementPresent(findTestObject('Web Part/FocusBrand(MSL)/Focus-UpdatedSuccessful_Alert'), 
                5)

            println('MustSell updated Successfully')

            WebUI.takeScreenshot()
        } else {
            WebUI.click(findTestObject('Web Part/FocusBrand(MSL)/Add_Btn'))

            WebUI.waitForElementNotVisible(findTestObject('Web Part/Re-usable/Page Load'), 100)

            WebUI.click(findTestObject('Web Part/FocusBrand(MSL)/Code'))

            WebUI.setText(findTestObject('Web Part/FocusBrand(MSL)/Code'), Code.get(i))

            WebUI.click(findTestObject('Web Part/FocusBrand(MSL)/Name'))

            WebUI.setText(findTestObject('Web Part/FocusBrand(MSL)/Name'), Name.get(i))

            WebUI.click(findTestObject('Web Part/FocusBrand(MSL)/EffectiveFrom'))

            not_run: WebUI.delay(5)

            WebUI.callTestCase(findTestCase('Product_Mobile/Common/DatePicker_and _Navigation/Date Picker'), [('DateValue') : 'Today'], 
                FailureHandling.STOP_ON_FAILURE)

            WebUI.click(findTestObject('Web Part/FocusBrand(MSL)/EffectiveTo'))

            not_run: WebUI.delay(5)

            WebUI.callTestCase(findTestCase('Product_Mobile/Common/DatePicker_and _Navigation/Date Picker - Future(1year)'), 
                [:], FailureHandling.STOP_ON_FAILURE)

            WebUI.click(findTestObject('Web Part/FocusBrand(MSL)/SaveAndProceed_Btn'))

            WebUI.waitForElementNotVisible(findTestObject('Web Part/Re-usable/Page Load'), 100)

            WebUI.click(findTestObject('Web Part/FocusBrand(MSL)/CodeFilterField'))

            GlobalVariable.label = GroupCode.get(i)

            WebUI.setText(findTestObject('Web Part/FocusBrand(MSL)/CodeFilterField'), GroupCode.get(i))

            if (WebUI.verifyElementPresent(findTestObject('Web Part/FocusBrand(MSL)/td_Tag(GridCode)'), 2, 
                FailureHandling.OPTIONAL)) {
                WebUI.click(findTestObject('Web Part/FocusBrand(MSL)/td_Tag(GridCode)'))

                WebUI.click(findTestObject('Web Part/FocusBrand(MSL)/Edit_Btn'))

                WebUI.click(findTestObject('Web Part/FocusBrand(MSL)/SkuNameFilter'))

                for (int b = 0; b < SkuName.size(); b++) {
                    GlobalVariable.label = SkuName.get(b)

                    WebUI.setText(findTestObject('Web Part/FocusBrand(MSL)/SkuNameFilter'), GlobalVariable.label)

                    WebUI.delay(2)

                    checkbox_type = WebUI.getAttribute(findTestObject('Web Part/FocusBrand(MSL)/SkuCheckBox_td_Tag(Global)'), 
                        'src', FailureHandling.STOP_ON_FAILURE)

                    unchecked_checkbox = findTestData('Web Input Data/MustSell').getValue('Verify', 1)

                    if (checkbox_type == unchecked_checkbox) {
                        WebUI.click(findTestObject('Web Part/FocusBrand(MSL)/SkuCheckBox_td_Tag(Global)'))
                    }
                }
                
                WebUI.click(findTestObject('Web Part/FocusBrand(MSL)/Save_Btn'))

                WebUI.waitForElementNotVisible(findTestObject('Web Part/Re-usable/Page Load'), 100)
            } else {
                WebUI.click(findTestObject('Web Part/FocusBrand(MSL)/Add_Btn'))

                WebUI.click(findTestObject('Web Part/FocusBrand(MSL)/GroupCode'))

                WebUI.setText(findTestObject('Web Part/FocusBrand(MSL)/GroupCode'), GroupCode.get(i))

                WebUI.click(findTestObject('Web Part/FocusBrand(MSL)/GroupDesc'))

                WebUI.setText(findTestObject('Web Part/FocusBrand(MSL)/GroupDesc'), GroupDesc.get(i))

                WebUI.click(findTestObject('Web Part/FocusBrand(MSL)/SkuNameFilter'))

                for (int b = 0; b < SkuName.size(); b++) {
                    GlobalVariable.label = SkuName.get(b)

                    WebUI.setText(findTestObject('Web Part/FocusBrand(MSL)/SkuNameFilter'), GlobalVariable.label)

                    WebUI.delay(2)

                    checkbox_type = WebUI.getAttribute(findTestObject('Web Part/FocusBrand(MSL)/SkuCheckBox_td_Tag(Global)'), 
                        'src', FailureHandling.STOP_ON_FAILURE)

                    unchecked_checkbox = findTestData('Web Input Data/MustSell').getValue('Verify', 1)

                    if (checkbox_type == unchecked_checkbox) {
                        WebUI.click(findTestObject('Web Part/FocusBrand(MSL)/SkuCheckBox_td_Tag(Global)'))
                    }
                }
                
                WebUI.click(findTestObject('Web Part/FocusBrand(MSL)/Save_Btn'))

                WebUI.click(findTestObject('Web Part/FocusBrand(MSL)/Next_Btn'))

                WebUI.waitForElementNotVisible(findTestObject('Web Part/Re-usable/Page Load'), 100)

                GlobalVariable.label = GroupCode.get(i)

                WebUI.click(findTestObject('Web Part/FocusBrand(MSL)/GroupCodeFilter-2'))

                WebUI.setText(findTestObject('Web Part/FocusBrand(MSL)/GroupCodeFilter-2'), GlobalVariable.label)

                WebUI.click(findTestObject('Web Part/FocusBrand(MSL)/GroupCode-2(Td_Tag_Global)'))

                WebUI.waitForElementNotVisible(findTestObject('Web Part/Re-usable/Page Load'), 100)

                WebUI.click(findTestObject('Web Part/FocusBrand(MSL)/LocationFilterField'))

                for (int e = 0; e < LocationName.size(); e++) {
                    if (LocationName.get(e) != 'NULL') {
                        GlobalVariable.label = LocationName.get(e)

                        println(GlobalVariable.label)

                        WebUI.setText(findTestObject('Web Part/FocusBrand(MSL)/LocationFilterField'), GlobalVariable.label)

                        WebUI.delay(2)

                        checkbox_type = WebUI.getAttribute(findTestObject('Web Part/FocusBrand(MSL)/SkuCheckBox_td_Tag(Global)'), 
                            'src', FailureHandling.STOP_ON_FAILURE)

                        unchecked_checkbox = findTestData('Web Input Data/MustSell').getValue('Verify', 1)

                        if (checkbox_type == unchecked_checkbox) {
                            WebUI.click(findTestObject('Web Part/FocusBrand(MSL)/SkuCheckBox_td_Tag(Global)'))
                        }
                        
                        WebUI.click(findTestObject('Web Part/FocusBrand(MSL)/td_Tag(GridCode)'))
                    }
                }
                
                WebUI.click(findTestObject('Web Part/FocusBrand(MSL)/Finish_Btn'))

                WebUI.verifyElementPresent(findTestObject('Web Part/FocusBrand(MSL)/Focus-SavedSuccessful_Alert'), 
                    5)

                WebUI.takeScreenshot()
            }
            
            println('INV_28 To verify user can able to create FocusBrand')
			
			Mobile.callTestCase(findTestCase('Product_Mobile/Common/JOB RUN/ProductTagging_Job Run-TC'), [:], FailureHandling.STOP_ON_FAILURE)
        }
    }
}


