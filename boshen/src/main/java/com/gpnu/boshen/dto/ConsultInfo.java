package com.gpnu.boshen.dto;

import lombok.Data;

@Data
public class ConsultInfo {
    private int consult_id;

    private String title;

    private String  content;

    private String consult_category_name;
}
