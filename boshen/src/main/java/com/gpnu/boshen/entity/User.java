package com.gpnu.boshen.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.sql.Date;

@NoArgsConstructor
@Data
@Accessors(chain = true)
public class User implements Serializable {

    private int user_id;
    private String user_name;
    private String password;
    private String email;
    private String phone;
    private String avatar;
    private int user_type;
    private Date create_time;
}
