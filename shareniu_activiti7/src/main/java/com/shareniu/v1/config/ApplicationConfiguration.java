package com.shareniu.v1.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import javax.sql.DataSource;

/**
 * @author 分享牛
 */
@Configuration
public class ApplicationConfiguration  extends WebSecurityConfigurerAdapter {

    private Logger logger = LoggerFactory.getLogger(ApplicationConfiguration.class);
    @Autowired
    private DataSource dataSource;

//    @Bean
//    public UserDetailsService myUserDetailsService() {
//
//        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);
//
//        String[][] usersGroupsAndRoles = {
//                {"salaboy", "password", "ROLE_ACTIVITI_USER", "GROUP_activitiTeam"},
//                {"ryandawsonuk", "password", "ROLE_ACTIVITI_USER", "GROUP_activitiTeam"},
//                {"erdemedeiros", "password", "ROLE_ACTIVITI_USER", "GROUP_activitiTeam"},
//                {"other", "password", "ROLE_ACTIVITI_USER", "GROUP_otherTeam"},
//                {"admin", "password", "ROLE_ACTIVITI_ADMIN"},
//        };
//
//        for (String[] user : usersGroupsAndRoles) {
//            List<String> authoritiesStrings = Arrays.asList(Arrays.copyOfRange(user, 2, user.length));
//            logger.info("> Registering new user: " + user[0] + " with the following Authorities[" + authoritiesStrings + "]");
////            jdbcUserDetailsManager.createUser(new User(user[0], passwordEncoder().encode(user[1]),
////                    authoritiesStrings.stream().map(s -> new SimpleGrantedAuthority(s)).collect(Collectors.toList())));
//        }
//
//        return jdbcUserDetailsManager;
//    }

//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .csrf().disable()
//                .authorizeRequests()
//                .anyRequest()
//                .authenticated()
//                .and()
//                .httpBasic();
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
}

