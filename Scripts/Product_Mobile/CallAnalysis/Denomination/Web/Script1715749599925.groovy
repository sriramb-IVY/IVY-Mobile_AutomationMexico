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

String sheet_name = 'Collection'

String file_name = 'Mobile Input Data'

ArrayList<String> Field_name = CustomKeywords.'poi.Automation.GetAllDataRow_Flag.getAllDataRow'(file_name, sheet_name, 'Field_name')

ArrayList<String> Entered_Value = CustomKeywords.'poi.Automation.GetAllDataRow_Flag.getAllDataRow'(file_name, sheet_name, 'Entered_Value')

ArrayList<String> Entered_Amt = CustomKeywords.'poi.Automation.GetAllDataRow_Flag.getAllDataRow'(file_name, sheet_name, 'Entered_Amt')

for (a = 0; a < Entered_Value.size(); a++) {
    println(Entered_Value.get(a))

    if (Entered_Value.get(a) != '') {
       // println(a)

        GlobalVariable.label = Field_name.get(a)
		
		println(GlobalVariable.label)

       count =  WebUI.getText(findTestObject('Web Part/Collection Acceptance/Count_Field(global)'), FailureHandling.STOP_ON_FAILURE)

        WebUI.verifyEqual(Integer.parseInt(count), null)

        value = WebUI.getText(findTestObject('Web Part/Collection Acceptance/Value_Field(global)'), FailureHandling.STOP_ON_FAILURE)

        WebUI.verifyEqual(Double.parseDouble(value), null)
    }
}

