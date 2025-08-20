import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.configuration.RunConfiguration as RunConfiguration
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable
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

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Web Login and Navigation/DivisionLogin'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('Product_Mobile/Common/Web Login and Navigation/Navigate to Digital Content Master'), 
    [:], FailureHandling.STOP_ON_FAILURE)

WebUI.waitForElementNotVisible(findTestObject('Web Part/Re-usable/Page Load'), 100)

String sheet_name = 'Digital Content'

String file_name = 'Web Input Data'

ArrayList<String> Name = CustomKeywords.'poi.Automation.GetAllWebDataRow_Flag.getAllDataRow'(file_name, sheet_name, 'Name')

ArrayList<String> Short_Desc = CustomKeywords.'poi.Automation.GetAllWebDataRow_Flag.getAllDataRow'(file_name, sheet_name, 
    'Short_Desc')

ArrayList<String> Long_Desc = CustomKeywords.'poi.Automation.GetAllWebDataRow_Flag.getAllDataRow'(file_name, sheet_name, 
    'Long_Desc')

ArrayList<String> Alerts = CustomKeywords.'poi.Automation.GetAllWebDataRow_Flag.getAllDataRow'(file_name, sheet_name, 'Alerts')

ArrayList<String> File_Type = CustomKeywords.'poi.Automation.GetAllWebDataRow_Flag.getAllDataRow'(file_name, sheet_name, 
    'File_Type')

for (int i = 0; i < Name.size(); i++) {
    GlobalVariable.Filename = Name.get(i)

    WebUI.setText(findTestObject('Web Part/Digital Content/Digital Content screen/Search file name'), GlobalVariable.Filename)

    if (WebUI.verifyElementPresent(findTestObject('Web Part/Digital Content/Global variables/td_tag (FileName)'), 
        3, FailureHandling.OPTIONAL)) {
        println('File Already uploaded!')
    } else {
        println('File Not Already uploaded so, we need to upload new file!')

        WebUI.sendKeys(findTestObject('Web Part/Digital Content/Digital Content screen/Name'), GlobalVariable.Filename)

        WebUI.sendKeys(findTestObject('Web Part/Digital Content/Digital Content screen/short description'), 
            Short_Desc.get(i))

        WebUI.sendKeys(findTestObject('Web Part/Digital Content/Digital Content screen/Full description'), 
            Long_Desc.get(i))

        WebUI.waitForElementPresent(findTestObject('Web Part/Digital Content/Digital Content screen/upload file'), 
            10)

        if (i == 0) {
            WebUI.uploadFile(findTestObject('Web Part/Digital Content/Digital Content screen/upload file'), 
                RunConfiguration.getProjectDir() + GlobalVariable.Image)

            WebUI.click(findTestObject('Web Part/Digital Content/Digital Content screen/FileUpload-Btn'))

            WebUI.waitForElementNotVisible(findTestObject('Web Part/Re-usable/Page Load'), 100)

            FilesavedAlert = WebUI.getText(findTestObject('Web Part/Digital Content/Digital Content screen/File upload successfully'))

            println(FilesavedAlert)

            WebUI.verifyMatch(FilesavedAlert, findTestData('Web Input Data/Digital Content').getValue('Alerts', 1), false)

            FilesavedAlert = WebUI.getText(findTestObject('Web Part/Digital Content/Digital Content screen/File upload successfully'))

            println(FilesavedAlert)

            WebUI.verifyMatch(FilesavedAlert, findTestData('Web Input Data/Digital Content').getValue('Alerts', 1), false)

            WebUI.takeScreenshot()

            WebUI.setText(findTestObject('Web Part/Digital Content/Digital Content screen/Search file name'), 
                GlobalVariable.Filename)
        }
        
        if (i == 1) {
            WebUI.uploadFile(findTestObject('Web Part/Digital Content/Digital Content screen/upload file'), 
                RunConfiguration.getProjectDir() + GlobalVariable.pdf)

            WebUI.click(findTestObject('Web Part/Digital Content/Digital Content screen/FileUpload-Btn'))

            WebUI.waitForElementNotVisible(findTestObject('Web Part/Re-usable/Page Load'), 100)

            FilesavedAlert = WebUI.getText(findTestObject('Web Part/Digital Content/Digital Content screen/File upload successfully'))

            println(FilesavedAlert)

            WebUI.verifyMatch(FilesavedAlert, findTestData('Web Input Data/Digital Content').getValue('Alerts', 1), false)

            FilesavedAlert = WebUI.getText(findTestObject('Web Part/Digital Content/Digital Content screen/File upload successfully'))

            println(FilesavedAlert)

            WebUI.verifyMatch(FilesavedAlert, findTestData('Web Input Data/Digital Content').getValue('Alerts', 1), false)

            WebUI.takeScreenshot()

            WebUI.setText(findTestObject('Web Part/Digital Content/Digital Content screen/Search file name'), 
                GlobalVariable.Filename)
        }
        
        if (i == 2) {
            WebUI.uploadFile(findTestObject('Web Part/Digital Content/Digital Content screen/upload file'), 
                RunConfiguration.getProjectDir() + GlobalVariable.Video)

            WebUI.click(findTestObject('Web Part/Digital Content/Digital Content screen/FileUpload-Btn'))

            WebUI.waitForElementNotVisible(findTestObject('Web Part/Re-usable/Page Load'), 100)

            FilesavedAlert = WebUI.getText(findTestObject('Web Part/Digital Content/Digital Content screen/File upload successfully'))

            println(FilesavedAlert)

            WebUI.verifyMatch(FilesavedAlert, findTestData('Web Input Data/Digital Content').getValue('Alerts', 1), false)

            FilesavedAlert = WebUI.getText(findTestObject('Web Part/Digital Content/Digital Content screen/File upload successfully'))

            println(FilesavedAlert)

            WebUI.verifyMatch(FilesavedAlert, findTestData('Web Input Data/Digital Content').getValue('Alerts', 1), false)

            WebUI.takeScreenshot()

            WebUI.setText(findTestObject('Web Part/Digital Content/Digital Content screen/Search file name'), 
                GlobalVariable.Filename)
        }
    }
    
    WebUI.verifyElementPresent(findTestObject('Web Part/Digital Content/Global variables/td_tag (FileName)'), 
        3, FailureHandling.STOP_ON_FAILURE)

    WebUI.verifyElementText(findTestObject('Web Part/Digital Content/Global variables/td_tag (FileName)'), 
        GlobalVariable.Filename, FailureHandling.STOP_ON_FAILURE)

    println(GlobalVariable.Filename + '  : This is the File Name')

    GlobalVariable.label = File_Type.get(i)

    WebUI.verifyElementPresent(findTestObject('Web Part/Digital Content/Global variables/td_tag (Grid Value)'), 
        3, FailureHandling.STOP_ON_FAILURE)

    WebUI.verifyElementText(findTestObject('Web Part/Digital Content/Global variables/td_tag (Grid Value)'), 
        File_Type.get(i), FailureHandling.STOP_ON_FAILURE)

    println(File_Type.get(i) + '  : This is the File format')

    WebUI.takeScreenshot()
}

WebUI.callTestCase(findTestCase('Product_Mobile/Common/Web Login and Navigation/Navigate to Digital content Mapping'), 
    [:], FailureHandling.STOP_ON_FAILURE)

GlobalVariable.label = findTestData('Web Input Data/Digital Content').getValue('Type', 1)

WebUI.setText(findTestObject('Web Part/Digital Content/Digital Content mapping/Criteria search'), GlobalVariable.label)

if (WebUI.verifyElementPresent(findTestObject('Web Part/Digital Content/Global variables/td_tag (Grid Value)'), 
    3, FailureHandling.OPTIONAL)) {
    println('Criteria Type is present as "user" ')

    WebUI.click(findTestObject('Web Part/Digital Content/Global variables/td_tag (Grid Value)'))

    WebUI.click(findTestObject('Web Part/Digital Content/Digital Content mapping/Edit'))

    WebUI.click(findTestObject('Web Part/Digital Content/Digital Content mapping/Effective to'))

    WebUI.callTestCase(findTestCase('Product_Mobile/Common/DatePicker_and _Navigation/Date Picker'), [('DateValue') : 'Today'], 
        FailureHandling.STOP_ON_FAILURE)

    WebUI.click(findTestObject('Web Part/Digital Content/Digital Content mapping/save and proceed'))

    WebUI.waitForElementNotVisible(findTestObject('Web Part/Re-usable/Page Load'), 100)

    //WebUI.click(findTestObject('Web Part/Digital Content/Digital Content mapping/SearchField-(Distributor)'))
    GlobalVariable.label = findTestData('Web Input Data/Digital Content').getValue('Distributor', 1)

    //    WebUI.setText(findTestObject('Web Part/Digital Content/Digital Content mapping/SearchField-(Distributor)'), 
    //        GlobalVariable.label)
    WebUI.delay(2)

    WebUI.click(findTestObject('Web Part/Digital Content/Global variables/td_tag (Grid Value)'))

    WebUI.waitForElementNotVisible(findTestObject('Web Part/Re-usable/Page Load'), 100)

    for (int j = 0; j < Name.size(); j++) {
        GlobalVariable.Filename = Name.get(j)

        WebUI.setText(findTestObject('Web Part/Digital Content/Digital Content mapping/FileName-FilterField(Mappig)'), 
            GlobalVariable.Filename)

        checkbox_type = WebUI.getAttribute(findTestObject('Web Part/Digital Content/Global variables/td_tag (Check Box)'), 
            'src', FailureHandling.STOP_ON_FAILURE)

        unchecked_checkbox = findTestData('Web Input Data/Digital Content').getValue('Verify', 1)

        if (checkbox_type == unchecked_checkbox) {
            println(GlobalVariable.Filename)

            WebUI.click(findTestObject('Web Part/Digital Content/Global variables/td_tag (Check Box)'))
        }
    }
    
    WebUI.click(findTestObject('Web Part/Digital Content/Digital Content mapping/finish'))

    GlobalVariable.label = findTestData('Web Input Data/Digital Content').getValue('Alerts', 3)

    WebUI.verifyElementPresent(findTestObject('Web Part/Digital Content/Global variables/div tag_Alert verify'), 
        2)

    WebUI.verifyElementText(findTestObject('Web Part/Digital Content/Global variables/div tag_Alert verify'), 
        GlobalVariable.label)

    WebUI.takeScreenshot()
} else {
    println('Criteria Type is not present as "user" ')

    WebUI.click(findTestObject('Web Part/Digital Content/Digital Content mapping/Add'))

    WebUI.click(findTestObject('Web Part/Digital Content/Digital Content mapping/Effective to'))

    WebUI.callTestCase(findTestCase('Product_Mobile/Common/DatePicker_and _Navigation/Date Picker'), [('DateValue') : 'Today'], 
        FailureHandling.STOP_ON_FAILURE)

    WebUI.click(findTestObject('Web Part/Digital Content/Digital Content mapping/Criteria type'))

    WebUI.click(findTestObject('Web Part/Digital Content/Global variables/span_tag_userSelect'))

    WebUI.click(findTestObject('Web Part/Digital Content/Digital Content mapping/save and proceed'))

    WebUI.waitForElementNotVisible(findTestObject('Web Part/Re-usable/Page Load'), 100)

    WebUI.click(findTestObject('Web Part/Digital Content/Digital Content mapping/SearchField-(Distributor)'))

    GlobalVariable.label = findTestData('Web Input Data/Digital Content').getValue('Distributor', 1)

    WebUI.setText(findTestObject('Web Part/Digital Content/Digital Content mapping/SearchField-(Distributor)'), 
        GlobalVariable.label)

    WebUI.delay(2)

    WebUI.click(findTestObject('Web Part/Digital Content/Global variables/td_tag (Grid Value)'))

    WebUI.waitForElementNotVisible(findTestObject('Web Part/Re-usable/Page Load'), 100)

    for (int k = 0; k < Name.size(); k++) {
        GlobalVariable.Filename = Name.get(k)

        WebUI.setText(findTestObject('Web Part/Digital Content/Digital Content mapping/FileName-FilterField(Mappig)'), 
            GlobalVariable.Filename)

        checkbox_type = WebUI.getAttribute(findTestObject('Web Part/Digital Content/Global variables/td_tag (Check Box)'), 
            'src', FailureHandling.STOP_ON_FAILURE)

        unchecked_checkbox = findTestData('Web Input Data/Digital Content').getValue('Verify', 1)

        if (checkbox_type == unchecked_checkbox) {
            WebUI.click(findTestObject('Web Part/Digital Content/Global variables/td_tag (Check Box)'))
        }
    }
    
    WebUI.click(findTestObject('Web Part/Digital Content/Digital Content mapping/finish'))

    GlobalVariable.label = findTestData('Web Input Data/Digital Content').getValue('Alerts', 4)

    WebUI.verifyElementPresent(findTestObject('Web Part/Digital Content/Global variables/div tag_Alert verify'), 
        2)

    WebUI.verifyElementText(findTestObject('Web Part/Digital Content/Global variables/div tag_Alert verify'), 
        GlobalVariable.label)

    WebUI.takeScreenshot()
}

WebUI.closeBrowser()


