package com.ruoyi.material.controller;

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
 * 【请填写功能名称】Controller
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
     * 查询【请填写功能名称】列表
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
     * 导出【请填写功能名称】列表
     */
    @PreAuthorize("@ss.hasPermi('ma:img:export')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, MaImg maImg)
    {
        List<MaImg> list = maImgService.selectMaImgList(maImg);
        ExcelUtil<MaImg> util = new ExcelUtil<MaImg>(MaImg.class);
        util.exportExcel(response, list, "【请填写功能名称】数据");
    }

    /**
     * 获取【请填写功能名称】详细信息
     */
    @PreAuthorize("@ss.hasPermi('ma:img:query')")
    @GetMapping(value = "/{imgid}")
    public AjaxResult getInfo(@PathVariable("imgid") Long imgid)
    {
        return success(maImgService.selectMaImgByImgid(imgid));
    }

    /**
     * 新增【请填写功能名称】
     */
    @PreAuthorize("@ss.hasPermi('ma:img:add')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody MaImg maImg)
    {
        return toAjax(maImgService.insertMaImg(maImg));
    }

    /**
     * 修改【请填写功能名称】
     */
    @PreAuthorize("@ss.hasPermi('ma:img:edit')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody MaImg maImg)
    {
        return toAjax(maImgService.updateMaImg(maImg));
    }

    /**
     * 删除【请填写功能名称】
     */
    @PreAuthorize("@ss.hasPermi('ma:img:remove')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.DELETE)
	@DeleteMapping("/{imgids}")
    public AjaxResult remove(@PathVariable Long[] imgids)
    {
        return toAjax(maImgService.deleteMaImgByImgids(imgids));
    }
    /**
     * 图片上传
     */
//    @Log(title = "物资图片上传", businessType = BusinessType.UPDATE)
//    @PostMapping("/imgupload")
//    public AjaxResult imgupload(@RequestParam("avatarfile") MultipartFile file,) throws Exception
//    {
//        if (!file.isEmpty())
//        {
//            MaImg maImg=new MaImg();
//
//            LoginUser loginUser = getLoginUser();
//            String imgurl= FileUploadUtils.upload(RuoYiConfig.getUploadPath(),file, MimeTypeUtils.IMAGE_EXTENSION);
//            maImg.setImgname(file.getName());
//            maImg.setImgpath(imgurl);
//            maImg.setCreateBy(loginUser.getUsername());
//            if (maImgService.insertMaImg(maImg)==1)
//            {
//                AjaxResult ajax = AjaxResult.success();
//                ajax.put("imgUrl", imgurl);
//                // 更新缓存用户头像
//                return ajax;
//            }
//        }
//        return error("上传图片异常，请联系管理员");
//    }
}
