package com.example.project1.Domain.Dictionary;

import com.example.project1.Domain.LocalOrganization;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table
public class DOrganizationType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String type;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "category")
    private List<LocalOrganization> organizations;
}
