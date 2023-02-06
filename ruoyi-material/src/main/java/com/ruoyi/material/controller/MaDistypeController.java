package com.ruoyi.material.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

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
import com.ruoyi.material.domain.MaDistype;
import com.ruoyi.material.service.IMaDistypeService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 灾害类型Controller
 * 
 * @author ruoyi
 * @date 2023-01-19
 */
@RestController
@RequestMapping("/ma/distype")
public class MaDistypeController extends BaseController
{
    @Autowired
    private IMaDistypeService maDistypeService;

    /**
     * 查询灾害类型列表
     */
    @PreAuthorize("@ss.hasPermi('ma:distype:list')")
    @GetMapping("/list")
    public TableDataInfo list(MaDistype maDistype)
    {
        startPage();
        List<MaDistype> list = maDistypeService.selectMaDistypeList(maDistype);
        return getDataTable(list);
    }

    /**
     * 导出灾害类型列表
     */
    @PreAuthorize("@ss.hasPermi('ma:distype:export')")
    @Log(title = "灾害类型", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, MaDistype maDistype)
    {
        List<MaDistype> list = maDistypeService.selectMaDistypeList(maDistype);
        ExcelUtil<MaDistype> util = new ExcelUtil<MaDistype>(MaDistype.class);
        util.exportExcel(response, list, "灾害类型数据");
    }

    /**
     * 获取灾害类型详细信息
     */
    @PreAuthorize("@ss.hasPermi('ma:distype:query')")
    @GetMapping(value = "/{disid}")
    public AjaxResult getInfo(@PathVariable("disid") Long disid)
    {
        return success(maDistypeService.selectMaDistypeByDisid(disid));
    }

    /**
     * 新增灾害类型
     */
    @PreAuthorize("@ss.hasPermi('ma:distype:add')")
    @Log(title = "新增灾害类型", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody MaDistype maDistype)
    {
        maDistype.setCreateBy(SecurityUtils.getUsername());
        return toAjax(maDistypeService.insertMaDistype(maDistype));
    }

    /**
     * 修改灾害类型
     */
    @PreAuthorize("@ss.hasPermi('ma:distype:edit')")
    @Log(title = "更新灾害类型数据", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody MaDistype maDistype)
    {
        maDistype.setUpdateBy(SecurityUtils.getUsername());
        return toAjax(maDistypeService.updateMaDistype(maDistype));
    }

    /**
     * 删除灾害类型
     */
    @PreAuthorize("@ss.hasPermi('ma:distype:remove')")
    @Log(title = "批量灾害类型数据", businessType = BusinessType.DELETE)
	@DeleteMapping("/{disids}")
    public AjaxResult remove(@PathVariable Long[] disids)
    {
        return toAjax(maDistypeService.deleteMaDistypeByDisids(disids));
    }
}
