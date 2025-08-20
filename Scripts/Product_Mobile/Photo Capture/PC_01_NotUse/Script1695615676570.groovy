import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import internal.GlobalVariable as GlobalVariable
import java.awt.Robot as Robot
import java.awt.event.KeyEvent as KeyEvent
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import org.apache.commons.lang.RandomStringUtils as RandomStringUtils
import com.kms.katalon.core.mobile.keyword.internal.MobileDriverFactory as MobileDriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import io.appium.java_client.AppiumDriver as AppiumDriver
import java.sql.Connection as Connection
import java.sql.DriverManager as DriverManager
import java.sql.ResultSet as ResultSet
import java.sql.Statement as Statement
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint

//Mobile.callTestCase(findTestCase('Product_Mobile/Common/Mobile_Login/VanSeller_Login-With Reset'), [:], FailureHandling.STOP_ON_FAILURE)
//Mobile.startApplication(GlobalVariable.APKFile, false)
//
//Mobile.delay(35, FailureHandling.STOP_ON_FAILURE)
//
//Mobile.swipe(0, 0, 0, 400, FailureHandling.OPTIONAL)
String sheet_name = 'Task_Management'

String file_name = 'Web Input Data'

ArrayList<String> Retailer = CustomKeywords.'poi.Automation.GetAllWebDataRow_Flag.getAllDataRow'(file_name, sheet_name, 
    'Retailer')

ArrayList<String> Task_title = CustomKeywords.'poi.Automation.GetAllWebDataRow_Flag.getAllDataRow'(file_name, sheet_name, 
    'Task_title')

ArrayList<String> Description = CustomKeywords.'poi.Automation.GetAllWebDataRow_Flag.getAllDataRow'(file_name, sheet_name, 
    'Description')

ArrayList<String> Web_Task = CustomKeywords.'poi.Automation.GetAllWebDataRow_Flag.getAllDataRow'(file_name, sheet_name, 
    'Web_Task')

ArrayList<String> One_day_task = CustomKeywords.'poi.Automation.GetAllWebDataRow_Flag.getAllDataRow'(file_name, sheet_name, 
    'One_day_task')

ArrayList<String> Remark = CustomKeywords.'poi.Automation.GetAllWebDataRow_Flag.getAllDataRow'(file_name, sheet_name, 'Remark')

for (int i = 0; i < Retailer.size(); i++) {
    // Mobile.tap(findTestObject('Mobile/Common/Btn_Menu'), 3)
    Mobile.startApplication(GlobalVariable.APKFile, false)

    Mobile.tap(findTestObject('Mobile/Common/Btn_Menu'), 3)

    Mobile.tap(findTestObject('Object Repository/Mobile/Deviation/Trade Coverage Menu'), 3)

    Mobile.tap(findTestObject('Mobile/Common/Icon_Search'), 0)

    Mobile.tap(findTestObject('Mobile/Common/Field_Search_EnterText'), 0)

    Mobile.sendKeys(findTestObject('Mobile/Common/Field_Search_EnterText'), Retailer.get(
            i))

    Retailer_name = Mobile.getText(findTestObject('Mobile/Seller Task/Retailer'), 0)

    KeywordUtil.logInfo('Retailer_name = ' + Retailer_name)

    Mobile.tap(findTestObject('Mobile/Seller Task/Retailer'), 0)

    Mobile.tap(findTestObject('Mobile/Seller Task/Start Visit button'), 0)

    Mobile.tap(findTestObject('Mobile/Deviation/Location validation yes button'), 5)

    Mobile.delay(2, FailureHandling.STOP_ON_FAILURE)

    not_run: Mobile.startApplication(GlobalVariable.APKFile, false)

    not_run: Mobile.delay(35, FailureHandling.STOP_ON_FAILURE)

    Mobile.swipe(0, 0, 0, 400, FailureHandling.OPTIONAL)

    Robot robot = new Robot()

    robot.mouseMove(630, 420)

    robot.keyPress(KeyEvent.VK_DOWN)

    GlobalVariable.label = findTestData('Data Files/Web Input Data/Survey').getValue('Menu', 4)

    //Mobile.scrollToText(GlobalVariable.label)
    Mobile.swipe(0, 150, 0, 400)

    Mobile.tap(findTestObject('Object Repository/Mobile/Store_Actvy/Menu-StoreCheck'), 0)

    Mobile.delay(2, FailureHandling.STOP_ON_FAILURE)

    GlobalVariable.label = findTestData('Data Files/Web Input Data/Survey').getValue('Menu', 5)

    Mobile.scrollToText(GlobalVariable.label)

    Mobile.tap(findTestObject('null'), 5)

    Mobile.delay(2, FailureHandling.STOP_ON_FAILURE)

    Mobile.verifyElementVisible(findTestObject('Mobile/Photo Capture/Select_Product_DD'), 0)

    Mobile.tap(findTestObject('Mobile/Photo Capture/Select_Product_DD'), 0)

    Mobile.verifyElementVisible(findTestObject('Mobile/Photo Capture/ProductType_DD_Value(Global)'), 0)

    Mobile.tap(findTestObject('Mobile/Photo Capture/ProductType_DD_Value(Global)'), 0)

    product_name = Mobile.getText(findTestObject('Mobile/Photo Capture/Select_Product_Get_text'), 0)

    println(product_name)

    GlobalVariable.Photo_Product_Name = product_name

    Mobile.delay(2, FailureHandling.STOP_ON_FAILURE)

    Mobile.tap(findTestObject('Mobile/Photo Capture/Select_Photo_DD'), 0)

    Mobile.tap(findTestObject('Mobile/Photo Capture/PhotoType_DD_Value(Global)'), 0)

    photo_desc = Mobile.getText(findTestObject('Mobile/Photo Capture/Select_Photo_get_text'), 0)

    println(photo_desc)

    GlobalVariable.Photo_Desc = photo_desc

    Mobile.delay(2, FailureHandling.STOP_ON_FAILURE)

    Mobile.tap(findTestObject('Mobile/Photo Capture/Comments_field'), 0)

    Mobile.setText(findTestObject('Mobile/Photo Capture/Comments_field'), findTestData('Mobile Input Data/NewRetailer').getValue(
            'Comments', 1), 0)

    Mobile.hideKeyboard(FailureHandling.OPTIONAL)

    Mobile.delay(2, FailureHandling.STOP_ON_FAILURE)

    Mobile.tap(findTestObject('Mobile/Photo Capture/Center_Camera_icon'), 0)

    Mobile.delay(2, FailureHandling.STOP_ON_FAILURE)

    Mobile.tap(findTestObject('Mobile/Photo Capture/Camera_photo_click_btn'), 0)

    Mobile.delay(5, FailureHandling.STOP_ON_FAILURE)

    Mobile.tap(findTestObject('Mobile/Common/Btn_OK'), 0)

    Mobile.delay(2, FailureHandling.STOP_ON_FAILURE)

    Mobile.tap(findTestObject('Mobile/Photo Capture/Save_btn_photo_capture'), 0)

    Mobile.takeScreenshot()

    Mobile.tap(findTestObject('Mobile/Common/Btn_OK'), 0)

    //Mobile.callTestCase(findTestCase('Product_Mobile/Common/CloseCall'), [:], FailureHandling.STOP_ON_FAILURE)
    Mobile.swipe(0, 150, 0, 400, FailureHandling.OPTIONAL)

    Mobile.tap(findTestObject('Mobile/Seller Task/Close Call1 Button'), 3, FailureHandling.OPTIONAL)

    Mobile.tap(findTestObject('Mobile/Seller Task/Select Reason for no order'), 3, FailureHandling.OPTIONAL)

    Mobile.tap(findTestObject('null'), 3, FailureHandling.OPTIONAL)

    Mobile.tap(findTestObject('Mobile/Seller Task/Remarks'), 3, FailureHandling.OPTIONAL)

    Mobile.setText(findTestObject('Mobile/Seller Task/Remarks'), Remark.get(i), 5, FailureHandling.OPTIONAL)

    Mobile.tap(findTestObject('Mobile/Seller Task/Close Call2 Button'), 4, FailureHandling.OPTIONAL)

    Mobile.tap(findTestObject('Mobile/Common/Btn_OK'), 4, FailureHandling.OPTIONAL)

    Mobile.closeApplication(FailureHandling.OPTIONAL)

     //Mobile.delay(5, FailureHandling.STOP_ON_FAILURE)

    not_run: Mobile.tap(findTestObject('Mobile/Common/Btn_Menu'), 3)

    not_run: Mobile.delay(2, FailureHandling.STOP_ON_FAILURE)

    not_run: Mobile.swipe(0, 150, 0, 400, FailureHandling.OPTIONAL)

    not_run: Mobile.tap(findTestObject('Mobile/MainMenu/Menu_Sync'), 0)

    not_run: Mobile.tap(findTestObject('Mobile/Sync/Btn-SYNCHRONIZATION'), 0)

    not_run: Mobile.waitForElementPresent(findTestObject('Mobile/Sync/SyncAlert_Data upload completed Successfully'), 
        50, FailureHandling.OPTIONAL)

   // Mobile.tap(findTestObject('Mobile/Common/Btn_OK'), 50, FailureHandling.OPTIONAL)
}

