package com.anks.tech.ecommerce.Respository;

import com.anks.tech.ecommerce.Entity.FileProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IFileProductRespository extends JpaRepository<FileProduct, String> {
}
