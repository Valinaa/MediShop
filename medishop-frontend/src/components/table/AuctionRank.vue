<script setup lang="ts">
import { ElMessage } from 'element-plus'

import useAuctionStore from '@/store/auction'

interface Rank {
  price: number
  name: string
  rank: number
}

const { user } = storeToRefs(useAuctionStore())
const route = useRoute()
const { t } = useI18n()
const goodsList = (): Rank[] => {
  const list = route.params.data
  if (Array.isArray(list)) {
    ElMessage.warning('返回的数据不是字符串！')
    return new Array<Rank>()
  }
  const result = JSON.parse(decodeURIComponent(list))
  console.log(result)
  return result
}
</script>

<template>
  <div>
    <el-table
      :data="goodsList()"
      style="width: 100%"
      max-height="600px"
      row-key="rank"
      stripe
      lazy
      highlight-current-row
      border>
      <el-table-column
        :label="t('medishop.rank')"
        width="200px"
        align="center">
        <template #default="{ row }">
          <div
            v-show="row.name === user.name"
            style="display: flex; align-items: center">
            <el-tag type="danger">{{ row.rank }}</el-tag>
          </div>
          <div
            v-show="row.name !== user.name"
            style="display: flex; align-items: center">
            {{ row.rank }}
          </div>
        </template>
      </el-table-column>
      <el-table-column
        :label="t('medishop.rankName')"
        width="200px"
        align="center">
        <template #default="{ row }">
          <div
            v-show="row.name === user.name"
            style="display: flex; align-items: center">
            <el-tag type="danger">{{ row.name }}</el-tag>
          </div>
          <div
            v-show="row.name !== user.name"
            style="display: flex; align-items: center">
            {{ row.name }}
          </div>
        </template>
      </el-table-column>
      <el-table-column
        :label="t('medishop.rankPrice')"
        width="200px"
        align="center">
        <template #default="{ row }">
          <div
            v-show="row.name === user.name"
            style="display: flex; align-items: center">
            <el-tag type="danger">{{ row.price }}</el-tag>
          </div>
          <div
            v-show="row.name !== user.name"
            style="display: flex; align-items: center">
            {{ row.price }}
          </div>
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>
