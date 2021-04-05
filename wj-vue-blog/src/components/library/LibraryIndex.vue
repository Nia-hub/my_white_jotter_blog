<template>
    <el-container>
        <el-aside  style="width: 200px;margin-top: 10px">
            <!--indexSelect为父子组件通讯的函数-->
            <side-menu @indexSelect="listByCategory" ref="sideMenu"></side-menu>
        </el-aside>
        <el-main>
            <books class="books-area" ref="booksArea"></books>
        </el-main>
    </el-container>
</template>

<script>
import SideMenu from "./SideMenu.vue";
import Books from "./Books.vue";
export default {
    name: "AppLibrary",
    components: {
        SideMenu,
        Books,
    },
    methods: {
        listByCategory() {
            var _this = this;
            var categoryId = this.$refs.sideMenu.categoryId;
            var url = "categories/" + categoryId + "/books";
            this.$axios.get(url).then((resp) => {
                if (resp && resp.status === 200) {
                    _this.$refs.booksArea.books = resp.data;
                }
            });
        },
    },
};
</script>

<style scoped>
.books-area {
    width: 1000px;
    margin-left: auto;
    margin-right: auto;
}
</style>
