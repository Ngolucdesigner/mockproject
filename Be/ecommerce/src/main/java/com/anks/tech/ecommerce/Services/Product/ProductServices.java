package com.anks.tech.ecommerce.Services.Product;


import com.anks.tech.ecommerce.Entity.*;
import com.anks.tech.ecommerce.Form.CreateProductForm;
import com.anks.tech.ecommerce.Respository.ICategoryRepository;
import com.anks.tech.ecommerce.Respository.IFileProductRespository;
import com.anks.tech.ecommerce.Respository.IOriginRespository;
import com.anks.tech.ecommerce.Respository.IProductRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class ProductServices implements IProductServices {
    @Autowired
    private IProductRepository productRepository;

    @Autowired
    private ICategoryRepository categoryRepository;

    @Autowired
     private IFileProductRespository fileProductRespository;

    @Autowired
    private IOriginRespository originRespository;
    @Autowired
    ModelMapper modelMapper;

    @Override
    public Page<Product> getAllProduct(Pageable pageable) {


        return productRepository.findAll(pageable);
    }

    @Override
    public Product getProductById(int id) {
        return productRepository.findById(id).get();
    }

    @Override
    public void createProduct(CreateProductForm form) {
        TypeMap typeMap = modelMapper.getTypeMap(CreateProductForm.class, Product.class);
        if (typeMap == null) {
            modelMapper.addMappings(new PropertyMap<CreateProductForm, Product>() {
                @Override
                protected void configure() {
                    skip(destination.getProductId());
                }
            });
        }

        Product product = modelMapper.map(form, Product.class);
        productRepository.save(product);

        Category category = product.getCategory();

        categoryRepository.save(category);

        FileProduct fileProduct = product.getFileProduct();
        fileProductRespository.save(fileProduct);

        Origin origin = product.getOrigin();
        originRespository.save(origin);


    }

    @Override
    public void deleteProduct(int id) {

        productRepository.deleteById(id);

    }
}
