package com.anks.tech.ecommerce.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "SERVICES", catalog = "ecommerce")
@Getter
@Setter
@NoArgsConstructor
public class ServicesSetting {

    @Id
    @Column(name = "iconId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "icon", length = 100, nullable = false )
    private String icon;

    @Column(name = "title", length = 100, nullable = false)
    private String title;

    @Column(name = "subTitle", columnDefinition = "TEXT", nullable = false)
    private String subTitle;

    @Column(name = "background", length = 50, nullable = false)
    private String background;

}
