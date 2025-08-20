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

import java.text.DecimalFormat;


import java.math.BigDecimal;
import java.math.RoundingMode;

import java.math.BigDecimal;
import java.math.RoundingMode;

String input=': 2034/12/02'

String[] part=input.split(':')

exp_date=part[1].trim()

println(exp_date)


		String numberStr = "4.1666666666";
		String[] parts = numberStr.split("\\.");
		
		println(parts[0])
		
		println(parts[1])
		
		String integerPart = parts[0];
		String decimalPart = parts[1].substring(0, 2);
		String resultStr = integerPart + "." + decimalPart;

		double result = Double.parseDouble(resultStr);
		System.out.println(result);  // Output: 4.16

		
	
		
		
				BigDecimal number = new BigDecimal("4.1666666666");
				BigDecimal truncatedNumber = number.setScale(4, RoundingMode.FLOOR);  // Truncate without rounding
		
				System.out.println(truncatedNumber);  // Output: 4.16
		




 

