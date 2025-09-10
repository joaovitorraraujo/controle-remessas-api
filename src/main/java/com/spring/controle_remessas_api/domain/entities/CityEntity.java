package com.spring.controle_remessas_api.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "cities")
@NoArgsConstructor
@AllArgsConstructor
public class CityEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String name;

    @ManyToMany
    @JoinTable(
            name = "responsible_cities",
            joinColumns = @JoinColumn(name = "city_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<UserEntity> responsibles = new ArrayList<>();

    @OneToMany(mappedBy = "city", cascade = CascadeType.ALL)
    private List<RemittanceEntity> remittances = new ArrayList<>();
}
