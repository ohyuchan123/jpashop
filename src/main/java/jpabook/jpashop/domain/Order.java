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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member_DM member_dm;//주문 회원

    @OneToMany(mappedBy = "order",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<OrderItem> orderItems = new ArrayList<>();

    @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private Delivery delivery;//배송 정보

    private LocalDateTime orderDate;//주문 시간

    @Enumerated(EnumType.STRING)
    private OrderStatus status; //주문상태 [ORDER,CANCEL]

    //==연관관계 편의 메서드==//
    public void setMember(Member_DM member_dm){
        this.member_dm = member_dm;
    }

    public void addOrderItem(OrderItem orderItem) {
        orderItems.add(orderItem);
        orderItem.setOrder(this);
    }

    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
        delivery.setOrder(this);
    }

    //==생성 메서드==//
    public static Order createOrder(Member_DM member_dm,Delivery delivery,
                                    OrderItem... orderItems){
        Order order = new Order();
        order.setMember(member_dm);
        order.setDelivery(delivery);
        for(OrderItem orderItem:orderItems){
            order.addOrderItem(orderItem);
        }
        order.setStatus(OrderStatus.ORDER);
        order.setOrderDate(LocalDateTime.now());
        return order;
    }

    //==비즈니스 로직==//
    /**
     * 주문 취소
     */
    public void cancel(){
        if(delivery.getStatus() == DeliveryStatus.COMP){
            throw new IllegalStateException("이미 배송완료된 상품은 취소가 불가능하다.");
        }
        this.setStatus(OrderStatus.CANSEL);
        for(OrderItem orderItem : orderItems){
             orderItem.cancel();
        }
    }

    //==조회 로직==//
    public int getTotalPrice() {
        int totalPrice = 0;

        for (OrderItem orderItem : orderItems) {
            totalPrice += orderItem.getTotalPrice();
        }

        return totalPrice;
    }
}
