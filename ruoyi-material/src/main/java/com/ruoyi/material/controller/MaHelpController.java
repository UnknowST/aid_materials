package com.ruoyi.material.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
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
import com.ruoyi.material.domain.MaHelp;
import com.ruoyi.material.service.IMaHelpService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 【请填写功能名称】Controller
 * 
 * @author ruoyi
 * @date 2023-01-20
 */
@RestController
@RequestMapping("/ma/help")
public class MaHelpController extends BaseController
{
    @Autowired
    private IMaHelpService maHelpService;

    /**
     * 查询【请填写功能名称】列表
     */
    @PreAuthorize("@ss.hasPermi('ma:help:list')")
    @GetMapping("/list")
    public TableDataInfo list(MaHelp maHelp)
    {
        startPage();
        List<MaHelp> list = maHelpService.selectMaHelpList(maHelp);
        return getDataTable(list);
    }

    /**
     * 导出【请填写功能名称】列表
     */
    @PreAuthorize("@ss.hasPermi('ma:help:export')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, MaHelp maHelp)
    {
        List<MaHelp> list = maHelpService.selectMaHelpList(maHelp);
        ExcelUtil<MaHelp> util = new ExcelUtil<MaHelp>(MaHelp.class);
        util.exportExcel(response, list, "【请填写功能名称】数据");
    }

    /**
     * 获取【请填写功能名称】详细信息
     */
    @PreAuthorize("@ss.hasPermi('ma:help:query')")
    @GetMapping(value = "/{hid}")
    public AjaxResult getInfo(@PathVariable("hid") Long hid)
    {
        return success(maHelpService.selectMaHelpByHid(hid));
    }

    /**
     * 新增【请填写功能名称】
     */
    @PreAuthorize("@ss.hasPermi('ma:help:add')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody MaHelp maHelp)
    {
        return toAjax(maHelpService.insertMaHelp(maHelp));
    }

    /**
     * 修改【请填写功能名称】
     */
    @PreAuthorize("@ss.hasPermi('ma:help:edit')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody MaHelp maHelp)
    {
        return toAjax(maHelpService.updateMaHelp(maHelp));
    }

    /**
     * 删除【请填写功能名称】
     */
    @PreAuthorize("@ss.hasPermi('ma:help:remove')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.DELETE)
	@DeleteMapping("/{hids}")
    public AjaxResult remove(@PathVariable Long[] hids)
    {
        return toAjax(maHelpService.deleteMaHelpByHids(hids));
    }
}
