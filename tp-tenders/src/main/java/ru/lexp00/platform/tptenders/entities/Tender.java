package ru.lexp00.platform.tptenders.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import ru.lexp00.platform.tptenders.enums.Status;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "tenders")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Tender {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long tenderId;

    @Column(name = "title")
    private String title;

    @Column(name = "data_start")
    private String dateStart;

    @Column(name = "date_finish")
    private String dateFinish;

    @Column(name = "address")
    private String address;

    @Column(name = "customer_id")
    private UUID customerId;

    @Column(name = "contractor_id")
    private UUID contractorId;

    @Column(name = "description")
    private String description;

    @Column(name = "min_price")
    private BigDecimal minPrice;

    @Column(name = "max_price")
    private BigDecimal maxPrice;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "status_id")
    private Status status;

    @Column(name = "specialization_id")
    private Long specializationId;

    @OneToMany (mappedBy = "tender", fetch = FetchType.LAZY)
    private List<Respond> responds;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createAt;


    @Column(name = "announce_date")
    private LocalDateTime announceDate;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Tender)) return false;
        Tender tender = (Tender) o;
        return createAt.equals(tender.createAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(createAt);
    }
}
