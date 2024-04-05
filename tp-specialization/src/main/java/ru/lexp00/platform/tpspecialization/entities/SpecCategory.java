package ru.lexp00.platform.tpspecialization.entities;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "specialization_categories")
@Getter
@Setter
@NoArgsConstructor
public class SpecCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @OneToMany(mappedBy = "specCategory", fetch = FetchType.LAZY)
    private List<Specialization> specList;


}
