package com.gpnu.boshen.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 培训类别
 */
@NoArgsConstructor
@Data
@Accessors(chain = true)
public class TrainCategory implements Serializable {

    private Integer train_category_id;

    private String train_category_name;

    private String introduce_imageurl;
    private String introduce_content;

    private String function_imageurl;
    private String function_content;

    private String history_imageurl;
    private String history_content;
}
