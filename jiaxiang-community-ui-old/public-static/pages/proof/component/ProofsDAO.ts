/*人员数据模型类*/
class ProofsDAO {
    public id: string;
    public name: string;
    public content: string;

    constructor(id?: string, name?: string, content?: string) {
        this.id = id;
        this.name = name;
        this.content = content;
    }
}

export default ProofsDAO;