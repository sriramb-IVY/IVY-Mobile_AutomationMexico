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
import java.text.SimpleDateFormat as SimpleDateFormat

String file_name = 'Web Input Data'

String sheet_name = 'Task_Management'

ArrayList<String> Title_Name = CustomKeywords.'poi.Automation.GetAllWebDataRow_Flag.getAllDataRow'(file_name, sheet_name, 
    'Title_Name')

ArrayList<String> Title_Description = CustomKeywords.'poi.Automation.GetAllWebDataRow_Flag.getAllDataRow'(file_name, sheet_name, 
    'Title_Description')

ArrayList<String> Execution_Type = CustomKeywords.'poi.Automation.GetAllWebDataRow_Flag.getAllDataRow'(file_name, sheet_name, 
    'Execution_Type')

ArrayList<String> Seller = CustomKeywords.'poi.Automation.GetAllWebDataRow_Flag.getAllDataRow'(file_name, sheet_name, 'Seller')

ArrayList<String> Route = CustomKeywords.'poi.Automation.GetAllWebDataRow_Flag.getAllDataRow'(file_name, sheet_name, 'Route')

ArrayList<String> Retailer = CustomKeywords.'poi.Automation.GetAllWebDataRow_Flag.getAllDataRow'(file_name, sheet_name, 
    'Retailer')

ArrayList<String> AssignTo = CustomKeywords.'poi.Automation.GetAllWebDataRow_Flag.getAllDataRow'(file_name, sheet_name, 
    'AssignTo')

'Create and config task'
Mobile.callTestCase(findTestCase('Product_Mobile/Common/Web Login and Navigation/DivisionLogin'), [:], FailureHandling.STOP_ON_FAILURE)

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Web Login and Navigation/Navigate to Task Create'), [:], FailureHandling.STOP_ON_FAILURE)

'Verify CPG_OneTime and CPG_Routine Task Creation'
for (i = 0; i < Title_Name.size(); i++) {
    WebUI.click(findTestObject('Object Repository/Web Part/Seller Task/Task_Name_Search_Field'))

    GlobalVariable.Name = Title_Name.get(i)

    WebUI.setText(findTestObject('Object Repository/Web Part/Seller Task/Task_Name_Search_Field'), GlobalVariable.Name)

    WebUI.delay(2)

    WebUI.takeScreenshot()

    if (WebUI.verifyElementPresent(findTestObject('Web Part/Seller Task/Task_grid'), 3, FailureHandling.OPTIONAL)) {
        'Expected Task Already Present so, Verify the Edit functionality'
        WebUI.takeScreenshot()

        WebUI.click(findTestObject('Web Part/Seller Task/Task_grid'))

        WebUI.delay(1)

        WebUI.click(findTestObject('Web Part/Seller Task/Edit button'))

        WebUI.waitForElementNotVisible(findTestObject('Web Part/Re-usable/Page Load'), 100)

        'Verify Edit Screen!'
        WebUI.takeScreenshot()

        GlobalVariable.label = findTestData('Web Input Data/Task_Management').getValue('Verify_Data', 1)

        WebUI.verifyElementVisible(findTestObject('Web Part/Seller Task/PopUp_Screen_Header(Global)'), FailureHandling.STOP_ON_FAILURE)

        WebUI.takeScreenshot()

        WebUI.delay(2)

        'Verify Green Alert Popup for Edit Screen!'
        WebUI.click(findTestObject('Web Part/Seller Task/Save Creation'))

        WebUI.takeScreenshot()

        GlobalVariable.label = findTestData('Web Input Data/Task_Management').getValue('Verify_Data', 3)
		
		WebUI.verifyElementPresent(findTestObject('Web Part/Seller Task/GreenAlert_Global)'), 10, FailureHandling.STOP_ON_FAILURE)

       // WebUI.verifyElementVisible(findTestObject('Web Part/Seller Task/GreenAlert_Global)'), FailureHandling.STOP_ON_FAILURE)

       

        WebUI.takeScreenshot()
    } else {
        'Expected Task Not Presented so, Verify Add functionality and add task!'
        WebUI.takeScreenshot()

        WebUI.click(findTestObject('Web Part/Seller Task/Add button'))

        'Verify Add Screen!'
        WebUI.takeScreenshot()

        GlobalVariable.label = findTestData('Web Input Data/Task_Management').getValue('Verify_Data', 1)

        WebUI.verifyElementVisible(findTestObject('Web Part/Seller Task/PopUp_Screen_Header(Global)'), FailureHandling.STOP_ON_FAILURE)

        WebUI.takeScreenshot()

        'Add New Task Name'
        WebUI.click(findTestObject('Web Part/Seller Task/Title Name'))

        WebUI.sendKeys(findTestObject('Web Part/Seller Task/Title Name'), Title_Name.get(i))

        WebUI.click(findTestObject('Web Part/Seller Task/PopUp_Screen_Header(Global)'))

        WebUI.takeScreenshot()

        Entered_TaskName = WebUI.getAttribute(findTestObject('Web Part/Seller Task/Title Name'), 'value')

        WebUI.verifyMatch(Entered_TaskName, Title_Name.get(i), false, FailureHandling.STOP_ON_FAILURE)

        KeywordUtil.logInfo('Entered_TaskName properly displaying : ' + Entered_TaskName)

        WebUI.takeScreenshot()

        'Add New Task Description'
        WebUI.click(findTestObject('Web Part/Seller Task/Description'))

        WebUI.sendKeys(findTestObject('Web Part/Seller Task/Description'), Title_Description.get(i))

        WebUI.click(findTestObject('Web Part/Seller Task/PopUp_Screen_Header(Global)'))

        WebUI.takeScreenshot()

        Entered_TaskDesc = WebUI.getAttribute(findTestObject('Web Part/Seller Task/Description'), 'value')

        WebUI.verifyMatch(Entered_TaskDesc, Title_Description.get(i), false, FailureHandling.STOP_ON_FAILURE)

        KeywordUtil.logInfo('Entered_Task Description properly displaying : ' + Entered_TaskDesc)

        WebUI.takeScreenshot()

        'Add New Task Execution Type'
        WebUI.click(findTestObject('Web Part/Seller Task/Execution Type_Dropdown'))

        GlobalVariable.label1 = Execution_Type.get(i)

        KeywordUtil.logInfo('Selected Task Execution type is : ' + GlobalVariable.label1)

        WebUI.waitForElementVisible(findTestObject('Web Part/Seller Task/Li_tag(Global)'), 10)

        WebUI.click(findTestObject('Web Part/Seller Task/Li_tag(Global)'))

        WebUI.takeScreenshot()

        Entered_Task_ExecutionType = WebUI.getAttribute(findTestObject('Web Part/Seller Task/Execution Type_Dropdown'), 
            'value')

        WebUI.verifyMatch(Entered_Task_ExecutionType, Execution_Type.get(i), false, FailureHandling.STOP_ON_FAILURE)

        KeywordUtil.logInfo('Entered_Task Execution Type properly displaying : ' + Entered_Task_ExecutionType)

        WebUI.takeScreenshot()

        'Verify Green Alert Popup while Add new task in the add Screen!'
        WebUI.click(findTestObject('Web Part/Seller Task/Save Creation'))

        WebUI.takeScreenshot()

        GlobalVariable.label = findTestData('Web Input Data/Task_Management').getValue('Verify_Data', 2)

        WebUI.verifyElementVisible(findTestObject('Web Part/Seller Task/GreenAlert_Global)'), FailureHandling.STOP_ON_FAILURE)

        WebUI.verifyElementPresent(findTestObject('Web Part/Seller Task/GreenAlert_Global)'), 10, FailureHandling.STOP_ON_FAILURE)

        WebUI.takeScreenshot()
    }
}

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Web Login and Navigation/Navigate to Task Config'), [:], FailureHandling.STOP_ON_FAILURE)

'verify the task configuration'
for (i = 0; i < Title_Name.size(); i++) {
    WebUI.click(findTestObject('Web Part/Seller Task/Task Title Search Field(index-2)'))

    WebUI.setText(findTestObject('Web Part/Seller Task/Task Title Search Field(index-2)'), Title_Name.get(
            i))

    GlobalVariable.Name = Title_Name.get(i)

    if (WebUI.verifyElementPresent(findTestObject('Web Part/Seller Task/Task_grid'), 3, FailureHandling.OPTIONAL)) {
        WebUI.delay(2)

        'Expected Task Config Already Present so, verify the Edit functionality '
        WebUI.takeScreenshot()

        WebUI.click(findTestObject('Web Part/Seller Task/Task_grid'))

        WebUI.delay(2)

        WebUI.click(findTestObject('Web Part/Seller Task/Edit button'))

        if (WebUI.verifyElementPresent(findTestObject('Object Repository/Web Part/Seller Task/AssignTo_Dropdown'), 
            5, FailureHandling.OPTIONAL)) {
            WebUI.click(findTestObject('Object Repository/Web Part/Seller Task/AssignTo_Dropdown'))

            GlobalVariable.label = AssignTo.get(i)

            WebUI.click(findTestObject('Object Repository/Web Part/Re-usable/Global/Span_Tag(Dropdown)'))
        }
        
        WebUI.click(findTestObject('Web Part/Seller Task/Task Title Search Field'))

        GlobalVariable.Name = Title_Name.get(i)

        WebUI.setText(findTestObject('Web Part/Seller Task/Task Title Search Field'), GlobalVariable.Name, 
            FailureHandling.STOP_ON_FAILURE)

        WebUI.click(findTestObject('Web Part/Seller Task/Task_grid'))

        WebUI.takeScreenshot()

        WebUI.scrollToElement(findTestObject('Web Part/Seller Task/Save and proceed'), 3)

        WebUI.click(findTestObject('Web Part/Seller Task/Save and proceed'), FailureHandling.STOP_ON_FAILURE)

        WebUI.waitForElementNotVisible(findTestObject('Web Part/Re-usable/Page Load'), 100)

        WebUI.waitForElementVisible(findTestObject('Object Repository/Web Part/Seller Task/UserSearchField'), 
            10, FailureHandling.STOP_ON_FAILURE)

        WebUI.takeScreenshot()

        'User should select the seller'
        WebUI.click(findTestObject('Object Repository/Web Part/Seller Task/UserSearchField'))

        WebUI.setText(findTestObject('Object Repository/Web Part/Seller Task/UserSearchField'), Seller.get(
                i), FailureHandling.STOP_ON_FAILURE)

        WebUI.delay(2)

        GlobalVariable.label = Seller.get(i)

        not_run: WebUI.scrollToElement(findTestObject('Object Repository/Web Part/Seller Task/Select_UserName(Global)'), 
            10)

        WebUI.click(findTestObject('Object Repository/Web Part/Seller Task/Select_UserName(Global)'))

        WebUI.waitForElementNotVisible(findTestObject('Web Part/Re-usable/Page Load'), 100)

        WebUI.takeScreenshot()

        'User should select route'
        WebUI.click(findTestObject('Object Repository/Web Part/Seller Task/Route_Dropdown'))

        GlobalVariable.label = Route.get(i)

        WebUI.click(findTestObject('Object Repository/Web Part/Re-usable/Global/Span_Tag(Dropdown)'))

        WebUI.waitForElementNotVisible(findTestObject('Web Part/Re-usable/Page Load'), 100)

        WebUI.takeScreenshot()

        'User should select retailer'
        WebUI.click(findTestObject('Object Repository/Web Part/Seller Task/Store_Name_Search_Field'))

        GlobalVariable.label = Retailer.get(i)

        WebUI.setText(findTestObject('Object Repository/Web Part/Seller Task/Store_Name_Search_Field'), GlobalVariable.label)

        WebUI.delay(2)

        WebUI.click(findTestObject('Object Repository/Web Part/Re-usable/Global/td_Tag(GridSkuRow)'))

        WebUI.takeScreenshot()

        if (WebUI.verifyElementPresent(findTestObject('Object Repository/Web Part/Seller Task/Store_CheckBox_Enabled'), 
            3, FailureHandling.OPTIONAL)) {
            'Store/retailer checked box already enabled!'
            WebUI.verifyElementVisible(findTestObject('Object Repository/Web Part/Seller Task/Store_CheckBox_Enabled'), 
                FailureHandling.STOP_ON_FAILURE)

            WebUI.takeScreenshot()
        } else {
            WebUI.takeScreenshot()

            'Store/retailer checked box not enabled! so now we need to enable!'
            WebUI.click(findTestObject('Object Repository/Web Part/Seller Task/Store_CheckBox_TapToEnable'))

            WebUI.delay(2)

            WebUI.verifyElementVisible(findTestObject('Object Repository/Web Part/Seller Task/Store_CheckBox_Enabled'), 
                FailureHandling.STOP_ON_FAILURE)

            WebUI.takeScreenshot()
        }
        
        WebUI.waitForElementVisible(findTestObject('Web Part/Seller Task/Save Creation'), 10)

        'Verify Green Alert Popup while Edit the task config!'
        WebUI.click(findTestObject('Web Part/Seller Task/Save Creation'))

        WebUI.takeScreenshot()

        GlobalVariable.label = findTestData('Web Input Data/Task_Management').getValue('Verify_Data', 5)

        WebUI.verifyElementVisible(findTestObject('Web Part/Seller Task/GreenAlert_Global)'), FailureHandling.STOP_ON_FAILURE)

        WebUI.verifyElementPresent(findTestObject('Web Part/Seller Task/GreenAlert_Global)'), 10, FailureHandling.STOP_ON_FAILURE)

        WebUI.takeScreenshot()

        WebUI.delay(2)

        WebUI.click(findTestObject('Object Repository/Web Part/Seller Task/Return_btn'))
    } else {
        WebUI.takeScreenshot()

        'Expected Task Config not present so, user need to add task config and verify the Add functionality '
        WebUI.click(findTestObject('Web Part/Seller Task/Add button'))

        WebUI.waitForElementNotVisible(findTestObject('Web Part/Re-usable/Page Load'), 100)

        WebUI.takeScreenshot()

        'Enter the data in the mandatory fields'
        WebUI.click(findTestObject('Web Part/Seller Task/Start date'))

        WebUI.callTestCase(findTestCase('Product_Mobile/Common/DatePicker_and _Navigation/Date Picker'), [('DateValue') : 'Today'], 
            FailureHandling.STOP_ON_FAILURE)

        'User should enter and validate (start date) because it is mandatory field'
        Actual_start_date_ = WebUI.getAttribute(findTestObject('Web Part/Seller Task/Start date'), 'value', 
            FailureHandling.STOP_ON_FAILURE)

        String pattern = 'dd/MM/yyyy'

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern)

        String Expected_currentdate = simpleDateFormat.format(new Date())

        KeywordUtil.logInfo(Expected_currentdate)

        WebUI.verifyMatch(Actual_start_date_, Expected_currentdate, false, FailureHandling.STOP_ON_FAILURE)

        WebUI.takeScreenshot()

        'User should enter and validate (AssignTo) field because it is mandatory field'
        WebUI.click(findTestObject('Object Repository/Web Part/Seller Task/AssignTo_Dropdown'))

        GlobalVariable.label = AssignTo.get(i)

        WebUI.click(findTestObject('Object Repository/Web Part/Re-usable/Global/Span_Tag(Dropdown)'))

        WebUI.delay(3)

        Actual_AssignTo = WebUI.getAttribute(findTestObject('Object Repository/Web Part/Seller Task/AssignTo_Dropdown'), 
            'value', FailureHandling.STOP_ON_FAILURE)

        WebUI.verifyMatch(Actual_AssignTo, AssignTo.get(i), false, FailureHandling.STOP_ON_FAILURE)

        WebUI.takeScreenshot()

        'User should select the Task'
        WebUI.click(findTestObject('Object Repository/Web Part/Seller Task/Task Title Search Field'))

        GlobalVariable.Name = Title_Name.get(i)

        WebUI.setText(findTestObject('Object Repository/Web Part/Seller Task/Task Title Search Field'), GlobalVariable.Name, 
            FailureHandling.STOP_ON_FAILURE)

        WebUI.click(findTestObject('Web Part/Seller Task/Task_grid'))

        WebUI.takeScreenshot()

        WebUI.scrollToElement(findTestObject('Web Part/Seller Task/Save and proceed'), 3)

        WebUI.verifyElementPresent(findTestObject('Web Part/Seller Task/Save and proceed'), 3)

        WebUI.click(findTestObject('Web Part/Seller Task/Save and proceed'))

        WebUI.waitForElementNotVisible(findTestObject('Web Part/Re-usable/Page Load'), 100)

        WebUI.waitForElementVisible(findTestObject('Object Repository/Web Part/Seller Task/UserSearchField'), 
            10, FailureHandling.STOP_ON_FAILURE)

        WebUI.takeScreenshot()

        'User entered all mandatory fields in task selection screen so screen navigate to next (criteria selection screen)'
        WebUI.click(findTestObject('Object Repository/Web Part/Seller Task/UserSearchField'))

        'User should select the seller'
        WebUI.setText(findTestObject('Object Repository/Web Part/Seller Task/UserSearchField'), Seller.get(
                i), FailureHandling.STOP_ON_FAILURE)

        WebUI.delay(2)

        GlobalVariable.label = Seller.get(i)

        not_run: WebUI.scrollToElement(findTestObject('Object Repository/Web Part/Seller Task/Select_UserName(Global)'), 
            10)

        WebUI.click(findTestObject('Object Repository/Web Part/Seller Task/Select_UserName(Global)'))

        WebUI.waitForElementNotVisible(findTestObject('Web Part/Re-usable/Page Load'), 100)

        WebUI.takeScreenshot()

        'User should select route'
        WebUI.click(findTestObject('Object Repository/Web Part/Seller Task/Route_Dropdown'))

        GlobalVariable.label = Route.get(i)

        WebUI.click(findTestObject('Object Repository/Web Part/Re-usable/Global/Span_Tag(Dropdown)'))

        WebUI.waitForElementNotVisible(findTestObject('Web Part/Re-usable/Page Load'), 100)

        WebUI.takeScreenshot()

        'User should select retailer'
        WebUI.click(findTestObject('Object Repository/Web Part/Seller Task/Store_Name_Search_Field'))

        GlobalVariable.label = Retailer.get(i)

        WebUI.setText(findTestObject('Object Repository/Web Part/Seller Task/Store_Name_Search_Field'), GlobalVariable.label)

        WebUI.delay(2)

        WebUI.click(findTestObject('Object Repository/Web Part/Re-usable/Global/td_Tag(GridSkuRow)'))

        WebUI.takeScreenshot()

        if (WebUI.verifyElementPresent(findTestObject('Object Repository/Web Part/Seller Task/Store_CheckBox_Enabled'), 
            3, FailureHandling.OPTIONAL)) {
            'Store/retailer checked box already enabled!'
            WebUI.verifyElementVisible(findTestObject('Object Repository/Web Part/Seller Task/Store_CheckBox_Enabled'), 
                FailureHandling.STOP_ON_FAILURE)

            WebUI.takeScreenshot()
        } else {
            WebUI.takeScreenshot()

            'Store/retailer checked box not enabled! so now we need to enable!'
            WebUI.click(findTestObject('Object Repository/Web Part/Seller Task/Store_CheckBox_TapToEnable'))

            WebUI.delay(2)

            WebUI.verifyElementVisible(findTestObject('Object Repository/Web Part/Seller Task/Store_CheckBox_Enabled'), 
                FailureHandling.STOP_ON_FAILURE)

            WebUI.takeScreenshot()
        }
        
        WebUI.waitForElementVisible(findTestObject('Web Part/Seller Task/Save Creation'), 10)

        'Verify Green Alert Popup while Add the task config!'
        WebUI.click(findTestObject('Web Part/Seller Task/Save Creation'))

        WebUI.takeScreenshot()

        GlobalVariable.label = findTestData('Web Input Data/Task_Management').getValue('Verify_Data', 4)

        WebUI.verifyElementVisible(findTestObject('Web Part/Seller Task/GreenAlert_Global)'), FailureHandling.STOP_ON_FAILURE)

        WebUI.verifyElementPresent(findTestObject('Web Part/Seller Task/GreenAlert_Global)'), 10, FailureHandling.STOP_ON_FAILURE)

        WebUI.takeScreenshot()

        WebUI.delay(2)

        WebUI.click(findTestObject('Object Repository/Web Part/Seller Task/Return_btn'))
    }
}

