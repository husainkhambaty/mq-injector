package com.fifty2minds.tools.controller;

import com.fifty2minds.tools.entity.MQMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.JmsException;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping(value = "/mq", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
public class MQController {

    @Autowired
    protected JmsTemplate jmsTemplate;

    @GetMapping("/health")
    public String getHealth() {
        return "{ \"status\": \"API-200\", \"message\": \"MQ Injector is OK\"}";
    }

    @PostMapping("/send")
    @ResponseBody
    ResponseEntity<String> send(@RequestBody final Map<String, String> body) {
        try {
            if (body.containsKey("queue") && body.containsKey("message")) {
                jmsTemplate.convertAndSend(body.get("queue"), body.get("message"));
                return ResponseEntity.ok("{ \"status\": \"API-200\", \"message\": \"MQ Message successfully sent\"}");
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{ \"status\": \"API-400\", \"message\": \"Request validation failed\"}");
            }
        } catch(JmsException ex) {
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{ \"status\": \"API-500\", \"message\": \"Error while sending MQ Message\"}");
        }
    }

    @PostMapping("/sendbulk")
    @ResponseBody
    ResponseEntity<String> sendbulk(@RequestBody final Map<String, String> body) {
        try {
            if (body.containsKey("queue") && body.containsKey("message") && body.containsKey("count")) {
                int count = Integer.parseInt(body.get("count"));

                for (int i = 0; i < count; i++) {
                    jmsTemplate.convertAndSend(body.get("queue"), body.get("message"));
                }
                return ResponseEntity.ok("{ \"status\": \"API-200\", \"message\": \"MQ Message successfully sent\"}");
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{ \"status\": \"API-400\", \"message\": \"Request validation failed\"}");
            }
        } catch(JmsException ex) {
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{ \"status\": \"API-500\", \"message\": \"Error while sending MQ Message\"}");
        }
    }

}
