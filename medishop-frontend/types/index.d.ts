import { ErrorTypeEnum } from '@/enums/exceptionEnum'

// Error-log information
export interface ErrorLogInfo {
  // Type of error
  type: ErrorTypeEnum
  // Error file
  file: string
  // Error name
  name?: string
  // Error message
  message: string
  // Error stack
  stack?: string
  // Error detail
  detail: string
  // Error url
  url: string
  // Error time
  time?: string
}
declare interface Fn<T, R = T> {
  (...arg: T[]): R
}

declare type TargetContext = '_self' | '_blank'
