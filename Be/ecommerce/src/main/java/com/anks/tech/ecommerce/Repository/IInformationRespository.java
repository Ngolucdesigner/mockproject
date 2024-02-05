package com.anks.tech.ecommerce.Repository;

import com.anks.tech.ecommerce.Entity.Information;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IInformationRespository extends JpaRepository<Information, Integer> {
}
