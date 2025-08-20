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
import java.util.ArrayList as ArrayList
import java.util.Collections as Collections
import java.util.HashMap as HashMap
import java.util.List as List
import java.util.Map as Map
import com.kms.katalon.core.mobile.keyword.internal.MobileDriverFactory as MobileDriverFactory
import java.sql.Connection as Connection
import java.sql.DriverManager as DriverManager
import java.sql.ResultSet as ResultSet
import java.sql.Statement as Statement
import java.text.SimpleDateFormat as SimpleDateFormat
import io.appium.java_client.AppiumDriver as AppiumDriver
import org.openqa.selenium.WebElement as WebElement
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import org.openqa.selenium.By as By
import org.openqa.selenium.WebDriver as WebDriver
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import org.openqa.selenium.chrome.ChromeDriver as ChromeDriver
import com.kms.katalon.keyword.excel.ExcelKeywords as ExcelKeywords
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint

//Mobile.startApplication(GlobalVariable.APK_File, false)
//
//Mobile.tap(findTestObject('Mobile/Common/Btn_Menu'), 0)

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Call_Analysis'), [:], FailureHandling.STOP_ON_FAILURE)

if (Mobile.verifyElementExist(findTestObject('Object Repository/Mobile/MainMenu/Menu_Denomination'), 
    2, FailureHandling.OPTIONAL)) {
   
    'Load Management menu visible'
} else if (Mobile.verifyElementExist(findTestObject('Mobile/Common/Btn_Menu'), 2, FailureHandling.OPTIONAL)) {
    Mobile.tap(findTestObject('Mobile/Common/Btn_Menu'), 0)
} else {
    Mobile.callTestCase(findTestCase('Product_Mobile/Common/Mobile_Login/Relaunch the app'), [:], FailureHandling.CONTINUE_ON_FAILURE)

    Mobile.tap(findTestObject('Mobile/Common/Btn_Menu'), 0)
}

'Delete old data from collection XL sheet'
exlpath = 'DDF\\Mobile Input data\\Mobile Input data.xlsx'

Sheetname = 'Collection'

workbook01 = ExcelKeywords.getWorkbook(exlpath)

sheet1 = ExcelKeywords.getExcelSheet(workbook01, Sheetname)

for (int i = 1; i <= 10; i++) {
    ExcelKeywords.setValueToCellByIndex(sheet1, i, 14, '')

    ExcelKeywords.setValueToCellByIndex(sheet1, i, 15, '')
}

ExcelKeywords.saveWorkbook(exlpath, workbook01)

amount = findTestData('Mobile Input Data/Collection').getValue('AMT_1', 1)

CollectedCashamount = Double.parseDouble(amount)

Mobile.tap(findTestObject('Object Repository/Mobile/MainMenu/Menu_Denomination'), 0)

TodayCollection = Mobile.getText(findTestObject('Mobile/Denomination/TodayCollection'), 0)

Before_Enteredvalue = Mobile.getText(findTestObject('Mobile/Denomination/TotalEnteredvalue'), 0)

'To Verify that the collected cash type value is accurately displayed on the denomination screen.'
Mobile.verifyEqual(Double.parseDouble(TodayCollection), CollectedCashamount, FailureHandling.STOP_ON_FAILURE)

'To verify that before enter the currency quantity total entered value field is zero'
Before_Enteredvalue = Before_Enteredvalue.replaceAll('Total : ', '')

Mobile.verifyEqual(Integer.parseInt(Before_Enteredvalue), 0, FailureHandling.STOP_ON_FAILURE)

Mobile.tap(findTestObject('Mobile/Denomination/Save_Btn'), 0)

AppiumDriver driver = MobileDriverFactory.getDriver()

Actualtoastmessage = driver.findElementByXPath('//android.widget.Toast[1]').getText()

println(Actualtoastmessage)

Mobile.verifyMatch(Actualtoastmessage, findTestData('Mobile Input Data/Collection').getValue('Alert', 1), false)

Mobile.takeScreenshot()

List<Integer> denominations = new ArrayList<String>()

denominations.add(2000)

denominations.add(500)

denominations.add(200)

denominations.add(100)

denominations.add(50)

denominations.add(20)

denominations.add(10)

denominations.add(5)

denominations.add(2)

denominations.add(1)

Map<Integer, Integer> currencyCount = new HashMap()

Collections.sort(denominations, Collections.reverseOrder())

for (int denomination : denominations) {
    int count = CollectedCashamount / denomination

    if (count > 0) {
        currencyCount.put(denomination, count)

        CollectedCashamount %= denomination
    }
}

for (Map.Entry<Integer, Integer> entry : currencyCount.entrySet()) {
    System.out.println(((entry.getValue() + ' notes of ') + entry.getKey()) + ' rupees')

    if (entry.getKey() == 2000) {
        GlobalVariable.Title = 'Two Thousand *'

        if (Mobile.verifyElementNotExist(findTestObject('Mobile/Denomination/EnterCurrencyQty(global)'), 
            1, FailureHandling.OPTIONAL)) {
            Mobile.scrollToText(GlobalVariable.Title)

            Mobile.tap(findTestObject('Mobile/Denomination/EnterCurrencyQty(global)'), 0)

            Mobile.hideKeyboard()
        }
        
        Mobile.setText(findTestObject('Mobile/Denomination/EnterCurrencyQty(global)'), entry.getValue().toString(), 
            0)

        ActualAmt = Mobile.getText(findTestObject('Mobile/Denomination/EnterCurrencyAmt'), 0, FailureHandling.STOP_ON_FAILURE)

        ExpectedAmt = (entry.getValue() * entry.getKey())

        Mobile.verifyEqual(ExpectedAmt, ActualAmt)

        exlpath = 'DDF\\Mobile Input data\\Mobile Input data.xlsx'

        Sheetname = 'Collection'

        workbook01 = ExcelKeywords.getWorkbook(exlpath)

        sheet1 = ExcelKeywords.getExcelSheet(workbook01, Sheetname)

        ExcelKeywords.setValueToCellByIndex(sheet1, 1, 14, entry.getValue().toString())

        ExcelKeywords.setValueToCellByIndex(sheet1, 1, 15, ExpectedAmt)

        ExcelKeywords.saveWorkbook(exlpath, workbook01)
    }
    
    if (entry.getKey() == 500) {
        GlobalVariable.Title = 'Five Hundred *'

        if (Mobile.verifyElementNotExist(findTestObject('Mobile/Denomination/EnterCurrencyQty(global)'), 
            1, FailureHandling.OPTIONAL)) {
            Mobile.scrollToText(GlobalVariable.Title)

            Mobile.tap(findTestObject('Mobile/Denomination/EnterCurrencyQty(global)'), 0)

            Mobile.hideKeyboard()
        }
        
        Mobile.setText(findTestObject('Mobile/Denomination/EnterCurrencyQty(global)'), entry.getValue().toString(), 
            0)

        ActualAmt = Mobile.getText(findTestObject('Mobile/Denomination/EnterCurrencyAmt'), 0, FailureHandling.STOP_ON_FAILURE)

        ExpectedAmt = (entry.getValue() * entry.getKey())

        Mobile.verifyEqual(ExpectedAmt, ActualAmt)

        exlpath = 'DDF\\Mobile Input data\\Mobile Input data.xlsx'

        Sheetname = 'Collection'

        workbook01 = ExcelKeywords.getWorkbook(exlpath)

        sheet1 = ExcelKeywords.getExcelSheet(workbook01, Sheetname)

        ExcelKeywords.setValueToCellByIndex(sheet1, 2, 14, entry.getValue().toString())

        ExcelKeywords.setValueToCellByIndex(sheet1, 2, 15, ExpectedAmt)

        ExcelKeywords.saveWorkbook(exlpath, workbook01)
    }
    
    if (entry.getKey() == 200) {
        GlobalVariable.Title = 'Two Hundred *'

        if (Mobile.verifyElementNotExist(findTestObject('Mobile/Denomination/EnterCurrencyQty(global)'), 
            1, FailureHandling.OPTIONAL)) {
            Mobile.scrollToText(GlobalVariable.Title)

            Mobile.tap(findTestObject('Mobile/Denomination/EnterCurrencyQty(global)'), 0)

            Mobile.hideKeyboard()
        }
        
        Mobile.setText(findTestObject('Mobile/Denomination/EnterCurrencyQty(global)'), entry.getValue().toString(), 
            0)

        ActualAmt = Mobile.getText(findTestObject('Mobile/Denomination/EnterCurrencyAmt'), 0, FailureHandling.STOP_ON_FAILURE)

        ExpectedAmt = (entry.getValue() * entry.getKey())

        Mobile.verifyEqual(ExpectedAmt, ActualAmt)

        exlpath = 'DDF\\Mobile Input data\\Mobile Input data.xlsx'

        Sheetname = 'Collection'

        workbook01 = ExcelKeywords.getWorkbook(exlpath)

        sheet1 = ExcelKeywords.getExcelSheet(workbook01, Sheetname)

        ExcelKeywords.setValueToCellByIndex(sheet1, 3, 14, entry.getValue().toString())

        ExcelKeywords.setValueToCellByIndex(sheet1, 3, 15, ExpectedAmt)

        ExcelKeywords.saveWorkbook(exlpath, workbook01)
    }
    
    if (entry.getKey() == 100) {
        GlobalVariable.Title = 'One Hundred *'

        if (Mobile.verifyElementNotExist(findTestObject('Mobile/Denomination/EnterCurrencyQty(global)'), 
            1, FailureHandling.OPTIONAL)) {
            Mobile.scrollToText(GlobalVariable.Title)

            Mobile.tap(findTestObject('Mobile/Denomination/EnterCurrencyQty(global)'), 0)

            Mobile.hideKeyboard()
        }
        
        Mobile.setText(findTestObject('Mobile/Denomination/EnterCurrencyQty(global)'), entry.getValue().toString(), 
            0)

        ActualAmt = Mobile.getText(findTestObject('Mobile/Denomination/EnterCurrencyAmt'), 0, FailureHandling.STOP_ON_FAILURE)

        ExpectedAmt = (entry.getValue() * entry.getKey())

        Mobile.verifyEqual(ExpectedAmt, ActualAmt)

        exlpath = 'DDF\\Mobile Input data\\Mobile Input data.xlsx'

        Sheetname = 'Collection'

        workbook01 = ExcelKeywords.getWorkbook(exlpath)

        sheet1 = ExcelKeywords.getExcelSheet(workbook01, Sheetname)

        ExcelKeywords.setValueToCellByIndex(sheet1, 4, 14, entry.getValue().toString())

        ExcelKeywords.setValueToCellByIndex(sheet1, 4, 15, ExpectedAmt)

        ExcelKeywords.saveWorkbook(exlpath, workbook01)
    }
    
    if (entry.getKey() == 50) {
        GlobalVariable.Title = 'Fifty *'

        if (Mobile.verifyElementNotExist(findTestObject('Mobile/Denomination/EnterCurrencyQty(global)'), 
            1, FailureHandling.OPTIONAL)) {
            Mobile.scrollToText(GlobalVariable.Title)

            Mobile.tap(findTestObject('Mobile/Denomination/EnterCurrencyQty(global)'), 0)

            Mobile.hideKeyboard()
        }
        
        Mobile.setText(findTestObject('Mobile/Denomination/EnterCurrencyQty(global)'), entry.getValue().toString(), 
            0)

        ActualAmt = Mobile.getText(findTestObject('Mobile/Denomination/EnterCurrencyAmt'), 0, FailureHandling.STOP_ON_FAILURE)

        ExpectedAmt = (entry.getValue() * entry.getKey())

        Mobile.verifyEqual(ExpectedAmt, ActualAmt)

        exlpath = 'DDF\\Mobile Input data\\Mobile Input data.xlsx'

        Sheetname = 'Collection'

        workbook01 = ExcelKeywords.getWorkbook(exlpath)

        sheet1 = ExcelKeywords.getExcelSheet(workbook01, Sheetname)

        ExcelKeywords.setValueToCellByIndex(sheet1, 5, 14, entry.getValue().toString())

        ExcelKeywords.setValueToCellByIndex(sheet1, 5, 15, ExpectedAmt)

        ExcelKeywords.saveWorkbook(exlpath, workbook01)
    }
    
    if (entry.getKey() == 20) {
        GlobalVariable.Title = 'Twenty *'

        if (Mobile.verifyElementNotExist(findTestObject('Mobile/Denomination/EnterCurrencyQty(global)'), 
            1, FailureHandling.OPTIONAL)) {
            Mobile.scrollToText(GlobalVariable.Title)

            Mobile.tap(findTestObject('Mobile/Denomination/EnterCurrencyQty(global)'), 0)

            Mobile.hideKeyboard()
        }
        
        Mobile.setText(findTestObject('Mobile/Denomination/EnterCurrencyQty(global)'), entry.getValue().toString(), 
            0)

        ActualAmt = Mobile.getText(findTestObject('Mobile/Denomination/EnterCurrencyAmt'), 0, FailureHandling.STOP_ON_FAILURE)

        ExpectedAmt = (entry.getValue() * entry.getKey())

        Mobile.verifyEqual(ExpectedAmt, ActualAmt)

        exlpath = 'DDF\\Mobile Input data\\Mobile Input data.xlsx'

        Sheetname = 'Collection'

        workbook01 = ExcelKeywords.getWorkbook(exlpath)

        sheet1 = ExcelKeywords.getExcelSheet(workbook01, Sheetname)

        ExcelKeywords.setValueToCellByIndex(sheet1, 6, 14, entry.getValue().toString())

        ExcelKeywords.setValueToCellByIndex(sheet1, 6, 15, ExpectedAmt)

        ExcelKeywords.saveWorkbook(exlpath, workbook01)
    }
    
    if (entry.getKey() == 10) {
        GlobalVariable.Title = 'Ten *'

        if (Mobile.verifyElementNotExist(findTestObject('Mobile/Denomination/EnterCurrencyQty(global)'), 
            1, FailureHandling.OPTIONAL)) {
            Mobile.scrollToText(GlobalVariable.Title)

            Mobile.tap(findTestObject('Mobile/Denomination/EnterCurrencyQty(global)'), 0)

            Mobile.hideKeyboard()
        }
        
        Mobile.setText(findTestObject('Mobile/Denomination/EnterCurrencyQty(global)'), entry.getValue().toString(), 
            0)

        ActualAmt = Mobile.getText(findTestObject('Mobile/Denomination/EnterCurrencyAmt'), 0, FailureHandling.STOP_ON_FAILURE)

        ExpectedAmt = (entry.getValue() * entry.getKey())

        Mobile.verifyEqual(ExpectedAmt, ActualAmt)

        exlpath = 'DDF\\Mobile Input data\\Mobile Input data.xlsx'

        Sheetname = 'Collection'

        workbook01 = ExcelKeywords.getWorkbook(exlpath)

        sheet1 = ExcelKeywords.getExcelSheet(workbook01, Sheetname)

        ExcelKeywords.setValueToCellByIndex(sheet1, 7, 14, entry.getValue().toString())

        ExcelKeywords.setValueToCellByIndex(sheet1, 7, 15, ExpectedAmt)

        ExcelKeywords.saveWorkbook(exlpath, workbook01)
    }
    
    if (entry.getKey() == 5) {
        GlobalVariable.Title = 'Five *'

        if (Mobile.verifyElementNotExist(findTestObject('Mobile/Denomination/EnterCurrencyQty(global)'), 
            1, FailureHandling.OPTIONAL)) {
            Mobile.scrollToText(GlobalVariable.Title)

            Mobile.tap(findTestObject('Mobile/Denomination/EnterCurrencyQty(global)'), 0)

            Mobile.hideKeyboard()
        }
        
        Mobile.setText(findTestObject('Mobile/Denomination/EnterCurrencyQty(global)'), entry.getValue().toString(), 
            0)

        ActualAmt = Mobile.getText(findTestObject('Mobile/Denomination/EnterCurrencyAmt'), 0, FailureHandling.STOP_ON_FAILURE)

        ExpectedAmt = (entry.getValue() * entry.getKey())

        Mobile.verifyEqual(ExpectedAmt, ActualAmt)

        exlpath = 'DDF\\Mobile Input data\\Mobile Input data.xlsx'

        Sheetname = 'Collection'

        workbook01 = ExcelKeywords.getWorkbook(exlpath)

        sheet1 = ExcelKeywords.getExcelSheet(workbook01, Sheetname)

        ExcelKeywords.setValueToCellByIndex(sheet1, 8, 14, entry.getValue().toString())

        ExcelKeywords.setValueToCellByIndex(sheet1, 8, 15, ExpectedAmt)

        ExcelKeywords.saveWorkbook(exlpath, workbook01)
    }
    
    if (entry.getKey() == 2) {
        GlobalVariable.Title = 'Two *'

        if (Mobile.verifyElementNotExist(findTestObject('Mobile/Denomination/EnterCurrencyQty(global)'), 
            1, FailureHandling.OPTIONAL)) {
            Mobile.scrollToText(GlobalVariable.Title)

            Mobile.tap(findTestObject('Mobile/Denomination/EnterCurrencyQty(global)'), 0)

            Mobile.hideKeyboard()
        }
        
        Mobile.setText(findTestObject('Mobile/Denomination/EnterCurrencyQty(global)'), entry.getValue().toString(), 
            0)

        ActualAmt = Mobile.getText(findTestObject('Mobile/Denomination/EnterCurrencyAmt'), 0, FailureHandling.STOP_ON_FAILURE)

        ExpectedAmt = (entry.getValue() * entry.getKey())

        Mobile.verifyEqual(ExpectedAmt, ActualAmt)

        exlpath = 'DDF\\Mobile Input data\\Mobile Input data.xlsx'

        Sheetname = 'Collection'

        workbook01 = ExcelKeywords.getWorkbook(exlpath)

        sheet1 = ExcelKeywords.getExcelSheet(workbook01, Sheetname)

        ExcelKeywords.setValueToCellByIndex(sheet1, 9, 14, entry.getValue().toString())

        ExcelKeywords.setValueToCellByIndex(sheet1, 9, 15, ExpectedAmt)

        ExcelKeywords.saveWorkbook(exlpath, workbook01)
    }
    
    if (entry.getKey() == 1) {
        GlobalVariable.Title = 'One *'

        if (Mobile.verifyElementNotExist(findTestObject('Mobile/Denomination/EnterCurrencyQty(global)'), 
            1, FailureHandling.OPTIONAL)) {
            Mobile.scrollToText(GlobalVariable.Title)

            Mobile.tap(findTestObject('Mobile/Denomination/EnterCurrencyQty(global)'), 0)

            Mobile.hideKeyboard()
        }
        
        Mobile.setText(findTestObject('Mobile/Denomination/EnterCurrencyQty(global)'), entry.getValue().toString(), 
            0)

        ActualAmt = Mobile.getText(findTestObject('Mobile/Denomination/EnterCurrencyAmt'), 0, FailureHandling.STOP_ON_FAILURE)

        ExpectedAmt = (entry.getValue() * entry.getKey())

        Mobile.verifyEqual(ExpectedAmt, ActualAmt)

        exlpath = 'DDF\\Mobile Input data\\Mobile Input data.xlsx'

        Sheetname = 'Collection'

        workbook01 = ExcelKeywords.getWorkbook(exlpath)

        sheet1 = ExcelKeywords.getExcelSheet(workbook01, Sheetname)

        ExcelKeywords.setValueToCellByIndex(sheet1, 10, 14, entry.getValue().toString())

        ExcelKeywords.setValueToCellByIndex(sheet1, 10, 15, ExpectedAmt)

        ExcelKeywords.saveWorkbook(exlpath, workbook01)
    }
}

Test = Mobile.getText(findTestObject('Mobile/Denomination/EnterCurrencyQty(global)'), 0)

Mobile.setText(findTestObject('Mobile/Denomination/EnterCurrencyQty(global)'), '0', 0)

Test02 = Mobile.getText(findTestObject('Mobile/Denomination/TotalEnteredvalue'), 0)

Test02 = Test02.replaceAll('Total : ', '')

//Mobile.verifyGreaterThan(findTestObject('Mobile/Denomination/TodayCollection'), Double.parseDouble(Test02))
Mobile.verifyNotEqual(Double.parseDouble(Test02), CollectedCashamount, FailureHandling.STOP_ON_FAILURE)

Mobile.tap(findTestObject('Mobile/Denomination/Save_Btn'), 0)

Actualtoastmessage = driver.findElementByXPath('//android.widget.Toast[1]').getText()

println(Actualtoastmessage)

Mobile.verifyMatch(Actualtoastmessage, findTestData('Mobile Input Data/Collection').getValue('Alert', 1), false)

Mobile.takeScreenshot()

if (Mobile.verifyElementNotExist(findTestObject('Mobile/Denomination/EnterCurrencyQty(global)'), 1, 
    FailureHandling.OPTIONAL)) {
    Mobile.scrollToText(GlobalVariable.Title)

    Mobile.tap(findTestObject('Mobile/Denomination/EnterCurrencyQty(global)'), 0)

    Mobile.hideKeyboard()
}

Mobile.setText(findTestObject('Mobile/Denomination/EnterCurrencyQty(global)'), Test, 0)

TotalEnteredvalue_After = Mobile.getText(findTestObject('Mobile/Denomination/TotalEnteredvalue'), 0)

TotalEnteredvalue_After = TotalEnteredvalue_After.replaceAll('Total : ', '')

amount = findTestData('Mobile Input Data/Collection').getValue('AMT_1', 1)

CollectedCashamount = Double.parseDouble(amount)

Mobile.verifyEqual(Double.parseDouble(TotalEnteredvalue_After), CollectedCashamount, FailureHandling.STOP_ON_FAILURE)

Mobile.tap(findTestObject('Mobile/Denomination/Save_Btn'), 0)

Actualtoastmessage = driver.findElementByXPath('//android.widget.Toast[1]').getText()

println(Actualtoastmessage)

Mobile.verifyMatch(Actualtoastmessage, findTestData('Mobile Input Data/Collection').getValue('Alert', 2), false)

Mobile.takeScreenshot()

