import java.io.IOException;
import java.util.Scanner;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
public class master {
    public static void main(String[] args) {
        String mensaje_menu= """
                Bienvenido a conversion de monedas.
                Elija una Opcion:
                
                0.Salir
                1.Cambiar moneda con otro par.
                2.Ver cambios de una moneda.(Proximamente)
                """;
        consultaCambio consulta = new consultaCambio();
        Scanner lectura = new Scanner(System.in);
        while (true) {
            System.out.println(mensaje_menu);
            int opcion = lectura.nextInt();
            lectura.nextLine();
            if(opcion==0) {
                System.out.println("Usted ha finalizado la aplicación, Hasta pronto.");
                break;
            }
            switch (opcion) {
                case 1:
                    System.out.println("Ingrese la moneda base:");
                    String base = lectura.nextLine();

                    System.out.println("Ingrese la moneda objetivo:");
                    String target = lectura.nextLine();

                    System.out.println("Ingrese la cantidad:");
                    float amount = lectura.nextFloat();
                    lectura.nextLine();  // Consumir la nueva línea
                    try {
                        cambio cambio = consulta.cambioDivisa(base, target, amount);
                        System.out.println("Resultados del cambio de divisas:");
                        System.out.println("Moneda base: " + cambio.baseCode());
                        System.out.println("Moneda objetivo: " + cambio.targetCode());
                        System.out.println("Cantidad inicial: " + amount);
                        System.out.println("Tasa de cambio: " + cambio.conversionRate());
                        System.out.println("Cantidad final: " + cambio.conversionResult());
                        System.out.println("\nPresione Enter para continuar...");
                        System.in.read();

                        //clearConsole();

                    } catch (RuntimeException | IOException e) {
                        System.out.println(e.getMessage());
                        System.out.println("Finalizando la aplicacion...");
                    }
                    break;
                default:
                    System.out.println("Opcion no valida, Intente de nuevo...");
                    break;
            }

        }
        lectura.close();
    }

    public static void clearConsole() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (IOException | InterruptedException ex) {
            ex.printStackTrace();
        }
}
}

