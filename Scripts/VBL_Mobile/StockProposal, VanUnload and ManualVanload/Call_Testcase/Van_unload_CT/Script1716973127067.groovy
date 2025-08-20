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
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import java.sql.Connection as Connection
import java.sql.DriverManager as DriverManager
import java.sql.ResultSet as ResultSet
import java.sql.Statement as Statement
import java.text.SimpleDateFormat as SimpleDateFormat
import com.kms.katalon.core.mobile.keyword.internal.MobileDriverFactory as MobileDriverFactory
import io.appium.java_client.AppiumDriver as AppiumDriver
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import org.openqa.selenium.WebElement as WebElement
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.keyword.excel.ExcelKeywords as ExcelKeywords
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil

//Mobile.startApplication(GlobalVariable.APK_File, false)
Mobile.tap(findTestObject('XXXXXXXXXXXX/Re-usable/Mobile Login/Menu'), 3, FailureHandling.OPTIONAL)

Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/MainMenu/Menu_End of the Day'), 0)

Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/End of the Day/SubMenu_Van Unload'), 0)

//Mobile.delay(2, FailureHandling.STOP_ON_FAILURE)
//Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Reusable Object/Product Search Icon'), 0)
//Mobile.hideKeyboard()
Mobile.delay(1, FailureHandling.STOP_ON_FAILURE)

sheet_name = 'Scheme_Stock_Validation'

file_name = 'Mobile Input data'

ArrayList<String> ProductName = CustomKeywords.'poi.VBL.Automation.GetAllDataRow_Flag.getAllDataRow'(file_name, sheet_name, 'SKU_Name')

for (int a = 0; a < ProductName.size(); a++) {
    if (ProductName.get(a) != '') {
        GlobalVariable.ProductName = ProductName.get(a)

        KeywordUtil.logInfo(GlobalVariable.ProductName)

        WebUI.callTestCase(findTestCase('Product_Mobile/Common/Search_Product'), [:], FailureHandling.CONTINUE_ON_FAILURE)

        Mobile.setText(findTestObject('XXXXXXXXXXXX/Mobile Part/Reusable Object/Product Enter Search'), GlobalVariable.ProductName, 0)

        Mobile.takeScreenshot()

        Mobile.delay(1)

        SIH = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/End of the Day/Van Unload/SIH(List_Object)'), 0)

        Mobile.setText(findTestObject('XXXXXXXXXXXX/Mobile Part/End of the Day/StockProposal/SP_PieceQty_Field'), SIH, 0)
    }
}

Mobile.takeScreenshot()

Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/End of the Day/Van Unload/Next-Button'), 0)

AppiumDriver driver = MobileDriverFactory.getDriver()

Actualtoastmessage = driver.findElementByXPath('//android.widget.Toast[1]').getText()

Mobile.takeScreenshot()

Mobile.delay(2)

Mobile.tap(findTestObject('Mobile/Common/Icon_Back'), 1, FailureHandling.OPTIONAL)

Mobile.tap(findTestObject('Mobile/Common/Icon_Back'), 1, FailureHandling.OPTIONAL)

Mobile.tap(findTestObject('XXXXXXXXXXXX/Re-usable/Mobile Login/Menu'), 3, FailureHandling.OPTIONAL)

