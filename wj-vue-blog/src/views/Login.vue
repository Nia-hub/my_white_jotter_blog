<template>

    <body class="login-wrap">
        <el-form :model="loginForm" :rules="rules" class="login-container" label-position="left">
            <h3 class="login-title">系统登录</h3>
            <el-form-item prop="loginName">
                <el-input type="text" v-model="loginForm.loginName" placeholder="请输入账号"></el-input>
            </el-form-item>
            <el-form-item prop="password">
                <el-input type="password" v-model="loginForm.password" placeholder="请输入密码"></el-input>
            </el-form-item>
            <el-form-item style="width: 100%">
                <el-button type="primary" style="width:100%;border: none;" @click="login">登录</el-button>
                <!--to后接组件名-->
                <router-link to="register"><el-button type="primary" style="width: 40%;background: #505458;border: none">注册</el-button></router-link>
            </el-form-item>
        </el-form>
    </body>
</template>

<script>
export default {
    data: function () {
        return {
            loginForm: {
                loginName: "admin",
                password: "123",
            },
            rules: {
                loginName: [
                    {
                        required: true,
                        message: "用户名不能为空",
                        trigger: "blur",
                    },
                ],
                password: [
                    {
                        required: true,
                        message: "密码不能为空",
                        trigger: "blur",
                    },
                ],
            },
        };
    },
    methods: {
        login() {
            console.log(this.$store.state);
            this.$axios
                .post("/login", {
                    loginName: this.loginForm.loginName,
                    password: this.loginForm.password,
                })
                .then((successResponse) => {
                    if (successResponse.data.code === 200) {
                        //将loginForm中的值对应赋值到store中的user上，这样localStorage中就有了对应的loginName信息
                        this.$store.commit("login", this.loginForm);
                        //route相当于当前正在跳转的路由对象。。可以从里面获取name,path,params,query等
                        var path = this.$route.query.redirect;
                        console.log(path);
                        this.$router.replace({
                            path:
                                path === "/" || path === undefined
                                    ? "/index"
                                    : path,
                        });
                    }
                })
                .catch((failResponse) => {
                    console.log(failResponse.data.message);
                });
        },
    },
};
</script>

<style>
.login-wrap {
    background: url("../assets/img/eva1.jpg") no-repeat;
    background-position: center;
    height: 100%;
    width: 100%;
    background-size: cover;
    position: fixed;
}
body {
    margin: 0;
}
.login-container {
    border-radius: 15px;
    background-clip: padding-box;
    margin: 200px auto;
    width: 350px;
    padding: 35px 35px 15px 35px;
    background: #fff;
    border: 1px solid #eaeaea;
    box-shadow: 0 0 25px #cac6c6;
}
.login_title {
    margin: 0px auto 40px auto;
    text-align: center;
    color: #505458;
}
</style>
