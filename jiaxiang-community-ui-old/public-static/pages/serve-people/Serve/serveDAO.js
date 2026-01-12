/*人员数据模型类*/
class ServeDAO {
    id;
    title;
    phone;
    time;
    address;
    image;
    description;
    content;
    rules;
    constructor(id, title, phone, time, address, image, description, content, rules) {
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
