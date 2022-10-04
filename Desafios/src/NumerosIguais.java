// IMPORTANTE: Para ler os dados de entrada do usuário, utilize a classe Scanner, 
// instanciando seu leitor da seguinte forma: "Scanner leitor = new Scanner(System.in);". 
// Por outro lado, para imprimir suas saídas, utilize System.out.print ou System.out.println.

import java.util.*;

public class NumerosIguais {

    public static void main(String[] args) {


        //BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //StringTokenizer st = new StringTokenizer(br.readLine()); // Lê a linha de entrada

        //int A = Integer.parseInt(st.nextToken());
        //int B = Integer.parseInt(st.nextToken());

        //TODO: Imprima se os valores numéricos passados são iguais ou não.

        Scanner leitor = new Scanner(System.in);

        int A = leitor.nextInt();
        int B = leitor.nextInt();
        if (A == B) {
            System.out.println("Sao iguais!");
        } else {
            System.out.println("Nao sao iguais!");
        }

    }
}