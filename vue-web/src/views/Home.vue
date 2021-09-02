<template>
  <div class="home">
    <img alt="Vue logo" src="../assets/logo.png">
    用户信息：{{data}}
  </div>
</template>

<script>
// @ is an alias to /src

export default {
  name: 'Home',
  data(){
            return{
                data:""
            }
        },
  created(){
      this.$axios.get("users").then(res=>{
          this.data=res.data;
          console.log(res)
      });
  }
}
</script>
