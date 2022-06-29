package com.aaa.backend.Services;

import java.io.File;
import java.util.concurrent.TimeUnit;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import com.aaa.backend.Models.AirQuote;
import com.aaa.backend.Models.ScaffoldQuote;
import com.aaa.backend.Models.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class EmailSenderService {
    
    @Autowired
    private JavaMailSender mailSender;

    @Async
    public void waitToSend(Supplier supplier) throws MessagingException, InterruptedException{
        
        File file = new File("C:\\Users\\SalaCAD\\Documents\\AAA\\PDFs\\"+supplier.getId()+"/test.pdf");

        System.out.print("\nRegistrando");
        while (!file.exists() || !(file.length() > 0)){
            System.out.print(".");
            TimeUnit.SECONDS.sleep(3);
        }
        String mailBody;

        mailBody = "<!DOCTYPE html><html><head>" +
        "<style>" +
        "h1   {color: blue;}" +
        "</style>" +
        "</head>" +
        "<body>" +
        "<h1>"+ supplier.getName() +"</h1>" +
        "<div>New supplier data:</div>" +
        "<div><strong>Direccion: </strong>" + supplier.getDireccion() + "</div>" +
        "<div><strong>Telefono: </strong>" + supplier.getTelefono() + "</div>" +
        "</body>" +
        "</html>";

        sendEmail("kajad19119@dilanfa.com", "New supplier", mailBody, file.getAbsolutePath());
    }

    @Async
    public void waitToSend(ScaffoldQuote quote) throws MessagingException, InterruptedException{
        
        File file = new File("C:\\Users\\SalaCAD\\Documents\\AAA\\PDFs\\"+quote.getId()+"/test.pdf");

        System.out.print("\nRegistrando");
        while (!file.exists() || !(file.length() > 0)){
            System.out.print(".");
            TimeUnit.SECONDS.sleep(3);
        }
        String mailBody;

        mailBody = "<!DOCTYPE html><html><head>" +
        "<style>" +
        "h1   {color: blue;}" +
        "</style>" +
        "</head>" +
        "<body>" +
        "<h1>"+ "AAA" +"</h1>" +
        "<div>Hola, tu cotización del andamio ya está lista!</div>" +
        "</body>" +
        "</html>";
        TimeUnit.SECONDS.sleep(10);
        sendEmail(quote.getUserEmail(), "Cotizacion", mailBody, file.getAbsolutePath());
    }

    @Async
    public void waitToSend(AirQuote quote) throws MessagingException, InterruptedException{
        
        File file = new File("C:\\Users\\SalaCAD\\Documents\\AAA\\PDFs\\"+quote.getId()+"\\test.pdf");

        System.out.print("\nRegistrando");
        while (!file.exists() || !(file.length() > 0)){
            System.out.print(".");
            TimeUnit.SECONDS.sleep(3);
        }
        String mailBody;

        mailBody = "<!DOCTYPE html><html><head>" +
        "<style>" +
        "h1   {color: blue;}" +
        "</style>" +
        "</head>" +
        "<body>" +
        "<h1>"+ "AAA" +"</h1>" +
        "<div>Hola, tu cotización del aire acondicionado ya está lista!</div>" +
        "</body>" +
        "</html>";
        TimeUnit.SECONDS.sleep(10);
        sendEmail(quote.getUserEmail(), "Cotizacion", mailBody, file.getAbsolutePath());
    }

    public void sendEmail(String toEmail, String subject, String body, String attachment) throws MessagingException{
        
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);

        mimeMessageHelper.setFrom("dfsanchezme@gmail.com");
        mimeMessageHelper.setTo(toEmail);
        mimeMessageHelper.setText(body, true);
        mimeMessageHelper.setSubject(subject);

        FileSystemResource fileSystemResource = new FileSystemResource(new File(attachment));

        mimeMessageHelper.addAttachment(fileSystemResource.getFilename(), fileSystemResource);
        mailSender.send(mimeMessage);

        System.out.println("\nMail sent successfully...");
    }
}
