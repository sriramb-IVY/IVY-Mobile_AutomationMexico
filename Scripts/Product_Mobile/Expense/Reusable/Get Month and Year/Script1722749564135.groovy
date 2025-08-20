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
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.temporal.TemporalAdjusters


// Get the current date
LocalDate currentDate = LocalDate.now()

// Get the first day of the previous month
LocalDate previousMonth = currentDate.minusMonths(1).withDayOfMonth(1)

// Format the previous month in MMM format
DateTimeFormatter monthFormatter = DateTimeFormatter.ofPattern("MMM")

String currentMonthFormatted = currentDate.format(monthFormatter)

GlobalVariable.sMonth = currentMonthFormatted

String previousMonthFormatted = previousMonth.format(monthFormatter)

GlobalVariable.pastMonth = previousMonthFormatted	

// Print the previous month
System.out.println("Previous month: " + previousMonthFormatted)

// Get the first day of the previous year
LocalDate previousYear = currentDate.minusYears(1).withDayOfYear(1)

// Format the previous year in YYY format
DateTimeFormatter yearFormatter = DateTimeFormatter.ofPattern("YYYY")

String currentYearFormatted = currentDate.format(yearFormatter)

GlobalVariable.sYear = currentYearFormatted

String previousYearFormatted = previousYear.format(yearFormatter)

GlobalVariable.pastYear = previousYearFormatted

// Print the previous year
System.out.println("Previous year: " + previousYearFormatted)

// Get the first day of the next month
LocalDate nextMonth = currentDate.plusMonths(1).withDayOfMonth(1)

// Format the next month in MMM format

String nextMonthFormatted = nextMonth.format(monthFormatter)

GlobalVariable.futureMonth = nextMonthFormatted

// Print the next month
System.out.println("Future month: " + nextMonthFormatted)

// Get the first day of the next year
LocalDate nextYear = currentDate.plusYears(1).withDayOfYear(1)

// Format the next year in YYYY format
String nextYearFormatted = nextYear.format(yearFormatter)

GlobalVariable.futureYear = nextYearFormatted

// Print the next year
System.out.println("Future year: " + nextYearFormatted)

