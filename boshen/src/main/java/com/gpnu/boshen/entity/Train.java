package com.gpnu.boshen.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

@NoArgsConstructor
@Data
@Accessors(chain = true)
public class Train implements Serializable {

    private int train_id;
    private String title;
    private String videourl;
    private int author_id;
    private int category_id;

    private List<TrainImage> trainImages;

    private List<Ttag> ttags;
}
