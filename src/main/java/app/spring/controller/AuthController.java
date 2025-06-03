package app.spring.controller;

import app.spring.model.UserEntity;
import app.spring.repository.ProductRepository;
import app.spring.repository.SectionRepository;
import app.spring.repository.UserRepository;
import app.spring.model.SectionEntity;
import app.spring.model.ProductEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class AuthController {

    private final UserRepository userRepo;
    private final PasswordEncoder passwordEncoder;

    private final SectionRepository sectionRepository;
    private final ProductRepository productRepository;

    // Страница входа


    @GetMapping("/")
    public String loginPage(@RequestParam(value = "error", required = false) String error, Model model) {
        return "login";
    }

    // Страница регистрации
    @GetMapping("/register")
    public String registerPage() {
        return "register";
    }

    // Обработка регистрации
    @PostMapping("/register")
    public String register(@RequestParam String name, @RequestParam String pass, @RequestParam String mail, Model model) {
        boolean userExists = userRepo.findByName(name).isPresent();

        if (userExists) {
            model.addAttribute("error", "Пользователь с таким именем уже существует");
            return "register";
        }

        UserEntity user = new UserEntity();
        user.setName(name);
        user.setPassword(passwordEncoder.encode(pass));
        user.setEmail(mail);
        user.setRole("USER");
        userRepo.resetAutoIncrement();
        userRepo.save(user);

        return "redirect:/login";
    }

    // Страница приветствия после входа
    @GetMapping("/welcome")
    public String userDashboard(HttpSession session, Model model) {
        String userName = (String) session.getAttribute("userName");
        model.addAttribute("userName", userName);
        model.addAttribute("products", productRepository.findByDeletedFalse()); // Получаем только неудаленные продукты
        return "welcome";
    }

    @GetMapping("/admin")
    public String adminDashboard(Model model) {
        model.addAttribute("sections", sectionRepository.findByDeletedFalse()); // Получаем только неудаленные секции
        return "admin";
    }

    // Выход из системы
    @PostMapping("/logout")
    public String logout(HttpSession session) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(null, null, auth);
        }
        session.invalidate();
        return "redirect:/login?logout=true";
    }

    @GetMapping("/edit-section/{id}")
    public String editSectionForm(@PathVariable Integer id, Model model) {
        Optional<SectionEntity> section = sectionRepository.findById(id);
        if (section.isPresent()) {
            model.addAttribute("section", section.get());
            return "edit-section";
        } else {
            return "redirect:/admin";
        }
    }

    @PostMapping("/edit-section/{id}")
    public String editSection(@PathVariable Integer id, @ModelAttribute SectionEntity sectionDetails) {
        return sectionRepository.findById(id).map(section -> {
            section.setSectionName(sectionDetails.getSectionName());
            sectionRepository.save(section);
            return "redirect:/admin";
        }).orElseGet(() -> "redirect:/admin");
    }

    @GetMapping("/add-section")
    public String addSectionForm(Model model) {
        model.addAttribute("section", new SectionEntity());
        return "add-section";
    }

    @PostMapping("/add-section")
    public String addSection(@ModelAttribute SectionEntity section) {
        sectionRepository.save(section);
        return "redirect:/admin";
    }

    @GetMapping("/edit-product/{id}")
    public String editProductForm(@PathVariable Integer id, Model model) {
        Optional<ProductEntity> product = productRepository.findById(id);
        if (product.isPresent()) {
            model.addAttribute("product", product.get());
            return "edit-product";
        } else {
            return "redirect:/welcome";
        }
    }

    @PostMapping("/edit-product/{id}")
    public String editProduct(@PathVariable Integer id, @ModelAttribute ProductEntity productDetails) {
        return productRepository.findById(id).map(product -> {
            product.setProductName(productDetails.getProductName());
            product.setSectionId(productDetails.getSectionId());
            productRepository.save(product);
            return "redirect:/welcome";
        }).orElseGet(() -> "redirect:/welcome");
    }

    @GetMapping("/add-product")
    public String addProductForm(Model model) {
        model.addAttribute("product", new ProductEntity());
        return "add-product";
    }

    @PostMapping("/add-product")
    public String addProduct(@ModelAttribute ProductEntity product, Model model) {
        // Проверяем, существует ли секция с указанным ID
        if (!sectionRepository.existsById(product.getSectionId())) {
            model.addAttribute("error", "Section with ID " + product.getSectionId() + " does not exist.");
            return "add-product"; // Возвращаемся на страницу добавления продукта с сообщением об ошибке
        }

        // Если секция существует, сохраняем продукт
        productRepository.save(product);
        return "redirect:/welcome";
    }

    @GetMapping("/delete-product/{id}")
    public String deleteProduct(@PathVariable Integer id) {
        return productRepository.findById(id).map(product -> {
            product.setDeleted(true); // Устанавливаем флаг удаления
            productRepository.save(product);
            return "redirect:/welcome";
        }).orElseGet(() -> "redirect:/welcome");
    }

    // Удаление секции через установку флага deleted
    @GetMapping("/delete-section/{id}")
    public String deleteSection2(@PathVariable Integer id) {
        return sectionRepository.findById(id).map(section -> {
            section.setDeleted(true); // Устанавливаем флаг удаления
            sectionRepository.save(section);
            return "redirect:/admin";
        }).orElseGet(() -> "redirect:/admin");
    }

}