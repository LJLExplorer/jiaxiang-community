import NavigatorAdapter from "../../../Navigator/NavigatorAdapter.js";
import NavigatorLoader from "../../../Navigator/NavigatorLoader.js";
import NavigatorCell from "../../../Navigator/NavigatorCell.js";

class NavigatorAdapterIndex implements NavigatorAdapter {
    public container: HTMLElement;
    public navigatorLoader: NavigatorLoader;

    public display(): void {
        const that = this;

        this.navigatorLoader.forEach((cell: NavigatorCell) => {
            const cellElement = document.createElement("nav");
            cellElement.classList.add("navigator-cell");
            cellElement.innerText = cell.content;
            cellElement.addEventListener("click", cell.callback);
            that.container.appendChild(cellElement);

            // 子导航，未测试通过
            if (cell.sub.length > 0) {
                const subContainer = document.createElement("div");
                subContainer.classList.add("navigator-sub-container");
                cellElement.appendChild(subContainer);
                cell.sub.forEach((subCell: NavigatorCell) => {
                    const subCellElement = document.createElement("nav");
                    subCellElement.classList.add("navigator-sub-cell");
                    subCellElement.innerText = subCell.content;
                })
            }
        });
    }

    constructor(container: HTMLElement, navigatorLoader: NavigatorLoader) {
        this.container = container;
        this.navigatorLoader = navigatorLoader;
    }

}

export default NavigatorAdapterIndex;