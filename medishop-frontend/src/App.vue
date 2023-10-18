<script lang="ts" setup>
import zhCn from 'element-plus/lib/locale/lang/zh-cn'

const locale = zhCn
</script>

<template>
  <el-config-provider :locale="locale">
    <el-container class="app-container">
      <el-header>
        <BootNav />
      </el-header>
      <el-container>
        <!-- <el-aside :width="Collapse ? '90.5px' : '220px'">
                    <el-menu
                        class="side-bar font-bold font-mono"
                        default-active="/"
                        :collapse="Collapse"
                        @mouseover="goCollapse"
                        @mouseleave="leaveCollapse">
                        <el-menu-item
                            index="/"
                            @click="goIndexPage()">
                            <i>
                                <IFluentHome20Regular />
                            </i>
                            <span>{{ t('home') }}</span>
                        </el-menu-item>
                        <el-sub-menu
                            index="1"
                            class="side-menu">
                            <template #title>
                                <i class="sub-menu-icon">
                                    <i-ep-trend-charts />
                                </i>
                                <span>{{ t('data analysis platform') }}</span>
                            </template>
                            <el-menu-item-group
                                :title="t('meta data management')">
                                <el-menu-item index="1-1">
                                    <i class="menu-item-icon">
                                        <i-vaadin-table />
                                    </i>
                                    <span>{{ t('view public data') }}</span>
                                </el-menu-item>
                                <el-menu-item index="1-2">
                                    <i class="menu-item-icon">
                                        <i-ep-edit />
                                    </i>
                                    <span>{{ t('editing interface') }}</span>
                                </el-menu-item>
                            </el-menu-item-group>
                            <el-menu-item-group
                                :title="t('analysis management')">
                                <el-menu-item index="1-3">
                                    <i class="menu-item-icon">
                                        <i-ep-edit />
                                    </i>
                                    <span>{{ t('editing interface') }}</span>
                                </el-menu-item>
                            </el-menu-item-group>
                            <el-menu-item-group
                                :title="t('dashboard management')">
                                <el-menu-item index="1-4">
                                    <i class="menu-item-icon">
                                        <i-vaadin-table />
                                    </i>
                                    <span>{{ t('view dashboard info') }}</span>
                                </el-menu-item>
                                <el-menu-item index="1-5">
                                    <i class="menu-item-icon">
                                        <i-vscode-icons-file-type-qlikview />
                                    </i>
                                    <span>
                                        {{ t('view default dashboard') }}
                                    </span>
                                </el-menu-item>
                                <el-menu-item index="1-6">
                                    <i class="menu-item-icon">
                                        <i-ep-edit-pen />
                                    </i>
                                    <span>
                                        {{ t('edit default dashboard') }}
                                    </span>
                                </el-menu-item>
                            </el-menu-item-group>
                            <el-sub-menu index="1-7">
                                <template #title>item four</template>
                                <el-menu-item index="1-7-1">
                                    item one
                                </el-menu-item>
                            </el-sub-menu>
                        </el-sub-menu>
                        <el-menu-item index="2">
                            <i>
                                <IFluentHome20Regular />
                            </i>
                            <span>Navigator Two</span>
                        </el-menu-item>
                        <el-menu-item
                            index="3"
                            disabled>
                            <i>
                                <IFluentHome20Regular />
                            </i>
                            <span>Navigator Three</span>
                        </el-menu-item>
                    </el-menu>
                </el-aside> -->
        <!-- <el-container> -->
        <el-main>
          <router-view />
        </el-main>
        <el-footer>
          <TheFooter />
        </el-footer>
        <!-- </el-container> -->
      </el-container>
    </el-container>
  </el-config-provider>
</template>

<style lang="scss" scoped>
.side-bar {
  border-right: solid 1px var(--el-border-color);
}

.el-menu-item * {
  vertical-align: middle;
}

.el-sub-menu * {
  vertical-align: middle;
}

.sub-menu-icon {
  display: block;
  width: 50px;
  text-align: center;
}

.menu-item-icon {
  display: block;
  width: 25px !important;
  margin-bottom: 4px !important;
  text-align: left !important;
}

.el-menu-item > i {
  width: 50px;
  text-align: center;
}

.el-menu--collapse {
  min-width: 82px;
}

.el-menu--collapse .el-sub-menu--collapse > .el-menu {
  flex: 1;
  overflow: hidden;
}

.side-bar:not(.el-menu--collapse) {
  width: 220px;
}
</style>

<style lang="scss">
.app-container {
  // z-index: 100;
  //     flex: 1;
  // display: flex;
  // flex-direction: column;
  height: 100%;
  background-image: linear-gradient(326deg, #bb667f, #bd8f9d, #b8b6bb, #addddb);
}

.dark .app-container {
  background-image: linear-gradient(315deg, #003160, #36466b, #575c76, #767481);
}

.el-footer {
  margin-top: auto; /* 将 el-footer 推至页面底部 */
  text-align: center;
}

.el-aside {
  display: flex;
  width: auto;

  --el-menu-icon-width: 0;
}

.el-header {
  padding: 0;

  --el-header-height: inherit;
}

.el-main {
  min-height: 610px;
}

.navbar {
  --bs-navbar-padding-y: 0 !important;
}

.dropdown-toggle {
  margin-bottom: 2px;
}

.dark .dropdown-menu {
  --bs-dropdown-bg: black;
}

.dark .dropdown-item:hover,
.dropdown-item:focus {
  background-color: #5d6d7e !important;
}

.dropdown-item:hover,
.dropdown-item:focus {
  background-color: #ccd1d1 !important;
}

.dark .dropdown-item {
  color: white !important;
}

.nav-item {
  width: 135px;
}

.container,
.container-fluid,
.container-xxl,
.container-xl,
.container-lg,
.container-md,
.container-sm {
  --bs-gutter-x: 0 !important;
}

:root {
  --el-menu-bg-color: transparent;
  --el-menu-border-color: transparent;
}
</style>
