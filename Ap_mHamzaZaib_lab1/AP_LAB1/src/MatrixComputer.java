import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;


public class MatrixComputer {
	ArrayList<matrix> matrixes;
	PostFixExpression operation;
	
	MatrixComputer(){
		matrixes=new ArrayList<matrix>();
	}
	//prints all the matrixes
	public void printAll(){
		for(int i=0;i<matrixes.size();i++){
			matrix temp=matrixes.get(i);
			for(int j=0;j<temp.rows;j++){
				String row="";
				for(int k=0;k<temp.columns;k++){
					row+=" "+temp.values[j][k];
				}
				System.out.println(row);
			}
			System.out.println();
		}
	}
	
	//reads file and stores the expression and matrixes
	//for better understanding read file format in documentation
	public String readFile(Reader file) throws IOException {
		 BufferedReader reader = new BufferedReader(file);
	     String line;
	     Integer[][] values=new Integer[0][0];
	     Integer rows=0,columns=0,count=0;
	     Boolean start=false,first=true;
		while((line=reader.readLine())!=null){
			String[] tokens=line.split(" ");
			//for first line of file
			if(first){
				operation=new PostFixExpression(tokens[0]);
				start =true;
				first=false;
			}
			//for start of every matrix.
			//read columns and rows of matrix
			else if(start){
				if(tokens.length==2){
					try {
					rows=Integer.parseInt(tokens[0]);
					columns=Integer.parseInt(tokens[1]);
					} catch (NumberFormatException e) {
						System.out.println("Invalid File");
						return "";
					}
					values=new Integer[rows][columns];
					count=0;
					start=false;
				}
				else{
					return "File not Correct";
				}
			}
			//continue reading rows till u have read as many as specified
			else if(count<rows){
				if(tokens.length==columns){
					for(int i=0;i<tokens.length;i++){
						try{
						values[count][i]=Integer.parseInt(tokens[i]);
						}catch(NumberFormatException e){
							System.out.println("Invalid File");
							return "";
						}
					}
					count++;
				}
				else{
					return "File not Correct";
				}
			}
			//confirm end of matrix
			else if(line.compareTo(".")==0){
				matrixes.add(new matrix(rows,columns,values));
				rows=0;
				columns=0;
				values=null;
				start=true;
			}
			else{
				return "format error";
			}
		}
		//insert the last matrix if not inserted
		if(count==rows){
			matrixes.add(new matrix(rows,columns,values));
		}
		return "done";
	}

	//execute the matrix expression
	public void execute(){
		operation.postFixValue(operation.getPostFix(),matrixes);
	}
}
