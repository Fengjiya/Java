import java.util.Scanner;

public class SalesFigures
{
	static final String[] MONTHS = {"Feb", "Apr", "June", "Aug", "Oct", "Dec"};
	
	public static void main(String[] args)
	{
		int month;
		double sale;
		
		Scanner scan = new Scanner(System.in);
		
		System.out.println("Input the month now: ");
		month = scan.nextInt();
		
		double[] sales = new double[month/2];
		
		for( int i = 0; i < month / 2; i++ )
		{
			System.out.println("Input the sale of " + MONTHS[i] + " : ");
			sale = scan.nextDouble();
			sales[i] = sale;
		}

		generateTable(sales);
		

	}
	
	public static void generateTable(double[] sales )
	{
		//��¼�м�����¼
		int tmp = sales.length;
		//������һ��
		System.out.print(" \t" + "Sum\t" + "Avg\t");
		for( int i = 0; i < tmp; i++ )
		{
			System.out.print(MONTHS[i] + "\t" );
		}
		System.out.println();
		//��������ÿһ�е�����
		for( int j = 0; j < tmp; j++ )
		{
			System.out.print(MONTHS[j] + "\t");
			System.out.print(Sum(sales, j) + "\t");
			System.out.print(Avg(sales, j) + "\t");
			for( int k = 0; k <= j; k++ )
			{
				System.out.print(sales[k] + "\t");
			}
			
			System.out.println();
		}
	}
	
	public static double Sum( double[] sales, int n )
	{
		double tmp = 0;
		for ( int i = 0; i <= n ; i++ )
		{
			tmp += sales[i];
		}
		
		return tmp;
	}
	
	public static double Avg( double[] sales, int n )
	{
		return Sum(sales, n) / (n+1);
	}
	
	

}