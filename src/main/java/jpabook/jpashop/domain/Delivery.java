package jpabook.jpashop.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter @Setter
public class Delivery {

    @Id @GeneratedValue
    @Column(name = "delivery_id")
    private Long id;

    @JsonIgnore
    @OneToOne(mappedBy = "delivery", fetch = LAZY, cascade = CascadeType.ALL)
    private Order order;

    @Embedded
    private Address address;

    @Enumerated(EnumType.STRING)
    // EnumType.Ordinal은 중간에 다른 값이 들어오면 망함
    private DeliveryStatus status; // READY, COMP
}
