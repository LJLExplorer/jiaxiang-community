class NavigatorCell {
    public content: string;
    public sub: NavigatorCell[];

    /*
    * 点击事件的回调函数
    * */
    public callback: (event: MouseEvent) => void = () => {
        return;
    };

    constructor(content: string, callback?: (event: MouseEvent) => void) {
        this.content = content;
        this.sub = [];
        if (callback) {
            this.callback = callback;
        }
    }
}

export default NavigatorCell;