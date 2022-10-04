import java.util.Scanner;

public class BuscaSequencial {
    public static void main(String[] args) {

        Scanner leitor = new Scanner(System.in);
        System.out.println("entre com o valor: ");

        int[] elementos = {64, 137, -16, 43, 67, 81, -90, 212, 10, 75};

        //TODO: Retorne o valor do elemento no Array junto de sua respectiva posição.

        int elemento = leitor.nextInt();
        boolean flag = false;
        for(int i = 0; i < 10; i++) {
            if (elementos[i] == elemento) {
                System.out.println("Achei " + elemento + " na posicao " + i);
                flag = true;
            }
        }
        if(!flag){
            System.out.println("Numero " + elemento + " nao encontrado!");
        }

    }
}
