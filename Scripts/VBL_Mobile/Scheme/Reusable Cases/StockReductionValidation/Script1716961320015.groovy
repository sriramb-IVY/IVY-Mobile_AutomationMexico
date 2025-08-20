import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
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

Mobile.callTestCase(findTestCase('VBL_Mobile/Scheme/Reusable Cases/StockCheck_BeforeInv'), [:], FailureHandling.CONTINUE_ON_FAILURE)

Mobile.callTestCase(findTestCase('VBL_Mobile/Scheme/Reusable Cases/StockAllocation(Web part)'), [:], FailureHandling.CONTINUE_ON_FAILURE)

Mobile.callTestCase(findTestCase('VBL_Mobile/Scheme/Reusable Cases/Admin_Config_Enable'), [:], FailureHandling.CONTINUE_ON_FAILURE)

Mobile.callTestCase(findTestCase('VBL_Mobile/Re-Usables/Mobile_Login/VanSeller_withoutAttendance'), [:], FailureHandling.CONTINUE_ON_FAILURE)

Mobile.callTestCase(findTestCase('VBL_Mobile/Trip/Trip_01 to 05'), [:], FailureHandling.STOP_ON_FAILURE)

Mobile.callTestCase(findTestCase('VBL_Mobile/Re-Usables/Attendance'), [:], FailureHandling.STOP_ON_FAILURE)

Mobile.callTestCase(findTestCase('VBL_Mobile/Van Load/Reusable/VanloadAccept'), [:], FailureHandling.STOP_ON_FAILURE)

Mobile.callTestCase(findTestCase('VBL_Mobile/Scheme/SH_23'), [:], FailureHandling.CONTINUE_ON_FAILURE)

Mobile.callTestCase(findTestCase('VBL_Mobile/Scheme/SH_31'), [:], FailureHandling.CONTINUE_ON_FAILURE)

Mobile.callTestCase(findTestCase('VBL_Mobile/Scheme/SH_33'), [:], FailureHandling.CONTINUE_ON_FAILURE)

Mobile.callTestCase(findTestCase('VBL_Mobile/Scheme/SH_19'), [:], FailureHandling.CONTINUE_ON_FAILURE)

Mobile.callTestCase(findTestCase('VBL_Mobile/Scheme/SH_09_10_11'), [:], FailureHandling.CONTINUE_ON_FAILURE)

Mobile.callTestCase(findTestCase('VBL_Mobile/Scheme/SH_06_07_08'), [:], FailureHandling.CONTINUE_ON_FAILURE)

Mobile.callTestCase(findTestCase('VBL_Mobile/Scheme/SH_01_INV_40'), [:], FailureHandling.CONTINUE_ON_FAILURE)

Mobile.callTestCase(findTestCase('VBL_Mobile/Scheme/SH_43'), [:], FailureHandling.CONTINUE_ON_FAILURE)

Mobile.callTestCase(findTestCase('VBL_Mobile/Scheme/SH_38'), [:], FailureHandling.CONTINUE_ON_FAILURE)

Mobile.callTestCase(findTestCase('VBL_Mobile/Scheme/SH_18'), [:], FailureHandling.CONTINUE_ON_FAILURE)

Mobile.callTestCase(findTestCase('VBL_Mobile/Scheme/SH_36'), [:], FailureHandling.CONTINUE_ON_FAILURE)

Mobile.callTestCase(findTestCase('VBL_Mobile/Scheme/SH_15'), [:], FailureHandling.CONTINUE_ON_FAILURE)

Mobile.callTestCase(findTestCase('VBL_Mobile/StockProposal, VanUnload and ManualVanload/Call_Testcase/Van_unload_CT'), [:], FailureHandling.CONTINUE_ON_FAILURE)

Mobile.callTestCase(findTestCase('VBL_Mobile/Trip/Trip_06 to 10'), [:], FailureHandling.CONTINUE_ON_FAILURE)

Mobile.callTestCase(findTestCase('VBL_Mobile/Re-Usables/Sync'), [:], FailureHandling.CONTINUE_ON_FAILURE)

Mobile.callTestCase(findTestCase('VBL_Mobile/Re-Usables/HHT_Transaction_DB'), [:], FailureHandling.CONTINUE_ON_FAILURE)

Mobile.callTestCase(findTestCase('VBL_Mobile/Scheme/Reusable Cases/Stock_Reconciliation'), [:], FailureHandling.CONTINUE_ON_FAILURE)

WebUI.delay(10, FailureHandling.CONTINUE_ON_FAILURE)

Mobile.callTestCase(findTestCase('VBL_Mobile/Scheme/Reusable Cases/StockCheck_AfterInv'), [:], FailureHandling.CONTINUE_ON_FAILURE)

Mobile.callTestCase(findTestCase('VBL_Mobile/Scheme/Reusable Cases/Stock reduce DB validation'), [:], FailureHandling.CONTINUE_ON_FAILURE)

Mobile.callTestCase(findTestCase('VBL_Mobile/Scheme/Reusable Cases/Admin_Config_Disable'), [:], FailureHandling.CONTINUE_ON_FAILURE)

