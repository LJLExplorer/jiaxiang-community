/*人员数据模型类*/
class MattersDAO {
    public id: string;
    public title: string;
    public file: string;

    constructor(id?: string, title?: string, file?: string) {
        this.id = id;
        this.title = title;
        this.file = file;
    }
}

export default MattersDAO;