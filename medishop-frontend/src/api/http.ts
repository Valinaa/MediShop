import CreateAxios from '../utils/http/index'
import {ApiVersionEnum} from "@/enums/httpEnum.ts";

const apiVersionPrefix = '/api' + ApiVersionEnum.V1
export default defaultHttp = CreateAxios({
  // baseURL: 'https://118.89.71.118',
  requestOptions: {
    apiUrl: '/medishop',
  },
})
export const userHttp = CreateAxios({
  requestOptions: {
    apiUrl: apiVersionPrefix,
    urlPrefix: '/user',
  },
})

export const medicineHttp = CreateAxios({
  requestOptions: {
    apiUrl: apiVersionPrefix,
    urlPrefix: '/medicine',
  }
})

export const medicineDetailHttp = CreateAxios({
  requestOptions: {
    apiUrl: apiVersionPrefix,
    urlPrefix: '/medicine/detail',
  }
})
