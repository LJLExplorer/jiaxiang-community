class DataInitializer {
    list = new Map();
    regist(target, callback) {
        if (this.list.has(target)) {
            this.list.get(target).push(callback);
        }
        else {
            this.list.set(target, [callback]);
        }
    }
}
export default DataInitializer;
