<script setup lang="ts">
import { ElMessage } from 'element-plus'

import router from '@/router'

import defaultHttp from '@/api/http'

const formData = ref({
    account: '',
    name: '',
    identity: '2',
    password: '',
})

const formRules = {
    account: [{ required: true, message: '请输入用户名', trigger: 'blur' }],
    name: [{ required: true, message: '请输入昵称', trigger: 'blur' }],
    identity: [{ required: true, message: '请输入身份', trigger: 'blur' }],
    password: [{ required: true, message: '请输入密码', trigger: 'blur' }],
}

const submitForm = () => {
    defaultHttp
        .post({
            url: '/register',
            data: {
                account: formData.value.account,
                name: formData.value.name,
                identity: Number(formData.value.identity),
                password: formData.value.password,
            },
        })
        .then((res) => {
            console.log(res)
            ElMessage.success('注册成功')
            router.push('/')
        })
        .catch((err) => {
            ElMessage.error(`意外错误！${err}`)
        })
}
</script>

<template>
    <div class="register-container">
        <div class="register-form">
            <h2 class="font-mono">注册</h2>
            <el-form
                :model="formData"
                :rules="formRules"
                label-width="80px">
                <el-form-item
                    label="用户名"
                    prop="account">
                    <el-input v-model="formData.account"></el-input>
                </el-form-item>
                <el-form-item
                    label="姓名"
                    prop="name">
                    <el-input v-model="formData.name"></el-input>
                </el-form-item>
                <el-form-item
                    label="身份"
                    prop="identity">
                    <el-select v-model="formData.identity">
                        <el-option
                            label="普通成员"
                            value="2" />
                        <el-option
                            label="VIP成员"
                            value="4" />
                    </el-select>
                </el-form-item>
                <el-form-item
                    label="密码"
                    prop="password">
                    <el-input
                        v-model="formData.password"
                        type="password"></el-input>
                </el-form-item>
                <el-form-item>
                    <el-button
                        type="primary"
                        @click="submitForm">
                        注册
                    </el-button>
                    <el-button
                        type="warning"
                        @click="router.push('/')">
                        点我去登录！
                    </el-button>
                </el-form-item>
            </el-form>
        </div>
    </div>
</template>

<style scoped>
.register-container {
    display: flex;
    justify-content: center;
    align-items: center;
    height: 80vh;

    /* background-image: url('your-background-image.jpg'); */
    background-position: center;
    background-size: cover;
}

.register-form {
    width: 400px;
    padding: 20px;
    border-radius: 8px;
    background-color: transparent;
    box-shadow: 0 0 10px rgb(0 0 0 / 20%);
}

.register-form h2 {
    margin-bottom: 20px;
    text-align: center;
}

.el-form-item {
    font-weight: 600;
}
</style>
