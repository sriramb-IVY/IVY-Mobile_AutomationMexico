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

Mobile.startApplication(GlobalVariable.APKFile, false)

Mobile.tap(findTestObject('Mobile/Common/Btn_Menu'), 10)

if (Mobile.verifyElementExist(findTestObject('Mobile/MainMenu/Menu_End of the Day'), 5, FailureHandling.OPTIONAL)) {
    'menu is loading'
} else {
    Mobile.callTestCase(findTestCase('Product_Mobile/Common/Mobile_Login/Relaunch the app'), [:], FailureHandling.CONTINUE_ON_FAILURE)

    Mobile.tap(findTestObject('Mobile/Common/Btn_Menu'), 0)
}

Mobile.tap(findTestObject('Mobile/Seller_2/Empty_Return_report/Reports_menu'), 0)

Mobile.waitForElementPresent(findTestObject('Mobile/Seller_2/Empty_Return_report/Empty_Return_Report_Menu'), 0, FailureHandling.STOP_ON_FAILURE)

Mobile.tap(findTestObject('Mobile/Seller_2/Empty_Return_report/Empty_Return_Report_Menu'), 0)

Mobile.delay(2)

Return_qty1 = findTestData('Mobile Input Data/EmptyReturn_Validation').getValue('ER_Return_Qty', 1)

Return_qty2 = findTestData('Mobile Input Data/EmptyReturn_Validation').getValue('ER_Return_Qty', 2)

Return_price1 = findTestData('Mobile Input Data/EmptyReturn_Validation').getValue('SO_Empty_Price', 1)

Return_price2 = findTestData('Mobile Input Data/EmptyReturn_Validation').getValue('SO_Empty_Price', 2)

return_amount1 = (Double.parseDouble(Return_qty1) * Double.parseDouble(Return_price1))

return_amount2 = (Double.parseDouble(Return_qty2) * Double.parseDouble(Return_price2))

Total_Return_amt = (return_amount1 + return_amount2)

GlobalVariable.label = Total_Return_amt

Return_id = Mobile.getText(findTestObject('Mobile/Seller_2/Empty_Return_report/Order_Id_get'), 0, FailureHandling.STOP_ON_FAILURE)

println(Return_id)

Mobile.tap(findTestObject('Mobile/Seller_2/Empty_Return_report/Order_Amount'), 0)

Mobile.verifyElementExist(findTestObject('Mobile/Seller_2/Empty_Return_report/EmptyReturnDetails_Title'), 0, FailureHandling.STOP_ON_FAILURE)

GlobalVariable.label = findTestData('Mobile Input Data/Empty_Return').getValue('SKU_Name', 1)

SKU1_qty = Mobile.getText(findTestObject('Mobile/Seller_2/Empty_Return_report/Return_qty'), 0, FailureHandling.STOP_ON_FAILURE)

println(SKU1_qty)

SKU1_price = Mobile.getText(findTestObject('Mobile/Seller_2/Empty_Return_report/Return_value'), 0, FailureHandling.STOP_ON_FAILURE)

println(SKU1_price)

SKU1_Net = Mobile.getText(findTestObject('Mobile/Seller_2/Empty_Return_report/Net_Value'), 0, FailureHandling.STOP_ON_FAILURE)

println(SKU1_Net)

Mobile.verifyMatch(SKU1_qty, Return_qty1, false, FailureHandling.STOP_ON_FAILURE)

Mobile.verifyMatch(SKU1_price, Return_price1, false, FailureHandling.STOP_ON_FAILURE)

Mobile.verifyEqual(Double.parseDouble(SKU1_Net), return_amount1, FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

GlobalVariable.label = findTestData('Mobile Input Data/Empty_Return').getValue('SKU_Name', 2)

SKU2_qty = Mobile.getText(findTestObject('Mobile/Seller_2/Empty_Return_report/Return_qty'), 0, FailureHandling.STOP_ON_FAILURE)

println(SKU2_qty)

SKU2_price = Mobile.getText(findTestObject('Mobile/Seller_2/Empty_Return_report/Return_value'), 0, FailureHandling.STOP_ON_FAILURE)

println(SKU2_price)

SKU2_Net = Mobile.getText(findTestObject('Mobile/Seller_2/Empty_Return_report/Net_Value'), 0, FailureHandling.STOP_ON_FAILURE)

println(SKU2_Net)

Mobile.verifyMatch(SKU2_qty, Return_qty2, false, FailureHandling.STOP_ON_FAILURE)

Mobile.verifyMatch(SKU2_price, Return_price2, false, FailureHandling.STOP_ON_FAILURE)

Mobile.verifyEqual(Double.parseDouble(SKU2_Net), return_amount2, FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

Total_lines = Mobile.getText(findTestObject('Mobile/Seller_2/Empty_Return_report/Total_Lines_get'), 0, FailureHandling.STOP_ON_FAILURE)

println(Total_lines)

Total_return_value = Mobile.getText(findTestObject('Mobile/Seller_2/Empty_Return_report/Total_value_details_screen'), 0, FailureHandling.STOP_ON_FAILURE)

println(Total_return_value)

Mobile.verifyEqual(Double.parseDouble(Total_return_value), Total_Return_amt, FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

Mobile.tap(findTestObject('Mobile/Common/Icon_Back'), 0)

Overall_return_qty = Mobile.getText(findTestObject('Mobile/Seller_2/Empty_Return_report/Total_return_qty'), 0, FailureHandling.STOP_ON_FAILURE)

println(Overall_return_qty)

Overall_return_Value = Mobile.getText(findTestObject('Mobile/Seller_2/Empty_Return_report/Total_return_Value'), 0, FailureHandling.STOP_ON_FAILURE)

println(Overall_return_Value)

Mobile.takeScreenshot()

