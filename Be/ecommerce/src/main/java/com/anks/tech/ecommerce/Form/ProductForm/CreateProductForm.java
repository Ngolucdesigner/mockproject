package com.anks.tech.ecommerce.Form.ProductForm;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CreateProductForm {

    @NotBlank(message = "product name not null")
    private String productName;
    private String imgUrl;
    @NotBlank(message = "product name not null")
    private double price;

    private double priceSales;
    @NotBlank(message = "short description name not null")
    private String shortDesc;
    @NotBlank(message = "description not null")
    private String description;
    @NotBlank(message = "category not null")
    private Category category;

    @NotBlank(message = "image product name not null")
    private FileProduct fileProduct;

    private Origin origin;
    private InformationForm informationForm;
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static  class Category {
        private String category;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class FileProduct{
        private String id;
        private String fileName;
        private String fileType;
        private byte[] data;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    public static class Origin{
        private String manufacturer;
        private String madeIn;
        private String guarantee;
    }


    public static class ProductFilterForm {
    }
}
