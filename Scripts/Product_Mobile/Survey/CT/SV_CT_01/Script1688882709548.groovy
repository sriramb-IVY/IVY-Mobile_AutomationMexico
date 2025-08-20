import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import javax.xml.bind.annotation.XmlElementDecl.GLOBAL as GLOBAL
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

WebUI.callTestCase(findTestCase('Product_Mobile/Common/Web Login and Navigation/DivisionLogin'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('Product_Mobile/Common/Web Login and Navigation/Navigate to Question Entry'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.waitForElementNotVisible(findTestObject('Web Part/Re-usable/Page Load'), 100)

WebUI.delay(2)

WebUI.click(findTestObject('Web Part/Survey/Short_description_field'))

WebUI.setText(findTestObject('Web Part/Survey/Short_description_field'), findTestData('Web Input Data/Survey').getValue(
        'Question', 1))

WebUI.delay(2)

WebUI.takeScreenshot()

GlobalVariable.label = findTestData('Web Input Data/Survey').getValue('Question', 1)

if (WebUI.verifyElementVisible(findTestObject('Object Repository/Web Part/Survey/1st_row'), FailureHandling.OPTIONAL)) {
    WebUI.takeScreenshot()

    WebUI.verifyElementVisible(findTestObject('Object Repository/Web Part/Survey/1st_row'), FailureHandling.STOP_ON_FAILURE)
} else {
    WebUI.takeScreenshot()

    WebUI.click(findTestObject('Object Repository/Web Part/Survey/Add_icon'))

    WebUI.waitForElementNotVisible(findTestObject('Web Part/Re-usable/Page Load'), 100)

    WebUI.delay(2)

    WebUI.click(findTestObject('Object Repository/Web Part/Survey/Short_description_field_edit'))

    WebUI.setText(findTestObject('Object Repository/Web Part/Survey/Short_description_field_edit'), findTestData(
            'Web Input Data/Survey').getValue('Question', 1))

    WebUI.click(findTestObject('Object Repository/Web Part/Survey/Description_field'))

    WebUI.setText(findTestObject('Object Repository/Web Part/Survey/Description_field'), findTestData('Web Input Data/Survey').getValue(
            'Question', 1))

    WebUI.click(findTestObject('Web Part/Survey/Photo_Mandatory_cbox'))

    WebUI.click(findTestObject('Web Part/Survey/Mandatory_cbox'))

    WebUI.click(findTestObject('Web Part/Survey/Question_type_dropdown'))

    WebUI.scrollToElement(findTestObject('Web Part/Survey/Multiple_select_option'), 2)

    WebUI.click(findTestObject('Web Part/Survey/Multiple_select_option'))

    WebUI.click(findTestObject('Web Part/Survey/Answer_field'))

    WebUI.setText(findTestObject('Web Part/Survey/Answer_field'), findTestData('Web Input Data/Survey').getValue(
            'Answer_type', 1))

    WebUI.takeScreenshot()

    WebUI.click(findTestObject('Web Part/Survey/Plus_icon'))

    WebUI.click(findTestObject('Web Part/Survey/Answer_field'))

    WebUI.setText(findTestObject('Web Part/Survey/Answer_field'), findTestData('Web Input Data/Survey').getValue(
            'Answer_type', 2))

    WebUI.click(findTestObject('Web Part/Survey/Plus_icon'))

    WebUI.takeScreenshot()

    WebUI.click(findTestObject('Web Part/Survey/Save_btn'))
}

WebUI.refresh()

WebUI.callTestCase(findTestCase('Product_Mobile/Common/Web Login and Navigation/Navigate to Survey Configuration'), 
    [:], FailureHandling.STOP_ON_FAILURE)

WebUI.waitForElementNotVisible(findTestObject('Web Part/Re-usable/Page Load'), 100)

WebUI.delay(2)

WebUI.click(findTestObject('Web Part/Survey/Short_description_field'))

WebUI.setText(findTestObject('Web Part/Survey/Short_description_field'), findTestData('Web Input Data/Survey').getValue(
        'Objective', 1))

WebUI.delay(2)

WebUI.takeScreenshot()

GlobalVariable.label = findTestData('Web Input Data/Survey').getValue('Objective', 1)

if (WebUI.verifyElementVisible(findTestObject('Object Repository/Web Part/Survey/1st_row'), FailureHandling.OPTIONAL)) {
    WebUI.takeScreenshot()

    WebUI.verifyElementVisible(findTestObject('Object Repository/Web Part/Survey/1st_row'), FailureHandling.STOP_ON_FAILURE)

    WebUI.click(findTestObject('Object Repository/Web Part/Survey/1st_row'))

    WebUI.click(findTestObject('Object Repository/Web Part/Product_Hierarchy/Edit_Btn'))

    WebUI.click(findTestObject('Web Part/Survey/Configuration/Effective_from'))

    WebUI.callTestCase(findTestCase('Product_Mobile/Common/DatePicker_and _Navigation/Date Picker - Current'), [:], 
        FailureHandling.STOP_ON_FAILURE)

    WebUI.delay(2)

    WebUI.click(findTestObject('Web Part/Survey/Configuration/Next_btn'))

    WebUI.waitForElementNotVisible(findTestObject('Web Part/Re-usable/Page Load'), 100)

    WebUI.takeScreenshot()

    WebUI.click(findTestObject('Web Part/Survey/Configuration/Save and proceed btn'))

    WebUI.waitForElementNotVisible(findTestObject('Web Part/Re-usable/Page Load'), 100)

    WebUI.click(findTestObject('Web Part/Survey/Configuration/Create_new_mapping_icon'))

    WebUI.waitForElementNotVisible(findTestObject('Web Part/Re-usable/Page Load'), 100)

    WebUI.click(findTestObject('Web Part/Survey/Configuration/Sup_login'))

    WebUI.delay(2)

    WebUI.click(findTestObject('Web Part/Survey/Configuration/Code_field'))

    WebUI.setText(findTestObject('Web Part/Survey/Configuration/Code_field'), findTestData('Web Input Data/Survey').getValue(
            'Seller_name', 2))

    WebUI.delay(2)

    WebUI.takeScreenshot()

    WebUI.click(findTestObject('Web Part/Survey/Configuration/Code_checkbox'))

    WebUI.click(findTestObject('Web Part/Survey/Configuration/Save_criteria_btn'))

    WebUI.takeScreenshot()

    WebUI.closeBrowser()
} else {
    WebUI.takeScreenshot()

    WebUI.click(findTestObject('Web Part/Survey/Add_icon'))

    WebUI.click(findTestObject('Web Part/Survey/Configuration/Objective_field'))

    WebUI.setText(findTestObject('Web Part/Survey/Configuration/Objective_field'), findTestData('Web Input Data/Survey').getValue(
            'Objective', 1))

    WebUI.takeScreenshot()

    WebUI.click(findTestObject('Web Part/Survey/Configuration/Remarks_field'))

    WebUI.setText(findTestObject('Web Part/Survey/Configuration/Remarks_field'), findTestData('Web Input Data/Survey').getValue(
            'Objective', 1))

    WebUI.click(findTestObject('Web Part/Survey/Configuration/Effective_from'))

    WebUI.callTestCase(findTestCase('Product_Mobile/Common/DatePicker_and _Navigation/Date Picker - Current'), [:], 
        FailureHandling.STOP_ON_FAILURE)

    WebUI.delay(2)

    WebUI.scrollToElement(findTestObject('Web Part/Survey/Configuration/Signature_checkbox'), 5)

    WebUI.click(findTestObject('Web Part/Survey/Configuration/Signature_checkbox'))

    GlobalVariable.label = findTestData('Web Input Data/Survey').getValue('Mandatory', 1)

    WebUI.click(findTestObject('Object Repository/Web Part/Survey/Configuration/Mandatory_Field'))

    WebUI.click(findTestObject('Object Repository/Web Part/Re-usable/Global/Span_Tag(Dropdown)'))

    WebUI.click(findTestObject('Web Part/Survey/Configuration/Next_btn'))

    WebUI.waitForElementNotVisible(findTestObject('Web Part/Re-usable/Page Load'), 100)

    WebUI.click(findTestObject('Web Part/Survey/Short_description_field'))

    WebUI.setText(findTestObject('Web Part/Survey/Short_description_field'), findTestData('Web Input Data/Survey').getValue(
            'Question', 1))

    WebUI.delay(2)

    WebUI.click(findTestObject('Web Part/Survey/Configuration/Question_checkbox'))

    WebUI.takeScreenshot()

    WebUI.click(findTestObject('Web Part/Survey/Configuration/Save and proceed btn'))

    WebUI.waitForElementNotVisible(findTestObject('Web Part/Re-usable/Page Load'), 100)

    WebUI.click(findTestObject('Web Part/Survey/Configuration/Create_new_mapping_icon'))

    WebUI.waitForElementNotVisible(findTestObject('Web Part/Re-usable/Page Load'), 100)

    WebUI.click(findTestObject('Web Part/Survey/Configuration/Sup_login'))

    WebUI.delay(2)

    WebUI.click(findTestObject('Web Part/Survey/Configuration/Code_field'))

    WebUI.setText(findTestObject('Web Part/Survey/Configuration/Code_field'), findTestData('Web Input Data/Survey').getValue(
            'Seller_name', 2))

    WebUI.delay(2)

    WebUI.takeScreenshot()

    WebUI.click(findTestObject('Web Part/Survey/Configuration/Code_checkbox'))

    WebUI.click(findTestObject('Web Part/Survey/Configuration/Save_criteria_btn'))

    WebUI.takeScreenshot()

    WebUI.closeBrowser()
}

