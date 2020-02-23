package com.gpnu.boshen.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.data.relational.core.sql.In;

import java.io.Serializable;
import java.util.List;

/**
 * 培训
 */
@NoArgsConstructor
@Data
@Accessors(chain = true)
public class Train implements Serializable {

    private Integer train_id;

    private String title;

    private String videourl;

    private Integer author_id;

    private Integer category_id;

    private List<TrainImage> trainImages;

    private List<Ttag> ttags;
}
