import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
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
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import org.openqa.selenium.WebElement as WebElement
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import org.junit.Assert as Assert
import java.util.ArrayList as ArrayList
import java.util.Collections as Collections
import java.util.Comparator as Comparator

'SKU and batch quantity should be added in the current stock view screen after accepting the new vanload.'
Mobile.callTestCase(findTestCase('Product_Mobile/Batch/CT_Stock_Allocation'), [:], FailureHandling.STOP_ON_FAILURE)

Mobile.startApplication(GlobalVariable.APK_File, false)

Mobile.delay(5)

Mobile.tap(findTestObject('Mobile/Common/Btn_Menu'), 0)

WebUI.callTestCase(findTestCase('Product_Mobile/Common/Seller_Menu_Selection'), [('MenuName') : findTestData('Mobile Input Data/Common').getValue(
            'Menu', 18), ('SubMenuName') : findTestData('Mobile Input Data/Common').getValue('Menu', 1)], FailureHandling.STOP_ON_FAILURE)

'To verify refresh icon on the right top of the load management screen'
Mobile.tap(findTestObject('Mobile/LoadManagement/Vanload/VanLoad_Refresh'), 0)

Mobile.delay(5, FailureHandling.STOP_ON_FAILURE)

Mobile.tap(findTestObject('Mobile/LoadManagement/SubMenu-Van Load'), 0)

GlobalVariable.VanLoad_No = findTestData('Mobile Input Data/VanLoad').getValue('Vanload_No', 1)

Mobile.scrollToText(GlobalVariable.VanLoad_No, FailureHandling.OPTIONAL)

Mobile.tap(findTestObject('Mobile/LoadManagement/Vanload/Global_RadioIcon'), 0)

'To verify whether that the popup screen closes when the YES button is clicked after the Accept button has been clicked.'
Mobile.tap(findTestObject('Mobile/LoadManagement/Vanload/VanLoadScreen-Accept Btn'), 0)

Mobile.tap(findTestObject('Mobile/LoadManagement/Vanload/YES-Btn'), 0)

Mobile.delay(20)



WebUI.callTestCase(findTestCase('Product_Mobile/Common/Seller_Menu_Selection'), [('MenuName') : findTestData('Mobile Input Data/Common').getValue(
            'Menu', 18), ('SubMenuName') : findTestData('Mobile Input Data/Common').getValue('Menu', 20)], FailureHandling.STOP_ON_FAILURE)


Mobile.tap(findTestObject('Object Repository/Mobile/LoadManagement/CurrentStockView/Icon-Expand'), 0)

sheet_name = 'VanLoad'

file_name = 'Mobile Input data'

ArrayList<String> SKU_Name = CustomKeywords.'poi.Automation.GetAllDataRow_Flag.getAllDataRow'(file_name, sheet_name, 'Sku_Name')

List<String> listWithoutDuplicates = new ArrayList<String>(new HashSet(SKU_Name))

System.out.println('List without duplicates: ' + listWithoutDuplicates)

for (int a = 0; a < listWithoutDuplicates.size(); a++) {
    GlobalVariable.ProductName = listWithoutDuplicates.get(a)

//    Mobile.tap(findTestObject('Mobile/Common/Icon_Search'), 0)
//
//    Mobile.setText(findTestObject('Mobile/Common/Field_Search_EnterText'), GlobalVariable.ProductName, 0)
//	
//	Mobile.hideKeyboard()
//	
//	Mobile.tap(findTestObject('Object Repository/Mobile/LoadManagement/CurrentStockView/Icon-Expand'), 0)
//	
    Mobile.verifyElementVisible(findTestObject('Mobile/LoadManagement/CurrentStockView/ProductName(Global)'), 0)

    int Individual_SKU_Total_SIH_2 = 0

    for (int b = 0; b < SKU_Name.size(); b++) {
        if (listWithoutDuplicates.get(a) == SKU_Name.get(b)) {
            'Verified -BatchName'
            GlobalVariable.BatchName = findTestData('Mobile Input Data/VanLoad').getValue('Batch_Name', b + 1)

            KeywordUtil.logInfo(GlobalVariable.BatchName)

            String BatchName_2 = Mobile.getText(findTestObject('Object Repository/Mobile/LoadManagement/CurrentStockView/BatchName(Global)'), 0)

            Boolean present_2 = BatchName_2.contains(GlobalVariable.BatchName)

            Assert.assertTrue(present_2)

            Case_2 = findTestData('Mobile Input Data/VanLoad').getValue('Case_Qty', b + 1)

            //KeywordUtil.logInfo(Case_2)
            Piece_2 = findTestData('Mobile Input Data/VanLoad').getValue('Piece_Qty', b + 1)

            //KeywordUtil.logInfo(Piece_2)
            Batch_Case_Size = findTestData('Mobile Input Data/VanLoad').getValue('Case_Size', b + 1)

            Batch_SIH = (((Integer.parseInt(Case_2) * 2) * Integer.parseInt(Batch_Case_Size)) + (Integer.parseInt(Piece_2) * 2))

            KeywordUtil.logInfo(Batch_SIH.toString())

            int Batch_Case = Batch_SIH / Integer.parseInt(Batch_Case_Size)

            int Batch_Piece = Batch_SIH % Integer.parseInt(Batch_Case_Size)

            'Verified -Batch Case Qty in the current stock view screen.'
            KeywordUtil.logInfo(Batch_Case.toString())

            Mobile.verifyElementText(findTestObject('Object Repository/Mobile/LoadManagement/CurrentStockView/BatchCaseQty(Global)'), Batch_Case.toString(), FailureHandling.STOP_ON_FAILURE)

            'Verified - Batch Piece Qty in the current stock view screen.'
            KeywordUtil.logInfo(Batch_Piece.toString())

            Mobile.verifyElementText(findTestObject('Object Repository/Mobile/LoadManagement/CurrentStockView/BatchPieceQty(Global)'), Batch_Piece.toString(), FailureHandling.STOP_ON_FAILURE)

            Individual_SKU_Total_SIH_2 += Batch_SIH

            GlobalVariable.Qty = Individual_SKU_Total_SIH_2

            GlobalVariable.CaseSize = findTestData('Mobile Input Data/VanLoad').getValue('Case_Size', b + 1)

            GlobalVariable.BasePrice = findTestData('Mobile Input Data/VanLoad').getValue('DP_Piece', b + 1)
        }
    }
    
    int SIH_CS = Individual_SKU_Total_SIH_2 / Integer.parseInt(GlobalVariable.CaseSize)

    int SIH = Individual_SKU_Total_SIH_2 % Integer.parseInt(GlobalVariable.CaseSize)

    total_for_each_Sku = (Individual_SKU_Total_SIH_2 * Double.parseDouble(GlobalVariable.BasePrice))

    Mobile.verifyElementText(findTestObject('Mobile/LoadManagement/CurrentStockView/CaseQty(Global)'), SIH_CS.toString(), FailureHandling.STOP_ON_FAILURE)

    Mobile.verifyElementText(findTestObject('Mobile/LoadManagement/CurrentStockView/PieceQty(Global)'), SIH.toString(), FailureHandling.STOP_ON_FAILURE)

    TotalForEachSKU = Mobile.getText(findTestObject('Object Repository/Mobile/LoadManagement/CurrentStockView/Total_Each_SKU(Global)'), 0)
	
	TotalForEachSKU = WebUI.callTestCase(findTestCase('Product_Mobile/Common/RemoveCommaInString'), [('GivenText') : TotalForEachSKU],
		FailureHandling.STOP_ON_FAILURE)

    Mobile.verifyEqual(total_for_each_Sku, Double.parseDouble(TotalForEachSKU), FailureHandling.STOP_ON_FAILURE) 
		
	//Mobile.tap(findTestObject('Mobile/Common/Icon_X'), 0)
        
}

