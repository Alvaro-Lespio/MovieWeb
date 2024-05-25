package org.example.movieweb.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
//Esta clase es un DTO de movie el cual solamente muestra el usuario y la contraseña. Esto lo hacemos para que no
// se muestre informacion extra al cliente
public class UserLoginDto {
    private String userName;
    private String password;
}
