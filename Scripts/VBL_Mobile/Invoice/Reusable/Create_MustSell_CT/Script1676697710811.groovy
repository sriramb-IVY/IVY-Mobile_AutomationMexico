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

Mobile.callTestCase(findTestCase('VBL_Mobile/Re-Usables/Web Login and Navigation/AdminLogin'), [:], FailureHandling.STOP_ON_FAILURE)

Mobile.callTestCase(findTestCase('VBL_Mobile/Re-Usables/Web Login and Navigation/Navigate to Focus Brand (MSL)'), [:], FailureHandling.STOP_ON_FAILURE)

String sheet_name = 'MustSell'

String file_name = 'Web Input Data'

ArrayList<String> Code = CustomKeywords.'poi.VBL.Automation.GetAllWebDataRow_Flag.getAllDataRow'(file_name, sheet_name, 
    'Code')

ArrayList<String> Name = CustomKeywords.'poi.VBL.Automation.GetAllWebDataRow_Flag.getAllDataRow'(file_name, sheet_name, 
    'Name')

ArrayList<String> GroupCode = CustomKeywords.'poi.VBL.Automation.GetAllWebDataRow_Flag.getAllDataRow'(file_name, sheet_name, 
    'GroupCode')

ArrayList<String> GroupDesc = CustomKeywords.'poi.VBL.Automation.GetAllWebDataRow_Flag.getAllDataRow'(file_name, sheet_name, 
    'GroupDesc')

ArrayList<String> SkuName = CustomKeywords.'poi.VBL.Automation.GetAllWebDataRow_Flag.getAllDataRow'(file_name, sheet_name, 
    'SkuName')

ArrayList<String> Sequence = CustomKeywords.'poi.VBL.Automation.GetAllWebDataRow_Flag.getAllDataRow'(file_name, sheet_name, 
    'Sequence')

ArrayList<String> LocationName = CustomKeywords.'poi.VBL.Automation.GetAllWebDataRow_Flag.getAllDataRow'(file_name, sheet_name, 
    'LocationName')

for (int i = 0; i < Code.size(); i++) {
    if (Code.get(i) != 'NULL') {
        WebUI.click(findTestObject('XXXXXXXXXXXX/Web Part/FocusBrand(MSL)/CodeFilterField'))

        GlobalVariable.label = Code.get(i)

        println(GlobalVariable.label)

        WebUI.setText(findTestObject('XXXXXXXXXXXX/Web Part/FocusBrand(MSL)/CodeFilterField'), GlobalVariable.label)

        WebUI.delay(1)

        if (WebUI.verifyElementPresent(findTestObject('XXXXXXXXXXXX/Web Part/FocusBrand(MSL)/td_Tag(GridCode)'), 2, FailureHandling.OPTIONAL)) {
            'MustSell Name Already Existx!'
            WebUI.takeScreenshot()

            WebUI.click(findTestObject('XXXXXXXXXXXX/Web Part/FocusBrand(MSL)/td_Tag(GridCode)'))

            WebUI.click(findTestObject('XXXXXXXXXXXX/Web Part/FocusBrand(MSL)/Edit_Btn'))

            WebUI.waitForElementNotVisible(findTestObject('XXXXXXXXXXXX/Web Part/Re-usable/Page Load'), 100)

            WebUI.takeScreenshot()

            WebUI.click(findTestObject('XXXXXXXXXXXX/Web Part/FocusBrand(MSL)/SaveAndProceed_Btn'))

            WebUI.waitForElementNotVisible(findTestObject('XXXXXXXXXXXX/Web Part/Re-usable/Page Load'), 100)

            WebUI.click(findTestObject('XXXXXXXXXXXX/Web Part/FocusBrand(MSL)/CodeFilterField'))

            GlobalVariable.label = GroupCode.get(i)

            WebUI.setText(findTestObject('XXXXXXXXXXXX/Web Part/FocusBrand(MSL)/CodeFilterField'), GlobalVariable.label)

            if (WebUI.verifyElementPresent(findTestObject('XXXXXXXXXXXX/Web Part/FocusBrand(MSL)/td_Tag(GridCode)'), 2, FailureHandling.OPTIONAL)) {
                'MusSell Group Name Already Exist!'
                WebUI.takeScreenshot()

                WebUI.click(findTestObject('XXXXXXXXXXXX/Web Part/FocusBrand(MSL)/td_Tag(GridCode)'))

                WebUI.click(findTestObject('XXXXXXXXXXXX/Web Part/FocusBrand(MSL)/Edit_Btn'))

                WebUI.click(findTestObject('XXXXXXXXXXXX/Web Part/FocusBrand(MSL)/SkuNameFilter'))

                for (int b = 0; b < SkuName.size(); b++) {
                    GlobalVariable.label = SkuName.get(b)

                    GlobalVariable.label2 = Sequence.get(b)

                    WebUI.setText(findTestObject('XXXXXXXXXXXX/Web Part/FocusBrand(MSL)/SkuNameFilter'), GlobalVariable.label)

                    WebUI.delay(2)

                    checkbox_type = WebUI.getAttribute(findTestObject('XXXXXXXXXXXX/Web Part/FocusBrand(MSL)/SkuCheckBox_td_Tag(Global)'), 
                        'src', FailureHandling.STOP_ON_FAILURE)

                    unchecked_checkbox = findTestData('VBL_Web Input Data/MustSell').getValue('Verify', 1)

                    if (checkbox_type == unchecked_checkbox) {
                        'SKU check box not enbaled so we need to enable and enter sequence'
                        WebUI.takeScreenshot()

                        WebUI.click(findTestObject('XXXXXXXXXXXX/Web Part/FocusBrand(MSL)/SkuCheckBox_td_Tag(Global)'))

                        WebUI.doubleClick(findTestObject('Object Repository/XXXXXXXXXXXX/Web Part/FocusBrand(MSL)/SequenceField_(global-td tag)'), 
                            FailureHandling.STOP_ON_FAILURE)

                        WebUI.clearText(findTestObject('Object Repository/XXXXXXXXXXXX/Web Part/FocusBrand(MSL)/SequenceField_(global-td input tag)'))

                        WebUI.setText(findTestObject('Object Repository/XXXXXXXXXXXX/Web Part/FocusBrand(MSL)/SequenceField_(global-td input tag)'), 
                            GlobalVariable.label2)

                        WebUI.takeScreenshot()
                    } else {
                        'SKU check box enabled so we need to  enter sequence'
                        WebUI.takeScreenshot()

                        WebUI.doubleClick(findTestObject('Object Repository/XXXXXXXXXXXX/Web Part/FocusBrand(MSL)/SequenceField_(global-td tag)'), 
                            FailureHandling.STOP_ON_FAILURE)

                        WebUI.clearText(findTestObject('Object Repository/XXXXXXXXXXXX/Web Part/FocusBrand(MSL)/SequenceField_(global-td input tag)'))

                        WebUI.setText(findTestObject('Object Repository/XXXXXXXXXXXX/Web Part/FocusBrand(MSL)/SequenceField_(global-td input tag)'), 
                            GlobalVariable.label2)

                        WebUI.takeScreenshot()
                    }
                }
                
                WebUI.click(findTestObject('XXXXXXXXXXXX/Web Part/FocusBrand(MSL)/Save_Btn'))

                WebUI.takeScreenshot()

                WebUI.waitForElementNotVisible(findTestObject('XXXXXXXXXXXX/Web Part/Re-usable/Page Load'), 100)
            } else {
                'FocusBrand Group Name Not Exist! so we need to create!'
                WebUI.takeScreenshot()

                WebUI.click(findTestObject('XXXXXXXXXXXX/Web Part/FocusBrand(MSL)/Add_Btn'))

                WebUI.click(findTestObject('XXXXXXXXXXXX/Web Part/FocusBrand(MSL)/GroupCode'))

                WebUI.setText(findTestObject('XXXXXXXXXXXX/Web Part/FocusBrand(MSL)/GroupCode'), GroupCode.get(i))

                WebUI.click(findTestObject('XXXXXXXXXXXX/Web Part/FocusBrand(MSL)/GroupDesc'))

                WebUI.setText(findTestObject('XXXXXXXXXXXX/Web Part/FocusBrand(MSL)/GroupDesc'), GroupDesc.get(i))

                WebUI.click(findTestObject('XXXXXXXXXXXX/Web Part/FocusBrand(MSL)/SkuNameFilter'))

                for (int b = 0; b < SkuName.size(); b++) {
                    GlobalVariable.label = SkuName.get(b)

                    GlobalVariable.label2 = Sequence.get(b)

                    WebUI.setText(findTestObject('XXXXXXXXXXXX/Web Part/FocusBrand(MSL)/SkuNameFilter'), GlobalVariable.label)

                    WebUI.delay(2)

                    checkbox_type = WebUI.getAttribute(findTestObject('XXXXXXXXXXXX/Web Part/FocusBrand(MSL)/SkuCheckBox_td_Tag(Global)'), 
                        'src', FailureHandling.STOP_ON_FAILURE)

                    unchecked_checkbox = findTestData('VBL_Web Input Data/MustSell').getValue('Verify', 1)

                    if (checkbox_type == unchecked_checkbox) {
                        'SKU check box not enabled so we need to enable and enter sequence'
                        WebUI.takeScreenshot()

                        WebUI.click(findTestObject('XXXXXXXXXXXX/Web Part/FocusBrand(MSL)/SkuCheckBox_td_Tag(Global)'))

                        WebUI.doubleClick(findTestObject('Object Repository/XXXXXXXXXXXX/Web Part/FocusBrand(MSL)/SequenceField_(global-td tag)'), 
                            FailureHandling.STOP_ON_FAILURE)

                        WebUI.clearText(findTestObject('Object Repository/XXXXXXXXXXXX/Web Part/FocusBrand(MSL)/SequenceField_(global-td input tag)'))

                        WebUI.setText(findTestObject('Object Repository/XXXXXXXXXXXX/Web Part/FocusBrand(MSL)/SequenceField_(global-td input tag)'), 
                            GlobalVariable.label2)

                        WebUI.takeScreenshot()
                    } else {
                        'SKU check box enbaled so we need to enter sequence'
                        WebUI.takeScreenshot()

                        WebUI.doubleClick(findTestObject('Object Repository/XXXXXXXXXXXX/Web Part/FocusBrand(MSL)/SequenceField_(global-td tag)'), 
                            FailureHandling.STOP_ON_FAILURE)

                        WebUI.clearText(findTestObject('Object Repository/XXXXXXXXXXXX/Web Part/FocusBrand(MSL)/SequenceField_(global-td input tag)'))

                        WebUI.setText(findTestObject('Object Repository/XXXXXXXXXXXX/Web Part/FocusBrand(MSL)/SequenceField_(global-td input tag)'), 
                            GlobalVariable.label2)

                        WebUI.takeScreenshot()
                    }
                }
                
                WebUI.click(findTestObject('XXXXXXXXXXXX/Web Part/FocusBrand(MSL)/Save_Btn'))

                WebUI.takeScreenshot()
            }
            
            WebUI.click(findTestObject('XXXXXXXXXXXX/Web Part/FocusBrand(MSL)/Next_Btn'))

            WebUI.waitForElementNotVisible(findTestObject('XXXXXXXXXXXX/Web Part/Re-usable/Page Load'), 100)

            WebUI.click(findTestObject('XXXXXXXXXXXX/Web Part/FocusBrand(MSL)/GroupCodeFilter-2'))

            GlobalVariable.label = GroupCode.get(i)

            WebUI.setText(findTestObject('XXXXXXXXXXXX/Web Part/FocusBrand(MSL)/GroupCodeFilter-2'), GlobalVariable.label)

            WebUI.takeScreenshot()

            WebUI.click(findTestObject('XXXXXXXXXXXX/Web Part/FocusBrand(MSL)/GroupCode-2(Td_Tag_Global)'))

            WebUI.waitForElementNotVisible(findTestObject('XXXXXXXXXXXX/Web Part/Re-usable/Page Load'), 100)

            WebUI.click(findTestObject('XXXXXXXXXXXX/Web Part/FocusBrand(MSL)/LocationFilterField'))

            for (int d = 0; d < LocationName.size(); d++) {
                if (LocationName.get(d) != 'NULL') {
                    GlobalVariable.label = LocationName.get(d)

                    println(GlobalVariable.label)

                    WebUI.setText(findTestObject('XXXXXXXXXXXX/Web Part/FocusBrand(MSL)/LocationFilterField'), GlobalVariable.label)

                    WebUI.delay(2)

                    WebUI.takeScreenshot()

                    checkbox_type = WebUI.getAttribute(findTestObject('XXXXXXXXXXXX/Web Part/FocusBrand(MSL)/SkuCheckBox_td_Tag(Global)'), 
                        'src', FailureHandling.STOP_ON_FAILURE)

                    unchecked_checkbox = findTestData('VBL_Web Input Data/MustSell').getValue('Verify', 1)

                    if (checkbox_type == unchecked_checkbox) {
                        'Entered location check box need to be enable'
                        WebUI.takeScreenshot()

                        WebUI.click(findTestObject('XXXXXXXXXXXX/Web Part/FocusBrand(MSL)/SkuCheckBox_td_Tag(Global)'))
                    }
                    
                    'Entered location check box already enabled'
                    WebUI.takeScreenshot()

                    WebUI.click(findTestObject('XXXXXXXXXXXX/Web Part/FocusBrand(MSL)/td_Tag(GridCode)'))
                }
            }
            
            WebUI.click(findTestObject('XXXXXXXXXXXX/Web Part/FocusBrand(MSL)/Finish_Btn'))

            WebUI.verifyElementPresent(findTestObject('XXXXXXXXXXXX/Web Part/FocusBrand(MSL)/UpdatedSuccessful_PopUp'), 5)

            println('MustSell updated Successfully')

            WebUI.takeScreenshot()
        } else {
            'MustSell Name Not exist so we need Create!'
            WebUI.takeScreenshot()

            WebUI.click(findTestObject('XXXXXXXXXXXX/Web Part/FocusBrand(MSL)/Add_Btn'))

            WebUI.waitForElementNotVisible(findTestObject('XXXXXXXXXXXX/Web Part/Re-usable/Page Load'), 100)

            WebUI.click(findTestObject('XXXXXXXXXXXX/Web Part/FocusBrand(MSL)/Code'))

            WebUI.setText(findTestObject('XXXXXXXXXXXX/Web Part/FocusBrand(MSL)/Code'), Code.get(i))

            WebUI.click(findTestObject('XXXXXXXXXXXX/Web Part/FocusBrand(MSL)/Name'))

            WebUI.setText(findTestObject('XXXXXXXXXXXX/Web Part/FocusBrand(MSL)/Name'), Name.get(i))

            WebUI.click(findTestObject('XXXXXXXXXXXX/Web Part/FocusBrand(MSL)/EffectiveFrom'))

            not_run: WebUI.delay(5)

            WebUI.callTestCase(findTestCase('VBL_Mobile/Re-Usables/DatePicker_and _Navigation/Date Picker'), [('DateValue') : 'Today'], 
                FailureHandling.STOP_ON_FAILURE)

            WebUI.click(findTestObject('XXXXXXXXXXXX/Web Part/FocusBrand(MSL)/EffectiveTo'))

            not_run: WebUI.delay(5)

            WebUI.callTestCase(findTestCase('VBL_Mobile/Re-Usables/DatePicker_and _Navigation/Date Picker - Future(1year)'), 
                [('DateValue') : 'tomorrow'], FailureHandling.STOP_ON_FAILURE)

            WebUI.takeScreenshot()

            WebUI.click(findTestObject('XXXXXXXXXXXX/Web Part/FocusBrand(MSL)/SaveAndProceed_Btn'))

            WebUI.waitForElementNotVisible(findTestObject('XXXXXXXXXXXX/Web Part/Re-usable/Page Load'), 100)

            WebUI.click(findTestObject('XXXXXXXXXXXX/Web Part/FocusBrand(MSL)/CodeFilterField'))

            GlobalVariable.label = GroupCode.get(i)

            WebUI.setText(findTestObject('XXXXXXXXXXXX/Web Part/FocusBrand(MSL)/CodeFilterField'), GroupCode.get(i))

            if (WebUI.verifyElementPresent(findTestObject('XXXXXXXXXXXX/Web Part/FocusBrand(MSL)/td_Tag(GridCode)'), 2, FailureHandling.OPTIONAL)) {
                'MustSell Group Name Already Exist!'
                WebUI.takeScreenshot()

                WebUI.click(findTestObject('XXXXXXXXXXXX/Web Part/FocusBrand(MSL)/td_Tag(GridCode)'))

                WebUI.click(findTestObject('XXXXXXXXXXXX/Web Part/FocusBrand(MSL)/Edit_Btn'))

                WebUI.click(findTestObject('XXXXXXXXXXXX/Web Part/FocusBrand(MSL)/SkuNameFilter'))

                for (int b = 0; b < SkuName.size(); b++) {
                    GlobalVariable.label = SkuName.get(b)

                    GlobalVariable.label2 = Sequence.get(b)

                    WebUI.setText(findTestObject('XXXXXXXXXXXX/Web Part/FocusBrand(MSL)/SkuNameFilter'), GlobalVariable.label)

                    WebUI.delay(2)

                    checkbox_type = WebUI.getAttribute(findTestObject('XXXXXXXXXXXX/Web Part/FocusBrand(MSL)/SkuCheckBox_td_Tag(Global)'), 
                        'src', FailureHandling.STOP_ON_FAILURE)

                    unchecked_checkbox = findTestData('VBL_Web Input Data/MustSell').getValue('Verify', 1)

                    if (checkbox_type == unchecked_checkbox) {
                        'Sku not checked so we need to check and enter Sequence'
                        WebUI.takeScreenshot()

                        WebUI.click(findTestObject('XXXXXXXXXXXX/Web Part/FocusBrand(MSL)/SkuCheckBox_td_Tag(Global)'))

                        WebUI.doubleClick(findTestObject('Object Repository/XXXXXXXXXXXX/Web Part/FocusBrand(MSL)/SequenceField_(global-td tag)'), 
                            FailureHandling.STOP_ON_FAILURE)

                        WebUI.clearText(findTestObject('Object Repository/XXXXXXXXXXXX/Web Part/FocusBrand(MSL)/SequenceField_(global-td input tag)'))

                        WebUI.setText(findTestObject('Object Repository/XXXXXXXXXXXX/Web Part/FocusBrand(MSL)/SequenceField_(global-td input tag)'), 
                            GlobalVariable.label2)
                    } else {
                        'Sku checked so we need to check and enter Sequence'
                        WebUI.takeScreenshot()

                        WebUI.doubleClick(findTestObject('Object Repository/XXXXXXXXXXXX/Web Part/FocusBrand(MSL)/SequenceField_(global-td tag)'), 
                            FailureHandling.STOP_ON_FAILURE)

                        WebUI.clearText(findTestObject('Object Repository/XXXXXXXXXXXX/Web Part/FocusBrand(MSL)/SequenceField_(global-td input tag)'))

                        WebUI.setText(findTestObject('Object Repository/XXXXXXXXXXXX/Web Part/FocusBrand(MSL)/SequenceField_(global-td input tag)'), 
                            GlobalVariable.label2)
                    }
                }
                
                WebUI.click(findTestObject('XXXXXXXXXXXX/Web Part/FocusBrand(MSL)/Save_Btn'))

                WebUI.waitForElementNotVisible(findTestObject('XXXXXXXXXXXX/Web Part/Re-usable/Page Load'), 100)
            } else {
                'MustSell group name not exist so we need to create'
                WebUI.takeScreenshot()

                WebUI.click(findTestObject('XXXXXXXXXXXX/Web Part/FocusBrand(MSL)/Add_Btn'))

                WebUI.click(findTestObject('XXXXXXXXXXXX/Web Part/FocusBrand(MSL)/GroupCode'))

                WebUI.setText(findTestObject('XXXXXXXXXXXX/Web Part/FocusBrand(MSL)/GroupCode'), GroupCode.get(i))

                WebUI.click(findTestObject('XXXXXXXXXXXX/Web Part/FocusBrand(MSL)/GroupDesc'))

                WebUI.setText(findTestObject('XXXXXXXXXXXX/Web Part/FocusBrand(MSL)/GroupDesc'), GroupDesc.get(i))

                WebUI.click(findTestObject('XXXXXXXXXXXX/Web Part/FocusBrand(MSL)/SkuNameFilter'))

                for (int b = 0; b < SkuName.size(); b++) {
                    GlobalVariable.label = SkuName.get(b)

                    GlobalVariable.label2 = Sequence.get(b)

                    WebUI.setText(findTestObject('XXXXXXXXXXXX/Web Part/FocusBrand(MSL)/SkuNameFilter'), GlobalVariable.label)

                    WebUI.delay(2)

                    checkbox_type = WebUI.getAttribute(findTestObject('XXXXXXXXXXXX/Web Part/FocusBrand(MSL)/SkuCheckBox_td_Tag(Global)'), 
                        'src', FailureHandling.STOP_ON_FAILURE)

                    unchecked_checkbox = findTestData('VBL_Web Input Data/MustSell').getValue('Verify', 1)

                    if (checkbox_type == unchecked_checkbox) {
                        'SKU check box not enabled so we need to enable and enter sequence'
                        WebUI.takeScreenshot()

                        WebUI.click(findTestObject('XXXXXXXXXXXX/Web Part/FocusBrand(MSL)/SkuCheckBox_td_Tag(Global)'))

                        WebUI.doubleClick(findTestObject('Object Repository/XXXXXXXXXXXX/Web Part/FocusBrand(MSL)/SequenceField_(global-td tag)'), 
                            FailureHandling.STOP_ON_FAILURE)

                        WebUI.clearText(findTestObject('Object Repository/XXXXXXXXXXXX/Web Part/FocusBrand(MSL)/SequenceField_(global-td input tag)'))

                        WebUI.setText(findTestObject('Object Repository/XXXXXXXXXXXX/Web Part/FocusBrand(MSL)/SequenceField_(global-td input tag)'), 
                            GlobalVariable.label2)

                        WebUI.takeScreenshot()
                    } else {
                        'SKU check box enbaled so we need to enter sequence'
                        WebUI.takeScreenshot()

                        WebUI.doubleClick(findTestObject('Object Repository/XXXXXXXXXXXX/Web Part/FocusBrand(MSL)/SequenceField_(global-td tag)'), 
                            FailureHandling.STOP_ON_FAILURE)

                        WebUI.clearText(findTestObject('Object Repository/XXXXXXXXXXXX/Web Part/FocusBrand(MSL)/SequenceField_(global-td input tag)'))

                        WebUI.setText(findTestObject('Object Repository/XXXXXXXXXXXX/Web Part/FocusBrand(MSL)/SequenceField_(global-td input tag)'), 
                            GlobalVariable.label2)

                        WebUI.takeScreenshot()
                    }
                }
                
                WebUI.click(findTestObject('XXXXXXXXXXXX/Web Part/FocusBrand(MSL)/Save_Btn'))
            }
            
            WebUI.click(findTestObject('XXXXXXXXXXXX/Web Part/FocusBrand(MSL)/Next_Btn'))

            WebUI.waitForElementNotVisible(findTestObject('XXXXXXXXXXXX/Web Part/Re-usable/Page Load'), 100)

            GlobalVariable.label = GroupCode.get(i)

            WebUI.click(findTestObject('XXXXXXXXXXXX/Web Part/FocusBrand(MSL)/GroupCodeFilter-2'))

            WebUI.setText(findTestObject('XXXXXXXXXXXX/Web Part/FocusBrand(MSL)/GroupCodeFilter-2'), GlobalVariable.label)

            WebUI.click(findTestObject('XXXXXXXXXXXX/Web Part/FocusBrand(MSL)/GroupCode-2(Td_Tag_Global)'))

            WebUI.waitForElementNotVisible(findTestObject('XXXXXXXXXXXX/Web Part/Re-usable/Page Load'), 100)

            WebUI.click(findTestObject('XXXXXXXXXXXX/Web Part/FocusBrand(MSL)/LocationFilterField'))

            for (int d = 0; d < LocationName.size(); d++) {
                if (LocationName.get(d) != 'NULL') {
                    GlobalVariable.label = LocationName.get(d)

                    println(GlobalVariable.label)

                    WebUI.setText(findTestObject('XXXXXXXXXXXX/Web Part/FocusBrand(MSL)/LocationFilterField'), GlobalVariable.label)

                    WebUI.delay(2)

                    checkbox_type = WebUI.getAttribute(findTestObject('XXXXXXXXXXXX/Web Part/FocusBrand(MSL)/SkuCheckBox_td_Tag(Global)'), 
                        'src', FailureHandling.STOP_ON_FAILURE)

                    unchecked_checkbox = findTestData('VBL_Web Input Data/MustSell').getValue('Verify', 1)

                    if (checkbox_type == unchecked_checkbox) {
                        'Entered location check box need to be enable'
                        WebUI.takeScreenshot()

                        WebUI.click(findTestObject('XXXXXXXXXXXX/Web Part/FocusBrand(MSL)/SkuCheckBox_td_Tag(Global)'))
                    }
                    
                    'Entered location check box already enabled'
                    WebUI.takeScreenshot()

                    WebUI.click(findTestObject('XXXXXXXXXXXX/Web Part/FocusBrand(MSL)/td_Tag(GridCode)'))
                }
            }
            
            WebUI.click(findTestObject('XXXXXXXXXXXX/Web Part/FocusBrand(MSL)/Finish_Btn'))

            WebUI.verifyElementPresent(findTestObject('XXXXXXXXXXXX/Web Part/FocusBrand(MSL)/SaveSuccessful_PopUp'), 5)

            WebUI.takeScreenshot()
        }
        
        println('INV_22 To verify user can able to create mustsell')
    }
}

WebUI.closeBrowser()

