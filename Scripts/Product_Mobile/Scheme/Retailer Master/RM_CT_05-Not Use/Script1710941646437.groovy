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

//Mobile.callTestCase(findTestCase('Product_Mobile/Common/Web Login and Navigation/DivisionLogin'), [:], FailureHandling.STOP_ON_FAILURE)

String sheet_name = 'Scheme_CriteriaMapping'

String file_name = 'Mobile Input data'

ArrayList<String> BuyProduct1 = CustomKeywords.'poi.Automation.GetAllDataRow_Flag.getAllDataRow'(file_name, sheet_name, 
    'BuyProduct1')

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Web Login and Navigation/Navigate to Product Hierarchy'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.waitForElementNotVisible(findTestObject('Web Part/Re-usable/Page Load'), 100)

WebUI.click(findTestObject('Web Part/Product_Hierarchy/SKU_SubMenu'))

WebUI.waitForElementNotVisible(findTestObject('Web Part/Re-usable/Page Load'), 100)

WebUI.waitForElementVisible(findTestObject('Web Part/Product_Hierarchy/Sku_Filter_Field'), 50, FailureHandling.STOP_ON_FAILURE)

WebUI.setText(findTestObject('Web Part/Product_Hierarchy/Sku_Filter_Field'), BuyProduct1.get(GlobalVariable.index))

WebUI.waitForElementNotVisible(findTestObject('Web Part/Re-usable/Page Load'), 100)

GlobalVariable.label = BuyProduct1.get(GlobalVariable.index)

WebUI.delay(2)

WebUI.click(findTestObject('Web Part/TaxScreen/Global Object/td_Tag(GridSkuRow)'))

WebUI.click(findTestObject('Web Part/Product_Hierarchy/Edit_Btn'))

WebUI.waitForElementNotVisible(findTestObject('Web Part/Re-usable/Page Load'), 100)

GlobalVariable.Category_name = WebUI.getAttribute(findTestObject('Web Part/Product_Hierarchy/Get_Category'), 'value')

KeywordUtil.logInfo('Category name of the product is - '+ GlobalVariable.Category_name)

