<template>
    <div>
        <!--组件使用时不能用大写驼峰要转- -->
        <nav-menu></nav-menu>
        <router-view></router-view>
    </div>
</template>

<script>
//映入导航栏组件
import NavMenu from './common/NavMenu.vue';
export default {
    name: "Home",
    //注册组件
    components: {
        NavMenu
    },
};
</script>