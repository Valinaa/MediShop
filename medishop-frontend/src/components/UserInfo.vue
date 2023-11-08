<script setup lang="ts">
// eslint-disable-next-line import/no-extraneous-dependencies
import { Iphone, Location, Tickets, User } from '@element-plus/icons-vue'
import { ElMessage } from 'element-plus'

import defaultHttp from '@/api/http'
import useAuthStore from '@/store/auth'

import { UserTypeEnum } from '@/enums/medishopEnum'

import type { User as UserInfo } from 'types/medishop/user'

const { userInfo } = storeToRefs(useAuthStore())
const route = useRoute()
const dialogFormVisible = ref(false)
const formLabelWidth = '140px'
const user = (): UserInfo => {
  const list = route.params.data
  if (Array.isArray(list)) {
    ElMessage.warning('返回的数据不是字符串！')
    return {
      id: null,
      username: '',
      password: '',
      authorities: [''],
      userType: UserTypeEnum.GUEST,
      fullName: '',
      email: '',
      phoneNumber: '',
      address: '',
      ipAddress: '',
      ipRegion: '',
      licenseImageUrl: '',
    }
  }
  const result = JSON.parse(decodeURIComponent(list))
  return result
}
const form = reactive({})

const iconStyle = computed(() => {
  const marginMap = {
    large: '8px',
    default: '6px',
    small: '4px',
  }
  return {
    marginRight: marginMap.large || marginMap.default,
  }
})
function submitUpdate() {
  const url = '/updateAccountInfo'
  defaultHttp
    .post({
      url,
      data: form,
    })
    .then((res: any) => {
      ElMessage.success('更新成功！')
      window.location.reload()
      console.log(res)
    })
    .catch((err: any) => {
      ElMessage.error(err)
    })
  dialogFormVisible.value = false
}
</script>

<template>
  <el-descriptions
    title="个人信息"
    :column="3"
    size="large"
    border>
    <template #extra>
      <el-button
        type="primary"
        @click="dialogFormVisible = true">
        修改
      </el-button>
    </template>
    <el-descriptions-item>
      <template #label>
        <div class="cell-item">
          <el-icon :style="iconStyle">
            <User />
          </el-icon>
          Account
        </div>
      </template>
      {{ user().account }}
    </el-descriptions-item>
    <el-descriptions-item>
      <template #label>
        <div class="cell-item">
          <el-icon :style="iconStyle">
            <Iphone />
          </el-icon>
          Telephone
        </div>
      </template>
      {{ user().phone === null ? 'unknown' : user().phone }}
    </el-descriptions-item>
    <el-descriptions-item>
      <template #label>
        <div class="cell-item">
          <el-icon :style="iconStyle">
            <User />
          </el-icon>
          name
        </div>
      </template>
      {{ user().name }}
    </el-descriptions-item>
    <el-descriptions-item>
      <template #label>
        <div class="cell-item">
          <el-icon :style="iconStyle">
            <Iphone />
          </el-icon>
          性别
        </div>
      </template>
      {{ user().sex === null ? 'unknown' : user().sex }}
    </el-descriptions-item>
    <el-descriptions-item>
      <template #label>
        <div class="cell-item">
          <el-icon :style="iconStyle">
            <Location />
          </el-icon>
          Email
        </div>
      </template>
      {{ user().email === null ? 'unknown' : user().email }}
    </el-descriptions-item>
    <el-descriptions-item>
      <template #label>
        <div class="cell-item">
          <el-icon :style="iconStyle">
            <Tickets />
          </el-icon>
          爱好
        </div>
      </template>
      <el-tag size="small">
        {{ user().love === null ? 'unknown' : user().love }}
      </el-tag>
    </el-descriptions-item>
    <el-descriptions-item>
      <template #label>
        <div class="cell-item">
          <el-icon :style="iconStyle">
            <Location />
          </el-icon>
          地址
        </div>
      </template>
      {{ user().location === null ? 'unknown' : user().location }}
    </el-descriptions-item>
    <el-descriptions-item>
      <template #label>
        <div class="cell-item">
          <el-icon :style="iconStyle">
            <Location />
          </el-icon>
          个性签名
        </div>
      </template>
      {{ user().personalSign === null ? 'unknown' : user().personalSign }}
    </el-descriptions-item>
  </el-descriptions>
  <el-dialog
    v-model="dialogFormVisible"
    title="修改个人信息">
    <el-form :model="form">
      <el-form-item
        label="昵称"
        :label-width="formLabelWidth">
        <el-input
          v-model="form.name"
          autocomplete="off" />
      </el-form-item>
      <el-form-item
        label="性别"
        :label-width="formLabelWidth">
        <el-radio-group v-model="form.sex">
          <el-radio
            label="男"
            size="large">
            男
          </el-radio>
          <el-radio
            label="女"
            size="large">
            女
          </el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item
        label="地址"
        :label-width="formLabelWidth">
        <el-input
          v-model="form.location"
          autocomplete="off" />
      </el-form-item>
      <el-form-item
        label="电话号码"
        :label-width="formLabelWidth">
        <el-input v-model="form.phone" />
      </el-form-item>
      <el-form-item
        label="Email"
        :label-width="formLabelWidth">
        <el-input v-model="form.email" />
      </el-form-item>
      <el-form-item
        label="个性签名"
        :label-width="formLabelWidth">
        <el-input v-model="form.personalSign" />
      </el-form-item>
      <el-form-item
        label="爱好"
        :label-width="formLabelWidth">
        <el-input v-model="form.love" />
      </el-form-item>
    </el-form>
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取消</el-button>
        <el-button
          type="primary"
          @click="submitUpdate()">
          确认
        </el-button>
      </span>
    </template>
  </el-dialog>
</template>

<style scoped>
.el-descriptions {
  margin-top: 20px;
}

.cell-item {
  display: flex;
  align-items: center;
}

.margin-top {
  margin-top: 20px;
}
</style>
