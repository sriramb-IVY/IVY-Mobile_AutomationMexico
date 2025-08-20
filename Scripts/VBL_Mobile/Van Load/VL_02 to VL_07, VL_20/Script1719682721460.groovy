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


Mobile.callTestCase(findTestCase('VBL_Mobile/Re-Usables/Mobile_Login/VanSeller_Login-With Reset'), [:], FailureHandling.STOP_ON_FAILURE)

//Mobile.startApplication(GlobalVariable.APK_File, false)
//Mobile.tap(findTestObject('XXXXXXXXXXXX/Re-usable/Mobile Login/Menu'), 0)
Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/MainMenu/Menu_Load Management'), 0)



'VL_20 - To verify whether star sysmbol is present or not in the highlighted modules'

Mobile.takeScreenshot()

Mobile.verifyElementVisible(findTestObject('XXXXXXXXXXXX/Mobile Part/LoadManagement/Vanload/SubMenu_Star_Symbol'), 0)

Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/LoadManagement/SubMenu-Van Load'), 0)

Mobile.delay(2)

Mobile.verifyElementVisible(findTestObject('XXXXXXXXXXXX/Mobile Part/LoadManagement/Vanload/VanLoad-Title'), 5)

Mobile.verifyElementText(findTestObject('XXXXXXXXXXXX/Mobile Part/LoadManagement/Vanload/VanLoad-Title'), findTestData('VBL_Mobile Input Data/VanLoad').getValue('ScreenHeader', 1), FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

//VL_03 To verify that Van load screen is displaying 


//GlobalVariable.VanLoad_No = 'VAN/20062024/0059'

println('Van Load Reference no  :  ' + GlobalVariable.VanLoad_No)

Mobile.scrollToText(GlobalVariable.VanLoad_No, FailureHandling.OPTIONAL)

Mobile.verifyElementExist(findTestObject('XXXXXXXXXXXX/Mobile Part/LoadManagement/Vanload/VanLoading No-(Global)'), 5)

Mobile.verifyElementVisible(findTestObject('XXXXXXXXXXXX/Mobile Part/LoadManagement/Vanload/VanLoading No-(Global)'), 5)

Mobile.takeScreenshot()

println(' To verify that User is able to view vanload no')

//VL_05 To verify that User is able to view vanload no
Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/LoadManagement/Vanload/VanLoading No-(Global)'), 0)

Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Reusable Object/Product Search Icon'), 0)

WebUI.callTestCase(findTestCase('Product_Mobile/Common/Search_Product'), [:], FailureHandling.STOP_ON_FAILURE)

Mobile.setText(findTestObject('XXXXXXXXXXXX/Mobile Part/Reusable Object/Product Enter Search'), GlobalVariable.ProductName, 0)

Mobile.delay(2)

Mobile.verifyElementVisible(findTestObject('XXXXXXXXXXXX/Mobile Part/LoadManagement/Vanload/VL_Case_Value'), 5, FailureHandling.STOP_ON_FAILURE)

Mobile.verifyElementVisible(findTestObject('XXXXXXXXXXXX/Mobile Part/LoadManagement/Vanload/VL_Piece_Value'), 5, FailureHandling.STOP_ON_FAILURE)

Mobile.verifyElementVisible(findTestObject('XXXXXXXXXXXX/Mobile Part/LoadManagement/Vanload/VL_TotalValueField'), 5)

Mobile.takeScreenshot()

println('Verified : that User is able to view SKU qty and Value')

//VL_04 To verify that User is able to view SKU qty and Value
//case_Qty = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/LoadManagement/Vanload/VL_Case_Value'), 0)
//
//println(case_Qty)
//
//Mobile.verifyMatch(case_Qty, GlobalVariable.CaseQty, false, FailureHandling.OPTIONAL)
piece_Qty = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/LoadManagement/Vanload/VL_Piece_Value'), 0)

println(piece_Qty)

Mobile.verifyEqual(Integer.parseInt(piece_Qty), GlobalVariable.PieceQty, FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

println('Verified : Entered Case and Piece Qty in web proposed screen, that same Qty reflected Mobile Vanload screen Properly! ')

Mobile.tap(findTestObject('Mobile/Common/Icon_Back'), 0)

Mobile.tap(findTestObject('Mobile/Common/Icon_Back'), 0)

Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/LoadManagement/SubMenu_Current Stock view'), 0)

Mobile.verifyElementExist(findTestObject('XXXXXXXXXXXX/Mobile Part/LoadManagement/CurrentStockView/Title_Current Stock view'), 0)

Mobile.verifyElementVisible(findTestObject('XXXXXXXXXXXX/Mobile Part/LoadManagement/CurrentStockView/Title_Current Stock view'), 0)

List<String> SKU_List = new ArrayList<String>()

for(int i = 1; i<=20; i++)
{
	GlobalVariable.label = i
	
	if(Mobile.verifyElementExist(findTestObject('XXXXXXXXXXXX/Mobile Part/LoadManagement/Vanload/Productlist(Global-Index)'), 2, FailureHandling.OPTIONAL))
	{
	SKU = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/LoadManagement/Vanload/Productlist(Global-Index)'), 0, FailureHandling.STOP_ON_FAILURE)

	SKU_List.add(SKU)
	
	}
	else
	{
		break
	}
}

ArrayList<String> duplicate_removed_SKU_List = new LinkedHashSet<String>(SKU_List)

KeywordUtil.logInfo(duplicate_removed_SKU_List.toString())

Collections.sort(duplicate_removed_SKU_List)

KeywordUtil.logInfo(Collections.sort(duplicate_removed_SKU_List))

//KeywordUtil.logInfo(Collections.sort(GlobalVariable.label1))

for(int j = 1; j<=20; j++)
{
	GlobalVariable.label = j
	
	if(Mobile.verifyElementExist(findTestObject('XXXXXXXXXXXX/Mobile Part/LoadManagement/Vanload/Productlist(Global-Index)'), 2, FailureHandling.OPTIONAL))
	{
	SKU = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/LoadManagement/Vanload/Productlist(Global-Index)'), 0, FailureHandling.STOP_ON_FAILURE)

	Mobile.verifyMatch(SKU, duplicate_removed_SKU_List[j-1], false, FailureHandling.STOP_ON_FAILURE)
	}
	else
	{
		break
	}
}


Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Reusable Object/Product Search Icon'), 0)

WebUI.callTestCase(findTestCase('Product_Mobile/Common/Search_Product'), [:], FailureHandling.STOP_ON_FAILURE)

Mobile.setText(findTestObject('XXXXXXXXXXXX/Mobile Part/Reusable Object/Product Enter Search'), GlobalVariable.ProductName, 0)

if (Mobile.verifyElementVisible(findTestObject('XXXXXXXXXXXX/Mobile Part/LoadManagement/CurrentStockView/SIH_Value'), 5, FailureHandling.OPTIONAL)) {
    Mobile.takeScreenshot()

    println('that Currwent stock view is displaying')

    Before_SIH_value = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/LoadManagement/CurrentStockView/SIH_Value'), 0)

    println('Before vanload accept_SIH_value  : ' + Before_SIH_value)

    Mobile.tap(findTestObject('Mobile/Common/Icon_Back'), 0)

    Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/LoadManagement/SubMenu-Van Load'), 0)

    Mobile.scrollToText(GlobalVariable.VanLoad_No, FailureHandling.OPTIONAL)

    Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/LoadManagement/Vanload/Global_RadioIcon'), 3, FailureHandling.OPTIONAL)

    Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/LoadManagement/Vanload/VanLoadScreen-Accept Btn'), 3, FailureHandling.OPTIONAL)

    Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/LoadManagement/Vanload/OK Btn'), 3, FailureHandling.OPTIONAL)

    AppiumDriver driver = MobileDriverFactory.getDriver()

    Actualtoastmessage = driver.findElementByXPath('//android.widget.Toast[1]').getText()

    println(Actualtoastmessage)

    Mobile.verifyMatch(Actualtoastmessage, findTestData('VBL_Mobile Input Data/VanLoad').getValue('Alerts', 1), false)

    Mobile.takeScreenshot()

    println('Verified : that User is able to accept the van load')

    //VL_06 To verify that User is able to accept the van load 
    Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/LoadManagement/SubMenu_Current Stock view'), 0)

    Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Reusable Object/Product Search Icon'), 0)

    WebUI.callTestCase(findTestCase('Product_Mobile/Common/Search_Product'), [:], FailureHandling.STOP_ON_FAILURE)

    Mobile.setText(findTestObject('XXXXXXXXXXXX/Mobile Part/Reusable Object/Product Enter Search'), findTestData('VBL_Web Input Data/Stock allocation').getValue('SKU_Name', 1), 0)

    after_SIH_value = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/LoadManagement/CurrentStockView/SIH_Value'), 0)

    println('Before vanload accept_SIH_value  : ' + Before_SIH_value)

    println('after_SIH_value : ' + after_SIH_value)

    Mobile.verifyNotMatch(after_SIH_value, Before_SIH_value, false)

    Mobile.takeScreenshot()

    println('Verified : that Currwent stock view is displaying' //VL_06 To verify that User is able to accept the van load
        )
} else {
    Mobile.tap(findTestObject('Mobile/Common/Icon_Back'), 0)

    Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/LoadManagement/SubMenu-Van Load'), 0)

    Mobile.scrollToText(GlobalVariable.VanLoad_No, FailureHandling.OPTIONAL)

    Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/LoadManagement/Vanload/VanLoading No-(Global)'), 0)

    Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Reusable Object/Product Search Icon'), 0)

    WebUI.callTestCase(findTestCase('Product_Mobile/Common/Search_Product'), [:], FailureHandling.STOP_ON_FAILURE)

    Mobile.setText(findTestObject('XXXXXXXXXXXX/Mobile Part/Reusable Object/Product Enter Search'), GlobalVariable.ProductName, 0)

    case_piece_Qty = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/LoadManagement/Vanload/VL_Piece_Value'), 0)

    println(case_piece_Qty)

    Mobile.tap(findTestObject('Mobile/Common/Icon_Back'), 0)

    Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/LoadManagement/Vanload/Global_RadioIcon'), 3, FailureHandling.OPTIONAL)

    Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/LoadManagement/Vanload/VanLoadScreen-Accept Btn'), 3, FailureHandling.OPTIONAL)

    Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/LoadManagement/Vanload/OK Btn'), 3, FailureHandling.OPTIONAL)

    AppiumDriver driver = MobileDriverFactory.getDriver()

    Actualtoastmessage = driver.findElementByXPath('//android.widget.Toast[1]').getText()

    println(Actualtoastmessage)

    Mobile.verifyMatch(Actualtoastmessage, findTestData('VBL_Mobile Input Data/VanLoad').getValue('Alerts', 1), false)

    Mobile.takeScreenshot()

    println('Verified : that User is able to accept the van load')

    Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/LoadManagement/SubMenu_Current Stock view'), 0)

    Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Reusable Object/Product Search Icon'), 0)

    WebUI.callTestCase(findTestCase('Product_Mobile/Common/Search_Product'), [:], FailureHandling.STOP_ON_FAILURE)

    Mobile.setText(findTestObject('XXXXXXXXXXXX/Mobile Part/Reusable Object/Product Enter Search'), findTestData('VBL_Web Input Data/Stock allocation').getValue('SKU_Name', 1), 0)

    SIH_value = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/LoadManagement/CurrentStockView/SIH_Value'), 0)

    println('vanload _SIH_value  : ' + case_piece_Qty)

    println('after accept_vanload_SIH_value : ' + SIH_value)

    Mobile.verifyMatch(SIH_value, case_piece_Qty, false)

    Mobile.takeScreenshot()

    println('Verified : that Current stock view is displaying')
}

Mobile.tap(findTestObject('Mobile/Common/Icon_Back'), 2, FailureHandling.OPTIONAL)

Mobile.tap(findTestObject('XXXXXXXXXXXX/Re-usable/Mobile Login/Menu'), 2, FailureHandling.OPTIONAL)

