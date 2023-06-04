package com.gremio.persistence.entity;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.envers.Audited;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Audited
public class Project  extends BaseEntity {

    private String name;

}