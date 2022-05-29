package com.sprincsecurity.exceptionHandler;

import lombok.*;

import java.io.Serializable;

@Data
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FieldMessage implements Serializable {

    private String fieldName;
    private String message;
}
