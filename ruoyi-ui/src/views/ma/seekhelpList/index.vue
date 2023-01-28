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
      <el-form-item label="求助者用户名" prop="husername">
        <el-input
          v-model="queryParams.husername"
          placeholder="请输入求助者用户名"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="求助者姓名" prop="hname">
        <el-input
          v-model="queryParams.hname"
          placeholder="请输入求助者姓名"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="求助者电话" prop="hphone">
        <el-input
          v-model="queryParams.hphone"
          placeholder="请输入求助者电话"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="提供帮助者用户名" prop="hedusername">
        <el-input
          v-model="queryParams.hedusername"
          placeholder="请输入提供帮助者用户名"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="提供帮助者姓名" prop="hedname">
        <el-input
          v-model="queryParams.hedname"
          placeholder="请输入提供帮助者姓名"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="提供帮助者电话" prop="headphone">
        <el-input
          v-model="queryParams.headphone"
          placeholder="请输入提供帮助者电话"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="详细信息" prop="heddetail">
        <el-input
          v-model="queryParams.heddetail"
          placeholder="请输入详细信息"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="提供帮助者地址" prop="hedaddress">
        <el-input
          v-model="queryParams.hedaddress"
          placeholder="请输入提供帮助者地址"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="申请状态" prop="status">
          <el-select
            v-model="queryParams.status"
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
          v-hasPermi="['system:help:add']"
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
          v-hasPermi="['system:help:edit']"
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
          v-hasPermi="['system:help:remove']"
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
          v-hasPermi="['system:help:export']"
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
            {{ getDisastypeName(scope.row.needtype) }}
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
      <el-table-column
        label="提供帮助者用户名"
        align="center"
        prop="hedusername"
      />
      <el-table-column label="提供帮助者姓名" align="center" prop="hedname" />
      <el-table-column label="提供帮助者电话" align="center" prop="headphone" />
      <el-table-column label="帮助状态" align="center" prop="hedstatus">
        <template slot-scope="scope">
          <dict-tag
            :options="dict.type.help_status"
            :value="scope.row.hedstatus"
          ></dict-tag>
        </template>
      </el-table-column>
      <el-table-column label="详细信息" align="center" prop="heddetail" />
      <el-table-column
        label="提供帮助者地址"
        align="center"
        prop="hedaddress"
      />
      <el-table-column label="备注" align="center" prop="remark" />
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
            v-hasPermi="['system:help:edit']"
            >修改</el-button
          >
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:help:remove']"
            >删除</el-button
          >
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleExam(scope.row)"
            v-hasPermi="['system:help:remove']"
            >审核</el-button
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
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="求助者用户名" prop="husername">
          <el-input v-model="form.husername" placeholder="请输入求助者用户名" />
        </el-form-item>
        <el-form-item label="求助者姓名" prop="hname">
          <el-input v-model="form.hname" placeholder="请输入求助者姓名" />
        </el-form-item>
        <el-form-item label="求助者电话" prop="hphone">
          <el-input v-model="form.hphone" placeholder="请输入求助者电话" />
        </el-form-item>
        <el-form-item label="提供帮助者用户名" prop="hedusername">
          <el-input
            v-model="form.hedusername"
            placeholder="请输入提供帮助者用户名"
          />
        </el-form-item>
        <el-form-item label="提供帮助者姓名" prop="hedname">
          <el-input v-model="form.hedname" placeholder="请输入提供帮助者姓名" />
        </el-form-item>
        <el-form-item label="提供帮助者电话" prop="headphone">
          <el-input
            v-model="form.headphone"
            placeholder="请输入提供帮助者电话"
          />
        </el-form-item>
        <el-form-item label="详细信息" prop="heddetail">
          <el-input v-model="form.heddetail" placeholder="请输入详细信息" />
        </el-form-item>
        <el-form-item label="提供帮助者地址" prop="hedaddress">
          <el-input
            v-model="form.hedaddress"
            placeholder="请输入提供帮助者地址"
          />
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
        <el-form-item label="申请帮助状态" prop="status">
          <el-select
            v-model="form.status"
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
  </div>
</template>
  
  <script>
import { listHelp, getHelp, delHelp, addHelp, updateHelp } from "@/api/ma/help";
import { listDistype } from "@/api/ma/distype";
import { listMatype } from "@/api/ma/matype";
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
      // 【请填写功能名称】表格数据
      helpList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      exopen:false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
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
        status:null,
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
  },
  methods: {
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
      for (var i = 0; i < this.needtype; i++) {
        if (option == this.needtype[i].maid) return this.needtype[i].maname;
      }
    },
    /** 查询求助列表 */
    getList() {
      this.loading = true;
      listHelp(this.queryParams).then((response) => {
        this.helpList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    //审核按钮
    handleExam(row){
        this.exopen=true;
        this.title="审核";
        this.form.hid=row.hid;
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },

    excancel(){
        this.exopen=false;
    },
    exsubmitForm(){
        this.$refs["form"].validate((valid) => {
        if (valid) {
            updateHelp(this.form).then(res=>{
                console.log(this.form)
                this.$modal.msgSuccess("修改成功")
                this.getList()
                this.exopen=false;
            })
        }
    })
       
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
      this.reset();
      const hid = row.hid || this.ids;
      getHelp(hid).then((response) => {
        this.form = response.data;
        this.open = true;
        this.title = "修改求助记录";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate((valid) => {
        if (valid) {
          if (this.form.hid != null) {
            updateHelp(this.form).then((response) => {
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
        .confirm('是否确认删除【请填写功能名称】编号为"' + hids + '"的数据项？')
        .then(function () {
          return delHelp(hids);
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
        "system/help/export",
        {
          ...this.queryParams,
        },
        `help_${new Date().getTime()}.xlsx`
      );
    },

  },
};
</script>
  