package com.anks.tech.ecommerce.Repository;

import com.anks.tech.ecommerce.Entity.FileProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface IFileProductRepository extends JpaRepository<FileProduct, String> {

    @Modifying
    @Transactional
    @Query("UPDATE FileProduct e SET e.data = NULL WHERE e.id = :id")
    void deleteDataById(String id);
}
