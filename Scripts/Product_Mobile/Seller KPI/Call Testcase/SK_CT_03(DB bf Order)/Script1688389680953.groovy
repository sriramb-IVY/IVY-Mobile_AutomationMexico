import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import java.awt.Robot as Robot
import java.awt.event.KeyEvent as KeyEvent
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import org.apache.commons.lang.RandomStringUtils as RandomStringUtils
import com.kms.katalon.core.mobile.keyword.internal.MobileDriverFactory as MobileDriverFactory
import io.appium.java_client.AppiumDriver as AppiumDriver
import java.sql.Connection as Connection
import java.sql.DriverManager as DriverManager
import java.sql.ResultSet as ResultSet
import java.sql.Statement as Statement
import javax.management.Descriptor as Descriptor
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import java.text.SimpleDateFormat as SimpleDateFormat

import java.time.LocalDate;
import java.time.YearMonth;

import com.kms.katalon.core.util.KeywordUtil as KeywordUtil


String pattern = 'yyyy-MM-dd'

SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern)

String currentdate = simpleDateFormat.format(new Date())

KeywordUtil.logInfo(currentdate)

GlobalVariable.sDate = currentdate

'Get the current year and month'

// Get the current year and month
YearMonth yearMonth = YearMonth.now()

LocalDate firstDate = yearMonth.atDay(1)

KeywordUtil.logInfo(firstDate.toString())

LocalDate lastDate = yearMonth.atEndOfMonth()

KeywordUtil.logInfo(lastDate.toString())

DB_Currentdate = (firstDate.toString() + ' 00:00:00.000')

KeywordUtil.logInfo(DB_Currentdate)

Connection connection = null

url = findTestData('DB Credentials/Config1').getValue('URL', 1)

dbname = findTestData('DB Credentials/Config1').getValue('dbname', 1)

username = findTestData('DB Credentials/Config1').getValue('username', 1)

password = findTestData('DB Credentials/Config1').getValue('password', 1)


queryString = (((('select * from Report_Seller_KPI where RSK_From_Date = \'' + DB_Currentdate) + '\'  and Rsk_User_Id = \'') + 
GlobalVariable.vanseller_user_id) + '\'   ')

KeywordUtil.logInfo(queryString)

String conn = (((((('jdbc:sqlserver://' + url) + ';databaseName=') + dbname) + ';user=') + username) + ';password=') + password

connection = DriverManager.getConnection(conn)

Statement stm = connection.createStatement()

ResultSet rs = stm.executeQuery(queryString)

def recordSet = rs

while (recordSet.next()) {
    Object RSK_id = recordSet.getObject('RSK_Id')

    GlobalVariable.RSK_Id = RSK_id

    println('RSK_id : ' + GlobalVariable.RSK_Id)
}

SV_Value = findTestData('Web Input Data/Seller KPI').getValue('Options', 2)

'Alov_id for Sales value'
//Alov_id for Sales value

queryString1 = (((('select * from adm_lovs where ALOV_Name = \'' + SV_Value) + '\'  and ALOV_Parent_Id = \'') + GlobalVariable.MS_Id) + 
'\'   ')

ResultSet rs1 = stm.executeQuery(queryString1)

def recordSet1 = rs1

while (recordSet1.next()) {
    Object Lov_id = recordSet1.getObject('ALOV_Id')

	GlobalVariable.SV_id = Lov_id
	
	println(GlobalVariable.SV_id)
}


LPC_Value = findTestData('Web Input Data/Seller KPI').getValue('Options', 6)

//Alov_id for Lines percall
queryString5 = (((('select * from adm_lovs where ALOV_Name = \'' + LPC_Value) + '\'  and ALOV_Parent_Id = \'') + GlobalVariable.MS_Id) +
'\'   ')

ResultSet rs5 = stm.executeQuery(queryString5)

def recordSet5 = rs5

while (recordSet5.next()) {
	Object Lpc_Lov_id = recordSet5.getObject('ALOV_Id')

	GlobalVariable.LPC_id = Lpc_Lov_id
	
	println(GlobalVariable.LPC_id)
}



//2nd DB Sales Value in Seller KPI Details
queryString2 = (((('select * from Report_Seller_KPI_Detail where RSKD_Target_Parameter_Lov_Id = \'' + GlobalVariable.SV_id) + '\'  and RSKD_RSK_Id = \'') + 
GlobalVariable.RSK_Id) + '\'   ')

ResultSet rs2 = stm.executeQuery(queryString2)

def recordSet2 = rs2

while (recordSet2.next()) {
    Object Target = recordSet2.getObject('RSKD_Target')

    println(Target)

    Object Acheivement = recordSet2.getObject('RSKD_Achievement')
	
	GlobalVariable.B_SV_Acheivement = Acheivement

    println(Acheivement)
}

//3nd DB Lines per call  in Seller KPI Details
queryString3 = (((('select * from Report_Seller_KPI_Detail where RSKD_Target_Parameter_Lov_Id = \'' + GlobalVariable.LPC_id) + '\'  and RSKD_RSK_Id = \'') + 
GlobalVariable.RSK_Id) + '\'   ')

ResultSet rs3 = stm.executeQuery(queryString3)

def recordSet3 = rs3

while (recordSet3.next()) {
    Object Target = recordSet3.getObject('RSKD_Target')

    println(Target)

    Object Acheivement = recordSet3.getObject('RSKD_Achievement')
	
	GlobalVariable.B_LPC_Acheivement = Acheivement

    println(Acheivement)
}

