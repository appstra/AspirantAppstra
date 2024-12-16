package com.appstra.aspirante.entity;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Comment;

import java.sql.Timestamp;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "ASK", schema = "PARAMETERIZATION")
public class Ask {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ASK_ID")
    private Integer askId;

    @Column(name = "STAT_ID")
    @Comment("Campo de la base de datos de UserAppstra tabla estados")
    private Integer statId;

    @Column(name = "ASK_ASK")
    private String askAsk;

    @Column(name = "ASK_CREATION_DATE")
    private Timestamp askCreationDate;

    @Column(name = "ASK_EDIT_DATE")
    private Timestamp askEditDate;

    @Column(name = "ASK_EDIT_USER_ID")
    private Integer askEditUserId;

    @ManyToOne
    @JoinColumn(name = "COMP_ID", referencedColumnName = "COMP_ID")
    private Competence competence;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy = "ask")
    private List<Response> responseList;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy = "ask")
    private List<ResponseEvaluation> responseEvaluationList;
}
