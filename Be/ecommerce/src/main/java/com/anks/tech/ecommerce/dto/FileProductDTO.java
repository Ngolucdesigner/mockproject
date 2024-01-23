package com.anks.tech.ecommerce.dto;

import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FileProductDTO {

    private String name;
    private String url;
    private String type;
    private long size;
}
