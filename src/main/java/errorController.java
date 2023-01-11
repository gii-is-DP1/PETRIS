import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class errorController {

    @RequestMapping("/")
    public void handleRequest() {
        throw new RuntimeException("test exception");
    }
    
}
