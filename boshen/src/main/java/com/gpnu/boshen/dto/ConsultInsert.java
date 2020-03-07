package com.gpnu.boshen.dto;

import lombok.Data;

@Data
public class ConsultInsert {
    private Integer consultId;

    private String consultTitle;

    private String content;

    private Integer categoryId;
}
