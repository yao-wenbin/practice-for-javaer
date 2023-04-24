package com.ywb.astart;

/**
 * @Author yaowenbin
 * @Date 2022/10/17
 */
import java.util.*;

/**
 * @author zhongteng <qq754159742@outlook.com>
 */
/*
	BFS和A*实现八数码
 */
public class EightFigure {

    private final static int[][] neighbour = {{-1,-1,3,1},{-1,0,4,2},{-1,1,5,-1},
            {0,-1,6,4},{1,3,7,5},{2,4,8,-1},
            {3,-1,-1,7},{4,6,-1,8},{5,7,-1,-1}};
    private final static int arrangement = 363000;
    private String initialState;
    private String resultState;
    private String[][] arrangeQueue;

    private EightFigure() {
        arrangeQueue = new String[arrangement][];
        initialState = "";
        resultState = "";
        for(int i=0; i<arrangement; i++){
            arrangeQueue[i] = new String[5];
        }
    }

    public static void main(String[] args) {
        String loopInput = "Y";
        System.out.println("*****测试用例*****\n" +
                "初始状态:\n" +
                "      0 1 2\n" +
                "      3 4 5\n" +
                "      6 7 8\n" +
                "结果状态：\n" +
                "      1 2 0\n" +
                "      3 4 5\n" +
                "      6 7 8\n" +
                "******************\n");
        while(loopInput.equals("Y")){
            EightFigure eightFigure = new EightFigure();
            String[] initInputLine = new String[3];
            String[] resultInputLine = new String[3];
            StringBuilder initInput = new StringBuilder();
            StringBuilder resultInput = new StringBuilder();
            Scanner sc = new Scanner(System.in);
            int initialIndex = 0;
            int step = 0;
            System.out.println("请输入初始状态：\n");
            for(int i=0; i<3; i++) {
                initInputLine[i] = sc.nextLine();
                initInput.append(initInputLine[i].replace(" ",""));
            }
            System.out.println("请输入结果状态：");
            for(int i=0; i<3; i++) {
                resultInputLine[i] = sc.nextLine();
                resultInput.append(resultInputLine[i].replace(" ",""));
            }
            eightFigure.initialState = initInput.toString();
            eightFigure.resultState = resultInput.toString();
            if (eightFigure.initialState.length()!=9 || eightFigure.resultState.length()!=9){
                System.out.println("错误输入，请重试.");
            } else {
                System.out.println("请输入您想选择的计算方式： 1.BFS 2.A*");
                String calMode = sc.next();
                System.out.println("\n计算结果中...\n");
                while(eightFigure.initialState.charAt(initialIndex)!='0'){
                    initialIndex++;
                }
                long startTime = System.currentTimeMillis();
                switch (calMode){
                    case "1":
                        if (!eightFigure.initialState.equals(eightFigure.resultState)){
                            step = eightFigure.bfs(eightFigure.initialState, initialIndex);
                        }
                    case "2":
                        if (!eightFigure.initialState.equals(eightFigure.resultState)){
                            step = eightFigure.aStar(eightFigure.initialState, initialIndex);
                        }
                }
                System.out.println("\n总步数：" + step + "   ,计算时长: " + (System.currentTimeMillis()-startTime) + "ms\n");
            }
            System.out.println("是否继续(Y/N)?");
            loopInput = sc.next();
            while(!loopInput.equals("Y") && !loopInput.equals("N")){
                System.out.println("输入选项错误，请重新输入.");
                System.out.println("是否继续(Y/N)?");
                loopInput = sc.next();
            }
        }
    }

    private void swap(char[] s, int i, int j) {
        char tmp = s[i];
        s[i] = s[j];
        s[j] = tmp;
    }

    /**
     * BFS
     * @param initialState 初始状态字符串
     * @param initailIndex 初始状态中0的位置
     * @return 步数
     */
    private int bfs(String initialState, int initailIndex){
        Stack<String> printTrace = new Stack<>();	// be used to print trace
        HashMap<String, Integer> record = new HashMap<>();	// record state which has been visited
        int head = 0;
        int tail = 1;
        String temp;
        arrangeQueue[head][0] = initialState;
        arrangeQueue[head][1] = String.valueOf(initailIndex);
        arrangeQueue[head][2] = String.valueOf(head);
        arrangeQueue[head][3] = String.valueOf(head); // cur_index
        arrangeQueue[head][4] = null;  // pre_index
        while(head!=tail){
            char[] cur;
            int position = Integer.parseInt(arrangeQueue[head][1]);
            cur = arrangeQueue[head][0].toCharArray();
            for(int i=0; i<4; i++){
                int swapTo = neighbour[position][i];
                if(swapTo!=-1){
                    swap(cur, position, swapTo);
                    temp = String.valueOf(cur);
                    if(!record.containsKey(temp)){
                        arrangeQueue[tail][0] = temp;
                        arrangeQueue[tail][1] = String.valueOf(swapTo);
                        arrangeQueue[tail][2] = String.valueOf(Integer.parseInt(arrangeQueue[head][2])+1);
                        arrangeQueue[tail][3] = String.valueOf(tail); //cur_index
                        arrangeQueue[tail][4] = arrangeQueue[head][3]; //pre_index
                        tail++;
                        record.put(temp, 1);
                    }
                    if(temp.equals(resultState)){
                        printTrace.push(temp);
                        String preIndexStr = arrangeQueue[tail-1][4];
                        while(!preIndexStr.equals("0")){
                            int preIndex = Integer.parseInt(preIndexStr);
                            printTrace.push(arrangeQueue[preIndex][0]);
                            preIndexStr = arrangeQueue[preIndex][4];
                        }
                        int stepConut = 1;
                        while(!printTrace.isEmpty()){
                            System.out.println(String.format("生成节点数:%d", printTrace.size()));
                            String printStep = printTrace.pop();
                            System.out.println("\n第" + stepConut + "步:");
                            System.out.println(String.format("%c %c %c",printStep.charAt(0), printStep.charAt(1), printStep.charAt(2)));
                            System.out.println(String.format("%c %c %c",printStep.charAt(3), printStep.charAt(4), printStep.charAt(5)));
                            System.out.println(String.format("%c %c %c",printStep.charAt(6), printStep.charAt(7), printStep.charAt(8)));
                            stepConut++;
                        }
                        return Integer.parseInt(arrangeQueue[head][2])+1;
                    }
                    swap(cur, position, swapTo);
                }
            }
            head++;
        }
        return tail;
    }

    /**
     * A*
     * @param initialState 初始状态字符串
     * @param initialIndex 初始状态中0的位置
     * @return 步数
     */
    private int aStar(String initialState, int initialIndex){
        HashMap<String, Integer> record = new HashMap<>();
        PriorityQueue<Node> priorityQueue = new PriorityQueue<>(nodeComparator);
        String temp;
        Node node = new Node(initialState, initialIndex, 0);
        priorityQueue.add(node);
        record.put(initialState, 1);
        while(!priorityQueue.isEmpty()){
            node = priorityQueue.poll();
            temp = node.getNum();
            char[] cur = temp.toCharArray();
            int position = node.getZeroIndex();
            for (int i=0; i<4; i++){
                int swapTo = neighbour[position][i];
                if(swapTo != -1){
                    swap(cur, position, swapTo);
                    temp = String.valueOf(cur);
                    if(temp.equals(resultState)){
                        return node.getStep()+1;
                    }
                    if(!record.containsKey(temp)){
                        Node tempNode = new Node(temp, swapTo, node.getStep()+1);
                        priorityQueue.add(tempNode);
                        record.put(temp, 1);
                    }
                    swap(cur, position, swapTo);
                }
            }
        }

        return 0;
    }

    /*	用于记录A*中节点cost	*/
    private class Node {
        private String num;
        private int step;
        private int cost;
        private int zeroIndex;
        Node(String num, int zeroIndex, int step){
            this.num = num;
            this.step = step;
            this.zeroIndex = zeroIndex;
            setCost();
        }
        private void setCost(){
            int count = 0;
            for(int i=0; i<9; i++){
                if (num.charAt(i) == initialState.charAt(i)){
                    count++;
                }
            }
            cost = count + step;
        }

        private String getNum() {
            return num;
        }

        private int getStep() {
            return step;
        }

        private int getZeroIndex() {
            return zeroIndex;
        }
    }

    /*	优先队列中的排序方法，根据节点cost值由小到大排列	*/
    private static Comparator<Node> nodeComparator = new Comparator<Node>() {
        @Override
        public int compare(Node o1, Node o2) {
            return o1.cost-o2.cost;
        }
    };
}
