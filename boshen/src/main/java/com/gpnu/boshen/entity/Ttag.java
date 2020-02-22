package com.gpnu.boshen.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

@NoArgsConstructor
@Data
@Accessors(chain = true)
public class Ttag implements Serializable {

    private int tag_id;
    private String tag_name;

    private List<Train> trains;
}
