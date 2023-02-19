package lk.ijse.spring.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@ComponentScan("lk.ijse.spring")
@EnableWebMvc
public class WebAppConfig {
    private int code;
    private String message;
    private Object data;

}
