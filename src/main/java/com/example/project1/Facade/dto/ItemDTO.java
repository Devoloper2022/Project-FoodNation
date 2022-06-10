package com.example.project1.Facade.dto;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class ItemDTO {
        private  Long orgID;
        private Integer rate;
}

