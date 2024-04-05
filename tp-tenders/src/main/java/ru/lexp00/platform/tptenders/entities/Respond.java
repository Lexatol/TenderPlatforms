package ru.lexp00.platform.tptenders.entities;


import jakarta.persistence.*;
import lombok.*;
import ru.lexp00.platform.tptenders.enums.Status;

import java.util.UUID;

@Entity
@Table(name = "responds")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Respond {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "client_id")
    private UUID clientId;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "tender_id")
    private Tender tender;

    @Column(name = "status_id")
    @Enumerated(EnumType.ORDINAL)
    private Status status;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private Long price;

    @Column(name = "term")
    private String term;
}
