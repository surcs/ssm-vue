<script>
export default {
  beforeRouteEnter(to, from, next) {
    next(vm => {
      vm.$router.replace(from.path)
    })
  }
}
</script>

<template>
</template>

<style scoped>

</style>