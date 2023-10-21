<!-- eslint-disable camelcase -->
<script lang="ts" setup>
import { ElMessage, ElMessageBox } from 'element-plus'

import defaultHttp from '@/api/http'
import useAuctionStore from '@/store/auction'

import type { Goods } from 'types/auction'

const { user } = storeToRefs(useAuctionStore())
const route = useRoute()
const maxPrice = ref(0)
const businessId = ref(0)
const goods = (): Goods => {
  const list = route.params.data
  if (Array.isArray(list)) {
    ElMessage.warning('返回的数据不是字符串！')
    return {
      id: -1,
      good_name: '',
      start_price: -1,
      start_time: '',
      pic: '',
      price_plus: -1,
      end_time: '',
      account_id: -1,
      goods_dec: '',
      status: '',
      now_price: -1,
      business_name: '',
      goodType: '',
      pack_mail: false,
      oimei: '',
      ensure: '',
      Exshoppingcart: -1,
    }
  }
  const result = JSON.parse(decodeURIComponent(list))
  return result
}

defaultHttp
  .get({
    url: `/getRecentRecord/${goods().id}`,
  })
  .then((res) => {
    console.log(res)
    maxPrice.value = res.nowPrice
    businessId.value = res.businessId
  })
  .catch((err) => {
    ElMessage.error(`获取账户信息失败!${err})`)
  })
const doAuction = (value: number) => {
  const accountName = ref('')
  const url = ref(`/auction`)
  defaultHttp
    .get({
      url: `/getAccountByAccountId/${user.value.accountId}`,
    })
    .then((res) => {
      console.log(res.name)
      accountName.value = res.name
      defaultHttp
        .post({
          url: url.value,
          data: {
            gid: goods().id,
            businessId: businessId.value,
            goodName: goods().good_name,
            startPrice: maxPrice.value,
            myPlus: value,
            accountId: user.value.accountId,
            accountName: accountName.value,
            status: goods().status,
          },
        })
        .then(() => {
          ElMessage.success('竞价成功!')
          // window.location.reload()
        })
        .catch((err) => {
          ElMessage.error(`竞价失败!${err}`)
        })
    })
    .catch((err) => {
      ElMessage.error(`获取账户信息失败!${err})`)
    })
}

const auction = () => {
  ElMessageBox.prompt('请填写您的加价', '参与竞拍', {
    confirmButtonText: '确认',
    cancelButtonText: '取消',
    inputType: 'number',
    inputValidator: (value: string) => {
      if (!value) {
        return '请输入加价'
      }
      if (Number(value) < goods().price_plus) {
        return '加价必须大于最低限额'
      }
      if (value.indexOf('.') + 1 > 0) {
        if (value.length - value.indexOf('.') > 3) {
          return '最多只能保留两位小数'
        }
      }
      return true
    },
  })
    .then(({ value }) => {
      doAuction(Number(value))
    })
    .catch((err) => {
      ElMessage({
        type: 'error',
        message: err,
      })
    })
}

// const changeCart = () => {
//     const url = ref(`/addShopCart/${goods().account_id}/${goods().id}`)
//     defaultHttp
//         .get({
//             url: url.value,
//         })
//         .then((res) => {
//             console.log(res)
//             ElMessage.success('cart change success!')
//             // window.location.reload()
//         })
//         .catch((err) => {
//             ElMessage.error('cart change failed!')
//             console.log(err)
//         })
// }
</script>

<template>
  <el-card>
    <div class="card-container font-mono">
      <div class="card-header">
        <!-- <img
                    :src="goods().pic"
                    class="card-image"
                    alt="Goods Image" /> -->
        <div>
          <h2 class="mb-2 text-center">{{ goods().good_name }}</h2>
          <p>
            <b>起拍价:</b>
            {{ goods().start_price }}
          </p>
          <p>
            <b>开始时间:</b>
            {{ goods().start_time }}
          </p>
        </div>
      </div>
      <div class="card-body mt-5">
        <p>
          <b>商品描述:</b>
          {{ goods().goods_dec }}
        </p>
        <p>
          <b>最低加价:</b>
          <el-tag type="danger">{{ goods().price_plus }}</el-tag>
        </p>
        <p>
          <b>现价:</b>
          <el-tag type="warning">{{ maxPrice }}</el-tag>
        </p>
        <p>
          <b>卖家:</b>
          {{ goods().business_name }}
        </p>
      </div>
      <div class="card-footer">
        <el-button
          type="primary"
          size="large"
          @click="auction">
          竞拍
        </el-button>
        <!-- <el-button
                    v-show="goods().Exshoppingcart === 0"
                    type="primary"
                    @click="changeCart">
                    加入购物车
                </el-button>
                <el-button
                    v-show="goods().Exshoppingcart > 0"
                    type="danger"
                    @click="changeCart">
                    移出购物车
                </el-button> -->
      </div>
    </div>
  </el-card>
</template>

<style scoped>
.card-container {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  width: 400px;
  height: 80vh;
  padding: 20px;
  margin: auto;
  border-radius: 8px;
  background-position: center;
  background-size: cover;
  background-color: transparent;
  box-shadow: 0 0 10px rgb(0 0 0 / 20%);
}

.el-card.is-always-shadow {
  box-shadow: none !important;
}

.el-card {
  border: none !important;
  background-color: transparent !important;
}
</style>
