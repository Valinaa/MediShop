<script lang="ts" setup>
import { ElMessage } from 'element-plus'

import router from '@/router'

import defaultHttp from '@/api/http'

import { UserTypeEnum } from '@/enums/medishopEnum'

import type { UserRequest } from 'types/medishop/user'

const formData: UserRequest = reactive({
  username: '',
  password: '',
  authorities: [''],
  fullName: '',
  email: '',
  phoneNumber: '',
  address: '',
  ipAddress: null,
  ipRegion: null,
  userType: UserTypeEnum.GUEST,
  licenseImageUrl: '',
})

const formRules = {
  username: [{ required: true, message: '请输入用户名', trigger: 'blur' }],
  fullName: [{ required: true, message: '请输入姓名', trigger: 'blur' }],
  userType: [{ required: true, message: '请选择身份', trigger: 'blur' }],
  password: [{ required: true, message: '请输入密码', trigger: 'blur' }],
}

const submitForm = () => {
  // grecaptcha.ready(function () {
  //   grecaptcha
  //     .execute(import.meta.env.VITE_LOCALHOST_RECAPTCHA_SITE_KEY, {
  //       action: 'submit',
  //     })
  //     .then(function (token: string) {
  //       defaultHttp
  //         .post({
  //           url: '/verifyRecaptcha',
  //           data: {
  //             token,
  //           },
  //         })
  //         .then(() => {
  //           ElMessage.success(`人机验证成功！`)
  defaultHttp
    .post({
      url: '/register',
      data: {
        username: formData.username,
        fullName: formData.fullName,
        userType: formData.userType,
        password: formData.password,
      },
    })
    .then((res) => {
      console.log(res)
      ElMessage.success('注册成功')
      router.push('/login')
    })
    .catch((err) => {
      ElMessage.error(`意外错误！${err}`)
    })
  //         })
  //         .catch((err) => {
  //           ElMessage.error(`人机验证失败！${err}`)
  //         })
  //     })
  // })
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
          prop="username">
          <el-input v-model="formData.username"></el-input>
        </el-form-item>
        <el-form-item
          label="姓名"
          prop="fullName">
          <el-input v-model="formData.fullName"></el-input>
        </el-form-item>
        <el-form-item
          label="身份"
          prop="userType">
          <el-select v-model="formData.userType">
            <el-option
              label="顾客"
              value="1" />
            <el-option
              label="商家"
              value="2" />
            <el-option
              label="管理员"
              value="3" />
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
