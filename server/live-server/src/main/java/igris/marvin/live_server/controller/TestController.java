package igris.marvin.live_server.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/test")
public class TestController {
    
    @GetMapping
    public ResponseEntity<String> getTest() {
        return ResponseEntity.ok("test complete!");
    }
}
