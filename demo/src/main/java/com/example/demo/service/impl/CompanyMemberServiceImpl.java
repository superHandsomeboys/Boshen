package com.example.demo.service.impl;

import com.example.demo.dto.CompanyMemberDTO;
import com.example.demo.dto.FileInfo;
import com.example.demo.dynamic.Data;
import com.example.demo.entity.CompanyMember;
import com.example.demo.mapper.CompanyMemberMapper;
import com.example.demo.service.CompanyMemberService;
import com.example.demo.util.FileUploadUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.util.List;

@Service
public class CompanyMemberServiceImpl implements CompanyMemberService{

    @Autowired
    CompanyMemberMapper companyMemberMapper;

    @Override
    @Transactional
    public int addMember(CompanyMemberDTO companyMemberDTO) {
        File dest = new File("");
        try {
            //1.添加头像
            FileInfo fileInfo = FileUploadUtil.systemImage(companyMemberDTO.getAvatar());
            dest =new File(fileInfo.getFrontPath() + fileInfo.getBackPath());
            //2.添加成员类
            CompanyMember companyMember = new CompanyMember();
            BeanUtils.copyProperties(companyMemberDTO,companyMember);
            companyMember.setAvatar(fileInfo.getBackPath());
            return companyMemberMapper.insertCompanyMember(companyMember);
        }catch (Exception e){
            dest.delete();
            throw new RuntimeException("公司成员类添加错误：" + e.getMessage());
        }

    }

    @Override
    @Transactional
    public int updateMember(CompanyMemberDTO companyMemberDTO) {
        File dest = new File("");
        try {
            String oldAvatar = companyMemberMapper.getById(companyMemberDTO.getMemberId()).getAvatar();
                System.out.println("----------");
            //1.上传头像
            FileInfo fileInfo = FileUploadUtil.systemImage(companyMemberDTO.getAvatar());
                System.out.println(fileInfo);
            dest =new File(fileInfo.getFrontPath() + fileInfo.getBackPath());
            //2.改成员类
            CompanyMember companyMember = new CompanyMember();
            BeanUtils.copyProperties(companyMemberDTO,companyMember);
            companyMember.setAvatar(fileInfo.getBackPath());
                System.out.println(companyMember);
            int result = companyMemberMapper.updateCompanyMember(companyMember);
            //3.删除头像,放最后是因为删了就不能拿回来，确保头像删了事务一定通过
            File oldAvatarFile = new File(Data.UPLOAD_IMAGE_PATH + oldAvatar);
            oldAvatarFile.delete();
            return result;
        }catch (Exception e){
            dest.delete();
            throw new RuntimeException("公司成员类添加错误：" + e.getMessage());
        }
    }

    @Override
    @Transactional
    public int deleteMember(int id) {
        try {
            String avatar = companyMemberMapper.getById(id).getAvatar();
            //1.删除成员类
            int result = companyMemberMapper.deleteCompanyMember(id);
            //2.删除头像
            File avatarFile = new File(Data.UPLOAD_IMAGE_PATH + avatar);
            avatarFile.delete();
            return result;
        }catch (Exception e){
            throw new RuntimeException("公司成员类添加错误：" + e.getMessage());
        }

    }

    @Override
    public CompanyMember findMemberById(int id) {
        return companyMemberMapper.getById(id);
    }

    @Override
    public List<CompanyMember> findAll() {
        return companyMemberMapper.getAllCompanyMember();
    }
}
