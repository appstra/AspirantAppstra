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
@Table(name = "TYPE_TEST", schema = "PARAMETERIZATION")
public class TypeTest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TYTE_ID")
    private Integer typeTestId;

    @Column(name = "STAT_ID")
    @Comment("Campo de la base de datos de UserAppstra tabla estados")
    private Integer statId;

    @Column(name = "TYTE_NAME")
    private String typeTestName;

    @Column(name = "TYTE_DESCRIPTION")
    private String typeTestDescription;

    @Column(name = "TYTE_CREATION_DATE")
    private Timestamp typeTestCreationDate;

    @Column(name = "TYTE_EDIT_DATE")
    private Timestamp typeTestEditDate;

    @Column(name = "TYTE_EDIT_USER_ID")
    private Integer typeTestEditUserId;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy = "typeTest")
    private List<Competence> competenceList;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy = "typeTest")
    private List<Evaluation> evaluationList;


}

