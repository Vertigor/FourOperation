package question;

import java.util.Random;
import java.util.Stack;

import fraction.Fractions;
import calculate.Calculate;

public class Question {
	private Character[] operators;
	private int[] operands;
	private Fractions[] operands_fra;
	private int operators_num;
	private Fractions result;
	private Stack<Character> priStack;// ������ջ   
	private Stack<Fractions> numStack;// ������ջ
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
		priStack = new Stack<Character>();
		numStack = new Stack<Fractions>();
		for(int i=0;i<this.operands.length;i++){
			operands[i]=random.nextInt(10)+1;
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
		this.calculate();
	}
	public String printQuestion(){
		String str="";
		for(int i=0;i<operators_num;i++){
			str+=operands[i]+operators[i].toString();
		}
		str+=operands[operators_num]+"=";
		return str;
	}
	private void calculate(){
		numStack.push(operands_fra[0]);
		for(int i=0;i<operators_num;i++){
			if(!compare(operators[i])){
				Fractions b = (Fractions) numStack.pop();// �ڶ���������
				Fractions a = (Fractions) numStack.pop();// �ڶ���������
				char ope = priStack.pop();
				Fractions tempresult ;
				switch (ope) {
				// ����ǼӺŻ��߼��ţ���   
		        case '+':
		        	tempresult = Calculate.addtion(a, b);
		        	numStack.push(tempresult);
		        	priStack.push(operators[i]);
		        	numStack.push(operands_fra[i+1]);
		        	break;
		        case '-':
		        	tempresult = Calculate.subtraction(a, b);
		        	numStack.push(tempresult);
		        	priStack.push(operators[i]);
		        	numStack.push(operands_fra[i+1]);
		        	break;
		        case '*':
		        	tempresult = Calculate.multiplication(a, b);
		        	numStack.push(tempresult);
		        	priStack.push(operators[i]);
		        	numStack.push(operands_fra[i+1]);
		        	break;
		        case '/':
		        	tempresult = Calculate.division(a, b);
		        	numStack.push(tempresult);
		        	priStack.push(operators[i]);
		        	numStack.push(operands_fra[i+1]);
		        	break;
				}
			}else{
				priStack.push(operators[i]);
				numStack.push(operands_fra[i+1]);
			}
		}
		while(!priStack.isEmpty()){
			Fractions b = (Fractions) numStack.pop();// �ڶ���������
			Fractions a = (Fractions) numStack.pop();// ��һ��������
			char ope = priStack.pop();
			Fractions tempresult ;
			switch (ope) {
			// ����ǼӺŻ��߼��ţ���   
	        case '+':
	        	tempresult = Calculate.addtion(a, b);
	        	numStack.push(tempresult);
	        	break;
	        case '-':
	        	tempresult = Calculate.subtraction(a, b);
	        	numStack.push(tempresult);
	        	break;
	        case '*':
	        	tempresult = Calculate.multiplication(a, b);
	        	numStack.push(tempresult);
	        	break;
	        case '/':
	        	tempresult = Calculate.division(a, b);
	        	numStack.push(tempresult);
	        	break;
			}
		}

		result = numStack.pop();
	}
	private boolean compare(char str) {   
	    if (priStack.empty()) {   
	      // ��Ϊ��ʱ����Ȼ ��ǰ���ȼ���ͣ����ظ�   
	      return true;   
	    }   
	    char last = (char) priStack.lastElement();   
	    // ���ջ��Ϊ'('��Ȼ�����ȼ���ͣ�')'������Ϊջ����   
	    if (last == '(') {   
	      return true;   
	    }   
	    switch (str) {   
	    case '#':   
	      return false;// ������   
	    case '(':   
	      // '('���ȼ����,��Ȼ����true   
	      return true;   
	    case ')':   
	      // ')'���ȼ���ͣ�   
	      return false;   
	    case '*': {   
	      // '*/'���ȼ�ֻ��'+-'��   
	      if (last == '+' || last == '-')   
	        return true;   
	      else  
	        return false;   
	    }   
	    case '/': {   
	      if (last == '+' || last == '-')   
	        return true;   
	      else  
	        return false;   
	    }   
	      // '+-'Ϊ��ͣ�һֱ����false   
	    case '+':   
	      return false;   
	    case '-':   
	      return false;   
	    }   
	    return true;   
	  }
	public Fractions getResult() {
		return result;
	}  

}
