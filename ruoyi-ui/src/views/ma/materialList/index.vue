<template>
  <div class="app-container">
    <el-form
      :model="queryParams"
      ref="queryForm"
      size="small"
      :inline="true"
      v-show="showSearch"
      label-width="68px"
    >
      <el-form-item label="物资标题" prop="mtitle">
        <el-input
          v-model="queryParams.mtitle"
          placeholder="请输入物资标题"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <!-- <el-form-item label="图片id" prop="mimagid">
        <el-input
          v-model="queryParams.mimagid"
          placeholder="请输入图片id"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item> -->
      <el-form-item label="地址" prop="maddress">
        <el-input
          v-model="queryParams.maddress"
          placeholder="请输入地址"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="审核状态" prop="mstatus">
        <el-select
          v-model="queryParams.mstatus"
          placeholder="审核状态"
          clearable
          style="width: 240px"
        >
          <el-option
            v-for="dict in dict.type.ma_examine_status"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button
          type="primary"
          icon="el-icon-search"
          size="mini"
          @click="handleQuery"
          >搜索</el-button
        >
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery"
          >重置</el-button
        >
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <!-- <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['system:material:add']"
          >新增</el-button
        >
      </el-col> -->
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['system:material:edit']"
          >修改</el-button
        >
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['system:material:remove']"
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
          v-hasPermi="['system:material:export']"
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
      :data="materialList"
      @selection-change="handleSelectionChange"
    >
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="id" align="center" prop="mid" />
      <el-table-column label="物资标题" align="center" prop="mtitle" />
      <el-table-column label="物资详细信息" align="center" prop="mdetail" />
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
      <el-table-column label="地址" align="center" prop="maddress" />
      <el-table-column label="物资状态" align="center" prop="mstatus">
        <template slot-scope="scope">
          <dict-tag
            :options="dict.type.ma_examine_status"
            :value="scope.row.mstatus"
          ></dict-tag>
        </template>
      </el-table-column>
      <!-- <el-table-column label="备注" align="center" prop="remark" /> -->
      <el-table-column
        label="操作"
        align="center"
        class-name="small-padding fixed-width"
      >
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:material:edit']"
            >修改</el-button
          >
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:material:remove']"
            >删除</el-button
          >

          <el-button
            size="mini"
            type="text"
            icon="el-icon-check"
            @click="handleExamine(scope.row)"
            v-hasPermi="['system:material:remove']"
            >审核</el-button
          >

          <el-button
            size="mini"
            type="text"
            icon="el-icon-message"
            @click="handleApply(scope.row)"
            v-hasPermi="['system:material:remove']"
            >申请</el-button
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

    <!-- 添加或修改【请填写功能名称】对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body :before-close="handleDialogClose">
      <el-form
        ref="form"
        :model="form"
        :rules="rules"
        size="medium"
        label-width="100px"
      >
        <el-form-item label="物资名称" prop="mtitle" required>
          <el-input
            v-model="form.mtitle"
            placeholder="请输入物资名称"
            clearable
            :style="{ width: '100%' }"
          >
          </el-input>
        </el-form-item>
        <el-form-item label="物资详细信息" prop="mdetail" required>
          <el-input
            v-model="form.mdetail"
            type="textarea"
            placeholder="请输入物资详细信息"
            :autosize="{ minRows: 4, maxRows: 4 }"
            :style="{ width: '100%' }"
          ></el-input>
        </el-form-item>
        <el-row :gutter="24">
          <el-form-item label="地址" prop="address">
            <el-col :span="8">
              <el-select
                v-model="form.region1"
                placeholder="请选择省"
                @change="provinceChange($event)"
              >
                <el-option
                  v-for="(item, index) in this.provinceList"
                  :key="item.name"
                  :value="item.name"
                ></el-option>
              </el-select>
            </el-col>
            <el-col :span="8">
              <el-select
                v-model="form.region2"
                placeholder="请选择城市"
                @change="cityChange($event)"
              >
                <el-option
                  v-for="(item, index) in this.cityList"
                  :key="item.name"
                  :value="item.name"
                ></el-option>
              </el-select>
            </el-col>
            <el-col :span="8">
              <el-select v-model="form.region3" placeholder="请选择区县">
                <el-option
                  v-for="(item, index) in this.countyList"
                  :key="item.name"
                  :value="item.name"
                ></el-option>
              </el-select>
            </el-col>
          </el-form-item>
        </el-row>
        <el-form-item label="详细地址" prop="address1" required>
          <el-input
            v-model="form.address1"
            placeholder="请详细地址"
            maxlength="20"
          />
        </el-form-item>
        <el-form-item label="图片上传" prop="minage">
          <el-upload
            ref="minage"
            :file-list="minagefileList"
            :action="minageAction"
            :before-upload="minageBeforeUpload"
            list-type="picture"
            :auto-upload="false"
            :on-success="uploadsuccess"
            name="file"
            :headers="this.hearders"
            :on-error="uploaderror"
            :on-change="fileChange"
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
        <el-form-item label="审核状态" prop="mstatus">
          <el-select
            v-model="form.mstatus"
            placeholder="审核状态"
            clearable
            style="width: 240px"
          >
            <el-option
              v-for="dict in dict.type.ma_examine_status"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            />
          </el-select>
        </el-form-item>
      </el-form>

      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>

    <!-- // 审核弹出框 -->
    <el-dialog
      :title="title"
      :visible.sync="exopen"
      width="500px"
      append-to-body
    >
      <el-form
        ref="form"
        :model="form"
        :rules="rules"
        size="medium"
        label-width="100px"
      >
        <el-form-item label="审核状态" prop="mstatus">
          <el-select
            v-model="form.mstatus"
            placeholder="审核状态"
            clearable
            style="width: 240px"
          >
            <el-option
              v-for="dict in dict.type.ma_examine_status"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            />
          </el-select>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="exsubmitForm">确 定</el-button>
        <el-button @click="excancel">取 消</el-button>
      </div>
    </el-dialog>
    <!-- // 申请弹出框 -->
    <el-dialog
      :title="title"
      :visible.sync="apopen"
      width="550px"
      append-to-body
    >
      <el-form
        ref="apform"
        :model="apform"
        :rules="rules"
        size="medium"
        label-width="100px"
        label-position="right"
      >
        <el-form-item label="用户名" prop="husername">
          <el-input
            v-model="user.userName"
            placeholder="请输入用户名"
            show-word-limit
            clearable
            prefix-icon="el-icon-mobile"
            :style="{ width: '100%' }"
            readonly=""
          ></el-input>
        </el-form-item>
        <el-form-item label="姓名" prop="hname">
          <el-input
            v-model="user.nickName"
            placeholder="请输入姓名"
            :style="{ width: '100%' }"
            readonly=""
          ></el-input>
        </el-form-item>
        <el-form-item label="联系方式" prop="hphone">
          <el-input
            v-model="user.phonenumber"
            placeholder="请输入联系方式"
            clearable
            :style="{ width: '100%' }"
            readonly=""
          >
          </el-input>
        </el-form-item>
        <!-- 层级太深 表单校验 不能检验到 数据的变化 -->
        <el-row :gutter="24">
          <el-form-item label="地址" prop="haddress">
            <el-col :span="8">
              <el-select
                v-model="apform.region1"
                placeholder="请选择省"
                @change="provinceChange($event)"
              >
                <el-option
                  v-for="(item, index) in this.provinceList"
                  :key="item.name"
                  :value="item.name"
                ></el-option>
              </el-select>
            </el-col>
            <el-col :span="8">
              <el-select
                v-model="apform.region2"
                placeholder="请选择城市"
                @change="cityChange($event)"
              >
                <el-option
                  v-for="(item, index) in this.cityList"
                  :key="item.name"
                  :value="item.name"
                ></el-option>
              </el-select>
            </el-col>
            <el-col :span="8">
              <el-select v-model="apform.region3" placeholder="请选择区县">
                <el-option
                  v-for="(item, index) in this.countyList"
                  :key="item.name"
                  :value="item.name"
                ></el-option>
              </el-select>
            </el-col>
          </el-form-item>
        </el-row>

        <el-row>
          <el-col>
            <el-form-item label="详细地址" prop="haddress1">
              <el-input
                v-model="apform.haddress1"
                placeholder="请详细地址"
                maxlength="20"
              />
            </el-form-item>
          </el-col>
        </el-row>
        <el-form-item label="灾害类型" prop="disastype">
          <el-select
            v-model="apform.disastype"
            placeholder="请选择灾害类型"
            clearable
            :style="{ width: '100%' }"
          >
            <el-option
              v-for="(item, index) in this.distype"
              :key="item.disid"
              :label="item.disname"
              :value="item.disid"
              :disabled="item.disabled"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="求助类型" prop="needtype">
          <el-select
            v-model="apform.needtype"
            placeholder="请选择求助类型"
            clearable
            :style="{ width: '100%' }"
          >
            <el-option
              v-for="(item, index) in this.needtype"
              :key="item.maid"
              :label="item.maname"
              :value="item.maid"
              :disabled="item.disabled"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="备注" prop="remark">
          <el-input
            v-model="apform.remark"
            type="textarea"
            placeholder="请输入备注"
            show-word-limit
            :autosize="{ minRows: 4, maxRows: 4 }"
            :style="{ width: '100%' }"
          ></el-input>
        </el-form-item>
        <el-form-item size="large">
          <el-button type="primary" @click="apsubmitForm">提交</el-button>
          <el-button @click="apCancel">取消</el-button>
        </el-form-item>
      </el-form>
    </el-dialog>
  </div>
</template>
  
  <script>
import {
  listMaterial,
  getMaterial,
  delMaterial,
  addMaterial,
  updateMaterial,
  getPosition,
} from "@/api/ma/maupload";
import { delImg } from "@/api/ma/img";
import { getToken } from "@/utils/auth";
import { updateImg } from "@/api/ma/img";
import { getUserProfile } from "@/api/system/user";
import { addHelped } from "@/api/ma/helped";

import { listDistype, listMatype } from "@/api/ma/maapply";
export default {
  name: "Material",
  dicts: ["ma_examine_status"],
  data() {
    return {
      //登录用户数据
      user: {},
      // 地址选择数据
      provinceList: [],
      cityList: [],
      countyList: [],
      CITY: [],
      XIAN: [],
      showbutton1: "false",
      showbutton2: "false",
      hearders: {
        Authorization: "Bearer " + getToken(),
      },
      minageAction: "http://localhost:8080/ma/material/imgupload",
      minagefileList: [],
      choiseFlag: "false",
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
      // 【请填写功能名称】表格数据
      materialList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 审核弹出层
      exopen: false,
      // 申请弹出层
      apopen: false,

      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        mtitle: null,
        mdetail: null,
        mtype: null,
        mimagid: null,
        maddress: null,
        mstatus: null,
      },
      distype: [],
      needtype: [],
      // 表单参数
      form: {
        mimagid: null,
        minage: null,
        mtitle: undefined,
        mdetail: undefined,
        minage: null,
        mstatus: 0,
        maddress: "",
        address: undefined,
        address1: undefined,
        disastype: undefined,
        needtype: undefined,
        // 地址选择数据
        provinceList: [],
        cityList: [],
        countyList: [],
        CITY: [],
        XIAN: [],
        region1: [],
        region2: [],
        region3: [],
      },
      apform: {
        mid: undefined,
        hname: undefined,
        husername: undefined,
        hphone: undefined,
        haddress: undefined,
        disastype: undefined,
        needtype: undefined,
        status: undefined,
        remark: undefined,
        haddress1: undefined,
        region1: undefined,
        region2: undefined,
        region3: undefined,
        create_by:undefined,
      },
      // 表单校验
      rules: {
        mtitle: [
          {
            required: true,
            message: "请输入物资名称",
            trigger: "blur",
          },
        ],
        mdetail: [
          {
            required: true,
            message: "请输入物资详细信息",
            trigger: "blur",
          },
        ],
        mstatus: [
          {
            required: true,
            message: "请选择审核状态",
            trigger: "change",
          },
        ],
        // address: [
        //   {
        //     required: true,
        //     message: "请选择地址",
        //     trigger: "blur",
        //   },
        // ],
        address1: [
          {
            required: true,
            message: "请输入详细地址",
            trigger: "blur",
          },
        ],
        hname: [
          {
            required: true,
            message: "姓名不能为空",
            trigger: "blur",
          },
        ],
        husername: [
          {
            required: true,
            message: "用户名不能为空",
            trigger: "blur",
          },
        ],
        hphone: [
          {
            pattern: /^1[3|4|5|6|7|8|9][0-9]\d{8}$/,
            message: "请输入正确的手机号码",
            trigger: "blur",
          },
        ],
        haddress1: [
          {
            required: true,
            message: "请输入详细地址",
            trigger: "blur",
          },
        ],
        region1: [
          {
            required: true,
            message: "请选择地址",
            trigger: "change",
          },
        ],
        region2: [
          {
            required: true,
            message: "请选择地址",
            trigger: "change",
          },
        ],
        region3: [
          {
            required: true,
            message: "请选择地址",
            trigger: "change",
          },
        ],
        disastype: [
          {
            required: true,
            message: "请选择灾害类型",
            trigger: "change",
          },
        ],
        needtype: [
          {
            required: true,
            message: "请选择求助类型",
            trigger: "change",
          },
        ],
      },
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
    /** 查询【请填写功能名称】列表 */
    getList() {
      this.loading = true;
      listMaterial(this.queryParams).then((response) => {
        this.materialList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    getUser() {
      getUserProfile().then((response) => {
        this.user = response.data;
        //console.log(this.user);
        this.roleGroup = response.roleGroup;
        this.postGroup = response.postGroup;
      });
    },
    getdata() {
      listDistype().then((res) => {
        this.distype = res.rows;
      });
      listMatype().then((res) => {
        this.needtype = res.rows;
      });
    },
    // 取消按钮
    cancel() {
      this.minagefileList=[]
      this.open = false;
      this.reset();
    },
    //点击右上角关闭按钮的回调事件
    handleDialogClose(){
      this.open=false;
      this.reset()
      this.minagefileList=[]
    },

    // 表单重置
    reset() {
      this.form = {
        mid: null,
        mtitle: null,
        mdetail: null,
        mtype: null,
        mimagid: null,
        maddress: null,
        address1: null,
        minagefileList: null,
        mstatus: null,
        delFlag: null,
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
      this.ids = selection.map((item) => item.mid);
      this.single = selection.length !== 1;
      this.multiple = !selection.length;
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加物资";
    },
    fileChange() {
      this.choiseFlag = "true";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.choiseFlag = "false";
    
      this.reset();
      const mid = row.mid || this.ids;
      getMaterial(mid).then((response) => {
        this.form = response.data;
        this.open = true;
        this.title = "修改物资信息";
        this.getPositions();

        str = this.getaddress(this.form.maddress);
        this.form.region1 = str[0];
        this.form.region2 = str[1];
        this.form.region3 = str[2];
        this.form.address1 = str[3];
        console.log(str);
        //console.log(response)
        //this.form.minage=response.data.imgpath
        let data = {
          name: response.data.imgname,
          url: this.baseurl + response.data.imgpath,
        };
        this.minagefileList.push(data);
      });
    },
    // 审核按钮
    handleExamine(row) {
      (this.exopen = true),
        (this.title = "审核物资"),
        (this.form.mstatus = row.mstatus);
      this.form.mid = row.mid;
    },
    excancel() {
      (this.exopen = false), this.reset();
    },
    apCancel() {
      this.apopen = false;
      this.reset();
    },
    exsubmitForm() {
      this.$refs["form"].validate((valid) => {
        if (valid) {
          updateMaterial(this.form).then((res) => {
            this.$modal.msgSuccess("操作成功");
            (this.exopen = false), this.getList();
          });
        }
      });
    },
    apsubmitForm() {
      this.$refs["apform"].validate((valid) => {
        if (valid) {
          if (
            this.apform.region1 == undefined ||
            this.apform.region2 == undefined ||
            this.apform.region3 == undefined
          ) {
            this.$message.error("地址信息必填");
            return;
          } else {
            this.apform.haddress =
              this.apform.region1 +
              this.apform.region2 +
              this.apform.region3 +
              this.apform.haddress;

            addHelped(this.apform).then((res) => {
              this.$modal.msgSuccess("操作成功");
              (this.apopen = false), this.getList();
            });
          }
        }
      });
    },
    // 申请按钮
    handleApply(row) {
      this.apopen = true;
      this.title = "申请物资";
      this.getUser();
      this.getdata();
      this.getPositions();
      this.apform.mid = row.mid;
     
      getUserProfile().then((response) => {
        this.user = response.data;
        if (this.user.address != null) {
          str = this.getaddress(this.user.address);
          (this.apform.region1 = str[0]),
            (this.apform.region2 = str[1]),
            (this.apform.region2 = str[2]),
            (this.apform.haddress1 = str[3]);
        }
        (this.apform.husername = this.user.userName),
          (this.apform.hname = this.user.nickName),
          (this.apform.hphone = this.user.phonenumber);
          this.apform.create_by=this.user.userName
      });
      // console.log(this.user)
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate((valid) => {
        if (valid) {
          if (this.form.mid != null) {
            // 首先判断 图片是否有更新
            let data = {
              maddress:
                this.form.region1 +
                this.form.region1 +
                this.form.region3 +
                this.form.address1,
            };
            Object.assign(this.form, data);
            console.log(this.choiseFlag);
            if (this.choiseFlag == "true") {
              //图片上传
              this.$refs.minage.submit();
            } else {
              updateMaterial(this.form).then((res) => {
                this.$modal.msgSuccess("修改成功");
                this.minagefileList = [];
                
                this.open = false;

                this.getList();
              });
            }
          } else {
            addMaterial(this.form).then((response) => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const mids = row.mid || this.ids;
      this.$modal
        .confirm('是否确认删除物资编号为"' + mids + '"的数据项？')
        .then(function () {
          return delMaterial(mids);
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
        "system/material/export",
        {
          ...this.queryParams,
        },
        `material_${new Date().getTime()}.xlsx`
      );
    },
    // 获取省市县 三级联动数据
    getPositions() {
      getPosition().then((res) => {
        this.provinceList = res.data.districts[0].districts; /* 省*/
        //console.log(this.provinceList);
        /* 进行遍历赋值*/
        /* 市区和县区*/
        let newProvince = this.provinceList;
        for (let i = 0; i < newProvince.length; i++) {
          /* 省级*/
          for (let j = 0; j < newProvince[i].districts.length; j++) {
            /* 市级*/
            let city = newProvince[i].districts[j].name;
            this.CITY.push({ id: j + 1, name: city, code: i + 1 });
            for (
              let k = 0;
              k < newProvince[i].districts[j].districts.length;
              k++
            ) {
              /* 县级*/
              let xian = newProvince[i].districts[j].districts[k].name;
              this.XIAN.push({
                id: k + 1,
                name: xian,
                code: j + 1,
                cityCountyName: city,
              });
            }
          }
        }
        for (let m = 0; m < newProvince.length; m++) {
          newProvince[m] = { ...newProvince[m], ...{ code: m + 1 } };
        }
        this.provinceList = newProvince;
      });
    },
    provinceChange(that) {
      // 根据选中省，匹配市
      let cityCode = 0;
      let newCityArry = [];
      this.provinceList.forEach((item, index) => {
        if (item.name == that) {
          cityCode = item.code;
        }
      });
      // console.log(cityCode)
      if (cityCode) {
        this.cityList = [];
        this.CITY.forEach((item, index) => {
          if (item.code == cityCode) {
            this.cityList.push(item);
          }
        }); /* 市匹配成功*/
      }
    },
    cityChange(that) {
      let countyCode = 0;
      let cityname = "";
      let newCountyArry = [];
      this.cityList.forEach((item, index) => {
        if (item.name == that) {
          countyCode = item.id;
          cityname = item.name;
        }
      });
      if (countyCode) {
        this.countyList = [];
        this.XIAN.forEach((item, index) => {
          if (item.code == countyCode && item.cityCountyName == cityname) {
            this.countyList.push(item);
          }
        });
      }
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
      console.log(this.choiseFlag);
      this.choiseFlag = "true";
      console.log(this.choiseFlag);
      return isRightSize;
    },
    uploadsuccess(res) {
      this.uploadflag = "false";
      console.log(this.uploadflag);
      // this.form.minage = res.imgUrl;
      let tempdata = res.mid;
      // 聚合地址数据
      let data = {
        maddress:
          this.form.region1 +
          this.form.region1 +
          this.form.region3 +
          this.form.address1,
      };
      Object.assign(this.form, data);
      // 先删除原来的图片
      console.log(this.form.mimagid);
      delImg(this.form.mimagid).then((res) => {
        console.log(res);
        this.form.mimagid = tempdata;
        // 再更新Material数据
        updateMaterial(this.form).then((res) => {
          // console.log(this.form)
          this.$modal.msgSuccess("修改成功！");
          this.minagefileList = [];
          this.open = false;
          this.getList();
        });
      });
    },
  },
};
</script>
  