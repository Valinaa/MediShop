<script setup lang="ts">
import { ElMessage } from 'element-plus'

import type { ShopCart } from 'types/auction'

const route = useRoute()
const { t } = useI18n()
const cartsList = (): ShopCart[] => {
  const list = route.params.data
  if (Array.isArray(list)) {
    ElMessage.warning('返回的数据不是字符串！')
    return new Array<ShopCart>()
  }
  const result = JSON.parse(decodeURIComponent(list))
  return result
}
</script>

<template>
  <div>
    <el-table
      :data="cartsList()"
      style="width: 100%"
      max-height="600px"
      row-key="id"
      stripe
      lazy
      highlight-current-row
      border>
      <el-table-column
        prop="good_name"
        :label="t('medishop.goodName')"
        width="150px"
        align="center" />
      <el-table-column
        prop="now_price"
        :label="t('medishop.nowPrice')"
        width="90px"
        align="center" />
      <el-table-column
        prop="status"
        :label="t('medishop.status')"
        width="80px"
        align="center" />
      <!-- <el-table-column
                :label="t('operation')"
                width="250px"
                align="center">
                <template #default="{ row }">
                    <el-button
                        class="mr-1"
                        type="primary"
                        size="large"
                        @click="ViewGood(row)">
                        {{ t('medishop.viewGood') }}
                    </el-button>
                </template>
            </el-table-column> -->
    </el-table>
  </div>
</template>
