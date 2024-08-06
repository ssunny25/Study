package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
public class AppConfig {
    //생성자 주입

    //역할
    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository());
    }

    //구현
    private MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    //역할
    public OrderService orderService() {
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    //구현
    public DiscountPolicy discountPolicy() {
        return new RateDicountPolicy();
    }
}
*/

// Spring
// @Bean memberService → new MemoryMemberRepository() 호출
// @Bean orderService → new MemoryMemberRepository() 호출
// 싱글톤이 깨지는거 아닌가?! → MemberServiceImpl / OrderServiceImpl / ConfigurationSingletonTest 참고
@Configuration //설정 정보
public class AppConfig {
    @Bean //스프링 컨테이너에 등록
    public MemberService memberService() {
        System.out.println("call AppConfig.memberService");
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        System.out.println("call AppConfig.memberRepository");
        return new MemoryMemberRepository();
    }

    @Bean
    public OrderService orderService() {
        System.out.println("call AppConfig.orderService");
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    @Bean
    public DiscountPolicy discountPolicy() {
        return new RateDiscountPolicy();
    }
}
