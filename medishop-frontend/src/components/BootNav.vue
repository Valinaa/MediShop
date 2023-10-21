<script lang="ts" setup>
import { ElMessage, ElMessageBox } from 'element-plus'

import { Icon } from '@iconify/vue'

import router from '@/router'

import avatar from '@/assets/avatar.jpg'
import { saveLanguage } from '@/utils/i18n'
import defaultHttp from '@/api/http'
import useAuctionStore from '@/store/auction'
import Timer from '@/utils/times'

import type { AuctionInfo, GoodsInfo, UserInfo } from 'types/auction'

const isDark = useDark()
const toggleDark = useToggle(isDark)
const { t, availableLocales, locale } = useI18n()
const { user } = storeToRefs(useAuctionStore())
const dialogFormVisible = ref(false)
const formLabelWidth = '140px'
const form = reactive({
  accountId: user.value.accountId,
  businessName: user.value.name,
  identity: user.value.identity,
  goodName: '',
  goodType: 2,
  startPrice: 0,
  pricePlus: 0,
  status: -1,
  startTime: '',
  endTime: '',
  goodsDec: '',
})
function toggleLocales() {
  const locales = availableLocales
  locale.value = locales[(locales.indexOf(locale.value) + 1) % locales.length]
  saveLanguage(locale.value) // 保存切换后的语言到本地存储
}

function goPage(path: string) {
  const aid = user.value.accountId
  const url = ref('')
  switch (path) {
    case '/userInfo':
      url.value = `/getAccountInfo`
      break
    case '/myGoods':
      url.value = '/getGoodsList'
      break
    // TODO
    case '/rank':
      url.value = '/getAuctionRank/gid'
      break
    case '/myAuctions':
      url.value = `/getAuctionRecord/${aid}`
      break
    // case '/shopCart':
    //     url.value = `/getShoppingCartList/${aid}`
    //     break
  }
  if (!(url.value === '/getAccountInfo')) {
    defaultHttp
      .get({
        url: url.value,
      })
      .then((res: Array<GoodsInfo | AuctionInfo | UserInfo>) => {
        const goods = encodeURIComponent(JSON.stringify(res))
        ElMessage.success('get list success!')
        const paths = `${path}/${goods}`
        void router.push(paths)
      })
      .catch((err: any) => {
        ElMessage.error(err)
      })
  } else {
    defaultHttp
      .post({
        url: url.value,
        data: {
          id: user.value.accountId,
          account: user.value.account,
          name: user.value.name,
          identity: user.value.identity,
        },
      })
      .then((res: Array<AuctionInfo>) => {
        const goods = encodeURIComponent(JSON.stringify(res))
        ElMessage.success('get list success!')
        const paths = `${path}/${goods}`
        void router.push(paths)
      })
      .catch((err: any) => {
        ElMessage.error(err)
      })
  }
}
function submitAddGood() {
  const url = '/saveGood'
  defaultHttp
    .post({
      url,
      data: form,
    })
    .then((res: any) => {
      ElMessage.success('添加成功！')
      const timer = new Timer(Date.now().toString())
      const targetTime = new Date(form.endTime).getTime()
      const needTime = (targetTime - Date.now()) / 1000
      timer.start(needTime, '拍卖结束！')
      timer.watchDestroy()
      goPage('/myGoods')
    })
    .catch((err: any) => {
      ElMessage.error(err)
    })
  dialogFormVisible.value = false
}
function goGitHub() {
  window.open('https://github.com/Valinaa')
}
function goIntro() {
  window.location.href = 'https://intro.valinaa-wei.tech'
}
function getEmails() {
  void ElMessageBox.alert(
    `${t('email content')}<br/>
        <ul>
            <li><b>valinaa@valinaa-wei.tech<b/></li>
            <li><b>1114854003@qq.com<b/></li>
            <li><b>ecustck@163.com<b/></li>
            <li><b>20002605@mail.ecust.edu.cn<b/></li>
            <li><b>valinaa.chenkang@gmail.com<b/></li>
            <li><b>kang.chen2@zatech.com<b/></li>
        <ul/>`,
    t('contact info'),
    {
      autofocus: true,
      confirmButtonText: 'OK',
      dangerouslyUseHTMLString: true,
      callback: () => {
        ElMessage({
          type: 'success',
          message: `${t('thank for visit')} ^_^`,
        })
      },
    }
  )
}
</script>

<template>
  <b-navbar
    class="font-semibold font-mono"
    toggleable="lg"
    :dark="isDark"
    :variant="isDark ? 'dark' : 'light'">
    <b-navbar-brand>
      <div
        style="
          padding-right: 16px;
          padding-left: 20px;
          place-items: center center;
        ">
        <el-avatar
          :size="55"
          :src="avatar" />
      </div>
    </b-navbar-brand>

    <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>

    <b-collapse
      id="nav-collapse"
      is-nav>
      <b-navbar-nav class="mr-auto">
        <b-nav-item @click="router.push('/index')">
          <span class="span-tab">
            <i class="menu-item-icon">
              <Icon
                icon="solar:home-outline"
                width="20.15"
                height="20.15" />
            </i>
            {{ t('medishop.home') }}
          </span>
        </b-nav-item>
        <b-nav-item @click="goPage('/userInfo')">
          <span class="span-tab">
            <i class="menu-item-icon">
              <Icon
                icon="mingcute:fork-spoon-line"
                width="20.15"
                height="20.15" />
            </i>
            {{ t('medishop.userInfo') }}
          </span>
        </b-nav-item>
        <b-nav-item @click="dialogFormVisible = true">
          <span class="span-tab">
            <i class="menu-item-icon">
              <Icon
                icon="solar:document-add-broken"
                width="20.15"
                height="20.15" />
            </i>
            {{ t('medishop.addGoods') }}
          </span>
        </b-nav-item>
        <b-nav-item @click="goPage('/myAuctions')">
          <span class="span-tab">
            <i class="menu-item-icon">
              <i-tabler-bulb />
            </i>
            {{ t('medishop.myAuctions') }}
          </span>
        </b-nav-item>
        <b-nav-item @click="goPage('/myGoods')">
          <span class="span-tab">
            <i class="menu-item-icon">
              <i-tabler-bulb />
            </i>
            {{ t('medishop.myGoods') }}
          </span>
        </b-nav-item>
        <!-- <b-nav-item @click="goPage('/shopCart')">
                    <span class="span-tab">
                        <i class="menu-item-icon">
                            <i-tabler-bulb />
                        </i>
                        {{ t('medishop.shopCart') }}
                    </span>
                </b-nav-item> -->
      </b-navbar-nav>

      <b-navbar-nav class="ml-auto">
        <b-nav-item class="res-composition">
          <span>
            <el-tooltip
              :content="isDark ? t('change light') : t('change dark')"
              placement="top">
              <button
                class="mx-2 !outline-none"
                @click="toggleDark()">
                <i-ph-cloud-moon-bold
                  v-if="isDark"
                  class="icon-nav" />
                <i-ph-sun-horizon-bold
                  v-else
                  class="icon-nav" />
              </button>
            </el-tooltip>
          </span>
        </b-nav-item>
        <b-nav-item class="res-composition lg:mr-4">
          <span>
            <el-tooltip
              :content="t('change lang')"
              placement="top">
              <button
                class="icon-btn mx-2"
                @click="toggleLocales()">
                <i-la-language class="icon-nav" />
              </button>
            </el-tooltip>
          </span>
        </b-nav-item>
        <b-nav-item @click="goIntro()">
          <span class="span-tab">
            <i style="font-size: 1.05em">
              <i-carbon-user-speaker />
            </i>
            {{ t('intro') }}
          </span>
        </b-nav-item>
        <b-nav-item-dropdown :text="t('contact me')">
          <b-dropdown-item @click="goGitHub()">
            <i class="menu-item-icon"><i-iconoir-github /></i>
            GitHub
          </b-dropdown-item>
          <b-dropdown-item @click="getEmails">
            <i class="menu-item-icon">
              <i-line-md-email-twotone />
            </i>
            {{ t('emails') }}
          </b-dropdown-item>
        </b-nav-item-dropdown>
      </b-navbar-nav>
    </b-collapse>
  </b-navbar>
  <el-dialog
    v-model="dialogFormVisible"
    title="添加商品">
    <el-form :model="form">
      <el-form-item
        label="商品名称"
        :label-width="formLabelWidth">
        <el-input
          v-model="form.goodName"
          autocomplete="off" />
      </el-form-item>
      <el-form-item
        label="商品描述"
        :label-width="formLabelWidth">
        <el-input
          v-model="form.goodsDec"
          autocomplete="off" />
      </el-form-item>
      <el-form-item
        label="起拍价格"
        :label-width="formLabelWidth">
        <el-input-number
          v-model="form.startPrice"
          :precision="2"
          :step="1" />
      </el-form-item>
      <el-form-item
        label="最低加价"
        :label-width="formLabelWidth">
        <el-input-number
          v-model="form.pricePlus"
          :precision="2"
          :step="1" />
      </el-form-item>
      <el-form-item
        label="开始时间"
        :label-width="formLabelWidth">
        <el-date-picker
          v-model="form.startTime"
          type="datetime"
          value-format="YYYY-MM-DD HH:mm:ss"
          placeholder="选择开始时间" />
      </el-form-item>
      <el-form-item
        label="结束时间"
        :label-width="formLabelWidth">
        <el-date-picker
          v-model="form.endTime"
          type="datetime"
          value-format="YYYY-MM-DD HH:mm:ss"
          placeholder="选择结束时间" />
      </el-form-item>
    </el-form>
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取消</el-button>
        <el-button
          type="primary"
          @click="submitAddGood()">
          确认
        </el-button>
      </span>
    </template>
  </el-dialog>
</template>

<style scoped>
.avatar-img {
  width: 55px;
  height: 55px;
  border-radius: 50%;
  object-fit: cover;
}

.res-composition button {
  border: 0;
  background-color: transparent;
}

.bg-light,
.bg-dark {
  background-color: transparent !important;
}

.icon-nav {
  font-size: 1.3em;
}

.menu-item-icon {
  font-size: 1.2em;
}

.res-composition {
  width: 60px;
}

.span-tab {
  margin-right: 10px;
}

.navbar-nav {
  --bs-nav-link-padding-y: 0 !important;
  --bs-nav-link-hover-color: var(--el-color-primary);
}

.container * {
  --bs-gutter-x: 0 !important;
}

@media screen and (width <= 992px) {
  .nav-item {
    margin: 6px auto;
  }

  .res-composition {
    width: 100px !important;
  }
}
</style>
