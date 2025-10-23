package com.spring.controle_remessas_api.domain.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "remittance_periods")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RemittancePeriodEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "remittance_type_id", nullable = false)
    private RemittanceTypeEntity remittanceType;

    @Column(nullable = false)
    private String name;
}
