package com.appstra.aspirante.entity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Comment;

import java.sql.Timestamp;

@Getter
@Setter
@Entity
@Table(name = "DESCRIPTION_FAMILY", schema = "TRANSACTIONAL")
public class DescriptionFamily {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DEFA_ID")
    private Integer descriptionFamilyId;

    @Column(name = "DEFA_FAMILY_DESCRIPTION")
    @Comment("Descripcion de la familia fortalezas")
    private String descriptionFamily;

    @Column(name = "DEFA_STRENGTHS")
    @Comment("de la persona fortalezas")
    private String descriptionFamilyStrengths;

    @Column(name = "DEFA_SELF_DESCRIPTION")
    @Comment("describa quien es usted")
    private String descriptionFamilySelfDescription;

    @Column(name = "DEFA_DATA_AUTHORIZATION")
    @Comment("Autorizacion de datos")
    private Boolean descriptionFamilyDataAuthorization;

    @Column(name = "DEFA_CREATION_DATE")
    private Timestamp descriptionFamilyCreationDate;

    @Column(name = "DEFA_EDIT_DATE")
    private Timestamp descriptionFamilyEditDate;

    @Column(name = "DEFA_EDIT_USER_ID")
    private Integer descriptionFamilyEditUserId;

    @ManyToOne
    @JoinColumn(name = "ASPI_ID", referencedColumnName = "ASPI_ID")
    private Aspirant aspirant;

}

