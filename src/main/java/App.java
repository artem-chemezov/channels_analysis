import org.apache.log4j.Logger;
import org.telegram.telegrambots.ApiContextInitializer;

public class App {
    private static final Logger log = Logger.getLogger(App.class);


    public static void main(String[] args) {
        ApiContextInitializer.init();
        Bot test_habr_bot = new Bot("test_bot", "1335623903:AAHXfpsJqnRkwFSOiuD_rqUWpVgLJ7duMbg");
        test_habr_bot.botConnect();
    }
}