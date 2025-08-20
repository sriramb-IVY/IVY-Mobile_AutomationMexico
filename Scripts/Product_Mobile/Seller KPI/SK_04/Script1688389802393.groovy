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

not_run: Mobile.callTestCase(findTestCase('Product_Mobile/Common/Mobile_Login/PreSeller_Login'), [:], FailureHandling.STOP_ON_FAILURE)

not_run: Mobile.delay(2, FailureHandling.STOP_ON_FAILURE)

Mobile.startApplication(GlobalVariable.APKFile, false)

Mobile.delay(10, FailureHandling.STOP_ON_FAILURE)

Mobile.verifyElementVisible(findTestObject('Product/Re-usable/Mobile Login/Menu_Icon'), 50)

Mobile.tap(findTestObject('Product/Re-usable/Mobile Login/Menu_Icon'), 0)

Mobile.delay(2, FailureHandling.STOP_ON_FAILURE)

Mobile.swipe(0, 1300, 0, 530, FailureHandling.OPTIONAL)

Mobile.delay(2, FailureHandling.STOP_ON_FAILURE)

Mobile.tap(findTestObject('Product/Mobile Part/Seller_1/DashBoard_KPI/DashBoard_KPI_menu'), 0)

Mobile.delay(2, FailureHandling.STOP_ON_FAILURE)

SV_Target = Mobile.getText(findTestObject('Product/Mobile Part/Seller_1/DashBoard_KPI/SalesValue_Target_get'), 0)

After_Achievement = Mobile.getText(findTestObject('Product/Mobile Part/Seller_1/DashBoard_KPI/SalesValue_Achieved_get'), 
    0)

After_SV_Balance = Mobile.getText(findTestObject('Product/Mobile Part/Seller_1/DashBoard_KPI/Salesvalue_Balance_get'), 0)

Calculated_Balance = (Double.parseDouble(SV_Target) - Double.parseDouble(After_Achievement))

Mobile.verifyEqual(GlobalVariable.Sum, Double.parseDouble(After_Achievement), FailureHandling.STOP_ON_FAILURE)

Mobile.verifyEqual(Calculated_Balance, Double.parseDouble(After_SV_Balance), FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

LPC_Achievement = Mobile.getText(findTestObject('Product/Mobile Part/Seller_1/DashBoard_KPI/Line_Per_Call_Achieved_get'), 
    0)

LPC_Balance = Mobile.getText(findTestObject('Product/Mobile Part/Seller_1/DashBoard_KPI/Line_Per_Call_Balance_get'), 0)

Mobile.verifyEqual(GlobalVariable.Sum1, Double.parseDouble(LPC_Achievement), FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()



