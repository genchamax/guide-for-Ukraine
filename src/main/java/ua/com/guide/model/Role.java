package ua.com.guide.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Max on 07.09.2016.
 */
@Entity
@Table(name = "ROLE")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Integer roleId;

    @Column(name = "ROLE_NAME")
    private String roleName;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "role")
    @JsonIgnore
    private List<User> users;

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    @Override
    public int hashCode() {
        return roleId.hashCode() + roleName.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Role))
            return false;

        Role aRole = (Role) obj;

        return aRole.hashCode() == this.hashCode() && this.roleId.equals(aRole.getRoleId());
    }
}
