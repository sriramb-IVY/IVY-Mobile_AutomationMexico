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

String pattern = 'yyyy-MM-dd'

SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern)

String currentdate = simpleDateFormat.format(new Date())

println(currentdate)

GlobalVariable.sDate = currentdate

String user_id = '533'

// Get the current year and month
YearMonth yearMonth = YearMonth.now();
LocalDate firstDate = yearMonth.atDay(1);
println(firstDate.toString())
LocalDate lastDate = yearMonth.atEndOfMonth();
println(lastDate.toString())

DB_Currentdate = (firstDate.toString() + ' 00:00:00.000')
println(DB_Currentdate)

GlobalVariable.First_Date = (firstDate.toString() + ' 00:00:00.000')
println(GlobalVariable.First_Date)

GlobalVariable.Last_Date = (lastDate.toString() + ' 00:00:00.000')

Connection connection = null

url = findTestData('DB Credentials/Config1').getValue('URL', 1)

dbname = findTestData('DB Credentials/Config1').getValue('dbname', 1)

username = findTestData('DB Credentials/Config1').getValue('username', 1)

password = findTestData('DB Credentials/Config1').getValue('password', 1)

GlobalVariable.SR_No = findTestData('Web Input Data/Sales_Order_Invoice_View').getValue('OrderNumber', 8)

queryString = (('select * from appdata_order_header  where oh_order_id=\'' + GlobalVariable.SR_No) + '\'')

String conn = (((((('jdbc:sqlserver://' + url) + ';databaseName=') + dbname) + ';user=') + username) + ';password=') + password

connection = DriverManager.getConnection(conn)

Statement stm = connection.createStatement()

ResultSet rs = stm.executeQuery(queryString)

def recordSet = rs

while (recordSet.next()) {
    Object oh_Value = recordSet.getObject('OH_Value')

    GlobalVariable.label = oh_Value

    println('oh_Value : ' + GlobalVariable.label)
	
	Object OH_AV_Id = recordSet.getObject('OH_AV_Id')

		GlobalVariable.AV_id = OH_AV_Id
	
		println('AV_id : ' + GlobalVariable.AV_id)
	
	
}

queryString5 = (((((((('select * from AppData_Order_Header where OH_User_Id =' + user_id) + 'and OH_AV_Id=\'') + GlobalVariable.AV_id) + 
'\'  and OH_Date between \'') + GlobalVariable.First_Date) + '\' and \'') + GlobalVariable.Last_Date) + '\'   ')

println(queryString5)

ResultSet rs5 = stm.executeQuery(queryString5)

def recordSet5 = rs5

List<Double> Values = new ArrayList<String>()

while (recordSet5.next()) {
    Object Order_value_in_db = recordSet5.getObject('OH_Value')

    double Order_value_in_db1 = Order_value_in_db

    println(Order_value_in_db1)

    Values.add(Order_value_in_db1)
}

println(Values)

double sum = 0

for (int i = 0; i < Values.size(); i++) {
    sum += Values.get(i)

    println('sum:' + sum)

    GlobalVariable.Sum = sum
}

queryString6 = (((((((('select * from AppData_Order_Header where OH_User_Id =' + user_id) + 'and OH_AV_Id=\'') + GlobalVariable.AV_id) +
	'\'  and OH_Date between \'') + GlobalVariable.First_Date) + '\' and \'') + GlobalVariable.Last_Date) + '\'   ')
	
	println(queryString6)
	
	ResultSet rs6 = stm.executeQuery(queryString6)
	
	def recordSet6 = rs6
	
	List<Double> Values_1 = new ArrayList<String>()
	
	while (recordSet6.next()) {
		Object Order_value_in_db = recordSet6.getObject('OH_Lines_Per_Call')
	
		double Order_value_in_db1 = Order_value_in_db
	
		println(Order_value_in_db1)
	
		Values_1.add(Order_value_in_db1)
	}
	
	println(Values_1)
	
	double sum_1 = 0
	
	for (int j = 0; j < Values_1.size(); j++) {
		sum_1 += Values_1.get(j)
	
		println('sum1:' + sum_1)
	
		GlobalVariable.Sum1 = sum_1
	}

WebUI.callTestCase(findTestCase('Product_Mobile/Common/JOB RUN/Seller_KPI_Job Run'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.delay(5)

//2nd DB
queryString1 = (((('select * from Report_Seller_KPI where RSK_From_Date = \'' + DB_Currentdate) + '\'  and Rsk_User_Id = \'') + 
GlobalVariable.Pre_user_id) + '\'   ')

ResultSet rs1 = stm.executeQuery(queryString1)

def recordSet1 = rs1

while (recordSet1.next()) {
    Object RSK_id = recordSet1.getObject('RSK_Id')

    GlobalVariable.RSK_Id = RSK_id

    println('RSK_id : ' + GlobalVariable.RSK_Id)
}

//GlobalVariable.SV_id = '6607'

//GlobalVariable.LPC_id = '6597'

//3nd DB Sales Value
queryString2 = (((('select * from Report_Seller_KPI_Detail where RSKD_Target_Parameter_Lov_Id = \'' + GlobalVariable.SV_id) + 
'\'  and RSKD_RSK_Id = \'') + GlobalVariable.RSK_Id) + '\'   ')

ResultSet rs2 = stm.executeQuery(queryString2)

def recordSet2 = rs2

while (recordSet2.next()) {
    Object Target = recordSet2.getObject('RSKD_Target')

    println(Target)

    Object After_SV_Acheivement = recordSet2.getObject('RSKD_Achievement')

    println(After_SV_Acheivement)
	
	Mobile.verifyEqual(GlobalVariable.Sum, After_SV_Acheivement)
	
	Mobile.takeScreenshot()
}

//4th DB Sales Value
queryString3 = (((('select * from Report_Seller_KPI_Detail where RSKD_Target_Parameter_Lov_Id = \'' + GlobalVariable.LPC_id) + 
'\'  and RSKD_RSK_Id = \'') + GlobalVariable.RSK_Id) + '\'   ')

ResultSet rs3 = stm.executeQuery(queryString3)

def recordSet3 = rs3

while (recordSet3.next()) {
    Object Target = recordSet3.getObject('RSKD_Target')

    println(Target)

    Object After_LPC_Acheivement = recordSet3.getObject('RSKD_Achievement')

    println(After_LPC_Acheivement)
	
	Mobile.verifyEqual(GlobalVariable.Sum1, After_LPC_Acheivement, FailureHandling.OPTIONAL)
	
	Mobile.takeScreenshot()

   
}

