package cn.author.alidns;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.alidns.model.v20150109.DescribeDomainRecordsRequest;
import com.aliyuncs.alidns.model.v20150109.DescribeDomainRecordsResponse;
import com.aliyuncs.alidns.model.v20150109.DescribeDomainRecordsResponse.Record;
import com.aliyuncs.alidns.model.v20150109.DescribeDomainsRequest;
import com.aliyuncs.alidns.model.v20150109.DescribeDomainsResponse;
import com.aliyuncs.alidns.model.v20150109.DescribeDomainsResponse.Domain;
import com.aliyuncs.alidns.model.v20150109.UpdateDomainRecordRequest;
import com.aliyuncs.alidns.model.v20150109.UpdateDomainRecordResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;

public class AliDnsTest {

    private static IAcsClient client = null;
    static {
        String regionId = "cn-hangzhou"; //必填固定值，必须为“cn-hanghou”
        String accessKeyId = ""; // your accessKey
        String accessKeySecret = "";// your accessSecret
        IClientProfile profile = DefaultProfile.getProfile(regionId, accessKeyId, accessKeySecret);
        // 若报Can not find endpoint to access异常，请添加以下此行代码
        // DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", "Alidns", "alidns.aliyuncs.com");  
        client = new DefaultAcsClient(profile);
    }
    public static void main(String[] args) {
//    	demoVoid();
//    	getDescribeDomainRecords();
    	updateDomainRecordRequest();
    }
    
    private static void updateDomainRecordRequest(){
    	UpdateDomainRecordRequest request = new UpdateDomainRecordRequest();
    	UpdateDomainRecordResponse response;
//    	updateDomainRecordRequest.setActionName(actionName);
    	request.setRecordId("3891869701211136");
    	request.setRR("a");
    	request.setType("A");
    	request.setValue("12.12.12.12");
    	
    	try {
            response = client.getAcsResponse(request);
            System.out.println(JSONObject.toJSONString(response));
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
    }
    
    private static void demoVoid(){
    	 DescribeDomainsRequest request = new DescribeDomainsRequest();
         DescribeDomainsResponse response;
         // describeRegionsRequest.setProtocol(ProtocolType.HTTPS); //指定访问协议
         // describeRegionsRequest.setAcceptFormat(FormatType.JSON); //指定api返回格式
         // describeRegionsRequest.setMethod(MethodType.POST); //指定请求方法
         // describeRegionsRequest.setRegionId("cn-hangzhou");//指定要访问的Region,仅对当前请求生效，不改变client的默认设置。
         try {
             response = client.getAcsResponse(request);
             String requestId = response.getRequestId();
             List<Domain> list = response.getDomains();
             for (Domain domain : list) {
                 System.out.println(domain.getDomainName());
             }
         } catch (ServerException e) {
             e.printStackTrace();
         } catch (ClientException e) {
             e.printStackTrace();
         }
    }
    
    private static void getDescribeDomainRecords(){
    	DescribeDomainRecordsRequest request = new DescribeDomainRecordsRequest();
    	DescribeDomainRecordsResponse response;
    	request.setDomainName("authordocument.cn");
    	 try {
             response = client.getAcsResponse(request);
             String requestId = response.getRequestId();
             List<Record> list = response.getDomainRecords();
             for (Record record : list) {
            	System.out.println(JSONObject.toJSONString(record)); 
			}
         } catch (ServerException e) {
             e.printStackTrace();
         } catch (ClientException e) {
             e.printStackTrace();
         }
    }

}
