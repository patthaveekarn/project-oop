package Backend.Statement;
import Backend.GameProcess.Game;
import Backend.Statement.Node.ExecuteNode;

public class DoneCommand extends ExecuteNode {

    // Constructor
    public DoneCommand() {
        // ไม่จำเป็นต้องมีการระบุโครงสร้างคอนสตรักเตอร์หากไม่มีการสร้างข้อมูลเพิ่มเติม
    }

    // เมธอด execute ที่เป็นการโอเวอร์ไรด์จากคลาส ExecuteNode
    @Override
    public boolean execute(Game bindings) {
        // เมื่อคำสั่ง "done" ถูกเรียกใช้งาน จะคืนค่าเป็น false เพื่อแสดงว่าการทำงานเสร็จสิ้น
        return false;
    }
}
