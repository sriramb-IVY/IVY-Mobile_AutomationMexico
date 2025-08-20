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

//Mobile.startApplication(GlobalVariable.APKFile, false)
//Mobile.delay(40)
//
//Mobile.tap(findTestObject('Mobile/Common/Btn_Menu'),10)
//Mobile.callTestCase(findTestCase('Product_Mobile/Common/Mobile_Login/VanSeller_Login-With Reset'), [:], FailureHandling.STOP_ON_FAILURE)

if (Mobile.verifyElementExist(findTestObject('Mobile/Store_Actvy/Menu-SalesReturn'), 2, FailureHandling.OPTIONAL)) {
    'SalesReturn menu visible'
} else {
    Mobile.callTestCase(findTestCase('Product_Mobile/Common/Mobile_Login/Relaunch the app'), [:], FailureHandling.CONTINUE_ON_FAILURE)

    Mobile.tap(findTestObject('Mobile/Common/Btn_Menu'), 0)

    GlobalVariable.RetailerName = findTestData('Mobile Input Data/CallAnalysis').getValue('Retailer_Name', 1)

    Mobile.tap(findTestObject('Mobile/MainMenu/Menu_Trade Coverage'), 0)

    Mobile.tap(findTestObject('Mobile/Common/Icon_Search'), 5)

    Mobile.setText(findTestObject('Mobile/Common/Field_Search_EnterText'), GlobalVariable.RetailerName, 
        5)

    Mobile.tap(findTestObject('Mobile/TradeCoverage/First_Retailer_Selection_From_List'), 5)

    Mobile.tap(findTestObject('Mobile/Common/Btn_StartVisit'), 5)

    if (Mobile.verifyElementVisible(findTestObject('Object Repository/Mobile/Common/Popup_Title_LocationValidation'), 
        5, FailureHandling.OPTIONAL)) {
        Mobile.tap(findTestObject('Mobile/Common/Btn_YES'), 5)
    }
    
    Mobile.delay(6)

    Mobile.swipe(0, 150, 0, 400, FailureHandling.OPTIONAL)
}

Mobile.tap(findTestObject('Mobile/Store_Actvy/Menu-SalesReturn'), 0)

Mobile.delay(2)

switch (GlobalVariable.Activation_Key) {
    case GlobalVariable.V155_ActivationKey:
        'Functionality TC start'
        Mobile.verifyElementVisible(findTestObject('Mobile/SalesReturn/Label-Product Name'), 
            0)

        Mobile.verifyElementVisible(findTestObject('Mobile/SalesReturn/Label-Cases'), 0)

        Mobile.verifyElementVisible(findTestObject('Mobile/SalesReturn/Label-Pieces'), 0)

        Mobile.takeScreenshot()

        Mobile.tap(findTestObject('Mobile/Common/Icon_Search'), 0)

        GlobalVariable.S_Sku_Name = findTestData('Mobile Input Data/Invoice').getValue('Sku_Name', 2)

        Mobile.setText(findTestObject('Mobile/Common/Field_Search_EnterText'), GlobalVariable.S_Sku_Name, 
            0)

        Mobile.takeScreenshot()

        Mobile.verifyElementVisible(findTestObject('Mobile/SalesReturn/ProductName-1st'), 
            3, FailureHandling.STOP_ON_FAILURE)

        Mobile.verifyElementText(findTestObject('Mobile/SalesReturn/ProductName-1st'), GlobalVariable.S_Sku_Name, 
            FailureHandling.STOP_ON_FAILURE)

        //CA_18
        KeywordUtil.logInfo('The list of the products  displayed in the Sales return screen')

        Mobile.takeScreenshot()

        Mobile.tap(findTestObject('Mobile/SalesReturn/Piece-1st'), 0)

        Mobile.delay(2, FailureHandling.STOP_ON_FAILURE)

        Mobile.verifyElementExist(findTestObject('Mobile/SalesReturn/RETURN_Screen_Title'), 
            0)

        Mobile.verifyElementVisible(findTestObject('Mobile/SalesReturn/RETURN_Screen_Title'), 
            0)

        //CA_21
        KeywordUtil.logInfo('User can able to select any product')

        Mobile.takeScreenshot()

        '1nd Reason'
        Mobile.setText(findTestObject('Mobile/SalesReturn/Return-InvoiceNo'), findTestData(
                'Mobile Input Data/CallAnalysis').getValue('InvoiceNo', 1), 0, FailureHandling.OPTIONAL)

        Mobile.takeScreenshot()

        MRP = Mobile.getText(findTestObject('Mobile/SalesReturn/Return-MRP'), 0)

        Mobile.tap(findTestObject('Mobile/SalesReturn/Return-Select Reason'), 0)

        Mobile.takeScreenshot()

        Reason = Mobile.getText(findTestObject('Mobile/SalesReturn/Damage-option'), 0)

        GlobalVariable.S_ReasonType = Reason

        Mobile.tap(findTestObject('Mobile/SalesReturn/Damage-option'), 0)

        Mobile.tap(findTestObject('Mobile/SalesReturn/Return-Piece'), 0, FailureHandling.STOP_ON_FAILURE)

        GlobalVariable.keypadValue = findTestData('Mobile Input Data/CallAnalysis').getValue('Keypad_Number', 2)

        Mobile.takeScreenshot()

        GlobalVariable.PieceQty = GlobalVariable.keypadValue

        Mobile.tap(findTestObject('Mobile/Common/Global_Number_keypad'), 
            0)

        Mobile.tap(findTestObject('Mobile/SalesReturn/Return-Case'), 0, FailureHandling.STOP_ON_FAILURE)

        GlobalVariable.keypadValue = findTestData('Mobile Input Data/CallAnalysis').getValue('Keypad_Number', 1)

        GlobalVariable.CaseQty = GlobalVariable.keypadValue
		
		Reason01_SIH = (Integer.parseInt(GlobalVariable.CaseQty) * 10) + Integer.parseInt(GlobalVariable.PieceQty)

        Mobile.tap(findTestObject('Mobile/Common/Global_Number_keypad'), 
            0)

        Mobile.takeScreenshot()

        //CA_14
        println('All field should be entered')

        exlpath = 'DDF\\Mobile Input data\\Mobile Input data.xlsx'

        Sheetname = 'SalesReturn'

        workbook01 = ExcelKeywords.getWorkbook(exlpath)

        sheet1 = ExcelKeywords.getExcelSheet(workbook01, Sheetname)

        ExcelKeywords.setValueToCellByIndex(sheet1, 1, 0, GlobalVariable.S_Sku_Name)

        ExcelKeywords.setValueToCellByIndex(sheet1, 1, 1, Reason)

        ExcelKeywords.setValueToCellByIndex(sheet1, 1, 3, MRP)

        ExcelKeywords.setValueToCellByIndex(sheet1, 1, 5, GlobalVariable.PieceQty)

        ExcelKeywords.setValueToCellByIndex(sheet1, 1, 7, GlobalVariable.CaseQty)

        ExcelKeywords.saveWorkbook(exlpath, workbook01)

        '2nd Reason'
        Mobile.tap(findTestObject('Mobile/SalesReturn/Add another Reason Quantity'), 0)

        Mobile.verifyElementVisible(findTestObject('Mobile/SalesReturn/Return-MRP_2'), 0, 
            FailureHandling.STOP_ON_FAILURE)

        Mobile.verifyElementVisible(findTestObject('Mobile/SalesReturn/Return-Select Reason'), 
            0, FailureHandling.STOP_ON_FAILURE)

        Mobile.verifyElementVisible(findTestObject('Mobile/SalesReturn/Return-Piece_2'), 
            0, FailureHandling.STOP_ON_FAILURE)

        Mobile.takeScreenshot()

        //CA_22
        KeywordUtil.logInfo('User should be able to Add another reason and quantity for the same product in product return screen')

        MRP2 = Mobile.getText(findTestObject('Mobile/SalesReturn/Return-MRP_2'), 0)

        Mobile.delay(2)

        Mobile.tap(findTestObject('Mobile/SalesReturn/Return-Select Reason_2'), 0)

        Reason2 = Mobile.getText(findTestObject('Mobile/SalesReturn/Salable-option'), 0)

        GlobalVariable.S_ReasonType = Reason2

        Mobile.tap(findTestObject('Mobile/SalesReturn/Salable-option'), 0)

        Mobile.delay(2)

        Mobile.tap(findTestObject('Mobile/SalesReturn/Return-Piece_2'), 0, FailureHandling.STOP_ON_FAILURE)

        GlobalVariable.keypadValue = findTestData('Mobile Input Data/CallAnalysis').getValue('Keypad_Number', 3)

        GlobalVariable.PieceQty = GlobalVariable.keypadValue
		
		Reason02_SIH = Integer.parseInt(GlobalVariable.PieceQty)

        Mobile.tap(findTestObject('Mobile/Common/Global_Number_keypad'), 
            0)

        Mobile.takeScreenshot()

        KeywordUtil.logInfo('Following product details should be entered Invoice no, MRP, Select reason, Case qty,Piece qty')

        ExcelKeywords.setValueToCellByIndex(sheet1, 1, 2, Reason2)

        ExcelKeywords.setValueToCellByIndex(sheet1, 1, 4, MRP2)

        ExcelKeywords.setValueToCellByIndex(sheet1, 1, 6, GlobalVariable.PieceQty)

        ExcelKeywords.saveWorkbook(exlpath, workbook01)

        '3rd Reason'
        Mobile.tap(findTestObject('Mobile/SalesReturn/Add another Reason Quantity'), 0)

        dev_height = Mobile.getDeviceHeight()

        y1 = (dev_height - 900)

        y2 = (dev_height - 1500)

        Mobile.swipe(100, y1, 100, y2, FailureHandling.OPTIONAL)

        Mobile.verifyElementVisible(findTestObject('Mobile/SalesReturn/Return-MRP_3'), 0, 
            FailureHandling.STOP_ON_FAILURE)

        Mobile.verifyElementVisible(findTestObject('Mobile/SalesReturn/Return-Select Reason_3'), 
            0, FailureHandling.STOP_ON_FAILURE)

        Mobile.verifyElementVisible(findTestObject('Mobile/SalesReturn/Return-Piece_3'), 
            0, FailureHandling.STOP_ON_FAILURE)

        Mobile.takeScreenshot()

        //CA_22
        KeywordUtil.logInfo('User should be able to Add another reason and quantity for the same product in product return screen')

        MRP3 = Mobile.getText(findTestObject('Mobile/SalesReturn/Return-MRP_3'), 0)

        Mobile.tap(findTestObject('Mobile/SalesReturn/Return-Select Reason_3'), 0)

        Mobile.delay(2)

        Reason3 = Mobile.getText(findTestObject('Mobile/SalesReturn/Expired-option'), 0)

        GlobalVariable.S_ReasonType = Reason3

        Mobile.tap(findTestObject('Mobile/SalesReturn/Expired-option'), 0)

        Mobile.delay(2)

        Mobile.tap(findTestObject('Mobile/SalesReturn/Return-Piece_3'), 0, FailureHandling.STOP_ON_FAILURE)

        GlobalVariable.keypadValue = findTestData('Mobile Input Data/CallAnalysis').getValue('Keypad_Number', 4)

        GlobalVariable.PieceQty = GlobalVariable.keypadValue
		
		Reason03_SIH = Integer.parseInt(GlobalVariable.PieceQty)

        Mobile.tap(findTestObject('Mobile/Common/Global_Number_keypad'), 
            0)

        Mobile.takeScreenshot()

        KeywordUtil.logInfo('Following product details should be entered Invoice no, MRP, Select reason, Case qty,Piece qty')

        ExcelKeywords.setValueToCellByIndex(sheet1, 1, 17, Reason3)

        ExcelKeywords.setValueToCellByIndex(sheet1, 1, 14, MRP3)

        ExcelKeywords.setValueToCellByIndex(sheet1, 1, 15, GlobalVariable.PieceQty)

        ExcelKeywords.saveWorkbook(exlpath, workbook01)

        //-----
        GlobalVariable.CaseSize = findTestData('Mobile Input Data/Invoice').getValue('CaseSize', 2)

        QtyCase = (Integer.parseInt(GlobalVariable.CaseSize) * Integer.parseInt(GlobalVariable.CaseQty))

        Piece_1 = findTestData('Mobile Input Data/SalesReturn').getValue('Piece_1', 1)

        piece_price_returned_Product = findTestData('Mobile Input Data/Invoice').getValue('PiecePrice', 2)

        Product_1_return_Amt = ((QtyCase + Integer.parseInt(Piece_1)) * Double.parseDouble(piece_price_returned_Product))

        Piece_2 = findTestData('Mobile Input Data/SalesReturn').getValue('Piece_2', 1)

        Product_2_return_Amt = (Integer.parseInt(Piece_2) * Double.parseDouble(piece_price_returned_Product))

        Piece_3 = findTestData('Mobile Input Data/SalesReturn').getValue('Piece_3', 1)

        Product_3_return_Amt = (Integer.parseInt(Piece_3) * Double.parseDouble(piece_price_returned_Product))

        GlobalVariable.PieceQty = ((Integer.parseInt(Piece_1) + Integer.parseInt(Piece_2)) + Integer.parseInt(Piece_3))

        Case_Piece_Qty = (QtyCase + GlobalVariable.PieceQty)

        ExcelKeywords.setValueToCellByIndex(sheet1, 1, 8, Case_Piece_Qty)

        ExcelKeywords.setValueToCellByIndex(sheet1, 1, 11, Product_1_return_Amt)

        ExcelKeywords.setValueToCellByIndex(sheet1, 1, 12, Product_2_return_Amt)

        ExcelKeywords.setValueToCellByIndex(sheet1, 1, 16, Product_3_return_Amt)

        ExcelKeywords.saveWorkbook(exlpath, workbook01)

        Mobile.tap(findTestObject('Mobile/Common/Btn_Done'), 0)

        Mobile.delay(2)

        piece_qty = Mobile.getText(findTestObject('Mobile/SalesReturn/Piece-1st'), 0)

        case_qty = Mobile.getText(findTestObject('Mobile/SalesReturn/Case-1st'), 0)

        Mobile.verifyEqual(piece_qty, GlobalVariable.PieceQty, FailureHandling.STOP_ON_FAILURE)

        case_Qtyy = findTestData('Mobile Input Data/SalesReturn').getValue('Case_1', 1)

        Mobile.verifyEqual(Integer.parseInt(case_qty), Integer.parseInt(case_Qtyy), FailureHandling.STOP_ON_FAILURE)

        Mobile.takeScreenshot()

        //CA_15 :
        KeywordUtil.logInfo(' Entered case&piece qty from the sales return screen should be reflected in sales return list screen')

        //CA_20
        'Buttom total value validation'
        value = Mobile.getText(findTestObject('Mobile/SalesReturn/SR-TotalValue'), 0)

        buttom_value = (Case_Piece_Qty * Double.parseDouble(piece_price_returned_Product))

        Mobile.verifyEqual(value, buttom_value, FailureHandling.STOP_ON_FAILURE)

        Mobile.takeScreenshot()

        KeywordUtil.logInfo(value + ' : Buttom total value')

        //CA_19
        'Buttom total lines count'
        line = Mobile.getText(findTestObject('Mobile/SalesReturn/SR-Lines'), 0)

        number_of_product_returned = findTestData('Mobile Input Data/SalesReturn').getValue('Lines', 1)

        Mobile.verifyMatch(line, number_of_product_returned, false, FailureHandling.STOP_ON_FAILURE)

        Mobile.takeScreenshot()

        KeywordUtil.logInfo(line + ' : Buttom total lines count')

        Mobile.tap(findTestObject('Mobile/SalesReturn/SR-Next btn'), 0)

        Mobile.verifyElementExist(findTestObject('Mobile/SalesReturn/Sales Return Summary-Title'), 
            0)

        Mobile.verifyElementVisible(findTestObject('Mobile/SalesReturn/Sales Return Summary-Title'), 
            0)

        Mobile.takeScreenshot()

        //CA_23
        KeywordUtil.logInfo('User should be able to view Sales return summary screen')

        Mobile.takeScreenshot()

        Mobile.verifyElementText(findTestObject('Mobile/SalesReturn/SR-ProductName'), GlobalVariable.S_Sku_Name, 
            FailureHandling.STOP_ON_FAILURE)

        Mobile.verifyElementText(findTestObject('Mobile/SalesReturn/SR-Reason'), Reason, 
            FailureHandling.STOP_ON_FAILURE)

        Mobile.verifyElementText(findTestObject('Mobile/SalesReturn/SR-Reason_2'), Reason2, 
            FailureHandling.STOP_ON_FAILURE)

        Mobile.verifyElementText(findTestObject('Mobile/SalesReturn/SR-Reason_3'), Reason3, 
            FailureHandling.STOP_ON_FAILURE)

        Mobile.verifyElementText(findTestObject('Mobile/SalesReturn/SR_InvoiceNo'), findTestData(
                'Mobile Input Data/CallAnalysis').getValue('InvoiceNo', 1), FailureHandling.OPTIONAL)

        MRP_Field_Value = Mobile.getText(findTestObject('Mobile/SalesReturn/SR_MRP'), 0)

        MRP_Value = MRP_Field_Value.replaceAll('[ Old MRP:  ]', ' ')

        Mobile.verifyEqual(Double.parseDouble(MRP), Double.parseDouble(MRP_Value), FailureHandling.STOP_ON_FAILURE)

        MRP_Field_Value2 = Mobile.getText(findTestObject('Mobile/SalesReturn/SR_MRP_2'), 
            0)

        MRP_Value2 = MRP_Field_Value2.replaceAll('[ Old MRP:  ]', ' ')

        Mobile.verifyEqual(Double.parseDouble(MRP2), Double.parseDouble(MRP_Value2), FailureHandling.STOP_ON_FAILURE)

        Mobile.verifyElementText(findTestObject('Mobile/SalesReturn/SR-Case'), GlobalVariable.CaseQty, 
            FailureHandling.STOP_ON_FAILURE)

        Mobile.verifyElementText(findTestObject('Mobile/SalesReturn/SR-Piece'), Piece_1, 
            FailureHandling.STOP_ON_FAILURE)

        Mobile.verifyElementText(findTestObject('Mobile/SalesReturn/SR-Piece_2'), Piece_2, 
            FailureHandling.STOP_ON_FAILURE)

        Mobile.verifyElementText(findTestObject('Mobile/SalesReturn/SR-Piece_3'), Piece_3, 
            FailureHandling.STOP_ON_FAILURE)

        Mobile.takeScreenshot()

        //CA_16
        KeywordUtil.logInfo('Following product details should be displayed correctly in sales return summary screen : Invoice no, MRP, Select reason, Case qty,Piece qty')

        SR_TotalValue = Mobile.getText(findTestObject('Mobile/SalesReturn/SR-TotalValue - 2'), 
            0)

        SR_Total_Value = Double.parseDouble(SR_TotalValue).round()

        Expected_SR_Total_Value = (SR_Total_Value.toString() + '.0')

        Mobile.tap(findTestObject('Mobile/SalesReturn/SR-Save btn'), 0)

        Mobile.tap(findTestObject('Mobile/Common/Btn_OK'), 3, FailureHandling.OPTIONAL)

        Mobile.verifyElementExist(findTestObject('Mobile/SalesReturn/SR-Saved Successfully'), 
            3, FailureHandling.OPTIONAL)

        Mobile.takeScreenshot()

        //CA_17
        KeywordUtil.logInfo('Sales return created successfully')

        Mobile.tap(findTestObject('Mobile/Common/Btn_OK'), 3, FailureHandling.OPTIONAL)

        Mobile.delay(3)

        Mobile.swipe(0, 150, 0, 400, FailureHandling.OPTIONAL)

        Mobile.tap(findTestObject('Mobile/CallAnalysis/StoreActy_Close Call Btn'), 0)

        Mobile.delay(2)

        Mobile.takeScreenshot()
		
		
		SalesReturnVolume = Mobile.getText(findTestObject('Mobile/CallAnalysis/Sales Return Volume'),
			0)

		Expected_SR_Vol = Reason01_SIH +  Reason02_SIH + Reason03_SIH

		
		Mobile.verifyEqual(Integer.parseInt(SalesReturnVolume), Expected_SR_Vol, FailureHandling.STOP_ON_FAILURE)

        SalesReturn = Mobile.getText(findTestObject('Mobile/CallAnalysis/Value-Sales Return'), 
            0)

        GlobalVariable.SR_Amt = SalesReturn

        ExcelKeywords.setValueToCellByIndex(sheet1, 1, 10, SalesReturn)

        ExcelKeywords.saveWorkbook(exlpath, workbook01)

        Mobile.verifyMatch(SalesReturn, Expected_SR_Total_Value, false, FailureHandling.STOP_ON_FAILURE)

        Mobile.takeScreenshot()

        //CA_24
        println('Sales return value should displayed correctly')

        Mobile.tap(findTestObject('Mobile/Common/Icon_Back'), 0, FailureHandling.OPTIONAL)

        Mobile.delay(3)

        Mobile.swipe(0, 150, 0, 400, FailureHandling.OPTIONAL)

        break
    case GlobalVariable.V158_ActivationKey:
        'Functionality TC start'
        Mobile.verifyElementVisible(findTestObject('Mobile/SalesReturn/Label-Product Name'), 
            0)

        Mobile.verifyElementVisible(findTestObject('Mobile/SalesReturn/Label-Cases'), 0)

        Mobile.verifyElementVisible(findTestObject('Mobile/SalesReturn/Label-Pieces'), 0)

        Mobile.takeScreenshot()

        Mobile.tap(findTestObject('Mobile/Common/Icon_Search'), 0)

        GlobalVariable.S_Sku_Name = findTestData('Mobile Input Data/Invoice').getValue('Sku_Name', 2)

        Mobile.setText(findTestObject('Mobile/Common/Field_Search_EnterText'), GlobalVariable.S_Sku_Name, 
            0)

        Mobile.takeScreenshot()

        Mobile.verifyElementVisible(findTestObject('Mobile/SalesReturn/ProductName-1st'), 
            3, FailureHandling.STOP_ON_FAILURE)

        Mobile.verifyElementText(findTestObject('Mobile/SalesReturn/ProductName-1st'), GlobalVariable.S_Sku_Name, 
            FailureHandling.STOP_ON_FAILURE)

        //CA_18
        KeywordUtil.logInfo('The list of the products  displayed in the Sales return screen')

        Mobile.takeScreenshot()

        Mobile.tap(findTestObject('Mobile/SalesReturn/Piece-1st'), 0)

        Mobile.delay(2, FailureHandling.STOP_ON_FAILURE)

        Mobile.verifyElementExist(findTestObject('Mobile/SalesReturn/RETURN_Screen_Title'), 
            0)

        Mobile.verifyElementVisible(findTestObject('Mobile/SalesReturn/RETURN_Screen_Title'), 
            0)

        //CA_21
        KeywordUtil.logInfo('User can able to select any product')

        Mobile.takeScreenshot()

        '1nd product'
        Mobile.setText(findTestObject('Mobile/SalesReturn/Return-InvoiceNo'), findTestData(
                'Mobile Input Data/CallAnalysis').getValue('InvoiceNo', 1), 0, FailureHandling.OPTIONAL)

        '1nd product'
        Mobile.sendKeys(findTestObject('Mobile/SalesReturn/Return-InvoiceNo'), findTestData(
                'Mobile Input Data/CallAnalysis').getValue('InvoiceNo', 1))

        Mobile.takeScreenshot()

        MRP = Mobile.getText(findTestObject('Mobile/SalesReturn/Return-MRP'), 0)

        x = Mobile.getAttribute(findTestObject('Mobile/SalesReturn/Return-Select Reason'), 
            'x', 2, FailureHandling.OPTIONAL)

        y = Mobile.getAttribute(findTestObject('Mobile/SalesReturn/Return-Select Reason'), 
            'y', 2, FailureHandling.OPTIONAL)

        height = Mobile.getAttribute(findTestObject('Mobile/SalesReturn/Return-Select Reason'), 
            'height', 2, FailureHandling.OPTIONAL)

        x_point = (Integer.parseInt(x) + 50)

        y_point = ((Integer.parseInt(y) + Integer.parseInt(height)) + 20)

        Mobile.tap(findTestObject('Mobile/SalesReturn/Return-Select Reason'), 0)

        Mobile.takeScreenshot()

        Reason = 'Damage'

        GlobalVariable.S_ReasonType = Reason

        Mobile.setText(findTestObject('Mobile/SalesReturn/Return-Select Reason'), Reason, 
            0, FailureHandling.STOP_ON_FAILURE)

        Mobile.takeScreenshot()

        Mobile.tapAtPosition(x_point, y_point)

        Mobile.tap(findTestObject('Mobile/SalesReturn/Return-Piece'), 0, FailureHandling.STOP_ON_FAILURE)

        GlobalVariable.keypadValue = findTestData('Mobile Input Data/CallAnalysis').getValue('Keypad_Number', 2)

        Mobile.takeScreenshot()

        GlobalVariable.PieceQty = GlobalVariable.keypadValue

        Mobile.tap(findTestObject('Mobile/Common/Global_Number_keypad'), 
            0)

        Mobile.tap(findTestObject('Mobile/SalesReturn/Return-Case'), 0, FailureHandling.STOP_ON_FAILURE)

        GlobalVariable.keypadValue = findTestData('Mobile Input Data/CallAnalysis').getValue('Keypad_Number', 1)

        GlobalVariable.CaseQty = GlobalVariable.keypadValue

        Mobile.tap(findTestObject('Mobile/Common/Global_Number_keypad'), 
            0)

        Mobile.takeScreenshot()
		
		Reason01_SIH = (Integer.parseInt(GlobalVariable.CaseQty) * 10) + Integer.parseInt(GlobalVariable.PieceQty)
		

        //CA_14
        println('All field should be entered')

        exlpath = 'DDF\\Mobile Input data\\Mobile Input data.xlsx'

        Sheetname = 'SalesReturn'

        workbook01 = ExcelKeywords.getWorkbook(exlpath)

        sheet1 = ExcelKeywords.getExcelSheet(workbook01, Sheetname)

        ExcelKeywords.setValueToCellByIndex(sheet1, 1, 0, GlobalVariable.S_Sku_Name)

        ExcelKeywords.setValueToCellByIndex(sheet1, 1, 1, Reason)

        ExcelKeywords.setValueToCellByIndex(sheet1, 1, 3, MRP)

        ExcelKeywords.setValueToCellByIndex(sheet1, 1, 5, GlobalVariable.PieceQty)

        ExcelKeywords.setValueToCellByIndex(sheet1, 1, 7, GlobalVariable.CaseQty)

        ExcelKeywords.saveWorkbook(exlpath, workbook01)

        '2nd product'
        Mobile.tap(findTestObject('Mobile/SalesReturn/Add another Reason Quantity'), 0)

        Mobile.verifyElementVisible(findTestObject('Mobile/SalesReturn/Return-MRP_2'), 0, 
            FailureHandling.STOP_ON_FAILURE)

        Mobile.verifyElementVisible(findTestObject('Mobile/SalesReturn/Return-Select Reason_2'), 
            0, FailureHandling.STOP_ON_FAILURE)

        Mobile.verifyElementVisible(findTestObject('Mobile/SalesReturn/Return-Piece_2'), 
            0, FailureHandling.STOP_ON_FAILURE)

        Mobile.takeScreenshot()

        //CA_22
        KeywordUtil.logInfo('User should be able to Add another reason and quantity for the same product in product return screen')

        MRP2 = Mobile.getText(findTestObject('Mobile/SalesReturn/Return-MRP_2'), 0)

        x = Mobile.getAttribute(findTestObject('Mobile/SalesReturn/Return-Select Reason_2'), 
            'x', 2, FailureHandling.OPTIONAL)

        y = Mobile.getAttribute(findTestObject('Mobile/SalesReturn/Return-Select Reason_2'), 
            'y', 2, FailureHandling.OPTIONAL)

        height = Mobile.getAttribute(findTestObject('Mobile/SalesReturn/Return-Select Reason_2'), 
            'height', 2, FailureHandling.OPTIONAL)

        x_point = (Integer.parseInt(x) + 50)

        y_point = ((Integer.parseInt(y) + Integer.parseInt(height)) + 20)

        Mobile.tap(findTestObject('Mobile/SalesReturn/Return-Select Reason_2'), 0)

        Reason2 = 'Salable'

        GlobalVariable.S_ReasonType = Reason2

        Mobile.setText(findTestObject('Mobile/SalesReturn/Return-Select Reason_2'), Reason2, 
            0, FailureHandling.STOP_ON_FAILURE)

        Mobile.takeScreenshot()

        Mobile.tapAtPosition(x_point, y_point)

        Mobile.tap(findTestObject('Mobile/SalesReturn/Return-Piece_2'), 0, FailureHandling.STOP_ON_FAILURE)

        GlobalVariable.keypadValue = findTestData('Mobile Input Data/CallAnalysis').getValue('Keypad_Number', 3)

        GlobalVariable.PieceQty = GlobalVariable.keypadValue
		
		Reason02_SIH = Integer.parseInt(GlobalVariable.PieceQty)
		

        Mobile.tap(findTestObject('Mobile/Common/Global_Number_keypad'), 
            0)

        Mobile.takeScreenshot()

        KeywordUtil.logInfo('Following product details should be entered Invoice no, MRP, Select reason, Case qty,Piece qty')

        ExcelKeywords.setValueToCellByIndex(sheet1, 1, 2, Reason2)

        ExcelKeywords.setValueToCellByIndex(sheet1, 1, 4, MRP2)

        ExcelKeywords.setValueToCellByIndex(sheet1, 1, 6, GlobalVariable.PieceQty)

        ExcelKeywords.saveWorkbook(exlpath, workbook01)

        '3rd Reason'
        Mobile.tap(findTestObject('Mobile/SalesReturn/Add another Reason Quantity'), 0)

        Mobile.verifyElementVisible(findTestObject('Mobile/SalesReturn/Return-MRP_3'), 0, 
            FailureHandling.STOP_ON_FAILURE)

        Mobile.verifyElementVisible(findTestObject('Mobile/SalesReturn/Return-Select Reason_3'), 
            0, FailureHandling.STOP_ON_FAILURE)

        Mobile.verifyElementVisible(findTestObject('Mobile/SalesReturn/Return-Piece_3'), 
            0, FailureHandling.STOP_ON_FAILURE)

        Mobile.takeScreenshot()

        //CA_22
        KeywordUtil.logInfo('User should be able to Add another reason and quantity for the same product in product return screen')

        MRP3 = Mobile.getText(findTestObject('Mobile/SalesReturn/Return-MRP_3'), 0)

        x = Mobile.getAttribute(findTestObject('Mobile/SalesReturn/Return-Select Reason_3'), 
            'x', 2, FailureHandling.OPTIONAL)

        y = Mobile.getAttribute(findTestObject('Mobile/SalesReturn/Return-Select Reason_3'), 
            'y', 2, FailureHandling.OPTIONAL)

        height = Mobile.getAttribute(findTestObject('Mobile/SalesReturn/Return-Select Reason_3'), 
            'height', 2, FailureHandling.OPTIONAL)

        x_point = (Integer.parseInt(x) + 50)

        y_point = ((Integer.parseInt(y) + Integer.parseInt(height)) + 20)

        Mobile.tap(findTestObject('Mobile/SalesReturn/Return-Select Reason_3'), 0)

        Mobile.delay(2)

        Reason3 = 'Expired'

        GlobalVariable.S_ReasonType = Reason3

        Mobile.setText(findTestObject('Mobile/SalesReturn/Return-Select Reason_3'), Reason3, 
            0, FailureHandling.STOP_ON_FAILURE)

        Mobile.takeScreenshot()

        Mobile.tapAtPosition(x_point, y_point)

        Mobile.delay(2)

        Mobile.tap(findTestObject('Mobile/SalesReturn/Return-Piece_3'), 0, FailureHandling.STOP_ON_FAILURE)

        GlobalVariable.keypadValue = findTestData('Mobile Input Data/CallAnalysis').getValue('Keypad_Number', 4)

        GlobalVariable.PieceQty = GlobalVariable.keypadValue
		
		Reason03_SIH = Integer.parseInt(GlobalVariable.PieceQty)
		

        Mobile.tap(findTestObject('Mobile/Common/Global_Number_keypad'), 
            0)

        Mobile.takeScreenshot()

        KeywordUtil.logInfo('Following product details should be entered Invoice no, MRP, Select reason, Case qty,Piece qty')

        ExcelKeywords.setValueToCellByIndex(sheet1, 1, 17, Reason3)

        ExcelKeywords.setValueToCellByIndex(sheet1, 1, 14, MRP3)

        ExcelKeywords.setValueToCellByIndex(sheet1, 1, 15, GlobalVariable.PieceQty)

        ExcelKeywords.saveWorkbook(exlpath, workbook01)

        //--
        GlobalVariable.CaseSize = findTestData('Mobile Input Data/Invoice').getValue('CaseSize', 2)

        QtyCase = (Integer.parseInt(GlobalVariable.CaseSize) * Integer.parseInt(GlobalVariable.CaseQty))

        Piece_1 = findTestData('Mobile Input Data/SalesReturn').getValue('Piece_1', 1)

        piece_price_returned_Product = findTestData('Mobile Input Data/Invoice').getValue('PiecePrice', 2)

        Product_1_return_Amt = ((QtyCase + Integer.parseInt(Piece_1)) * Double.parseDouble(piece_price_returned_Product))

        Piece_2 = findTestData('Mobile Input Data/SalesReturn').getValue('Piece_2', 1)

        Product_2_return_Amt = (Integer.parseInt(Piece_2) * Double.parseDouble(piece_price_returned_Product))

        Piece_3 = findTestData('Mobile Input Data/SalesReturn').getValue('Piece_3', 1)

        Product_3_return_Amt = (Integer.parseInt(Piece_3) * Double.parseDouble(piece_price_returned_Product))

        GlobalVariable.PieceQty = ((Integer.parseInt(Piece_1) + Integer.parseInt(Piece_2)) + Integer.parseInt(Piece_3))

        Case_Piece_Qty = (QtyCase + GlobalVariable.PieceQty)

        ExcelKeywords.setValueToCellByIndex(sheet1, 1, 8, Case_Piece_Qty)

        ExcelKeywords.setValueToCellByIndex(sheet1, 1, 11, Product_1_return_Amt)

        ExcelKeywords.setValueToCellByIndex(sheet1, 1, 12, Product_2_return_Amt)

        ExcelKeywords.setValueToCellByIndex(sheet1, 1, 16, Product_3_return_Amt)

        ExcelKeywords.saveWorkbook(exlpath, workbook01)

        Mobile.tap(findTestObject('Mobile/Common/Btn_Done'), 0)

        Mobile.delay(2)

        Mobile.callTestCase(findTestCase('Product_Mobile/Common/Search_Product'), [:], FailureHandling.STOP_ON_FAILURE)

        Mobile.setText(findTestObject('Mobile/Common/Field_Search_EnterText'), GlobalVariable.S_Sku_Name, 
            0)

        piece_qty = Mobile.getText(findTestObject('Mobile/SalesReturn/Piece-1st'), 0)

        case_qty = Mobile.getText(findTestObject('Mobile/SalesReturn/Case-1st'), 0)

        Mobile.verifyEqual(piece_qty, GlobalVariable.PieceQty, FailureHandling.STOP_ON_FAILURE)

        case_Qtyy = findTestData('Mobile Input Data/SalesReturn').getValue('Case_1', 1)

        Mobile.verifyEqual(Integer.parseInt(case_qty), Integer.parseInt(case_Qtyy), FailureHandling.STOP_ON_FAILURE)

        Mobile.takeScreenshot()

        //CA_15 :
        KeywordUtil.logInfo(' Entered case&piece qty from the sales return screen should be reflected in sales return list screen')

        //CA_20
        'Buttom total value validation'
        value = Mobile.getText(findTestObject('Mobile/SalesReturn/SR-TotalValue'), 0)

        buttom_value = (Case_Piece_Qty * Double.parseDouble(piece_price_returned_Product))

        Mobile.verifyEqual(value, buttom_value, FailureHandling.STOP_ON_FAILURE)

        Mobile.takeScreenshot()

        KeywordUtil.logInfo(value + ' : Buttom total value')

        //CA_19
        'Buttom total lines count'
        line = Mobile.getText(findTestObject('Mobile/SalesReturn/SR-Lines'), 0)

        number_of_product_returned = findTestData('Mobile Input Data/SalesReturn').getValue('Lines', 1)

        Mobile.verifyMatch(line, number_of_product_returned, false, FailureHandling.STOP_ON_FAILURE)

        Mobile.takeScreenshot()

        KeywordUtil.logInfo(line + ' : Buttom total lines count')

        Mobile.tap(findTestObject('Mobile/SalesReturn/SR-Next btn'), 0)

        Mobile.verifyElementExist(findTestObject('Mobile/SalesReturn/Sales Return Summary-Title'), 
            0)

        Mobile.verifyElementVisible(findTestObject('Mobile/SalesReturn/Sales Return Summary-Title'), 
            0)

        Mobile.takeScreenshot()

        //CA_23
        KeywordUtil.logInfo('User should be able to view Sales return summary screen')

        Mobile.takeScreenshot()

        Mobile.verifyElementText(findTestObject('Mobile/SalesReturn/SR-ProductName'), GlobalVariable.S_Sku_Name, 
            FailureHandling.OPTIONAL)

        Mobile.verifyElementText(findTestObject('Mobile/SalesReturn/SR-Reason'), Reason, 
            FailureHandling.OPTIONAL)

        Mobile.verifyElementText(findTestObject('Mobile/SalesReturn/SR-Reason_2'), Reason2, 
            FailureHandling.OPTIONAL)

        Mobile.verifyElementText(findTestObject('Mobile/SalesReturn/SR-Reason_3'), Reason3, 
            FailureHandling.OPTIONAL)

        Mobile.verifyElementText(findTestObject('Mobile/SalesReturn/SR_InvoiceNo'), findTestData(
                'Mobile Input Data/CallAnalysis').getValue('InvoiceNo', 1), FailureHandling.OPTIONAL)

        MRP_Field_Value = Mobile.getText(findTestObject('Mobile/SalesReturn/SR_MRP'), 0, 
            FailureHandling.OPTIONAL)

        MRP_Value = MRP_Field_Value.replaceAll('[ Old MRP:  ]', ' ')

        Mobile.verifyEqual(Double.parseDouble(MRP), Double.parseDouble(MRP_Value), FailureHandling.STOP_ON_FAILURE)

        MRP_Field_Value2 = Mobile.getText(findTestObject('Mobile/SalesReturn/SR_MRP_2'), 
            0)

        MRP_Value2 = MRP_Field_Value2.replaceAll('[ Old MRP:  ]', ' ')

        Mobile.verifyEqual(Double.parseDouble(MRP2), Double.parseDouble(MRP_Value2), FailureHandling.STOP_ON_FAILURE)

        Mobile.verifyElementText(findTestObject('Mobile/SalesReturn/SR-Case'), GlobalVariable.CaseQty, 
            FailureHandling.STOP_ON_FAILURE)

        Mobile.verifyElementText(findTestObject('Mobile/SalesReturn/SR-Piece'), Piece_1, 
            FailureHandling.STOP_ON_FAILURE)

        Mobile.verifyElementText(findTestObject('Mobile/SalesReturn/SR-Piece_2'), Piece_2, 
            FailureHandling.STOP_ON_FAILURE)

        Mobile.verifyElementText(findTestObject('Mobile/SalesReturn/SR-Piece_3'), Piece_3, 
            FailureHandling.STOP_ON_FAILURE)

        Mobile.takeScreenshot()

        //CA_16
        KeywordUtil.logInfo('Following product details should be displayed correctly in sales return summary screen : Invoice no, MRP, Select reason, Case qty,Piece qty')

        SR_TotalValue = Mobile.getText(findTestObject('Mobile/SalesReturn/SR-TotalValue - 2'), 
            0)

        SR_Total_Value = Double.parseDouble(SR_TotalValue).round()

        Expected_SR_Total_Value = (SR_Total_Value.toString() + '.0')

        Mobile.tap(findTestObject('Mobile/SalesReturn/SR-Save btn'), 0)

        Mobile.tap(findTestObject('Mobile/Common/Btn_OK'), 3, FailureHandling.OPTIONAL)

        Mobile.verifyElementExist(findTestObject('Mobile/SalesReturn/SR-Saved Successfully'), 
            3, FailureHandling.OPTIONAL)

        Mobile.takeScreenshot()

        //CA_17
        KeywordUtil.logInfo('Sales return created successfully')

        //Mobile.tap(findTestObject('Mobile/Common/Btn_OK'), 3, FailureHandling.OPTIONAL)
        Mobile.delay(3)

        Mobile.swipe(0, 150, 0, 400, FailureHandling.OPTIONAL)

        Mobile.tap(findTestObject('Mobile/CallAnalysis/StoreActy_Close Call Btn'), 0)

        Mobile.delay(2)

        Mobile.takeScreenshot(			)
		
		SalesReturnVolume = Mobile.getText(findTestObject('Mobile/CallAnalysis/Sales Return Volume'),
			0)

		Expected_SR_Vol = Reason01_SIH +  Reason02_SIH + Reason03_SIH 

		
		Mobile.verifyEqual(Integer.parseInt(SalesReturnVolume), Expected_SR_Vol, FailureHandling.STOP_ON_FAILURE)
		
        SalesReturn = Mobile.getText(findTestObject('Mobile/CallAnalysis/Value-Sales Return'), 
            0)

        GlobalVariable.SR_Amt = SalesReturn

        ExcelKeywords.setValueToCellByIndex(sheet1, 1, 10, SalesReturn)

        ExcelKeywords.saveWorkbook(exlpath, workbook01)

        Mobile.verifyMatch(SalesReturn, Expected_SR_Total_Value, false, FailureHandling.STOP_ON_FAILURE)

        Mobile.takeScreenshot()

        //CA_24
        println('Sales return value should displayed correctly')

        Mobile.tap(findTestObject('Mobile/Common/Icon_Back'), 0, FailureHandling.OPTIONAL)

        Mobile.delay(3)

        Mobile.swipe(0, 150, 0, 400, FailureHandling.OPTIONAL)

        break
}

