import java.io.IOException;
import java.util.Scanner;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
public class master {
    public static void main(String[] args) {

        consultaCambio consulta = new consultaCambio();
        Scanner lectura = new Scanner(System.in);
        System.out.println("Escriba el par que quiere cambiar y la cantidad.(moneda base, moneda objetivo, cantidad)");

        try {
            String divisas = lectura.nextLine();
            String[] parts = divisas.split(" ");
            String base = parts[0];
            String target = parts[1];
            float amount = Float.parseFloat(parts[2]);
            System.out.println("su divisas son: " + base + " y " + target + " cambias... " + amount);
            cambio cambio = consulta.cambioDivisa(base, target, amount);
            System.out.println(cambio);

        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            System.out.println("Finalizando la aplicacion...");
        }
    }
}

