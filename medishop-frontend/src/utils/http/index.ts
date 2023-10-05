/* eslint-disable complexity */
/* eslint-disable no-param-reassign */
// axios配置  可自行根据项目进行更改，只需更改该文件即可，其他文件可以不动
// The axios configuration can be changed according to the project, just change the file, other files can be left unchanged

import { ElMessage, ElMessageBox } from 'element-plus'

import { deepMerge, setObjToUrlParams } from '@/utils'

import { ContentTypeEnum, RequestEnum, ResultEnum } from '@/enums/httpEnum'
import { isString } from '@/utils/is'
import { useErrorLogStoreWithOut } from '@/store/errorLog'

import i18n from '../i18n'

import { formatRequestDate, joinTimestamp } from './helper'
import { VAxios } from './Axios'

import type { AxiosTransform, CreateAxiosOptions } from './axiosTransform'
import type { RequestOptions, Result } from 'types/axios'
import type { AxiosResponse } from 'axios'

const { t } = i18n.global
/**
 * @description: 数据处理，方便区分多种处理方式
 */
const transform: AxiosTransform = {
    /**
     * @description: 处理请求数据。如果数据不是预期格式，可直接抛出错误
     */
    transformRequestHook: (
        res: AxiosResponse<Result>,
        options: RequestOptions
    ) => {
        const { isTransformResponse, isReturnNativeResponse } = options
        // 是否返回原生响应头 比如：需要获取响应头时使用该属性
        if (isReturnNativeResponse) {
            return res
        }

        // 不进行任何处理，直接返回
        // 用于页面代码可能需要直接获取code，data，message这些信息时开启
        if (!isTransformResponse) {
            return res.data
        }

        // 错误的时候返回

        const { data: resp } = res
        if (!resp) {
            // return '[HTTP] Request has no return value';
            throw new Error(t('sys.api.apiRequestFailed'))
        }
        //  这里 code，data，message为 后台统一的字段，需要在 types.ts内修改为项目自己的接口返回格式
        const { code, data, message } = resp

        // 这里逻辑可以根据项目进行修改
        const hasSuccess = resp && Reflect.has(resp, 'code') && code === 200
        if (hasSuccess) {
            return data
        }

        // 在此处根据自己项目的实际情况对不同的code执行不同的操作
        // 如果不希望中断当前请求，请return数据，否则直接抛出异常即可
        let timeoutMsg = ''
        // TODO 超时时设置Token
        // const userStore = useUserStoreWithOut()
        switch (code) {
            case ResultEnum.TIMEOUT:
                timeoutMsg = t('sys.api.timeoutMessage')
                // userStore.setToken(undefined)
                // userStore.logout(true)
                break
            default:
                if (message) {
                    timeoutMsg = message
                }
        }

        // errorMessageMode=‘modal’的时候会显示modal错误弹窗，而不是消息提示，用于一些比较重要的错误
        // errorMessageMode='none' 一般是调用时明确表示不希望自动弹出错误提示
        if (options.errorMessageMode === 'modal')
            ElMessageBox.confirm(timeoutMsg, t('sys.api.errorTip'), {
                confirmButtonText: 'OK',
                type: 'error',
                center: true,
            }).then(() => {
                ElMessage({
                    type: 'warning',
                    message: 'Try to resolve your problem',
                })
            })
        else if (options.errorMessageMode === 'message')
            ElMessage.error(timeoutMsg)

        throw new Error(timeoutMsg || t('sys.api.apiRequestFailed'))
    },

    // 请求之前处理config
    beforeRequestHook: (config, options) => {
        const {
            apiUrl,
            joinPrefix,
            joinParamsToUrl,
            formatDate,
            joinTime = true,
            urlPrefix,
        } = options

        if (joinPrefix) {
            config.url = `${urlPrefix}${config.url}`
        }

        if (apiUrl && isString(apiUrl)) {
            config.url = `${apiUrl}${config.url}`
        }

        const params = config.params || {}
        const data = config.data || false
        formatDate && data && !isString(data) && formatRequestDate(data)
        if (config.method?.toUpperCase() === RequestEnum.GET) {
            if (!isString(params)) {
                // 给 get 请求加上时间戳参数，避免从缓存中拿数据。
                config.params = Object.assign(
                    params || {},
                    joinTimestamp(joinTime, false)
                )
            } else {
                // 兼容restful风格
                config.url = `${config.url + params}${joinTimestamp(
                    joinTime,
                    true
                )}`
                config.params = undefined
            }
        } else if (!isString(params)) {
            formatDate && formatRequestDate(params)
            if (
                Reflect.has(config, 'data') &&
                config.data &&
                Object.keys(config.data).length > 0
            ) {
                config.data = data
                config.params = params
            } else {
                // 非GET请求如果没有提供data，则将params视为data
                config.data = params
                config.params = undefined
            }
            if (joinParamsToUrl)
                config.url = setObjToUrlParams(config.url as string, {
                    ...config.params,
                    ...config.data,
                })
        } else {
            // 兼容restful风格
            config.url += params
            config.params = undefined
        }
        return config
    },

    /**
     * @description: 请求拦截器处理
     */
    requestInterceptors: (config, options) => {
        // 请求之前处理config
        // TODO 请求前携带token
        // const token = getToken()
        const token = ''
        if (
            token &&
            (config as Recordable)?.requestOptions?.withToken !== false
        ) {
            // jwt token
            ;(config as Recordable).headers.Authorization =
                options.authenticationScheme
                    ? `${options.authenticationScheme} ${token}`
                    : token
        }
        return config
    },

    /**
     * @description: 响应拦截器处理
     */
    responseInterceptors: (res: AxiosResponse<any>) => {
        return res
    },

    /**
     * @description: 响应错误处理
     */
    responseInterceptorsCatch: (error: any) => {
        const errorLogStore = useErrorLogStoreWithOut()
        errorLogStore.addAjaxErrorInfo(error)
        const { code, message, config } = error || {}
        const errorMessageMode =
            config?.requestOptions?.errorMessageMode || 'none'
        // const msg: string = response?.data?.error?.message ?? ''
        const err: string = error?.toString?.() ?? ''
        let errMessage = ''

        try {
            if (code === 'ECONNABORTED' && message.includes('timeout'))
                errMessage = t('sys.api.apiTimeoutMessage')

            if (err?.includes('Network Error'))
                errMessage = t('sys.api.networkExceptionMsg')

            if (errMessage) {
                if (errorMessageMode === 'modal')
                    ElMessageBox.confirm(errMessage, t('sys.api.errorTip'), {
                        confirmButtonText: 'OK',
                        type: 'error',
                        center: true,
                    }).then(() => {
                        ElMessage({
                            type: 'warning',
                            message: 'Try to resolve your problem',
                        })
                    })
                else if (errorMessageMode === 'message')
                    ElMessage.error(errMessage)
                // createMessage?.error(errMessage)

                return Promise.reject(error)
            }
        } catch (error) {
            ElMessageBox.confirm(errMessage, t('sys.api.errorTip'), {
                confirmButtonText: 'OK',
                type: 'error',
                center: true,
            }).then(() => {
                ElMessage({
                    type: 'warning',
                    message: 'Try to resolve your problem',
                })
            })
        }

        return Promise.reject(error)
    },
}

export default function createAxios(opt?: Partial<CreateAxiosOptions>) {
    return new VAxios(
        deepMerge(
            {
                // See https://developer.mozilla.org/en-US/docs/Web/HTTP/Authentication#authentication_schemes
                // authentication schemes，e.g: Bearer
                // authenticationScheme: 'Bearer',
                authenticationScheme: '',
                timeout: 10 * 1000,
                // 基础接口地址
                // baseURL: globSetting.apiUrl,

                headers: { 'Content-Type': ContentTypeEnum.JSON },
                // 如果是form-data格式
                // headers: { 'Content-Type': ContentTypeEnum.FORM_URLENCODED },
                // 数据处理方式
                transform,
                // 配置项，下面的选项都可以在独立的接口请求中覆盖
                requestOptions: {
                    // 默认将prefix 添加到url
                    joinPrefix: true,
                    // 是否返回原生响应头 比如：需要获取响应头时使用该属性
                    isReturnNativeResponse: false,
                    // 需要对返回数据进行处理
                    isTransformResponse: true,
                    // post请求的时候添加参数到url
                    joinParamsToUrl: false,
                    // 格式化提交参数时间
                    formatDate: true,
                    // 消息提示类型
                    errorMessageMode: 'message',
                    // 接口地址
                    apiUrl: '',
                    // 接口拼接地址
                    urlPrefix: '',
                    //  是否加入时间戳
                    joinTime: true,
                    // 忽略重复请求
                    ignoreCancelToken: true,
                    // 是否携带token
                    withToken: true,
                },
            },
            opt || {}
        )
    )
}
