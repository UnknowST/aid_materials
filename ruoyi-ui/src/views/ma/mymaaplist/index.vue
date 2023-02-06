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

        <el-form-item label="帮助状态" prop="status">
          <el-select
            v-model="queryParams.status"
            placeholder="帮助状态"
            clearable
            style="width: 240px"
          >
            <el-option
              v-for="dict in dict.type.help_status"
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
              v-hasPermi="['ma:material:add']"
              >新增</el-button
            >
          </el-col> -->
        
        <el-col :span="1.5">
          <el-button
            type="warning"
            plain
            icon="el-icon-download"
            size="mini"
            @click="handleExport"
            v-hasPermi="['ma:material:export']"
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
      <el-table-column label="id" align="center" prop="huid" />
      <el-table-column label="物资编号" align="center" prop="mid" />
        <el-table-column label="用户名" align="center" prop="husername" />
        <el-table-column label="姓名" align="center" prop="hname" />
        <el-table-column label="联系方式" align="center" prop="hphone">
        </el-table-column>
        <el-table-column label="地址" align="center" prop="haddress" />
        <el-table-column label="灾害类型" align="center" prop="disastype">
          <template slot-scope="scope">
            <span>
              {{ getDisastypeName(scope.row.disastype) }}
            </span>
          </template>
        </el-table-column>

        <el-table-column label="求助类型" align="center" prop="needtype">
          <template slot-scope="scope">
            <span>
              {{ getNeedtypeName(scope.row.needtype) }}
            </span>
          </template>
        </el-table-column>
        <el-table-column label="申请状态" align="center" prop="status" width="100">
          <template slot-scope="scope">
            <dict-tag
              :options="dict.type.help_status"
              :value="scope.row.status"
            ></dict-tag>
          </template>
        </el-table-column>
        <el-table-column label="申请时间" align="center" prop="createTime">
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
              v-if="scope.row.status==0||scope.row.status==2"
              @click="handleUpdate(scope.row)"
              v-hasPermi="['ma:material:edit']"
              >修改</el-button
            >
            <el-button
              size="mini"
              type="text"
              icon="el-icon-delete"
              v-if="scope.row.status==0||scope.row.status==2"
              @click="handleDelete(scope.row)"
              v-hasPermi="['ma:material:remove']"
              >删除</el-button
            >
  
            <!-- <el-button
                size="mini"
                type="text"
                icon="el-icon-check"
                @click="handleExamine(scope.row)"
                v-hasPermi="['ma:material:remove']"
                >审核</el-button> -->
  
            <el-button
              size="mini"
              type="text"
              icon="el-icon-s-data"
              v-if="scope.row.status==1"
              @click="handleApply(scope.row)"
              v-hasPermi="['ma:material:remove']"
              >完成</el-button
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
  
      <!-- 修改申请对话框 -->
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
            v-model="apform.husername"
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
            v-model="apform.hname"
            placeholder="请输入姓名"
            :style="{ width: '100%' }"
            readonly=""
          ></el-input>
        </el-form-item>
        <el-form-item label="联系方式" prop="hphone">
          <el-input
            v-model="apform.hphone"
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
              v-for="(item, index) in this.disastype"
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
        <el-form-item label="备注">
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
          <el-button @click="cancel">取消</el-button>
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
    mylistMaterial,
  } from "@/api/ma/maupload";
  import { delImg } from "@/api/ma/img";
  import { getToken } from "@/utils/auth";
  import { updateImg } from "@/api/ma/img";
  import { getUserProfile } from "@/api/system/user";
  import { addHelped } from "@/api/ma/helped";
  
  import { listDistype, listMatype } from "@/api/ma/maapply";
  import { listHelped, updateHelped,mylistHelped,delHelped } from "@/api/ma/helped";

  export default {
    name: "mymaaplist",
  
    dicts: ["ma_examine_status", "help_status"],
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
        drawer: false,
        showbutton1: "false",
        showbutton2: "false",
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
        // 物资列表表格数据
        materialList: [],
        // 申请记录列表数据
        helpedList: [],
        // 弹出层标题
        title: "",
        // 是否显示弹出层
        open: false,
  
        // 申请弹出层
        apopen: false,
  
        // 查询参数
        queryParams: {
          pageNum: 1,
          pageSize: 10,
          status: null,
        },
        //申请列表查询参数
        helpedqueryParams: {
          pageNum: 1,
          pageSize: 10,
          mid: null,
          status:null,
        },
  
        disastype: [],
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
          create_by: undefined,
        },
        //帮助状态修改 form
        Reform: {
          huid: null,
          status: null,
          remark: null,
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
          status: [
            {
              required: true,
              message: "请选择状态",
              trigger: "change",
            },
          ],
          remark: [
            {
              required: true,
              message: "请输入备注",
              trigger: "change",
            },
          ],
        },
      };
    },
    created() {
      this.getList();
      this.getdata();
      this.getUser();
      
    },
  
    computed: {
      baseurl() {
        return process.env.VUE_APP_BASE_API;
      },
    },
  
    methods: {
      //查询灾害类型的名称
      getDisastypeName(option) {
        for (var i = 0; i < this.disastype.length; i++) {
          if (option == this.disastype[i].disid) return this.disastype[i].disname;
        }
      },
      //查询求助类型的名称
      getNeedtypeName(option) {
        for (var i = 0; i < this.needtype.length; i++) {
          if (option == this.needtype[i].maid) return this.needtype[i].maname;
        }
      },
      /** 查询用户物资列表 */
      getList() {
        this.loading = true;
        mylistHelped(this.queryParams).then((response) => {
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
          this.disastype = res.rows;
        });
        listMatype().then((res) => {
          this.needtype = res.rows;
        });
      },
      // 取消按钮
      cancel() {
    
        this.apopen = false;
        this.reset();
      },
      //点击右上角关闭按钮的回调事件
      handleDialogClose() {
        this.apopen = false;
        this.reset();
       
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
        this.reset();
        this.getPositions();
        this.apform.huid=row.huid;
        this.$set(this.apform, 'husername', this.user.userName)
        this.$set(this.apform, 'hname', this.user.nickName)
        this.$set(this.apform, 'hphone',this.user.phonenumber)
        if(row.status==1||row.status==3) {
            this.$message("不允许修改")
            return ;
        }
        if(row.haddress!=undefined){
            str = this.getaddress(row.haddress);
        this.$set(this.apform, 'region1', str[0])
        this.$set(this.apform, 'region2', str[1])
        this.$set(this.apform, 'region3', str[2])
        this.$set(this.apform, 'haddress1', str[3])
        }
        this.apform.disastype=row.disastype
        this.apform.needtype=row.needtype
        this.apform.remark=row.remark
        this.apopen=true;
        this.title="修改申请信息"

      },
      //修改提交
      apsubmitForm(){
        this.$refs["apform"].validate((valid) => {
            if(valid){
                this.apform.haddress=this.apform.region1+this.apform.region2+this.apform.region3+this.apform.haddress1
               
                //console.log(this.apform)
                updateHelped(this.apform).then(res=>{
                    this.$modal.msgSuccess("修改成功")
                    this.apopen=false;
                    this.getList();
                })
            }
        })
      },
 
  
      // 申请列表按钮
      handleApply(row) {
        updateHelped({
            huid:row.huid,
            status:3
        }).then(res=>{
            this.$modal.msgSuccess("确认成功")
            this.getList();
        })
      },

 

      /** 删除按钮操作 */
      handleDelete(row) {
        const huids = row.huid || this.ids;
        this.$modal
          .confirm('是否确认删除申请编号为"' + huids + '"的数据项？')
          .then(function () {
            return delHelped(huids);
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
          "ma/helped/export",
          {
            ...this.queryParams,
          },
          `helped${new Date().getTime()}.xlsx`
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
    },
  };
  </script>
      