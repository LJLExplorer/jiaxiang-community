/*人员数据模型类*/
class CommissionerDAO {
    public id: string;
    public name: string;
    public role: string;
    public duty: string;
    public image: string;
    public dutyContent: string[];

    constructor(id?: string, name?: string, role?: string, duty?: string, image?: string, dutyContent?: string[]) {
        this.id = id;
        this.name = name;
        this.role = role;
        this.duty = duty;
        this.image = image;
        this.dutyContent = dutyContent;
    }
}

export default CommissionerDAO;