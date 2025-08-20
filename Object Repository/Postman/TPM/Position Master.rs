<?xml version="1.0" encoding="UTF-8"?>
<WebServiceRequestEntity>
   <description></description>
   <name>Position Master</name>
   <tag></tag>
   <elementGuidId>0b66bbec-5a51-42fc-a134-f5d4432d1e5b</elementGuidId>
   <selectorMethod>BASIC</selectorMethod>
   <useRalativeImagePath>false</useRalativeImagePath>
   <connectionTimeout>-1</connectionTimeout>
   <followRedirects>false</followRedirects>
   <httpBody></httpBody>
   <httpBodyContent>{
  &quot;text&quot;: &quot;{\r\n   \&quot;Code\&quot; : \&quot;MD03\&quot;,\r\n   \&quot;Name\&quot; :  \&quot;MD 03\&quot;,\r\n   \&quot;LevelCode\&quot; : \&quot;MD\&quot;,\r\n   \&quot;LocationCode\&quot; : \&quot;11110\&quot;,\r\n   \&quot;ParentCode\&quot; :   \&quot;\&quot;,\r\n   \&quot;Status\&quot; : \&quot;1\&quot;\r\n }&quot;,
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
      <webElementGuid>c7ddd34e-1dd4-48fc-be46-c0b2cd8889ab</webElementGuid>
   </httpHeaderProperties>
   <httpHeaderProperties>
      <isSelected>false</isSelected>
      <matchCondition>equals</matchCondition>
      <name>Authentication</name>
      <type>Main</type>
      <value></value>
      <webElementGuid>fd7ba425-0a78-4ae0-9d98-e291acdce09c</webElementGuid>
   </httpHeaderProperties>
   <katalonVersion>8.4.1</katalonVersion>
   <maxResponseSize>-1</maxResponseSize>
   <migratedVersion>5.4.1</migratedVersion>
   <restRequestMethod>POST</restRequestMethod>
   <restUrl>${URL}/PositionSave/Single</restUrl>
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
      <id>6db96756-27ba-4c55-97e6-67880e9c5faa</id>
      <masked>false</masked>
      <name>URL</name>
   </variables>
   <wsdlAddress></wsdlAddress>
</WebServiceRequestEntity>
