package city.olooe.hello.config;

import city.olooe.hello.security.JwtAuthenticationFilter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.web.filter.CorsFilter;

@SuppressWarnings("deprecation")
@EnableWebSecurity
@Slf4j
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors()// WebMvcConfig에서 이미 설정했으므로 기본 cors 설정
                .and().csrf()// crsf는 현재 사용하지 않으므로 disable
                    .disable()
                .httpBasic()
                    .disable() // token을 사용하지 않으므로 basic 인증 disable
                .sessionManagement() // session 기반이 아님을 선언
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests() // /와 /auth/** 경로는 인증 안 해도 됨
                    .antMatchers("/","/auth/**", "/oauth2/**").permitAll() // oauth2 엔드포인트 추가
                .anyRequest()
                    .authenticated()
                .and()
                .oauth2Login()
                .authorizationEndpoint().baseUri("/oauth2/auth")
                ; // oauth2Login 설정
        http.addFilterAfter(jwtAuthenticationFilter, CorsFilter.class);
    }

//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.cors()
//                .and()
//                .csrf()
//                .disable()
//                .httpBasic()
//                .disable()
//                .sessionManagement()
//                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                .and()
//                .authorizeRequests()
//                .antMatchers("/", "/auth/**", "/oauth2/**").permitAll()
//                .anyRequest()
//                .authenticated()
//                .and()
//                .oauth2Login()
//                .authorizationEndpoint()
//                .baseUri("/oauth2/callback/*");
//        http.addFilterAfter(jwtAuthenticationFilter, CorsFilter.class);
//    }



}
