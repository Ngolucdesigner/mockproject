package com.anks.tech.ecommerce.Services.Product;

import com.anks.tech.ecommerce.Entity.Product;
import com.anks.tech.ecommerce.Form.ProductForm.CreateProductForm;
import com.anks.tech.ecommerce.Form.ProductForm.ProductFilterForm;
import com.anks.tech.ecommerce.Form.ProductForm.UpdateProductForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IProductServices {

    Page<Product> getAllProduct(Pageable pageable, ProductFilterForm productFilterForm);

    Product getProductById(int id);
    Product getProductByProductCode(String productCode);
    void createProduct(CreateProductForm form);
    void deleteProduct(int id);

    void updateProduct(UpdateProductForm form);

}
