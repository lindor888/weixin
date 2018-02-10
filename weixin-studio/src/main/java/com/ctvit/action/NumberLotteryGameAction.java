package com.ctvit.action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.ctvit.bean.nickname_user;
import com.ctvit.dao.RandomUtil;
import com.opensymphony.xwork2.ActionSupport;

public class NumberLotteryGameAction extends ActionSupport implements ServletRequestAware{ 
    /**
	 * 
	 */
	private static final long serialVersionUID = 775651540602333858L;
	private int gamesNumber;    // 生成游戏的数量，为以后多线程扩展做考虑 
    private int numbersLength;  // 数字序列的总长度 
    private int winningNumbersLength;  // 中奖的数字序列的长度 
    private int number;
	private HttpServletRequest request;
	private HttpSession session;
	private ServletContext application;
    static List<Integer> list = new ArrayList<Integer>();
    
    public String execute(){
		
		System.out.println("Action");
		
		return "success";
	}
    public void setServletRequest(HttpServletRequest request) {
		this.request = request;
		this.session = request.getSession();
		this.application = session.getServletContext();
	} 
    /**
     * Constructor with three parameters
     * 初始化3个参数的构造方法
     * @param gamesNumber
     * @param numbersLength
     * @param winningNumbersLength
     */ 
    public NumberLotteryGameAction() { 
        
    }
    public NumberLotteryGameAction(int gamesNumber, int numbersLength, int winningNumbersLength) { 
        this.gamesNumber = gamesNumber; 
        this.numbersLength = numbersLength; 
        this.winningNumbersLength = winningNumbersLength; 
    } 
 
    /**
     * Generate a number Array
     * 生成一个产生中奖序列所需的数字序列
     * @param length 数字序列的长度
     * @return numbers 产生的数字序列
     */ 
    public static int[] generateNumberArray(int length) { 
        int[] numbers = new int[length]; 
         
        for (int i = 0; i < numbers.length; i++) { 
            numbers[i] = i + 1; 
            //System.out.println("numbers[i]===="+numbers[i]);
        } 
         
        return numbers; 
    } 
     
    /**
     * Generate a winning number array
     * 根据生成的数字序列，产生不重复的中奖数字序列
     * @param length 中奖数字序列的长度
     * @return result 中奖数字数组
     */ 
    public int[] generateWinningNumberArray(int length) { 
        int[] numbers = NumberLotteryGameAction.generateNumberArray(numbersLength); 
         
        int[] result = new int[length]; 
         
        int n = numbersLength; 
         
        // 该for循环为产生不重复的中奖序列的核心代码 
        for(int i = 0; i < result.length; i++) { 
            int r = (int) (Math.random() * n);  // 随机产生一个从0——(n-1)的数字,Math.random() 
            //System.out.println("numbers[n]===="+n);                               // 随机产生一个[0, 1)范围的double型数值, 
            result[i] = numbers[r];             // 将该随机数字作为数组的下标， 
            //System.out.println("dd"+result[i]); // 将该下标对应的值赋给result[i] 
            numbers[r] = numbers[n - 2];        // 将numbers数组的numbers[n-1]的值，赋给刚已赋 
            //System.out.println("numbers[r]===="+r+"   "+numbers[r]);                                   // 值过的numbers[r]。 
            n--;   // 将n-1,从而下一次循环产生的随机的原数组下标的范围从0——（n-1）-1, 
                   // 保证了上一步中，已经赋值给数组中其他数的numbers[n-1]，不会在下次循环中给取 
                   // 得，从而保证了产生的中奖数组result为不重复的。 
        } 
         
        return result; 
    } 
     
    /**
     * Show winning NumberArray
     * 显示中将数组
     * 显示人名的方法
     */ 
    public String showWinningNumberArray() { 
        int[] winningNumbers = this.generateWinningNumberArray(winningNumbersLength); 
        Arrays.sort(winningNumbers); 
        RandomUtil ru = new RandomUtil();
       
    	int u = ru.setSeed();
        NumberLotteryGameAction game1 = new NumberLotteryGameAction(1, u, 5);
        int i = 2;    // int i = Integer.parseInt(args[0]); 
        for(int j = 0; j < i; j++) { 
        	 
            //game1.showWinningNumberArray(); 
            game1.namelist();
           
            
         
        }
        List namelist = ru.SelectName();
        System.out.println("namelist " + namelist.size() );
        request.setAttribute("namelist",namelist);
        game1.namelist().clear();
		return "success";
        
    } 
    
    public List namelist(){
    	
    	if(winningNumbersLength > 0){
    	 int[] winningNumbers = this.generateWinningNumberArray(winningNumbersLength); 
         Arrays.sort(winningNumbers); 
         
         for(int i=0;i<winningNumbers.length;i++){
        	 
        	 list.add(winningNumbers[i]);
         }
        // list2.add(list);
         System.out.println("size "+list.size());
         }
    	
    	
    	return list;
    }
 
    /**
     * @return the gamesNumber
     */ 
    public int getGamesNumber() { 
        return gamesNumber; 
    } 
    
    public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
 
    /**
     * @param gamesNumber the gamesNumber to set
     */ 
    public void setGamesNumber(int gamesNumber) { 
        this.gamesNumber = gamesNumber; 
    } 
 
    /**
     * @return the numbersLength
     */ 
    public int getNumbersLength() { 
        return numbersLength; 
    } 
 
    /**
     * @param numbersLength the numbersLength to set
     */ 
    public void setNumbersLength(int numbersLength) { 
        this.numbersLength = numbersLength; 
    } 
 
    /**
     * @return the winningNumbersLength
     */ 
    public int getWinningNumbersLength() { 
        return winningNumbersLength; 
    } 
 
    /**
     * @param winningNumbersLength the winningNumbersLength to set
     */ 
    public void setWinningNumbersLength(int winningNumbersLength) { 
        this.winningNumbersLength = winningNumbersLength; 
    } 
    
    /**
     * 
     * @return
     * 根据传入的参数查询获奖人
     */
    public String select() { 
    	
    	RandomUtil rdu = new RandomUtil();
    	List list = rdu.goodluckname(number);
    	request.setAttribute("namelist",list);
    	for(int i=0;i<list.size();i++){
    		
    		System.out.println("///////////////////"+list.get(i));
    	}
    	rdu.insert();
    	ArrayList<nickname_user> al = rdu.getUser();
    	for(int j=0;j<al.size();j++){
    		
    		nickname_user nu = al.get(j);
    		System.out.println("cid" + nu.getNickname());
    	}
        System.out.println("number" + number);
		return "success";
        
    } 
    /**
     * Main method
     * 用于测试该类的main方法
     * @param args
     */  
    public static void main(String[] args) { 
  
    	RandomUtil ru = new RandomUtil();
    	int u = ru.setSeed();
        int i = 2;    // int i = Integer.parseInt(args[0]); 
        for(int j = 0; j < i; j++) { 
        	NumberLotteryGameAction game1 = new NumberLotteryGameAction(1, u, 5); 
            game1.showWinningNumberArray(); 
            game1.namelist();
           
            ru.SelectName();
          // System.out.println(Math.random());    
        } 
       
           
    }

	
        
} 