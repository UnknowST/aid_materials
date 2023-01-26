package com.ruoyi.material.mapper;

import java.util.List;
import com.ruoyi.material.domain.MaUser;
import org.apache.ibatis.annotations.Param;
import org.springframework.security.core.parameters.P;

/**
 * 【请填写功能名称】Mapper接口
 * 
 * @author ruoyi
 * @date 2023-01-17
 */
public interface MaUserMapper 
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
     * 删除【请填写功能名称】
     * 
     * @param userid 【请填写功能名称】主键
     * @return 结果
     */
    public int deleteMaUserByUserid(Long userid);

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param userids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteMaUserByUserids(Long[] userids);

    /**
     * 检查用户名是否唯一
     * @param username
     * @return
     */
    public MaUser checkUserNameUnique(String username);

    /**
     * 检查手机号 是否唯一
     * @param phone
     * @return
     */
    public MaUser checkPhoneUnique(String phone);

    /**
     * 检查手机号是否唯一
     * @param email
     * @return
     */
    public MaUser checkEmailUnique(String email);

    /**
     * 重置用户密码
     * @param username
     * @param password
     * @return
     */
    public int resetUserPwd(@Param("username")String username, @Param("password")String password);
}
