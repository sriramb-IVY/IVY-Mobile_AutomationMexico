package com.katalon.testcloud

import java.time.Duration

import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeOptions
import org.openqa.selenium.remote.HttpCommandExecutor
import org.openqa.selenium.remote.RemoteWebDriver
import org.openqa.selenium.remote.http.ClientConfig
import org.openqa.selenium.remote.http.Filter
import org.openqa.selenium.remote.http.HttpHandler

import com.kms.katalon.core.annotation.Keyword



public class TestCloudDriverUtility {
	
	@Keyword
	public static WebDriver openTestCloudBrowser(String token, String orgId, String chromeverison) {
		String url = "https://testcloud.katalon.com/hub"
		HashMap<String, Object> katalonOptions = new HashMap<String, Object>()
		HashMap<String, Object> metadata = new HashMap<String, Object>()
		metadata.put("organizationId", orgId)
		katalonOptions.put("metadata", metadata)
		katalonOptions.put("usingTunnel", true)
		ChromeOptions options = new ChromeOptions()
		options.setBrowserVersion(chromeverison)
		options.setPlatformName("Windows")
		options.setCapability("katalon:options", katalonOptions)
		ClientConfig clientConfig = ClientConfig.defaultConfig()
				.baseUrl(new URL(url))
				.connectionTimeout(Duration.ofMinutes(30))
				.readTimeout(Duration.ofMinutes(30))
				.withFilter(new Filter() {
					@Override
					HttpHandler apply(HttpHandler next) {
						return { req ->
							String encodedToken = Base64.getEncoder().encodeToString(String.format("%s:%s", "token", token).getBytes("UTF-8"))
							req.addHeader("Authorization", "Basic " + encodedToken)
							return next.execute(req)
						}
					}
				})
		WebDriver driver = new RemoteWebDriver(new HttpCommandExecutor(clientConfig), options)
	}
}