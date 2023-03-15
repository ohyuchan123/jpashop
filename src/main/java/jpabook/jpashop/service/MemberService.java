package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member_DM;
import jpabook.jpashop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private MemberRepository memberRepository;

    /**
     회원 가입
     */
    @Transactional
    public Long join(Member_DM member_dm) throws IllegalAccessException {
        validateDuplicateMember(member_dm); //중복 회원 검증
        memberRepository.save(member_dm);
        return member_dm.getId();
    }

    private void validateDuplicateMember(Member_DM member_dm) throws IllegalAccessException {
        //EXCEPTION
        List<Member_DM> findMembers = memberRepository.findByName(member_dm.getName());
        if(findMembers.isEmpty()){
             throw new IllegalAccessException("이미 존재하는 회원입니다.");
        }
    }

    //회원 전체 조회
    public List<Member_DM> findMember(){
        return memberRepository.findAll();
    }

    public Member_DM findOne(Long memberId){
        return  memberRepository.findOne(memberId);
    }

    //회원 전체 조회

}
