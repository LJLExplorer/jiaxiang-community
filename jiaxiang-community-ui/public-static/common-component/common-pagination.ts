//根据后端现改吧
class CommonPagination extends HTMLElement{
    private pageInfo: HTMLDivElement;
    private arrowLeft: HTMLDivElement;
    private arrowRight: HTMLDivElement;
    private pageNum: number;
    private pageSize: number;
    private currentPage: number;
    private totalPage: number;
    private pageChangeEvent: Event;
    private pageChangeCallback: Function;

    private initStyleSheet() {
        const stylesheet = new CSSStyleSheet();
        stylesheet.replaceSync(`
.pagination {
    display: flex;
    align-items: center;
    justify-content: space-between;
    width: 100%;
    margin-top: 30px;
}

.pagination .page-info {
    font-size: 20px;
    color: #00c3ff;
}

.pagination .arrow {
    cursor: pointer;
    font-size: 40px;
    transition: color 0.3s ease;
}

.pagination .arrow.enabled {
    color: #00c3ff;
}

.pagination .arrow.disabled {
    color: #ccc;
    cursor: default;
}
        `);

        document.adoptedStyleSheets = [stylesheet];
    }

    private render() {
        this.innerHTML = `
        <div class="pagination">
            <span class="arrow" id="prev-arrow">&#8592;</span>
            <div class="page-info">
                <span class="page-number" id="current-page">1</span> / <span id="total-pages">1</span>
                共 <span id="total-items">0</span> 条
            </div>
            <span class="arrow" id="next-arrow">&#8594;</span>
        </div>
        `;

    }
    constructor() {
        super();

        this.initStyleSheet();
        this.render();

    }
}

customElements.define('common-pagination', CommonPagination);

export default CommonPagination;