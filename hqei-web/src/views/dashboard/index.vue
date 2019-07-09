<template>
  <div class="dashboard-container">
    <!--<a href="https://github.com/Heeexy/SpringBoot-Shiro-Vue" target="_blank">-->
      <img style="margin: 40px"
      :src="hqeiImg">
    <!--</a>-->
  </div>
</template>
<script>
import hqeiImg from "../../assets/hqei.png"

  export default {
    name: 'dashboard',
    data() {
      return {
        hqeiImg
      }
    }
  }
</script>
