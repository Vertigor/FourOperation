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
	private Stack<Character> priStack;// 操作符栈   
	private Stack<Fractions> numStack;// 操作数栈
	private int[] leftBracket;
	private int[] rightBracket;
	private int bracketNum;
	private String expression;
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
		if(operators_num==1)
			bracketNum=0;
		else
			bracketNum=random.nextInt(operators_num/2+operators_num%2+1);
		leftBracket = new int[operators_num];
		rightBracket = new int[operators_num];
		priStack = new Stack<Character>();
		numStack = new Stack<Fractions>();
		initBracketArray();
		if(bracketNum>0){
		for(int i=0;i<this.bracketNum;i++){
			int pos = random.nextInt(operators_num);
			leftBracket[pos]++;
			rightBracket[random.nextInt(operators_num-pos)+pos]++;

		}
		checkBracket();
		}
		for(int i=0;i<this.operands.length;i++){
			operands[i]=random.nextInt(100)+1;
		}
		for(int i=0;i<this.operands_fra.length;i++){
			operands_fra[i]=new Fractions(operands[i],1);
		}
		for(int i=0;i<this.operators.length;i++){
			switch(random.nextInt(5)){
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
            case 4:
            	operators[i]='÷';
                break;
            }
		}
		this.setExpression(printQuestion());
		this.calculate();
	}
	private void initBracketArray(){
		for(int i=0;i<this.operators_num;i++){
			leftBracket[i]=0;
			rightBracket[i]=0;
		}
	}
	private boolean checkBracket(){
		boolean flag = true;
		int[] lb = leftBracket.clone();
		int[] rb = rightBracket.clone();
		for(int i=0;i<operators_num;i++){
			int temp =i;
			while(rb[i]>0){
				for(int j=i;j>-1;j--){
					while(lb[j]>0&&rb[i]>0){
						lb[j]--;
						rb[i]--;
						if(temp-1==j||temp==j||(i==operators_num-1&&j==0)){
							deleteBracket(j, i);
							flag = false;
						}
						temp=j;
					}
				}
			}
		}
		return flag;
	}
	private boolean deleteBracket(int lb,int rb){
		if(leftBracket[lb]==0||rightBracket[rb]==0)
			return false;
		leftBracket[lb]--;
		rightBracket[rb]--;
		bracketNum--;
		return true;
	}
	private String printQuestion(){
		String str="";
		for(int i=0;i<operators_num;i++){
			for(int j=0;j<leftBracket[i];j++){
				str+="(";
			}
			str+=operands[i];
			if(i>0){
				for(int j=0;j<rightBracket[i-1];j++){
					str+=")";
				}
			}
			str+=operators[i].toString();
		}
		str+=operands[operators_num];
		if(bracketNum>0)
		for(int j=0;j<rightBracket[operators_num-1];j++){
			str+=")";
		}
		str+="=";
		return str;
	}
	private void calculate(){
		numStack.push(operands_fra[0]);
		int i=0;
		int[] lb = leftBracket.clone();
		int[] rb = rightBracket.clone();
		while(i<operators_num){
			while(lb[i]>0){
				priStack.push('(');
				lb[i]--;
			}
			if(i>0){
				if(rb[i-1]>0){
					char ope = priStack.pop();
					if(ope=='(')
						continue;
					Fractions b = (Fractions) numStack.pop();// 第二个运算数
					Fractions a = (Fractions) numStack.pop();// 第二个运算数
					Fractions tempresult ;
					switch (ope) {
					// 如果是加号或者减号，则   
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
			        case '÷':
			        	tempresult = Calculate.division(a, b);
			        	numStack.push(tempresult);
			        	break;
					}
					rb[i-1]--;
				}
			}
			if(!compare(operators[i])){
				Fractions b = (Fractions) numStack.pop();// 第二个运算数
				Fractions a = (Fractions) numStack.pop();// 第二个运算数
				char ope = priStack.pop();
				Fractions tempresult ;
				switch (ope) {
				// 如果是加号或者减号，则   
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
		        case '÷':
		        	tempresult = Calculate.division(a, b);
		        	numStack.push(tempresult);
		        	break;
				}
			}else{
				priStack.push(operators[i]);
				numStack.push(operands_fra[i+1]);
				i++;
			}
		}
		while(!priStack.isEmpty()){
			char ope = priStack.pop();
			if(ope=='(')
				continue;
			Fractions b = (Fractions) numStack.pop();// 第二个运算数
			Fractions a = (Fractions) numStack.pop();// 第一个运算数
			Fractions tempresult ;
			switch (ope) {
			// 如果是加号或者减号，则   
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
	        case '÷':
	        	tempresult = Calculate.division(a, b);
	        	numStack.push(tempresult);
	        	break;
			}
		}

		result = numStack.pop();
	}
	private boolean compare(char str) {   
	    if (priStack.empty()) {   
	      // 当为空时，显然 当前优先级最低，返回高   
	      return true;   
	    }   
	    char last = (char) priStack.lastElement();   
	    // 如果栈顶为'('显然，优先级最低，')'不可能为栈顶。   
	    if (last == '(') {   
	      return true;   
	    }   
	    switch (str) {   
	    case '=':   
	      return false;// 结束符   
	    case '(':   
	      // '('优先级最高,显然返回true   
	      return true;   
	    case ')':   
	      // ')'优先级最低，   
	      return false;   
	    case '*': {   
	      // '*/'优先级只比'+-'高   
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
	    case '÷': {   
		      if (last == '+' || last == '-')   
		        return true;   
		      else  
		        return false;   
		    }   
	      // '+-'为最低，一直返回false   
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
	public String getExpression() {
		return expression;
	}
	private void setExpression(String expression) {
		this.expression = expression;
	}  

}
