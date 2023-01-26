<template>
  <div style="margin-top: 20px">
    <el-form
      ref="maForm"
      :model="formData"
      :rules="rules"
      size="medium"
      label-width="100px"
    >
      <el-form-item label="物资名称" prop="mtitle">
        <el-input
          v-model="formData.mtitle"
          placeholder="请输入物资名称"
          clearable
          :style="{ width: '100%' }"
        >
        </el-input>
      </el-form-item>
      <el-form-item label="物资详细信息" prop="mdetail">
        <el-input
          v-model="formData.mdetail"
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
      <el-form-item label="详细地址" prop="address1">
        <el-input
          v-model="formData.address1"
          placeholder="请详细地址"
          maxlength="11"
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
          v-model="formData.mstatus"
          placeholder="请选择审核状态"
          clearable
          :style="{ width: '100%' }"
        >
          <el-option
            v-for="(item, index) in mstatusOptions"
            :key="index"
            :label="item.label"
            :value="item.value"
            :disabled="item.disabled"
          ></el-option>
        </el-select>
      </el-form-item>
      <el-form-item size="large">
        <el-button type="primary" @click="submitForm">提交</el-button>
        <el-button @click="resetForm">重置</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>
  <script>
import { getPosition, addMaterial } from "@/api/ma/maupload";
import { updateImg } from "@/api/ma/img";
import { getUserProfile } from "@/api/system/user";
import { getToken } from "@/utils/auth";
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
      fileList: [],
      uploadflag: "false",
      hearders: {
        Authorization: "Bearer " + getToken(),
      },
      formData: {
        mimagid: null,
        mtitle: undefined,
        mdetail: undefined,
        minage: null,
        mstatus: 0,
        address: undefined,
        address1: undefined,
        file: [],
        minagefileList: [],
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
      },
      minageAction: "http://localhost:8080/ma/material/imgupload",
      minagefileList: [],
      mstatusOptions: [
        {
          label: "待审核",
          value: 0,
        },
      ],
    };
  },
  computed: {},
  watch: {},
  created() {
    this.getPositions();
  },
  mounted() {},
  methods: {
    submitForm() {
      this.$refs["maForm"].validate((valid) => {
        if (!valid) return;
        // TODO 提交表单
        else {
          if(this.formData.region1==""||this.formData.region2==""|| this.formData.region3==""){
            this.$message.error('请选择地址信息！');
            return;
          }
          //图片上传
          this.$refs.minage.submit();
        }
      });
    },
    resetForm() {
      this.$refs["maForm"].resetFields();
    },
    //获取当前登录的 user
    getUser() {
      getUserProfile().then((response) => {
        this.user = response.data;
        // console.log(this.user);
        //this.roleGroup = response.roleGroup;
        // this.postGroup = response.postGroup;
      });
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
      this.uploadflag = "true";
      console.log(this.uploadflag);
      this.formData.minage = res.imgUrl;
      this.formData.mimagid = res.mid;
      // 聚合地址数据
      let data = {
        maddress:
          this.formData.region1 +
          this.formData.region1 +
          this.formData.region3 +
          this.formData.address1,
      };
      Object.assign(this.formData, data);
      addMaterial(this.formData).then((res) => {
        // console.log(this.formData)
        console.log(res);
        let data={
          mid:res.mid,
          imgid:this.formData.mimagid
        }

        updateImg(data).then((res) => {
          this.$modal.msgSuccess("上传成功，请等待审核");
        });
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
.el-upload__tip {
  line-height: 1.2;
}
</style>
  