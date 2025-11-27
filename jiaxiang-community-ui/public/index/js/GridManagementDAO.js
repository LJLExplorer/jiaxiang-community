class GridManagementDAO {
    id;
    community;
    icon;
    href;
    meta;
    constructor(id, community, icon, href, meta) {
        this.id = id;
        this.community = community;
        this.icon = icon;
        this.href = href;
        this.meta = meta;
    }
}
export default GridManagementDAO;
