/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package problemaExamen;

import java.util.Random;

/**
 *
 * @author eduar
 */
public class generadorNumeros {
    public static void main(String[] args) {
        Random random = new Random();
        int numeroEntero;
        
        for(int i=0; i<10000;i++){
            numeroEntero = random.nextInt(-1000,1000) + 1;
            System.out.println(numeroEntero);
        }
    }
    
}
