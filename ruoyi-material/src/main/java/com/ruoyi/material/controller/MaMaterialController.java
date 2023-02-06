package com.ruoyi.material.controller;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import javax.swing.text.html.MinimalHTMLWriter;

import com.ruoyi.common.config.RuoYiConfig;
import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.utils.SecurityUtils;
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
 * 物资记录Controller
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
     * 查询物资记录列表
     */
    @PreAuthorize("@ss.hasPermi('ma:material:list')")
    @GetMapping("/list")
    public TableDataInfo list(MaMaterial maMaterial)
    {
        //这里应该是三种情况
        //1.如果是管理员和超级管理员 返回所有数据
        //2. 不是管理员 返回所有审核通过的数据
        //3. 返回当前用户的物资列表  单独使用一个方法
        startPage();
        List<MaMaterial> list=new ArrayList<>();
        LoginUser loginUser=getLoginUser();
        // 先判断是不是管理员
        if(loginUser.isAdmin() || loginUser.isMaAdmin()){
            list = maMaterialService.selectMaMaterialList(maMaterial);
        }else{
            maMaterial.setMstatus("1");
            list=maMaterialService.selectMaMaterialList(maMaterial);
        }
        //List<MaMaterial> list = maMaterialService.selectMaMaterialList(maMaterial);
        return getDataTable(list);
    }

    /**
     * 返回登录用户的物资列表
     */
    @PreAuthorize("@ss.hasPermi('ma:material:mylist')")
    @GetMapping("/mylist")
    public TableDataInfo mylist(MaMaterial maMaterial)
    {

        startPage();

        LoginUser loginUser=getLoginUser();
        maMaterial.setCreateBy(loginUser.getUsername());
        List<MaMaterial> list = maMaterialService.selectMaMaterialList(maMaterial);
        return getDataTable(list);
    }

    /**
     * 导出物资记录列表
     */
    @PreAuthorize("@ss.hasPermi('ma:material:export')")
    @Log(title = "导出物资记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, MaMaterial maMaterial)
    {
        List<MaMaterial> list = maMaterialService.selectMaMaterialList(maMaterial);
        ExcelUtil<MaMaterial> util = new ExcelUtil<MaMaterial>(MaMaterial.class);
        util.exportExcel(response, list, "物资记录数据");
    }

    /**
     * 获取物资记录详细信息
     */
    @PreAuthorize("@ss.hasPermi('ma:material:query')")
    @GetMapping(value = "/{mid}")
    public AjaxResult getInfo(@PathVariable("mid") Long mid)
    {
        return success(maMaterialService.selectMaMaterialByMid(mid));
    }

    /**
     * 新增物资记录
     */
    @PreAuthorize("@ss.hasPermi('ma:material:add')")
    @Log(title = "新增物资记录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody MaMaterial maMaterial)
    {
        LoginUser loginUser=getLoginUser();
        maMaterial.setCreateBy(loginUser.getUsername());
        int flag=maMaterialService.insertMaMaterial(maMaterial);
        AjaxResult ajax= AjaxResult.success();
        if(flag!=0){
            ajax.put("mid",flag);
            return ajax;
        }
        return error("上传失败，请重试！");
    }

    /**
     * 修改物资记录
     */
    @PreAuthorize("@ss.hasPermi('ma:material:edit')")
    @Log(title = "修改物资记录", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody MaMaterial maMaterial)
    {
        maMaterial.setUpdateBy(SecurityUtils.getUsername());
        return toAjax(maMaterialService.updateMaMaterial(maMaterial));
    }

    /**
     * 删除物资记录
     */
    @PreAuthorize("@ss.hasPermi('ma:material:remove')")
    @Log(title = "删除物资记录", businessType = BusinessType.DELETE)
	@DeleteMapping("/{mids}")
    public AjaxResult remove(@PathVariable Long[] mids)
    {
        // 首先要删除关联的图片
        for(Long mid:mids){

            int flag=maImgService.deleteMaImgByImgid(mid);
            if(flag==0){
                return error("删除失败，请重试！");
            }
            maMaterialService.deleteMaMaterialByMid(mid);
        }

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


}
