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
import java.text.DateFormat as DateFormat
import java.text.SimpleDateFormat as SimpleDateFormat
import java.util.Date as Date
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import java.sql.Connection as Connection
import java.sql.DriverManager as DriverManager
import java.sql.ResultSet as ResultSet
import java.sql.Statement as Statement
import com.kms.katalon.core.mobile.keyword.internal.MobileDriverFactory as MobileDriverFactory
import io.appium.java_client.AppiumDriver as AppiumDriver
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import org.openqa.selenium.WebElement as WebElement

/*Mobile.callTestCase(findTestCase('Product_Mobile/Common/Mobile_Login/VanSeller_Login-With Reset'), [:], FailureHandling.STOP_ON_FAILURE)
//Mobile.startApplication(GlobalVariable.APK_File, false)
//CustomKeywords.'android.Keywords.android_custom_keywords.Resetapk'()*/

Mobile.tap(findTestObject('Mobile/Common/Btn_Menu'), 0)

Mobile.scrollToText('Expense', FailureHandling.STOP_ON_FAILURE)

Mobile.tap(findTestObject('Mobile/Expense/Menu_Expense'), 0)

for (i = 1; i < 3; i++) {
    'Create new expense'
    Mobile.tap(findTestObject('Mobile/Expense/Field_Date'), 0)

    Mobile.callTestCase(findTestCase('Product_Mobile/Expense/Reusable/Mobile Date Picker-EnableValidation'), [('DateCount') : -2], FailureHandling.STOP_ON_FAILURE)

    Mobile.verifyElementVisible(findTestObject('Mobile/SummaryScreen/date_Global'), 0)

    Mobile.tap(findTestObject('Mobile/SummaryScreen/date_Global'), 0)

    Mobile.tap(findTestObject('Mobile/Common/Btn_OK'), 0)

    Mobile.tap(findTestObject('Mobile/Expense/Field_Dropdown_ExpenseType'), 0)

    expenseType = (GlobalVariable.label = findTestData('Mobile Input Data/Expense').getValue('Expense_Type', 4))

    Amount = findTestData('Mobile Input Data/Expense').getValue('Amount', 4)

    Mobile.tap(findTestObject('Mobile/Expense/Dropdown_Global'), 0)

    Mobile.setText(findTestObject('Mobile/Expense/Field_Amount'), Amount, 0)

    Mobile.setText(findTestObject('Mobile/Expense/Field_Remark'), findTestData('Mobile Input Data/Expense').getValue('Remarks', 4), 0)

    Mobile.tap(findTestObject('Mobile/Expense/Camera_Icon'), 0)

    Mobile.waitForElementPresent(findTestObject('Mobile/Expense/Camera_CenterIcon'), 0, FailureHandling.STOP_ON_FAILURE)

    Mobile.tap(findTestObject('Mobile/Expense/Camera_CenterIcon'), 0)

    Mobile.takeScreenshot()

    Mobile.waitForElementPresent(findTestObject('Mobile/Expense/Camera_TickBtn'), 0, FailureHandling.STOP_ON_FAILURE)

    Mobile.tap(findTestObject('Mobile/Expense/Camera_TickBtn'), 0)

    //Capturing the image count:-	
    GlobalVariable.Img = Mobile.getAttribute(findTestObject('Mobile/Expense/ImageCount'), 'text', 0)

    'The "Save Successfully." alert toast should be visible.'
    Mobile.tap(findTestObject('Mobile/Common/Btn_Done'), 0)

    Mobile.takeScreenshot()

    if (i == 1) {
		'Date CT to validate the Date field:-'
        Mobile.callTestCase(findTestCase('Product_Mobile/Expense/Reusable/Date(DD-MM-YYYY)'), [('DateCount') : -2], FailureHandling.STOP_ON_FAILURE)

        GlobalVariable.label = GlobalVariable.sDate

		'Validating the Expense Date and Type:-'
        Mobile.verifyElementText(findTestObject('Mobile/Expense/Grid_Value_date'), GlobalVariable.label)

        GlobalVariable.label = findTestData('Mobile Input Data/Expense').getValue('Amount', 4)

        Mobile.verifyElementText(findTestObject('Mobile/Expense/MTD_Grid_Expense_Type'), expenseType, FailureHandling.STOP_ON_FAILURE)

		'Validating the Image Proof dialog box and the functionality of its Cancel and OK buttons:-'
        Mobile.tap(findTestObject('Mobile/Expense/Grid_Value_CaptureProof'), 0)

        Mobile.verifyElementVisible(findTestObject('Mobile/Expense/ImageProof_DialogBox'), 0, FailureHandling.STOP_ON_FAILURE)

        Mobile.tap(findTestObject('Mobile/Expense/ImageProof_CloseBtn'), 0)

        Mobile.verifyElementNotVisible(findTestObject('Mobile/Expense/ImageProof_DialogBox'), 0, FailureHandling.STOP_ON_FAILURE)

        Mobile.tap(findTestObject('Mobile/Expense/Grid_Value_CaptureProof'), 0)

        Mobile.tap(findTestObject('Mobile/Expense/DeleteImageFromProof'), 0)

        Mobile.verifyElementVisible(findTestObject('Mobile/Expense/ImageDeleteConfirmationMsg'), 0, FailureHandling.STOP_ON_FAILURE)

        Mobile.verifyElementVisible(findTestObject('Mobile/Expense/ImgDeleteConfirmationCancelBtn'), 0, FailureHandling.STOP_ON_FAILURE)

        Mobile.verifyElementVisible(findTestObject('Mobile/Expense/ImgDeleteConfirmationOkBtn'), 0, FailureHandling.STOP_ON_FAILURE)

        Mobile.tap(findTestObject('Mobile/Expense/ImgDeleteConfirmationCancelBtn'), 0)

        Mobile.verifyElementNotVisible(findTestObject('Mobile/Expense/ImageDeleteConfirmationMsg'), 0, FailureHandling.STOP_ON_FAILURE)

        Mobile.tap(findTestObject('Mobile/Expense/DeleteImageFromProof'), 0)

        Mobile.tap(findTestObject('Mobile/Expense/ImgDeleteConfirmationOkBtn'), 0)

        countAfterDeletion = Mobile.getAttribute(findTestObject('Mobile/Expense/ImageCount'), 'text', 0)

        Mobile.verifyNotMatch(GlobalVariable.Img, countAfterDeletion, false, FailureHandling.STOP_ON_FAILURE)

		'Validating the Remarks:-'
        GlobalVariable.label = findTestData('Mobile Input Data/Expense').getValue('Remarks', 4)

        Mobile.verifyElementText(findTestObject('Mobile/Expense/Grid_Value_Remark'), GlobalVariable.label)

		'Validating the expense deletion and the cancel and ok buttons functionalities:-'
        Mobile.longPress(findTestObject('Mobile/Expense/Grid_Value_Remark'), 0)
		
		Mobile.verifyElementVisible(findTestObject('Mobile/Expense/ExpenseDeleteConfirmationMsg'), 0, FailureHandling.STOP_ON_FAILURE)
		
		Mobile.verifyElementVisible(findTestObject('Mobile/Expense/ImgDeleteConfirmationCancelBtn'), 0, FailureHandling.STOP_ON_FAILURE)

		Mobile.verifyElementVisible(findTestObject('Mobile/Expense/ImgDeleteConfirmationOkBtn'), 0, FailureHandling.STOP_ON_FAILURE)

		Mobile.tap(findTestObject('Mobile/Expense/ImgDeleteConfirmationCancelBtn'), 0)
		
		Mobile.verifyElementNotVisible(findTestObject('Mobile/Expense/ExpenseDeleteConfirmationMsg'), 0, FailureHandling.STOP_ON_FAILURE)
		
		Mobile.longPress(findTestObject('Mobile/Expense/Grid_Value_Remark'), 0)
		
		Mobile.tap(findTestObject('Mobile/Expense/ImgDeleteConfirmationOkBtn'), 0)
		
		Mobile.verifyElementNotVisible(findTestObject('Mobile/Expense/Grid_Value_Remark'), 0, FailureHandling.STOP_ON_FAILURE)
				
    } else if (i == 2) {
        AppiumDriver driver = MobileDriverFactory.getDriver()

        Actualtoastmessage = driver.findElementByXPath('//android.widget.Toast[1]').getText()

        KeywordUtil.logInfo(Actualtoastmessage)

        Mobile.verifyMatch(Actualtoastmessage, findTestData('Mobile Input Data/Expense').getValue('Alerts', 3), false)

        'The Total Amount should be shown expense value '
        Mobile.takeScreenshot()

        expense_val = Mobile.getText(findTestObject('Mobile/Expense/Field_TotalAmount'), 0)

        expected_expense_Val = findTestData('Mobile Input Data/Expense').getValue('Amount', 4)

        Mobile.verifyEqual(Double.parseDouble(expense_val), Double.parseDouble(expected_expense_Val), FailureHandling.STOP_ON_FAILURE)
    }
}

