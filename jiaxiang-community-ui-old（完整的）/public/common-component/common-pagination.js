class CommonPagination extends HTMLElement {
    pageInfo;
    arrowLeft;
    arrowRight;
    pageChangeEvent;
    pageChangeCallback;
    pageSize; // 每页显示的条目数
    __currentPage; // 当前页码
    __totalPage; // 总页数
    __itemsNum; //总共多少项
    currentPageContainer = document.createElement('span');
    totalPageContainer = document.createElement('span');
    itemsNumContainer = document.createElement('span');
    get currentPage() {
        return this.__currentPage;
    }
    set currentPage(currentPage) {
        this.currentPageContainer.innerText = currentPage.toString();
        this.arrowLeft.classList.toggle("enabled", currentPage > 1);
        this.arrowLeft.classList.toggle("disabled", currentPage === 1);
        this.arrowRight.classList.toggle("enabled", currentPage < this.totalPage);
        this.arrowRight.classList.toggle("disabled", currentPage === this.totalPage);
        this.__currentPage = currentPage;
    }
    get totalPage() {
        return this.__totalPage;
    }
    set totalPage(totalPage) {
        this.totalPageContainer.innerText = totalPage.toString();
        this.__totalPage = totalPage;
    }
    get itemsNum() {
        return this.__itemsNum;
    }
    set itemsNum(itemsNum) {
        this.itemsNumContainer.innerText = itemsNum.toString();
        this.__itemsNum = itemsNum;
    }
    jumpTo(pageNum) {
        if (pageNum < 1 || pageNum > this.totalPage) {
            return;
        }
        this.currentPage = pageNum;
        this.dispatchEvent(new Event("jumpto"));
    }
    ;
    next() {
        this.jumpTo(this.currentPage + 1);
        this.dispatchEvent(new Event("next"));
    }
    prev() {
        this.jumpTo(this.currentPage - 1);
        this.dispatchEvent(new Event("pre"));
    }
    initStyleSheet() {
        const stylesheet = new CSSStyleSheet();
        stylesheet.replaceSync(`
common-pagination .pagination {
    display: flex;
    align-items: center;
    justify-content: space-between;
    width: 100%;
    margin-top: 30px;
}

common-pagination .pagination .page-info {
    font-size: 20px;
    color: #00c3ff;
}

common-pagination .pagination .arrow {
    cursor: pointer;
    font-size: 40px;
    transition: color 0.3s ease;
}

common-pagination .pagination .arrow.enabled {
    color: #00c3ff;
}

common-pagination .pagination .arrow.disabled {
    color: #ccc;
    cursor: default;
}
        `);
        document.adoptedStyleSheets = [stylesheet];
    }
    render() {
        this.innerHTML = `
        <div class="pagination">
            <span class="arrow" id="prev-arrow">&#8592;</span>
            <div class="page-info">
                <span class="page-number" id="current-page">
                    <slot name="current-page"></slot> /
                    <slot name="total-page"></slot>
                    共 <slot name="items"></slot>
                </span> 条
            </div>
            <span class="arrow" id="next-arrow">&#8594;</span>
        </div>
        `;
        const currentPage = this.querySelector('slot[name="current-page"]');
        const totalPage = this.querySelector('slot[name="total-page"]');
        const items = this.querySelector('slot[name="items"]');
        currentPage.replaceWith(this.currentPageContainer);
        totalPage.replaceWith(this.totalPageContainer);
        items.replaceWith(this.itemsNumContainer);
        this.pageInfo = this.querySelector('.page-info');
        this.arrowLeft = this.querySelector('#prev-arrow');
        this.arrowRight = this.querySelector('#next-arrow');
        this.arrowLeft.addEventListener('click', () => {
            this.prev();
            this.pageChangeEvent && this.dispatchEvent(this.pageChangeEvent);
            this.pageChangeCallback && this.pageChangeCallback();
        });
        this.arrowRight.addEventListener('click', () => {
            this.next();
            this.pageChangeEvent && this.dispatchEvent(this.pageChangeEvent);
            this.pageChangeCallback && this.pageChangeCallback();
        });
    }
    constructor(currentPage, totalPage, itemsNum, pageSize) {
        super();
        /*    this.currentPageContainer = document.createElement('span');
              this.totalPageContainer = document.createElement('span');
              this.itemsNumContainer = document.createElement('span');
      */
        this.initStyleSheet();
        this.render();
        this.totalPage = totalPage;
        this.currentPage = currentPage;
        this.itemsNum = itemsNum;
        this.pageSize = pageSize;
    }
}
customElements.define('common-pagination', CommonPagination);
export default CommonPagination;
