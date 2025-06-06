class NavigatorLoader {
    cells = [];
    adapter = null;
    appendCell(cell) {
        this.cells.push(cell);
        return this;
    }
    ;
    removeCell(cell) {
        this.cells.splice(this.cells.indexOf(cell), 1);
        return this;
    }
    ;
    forEach(callback) {
        this.cells.forEach(callback);
        return;
    }
    constructor() {
    }
}
export default NavigatorLoader;
