package com.ruoyi.material.controller;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.utils.SecurityUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
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
import com.ruoyi.material.domain.MaHelped;
import com.ruoyi.material.service.IMaHelpedService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 帮助记录Controller
 *
 * @author ruoyi
 * @date 2023-01-27
 */
@RestController
@RequestMapping("/ma/helped")
public class MaHelpedController extends BaseController {
    @Autowired
    private IMaHelpedService maHelpedService;

    /**
     * 查询帮助记录列表
     */
    @PreAuthorize("@ss.hasPermi('ma:helped:list')")
    @GetMapping("/list")
    public TableDataInfo list(MaHelped maHelped) {
        // 系统管理员和超级管理员时 返回所有数据

        startPage();
        List<MaHelped> list = new ArrayList<>();
        LoginUser loginUser = getLoginUser();

        if (loginUser.isMaAdmin() || loginUser.isAdmin()) {
            list = maHelpedService.selectMaHelpedList(maHelped);
        } else {
            //返回审核通过的求助
            maHelped.setStatus("1");
            list = maHelpedService.selectMaHelpedList(maHelped);
        }
        return getDataTable(list);
    }

    /**
     * 查询登录用户的申请物资记录
     */
    @PreAuthorize("@ss.hasPermi('ma:helped:mylist')")
    @GetMapping("/mylist")
    public TableDataInfo mylist(MaHelped maHelped) {

        startPage();
        List<MaHelped> list = new ArrayList<>();

        maHelped.setHusername(SecurityUtils.getUsername());
        list = maHelpedService.selectMaHelpedList(maHelped);

        return getDataTable(list);
    }

    /**
     * 导出帮助记录列表
     */
    @PreAuthorize("@ss.hasPermi('ma:helped:export')")
    @Log(title = "导出帮助记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, MaHelped maHelped) {
        List<MaHelped> list = maHelpedService.selectMaHelpedList(maHelped);
        ExcelUtil<MaHelped> util = new ExcelUtil<MaHelped>(MaHelped.class);
        util.exportExcel(response, list, "帮助记录数据");
    }

    /**
     * 获取帮助记录详细信息
     */
    @PreAuthorize("@ss.hasPermi('ma:helped:query')")
    @GetMapping(value = "/{huid}")
    public AjaxResult getInfo(@PathVariable("huid") Long huid) {
        return success(maHelpedService.selectMaHelpedByHuid(huid));
    }

    /**
     * 新增帮助记录
     */
    @PreAuthorize("@ss.hasPermi('ma:helped:add')")
    @Log(title = "新增帮助记录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody MaHelped maHelped) {
        maHelped.setCreateBy(SecurityUtils.getUsername());
        return toAjax(maHelpedService.insertMaHelped(maHelped));
    }

    /**
     * 修改帮助记录
     */
    @PreAuthorize("@ss.hasPermi('ma:helped:edit')")
    @Log(title = "修改帮助记录", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody MaHelped maHelped) {
        maHelped.setUpdateBy(SecurityUtils.getUsername());
        return toAjax(maHelpedService.updateMaHelped(maHelped));
    }

    /**
     * 删除帮助记录
     */
    @PreAuthorize("@ss.hasPermi('ma:helped:remove')")
    @Log(title = "删除帮助记录", businessType = BusinessType.DELETE)
    @DeleteMapping("/{huids}")
    public AjaxResult remove(@PathVariable Long[] huids) {
        return toAjax(maHelpedService.deleteMaHelpedByHuids(huids));
    }


}
