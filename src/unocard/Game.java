/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unocard;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Jovanveer Singh
 */
public class Game {
    public static void jovan(){
    ArrayList<UnoCard> deck = new ArrayList<UnoCard>();
        ArrayList<UnoCard> deck1 = new ArrayList<UnoCard>();
        int win;
        Scanner jovan;
        UnoCard card;
        int index; 
        String color;

        gameLoop:
        while (true)
        {
            deck.clear();
            deck1.clear();
            win = 0;
            card = new UnoCard();
            color = card.color;

            System.out.println("Welcome, Starting game");
            draw(7, deck);
            draw(7, deck1);

            
            for (boolean user = true; win == 0; user ^= true)
            {
                index = 0;
                System.out.println("\nThe First card is: " + card.getFace());

                if (user) 
                {
                    System.out.println("Your Turn:");
                    for (int i = 0; i < deck.size(); i++)
                    {
                        System.out.print(String.valueOf(i + 1) + ". " + 
                        ((UnoCard) deck.get(i) ).getFace() + "\n");
                    }
                    System.out.println(String.valueOf(deck.size() + 1 ) + ". " + "Draw card" + "\n" + 
                                       String.valueOf(deck.size() + 2) + ". " + "Quit");
                   
                    do
                    {
                        System.out.print("\nEnter Your Choice: ");
                        jovan = new Scanner(System.in);
                    } while (!jovan.hasNextInt() );
                    
                    index = jovan.nextInt() - 1;

                    
                    if (index == deck.size() )
                        draw(1, deck);
                    else if (index == deck.size() + 1)
                        break gameLoop;
                    else if ( ((UnoCard) deck.get(index)).canPlace(card, color) )
                    {
                        card = (UnoCard) deck.get(index);
                        deck.remove(index);
                        color = card.color;
                                            
                        if (card.value >= 10)
                        {
                            user = false;

                            switch (card.value)
                            {
                                case 12:
                                System.out.println("Drawing 2 cards");
                                draw(2,deck1);
                                break;

                                case 13: case 14:                     
                                do 
                                {
                                    System.out.print("\nEnter the color you want: ");
                                    jovan = new Scanner(System.in);
                                } while (!jovan.hasNext("R..|r..|G....|g....|B...|b...|Y.....|y.....") );
                                if (jovan.hasNext("R..|r..") )
                                    color = "Red";
                                else if (jovan.hasNext("G....|g....") )
                                    color = "Green";
                                else if (jovan.hasNext("B...|b...") )
                                    color = "Blue";
                                else if (jovan.hasNext("Y.....|y.....") )
                                    color = "Yellow";

                                System.out.println("You choose " + color);
                                if (card.value == 14)
                                {
                                    System.out.println("Drawing 4 cards");
                                    draw(4,deck1);
                                }
                                break;
                            }
                        }
                    } else System.out.println("Invalid choice");


                } else
                {
                    System.out.println("PC Turn" + String.valueOf(deck1.size() ) 
                                        + " cards left " + ((deck1.size() == 1) ? "Uno":"") );
                    
                    for (index = 0; index < deck1.size(); index++)
                    {
                        if ( ((UnoCard) deck1.get(index)).canPlace(card, color) ) // Searching for playable cards
                            break; 
                    }

                    if (index == deck1.size() )
                    {
                         System.out.println("You have no cards to draw");
                         draw(1,deck1);
                    } else 
                    card = (UnoCard) deck1.get(index);
                         deck1.remove(index);
                         color = card.color;
                         System.out.println("Choose" + card.getFace() + " ");

                         
                         if (card.value >= 10)
                         {
                             user = true; // Skipping turn

                             switch (card.value)
                             {
                                 case 12: // Draw 2
                                 System.out.println("Drawing 2 cards for you...");
                                 draw(2,deck);
                                 break;

                                 case 13: case 14:                        
                                 do 
                                 {
                                     color = new UnoCard().color;
                                 } while (color == "none");

                                 System.out.println("New color is " + color);
                                 if (card.value == 14) // Wild draw 4
                                 {
                                     System.out.println("Drawing 4 cards for you...");
                                     draw(4,deck);
                                 }
                                 break;
                             }
                         }
                    }

                  
                    if (deck.size() == 0)
                        win = 1;
                    else if (deck1.size() == 0)
                        win = -1;
                }

            } 
            if (win == 1)
                System.out.println("You win :)");
            else 
                System.out.println("You lose :(");

            System.out.print("\nPlay again? ");
            jovan = new Scanner(System.in);

            if (jovan.next().toLowerCase().contains("n") ){
               
            }
         

        System.out.println("Bye bye");

}
    public static void draw(int cards, ArrayList<UnoCard> deck)
    {
        for (int i = 0; i < cards; i++)
            deck.add(new UnoCard() );
    }
    
}
