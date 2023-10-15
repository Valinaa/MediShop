<script setup lang="ts">
import { ElMessage } from 'element-plus'

import router from '@/router'

import defaultHttp from '@/api/http'
import useAuctionStore from '@/store/auction'

const { hasLogin } = useAuctionStore()
const formData = ref({
  account: '',
  password: '',
})

const formRules = {
  account: [{ required: true, message: '请输入用户名', trigger: 'blur' }],
  password: [{ required: true, message: '请输入密码', trigger: 'blur' }],
}

const submitForm = () => {
  defaultHttp
    .post({
      url: '/login',
      data: {
        account: formData.value.account,
        password: formData.value.password,
      },
    })
    .then((res) => {
      ElMessage.success('登录成功')
      router.push('/index')
      hasLogin(res.account)
    })
    .catch((err) => {
      ElMessage.error(err)
    })
}
</script>

<template>
  <div class="register-container">
    <div class="register-form">
      <h2>登录</h2>
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
            登录
          </el-button>
          <el-button
            type="warning"
            @click="router.push('/register')">
            点我去注册！
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
