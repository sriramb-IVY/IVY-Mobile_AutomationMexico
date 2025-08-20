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
import java.text.SimpleDateFormat as SimpleDateFormat
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import java.math.BigDecimal as BigDecimal
import java.math.RoundingMode as RoundingMode
import java.text.DecimalFormat as DecimalFormat
import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.chrome.ChromeDriver as ChromeDriver
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import com.kms.katalon.keyword.excel.ExcelKeywords as ExcelKeywords
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint

//Mobile.startApplication(GlobalVariable.APK_File, false)
//
//Mobile.delay(20)
//
//Mobile.tap(findTestObject('Mobile/Common/Btn_Menu'), 0)
//
//not_run: Mobile.callTestCase(findTestCase('Product_Mobile/Common/Mobile_Login/VanSeller_Login-With Reset'), [:], FailureHandling.STOP_ON_FAILURE)
//
//GlobalVariable.RetailerName = 'Dretailer5'
//
//Mobile.tap(findTestObject('Mobile/MainMenu/Menu_Trade Coverage'), 0)
//
//Mobile.tap(findTestObject('Mobile/Common/Icon_Search'), 5)
//
//Mobile.setText(findTestObject('Mobile/Common/Field_Search_EnterText'), GlobalVariable.RetailerName, 
//    5)
//
//Mobile.tap(findTestObject('Mobile/TradeCoverage/First_Retailer_Selection_From_List'), 5)
//
//Mobile.tap(findTestObject('Mobile/Common/Btn_StartVisit'), 5)
//
//if (Mobile.verifyElementVisible(findTestObject('Object Repository/Mobile/Common/Popup_Title_LocationValidation'), 
//    5, FailureHandling.OPTIONAL)) {
//    Mobile.tap(findTestObject('Mobile/Common/Btn_YES'), 5)
//}
//
//Mobile.delay(6)
//
//Mobile.swipe(0, 150, 0, 400, FailureHandling.OPTIONAL)
//
//Mobile.startApplication(GlobalVariable.APKFile, false)
//
//Mobile.tap(findTestObject('Mobile/Common/Btn_Menu'), 0)
//
//GlobalVariable.RetailerName = findTestData('Mobile Input Data/CallAnalysis').getValue('Retailer_Name', 1)
//
//Mobile.tap(findTestObject('Mobile/MainMenu/Menu_Trade Coverage'), 0)
//
//Mobile.tap(findTestObject('Mobile/Common/Icon_Search'), 5)
//
//Mobile.setText(findTestObject('Mobile/Common/Field_Search_EnterText'), GlobalVariable.RetailerName, 
//    5)
//
//Mobile.tap(findTestObject('Mobile/TradeCoverage/First_Retailer_Selection_From_List'), 5)
//
//Mobile.tap(findTestObject('Mobile/Common/Btn_StartVisit'), 5)
//
//if (Mobile.verifyElementVisible(findTestObject('Object Repository/Mobile/Common/Popup_Title_LocationValidation'), 
//    5, FailureHandling.OPTIONAL)) {
//    Mobile.tap(findTestObject('Mobile/Common/Btn_YES'), 5)
//}
//
//Mobile.delay(6)
//
//Mobile.swipe(0, 150, 0, 400, FailureHandling.OPTIONAL)
//
//not_run: Mobile.tap(findTestObject('Mobile/Common/Icon_Back'), 0, FailureHandling.OPTIONAL)
//
//not_run: Mobile.delay(3)
//
//not_run: Mobile.swipe(0, 150, 0, 400, FailureHandling.OPTIONAL)

if (Mobile.verifyElementExist(findTestObject('Mobile/Store_Actvy/Menu-Collection'), 2, FailureHandling.OPTIONAL)) {
    'Collection menu visible'
} else {
    Mobile.callTestCase(findTestCase('Product_Mobile/Common/Mobile_Login/Relaunch the app'), [:], FailureHandling.CONTINUE_ON_FAILURE)

    Mobile.tap(findTestObject('Mobile/Common/Btn_Menu'), 0)

    GlobalVariable.RetailerName = findTestData('Mobile Input Data/CallAnalysis').getValue('Retailer_Name', 1)

    Mobile.tap(findTestObject('Mobile/MainMenu/Menu_Trade Coverage'), 0)

    Mobile.tap(findTestObject('Mobile/Common/Icon_Search'), 5)

    Mobile.setText(findTestObject('Mobile/Common/Field_Search_EnterText'), GlobalVariable.RetailerName, 5)

    Mobile.tap(findTestObject('Mobile/TradeCoverage/First_Retailer_Selection_From_List'), 5)

    Mobile.tap(findTestObject('Mobile/Common/Btn_StartVisit'), 5)

    if (Mobile.verifyElementVisible(findTestObject('Object Repository/Mobile/Common/Popup_Title_LocationValidation'), 5, FailureHandling.OPTIONAL)) {
        Mobile.tap(findTestObject('Mobile/Common/Btn_YES'), 5)
    }
    
    Mobile.delay(3)

    Mobile.swipe(0, 150, 0, 400, FailureHandling.OPTIONAL)
}

Mobile.tap(findTestObject('Mobile/Store_Actvy/Menu-Collection'), 0)

String pattern = 'dd-MM-yyyy'

SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern)

String currentdate = simpleDateFormat.format(new Date())

ScrollDate = (currentdate + '(')

GlobalVariable.label = currentdate

Mobile.scrollToText(ScrollDate, FailureHandling.OPTIONAL)

switch (GlobalVariable.Activation_Key) {
    case GlobalVariable.V155_ActivationKey:
        Mobile.verifyElementVisible(findTestObject('Mobile/Collection/Title/Invoice No'), 0)

        Mobile.verifyElementVisible(findTestObject('Mobile/Collection/Values/Get_InvoiceNo'), 0)

        Mobile.verifyElementVisible(findTestObject('Mobile/Collection/Title/Invoice Date'), 0)

        Mobile.verifyElementVisible(findTestObject('Mobile/Collection/Values/Get_InvoiceNo_Date'), 0)

        Mobile.verifyElementVisible(findTestObject('Mobile/Collection/Title/OS Amt'), 0)

        Mobile.verifyElementVisible(findTestObject('Mobile/Collection/Title/Due Date'), 0)

        Mobile.verifyElementVisible(findTestObject('Mobile/Collection/Values/Due Date_Value'), 0)

        Mobile.verifyElementVisible(findTestObject('Mobile/Collection/Title/Invoice Amt'), 0)

        Mobile.verifyElementVisible(findTestObject('Mobile/Collection/Values/Transaction_Amt'), 0)

        Mobile.verifyElementVisible(findTestObject('Mobile/Collection/Title/Received'), 0)

        Mobile.verifyElementVisible(findTestObject('Mobile/Collection/Values/Received_Amt'), 0)

        Mobile.takeScreenshot()

        KeywordUtil.logInfo('The user can view the list of Pending Invoices details')

        TransactionNo = Mobile.getText(findTestObject('Mobile/Collection/Values/Get_InvoiceNo'), 0)

        GlobalVariable.Transaction_No = TransactionNo

        Transaction_Amt = Mobile.getText(findTestObject('Mobile/Collection/Values/Transaction_Amt'), 0)

        GlobalVariable.C_TransactionAmt = Transaction_Amt

        pay = (Double.parseDouble(Transaction_Amt) / 3)

        BigDecimal single_pay = new BigDecimal(pay).setScale(0, RoundingMode.HALF_DOWN)

        KeywordUtil.logInfo(single_pay.toString())

        before_OS_Amt = Mobile.getText(findTestObject('Mobile/Collection/Values/OS_Amt'), 0)

        Mobile.tap(findTestObject('Mobile/Collection/Values/Get_InvoiceNo'), 0)

        Mobile.verifyElementVisible(findTestObject('Object Repository/Mobile/Collection/Blue_Tick'), 0)

        Mobile.takeScreenshot()

        KeywordUtil.logInfo('The user can able to select any invoice')

        Mobile.tap(findTestObject('Mobile/Collection/Pay_Btn'), 0)

        Mobile.verifyElementVisible(findTestObject('Mobile/Collection/Bill Payment Screen_Title'), 0)

        Mobile.takeScreenshot()

        KeywordUtil.logInfo('The user can able to navigate Bill payment screen while click the pay button')

        Mobile.verifyElementVisible(findTestObject('Mobile/Collection/Payment Amount_Value'), 0)

        Mobile.verifyElementVisible(findTestObject('Mobile/Collection/Balance Amount_Value'), 0)

        Mobile.takeScreenshot()

        KeywordUtil.logInfo('The user can able to view the Payment Amount_Value and Balance Amount_Value')

        'Validation for Cash'
        mode_1 = Mobile.getText(findTestObject('Mobile/Collection/Cash/Menu-Cash'), 0)

        Mobile.tap(findTestObject('Mobile/Collection/Cash/Menu-Cash'), 0)

        Mobile.clearText(findTestObject('Mobile/Collection/Cash/Amount'), 0, FailureHandling.OPTIONAL)

        Mobile.setText(findTestObject('Mobile/Collection/Cash/Amount'), single_pay.toString(), 0, FailureHandling.STOP_ON_FAILURE)

        Paid_Amount = Mobile.getText(findTestObject('Mobile/Collection/Cash/Amount'), 0)

        Mobile.tap(findTestObject('Mobile/Collection/APPLY_Btn'), 0)

        Paid_Amt = Mobile.getText(findTestObject('Mobile/Collection/Values/Paid_Amt'), 0)

        Paid_Amt2 = Paid_Amt.replaceAll('\\s', '')

        Exepected_Cash_Paid_Amount = Paid_Amt2.replaceAll('Paid', '')

        Mobile.verifyEqual(Double.parseDouble(Paid_Amount), Double.parseDouble(Exepected_Cash_Paid_Amount), FailureHandling.STOP_ON_FAILURE)

        Mobile.takeScreenshot()

        KeywordUtil.logInfo('The user can collect the amount by cash mode!')

        exlpath = 'DDF\\Mobile Input data\\Mobile Input data.xlsx'

        Sheetname = 'Collection'

        workbook01 = ExcelKeywords.getWorkbook(exlpath)

        sheet1 = ExcelKeywords.getExcelSheet(workbook01, Sheetname)

        ExcelKeywords.setValueToCellByIndex(sheet1, 1, 4, mode_1)

        ExcelKeywords.setValueToCellByIndex(sheet1, 1, 5, Exepected_Cash_Paid_Amount)

        ExcelKeywords.setValueToCellByIndex(sheet1, 1, 10, TransactionNo)

        ExcelKeywords.saveWorkbook(exlpath, workbook01)

        'Validation for Cheque'
        mode_2 = Mobile.getText(findTestObject('Mobile/Collection/Cheque/Cheque_Submenu'), 0)

        Mobile.tap(findTestObject('Mobile/Collection/Cheque/Cheque_Submenu'), 0)

        Mobile.verifyElementVisible(findTestObject('Mobile/Collection/Cheque/Cheque Screen_Title'), 0)

        Mobile.takeScreenshot()

        KeywordUtil.logInfo('The user can able to select cheque mode of payment')

        Mobile.setText(findTestObject('Mobile/Collection/Cheque/Enter_ChequeNo'), '12345', 0, FailureHandling.STOP_ON_FAILURE)

        String bound = Mobile.getAttribute(findTestObject('Mobile/Collection/Cheque/Select Bank'), 'bounds', 2, FailureHandling.OPTIONAL)

        x = Mobile.getAttribute(findTestObject('Mobile/Collection/Cheque/Select Bank'), 'x', 2, FailureHandling.OPTIONAL)

        Mobile.tap(findTestObject('Mobile/Collection/Cheque/Select Bank'), 0)

        Bank_Name = findTestData('Mobile Input Data/Collection').getValue('Bank_Name', 1)

        Mobile.sendKeys(findTestObject('Mobile/Collection/Cheque/Select Bank'), Bank_Name)

        Mobile.delay(2)

        String[] arr = bound.split(']')

        Start_Y_value = (arr[0])

        End_Y_value = (arr[1])

        String[] ar = Start_Y_value.split(',')

        Start_Y_value = (ar[1])

        KeywordUtil.logInfo(Start_Y_value)

        String[] ar1 = End_Y_value.split(',')

        End_Y_value = (ar1[1])

        KeywordUtil.logInfo(End_Y_value)

        y_point = (Integer.parseInt(End_Y_value) + 35)

        x_point = (Integer.parseInt(x) + 30)

        Mobile.tapAtPosition(x_point, y_point)

        //Mobile.tapAtPosition(450, 500)
        Mobile.delay(3)

        Mobile.tap(findTestObject('Mobile/Collection/Cheque/Select Branch'), 0)

        Mobile.delay(2)

        Mobile.tap(findTestObject('Mobile/Collection/Cheque/BranchName_1'), 0)

        Mobile.clearText(findTestObject('Mobile/Collection/Cheque/Enter_Amount_Field'), 0)

        Mobile.sendKeys(findTestObject('Mobile/Collection/Cheque/Enter_Amount_Field'), single_pay.toString())

        Paid_Amount = Mobile.getText(findTestObject('Mobile/Collection/Cheque/Enter_Amount_Field'), 0)

        Mobile.hideKeyboard(FailureHandling.OPTIONAL)

        Mobile.tap(findTestObject('Mobile/Collection/APPLY_Btn'), 0)

        Paid_Amt = Mobile.getText(findTestObject('Mobile/Collection/Cheque/Cheque Paid_field'), 0)

        Paid_Amt2 = Paid_Amt.replaceAll('\\s', '')

        Exepected_Cheque_Paid_Amount = Paid_Amt2.replaceAll('Paid', '')

        Mobile.verifyEqual(Double.parseDouble(Paid_Amount), Double.parseDouble(Exepected_Cheque_Paid_Amount), FailureHandling.STOP_ON_FAILURE)

        Mobile.takeScreenshot()

        KeywordUtil.logInfo('The user can collect the amount by Cheque mode!')

        ExcelKeywords.setValueToCellByIndex(sheet1, 1, 6, mode_2)

        ExcelKeywords.setValueToCellByIndex(sheet1, 1, 7, Exepected_Cheque_Paid_Amount)

        ExcelKeywords.saveWorkbook(exlpath, workbook01)

        'Validation for creditnote'
        mode_3 = Mobile.getText(findTestObject('Mobile/Collection/CreditNote/Credit Note_Submenu'), 0)

        Mobile.tap(findTestObject('Mobile/Collection/CreditNote/Credit Note_Submenu'), 0)

        Mobile.verifyElementVisible(findTestObject('Mobile/Collection/CreditNote/Credit Note Screen_Title'), 0)

        Mobile.takeScreenshot()

        KeywordUtil.logInfo('The user can able to select cheque mode of payment')

        Mobile.tap(findTestObject('Mobile/Collection/CreditNote/CreditNote_CheckBox'), 0)

        Mobile.getText(findTestObject('Mobile/Collection/CreditNote/CreditNote_AMT'), 0)

        Mobile.tap(findTestObject('Mobile/Collection/APPLY_Btn'), 0)

        Paid_Amt = Mobile.getText(findTestObject('Mobile/Collection/CreditNote/CreditNote_Paid'), 0)

        Paid_Amt2 = Paid_Amt.replaceAll('\\s', '')

        Exepected_Paid_Amount = Paid_Amt2.replaceAll('Paid', '')

        Mobile.verifyEqual(Double.parseDouble(Paid_Amount), Double.parseDouble(Exepected_Paid_Amount), FailureHandling.STOP_ON_FAILURE)

        Mobile.takeScreenshot()

        'The user can collect the amount by CreditNote mode!'
        ExcelKeywords.setValueToCellByIndex(sheet1, 1, 8, mode_3)

        ExcelKeywords.setValueToCellByIndex(sheet1, 1, 9, Exepected_Cheque_Paid_Amount)

        ExcelKeywords.saveWorkbook(exlpath, workbook01)

        Mobile.tap(findTestObject('Mobile/Collection/SAVE_Btn'), 0)

        Mobile.setText(findTestObject('Mobile/Collection/Enter Receipt Number'), findTestData('Mobile Input Data/CallAnalysis').getValue('InvoiceNo', 1), 0)

        Mobile.tap(findTestObject('Mobile/Common/Btn_OK'), 0)

        Mobile.tap(findTestObject('Mobile/Common/Icon_Back'), 0)

        Mobile.tap(findTestObject('Mobile/Common/Icon_Back'), 2, FailureHandling.OPTIONAL)

        Mobile.scrollToText(ScrollDate, FailureHandling.OPTIONAL)

        After_OS_Amt = Mobile.getText(findTestObject('Mobile/Collection/Values/OS_Amt'), 2, FailureHandling.OPTIONAL)

        GlobalVariable.C_OS_Amt = After_OS_Amt

        String pastdate = simpleDateFormat.format(new Date() - 1)

        Received_Amt = Mobile.getText(findTestObject('Mobile/Collection/Values/Received_Amt'), 2, FailureHandling.OPTIONAL)

        GlobalVariable.label = pastdate

        //Received_Amt = Mobile.getText(findTestObject('Mobile/Collection/Values/Received_Amt'), 2, FailureHandling.OPTIONAL)
        GlobalVariable.C_ReceivedAmt = Received_Amt

        Exepected_Paid_Amount = Transaction_Amt

        Mobile.verifyEqual(Double.parseDouble(Received_Amt), Double.parseDouble(Exepected_Paid_Amount), FailureHandling.STOP_ON_FAILURE)

        Mobile.takeScreenshot()

        KeywordUtil.logInfo(Received_Amt + ': Entered amount value should be display in recieved amt field')

        println('Entered amount value should be display in recieved amt field')

        Calculated_OS_Amt = (Double.parseDouble(Transaction_Amt) - Double.parseDouble(Exepected_Paid_Amount))

        Mobile.verifyEqual(Double.parseDouble(After_OS_Amt), Calculated_OS_Amt, FailureHandling.STOP_ON_FAILURE)

        Mobile.takeScreenshot()

        KeywordUtil.logInfo(After_OS_Amt + '  : OS amount should be displayed properly')

        Mobile.tap(findTestObject('Mobile/Common/Icon_Back'), 0)

        Mobile.delay(3)

        Mobile.swipe(0, 150, 0, 400, FailureHandling.OPTIONAL)

		Mobile.tap(findTestObject('Mobile/CallAnalysis/StoreActy_Close Call Btn'), 0)
		
		Collection_Amt = Mobile.getText(findTestObject('Mobile/CallAnalysis/Value-Collection Amount'), 0)
		
		Mobile.verifyEqual(Double.parseDouble(Collection_Amt), Double.parseDouble(Received_Amt), FailureHandling.STOP_ON_FAILURE)
		
		Mobile.takeScreenshot()
		
		KeywordUtil.logInfo(Collection_Amt + '  : Collection value should displayed properly')
		
		ExcelKeywords.setValueToCellByIndex(sheet1, 1, 3, Collection_Amt)
		
		ExcelKeywords.saveWorkbook(exlpath, workbook01)
		
		Mobile.tap(findTestObject('Mobile/Common/Icon_Back'), 0, FailureHandling.OPTIONAL)
		
		Mobile.delay(3)
		
		Mobile.swipe(0, 150, 0, 400, FailureHandling.OPTIONAL) 
		
		break

    case GlobalVariable.V158_ActivationKey:
        Mobile.verifyElementVisible(findTestObject('Mobile/Collection/Title/Invoice No'), 0)

        Mobile.verifyElementVisible(findTestObject('Mobile/Collection/Values/Get_InvoiceNo'), 0)

        Mobile.verifyElementVisible(findTestObject('Mobile/Collection/Title/Invoice Date'), 0)

        Mobile.verifyElementVisible(findTestObject('Mobile/Collection/Values/Get_InvoiceNo_Date'), 0)

        Mobile.verifyElementVisible(findTestObject('Mobile/Collection/Title/OS Amt'), 0)

        Mobile.verifyElementVisible(findTestObject('Mobile/Collection/Title/Due Date'), 0)

        Mobile.verifyElementVisible(findTestObject('Mobile/Collection/Values/Due Date_Value'), 0)

        Mobile.verifyElementVisible(findTestObject('Mobile/Collection/Title/Invoice Amt'), 0)

        Mobile.verifyElementVisible(findTestObject('Mobile/Collection/Values/Transaction_Amt'), 0)

        Mobile.verifyElementVisible(findTestObject('Mobile/Collection/Title/Received'), 0)

        Mobile.verifyElementVisible(findTestObject('Mobile/Collection/Values/Received_Amt'), 0)

        Mobile.takeScreenshot()

        KeywordUtil.logInfo('The user can view the list of Pending Invoices details')

        TransactionNo = Mobile.getText(findTestObject('Mobile/Collection/Values/Get_InvoiceNo'), 0)

        GlobalVariable.Transaction_No = TransactionNo

        Transaction_Amt = Mobile.getText(findTestObject('Mobile/Collection/Values/Transaction_Amt'), 0)

        GlobalVariable.C_TransactionAmt = Transaction_Amt

        pay = (Double.parseDouble(Transaction_Amt) / 3)

        BigDecimal single_pay = new BigDecimal(pay).setScale(0, RoundingMode.HALF_DOWN)

        KeywordUtil.logInfo(single_pay.toString())

        before_OS_Amt = Mobile.getText(findTestObject('Mobile/Collection/Values/OS_Amt'), 0)

        Mobile.tap(findTestObject('Mobile/Collection/Values/Get_InvoiceNo'), 0)

        Mobile.verifyElementVisible(findTestObject('Object Repository/Mobile/Collection/Blue_Tick'), 0)

        Mobile.takeScreenshot()

        KeywordUtil.logInfo('The user can able to select any invoice')

        Mobile.tap(findTestObject('Mobile/Collection/Pay_Btn'), 0)

        //Mobile.verifyElementVisible(findTestObject('Mobile/Collection/Bill Payment Screen_Title'),
        //	0)
        Mobile.takeScreenshot()

        KeywordUtil.logInfo('The user can able to navigate Bill payment screen while click the pay button')

        Mobile.verifyElementVisible(findTestObject('Mobile/Collection/Payment Amount_Value'), 0)

        Mobile.verifyElementVisible(findTestObject('Mobile/Collection/Balance Amount_Value'), 0)

        Mobile.takeScreenshot()

        KeywordUtil.logInfo('The user can able to view the Payment Amount_Value and Balance Amount_Value')

        'Validation for Cash'
        mode_1 = Mobile.getText(findTestObject('Mobile/Collection/Cash/Menu-Cash'), 0)

        Mobile.tap(findTestObject('Mobile/Collection/Cash/Menu-Cash'), 0)

        Mobile.clearText(findTestObject('Mobile/Collection/Cash/V158_Amount_Field'), 0, FailureHandling.OPTIONAL)

        Mobile.setText(findTestObject('Mobile/Collection/Cash/V158_Amount_Field'), single_pay.toString(), 0, FailureHandling.STOP_ON_FAILURE)

        Paid_Amount = Mobile.getText(findTestObject('Mobile/Collection/Cash/V158_Amount_Field'), 0)

        Mobile.tap(findTestObject('Mobile/Collection/APPLY_Btn'), 0)

        Paid_Amt = Mobile.getText(findTestObject('Mobile/Collection/Values/Paid_Amt'), 0)

        Paid_Amt2 = Paid_Amt.replaceAll('\\s', '')

        Exepected_Cash_Paid_Amount = Paid_Amt2.replaceAll('Paid', '')

        Mobile.verifyEqual(Double.parseDouble(Paid_Amount), Double.parseDouble(Exepected_Cash_Paid_Amount), FailureHandling.STOP_ON_FAILURE)

        Mobile.takeScreenshot()

        KeywordUtil.logInfo('The user can collect the amount by cash mode!')

        exlpath = 'DDF\\Mobile Input data\\Mobile Input data.xlsx'

        Sheetname = 'Collection'

        workbook01 = ExcelKeywords.getWorkbook(exlpath)

        sheet1 = ExcelKeywords.getExcelSheet(workbook01, Sheetname)

        ExcelKeywords.setValueToCellByIndex(sheet1, 1, 4, mode_1)

        ExcelKeywords.setValueToCellByIndex(sheet1, 1, 5, Exepected_Cash_Paid_Amount)

        ExcelKeywords.setValueToCellByIndex(sheet1, 1, 10, TransactionNo)

        ExcelKeywords.saveWorkbook(exlpath, workbook01)

        'Validation for Cheque'
        mode_2 = Mobile.getText(findTestObject('Mobile/Collection/Cheque/Cheque_Submenu'), 0)

        Mobile.tap(findTestObject('Mobile/Collection/Cheque/Cheque_Submenu'), 0)

        Mobile.verifyElementVisible(findTestObject('Mobile/Collection/Cheque/Cheque Screen_Title'), 0)

        Mobile.takeScreenshot()

        KeywordUtil.logInfo('The user can able to select cheque mode of payment')

        Mobile.setText(findTestObject('Mobile/Collection/Cheque/V158_Enter_Cheque_No'), '12345', 0, FailureHandling.STOP_ON_FAILURE)
		
		x = Mobile.getAttribute(findTestObject('Mobile/Collection/Cheque/V158_BankName'), 'x', 2, FailureHandling.OPTIONAL)

        y = Mobile.getAttribute(findTestObject('Mobile/Collection/Cheque/V158_BankName'), 'y', 2, FailureHandling.OPTIONAL)

        height = Mobile.getAttribute(findTestObject('Mobile/Collection/Cheque/V158_BankName'), 'height', 2, FailureHandling.OPTIONAL)
		
		x_point = Integer.parseInt(x) + 50
		
		y_point = Integer.parseInt(y) + Integer.parseInt(height) + 10

        Mobile.tap(findTestObject('Mobile/Collection/Cheque/V158_BankName'), 0)

        Bank_Name = findTestData('Mobile Input Data/Collection').getValue('Bank_Name', 1)
		
		Mobile.sendKeys(findTestObject('Mobile/Collection/Cheque/V158_BankName'), 'state' , FailureHandling.STOP_ON_FAILURE)
		
//		Mobile.setText(findTestObject('Mobile/Collection/Cheque/V158_BankName'), Bank_Name, 5, FailureHandling.STOP_ON_FAILURE)
//		
//		Mobile.setText(findTestObject('Mobile/Collection/Cheque/V158_BankName'), Bank_Name, 0)

        Mobile.delay(2)

        Mobile.tapAtPosition(x_point, y_point)
 
        Mobile.delay(3)
		
		x = Mobile.getAttribute(findTestObject('Mobile/Collection/Cheque/V158_BranchName'), 'x', 2, FailureHandling.OPTIONAL)
		
	    y = Mobile.getAttribute(findTestObject('Mobile/Collection/Cheque/V158_BranchName'), 'y', 2, FailureHandling.OPTIONAL)

		height = Mobile.getAttribute(findTestObject('Mobile/Collection/Cheque/V158_BranchName'), 'height', 2, FailureHandling.OPTIONAL)
		
		x_point = Integer.parseInt(x) + 50
		
		y_point = Integer.parseInt(y) + Integer.parseInt(height) + 10

       Mobile.tap(findTestObject('Mobile/Collection/Cheque/V158_BranchName'), 0)

       Branch_Name = findTestData('Mobile Input Data/Collection').getValue('Bank_Name', 2)

	   //Mobile.setText(findTestObject('Mobile/Collection/Cheque/V158_BranchName'), Branch_Name, 5, FailureHandling.STOP_ON_FAILURE)

	   Mobile.sendKeys(findTestObject('Mobile/Collection/Cheque/V158_BranchName'), Branch_Name , FailureHandling.STOP_ON_FAILURE)
	   
	   Mobile.delay(2)
	   
	   Mobile.tapAtPosition(x_point, y_point)

       Mobile.clearText(findTestObject('Mobile/Collection/Cheque/V158_Amount_Field'), 0)

       Mobile.setText(findTestObject('Mobile/Collection/Cheque/V158_Amount_Field'), single_pay.toString(), 5, FailureHandling.STOP_ON_FAILURE)

       Paid_Amount = Mobile.getText(findTestObject('Mobile/Collection/Cheque/V158_Amount_Field'), 0)

       Mobile.hideKeyboard(FailureHandling.OPTIONAL)

       Mobile.tap(findTestObject('Mobile/Collection/APPLY_Btn'), 0)

        Paid_Amt = Mobile.getText(findTestObject('Mobile/Collection/Cheque/Cheque Paid_field'), 0)

        Paid_Amt2 = Paid_Amt.replaceAll('\\s', '')

        Exepected_Cheque_Paid_Amount = Paid_Amt2.replaceAll('Paid', '')

        Mobile.verifyEqual(Double.parseDouble(Paid_Amount), Double.parseDouble(Exepected_Cheque_Paid_Amount), FailureHandling.STOP_ON_FAILURE)

        Mobile.takeScreenshot()

        KeywordUtil.logInfo('The user can collect the amount by Cheque mode!')

        ExcelKeywords.setValueToCellByIndex(sheet1, 1, 6, mode_2)

        ExcelKeywords.setValueToCellByIndex(sheet1, 1, 7, Exepected_Cheque_Paid_Amount)

        ExcelKeywords.saveWorkbook(exlpath, workbook01)

        'Validation for creditnote'
        mode_3 = Mobile.getText(findTestObject('Mobile/Collection/CreditNote/Credit Note_Submenu'), 0)

        Mobile.tap(findTestObject('Mobile/Collection/CreditNote/Credit Note_Submenu'), 0)

        Mobile.verifyElementVisible(findTestObject('Mobile/Collection/CreditNote/Credit Note Screen_Title'), 0)

        Mobile.takeScreenshot()

        KeywordUtil.logInfo('The user can able to select cheque mode of payment')

        Mobile.tap(findTestObject('Mobile/Collection/CreditNote/CreditNote_CheckBox'), 0)

        Mobile.getText(findTestObject('Mobile/Collection/CreditNote/CreditNote_AMT'), 0)

        Mobile.tap(findTestObject('Mobile/Collection/APPLY_Btn'), 0)

        Paid_Amt = Mobile.getText(findTestObject('Mobile/Collection/CreditNote/CreditNote_Paid'), 0)

        Paid_Amt2 = Paid_Amt.replaceAll('\\s', '')

        Exepected_Paid_Amount = Paid_Amt2.replaceAll('Paid', '')

        Mobile.verifyEqual(Double.parseDouble(Paid_Amount), Double.parseDouble(Exepected_Paid_Amount), FailureHandling.STOP_ON_FAILURE)

        Mobile.takeScreenshot()

        'The user can collect the amount by CreditNote mode!'
        ExcelKeywords.setValueToCellByIndex(sheet1, 1, 8, mode_3)

        ExcelKeywords.setValueToCellByIndex(sheet1, 1, 9, Exepected_Cheque_Paid_Amount)

        ExcelKeywords.saveWorkbook(exlpath, workbook01)

        Mobile.tap(findTestObject('Mobile/Collection/SAVE_Btn'), 0)

        Mobile.setText(findTestObject('Mobile/Collection/Enter Receipt Number'), findTestData('Mobile Input Data/CallAnalysis').getValue('InvoiceNo', 1), 0)

        Mobile.tap(findTestObject('Mobile/Common/Btn_OK'), 0)

        Mobile.tap(findTestObject('Mobile/Common/Icon_Back'), 0)

        Mobile.tap(findTestObject('Mobile/Common/Icon_Back'), 2, FailureHandling.OPTIONAL)

        Mobile.scrollToText(ScrollDate, FailureHandling.OPTIONAL)

        After_OS_Amt = Mobile.getText(findTestObject('Mobile/Collection/Values/OS_Amt'), 2, FailureHandling.OPTIONAL)

        GlobalVariable.C_OS_Amt = After_OS_Amt

        String pastdate = simpleDateFormat.format(new Date() - 1)

        Received_Amt = Mobile.getText(findTestObject('Mobile/Collection/Values/Received_Amt'), 2, FailureHandling.OPTIONAL)

        GlobalVariable.label = pastdate

        //Received_Amt = Mobile.getText(findTestObject('Mobile/Collection/Values/Received_Amt'), 2, FailureHandling.OPTIONAL)
        
		GlobalVariable.C_ReceivedAmt = Received_Amt

        Exepected_Paid_Amount = Transaction_Amt

        Mobile.verifyEqual(Double.parseDouble(Received_Amt), Double.parseDouble(Exepected_Paid_Amount), FailureHandling.STOP_ON_FAILURE)

        Mobile.takeScreenshot()

        KeywordUtil.logInfo(Received_Amt + ': Entered amount value should be display in recieved amt field')

        println('Entered amount value should be display in recieved amt field')

        Calculated_OS_Amt = (Double.parseDouble(Transaction_Amt) - Double.parseDouble(Exepected_Paid_Amount))

        Mobile.verifyEqual(Double.parseDouble(After_OS_Amt), Calculated_OS_Amt, FailureHandling.STOP_ON_FAILURE)

        Mobile.takeScreenshot()

        KeywordUtil.logInfo(After_OS_Amt + '  : OS amount should be displayed properly')

        Mobile.tap(findTestObject('Mobile/Common/Icon_Back'), 0)

        Mobile.delay(3)

        Mobile.swipe(0, 150, 0, 400, FailureHandling.OPTIONAL)

		Mobile.tap(findTestObject('Mobile/CallAnalysis/StoreActy_Close Call Btn'), 0)
		
		Collection_Amt = Mobile.getText(findTestObject('Mobile/CallAnalysis/Value-Collection Amount'), 0)
		
		Mobile.verifyEqual(Double.parseDouble(Collection_Amt), Double.parseDouble(Received_Amt), FailureHandling.STOP_ON_FAILURE)
		
		Mobile.takeScreenshot()
		
		KeywordUtil.logInfo(Collection_Amt + '  : Collection value should displayed properly')
		
		ExcelKeywords.setValueToCellByIndex(sheet1, 1, 3, Collection_Amt)
		
		ExcelKeywords.saveWorkbook(exlpath, workbook01)
		
		Mobile.tap(findTestObject('Mobile/Common/Icon_Back'), 0, FailureHandling.OPTIONAL)
		
		Mobile.delay(3)
		
		Mobile.swipe(0, 150, 0, 400, FailureHandling.OPTIONAL)
		
		break
}



