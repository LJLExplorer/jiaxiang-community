/*人员数据模型类*/
class HonorDAO {
    id: string;
    content: string;

    constructor(id?: string, content?: string) {
        this.id = id;
        this.content = content;
    }
}

export default HonorDAO;