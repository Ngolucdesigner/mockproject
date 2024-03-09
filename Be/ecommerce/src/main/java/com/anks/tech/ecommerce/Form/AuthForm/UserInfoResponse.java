package com.anks.tech.ecommerce.Form.AuthForm;

import com.anks.tech.ecommerce.Entity.Enum.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserInfoResponse {
    private int id;
    private String username;
    private String email;
    private String token;
    private List<String> roles;
    private File avatar;
    @Setter
    @Getter
    @NoArgsConstructor
    public  static  class File{
        private String id;
        private String fileName;
        private String fileType;
        private String url;
//        private byte[] data;
    }
}
