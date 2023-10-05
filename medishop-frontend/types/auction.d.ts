export interface GoodsInfo {
    accountId: number
    endTime: string
    ensure: string
    goodName: string
    goodType: number
    goodsDec: string
    id: number
    nowPrice: number
    oimei: string
    packMail: string
    pic: string
    pricePlus: number
    salerName: string
    startPrice: number
    startTime: string
    status: number
}

export interface ShopCart {
    goodName: string
    nowPrice: number
    status: number
}

export interface AuctionInfo {
    gid: number
    goodName: string
    nowPrice: number
    pricePlus: number
    createTime: string
    status: number
}
export interface UserInfo {
    account: string
    name: string
    identity: number
    sex: string
    location: string
    phone: string
    email: string
    personalSign: string
    love: string
}
export interface Goods {
    id: number
    good_name: string
    start_price: number
    start_time: string
    pic: string
    price_plus: number
    end_time: string
    account_id: number
    goods_dec: string
    status: string
    now_price: number
    saler_name: string
    goodType: string
    pack_mail: boolean
    oimei: string
    ensure: string
    Exshoppingcart: number
}
