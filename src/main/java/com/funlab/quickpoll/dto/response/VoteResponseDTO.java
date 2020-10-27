package com.funlab.quickpoll.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VoteResponseDTO {
    private Long id;
    private OptionResponseDTO option;
}
