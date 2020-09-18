package com.deutsche.view.handlers;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;

import java.lang.reflect.Field;

@SpringBootTest
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = MockTestConfiguration.class)
class ResponseHandlerTest {
    @Autowired
    private Handler handler;

    @SneakyThrows
    private Update createUpdate(String textStr){
        Message message = new Message();
        Field text = message.getClass().getDeclaredField("text");
        text.setAccessible(true);
        text.set(message, textStr);
        Update update = new Update();
        Field mes = update.getClass().getDeclaredField("message");
        mes.setAccessible(true);
        mes.set(update, message);
        return update;
    }

    @Test
    void testThatHandleReturnsDefaultWhenUncorrectCommand() {
        Update update = createUpdate("Invalid command");
        Assertions.assertTrue(handler.handle(update).get(0).equals("Мы не знаем таких команд :("));
    }

    @Test
    void testHandleReturnsCategorizeChannel() {
        Update update = createUpdate("Классификация канала");
        Assertions.assertTrue(handler.handle(update).get(0).equals("Введи канал/группу в формате @testChannel"));

        update = createUpdate("Классификация; канала; eafd");
        handler.handle(update);
    }

    @Test
    void testHandleReturnsStatistics() {
        Update update = createUpdate("Статистика");
        Assertions.assertTrue(handler.handle(update).get(0).equals("Введи канал/группу, дату с, дату по в формате @testChannel; 26.06.2017 20:40 - 18.07.2019 06:45"));

        update = createUpdate("Классификация; канала; eafd");
        handler.handle(update);
    }

    @Test
    void testHandleReturnsWordFromChannel() {
        Update update = createUpdate("Поиск слова на каннале");
        Assertions.assertTrue(handler.handle(update).get(0).equals("Введи слово, канал/группу, дату с, дату по в формате Кино; @testChannel; 26.06.2017 20:40 - 18.07.2019 06:45"));

        update = createUpdate("Классификация; канала; eafd");
        handler.handle(update);
    }
}