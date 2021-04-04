<template>
    <el-container>
        <el-aside>
            <switch></switch>
            <side-menu></side-menu>
        </el-aside>
        <el-main>
            <books class="books-area"></books>
        </el-main>
    </el-container>
</template>

<script>
import SideMenu from './SideMenu.vue';
import Books from './Books.vue'
export default {
    name: "AppLibrary",
    components: {
        SideMenu,
        Books
    }
};
</script>

<style scoped>

  .books-area {
    width: 990px;
    margin-left: auto;
    margin-right: auto;
  }

</style>
