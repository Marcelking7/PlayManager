package bj.csam.playlist.PlayManager.Controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    // Pour l'instant, on log les erreurs mais on ne les active pas encore
    // @ExceptionHandler(Exception.class)
    // public String handleException(Exception e, RedirectAttributes ra) {
    //     log.error("Erreur globale: ", e);
    //     ra.addFlashAttribute("flashMessage", "Une erreur est survenue");
    //     ra.addFlashAttribute("flashType", "error");
    //     return "redirect:/";
    // }

    @ExceptionHandler(RuntimeException.class)
    public String handleRuntimeException(RuntimeException e, RedirectAttributes ra) {
        log.error("Erreur runtime: ", e);
        // Pour l'instant, on ne redirige pas, mais on log
        return "error"; // Page d'erreur générique si elle existe
    }
}