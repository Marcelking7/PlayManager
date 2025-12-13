package bj.csam.playlist.PlayManager.Controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/test")
@Slf4j
public class TestController {

    @GetMapping
    public String showTestHomePage() {
        log.info("Accès à la page d'accueil des tests");
        return "test/index";
    }
}