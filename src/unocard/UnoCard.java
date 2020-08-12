/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unocard;
import java.util.Random;
/**
 *
 * @author Jovanveer Singh
 */
public class UnoCard
{
    public String color;
    public int value;
    private Random random;
    private String face;

    public UnoCard(int v, String c)
    {
        value = v;
        color = c; 
    }
    public UnoCard()
    {
        random = new Random();
        value = random.nextInt(28);
        
        if (value >= 14)
            value -= 14;
        
        random = new Random();
        switch(random.nextInt(4) )
        {
            case 0: color = "Red"; 
                break;
            case 1: color = "Green"; 
                break;
            case 2: color = "Blue"; 
                break;
            case 3: color = "Yellow"; 
                break;
        }
        // If the card is a wild card
        if (value >= 13)
            color = "none";
    }

    public String getFace()
    {
        /* Returns the face of the card (what the player sees)
         * Ex. [Red 5]
         */
        face = "[";
        if (color != "none")
        {
            face += this.color + " ";
        }

        switch(this.value)
        {
            default: face += String.valueOf(this.value); 
                break;
            case 10: face += "Next"; 
                break;
            case 11: face += "Backwards"; 
                break;
            case 12: face += "Draw 2"; 
                break;
            case 13: face += "Wild"; 
                break;
            case 14: face += "Wild Draw 4"; 
                break;
        }
        face += "]";
        return face;
    }

  
    public boolean canPlace(UnoCard o, String c)
    {
        if (this.color == c)
            return true;
        else if (this.value == o.value)
            return true;
        else if (this.color == "none")
            return true;
        return false;
    }
}
