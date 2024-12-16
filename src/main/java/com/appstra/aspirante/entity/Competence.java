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
@Table(name = "COMPETENCE", schema = "PARAMETERIZATION")
public class Competence {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "COMP_ID")
    private Integer competenceId;

    @Column(name = "STAT_ID")
    @Comment("Campo de la base de datos de UserAppstra tabla estados")
    private Integer statId;

    @Column(name = "COMP_NAME")
    private String competenceName;

    @Column(name = "COMP_CREATION_DATE")
    private Timestamp competenceCreationDate;

    @Column(name = "COMP_EDIT_DATE")
    private Timestamp competenceEditDate;

    @Column(name = "COMP_EDIT_USER_ID")
    private Integer competenceEditUserId;

    @ManyToOne
    @JoinColumn(name = "TYTE_ID", referencedColumnName = "TYTE_ID")
    private TypeTest typeTest;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy = "competence")
    private List<Ask> askList;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy = "competence")
    private List<TestParameters> testParametersList;


}

