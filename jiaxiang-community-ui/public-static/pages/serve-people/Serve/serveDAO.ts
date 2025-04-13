/*人员数据模型类*/
class ServeDAO {
    id: string;
    title: string;
    phone: string;
    time: string;
    address: string;
    image: string;
    description: string;
    content: any[];
    rules: any[];

    constructor(id?: string, title?: string, phone?: string, time?: string, address?: string, image?: string, description?: string, content?: any[], rules?: any[]) {
        this.id = id;
        this.title = title;
        this.phone = phone;
        this.time = time;
        this.address = address;
        this.image = image;
        this.description = description;
        this.content = content;
        this.rules = rules;
    }
}

export default ServeDAO;