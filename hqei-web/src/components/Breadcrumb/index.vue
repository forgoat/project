<template>
  <el-breadcrumb class="app-breadcrumb" separator="/">
    <transition-group name="breadcrumb">
      <el-breadcrumb-item v-for="(item,index)  in levelList" :key="item.path" >
        <span v-if="item.redirect==='noredirect'||index==levelList.length-1" class="no-redirect">{{item.meta.title}}</span>
        <router-link v-else :to="item.redirect||item.path">{{item.meta.title}}</router-link>
      </el-breadcrumb-item>
    </transition-group>
  </el-breadcrumb>
</template>

<script>
export default {
  created() {
    this.getBreadcrumb()
  },
  data() {
    return {
      levelList: null,
      firstBread: { path: '/dashboard', meta: { title: '首页' }}
    }
  },
  watch: {
    $route() {
      this.getBreadcrumb()
    }
  },
  methods: {
    getBreadcrumb() {
      let matched = this.$route.matched.filter(item => item.name)[0]
      let level = matched.meta.level
      //总共就三级面包屑 当前路由所处的级用meta.level表示
      if(level === '0'){ 
        this.levelList = [this.firstBread]
      }else if(level === '1'){
        sessionStorage.setItem('secondBread', JSON.stringify({path:matched.path, meta:{title: matched.meta.title}}))
        this.levelList = [this.firstBread, matched]
      }else if(level === '2'){
        sessionStorage.setItem('thirdBread', JSON.stringify({path:matched.path, meta:{title: matched.meta.title}}))
        let second = JSON.parse(sessionStorage.getItem('secondBread'))
        this.levelList = [
          this.firstBread,
          second,
          matched
        ]
      }
    }
  }
}
</script>

<style rel="stylesheet/scss" lang="scss" scoped>
  .app-breadcrumb.el-breadcrumb {
    display: inline-block;
    font-size: 14px;
    line-height: 50px;
    margin-left: 10px;
    .no-redirect {
      color: #97a8be;
      cursor: text;
    }
  }
</style>
