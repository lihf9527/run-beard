package org.game.api;

import org.game.model.UserForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {
    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @Autowired
    private MessageSource messageSource;

    @GetMapping("/click")
    public String click() {
        System.out.println("click ...");
        simpMessagingTemplate.convertAndSend("/topic/abc", "hello world");
        return "success";
    }

    @MessageMapping("/hello")
    public String hello(UserForm form) {
        System.out.println(form);
        return "hello";
    }

    @SubscribeMapping("/hi")
    public String hi() {
        System.out.println("hi ...");
        return "hi";
    }

    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    @ResponseBody
    @GetMapping("/sayHello")
    public String sayHello() {
        System.out.println("hello world ...");
        return messageSource.getMessage("hello", null, LocaleContextHolder.getLocale());
    }
}
