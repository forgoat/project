<template>
  <div class="app-container">
    <el-form :model="form" label-width="100px" ref="ruleForm">
      <el-form-item label="规则名称:">
        <el-input v-model="form.ruleName" placeholder="请输入规则名称" style="width: 50%;"/>
      </el-form-item>
      <el-form-item label="数据类型:">
        <el-select v-model="form.ruleType" clearable placeholder="请选择数据类别">
          <el-option
            v-for="item in ruleTypeOptions"
            :key="item.value"
            :label="item.label"
            :value="item.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item
        v-for="(rule,index) in form.rules"
        :key="rule.id"
        :label="'映射字段' + index"
        :rules="{required: true, message: '映射字段不能为空', trigger: 'blur'}"
      >
        原始字段:
        <el-input v-model="rule.originalName" class="filter-item" style="width: 19%" />
        规范字段：
        <el-input v-model="rule.standardName" class="filter-item" style="width: 19%"/>
        <el-button @click.prevent="removeRule(rule)">删除</el-button>
      </el-form-item>
      <el-form-item>
        <el-button @click="addRule">新增字段</el-button>
        <el-button type="primary" @click="submitForm('ruleForm')">提交</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
import sysEnum from "../../common/sysEnum";

export default {
  name: "NewMappingRule",
  data() {
    return {
      ruleTypeOptions: sysEnum.dataTypes,
      form: {
        ruleName: "",
        ruleType: "",
        rules: []
      }
    };
  },
  created() {},
  methods: {
    removeRule(rule) {
      var index = this.form.rules.indexOf(rule);
      if (index != -1) {
        this.form.rules.splice(index, 1);
      }
    },
    addRule() {
      this.form.rules.push({
        originalName: "",
        standardName: "",
        id: Date.now()
      });
    },
    submitForm(formName) {
        this.$refs[formName].validate((valid) => {
            if(valid) {
                alert('提交成功！');
            } else {
                alert('提交失败！');
                return false;
            }
        });
    }
  }
};
</script>

<style>
</style>
