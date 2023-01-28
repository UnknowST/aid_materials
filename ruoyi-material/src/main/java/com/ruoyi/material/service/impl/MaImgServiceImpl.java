package com.ruoyi.material.service.impl;

import java.io.File;
import java.util.List;

import com.ruoyi.common.config.RuoYiConfig;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.material.mapper.MaImgMapper;
import com.ruoyi.material.domain.MaImg;
import com.ruoyi.material.service.IMaImgService;

/**
 * 物资图片Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-01-24
 */
@Service
public class MaImgServiceImpl implements IMaImgService 
{
    @Autowired
    private MaImgMapper maImgMapper;

    /**
     * 查询物资图片
     * 
     * @param imgid 物资图片主键
     * @return 物资图片
     */
    @Override
    public MaImg selectMaImgByImgid(Long imgid)
    {
        return maImgMapper.selectMaImgByImgid(imgid);
    }

    /**
     * 查询物资图片列表
     * 
     * @param maImg 物资图片
     * @return 物资图片
     */
    @Override
    public List<MaImg> selectMaImgList(MaImg maImg)
    {
        return maImgMapper.selectMaImgList(maImg);
    }

    /**
     * 新增物资图片
     * 
     * @param maImg 物资图片
     * @return 结果
     */
    @Override
    public int insertMaImg(MaImg maImg)
    {
        maImg.setCreateTime(DateUtils.getNowDate());
        if(maImgMapper.insertMaImg(maImg)==1) {
            //System.out.println(maImg.getImgid());
            return Integer.parseInt(maImg.getImgid().toString());
        }
        return 0;
    }

    /**
     * 修改物资图片
     * 
     * @param maImg 物资图片
     * @return 结果
     */
    @Override
    public int updateMaImg(MaImg maImg)
    {
        maImg.setUpdateTime(DateUtils.getNowDate());
        return maImgMapper.updateMaImg(maImg);
    }

    /**
     * 批量删除物资图片
     * 
     * @param imgids 需要删除的物资图片主键
     * @return 结果
     */
    @Override
    public int deleteMaImgByImgids(Long[] imgids)
    {

        return maImgMapper.deleteMaImgByImgids(imgids);
    }

    /**
     * 删除物资图片信息
     * 
     * @param imgid 物资图片主键
     * @return 结果
     */
    @Override
    public int deleteMaImgByImgid(Long imgid)
    {
        MaImg maImg=  maImgMapper.selectMaImgByImgid(imgid);
        // 修改文件名
        String fileNewName=maImg.getImgpath().replace("/profile/upload","");
        File file=new File(RuoYiConfig.getUploadPath()+fileNewName);
        //System.out.println(file.getPath());
        //System.out.println(file.exists());

        if(file.delete()){
            maImgMapper.deleteMaImgByImgid(imgid);
        }else{
            return 0;
        }
        return 1;
    }
}
