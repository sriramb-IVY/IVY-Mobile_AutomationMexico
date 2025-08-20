package credit_management

import static com.kms.katalon.core.testdata.TestDataFactory.findTestData

import java.sql.Connection
import java.sql.DriverManager
import java.sql.ResultSet
import java.sql.Statement

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.util.KeywordUtil

import internal.GlobalVariable
import java.text.DateFormat as DateFormat
import java.util.Date as Date

import java.text.SimpleDateFormat as SimpleDateFormat


public class Limit_period_bill_increase_1 {

	@Keyword
	public  String Credit_Limit(def retailer_Code){

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

		String queryString1 = ('update ADM_Retailer_Mapping set ARM_Credit_Limit = 3000 where ARM_ARTR_Id = \'' + GlobalVariable.Retailer_Id +'\'')

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
	public static String increase_CP(def retailer_Code){

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

		String queryString1 = ('update ADM_Retailer_Mapping set ARM_Credit_Period = 4 where ARM_ARTR_Id = \'' + GlobalVariable.Retailer_Id +'\'')

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
	public static String increase_Creditbill(def retailer_Code){

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

		String queryString1 = ('update ADM_Retailer_Mapping set ARM_Credit_Bills_Count = 2 where ARM_ARTR_Id = \'' + GlobalVariable.Retailer_Id +'\'')

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
	public static String decrease_limits(def retailer_Code){

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

		String queryString1 = ('update ADM_Retailer_Mapping set ARM_Credit_Limit = 1000.000000 where ARM_ARTR_Id = \'' + GlobalVariable.Retailer_Id +'\'')

		String queryString2 = ('update ADM_Retailer_Mapping set ARM_Credit_Period = 1 where ARM_ARTR_Id = \'' + GlobalVariable.Retailer_Id +'\'')

		String queryString3 = ('update ADM_Retailer_Mapping set ARM_Credit_Bills_Count = 1 where ARM_ARTR_Id = \'' + GlobalVariable.Retailer_Id +'\'')

		KeywordUtil.logInfo(queryString1)

		KeywordUtil.logInfo(queryString2)

		KeywordUtil.logInfo(queryString3)

		String conn1 = (((((('jdbc:sqlserver://' + url) + ';databaseName=') + dbname) + ';user=') + username) + ';password=') + password

		if ((connection != null) && !(connection.isClosed())) {

			connection.close()
		}

		connection = DriverManager.getConnection(conn1)
		Statement stm = connection.createStatement()
		boolean rs = stm.execute(queryString1)
		boolean rs2 = stm.execute(queryString2)
		boolean rs3 = stm.execute(queryString3)
	}


	@Keyword
	public static String increase_warncredit_limit(def retailer_Code){

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

		String queryString1 = ('update ADM_Retailer_Mapping set ARM_Warn_Credit_Limit = 1500 where ARM_ARTR_Id = \'' + GlobalVariable.Retailer_Id +'\'')

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
	public static String inc_warncredit_limit(def retailer_Code){

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

		String queryString1 = ('update ADM_Retailer_Mapping set ARM_Warn_Credit_Limit = 1500.000000 where ARM_ARTR_Id = \'' + GlobalVariable.Retailer_Id +'\'')

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
	public static String warn_Creditbill(def retailer_Code){

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

		String queryString1 = ('update ADM_Retailer_Mapping set ARM_Warn_Credit_Bills = 4 where ARM_ARTR_Id = \'' + GlobalVariable.Retailer_Id +'\'')

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
	public static String increase_Warn_CP(def retailer_Code){

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

		String queryString1 = ('update ADM_Retailer_Mapping set ARM_Warn_Credit_Period = 4 where ARM_ARTR_Id = \'' + GlobalVariable.Retailer_Id +'\'')

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
	public static String decrease_warn_lpb(def retailer_Code){

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

		String queryString1 = ('update ADM_Retailer_Mapping set ARM_Warn_Credit_Limit = 500.000000 where ARM_ARTR_Id = \'' + GlobalVariable.Retailer_Id +'\'')

		String queryString2 = ('update ADM_Retailer_Mapping set ARM_Warn_Credit_Period = 1 where ARM_ARTR_Id = \'' + GlobalVariable.Retailer_Id +'\'')

		String queryString3 = ('update ADM_Retailer_Mapping set ARM_Warn_Credit_Bills = 3 where ARM_ARTR_Id = \'' + GlobalVariable.Retailer_Id +'\'')

		KeywordUtil.logInfo(queryString1)

		KeywordUtil.logInfo(queryString2)

		KeywordUtil.logInfo(queryString3)

		String conn1 = (((((('jdbc:sqlserver://' + url) + ';databaseName=') + dbname) + ';user=') + username) + ';password=') + password

		if ((connection != null) && !(connection.isClosed())) {

			connection.close()
		}

		connection = DriverManager.getConnection(conn1)
		Statement stm = connection.createStatement()
		boolean rs = stm.execute(queryString1)
		boolean rs2 = stm.execute(queryString2)
		boolean rs3 = stm.execute(queryString3)
	}


	@Keyword
	public static String Decrease_Period_Limit(def retailer_Code) {

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

		String queryString1 = ('update ADM_Retailer_Mapping set ARM_Credit_Period = 2 where ARM_ARTR_Id = \'' + GlobalVariable.Retailer_Id +'\'')

		println(queryString1)

		String conn1 = (((((('jdbc:sqlserver://' + url) + ';databaseName=') + dbname) + ';user=') + username) + ';password=') + password

		if ((connection != null) && !(connection.isClosed())) {
			connection.close()
		}
		connection = DriverManager.getConnection(conn1)

		Statement stm = connection.createStatement()

		boolean rs = stm.execute(queryString1)
	}
}