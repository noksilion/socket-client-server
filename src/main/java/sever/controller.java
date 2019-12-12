package sever;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class controller {

    @MessageMapping("/chat")
    @SendTo("/topic/news")
    public String send(String message) throws Exception {
        String time = new SimpleDateFormat("HH:mm").format(new Date());
        return time.toString() + " - " + message;
    }

    @MessageMapping("/chat.addUser")
    @SendTo("/topic/news")
    public String addUser(@Payload String chatMessage,
                               SimpMessageHeaderAccessor headerAccessor) {
        // Add username in web socket session
        headerAccessor.getSessionAttributes().put("username", chatMessage);
        return chatMessage;
    }
}
