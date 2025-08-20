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
import org.openqa.selenium.WebElement as WebElement
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.keyword.excel.ExcelKeywords as ExcelKeywords
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import org.openqa.selenium.Keys as Keys
import org.junit.Assert as Assert

not_run: Mobile.startApplication(GlobalVariable.APK_File, false)

not_run: Mobile.tap(findTestObject('Mobile/Common/Btn_Menu'), 0)

if (Mobile.verifyElementExist(findTestObject('Mobile/MainMenu/Menu_End of the Day'), 2, FailureHandling.OPTIONAL)) {
    'End of the Day menu visible'
} else {
    Mobile.callTestCase(findTestCase('Product_Mobile/Common/Mobile_Login/Relaunch the app'), [:], FailureHandling.CONTINUE_ON_FAILURE)

    Mobile.tap(findTestObject('Mobile/Common/Btn_Menu'), 0)
}

Mobile.verifyElementExist(findTestObject('Mobile/MainMenu/Menu_End of the Day'), 0)

Mobile.verifyElementVisible(findTestObject('Mobile/MainMenu/Menu_End of the Day'), 0)

Mobile.takeScreenshot()

'Stock Proposal Module verifications'
Mobile.tap(findTestObject('Mobile/MainMenu/Menu_End of the Day'), 0)

Mobile.verifyElementExist(findTestObject('Mobile/End of the Day/SubMenu_Stock Proposal'), 0)

Mobile.verifyElementVisible(findTestObject('Mobile/End of the Day/SubMenu_Stock Proposal'), 0)

Mobile.takeScreenshot()

Mobile.tap(findTestObject('Mobile/End of the Day/SubMenu_Stock Proposal'), 0)

Mobile.verifyElementExist(findTestObject('Mobile/End of the Day/StockProposal/Title_Stock Proposal'), 0)

Mobile.verifyElementVisible(findTestObject('Mobile/End of the Day/StockProposal/Title_Stock Proposal'), 0)

Mobile.verifyElementVisible(findTestObject('Mobile/End of the Day/StockProposal/SP_ProductNameField'), 0)

Mobile.verifyElementVisible(findTestObject('Mobile/End of the Day/StockProposal/SP_CaseQty_Field'), 0)

Mobile.verifyElementVisible(findTestObject('Mobile/End of the Day/StockProposal/SP_PieceQty_Field'), 0)

Mobile.takeScreenshot()

KeywordUtil.logInfo('Verified : the “Stock Proposal” screen')

Mobile.tap(findTestObject('Mobile/Common/Icon_Search'), 0)

GlobalVariable.ProductName = findTestData('Mobile Input Data/StockProposal').getValue('Product_Name', 1)

Mobile.setText(findTestObject('Mobile/Common/Field_Search_EnterText'), GlobalVariable.ProductName, 0)

Mobile.delay(2)

Mobile.verifyElementExist(findTestObject('Object Repository/Mobile/End of the Day/StockProposal/SP_ProductNameField'), 0)

Mobile.verifyElementText(findTestObject('Object Repository/Mobile/End of the Day/StockProposal/SP_ProductNameField'), GlobalVariable.ProductName)

KeywordUtil.logInfo(' This product from the product distributor screen correctly reflected in the stock proposal screen')

Mobile.takeScreenshot()

//////////////////////
Mobile.clearText(findTestObject('Mobile/Common/Field_Search_EnterText'), 2, FailureHandling.OPTIONAL)

Mobile.tap(findTestObject('Mobile/Common/Icon_FunnelFilter'), 5)

Mobile.hideKeyboard(FailureHandling.OPTIONAL)

GlobalVariable.label = findTestData('Mobile Input Data/StockProposal').getValue('Menu', 1)

Mobile.tap(findTestObject('Mobile/Common/FilterScreen-MenuList(Global)'), 5)

GlobalVariable.label = findTestData('Mobile Input Data/StockProposal').getValue('Category', 1)

Mobile.scrollToText(GlobalVariable.label, FailureHandling.OPTIONAL)

Mobile.tap(findTestObject('Mobile/Common/FilterScreen-SubMenuList(Global)'), 5, FailureHandling.OPTIONAL)

Mobile.tap(findTestObject('Mobile/Common/Btn_Apply'), 0)

Mobile.delay(3)

Mobile.setText(findTestObject('Mobile/Common/Field_Search_EnterText'), findTestData('Mobile Input Data/StockProposal').getValue('Product_Name', 1), 0)

Mobile.verifyElementVisible(findTestObject('Mobile/End of the Day/StockProposal/SP_ProductNameField'), 0)

Mobile.verifyElementText(findTestObject('Mobile/End of the Day/StockProposal/SP_ProductNameField'), findTestData('Mobile Input Data/StockProposal').getValue('Product_Name', 1))

Mobile.takeScreenshot()

KeywordUtil.logInfo('User verified the functionality of the product hierarchy filter icon in the Stock Proposal screen')

//////////////
Mobile.tap(findTestObject('Mobile/End of the Day/StockProposal/SP_CaseQty_Field'), 5, FailureHandling.STOP_ON_FAILURE)

Mobile.tap(findTestObject('Mobile/Common/Vertical keypad-BackSpace'), 2, FailureHandling.OPTIONAL)

Mobile.tap(findTestObject('Mobile/Common/Vertical keypad-BackSpace'), 2, FailureHandling.OPTIONAL)

GlobalVariable.CaseQty = findTestData('Mobile Input Data/StockProposal').getValue('CaseQty', 1)

GlobalVariable.keypadValue = GlobalVariable.CaseQty

Mobile.tap(findTestObject('Mobile/Common/Global_Number_keypad'), 0)

Mobile.tap(findTestObject('Mobile/End of the Day/StockProposal/SP_PieceQty_Field'), 5, FailureHandling.STOP_ON_FAILURE)

Mobile.tap(findTestObject('Mobile/Common/Vertical keypad-BackSpace'), 2, FailureHandling.OPTIONAL)

Mobile.tap(findTestObject('Mobile/Common/Vertical keypad-BackSpace'), 2, FailureHandling.OPTIONAL)

GlobalVariable.PieceQty = findTestData('Mobile Input Data/StockProposal').getValue('PieceQty', 1)

GlobalVariable.keypadValue = GlobalVariable.PieceQty

Mobile.tap(findTestObject('Mobile/Common/Global_Number_keypad'), 0)

Conversion_Qty = findTestData('Mobile Input Data/StockProposal').getValue('Conversion_Qty', 1)

Given_Qty = (Double.parseDouble(GlobalVariable.PieceQty) + (Double.parseDouble(GlobalVariable.CaseQty) * Double.parseDouble(Conversion_Qty)))

Product_Price = findTestData('Mobile Input Data/StockProposal').getValue('Product_Price', 1)

Calculated_Total_Value = (Given_Qty * Double.parseDouble(Product_Price))

Expected_Total_Value = Mobile.getText(findTestObject('Object Repository/Mobile/End of the Day/StockProposal/TotalValue'), 0, FailureHandling.STOP_ON_FAILURE)

Mobile.verifyEqual(Calculated_Total_Value, Double.parseDouble(Expected_Total_Value), FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

KeywordUtil.logInfo('Total value displayed correctly!')

Mobile.tap(findTestObject('Mobile/Deviation/Next button'), 0, FailureHandling.OPTIONAL)

Mobile.tap(findTestObject('Mobile/End of the Day/StockProposal/Button-Save'), 5)

Mobile.tap(findTestObject('Object Repository/Mobile/Common/Btn_Save'), 5, FailureHandling.OPTIONAL)

Mobile.tap(findTestObject('Mobile/End of the Day/StockProposal/Alert-Yes Btn'), 5)

Mobile.verifyElementVisible(findTestObject('Mobile/End of the Day/StockProposal/Saved Successfully'), 0)

Mobile.verifyElementExist(findTestObject('Mobile/End of the Day/StockProposal/Saved Successfully'), 0)

Mobile.takeScreenshot()

KeywordUtil.logInfo('Verified : The “Stock Proposal” screen Save Functinality.')

Mobile.tap(findTestObject('Mobile/Common/Btn_OK'), 0)

Mobile.delay(3)

Mobile.tap(findTestObject('Mobile/Common/Btn_Menu'), 0)

Mobile.tap(findTestObject('Mobile/MainMenu/Menu_Load Management'), 0)

Mobile.tap(findTestObject('Mobile/LoadManagement/SubMenu_Current Stock view'), 0)

Mobile.delay(2, FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('Product_Mobile/Common/Search_Product'), [('ProductName') : findTestData('Mobile Input Data/StockProposal').getValue('Product_Name', 1)], FailureHandling.STOP_ON_FAILURE)

GlobalVariable.label = '1'

product = Mobile.getText(findTestObject('Mobile/LoadManagement/CurrentStockView/Product(list_Xpath)'), 1, FailureHandling.OPTIONAL)

SIH = Mobile.getText(findTestObject('Mobile/LoadManagement/CurrentStockView/SIH(list_Xpath)'), 1, FailureHandling.OPTIONAL)

/*for (j = 1; j <= 10; j++) {
	GlobalVariable.label = j

	if (Mobile.verifyElementExist(findTestObject('Mobile/LoadManagement/CurrentStockView/Product(list_Xpath)'),
	1, FailureHandling.OPTIONAL)) {
		product = Mobile.getText(findTestObject('Mobile/LoadManagement/CurrentStockView/Product(list_Xpath)'),
				1, FailureHandling.OPTIONAL)

		SIH = Mobile.getText(findTestObject('Mobile/LoadManagement/CurrentStockView/SIH(list_Xpath)'),
				1, FailureHandling.OPTIONAL)

		String exlpath = 'DDF/Mobile Input data/Mobile Input data.xlsx'

		String Sheetname = 'VanUnload'

		workbook01 = ExcelKeywords.getWorkbook(exlpath)

		sheet1 = ExcelKeywords.getExcelSheet(workbook01, Sheetname)

		ExcelKeywords.setValueToCellByIndex(sheet1, j, 0, product)

		ExcelKeywords.setValueToCellByIndex(sheet1, j, 1, SIH)

		ExcelKeywords.saveWorkbook(exlpath, workbook01)
	}
}
*/
Mobile.tap(findTestObject('Mobile/Common/Icon_Back'), 0)

Mobile.tap(findTestObject('Mobile/Common/Btn_Menu'), 0)

Mobile.tap(findTestObject('Mobile/MainMenu/Menu_End of the Day'), 0)

Mobile.tap(findTestObject('Mobile/End of the Day/SubMenu_Van Unload'), 0)

Mobile.delay(2)

Mobile.tap(findTestObject('Mobile/Common/Icon_Search'), 0)

Mobile.hideKeyboard()

Mobile.delay(2)

WebUI.callTestCase(findTestCase('Product_Mobile/Common/Search_Product'), [('ProductName') : findTestData('Mobile Input Data/StockProposal').getValue('Product_Name', 1)], FailureHandling.STOP_ON_FAILURE)

GlobalVariable.ProductName = findTestData('Mobile Input Data/StockProposal').getValue('Product_Name', 1)

GlobalVariable.productvalue = SIH

Mobile.verifyElementVisible(findTestObject('Mobile/End of the Day/Van Unload/Product(List_Object)'), 0)

Mobile.verifyElementText(findTestObject('Mobile/End of the Day/Van Unload/Product(List_Object)'), GlobalVariable.ProductName)

KeywordUtil.logInfo(GlobalVariable.productvalue)

Mobile.verifyElementVisible(findTestObject('Mobile/End of the Day/Van Unload/SIH(List_Object)'), 0)

Mobile.verifyElementText(findTestObject('Mobile/End of the Day/Van Unload/SIH(List_Object)'), GlobalVariable.productvalue)

/*sheet_name = 'VanUnload'

file_name = 'Mobile Input data'

ArrayList<String> ProductName = CustomKeywords.'poi.Automation.GetAllDataRow_Flag.getAllDataRow'(file_name, sheet_name,
		'Product_Name')

ArrayList<String> SIH = CustomKeywords.'poi.Automation.GetAllDataRow_Flag.getAllDataRow'(file_name, sheet_name, 'SIH')

for (int a = 0; a < ProductName.size(); a++) {
	if (ProductName.get(a) != '') {
		GlobalVariable.ProductName = ProductName.get(a)

		GlobalVariable.productvalue = SIH.get(a)

		KeywordUtil.logInfo(GlobalVariable.ProductName)

		Mobile.verifyElementVisible(findTestObject('Mobile/End of the Day/Van Unload/Product(List_Object)'),
				0)

		Mobile.verifyElementText(findTestObject('Mobile/End of the Day/Van Unload/Product(List_Object)'),
				GlobalVariable.ProductName)

		KeywordUtil.logInfo(GlobalVariable.productvalue)

		Mobile.verifyElementVisible(findTestObject('Mobile/End of the Day/Van Unload/SIH(List_Object)'),
				0)

		Mobile.verifyElementText(findTestObject('Mobile/End of the Day/Van Unload/SIH(List_Object)'),
				GlobalVariable.productvalue)
	}
}*/
KeywordUtil.logInfo('Verified : Current stock view details and van unload details should be same')

Mobile.takeScreenshot()

Mobile.tap(findTestObject('Mobile/Common/Field_Search_EnterText'), 0)

Mobile.setText(findTestObject('Mobile/Common/Field_Search_EnterText'), findTestData('Mobile Input Data/VanUnload').getValue('Product_Name', 1), 0)

Mobile.delay(1)

GlobalVariable.keypadValue = findTestData('Mobile Input Data/VanUnload').getValue('CaseQty', 1)

Mobile.tap(findTestObject('Mobile/End of the Day/StockProposal/SP_CaseQty_Field'), 0, FailureHandling.STOP_ON_FAILURE)

Mobile.tap(findTestObject('Mobile/Common/Global_Number_keypad'), 0)

GlobalVariable.keypadValue = findTestData('Mobile Input Data/VanUnload').getValue('PieceQty', 1)

Mobile.tap(findTestObject('Mobile/End of the Day/StockProposal/SP_PieceQty_Field'), 0, FailureHandling.STOP_ON_FAILURE)

Mobile.tap(findTestObject('Mobile/Common/Global_Number_keypad'), 0)

'Van Unload should be saved successfully'
switch (GlobalVariable.Activation_Key) {
    case GlobalVariable.V155_ActivationKey:
        Mobile.tap(findTestObject('Mobile/End of the Day/Van Unload/Next-Button'), 0)

        AppiumDriver driver = MobileDriverFactory.getDriver()

        Actualtoastmessage = driver.findElementByXPath('//android.widget.Toast[1]').getText()

        Mobile.verifyMatch(Actualtoastmessage, findTestData('Mobile Input Data/VanUnload').getValue('Verify', 1), false, FailureHandling.STOP_ON_FAILURE)

        Mobile.takeScreenshot()

        break
    case GlobalVariable.V158_ActivationKey:
        Mobile.tap(findTestObject('Mobile/End of the Day/Van Unload/Next-Button'), 0)

        Mobile.verifyElementVisible(findTestObject('Mobile/End of the Day/StockProposal/Saved Successfully'), 0)

        Mobile.tap(findTestObject('Mobile/Common/Btn_OK'), 0)

        break
}

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Sync with End Attendence'), [:], FailureHandling.OPTIONAL)

Mobile.closeApplication()

Mobile.callTestCase(findTestCase('Product_Mobile/Common/HHT_Transaction_DB'), [:], FailureHandling.STOP_ON_FAILURE)

Mobile.callTestCase(findTestCase('Product_Mobile/Load_Management/StockProposal and Van Stock Create/Call_TC/LM_17_CT'), [:], FailureHandling.CONTINUE_ON_FAILURE)

WebUI.callTestCase(findTestCase('Product_Mobile/Load_Management/StockProposal and Van Stock Create/Call_TC/LM_24_CT'), [:], FailureHandling.CONTINUE_ON_FAILURE)

WebUI.callTestCase(findTestCase('Product_Mobile/Common/Web Login and Navigation/ClearCashe Login'), [:], FailureHandling.STOP_ON_FAILURE)

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Web Login and Navigation/DistributorLogin'), [:], FailureHandling.STOP_ON_FAILURE)

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Web Login and Navigation/Navigate to StockAllocation'), [:], FailureHandling.STOP_ON_FAILURE)

String sheet_name = 'Stock allocation'

String file_name = 'Web Input Data'

ArrayList<String> Warehouse = CustomKeywords.'poi.Automation.GetAllWebDataRow_Flag.getAllDataRow'(file_name, sheet_name, 'Warehouse')

ArrayList<String> Store = CustomKeywords.'poi.Automation.GetAllWebDataRow_Flag.getAllDataRow'(file_name, sheet_name, 'Store')

ArrayList<String> User_Details = CustomKeywords.'poi.Automation.GetAllWebDataRow_Flag.getAllDataRow'(file_name, sheet_name, 'User_Details')

ArrayList<String> case_Quantity = CustomKeywords.'poi.Automation.GetAllWebDataRow_Flag.getAllDataRow'(file_name, sheet_name, 'case_Quantity')

ArrayList<String> Piece_Quantity = CustomKeywords.'poi.Automation.GetAllWebDataRow_Flag.getAllDataRow'(file_name, sheet_name, 'Piece_Quantity')

ArrayList<String> SKU_Name = CustomKeywords.'poi.Automation.GetAllWebDataRow_Flag.getAllDataRow'(file_name, sheet_name, 'SKU_Name')

for (int i = 0; i < Warehouse.size(); i++) {
    if (Warehouse.get(i) != 'NULL') {
        WebUI.click(findTestObject('Web Part/Stock Allocation/Drop down values/Ware house'))

        GlobalVariable.label = Warehouse.get(i)

        WebUI.click(findTestObject('Web Part/Stock Allocation/Global Variables/DropdownSelect_Li Tag'))

        WebUI.waitForElementNotVisible(findTestObject('Web Part/Re-usable/Page Load'), 50, FailureHandling.STOP_ON_FAILURE)

        WebUI.click(findTestObject('Web Part/Stock Allocation/Drop down values/Store'))

        GlobalVariable.label = Store.get(i)

        WebUI.click(findTestObject('Web Part/Stock Allocation/Global Variables/DropdownSelect_Li Tag'))

        WebUI.waitForElementNotVisible(findTestObject('Web Part/Re-usable/Page Load'), 50, FailureHandling.STOP_ON_FAILURE)

        WebUI.click(findTestObject('Web Part/Stock Allocation/Sku(hyperlink)Field values/User Details_Title'))

        GlobalVariable.label = User_Details.get(i)

        WebUI.scrollToElement(findTestObject('Web Part/Stock Allocation/Global Variables/UserSelect_Span b Tag'), 1, FailureHandling.OPTIONAL)

        WebUI.click(findTestObject('Web Part/Stock Allocation/Global Variables/UserSelect_Span b Tag'))

        WebUI.waitForElementNotVisible(findTestObject('Web Part/Re-usable/Page Load'), 50, FailureHandling.STOP_ON_FAILURE)

        WebUI.click(findTestObject('Web Part/Stock Allocation/Button/Proposed_btn'))

        WebUI.waitForElementNotVisible(findTestObject('Web Part/Re-usable/Page Load'), 50, FailureHandling.STOP_ON_FAILURE)

        WebUI.delay(2)

        WebUI.verifyElementVisible(findTestObject('Web Part/Stock Allocation/Proposal Based Allocation-Header'))

        Header = WebUI.getText(findTestObject('Web Part/Stock Allocation/Proposal Based Allocation-Header'), FailureHandling.STOP_ON_FAILURE)

        WebUI.takeScreenshot()

        KeywordUtil.logInfo(('Able to select Proposed stock Button, Screen navigate to "' + Header) + '" screen.')

        GlobalVariable.ProductName = findTestData('Mobile Input Data/StockProposal').getValue('Product_Name', 1)

        //////
        WebUI.verifyElementVisible(findTestObject('Web Part/Stock Allocation/Global Variables/Hyper_a_tag(grid)'))

        WebUI.verifyElementVisible(findTestObject('Web Part/Stock Allocation/Grid_Row/SIH_Case_Value_Field'))

        WebUI.verifyElementVisible(findTestObject('Web Part/Stock Allocation/Grid_Row/SIH_Piece_Value_Field'))

        WebUI.verifyElementVisible(findTestObject('Web Part/Stock Allocation/Grid_Row/Proposal_Case_Value_Field'))

        WebUI.verifyElementText(findTestObject('Web Part/Stock Allocation/Grid_Row/Proposal_Case_Value_Field'), findTestData('Mobile Input Data/StockProposal').getValue('CaseQty', 1))

        WebUI.verifyElementVisible(findTestObject('Web Part/Stock Allocation/Grid_Row/Proposal_Piece_Value_Field'))

        WebUI.verifyElementText(findTestObject('Web Part/Stock Allocation/Grid_Row/Proposal_Piece_Value_Field'), findTestData('Mobile Input Data/StockProposal').getValue('PieceQty', 1))

        WebUI.verifyElementVisible(findTestObject('Web Part/Stock Allocation/Grid_Row/Allocated_Case_Value_Field'))

        WebUI.verifyElementVisible(findTestObject('Web Part/Stock Allocation/Grid_Row/Allocated_Piece_Value_Field'))

        WebUI.takeScreenshot()

        KeywordUtil.logInfo('User can view a list of products proposed for stock allocation with SIH case&piece, Proposal case&piece and, Allocated case&piece')

        //////////////////////////////
        WebUI.click(findTestObject('Web Part/Stock Allocation/Global Variables/Hyper_a_tag(grid)'))

        WebUI.waitForElementNotVisible(findTestObject('Web Part/Re-usable/Page Load'), 50, FailureHandling.OPTIONAL)

        WebUI.verifyElementVisible(findTestObject('Web Part/Stock Allocation/Sku(hyperlink)Field values/Salesperson'))

        WebUI.verifyElementText(findTestObject('Web Part/Stock Allocation/Sku(hyperlink)Field values/Salesperson'), findTestData('Mobile Input Data/StockProposal').getValue('SalesPerson', 1))

        SalesPerson = WebUI.getText(findTestObject('Web Part/Stock Allocation/Sku(hyperlink)Field values/Salesperson'))

        KeywordUtil.logInfo(SalesPerson + ' : Sales person name loaded in the selected proposed stock sku hyper link')

        WebUI.takeScreenshot()

        proposedCaseValue = WebUI.getText(findTestObject('Web Part/Stock Allocation/Sku(hyperlink)Field values/GetCaseValue_Proposal'))

        WebUI.verifyMatch(proposedCaseValue, findTestData('Mobile Input Data/StockProposal').getValue('CaseQty', 1), false, FailureHandling.STOP_ON_FAILURE)

        KeywordUtil.logInfo(proposedCaseValue + ' : Entered Case Qty in Mobile Stock Proposal Screen that would refected in Web Stock Allocation Screen Properly')

        WebUI.takeScreenshot()

        proposedPieceValue = WebUI.getText(findTestObject('Web Part/Stock Allocation/Sku(hyperlink)Field values/GetPieceValue_Proposal'))

        println(proposedPieceValue)

        WebUI.verifyMatch(proposedPieceValue, findTestData('Mobile Input Data/StockProposal').getValue('PieceQty', 1), false, FailureHandling.STOP_ON_FAILURE)

        KeywordUtil.logInfo(proposedPieceValue + ' : Entered Piece Qty In Mobile Stock Proposal Screen that would refected in Web Stock Allocation Screen Properly')

        WebUI.takeScreenshot()

        WebUI.click(findTestObject('Web Part/Stock Allocation/Sku(hyperlink)Field values/AutoAdjust-Checkbox'), FailureHandling.STOP_ON_FAILURE)

        WebUI.delay(2)

        Allocated_Case = WebUI.getAttribute(findTestObject('Web Part/Stock Allocation/Sku(hyperlink)Field values/EnterCaseValue_Allocation'), 'title')

        Allocated_Piece = WebUI.getAttribute(findTestObject('Web Part/Stock Allocation/Sku(hyperlink)Field values/EnterPieceValue_Allocation'), 'title')

        WebUI.verifyMatch(Allocated_Case, findTestData('Mobile Input Data/StockProposal').getValue('CaseQty', 1), false, FailureHandling.STOP_ON_FAILURE)

        WebUI.verifyMatch(Allocated_Piece, findTestData('Mobile Input Data/StockProposal').getValue('PieceQty', 1), false, FailureHandling.STOP_ON_FAILURE)

        WebUI.takeScreenshot()

        KeywordUtil.logInfo('Selecting Auto adjust will auto populate the proposed qty to allocated qty Successfully!')

        WebUI.click(findTestObject('Web Part/Stock Allocation/Sku(hyperlink)Field values/EnterCaseValue_Allocation_2'))

        not_run: WebUI.clearText(findTestObject('Web Part/Stock Allocation/Sku(hyperlink)Field values/EnterCaseValue_Allocation_2'), FailureHandling.STOP_ON_FAILURE)

        WebUI.sendKeys(findTestObject('Web Part/Stock Allocation/Sku(hyperlink)Field values/EnterCaseValue_Allocation_2'), Keys.chord(Keys.ARROW_RIGHT, Keys.BACK_SPACE))

        WebUI.delay(2)

        WebUI.setText(findTestObject('Web Part/Stock Allocation/Sku(hyperlink)Field values/EnterCaseValue_Allocation_2'), findTestData('Mobile Input Data/StockProposal').getValue('Keypad_Number', 1))

        WebUI.click(findTestObject('Web Part/Stock Allocation/Sku(hyperlink)Field values/EnterPieceValue_Allocation_2'))

        WebUI.clearText(findTestObject('Web Part/Stock Allocation/Sku(hyperlink)Field values/EnterPieceValue_Allocation_2'), FailureHandling.STOP_ON_FAILURE)

        WebUI.sendKeys(findTestObject('Web Part/Stock Allocation/Sku(hyperlink)Field values/EnterPieceValue_Allocation_2'), Keys.chord(Keys.ARROW_RIGHT, Keys.BACK_SPACE))

        WebUI.delay(2)

        WebUI.setText(findTestObject('Web Part/Stock Allocation/Sku(hyperlink)Field values/EnterPieceValue_Allocation_2'), findTestData('Mobile Input Data/StockProposal').getValue('Keypad_Number', 2))

        WebUI.click(findTestObject('Web Part/Stock Allocation/Button/Save_btn'))

        WebUI.delay(2)

        WebUI.waitForElementNotVisible(findTestObject('Web Part/Re-usable/Page Load'), 50, FailureHandling.STOP_ON_FAILURE)

        WebUI.delay(2)

        Allocated_Case = WebUI.getText(findTestObject('Web Part/Stock Allocation/Grid_Row/Allocated_Case_Value_Field'))

        Allocated_Piece = WebUI.getText(findTestObject('Web Part/Stock Allocation/Grid_Row/Allocated_Piece_Value_Field'))

        WebUI.verifyMatch(Allocated_Case, findTestData('Mobile Input Data/StockProposal').getValue('Keypad_Number', 1), false, FailureHandling.STOP_ON_FAILURE)

        WebUI.verifyMatch(Allocated_Piece, findTestData('Mobile Input Data/StockProposal').getValue('Keypad_Number', 2), false, FailureHandling.STOP_ON_FAILURE)

        WebUI.takeScreenshot()

        KeywordUtil.logInfo('User enetered case piece qty properly reflected in the proposal based allocation screen.')

        WebUI.click(findTestObject('Web Part/Stock Allocation/Button/Proceed_btn'))

        WebUI.delay(4)

        WebUI.waitForElementNotVisible(findTestObject('Web Part/Re-usable/Page Load'), 50, FailureHandling.STOP_ON_FAILURE)

        WebUI.click(findTestObject('Web Part/Stock Allocation/Button/Allocation Btn (proposed)'))

        WebUI.takeScreenshot()

        WebUI.verifyElementPresent(findTestObject('Web Part/Stock Allocation/Button/Proposed_btn'), 10)

        KeywordUtil.logInfo('Proposed data added in Stock allocation Successfully!!')
    }
}

WebUI.delay(5)

Mobile.callTestCase(findTestCase('Product_Mobile/Common/DB'), [('Query') : 'select top 1 * from AppData_Van_Load_Header where VLH_AUH_Id = 1589 order by 1 desc', ('columnNames') : ['VLH_Reference_No']], FailureHandling.STOP_ON_FAILURE)

String VLH_Reference_No = GlobalVariable.data[0]

KeywordUtil.logInfo('Reference No : ' + VLH_Reference_No)

GlobalVariable.VanLoad_No = VLH_Reference_No

KeywordUtil.logInfo('Van Load Reference no  :  ' + GlobalVariable.VanLoad_No)

Mobile.callTestCase(findTestCase('Product_Mobile/Common/DB'), [('Query') : ('Select * from appdata_van_load_header where vlh_reference_no=\'' + GlobalVariable.VanLoad_No) + '\'', ('columnNames') : ['VLH_Id']], FailureHandling.STOP_ON_FAILURE)

String VLH_Id = GlobalVariable.data[0]

KeywordUtil.logInfo('Header No : ' + VLH_Id)

GlobalVariable.label1 = VLH_Id

KeywordUtil.logInfo('VLH id  :  ' + GlobalVariable.label1)

Mobile.callTestCase(findTestCase('Product_Mobile/Common/DB'), [('Query') : ('Select * from appdata_van_load_detail where VLD_VLH_Id =\'' + GlobalVariable.label1) + '\'', ('columnNames') : ['VLD_Qty']], FailureHandling.STOP_ON_FAILURE)

String Quantity = GlobalVariable.data[0]

KeywordUtil.logInfo('Quantity  : ' + Quantity)

GlobalVariable.PieceQty = Quantity

KeywordUtil.logInfo('Total piece Qty  :  ' + GlobalVariable.PieceQty)

