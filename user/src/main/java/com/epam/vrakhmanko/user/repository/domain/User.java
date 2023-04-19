package com.epam.vrakhmanko.user.repository.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Min(1)
    @NotNull
    @Id
    private Integer id;
    @NotNull
    @Column(nullable = false)
    private String username;
    @Column(nullable = false, columnDefinition = "integer default '0'")
    private Integer amountOfPosts = 0;
}