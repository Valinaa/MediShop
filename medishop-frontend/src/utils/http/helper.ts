/* eslint-disable no-param-reassign */
import { isObject, isString } from '@/utils/isType'

const DATE_TIME_FORMAT = 'YYYY-MM-DD HH:mm'

export function joinTimestamp<T extends boolean>(
  join: boolean,
  restful: T
): T extends true ? string : object

export function joinTimestamp(join: boolean, restful = false): string | object {
  if (!join) {
    return restful ? '' : {}
  }
  const now = new Date().getTime()
  if (restful) {
    return `?_t=${now}`
  }

  return { _t: now }
}

/**
 * @description: Format request parameter time
 */
export function formatRequestDate(params: Recordable) {
  if (!isObject(params)) {
    return
  }

  for (const key in params) {
    const format = params[key]?.format ?? null
    if (format && typeof format === 'function') {
      params[key] = params[key].format(DATE_TIME_FORMAT)
    }

    if (isString(key)) {
      const value = params[key]
      if (value) {
        params[key] = isString(value) ? value.trim() : value
      }
    }

    if (isObject(params[key])) {
      formatRequestDate(params[key])
    }
  }
}
