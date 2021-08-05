import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import java.lang.reflect.Array as Array
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys

int row = CustomKeywords.'poi.png_Product.GetSheetRow.getSheet'('Navigation')

String config = findTestData('PNG Product/Master/Master').getValue('Configuration', row)

int rowSIL = CustomKeywords.'poi.png_Product.GetSheetRow.getSheet'('SalesInvoice')

String configSIL = findTestData('PNG Product/Master/Master').getValue('Configuration', rowSIL)

println(configSIL)

int rowSIG = CustomKeywords.'poi.png_Product.GetSheetRow.getSheet'('SalesInvoiceGrid') - 1

String configSIG = findTestData('PNG Product/Master/Master').getValue('Configuration', rowSIG)

println(configSIG)

String navpath = 'PNG Product/Modules/Navigation/' + config

ArrayList<String> SI_label = CustomKeywords.'poi.png_Product.Fetchvalue.getValue'('Sales Person', 'SalesInvoice')

ArrayList<String> SI_sales_Person = CustomKeywords.'poi.png_Product.Fetchvalue.getValue'('Sales Person', 'SalesInvoicedata')

ArrayList<String> SI_Route = CustomKeywords.'poi.png_Product.Fetchvalue.getValue'('Route', 'SalesInvoicedata')

ArrayList<String> SI_Retailer = CustomKeywords.'poi.png_Product.Fetchvalue.getValue'('Retailer', 'SalesInvoicedata')

ArrayList<String> SI_Status = CustomKeywords.'poi.png_Product.Fetchvalue.getValue'('Status', 'SalesInvoicedata')

ArrayList<String> SI_SOfromDate = CustomKeywords.'poi.png_Product.Fetchvalue.getValue'('SO From Date', 'SalesInvoicedata')

ArrayList<String> SI_SOtoDate = CustomKeywords.'poi.png_Product.Fetchvalue.getValue'('SO To Date', 'SalesInvoicedata')

for (int SIind = 0; SIind < SI_label.size(); SIind++) {
    WebUI.callTestCase(findTestCase('PNG Product/Re-Usables/Login/Branch Login'), [:], FailureHandling.STOP_ON_FAILURE)

    WebUI.callTestCase(findTestCase('PNG Product/Re-Usables/DatePicker_and _Navigation/Navigation'), [('Navigation') : findTestData(
                navpath).getValue(2, 2)], FailureHandling.STOP_ON_FAILURE)

    println(findTestData('PNG Product/Modules/SalesInvoice/Config1').getColumnNumbers())

    for (int si = 1; si < findTestData('PNG Product/Modules/SalesInvoice/Config1').getColumnNumbers(); si++) {
        GlobalVariable.label = findTestData('PNG Product/Modules/SalesInvoice/Config1').getValue(si, 1).trim()

        println(GlobalVariable.label)

        GlobalVariable.label_object = findTestData('PNG Product/Modules/SalesInvoice/Config1').getValue(si, 1).trim()

        println(GlobalVariable.label_object)

        WebUI.delay(5)

        WebUI.verifyElementText(findTestObject('PNG Product/Sales Order/Label'), GlobalVariable.label_object)

        WebUI.takeScreenshot()
    }
    
    WebUI.sendKeys(findTestObject('PNG Product/Sales Invoice/input_Sales_Person'), SI_sales_Person.get(SIind))

    WebUI.sendKeys(findTestObject('PNG Product/Sales Invoice/input_Sales_Person'), Keys.chord(Keys.DOWN, Keys.ENTER))

    WebUI.sendKeys(findTestObject('PNG Product/Sales Invoice/input_Route'), SI_Route.get(SIind))

    WebUI.sendKeys(findTestObject('PNG Product/Sales Invoice/input_Route'), Keys.chord(Keys.DOWN, Keys.ENTER))

    WebUI.sendKeys(findTestObject('PNG Product/Sales Invoice/input_Retailer'), SI_Retailer.get(SIind))

    WebUI.sendKeys(findTestObject('PNG Product/Sales Invoice/input_Retailer'), Keys.chord(Keys.DOWN, Keys.ENTER))

    WebUI.selectOptionByLabel(findTestObject('PNG Product/Sales Invoice/select_Status'), SI_Status.get(SIind), false)

    WebUI.click(findTestObject('PNG Product/Sales Invoice/SO From Date'))

    WebUI.callTestCase(findTestCase('PNG Product/Re-Usables/DatePicker_and _Navigation/Date Picker'), [('DateValue') : SI_SOfromDate.get(
                SIind)], FailureHandling.STOP_ON_FAILURE)

    WebUI.click(findTestObject('PNG Product/Sales Invoice/SO To Date'))

    WebUI.callTestCase(findTestCase('PNG Product/Re-Usables/DatePicker_and _Navigation/Date Picker'), [('DateValue') : SI_SOtoDate.get(
                SIind)], FailureHandling.STOP_ON_FAILURE)

    WebUI.click(findTestObject('PNG Product/Sales Invoice/button_Search'))

    for (int SIgridindex = 1; SIgridindex < (findTestData('PNG Product/Modules/SalesInvoiceGrid/' + configSIG).getColumnNumbers() - 
    1); SIgridindex++) {
        GlobalVariable.label = findTestData('PNG Product/Modules/SalesInvoiceGrid/' + configSIG).getValue(SIgridindex, 1).trim()

        println(GlobalVariable.label)

        GlobalVariable.label_object = findTestData('PNG Product/Modules/SalesInvoiceGrid/' + configSIG).getValue(SIgridindex, 
            2).trim()

        println(GlobalVariable.label_object)

        WebUI.delay(5)

        WebUI.verifyElementText(findTestObject('PNG Product/Sales Invoice/grid_Label'), GlobalVariable.label_object)

        WebUI.takeScreenshot()
    }
    
    WebUI.delay(5)

    WebUI.click(findTestObject('PNG Product/Sales Invoice/Grid Filter'))

    WebUI.sendKeys(findTestObject('PNG Product/Sales Invoice/GridFilter/input_SO No'), GlobalVariable.SalesOrder_No)

    WebUI.click(findTestObject('PNG Product/Sales Invoice/GridFilter/button_Apply'))
	
	WebUI.verifyElementPresent(findTestObject('PNG Product/Sales Invoice/salesOrder_no'), 0)
	
	WebUI.takeScreenshot()
	
	WebUI.click(findTestObject('PNG Product/Sales Invoice/button_GenerateInvoice'))
	
	WebUI.delay(10)
	
	WebUI.verifyElementPresent(findTestObject('PNG Product/Sales Invoice/button_Submit'), 0)
	
	WebUI.click(findTestObject('PNG Product/Sales Invoice/button_Submit'))
	
	WebUI.takeScreenshot()
	
	WebUI.verifyElementPresent(findTestObject('PNG Product/Sales Invoice/button_Save Invoice'), 0)
	
	WebUI.click(findTestObject('PNG Product/Sales Invoice/button_Save Invoice'))
	
	WebUI.takeScreenshot()
	
	String invoice_no=WebUI.getText(findTestObject('PNG Product/Sales Invoice/Order Submitted Successfully'))
	println invoice_no
	GlobalVariable.salesInvoice_No=invoice_no.replace(" - Submitted Successfully","")
	WebUI.closeBrowser()
}

