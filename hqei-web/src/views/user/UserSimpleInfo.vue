<template>
  <div class="app-container" style="width: 400px;">
    <el-form :model="resetForm" status-icon :rules="resetFormRules" ref="resetForm" label-width="100px">
      <el-form-item label="新密码" prop="newpwd">
        <el-input type="password" placeholder="不填则表示不修改" v-model="resetForm.password" auto-complete="off"></el-input>
      </el-form-item>
      <el-form-item label="昵称" prop="nickname">
        <el-input type="text" v-model="resetForm.nickname" auto-complete="off"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="doModify">修改</el-button>
        <el-button>
          <router-link  to="/">
              取消
          </router-link></el-button>
      </el-form-item>
    </el-form>
  </div>
</template>
<script>
  import {mapGetters} from 'vuex'
  import roleService from "../../api/RoleService";
  import UserService from "../../api/UserService";

  export default {
    data(){
      return{
        //重置密码
        resetForm: {
          password: '',
          nickname: '',
        },
        resetFormRules: {
          nickname: [{required: true, message: '昵称是必填的！', trigger: 'blur'}]
        },
      }
    },
    created() {
      UserService.getUserSimpleInfo().then(resp=>{
        this.resetForm.nickname = resp.result.nickname;
      });
    },
    computed: {
      ...mapGetters([
        'userId'
      ])
    },
    methods: {
      doModify(){
        UserService.modifySimpleInfo(this.resetForm).then(resp=>{
          this.$message.success("修改成功");
          this.$router.push({
            path: "/"
          })
        })
      },
    }
  }
</script>
