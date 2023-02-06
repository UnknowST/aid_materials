package com.ruoyi.material.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.utils.SecurityUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
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
import com.ruoyi.material.domain.MaMatype;
import com.ruoyi.material.service.IMaMatypeService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 求助类型Controller
 * 
 * @author ruoyi
 * @date 2023-01-19
 */
@RestController
@RequestMapping("/ma/matype")
public class MaMatypeController extends BaseController
{
    @Autowired
    private IMaMatypeService maMatypeService;

    /**
     * 查询求助类型列表
     */
    @PreAuthorize("@ss.hasPermi('ma:matype:list')")
    @GetMapping("/list")
    public TableDataInfo list(MaMatype maMatype)
    {

        startPage();
        List<MaMatype> list = maMatypeService.selectMaMatypeList(maMatype);
        return getDataTable(list);
    }

    /**
     * 导出求助类型列表
     */
    @PreAuthorize("@ss.hasPermi('ma:matype:export')")
    @Log(title = "导出求助类型", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, MaMatype maMatype)
    {
        List<MaMatype> list = maMatypeService.selectMaMatypeList(maMatype);
        ExcelUtil<MaMatype> util = new ExcelUtil<MaMatype>(MaMatype.class);
        util.exportExcel(response, list, "求助类型数据");
    }

    /**
     * 获取求助类型详细信息
     */
    @PreAuthorize("@ss.hasPermi('ma:matype:query')")
    @GetMapping(value = "/{maid}")
    public AjaxResult getInfo(@PathVariable("maid") Long maid)
    {
        return success(maMatypeService.selectMaMatypeByMaid(maid));
    }

    /**
     * 新增求助类型
     */
    @PreAuthorize("@ss.hasPermi('ma:matype:add')")
    @Log(title = "新增求助类型", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody MaMatype maMatype)
    {
        maMatype.setCreateBy(SecurityUtils.getUsername());
        return toAjax(maMatypeService.insertMaMatype(maMatype));
    }

    /**
     * 修改求助类型
     */
    @PreAuthorize("@ss.hasPermi('ma:matype:edit')")
    @Log(title = "修改求助类型", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody MaMatype maMatype)
    {

        maMatype.setUpdateBy(SecurityUtils.getUsername());
        return toAjax(maMatypeService.updateMaMatype(maMatype));
    }

    /**
     * 删除求助类型
     */
    @PreAuthorize("@ss.hasPermi('ma:matype:remove')")
    @Log(title = "删除求助类型", businessType = BusinessType.DELETE)
	@DeleteMapping("/{maids}")
    public AjaxResult remove(@PathVariable Long[] maids)
    {
        return toAjax(maMatypeService.deleteMaMatypeByMaids(maids));
    }
}
