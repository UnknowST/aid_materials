package com.ruoyi.material.controller;

import java.io.File;
import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.common.config.RuoYiConfig;
import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.utils.file.FileUploadUtils;
import com.ruoyi.common.utils.file.MimeTypeUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.material.domain.MaImg;
import com.ruoyi.material.service.IMaImgService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

/**
 * 物资图片Controller
 * 
 * @author ruoyi
 * @date 2023-01-24
 */
@RestController
@RequestMapping("/ma/img")
public class MaImgController extends BaseController
{
    @Autowired
    private IMaImgService maImgService;

    /**
     * 查询物资图片列表
     */
    @PreAuthorize("@ss.hasPermi('ma:img:list')")
    @GetMapping("/list")
    public TableDataInfo list(MaImg maImg)
    {
        startPage();
        List<MaImg> list = maImgService.selectMaImgList(maImg);
        return getDataTable(list);
    }

    /**
     * 导出物资图片列表
     */
    @PreAuthorize("@ss.hasPermi('ma:img:export')")
    @Log(title = "导出物资图片", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, MaImg maImg)
    {
        List<MaImg> list = maImgService.selectMaImgList(maImg);
        ExcelUtil<MaImg> util = new ExcelUtil<MaImg>(MaImg.class);
        util.exportExcel(response, list, "物资图片数据");
    }

    /**
     * 获取物资图片详细信息
     */
    @PreAuthorize("@ss.hasPermi('ma:img:query')")
    @GetMapping(value = "/{imgid}")
    public AjaxResult getInfo(@PathVariable("imgid") Long imgid)
    {
        return success(maImgService.selectMaImgByImgid(imgid));
    }

    /**
     * 新增物资图片
     */
    @PreAuthorize("@ss.hasPermi('ma:img:add')")
    @Log(title = "新增物资图片", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody MaImg maImg)
    {
        return toAjax(maImgService.insertMaImg(maImg));
    }

    /**
     * 修改物资图片
     */
    @PreAuthorize("@ss.hasPermi('ma:img:edit')")
    @Log(title = "修改物资图片", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody MaImg maImg)
    {
        return toAjax(maImgService.updateMaImg(maImg));
    }

    /**
     * 删除物资图片
     */
    @PreAuthorize("@ss.hasPermi('ma:img:remove')")
    @Log(title = "删除物资图片", businessType = BusinessType.DELETE)
	@DeleteMapping("/{imgids}")
    public AjaxResult remove(@PathVariable Long[] imgids)
    {


        for(Long imgid:imgids){

           int flag= maImgService.deleteMaImgByImgid(imgid);
           if(flag==0){
               return error("文件删除失败，请重试！");
           }
        }

        return AjaxResult.success();
    }

}
