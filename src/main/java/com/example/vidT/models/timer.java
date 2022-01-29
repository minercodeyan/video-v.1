/* package com.example.vidT.models;
import java.util.Arrays;
import java.util.List;
import java.util.TimerTask;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity

public class timer extends TimerTask{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long idt;
    private int time;


   public timer() {
    }

    public timer(int time) {
        this.time = time;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    @Override
    public void run() {
      if(this.time<0)
      {this.time--;  System.out.println(time);;}

      else return;
    }

}
*/

