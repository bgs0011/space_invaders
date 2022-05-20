package game.classes;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

import game.level1_2.Game;
import game.level1_2.Game.STATE;

public class GameTimer {
  Timer timer;

  public GameTimer(int seconds) {
    timer = new Timer();
    timer.schedule(new RemindTask(), seconds * 1000);

  }

  class RemindTask extends TimerTask {
	  
    public void run() {
    	

      if(Game.State == STATE.LEVEL1) {
    	  Game.State = STATE.Level_1_2;   
    	  
      }else if(Game.State == STATE.LEVEL2){
    	  Game.State = STATE.Level_2_3;    	  
      
      }
      else if(Game.State == STATE.LEVEL3){
    	  
    	  try {
        	  File file = new File("HighScores.txt");
    	      if (file.createNewFile()) {
    	        System.out.println("File created: " + file.getName());
    	        FileWriter myWriter = new FileWriter("HighScores.txt");
    	        myWriter.write( Game.p_name + " " + Game.SCORE + " ");
    	        myWriter.close();
    	      
    	      
    	      } else {
    	        System.out.println("File already exists.");
    	        Scanner s = new Scanner(file);
    	        int arrayLength = 0;
    	        
    	  		
    	          
    	          String[] names = new String[6];
    	          int[] scores = new int[6];

    	          int rv1 = 0;
    	          //rv means random variable 
    	          while (s.hasNext()) {
    	              System.out.println("test");

    	              names[rv1] = s.next();
    	              scores[rv1] = s.nextInt();
    	              rv1++;
    	              arrayLength++;
    	              System.out.println("arrayLength");
    	              System.out.println(arrayLength);
    	                      
    	          }
    	          System.out.println("arrayLength");
    	          System.out.println(arrayLength);
    	          
    	          names[arrayLength] = Game.p_name;
    	          scores[arrayLength] = Game.SCORE;
    	          
    	          
    	          System.out.println("names[arrayLength]");
    	          System.out.println(names[arrayLength]);
    	          System.out.println("scores[arrayLength]");
    	          System.out.println(scores[arrayLength]);
    	          
    	          int n = arrayLength+1;
    	          Pair arr[] = new Pair [n];
    	          
    	          int rv2 =  0;
    	          while (rv2<=arrayLength) {
    	              arr[rv2] = new Pair (names[rv2],scores[rv2]);
    	              rv2++;
    	              System.out.println("rv2:");
    	              System.out.println(rv2);

    	          }
    	          
    	    	  s.close();

    	    	  PrintWriter writer = null;
        			try {
        	  			writer = new PrintWriter("HighScores.txt", "UTF-8");
        	  		} catch (FileNotFoundException e) {
        	  			// TODO Auto-generated catch block
        	  			e.printStackTrace();
        	  		} catch (UnsupportedEncodingException e) {
        	  			// TODO Auto-generated catch block
        	  			e.printStackTrace();
        	  		}
        		
        		  Compare obj = new Compare();
        		  
                  
        		  
  	              obj.compare(arr, n);
  	              for (int i = arrayLength; i >= 0 ; i--) {
  	            	  
  	            	if(arrayLength==5) {
  	            		if(i==0) {
  	            			
  	            		}else {
        	                  writer.println(arr[i].x + " " + arr[i].y + " ");

      	              }
    	              }else {
    	                  writer.println(arr[i].x + " " + arr[i].y + " ");

    	              }
  	              }
  	              
  	              
  	              writer.close();  


    	          System.out.println("arrayLength:  ");
    	          System.out.println(arrayLength);
    	        
    	        
    	      }
    	    } catch (IOException e) {
    	      System.out.println("An error occurred.");
    	      e.printStackTrace();
    	    }
    	  
    	  
    	  
    	  Game.State = STATE.YOU_WON;  
    	  
      }
    }
  }

public Timer getTimer() {
	return timer;
}


public void setTimer(Timer timer) {
	this.timer = timer;
}

public void setSeconds(int seconds) {
    timer = new Timer();
    timer.schedule(new RemindTask(), seconds * 1000);

}
  
}  