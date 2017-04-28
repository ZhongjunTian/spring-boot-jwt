package hello;

import hello.jwt.TokenAuthUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.core.token.Token;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
	https://auth0.com/blog/securing-spring-boot-with-jwts/
	https://github.com/auth0-blog/spring-boot-jwts
	https://github.com/szerhusenBC/jwt-spring-security-demo
*/
	
@SpringBootApplication
@RestController
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @GetMapping("/hello")
    public @ResponseBody Object hellWorld() {
        return "hello world";
    }

    public static final String LOGIN_PATH = "/login";
    @PostMapping(LOGIN_PATH)
    public void auth(HttpServletRequest request, HttpServletResponse response,
                                     @RequestBody AccountCredentials credentials) {
        TokenAuthUtil.addTokenToHeader(response,credentials.getUsername());
    }

    public static class AccountCredentials {
        public AccountCredentials() {
        }
        private String username;
        private String password;

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }
}