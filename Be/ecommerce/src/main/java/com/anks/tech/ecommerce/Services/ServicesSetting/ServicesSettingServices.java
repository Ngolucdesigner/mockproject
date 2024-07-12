package com.anks.tech.ecommerce.Services.ServicesSetting;

import com.anks.tech.ecommerce.Entity.ServicesSetting;
import com.anks.tech.ecommerce.Form.ServicesSettingForm.ServicesSettingForm;
import com.anks.tech.ecommerce.Repository.IServicesSettingRespository;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional(rollbackFor = Exception.class)
public class ServicesSettingServices implements IServicesSettingServices {

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private IServicesSettingRespository servicesSettingRespository;

    @Override
    public void createSetting(ServicesSettingForm form) {
        TypeMap typeMap = modelMapper.getTypeMap(ServicesSettingForm.class, ServicesSetting.class);

        if (typeMap == null) {
            modelMapper.addMappings(new PropertyMap<ServicesSettingForm, ServicesSetting>() {

                                        @Override
                                        protected void configure() {
                                            skip(destination.getId());
                                        }
                                    }

            );
        }

        ServicesSetting servicesSetting = modelMapper.map(form, ServicesSetting.class);

        servicesSettingRespository.save(servicesSetting);

    }

    @Override
    public List<ServicesSetting> getServicesSetting() {
        return servicesSettingRespository.findAll();
    }
}
