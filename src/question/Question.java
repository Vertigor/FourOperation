package question;

import java.util.Random;
import java.util.Scanner;
import java.util.Stack;

import fraction.Fractions;
import calculate.Calculate;

public class Question {
	private Character[] operators;
	private int[] operands;
	private Fractions[] operands_fra;
	private int operators_num;
	private Fractions result;
	public Question(int operators_num){
		if(operators_num<1||operators_num>10){
			System.out.println("Error:operators number error!");
			return;
		}
		this.operators_num = operators_num;
		this.operands = new int[operators_num+1];
		this.operators = new Character[operators_num];
		this.operands_fra = new Fractions[operators_num+1];
		this.init();
	}
	private void init(){
		Random random=new Random();
		for(int i=0;i<this.operands.length;i++){
			operands[i]=random.nextInt(100)+1;
		}
		for(int i=0;i<this.operands_fra.length;i++){
			operands_fra[i]=new Fractions(operands[i],1);
		}
		for(int i=0;i<this.operators.length;i++){
			switch(random.nextInt(4)){
            case 0:
                operators[i]='+';
                break;
            case 1:
            	operators[i]='-';
                break;
            case 2:
            	operators[i]='*';
                break;
            case 3:
            	operators[i]='/';
                break;
            }
		}
	}
	public String printQuestion(){
		String str="";
		for(int i=0;i<operators_num;i++){
			str+=operands[i]+operators[i];
		}
		str+=operators[operators_num];
		return str;
	}
	private void calculate(){
		Stack<Character> priStack = new Stack<Character>();// ²Ù×÷·ûÕ»   
		Stack<Integer> numStack = new Stack<Integer>();
		
	}

}
