public class Node {
    
    double price;
    int investorID;
    int amount;
    int timestamp;
    boolean isMinHeap;
    
    public Node(double price, int investorID, int amount, boolean isMinHeap){
        this.price = price;
        this.investorID = investorID;
        this.amount = amount;
        this.isMinHeap = isMinHeap;
    }

    public boolean compare(Node rightNode){
        /*
         * หลักการ
         *  - จะเปรียบเทียบ node 2 node โดย
         *      > return true   ถ้า Priority(thisNode) > Priority(rightNode)
         *      > return false  ถ้า Priority(thisNode) < Priority(rightNode)
         *  - การเปรียบเทียบ Priority 
         *      > หากเป็น minHeap
         *          - price น้อยกว่า จะมี priority สูงกว่า (price ต่ำจะมาก่อน price สูง)
         *          - ถ้า price เท่ากัน ดูที่ timestamp น้อยกว่า จะมี priority สูงกว่า (มาก่อน ได้ก่อน)
         *      > หากเป็น maxHeap
         *          - price น้อยกว่า จะมี priority ต่ำกว่า (price สูงจะมาก่อน price ต่ำ)
         *          - ถ้า price เท่ากัน ดูที่ timestamp น้อยกว่า จะมี priority สูงกว่า (มาก่อน ได้ก่อน)
         */        
         if (this.price == rightNode.price) { // check price ว่าเท่ากันหรือไม่
            if(this.timestamp < rightNode.timestamp) // thisNode มาก่อน rightNode
                return true;
            else
                return false;
        }else{
            if (isMinHeap){ // เป็น minHeap
                if(this.price < rightNode.price) // thisNode price ต่ำกว่า rightNode
                    return true;
                else 
                    return false;
            }else{ // เป็น maxHeap
                if(this.price > rightNode.price) // thisNode price สูงกว่า rightNode
                    return true;
                else 
                    return false;
            }
        }
    }
    
    public Node(){}
}
