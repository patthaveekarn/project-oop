package Backend.Statement;
import Backend.GameProcess.Game;
import Backend.Statement.Node.*;

public class OpponentNode extends ExpressionNode {

    // Constructor
    public OpponentNode() {
        // ไม่จำเป็นต้องระบุการสร้างคอนสตรักเตอร์หากไม่มีการสร้างข้อมูลเพิ่มเติม
    }

    // เมธอด evaluate เพื่อทำการประเมินค่าของ OpponentNode
    @Override
    public long evaluate(Game bindings) {
        // คืนค่าผลลัพธ์จากการเรียกเมธอด opponent() ของเกม
        return bindings.opponent();
    }

    // เมธอด toString เพื่อแสดงคำอธิบายของ OpponentNode
    @Override
    public String toString() {
        // คืนค่าข้อความที่บ่งบอกถึง OpponentNode
        return "opponent";
    }
}
