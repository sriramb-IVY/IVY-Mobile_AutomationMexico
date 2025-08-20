import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import java.sql.Connection as Connection
import java.sql.DriverManager as DriverManager
import java.sql.ResultSet as ResultSet
import java.sql.Statement as Statement
import java.text.SimpleDateFormat as SimpleDateFormat
import com.google.common.collect.FilteredEntryMultimap.Keys as Keys
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import org.junit.Assert as Assert

String pattern = 'yyyy-MM-dd'

SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern)

String currentdate = simpleDateFormat.format(new Date())

println(currentdate)

GlobalVariable.sDate = currentdate

Connection connection = null

url = findTestData('DB Credentials/Config1').getValue('URL', 1)

dbname = findTestData('DB Credentials/Config1').getValue('dbname', 1)

username = findTestData('DB Credentials/Config1').getValue('username', 1)

password = findTestData('DB Credentials/Config1').getValue('password', 1)

//GlobalVariable.label = 1589
queryString = (((('select * from AppData_Visibility_Photo_Capture where VPH_User_Id=\'' + GlobalVariable.vanseller_user_id) + 
'\'  and VPH_Visit_Date =\'') + GlobalVariable.sDate) + '\' order by 1 desc')

String conn = (((((('jdbc:sqlserver://' + url) + ';databaseName=') + dbname) + ';user=') + username) + ';password=') + password

connection = DriverManager.getConnection(conn)

Statement stm = connection.createStatement()

ResultSet rs = stm.executeQuery(queryString)

def recordSet = rs

if (recordSet.next()) {
    Object Feedback = recordSet.getObject('VPH_Feedback')

    KeywordUtil.logInfo(Feedback)

    Comment = findTestData('Mobile Input Data/PhotoCapture').getValue('Comments', 1)

    Mobile.verifyMatch(Feedback, Comment, false)

    Object Lov_id = recordSet.getObject('VPH_Type_LOV_Id')

    KeywordUtil.logInfo('lov ' + Lov_id)

    GlobalVariable.label = Lov_id

    Object Aph_id = recordSet.getObject('VPH_APH_Id')

    KeywordUtil.logInfo('aph =' + Aph_id)

    GlobalVariable.label1 = Aph_id
}
 else {
	'If data is empty in the DB, then the flow must fail'
	Assert.fail()
}
queryString2 = (('select * from adm_product_hierarchy where aph_id=\'' + GlobalVariable.label1) + '\'')

println(queryString2)

ResultSet rs2 = stm.executeQuery(queryString2)

def recordSet2 = rs2

while (recordSet2.next()) {
    Object Prod_Name = recordSet2.getObject('APH_Name')

    KeywordUtil.logInfo(Prod_Name)
	


    Mobile.verifyMatch(Prod_Name ,findTestData('Mobile Input Data/PhotoCapture').getValue('Product_Type', 1), false)
}

//3rd DB
queryString3 = (('select * from adm_lovs where alov_id= \'' + GlobalVariable.label) + '\'')

KeywordUtil.logInfo(queryString3)

ResultSet rs3 = stm.executeQuery(queryString3)

def recordSet3 = rs3

while (recordSet3.next()) {
    Object Product_Type = recordSet3.getObject('ALOV_Name')

    KeywordUtil.logInfo(Product_Type)

    Mobile.verifyMatch(Product_Type, findTestData('Mobile Input Data/PhotoCapture').getValue('Photo_Type', 1), false)
}

