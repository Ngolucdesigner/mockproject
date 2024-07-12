package com.anks.tech.ecommerce.Form.AuthForm;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FileAccount {
    private String id;
    private String fileName;
    private String fileType;
    private byte[] data;
}
