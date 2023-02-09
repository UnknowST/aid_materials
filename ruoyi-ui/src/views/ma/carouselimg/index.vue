<template>
  <div class="app-container">
    <!-- <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
        <el-form-item label="名称" prop="imgname">
          <el-input
            v-model="queryParams.imgname"
            placeholder="请输入名称"
            clearable
            @keyup.enter.native="handleQuery"
          />
        </el-form-item>
        <el-form-item label="图片路径" prop="imgpath">
          <el-input
            v-model="queryParams.imgpath"
            placeholder="请输入图片路径"
            clearable
            @keyup.enter.native="handleQuery"
          />
        </el-form-item>
        <el-form-item>
          <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
          <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
        </el-form-item>
      </el-form> -->

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['ma:carouselimg:add']"
          >新增</el-button
        >
      </el-col>
      <el-col :span="1.5">
        <!-- <el-button
            type="success"
            plain
            icon="el-icon-edit"
            size="mini"
            :disabled="single"
            @click="handleUpdate"
            v-hasPermi="['ma:carouselimg:edit']"
          >修改</el-button> -->
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['ma:carouselimg:remove']"
          >删除</el-button
        >
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['ma:carouselimg:export']"
          >导出</el-button
        >
      </el-col>
      <right-toolbar
        :showSearch.sync="showSearch"
        @queryTable="getList"
      ></right-toolbar>
    </el-row>

    <el-table
      v-loading="loading"
      :data="carouselimgList"
      @selection-change="handleSelectionChange"
    >
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="id" align="center" prop="id" />
      <el-table-column label="名称" align="center" prop="imgname" />
      <el-table-column label="图片" align="center" prop="imgpath">
        <template slot-scope="scope">
          <div class="demo-image">
            <div class="block">
              <el-image
                style="width: 100px; height: 100px"
                :src="baseurl + scope.row.imgpath"
              ></el-image>
            </div>
          </div>
        </template>
      </el-table-column>
      <el-table-column label="图片状态" align="center" prop="imgstatus">
        <template slot-scope="scope">
          <el-switch
            v-model="scope.row.imgstatus"
            active-value="0"
            inactive-value="1"
            @change="handleStatusChange(scope.row)"
          ></el-switch>
        </template>
      </el-table-column>
      <el-table-column label="备注" align="center" prop="remark" />
      <el-table-column
        label="操作"
        align="center"
        class-name="small-padding fixed-width"
      >
        <template slot-scope="scope">
          <!-- <el-button
              size="mini"
              type="text"
              icon="el-icon-edit"
              @click="handleUpdate(scope.row)"
              v-hasPermi="['ma:carouselimg:edit']"
            >修改</el-button> -->
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['ma:carouselimg:remove']"
            >删除</el-button
          >
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total > 0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改轮播图对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body :before-close="handleDialogClose">
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="图片上传" prop="minage">
          <el-upload
            ref="minage"
            :file-list="minagefileList"
            :action="minageAction"
            :before-upload="minageBeforeUpload"
            list-type="picture"
            :auto-upload="false"
            :on-success="uploadsuccess"
            :multiple="false"
            :limit="1"
            name="file"
            :headers="this.hearders"
            :on-error="uploaderror"
          >
            <el-button
              size="small"
              type="primary"
              icon="el-icon-upload"
              v-hasPermi="['ma:img:add']"
              >点击上传</el-button
            >
          </el-upload>
        </el-form-item>
        <el-form-item label="备注" prop="remark">
          <el-input
            v-model="form.remark"
            type="textarea"
            placeholder="请输入内容"
          />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>
  
  <script>
import {
  listCarouselimg,
  getCarouselimg,
  delCarouselimg,
  addCarouselimg,
  updateCarouselimg,
  changeCarouseStatus,
} from "@/api/ma/carouselimg";
import { getToken } from "@/utils/auth";
export default {
  name: "Carouselimg",
  data() {
    return {
      //图片上传 请求头
      hearders: {
        Authorization: "Bearer " + getToken(),
      },
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 轮播图表格数据
      carouselimgList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        imgname: null,
        imgpath: null,
        imgstatus: null,
      },
      minageAction: "http://localhost:8080/ma/carouselimg/imgupload",
      minagefileList: [],
      // 表单参数
      form: {},
      // 表单校验
      rules: {},
    };
  },
  created() {
    this.getList();
  },
  computed: {
    baseurl() {
      return process.env.VUE_APP_BASE_API;
    },
  },
  methods: {
    /** 查询轮播图列表 */
    getList() {
      this.loading = true;
      listCarouselimg(this.queryParams).then((response) => {
        this.carouselimgList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 轮播图状态修改
    handleStatusChange(row) {
      console.log(row.imgstatus)
      let text = row.imgstatus === "0" ? "启用" : "停用";
      this.$modal
        .confirm('确认要"' + text + '""' + row.imgname + '"轮播图吗？')
        .then(function () {
          return changeCarouseStatus(row.id, row.imgstatus);
        })
        .then(() => {
          this.$modal.msgSuccess(text + "成功");
        })
        .catch(function () {
          row.imgstatus = row.imgstatus === "0" ? "1" : "0";
        });
    },
    // 取消按钮
    cancel() {
      
      this.open = false;
      this.reset();
      this.minagefileList=[]
    },
    handleDialogClose(){
     
      this.open=false,
      this.reset()
      this.minagefileList=[]
      
    },
    // 表单重置
    reset() {
      this.form = {
        id: null,
        imgname: null,
        imgpath: null,
        imgstatus: null,
        createBy: null,
        createTime: null,
        updateBy: null,
        updateTime: null,
        remark: null,
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map((item) => item.id);
      this.single = selection.length !== 1;
      this.multiple = !selection.length;
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.minagefileList=[]
      this.open = true;
      this.title = "添加轮播图";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids;
      getCarouselimg(id).then((response) => {
        this.form = response.data;
        this.open = true;
        this.title = "修改轮播图";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate((valid) => {
        if (valid) {
          //图片上传
          this.$refs.minage.submit();
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const ids = row.id || this.ids;
      this.$modal
        .confirm('是否确认删除轮播图编号为"' + ids + '"的数据项？')
        .then(function () {
          return delCarouselimg(ids);
        })
        .then(() => {
          this.getList();
          this.$modal.msgSuccess("删除成功");
        })
        .catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download(
        "ma/carouselimg/export",
        {
          ...this.queryParams,
        },
        `carouselimg_${new Date().getTime()}.xlsx`
      );
    },
    uploaderror() {
      this.$message.error("图片上传失败，请重试！");
      this.uploadflag = "false";
    },
    minageBeforeUpload(file) {
      //console.log(file)
      let isRightSize = file.size / 1024 / 1024 < 5;
      if (!isRightSize) {
        this.$message.error("文件大小超过 5MB");
      }

      return isRightSize;
    },
    uploadsuccess(res) {
      this.open = false;
      this.minagefileList=[]
      this.getList();
    },
  },
};
</script>
  