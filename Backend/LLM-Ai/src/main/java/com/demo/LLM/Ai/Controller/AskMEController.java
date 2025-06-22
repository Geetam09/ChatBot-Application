package com.demo.LLM.Ai.Controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.MediaType.APPLICATION_JSON;

@RestController
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class AskMEController {

//    @GetMapping("/askme")
//    public ResponseEntity<String> askMe(@RequestParam String prompt) {
//        String url="http://localhost:11434/api/generate";
//        Map<String, Object> requestBody = new HashMap<>();
//        requestBody.put("model", "tinyllama");
//        requestBody.put("prompt", prompt);
//        requestBody.put("Stream", false);
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.set("Content-Type", MediaType.APPLICATION_JSON_VALUE);
//
//        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(requestBody, headers);
//        try{
//            ResponseEntity<String> response = new RestTemplate().postForEntity(url, entity, String.class);
//           return ResponseEntity.ok(response.getBody());
//        }catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: "+e.getMessage());
//        }
//
//    }

    @GetMapping("/askme")
    public ResponseEntity<String> askMe(@RequestParam String prompt) {
        String url = "http://localhost:11434/api/generate";
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("model", "tinyllama");
        requestBody.put("prompt", prompt);
        requestBody.put("Stream", false);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(requestBody, headers);

        try {
            ResponseEntity<String> response = new RestTemplate().postForEntity(url, entity, String.class);

            // Extract only the "response" field from JSON
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(response.getBody());
            String llmResponse = root.path("response").asText();

            return ResponseEntity.ok(llmResponse);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        }
    }

}
