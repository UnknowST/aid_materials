package com.ruoyi.system.service;

import java.util.List;

import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.material.domain.MaUser;

/**
 * 【请填写功能名称】Service接口
 * 
 * @author ruoyi
 * @date 2023-01-17
 */
public interface IMaUserService 
{
    /**
     * 查询【请填写功能名称】
     * 
     * @param userid 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    public MaUser selectMaUserByUserid(Long userid);

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param maUser 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<MaUser> selectMaUserList(MaUser maUser);

    /**
     * 新增【请填写功能名称】
     * 
     * @param maUser 【请填写功能名称】
     * @return 结果
     */
    public int insertMaUser(MaUser maUser);

    /**
     * 修改【请填写功能名称】
     * 
     * @param maUser 【请填写功能名称】
     * @return 结果
     */
    public int updateMaUser(MaUser maUser);

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param userids 需要删除的【请填写功能名称】主键集合
     * @return 结果
     */
    public int deleteMaUserByUserids(Long[] userids);

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param userid 【请填写功能名称】主键
     * @return 结果
     */
    public int deleteMaUserByUserid(Long userid);

    /**
     * 检查用户名是否唯一
     * @param username
     * @return
     */
    public String checkUserNameUnique(MaUser user);

    /**
     * 检查手机号 是否唯一
     * @param phone
     * @return
     */
    public String  checkPhoneUnique(MaUser user);

    /**
     * 检查手机号是否唯一
     * @param email
     * @return
     */
    public String checkEmailUnique(MaUser user);

    /**
     * 重置用户密码
     *
     * @param user 用户信息
     * @return 结果
     */
    public int resetPwd(MaUser mauser);

    /**
     * 重置用户密码
     *
     * @param username 用户名
     * @param password 密码
     * @return 结果
     */
    public int resetUserPwd(String username, String password);
}
