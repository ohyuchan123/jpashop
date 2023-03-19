package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member_DM;
import jpabook.jpashop.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberServiceTest {

        @Autowired
        MemberService memberService;
        @Autowired
        MemberRepository memberRepository;

        @Test
        public void 회원가입() throws Exception {
            //Given : 이렇게 주어졌을때
            Member_DM member_dm = new Member_DM();
            member_dm.setName("kim");
            //When : 이 조건을 실행하면
            Long saveId = memberService.join(member_dm);
            //Then : 이러한 결과가 나와야 한다.
            assertEquals(member_dm, memberRepository.findOne(saveId));
        }

        @Test
        public void 중복_회원_예외() throws Exception {
            //Given
            Member_DM member1_dm = new Member_DM();
            member1_dm.setName("kim");
            Member_DM member2_dm = new Member_DM();
            member2_dm.setName("kim");
            //When
            memberService.join(member1_dm);
            memberService.join(member2_dm); //예외가 발생해야 한다.
            //Then
            fail("예외가 발생해야 한다.");
        }
}