import { UserTypeEnum } from '@/enums/medishopEnum'

import type { JWTResponse, User } from 'types/medishop/user'

const useAuthStore = defineStore(
  'medishop-auth',
  () => {
    const isAuthenticated = ref(false)
    const tokenInfo: JWTResponse = reactive({
      accessToken: '',
      refreshToken: '',
      expiredIn: 0,
      tokenType: '',
    })
    const userInfo: User = reactive({
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
      tokenInfo.accessToken = jwt.accessToken
      tokenInfo.refreshToken = jwt.refreshToken
      tokenInfo.tokenType = jwt.tokenType
      tokenInfo.expiredIn = new Date(jwt.expiredIn).getUTCMilliseconds()
      userInfo.id = user.id
      userInfo.username = user.username
      userInfo.password = user.password
      userInfo.authorities = user.authorities
      userInfo.fullName = user.fullName
      userInfo.email = user.email
      userInfo.phoneNumber = user.phoneNumber
      userInfo.address = user.address
      userInfo.ipAddress = user.ipAddress
      userInfo.ipRegion = user.ipRegion
      userInfo.userType = user.userType
      userInfo.licenseImageUrl = user.licenseImageUrl
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
