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
      <el-form-item label="帮助状态" prop="hedstatus">
        <el-select
          v-model="queryParams.hedstatus"
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
              v-hasPermi="['ma:help:add']"
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
          v-hasPermi="['ma:help:edit']"
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
          v-hasPermi="['ma:help:remove']"
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
          v-hasPermi="['ma:help:export']"
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
      :data="helpList"
      @selection-change="handleSelectionChange"
    >
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="记录id" align="center" prop="hid" />
      <el-table-column label="求助者用户名" align="center" prop="husername" />
      <el-table-column label="求助者姓名" align="center" prop="hname" />
      <el-table-column label="求助者电话" align="center" prop="hphone" />
      <el-table-column label="求助者地址" align="center" prop="haddress" />
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
      <el-table-column label="申请状态" align="center" prop="status">
        <template slot-scope="scope">
          <dict-tag
            :options="dict.type.ma_examine_status"
            :value="scope.row.status"
          ></dict-tag>
        </template>
      </el-table-column>
      <!-- <el-table-column
          label="提供帮助者用户名"
          align="center"
          prop="hedusername"
        />
        <el-table-column label="提供帮助者姓名" align="center" prop="hedname" />
        <el-table-column label="提供帮助者电话" align="center" prop="headphone" /> -->
      <el-table-column label="帮助状态" align="center" prop="hedstatus">
        <template slot-scope="scope">
          <dict-tag
            :options="dict.type.help_status"
            :value="scope.row.hedstatus"
          ></dict-tag>
        </template>
      </el-table-column>
      <!-- <el-table-column label="详细信息" align="center" prop="heddetail" /> -->
      <!-- <el-table-column
          label="提供帮助者地址"
          align="center"
          prop="hedaddress"
        /> -->
      <!-- <el-table-column label="备注" align="center" prop="remark" /> -->
      <el-table-column
        label="创建时间"
        align="center"
        prop="createTime"
        width="200"
      >
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createTime) }}</span>
        </template>
      </el-table-column>
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
            v-if="scope.row.status == 0 || scope.row.status == 2"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['ma:help:edit']"
            >修改</el-button
          >
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['ma:help:remove']"
            >删除</el-button
          >
          <el-button
            size="mini"
            type="text"
            icon="el-icon-info"
            @click="handleHelp(scope.row)"
            v-hasPermi="['ma:help:remove']"
            >帮助信息</el-button
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

    <!-- 修改求助记录表 -->
    <el-dialog :title="title" :visible.sync="open" width="700px" append-to-body>
      <el-form
        ref="form"
        :model="form"
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

        <el-row>
          <el-col>
            <el-form-item label="详细地址" prop="address1">
              <el-input
                v-model="form.address1"
                placeholder="请详细地址"
                maxlength="11"
              />
            </el-form-item>
          </el-col>
        </el-row>
        <el-form-item label="灾害类型" prop="disastype">
          <el-select
            v-model="form.disastype"
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
            v-model="form.needtype"
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
            v-model="form.remark"
            type="textarea"
            placeholder="请输入备注"
            show-word-limit
            :autosize="{ minRows: 4, maxRows: 4 }"
            :style="{ width: '100%' }"
          ></el-input>
        </el-form-item>
        <el-form-item size="large">
          <el-button type="primary" @click="submitForm">提交</el-button>
          <el-button @click="resetForm">重置</el-button>
          <el-button @click="cancel">取 消</el-button>
        </el-form-item>
      </el-form>
    </el-dialog>

    <!-- // 审核弹出框 -->
    <el-dialog
      :title="title"
      :visible.sync="helpinfo"
      width="700px"
      append-to-body
    >
      <el-descriptions title="提供帮助者信息">
        <el-descriptions-item label="用户名">{{
          this.form.hedusername
        }}</el-descriptions-item>
        <el-descriptions-item label="姓名">{{
          this.form.hedname
        }}</el-descriptions-item>
        <el-descriptions-item label="联系方式">{{
          this.form.headphone
        }}</el-descriptions-item>
        <el-descriptions-item label="地址">{{
          this.form.hedaddress
        }}</el-descriptions-item>
        <el-descriptions-item label="详细信息">{{
          this.form.heddetail
        }}</el-descriptions-item>
        <el-descriptions-item label="帮助时间">{{
          this.form.updateTime
        }}</el-descriptions-item>
      </el-descriptions>
      <div slot="footer" class="dialog-footer">
        <el-button @click="excancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>
      
      <script>
import {
  mylistHelp,
  getHelp,
  delHelp,
  addHelp,
  updateHelp,
  myedlistHelp,
} from "@/api/ma/help";
import { listDistype } from "@/api/ma/distype";
import { listMatype } from "@/api/ma/matype";
import { getUserProfile } from "@/api/system/user";
import { getPosition } from "@/api/ma/maapply";

export default {
  name: "Help",
  dicts: ["ma_examine_status", "help_status"],
  data() {
    return {
      //灾害类型
      disastype: [],
      //求助类型
      needtype: [],
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
      // 求助记录表格数据
      helpList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      helpinfo: false,
      user: {},
      provinceList: [],
      cityList: [],
      countyList: [],
      CITY: [],
      XIAN: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        status: null,
        hedstatus: null,
      },

      // 表单参数
      form: {},
      // 表单校验
      rules: {
        disastype: [
          { required: true, message: "灾害类型不能为空", trigger: "change" },
        ],
        needtype: [
          { required: true, message: "求助类型不能为空", trigger: "change" },
        ],
      },
    };
  },
  created() {
    this.getList();
    this.getData();
    this.getUser();
  },
  methods: {
    //获取当前用户信息
    getUser() {
      getUserProfile().then((response) => {
        this.user = response.data;
        // console.log(this.user);
        //this.roleGroup = response.roleGroup;
        // this.postGroup = response.postGroup;
      });
    },
    //查看帮助信息
    handleHelp(row) {
      if (row.hedstatus == "0") {
        this.$message("该记录当前没有帮助信息！");
      } else {
        console.log(row);
        this.helpinfo = true;
        this.form = row;
        this.form.hedusername = row.hedusername;
      }
    },
    //查询灾害数据和 求助类型数据
    getData() {
      listDistype().then((res) => {
        this.disastype = res.rows;
      });
      listMatype().then((res) => {
        this.needtype = res.rows;
      });
    },
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
    /** 查询求助列表 */
    getList() {
      this.loading = true;
      myedlistHelp(this.queryParams).then((response) => {
        this.helpList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    //审核按钮
    handleExam(row) {
      this.exopen = true;
      this.title = "审核";
      this.form.hid = row.hid;
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },

    excancel() {
      this.helpinfo = false;
    },
    exsubmitForm() {
      this.$refs["form"].validate((valid) => {
        if (valid) {
          updateHelp(this.form).then((res) => {
            console.log(this.form);
            this.$modal.msgSuccess("修改成功");
            this.getList();
            this.exopen = false;
          });
        }
      });
    },
    // 表单重置
    reset() {
      this.form = {
        hid: null,
        husername: null,
        hname: null,
        hphone: null,
        haddress: null,
        disastype: null,
        needtype: null,
        status: null,
        hedusername: null,
        hedname: null,
        headphone: null,
        hedstatus: null,
        heddetail: null,
        hedaddress: null,
        createBy: null,
        createTime: null,
        updateBy: null,
        updateTime: null,
        remark: null,
        address1: undefined,
        provinceList: [],
        cityList: [],
        countyList: [],
        CITY: [],
        XIAN: [],
        region1: "",
        region2: "",
        region3: "",
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
      this.ids = selection.map((item) => item.hid);
      this.single = selection.length !== 1;
      this.multiple = !selection.length;
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加求助记录";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      if (row.status != "0") {
        this.$message("不是待审核的数据不允许修改");
        return;
      }
      this.reset();
      const hid = row.hid || this.ids;
      getHelp(hid).then((response) => {
        this.form = response.data;
        str = this.getaddress(response.data.haddress);
        this.$set(this.form, "region1", str[0]);
        this.$set(this.form, "region2", str[1]);
        this.$set(this.form, "region3", str[2]);
        this.$set(this.form, "address1", str[3]);
        //   this.form.region1 = str[0];
        //   this.form.region2 = str[1];
        //   this.form.region3 = str[2];
        //   this.form.address1 = str[3];
        this.getPositions();
        this.open = true;
        this.title = "修改求助记录";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate((valid) => {
        if (valid) {
          if (this.form.hid != null) {
            //this.form.haddress=this.form.region1+this.form.region2+this.form.region3+this.form.address1;
            // console.log(this.form);
            updateHelp({
              hid: this.form.hid,
              husername: this.form.husername,
              hname: this.form.hname,
              hphone: this.form.hphone,
              haddress:
                this.form.region1 +
                this.form.region2 +
                this.form.region3 +
                this.form.address1,
              disastype: this.form.disastype,
              needtype: this.form.needtype,
              remark: this.form.remark,
            }).then((response) => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addHelp(this.form).then((response) => {
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
      const hids = row.hid || this.ids;
      this.$modal
        .confirm("是否确认放弃对" + row.husername + "的帮助？")
        .then(() => {
          return updateHelp({
            hid: hids,
            hedusername: " ",
            hedphone: " ",
            hedstatus: 0,
            heddetail: " ",
            hedaddress: " ",
            updateBy: this.user.userName,
          });
        })
        .then(() => {
          // 修改帮助状态
          this.getList();
          this.$modal.msgSuccess("删除成功");
        })
        .catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download(
        "ma/help/export",
        {
          ...this.queryParams,
        },
        `help_${new Date().getTime()}.xlsx`
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
      