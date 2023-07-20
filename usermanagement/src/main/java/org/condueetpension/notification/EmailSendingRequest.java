package org.condueetpension.notification;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class EmailSendingRequest {
    private String recipientName;
    private String recipientEmail;
}
