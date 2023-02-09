package com.ruoyi.system.controller;

import java.io.File;
import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.common.config.RuoYiConfig;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.utils.file.FileUploadUtils;
import com.ruoyi.common.utils.file.MimeTypeUtils;
import com.ruoyi.material.domain.MaImg;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.material.domain.MaCarouselimg;
import com.ruoyi.material.service.IMaCarouselimgService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

/**
 * 轮播图Controller
 * 
 * @author ruoyi
 * @date 2023-01-27
 */
@RestController
@RequestMapping("/ma/carouselimg")
public class MaCarouselimgController extends BaseController
{
    @Autowired
    private IMaCarouselimgService maCarouselimgService;

    /**
     * 查询轮播图列表
     */
   // @PreAuthorize("@ss.hasPermi('ma:carouselimg:list')")
    @GetMapping("/list")
    public TableDataInfo list(MaCarouselimg maCarouselimg)
    {
        startPage();
        List<MaCarouselimg> list = maCarouselimgService.selectMaCarouselimgList(maCarouselimg);
        return getDataTable(list);
    }

    /**
     * 导出轮播图列表
     */
    @PreAuthorize("@ss.hasPermi('ma:carouselimg:export')")
    @Log(title = "轮播图", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, MaCarouselimg maCarouselimg)
    {
        List<MaCarouselimg> list = maCarouselimgService.selectMaCarouselimgList(maCarouselimg);
        ExcelUtil<MaCarouselimg> util = new ExcelUtil<MaCarouselimg>(MaCarouselimg.class);
        util.exportExcel(response, list, "轮播图数据");
    }

    /**
     * 获取轮播图详细信息
     */
    @PreAuthorize("@ss.hasPermi('ma:carouselimg:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(maCarouselimgService.selectMaCarouselimgById(id));
    }

    /**
     * 新增轮播图
     */
    @PreAuthorize("@ss.hasPermi('ma:carouselimg:add')")
    @Log(title = "轮播图", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody MaCarouselimg maCarouselimg)
    {
        return toAjax(maCarouselimgService.insertMaCarouselimg(maCarouselimg));
    }

    /**
     * 修改轮播图
     */
    @PreAuthorize("@ss.hasPermi('ma:carouselimg:edit')")
    @Log(title = "轮播图", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody MaCarouselimg maCarouselimg)
    {

        return toAjax(maCarouselimgService.updateMaCarouselimg(maCarouselimg));
    }

    /**
     * 删除轮播图
     */
    @PreAuthorize("@ss.hasPermi('ma:carouselimg:remove')")
    @Log(title = "轮播图", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        for(Long id:ids){
            //1.先获取对应 id的详细信息
            MaCarouselimg maCarouselimg= (MaCarouselimg) getInfo(id).get("data");
            assert maCarouselimg != null;
            String fileNewName=maCarouselimg.getImgpath().replace("/profile/upload","");
            File file=new File(RuoYiConfig.getUploadPath()+fileNewName);
            // 2.判断文件是否存在
            if(file.exists()){
                //删除本地文件
                if(file.delete()) {
                    maCarouselimgService.deleteMaCarouselimgById(id);
                }
                else {
                    return AjaxResult.error("删除本地文件失败，请重试！");
                }
            }else{
                //本地找不到文件 直接删除数据库记录
                maCarouselimgService.deleteMaCarouselimgById(id);
            }
        }

        return AjaxResult.success("删除成功");
    }

    /**
     * 图片上传
     */
    @PreAuthorize("@ss.hasPermi('ma:carousellimg:upload')")
    @Log(title = "轮播图上传", businessType = BusinessType.UPDATE)
    @RequestMapping("/imgupload")
    @CrossOrigin
    public AjaxResult imgupload(@RequestParam("file") MultipartFile file) throws Exception {
        if (!file.isEmpty()) {
            MaCarouselimg maCarouselimg = new MaCarouselimg();


            LoginUser loginUser = getLoginUser();
            String imgurl = FileUploadUtils.upload(RuoYiConfig.getUploadPath(), file, MimeTypeUtils.IMAGE_EXTENSION);
            maCarouselimg.setImgname(file.getOriginalFilename());
            maCarouselimg.setImgpath(imgurl);
            maCarouselimg.setCreateBy(loginUser.getUsername());
            return toAjax(maCarouselimgService.insertMaCarouselimg(maCarouselimg));
        } else {
            return AjaxResult.error("文件不能为空");
        }
    }

    /**
     * 状态修改
     */
    @PreAuthorize("@ss.hasPermi('ma:carousellimg:changestatus')")
    @Log(title = "修改轮播图状态", businessType = BusinessType.UPDATE)
    @PutMapping("/changeStatus")
    public AjaxResult changeStatus(@RequestBody MaCarouselimg carouselimg)
    {
        // 获取登录用户
        LoginUser loginUser=getLoginUser();
        carouselimg.setUpdateBy(loginUser.getUsername());
        return toAjax(maCarouselimgService.updateMaCarouselimg(carouselimg));
    }
}
