import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        @SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
        int num = Integer.parseInt(scanner.nextLine().trim());
        int index = 0;
        String[] locations = new String[num];
        while(num-- > 0){
            locations[index++] = scanner.nextLine();
        }

        System.out.println(calculate(locations));  
    }
    
    static int calculate(String[] locations) {
    	int x = 0;
    	int y = 0;
    	int sum = 0;
    	int count = 0;
    	
    	for(String location : locations){
    		String[] arrs = location.split(",");
            int xTemp = Integer.parseInt(arrs[0]);
            int yTemp = Integer.parseInt(arrs[1]);
            
            sum += Math.abs(xTemp - x) + Math.abs(yTemp - y);
            
            if(count == locations.length - 1){
            	sum += xTemp + yTemp;
            }else{
            	 x = xTemp;
                 y = yTemp;
            }
            
            count++;
    	}
    	
        return sum;
    }
}