package com.example.backend.util.jpa;

import java.util.Objects;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import com.example.backend.util.common.Identifiable;

import lombok.Data;

@Data
@MappedSuperclass
public class JPAEntity implements Identifiable {
    @Id
    @GeneratedValue
    @Column(columnDefinition = "UUID")
    private UUID id;

    @Override
    public final boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof JPAEntity)) {
            return false;
        }
        JPAEntity jpaEntity = (JPAEntity) o;
        return Objects.equals(getId(), jpaEntity.getId());
    }

    @Override
    public final int hashCode() {
        return Objects.hash(getId());
    }
}
