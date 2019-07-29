package com.qm.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.ManyToAny;

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
@Table(name = "sys_role")
public class Role implements Serializable {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "ROLE_CODE")
    private String roleCode;

    @Column(name = "ROLE_NAME")
    private String roleName;

    @Column(name = "CREATE_TIME")
    @CreationTimestamp
    private Date createTime;

    @Column(name = "UPDATE_TIME")
    @CreationTimestamp
    private Date updateTime;

//    @ManyToMany(fetch=FetchType.EAGER)
//    @JoinTable(name="sys_role_permission",joinColumns={@JoinColumn(name="ROLE_ID")},inverseJoinColumns={@JoinColumn(name="PERMISSION_ID")})
//    private List<Permission> permissions;

}
