import { ElMessageBox } from 'element-plus'

export default class Timer {
    private timer: number | any = null
    private countdownTime = ref<number>(0) // 倒计时时间，单位为秒

    constructor(private readonly id: string) {}

    start(time: number, message: string): void {
        this.countdownTime.value = time
        // if (this.timer === null) {
        this.timer = setInterval(() => {
            this.countdownTime.value--
            if (this.countdownTime.value === 0) {
                this.showMessageBox(message)
                this.stop()
            }
        }, 1000)
        // }
    }

    stop(): void {
        if (this.timer !== null) {
            clearInterval(this.timer)
            this.timer = null
        }
    }

    showMessageBox(message: string): void {
        ElMessageBox.alert(message, '祝贺！', {
            type: 'success',
            confirmButtonText: '已收到',
        })
    }

    // 监听组件的销毁事件，停止计时器
    watchDestroy(): void {
        watchEffect((onInvalidate) => {
            onInvalidate(() => {
                this.stop()
            })
        })
    }

    getCountdownTime(): number {
        return this.countdownTime.value
    }
}
