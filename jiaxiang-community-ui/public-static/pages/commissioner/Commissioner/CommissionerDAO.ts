/*人员数据模型类*/
class CommissionerDAO {
    public name: string;
    public role: string;
    public duty: string;
    public image: string;
    public dutyContent: string[];

    constructor(name?: string, role?: string, duty?: string, image?: string, dutyContent?: string[]) {
        this.name = name;
        this.role = role;
        this.duty = duty;
        this.image = image;
        this.dutyContent = dutyContent;
    }
}

export default CommissionerDAO;