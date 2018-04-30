package harry.test.solution;

import org.junit.Test;

/**
 * 
 * @author harry
 *
 */
public class EightQueen {
	@Test
	public void test(){
		/**
		 * 下标代表列，值代表行
		 */
		int[] arrs = new int[8];
		for (int i = 0; i < 8; i++) {
			//假设第一列的放置情况
			arrs[0] = i;
			//判断后面棋子的放置情况是否产生冲突
			place(arrs,0);
		}
	}
	
	public boolean place(int[] arrs,int col){
		int row = 0;
		boolean isFound = false;
		if(col == 7){
			isFound = true;
		}else{
			while(row < 8 && !isFound){
				if(isSave(row,col,arrs)){
					arrs[col] = row;
					if(!place(arrs,col + 1)){
						row++;
					}
				}else{
					row++;
				}
			}
		}
		
		return isFound;
	}
	
	public boolean isSave(int row,int col,int[] arrs){
		for (int i = 0; i < col; i++) {
			int queenRow = arrs[i];
			if(row == queenRow){
				return false;
			}
			
			if(i == col){
				return false;
			}
			
			if(queenRow - i == row - col || queenRow + i == row + col){
				return false;
			}
		}
		
		return true;
	}
}
