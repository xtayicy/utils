package harry.utils;

import java.util.List;

/**
 * 
 * @author harry
 *
 */
public final class MathUtil {
	private MathUtil(){}
	
	public static boolean isArithmeticProgression(List<Integer> list){
		Integer temp = null,subtract = null;
		boolean flag = false;
		int index = list != null ? list.size() - 1 : 0;
		
		while(index > 1){
			subtract = list.get(index) - list.get(index - 1);
			if(list.size() - 1 == index){
				temp = subtract;
			}else{
				if(subtract != null && subtract != temp){
					return false;
				}
			}
			
			index--;
			subtract = null;
			if(index == 1)
				flag = true;
		}
		
		return flag;
	} 
}
