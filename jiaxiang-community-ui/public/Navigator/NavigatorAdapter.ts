import NavigatorLoader from "./NavigatorLoader.js";

interface NavigatorAdapter {
    navigatorLoader: NavigatorLoader;
    container: HTMLElement;

    display(): void;     //用于将navigatorLoader中的数据渲染到页面上

}

export default NavigatorAdapter;