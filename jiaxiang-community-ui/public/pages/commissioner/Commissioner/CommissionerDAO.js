/*人员数据模型类*/
class CommissionerDAO {
    id;
    name;
    role;
    duty;
    image;
    dutyContent;
    constructor(id, name, role, duty, image, dutyContent) {
        this.id = id;
        this.name = name;
        this.role = role;
        this.duty = duty;
        this.image = image;
        this.dutyContent = dutyContent;
    }
}
export default CommissionerDAO;
