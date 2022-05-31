package co.adeshina.perseus.model.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "phone_numbers")
public class PhoneNumber extends BaseEntity {
    private String phoneNumber;
}
