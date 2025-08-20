<?xml version="1.0" encoding="UTF-8"?>
<WebServiceRequestEntity>
   <description></description>
   <name>Sales Invoice API</name>
   <tag></tag>
   <elementGuidId>08137ba2-1626-41af-8277-1911af929d09</elementGuidId>
   <selectorMethod>BASIC</selectorMethod>
   <useRalativeImagePath>false</useRalativeImagePath>
   <connectionTimeout>-1</connectionTimeout>
   <followRedirects>false</followRedirects>
   <httpBody></httpBody>
   <httpBodyContent>{
  &quot;text&quot;: &quot;{\n\&quot;DistCode\&quot;:\&quot;DIST_9\&quot;,\n\&quot;RetCode\&quot;:\&quot;RTR_HVN_SubD_129\&quot;,\n\&quot;UserCode\&quot;:\&quot;\&quot;,\n\&quot;RefNo\&quot;:\&quot;223344\&quot;,\n\&quot;RefDate\&quot;:\&quot;2023-05-18\&quot;,\n\&quot;Type\&quot;:\&quot;CASH\&quot;,\n\&quot;Gross\&quot;:1,\n\&quot;Net\&quot;:1,\n\&quot;Source\&quot;:\&quot;\&quot;, \n\&quot;Details\&quot;:\n      [{\n        \&quot;ProductCode\&quot;:\&quot;SKU035\&quot;,\n        \&quot;Qty\&quot;:100,\n        \&quot;Uom\&quot;:\&quot;CASE\&quot;,\n        \&quot;Price\&quot;:600,\n        \&quot;Gross\&quot;:500,\n        \&quot;Net\&quot;:550\n     }\n,\n\n {\n        \&quot;ProductCode\&quot;:\&quot;SKU036\&quot;,\n        \&quot;Qty\&quot;:200,\n        \&quot;Uom\&quot;:\&quot;CASE\&quot;,\n        \&quot;Price\&quot;:600,\n        \&quot;Gross\&quot;:500,\n        \&quot;Net\&quot;:550\n      }]\n,\n\&quot;Free\&quot;:\n    [\n]\n}\n&quot;,
  &quot;contentType&quot;: &quot;application/json&quot;,
  &quot;charset&quot;: &quot;UTF-8&quot;
}</httpBodyContent>
   <httpBodyType>text</httpBodyType>
   <httpHeaderProperties>
      <isSelected>false</isSelected>
      <matchCondition>equals</matchCondition>
      <name>Content-Type</name>
      <type>Main</type>
      <value>application/json</value>
      <webElementGuid>e8eb6123-f878-408c-84d9-58d75bd47c4c</webElementGuid>
   </httpHeaderProperties>
   <httpHeaderProperties>
      <isSelected>false</isSelected>
      <matchCondition>equals</matchCondition>
      <name>Authentication</name>
      <type>Main</type>
      <value>eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJqdGkiOiI4Y2FmZDA5Zi05ZDc2LTRjZTQtYWQxOS01MTRiOWVlYjcxOGIiLCJpYXQiOjE2ODQ0MDAzMzUsImF1ZCI6Ikl2eUNQRyIsImlzcyI6Imh0dHA6Ly8xOTIuMTY4LjIuMTczIiwicHJvamVjdF9pZCI6Ijc1ZGE4MzI3LWU5ZjYtNDdkOS05NjliLTc1YWZkYmY1NjE1YSIsImdyYW50X3R5cGUiOiJhY2Nlc3NfdG9rZW4iLCJjb21wb25lbnRfdHlwZSI6WyIxIiwiMSIsIjEiXSwidXNlcl9pZCI6IjEiLCJuYmYiOjE2ODQ0MDAzMzUsImV4cCI6MTY4NDQxNDczNX0.ZVCp5kOPWnUF2471AEWudaJCYP8ClsinwBuFBsEG4Ew</value>
      <webElementGuid>f24f08fb-54a1-409d-aadb-2113f93aeb73</webElementGuid>
   </httpHeaderProperties>
   <httpHeaderProperties>
      <isSelected>false</isSelected>
      <matchCondition>equals</matchCondition>
      <name>Auth_Secret</name>
      <type>Main</type>
      <value>Y2F0Y2hlciUyMHdvbmclMjBsb3ZlJTIwLm5ldA==</value>
      <webElementGuid>42050b1b-0a5b-494d-90e4-c22a0fed1a6b</webElementGuid>
   </httpHeaderProperties>
   <katalonVersion>8.4.1</katalonVersion>
   <maxResponseSize>-1</maxResponseSize>
   <migratedVersion>5.4.1</migratedVersion>
   <restRequestMethod>POST</restRequestMethod>
   <restUrl>${URL}/InvoiceOrder/Single</restUrl>
   <serviceType>RESTful</serviceType>
   <soapBody></soapBody>
   <soapHeader></soapHeader>
   <soapRequestMethod></soapRequestMethod>
   <soapServiceEndpoint></soapServiceEndpoint>
   <soapServiceFunction></soapServiceFunction>
   <socketTimeout>-1</socketTimeout>
   <useServiceInfoFromWsdl>true</useServiceInfoFromWsdl>
   <variables>
      <defaultValue>GlobalVariable.URL</defaultValue>
      <description></description>
      <id>2611c174-729e-4644-af51-1f631915c5a5</id>
      <masked>false</masked>
      <name>URL</name>
   </variables>
   <verificationScript>import static org.assertj.core.api.Assertions.*

import com.kms.katalon.core.testobject.RequestObject
import com.kms.katalon.core.testobject.ResponseObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webservice.verification.WSResponseManager

import groovy.json.JsonSlurper
import internal.GlobalVariable as GlobalVariable

RequestObject request = WSResponseManager.getInstance().getCurrentRequest()

ResponseObject response = WSResponseManager.getInstance().getCurrentResponse()</verificationScript>
   <wsdlAddress></wsdlAddress>
</WebServiceRequestEntity>
