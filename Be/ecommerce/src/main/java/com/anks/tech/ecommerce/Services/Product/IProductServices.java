package com.anks.tech.ecommerce.Services.Product;

import com.anks.tech.ecommerce.Entity.Product;
import com.anks.tech.ecommerce.Form.CreateProductForm;
import com.anks.tech.ecommerce.Form.UpdateProductForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IProductServices {

    Page<Product> getAllProduct(Pageable pageable);

    Product getProductById(int id);
    void createProduct(CreateProductForm form);
    void deleteProduct(int id);

    void updateProduct(UpdateProductForm form);

}
