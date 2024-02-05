package com.anks.tech.ecommerce.Services.Origin;

import com.anks.tech.ecommerce.Entity.Origin;
import com.anks.tech.ecommerce.Form.ProductForm.CreateOriginForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IOriginServices {

    Page<Origin> getAllOrigin(Pageable pageable);
    void createOrigin(CreateOriginForm form);
}
