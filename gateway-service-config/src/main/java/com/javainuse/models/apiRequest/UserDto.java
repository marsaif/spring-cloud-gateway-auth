package com.javainuse.models.apiRequest;

import com.javainuse.entities.Role;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    @NotBlank(message = "Username cannot be blank")
    @NotNull
    private String username ;
    @NotBlank(message = "Password cannot be blank")
    @NotNull
    private String password ;

}
