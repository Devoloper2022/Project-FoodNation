package com.example.project1.Domain;

import lombok.Data;
import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.*;


@Data
@Entity
public class ImageModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Lob
    @Column(columnDefinition = "BYTEA")
    private byte[] file;
    @Column(columnDefinition = "text")
    private String link;
    @JsonIgnore
    private Long userId;
    @JsonIgnore
    private Long OrgId;
    @JsonIgnore
    private Long FoodId;
}
