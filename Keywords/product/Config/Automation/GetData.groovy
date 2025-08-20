package product.Config.Automation

import static com.kms.katalon.core.testdata.TestDataFactory.findTestData

import com.kms.katalon.core.annotation.Keyword

public class GetData {
	@Keyword
	public String getData(String column_name){
		String screen_name=findTestData('Product Config Automation/Master/Master').getValue('Sheet Name', 1)
		String configuration=findTestData('Product Config Automation/Master/Master').getValue('Configuration', 1)
		String path = 'Product Config Automation/Modules/' +screen_name+'/'+configuration
		String filepath = 'DDF//Product Config Automation//' + screen_name + '//' + screen_name + '.xlsx'
		int i=1,column_no,index
		int collumn=findTestData(path).getColumnNumbers(),rowsize=findTestData(path).getRowNumbers()
		List<Integer> rowlist = new ArrayList<Integer>()
		List<String> testData = new ArrayList<Integer>()

		for(i=1;i<=collumn;i++) {
			if(findTestData(path).getValue(i, 1).equalsIgnoreCase("Data to be used")) {
				column_no=i
			}
		}
		for(int row=1;row<=rowsize;row++) {
			if((findTestData(path).getValue(column_no, row).equalsIgnoreCase("Y"))) {
				rowlist.add(row)
				//println rowlist
			}
		}
		for(index=0;index<rowlist.size();index++) {

			testData.add(findTestData(path).getValue(column_name,rowlist.get(index)))
		}
		return testData
	}
}
