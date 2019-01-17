package org.jeejeejango.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * @author jeejeejango
 * @since 17/01/2019 3:59 PM
 */
@Entity
@Data
public class Team implements Serializable {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @NotEmpty
    @Size(max = 50)
    private String name;

    @Size(max = 255)
    private String description;


    public Team() {
    }


    public Team(@NotEmpty @Size(max = 50) String name, @Size(max = 255) String description) {
        this.name = name;
        this.description = description;
    }


}
