package com.anks.tech.ecommerce.Repository;

import com.anks.tech.ecommerce.Entity.ServicesSetting;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IServicesSettingRespository extends JpaRepository<ServicesSetting, Integer> {
}
