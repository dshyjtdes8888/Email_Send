package com.example.emailservice;

import com.aliyun.auth.credentials.Credential;
import com.aliyun.auth.credentials.provider.StaticCredentialProvider;
import com.aliyun.sdk.service.dm20151123.AsyncClient;
import com.aliyun.sdk.service.dm20151123.models.SingleSendMailRequest;
import com.aliyun.sdk.service.dm20151123.models.SingleSendMailResponse;
import com.google.gson.Gson;
import darabonba.core.client.ClientOverrideConfiguration;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
@Component
public class EmailService {
    //发送单一邮件
    public boolean sendEmail(String subject, String toAddress, String body) {
        StaticCredentialProvider provider = StaticCredentialProvider.create(Credential.builder()
                .accessKeyId("")
                .accessKeySecret("")
                .build());

        AsyncClient client = AsyncClient.builder()
                .region("cn-hangzhou") // Region ID
                .credentialsProvider(provider)
                .overrideConfiguration(
                        ClientOverrideConfiguration.create()
                                .setEndpointOverride("dm.aliyuncs.com")
                )
                .build();

        SingleSendMailRequest singleSendMailRequest = SingleSendMailRequest.builder()
                .accountName("ly_yuan@ly.ylaz.xyz")
                .addressType(1)
                .toAddress(toAddress)
                .subject(subject)
                .htmlBody(body)
                .textBody("")
                .replyToAddress(false)
                .build();

        CompletableFuture<SingleSendMailResponse> response = client.singleSendMail(singleSendMailRequest);

        try {
            SingleSendMailResponse resp = response.get();
            System.out.println(new Gson().toJson(resp));
            return true; // 检查状态码是否为成功
        } catch (Exception e) {
            e.printStackTrace();
            return false; // 发送邮件时发生异常，视为发送失败
        } finally {
            client.close();
        }
    }


    //批量发送文件
    public boolean sendEmailBatch(String subject, String toAddress, String body) {
        StaticCredentialProvider provider = StaticCredentialProvider.create(Credential.builder()
                .accessKeyId("")
                .accessKeySecret("")
                .build());

        AsyncClient client = AsyncClient.builder()
                .region("cn-hangzhou") // Region ID
                .credentialsProvider(provider)
                .overrideConfiguration(
                        ClientOverrideConfiguration.create()
                                .setEndpointOverride("dm.aliyuncs.com")
                )
                .build();

        SingleSendMailRequest singleSendMailRequest = SingleSendMailRequest.builder()
                .accountName("ly_yuan@ly.ylaz.xyz")
                .addressType(1)
                .toAddress(toAddress)
                .subject(subject)
                .htmlBody(body)
                .textBody("")
                .replyToAddress(false)
                .build();

        CompletableFuture<SingleSendMailResponse> response = client.singleSendMail(singleSendMailRequest);

        try {
            SingleSendMailResponse resp = response.get();
            System.out.println(new Gson().toJson(resp));
            return true; // 检查状态码是否为成功
        } catch (Exception e) {
            e.printStackTrace();
            return false; // 发送邮件时发生异常，视为发送失败
        } finally {
            client.close();
        }
    }


    //验证邮箱
    public boolean validateEmailAddress(String str) {
        String pat="^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern p=Pattern.compile(pat);
        Matcher m=p.matcher(str);
        if(m.matches()){
            return true;
        }else{
            return false;
        }
    }
}
