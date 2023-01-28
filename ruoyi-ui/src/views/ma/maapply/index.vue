<template>
  <div>
    <el-form
      ref="maform"
      :model="formData"
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
   
      <el-row :gutter="24">
        <el-form-item label="地址" prop="address">
        <el-col :span="8">
         
            <el-select
              v-model="formData.region1"
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
            v-model="formData.region2"
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
          <el-select v-model="formData.region3" placeholder="请选择区县">
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
              v-model="formData.address1"
              placeholder="请详细地址"
              maxlength="11"
            />
          </el-form-item>
        </el-col>
      </el-row>
      <!-- <el-form-item label="地址" prop="address">
        <el-input
          v-model="user.address"
          placeholder="请输入地址"
          clearable
          :style="{ width: '100%' }"
        >
        </el-input>
      </el-form-item> -->
      <el-form-item label="灾害类型" prop="disastype">
        <el-select
          v-model="formData.disastype"
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
          v-model="formData.needtype"
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
          v-model="formData.remark"
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
      </el-form-item>
    </el-form>
  </div>
</template>
  <script>
import { listDistype, listMatype, getPosition,addHelp } from "@/api/ma/maapply";
import { getUserProfile } from "@/api/system/user";

import "@riophae/vue-treeselect/dist/vue-treeselect.css";

export default {
  components: {},
  props: [],
  data() {
    return {
      // 地址选择数据
      provinceList: [],
      cityList: [],
      countyList: [],
      CITY: [],
      XIAN: [],
      formData: {
        husername: "",
        hname: undefined,
        hphone: undefined,
        address: undefined,
        address1:undefined,
        disastype: undefined,
        needtype: undefined,
        remark: undefined,
        // 地址选择数据
        provinceList: [],
        cityList: [],
        countyList: [],
        CITY: [],
        XIAN: [],
        region1: "",
        region2: "",
        region3: "",
      },
      distype: [],
      needtype: [],
      user: {},
      rules: {
        username: [
          {
            required: true,
            message: "请输入用户名",
            trigger: "blur",
          },
        ],
        name: [
          {
            required: true,
            message: "请输入姓名",
            trigger: "blur",
          },
        ],
        phone: [
          {
            required: true,
            message: "请输入联系方式",
            trigger: "blur",
          },
          {
            pattern: /^1(3|4|5|7|8|9)\d{9}$/,
            message: "手机号格式错误",
            trigger: "blur",
          },
        ],
        address: [
          {
            // required: true,
            // message: "请选择地址",
            // trigger: "change",
          },
        ],
        address1: [
          {
            required: true,
            message: "请输入详细地址",
            trigger: "blur",
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
        remark: [],
      },
    };
  },
  computed: {},
  watch: {},
  created() {
    this.getdata();
    this.getUser();
    this.getPositions();
  },
  mounted() {},
  methods: {
    submitForm() {
      this.$refs["maform"].validate((valid) => {
        if (!valid) return;
        // TODO 提交表单
        else{
            
            let data1={
                haddress:this.formData.region1+this.formData.region2+this.formData.region3+this.formData.address1
            }
            this.formData.husername=this.user.userName
            this.formData.hname=this.user.nickName
            this.formData.hphone=this.user.phonenumber
            Object.assign(this.formData,data1)
            console.log(this.formData);
            addHelp(this.formData).then((res)=>{
                this.$modal.msgSuccess("申请成功，请等待审核");
               //this.open = false;
              
            })
        }
      });
    },
    resetForm() {
      this.$refs["maform"].resetFields();
    },
    getdata() {
      listDistype().then((res) => {
        this.distype = res.rows;
      });
      listMatype().then((res) => {
        this.needtype = res.rows;
      });
    },
    getUser() {
      getUserProfile().then((response) => {
        this.user = response.data;
       // console.log(this.user);
        //this.roleGroup = response.roleGroup;
        // this.postGroup = response.postGroup;
      });
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
  <style>
</style>
  