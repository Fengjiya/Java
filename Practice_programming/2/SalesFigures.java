import java.util.Scanner;

public class SalesFigures
{
	private String[]  MONTHS = {"Feb", "April", "June", "Aug", "Oct", "Dec"};
	private double[][] monthly_Sales = new double[MONTHS.length][];
	private double[][] sum_Avg = new double[MONTHS.length][2];
	private int months;
	
	//��ʼ��ÿ�������۶������
	public void init()
	{
		months = 0;
		System.out.println("������Ҫͳ�Ƶ��·�����������ż������" );
		Scanner scan = new Scanner( System.in );
		months = scan.nextInt();
		while( (months <=0) || (months % 2 != 0) )
		{
			System.out.println("����������������ż��: ");
			months = scan.nextInt();
		}
	
		System.out.println("������ǰ" + (months/2) + "�ε�ÿ�����۶�" );
		double[] sale = new double[months/2];
		for(int k = 0; k < sale.length; k++)
		{
			sale[k] = scan.nextDouble();
		}
		for( double sales: sale )
			System.out.print( sales + " " );
		
		for( int i = 0; i < sale.length; i++)
			for( int j = 0; j < i; j++)
				monthly_Sales[i][j] = sale[j];
				
		/*for( double[] sales: monthly_Sales )
			System.out.print( sales + " " );*/
	}
	
	//���������۶��ƽ�����۶�
	public void computer_sum_Avg()
	{
		
	}
	
	//��������ܱ��
	public void output_Figures()
	{
		
	}
	
	public static void main(String[] args)
	{
		new SalesFigures().init();
	}
	
}