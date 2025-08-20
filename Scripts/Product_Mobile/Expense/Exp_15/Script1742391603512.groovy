import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import graphql.Assert as Assert
import internal.GlobalVariable as GlobalVariable
import org.junit.Assert as Assert
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import org.openqa.selenium.Keys as Keys
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.By as By
import org.openqa.selenium.WebElement as WebElement
import org.openqa.selenium.chrome.ChromeDriver as ChromeDriver
import org.openqa.selenium.interactions.Actions as Actions

WebUI.callTestCase(findTestCase('Product_Mobile/Common/Web Login and Navigation/DivisionLogin'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('Product_Mobile/Common/Web Login and Navigation/Navigate to Expense Approval'), [:], FailureHandling.STOP_ON_FAILURE)

'Validating the Screen Name and Other UI:-'
screenName = findTestData('Web Input Data/Expense Approval').getValue('Screen_Name', 1)

app_screenName = WebUI.getAttribute(findTestObject('Web Part/Expense/UI_Validation/expense_approval_screen'), 'textContent')

WebUI.verifyMatch(app_screenName, screenName, false, FailureHandling.STOP_ON_FAILURE)

WebUI.verifyElementVisible(findTestObject('Web Part/Expense/Input_Fields/month_field'), FailureHandling.STOP_ON_FAILURE)

not_run: WebUI.verifyElementVisible(findTestObject('Web Part/Expense/Input_Fields/remarks_field'), FailureHandling.STOP_ON_FAILURE)

not_run: WebUI.verifyElementVisible(findTestObject('Web Part/Expense/Buttons/show_button'), FailureHandling.STOP_ON_FAILURE)

not_run: WebUI.verifyElementVisible(findTestObject('Web Part/Expense/Buttons/reject_button'), FailureHandling.STOP_ON_FAILURE)

WebUI.takeScreenshot()

'Validating the Grid Headers:-'
for (a = 1; a < 8; a++) {
    GlobalVariable.index = a

    sheetGridHeaders = findTestData('Web Input Data/Expense Approval').getValue('Grid_Header', a)

    gridHeaders = WebUI.getText(findTestObject('Web Part/Expense/UI_Validation/grid_headers'))

    validation = sheetGridHeaders.contains(gridHeaders)

    Assert.assertTrue(validation //Fails when the variables validation returns false.
        )

    WebUI.takeScreenshot()
}

'Date Widget validations:-'
WebUI.click(findTestObject('Web Part/Expense/Input_Fields/month_field'))

WebUI.verifyElementVisible(findTestObject('Web Part/Expense/UI_Validation/datepicker_widget'), FailureHandling.STOP_ON_FAILURE)

WebUI.takeScreenshot()

'Call TC to get the past, present, and future month and year:-'
WebUI.callTestCase(findTestCase('Product_Mobile/Expense/Reusable/Get Month and Year'), [('DateCount') : -1], FailureHandling.STOP_ON_FAILURE)

'Future month and year validations:-'
WebUI.click(findTestObject('Web Part/Expense/Click/datepicker_month'))

WebUI.verifyElementNotPresent(findTestObject('Web Part/Expense/UI_Validation/future_month'), 0, FailureHandling.STOP_ON_FAILURE)

WebUI.takeScreenshot()

WebUI.click(findTestObject('Web Part/Expense/Click/datepicker_year'))

WebUI.verifyElementNotPresent(findTestObject('Web Part/Expense/UI_Validation/future_year'), 0, FailureHandling.STOP_ON_FAILURE)

WebUI.takeScreenshot()

'Past month and year validations:-'
WebUI.click(findTestObject('Web Part/Expense/Click/datepicker_month'))

WebUI.click(findTestObject('Web Part/Expense/Click/past_month'))

WebUI.verifyElementText(findTestObject('Web Part/Expense/Click/past_month'), GlobalVariable.pastMonth, FailureHandling.STOP_ON_FAILURE)

WebUI.takeScreenshot()

WebUI.click(findTestObject('Web Part/Expense/Click/datepicker_year'))

WebUI.click(findTestObject('Web Part/Expense/Click/past_year'))

WebUI.verifyElementText(findTestObject('Web Part/Expense/Click/past_year'), GlobalVariable.pastYear, FailureHandling.STOP_ON_FAILURE)

WebUI.takeScreenshot()

WebUI.click(findTestObject('Web Part/Expense/Buttons/done_button'))

'Show button functionality: "No Data Found" Validation:-'
WebUI.click(findTestObject('Web Part/Expense/Buttons/show_button'))

not_run: WebUI.verifyElementText(findTestObject('Web Part/Expense/UI_Validation/no_data_found'), findTestData('Web Input Data/Expense Approval').getValue(
        'Alerts', 1), FailureHandling.STOP_ON_FAILURE)

WebUI.takeScreenshot()

'Show button functionality after choosing the current month and year:-'
WebUI.click(findTestObject('Web Part/Expense/Input_Fields/month_field'))

WebUI.click(findTestObject('Web Part/Expense/Click/datepicker_month'))

WebUI.click(findTestObject('Web Part/Expense/Click/current_month'))

WebUI.click(findTestObject('Web Part/Expense/Click/datepicker_year'))

WebUI.click(findTestObject('Web Part/Expense/Click/current_year'))

WebUI.click(findTestObject('Web Part/Expense/Buttons/show_button'))


//Mobile.callTestCase(findTestCase('Product_Mobile/Expense/Reusable/Mobile Date Picker-EnableValidation'), [('DateCount') : -2],FailureHandling.STOP_ON_FAILURE)
'Grid Expense Details Validation:-'
One_of_The_Expense_Amount = findTestData('Mobile Input Data/Expense').getValue('Amount', 3)

expenseStatus = findTestData('Web Input Data/Expense Approval').getValue('Status', 1)

GlobalVariable.expenseType = (expenseType = findTestData('Mobile Input Data/Expense').getValue('Expense_Type', 3))

'Total Amount: Datatype conversion:-'
GlobalVariable.sDate = WebUI.getText(findTestObject('Web Part/Expense/Grid_Detail/expense_Date'))

String tAmount = WebUI.getText(findTestObject('Web Part/Expense/Grid_Detail/row1_total_amount'))

totalAmount = tAmount.replace('$', '')

GlobalVariable.Total_Amt = totalAmount

WebUI.verifyEqual(Double.parseDouble(One_of_The_Expense_Amount), Double.parseDouble(totalAmount), FailureHandling.STOP_ON_FAILURE)

WebUI.takeScreenshot()

'Expense Status and Status By validations:-'
status = WebUI.getText(findTestObject('Web Part/Expense/Grid_Detail/status'))

WebUI.verifyMatch(expenseStatus, status, false, FailureHandling.STOP_ON_FAILURE)

WebUI.takeScreenshot()

String userName = GlobalVariable.VanSeller_UserName

statusBy = WebUI.getText(findTestObject('Web Part/Expense/Grid_Detail/statusby'))

formattedUserName = (userName.substring(0, 1).toUpperCase() + userName.substring(1).toLowerCase())

WebUI.verifyMatch(formattedUserName, statusBy, false, FailureHandling.STOP_ON_FAILURE)

WebUI.takeScreenshot()

'Checkbox validation:-'
not_run: WebUI.verifyElementVisible(findTestObject('Web Part/Expense/Grid_Detail/checkbox_ViewIcon'), FailureHandling.STOP_ON_FAILURE)

WebUI.takeScreenshot()

WebUI.click(findTestObject('Web Part/Expense/Grid_Detail/checkbox_ViewIcon'))

'Expense type, Total Amount validations:-'
WebUI.verifyElementText(findTestObject('Web Part/Expense/Grid_Detail/expense_type'), expenseType, FailureHandling.STOP_ON_FAILURE)

String Actual_Amt = WebUI.getText(findTestObject('Web Part/Expense/Grid_Detail/grid_split_amount'))

WebUI.verifyEqual(Double.parseDouble(One_of_The_Expense_Amount), Double.parseDouble(Actual_Amt), FailureHandling.STOP_ON_FAILURE)

WebUI.takeScreenshot()

'Reimbursed amount field and Image icon validations:-'
WebUI.verifyElementVisible(findTestObject('Web Part/Expense/Grid_Detail/grid_split_reimbursed'), FailureHandling.STOP_ON_FAILURE)

WebUI.verifyElementVisible(findTestObject('Web Part/Expense/Grid_Detail/image_icon'), FailureHandling.OPTIONAL)

WebUI.takeScreenshot()

'Approve button functionality: "Please enter Reimbursed Amount" toast validation:-'

WebUI.click(findTestObject('Web Part/Expense/Buttons/approve_button'))

WebUI.acceptAlert()

WebUI.switchToDefaultContent()

WebUI.verifyElementText(findTestObject('Web Part/Expense/UI_Validation/toast_msg_validation'), findTestData('Web Input Data/Expense Approval').getValue(
        'Alerts', 4), FailureHandling.STOP_ON_FAILURE)

WebUI.takeScreenshot()

WebUI.click(findTestObject('Web Part/Expense/UI_Validation/close_toast_msg'), FailureHandling.OPTIONAL)

'"Reimbused Amount Should not exceed the expense amount!": Alert validation:-'
defaultimbursedAmount = WebUI.getAttribute(findTestObject('Web Part/Expense/Grid_Detail/grid_split_reimbursed'), 'value')

WebUI.click(findTestObject('Web Part/Expense/Grid_Detail/grid_split_reimbursed'))

WebDriver driver = DriverFactory.getWebDriver()

Actions actions = new Actions(driver)

actions.keyDown(Keys.CONTROL).sendKeys('a').keyUp(Keys.CONTROL).perform()

actions.sendKeys(Keys.BACK_SPACE).perform()

amt = findTestData('Web Input Data/Expense Approval').getValue('Excess_Reimbursed_Amount', 1)

actions.sendKeys(amt).perform()

WebUI.click(findTestObject('Web Part/Expense/Grid_Detail/grid_split_amount'))

WebUI.verifyElementText(findTestObject('Web Part/Expense/UI_Validation/toast_msg_validation'), findTestData('Web Input Data/Expense Approval').getValue(
        'Alerts', 2), FailureHandling.STOP_ON_FAILURE)

WebUI.takeScreenshot()

'Less reimbursed amount validation:-'
Double lessAmount = Double.parseDouble(One_of_The_Expense_Amount) - 5

WebUI.delay(3)

WebUI.doubleClick(findTestObject('Web Part/Expense/Grid_Detail/grid_split_reimbursed'), FailureHandling.STOP_ON_FAILURE)

actions.keyDown(Keys.CONTROL).sendKeys('a').keyUp(Keys.CONTROL).perform()

actions.sendKeys(Keys.BACK_SPACE).perform()

actions.sendKeys(lessAmount.round().toString()).perform()

WebUI.click(findTestObject('Web Part/Expense/Grid_Detail/grid_split_amount'))

WebUI.getText(findTestObject('Web Part/Expense/Grid_Detail/grid_split_reimbursed'), FailureHandling.STOP_ON_FAILURE)

GlobalVariable.reimbursedAmt = (reimbursedAmount = WebUI.getText(findTestObject('Web Part/Expense/Grid_Detail/grid_split_reimbursed'), 
    FailureHandling.STOP_ON_FAILURE))

WebUI.verifyLessThan(Double.parseDouble(reimbursedAmount), Double.parseDouble(One_of_The_Expense_Amount), FailureHandling.STOP_ON_FAILURE)

WebUI.takeScreenshot()

enteredReimbursedAmount = WebUI.getText(findTestObject('Web Part/Expense/Grid_Detail/grid_split_reimbursed'), FailureHandling.STOP_ON_FAILURE)

WebUI.verifyEqual(Double.parseDouble(enteredReimbursedAmount), lessAmount, FailureHandling.STOP_ON_FAILURE)

WebUI.takeScreenshot()

'Reimbursed amount field: Editable:-'
if (enteredReimbursedAmount != '0') {
    KeywordUtil.logInfo('The Reimbursed field is editable')
} else {
    Assert.fail()
}

'Image icon validation:-'
WebUI.click(findTestObject('Web Part/Expense/Grid_Detail/image_icon'), FailureHandling.OPTIONAL)

WebUI.verifyElementVisible(findTestObject('Web Part/Expense/UI_Validation/GalleryWindow'), FailureHandling.STOP_ON_FAILURE)

WebUI.takeScreenshot()

'Back Button Functionality:-'
WebUI.click(findTestObject('Web Part/Expense/Buttons/back_button'))

WebUI.verifyElementVisible(findTestObject('Web Part/Expense/UI_Validation/expense_breakup_screen'), FailureHandling.STOP_ON_FAILURE)

WebUI.takeScreenshot()

'Remark field validation:-'
sheetRemark = findTestData('Web Input Data/Expense Approval').getValue('Remark', 1)

WebUI.click(findTestObject('Web Part/Expense/Input_Fields/remarks_field'))

WebUI.sendKeys(findTestObject('Web Part/Expense/Input_Fields/remarks_field'), sheetRemark)

remark = WebUI.getAttribute(findTestObject('Web Part/Expense/Input_Fields/remarks_field'), 'value')

WebUI.verifyMatch(remark, sheetRemark, false, FailureHandling.STOP_ON_FAILURE)

WebUI.takeScreenshot()

WebUI.doubleClick(findTestObject('Web Part/Expense/Grid_Detail/grid_split_reimbursed'), FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Web Part/Expense/Grid_Detail/grid_split_reimbursed'))

actions.keyDown(Keys.CONTROL).sendKeys('a').keyUp(Keys.CONTROL).perform()

actions.sendKeys('0').perform()

WebUI.click(findTestObject('Web Part/Expense/Grid_Detail/grid_split_amount'))

'Reject button and Rejected Successfully Toast validation:-'
GlobalVariable.reimbursedAmt = (getReimbursedAmt = WebUI.getText(findTestObject('Web Part/Expense/Grid_Detail/grid_split_reimbursed'), 
    FailureHandling.STOP_ON_FAILURE))

if (getReimbursedAmt == '0') {
   // WebUI.click(findTestObject('Web Part/Expense/Grid_Detail/checkbox_ViewIcon'))

    WebUI.click(findTestObject('Web Part/Expense/Buttons/reject_button'))

    WebUI.acceptAlert()

    WebUI.switchToDefaultContent()

    WebUI.delay(1)

    WebUI.verifyElementText(findTestObject('Web Part/Expense/UI_Validation/toast_msg_validation'), findTestData('Web Input Data/Expense Approval').getValue(
            'Alerts', 6), FailureHandling.STOP_ON_FAILURE)

    WebUI.takeScreenshot()

    WebUI.click(findTestObject('Web Part/Expense/UI_Validation/close_toast_msg'), FailureHandling.OPTIONAL)

    'Status Validation:-'
    expenseStatus = findTestData('Web Input Data/Expense Approval').getValue('Status', 2)

    status = WebUI.getText(findTestObject('Web Part/Expense/Grid_Detail/status'))

    WebUI.verifyMatch(expenseStatus, status, false, FailureHandling.STOP_ON_FAILURE)

    WebUI.takeScreenshot()

    'StatusBy Validation:-'
    String adminUserName = GlobalVariable.DivisionUsername

    statusBy = WebUI.getText(findTestObject('Web Part/Expense/Grid_Detail/statusby'))

    formattedAdminUserName = adminUserName.toLowerCase()

    WebUI.verifyMatch(formattedAdminUserName, statusBy, false, FailureHandling.STOP_ON_FAILURE)

    WebUI.verifyNotMatch(formattedUserName, formattedAdminUserName, false, FailureHandling.STOP_ON_FAILURE)

    WebUI.takeScreenshot()

    'Rejection DB Validation:-'
    Mobile.callTestCase(findTestCase('Product_Mobile/Expense/Reusable/Rejection DB Validation'), [:], FailureHandling.STOP_ON_FAILURE)
}

'"Rejected Successfully" Toast message validation after entering the reimbursed amount:-'
GlobalVariable.Total_Amt = (Total_Amt = WebUI.getText(findTestObject('Web Part/Expense/Grid_Detail/row2_total_amount')))

KeywordUtil.logInfo('Total Amount in the second row: ' + Total_Amt)

WebUI.click(findTestObject('Web Part/Expense/Grid_Detail/checkbox_ViewIcon'))

travelExpense = findTestData('Mobile Input Data/Expense').getValue('Amount', 4)

others = findTestData('Mobile Input Data/Expense').getValue('Amount', 2)

GlobalVariable.expenseType = findTestData('Mobile Input Data/Expense').getValue('Expense_Type', 4)

WebUI.click(findTestObject('Web Part/Expense/Grid_Detail/grid_split_reimbursed'))

actions.keyDown(Keys.CONTROL).sendKeys('a').keyUp(Keys.CONTROL).perform()

actions.sendKeys(Keys.BACK_SPACE).perform()

actions.sendKeys(travelExpense).perform()

WebUI.click(findTestObject('Web Part/Expense/Grid_Detail/grid_split_amount'))

'"Please enter Reimbursed Amount" toast message validation:-'
WebUI.click(findTestObject('Web Part/Expense/Buttons/reject_button'))

WebUI.acceptAlert()

WebUI.switchToDefaultContent()

WebUI.delay(1)

WebUI.verifyElementText(findTestObject('Web Part/Expense/UI_Validation/toast_msg_validation'), findTestData('Web Input Data/Expense Approval').getValue(
        'Alerts', 6), FailureHandling.STOP_ON_FAILURE)

WebUI.takeScreenshot()

WebUI.click(findTestObject('Web Part/Expense/UI_Validation/close_toast_msg'), FailureHandling.OPTIONAL)

'Rejection DB Validation:-'
Mobile.callTestCase(findTestCase('Product_Mobile/Expense/Reusable/Rejection DB Validation'), [:], FailureHandling.STOP_ON_FAILURE)


//WebDriver driver = DriverFactory.getWebDriver()
//
//Actions actions = new Actions(driver)

'Approval functionality validation without entering one of the reimbursed amount:-'
GlobalVariable.Total_Amt = (Total_Amt = WebUI.getText(findTestObject('Web Part/Expense/Grid_Detail/row3_total_amount')))

KeywordUtil.logInfo('Total Amount in the third row: ' + Total_Amt)

WebUI.click(findTestObject('Web Part/Expense/Grid_Detail/checkbox_ViewIcon'))

GlobalVariable.dailyExpense = (dailyExpense = findTestData('Mobile Input Data/Expense').getValue('Amount', 1))

GlobalVariable.otherExpense = (others = findTestData('Mobile Input Data/Expense').getValue('Amount', 2))

GlobalVariable.expenseType = findTestData('Mobile Input Data/Expense').getValue('Expense_Type', 1)

WebUI.click(findTestObject('Web Part/Expense/Grid_Detail/grid_split_reimbursed'))

actions.keyDown(Keys.CONTROL).sendKeys('a').keyUp(Keys.CONTROL).perform()

actions.sendKeys(Keys.BACK_SPACE).perform()

dailyExpense = findTestData('Mobile Input Data/Expense').getValue('Amount', 1)

actions.sendKeys(dailyExpense).perform()

WebUI.click(findTestObject('Web Part/Expense/Grid_Detail/grid_split_amount'))

'"Please enter Reimbursed Amount" toast message validation:-'
WebUI.click(findTestObject('Web Part/Expense/Buttons/approve_button'))

WebUI.acceptAlert()

WebUI.switchToDefaultContent()

WebUI.delay(1)

WebUI.verifyElementText(findTestObject('Web Part/Expense/UI_Validation/toast_msg_validation'), findTestData('Web Input Data/Expense Approval').getValue(
        'Alerts', 4), FailureHandling.STOP_ON_FAILURE)

WebUI.takeScreenshot()

WebUI.click(findTestObject('Web Part/Expense/UI_Validation/close_toast_msg'), FailureHandling.OPTIONAL)

'Approve button validation after entering the reimbursed amount for all the expenses:-'
GlobalVariable.expenseType = findTestData('Mobile Input Data/Expense').getValue('Expense_Type', 2)

//WebUI.click(findTestObject('Web Part/Expense/Grid_Detail/grid_split_reimbursed'))

WebUI.doubleClick(findTestObject('Web Part/Expense/Grid_Detail/grid_split_reimbursed_2'), FailureHandling.STOP_ON_FAILURE)

actions.keyDown(Keys.CONTROL).sendKeys('a').keyUp(Keys.CONTROL).perform()

actions.sendKeys(Keys.BACK_SPACE).perform()

others = findTestData('Mobile Input Data/Expense').getValue('Amount', 2)

actions.sendKeys(others).perform()

WebUI.click(findTestObject('Web Part/Expense/Grid_Detail/grid_split_amount'))

'"Approved Successfully" toast message validation:-'
WebUI.click(findTestObject('Web Part/Expense/Buttons/approve_button'))

WebUI.acceptAlert()

WebUI.switchToDefaultContent()

WebUI.delay(1)

WebUI.verifyElementText(findTestObject('Web Part/Expense/UI_Validation/toast_msg_validation'), findTestData('Web Input Data/Expense Approval').getValue(
        'Alerts', 7), FailureHandling.STOP_ON_FAILURE)

WebUI.takeScreenshot()

WebUI.click(findTestObject('Web Part/Expense/UI_Validation/close_toast_msg'), FailureHandling.OPTIONAL)

'Status Validation:-'
expenseStatus = findTestData('Web Input Data/Expense Approval').getValue('Status', 3)

approvalStatus = WebUI.getText(findTestObject('Web Part/Expense/Grid_Detail/status'))

WebUI.verifyMatch(expenseStatus, approvalStatus, false, FailureHandling.STOP_ON_FAILURE)

WebUI.takeScreenshot()

'StatusBy Validation:-'
String adminUserName = GlobalVariable.DivisionUsername

statusBy = WebUI.getText(findTestObject('Web Part/Expense/Grid_Detail/statusby'))

formattedAdminUserName = adminUserName.toLowerCase()

WebUI.verifyMatch(formattedAdminUserName, statusBy, false, FailureHandling.STOP_ON_FAILURE)

WebUI.verifyNotMatch(formattedUserName, formattedAdminUserName, false, FailureHandling.STOP_ON_FAILURE)

WebUI.takeScreenshot()

'Approval DB Validation:-'
GlobalVariable.expenseStatus = findTestData('Web Input Data/Expense Approval').getValue('DB_Status', 2)

Mobile.callTestCase(findTestCase('Product_Mobile/Expense/Reusable/Approval DB Validation'), [:], FailureHandling.STOP_ON_FAILURE)

