package com.gpnu.boshen.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

@NoArgsConstructor
@Data
@Accessors(chain = true)
public class TrainImage implements Serializable {

    private int train_image_id;
    private int train_id;
    private String image_url;

    private Train train;
}
