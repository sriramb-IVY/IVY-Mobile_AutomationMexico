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
//import io.appium.java_client.MobileElement as MobileElement
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.keyword.excel.ExcelKeywords as ExcelKeywords

Mobile.startApplication(GlobalVariable.APKFile, false)


Mobile.tap(findTestObject('Mobile/MainMenu/Menu_Load Management'), 0)

Mobile.tap(findTestObject('Mobile/LoadManagement/SubMenu_Current Stock view'), 0)

Mobile.verifyElementExist(findTestObject('Mobile/LoadManagement/CurrentStockView/Title_Current Stock view'), 0)

WebUI.callTestCase(findTestCase('Product_Mobile/Common/Search_Product'), [:], FailureHandling.STOP_ON_FAILURE)

Mobile.setText(findTestObject('Mobile/Common/Field_Search_EnterText'), findTestData('Mobile Input Data/Empty_Return').getValue('Salable_SKU_Name', 1), 0)

First_productname = Mobile.getText(findTestObject('Mobile/LoadManagement/CurrentStockView/Product_Name'), 0)

Product1_SIH = Mobile.getText(findTestObject('Mobile/LoadManagement/CurrentStockView/SIH_Value'), 0)

Product1_Total = Mobile.getText(findTestObject('Mobile/LoadManagement/CurrentStockView/Total'), 0, FailureHandling.STOP_ON_FAILURE)

Mobile.verifyMatch(First_productname, findTestData('Mobile Input Data/Empty_Return').getValue('Salable_SKU_Name', 1), false)
SIH_1=findTestData('Mobile Input Data/Empty_Return').getValue('Buy_Qty', 1)
SIH_2=findTestData('Mobile Input Data/Empty_Return').getValue('Buy_Qty', 3)

qty=findTestData('Web Input Data/Empties').getValue('case_Quantity', 1)

Conversion=findTestData('Web Input Data/Empties').getValue('Conversion_Qty', 1)

Order_Qty=Integer.parseInt(qty)*  Integer.parseInt(Conversion)

Current_SIH =(Integer.parseInt(SIH_1))+(Integer.parseInt(SIH_2))

Cal_SIH=((Order_Qty))-((Current_SIH))

Mobile.verifyEqual(Integer.parseInt(Product1_SIH),Cal_SIH,FailureHandling.STOP_ON_FAILURE)

piece_price = findTestData('Mobile Input Data/Empty_Return').getValue('Piece_Price', 1)

Total_cal = (Double.parseDouble(Product1_SIH) * Double.parseDouble(piece_price))

Mobile.verifyEqual(Double.parseDouble(Product1_Total), Total_cal, FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

//Product2
WebUI.callTestCase(findTestCase('Product_Mobile/Common/Search_Product'), [:], FailureHandling.STOP_ON_FAILURE)

Mobile.setText(findTestObject('Mobile/Common/Field_Search_EnterText'), findTestData('Mobile Input Data/Empty_Return').getValue('Salable_SKU_Name', 2), 0)

Second_productname = Mobile.getText(findTestObject('Mobile/LoadManagement/CurrentStockView/Product_Name'), 0)

Product2_SIH = Mobile.getText(findTestObject('Mobile/LoadManagement/CurrentStockView/SIH_Value'), 0)

Product2_Total = Mobile.getText(findTestObject('Mobile/LoadManagement/CurrentStockView/Total'), 0, FailureHandling.STOP_ON_FAILURE)

Mobile.verifyMatch(Second_productname, findTestData('Mobile Input Data/Empty_Return').getValue('Salable_SKU_Name', 2), false)

SIH_3=findTestData('Mobile Input Data/Empty_Return').getValue('Buy_Qty', 2)
SIH_4=findTestData('Mobile Input Data/Empty_Return').getValue('Buy_Qty', 4)

qty1=findTestData('Web Input Data/Empties').getValue('case_Quantity', 2)

Conversion1=findTestData('Web Input Data/Empties').getValue('Conversion_Qty', 2)

Order_Qty1=Integer.parseInt(qty1)*  Integer.parseInt(Conversion1)

SIH1 =(Integer.parseInt(SIH_3))+(Integer.parseInt(SIH_4))

Cal_SIH1=((Order_Qty1))-((SIH1))

Mobile.verifyEqual(Integer.parseInt(Product2_SIH),Cal_SIH1,FailureHandling.STOP_ON_FAILURE)

piece_price2 = findTestData('Mobile Input Data/Empty_Return').getValue('Piece_Price', 2)

Total_cal2 = (Double.parseDouble(Product2_SIH) * Double.parseDouble(piece_price2))

Mobile.verifyEqual(Double.parseDouble(Product2_Total), Total_cal2, FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

Mobile.tap(findTestObject('Mobile/Common/Icon_X'), 2,FailureHandling.OPTIONAL)

exlpath = 'DDF\\Mobile Input data\\Mobile Input data.xlsx'

Sheetname = 'Empty_Return'

workbook01 = ExcelKeywords.getWorkbook(exlpath)

sheet1 = ExcelKeywords.getExcelSheet(workbook01, Sheetname)

ExcelKeywords.setValueToCellByIndex(sheet1, 1, 15, Cal_SIH)
ExcelKeywords.setValueToCellByIndex(sheet1, 2, 15, Cal_SIH1)
ExcelKeywords.saveWorkbook(exlpath, workbook01)

Total_value = Mobile.getText(findTestObject('Mobile/LoadManagement/CurrentStockView/TotalValue'), 0)

KeywordUtil.logInfo('Overall value : ' + Total_value)

Overcal = (Total_cal + Total_cal2)

KeywordUtil.logInfo('Overall Calculation value : ' + Overcal)

Mobile.verifyEqual(Double.parseDouble(Total_value), Overcal, FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

Mobile.tap(findTestObject('Mobile/Common/Icon_Back'), 2, FailureHandling.OPTIONAL)

Mobile.tap(findTestObject('Mobile/Common/Btn_Menu'), 2, FailureHandling.OPTIONAL)

