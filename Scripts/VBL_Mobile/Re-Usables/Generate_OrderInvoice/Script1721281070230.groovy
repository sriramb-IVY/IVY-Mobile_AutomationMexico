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
import com.kms.katalon.core.mobile.keyword.internal.MobileDriverFactory as MobileDriverFactory
import io.appium.java_client.AppiumDriver as AppiumDriver
//import io.appium.java_client.MobileElement as MobileElement
import org.openqa.selenium.WebElement as WebElement
import java.text.DateFormat as DateFormat
import java.text.SimpleDateFormat as SimpleDateFormat
import java.util.Date as Date
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

Mobile.waitForElementPresent(findTestObject('Mobile/SummaryScreen/SummaryScreen-Order btn'), 0)

Mobile.tap(findTestObject('Mobile/SummaryScreen/SummaryScreen-Order btn'), 0)

Mobile.callTestCase(findTestCase('Product_Mobile/Common/DatePicker_and _Navigation/Mobile Date Picker - Current Date'), [:], FailureHandling.STOP_ON_FAILURE)

Mobile.tap(findTestObject('Mobile/Common/Btn_Confirm'), 0)

Mobile.delay(2, FailureHandling.STOP_ON_FAILURE)

id = Mobile.getText(findTestObject('Mobile/SummaryScreen/Click Order-OrderSavedID-Title'), 0)

Ord_ID = id.replaceAll('[Order Saved. Order ID is:\']', '')

invoice_ID = Ord_ID.replaceAll('[\']', '')

KeywordUtil.logInfo(invoice_ID)

GlobalVariable.InvoiceNo = invoice_ID

Mobile.tap(findTestObject('Mobile/Common/Btn_OK'), 0)

Mobile.delay(1)

Mobile.swipe(0, 150, 0, 400, FailureHandling.OPTIONAL)

Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/Menu-OrderInvoice'), 0, FailureHandling.STOP_ON_FAILURE)

//Mobile.callTestCase(findTestCase('Product_Mobile/Common/OrderPopUp(EditOrder)'), [:], FailureHandling.STOP_ON_FAILURE)

if (Mobile.verifyElementVisible(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/OrderPopUp/OrderPopUp_Title'), 3, FailureHandling.OPTIONAL)) {
    AppiumDriver driver = MobileDriverFactory.getDriver()

    List<Double> elements = driver.findElementsByClassName('android.widget.ImageView')

    def size = elements.size()

    println('The size of elements is ::' + elements.size())

    for (int i = 0; i < (size - 1); i++) {
     Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/OrderPopUp/Edit_Order 1'), 0)

        Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/SummaryScreen/SummaryScreen-Invoice btn'), 0)

        Mobile.delay(1)

       if (Mobile.verifyElementExist(findTestObject('Object Repository/Mobile/SummaryScreen/Field_EnterCollectionAmount'), 2, FailureHandling.OPTIONAL)) {
            Mobile.tap(findTestObject('Object Repository/Mobile/SummaryScreen/Field_EnterCollectionAmount'), 0)

            Mobile.delay(1)

            Mobile.tap(findTestObject('Mobile/Common/Btn_Done'), 0)
       }
     
       Mobile.callTestCase(findTestCase('Product_Mobile/Common/DatePicker_and _Navigation/Mobile Date Picker - Current Date'), [:], FailureHandling.STOP_ON_FAILURE)

       Mobile.tap(findTestObject('Mobile/Common/Btn_Confirm'), 0)
       Mobile.delay(2)

       if (Mobile.verifyElementExist(findTestObject('Mobile/Common/Icon_Back'), 2, FailureHandling.OPTIONAL)) {
		   
           Mobile.tap(findTestObject('Mobile/Common/Icon_Back'), 3, FailureHandling.OPTIONAL)

           Mobile.delay(1)//Mobile.tap(findTestObject('Mobile/Common/Btn_Done'), 0)

            Mobile.tap(findTestObject('Mobile/Common/Icon_Back'), 3, FailureHandling.OPTIONAL)

            Mobile.delay(1)
        } else if (Mobile.verifyElementExist(findTestObject('Mobile/Common/Btn_OK'), 2, FailureHandling.OPTIONAL)) {
            Mobile.tap(findTestObject('Mobile/Common/Btn_OK'), 0, FailureHandling.OPTIONAL)
        }
        
       Mobile.delay(2)
	   
	   //Mobile.tap(findTestObject('Mobile/Common/Icon_Back'), 3, FailureHandling.OPTIONAL)
    }
}

not_run: for (int i = 0; i < 1; i++) {
    //Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/OrderPopUp/Edit_Order 1'), 0)
    Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/SummaryScreen/SummaryScreen-Invoice btn'), 0)

    Mobile.delay(1)

    if (Mobile.verifyElementExist(findTestObject('Object Repository/Mobile/SummaryScreen/Field_EnterCollectionAmount'), 2, FailureHandling.OPTIONAL)) {
        Mobile.tap(findTestObject('Object Repository/Mobile/SummaryScreen/Field_EnterCollectionAmount'), 0)

        Mobile.delay(1)

        Mobile.tap(findTestObject('Mobile/Common/Btn_Done'), 0)
    }
    
    if (Mobile.verifyElementVisible(findTestObject('Mobile/SummaryScreen/Delivery_Date'), 2, FailureHandling.OPTIONAL)) {
        Mobile.tap(findTestObject('Mobile/SummaryScreen/Delivery_Date'), 0)

        //Mobile.callTestCase(findTestCase('VBL_Mobile/Re-Usables/DatePicker_and _Navigation/Date Picker'), [('DateValue') : 'Today'], FailureHandling.STOP_ON_FAILURE)
        Mobile.tap(findTestObject('Mobile/SummaryScreen/date_Global'), 0)

        Mobile.tap(findTestObject('Mobile/Common/Btn_OK'), 0)
    }
    
    if (Mobile.verifyElementExist(findTestObject('Object Repository/Mobile/SummaryScreen/Field_EnterCollectionAmount'), 2, FailureHandling.OPTIONAL)) {
        Mobile.tap(findTestObject('Object Repository/Mobile/SummaryScreen/Field_EnterCollectionAmount'), 0)

        Mobile.delay(1)

        Mobile.tap(findTestObject('Mobile/Common/Btn_Done'), 0)
    }
    
    Mobile.tap(findTestObject('Mobile/Common/Btn_Confirm'), 0)

    Mobile.delay(2)

    if (Mobile.verifyElementExist(findTestObject('Mobile/Common/Icon_Back'), 2, FailureHandling.OPTIONAL)) {
        Mobile.tap(findTestObject('Mobile/Common/Icon_Back'), 3, FailureHandling.OPTIONAL)

        Mobile.delay(1)

        Mobile.tap(findTestObject('Mobile/Common/Icon_Back'), 3, FailureHandling.OPTIONAL)

        Mobile.delay(1)
    } else if (Mobile.verifyElementExist(findTestObject('Mobile/Common/Btn_OK'), 2, FailureHandling.OPTIONAL)) {
        Mobile.tap(findTestObject('Mobile/Common/Btn_OK'), 0, FailureHandling.OPTIONAL)
    }
}

not_run: WebUI.callTestCase(findTestCase('VBL_Mobile/Re-Usables/Call_Analysis'), [:], FailureHandling.STOP_ON_FAILURE)



