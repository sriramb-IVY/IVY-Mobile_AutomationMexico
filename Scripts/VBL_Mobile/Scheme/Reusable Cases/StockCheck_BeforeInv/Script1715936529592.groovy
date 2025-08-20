import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import java.sql.Connection
import java.sql.DriverManager
import java.sql.ResultSet
import java.sql.Statement
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.keyword.excel.ExcelKeywords
import internal.GlobalVariable as GlobalVariable
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil


String sheet_name = 'Scheme_Stock_Validation'

String file_name = 'Mobile Input data'

ArrayList<String> SKU_Name = CustomKeywords.'poi.VBL.Automation.GetAllDataRow_Flag.getAllDataRow'(file_name, sheet_name, 'SKU_Name')

for (int i = 0; i < SKU_Name.size(); i++) {

	List<String> a = new ArrayList<String>()

	Connection connection = null

	url = findTestData('VBL_DB Credentials/Config1').getValue('URL', 1)

	dbname = findTestData('VBL_DB Credentials/Config1').getValue('dbname', 1)

	username = findTestData('VBL_DB Credentials/Config1').getValue('username', 1)

	password = findTestData('VBL_DB Credentials/Config1').getValue('password', 1)

	String queryString1 = (('select * from adm_product_hierarchy where aph_name =\'' + SKU_Name.get(i))+ '\'')


	KeywordUtil.logInfo(queryString1)

	String conn1 = (((((('jdbc:sqlserver://' + url) + ';databaseName=') + dbname) + ';user=') + username) + ';password=') + password

	if ((connection != null) && !(connection.isClosed())) {
		connection.close()
	}

	connection = DriverManager.getConnection(conn1)

	Statement stm1 = connection.createStatement()

	ResultSet rs1 = stm1.executeQuery(queryString1)

	def recordSet1 = rs1

	while (recordSet1.next()) {
		String data01 = recordSet1.getObject('APH_Id')

		KeywordUtil.logInfo(data01)

		GlobalVariable.aph_id = data01

		String pn1 = recordSet1.getObject('APH_Name')

		KeywordUtil.logInfo(pn1)

	}

	String queryString2 = (('select * from appdata_stock_summary where ASS_APH_Id  =\'' + 	GlobalVariable.aph_id)+ '\' and ASS_ASL_Id = 302 and ASS_ADH_Id =72')
	
	println(queryString2)
	
	String conn2 = (((((('jdbc:sqlserver://' + url) + ';databaseName=') + dbname) + ';user=') + username) + ';password=') + password
	
	connection = DriverManager.getConnection(conn2)
	
	Statement stm2 = connection.createStatement()
	
	ResultSet rs2 = stm1.executeQuery(queryString2)
	
	def recordSet2 = rs2
	
	double stock_buy = 0
	
	while (recordSet2.next()) {
		
		String Quantity2 = recordSet2.getObject('ASS_Qty')
		
		KeywordUtil.logInfo(Quantity2)
				
		stock_buy = Double.parseDouble(Quantity2)
		
		KeywordUtil.logInfo('Stock before invoice' + stock_buy)
		
		GlobalVariable.Qty = stock_buy
		
	}
	
	String exlpath = 'DDF\\VBL\\Mobile Input data\\Mobile Input data.xlsx'

	String Sheetname = 'Scheme_Stock_Validation'

	workbook01 = ExcelKeywords.getWorkbook(exlpath)

	sheet1 = ExcelKeywords.getExcelSheet(workbook01, Sheetname)

	ExcelKeywords.setValueToCellByIndex(sheet1, i + 1, 2, GlobalVariable.aph_id)

	ExcelKeywords.setValueToCellByIndex(sheet1, i + 1, 4, GlobalVariable.Qty)

	ExcelKeywords.saveWorkbook(exlpath, workbook01)
}
