package app.spring.controller;

import app.spring.model.ProductEntity;
import app.spring.model.SectionEntity;
import app.spring.model.UserEntity;
import app.spring.repository.ProductRepository;
import app.spring.repository.SectionRepository;
import app.spring.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ApiController {

    private final ProductRepository productRepository;
    private final SectionRepository sectionRepository;
    private final UserRepository userRepository;

    // Create a new product
    @PostMapping("/products")
    public ProductEntity createProduct(@RequestBody ProductEntity product) {
        return productRepository.save(product);
    }

    // Get all products
    @GetMapping("/products")
    public List<ProductEntity> getAllProducts() {
        return productRepository.findAll();
    }

    // Get a single product by ID
    @GetMapping("/products/{id}")
    public ResponseEntity<ProductEntity> getProductById(@PathVariable Integer id) {
        Optional<ProductEntity> product = productRepository.findById(id);
        return product.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Update an existing product
    @PutMapping("/products/{id}")
    public ResponseEntity<ProductEntity> updateProduct(@PathVariable Integer id, @RequestBody ProductEntity productDetails) {
        return productRepository.findById(id).map(product -> {
            product.setProductName(productDetails.getProductName());
            product.setSectionId(productDetails.getSectionId());
            return ResponseEntity.ok(productRepository.save(product));
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Delete a product
    @DeleteMapping("/products/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Integer id) {
        return productRepository.findById(id).map(product -> {
            productRepository.delete(product);
            return ResponseEntity.ok().<Void>build();
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Create a new section
    @PostMapping("/sections")
    public SectionEntity createSection(@RequestBody SectionEntity section) {
        return sectionRepository.save(section);
    }

    // Get all sections
    @GetMapping("/sections")
    public List<SectionEntity> getAllSections() {
        return sectionRepository.findAll();
    }

    // Get a single section by ID
    @GetMapping("/sections/{id}")
    public ResponseEntity<SectionEntity> getSectionById(@PathVariable Integer id) {
        Optional<SectionEntity> section = sectionRepository.findById(id);
        return section.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Update an existing section
    @PutMapping("/sections/{id}")
    public ResponseEntity<SectionEntity> updateSection(@PathVariable Integer id, @RequestBody SectionEntity sectionDetails) {
        return sectionRepository.findById(id).map(section -> {
            section.setSectionName(sectionDetails.getSectionName());
            return ResponseEntity.ok(sectionRepository.save(section));
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Delete a section
    @DeleteMapping("/sections/{id}")
    public ResponseEntity<Void> deleteSection(@PathVariable Integer id) {
        return sectionRepository.findById(id).map(section -> {
            sectionRepository.delete(section);
            return ResponseEntity.ok().<Void>build();
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Create a new user
    @PostMapping("/users")
    public UserEntity createUser(@RequestBody UserEntity user) {
        return userRepository.save(user);
    }

    // Get all users
    @GetMapping("/users")
    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }

    // Get a single user by ID
    @GetMapping("/users/{id}")
    public ResponseEntity<UserEntity> getUserById(@PathVariable Integer id) {
        Optional<UserEntity> user = userRepository.findById(id);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Update an existing user
    @PutMapping("/users/{id}")
    public ResponseEntity<UserEntity> updateUser(@PathVariable Integer id, @RequestBody UserEntity userDetails) {
        return userRepository.findById(id).map(user -> {
            user.setName(userDetails.getName());
            user.setPassword(userDetails.getPassword());
            user.setEmail(userDetails.getEmail());
            return ResponseEntity.ok(userRepository.save(user));
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Delete a user
    @DeleteMapping("/users/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Integer id) {
        return userRepository.findById(id).map(user -> {
            userRepository.delete(user);
            return ResponseEntity.ok().<Void>build();
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }




}
