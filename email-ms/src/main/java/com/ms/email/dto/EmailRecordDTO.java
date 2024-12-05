package com.ms.email.dto;

import java.util.UUID;

public record EmailRecordDTO(UUID userId,
							String emailto,
							String subject,
							String text) {

}
