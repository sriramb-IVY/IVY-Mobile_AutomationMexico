import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import org.openqa.selenium.Keys as Keys

import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import internal.GlobalVariable as GlobalVariable

int row = CustomKeywords.'poi.png_Product.GetSheetRow.getSheet'('Navigation')

config = findTestData('PNG Product/Master/Master').getValue('Configuration', row)

int rowSOL = CustomKeywords.'poi.png_Product.GetSheetRow.getSheet'('SalesOrderLabel')

configSOL = findTestData('PNG Product/Master/Master').getValue('Configuration', rowSOL)

int rowSOG = CustomKeywords.'poi.png_Product.GetSheetRow.getSheet'('SalesOrderGrid')

configSOG = findTestData('PNG Product/Master/Master').getValue('Configuration', rowSOG)

String navpath = 'PNG Product/Modules/Navigation/' + config

ArrayList<String> element_label = CustomKeywords.'poi.png_Product.Fetchvalue.getValue'('SO Number', 'SalesOrderLabel')

ArrayList<String> so_Data = CustomKeywords.'poi.png_Product.Fetchvalue.getValue'('Retailer', 'SalesOrder')

ArrayList<String> sales_Person = CustomKeywords.'poi.png_Product.Fetchvalue.getValue'('Sales Person', 'SalesOrder')

ArrayList<String> so_Date = CustomKeywords.'poi.png_Product.Fetchvalue.getValue'('SO Date', 'SalesOrder')

ArrayList<String> exp_Del_Date = CustomKeywords.'poi.png_Product.Fetchvalue.getValue'('Exp. Del. Date', 'SalesOrder')

ArrayList<String> Warehouse = CustomKeywords.'poi.png_Product.Fetchvalue.getValue'('Warehouse', 'SalesOrder')

ArrayList<String> sku = CustomKeywords.'poi.png_Product.Fetchvalue.getValue'('SKU Name', 'SalesOrder')

ArrayList<String> sku_Case = CustomKeywords.'poi.png_Product.Fetchvalue.getValue'('Case', 'SalesOrder')

ArrayList<String> sku_Piece = CustomKeywords.'poi.png_Product.Fetchvalue.getValue'('Piece', 'SalesOrder')

for (int ind = 0; ind < element_label.size(); ind++) {
    WebUI.callTestCase(findTestCase('PNG Product/Re-Usables/Login/Branch Login'), [:], FailureHandling.STOP_ON_FAILURE)

    WebUI.callTestCase(findTestCase('PNG Product/Re-Usables/DatePicker_and _Navigation/Navigation'), [('Navigation') : findTestData(
                navpath).getValue(2, 1)], FailureHandling.STOP_ON_FAILURE)

    WebUI.sendKeys(null, Keys.chord(Keys.SPACE))

    for (int i = 1; i < (findTestData('PNG Product/Modules/SalesOrderLabel/' + configSOL).getColumnNumbers() - 1); i++) {
        GlobalVariable.label = findTestData('PNG Product/Modules/SalesOrderLabel/' + configSOL).getValue(i, 1).trim()

        println(GlobalVariable.label)

        GlobalVariable.label_object = findTestData('PNG Product/Modules/SalesOrderLabel/' + configSOL).getValue(i, 2).trim()

        println(GlobalVariable.label_object)

        WebUI.delay(5)

        WebUI.verifyElementText(findTestObject('PNG Product/Sales Order/Label'), GlobalVariable.label_object)
		if(WebUI.verifyElementText(findTestObject('PNG Product/Sales Order/Label'), GlobalVariable.label_object, FailureHandling.OPTIONAL))
		{
		KeywordUtil.logInfo(WebUI.getText(findTestObject('PNG Product/Sales Order/Label'))+" and "+GlobalVariable.label_object+" are Matched")
		}
		else
		{
			KeywordUtil.logInfo(WebUI.getText(findTestObject('PNG Product/Sales Order/Label'))+" and "+GlobalVariable.label_object+" are not Matched")
		}
        WebUI.takeScreenshot()
    }
    
    SalesOrder_No = WebUI.getAttribute(findTestObject('PNG Product/Sales Order/SalesOrder_No'), 'value')

    GlobalVariable.SalesOrder_No = SalesOrder_No

    WebUI.sendKeys(findTestObject('PNG Product/Sales Order/Input_Salesperson'), sales_Person.get(ind))

    WebUI.delay(5)

    WebUI.sendKeys(findTestObject('PNG Product/Sales Order/Input_Salesperson'), Keys.chord(Keys.DOWN, Keys.ENTER))

    WebUI.delay(5)

    WebUI.sendKeys(findTestObject('PNG Product/Sales Order/Input_Retailer'), so_Data.get(ind))

    WebUI.delay(10)

    WebUI.sendKeys(findTestObject('PNG Product/Sales Order/Input_Retailer'), Keys.chord(Keys.DOWN, Keys.ENTER))

    WebUI.click(findTestObject('PNG Product/Sales Order/Input SO Date'))

    println(so_Date.get(ind))

    WebUI.callTestCase(findTestCase('PNG Product/Re-Usables/DatePicker_and _Navigation/Date Picker'), [('DateValue') : so_Date.get(
                ind)], FailureHandling.STOP_ON_FAILURE)

    WebUI.click(findTestObject('PNG Product/Sales Order/Input_Exp. Del. Date'))

    WebUI.delay(5)

    WebUI.callTestCase(findTestCase('PNG Product/Re-Usables/DatePicker_and _Navigation/Date Picker'), [('DateValue') : exp_Del_Date.get(
                ind)], FailureHandling.STOP_ON_FAILURE)

    WebUI.delay(5)

    WebUI.selectOptionByLabel(findTestObject('PNG Product/Sales Order/Warehouse'), Warehouse.get(ind), false)

    WebUI.delay(5)

    //WebUI.click(findTestObject('PNG Product/Sales Order/Search SKU'), FailureHandling.STOP_ON_FAILURE)
    WebUI.sendKeys(findTestObject('PNG Product/Sales Order/Search SKU'), sku.get(ind))

    WebUI.delay(5)

    WebUI.sendKeys(findTestObject('PNG Product/Sales Order/Search SKU'), Keys.chord(Keys.DOWN, Keys.ENTER))

    WebUI.delay(2)

    WebUI.click(findTestObject('PNG Product/Sales Order/Table Row'), FailureHandling.STOP_ON_FAILURE)

    WebUI.click(findTestObject('PNG Product/Sales Order/input_Case'))

    WebUI.delay(2)

    WebUI.sendKeys(findTestObject('PNG Product/Sales Order/input_Case'), sku_Case.get(ind))

    WebUI.delay(5)

    WebUI.click(findTestObject('PNG Product/Sales Order/input_Piece'))

    WebUI.delay(2)

    WebUI.sendKeys(findTestObject('PNG Product/Sales Order/input_Piece'), sku_Case.get(ind))

    WebUI.click(findTestObject('PNG Product/Sales Order/Table Row'), FailureHandling.STOP_ON_FAILURE)

    WebUI.delay(2)

    for (int gridindex = 1; gridindex < (findTestData('PNG Product/Modules/SalesOrderGrid/' + configSOG).getColumnNumbers() - 
    1); gridindex++) {
        GlobalVariable.label = findTestData('PNG Product/Modules/SalesOrderGrid/' + configSOG).getValue(gridindex, 1).trim()

        println(GlobalVariable.label)

        GlobalVariable.label_object = findTestData('PNG Product/Modules/SalesOrderGrid/' + configSOG).getValue(gridindex, 
            2).trim()

        println(GlobalVariable.label_object)

        WebUI.delay(5)

        WebUI.verifyElementText(findTestObject('PNG Product/Sales Order/Grid_Label'), GlobalVariable.label_object)

        WebUI.takeScreenshot()
    }
    
    if (WebUI.verifyElementVisible(findTestObject('PNG Product/Sales Order/button_submit Order'), FailureHandling.OPTIONAL)) {
        WebUI.click(findTestObject('PNG Product/Sales Order/button_submit Order'))

        WebUI.delay(5)

        WebUI.click(findTestObject('PNG Product/Sales Order/Button yes Continue'))

        WebUI.takeScreenshot()
    } else {
        WebUI.click(findTestObject('PNG Product/Sales Order/button_Empties'))

        WebUI.delay(2)

		if (WebUI.verifyElementVisible(findTestObject('PNG Product/Sales Order/Empties/button_Close card Alert'), FailureHandling.OPTIONAL)) {
		
        WebUI.click(findTestObject('PNG Product/Sales Order/Empties/button_Close card Alert'))
		}

        WebUI.delay(2)

        WebUI.click(findTestObject('PNG Product/Sales Order/Empties/button_Submit Order'))

        WebUI.delay(10)

        WebUI.click(findTestObject('PNG Product/Sales Order/Button yes Continue'))
    }
    
    println(WebUI.getText(findTestObject('PNG Product/Sales Order/Order Submitted Successfully')))
}

