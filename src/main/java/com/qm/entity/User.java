package com.qm.entity;

import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * author: liqm
 * 2019-07-23
 */
@Data
@ToString
@Entity
@Table(name = "sys_user")
public class User implements Serializable {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "USERNAME")
    private String username;

    @Column(name = "NICK_NAME")
    private String nickName;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "CREATE_TIME")
    @CreationTimestamp
    private Date createTime;

    @Column(name = "UPDATE_TIME")
    @CreationTimestamp
    private Date updateTime;

    @OneToOne
    @JoinColumn(name="ROLE_ID",referencedColumnName="id")
    private Role role;
}
