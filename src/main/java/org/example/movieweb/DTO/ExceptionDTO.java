package org.example.movieweb.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
//Esta clase la vamos a usar cada vez que querramos mostrar un mensaje en una excepcion personalizada
public class ExceptionDTO {
    private String mesage;
    private int statusCode;
}
