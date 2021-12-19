package jpabook.jpashop.service;

import jpabook.jpashop.domain.Address;
import jpabook.jpashop.domain.Item.Book;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.domain.Order;
import jpabook.jpashop.domain.OrderStatus;
import jpabook.jpashop.repository.OrderRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class OrderServiceTest {

    @Autowired EntityManager em;
    @Autowired OrderService orderService;
    @Autowired OrderRepository orderRepository;

    @Test
    @Rollback(value = false)
    public void 상품주문() throws Exception {
        //given
        Member member = new Member();
        member.setName("강원도");
        member.setAddress(new Address("강원도", "고한읍", "727272"));
        em.persist(member);

        Book book = new Book();
        book.setName("하이원사용법");
        book.setPrice(50000);
        book.setStockQty(10);
        em.persist(book);

        int orderCount = 2;

        //when

        Long orderId = orderService.order(member.getId(), book.getId(), orderCount);
        System.out.println("member id=" + member.getId());
        System.out.println("book id=" + book.getId());
        System.out.println("order id=" + orderId);

        //then
        Order getOrder = orderRepository.findOne(orderId);

        assertEquals("상품 주문시 상태틑 ORDER=", OrderStatus.ORDER, getOrder.getStatus());

    }

    @Test
    public void 주문취소() throws Exception {
        //given

        //when

        //then

    }

    @Test
    public void 상품주문_재고수량초과() throws Exception {
        //given

        //when

        //then

    }
}