import java.sql.Connection as Connection
import java.sql.DriverManager as DriverManager
import java.sql.ResultSet as ResultSet
import java.sql.Statement as Statement
import java.text.SimpleDateFormat as SimpleDateFormat
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.mobile.keyword.internal.MobileDriverFactory as MobileDriverFactory
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import internal.GlobalVariable as GlobalVariable
import io.appium.java_client.AppiumDriver as AppiumDriver
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import org.openqa.selenium.WebElement as WebElement
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import org.junit.Assert as Assert
import java.util.ArrayList as ArrayList
import java.util.Collections as Collections
import java.util.Comparator as Comparator
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

// Mobile.callTestCase(findTestCase('Product_Mobile/Batch/CT_Get_DP_Price'), [:], FailureHandling.STOP_ON_FAILURE)
//Mobile.startApplication(GlobalVariable.APK_File, false)
//
//Mobile.delay(5)
//
//Mobile.tap(findTestObject('Mobile/Common/Btn_Menu'), 0)
WebUI.callTestCase(findTestCase('Product_Mobile/Batch/SA_01_02_03'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('Product_Mobile/Common/Mobile_Login/VanSeller_Login-With Reset'), [:], FailureHandling.STOP_ON_FAILURE)

AppiumDriver driver = MobileDriverFactory.getDriver()

sheet_name = 'VanLoad'

file_name = 'Mobile Input data'

ArrayList<String> SKU_Name = CustomKeywords.'poi.Automation.GetAllDataRow_Flag.getAllDataRow'(file_name, sheet_name, 'Sku_Name')

ArrayList<String> Batch_Name = CustomKeywords.'poi.Automation.GetAllDataRow_Flag.getAllDataRow'(file_name, sheet_name, 'Batch_Name')

ArrayList<String> case_Quantity = CustomKeywords.'poi.Automation.GetAllDataRow_Flag.getAllDataRow'(file_name, sheet_name, 
    'Case_Qty')

ArrayList<String> Piece_Quantity = CustomKeywords.'poi.Automation.GetAllDataRow_Flag.getAllDataRow'(file_name, sheet_name, 
    'Piece_Qty')

ArrayList<String> DP_Piece = CustomKeywords.'poi.Automation.GetAllDataRow_Flag.getAllDataRow'(file_name, sheet_name, 'DP_Piece')

ArrayList<String> Case_Size = CustomKeywords.'poi.Automation.GetAllDataRow_Flag.getAllDataRow'(file_name, sheet_name, 'Case_Size')

'The current stock view screen should display "No data available" before accepting the vanload.'
WebUI.callTestCase(findTestCase('Product_Mobile/Common/Seller_Menu_Selection'), [('MenuName') : findTestData('Mobile Input Data/Common').getValue(
            'Menu', 18), ('SubMenuName') : findTestData('Mobile Input Data/Common').getValue('Menu', 20)], FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

//Mobile.verifyElementVisible(findTestObject('Mobile/LoadManagement/CurrentStockView/No data available'), 0)
Mobile.verifyElementNotExist(findTestObject('Mobile/LoadManagement/CurrentStockView/Product_Name'), 0)

Mobile.tap(findTestObject('Mobile/Common/Icon_Back'), 0)

Mobile.tap(findTestObject('Mobile/LoadManagement/SubMenu-Van Load'), 0)

'Following fields should be displayed properly:1.Header - Van Load Acceptenace, 2.Label - Loading No, Total lines, Total Value, 3.Button - Accept'
Mobile.takeScreenshot()

Mobile.verifyElementVisible(findTestObject('Mobile/LoadManagement/Vanload/VanLoad-Title'), 0)

Mobile.verifyElementVisible(findTestObject('Mobile/LoadManagement/Vanload/Lable_Loading No'), 0)

Mobile.verifyElementVisible(findTestObject('Mobile/LoadManagement/Vanload/Lable_Total_Lines'), 0)

Mobile.verifyElementVisible(findTestObject('Mobile/LoadManagement/Vanload/Lable_Total_Value'), 0)

Mobile.verifyElementVisible(findTestObject('Mobile/LoadManagement/Vanload/VanLoadScreen-Accept Btn'), 0)

'"Please select any one item" toast message should be displayed when clicked "Accept" and "Reject" button without selection vanload number.'
Mobile.tap(findTestObject('Mobile/LoadManagement/Vanload/VanLoadScreen-Accept Btn'), 0)

Actualtoastmessage = driver.findElement(By.xpath('//android.widget.Toast[1]')).getText()

println(Actualtoastmessage)

Mobile.verifyMatch(Actualtoastmessage, findTestData('Mobile Input Data/VanLoad').getValue('Alerts', 2), false)

Mobile.takeScreenshot()

Mobile.tap(findTestObject('Mobile/LoadManagement/Vanload/VanLoadScreen-RejectBtn'), 0)

Actualtoastmessage = driver.findElement(By.xpath('//android.widget.Toast[1]')).getText()

KeywordUtil.logInfo(Actualtoastmessage)

Mobile.verifyMatch(Actualtoastmessage, findTestData('Mobile Input Data/VanLoad').getValue('Alerts', 2), false)

Mobile.takeScreenshot()

'1.VanLoad Number, 2.Vanload Date, 3.Total Vanload number, 4. Total Vanload Value are should be displayed properly on the inner vanload acceptenance screen from the VanLoad_Header table.'
GlobalVariable.VanLoad_No = findTestData('Mobile Input Data/VanLoad').getValue('Vanload_No', 1)

Mobile.scrollToText(GlobalVariable.VanLoad_No, FailureHandling.OPTIONAL)

Mobile.takeScreenshot()

Mobile.verifyElementVisible(findTestObject('Mobile/LoadManagement/Vanload/VanLoading No-(Global)'), 5)

String pattern = 'yyyy/MM/dd'

SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern)

String currentdate = simpleDateFormat.format(new Date())

KeywordUtil.logInfo(currentdate)

Mobile.verifyElementText(findTestObject('Mobile/LoadManagement/Vanload/Date(Global))'), currentdate)

Total_lines = SKU_Name.size()

Mobile.verifyElementText(findTestObject('Mobile/LoadManagement/Vanload/TotalLines(Global)'), Total_lines.toString())

Double totalvalue = 0

for (int i = 0; i < SKU_Name.size(); i++) {
    totalvalue += (((Double.parseDouble(case_Quantity.get(i)) * Double.parseDouble(Case_Size.get(i))) + Double.parseDouble(
        Piece_Quantity.get(i))) * Double.parseDouble(DP_Piece.get(i)))
}

Actual_Total_Value = Mobile.getText(findTestObject('Mobile/LoadManagement/Vanload/TotalValue(Global)'), 0)

Actual_Total_Value = WebUI.callTestCase(findTestCase('Product_Mobile/Common/RemoveCommaInString'), [('GivenText') : Actual_Total_Value], 
    FailureHandling.STOP_ON_FAILURE)

Mobile.verifyEqual(Double.parseDouble(Actual_Total_Value), totalvalue)

Mobile.tap(findTestObject('Mobile/LoadManagement/Vanload/Global_RadioIcon'), 3, FailureHandling.STOP_ON_FAILURE)

'To verify whether radio icon appears enabled when tap the radio icon'
Mobile.takeScreenshot()

Mobile.verifyElementVisible(findTestObject('Mobile/LoadManagement/Vanload/Global_RadioIcon_Enabled'), 0)

'To verify that the popup screen is displayed when either the accept or reject button is clicked.'
Mobile.tap(findTestObject('Mobile/LoadManagement/Vanload/VanLoadScreen-RejectBtn'), 0)

Mobile.takeScreenshot()

Mobile.verifyElementVisible(findTestObject('Mobile/LoadManagement/Vanload/Are you sure to reject stock'), 0)

Mobile.verifyElementVisible(findTestObject('Mobile/LoadManagement/Vanload/CANCEL-Btn'), 0)

Mobile.verifyElementVisible(findTestObject('Mobile/LoadManagement/Vanload/YES-Btn'), 0)

Mobile.tap(findTestObject('Mobile/LoadManagement/Vanload/CANCEL-Btn'), 0)

'To verify whether that the popup screen closes when the CANCEL button is clicked after Reject button has been clicked.'
Mobile.takeScreenshot()

Mobile.verifyElementNotExist(findTestObject('Mobile/LoadManagement/Vanload/Are you sure to reject stock'), 1)

Mobile.tap(findTestObject('Mobile/LoadManagement/Vanload/VanLoadScreen-Accept Btn'), 0)

Mobile.takeScreenshot()

Mobile.verifyElementVisible(findTestObject('Mobile/LoadManagement/Vanload/Are you sure to accept stock'), 0)

Mobile.verifyElementVisible(findTestObject('Mobile/LoadManagement/Vanload/CANCEL-Btn'), 0)

Mobile.verifyElementVisible(findTestObject('Mobile/LoadManagement/Vanload/YES-Btn'), 0)

Mobile.tap(findTestObject('Mobile/LoadManagement/Vanload/CANCEL-Btn'), 0)

'To verify whether that the popup screen closes when the CANCEL button is clicked after the Accept  button has been clicked.'
Mobile.takeScreenshot()

Mobile.verifyElementNotExist(findTestObject('Mobile/LoadManagement/Vanload/Are you sure to accept stock'), 1)

'1. "VanLoad Stock Rejected" toast message should be displayed'
Mobile.tap(findTestObject('Mobile/LoadManagement/Vanload/VanLoadScreen-RejectBtn'), 0)

Mobile.tap(findTestObject('Mobile/LoadManagement/Vanload/YES-Btn'), 0)

Actualtoastmessage = driver.findElement(By.xpath('//android.widget.Toast[1]')).getText()

KeywordUtil.logInfo(Actualtoastmessage)

///Mobile.verifyMatch(Actualtoastmessage, findTestData('Mobile Input Data/VanLoad').getValue('Alerts', 3), false)
Mobile.takeScreenshot()

'2. Popup screen should be closed when click YES button.'
Mobile.verifyElementNotExist(findTestObject('Mobile/LoadManagement/Vanload/Are you sure to reject stock'), 1)

Mobile.tap(findTestObject('Mobile/LoadManagement/SubMenu-Van Load'), 0)

Mobile.scrollToText(GlobalVariable.VanLoad_No, FailureHandling.OPTIONAL)

'Vanload number radio button should be displayed inactive after rejecting vanload.'
Mobile.takeScreenshot()

Mobile.verifyElementVisible(findTestObject('Mobile/LoadManagement/Vanload/Global_RadioIcon_Invisible'), 0)

exlpath = 'DDF\\Mobile Input data\\Mobile Input data.xlsx'

Sheetname = 'VanLoad'

workbook01 = ExcelKeywords.getWorkbook(exlpath)

sheet1 = ExcelKeywords.getExcelSheet(workbook01, Sheetname)

ExcelKeywords.setValueToCellByIndex(sheet1, 1, 11, GlobalVariable.VanLoad_No)

ExcelKeywords.saveWorkbook(exlpath, workbook01)

///////////////////////////////////////////
Mobile.callTestCase(findTestCase('Product_Mobile/Batch/CT_Stock_Allocation'), [:], FailureHandling.STOP_ON_FAILURE)

not_run: Mobile.startApplication(GlobalVariable.APK_File, false)

Mobile.delay(5)

//AppiumDriver driver = MobileDriverFactory.getDriver()
not_run: Mobile.tap(findTestObject('Mobile/Common/Btn_Menu'), 0)

WebUI.callTestCase(findTestCase('Product_Mobile/Common/Seller_Menu_Selection'), [('MenuName') : findTestData('Mobile Input Data/Common').getValue(
            'Menu', 18), ('SubMenuName') : findTestData('Mobile Input Data/Common').getValue('Menu', 1)], FailureHandling.STOP_ON_FAILURE)

'To verify refresh icon on the right top of the load management screen'
Mobile.tap(findTestObject('Mobile/LoadManagement/Vanload/VanLoad_Refresh'), 0)

Mobile.delay(5, FailureHandling.STOP_ON_FAILURE)

Mobile.tap(findTestObject('Mobile/LoadManagement/SubMenu-Van Load'), 0)

GlobalVariable.VanLoad_No = findTestData('Mobile Input Data/VanLoad').getValue('Vanload_No', 1)

Mobile.scrollToText(GlobalVariable.VanLoad_No, FailureHandling.OPTIONAL)

'To verify whether that the details of the van load are displayed on the inner van load acceptance screen from the VanLoad_Details table.'
Mobile.takeScreenshot()

Mobile.verifyElementVisible(findTestObject('Mobile/LoadManagement/Vanload/VanLoading No-(Global)'), 5)

Mobile.tap(findTestObject('Mobile/LoadManagement/Vanload/VanLoading No-(Global)'), 0)

Mobile.verifyElementVisible(findTestObject('Mobile/LoadManagement/Vanload/SortIcon'), 0)

Mobile.verifyElementVisible(findTestObject('Mobile/LoadManagement/Vanload/Label_Cases'), 0)

Mobile.verifyElementVisible(findTestObject('Mobile/LoadManagement/Vanload/Label_Pieces'), 0)

//for (int j = 0; j < SKU_Name.size(); j++) {
'To verify whether that the details of the van load are displayed on the inner van load acceptance screen from the VanLoad_Details table.'
for (int j = 0; j < 6; j++) {
    GlobalVariable.ProductName = SKU_Name.get(j)

    KeywordUtil.logInfo(GlobalVariable.ProductName)

    'To verify search icon functionality on the inner vanload details displayed screen.'
    Mobile.takeScreenshot()

    Mobile.tap(findTestObject('Mobile/Common/Icon_Search'), 0)

    Mobile.setText(findTestObject('Mobile/Common/Field_Search_EnterText'), GlobalVariable.ProductName, 0)

    Mobile.verifyElementVisible(findTestObject('Mobile/LoadManagement/Vanload/ProductName(Global)'), 0)

    GlobalVariable.ProductCode = SKU_Name.get(j)

    KeywordUtil.logInfo(GlobalVariable.ProductCode)

    Mobile.verifyElementVisible(findTestObject('Mobile/LoadManagement/Vanload/ProductCode(Global)'), 0)

    GlobalVariable.BatchName = Batch_Name.get(j)

    KeywordUtil.logInfo(GlobalVariable.BatchName)

    String BatchName = Mobile.getText(findTestObject('Mobile/LoadManagement/Vanload/BatchName(Global)'), 0)

    Boolean present = BatchName.contains(GlobalVariable.BatchName)

    Assert.assertTrue(present)

    KeywordUtil.logInfo(Piece_Quantity.get(j))

    Mobile.verifyElementText(findTestObject('Mobile/LoadManagement/Vanload/PieceQty(Global)'), Piece_Quantity.get(j), FailureHandling.STOP_ON_FAILURE)

    KeywordUtil.logInfo(case_Quantity.get(j))

    Mobile.verifyElementText(findTestObject('Mobile/LoadManagement/Vanload/CaseQty(Global)'), case_Quantity.get(j), FailureHandling.STOP_ON_FAILURE)

    TotalValueForEachBat = Mobile.getText(findTestObject('Mobile/LoadManagement/Vanload/TotalValueForEachBatch(Global)'), 
        0)

    TotalValueForEachBat = WebUI.callTestCase(findTestCase('Product_Mobile/Common/RemoveCommaInString'), [('GivenText') : TotalValueForEachBat], 
        FailureHandling.STOP_ON_FAILURE)

    AppliedQuantity = ((Double.parseDouble(case_Quantity.get(j)) * Double.parseDouble(Case_Size.get(j))) + Double.parseDouble(
        Piece_Quantity.get(j)))

    totalvalueforeachbatch = (AppliedQuantity * Double.parseDouble(DP_Piece.get(j)))

    Mobile.verifyEqual(Double.parseDouble(TotalValueForEachBat), totalvalueforeachbatch, FailureHandling.STOP_ON_FAILURE)

    String AppliedQty = Mobile.getText(findTestObject('Mobile/LoadManagement/Vanload/BatchName(Global)'), 0)

    String[] parts = AppliedQty.split(':')

    String quantity = (parts[2]).trim()

    Mobile.verifyEqual(AppliedQuantity, Double.parseDouble(quantity), FailureHandling.STOP_ON_FAILURE)

    Mobile.tap(findTestObject('Mobile/Common/Icon_X'), 0)
}

Mobile.delay(2, FailureHandling.STOP_ON_FAILURE)

Mobile.tap(findTestObject('Mobile/LoadManagement/Vanload/SortIcon'), 0)

'Verified - When selecting Sort Z to A - Order should be displayed properly'
Mobile.tap(findTestObject('Mobile/LoadManagement/Vanload/Sort Z to A'), 0)

Mobile.takeScreenshot()

ArrayList<String> Descending_skuList = new ArrayList<String>()

for (n = 1; n < 7; n++) {
    GlobalVariable.Index = n

    if (Mobile.verifyElementExist(findTestObject('Mobile/LoadManagement/Vanload/ProductName(Global-Index)'), 1, FailureHandling.OPTIONAL)) {
        sku = Mobile.getText(findTestObject('Mobile/LoadManagement/Vanload/ProductName(Global-Index)'), 0)

        Descending_skuList.add(sku)
    } else {
        break
    }
}

Collections.sort(Descending_skuList, Collections.reverseOrder())

for (m = 1; m < 7; m++) {
    GlobalVariable.Index = m

    if (Mobile.verifyElementExist(findTestObject('Mobile/LoadManagement/Vanload/ProductName(Global-Index)'), 1, FailureHandling.OPTIONAL)) {
        sku = Mobile.getText(findTestObject('Mobile/LoadManagement/Vanload/ProductName(Global-Index)'), 0)

        expected_sku = Descending_skuList.get(m - 1)

        Mobile.verifyMatch(sku, expected_sku, false, FailureHandling.STOP_ON_FAILURE)
    }
}

'Verified - When selecting Sort A to Z - Order should be displayed properly'
Mobile.tap(findTestObject('Mobile/LoadManagement/Vanload/SortIcon'), 0)

Mobile.tap(findTestObject('Mobile/LoadManagement/Vanload/Sort A to Z'), 0)

Mobile.takeScreenshot()

ArrayList<String> AscendingskuList = new ArrayList<String>()

for (p = 1; p < 7; p++) {
    GlobalVariable.Index = p

    if (Mobile.verifyElementExist(findTestObject('Mobile/LoadManagement/Vanload/ProductName(Global-Index)'), 1, FailureHandling.OPTIONAL)) {
        sku = Mobile.getText(findTestObject('Mobile/LoadManagement/Vanload/ProductName(Global-Index)'), 0)

        AscendingskuList.add(sku)
    } else {
        break
    }
}

Collections.sort(AscendingskuList)

for (r = 1; r < 7; r++) {
    GlobalVariable.Index = r

    if (Mobile.verifyElementExist(findTestObject('Mobile/LoadManagement/Vanload/ProductName(Global-Index)'), 1, FailureHandling.OPTIONAL)) {
        sku = Mobile.getText(findTestObject('Mobile/LoadManagement/Vanload/ProductName(Global-Index)'), 0)

        expected_sku = AscendingskuList.get(r - 1)

        Mobile.verifyMatch(sku, expected_sku, false, FailureHandling.STOP_ON_FAILURE)
    }
}

Mobile.tap(findTestObject('Mobile/Common/Icon_Back'), 0)

////
Mobile.scrollToText(GlobalVariable.VanLoad_No, FailureHandling.OPTIONAL)

Mobile.tap(findTestObject('Mobile/LoadManagement/Vanload/Global_RadioIcon'), 0)

Mobile.delay(2)

'To verify whether that the popup screen closes when the YES button is clicked after the Accept button has been clicked.'
Mobile.tap(findTestObject('Mobile/LoadManagement/Vanload/VanLoadScreen-Accept Btn'), 0)

Mobile.tap(findTestObject('Mobile/LoadManagement/Vanload/YES-Btn'), 0)

///-----once issue fixed enable below validation
//AppiumDriver driver1 = MobileDriverFactory.getDriver()
//
//Actualtoastmessage = driver.findElement(By.xpath("//android.widget.Toast[1]")).getText()
//
//KeywordUtil.logInfo(Actualtoastmessage)
//
//Mobile.verifyMatch(Actualtoastmessage, findTestData('Mobile Input Data/VanLoad').getValue('Alerts', 1), false)
Mobile.takeScreenshot()

Mobile.delay(15)

Mobile.verifyElementNotExist(findTestObject('Mobile/LoadManagement/Vanload/Are you sure to accept stock'), 1)

Mobile.tap(findTestObject('Mobile/LoadManagement/SubMenu-Van Load'), 0)

Mobile.scrollToText(GlobalVariable.VanLoad_No, FailureHandling.OPTIONAL)

///-----once issue fixed enable below validation
//'Vanload number radio button should be displayed inactive after accepting vanload.'
//Mobile.takeScreenshot()
//
//Mobile.verifyElementVisible(findTestObject('Mobile/LoadManagement/Vanload/Global_RadioIcon_Invisible'), 0)
Mobile.tap(findTestObject('Mobile/Common/Icon_Back'), 0)

exlpath = 'DDF\\Mobile Input data\\Mobile Input data.xlsx'

Sheetname = 'VanLoad'

workbook01 = ExcelKeywords.getWorkbook(exlpath)

sheet1 = ExcelKeywords.getExcelSheet(workbook01, Sheetname)

ExcelKeywords.setValueToCellByIndex(sheet1, 2, 11, GlobalVariable.VanLoad_No)

ExcelKeywords.saveWorkbook(exlpath, workbook01)

//////////////////////////////////////////////////////////
Mobile.callTestCase(findTestCase('Test Cases/Product_Mobile/Common/Sync'), [:], FailureHandling.STOP_ON_FAILURE)

//
//Mobile.startApplication(GlobalVariable.APK_File, false)
//
//Mobile.delay(5)
//
//Mobile.tap(findTestObject('Mobile/Common/Btn_Menu'), 0)
'To verify whether that Current Stock View screen is displayed while click on Current Stock View menu'
WebUI.callTestCase(findTestCase('Product_Mobile/Common/Seller_Menu_Selection'), [('MenuName') : findTestData('Mobile Input Data/Common').getValue(
            'Menu', 18), ('SubMenuName') : findTestData('Mobile Input Data/Common').getValue('Menu', 20)], FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

Mobile.verifyElementVisible(findTestObject('Mobile/LoadManagement/CurrentStockView/Title_Current Stock view'), 0)

Mobile.verifyElementVisible(findTestObject('Mobile/LoadManagement/CurrentStockView/Filter_Icon'), 0, FailureHandling.OPTIONAL)

Mobile.verifyElementVisible(findTestObject('Mobile/LoadManagement/CurrentStockView/Icon-Expand'), 0)

Mobile.verifyElementVisible(findTestObject('Mobile/LoadManagement/CurrentStockView/Label-Product Name'), 0)

Mobile.verifyElementVisible(findTestObject('Mobile/LoadManagement/CurrentStockView/Label-SIH CS'), 0)

Mobile.verifyElementVisible(findTestObject('Mobile/LoadManagement/CurrentStockView/Label-SIH'), 0)

Mobile.verifyElementVisible(findTestObject('Mobile/LoadManagement/CurrentStockView/Label-Total_Each_SKU'), 0)

Mobile.verifyElementVisible(findTestObject('Mobile/LoadManagement/CurrentStockView/Label-Total Value'), 0)

Mobile.verifyElementVisible(findTestObject('Mobile/Common/Icon_Back'), 0)

Mobile.verifyElementVisible(findTestObject('Mobile/Common/Icon_Search'), 0)

Mobile.tap(findTestObject('Object Repository/Mobile/LoadManagement/CurrentStockView/Icon-Expand'), 0)

List<String> listWithoutDuplicates = new ArrayList<String>(new HashSet(SKU_Name))

System.out.println('List without duplicates: ' + listWithoutDuplicates)

for (int a = 0; a < listWithoutDuplicates.size(); a++) {
    GlobalVariable.ProductName = listWithoutDuplicates.get(a)

    //    Mobile.tap(findTestObject('Mobile/Common/Icon_Search'), 0)
    //
    //    Mobile.setText(findTestObject('Mobile/Common/Field_Search_EnterText'), GlobalVariable.ProductName, 0)
    //
    //    Mobile.hideKeyboard()
    //
    //    Mobile.tap(findTestObject('Object Repository/Mobile/LoadManagement/CurrentStockView/Icon-Expand'), 0)
    Mobile.verifyElementVisible(findTestObject('Mobile/LoadManagement/CurrentStockView/ProductName(Global)'), 0)

    int Individual_SKU_Total_SIH = 0

    for (int b = 0; b < SKU_Name.size(); b++) {
        if (listWithoutDuplicates.get(a) == SKU_Name.get(b)) {
            'Verified -BatchName'
            GlobalVariable.BatchName = Batch_Name.get(b)

            KeywordUtil.logInfo(GlobalVariable.BatchName)

            Mobile.scrollToText(GlobalVariable.BatchName, FailureHandling.STOP_ON_FAILURE)

            String BBatchName = Mobile.getText(findTestObject('Object Repository/Mobile/LoadManagement/CurrentStockView/BatchName(Global)'), 
                0)

            Boolean present = BBatchName.contains(GlobalVariable.BatchName)

            Assert.assertTrue(present)

            'Verified -Batch Case Qty in the current stock view screen.'
            KeywordUtil.logInfo(case_Quantity.get(b))

            Mobile.verifyElementText(findTestObject('Object Repository/Mobile/LoadManagement/CurrentStockView/BatchCaseQty(Global)'), 
                case_Quantity.get(b), FailureHandling.STOP_ON_FAILURE)

            'Verified - Batch Piece Qty in the current stock view screen.'
            KeywordUtil.logInfo(Piece_Quantity.get(b))

            Mobile.verifyElementText(findTestObject('Object Repository/Mobile/LoadManagement/CurrentStockView/BatchPieceQty(Global)'), 
                Piece_Quantity.get(b), FailureHandling.STOP_ON_FAILURE)

            Individual_SKU_Total_SIH += ((Integer.parseInt(case_Quantity.get(b)) * Integer.parseInt(Case_Size.get(b))) + 
            Integer.parseInt(Piece_Quantity.get(b)))

            GlobalVariable.Qty = Individual_SKU_Total_SIH

            GlobalVariable.CaseSize = Integer.parseInt(Case_Size.get(b))

            GlobalVariable.BasePrice = DP_Piece.get(b)
        }
    }
    
    Individual_SKU_TotalSIH = GlobalVariable.Qty

    //Case_Size = GlobalVariable.CaseSize
    DPprice = GlobalVariable.BasePrice

    int quotient = Individual_SKU_TotalSIH / GlobalVariable.CaseSize

    int remainder = Individual_SKU_TotalSIH % GlobalVariable.CaseSize

    total_for_each_Sku = (Individual_SKU_TotalSIH * Double.parseDouble(DPprice))

    SIH_CS = quotient

    SIH = remainder

    Mobile.verifyElementText(findTestObject('Mobile/LoadManagement/CurrentStockView/CaseQty(Global)'), SIH_CS.toString(), 
        FailureHandling.STOP_ON_FAILURE)

    Mobile.verifyElementText(findTestObject('Mobile/LoadManagement/CurrentStockView/PieceQty(Global)'), SIH.toString(), 
        FailureHandling.STOP_ON_FAILURE)

    TotalForEachSKU = Mobile.getText(findTestObject('Object Repository/Mobile/LoadManagement/CurrentStockView/Total_Each_SKU(Global)'), 
        0)

    TotalForEachSKU = WebUI.callTestCase(findTestCase('Product_Mobile/Common/RemoveCommaInString'), [('GivenText') : TotalForEachSKU], 
        FailureHandling.STOP_ON_FAILURE)

    Mobile.verifyEqual(total_for_each_Sku, Double.parseDouble(TotalForEachSKU), FailureHandling.STOP_ON_FAILURE) // Mobile.tap(findTestObject('Mobile/Common/Icon_X'), 0)
}

Mobile.tap(findTestObject('Mobile/Common/Icon_FunnelFilter'), 5)

GlobalVariable.label = findTestData('Mobile Input Data/VanLoad').getValue('Menu', 1)

Mobile.tap(findTestObject('Mobile/Common/FilterScreen-MenuList(Global)'), 5)

GlobalVariable.label = findTestData('Mobile Input Data/VanLoad').getValue('Category', 1)

Mobile.tap(findTestObject('Mobile/Common/FilterScreen-SubMenuList(Global)'), 5)

Mobile.tap(findTestObject('Mobile/Common/Btn_Apply'), 0)

Mobile.delay(3)

Mobile.verifyElementVisible(findTestObject('Mobile/Common/Icon_Search'), 0)

Mobile.tap(findTestObject('Mobile/Common/Icon_Search'), 0)

Mobile.clearText(findTestObject('Mobile/Common/Field_Search_EnterText'), 0)

Mobile.setText(findTestObject('Mobile/Common/Field_Search_EnterText'), findTestData('Mobile Input Data/VanLoad').getValue(
        'Sku_Name', 1), 0)

Mobile.verifyElementText(findTestObject('Mobile/LoadManagement/CurrentStockView/Product_Name'), findTestData('Mobile Input Data/VanLoad').getValue(
        'Sku_Name', 1))

Mobile.takeScreenshot()

Mobile.tap(findTestObject('Object Repository/Mobile/LoadManagement/CurrentStockView/Icon-Expand'), 0)

Mobile.tap(findTestObject('Object Repository/Mobile/LoadManagement/CurrentStockView/Icon-Expand'), 0)

GlobalVariable.BatchName = findTestData('Mobile Input Data/VanLoad').getValue('Batch_Name', 1)

String BatchName = Mobile.getText(findTestObject('Object Repository/Mobile/LoadManagement/CurrentStockView/BatchName(Global)'), 
    0)

Boolean present = BatchName.contains(GlobalVariable.BatchName)

Assert.assertTrue(present)

Mobile.callTestCase(findTestCase('Product_Mobile/Batch/LM_08_14_19'), [:], FailureHandling.STOP_ON_FAILURE)

//1st DB
'VLH_Status column data should be updated from I to A when stock is accepetd.'
Accepted_Vanload_No = findTestData('Mobile Input Data/VanLoad').getValue('Verify_DB_Status', 2)

queryString = (((('select * from AppData_Van_Load_Header where VLH_AUH_Id =\'' + GlobalVariable.vanseller_user_id) + '\' and VLH_Reference_No =\'') + 
Accepted_Vanload_No) + '\' ')

KeywordUtil.logInfo(queryString)

String conn = (((((('jdbc:sqlserver://' + GlobalVariable.DB_URL) + ';databaseName=') + GlobalVariable.DB_Name) + ';user=') + 
GlobalVariable.DB_Username) + ';password=') + GlobalVariable.DB_Password

connection = DriverManager.getConnection(conn)

Statement stm = connection.createStatement()

ResultSet rs = stm.executeQuery(queryString)

def recordSet = rs

while (recordSet.next()) {
    Object data1 = recordSet.getObject('VLH_Status')

    KeywordUtil.logInfo(data1)

    Mobile.verifyMatch(data1, 'A', false, FailureHandling.STOP_ON_FAILURE)
}

//2nd DB
'VLH_Status column data should be updated from "I" to "C" when stock is rejected.'
Rejected_Vanload_No = findTestData('Mobile Input Data/VanLoad').getValue('Verify_DB_Status', 1)

queryString1 = (((('select * from AppData_Van_Load_Header where VLH_AUH_Id =\'' + GlobalVariable.vanseller_user_id) + '\' and VLH_Reference_No =\'') + 
Rejected_Vanload_No) + '\' ')

KeywordUtil.logInfo(queryString1)

String conn1 = (((((('jdbc:sqlserver://' + GlobalVariable.DB_URL) + ';databaseName=') + GlobalVariable.DB_Name) + ';user=') + 
GlobalVariable.DB_Username) + ';password=') + GlobalVariable.DB_Password

connection1 = DriverManager.getConnection(conn1)

Statement stm1 = connection1.createStatement()

ResultSet rs1 = stm1.executeQuery(queryString1)

def recordSet1 = rs1

while (recordSet1.next()) {
    Object data2 = recordSet1.getObject('VLH_Status')

    KeywordUtil.logInfo(data2)

    Mobile.verifyMatch(data2, 'C', false, FailureHandling.STOP_ON_FAILURE)
}

