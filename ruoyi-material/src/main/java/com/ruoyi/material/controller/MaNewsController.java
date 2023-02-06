package com.ruoyi.material.controller;

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
import com.ruoyi.material.domain.MaNews;
import com.ruoyi.material.service.IMaNewsService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 新闻列Controller
 *
 * @author ruoyi
 * @date 2023-01-28
 */
@RestController
@RequestMapping("/ma/news")
public class MaNewsController extends BaseController {
    @Autowired
    private IMaNewsService maNewsService;

    /**
     * 查询新闻列列表
     */
    @PreAuthorize("@ss.hasPermi('ma:news:list')")
    @GetMapping("/list")
    public TableDataInfo list(MaNews maNews) {
        startPage();
        List<MaNews> list = maNewsService.selectMaNewsList(maNews);
        return getDataTable(list);
    }

    /**
     * 导出新闻列列表
     */
    @PreAuthorize("@ss.hasPermi('ma:news:export')")
    @Log(title = "新闻列", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, MaNews maNews) {
        List<MaNews> list = maNewsService.selectMaNewsList(maNews);
        ExcelUtil<MaNews> util = new ExcelUtil<MaNews>(MaNews.class);
        util.exportExcel(response, list, "新闻列数据");
    }

    /**
     * 获取新闻列详细信息
     */
    @PreAuthorize("@ss.hasPermi('ma:news:query')")
    @GetMapping(value = "/{nid}")
    public AjaxResult getInfo(@PathVariable("nid") Long nid) {
        return success(maNewsService.selectMaNewsByNid(nid));
    }

    /**
     * 新增新闻列
     */
    @PreAuthorize("@ss.hasPermi('ma:news:add')")
    @Log(title = "新增新闻列", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody MaNews maNews) {

        maNews.setCreateBy(SecurityUtils.getUsername());
        return toAjax(maNewsService.insertMaNews(maNews));
    }

    /**
     * 修改新闻列
     */
    @PreAuthorize("@ss.hasPermi('ma:news:edit')")
    @Log(title = "新闻列", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody MaNews maNews) {
        //LoginUser loginUser = getLoginUser();
        maNews.setUpdateBy(SecurityUtils.getUsername());
        return toAjax(maNewsService.updateMaNews(maNews));
    }

    /**
     * 删除新闻列
     */
    @PreAuthorize("@ss.hasPermi('ma:news:remove')")
    @Log(title = "新闻列", businessType = BusinessType.DELETE)
    @DeleteMapping("/{nids}")
    public AjaxResult remove(@PathVariable Long[] nids) {
        return toAjax(maNewsService.deleteMaNewsByNids(nids));
    }
}
