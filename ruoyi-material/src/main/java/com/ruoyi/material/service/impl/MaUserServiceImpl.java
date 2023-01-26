package com.ruoyi.material.service.impl;

import java.util.List;

import com.ruoyi.common.constant.UserConstants;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import com.ruoyi.material.mapper.MaUserMapper;
import com.ruoyi.material.domain.MaUser;
import com.ruoyi.system.service.IMaUserService;

/**
 * 【请填写功能名称】Service业务层处理
 *
 * @author ruoyi
 * @date 2023-01-17
 */
@Service
public class MaUserServiceImpl implements IMaUserService {
    @Autowired
    private MaUserMapper maUserMapper;

    /**
     * 查询【请填写功能名称】
     *
     * @param userid 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    @Override
    public MaUser selectMaUserByUserid(Long userid) {
        return maUserMapper.selectMaUserByUserid(userid);
    }

    /**
     * 查询【请填写功能名称】列表
     *
     * @param maUser 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<MaUser> selectMaUserList(MaUser maUser) {
        return maUserMapper.selectMaUserList(maUser);
    }

    /**
     * 新增【请填写功能名称】
     *
     * @param maUser 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int insertMaUser(MaUser maUser) {
        maUser.setCreateTime(DateUtils.getNowDate());
        return maUserMapper.insertMaUser(maUser);
    }

    /**
     * 修改【请填写功能名称】
     *
     * @param maUser 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updateMaUser(MaUser maUser) {
        maUser.setUpdateTime(DateUtils.getNowDate());
        return maUserMapper.updateMaUser(maUser);
    }

    /**
     * 批量删除【请填写功能名称】
     *
     * @param userids 需要删除的【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteMaUserByUserids(Long[] userids) {
        return maUserMapper.deleteMaUserByUserids(userids);
    }

    /**
     * 删除【请填写功能名称】信息
     *
     * @param userid 【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteMaUserByUserid(Long userid) {
        return maUserMapper.deleteMaUserByUserid(userid);
    }

    @Override
    public String checkUserNameUnique(MaUser user) {
        Long userid = StringUtils.isNull(user.getUserid()) ? -1L : user.getUserid();
        MaUser info = maUserMapper.checkUserNameUnique(user.getUsername());
        if (StringUtils.isNotNull(info) && info.getUserid().longValue() != userid.longValue()) {
            return UserConstants.NOT_UNIQUE;
        }
        return UserConstants.UNIQUE;
    }

    @Override
    public String checkPhoneUnique(MaUser user) {
        Long userId = StringUtils.isNull(user.getUserid()) ? -1L : user.getUserid();
        MaUser info = maUserMapper.checkPhoneUnique(user.getPhone());
        if (StringUtils.isNotNull(info) && info.getUserid().longValue() != userId.longValue()) {
            return UserConstants.NOT_UNIQUE;
        }
        return UserConstants.UNIQUE;
    }

    @Override
    public String checkEmailUnique(MaUser user) {
        Long userId = StringUtils.isNull(user.getUserid()) ? -1L : user.getUserid();
        MaUser info = maUserMapper.checkEmailUnique(user.getEmail());
        if (StringUtils.isNotNull(info) && info.getUserid().longValue() != userId.longValue()) {
            return UserConstants.NOT_UNIQUE;
        }
        return UserConstants.UNIQUE;
    }

    @Override
    public int resetPwd(MaUser mauser) {
        return maUserMapper.updateMaUser(mauser);
    }

    @Override
    public int resetUserPwd(String username, String password) {
        return maUserMapper.resetUserPwd(username,password);
    }


}
