package com.appstra.aspirante.entity;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "RESPONSE", schema = "PARAMETERIZATION")
public class Response {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "RESP_ID")
    private Integer responseId;

    @Column(name = "RESP_ANSWER")
    private String responseAnswer;

    @Column(name = "RESP_ANSWER_CORRECT")
    private Boolean responseAnswerCorret;

    @Column(name = "RESP_CREATION_DATE")
    private Timestamp responseCreationDate;

    @Column(name = "RESP_EDIT_DATE")
    private Timestamp responseEditDate;

    @Column(name = "RESP_EDIT_USER_ID")
    private Integer responseEditUserId;

    @ManyToOne
    @JoinColumn(name = "ASK_ID", referencedColumnName = "ASK_ID")
    private Ask ask;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy = "response")
    private List<ResponseEvaluation> responseEvaluationList;
}

