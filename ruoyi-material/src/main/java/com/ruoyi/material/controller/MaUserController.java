package com.ruoyi.material.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.common.constant.UserConstants;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.material.domain.MaUser;
import com.ruoyi.system.service.IMaUserService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 用户Controller
 * 
 * @author ruoyi
 * @date 2023-01-17
 */
@RestController
@RequestMapping("/ma/user")
public class MaUserController extends BaseController
{
    @Autowired
    private IMaUserService maUserService;

    /**
     * 查询用户列表
     */
    @PreAuthorize("@ss.hasPermi('ma:user:list')")
    @GetMapping("/list")
    public TableDataInfo list(MaUser maUser)
    {
        startPage();
        List<MaUser> list = maUserService.selectMaUserList(maUser);
        return getDataTable(list);
    }

    /**
     * 导出用户列表
     */
    @PreAuthorize("@ss.hasPermi('ma:user:export')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, MaUser maUser)
    {
        List<MaUser> list = maUserService.selectMaUserList(maUser);
        ExcelUtil<MaUser> util = new ExcelUtil<MaUser>(MaUser.class);
        util.exportExcel(response, list, "【请填写功能名称】数据");
    }

    /**
     * 获取用户详细信息
     */
    @PreAuthorize("@ss.hasPermi('ma:user:query')")
    @GetMapping(value = "/{userid}")
    public AjaxResult getInfo(@PathVariable("userid") Long userid)
    {
        return success(maUserService.selectMaUserByUserid(userid));
    }

    /**
     * 新增用户
     */
    @PreAuthorize("@ss.hasPermi('ma:user:add')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@Validated @RequestBody MaUser maUser)
    {
        if (UserConstants.NOT_UNIQUE.equals(maUserService.checkUserNameUnique(maUser)))
        {
            return error("新增用户'" + maUser.getUsername() + "'失败，登录账号已存在");
        }
        else if (StringUtils.isNotEmpty(maUser.getPhone())
                && UserConstants.NOT_UNIQUE.equals(maUserService.checkPhoneUnique(maUser)))
        {
            return error("新增用户'" + maUser.getUsername() + "'失败，手机号码已存在");
        }
        else if (StringUtils.isNotEmpty(maUser.getEmail())
                && UserConstants.NOT_UNIQUE.equals(maUserService.checkEmailUnique(maUser)))
        {
            return error("新增用户'" + maUser.getUsername() + "'失败，邮箱账号已存在");
        }
        maUser.setCreateBy(getUsername());
        maUser.setPassword(SecurityUtils.encryptPassword(maUser.getPassword()));
        return toAjax(maUserService.insertMaUser(maUser));
    }

    /**
     * 修改用户
     */
    @PreAuthorize("@ss.hasPermi('ma:user:edit')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody MaUser maUser)
    {
       // maUserService.checkUserAllowed(maUser);
        //maUserService.checkUserDataScope(maUser.getUserid());
        if (UserConstants.NOT_UNIQUE.equals(maUserService.checkUserNameUnique(maUser)))
        {
            return error("修改用户'" + maUser.getUsername() + "'失败，登录账号已存在");
        }
        else if (StringUtils.isNotEmpty(maUser.getPhone())
                && UserConstants.NOT_UNIQUE.equals(maUserService.checkPhoneUnique(maUser)))
        {
            return error("修改用户'" + maUser.getUsername() + "'失败，手机号码已存在");
        }
        else if (StringUtils.isNotEmpty(maUser.getEmail())
                && UserConstants.NOT_UNIQUE.equals(maUserService.checkEmailUnique(maUser)))
        {
            return error("修改用户'" + maUser.getUsername() + "'失败，邮箱账号已存在");
        }
        maUser.setUpdateBy(getUsername());
        return toAjax(maUserService.updateMaUser(maUser));
    }

    /**
     * 删除用户
     */
    @PreAuthorize("@ss.hasPermi('ma:user:remove')")
    @Log(title = "删除用户", businessType = BusinessType.DELETE)
	@DeleteMapping("/{userids}")
    public AjaxResult remove(@PathVariable Long[] userids)
    {
        return toAjax(maUserService.deleteMaUserByUserids(userids));
    }
    /**
     * 重置密码
     */
    @PreAuthorize("@ss.hasPermi('ma:user:resetPwd')")
    @Log(title = "用户管理", businessType = BusinessType.UPDATE)
    @PutMapping("/resetPwd")
    public AjaxResult resetPwd(@RequestBody MaUser user)
    {
        //maUserService.checkUserAllowed(user);
        //maUserService.checkUserDataScope(user.getUserId());
        user.setPassword(SecurityUtils.encryptPassword(user.getPassword()));
        user.setUpdateBy(getUsername());
        return toAjax(maUserService.resetPwd(user));
    }
}
