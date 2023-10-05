<script setup lang="ts">
import { ElMessage } from 'element-plus'

import router from '@/router'

import defaultHttp from '@/api/http'

import type { AuctionInfo } from 'types/auction'

const route = useRoute()
const { t } = useI18n()
const auctionsList = (): AuctionInfo[] => {
    const list = route.params.data
    if (Array.isArray(list)) {
        ElMessage.warning('返回的数据不是字符串！')
        return new Array<AuctionInfo>()
    }
    const result = JSON.parse(decodeURIComponent(list))
    console.log(result)
    return result
}
function ViewRank(good: AuctionInfo) {
    const url = ref(`/getAuctionRank/${good.gid}`)
    defaultHttp
        .get({
            url: url.value,
        })
        .then((res) => {
            console.log(res)
            ElMessage.success('get auctions rank success!')
            router.push(
                `/auctionRank/${encodeURIComponent(JSON.stringify(res))}`
            )
        })
        .catch((err) => {
            console.log(err)
        })
}
</script>

<template>
    <div>
        <el-table
            :data="auctionsList()"
            style="width: 100%"
            max-height="600px"
            row-key="id"
            stripe
            lazy
            highlight-current-row
            border>
            <el-table-column
                :label="t('auction.goodInfo')"
                align="center">
                <el-table-column
                    prop="good_name"
                    :label="t('auction.goodName')"
                    width="100px"
                    align="center" />
                <el-table-column
                    prop="now_price"
                    :label="t('auction.nowPrice')"
                    width="60px"
                    align="center" />
            </el-table-column>
            <el-table-column
                prop="my_plus"
                :label="t('auction.pricePlus')"
                width="80px"
                align="center" />
            <el-table-column
                :label="t('auction.createTime')"
                width="200px"
                align="center">
                <template #default="{ row }">
                    <div style="display: flex; align-items: center">
                        <i-ep-timer style="margin-right: 10px" />
                        {{ row.create_time }}
                    </div>
                </template>
            </el-table-column>
            <el-table-column
                prop="status"
                :label="t('auction.status')"
                width="80px"
                align="center" />
            <el-table-column
                :label="t('operation')"
                width="150px"
                align="center">
                <template #default="{ row }">
                    <el-button
                        class="mb-1"
                        type="primary"
                        size="large"
                        @click="ViewRank(row)">
                        {{ t('auction.viewRank') }}
                    </el-button>
                </template>
            </el-table-column>
        </el-table>
    </div>
</template>
