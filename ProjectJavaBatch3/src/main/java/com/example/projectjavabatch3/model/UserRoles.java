package com.example.projectjavabatch3.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "user_roles", schema = "dbo", catalog = "DoAn")
public class UserRoles {
    @Basic
    @Column(name = "user_id")
    private Integer userId;
    @Basic
    @Column(name = "role_id")
    private Integer roleId;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserRoles userRoles = (UserRoles) o;
        return id == userRoles.id && Objects.equals(userId, userRoles.userId) && Objects.equals(roleId, userRoles.roleId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, roleId, id);
    }
}
