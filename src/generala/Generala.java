package generala;
import java.util.Random;
import java.util.Scanner;

public class Generala {
    public static boolean aprobado;
    public static Integer valorDado, cantidadJugadores, numeroTiradas = 3, turnosTotales = 1,turnosRestantes = 1, tablaDePuntajes[][];
    public static Random rnd = new Random();
    public static Scanner sca;
    public static String[] nombreJugadores;
    public static String valorInput="";
    public static Integer jugadaGuardada[];
    public static Integer primeraTirada[];
    public static Integer Eleccion;   // cambiar nombre
    
    public static void TirarDados(Integer cantidadDados) {
        jugadaGuardada = new Integer[5];
        primeraTirada = new Integer [cantidadJugadores];        
        sca=new Scanner (System.in);
        
        do{
            System.out.println("Para tirar los dados, escribí la letra t y luego enter.");
            valorInput = sca.nextLine();
            // 116 es el numero relacionado a la letra t
            if ((int)valorInput.charAt(0)==116) {
                if(cantidadDados == 1) {
                    valorDado = rnd.nextInt(6)+1;
                    jugadaGuardada[0] = valorDado;
                    System.out.print("Valor del dado es " + jugadaGuardada[0] + "\n");
                } else {
                    for (int i = 1; i < cantidadDados + 1; i++) {
                       valorDado = rnd.nextInt(6)+1;
                       jugadaGuardada[i - 1] = valorDado;
                       System.out.print("Valor del dado número " + i + " es " + jugadaGuardada[i - 1] + "\n");
                    }                    
                }
                
                aprobado=true;
                //System.out.println("Bien "+aprobado + valorInput);
            } else {
                aprobado=false;
                //System.out.println("mal "+ aprobado + valorInput);
            }
        }
        while(aprobado == false);
    }
    public static void AsignarJugadores() {
        sca=new Scanner (System.in);
        do{
            System.out.println("El número de jugadores tiene que ser entre 2 y 4.");
            System.out.println("Indicá el número de jugadores: ");
            cantidadJugadores = sca.nextInt();
            if( cantidadJugadores > 1 && cantidadJugadores <5) {
                aprobado=true;
                //System.out.println("Bien "+aprobado);
            } else {
                aprobado=false;
                //System.out.println("mal "+aprobado);
            }
        } while(aprobado==false);
        
        System.out.println("El número de jugadores es: " + cantidadJugadores); 
    }
    public static void QuienEmpiezaAJugar() {
        nombreJugadores = new String [cantidadJugadores];
        mayorNumero = new Integer [cantidadJugadores];
        
        for (int i = 1; i < cantidadJugadores + 1; i++) {
            System.out.println("");
            System.out.println("Escribí el nombre del jugador " + i);
            nombreJugadores[i - 1] = sca.next();
            //System.out.println("El nombre del jugador " + i  + " es " + nombreJugadores[i - 1] );
        }
        for( int i = 1; i < cantidadJugadores + 1; i++) {
            System.out.println("");
            System.out.println(nombreJugadores[i - 1] + " tiene que tirar un dado.");
            TirarDados(1);
            //El valor que sacó cada usuario lo guardo en otra variable para desp calcularle el mayor
            primeraTirada[i - 1] = jugadaGuardada[0];
            System.out.println("Imprimo el valor de la primera tirada de esta persona " + primeraTirada[i - 1] + primeraTirada[0]);
            System.out.println(primeraTirada[1]);
        }
        System.out.println(primeraTirada[0]);
        OrdenarMayor();
        //mayorNumero[i - 1] = valorDado;
        //System.out.println("El mayor numero es: " + mayorNumero[0] + ", el segudo mayor es " + mayorNumero[1]);
    }
    public static void Turno() {
        for (int i = 0; i < cantidadJugadores; i++) {
            System.out.println("");
            System.out.println("Es el turno de " + nombreJugadores[i] + ". Le quedan 3 tiradas.");
            PrimerTirada();
            //System.out.println("A " + nombreJugadores[i] + " le quedan 2 tiradas.");
            //segundaTirada();
            //System.out.println("A " + nombreJugadores[i] + " le queda 1 tirada.");
            //tercerTirada()
        }
    }
    public static void PrimerTirada() { 
        //sca=new Scanner (System.in);
        numeroTiradas --;             
        TirarDados(5);
        // Muestro la jugada de los 5 dados
        System.out.println(jugadaGuardada[0] + " " + jugadaGuardada[1] + "  " + jugadaGuardada[2] + " " + jugadaGuardada[3] + " " + jugadaGuardada[4]);      
        System.out.println("Qué dados desea descartar?");
        System.out.println("1, 2, 3, 4, 5 o 0 para ninguno");
        Eleccion = sca.nextInt();
        System.out.println(Eleccion);
        




        //AsignarPuntajes();
    }
    public static void AsignarPuntajes() {
        tablaDePuntajes = new Integer[11][cantidadJugadores];//[][max3]
        tablaDePuntajes[2][1] = 2;
        tablaDePuntajes[3][0]= 4;
        System.out.println("El puntaje asignado es: "+ tablaDePuntajes[3][0]);   
    }    
    public static void OrdenarMayor(){
        //ej primerTirada [] a ={4, 5, 4, 3};
        System.out.println("numeros para ordenar " + primeraTirada[0] + "" + primeraTirada[1]);
        
    }
    
    public static void main(String[] args) {
        System.out.println("Empieza el juego.");
        System.out.println("");
        AsignarJugadores();
        QuienEmpiezaAJugar();
        for ( int i = 0; i < turnosTotales; i++) {
            System.out.println("");
            // Chequear este calculo del + 1
            System.out.println("Es el turno número " + (turnosTotales + 1 - turnosRestantes + "."));
            //Turno();
            turnosRestantes --;
        }
        //AsigarPuntaje();
        System.out.println("");
        System.out.println("Terminó el juego.");
    }
}


// Cambiar jugadaGuardada a una matriz de 5x3 (dados*tiradas). desp el profe dijo que es mas dificil asi    

// Profe dice que es mejor que el usuario diga que descartar
// Tangerine hizo que para que el usuario elija que dados descartar el usuario escriba 0,
// haciendo un do while con la condicion

//En la clase decidimos hacer en conjunto
// el usuario debera elegir que dados vovlvera a tirar (1,2,3,4,5 o ninguno). 
//La eleccion la vamos a hacer a traves de una esctruca DO While
// El DO WHILE va a revisar en cada iteracion que la seleccion no se haya hecho 
// y va a tener un maximo de 5 iteraciones
// profe hizo un metodo numeroDeTirada () if numTirada < 4, puedeJugar=true sino false