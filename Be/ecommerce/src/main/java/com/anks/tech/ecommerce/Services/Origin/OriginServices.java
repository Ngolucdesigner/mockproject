package com.anks.tech.ecommerce.Services.Origin;

import com.anks.tech.ecommerce.Entity.Origin;
import com.anks.tech.ecommerce.Entity.Review;
import com.anks.tech.ecommerce.Form.CreateOriginForm;
import com.anks.tech.ecommerce.Form.ReviewForm;
import com.anks.tech.ecommerce.Respository.IOriginRespository;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class OriginServices implements IOriginServices{
    @Autowired
    private  ModelMapper modelMapper;
    @Autowired
    private  IOriginRespository originRespository;
    @Override
    public Page<Origin> getAllOrigin(Pageable pageable) {
        return originRespository.findAll(pageable);
    }

    @Override
    public void createOrigin(CreateOriginForm form) {
        TypeMap typeMap = modelMapper.getTypeMap(CreateOriginForm.class,Origin.class);

        if(typeMap ==null){
            modelMapper.addMappings(new PropertyMap<CreateOriginForm, Origin>() {
                @Override
                protected void configure() {
                    skip(destination.getId());
                }
            });
        }
        Origin origin = modelMapper.map(form, Origin.class);
        originRespository.save(origin);
    }
}
