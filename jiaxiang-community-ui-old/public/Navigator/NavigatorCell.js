class NavigatorCell {
    content;
    sub;
    /*
    * 点击事件的回调函数
    * */
    callback = () => {
        return;
    };
    constructor(content, callback) {
        this.content = content;
        this.sub = [];
        if (callback) {
            this.callback = callback;
        }
    }
}
export default NavigatorCell;
