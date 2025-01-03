package com.appstra.aspirante.methods;
import com.appstra.aspirante.dto.BodyEmailDTO;
import com.appstra.aspirante.dto.ParameterEmail;
import com.appstra.aspirante.service.EmailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailMeth implements EmailService {
    @Value("${spring.mail.username}")
    private String from;

    @Autowired
    private JavaMailSender mailSender;

    public void sendEmail(ParameterEmail ParameterEmail){
        MimeMessage message = mailSender.createMimeMessage();
        try{
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(ParameterEmail.getTo());
            helper.setSubject(ParameterEmail.getSubject());
            helper.setText(this.bodyEmail(ParameterEmail.getBodyEmailDTO()), true);
            helper.setFrom(from);
        }catch (MessagingException e){
            System.out.println("Error: " + e.getMessage());
        }
        mailSender.send(message);
    }

    public String bodyEmail(BodyEmailDTO bodyEmailDTO) {
        return String.format("""
        <!DOCTYPE html>
        <html lang="es">
        <head>
            <meta charset="UTF-8">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <title>Correo de Appstra</title>
            <style>
                body {
                    font-family: Arial, sans-serif;
                    margin: 0;
                    padding: 0;
                    background-color: #f4f4f4;
                }
                .email-container {
                    width: 100%%;
                    max-width: 600px;
                    margin: 0 auto;
                    background-color: #ffffff;
                    padding: 20px;
                    border: 1px solid #ddd;
                }
                .header {
                    text-align: center;
                    font-size: 24px;
                    color: #2d87f0;
                    padding-bottom: 20px;
                }
                .content {
                    font-size: 16px;
                    color: #333;
                    line-height: 1.5;
                }
                .footer {
                    text-align: center;
                    background-color: #333;
                    color: #fff;
                    padding: 10px;
                    font-size: 14px;
                    margin-top: 30px;
                }
                .footer a {
                    color: #ffffff;
                    text-decoration: none;
                    font-weight: bold;
                }
                .banner {
                    background-color: #f5f5f5;
                    padding: 10px;
                    font-size: 12px;
                    color: #999;
                    text-align: center;
                    margin-top: 20px;
                }
            </style>
        </head>
        <body>
            <div class="email-container">
                <!-- Encabezado -->
                <div class="header">
                    Appstra
                </div>
                <!-- Contenido del correo -->
                <div class="content">
                    <p>Estimado/a %s,</p>
                    <p>%s</p>
                    <p>
                        <a href="%s" style="color: #2d87f0; text-decoration: none; font-weight: bold;">
                            Haga clic aqui para mas informacion
                        </a>
                    </p>
                    <p>Saludos cordiales,</p>
                    <p>El equipo de Appstra</p>
                </div>
                <!-- Banner informativo al final -->
                <div class="banner">
                    <p><strong>Este correo es informativo. No responda a este correo electrónico.</strong></p>
                </div>
                <!-- Pie de página -->
                <div class="footer">
                    <p>&copy; 2025 Appstra. Todos los derechos reservados.</p>
                </div>
            </div>
        </body>
        </html>
        """, bodyEmailDTO.getNamePerson(), bodyEmailDTO.getMenssage(), bodyEmailDTO.getUrl());
    }
}

