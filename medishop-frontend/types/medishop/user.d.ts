import { UserTypeEnum } from '@/enums/medishopEnum'

/** 用户注册实体 */
export interface UserRequest {
  /** 用户名 */
  username: string
  /** 密码 */
  password: string
  /** 用户权限列表 */
  authorities: string[]
  /** 姓名 */
  fullName: string
  /** 邮箱 */
  email: string
  /** 手机号码 */
  phoneNumber: string
  /** 用户地址 */
  address: string
  /** 用户IP地址(IPV4 或 IPV6) */
  ipAddress: null
  /** 用户IP地址对应地区 */
  ipRegion: null
  userType: UserTypeEnum
  /** 用户证件照片访问链接 */
  licenseImageUrl: string
}

/** 用户实体 */
export interface User extends UserRequest {
  /** User Id */
  id: number
  ipAddress: string
  ipRegion: string
}

/** 登陆成功返回实体 */
export interface JWTResponse {
  /** accessToken */
  accessToken: string
  /** refreshToken */
  refreshToken: string
  /** token类型，默认为Bearer */
  tokenType: string
  /** 到期时间 */
  expiredIn: number
}
