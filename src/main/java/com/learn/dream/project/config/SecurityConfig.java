package com.learn.dream.project.config;

//@Configuration
//@EnableWebSecurity
//public class SecurityConfig {
//
//    private static final String[] SWAGGER_WHITELIST = {
//            "/swagger-ui/**",
//            "/v3/api-docs/**",
//            "/swagger-resources/**",
//            "/swagger-resources"
//    };
//
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http
//                .csrf().disable()
//                .authorizeRequests()
//                // permit all swagger url's
//                .antMatchers(SWAGGER_WHITELIST).permitAll()
//                .anyRequest().authenticated()
//                .and()
//                .httpBasic();
//        return http.build();
//    }
//}

