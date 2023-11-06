/**
 * Independent time operation tool to facilitate subsequent switch to dayjs
 */
import { format } from 'date-fns'

const STANDARD_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX"
const DATE_TIME_FORMAT = 'yyyy-MM-dd HH:mm:ss'
const DATE_FORMAT = 'yyyy-MM-dd '

export function formatToDateTime(
  date: Date | number = Date.now(),
  formatType = DATE_TIME_FORMAT
): string {
  return format(date, formatType)
}

export function formatToDate(
  date: Date | number = Date.now(),
  formatType = DATE_FORMAT
): string {
  return format(date, formatType)
}

export function formatToStandardDate(
  date: Date | number = Date.now(),
  formatType = STANDARD_DATE_FORMAT
): string {
  return format(date, formatType)
}
