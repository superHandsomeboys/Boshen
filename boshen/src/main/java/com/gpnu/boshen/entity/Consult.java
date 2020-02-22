package com.gpnu.boshen.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

@NoArgsConstructor
@Data
@Accessors(chain = true)
public class Consult implements Serializable {

    private int consult_id;

    private String title;

    private int article_id;

    private int consult_category_id;
}
