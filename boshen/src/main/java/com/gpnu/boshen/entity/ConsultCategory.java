package com.gpnu.boshen.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 咨询类别
 */
@NoArgsConstructor
@Data
@Accessors(chain = true)
public class ConsultCategory implements Serializable {
    private Integer consultCategoryId;

    private String consultCategoryName;

    private String consultCategoryIntroduce;
}
