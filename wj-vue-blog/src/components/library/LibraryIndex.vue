<template>
    <el-container>
        <el-aside>
            <switch></switch>
            <side-menu></side-menu>
        </el-aside>
        <el-main>
            <books></books>
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
</style>
