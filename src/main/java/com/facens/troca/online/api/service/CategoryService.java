package com.facens.troca.online.api.service;

import com.facens.troca.online.api.dto.category.CategoryRegisterDTO;
import com.facens.troca.online.api.model.Category;
import com.facens.troca.online.api.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository repository;

    public Category insert(CategoryRegisterDTO inCategory) {
        Category cat = new Category(inCategory);
        cat = repository.save(cat);
        return cat;
    }

    public Category getByIdRaw(Long id) {
        return repository.findById(id).orElseThrow(() ->{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "None Category was found by the informed id");
        });
    }

    public void delete(Long id) {
        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException error) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Provided Category was not found.");
        }
    }
}
