package com.example.springintro;

import com.example.springintro.repository.JdbcMemberRepository;
import com.example.springintro.repository.MemberRepository;
import com.example.springintro.repository.MemoryMemberRepository;
import com.example.springintro.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class SpringConfig {

    private final DataSource dataSource;
    public SpringConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    @Bean
    public MemberService memberService(){
        return  new MemberService(memberRepository());
    }
    @Bean
    public MemberRepository memberRepository(){
        // Interface 로 구현체만 변경해주면 됨(설정 Config) 변경 시 영향도 낮춤. 직접 사용되는 서비스로 가서 수정할 필요가 없음
        //return new MemoryMemberRepository();
        return new JdbcMemberRepository(dataSource);
    }
}
