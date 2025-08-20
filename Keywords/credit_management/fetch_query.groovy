package credit_management

import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import java.sql.Connection as Connection
import java.sql.DriverManager as DriverManager
import java.sql.ResultSet as ResultSet
import java.sql.Statement as Statement
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import com.kms.katalon.core.annotation.Keyword
import java.text.DateFormat
import java.text.SimpleDateFormat

public class  fetch_query {

	@Keyword
	public static List<String> query(def retailer_code) {
		List<String> a = new ArrayList<String>()

		Connection connection = null

		String url = findTestData('DB Credentials/Config1').getValue('URL', 1)

		String dbname = findTestData('DB Credentials/Config1').getValue('dbname', 1)

		String username = findTestData('DB Credentials/Config1').getValue('username', 1)

		String password = findTestData('DB Credentials/Config1').getValue('password', 1)

		String queryString = (('select * from adm_retailer where ARTR_Code = \'' +retailer_code)+ '\'')

		println(queryString)

		String conn = (((((('jdbc:sqlserver://' + url) + ';databaseName=') + dbname) + ';user=') + username) + ';password=') + password

		if ((connection != null) && !(connection.isClosed())) {
			connection.close()
		}
		connection = DriverManager.getConnection(conn)

		Statement stm1 = connection.createStatement()

		ResultSet rs1 = stm1.executeQuery(queryString)

		def recordSet1 = rs1

		while (recordSet1.next()) {
			String retailer_id = recordSet1.getObject('ARTR_Id')

			KeywordUtil.logInfo( GlobalVariable.Retailer_Id = retailer_id)
		}

		String queryString1 = (('select * from AppData_Pending_Bills where pb_artr_id=\'' +  GlobalVariable.Retailer_Id) +'\'  order by 1 desc ')

		println(queryString1)

		String conn1 = (((((('jdbc:sqlserver://' + url) + ';databaseName=') + dbname) + ';user=') + username) + ';password=') + password

		if ((connection != null) && !(connection.isClosed())) {
			connection.close()
		}
		connection = DriverManager.getConnection(conn1)

		Statement stm = connection.createStatement()

		ResultSet rs = stm.executeQuery(queryString1)

		def recordSet = rs

		while (recordSet.next()) {
			String invoice = recordSet.getObject('PB_SIH_No')

			a.add(invoice)

			KeywordUtil.logInfo(invoice)

			KeywordUtil.logInfo(GlobalVariable.Pending_bills_invoice = invoice)

			String pending_id = recordSet.getObject('PB_Id')

			KeywordUtil.logInfo(GlobalVariable.Pending_bill_id = pending_id)
		}

		println(a)

		println(a.size())

		//Db_invoice  = a.size()

		GlobalVariable.Db_Invoicesize = a

		return(a)
	}
	@Keyword
	public static query1 (def retailer_code ) {

		List<String> b = fetch_query.query(retailer_code)

		if(b.size()> 0) {
			WebUI.callTestCase(findTestCase('Test Cases/Product/Reusables/Login/Branch Login'), [:], FailureHandling.STOP_ON_FAILURE)

			for(int i = 0 ;i<b.size();i++) {

				String invoice1 = b.get(i)

				println(GlobalVariable.Pending_bills_invoice = invoice1)

				WebUI.callTestCase(findTestCase('Product/Reusables/Navigation/Navigate to Collection Direct'), [:], FailureHandling.STOP_ON_FAILURE)

				WebUI.waitForElementNotVisible(findTestObject('Object Repository/Reusables/Page Load'), 50)

				WebUI.click(findTestObject('Object Repository/Reusables/Menubar_hide_button'))

				WebUI.click(findTestObject('Object Repository/Collections/Collection/invoice_radio_button'))

				WebUI.click(findTestObject('Object Repository/Collections/Collection/Click/Click_SalesPerson'))

				WebUI.waitForElementVisible(findTestObject('Object Repository/Collections/collection acceptance new/Sales Person_Search'), 30, FailureHandling.STOP_ON_FAILURE)

				GlobalVariable.salesman_name = findTestData('Data Files/Automation/Global_Data/Credit_Mgnt').getValue('SalesPerson', 1)

				WebUI.sendKeys(findTestObject('Object Repository/Collections/collection acceptance new/Sales Person_Search'), GlobalVariable.salesman_name,
						FailureHandling.STOP_ON_FAILURE)

				WebUI.click(findTestObject('Object Repository/Collections/collection acceptance new/salesperson_select'))

				WebUI.waitForElementNotVisible(findTestObject('Object Repository/Reusables/Page Load'), 50)

				WebUI.click(findTestObject('Object Repository/Collections/Collection/invoice_no'))

				WebUI.sendKeys(findTestObject('Object Repository/Collections/Collection/invoice_no'), GlobalVariable.Pending_bills_invoice)

				GlobalVariable.Button = findTestData('Data Files/Automation/Global_Data/Credit_Mgnt').getValue('Button', 1)

				WebUI.scrollToElement(findTestObject('Object Repository/Collections/collection acceptance new/button'), 10)

				WebUI.click(findTestObject('Object Repository/Collections/collection acceptance new/button'))

				WebUI.waitForElementNotVisible(findTestObject('Object Repository/Reusables/Page Load'), 50)

				String balanceAmount = WebUI.getText(findTestObject('Object Repository/Collections/Collection/balance_amount'))

				KeywordUtil.logInfo(balanceAmount)

				WebUI.click(findTestObject('Object Repository/Collections/Collection/Amount_collected_link'))

				WebUI.waitForElementNotVisible(findTestObject('Object Repository/Reusables/Page Load'), 50)

				WebUI.waitForElementVisible(findTestObject('Object Repository/Collections/Collection/Payment/cash'), 50)

				WebUI.click(findTestObject('Object Repository/Collections/Collection/Payment/cash'))

				WebUI.waitForElementVisible(findTestObject('Object Repository/Collections/Collection/Payment/cash'), 50)

				String collectionAmount = balanceAmount.replace('$', '').replace(',', '').trim()

				println(collectionAmount)

				WebUI.sendKeys(findTestObject('Object Repository/Collections/Collection/Payment/By_cash'), collectionAmount)

				WebUI.click(findTestObject('Object Repository/Collections/Collection/payment_save_button'))

				GlobalVariable.Alert_message = findTestData('Data Files/Automation/Global_Data/Credit_Mgnt').getValue('Alert', 1)

				WebUI.click(findTestObject('Object Repository/Collections/collection acceptance new/alert_msg_close'), FailureHandling.OPTIONAL)

				WebUI.switchToDefaultContent()

				WebUI.click(findTestObject('Object Repository/Collections/Collection/payment_close_button'))

				GlobalVariable.Button = findTestData('Data Files/Automation/Global_Data/Credit_Mgnt').getValue('Button', 2)

				WebUI.waitForElementVisible(findTestObject('Object Repository/Collections/collection acceptance new/button'), 50)

				WebUI.click(findTestObject('Object Repository/Collections/collection acceptance new/button'))

				WebUI.click(findTestObject('Object Repository/Collections/Collection/confirm_ok_button'))

				WebUI.waitForElementNotVisible(findTestObject('Object Repository/Reusables/Page Load'), 50)

				GlobalVariable.Alert_message = findTestData('Data Files/Automation/Global_Data/Credit_Mgnt').getValue('Alert', 3)

				if(WebUI.verifyElementVisible(findTestObject('Object Repository/Collections/collection acceptance new/alert_msg'), FailureHandling.OPTIONAL)) {
					String alertMessage = WebUI.getText(findTestObject('Object Repository/Collections/collection acceptance new/alert_msg'))

					KeywordUtil.logInfo(alertMessage)

					WebUI.verifyMatch(alertMessage, GlobalVariable.Alert_message, false)

					WebUI.takeScreenshot()
				}
				else {

					GlobalVariable.Button = findTestData('Data Files/Automation/Global_Data/Credit_Mgnt').getValue('Button', 1)

					WebUI.scrollToElement(findTestObject('Object Repository/Collections/collection acceptance new/button'), 10)

					WebUI.click(findTestObject('Object Repository/Collections/collection acceptance new/button'))

					WebUI.waitForElementVisible(findTestObject('Object Repository/Collections/collection acceptance new/alert_msg'), 20)

					String alertMessage = WebUI.getText(findTestObject('Object Repository/Collections/collection acceptance new/alert_msg'))

					WebUI.delay(3)

					KeywordUtil.logInfo(alertMessage)

					WebUI.verifyMatch(alertMessage, GlobalVariable.Alert_message, false)

					WebUI.takeScreenshot()
				}
				WebUI.refresh()
			}
		}
	}

	@Keyword
	public static void delete_invoice(def retailer_code) {

		Connection connection = null

		String url = findTestData('DB Credentials/Config1').getValue('URL', 1)

		String dbname = findTestData('DB Credentials/Config1').getValue('dbname', 1)

		String username = findTestData('DB Credentials/Config1').getValue('username', 1)

		String password = findTestData('DB Credentials/Config1').getValue('password', 1)

		String queryString = (('select * from adm_retailer where ARTR_Code = \'' +retailer_code)+ '\'')

		KeywordUtil.logInfo(queryString)

		String conn = (((((('jdbc:sqlserver://' + url) + ';databaseName=') + dbname) + ';user=') + username) + ';password=') + password

		if ((connection != null) && !(connection.isClosed())) {
			connection.close()
		}
		connection = DriverManager.getConnection(conn)

		Statement stm1 = connection.createStatement()

		ResultSet rs1 = stm1.executeQuery(queryString)

		def recordSet1 = rs1

		while (recordSet1.next()) {
			String retailer_id = recordSet1.getObject('ARTR_Id')

			KeywordUtil.logInfo( GlobalVariable.Retailer_Id = retailer_id)
		}

		String queryString1 = (('delete from AppData_Pending_Bills where pb_artr_id= \'' + GlobalVariable.Retailer_Id) + '\'')

		KeywordUtil.logInfo(queryString1)

		String conn1 = (((((('jdbc:sqlserver://' + url) + ';databaseName=') + dbname) + ';user=') + username) + ';password=') + password

		if ((connection != null) && !(connection.isClosed())) {
			connection.close()
		}
		connection = DriverManager.getConnection(conn1)

		Statement stm = connection.createStatement()

		boolean rs0 = stm.execute(queryString1)
	}

	@Keyword
	public static String increase_credit_billcount(def retailer_Code){

		Connection connection = null

		String url = findTestData('DB Credentials/Config1').getValue('URL', 1)

		String dbname = findTestData('DB Credentials/Config1').getValue('dbname', 1)

		String username = findTestData('DB Credentials/Config1').getValue('username', 1)

		String password = findTestData('DB Credentials/Config1').getValue('password', 1)

		String queryString = (('select * from adm_retailer where ARTR_Code = \'' + retailer_Code)+ '\'')

		println(queryString)

		String conn = (((((('jdbc:sqlserver://' + url) + ';databaseName=') + dbname) + ';user=') + username) + ';password=') + password

		if ((connection != null) && !(connection.isClosed())) {
			connection.close()
		}
		connection = DriverManager.getConnection(conn)

		Statement stm1 = connection.createStatement()

		ResultSet rs1 = stm1.executeQuery(queryString)

		def recordSet1 = rs1

		while (recordSet1.next()) {
			String retailer_id = recordSet1.getObject('ARTR_Id')

			KeywordUtil.logInfo(GlobalVariable.Retailer_Id = retailer_id)
		}

		String queryString1 = ('update ADM_Retailer_Mapping set ARM_Warn_Credit_Bills = 1 where ARM_ARTR_Id = \'' + GlobalVariable.Retailer_Id +'\'')

		println(queryString1)

		String conn1 = (((((('jdbc:sqlserver://' + url) + ';databaseName=') + dbname) + ';user=') + username) + ';password=') + password

		if ((connection != null) && !(connection.isClosed())) {
			connection.close()
		}
		connection = DriverManager.getConnection(conn1)

		Statement stm = connection.createStatement()

		boolean rs = stm.execute(queryString1)
	}

	@Keyword
	public static String  LIMITS_overdue(def retailer_Code,def limit,def overdue){

		Connection connection = null

		String url = findTestData('DB Credentials/Config1').getValue('URL', 1)

		String dbname = findTestData('DB Credentials/Config1').getValue('dbname', 1)

		String username = findTestData('DB Credentials/Config1').getValue('username', 1)

		String password = findTestData('DB Credentials/Config1').getValue('password', 1)

		String queryString = (('select * from adm_retailer where ARTR_Code = \'' + retailer_Code)+ '\'')

		KeywordUtil.logInfo(queryString)

		String conn = (((((('jdbc:sqlserver://' + url) + ';databaseName=') + dbname) + ';user=') + username) + ';password=') + password

		if ((connection != null) && !(connection.isClosed())) {
			connection.close()
		}
		connection = DriverManager.getConnection(conn)

		Statement stm1 = connection.createStatement()

		ResultSet rs1 = stm1.executeQuery(queryString)

		def recordSet1 = rs1

		while (recordSet1.next()) {
			String retailer_id = recordSet1.getObject('ARTR_Id')

			KeywordUtil.logInfo(GlobalVariable.Retailer_Id = retailer_id)
		}

		GlobalVariable.limit =limit

		GlobalVariable.overdue = overdue

		String queryString1 = ('update ADM_Retailer_Over_Due set AROD_Credit_Balance = \'' +GlobalVariable.limit +'\' where AROD_ARTR_Id = \'' + GlobalVariable.Retailer_Id +'\'')

		String queryString2 = ('update ADM_Retailer_Over_Due set AROD_Over_Due = \'' +GlobalVariable.overdue +'\' where AROD_ARTR_Id = \'' + GlobalVariable.Retailer_Id +'\'')

		KeywordUtil.logInfo(queryString1)

		KeywordUtil.logInfo(queryString2)

		String conn1 = (((((('jdbc:sqlserver://' + url) + ';databaseName=') + dbname) + ';user=') + username) + ';password=') + password

		if ((connection != null) && !(connection.isClosed())) {
			connection.close()
		}
		connection = DriverManager.getConnection(conn1)

		Statement stm = connection.createStatement()

		boolean rs = stm.execute(queryString1)

		boolean rs2 = stm.execute(queryString2)
	}
}