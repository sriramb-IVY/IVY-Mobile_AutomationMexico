<?xml version="1.0" encoding="UTF-8"?>
<WebServiceRequestEntity>
   <description></description>
   <name>Get Token</name>
   <tag></tag>
   <elementGuidId>00b15f69-a8d7-416e-8730-0d4e87d9abec</elementGuidId>
   <selectorMethod>BASIC</selectorMethod>
   <smartLocatorEnabled>false</smartLocatorEnabled>
   <useRalativeImagePath>false</useRalativeImagePath>
   <autoUpdateContent>false</autoUpdateContent>
   <connectionTimeout>0</connectionTimeout>
   <followRedirects>true</followRedirects>
   <httpBody></httpBody>
   <httpBodyContent>{
  &quot;text&quot;: &quot;{\n    \&quot;email\&quot;: \&quot;savithri.p@ivymobility.com\&quot;,\n    \&quot;password\&quot;: \&quot;Qwerty@123\&quot;\n}&quot;,
  &quot;contentType&quot;: &quot;application/json&quot;,
  &quot;charset&quot;: &quot;UTF-8&quot;
}</httpBodyContent>
   <httpBodyType>text</httpBodyType>
   <httpHeaderProperties>
      <isSelected>true</isSelected>
      <matchCondition>equals</matchCondition>
      <name>Content-Type</name>
      <type>Main</type>
      <value>application/json</value>
      <webElementGuid>818627ef-77ba-42cf-993b-ba86989bd4ae</webElementGuid>
   </httpHeaderProperties>
   <katalonVersion>10.1.1</katalonVersion>
   <maxResponseSize>0</maxResponseSize>
   <migratedVersion>5.4.1</migratedVersion>
   <path></path>
   <restRequestMethod>POST</restRequestMethod>
   <restUrl>https://api.katalon.com/v1/auth/login</restUrl>
   <serviceType>RESTful</serviceType>
   <soapBody></soapBody>
   <soapHeader></soapHeader>
   <soapRequestMethod></soapRequestMethod>
   <soapServiceEndpoint></soapServiceEndpoint>
   <soapServiceFunction></soapServiceFunction>
   <socketTimeout>0</socketTimeout>
   <useServiceInfoFromWsdl>true</useServiceInfoFromWsdl>
   <verificationScript>import static org.assertj.core.api.Assertions.*

import com.kms.katalon.core.testobject.RequestObject
import com.kms.katalon.core.testobject.ResponseObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webservice.verification.WSResponseManager

import groovy.json.JsonSlurper
import internal.GlobalVariable as GlobalVariable

RequestObject request = WSResponseManager.getInstance().getCurrentRequest()

ResponseObject response = WSResponseManager.getInstance().getCurrentResponse()
WS.verifyElementPropertyValue(response, 'data.jwt', &quot;eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJpZTlDMnN4ZjZ4czczSHJxY0QyejRxcVVzNEFpOWVzNXR5SVVKYmRTYW04In0.eyJleHAiOjE3NDM5MTc0NDcsImlhdCI6MTc0Mzc0NDY0NywianRpIjoiNTYzOThhZjktM2M5OS00ZGVlLTg3ZDMtNTRlYmEyNzNiMWY1IiwiaXNzIjoiaHR0cHM6Ly9sb2dpbi5rYXRhbG9uLmNvbS9yZWFsbXMva2F0YWxvbiIsImF1ZCI6ImFjY291bnQiLCJzdWIiOiJjMGYyNmMwNC01ZGRlLTRlMjUtYjFiMC02YzczM2I5OWY5ZWQiLCJ0eXAiOiJCZWFyZXIiLCJhenAiOiJrYXRhbG9uLWFkbWluIiwic2lkIjoiOWYwYjY5OGMtNGFmYy00MTJlLWEyNjMtNTUwMzdkZDViNzg2IiwiYWNyIjoiMSIsImFsbG93ZWQtb3JpZ2lucyI6WyJodHRwczovL215LmthdGFsb24uY29tIiwiaHR0cHM6Ly9hZG1pbi5rYXRhbG9uLmNvbSJdLCJyZWFsbV9hY2Nlc3MiOnsicm9sZXMiOlsidW1hX2F1dGhvcml6YXRpb24iLCJkZWZhdWx0LXJvbGVzLWthdGFsb24iXX0sInJlc291cmNlX2FjY2VzcyI6eyJhY2NvdW50Ijp7InJvbGVzIjpbIm1hbmFnZS1hY2NvdW50IiwibWFuYWdlLWFjY291bnQtbGlua3MiLCJ2aWV3LXByb2ZpbGUiXX19LCJzY29wZSI6Im9wZW5pZCBwcm9maWxlIGVtYWlsIiwiZW1haWxfdmVyaWZpZWQiOnRydWUsInVwZGF0ZWRfdGltZXN0YW1wIjoxNjk2ODI0NTk0NTM0LCJsYXN0X2xvZ2luIjoxNzQzNzQ0NDI4NTcyLCJuYW1lIjoiTW9oaXQgS3VtYXIiLCJjcmVhdGVkX3RpbWVzdGFtcCI6MTYyMDgwNTQ2MjI0NiwicHJlZmVycmVkX3VzZXJuYW1lIjoibW9oaXQua3VtYXJAa2F0YWxvbi5jb20iLCJnaXZlbl9uYW1lIjoiTW9oaXQiLCJmYW1pbHlfbmFtZSI6Ikt1bWFyIiwiZW1haWwiOiJtb2hpdC5rdW1hckBrYXRhbG9uLmNvbSJ9.SRyVe1Go7HlPWqarpNsVnF377PhAxpULTfcsvgDg5U-3uQG9P8B-gSCFEXlm95mRF1ndQsqfr-MA4X-5_nSD4caEYeTB6IGr8R8aI3GAjlX66K8GSMBMyR5gz-6UrYFpFv8mOwsPN_Ug76t7Qx_HjMUIP7ZdDwi-IrNQcNDiZr3vDgdsnW52TvaKGShKLGdN0C4PAnLMwxRRnB5772JxF7niZSObCBp4BdhB-24gcBHaFqo5LUUqX1sb-j_rbU2z5YOFJj08kaeWn5hTPTmNK7k87neWCSZsyhZFpolDbI_zSNEe8iMbhO_EQgydX8mszpynNsk_ddOTMqMcjJ61iQ&quot;)</verificationScript>
   <wsdlAddress></wsdlAddress>
</WebServiceRequestEntity>
