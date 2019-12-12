package client;

import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.converter.StringMessageConverter;
import org.springframework.messaging.simp.stomp.StompFrameHandler;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandler;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;

import java.lang.reflect.Type;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;

public class Client {
    public static void main(String[] args) {

        String url = "ws://127.0.0.1:8080/chat";


            WebSocketClient client = new StandardWebSocketClient();
            WebSocketStompClient stompClient = new WebSocketStompClient(client);

            stompClient.setMessageConverter(new MappingJackson2MessageConverter());
            stompClient.setMessageConverter(new StringMessageConverter());

            StompSessionHandler sessionHandler = new MyStompSessionHandler();
            stompClient.connect(url, sessionHandler);

            new Scanner(System.in).nextLine();

    }

}
