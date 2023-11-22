<script lang="ts" setup>
import { ElMessage } from 'element-plus'

import router from '@/router'

import defaultHttp from '@/api/http'
import useAuctionStore from '@/store/auction'

import type { GoodsInfo } from 'types/auction'

const timer = ref()
const { user } = storeToRefs(useAuctionStore())
const route = useRoute()
const { t } = useI18n()
const goodsList = (): GoodsInfo[] => {
  const list = route.params.data
  if (Array.isArray(list)) {
    ElMessage.warning('返回的数据不是字符串！')
    return new Array<GoodsInfo>()
  }
  const result = JSON.parse(decodeURIComponent(list))
  return result
}
const updateStatus = () => {
  const currentTime = new Date()

  for (const item of goodsList()) {
    const startTime = new Date(item.startTime)
    const endTime = new Date(item.endTime)

    if (currentTime < startTime) {
      // 当前时间小于开始时间，不做任何操作
    } else if (currentTime >= startTime && currentTime < endTime) {
      // 当前时间在开始时间和结束时间之间，将状态改为1
      item.status = 1
    } else {
      // 当前时间超过结束时间，将状态改为2，并不再遍历该值
      item.status = 2
    }
  }
}
const startTimer = () => {
  timer.value = setInterval(updateStatus, 1000) // 每秒钟执行一次
}

const stopTimer = () => {
  clearInterval(timer.value)
}

onMounted(() => {
  startTimer()
})
onUnmounted(() => {
  stopTimer()
})

function ViewGood(good: GoodsInfo) {
  const url = ref(`/getGoodInfoById/${good.id}/${user.value.accountId}`)
  defaultHttp
    .get({
      url: url.value,
    })
    .then((res) => {
      console.log(res)
      ElMessage.success('get goods success!')
      router.push(`/goodDesc/${encodeURIComponent(JSON.stringify(res))}`)
    })
    .catch((err) => {
      ElMessage.error('get goods failed!')
      console.log(err)
    })
}

function ViewRank(good: GoodsInfo) {
  const url = ref(`/getAuctionRank/${good.id}`)
  defaultHttp
    .get({
      url: url.value,
    })
    .then((res) => {
      console.log(res)
      ElMessage.success('get auctions rank success!')
      router.push(`/auctionRank/${encodeURIComponent(JSON.stringify(res))}`)
    })
    .catch((err) => {
      console.log(err)
    })
}
</script>

<template>
  <div>
    <el-table
      :data="goodsList()"
      max-height="600px"
      row-key="id"
      lazy
      stripe
      highlight-current-row
      border
      style="width: 100%">
      <el-table-column
        :label="t('medishop.startTime')"
        align="center"
        width="190px">
        <template #default="{ row }">
          <div style="display: flex; align-items: center">
            <i-ep-timer style="margin-right: 10px" />
            {{ row.startTime }}
          </div>
        </template>
      </el-table-column>
      <el-table-column
        :label="t('medishop.endTime')"
        align="center"
        width="190px">
        <template #default="{ row }">
          <div style="display: flex; align-items: center">
            <i-ep-timer style="margin-right: 10px" />
            {{ row.endTime }}
          </div>
        </template>
      </el-table-column>
      <el-table-column
        :label="t('medishop.businessName')"
        align="center"
        prop="businessName"
        width="90px" />
      <el-table-column
        :label="t('medishop.goodInfo')"
        align="center">
        <el-table-column
          :label="t('medishop.goodName')"
          align="center"
          prop="goodName"
          width="100px"></el-table-column>
        <el-table-column
          :label="t('medishop.goodType')"
          align="center"
          prop="goodType"
          width="90px"></el-table-column>
        <el-table-column
          :label="t('medishop.goodsDec')"
          align="center"
          prop="goodsDec"
          width="100px"></el-table-column>
        <el-table-column
          :label="t('medishop.nowPrice')"
          align="center"
          prop="nowPrice"
          width="100px"></el-table-column>
        <el-table-column
          :label="t('medishop.startPrice')"
          align="center"
          prop="startPrice"
          width="100px"></el-table-column>
      </el-table-column>
      <el-table-column
        :label="t('medishop.pricePlus')"
        align="center"
        prop="pricePlus"
        width="160px" />
      <el-table-column
        :label="t('medishop.status')"
        align="center"
        prop="status"
        width="80px" />
      <el-table-column
        :label="t('operation')"
        align="center"
        width="150px">
        <template #default="{ row }">
          <el-button
            class="mb-1"
            size="large"
            type="primary"
            @click="ViewRank(row)">
            {{ t('medishop.viewRank') }}
          </el-button>
          <br />
          <el-button
            :disabled="row.status === 0"
            class="mt-1"
            size="large"
            type="success"
            @click="ViewGood(row)">
            {{ t('medishop.viewGood') }}
          </el-button>
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>
