<!-- eslint-disable camelcase -->
<script setup lang="ts">
import { ElMessage, ElNotification } from 'element-plus'

const { t } = useI18n()
ElMessage.success({
  message: t('welcome'),
  duration: 1000,
})
ElNotification({
  title: 'Issue',
  message: `${t(
    'issue'
  )}<br/><b><i>https://github.com/Valinaa/Medishop/issues<i/><b/>`,
  dangerouslyUseHTMLString: true,
  duration: 5000,
})
</script>

<template>
  <div class="mx-auto mt-10 max-w-3xl container"></div>
</template>

<style lang="scss" scoped>
i {
  padding-top: 4px;
}

a {
  @apply text-sky-400 hover:(text-sky-600) transition-all ease-out duration-100;
}

.button-link-a {
  color: white !important;
}
</style>
