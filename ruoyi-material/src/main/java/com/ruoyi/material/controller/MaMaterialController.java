package com.ruoyi.material.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import javax.swing.text.html.MinimalHTMLWriter;

import com.ruoyi.common.config.RuoYiConfig;
import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.utils.file.FileUploadUtils;
import com.ruoyi.common.utils.file.MimeTypeUtils;
import com.ruoyi.material.domain.MaImg;
import com.ruoyi.material.service.IMaImgService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.material.domain.MaMaterial;
import com.ruoyi.material.service.IMaMaterialService;
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
@RequestMapping("/ma/material")
public class MaMaterialController extends BaseController
{
    @Autowired
    private IMaMaterialService maMaterialService;
    @Autowired
    private IMaImgService maImgService;
    /**
     * 查询【请填写功能名称】列表
     */
    @PreAuthorize("@ss.hasPermi('ma:material:list')")
    @GetMapping("/list")
    public TableDataInfo list(MaMaterial maMaterial)
    {
        startPage();
        List<MaMaterial> list = maMaterialService.selectMaMaterialList(maMaterial);
        return getDataTable(list);
    }

    /**
     * 导出【请填写功能名称】列表
     */
    @PreAuthorize("@ss.hasPermi('ma:material:export')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, MaMaterial maMaterial)
    {
        List<MaMaterial> list = maMaterialService.selectMaMaterialList(maMaterial);
        ExcelUtil<MaMaterial> util = new ExcelUtil<MaMaterial>(MaMaterial.class);
        util.exportExcel(response, list, "【请填写功能名称】数据");
    }

    /**
     * 获取【请填写功能名称】详细信息
     */
    @PreAuthorize("@ss.hasPermi('ma:material:query')")
    @GetMapping(value = "/{mid}")
    public AjaxResult getInfo(@PathVariable("mid") Long mid)
    {
        return success(maMaterialService.selectMaMaterialByMid(mid));
    }

    /**
     * 新增【请填写功能名称】
     */
    @PreAuthorize("@ss.hasPermi('ma:material:add')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody MaMaterial maMaterial)
    {
        int flag=maMaterialService.insertMaMaterial(maMaterial);
        AjaxResult ajax= AjaxResult.success();
        if(flag!=0){
            ajax.put("mid",flag);
            return ajax;
        }
        return error("上传失败，请重试！");
    }

    /**
     * 修改【请填写功能名称】
     */
    @PreAuthorize("@ss.hasPermi('ma:material:edit')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody MaMaterial maMaterial)
    {
        return toAjax(maMaterialService.updateMaMaterial(maMaterial));
    }

    /**
     * 删除【请填写功能名称】
     */
    @PreAuthorize("@ss.hasPermi('ma:material:remove')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.DELETE)
	@DeleteMapping("/{mids}")
    public AjaxResult remove(@PathVariable Long[] mids)
    {
        // 首先要删除关联的图片
        for(Long mid:mids){
            /*AjaxResult ajaxResult=getInfo(mid);
            MaMaterial maMaterial= (MaMaterial) ajaxResult.get("data");
            //System.out.println(maMaterial);
            maImgService.deleteMaImgByImgid(maMaterial.getMimagid());*/
            int flag=maImgService.deleteMaImgByImgid(mid);
            if(flag==0){
                return error("删除失败，请重试！");
            }
            maMaterialService.deleteMaMaterialByMid(mid);
        }
        //maMaterialService.deleteMaMaterialByMids(mids)
        return AjaxResult.success();
    }

    /**
     * 图片上传
     */
    @PreAuthorize("@ss.hasPermi('ma:img:add')")
    @Log(title = "物资图片上传", businessType = BusinessType.UPDATE)
    @RequestMapping("/imgupload")
    @CrossOrigin
    public AjaxResult imgupload(@RequestParam("file") MultipartFile file) throws Exception
    {
        //System.out.println("======="+maMaterial.getMdetail()+maMaterial.getMtitle());
        if (!file.isEmpty())
        {
            MaImg maImg=new MaImg();

            LoginUser loginUser = getLoginUser();
            String imgurl= FileUploadUtils.upload(RuoYiConfig.getUploadPath(),file, MimeTypeUtils.IMAGE_EXTENSION);
            maImg.setImgname(file.getOriginalFilename());
            maImg.setImgpath(imgurl);
            maImg.setCreateBy(loginUser.getUsername());
            int flag=maImgService.insertMaImg(maImg);
            if (flag!=0)
            {
                AjaxResult ajax = AjaxResult.success();
                ajax.put("imgUrl", imgurl);
                ajax.put("mid",flag);
                // 更新缓存用户头像
                return ajax;
            }
        }
        return error("上传图片异常，请联系管理员");
    }
//
//    /**
//     * 图片上传
//     */
//    @PreAuthorize("@ss.hasPermi('ma:img:update')")
//    @Log(title = "更新图片", businessType = BusinessType.UPDATE)
//    @RequestMapping("/updateimg")
//    @CrossOrigin
//    public AjaxResult updateimg(@RequestParam("file") MultipartFile file) throws Exception
//    {
//        //System.out.println("======="+maMaterial.getMdetail()+maMaterial.getMtitle());
//        if (!file.isEmpty())
//        {
//            MaImg maImg=new MaImg();
//
//            LoginUser loginUser = getLoginUser();
//            String imgurl= FileUploadUtils.upload(RuoYiConfig.getUploadPath(),file, MimeTypeUtils.IMAGE_EXTENSION);
//            maImg.setImgname(file.getName());
//            maImg.setImgpath(imgurl);
//            maImg.setCreateBy(loginUser.getUsername());
//            int flag=maImgService.insertMaImg(maImg);
//            if (flag!=0)
//            {
//                AjaxResult ajax = AjaxResult.success();
//                ajax.put("imgUrl", imgurl);
//                ajax.put("mid",flag);
//                // 更新缓存用户头像
//                return ajax;
//            }
//        }
//        return error("上传图片异常，请联系管理员");
//    }

}
