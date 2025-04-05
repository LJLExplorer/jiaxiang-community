class NavigatorAdapterIndex {
    container;
    navigatorLoader;
    display() {
        const that = this;
        this.navigatorLoader.forEach((cell) => {
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
                cell.sub.forEach((subCell) => {
                    const subCellElement = document.createElement("nav");
                    subCellElement.classList.add("navigator-sub-cell");
                    subCellElement.innerText = subCell.content;
                });
            }
        });
    }
    constructor(container, navigatorLoader) {
        this.container = container;
        this.navigatorLoader = navigatorLoader;
    }
}
export default NavigatorAdapterIndex;
