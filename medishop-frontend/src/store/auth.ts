import { UserTypeEnum } from '@/enums/medishopEnum'

import type { JWTResponse, User } from 'types/medishop/user'

const useAuthStore = defineStore(
  'medishop-auth',
  () => {
    const isAuthenticated = ref(false)
    const tokenInfo = ref({
      accessToken: '',
      refreshToken: '',
      expiredIn: '',
      tokenType: '',
    })
    const userInfo = ref({
      id: -1,
      username: '',
      password: '',
      authorities: [''],
      fullName: '',
      email: '',
      phoneNumber: '',
      address: '',
      ipAddress: '',
      ipRegion: '',
      userType: UserTypeEnum.GUEST,
      licenseImageUrl: '',
    })

    const resolveRes = (jwt: JWTResponse, user: User) => {
      tokenInfo.value.accessToken = jwt.accessToken
      tokenInfo.value.refreshToken = jwt.refreshToken
      tokenInfo.value.tokenType = jwt.tokenType
      tokenInfo.value.expiredIn = new Date(jwt.expiredIn).toLocaleString()
      userInfo.value.id = user.id
      userInfo.value.username = user.username
      userInfo.value.password = user.password
      userInfo.value.authorities = user.authorities
      userInfo.value.fullName = user.fullName
      userInfo.value.email = user.email
      userInfo.value.phoneNumber = user.phoneNumber
      userInfo.value.address = user.address
      userInfo.value.ipAddress = user.ipAddress
      userInfo.value.ipRegion = user.ipRegion
      userInfo.value.userType = user.userType
      userInfo.value.licenseImageUrl = user.licenseImageUrl
      isAuthenticated.value = true
    }
    return { isAuthenticated, tokenInfo, userInfo, resolveRes }
  },
  {
    persist: {
      afterRestore(context) {
        console.log(context)
      },
    },
  }
)
export default useAuthStore
