import { UserTypeEnum } from '@/enums/medishopEnum'

export interface User {
  id: number
  username: string
  password: string
  fullName: string
  authorities: string[]
  email: string
  phoneNumber: string
  address: string
  ipAddress: string
  ipRegion: string
  userType: UserTypeEnum
  licenseImageUrl: string
}
