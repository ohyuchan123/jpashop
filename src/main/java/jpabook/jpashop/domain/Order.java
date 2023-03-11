package jpabook.jpashop.domain;

import jpabook.jpashop.Member;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter @Setter
public class Order {

    @Id
    @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member_DM member_dm;//주문 회원

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "order")
    private List<OrderItem> orderItems = new ArrayList<>();

    @OneToOne
    private Delivery delivery;//배송 정보

    private LocalDateTime orderDate;//주문 시간

    @Enumerated(EnumType.STRING)
    private OrderStatus status; //주문상태 [ORDER,CANCEL]
}
