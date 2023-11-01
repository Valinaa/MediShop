import { ApiVersionEnum } from '@/enums/httpEnum'

import CreateAxios from '../utils/http/index'

const apiVersionPrefix = `/api${ApiVersionEnum.V1}`

const defaultHttp = CreateAxios({
  requestOptions: {
    apiUrl: apiVersionPrefix,
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
  },
})

export const medicineDetailHttp = CreateAxios({
  requestOptions: {
    apiUrl: apiVersionPrefix,
    urlPrefix: '/medicine/detail',
  },
})

export default defaultHttp
