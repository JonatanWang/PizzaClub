package com.pci.workforcemanagement.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
public class FieldErrorMessage {

    @Getter @Setter
    private String field;
    @Getter @Setter
    private String message;
}
