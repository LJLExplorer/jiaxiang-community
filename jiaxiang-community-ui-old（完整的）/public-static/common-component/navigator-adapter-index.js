class NavigatorAdapterIndex {
    container;
    navigatorLoader;
    clear() {
        this.container.innerHTML = "";
    }
    display() {
        const that = this;
        this.navigatorLoader.forEach(function cb(cell) {
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
                cell.sub.forEach((subCell) => {
                    subContainer.appendChild(cb(subCell));
                });
            }
            return cellElement;
        });
    }
    constructor(container, navigatorLoader) {
        this.container = container;
        this.navigatorLoader = navigatorLoader;
    }
}
export default NavigatorAdapterIndex;
