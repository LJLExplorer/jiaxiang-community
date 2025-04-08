import NavigatorAdapter from "../Navigator/NavigatorAdapter.js";
import NavigatorLoader from "../Navigator/NavigatorLoader.js";
import NavigatorCell from "../Navigator/NavigatorCell.js";

class NavigatorAdapterIndex implements NavigatorAdapter {
    public container: HTMLElement;
    public navigatorLoader: NavigatorLoader;

    public clear(): void {
        this.container.innerHTML = "";
    }
    public display(): void {
        const that = this;

        this.navigatorLoader.forEach(function cb(cell: NavigatorCell){
            const cellElement = document.createElement("nav");
            cellElement.classList.add("navigator-cell");

            const content = document.createElement("span");
            content.textContent = cell.content;
            cellElement.appendChild(content);

            cellElement.addEventListener("click", cell.callback);
            that.container.appendChild(cellElement);

            if (cell.sub.length > 0) {
                cellElement.classList.add("multi-level");

                const subContainer = document.createElement("div");
                cellElement.appendChild(subContainer);

                cell.sub.forEach((subCell: NavigatorCell) => {
                    subContainer.appendChild(cb(subCell));
                })
            }

            return cellElement;
        });
    }

    constructor(container: HTMLElement, navigatorLoader: NavigatorLoader) {
        this.container = container;
        this.navigatorLoader = navigatorLoader;
    }

}

export default NavigatorAdapterIndex;