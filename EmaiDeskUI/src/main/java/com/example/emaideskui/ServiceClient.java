package com.example.emaideskui;

import com.example.emaideskui.wsdl.SenderRequest;
import com.example.emaideskui.wsdl.SenderResponse;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.ws.client.core.WebServiceTemplate;

import javax.swing.*;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class ServiceClient {

    private String toAddress;
    private String subject;
    private String body;

    public void SetParameter(String toAddress, String subject, String body) {
        this.toAddress = toAddress;
        this.subject = subject;
        this.body = body;
    }

    public void RestRequest() throws IOException {
        toAddress = URLEncoder.encode(toAddress,"utf-8");
        subject = URLEncoder.encode(subject,"utf-8");
        body = URLEncoder.encode(body,"utf-8");
        String httpurl= "http://localhost:8080/rest/email/send?toAddress="+toAddress+"&subject="+subject+"&body="+body;;
        //创建HTTP连接对象
        URL obj = new URL(httpurl);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        //设置请求方法
        con.setRequestMethod("POST");
        //读取服务端返回的数据
        int responseCode = con.getResponseCode();

        System.out.println(responseCode);
    }

    public void SoapRequest() {
        WebServiceTemplate webServiceTemplate = new WebServiceTemplate();
        //新建转换
        Jaxb2Marshaller jaxb2Marshaller = new Jaxb2Marshaller();
        jaxb2Marshaller.setClassesToBeBound(SenderRequest.class, SenderResponse.class);
        //设置转换
        webServiceTemplate.setMarshaller(jaxb2Marshaller);
        webServiceTemplate.setUnmarshaller(jaxb2Marshaller);
        //构造 SOAP 请求
        SenderRequest request = new SenderRequest();

        request.setUrl(toAddress);
        request.setTitle(subject);
        request.setPayload(body);
        //收到回复
        SenderResponse response = (SenderResponse) webServiceTemplate.marshalSendAndReceive(
                "http://localhost:8080/ws/email", request);
        //输出回复结果
        System.out.println(response.getStatue());
    }

    public void URLRequest() throws IOException {
        toAddress = URLEncoder.encode(toAddress,"utf-8");

        String httpurl= "http://localhost:8080/rest/email/validate?emailAddress="+toAddress;
        //创建HTTP连接对象
        URL obj = new URL(httpurl);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        //设置请求方法
        con.setRequestMethod("POST");
        //读取服务端返回的数据
        int responseCode = con.getResponseCode();
        if(responseCode==200){
            JOptionPane.showMessageDialog(null, "邮箱格式正确！", "成功", JOptionPane.INFORMATION_MESSAGE);
        }
        System.out.println(responseCode);
    }

    public void SetAddress(String Address) {
        this.toAddress = Address;
    }
}
