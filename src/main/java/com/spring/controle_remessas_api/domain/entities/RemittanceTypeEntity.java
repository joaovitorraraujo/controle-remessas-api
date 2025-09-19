package com.spring.controle_remessas_api.domain.entities;

import com.spring.controle_remessas_api.domain.enums.TypeRemittanceEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "remittance_types")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RemittanceTypeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, unique = true)
    private TypeRemittanceEnum name;

    @OneToMany(mappedBy = "remittanceType", cascade = CascadeType.ALL)
    private List<RemittancePeriodEntity> periods = new ArrayList<>();
}

