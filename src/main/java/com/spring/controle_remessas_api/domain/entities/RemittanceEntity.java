package com.spring.controle_remessas_api.domain.entities;

import com.spring.controle_remessas_api.domain.enums.StatusEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;
@Entity
@Table(name = "remittances")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class RemittanceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", columnDefinition = "UUID DEFAULT gen_random_uuid()", nullable = false)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "city_id", nullable = false)
    private CityEntity city;

    @ManyToOne
    @JoinColumn(name = "fund_id", nullable = false)
    private FundEntity fund;

    @ManyToOne
    @JoinColumn(name = "remittance_type_id", nullable = false)
    private RemittanceTypeEntity remittanceType;

    @ManyToOne
    @JoinColumn(name = "remittance_period_id", nullable = false)
    private RemittancePeriodEntity remittancePeriod;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusEnum status;

    @Column(name = "reason_pendency", length = 1000)
    private String reasonPendency;

    @Column(name = "date_shipping")
    private LocalDate dateShipping;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity responsibleShipping;
}
