import java.util.Scanner;

public class DFAM {
    private int[][] tran; // for transition from one state to another state
    private int[] acceptStates;
    private int acceptState;

    public DFAM (int numStates, int acceptState) {
        tran = new int[numStates+1][2];
        acceptStates = new int[numStates];
        this.acceptState = 0;
    }

    public void addTran(int startState, int alphabet, int endState) {
        if (startState >= 1 && endState >= 1 && startState <= tran.length && endState <= tran.length) {
            tran[startState][alphabet] = endState;
        }
    }

    public boolean isAccept(String w) {
        int alphabet;
        int state = 1;
        for (int i = 0; i < w.length(); i++){
            if(w.charAt(i) == '0') {
                alphabet = 0;
            }
            else if(w.charAt(i) == '1') {
                alphabet = 1;
            }
            else {
                return false;
            }

            state = tran[state][alphabet]; //makes a new transition to new state
        }

        return isAcceptState(state);
    }

    public boolean isAcceptState(int state) {
        for (int i = 0; i < acceptStates.length; i++) {
            if(acceptStates[i] == state){
                return true;
            }
        }
        return false;
    }

    public void addAcceptState(int state) {
        acceptStates[acceptState++] = state;
    }

    public static void main(String[] args) {
        int numStates;
        int acceptStates;
        int state;
        String w;
        Scanner input = new Scanner(System.in);

        System.out.print("How many states does the DFA  M Machine have? ");
        numStates = input.nextInt()
        System.out.print("How many accept states does M have? ");
        acceptStates = input.nextInt();

        DFAM m = new DFAM(numStates, acceptStates);

        System.out.printf("Enter the %d accept state(s) separated by space: ", acceptStates);
        for(int i = 1; i <= acceptStates; i++) {
            state = input.nextInt();
            m.addAcceptState(state);
        }

        System.out.println("For the transition of each state of inputs 0 and 1");

        for(int j = 1; j <= numStates; j++) {
            System.out.printf("From state %d of input 0, to which state? ", j);
            state = input.nextInt();
            m.addTran(j, 0, state);
            System.out.printf("From state %d of input 1, to which state? ", j);
            state = input.nextInt();
            m.addTran(j, 1, state);
        }

        while (true) {
            System.out.print("Enter the input string w of 0's and 1's (type STOP to stop): ");
            w = input.next();
            if(w.equalsIgnoreCase("STOP")){
                break;
            }
            if(m.isAccept(w)){
                System.out.println("DFA M Machine accepts the input string " + w);
            }
            else {
                System.out.println("DFA M Machine does not accept the input string " + w);
            }
        }
    }
}