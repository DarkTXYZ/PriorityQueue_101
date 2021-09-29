public class Heap {

    int capacity;
    Node[] arr;
    int size;
    boolean isMinHeap;
    int timer;

    public Heap(boolean isMinHeap, int cap) {
        arr = new Node[cap + 1];
        size = 0;
        capacity = cap;
        this.isMinHeap = isMinHeap;
        timer = 0;
    }

    public Node top() {
        /*
         * หลักการ
         *  - root ของ heap ก็คือ arr[1] นั่นเอง
         */
        return arr[1];
    }

    public void push(Node node) {
        /*
         * หลักการ
         *  - เพิ่ม timer ไว้ timestamp ให้กับ node
         *  - เพิ่ม size ของ heap 1
         *  - push node ไปต่อหลัง array
         *  - จากนั้นทำการ Percolation ขึ้นมา
         *      > check priority ของ current (posn) กับ parent (posn / 2)
         *      > ถ้า Priority(current) > Priority(parent) ให้ทำการ swap และ update ให้ posn = posn / 2
         *      > ทำไปเรื่อยๆ จนกว่าจะถึง root หรือ ตอนที่ไม่เกิดการ swap
         */

        timer++;
        node.timestamp = timer; 

        size++;
        arr[size] = node;

        // Percolation
        int posn = size;
        while (posn > 1) { // posn ยังไม่ถึง root(index 1)
            if (arr[posn].compare(arr[posn / 2])) { // Priority(current) > Priority(parent)
                swap(posn, posn / 2);
                posn /= 2;
            } else { // ไม่เกิดการ swap
                break;
            }
        }

    }

    public Node pop() {
        /*
         * หลักการ
         *  - ให้ Node top = arr[1] เก็บ root ไว้
         *  - นำ node สุดท้าย ขึ้นมาที่ root และ ลด size ของ heap 1
         *  - จากนั้นทำการ Percolation ลงไป
         *      > check ว่า current(posn) มี child กี่ตัว [left(2*posn) , right(2*posn + 1)]
         *          - ไม่มี  --> หยุด loop
         *          - 1 ตัว --> check ว่า ถ้า Priority(child) > Priority(current) ก็ทำการ swap
         *          - 2 ตัว --> 
         *              > check ว่า child ตัวไหนมี Priority สูงกว่า 
         *              > จากนั้นจึงนำ child ตัวนั้น มา check ต่อว่า ถ้า Priority(child) > Priority(current) ก็ทำการ swap
         *      > ทำไปเรื่อยๆ จนกว่าจะเลย size ของ heap หรือ ตอนที่ไม่เกิดการ swap
         */

        // เก็บ root และ เอา node สุดท้ายขึ้นมาที่ root
        Node top = arr[1];
        arr[1] = arr[size];
        arr[size] = null;
        size--;

        // Percolation
        int posn = 1;
        while (posn <= size) { // posn ยังไม่เกิน size ของ heap
            Node current = arr[posn];
            // check ว่ามี child กี่ตัว
            Node left = null, right = null;
            if (2 * posn <= size)
                left = arr[2 * posn];
            if (2 * posn + 1 <= size)
                right = arr[2 * posn + 1];

            if (left == null && right == null) // ไม่มี child
                break; // ไม่เกิดการ swap
            else if (left != null && right == null) { // มี left child
                if(left.compare(current)){ // Priority(left) > Priority(current)
                    swap(posn, 2 * posn);
                    posn *= 2;
                }
                else // ไม่เกิดการ swap
                    break;
                
            } else if (left == null && right != null ) { // มี right child
                if(right.compare(current)){ // Priority(right) > Priority(current)
                    swap(posn, 2 * posn + 1);
                    posn *= 2;
                    posn += 1;
                }
                else // ไม่เกิดการ swap
                    break;
            } else { // มี left,right child
                Node higherPriority = left.compare(right) ? left : right; // check ว่า child ตัวไหนมี Priority สูงกว่า 
                if (higherPriority.compare(current)) { // Priority(higherPriority) > Priority(current)
                    if (higherPriority == left) { // child ตัวนั้น คือ left
                        swap(posn, 2 * posn);
                        posn *= 2;
                    } else { // child ตัวนั้น คือ right
                        swap(posn, 2 * posn + 1);
                        posn *= 2;
                        posn += 1;
                    }
                } else // ไม่เกิดการ swap
                    break;
            }
        }

        return top;
    }

    public void swap(int index1, int index2) {
        Node temp = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = temp;
    }

}
