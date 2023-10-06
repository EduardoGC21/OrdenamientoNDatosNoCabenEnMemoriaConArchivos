/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author eduar
 */

//ordenar n datos que no caben en un arreglo en la memoria mediante 4 archivos y haciendo una especie de merge sort pero volteado, por ventanas de 2^n
//se necesitan 5 archivos, el archivo a ordenar, archivoA, archivoB, archivoC, archivoD.
//termina ordenandolo en arhcivoOrdenar
public class problemaVF {
    
    //fusion inicial para tener ventanas de dos
    public static void fusion(String archivoEntrada1, String archivoEntrada2, String archivoSalida1, String archivoSalida2, int contA) throws FileNotFoundException, IOException{    
        BufferedReader lector1 = new BufferedReader(new FileReader(archivoEntrada1));
        BufferedReader lector2 = new BufferedReader(new FileReader(archivoEntrada2));
        BufferedWriter escritor1 = new BufferedWriter(new FileWriter(archivoSalida1));
        BufferedWriter escritor2 = new BufferedWriter(new FileWriter(archivoSalida2));
        String linea1, linea2;     
        int n1,n2;
        for(int j=0; j<contA;j++){
            linea1=lector1.readLine();
            linea2=lector2.readLine();
            if(j%2==0){//mod nos indica en que archivo ponerlo
                if(linea1!=null && linea2 !=null){
                    n1= Integer.parseInt(linea1);
                    n2= Integer.parseInt(linea2);
                    if(n1<=n2){
                        escritor1.write(linea1);
                        escritor1.newLine();
                        escritor1.write(linea2);
                        escritor1.newLine();
                    }else{
                        escritor1.write(linea2);
                        escritor1.newLine();
                        escritor1.write(linea1);
                        escritor1.newLine();
                    }
                }else{//termina de poner las linea pues si hubo por ejemplo n datos, archivoA (escritor 1) tendra n+1 datos que archivoB que tendra n
                    escritor1.write(linea1);
                    escritor1.newLine();
                }
            }else{
                if(linea1!=null && linea2 !=null){
                    n1= Integer.parseInt(linea1);
                    n2= Integer.parseInt(linea2);
                    if(n1<n2){
                        escritor2.write(linea1);
                        escritor2.newLine();
                        escritor2.write(linea2);
                        escritor2.newLine();
                    }else{
                        escritor2.write(linea2);
                        escritor2.newLine();
                        escritor2.write(linea1);
                        escritor2.newLine();
                    }
                }    
            }
        }
        lector1.close();
        lector2.close();
        escritor1.flush();
        escritor2.flush();
        escritor1.close();
        escritor2.close();
        
    }
    
    
    //fusion para ventanas de 2^n
    public static void fusion2(String archivoEntrada1, String archivoEntrada2, String archivoSalida1, String archivoSalida2, int contA, int ventana) throws FileNotFoundException, IOException{    
        BufferedReader lector1 = new BufferedReader(new FileReader(archivoEntrada1));
        BufferedReader lector2 = new BufferedReader(new FileReader(archivoEntrada2));
        BufferedWriter escritor1 = new BufferedWriter(new FileWriter(archivoSalida1));
        BufferedWriter escritor2 = new BufferedWriter(new FileWriter(archivoSalida2));
        String linea1 = lector1.readLine();
        String linea2 = lector2.readLine();
        int numrep= (contA/ventana)+1;//+1 pues si no no funciona si no es 2^n numeros
        int n1, n2;
        
        for(int i=0; i< numrep; i++){
            int j=0;
            int k=0;
            if(i%2==0){//alternar entre archivos
                while (linea1 != null && linea2 != null && j<ventana && k<ventana) {
                // Comparar las líneas y escribir la menor
                    n1= Integer.parseInt(linea1);
                    n2= Integer.parseInt(linea2);
                    if (n1<=n2) {
                        escritor1.write(linea1);
                        escritor1.newLine();
                        linea1 = lector1.readLine();
                        j++;
                    } else {
                        escritor1.write(linea2);
                        escritor1.newLine();
                        linea2 = lector2.readLine();
                        k++;
                    }
                }
                //terminamos copiar
                while (linea1 != null && j<ventana) {
                    escritor1.write(linea1);
                    escritor1.newLine();
                    linea1 = lector1.readLine();
                    j++;
                }

                while (linea2 != null && k<ventana) {
                    escritor1.write(linea2);
                    escritor1.newLine();
                    linea2 = lector2.readLine();
                    k++;
                }
            }else{
                while (linea1 != null && linea2 != null && j<ventana && k<ventana) {
                    // Comparar las líneas y escribir la menor en el archivo C
                    n1= Integer.parseInt(linea1);
                    n2= Integer.parseInt(linea2);
                    if (n1<=n2) {
                        escritor2.write(linea1);
                        escritor2.newLine();
                        linea1 = lector1.readLine();
                        j++;
                    } else {
                        escritor2.write(linea2);
                        escritor2.newLine();
                        linea2 = lector2.readLine();
                        k++;
                    }
                }
                //terminamos copiar
                while (linea1 != null && j<ventana) {
                    escritor2.write(linea1);
                    escritor2.newLine();
                    linea1 = lector1.readLine();
                    j++;
                }

                while (linea2 != null && k<ventana) {
                    escritor2.write(linea2);
                    escritor2.newLine();
                    linea2 = lector2.readLine();
                    k++;
                }
            }
            
        }
        lector1.close();
        lector2.close();
        escritor1.flush();
        escritor2.flush();
        escritor1.close();
        escritor2.close();
        
    }
    
    
    //termina de ordenar en archivo original
    public static void fusionF(String archivoEntrada1, String archivoEntrada2, String archivoSalida1, int ventana, boolean impar, int np) throws FileNotFoundException, IOException{    
        BufferedReader lector1 = new BufferedReader(new FileReader(archivoEntrada1));
        BufferedReader lector2 = new BufferedReader(new FileReader(archivoEntrada2));
        BufferedWriter escritor1 = new BufferedWriter(new FileWriter(archivoSalida1));
        String linea1 = lector1.readLine();
        String linea2 = lector2.readLine();
        int n1, n2;
        int j=0;
        int k=0;
        if(impar){//para que ponga el dato impar que quedo volando
            while (linea1 != null && linea2 != null && j<ventana && k<ventana) {
            // Comparar las líneas y escribir la menor
                n1= Integer.parseInt(linea1);
                n2= Integer.parseInt(linea2);
                if(impar && np<=n1 && np<=n2){ //si es menor a los dos se pone ese primero
                    impar=!impar;
                    escritor1.write(np+"");
                    escritor1.newLine();
                }
                if (n1<=n2) {
                    escritor1.write(linea1);
                    escritor1.newLine();
                    linea1 = lector1.readLine();
                    j++;
                } else {
                    escritor1.write(linea2);
                    escritor1.newLine();
                    linea2 = lector2.readLine();
                    k++;
                }
            }
            //terminamos copiar
            while (linea1 != null && j<ventana) {
                if(impar && np<=Integer.parseInt(linea1)){//si es menor a uno que sobro se pone
                    escritor1.write(np+"");
                    escritor1.newLine();
                    impar=!impar;
                 }
                escritor1.write(linea1);
                escritor1.newLine();
                linea1 = lector1.readLine();
                j++;
                
            }

            while (linea2 != null && k<ventana) {//si es menor a uno que sobro se pone
                if(impar && np<=Integer.parseInt(linea2)){
                    escritor1.write(np+"");
                    escritor1.newLine();
                    impar=!impar;
                }
                escritor1.write(linea2);
                escritor1.newLine();
                linea2 = lector2.readLine();
                k++;
            }
            
            if(impar){//si fue mayor a todos, ponemos al final
                escritor1.write(np+"");
                escritor1.newLine();
                impar=!impar;
            }
            lector1.close();
            lector2.close();
            escritor1.flush();
            escritor1.close();
        }else{//fue n par
            while (linea1 != null && linea2 != null && j<ventana && k<ventana) {
            // Comparar las líneas y escribir la menor
                n1= Integer.parseInt(linea1);
                n2= Integer.parseInt(linea2);
                if (n1<=n2) {
                    escritor1.write(linea1);
                    escritor1.newLine();
                    linea1 = lector1.readLine();
                    j++;
                } else {
                    escritor1.write(linea2);
                    escritor1.newLine();
                    linea2 = lector2.readLine();
                    k++;
                }
            }
            //terminamos copiar
            while (linea1 != null && j<ventana) {
                escritor1.write(linea1);
                escritor1.newLine();
                linea1 = lector1.readLine();
                j++;
            }

            while (linea2 != null && k<ventana) {
                escritor1.write(linea2);
                escritor1.newLine();
                linea2 = lector2.readLine();
                k++;
            }

            lector1.close();
            lector2.close();
            escritor1.flush();
            escritor1.close();
        }
        
    }
    
    public static void imprimirContenidoArchivo(String nombreArchivo) {
        try {
            File archivo = new File(nombreArchivo);
            Scanner scanner = new Scanner(archivo);

            while (scanner.hasNextLine()) {
                String linea = scanner.nextLine();
                System.out.println(linea);
            }

            scanner.close();
        } catch (FileNotFoundException e) {
            System.err.println("No se pudo encontrar el archivo: " + nombreArchivo);
            e.printStackTrace();
        }
    }

    //lectura archivo
    public static void main(String[] args) {
        //instanciamos todos los archivos a leer y escribir
        try (BufferedReader arOR = new BufferedReader(new FileReader("archivoOrdenar.txt"));
             BufferedWriter arAW = new BufferedWriter(new FileWriter("archivoA.txt")); 
             BufferedWriter arBW = new BufferedWriter(new FileWriter("archivoB.txt"))) {
            
            String linea;
            String lineaaux=null;
            int cont=0;
            int contA=0;
            int contB=0;
            
            while ((linea = arOR.readLine()) != null) {//mientras haya linea
                cont+=1;
                if(cont%2==1){
                    contA+=1;   
                    arAW.write(linea); // Escribe la línea en el archivo de destino
                    arAW.newLine(); // Agrega un salto de línea después de cada línea
                }else{
                    arBW.write(linea);
                    arBW.newLine();
                    contB+=1;
                }
                lineaaux=linea;
            }
            
            //funciona si se dividen en mitades pares, pero si no son pares A tiene un elemento mas, este lo vamos a guardar y acomodar al final
            boolean meterImp = false;
            int numExtra = 0;
            if((contA==2 && cont==3)||(contA >1 && contA%2==1)){//si fue impar, tomando caso especial 3 datos, y el caso contA >1
                contA--;
                numExtra = Integer.parseInt(lineaaux);
                meterImp = true;
            }
            //cerramos
            arAW.flush();
            arBW.flush();
            arOR.close();
            arAW.close();
            arBW.close();

            
            
            //ya tenemos una mitad en archivo A y otra en archivo B
            //a lo mas hara log_2(cont) veces el ordenamiento
            // Calcula el logaritmo en base 2
            System.out.println(cont);
            int i=1;
            boolean alternar = true;
            while (Math.pow(2, i) < cont) {// intercambia a-b, c-d,
                if(i==1){//pasarlo a ventanas de 2 unico caso distinto
                    //2
                    fusion("archivoA.txt", "archivoB.txt", "archivoC.txt", "archivoD.txt", contA);
                    /*
                    System.out.println("");
                    System.out.println("Ventanas 2");
                    System.out.println("C");
                    imprimirContenidoArchivo("archivoC.txt");
                    System.out.println("D");
                    imprimirContenidoArchivo("archivoD.txt");
                    */
                }else{
                    if (alternar) {//vamos creando ventanas de 2^n n>1
                        //8,32,
                        fusion2("archivoA.txt", "archivoB.txt", "archivoC.txt", "archivoD.txt", contA, (int) Math.pow(2, i)/2);//math.pow manda como estan ordenadas actualmente en ventanas
                        /*
                        System.out.println("ArC");
                        imprimirContenidoArchivo("archivoC.txt");
                        System.out.println("ArD");
                        imprimirContenidoArchivo("archivoD.txt");
                        */
                        
                    } else {
                        //4,16,64
                        fusion2("archivoC.txt", "archivoD.txt", "archivoA.txt", "archivoB.txt", contA, (int) Math.pow(2, i)/2);
                        /*
                        System.out.println("ArA");
                        imprimirContenidoArchivo("archivoA.txt");
                        System.out.println("ArB");
                        imprimirContenidoArchivo("archivoB.txt");
                        */
                        
                    }
                }

                alternar = !alternar; // Cambiar entre A-B y C-D de lectura y escritura
                i++;
            }
            
            //termina ordenando en original
            if (alternar) {
                fusionF("archivoA.txt", "archivoB.txt", "archivoOrdenar.txt",(int) Math.pow(2, i)/2, meterImp, numExtra);
            } else {
                fusionF("archivoC.txt", "archivoD.txt", "archivoOrdenar.txt",(int) Math.pow(2, i)/2, meterImp, numExtra);
            }
            //impresion para verificar
            //imprimirContenidoArchivo("archivoOrdenar.txt");
            
            //metemos ultimo dato que quedo volando
            
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}