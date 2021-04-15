package com.immortal.market2hand.util;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;
import java.util.Properties;

public class MailUtils {

    public static void sendMail(String email)
            throws AddressException, MessagingException {
        // 1.创建一个程序与邮件服务器会话对象 Session

        Properties props = new Properties();
        props.setProperty("mail.transport.protocol", "SMTP");
        props.setProperty("mail.host", "smtp.163.com");
        props.setProperty("mail.smtp.auth", "true");// 指定验证为true

        // 创建验证器
        Authenticator auth = new Authenticator() {
            @Override
            public PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("immortal90@163.com", "KPBRGDJRZVCWHMVG");
            }
        };

        Session session = Session.getInstance(props, auth);

        // 2.创建一个Message，它相当于是邮件内容
        Message message = new MimeMessage(session);

        message.setFrom(new InternetAddress("immortal90@163.com")); // 设置发送者

        message.setRecipient(RecipientType.TO, new InternetAddress(email)); // 设置发送方式与接收者

        message.setSubject("二手交易商城找回密码");
        String url = "http://127.0.0.1:8989/home/index/updatePassword";
        String comment = "【请点此链接重置您的密码】";

        message.setContent("<h1>来自二手交易商城的密码修改邮件，请点击以下链接以更新您的密码！</h1>" +
                "<h3><a href='" + url + "'>" + comment + "</a>", "text/html;charset=utf-8");

        // 3.创建 Transport用于将邮件发送

        Transport.send(message);
    }
}
