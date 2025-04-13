import NavigatorCell from "./NavigatorCell.js";
import NavigatorAdapter from "./NavigatorAdapter.js";

class NavigatorLoader {
    public cells: NavigatorCell[] = [];
    public adapter: NavigatorAdapter = null;

    public appendCell(cell: NavigatorCell): this {
        this.cells.push(cell);
        return this;
    };

    public removeCell(cell: NavigatorCell): this {
        this.cells.splice(this.cells.indexOf(cell), 1);
        return this;
    };

    public forEach(callback: (value: NavigatorCell, index?: number, array?: NavigatorCell[]) => void): void {
        this.cells.forEach(callback);
        return;
    }

    constructor() {

    }
}

export default NavigatorLoader;