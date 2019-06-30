package com.yevhensynii.model;

import com.yevhensynii.annotations.InjectRandomInt;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Getter
@Setter
@Accessors(chain = true)
@EqualsAndHashCode(exclude = {"id"})
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String name;

    @InjectRandomInt(min = 21, max = 49)
    @Column(name = "age")
    private Integer age;
}
