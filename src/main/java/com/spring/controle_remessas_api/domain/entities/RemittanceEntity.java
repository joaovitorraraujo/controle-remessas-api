package com.spring.controle_remessas_api.domain.entities;

import com.spring.controle_remessas_api.domain.enums.StatusEnum;
import com.spring.controle_remessas_api.domain.enums.TypeRemittanceEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "remittances")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RemittanceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TypeRemittanceEnum TypeRemittance;

    @Column(nullable = false)
    private List<String> period;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusEnum status;

    @Column(length = 1000)
    private String reasonPendency;

    private LocalDate dateShipping;

    @ManyToOne
    @JoinColumn(name = "city_id", nullable = false)
    private CityEntity city;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity responsibleShipping;

}
