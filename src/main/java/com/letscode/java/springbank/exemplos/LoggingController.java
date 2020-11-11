package com.letscode.java.springbank.exemplos;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
public class LoggingController {

//    private final Logger logger = LoggerFactory.getLogger(LoggingController.class);
    
    @RequestMapping("/logging")
    public String index() {
        
        log.trace("A TRACE Message");
        log.debug("A DEBUG Message");
        log.info("An INFO Message");
        log.warn("A WARN Message");
        log.error("An ERROR Message");
        
        return "Howdy! Check out the Logs to see the output...";
    }
}
