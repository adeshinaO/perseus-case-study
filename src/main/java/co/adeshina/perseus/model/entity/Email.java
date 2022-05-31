package co.adeshina.perseus.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Data;

@Data
@Table(name = "emails")
public class Email extends BaseEntity {

    @Column(name = "mail")
    private String mail;
}
