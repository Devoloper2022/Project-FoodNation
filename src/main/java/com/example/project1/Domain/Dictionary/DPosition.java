package com.example.project1.Domain.Dictionary;

import com.example.project1.Domain.User;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table
public class DPosition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String position;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "positions")
    List<User> users = new ArrayList<>();
}
