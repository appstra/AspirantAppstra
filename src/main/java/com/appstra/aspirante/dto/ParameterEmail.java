package com.appstra.aspirante.dto;

public class ParameterEmail {
    private String to;
    private String subject;
    private BodyEmailDTO bodyEmailDTO;

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public BodyEmailDTO getBodyEmailDTO() {
        return bodyEmailDTO;
    }

    public void setBodyEmailDTO(BodyEmailDTO bodyEmailDTO) {
        this.bodyEmailDTO = bodyEmailDTO;
    }
}
