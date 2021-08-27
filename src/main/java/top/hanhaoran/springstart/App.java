package top.hanhaoran.springstart;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "top/hanhaoran/springstart/Controller")
public class App {
    public static void main(String[] args){
        SpringApplication.run(App.class,args);
    }
}
