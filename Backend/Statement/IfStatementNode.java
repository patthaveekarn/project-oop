package Backend.Statement;

public class IfStatementNode extends ConditionStatementNode {

    // Constructor ที่รับพารามิเตอร์เป็นเงื่อนไข (ExpressionNode) และคำสั่งที่เป็นจริงและเท็จ (ExecuteNode)
    public IfStatementNode(ExpressionNode conditionNode, ExecuteNode trueStatement, ExecuteNode falseStatement) {
        super(conditionNode, trueStatement, falseStatement);
    }

    // เมธอด execute เพื่อทำการประมวลผลคำสั่ง if-else
    @Override
    public boolean execute(Game bindings) {
        // ตรวจสอบเงื่อนไข
        boolean condition = conditionNode.evaluate(bindings);

        // ถ้าเงื่อนไขเป็นจริง
        if (condition) {
            // ประมวลผลคำสั่งที่เป็นจริง
            trueStatement.execute(bindings);
        } else {
            // ถ้าไม่เป็นจริง ประมวลผลคำสั่งที่เท็จ (หากมี)
            if (falseStatement != null) {
                falseStatement.execute(bindings);
            }
        }

        // คำสั่ง IfStatementNode ไม่ได้ส่งค่าคืน ให้คืนค่าเป็น true เพื่อแสดงว่าประมวลผลเสร็จสิ้น
        return true;
    }
}

