/**
 * @description: Exception related enumeration
 */
export enum ExceptionEnum {

  PAGE_NOT_ACCESS = 403,

  PAGE_NOT_FOUND = 404,

  ERROR = 500,

  NET_WORK_ERROR = 10000,

  // No data on the page. In fact, it is not an exception page
  PAGE_NOT_DATA = 10100,
}

export enum ErrorTypeEnum {
  VUE = 'vue',
  SCRIPT = 'script',
  RESOURCE = 'resource',
  AJAX = 'ajax',
  PROMISE = 'promise',
}
