export interface JSONPOption {
  param?: string
  timeout?: number
  prefix?: string
  name?: string
  jsonpCallback?: string | boolean
  jsonpCallbackFunction?: string
}
