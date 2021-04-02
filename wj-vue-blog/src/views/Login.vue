<template>
    <div class="login-wrap">
        <div class="wj-title">white jotter bolg登录</div>
        <div class="wj-login">
            <el-form :model="loginForm" :rules="rules" ref="ruleForm">
                <el-form-item prop="username">
                    <el-input v-model="loginForm.userName" placeholder="用户名"></el-input>
                </el-form-item>
                <el-form-item prop="password">
                    <el-input type="password" v-model="loginForm.password" placeholder="密码"></el-input>
                </el-form-item>
                <div class="login-btn">
                    <el-button type="primary" @click="login">登录</el-button>
                </div>
            </el-form>
        </div>
    </div>
</template>

<script>
export default {
    data: function () {
        return {
            loginForm: {
                userName: "admin",
                password: "123",
            },
            rules: {
                userName: [
                    {
                        required: true,
                        message: "请输入用户名",
                        trigger: "blur",
                    },
                ],
                passord: [
                    { required: true, message: "请输入密码", trigger: "blur" },
                ],
            },
        };
    },
    methods: {
        login() {
            this.axios.post('/login', {
                userName: this.loginForm.userName,
                password: this.loginForm.password
            })
        },
    },
};
</script>

<style scoped>
.login-wrap {
    position: absolute;
    background: url("../assets/img/eva1.jpg");
    background-attachment: fixed;
    background-position: center;
    background-size: cover;
    width: 100%;
    height: 100%;
}
.wj-title {
    position: absolute;
    top: 50%;
    width: 100%;
    margin-top: -230px;
    text-align: center;
    font-size: 30px;
    font-weight: 600;
    color: #fff;
}
.wj-login {
    position: absolute;
    left: 50%;
    top: 50%;
    width: 300px;
    height: 160px;
    margin-left: -190px;
    margin-top: -150px;
    padding: 40px;
    border-radius: 5px;
    background: #fff;
}
.login-btn {
    text-align: center;
}
.login-btn button {
    width: 100%;
    height: 36px;
}
</style>
