package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class) // junit 실행할 때 스프링이랑 같이 실행하겠다
@SpringBootTest // 스프링 부트를 띄운 상태로 테스트를 하겠다
@Transactional // 트랜잭션 걸고 테스트를 돌린 뒤 테스트 끝나면 롤백
public class MemberServiceTest {

    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;
    @Autowired EntityManager em;

    @Test
    public void 회원가입() {
        // given 주어졌을 때
        Member member = new Member();
        member.setName("kim");

        // when 이렇게 하면
        Long savedId = memberService.join(member);

        //then 결과가 이렇게 나온다.
        em.flush();
        assertEquals(member, memberRepository.findOne(savedId));
    }

    @Test(expected = IllegalStateException.class)
    public void 중복_회원_예외() {
        //given
        Member member1 = new Member();
        member1.setName("kim");
        Member member2 = new Member();
        member2.setName("kim");

        // when
        memberService.join(member1);
        memberService.join(member2); // 예외가 발생해야 한다.

        //then
        fail("예외가 발생해야 한다.");
    }
}