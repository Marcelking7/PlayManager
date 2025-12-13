package bj.csam.playlist.PlayManager.Controller;
// ============= MainController.java =============
import bj.csam.playlist.PlayManager.Dto.CategoryDto;
import bj.csam.playlist.PlayManager.Dto.PlaylistDto;
import bj.csam.playlist.PlayManager.Service.CategoryService;
import bj.csam.playlist.PlayManager.Service.PlaylistService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class MainController {
    private final CategoryService categoryService;
    private final PlaylistService linkService;




        @GetMapping("/login")
        public String login() {
            return "login";
        }


    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("categories", categoryService.findAll());
        return "index";
    }

    @PostMapping("/category/add")
    public String addCategory(@ModelAttribute CategoryDto categoryDto) {
        categoryService.save(categoryDto);
        return "redirect:/";
    }

    @GetMapping("/category/{id}")
    public String viewCategory(@PathVariable Long id, Model model) {
        CategoryDto category = categoryService.findById(id);
        if (category == null) {
            return "redirect:/";
        }
        model.addAttribute("category", category);
        model.addAttribute("links", linkService.findByCategoryId(id));
        return "category";
    }

    @PostMapping("/category/{id}/delete")
    public String deleteCategory(@PathVariable Long id) {
        categoryService.deleteById(id);
        return "redirect:/";
    }

    @PostMapping("/link/add")
    public String addLink(@RequestParam Long categoryId, @ModelAttribute PlaylistDto linkDTO) {
        linkDTO.setCategoryId(categoryId);
        linkService.save(linkDTO);
        return "redirect:/category/" + categoryId;
    }

    @PostMapping("/link/{id}/delete")
    public String deleteLink(@PathVariable Long id, @RequestParam Long categoryId) {
        linkService.deleteById(id);
        return "redirect:/category/" + categoryId;
    }
}
