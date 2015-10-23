import java.util.*;

public class Juego {
     
    public static void main(String[] args) {

        int [][] tablero = new int [3][3];
        boolean turno = false;
        int fila = 0;
        int columna = 0;

        while (!isGanador(tablero, !turno) && !terminoJuego(tablero)){
            imprimirMatriz(tablero);
            fila = 0;
            columna = 0;
           
            if (!turno)    System.out.println("Turno de Jugador 1");
            else System.out.println("Turno de Jugador 2");
            fila = insertValue(false);
            columna = insertValue(true);
           
            while (!posicionValida (tablero, fila, columna) ){
                System.out.println("Posicion no valida");
                fila = insertValue(false);
                columna = insertValue(true);
            }
            tablero = colocarFicha(tablero, fila, columna, turno);
            turno = !turno;
        }
        if (isGanador(tablero, !turno)) {
            System.out.println("FELICITACIONES, "+((!turno)?"Jugador 2":"Jugador 1")+" GANÓ!");
            imprimirMatriz(tablero);
        } else
        if (terminoJuego(tablero)) {
            System.out.println("Fin de juego... Sin ganadores.");
            imprimirMatriz(tablero);
        }
       
    }

    public static int insertValue(boolean horizontal) {
        Scanner input = new Scanner(System.in);
        if (horizontal) System.out.println("Ingrese la coordenada Horizontal (1 a 3)");
        else System.out.println("Ingrese la coordenada Vertical (1 a 3)");
        return input.nextInt()-1;
    }
   
    public static void imprimirMatriz(int [][] mat) {
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat.length; j++) {
                System.out.print(mat[i][j] + " ");
            }
            System.out.println();
        }
    }
 
    public static int [][] colocarFicha(int [][] mat, int fila, int columna, boolean turno) {
        if (turno) mat[fila][columna] = 2;
        else mat[fila][columna] = 1;
        return mat;
    }
 
    public static boolean posicionValida(int [][] mat, int fila, int columna) {
        if (fila<0 || fila>2) return false;
        if (columna<0 || columna>2) return false;
        return (mat[fila][columna] == 0);
    }
 
    public static boolean isGanador(int [][] mat, boolean turno) {
        int claims = 0;
        int player = 1;
        if (turno) player = 2;
       
        // Reviso si hay alguno q tenga las diagonales.
        if ((mat[0][0]==player && mat[1][1]==player && mat[2][2]==player)||(mat[0][2]==player && mat[1][1]==player && mat[2][0]==player)) return true;
       
        // Ahora, que lo que costaba menos performance ya pasó y no se cumplió, reviso las horizontales.
        for (int row = 0; row < mat.length; row++){
            for (int col = 0; col < mat.length; col++){
                if (mat[row][col]==player) claims++;
                if (claims == 3 ) return true;
            }
            claims = 0;
        }
        // recorro las verticales.
        for (int col = 0; col < mat.length; col++){
            for (int row = 0; row < mat.length; row++){
                if (mat[row][col]==player) claims++;
                if (claims == 3) return true;
            }
            claims = 0;
        }
       
        return false;
    }
   
    public static boolean terminoJuego(int [][] mat){
       
        int round = 0;
        for (int i = 0; i < mat.length; i++){
            for (int j = 0; j < mat.length; j++){
                if(mat[i][j] != 0){
                    round++;
                    if (round > 8) return true;
                }
            }
        }
        return false;
    }
}