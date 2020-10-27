package com.funlab.quickpoll.dto.response;

import java.util.Set;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PollResponseDTO {
    private Long id;
    private String question;
    private Set<OptionResponseDTO> options;
}
