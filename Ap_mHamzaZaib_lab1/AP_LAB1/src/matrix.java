
public class matrix {
	Integer rows;
	Integer columns;
	Integer[][] values;
	
	matrix(){
		rows=0;
		columns=0;
		values=new Integer[0][0];
	}
	
	matrix(Integer rows, Integer columns,Integer[][] values){
		this.rows=rows;
		this.columns=columns;
		this.values=values;
	}
	//add this matrix to the one passed and return the result
	public matrix add(matrix matrix1){
		//check if matrices are compatible for addition
		if(rows==matrix1.rows && columns==matrix1.columns){
			matrix temp=new matrix(rows,columns,new Integer[rows][columns]);
			//iterate over all elements and add them
			for(int i=0;i<rows;i++){
				for(int j=0;j<columns;j++){
					temp.values[i][j]=values[i][j]+matrix1.values[i][j];
				}
			}
			return temp;
		}
		
		return null;
	}
	public matrix sub(matrix matrix1){
		//check if matrices are compatible for subtraction
		if(rows==matrix1.rows && columns==matrix1.columns){
			matrix temp=new matrix(rows,columns,new Integer[rows][columns]);
			//iterate over all elements and subtract them
			for(int i=0;i<rows;i++){
				for(int j=0;j<columns;j++){
					temp.values[i][j]=values[i][j]-matrix1.values[i][j];
				}
			}
			return temp;
		}
		
		return null;
	}
	public matrix mul(matrix matrix1){
		//check if matrices are compatible for multiplication
		if(rows==matrix1.columns){
			matrix temp=new matrix(rows,columns,new Integer[rows][matrix1.columns]);
			//multiplication algo.
			//multiply row say i of first matrix with the column say j of second matrix and add all values
			//you will get the element(i,j) of the resultant matrix.
			//calculate all the elements
			for(int i=0;i<rows;i++){
				for(int j=0;j<matrix1.columns;j++){
					temp.values[i][j]=0;
					for(int k=0;k<rows;k++){
						temp.values[i][j]+=values[k][j]*matrix1.values[i][k];
					}
				}
			}
			return temp;
		}
		
		return null;
	}
	//prints the matrix
	public void print() {
		for(int i=0;i<rows;i++){
			String line="";
			for(int j=0;j<columns;j++){
				line+=values[i][j]+" ";
			}
			System.out.println(line);
		}
	}
}
