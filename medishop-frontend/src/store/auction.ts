import { defineStore } from 'pinia'

const useAuctionStore = defineStore(
    'auction',
    () => {
        const isAuthenticated = ref(false)
        const user = ref({
            account: '',
            name: '',
            accountId: -1,
            identity: -1,
            // 注册时间
            regTime: '',
        })

        function hasLogin(obj: any) {
            isAuthenticated.value = true
            user.value.account = obj.account
            user.value.name = obj.name
            user.value.accountId = obj.id
            user.value.identity = obj.identity
            user.value.regTime = obj.regTime
        }
        return { isAuthenticated, user, hasLogin }
    },
    {
        persist: {
            key: 'auction',
        },
    }
)
export default useAuctionStore
