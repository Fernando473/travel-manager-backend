package com.conecta.travelmanager.domain.models;

import com.conecta.travelmanager.domain.enums.RoleName;
import jakarta.persistence.*;
import jakarta.xml.bind.annotation.XmlAttribute;
import lombok.*;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Long roleId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RoleName name;

    @ManyToMany(mappedBy = "roles")
    private Set<UserClient> userClientSet = new HashSet<>();

    @Override
    public String toString() {
        return "Role{" +
                "id=" + roleId +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Role)) return false;
        Role role = (Role) o;
        return Objects.equals(roleId, role.roleId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roleId);
    }
}
