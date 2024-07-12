package com.anks.tech.ecommerce.Services.ServicesSetting;

import com.anks.tech.ecommerce.Entity.ServicesSetting;
import com.anks.tech.ecommerce.Form.ServicesSettingForm.ServicesSettingForm;

import java.util.List;
import java.util.Optional;

public interface IServicesSettingServices {

    void createSetting(ServicesSettingForm form);

    List<ServicesSetting> getServicesSetting();

}
