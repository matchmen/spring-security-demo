package com.qm.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * author: liqm
 * 2019-07-23
 */
@Data
@Entity
@Table(name = "sys_permission")
public class Permission implements Serializable {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "PERMISSION_NAME",nullable = false)
    private String permissionName;

    @Column(name = "PERMISSION_CODE",nullable = false)
    private String permissionCode;

    @Column(name = "URL",nullable = false)
    private String url;

    @Column(name = "CREATE_TIME")
    @CreationTimestamp
    private Date createTime;

    @Column(name = "UPDATE_TIME")
    @CreationTimestamp
    private Date updateTime;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "sys_role_permission", joinColumns = {@JoinColumn(name = "PERMISSION_ID")}, inverseJoinColumns = {@JoinColumn(name = "ROLE_ID")})
    private List<Role> roles;

}
