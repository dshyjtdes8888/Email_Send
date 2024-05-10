package com.example.emaideskui;
import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class EmailClient extends JFrame {

    // 定义组件
    //构件
    static JPanel jp1, jp2, jp3,jp4,jp5;
    //标签
    static JLabel url_label, mutiurl_label, title_label, payload_label;
    //发送按钮
    static JButton restsend, soapsend, testurl;
    //输入框
    static JTextField toAddress_input, subject_input;
    //创建文本域
    static JTextArea body_text;

    static void init() {

        //新建构件
        jp1 = new JPanel();
        jp2 = new JPanel();
        jp3 = new JPanel();
        jp4 = new JPanel();
        jp5 = new JPanel();

        //标签
        url_label = new JLabel("收件人邮箱");
        mutiurl_label = new JLabel("多个收件人请用“，“分隔邮箱");
        title_label = new JLabel("邮件标题");
        payload_label = new JLabel("邮件内容");

        //按钮
        restsend = new JButton("调用Rest发送");
        soapsend = new JButton("调用Soap发送");
        testurl = new JButton("校验邮箱");

        //输入框
        toAddress_input = new JTextField(15);
        toAddress_input.setText("2223572292@qq.com");
        subject_input = new JTextField(15);
        subject_input.setText("输入邮件标题");

        //文本域
        body_text = new JTextArea(15,40);
        body_text.setText("输入邮件内容内容");
        body_text.setLineWrap(true);

        //放邮箱输入
        jp1.add(url_label);
        jp1.add(toAddress_input);
        jp1.add(mutiurl_label);
        //放标题输入
        jp2.add(title_label);
        jp2.add(subject_input);
        //放入文本框
        jp3.add(payload_label);
        jp4.add(body_text);
        //放入按钮
        jp5.add(restsend);
        jp5.add(soapsend);
        jp5.add(testurl);

        restsend.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String url = toAddress_input.getText();
                String title = subject_input.getText();
                String payload = body_text.getText();
                try {
                    ServiceClient s = new ServiceClient();
                    s.SetParameter(url,title,payload);
                    s.RestRequest();
                    JOptionPane.showMessageDialog(null, "邮件发送成功！", "成功", JOptionPane.INFORMATION_MESSAGE);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        soapsend.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String url = toAddress_input.getText();
                String title = subject_input.getText();
                String payload = body_text.getText();
                try {

                    ServiceClient s = new ServiceClient();
                    s.SetParameter(url,title,payload);
                    s.SoapRequest();
                    JOptionPane.showMessageDialog(null, "邮件发送成功！", "成功", JOptionPane.INFORMATION_MESSAGE);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        testurl.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String Address = toAddress_input.getText();

                try {
                    ServiceClient s = new ServiceClient();
                    s.SetAddress(Address);
                    s.URLRequest();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

    }


    public EmailClient() {
        //布局策略
        this.setLayout(new GridLayout(5, 1));
        init();
        this.add(jp1);
        this.add(jp2);
        //this.add(payload_text);
        this.add(jp3);
        this.add(jp4);
        this.add(jp5);
        this.setSize(300, 250);
        this.setTitle("登录");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        EmailClient s = new EmailClient();


            // 设置窗口的大小（宽度和高度）
            int width = 600;
            int height = 400;
            s.setSize(width, height);

            // 设置窗口关闭时的默认操作
            s.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            // 可选：将窗口定位在屏幕中央
            s.setLocationRelativeTo(null);

            // 使窗口可见
            s.setVisible(true);
        }

}
