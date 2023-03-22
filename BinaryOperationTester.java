import java.util.*;

abstract class BinaryOperation_3_2 {
    public static final int UPPER = 100;
    public static final int LOWER = 0;
    private int left_operand = 0;
    private int right_operand = 0;
    private char operator;
    private int value;
    //调用了两个抽象方法,生成合法的算式成分,调用者负责输入合法的参数
    protected void generateBinaryOperation(char anOperator) {
        int left, right, result;
        Random random = new Random();
        left = random.nextInt(UPPER + 1);
        do {
            right = random.nextInt(UPPER + 1);
            result = calculate(left, right);
        } while (!(checkingCalculation(result)));
        left_operand = left;
        right_operand = right;
        operator = anOperator;
        value = result;
    }
    protected void generateMultiplication(char anOperator){
        int left, right, result;
        left = (int)(Math.random()*9+1);
        do {
            right = (int)(Math.random()*9+1);
            result = calculate(left, right);
        } while (!(checkingCalculation(result)));
        left_operand = left;
        right_operand = right;
        operator = anOperator;
        value = result;
    }
    public int getLeft_operand(){
        return left_operand;
    }
    public int getRight_operand(){
        return right_operand;
    }
    public char getOperator(){
        return operator;
    }
    public int getValue(){
        return value;
    }
    //子类必须实现的两个方法
    abstract boolean checkingCalculation(int anInteger);

    abstract int calculate(int left, int right);

    //判断两个算式是否相等，相等返回1，不相等返回0
    public boolean equals(BinaryOperation_3_2 anOperation) {
        return left_operand == anOperation.getLeft_operand()&&
                right_operand == anOperation.getRight_operand()&&
                operator == anOperation.getLeft_operand();
    }
    public String toString(){
        String str;
        str=String.format("%d %c %d ",left_operand,getOperator(),right_operand);//整数，字符
        return str;
    }
    public String asString(){
        return toString()+"=";
    }
    public String fullString(){
        return toString()+"="+getValue();
    }
}

class AdditionOperation extends BinaryOperation_3_2{
    public AdditionOperation(){
        generateBinaryOperation('+');
    }
    public boolean checkingCalculation(int anInteger){
        return anInteger <= UPPER;
    }
    public int calculate(int left,int right){
        return left + right;
    }
}

class SubtractOperation extends BinaryOperation_3_2 {
    public SubtractOperation(){
        generateBinaryOperation('-');
    }

    @Override
    boolean checkingCalculation(int anInteger) {
        return anInteger>=LOWER;
    }

    @Override
    int calculate(int left, int right) {
        return left-right;
    }
}
class MultiplicationOperation extends BinaryOperation_3_2{
    public MultiplicationOperation(){
        generateMultiplication('*');
    }
    @Override
    boolean checkingCalculation(int anInteger) {
        return anInteger>=LOWER;
    }

    @Override
    int calculate(int left, int right) {
        return left*right;
    }
}

class Exercise {
    // 存放算式的动态数组
    private ArrayList<BinaryOperation_3_2> operationList = new ArrayList<BinaryOperation_3_2>();
    private int current = 0; // 动态数组的游标
    //产生加法算式习题
    public void generateAdditionExercise(int operationCount) {
        BinaryOperation_3_2 anOperation;
        while(operationCount > 0) {
            do {
                anOperation = new AdditionOperation();
            }while(operationList.equals(anOperation));
            operationList.add(anOperation);
            operationCount--;
        }
    }

    //产生减法算式习题
    public void generateSubtractExercise(int operationCount) {
        BinaryOperation_3_2 anOperation;
        while(operationCount > 0) {
            do {
                anOperation = new SubtractOperation();
            }while(operationList.contains(anOperation));
            operationList.add(anOperation);
            operationCount--;
        }
    }

    //产生加减法混合算式习题
    public void generateBinaryExercise(int operationCount) {
        BinaryOperation_3_2 anOperation;
        Random random = new Random();
        while(operationCount > 0) {
            do {
                int opValue = random.nextInt(2);
                if(opValue == 0)
                    anOperation = new AdditionOperation();
                else
                    anOperation = new SubtractOperation();
            }while(operationList.contains(anOperation));
            operationList.add(anOperation);
            operationCount--;
        }
    }

    //产生乘法口诀练习
    public void generateMultiplicationExercise(int operationCount){
        BinaryOperation_3_2 anOperation ;
        while(operationCount > 0){
            do{
                anOperation =new MultiplicationOperation();
            }while(operationList.contains(anOperation));
            operationList.add(anOperation);
            operationCount--;
        }
    }


    public boolean hasNext() {
        return current <= operationList.size()-1;
    }

    public BinaryOperation_3_2 next() {
        return operationList.get(current++);
    }

}
class ExerciseSheet {
    public void formattedDisplay(Exercise ex) {
        int count = 1;//题目序号
        Map<Integer, Integer> map = new HashMap();
        while(ex.hasNext()) {
            System.out.printf("%3d. ", count);
            BinaryOperation_3_2 e=ex.next();
            System.out.print(e.asString()+"  ");
            Scanner sc = new Scanner(System.in);
            int num = sc.nextInt();
            if (e.getValue() != num) {
                map.put(count, e.getValue());
            }
            count++;
        }
        Set<Map.Entry<Integer, Integer>> entrySet = map.entrySet();
        for (Map.Entry entry : entrySet) {
            System.out.println("第"+entry.getKey() + "题错误，" +"正确答案为："+ entry.getValue());
        }
    }
}

//一道题的检测代码
//public class BinaryOperationTester {
//    public static void main(String[] args) {
//        Exercise exercise=new Exercise();
//        exercise.generateAdditionExercise(3);
//        while(exercise.hasNext()){
//            BinaryOperation_3_2 e=exercise.next();
//            System.out.print(e.asString());
//            Scanner sc = new Scanner(System.in);
//            //System.out.println("请输入你的答案：");
//            int num = sc.nextInt();
//            if (e.getValue() == num) {
//                System.out.println("恭喜你！回答正确");
//            }
//            else {
//                System.out.println("Sorry！回答错误");
//                System.out.println("正确答案是："+e.fullString());
//            }
//        }
//    }
//}

//一套题的检测代码
public class BinaryOperationTester {
    public static void main(String[] args) {
        Exercise exercise=new Exercise();
        exercise.generateAdditionExercise(3);
        ExerciseSheet sheet=new ExerciseSheet();
        sheet.formattedDisplay(exercise);
    }
}

//乘法口诀
//public class BinaryOperationTester {
//    public static void main(String[] args) {
//        Exercise exercise=new Exercise();
//        exercise.generateMultiplicationExercise(3);
//        ExerciseSheet sheet=new ExerciseSheet();
//        sheet.formattedDisplay(exercise);
//    }
//}