package com.suguna.breeder_revamp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "test_crud_pandi")
@Getter
@Setter
public class EmpModel {
    @Id
    String ID;
    String NAME;
    String AGE;
    String FARM_NAME;
    String EMPCODE;
}
