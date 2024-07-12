package com.anks.tech.ecommerce.Controller;

import com.anks.tech.ecommerce.DTO.ServicesSettingDTO.ServicesSettingDTO;
import com.anks.tech.ecommerce.Entity.ServicesSetting;
import com.anks.tech.ecommerce.Form.ServicesSettingForm.ServicesSettingForm;
import com.anks.tech.ecommerce.Services.ServicesSetting.IServicesSettingServices;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/v1/ServicesSetting")
@ResponseBody
public class ServicesSettingController {

    @Autowired
    private IServicesSettingServices servicesSettingServices;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/setting")
    public ResponseEntity<?> getAllServicesSetting(){

        List<ServicesSetting> servicesSettings = servicesSettingServices.getServicesSetting();

        List<ServicesSettingDTO> servicesSettingDTOS = servicesSettings.stream()
                .map(servicesSetting -> modelMapper.map(servicesSetting, ServicesSettingDTO.class)).collect(Collectors.toList());
        return ResponseEntity.ok().body(servicesSettingDTOS);
    }

    @PostMapping ("createSetting")
    ResponseEntity<?> createSettingServices(@RequestBody ServicesSettingForm form){

        servicesSettingServices.createSetting(form);
        return ResponseEntity.accepted().body("create successfully!");
    }

}
