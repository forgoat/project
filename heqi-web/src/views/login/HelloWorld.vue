<template>
  <div class="hello">
    <h1>{{ msg }}</h1>
    <h2>没登陆情况下，拦截路由跳到login</h2>
    <form>
      <input placeholder="name" type="text" v-model="name">
      <input placeholder="password" type="password" v-model="password">
    </form>
  </div>
</template>

<script>
export default {
  name: 'HelloWorld',
  data () {
    return {
      msg: 'heqi front-end',
      name: '',
      password: ''
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
h1, h2 {
  font-weight: normal;
}

form {
  max-width: 680px;
  margin: 0 auto;
}

h2 {
  margin-bottom: 200px;
}
ul {
  list-style-type: none;
  padding: 0;
}
li {
  display: inline-block;
  margin: 0 10px;
}
a {
  color: #42b983;
}
</style>
