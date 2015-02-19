import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


public class unitTests {

	public static void main(String[] args){
		MatrixComputer temp=new MatrixComputer();
		try {
			//try read test
			String result=temp.readFile(new FileReader("file.txt"));
			if (result.compareTo("done")!=0){
				System.out.println("Read test failed");
				return;
			}
			System.out.println("Executing operations from file");
			temp.execute();
			System.out.println("\n\nTest1\n");
			test1(temp);
			System.out.println("\n\nTest2\n");
			test2(temp);
			System.out.println("\n\nTest3\n");
			test3(temp);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	//add three matrices. the first three compatible matrices found are added.
	//prints result if three compatible matrices are found
	//otherwise prints error
	private static void test2(MatrixComputer temp) {
		matrix result= new matrix();
		if(temp.matrixes.size()>0){
			result=temp.matrixes.get(0);
		}
		int count=0;
		for(int i=1;i<temp.matrixes.size();i++){
			matrix temp1=temp.matrixes.get(i);
			if(result.rows==temp1.rows && result.columns==temp1.columns){
				if(count==0)
					result.print();
				System.out.println("+");
				temp1.print();
				if(count==1){
					System.out.println("=");
					result.add(temp1).print();
				}
				else{
					result=result.add(temp1);
				}
				count++;
				if(count==2)
					return;
			}
		}
		System.out.println("No matrices can be added");		
	}

	//multiplies three matrices. the first three compatible matrices found are added.
	//prints result if three compatible matrices are found
	//otherwise prints error
		
	private static matrix test1(MatrixComputer temp) {
		matrix result= new matrix();
		if(temp.matrixes.size()>0){
			result=temp.matrixes.get(0);
		}
		for(int i=1;i<temp.matrixes.size();i++){
			matrix temp1=temp.matrixes.get(i);
			if(result.rows==temp1.rows && result.columns==temp1.columns){
				result.print();
				System.out.println("*");
				temp1.print();
				System.out.println("=");
				result.mul(temp1).print();
				return result.mul(temp1);
			}
		}
		System.out.println("No matrices can be subtracted");
		return null;
	}
	//multiplies two matrices and adds a matrix further down the array to the result.
	//prints result if compatible matrices are found
	//otherwise prints error
		
	private static void test3(MatrixComputer temp){
		matrix result= new matrix();
		matrix result1=new matrix();
		if(temp.matrixes.size()>0){
			result=temp.matrixes.get(0);
		}
		int count=0;
		for(int i=1;i<temp.matrixes.size();i++){
			matrix temp1=temp.matrixes.get(i);
			if(count==0){
				if(result.rows==temp1.columns){
					result.print();
					System.out.println("*");
					temp1.print();
					result1=result.mul(temp1);
					count++;
				}
			}
			else{
				if(result1.rows==temp1.rows && result.columns==temp1.columns){
					System.out.println("+");
					temp1.print();
					System.out.println("=");
					result.add(temp1).print();
					return;
				}
			}
		}
		System.out.println("Not enough input");
	}
}
