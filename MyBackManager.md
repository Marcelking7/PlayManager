** // ============= pom.xml ============= **

** <?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
xsi:schemaLocation="http://maven.apache.org/POM/4.0.0
https://maven.apache.org/xsd/maven-4.0.0.xsd">
<modelVersion>4.0.0</modelVersion>
<parent>
<groupId>org.springframework.boot</groupId>
<artifactId>spring-boot-starter-parent</artifactId>
<version>3.2.0</version>
</parent>

    <groupId>bj.csam.playlist.PlayManager</groupId>
    <artifactId>playlist-manager</artifactId>
    <version>1.0.0</version>
    
    <properties>
        <java.version>17</java.version>
    </properties>
    
    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-thymeleaf</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-jpa</artifactId>
        </dependency>
        <dependency>
            <groupId>com.h2database</groupId>
            <artifactId>h2</artifactId>
            <scope>runtime</scope>
        </dependency>
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <optional>true</optional>
        </dependency>
    </dependencies>
</project>**

// ============= src/main/resources/application.properties =============
spring.application.name=Playlist Manager
spring.datasource.url=jdbc:h2:file:./data/playlistdb
spring.datasource.driverClassName=org.h2.Driver
spring.jpa.hibernate.ddl-auto=update
spring.h2.console.enabled=true
```java
// ============= Category.java =============
package bj.csam.playlist.PlayManager.model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Category {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;

    private String name;
    private String description;
    
    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PlaylistLink> links = new ArrayList<>();
}

// ============= PlaylistLink.java =============
package bj.csam.playlist.PlayManager.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Data
public class PlaylistLink {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;

    private String title;
    private String url;
    private String platform; // YouTube, Facebook, etc.
    
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
    
    private LocalDateTime createdAt = LocalDateTime.now();
}

// ============= CategoryRepository.java =============
package bj.csam.playlist.PlayManager.repository;

import bj.csam.playlist.PlayManager.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}

// ============= PlaylistLinkRepository.java =============
package bj.csam.playlist.PlayManager.repository;

import bj.csam.playlist.PlayManager.model.PlaylistLink;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PlaylistLinkRepository extends JpaRepository<PlaylistLink, Long> {
List<PlaylistLink> findByCategoryId(Long categoryId);
}

// ============= CategoryDTO.java =============
package bj.csam.playlist.PlayManager.dto;

import lombok.Data;
import java.util.ArrayList;
import java.util.List;

@Data
public class CategoryDTO {
private Long id;
private String name;
private String description;
private int linksCount;
private List<PlaylistLinkDTO> links = new ArrayList<>();
}

// ============= PlaylistLinkDTO.java =============
package bj.csam.playlist.PlayManager.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class PlaylistLinkDTO {
private Long id;
private String title;
private String url;
private String platform;
private Long categoryId;
private String categoryName;
private LocalDateTime createdAt;
}

// ============= CategoryMapper.java =============
package bj.csam.playlist.PlayManager.mapper;

import bj.csam.playlist.PlayManager.dto.CategoryDTO;
import bj.csam.playlist.PlayManager.model.Category;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CategoryMapper {

    public CategoryDTO toDTO(Category category) {
        if (category == null) return null;
        
        CategoryDTO dto = new CategoryDTO();
        dto.setId(category.getId());
        dto.setName(category.getName());
        dto.setDescription(category.getDescription());
        dto.setLinksCount(category.getLinks() != null ? category.getLinks().size() : 0);
        return dto;
    }
    
    public Category toEntity(CategoryDTO dto) {
        if (dto == null) return null;
        
        Category category = new Category();
        category.setId(dto.getId());
        category.setName(dto.getName());
        category.setDescription(dto.getDescription());
        return category;
    }
    
    public List<CategoryDTO> toDTOList(List<Category> categories) {
        return categories.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }
}

// ============= PlaylistLinkMapper.java =============
package bj.csam.playlist.PlayManager.mapper;

import bj.csam.playlist.PlayManager.dto.PlaylistLinkDTO;
import bj.csam.playlist.PlayManager.model.PlaylistLink;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class PlaylistLinkMapper {

    public PlaylistLinkDTO toDTO(PlaylistLink link) {
        if (link == null) return null;
        
        PlaylistLinkDTO dto = new PlaylistLinkDTO();
        dto.setId(link.getId());
        dto.setTitle(link.getTitle());
        dto.setUrl(link.getUrl());
        dto.setPlatform(link.getPlatform());
        dto.setCreatedAt(link.getCreatedAt());
        
        if (link.getCategory() != null) {
            dto.setCategoryId(link.getCategory().getId());
            dto.setCategoryName(link.getCategory().getName());
        }
        
        return dto;
    }
    
    public PlaylistLink toEntity(PlaylistLinkDTO dto) {
        if (dto == null) return null;
        
        PlaylistLink link = new PlaylistLink();
        link.setId(dto.getId());
        link.setTitle(dto.getTitle());
        link.setUrl(dto.getUrl());
        link.setPlatform(dto.getPlatform());
        link.setCreatedAt(dto.getCreatedAt());
        return link;
    }
    
    public List<PlaylistLinkDTO> toDTOList(List<PlaylistLink> links) {
        return links.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }
}

// ============= CategoryService.java =============
package bj.csam.playlist.PlayManager.service;

import bj.csam.playlist.PlayManager.dto.CategoryDTO;
import bj.csam.playlist.PlayManager.mapper.CategoryMapper;
import bj.csam.playlist.PlayManager.model.Category;
import bj.csam.playlist.PlayManager.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CategoryService {
private final CategoryRepository repository;
private final CategoryMapper mapper;

    public List<CategoryDTO> findAll() {
        return mapper.toDTOList(repository.findAll());
    }
    
    public CategoryDTO findById(Long id) {
        return repository.findById(id)
                .map(mapper::toDTO)
                .orElse(null);
    }
    
    public CategoryDTO save(CategoryDTO categoryDTO) {
        Category category = mapper.toEntity(categoryDTO);
        Category saved = repository.save(category);
        return mapper.toDTO(saved);
    }
    
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}

// ============= PlaylistLinkService.java =============
package bj.csam.playlist.PlayManager.service;

import bj.csam.playlist.PlayManager.dto.PlaylistLinkDTO;
import bj.csam.playlist.PlayManager.mapper.PlaylistLinkMapper;
import bj.csam.playlist.PlayManager.model.Category;
import bj.csam.playlist.PlayManager.model.PlaylistLink;
import bj.csam.playlist.PlayManager.repository.CategoryRepository;
import bj.csam.playlist.PlayManager.repository.PlaylistLinkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class PlaylistLinkService {
private final PlaylistLinkRepository repository;
private final CategoryRepository categoryRepository;
private final PlaylistLinkMapper mapper;

    public List<PlaylistLinkDTO> findByCategoryId(Long categoryId) {
        return mapper.toDTOList(repository.findByCategoryId(categoryId));
    }
    
    public PlaylistLinkDTO save(PlaylistLinkDTO linkDTO) {
        PlaylistLink link = mapper.toEntity(linkDTO);
        
        // Récupérer et assigner la catégorie
        if (linkDTO.getCategoryId() != null) {
            Category category = categoryRepository.findById(linkDTO.getCategoryId())
                    .orElseThrow(() -> new RuntimeException("Catégorie non trouvée"));
            link.setCategory(category);
        }
        
        detectPlatform(link);
        PlaylistLink saved = repository.save(link);
        return mapper.toDTO(saved);
    }
    
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
    
    private void detectPlatform(PlaylistLink link) {
        String url = link.getUrl().toLowerCase();
        if (url.contains("youtube.com") || url.contains("youtu.be")) {
            link.setPlatform("YouTube");
        } else if (url.contains("facebook.com") || url.contains("fb.com")) {
            link.setPlatform("Facebook");
        } else if (url.contains("spotify.com")) {
            link.setPlatform("Spotify");
        } else if (url.contains("soundcloud.com")) {
            link.setPlatform("SoundCloud");
        } else if (url.contains("deezer.com")) {
            link.setPlatform("Deezer");
        } else {
            link.setPlatform("Autre");
        }
    }
}

// ============= MainController.java =============
package bj.csam.playlist.PlayManager.controller;

import bj.csam.playlist.PlayManager.dto.CategoryDTO;
import bj.csam.playlist.PlayManager.dto.PlaylistLinkDTO;
import bj.csam.playlist.PlayManager.service.CategoryService;
import bj.csam.playlist.PlayManager.service.PlaylistLinkService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class MainController {
private final CategoryService categoryService;
private final PlaylistLinkService linkService;

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("categories", categoryService.findAll());
        return "index";
    }
    
    @PostMapping("/category/add")
    public String addCategory(@ModelAttribute CategoryDTO categoryDTO) {
        categoryService.save(categoryDTO);
        return "redirect:/";
    }
    
    @GetMapping("/category/{id}")
    public String viewCategory(@PathVariable Long id, Model model) {
        CategoryDTO category = categoryService.findById(id);
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
    public String addLink(@RequestParam Long categoryId, @ModelAttribute PlaylistLinkDTO linkDTO) {
        linkDTO.setCategoryId(categoryId);
        linkService.save(linkDTO);
        return "redirect:/category/" + categoryId;
    }
    
    @PostMapping("/link/{id}/delete")
    public String deleteLink(@PathVariable Long id, @RequestParam Long categoryId) {
        linkService.deleteById(id);
        return "redirect:/category/" + categoryId;
    }
}```
**

