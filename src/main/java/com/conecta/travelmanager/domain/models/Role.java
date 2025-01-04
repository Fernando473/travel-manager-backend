package com.conecta.travelmanager.domain.models;

import com.conecta.travelmanager.domain.enums.RoleName;
import jakarta.persistence.*;
import jakarta.xml.bind.annotation.XmlAttribute;
import lombok.*;

import java.util.HashSet;
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


}
