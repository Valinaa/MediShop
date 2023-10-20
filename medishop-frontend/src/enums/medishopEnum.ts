export enum MedicineStatusEnum {
  // "缺货"
  OUT_OF_STOCK = '0',
  // "审核中"
  UNDER_REVIEW = '1',
  // "可购买"
  AVAILABLE = '2',
  // "售罄"
  SELL_OUT = '3',
}

export enum UserTypeEnum {
  // "游客"
  GUEST = '0',
  // "消费者"
  CUSTOMER = '1',
  // "商家"
  BUSINESS = '2',
  // "管理员"
  ADMIN = '3',
}
