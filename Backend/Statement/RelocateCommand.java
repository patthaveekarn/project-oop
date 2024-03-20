package Backend.Statement;

import Backend.GameProcess.Game;
import Backend.Statement.Node.*;

public class RelocateCommand extends ExecuteNode {

    // Constructor
    public RelocateCommand() {
        // ไม่จำเป็นต้องระบุการสร้างคอนสตรักเตอร์หากไม่มีการสร้างข้อมูลเพิ่มเติม
    }

    // เมธอด execute เพื่อทำการดำเนินการ RelocateCommand
    @Override
    public boolean execute(Game bindings) {
        // เรียกใช้เมธอด relocate() ในเกม
        bindings.relocate();
        // คืนค่า true เพื่อแสดงว่าการดำเนินการเสร็จสิ้นอย่างสมบูรณ์
        return true;
    }
}
