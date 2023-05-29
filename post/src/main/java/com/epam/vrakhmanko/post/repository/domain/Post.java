package com.epam.vrakhmanko.post.repository.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Post {
    @Min(1)
    @NotNull
    @Id
    private Integer id;

    @Min(1)
    @NotNull
    private Integer authorId;

    private String text;
    private String topic;

    private LocalDate postedAt;
}
