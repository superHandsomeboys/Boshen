package com.gpnu.boshen.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 培训的图片，
 */
@NoArgsConstructor
@Data
@Accessors(chain = true)
public class TrainImage implements Serializable {

    private Integer train_image_id;

    private Integer train_id;

    private String image_url;

    private Train train;
}
