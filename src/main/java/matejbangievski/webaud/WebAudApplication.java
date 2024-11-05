package matejbangievski.webaud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class WebAudApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebAudApplication.class, args);
    }

}
