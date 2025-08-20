package credit_management

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows

import java.sql.Connection
import java.sql.DriverManager
import java.sql.ResultSet
import java.sql.Statement

import com.kms.katalon.core.util.KeywordUtil

import internal.GlobalVariable
import java.text.DateFormat as DateFormat
import java.util.Date as Date

import java.text.SimpleDateFormat as SimpleDateFormat



public class Period_Date_Change_1 {


	@Keyword
	public static String CP_date_change(def retailer_Code){

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

		String pattern = 'yyyy-MM-dd'

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern)

		String currentdate = simpleDateFormat.format(new Date())

		println(currentdate)

		String Previous_Date = currentdate

		DateFormat dateFormat = new SimpleDateFormat('yyyy-MM-dd')

		Date date = new Date()

		String P_Date = dateFormat.format(date - 2)

		println(P_Date)

		GlobalVariable.sDate = P_Date

		String queryString1 = (((('update AppData_Pending_Bills set PB_SIH_Date =\'' + GlobalVariable.sDate) + '\'  where PB_ARTR_ID =\'') + GlobalVariable.Retailer_Id) + '\'')

		KeywordUtil.logInfo('query=' + queryString1)

		connection = DriverManager.getConnection(conn)

		Statement stm = connection.createStatement()

		boolean rs = stm.execute(queryString1)

		WebUI.delay(5)
	}
}
