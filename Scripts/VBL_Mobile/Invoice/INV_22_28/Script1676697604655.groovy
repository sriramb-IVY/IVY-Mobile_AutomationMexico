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

Mobile.callTestCase(findTestCase('VBL_Mobile/Invoice/Reusable/Create_MustSell_CT'), [:], FailureHandling.CONTINUE_ON_FAILURE)

Mobile.callTestCase(findTestCase('VBL_Mobile/Invoice/Reusable/Create_FocusBrand_CT'), [:], FailureHandling.CONTINUE_ON_FAILURE)

Mobile.callTestCase(findTestCase('VBL_Mobile/Invoice/Reusable/Get Sku_Price_Detail_CT'), [('ProductName') : findTestData(
            'VBL_Mobile Input Data/Invoice').getValue('Product_Name', 1), ('Index') : 0], FailureHandling.CONTINUE_ON_FAILURE)

Mobile.callTestCase(findTestCase('VBL_Mobile/Invoice/Reusable/Get_CGST_Tax_CT'), [('ProductName') : findTestData('VBL_Mobile Input Data/Invoice').getValue(
            'Product_Name', 1), ('Index') : 1], FailureHandling.CONTINUE_ON_FAILURE)

Mobile.callTestCase(findTestCase('VBL_Mobile/Re-Usables/Job Run-TC'), [:], FailureHandling.CONTINUE_ON_FAILURE)

