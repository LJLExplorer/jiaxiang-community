/*人员数据模型类*/
class MattersDAO {
    id;
    title;
    file;
    constructor(id, title, file) {
        this.id = id;
        this.title = title;
        this.file = file;
    }
}
export default MattersDAO;
