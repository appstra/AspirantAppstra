package com.appstra.aspirante.controller;

import com.appstra.aspirante.dto.BodyEmailDTO;
import com.appstra.aspirante.dto.ParameterEmail;
import com.appstra.aspirante.service.EmailService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/email")
public class EmailController {
    private final EmailService emailService;

    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping("/send")
    @Operation(summary = "Enviar Correo", description = "Enviar un correo electrónico personalizado")
    public ResponseEntity<String> sendEmail(@Validated @RequestBody ParameterEmail parameterEmail) {
        emailService.sendEmail(parameterEmail);
        return ResponseEntity.ok("Correo enviado correctamente");
    }

    @GetMapping("/test")
    @Operation(summary = "Prueba de Envío", description = "Prueba para verificar el funcionamiento del servicio de correo")
    public ResponseEntity<String> testEmail() {
        ParameterEmail parameterEmail = new ParameterEmail();
        BodyEmailDTO bodyEmailDTO = new BodyEmailDTO();

        bodyEmailDTO.setNamePerson("Edwin Quimbay");
        bodyEmailDTO.setMenssage("Correo de prueba");
        bodyEmailDTO.setUrl("https://www.youtube.com/watch?v=dQw4w9WgXcQ&list=PLpHl20Aa3qX7gQye-lm_6XuFW8pckp3Rr");


        parameterEmail.setTo("quimbay.edwin@gmail.com");
        parameterEmail.setSubject("Correo Prueba");
        parameterEmail.setBodyEmailDTO(bodyEmailDTO);

        emailService.sendEmail(parameterEmail);
        return ResponseEntity.ok("Correo de prueba enviado");
    }
}
