package com.example.demo.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class CompanyMemberDTO {
    private Integer memberId;

    private String memberName;

    private String position;

    private String introduce;

    private MultipartFile avatar;

}
