

import org.junit.runner.RunWith
import cucumber.api.CucumberOptions
import cucumber.api.junit.Cucumber

@RunWith(Cucumber.class)
@CucumberOptions(features="Include/features/CPG/Mitha",glue="",tags="",plugin=["pretty","html:Reports/Cucumber/cucumberreport.html"],monochrome = true)
//@CucumberOptions(features="Include/features/CPG/Mitha",glue="",tags="@Surveylogin", plugin=["html:Reports/Cucumber/cucumberreport.html"],monochrome = true)
class Runner {
}