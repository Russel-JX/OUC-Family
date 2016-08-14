package com.igate.izone.test;

import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
 
public class SendMailDemo {   
   public  void send163ByMutil() throws MessagingException {   
       JavaMailSenderImpl  javaMail  =  new  JavaMailSenderImpl();   
       javaMail.setHost("smtp.163.com");   
       javaMail.setPassword("izone123");   
       javaMail.setUsername("izonehelper@163.com");   
       Properties  prop  =  new  Properties();   
       prop.setProperty("mail.smtp.auth", "true");        
       javaMail.setJavaMailProperties(prop);   
       MimeMessage  message  =  javaMail .createMimeMessage();   
       MimeMessageHelper  messageHelp  =  new  MimeMessageHelper(message,true,"GBK");   
       messageHelp.setFrom("iZoneHelper@163.com");   
       messageHelp.setTo("qi.ye@igate.com");   
       messageHelp.setSubject("邮件测试");   
       // messageHelp   
       String  body  =  "iZone客服团队 邮件测试" ;   
       messageHelp.setText(body, true);   
       javaMail.send(message);   
       System.out.println("发送成功");
   }   
   public static void main(String[] args) throws MessagingException {         
       SendMailDemo  send = new  SendMailDemo();   
       send.send163ByMutil();   
   }   
}    