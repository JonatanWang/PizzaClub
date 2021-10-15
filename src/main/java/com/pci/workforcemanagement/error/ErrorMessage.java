package com.pci.workforcemanagement.error;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
public class ErrorMessage {
    @Getter
    @Setter
    private String status;
    @Getter
    @Setter
    private String message;

}
