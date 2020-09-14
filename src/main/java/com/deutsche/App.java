package com.deutsche;

import com.deutsche.users.model.User;
import com.deutsche.users.services.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication(scanBasePackages = {"com.deutsche"})
public class App {
    public static void main(String[] args) {
//        ApiContextInitializer.init();
//        Bot test_habr_bot = new Bot("test_bot", "1335623903:AAHXfpsJqnRkwFSOiuD_rqUWpVgLJ7duMbg");
//        test_habr_bot.botConnect();

        ConfigurableApplicationContext context = SpringApplication.run(App.class, args);
        var con = context.getBean(UserService.class);
        User user = User.builder()
                .login("qwe")
                .name("qwe")
                .build();
        con.saveUser(user);

        System.out.println(con.getByLogin("qwe"));
    }
}