package org.example.movieweb.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
//Esta clase es un DTO de movie el cual se le muestra lo mismo que la entidad original pero sin su id.Esto lo hacemos
// para que no se muestre informacion extra al cliente
public class UserDTO {
    private String email;
    private String name;
    private String lastName;
    private String userName;
    private String password;
}
