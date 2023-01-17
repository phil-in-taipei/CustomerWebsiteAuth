package CustomerWebsiteAuth.CustomerWebsiteAuth.security;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity(debug = true)
@AllArgsConstructor
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                //disable CSRF filter to allow Postman to easily use the application
                .csrf(csrf -> csrf.disable())
                //start changing endpoint access settings
                .authorizeRequests(auth -> auth
                        //the following 4 paths should be allowed to all always. They are static and are required to present the pages properly.
                        .antMatchers("/js/**", "/css/**", "/img/**", "/webjars/**").permitAll()
                        //make sure that the admin page can only be accessed user with ROLE_ADMIN
                        //.antMatchers("/").hasRole("USER"))
                        .anyRequest().authenticated())
                        //.anyRequest().permitAll())
                //use HttpBasic authentication for /update-user, withDefaults() allows you to chain the next method
                .httpBasic(Customizer.withDefaults())
                //use a form to log in with the default login page
                .formLogin();

        return http.build();
    }

    @Bean
    public  PasswordEncoder passwordEncoder() { //BCryptPasswordEncoder
        System.out.println("*************Running password encoder*************");
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode("testpassword");
        System.out.println(encodedPassword);
        boolean decodedPassword = passwordEncoder.matches("testpassword", encodedPassword);
        System.out.println("The password is decoded properly: " + decodedPassword);
        return
                new BCryptPasswordEncoder(5); //  SCryptPasswordEncoder();
    }
}
