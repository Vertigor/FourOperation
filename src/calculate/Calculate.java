package calculate;

import fraction.Fractions;
public class Calculate {
   
    public Calculate(){
    }
    // �ӷ�����
    public Fractions addtion(Fractions fractions1,Fractions fractions2)
    {
        int result_numerator,min;  // ��Ӻ�ķ����Լ���������ĸ����С������
        min=Fractions.minCommonMultiple(fractions1.getDenominator(), fractions2.getDenominator());
        result_numerator=(min/fractions1.getDenominator())*fractions1.getNumerator()+(min/fractions2.getDenominator())*fractions2.getNumerator();
        Fractions result=new Fractions(result_numerator, min);
        return result;
    }
    // ��������
    public Fractions subtraction(Fractions fractions1,Fractions fractions2)
    {
        int result_numerator,min;  // �����ķ����Լ���������ĸ����С������
        min=Fractions.minCommonMultiple(fractions1.getDenominator(), fractions2.getDenominator());
        result_numerator=(min/fractions1.getDenominator())*fractions1.getNumerator()-(min/fractions2.getDenominator())*fractions2.getNumerator();
        Fractions result=new Fractions(result_numerator, min);
        return result;
    }
    // �˷�����
    public Fractions multiplication(Fractions fractions1,Fractions fractions2)
    {
        int result_numerator,result_denominator;  // ��˺�ķ��Ӻͷ�ĸ
        result_numerator=fractions1.getNumerator()*fractions2.getNumerator();
        result_denominator=fractions1.getDenominator()*fractions2.getDenominator();
        Fractions result=new Fractions(result_numerator, result_denominator);
        return result;
    }
    // ��������
    public Fractions division(Fractions fractions1,Fractions fractions2)
    {
        int result_numerator,result_denominator;  // �����ķ��Ӻͷ�ĸ
        // �����������ת���ɷ����������
        result_numerator=fractions1.getNumerator()*fractions2.getDenominator();
        result_denominator=fractions1.getDenominator()*fractions2.getNumerator();
        Fractions result=new Fractions(result_numerator, result_denominator);
        return result;
    }
}
