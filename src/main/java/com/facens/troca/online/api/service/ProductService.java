package com.facens.troca.online.api.service;

import com.facens.troca.online.api.dto.product.ProductOutDTO;
import com.facens.troca.online.api.dto.product.ProductRegisterDTO;
import com.facens.troca.online.api.dto.user.UserOutDTO;
import com.facens.troca.online.api.model.Category;
import com.facens.troca.online.api.model.Product;
import com.facens.troca.online.api.model.User;
import com.facens.troca.online.api.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ProductService {
    @Autowired private ProductRepository repository;
    @Autowired private CategoryService categoryService;
    @Autowired private UserService userService;

    public ProductOutDTO insert(ProductRegisterDTO inProduct) {
        User user = userService.getByIdRaw(inProduct.getIdUser());
        Category cat = categoryService.getByIdRaw(inProduct.getIdCategory());

        Product prod = new Product(user,cat,inProduct);
        prod = repository.save(prod);
        return new ProductOutDTO(new UserOutDTO(user),cat,prod);
    }

    public ProductOutDTO getById(Long id) {
        Product prod= repository.findById(id).orElseThrow(() ->{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "None Product was found by the informed id");
        });
        User user = prod.getUser();
        Category cat = prod.getCategory();
        return new ProductOutDTO(new UserOutDTO(user),cat,prod);
    }

    public Product getByIdRaw(Long id) {
        return repository.findById(id).orElseThrow(() ->{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "None Product was found by the informed id");
        });
    }

    public void delete(Long id) {
        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException error) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Provided Product was not found.");
        }
    }
}
