import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.chrome.ChromeDriver as ChromeDriver
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.keyword.excel.ExcelKeywords as ExcelKeywords
import internal.GlobalVariable as GlobalVariable
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import com.kms.katalon.core.mobile.keyword.internal.MobileDriverFactory as MobileDriverFactory
import io.appium.java_client.android.AndroidDriver as AndroidDriver
//import io.appium.java_client.MobileElement as MobileElement
import io.appium.java_client.android.nativekey.AndroidKey as AndroidKey
import io.appium.java_client.android.nativekey.KeyEvent as KeyEvent
import io.appium.java_client.AppiumDriver as AppiumDriver

Mobile.startApplication(GlobalVariable.APKFile, false)

Mobile.tap(findTestObject('Mobile/Common/Btn_Menu'), 10)
	
if(Mobile.verifyElementExist(findTestObject('Mobile/MainMenu/Menu_End of the Day'), 5, FailureHandling.OPTIONAL))
{
	'menu is loading'
}
else {
	Mobile.callTestCase(findTestCase('Product_Mobile/Common/Mobile_Login/Relaunch the app'), [:], FailureHandling.CONTINUE_ON_FAILURE)
	
		Mobile.tap(findTestObject('Mobile/Common/Btn_Menu'), 0)
		
		
}
Mobile.tap(findTestObject('Mobile/MainMenu/Menu_End of the Day'), 0)

Mobile.tap(findTestObject('Mobile/End of the Day/SubMenu_Van Unload'), 0)

Mobile.delay(2)

Mobile.comment('Validation empty return products loading in van unload screen')

GlobalVariable.ProductName = findTestData('Mobile Input Data/Empty_Return').getValue('SKU_Name', 1)

Mobile.verifyElementExist(findTestObject('Object Repository/Mobile/Mobile Part/End of the Day/Van Unload/New/Vanunload_Product_Name'), 5, FailureHandling.STOP_ON_FAILURE)

GlobalVariable.ProductName = findTestData('Mobile Input Data/Empty_Return').getValue('SKU_Name', 2)

Mobile.verifyElementExist(findTestObject('Object Repository/Mobile/Mobile Part/End of the Day/Van Unload/New/Vanunload_Product_Name'), 5, FailureHandling.STOP_ON_FAILURE)

GlobalVariable.ProductName = findTestData('Mobile Input Data/Empty_Return').getValue('Salable_SKU_Name', 1)

Mobile.verifyElementExist(findTestObject('Object Repository/Mobile/Mobile Part/End of the Day/Van Unload/New/Vanunload_Product_Name'), 5, FailureHandling.STOP_ON_FAILURE)

GlobalVariable.ProductName = findTestData('Mobile Input Data/Empty_Return').getValue('Salable_SKU_Name', 2)

Mobile.verifyElementExist(findTestObject('Object Repository/Mobile/Mobile Part/End of the Day/Van Unload/New/Vanunload_Product_Name'), 5, FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

Mobile.tap(findTestObject('Object Repository/Mobile/Mobile Part/End of the Day/Van Unload/New/Search_icon'), 0)

Mobile.setText(findTestObject('Mobile/Mobile Part/End of the Day/Van Unload/New/Product_search_field'), findTestData('Mobile Input Data/Empty_Return').getValue('SKU_Name', 1), 0)

SIH1 = Mobile.getText(findTestObject('Mobile/Mobile Part/End of the Day/Van Unload/New/SIH_Get_qty'), 0)

KeywordUtil.logInfo('SIH for first prodcut :' + SIH1)

NonSalable_Qty = Mobile.getText(findTestObject('Mobile/Mobile Part/End of the Day/Van Unload/New/NS_get_qty'), 0)

KeywordUtil.logInfo('Non salable qty  :' + NonSalable_Qty)

Mobile.comment('validation for empty return quantity correctly loading in SIH and NS column ')

Piece_Qty1=findTestData('Mobile Input Data/Empty_Return').getValue('Buy_Qty', 3)
Piece_Qty2=findTestData('Mobile Input Data/Empty_Return').getValue('Quantity', 1)

Total_Piece = Integer.parseInt(Piece_Qty1) + Integer.parseInt(Piece_Qty2)

Mobile.verifyEqual(Total_Piece, Integer.parseInt(SIH1), FailureHandling.STOP_ON_FAILURE)

Ns = findTestData('Mobile Input Data/Empty_Return').getValue('Quantity', 2)

Mobile.verifyEqual(Integer.parseInt(Ns), Integer.parseInt(NonSalable_Qty), FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

Mobile.tap(findTestObject('Object Repository/Mobile/Mobile Part/End of the Day/Van Unload/New/Search_clear_btn'), 5, FailureHandling.OPTIONAL)

Mobile.tap(findTestObject('Object Repository/Mobile/Mobile Part/End of the Day/Van Unload/New/Search_icon'), 0)

Mobile.setText(findTestObject('Mobile/Mobile Part/End of the Day/Van Unload/New/Product_search_field'), findTestData('Mobile Input Data/Empty_Return').getValue('SKU_Name', 2), 0)

SIH2 = Mobile.getText(findTestObject('Mobile/Mobile Part/End of the Day/Van Unload/New/SIH_Get_qty'), 0)

KeywordUtil.logInfo('SIH for second prodcut :' + SIH2)

SKU2_Piece_Qty1=findTestData('Mobile Input Data/Empty_Return').getValue('Buy_Qty', 4)
SKU2_Piece_Qty2=findTestData('Mobile Input Data/Empty_Return').getValue('Quantity', 3)

Product2_Total_Piece_qty = Integer.parseInt(SKU2_Piece_Qty1) + Integer.parseInt(SKU2_Piece_Qty2)

Mobile.verifyEqual((Product2_Total_Piece_qty), Integer.parseInt(SIH2), FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

//Salable SKU1
Mobile.tap(findTestObject('Object Repository/Mobile/Mobile Part/End of the Day/Van Unload/New/Search_clear_btn'), 5, FailureHandling.OPTIONAL)

Mobile.tap(findTestObject('Object Repository/Mobile/Mobile Part/End of the Day/Van Unload/New/Search_icon'), 0)

Mobile.setText(findTestObject('Mobile/Mobile Part/End of the Day/Van Unload/New/Product_search_field'), findTestData('Mobile Input Data/Empty_Return').getValue('Salable_SKU_Name', 1), 0)

Salable_SKU1_SIH = Mobile.getText(findTestObject('Mobile/Mobile Part/End of the Day/Van Unload/New/SIH_Get_qty'), 0)

KeywordUtil.logInfo('SIH for Salable prodcut :' + Salable_SKU1_SIH)

Salable_SKU1_Piece=findTestData('Mobile Input Data/Empty_Return').getValue('Current_Stock', 1)

Mobile.verifyEqual(Integer.parseInt(Salable_SKU1_SIH), Integer.parseInt(Salable_SKU1_Piece), FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()
//Salable SKU2
Mobile.tap(findTestObject('Object Repository/Mobile/Mobile Part/End of the Day/Van Unload/New/Search_clear_btn'), 5, FailureHandling.OPTIONAL)

Mobile.tap(findTestObject('Object Repository/Mobile/Mobile Part/End of the Day/Van Unload/New/Search_icon'), 0)

Mobile.setText(findTestObject('Mobile/Mobile Part/End of the Day/Van Unload/New/Product_search_field'), findTestData('Mobile Input Data/Empty_Return').getValue('Salable_SKU_Name', 2), 0)

Salable_SKU2_SIH = Mobile.getText(findTestObject('Mobile/Mobile Part/End of the Day/Van Unload/New/SIH_Get_qty'), 0)

KeywordUtil.logInfo('SIH for Salable prodcut :' + Salable_SKU2_SIH)

Salable_SKU2_Piece=findTestData('Mobile Input Data/Empty_Return').getValue('Current_Stock', 2)

Mobile.verifyEqual(Integer.parseInt(Salable_SKU2_SIH), Integer.parseInt(Salable_SKU2_Piece), FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

Mobile.tap(findTestObject('Object Repository/Mobile/Mobile Part/End of the Day/Van Unload/New/Search_clear_btn'), 5, FailureHandling.OPTIONAL)

Mobile.tap(findTestObject('Object Repository/Mobile/Mobile Part/End of the Day/Van Unload/New/Search_icon'), 0)

Mobile.setText(findTestObject('Mobile/Mobile Part/End of the Day/Van Unload/New/Product_search_field'), findTestData('Mobile Input Data/Empty_Return').getValue('SKU_Name', 2), 0)


Mobile.comment('Validation for able to enter more than SIH')

H_qty = ((Product2_Total_Piece_qty) + '1')

KeywordUtil.logInfo('Added SIH qty :' + H_qty)

Mobile.tap(findTestObject('Object Repository/Mobile/Mobile Part/End of the Day/Van Unload/New/piece_enter_field'), 0)

Mobile.setText(findTestObject('Object Repository/Mobile/Mobile Part/End of the Day/Van Unload/New/piece_enter_field'), H_qty, 0)

AppiumDriver driver = MobileDriverFactory.getDriver()

Exceed_Alert = driver.findElementByXPath('//android.widget.Toast[1]').getText()

KeywordUtil.logInfo('Exceed Alert msg :' + Exceed_Alert)


//need to update
//Mobile.verifyMatch(Exceed_Alert, findTestData('Mobile Input Data/Empty_Return').getValue('Alert_Msg', 1), false)


//Empty SKU001

Mobile.tap(findTestObject('Mobile/Mobile Part/End of the Day/Van Unload/New/Search_clear_btn'), 0, FailureHandling.OPTIONAL)

Mobile.tap(findTestObject('Mobile/Mobile Part/End of the Day/Van Unload/New/Search_icon'), 0)

Mobile.setText(findTestObject('Mobile/Mobile Part/End of the Day/Van Unload/New/Product_search_field'), findTestData('Mobile Input Data/Empty_Return').getValue('SKU_Name', 2), 0)

not_run: Mobile.clearText(findTestObject('Mobile/Mobile Part/End of the Day/Van Unload/New/Product_search_field'), 0)

EN_qty1 = Mobile.getText(findTestObject('Mobile/Mobile Part/End of the Day/Van Unload/New/SIH_Get_qty'), 0)

Mobile.setText(findTestObject('Mobile/Mobile Part/End of the Day/Van Unload/New/piece_enter_field'), EN_qty1, 0)

Mobile.delay(2)

//Mobile.tap(findTestObject('Mobile/Mobile Part/End of the Day/Van Unload/New/Search_clear_btn'), 0, FailureHandling.OPTIONAL)
//
//Mobile.tap(findTestObject('Mobile/Mobile Part/End of the Day/Van Unload/New/Search_icon'), 0)

Mobile.clearText(findTestObject('Mobile/Mobile Part/End of the Day/Van Unload/New/Product_search_field'), 0, FailureHandling.OPTIONAL)

Mobile.setText(findTestObject('Mobile/Mobile Part/End of the Day/Van Unload/New/Product_search_field'), findTestData('Mobile Input Data/Empty_Return').getValue('SKU_Name', 1), 0)

EN_qty2 = Mobile.getText(findTestObject('Mobile/Mobile Part/End of the Day/Van Unload/New/SIH_Get_qty'), 0)

Mobile.setText(findTestObject('Mobile/Mobile Part/End of the Day/Van Unload/New/piece_enter_field'), EN_qty2, 0)

Mobile.takeScreenshot()
//Salable sku 001
Mobile.tap(findTestObject('Mobile/Mobile Part/End of the Day/Van Unload/New/Search_clear_btn'), 0, FailureHandling.OPTIONAL)

Mobile.tap(findTestObject('Mobile/Mobile Part/End of the Day/Van Unload/New/Search_icon'), 0)

Mobile.setText(findTestObject('Mobile/Mobile Part/End of the Day/Van Unload/New/Product_search_field'), findTestData('Mobile Input Data/Empty_Return').getValue('Salable_SKU_Name', 1), 0)

not_run: Mobile.clearText(findTestObject('Mobile/Mobile Part/End of the Day/Van Unload/New/Product_search_field'), 0)

EN_qty3 = Mobile.getText(findTestObject('Mobile/Mobile Part/End of the Day/Van Unload/New/SIH_Get_qty'), 0)

Mobile.setText(findTestObject('Mobile/Mobile Part/End of the Day/Van Unload/New/piece_enter_field'), EN_qty3, 0)

Mobile.delay(2)
//Salable Sku002
Mobile.tap(findTestObject('Mobile/Mobile Part/End of the Day/Van Unload/New/Search_clear_btn'), 0, FailureHandling.OPTIONAL)

Mobile.tap(findTestObject('Mobile/Mobile Part/End of the Day/Van Unload/New/Search_icon'), 0)

Mobile.setText(findTestObject('Mobile/Mobile Part/End of the Day/Van Unload/New/Product_search_field'), findTestData('Mobile Input Data/Empty_Return').getValue('Salable_SKU_Name', 2), 0)


EN_qty4 = Mobile.getText(findTestObject('Mobile/Mobile Part/End of the Day/Van Unload/New/SIH_Get_qty'), 0)

Mobile.setText(findTestObject('Mobile/Mobile Part/End of the Day/Van Unload/New/piece_enter_field'), EN_qty4, 0)

Mobile.delay(2)

Mobile.tap(findTestObject('Mobile/Common/Icon_X'), 2,FailureHandling.OPTIONAL)

Overall_SIH = Mobile.getText(findTestObject('Mobile/Mobile Part/End of the Day/Van Unload/New/Total_SIH_get_bottom'), 0)

Cal_Total_SIH = (Integer.parseInt(SIH1))+(Integer.parseInt(SIH2))+(Integer.parseInt(Salable_SKU1_SIH))+(Integer.parseInt(Salable_SKU2_SIH))

Mobile.verifyEqual(Integer.parseInt(Overall_SIH),Cal_Total_SIH,FailureHandling.STOP_ON_FAILURE)
Mobile.takeScreenshot()

Overall_Case = Mobile.getText(findTestObject('Mobile/Mobile Part/End of the Day/Van Unload/New/Total_case_get_bottom'), 0)

Overall_Piece = Mobile.getText(findTestObject('Mobile/Mobile Part/End of the Day/Van Unload/New/Total_Piece_get_bottom'), 0)

Mobile.tap(findTestObject('Mobile/Mobile Part/Reusable Object/Save_btn'), 0)

Mobile.comment('Validation for save button functionality')

Unload_Savedmsg = driver.findElementByXPath('//android.widget.Toast[1]').getText()

Mobile.takeScreenshot()

KeywordUtil.logInfo('Unload saved msg :' + Unload_Savedmsg)
//------

Mobile.callTestCase(findTestCase('Test Cases/Product_Mobile/Empty_Return/ER_10'), [:], FailureHandling.STOP_ON_FAILURE)