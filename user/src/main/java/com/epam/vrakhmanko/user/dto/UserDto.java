package com.epam.vrakhmanko.user.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    @Min(1)
    @NotNull
    private Integer id;

    @NotNull
    private String username;

    @Min(0)
    private Integer amountOfPosts = 0;
}
